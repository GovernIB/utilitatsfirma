package es.caib.utilitatsfirma.logic;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;

import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.datasource.IDataSource;
import es.caib.utilitatsfirma.logic.passarela.api.ValidacioCompletaResponse;
import es.caib.utilitatsfirma.logic.utils.I18NLogicUtils;
import es.caib.utilitatsfirma.logic.utils.PdfComparator;
import es.caib.utilitatsfirma.logic.utils.SignatureUtils;
import es.caib.utilitatsfirma.logic.utils.ValidacioCompletaRequest;
import es.caib.utilitatsfirma.logic.utils.ValidacioException;
import es.caib.utilitatsfirma.logic.utils.ValidationsCAdES;
import es.caib.utilitatsfirma.logic.utils.ValidationsXAdES;

/*
import es.caib.portafib.logic.utils.DNIUtils;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfComparator;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.ValidationsCAdES;
import es.caib.portafib.logic.utils.ValidationsXAdES;
*/
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;

import org.fundaciobit.pluginsib.utils.signature.SignatureConstants;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.validatesignature.api.ValidationStatus;

import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Stateless(name = "ValidacioCompletaFirmaLogicaEJB")
public class ValidacioCompletaFirmaLogicaEJB implements ValidacioCompletaFirmaLogicaLocal {

    protected static final Logger log = Logger.getLogger(ValidacioCompletaFirmaLogicaEJB.class);

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;



    @Override
    public ValidacioCompletaResponse validateCompletaFirma(String transaccioID,
            ValidacioCompletaRequest validacioRequest, boolean validateChangesInAttachedFiles)
            throws ValidacioException {
        try {
            return internalValidateCompletaFirma(transaccioID, validacioRequest, validateChangesInAttachedFiles);
        } catch (I18NException e) {
            String message = I18NLogicUtils.getMessage(e, new Locale(validacioRequest.getLanguageUI()));
            log.error("Transaccio[" + transaccioID + "]: Rebut error de validació de firma: " + message);
            throw new ValidacioException(message, e);
        }
    }

    private ValidacioCompletaResponse internalValidateCompletaFirma(String transaccioID,
            ValidacioCompletaRequest validacioRequest, boolean validateChangesInAttachedFiles)
            throws I18NException, ValidacioException {

        String signType;
        String mime;
        String extension;

        switch (validacioRequest.getSignTypeID()) {
            case Constants.TIPUSFIRMA_PADES:
                extension = "pdf";
                mime = Constants.MIME_TYPE_PDF;
                signType = FileInfoSignature.SIGN_TYPE_PADES;
            break;

            case Constants.TIPUSFIRMA_CADES:
                extension = "csig";
                mime = Constants.MIME_TYPE_BINARY;
                signType = FileInfoSignature.SIGN_TYPE_CADES;
            break;

            case Constants.TIPUSFIRMA_XADES:
                extension = "xml";
                mime = Constants.MIME_TYPE_XML;
                signType = FileInfoSignature.SIGN_TYPE_XADES;
            break;

            default:
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi",
                        "Transaccio[" + transaccioID
                                + "]: No esta implementada la validacio completa de fitxers firmats"
                                + " amb tipus de firma " + validacioRequest.getSignTypeID()
                                + " (TIPUSFIRMA_PADES=0, TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)");
        }

        // (a) Validar el Fitxer de la Firma
        //log.info("internalValidateCompletaFirma():: (a) Validar el Fitxer de la Firma");
        String nifFirmant = null;
        //String cifFirmant = null;
        BigInteger numeroSerieCertificat = null;
        String emissorCertificat = null;
        String subjectCertificat = null;
        Boolean checkValidationSignature = null;
        String perfilDeFirma = null;

