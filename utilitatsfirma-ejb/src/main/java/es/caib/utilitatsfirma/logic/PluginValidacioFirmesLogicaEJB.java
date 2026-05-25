package es.caib.utilitatsfirma.logic;

import es.caib.utilitatsfirma.persistence.PluginJPA;
import es.caib.evidenciesib.api.externa.client.evidencies.v1.api.EvidenciesApi;
import es.caib.evidenciesib.api.externa.client.evidencies.v1.services.ApiClient;
import es.caib.evidenciesib.api.externa.client.evidencies.v1.services.ApiException;
import es.caib.utilitatsfirma.commons.utils.Configuracio;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.datasource.IDataSource;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaNonCryptographicInformation;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidateSignatureResponse;
import es.caib.utilitatsfirma.logic.utils.I18NLogicUtils;
import es.caib.utilitatsfirma.logic.utils.ValidacioException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.v3.utils.ISO8601;
import org.fundaciobit.pluginsib.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureRequestedInformation;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureRequest;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.validatesignature.api.ValidationStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author anadal
 */
@Stateless(name = "PluginValidacioFirmesLogicaEJB")
public class PluginValidacioFirmesLogicaEJB extends AbstractPluginIBLogicaEJB<IValidateSignaturePlugin>
        implements PluginValidacioFirmesLogicaLocal {

    @EJB(mappedName = EstadisticaLogicaService.JNDI_NAME)
    protected EstadisticaLogicaService estadisticaLogicaEjb;

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_VALIDACIOFIRMES;
    }

    @Override
    protected String getName() {
        return "Modul de Validació de Firmes";
    }

    @Override
    public PassarelaValidateSignatureResponse validateSignature(String signType, IDataSource signatureDS,
            IDataSource documentDetachedDS, String languageUI, String usuariAplicacioID, int entorn,
            SignatureRequestedInformation sri) throws ValidacioException {

        try {
            Long pluginValidateSignatureID = getCurrentPluginValidateSignatureID();

            if (pluginValidateSignatureID == null) {
                // No s'ha de validar
                log.info("pluginValidateSignatureID is null");
                return null;
            }

            byte[] documentDetached;
            if (documentDetachedDS == null) {
                documentDetached = null;
            } else {
                try {
                    documentDetached = documentDetachedDS.getByteArray();
                } catch (Exception e1) {
                    // XYZ ZZZ traduir missatge
                    String msg = "No s'ha pogut llegir el fitxer detached per la validació: " + e1.getMessage();
                    throw new I18NException("genapp.comodi", msg);
                }
            }

            byte[] signature;
            try {
                signature = signatureDS.getByteArray();
            } catch (Exception e1) {
                // XYZ ZZZ traduir missatge
                String msg = "No s'ha pogut llegir el fitxer de Firma per la validació: " + e1.getMessage();
                throw new I18NException("genapp.comodi", msg);
            }

            if (log.isDebugEnabled()) {
                log.debug("Signature bytes[] => " + signature.length);
                log.debug("DocumentDetached bytes[] => "
                        + ((documentDetached == null) ? "NULL" : ("" + documentDetached.length)));
            }

            ValidateSignatureResponse response = internalValidateSignature(pluginValidateSignatureID, signType,
                    signature, documentDetached, languageUI, sri);

            int tipus;
            switch (response.getValidationStatus().getStatus()) {
                case ValidationStatus.SIGNATURE_ERROR:
                    tipus = Constants.ESTADISTICA_TIPUS_VALIDACIO_ERROR;
                break;
                case ValidationStatus.SIGNATURE_VALID:
                    tipus = Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_VALIDA;
                break;
                case ValidationStatus.SIGNATURE_INVALID:
                    tipus = Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_INVALIDA;
                break;
                default:
                    tipus = 0;
            }

            PassarelaNonCryptographicInformation nonCryptoInfo = null;

            if (tipus == Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_VALIDA
                    || tipus == Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_INVALIDA) {

                // mirar si la firma es NO-Criptogràfica
                // Miram si es una firma PAdES NO-Criptogràfica

                // Per ara l'únic sistema que tenim és EvidènciesIB                
                nonCryptoInfo = checkEvidenciesIB(signature, response, languageUI);
                /*
                if (nonCryptoInfo == null) {
                    // Revisam altres sistemes de firma no criptogràfica que es puguin anar afegint en el futur
                }
                */
            }

            if (tipus != 0) {
                estadisticaLogicaEjb.addEstadistica(tipus, 1, usuariAplicacioID, entorn);
            }

            return new PassarelaValidateSignatureResponse(response, nonCryptoInfo);

        } catch (Exception e) {
            String message;
            if (e instanceof I18NException) {
                message = I18NLogicUtils.getMessage((I18NException) e, new Locale(languageUI));
            } else {
                message = e.getMessage();
            }
            log.error("Error al plugin de validació de firma: " + message);
            estadisticaLogicaEjb.addEstadistica(Constants.ESTADISTICA_TIPUS_VALIDACIO_ERROR, 1, usuariAplicacioID,
                    entorn);
            throw new ValidacioException(message, e);
        }
    }

    public final PassarelaNonCryptographicInformation checkEvidenciesIB(byte[] signature,
            ValidateSignatureResponse response, String languageUI) throws I18NException {
        if (ValidateSignatureResponse.SIGNTYPE_PAdES.equals(response.getSignType())) {

            // Amb PDFBox obtenir si de les propietats del PDF n'hi ha una que sigui "EvidenciesIB.EvidenciaID"

            // Leer el PDF a partir de los byte[] del PDF que se encuentran en la variable "signature" utilizando PDFBOX

            // Leer el PDF a partir de los byte[] de la variable "signature"
            try (PDDocument document = Loader.loadPDF(signature)) {

                PDDocumentInformation info = document.getDocumentInformation();

                // Obtener la propiedad "EvidenciesIB.EvidenciaID"
                // En PDFBox, las propiedades personalizadas se consultan con getCustomMetadataValue
                String evidenciaId = info.getCustomMetadataValue("EvidenciesIB.EvidenciaID");

                String evidenciaIdEncrypted = info.getCustomMetadataValue("EvidenciesIB.EvidenciaID.encrypted");

                log.info("EVIDENCIA: " + evidenciaId);
                log.info("evidenciaIdEncrypted: " + evidenciaIdEncrypted);

                if (evidenciaId != null && evidenciaIdEncrypted != null) {

                    int index = 0;
                    while (true) {
                        index++;
                        // Cridam a evidenciesIB per la validació d'evidències

                        String url = Configuracio.getEvidenciesIbUrl(index);
                        String username = Configuracio.getEvidenciesIbUsername(index);
                        String password = Configuracio.getEvidenciesIbPassword(index);

                        if (url == null || username == null || password == null) {
                            // Ja no hi ha més servidors on mirar
                            break;
                        } else {

                            ApiClient apiclient = new ApiClient();

                            //        SimpleModule modul = new SimpleModule();
                            //        modul.addDeserializer(byte[].class, new MyByteArraySerializer());
                            //        apiclient.getJSON().getContext(null).registerModule(modul);

                            apiclient.setBasePath(url);
                            apiclient.setUsername(username);
                            apiclient.setPassword(password);

                            apiclient.setDebugging(true);

                            EvidenciesApi api = new EvidenciesApi(apiclient);

                            Map<String, Object> props;

                            try {
                                props = api.getbasicproperties(evidenciaIdEncrypted, languageUI);
                            } catch (ApiException ae) {

                                log.error(
                                        "Error cridant a EvidenciesIB per validar l'evidència amb ID encriptat "
                                                + evidenciaIdEncrypted + " al servidor " + url + ": " + ae.getMessage(),
                                        ae);

                                // Següent servidor
                                continue;
                            }

                            if (props != null && props.size() > 5) {

                                PassarelaNonCryptographicInformation nonCryptoInfo = new PassarelaNonCryptographicInformation();

                                Map<String, String> additionalInformation = new HashMap<String, String>();

                                for (Map.Entry<String, Object> entry : props.entrySet()) {

                                    //log.debug("Propietat de l'evidència: " + entry.getKey() + " => " + entry.getValue());
                                    final String key = entry.getKey();
                                    final String value = (entry.getValue() != null) ? entry.getValue().toString()
                                            : null;

                                    if ("EvidenciaID.encrypted".equals(key)) {
                                        nonCryptoInfo.setNonCryptographicSignatureIdentifier(value);
                                    } else if ("person.administrationid".equals(key)) {
                                        nonCryptoInfo.setAdministrationID(value);
                                    } else if ("person.name".equals(key)) {
                                        nonCryptoInfo.setName(value);
                                    } else if ("person.surname1".equals(key)) {
                                        nonCryptoInfo.setSurname1(value);
                                    } else if ("person.surname2".equals(key)) {
                                        nonCryptoInfo.setSurname2(value);
                                    } else if ("sign.intention.date".equals(key)) {

                                        try {
                                            Date parsedDate = ISO8601.ISO8601ToDate(value);
                                            nonCryptoInfo.setDateOfSignature(parsedDate);
                                        } catch (Exception e) {
                                            log.error("Error parsing date from EvidenciesIB property"
                                                    + " 'sign.intention.date': " + value, e);
                                        }

                                    } else if ("url.downloadfile".equals(key)) {
                                        nonCryptoInfo.setUrlToDownloadFile(value);

                                    } else if ("url.web".equals(key)) {
                                        nonCryptoInfo.setUrlToWebInfo(value);

                                    } else {
                                        // "EvidenciaID"
                                        // login.date, login.id, login.properties.sha256, login.qaa, login.subtype, login.type

                                        additionalInformation.put(key, value);
                                    }

                                }

                                //nonCryptoInfo.set Non EvidenciaId(props.get("EvidenciaID").toString());

                                nonCryptoInfo.setNonCryptographicSystemName("EvidenciesIB");
                                nonCryptoInfo.setNonCryptographicSystemCode("EVI");
                                nonCryptoInfo.setAdditionalInformation(additionalInformation);

                                return nonCryptoInfo;

                            } else {
                                log.debug("No s'han obtingut propietats de l'evidència des d'EvidenciesIB (index = "
                                        + index
                                        + "). És possible que l'evidència no existeixi o que les credencials siguin incorrectes");
                            }
                        }

                    } // Final Bucle 

                } else {
                    log.debug("No s'ha trobat la propietat EvidenciesIB.EvidenciaID al PDF");
                }

            } catch (Throwable th) {
                String msg = "Error llegint el PDF amb PDFBox per mirar si és una firma no criptogràfica de EvidenciesIB: "
                        + th.getMessage();
                log.error(msg, th);
                throw new I18NException(th, "genapp.comodi", msg);
            }

        }

        return null;
    }

    @Override
    public SignatureRequestedInformation getSignatureRequestedInformation(String languageID)
            throws I18NException, ValidacioException {
        Long pluginValidateSignatureID = getCurrentPluginValidateSignatureID();
        if (pluginValidateSignatureID == null) {
            // No s'ha de validar
            log.info("pluginValidateSignatureID is null");
            return null;
        }
        IValidateSignaturePlugin validator = getInstanceByPluginID(pluginValidateSignatureID);
        return validator.getSupportedSignatureRequestedInformation();
    }

    protected Long getCurrentPluginValidateSignatureID() throws I18NException, ValidacioException {
        //log.info("validateSignature");

        // TODO Per ara seleccionarà el plugin de validació de firmes a partir dels actius i ordenats 
        // segons el camp ordre

        List<Long> ids = this.executeQuery(PLUGINID,
                Where.AND(ACTIU.equal(true), TIPUS.equal(Constants.TIPUS_PLUGIN_VALIDACIOFIRMES)), new OrderBy(ORDRE));

        if (ids == null || ids.isEmpty()) {
            String msg = "No hi ha cap plugin de validació de firmes actiu per realitzar la validació";
            throw new ValidacioException(msg);
        }

        Long pluginValidateSignatureID = ids.get(0);
        return pluginValidateSignatureID;
    }

    protected ValidateSignatureResponse internalValidateSignature(Long pluginValidateSignatureID, String signType,
            byte[] signature, byte[] documentDetachedFile, String languageUI, SignatureRequestedInformation sri)
            throws I18NException {

        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("PLUGIN ID VALIDACIO FIRMES:  " + pluginValidateSignatureID);
        }

        IValidateSignaturePlugin validator = getInstanceByPluginID(pluginValidateSignatureID);

        ValidateSignatureRequest vsr = new ValidateSignatureRequest();
        vsr.setLanguage(languageUI);
        vsr.setSignatureRequestedInformation(sri);
        vsr.setSignatureData(signature);
        vsr.setSignedDocumentData(documentDetachedFile);

        String error = validator.filter(vsr);
        if (error != null) {
            // XYZ ZZZ Falta Traduir missatge TODO
            PluginJPA plugin = findByPrimaryKey(pluginValidateSignatureID);
            throw new I18NException("genapp.comodi",
                    "El validador de firmes " + plugin.getNom().getTraduccio(languageUI).getValor()
                            + " no suporta validar fitxers del tipus " + signType
                            + ". El problema amb el validador és el següent: " + error);
        }
        ValidateSignatureResponse vsresp;
        try {
            vsresp = validator.validateSignature(vsr);
            if (vsresp == null || vsresp.getValidationStatus() == null) {
                // XYZ ZZZ TRA
                throw new Exception("La resposta del validador o el camp estat del validador valen null");
            }

            if (vsresp != null && vsresp.getValidationStatus() != null
                    && vsresp.getValidationStatus().getStatus() == ValidationStatus.SIGNATURE_VALID) {
                // Parxe per a evitar que es mostri un error en el cas que la validació sigui correcta
                vsresp.getValidationStatus().setErrorMsg(null);
                vsresp.getValidationStatus().setErrorException(null);
            }

            return vsresp;

            //log.info("validateSignature status = " + vsresp.getValidationStatus().getStatus());
        } catch (Exception e) {
            PluginJPA plugin = findByPrimaryKey(pluginValidateSignatureID);

            // XYZ ZZZ TRA
            String msg = "Error no controlat cridant al validador de firmes "
                    + plugin.getNom().getTraduccio(languageUI).getValor() + ": " + e.getMessage();

            if (e.getCause() != null) {
                String causeMsg = e.getCause().getMessage();
                if (causeMsg.contains("413: Request Entity Too Large")) {
                    causeMsg = "El fitxer de la signatura o el document associat és massa gran per ser validat pel validador de firmes "
                            + plugin.getNom().getTraduccio(languageUI).getValor();
                }
                msg += " (Detalls: " + causeMsg + ")";
            }

            log.error(msg, e);
            // XYZ ZZZ Traduir
            throw new I18NException("genapp.comodi", msg);
        }

    }

}
