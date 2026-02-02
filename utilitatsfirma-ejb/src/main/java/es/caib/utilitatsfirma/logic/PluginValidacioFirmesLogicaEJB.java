package es.caib.utilitatsfirma.logic;


import es.caib.utilitatsfirma.persistence.PluginJPA;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.datasource.IDataSource;
import es.caib.utilitatsfirma.logic.utils.I18NLogicUtils;
import es.caib.utilitatsfirma.logic.utils.ValidacioException;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureRequestedInformation;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureRequest;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.validatesignature.api.ValidationStatus;

import javax.ejb.Stateless;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author anadal
 */
@Stateless(name = "PluginValidacioFirmesLogicaEJB")
public class PluginValidacioFirmesLogicaEJB extends AbstractPluginIBLogicaEJB<IValidateSignaturePlugin>
        implements PluginValidacioFirmesLogicaLocal {


    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_VALIDACIOFIRMES;
    }

    @Override
    protected String getName() {
        return "Modul de Validació de Firmes";
    }

    @Override
    public ValidateSignatureResponse validateSignature(String signType,
            IDataSource signatureDS, IDataSource documentDetachedDS, String languageUI)
            throws ValidacioException {

        try {
            //log.info("validateSignature");
            
            // TODO Per ara seleccionarà el plugin de validació de firmes a partir dels actius i ordenats 
            // segons el camp ordre
            
            List<Long> ids = this.executeQuery(PLUGINID, Where.AND(ACTIU.equal(true), TIPUS.equal(Constants.TIPUS_PLUGIN_VALIDACIOFIRMES)), new OrderBy(ORDRE) );
            
            if (ids == null || ids.isEmpty()) {
                String msg = "No hi ha cap plugin de validació de firmes actiu per realitzar la validació";
                throw new ValidacioException(msg);
            }
            
            Long pluginValidateSignatureID = ids.get(0);
            /*
            Long pluginValidateSignatureID = entitatEjb.executeQueryOne(EntitatFields.PLUGINVALIDAFIRMESID,
                    EntitatFields.ENTITATID.equal(entitatID));
                    */

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

            return internalValidateSignature(pluginValidateSignatureID, signType, signature, documentDetached,
                    languageUI);
        } catch (I18NException e) {
            String message = I18NLogicUtils.getMessage(e, new Locale(languageUI));
            log.error("Error al plugin de validació de firma: " + message);
            throw new ValidacioException(message, e);
        }
    }

    protected ValidateSignatureResponse internalValidateSignature(Long pluginValidateSignatureID, String signType,
            byte[] signature, byte[] documentDetachedFile, String languageUI) throws I18NException {

        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("PLUGIN ID VALIDACIO FIRMES:  " + pluginValidateSignatureID);
        }

        IValidateSignaturePlugin validator = getInstanceByPluginID(pluginValidateSignatureID);

        SignatureRequestedInformation sri = new SignatureRequestedInformation();
        sri.setReturnSignatureTypeFormatProfile(true);
        sri.setReturnCertificateInfo(true);

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
                if (causeMsg.contains("413: Request Entity Too Large") ) {
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