        ValidateSignatureResponse validateSignatureResponse = null;
        if (validacioRequest.isValidarFitxerFirma()) {

            IDataSource documentDetached = validacioRequest.getDocumentDetachedData();

            if (documentDetached == null) {
                // Si es CAdES o XAdES en la primera firma i es requereix firma explicita o
                // detached, llavors getDocumentDetachedData() valdrà null, però per la
                // validació necessitam el valor del fitxer original, sempre i quan
                // no sigui un XAdES 'internally detached' que sí que inclou el document.
                if (validacioRequest.getSignMode() == SignatureConstants.SIGN_MODE_DETACHED) {
                    // per tant, comprovam que no és XAdES, o sí és XAdES no és un internally detached
                    if (validacioRequest.getSignTypeID() != Constants.TIPUSFIRMA_XADES || !ValidationsXAdES
                            .isXadesDettachedWithOriginalDocumentAsSibling(validacioRequest.getSignatureData())) {
                        documentDetached = validacioRequest.getOriginalData();
                    }
                }
            }

            if (log.isDebugEnabled()) {
                log.debug("validateCompletaFirma :: getDocumentDetachedData() => " + documentDetached);
            }

            validateSignatureResponse = validacioFirmesEjb.validateSignature(signType,
                    validacioRequest.getSignatureData(), documentDetached, validacioRequest.getLanguageUI());

            if (validateSignatureResponse == null) {
                // XYZ ZZZ TRA

                String msg = "Per aquesta transacció es requereix validació de la firma "
                        + "però no s'ha definit cap Plugin de Validació.";
                log.error("Transaccio[" + transaccioID + "]: " + msg);
                throw new I18NException("genapp.comodi", msg);

            } else if (validateSignatureResponse.getValidationStatus()
                    .getStatus() != ValidationStatus.SIGNATURE_VALID) {
                String msg = "La firma no és vàlida. Raó: "
                        + validateSignatureResponse.getValidationStatus().getErrorMsg();
                log.error("Transaccio[" + transaccioID + "]: " + msg);
                throw new I18NException("genapp.comodi", msg);
            }

            perfilDeFirma = validateSignatureResponse.getSignProfile();

            SignatureDetailInfo[] sdi = validateSignatureResponse.getSignatureDetailInfo();

            if (sdi != null && sdi.length > 0) {

                // Esbrinar informació de la darrera Firma
                InformacioCertificat info = sdi[0].getCertificateInfo();
                Date signDate = sdi[0].getSignDate();

                if (sdi.length > 1) {
                    for (SignatureDetailInfo signatureDetailInfo : sdi) {
                        Date d = signatureDetailInfo.getSignDate();
                        if (d == null) {
                            info = null;
                            break;
                        } else {
                            if (signDate == null || d.getTime() > signDate.getTime()) {
                                signDate = d;
                                info = signatureDetailInfo.getCertificateInfo();
                            }
                        }
                    }
                }

                if (info == null) {
                    log.warn("Transaccio[" + transaccioID + "]: "
                            + "No ha definit alguna de les dates de la firma cosa que "
                            + "implica que la informació de la validació pot ser inconsistent."
                            + " Omitim la cerca en aquest punt.");
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("NIF DE LA DARRERA FIRMA => " + info.getNifResponsable());
                        log.debug("CIF DE LA DARRERA FIRMA => " + info.getUnitatOrganitzativaNifCif());
                    }
                    nifFirmant = info.getNifResponsable();
                    //cifFirmant = info.getUnitatOrganitzativaNifCif();
                    numeroSerieCertificat = info.getNumeroSerie();
                    emissorCertificat = info.getEmissorOrganitzacio();
                    subjectCertificat = info.getSubject();
                }
            } else {
                log.warn(
                        "Transaccio[" + transaccioID + "]: "
                                + "El validador de signatures no ha retornat informació del certificat !!!!",
                        new Exception());
            }

            checkValidationSignature = true;

        }

        // (b) Validar si s'ha modificat el fitxer original
        //log.info("internalValidateCompletaFirma():: (b) Validar si s'ha modificat el fitxer original");
        Boolean checkDocumentModifications = null;
        X509Certificate certificateLastSign = null;
        if (validacioRequest.isCheckCanviatDocFirmat()) {

            switch (validacioRequest.getSignTypeID()) {

                case Constants.TIPUSFIRMA_PADES: {
                    File tmpDir = new File(FileSystemManager.getFilesPath(), "COMPAREPDF");
                    tmpDir.mkdirs();

                    
                    if (validateChangesInAttachedFiles == true) {
                        throw new I18NException("genapp.comodi",
                                "NO ES PETMETEN VALIDACIONS DE FITXERS ADJUNTS EN AQUESTA VERSIÓ.(Veure validateChangesInAttachedFiles)");
                    }
                    

                    PdfComparator.compare(validacioRequest.getAdaptedData(), validacioRequest.getSignatureData(),
                            tmpDir, false);

                    checkDocumentModifications = true;
                }
                break;

                // XAdES => #333
                case Constants.TIPUSFIRMA_XADES:

                    // Si és attached llavors validam
                    if (validacioRequest.getSignMode() != SignatureConstants.SIGN_MODE_DETACHED) {

                        byte[] documentOriginal = ValidationsXAdES
                                .getProcessedOriginalData(validacioRequest.getAdaptedData());

                        byte[] documentOriginalExtret;
                        {
                            InputStream is = validacioRequest.getSignatureData().getInputStream();
                            try {
                                documentOriginalExtret = ValidationsXAdES
                                        .getOriginalDocumentOfXadesAttachedSignature(is);
                            } finally {
                                try {
                                    is.close();
                                } catch (IOException ignored) {
                                }
                            }
                        }

                        try {
                            boolean isEquals = Arrays.equals(documentOriginal, documentOriginalExtret);

                            if (isEquals) {
                                checkDocumentModifications = true;
                            } else {
                                // XYZ ZZZ TRA
                                throw new I18NException("genapp.comodi",
                                        "Transaccio[" + transaccioID + "]: "
                                                + "Pareix ser que el document adjunt en la firna XAdES Attached NO es"
                                                + " igual al document original enviat");
                            }
                        } catch (Exception e) {
                            throw new I18NException("genapp.comodi", "Transaccio[" + transaccioID + "]: "
                                    + "Error llegint el document adjunt en la firna XAdES Attached o el document "
                                    + "original enviat");
                        }
                    } else {
                        checkDocumentModifications = true;
                    }
                break;

                // CAdES => #334
                case Constants.TIPUSFIRMA_CADES:

                    // Si és attached llavors validam
                    if (validacioRequest.getSignMode() != SignatureConstants.SIGN_MODE_DETACHED) {

                        IDataSource originalBo = validacioRequest.getAdaptedData();

                        InputStream is = validacioRequest.getSignatureData().getInputStream();
                        byte[] documentOriginal = ValidationsCAdES.getOriginalDocumentOfCadesAttachedSignature(is);
                        try {
                            is.close();
                        } catch (IOException ignored) {
                        }

                        try {
                            is = originalBo.getInputStream();
                            boolean isEquals = IOUtils.contentEquals(is, new ByteArrayInputStream(documentOriginal));
                            try {
                                is.close();
                            } catch (IOException ignored) {
                            }
                            if (isEquals) {
                                log.debug("Transaccio[" + transaccioID + "]: "
                                        + "Pareix ser que el document adjunt en la firna CAdES Attached es igual al document original enviat");
                                checkDocumentModifications = true;
                            } else {
                                // XYZ ZZZ TRA
                                throw new I18NException("genapp.comodi",
                                        "Transaccio[" + transaccioID + "]: "
                                                + "Pareix ser que el document adjunt en la firna CAdES Attached NO es"
                                                + " igual al document original enviat");
                            }
                        } catch (IOException e) {
                            throw new I18NException("genapp.comodi", "Transaccio[" + transaccioID + "]: "
                                    + "Error llegint el document adjunt en la firna CAdES Attached o el document "
                                    + "original enviat");
                        }
                    } else {
                        checkDocumentModifications = true;
                    }
                break;

                default: {
                    String msg = "Transaccio[" + transaccioID + "]"
                            + "No esta implementat el xequeig de modificacio de fitxer signat" + " amb tipus de firma "
                            + validacioRequest.getSignTypeID()
                            + "(TIPUSFIRMA_PADES=0, TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)."
                            + " Consulti amb l'administrador de PortaFIB el valor de la propietat es.caib.portafib.strictvalidation";
                    // TODO 
                    //if (PropietatGlobalUtil.isStrictValidation()) {
                        // XYZ ZZZ TRA
                        throw new I18NException("genapp.comodi", msg);
                    //} else {
                    //    checkDocumentModifications = false;
                    //}
                }
            }

        }

        // Debug
        final boolean isDebug = log.isDebugEnabled();
        if (isDebug) {
            log.debug("checkCanviatDocFirmat: " + validacioRequest.isCheckCanviatDocFirmat());
            log.debug("NumeroSerieCertificat = " + numeroSerieCertificat);
            log.debug("Emissor = " + emissorCertificat);
            log.debug("Subject = " + subjectCertificat);
            log.debug("NIF = " + nifFirmant);
            //log.debug("checkAdministrationIDOfSigner: " + checkAdministrationIDOfSigner);
            log.debug("checkDocumentModifications: " + checkDocumentModifications);
            log.debug("checkValidationSignature: " + checkValidationSignature);
        }

        //log.info("internalValidateCompletaFirma():: Resposta ...");

        ValidacioCompletaResponse resposta = new ValidacioCompletaResponse(signType, mime, extension, nifFirmant,
                false, checkDocumentModifications, checkValidationSignature,
                validateSignatureResponse, numeroSerieCertificat, emissorCertificat, subjectCertificat,
                certificateLastSign, perfilDeFirma);

        return resposta;
    }

    /*
    public static boolean isPseudonymCertificate(X509Certificate certificate) throws Exception {
        String politica = getCertificatePolicyId(certificate);
        return politica != null && politica.startsWith("2.16.724.1.3.5.4.");
    }
    
    public static String getCertificatePolicyId(X509Certificate cert) throws Exception {
    
        byte[] extvalue = cert.getExtensionValue("2.5.29.32");
    
        int pos = 0;
        if (extvalue != null) {
    
            ASN1InputStream extAsn1InputStream = new ASN1InputStream(new ByteArrayInputStream(extvalue));
            try {
                DEROctetString oct = (DEROctetString) (extAsn1InputStream.readObject());
                ASN1InputStream octAsn1InputStream = new ASN1InputStream(new ByteArrayInputStream(oct.getOctets()));
                try {
                    ASN1Sequence seq = (ASN1Sequence) octAsn1InputStream.readObject();
                    // Check the size so we don't ArrayIndexOutOfBounds
                    if (seq.size() < pos + 1) {
                        return null;
                    }
                    PolicyInformation pol = PolicyInformation.getInstance((ASN1Sequence) seq.getObjectAt(pos));
                    return pol.getPolicyIdentifier().getId();
                } finally {
                    octAsn1InputStream.close();
                }
            } finally {
                extAsn1InputStream.close();
            }
        }
    
        return null;
    }
    */
    public static X509Certificate getLastCertificateOfSignedPdf(IDataSource signedPDFData, int numFirmaPortaFIB,
            int numFirmesOriginals) throws I18NException {

        PdfReader reader;
        try {
            reader = new PdfReader(signedPDFData.getInputStream());
        } catch (IOException e1) {
            throw new I18NException(e1, "genapp",
                    new I18NArgumentString("Error llegint PDF firmat: " + e1.getMessage()));
        }

        {
            // Calculam només les firmes sense els Segells de Temps
            int totalNomesFirmes = SignatureUtils.getNumberOfSignaturesInPDF(reader);

            if (totalNomesFirmes != (numFirmaPortaFIB + numFirmesOriginals)) {
                // TODO XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "S´esperaven " + (numFirmaPortaFIB + numFirmesOriginals)
                        + " firmes, però el document pujat conté " + totalNomesFirmes + " firmes");
            }

        }

        // ================ Validar el certificat de la darrera firma
        AcroFields af = reader.getAcroFields();
        ArrayList<String> names = af.getSignatureNames();
        for (int i = names.size() - 1; i >= 0; i--) {
            String name = names.get(i); // names.size() - 1

            try {
                PdfPKCS7 pk = af.verifySignature(name);

                if (pk.isTsp()) {
                    continue;
                }

                // Sembla que IText no parseji bé el X509Certificate, per això obtenim el seus bytes i el recarregam
                byte[] certificateBytes = pk.getSigningCertificate().getEncoded();
                X509Certificate cert = CertificateUtils.decodeCertificate(new ByteArrayInputStream(certificateBytes));

                if (log.isDebugEnabled()) {
                    log.debug("getLastCertificateOfSignedPdf()::PdfPKCS7 pk = " + pk);
                    log.debug(
                            "getLastCertificateOfSignedPdf()::PdfPKCS7 X509Certificate.cert = " + cert.getSubjectDN());
                    log.debug("getLastCertificateOfSignedPdf()::PdfPKCS7 X509Certificate.getSubjectDN() = "
                            + cert.getSubjectDN());
                }

                return cert;
            } catch (Exception e) {
                final String msg = "Error desconegut parsejant Certificats d'una firma PADES: " + e.getMessage();
                log.error(msg, e);
                throw new I18NException("genapp.comodi", msg);
            }
        }

        final String msg = "En el procés de cerca del darrer cerificat, no se n'ha trobat cap en la llista de firmes del PDF.";
        log.error(msg);
        throw new I18NException("genapp.comodi", msg);

    }

}
