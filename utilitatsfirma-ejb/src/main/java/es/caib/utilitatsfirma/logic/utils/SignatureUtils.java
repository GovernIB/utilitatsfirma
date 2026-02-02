package es.caib.utilitatsfirma.logic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;

import org.apache.commons.io.FileUtils;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.CommonInfoSignature;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.ITimeStampGenerator;
import org.fundaciobit.pluginsib.signature.api.PdfVisibleSignature;
import org.fundaciobit.pluginsib.signature.api.PolicyInfoSignature;
import org.fundaciobit.pluginsib.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.pluginsib.signature.api.SignaturesSet;
import org.fundaciobit.pluginsib.signature.api.SignaturesTableHeader;
import org.fundaciobit.pluginsib.utils.signature.SignatureConstants;
import org.jboss.logging.Logger;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;


import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.logic.utils.SignatureUtils;
import es.caib.utilitatsfirma.model.bean.FitxerBean;
import es.caib.utilitatsfirma.model.entity.Fitxer;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;

/**
 * 
 * @author anadal
 *
 */
public class SignatureUtils implements Constants {

    protected static final Logger log = Logger.getLogger(SignatureUtils.class);

    public static String getEniTipoFirma(final String signType, final Integer signMode) {

        if (signType == null || signType.trim().length() == 0) {
            log.warn("SignatureUtils::getEniTipoFirma(): S'ha enviat un signType null o buit");
            return null;
        }

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
            return "TF06";
        }

        if (signMode == null) {
            log.warn("SignatureUtils::getEniTipoFirma(): S'ha enviat un signType " + signType
                    + "[ però signMode es null");
            return null;
        }

        if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
            // SIGN_MODE_IMPLICIT està malament, només es per a PADES
            if (signMode == FileInfoSignature.SIGN_MODE_ATTACHED_ENVELOPING) {
                return "TF05"; // (CAdES attached/implicit signature),
            }

            if (signMode == FileInfoSignature.SIGN_MODE_DETACHED) {
                return "TF04"; // (CAdES detached/explicit
            }
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {

            if (signMode == FileInfoSignature.SIGN_MODE_ATTACHED_ENVELOPED) {
                return "TF03"; // (XAdES enveloped signature)
            }

            if (signMode == FileInfoSignature.SIGN_MODE_INTERNALLY_DETACHED) {
                return "TF02"; // (XAdES internally detached signature), ,
            }
        }

        log.warn("SignatureUtils::getEniTipoFirma(): No s'ha trobat ENI Tipo Firma per signType=]" + signType
                + "[ i signMode=]" + signMode + "[");

        return null;
    }

    /**
     * 
     * @param locale
     * @param entitatID
     * @param pfis
     * @param original
     * @param adaptat
     * @param entitatEjb
     * @param codiBarresEjb
     * @return
     * @throws I18NException
     */
    public static int processFileToSign(Locale locale, PassarelaFileInfoSignature pfis, File original, File adaptat,
            UsuariAplicacioJPA usrApp) throws I18NException {

        // (1) Moure FitxerBean (datasource en memòria) a Fitxer en el Sistema
        // d'arxius
        FitxerBean originalInfo = pfis.getFileToSign();

        try {
            FileUtils.copyInputStreamToFile(originalInfo.getData().getInputStream(), original);
        } catch (IOException e) {
            // TODO traduir
            String msg = "Error desconegut copiant fitxer des de DataSource (" + pfis.getSignID() + ") a "
                    + original.getAbsolutePath() + ": " + e.getMessage();
            throw new I18NException("error.unknown", msg);
        }
        // Desreferenciam memoria
        originalInfo.setData(null);
        // Alliberar memòria DataSource
        //System.gc();

        // (2) Adaptam el fitxer

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(pfis.getSignType())) {

            // (a.2.1) Converteix a PDF si necessari. En qualsevol cas deixa el
            // fitxer a "adaptat"
            SignatureUtils.convertirDocumentAPDF(originalInfo, original, adaptat);

            //StampTaulaDeFirmes stampTaulaDeFirmes = null;

            // (a.2.2) Afegir taula de firmes
            /*
            final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();
            if (posicioTaulaFirmesID != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
                final byte[] logoSegellJpeg;
            
                final String titol;
                final String descripcio;
                final String signantLabel;
                final String resumLabel;
                final String titolLabel;
                final String descLabel;
            
                PassarelaSignaturesTableHeader tableHeader = pfis.getSignaturesTableHeader();
            
                if (tableHeader == null) {
            
                    Long logoSegellID = usrApp.getLogoSegellID();
                    if (logoSegellID == null) {
                        logoSegellID = entitatEjb.executeQueryOne(EntitatFields.LOGOSEGELLID,
                                EntitatFields.ENTITATID.equal(entitatID));
                    }
                    try {
                        logoSegellJpeg = FileUtils.readFileToByteArray(FileSystemManager.getFile(logoSegellID));
                    } catch (IOException e) {
                        // TODO Traduir
                        String msg = "Error desconegut llegint logo-segell de l'entitat " + entitatID + ": "
                                + e.getMessage();
                        throw new I18NException("error.unknown", msg);
                    }
            
                    Locale localeSign = new Locale(pfis.getLanguageSign());
            
                    titol = I18NLogicUtils.tradueix(locale, "tauladefirmes");
                    descripcio = ""; // TODO Posar alguna cosa ????
            
                    signantLabel = I18NLogicUtils.tradueix(localeSign, "signant");
                    resumLabel = I18NLogicUtils.tradueix(localeSign, "resumdefirmes");
                    titolLabel = I18NLogicUtils.tradueix(localeSign, "titol");
                    descLabel = I18NLogicUtils.tradueix(localeSign, "descripcio");
            
                } else {
            
                    logoSegellJpeg = tableHeader.getLogoJpeg();
            
                    titol = tableHeader.getTitleFieldValue();
                    descripcio = tableHeader.getDescriptionFieldValue();
            
                    signantLabel = tableHeader.getSignatureLabel();
                    resumLabel = tableHeader.getTitle();
                    titolLabel = tableHeader.getTitleFieldLabel();
                    descLabel = tableHeader.getDescriptionFieldLabel();
                }
            
                stampTaulaDeFirmes = new StampTaulaDeFirmes(pfis.getSignNumber(), posicioTaulaFirmesID, signantLabel,
                        resumLabel, descLabel, descripcio, titolLabel, titol, logoSegellJpeg);
            }
            
            StampCustodiaInfo stampCodiSegurVerificacio = null;
            PassarelaSecureVerificationCodeStampInfo pcvsStamp = pfis.getSecureVerificationCodeStampInfo();
            
            if (pcvsStamp != null) {
            
                // TODO Message Position s'usarà per CodiBarPosition !!!!!
                if (pcvsStamp.getMessagePosition() != SecureVerificationCodeStampInfo.POSITION_NONE) {
            
                    String codiBarresClass = codiBarresEjb.executeQueryOne(CodiBarresFields.CODIBARRESID,
                            CodiBarresFields.NOM.equal(pcvsStamp.getBarCodeType()));
            
                    if (codiBarresClass == null) {
                        // TODO Traduir
                        String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom "
                                + pcvsStamp.getBarCodeType();
                        throw new I18NException("error.unknown", msg);
                    }
            
                    //IBarcodePlugin barcodePlugin;
                    //barcodePlugin = (IBarcodePlugin) PluginsManager.instancePluginByClassName(codiBarresClass);
            
                    stampCodiSegurVerificacio = new StampCustodiaInfo();
            
                    stampCodiSegurVerificacio.setBarcodePlugin(barcodePlugin);
                    stampCodiSegurVerificacio.setBarcodeText(pcvsStamp.getBarCodeText());
                    stampCodiSegurVerificacio.setMissatgeCustodia(pcvsStamp.getMessage());
                    stampCodiSegurVerificacio.setPagines(pcvsStamp.getPages());
                    stampCodiSegurVerificacio.setPosicioCustodiaInfo(pcvsStamp.getMessagePosition());
                }
            }
            
            final boolean acceptTransformPDFA = false; //PropietatGlobalUtil.acceptTransformPDFA(entitatID);
            
            int val = SignatureUtils.afegirTaulaDeFirmesCodiSegurVerificacio(adaptat, stampTaulaDeFirmes,
                    stampCodiSegurVerificacio, acceptTransformPDFA);
            
            // El contingut original els substituim per l'adaptat
            pfis.getFileToSign().setData(new DataHandler(new javax.activation.FileDataSource(adaptat)));
            */

            final int originalNumberOfSigns;

            originalNumberOfSigns = getNumberOfSignaturesInPDF(adaptat);

            return originalNumberOfSigns;

            // Final IF PADES
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(pfis.getSignType())
                || FileInfoSignature.SIGN_TYPE_CADES.equals(pfis.getSignType())
                || FileInfoSignature.SIGN_TYPE_SMIME.equals(pfis.getSignType())) {
            // L'original és l'adaptat, per això el movem allà
            try {
                FileUtils.moveFile(original, adaptat);

                // El contingut original els substituim per l'adaptat
                pfis.getFileToSign().setData(new DataHandler(new javax.activation.FileDataSource(adaptat)));

                return 0;

            } catch (Exception e) {
                log.error(
                        " Error movent fitxer des de " + original.getAbsolutePath() + " a " + adaptat.getAbsolutePath(),
                        e);
                throw new I18NException("error.copyfile", original.getAbsolutePath(), adaptat.getAbsolutePath());
            }

        } else {
            throw new I18NException(new Exception(), "error.desconegut", new I18NArgumentString("Tipus de Signatura "
                    + pfis.getSignType() + " no supportat dins la classe " + SignatureUtils.class.getName()));
        }

    }

    /**
     * 
     * @param pdf
     * @return Si el fitxer no és PDF llavors retorna 0.
     */
    public static int getNumberOfSignaturesInPDF(File pdf) throws I18NException {

        InputStream is = null;
        try {
            is = new FileInputStream(pdf);
            return getNumberOfSignaturesInPDF(is);
        } catch (FileNotFoundException e) {
            log.error("No s'ha trobat el fitxer " + pdf.getAbsolutePath() + ":" + e.getMessage(), e);
            throw new I18NException("fitxer.notfound", pdf.getAbsolutePath(), e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("Error tancant InputStream del fitxer " + pdf.getAbsolutePath() + ":" + e.getMessage(),
                            e);
                }
            }
        }
    }

    public static int getNumberOfSignaturesInPDF(InputStream pdfis) {
        try {
            PdfReader reader;
            try {
                reader = new PdfReader(pdfis);
            } catch (IOException e1) {
                throw new I18NException(e1, "genapp",
                        new I18NArgumentString("Error llegint PDF firmat: " + e1.getMessage()));
            }

            return getNumberOfSignaturesInPDF(reader);
        } catch (Throwable e) {
            log.error("Error desconegut intentant obtenir numero de firmes d'un PDF: " + e.getMessage());
            return 0;
        }

    }

    public static int getNumberOfSignaturesInPDF(PdfReader reader) {
        AcroFields af = reader.getAcroFields();
        ArrayList<String> names = af.getSignatureNames();

        if (names == null || names.size() == 0) {
            return 0;
        }

        // Calculam només les firmes sense els Segells de Temps
        int totalNomesFirmes = 0;
        for (int i = names.size() - 1; i >= 0; i--) {

            String name = names.get(i);
            PdfPKCS7 pk = af.verifySignature(name);
            if (!pk.isTsp()) {
                totalNomesFirmes++;
            }

        }

        return totalNomesFirmes;
    }

    /**
    *
    * @return
    */
    public static void convertirDocumentAPDF(Fitxer fileToConvertInfo, File fileToConvert, File dst)
            throws I18NException {

        try {
            Fitxer fitxerConvertit = convertToPDF(fileToConvert, fileToConvertInfo);

            if (fitxerConvertit == fileToConvertInfo || fitxerConvertit.getData() == fileToConvertInfo.getData()) {
                // Es un PDF. Movem l'original a l'adaptat
                FileUtils.moveFile(fileToConvert, dst);
            } else {
                // No és un PDF, ho substituim pel fitxer convertit

                InputStream is = fitxerConvertit.getData().getInputStream();

                FileUtils.copyInputStreamToFile(is, dst);

            }
            // OK
        } catch (Exception e) {
            log.error("Error desconegut convertint document a pdf: " + e.getMessage(), e);
            throw new I18NException(e, "formatfitxer.conversio.error", new I18NArgumentString(e.getMessage()));
        }

    }

    public static Fitxer convertToPDF(File fileToConvert, Fitxer fileToConvertInfo) throws I18NException {
        String mime = fileToConvertInfo.getMime();
        boolean isDebug = log.isDebugEnabled();
        if (isDebug) {
            log.debug("convertToPDF(): Name = " + fileToConvertInfo.getNom());
            log.debug("convertToPDF(): File = " + fileToConvert.getAbsolutePath());
            log.debug("convertToPDF(): File Exists?= " + fileToConvert.exists());
            log.debug("convertToPDF(): MIME = " + mime);
        }

        if (MIME_TYPE_PDF.equals(mime) || isPdf(fileToConvert)) {

            if (fileToConvertInfo.getNom().toLowerCase().endsWith("." + PDF_FILE_EXTENSION)
                    && (MIME_TYPE_PDF.equals(mime) || mime == null)) {
                // No cal fer res
                return fileToConvertInfo;
            } else {
                FitxerBean fileConvertedInfo = new FitxerBean(fileToConvertInfo);
                fileConvertedInfo.setMime(MIME_TYPE_PDF);
                fileConvertedInfo.setNom(fileToConvertInfo.getNom() + "." + PDF_FILE_EXTENSION);
                return fileConvertedInfo;
            }

        } else {

            throw new I18NException(new Exception(), "comodi",
                    new I18NArgumentString("Tipus de fitxer " + mime + " no suportat per a conversió a PDF"));
        }
    }

    public static final boolean isPdf(File file) {

        // Llegeix el fitxer amb itext. Si llança excepció retornar false. Sinó retorna true
        try {
            PdfReader reader = new PdfReader(file.getAbsolutePath());
            reader.close();
            return true;
        } catch (Throwable e) {
            return false;
        }

    }

    public static SignaturesSet passarelaSignaturesSetToSignaturesSet(AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb,
            PassarelaSignaturesSet pss, UsuariAplicacioJPA usuariAplicacio, PerfilDeFirma perfilDeFirma,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID, Set<String> timeStampUrls)
            throws I18NException {
        final String signaturesSetID = pss.getSignaturesSetID();
        SignaturesSet ss = new SignaturesSet();

        // Part comuna
        CommonInfoSignature commonInfoSignature;
        PolicyInfoSignature pis;
        {
            PassarelaCommonInfoSignature cis = pss.getCommonInfoSignature();
            final String username = cis.getUsername();
            final String administrationID = cis.getAdministrationID();
            final String langUI = cis.getLanguageUI();

            PassarelaPolicyInfoSignature ppis = cis.getPolicyInfoSignature();
            if (ppis == null) {
                //log.info(" PassarelaPolicyInfoSignature = NULL");
                pis = null;
            } else {
                pis = new PolicyInfoSignature(ppis.getPolicyIdentifier(), ppis.getPolicyIdentifierHash(),
                        ppis.getPolicyIdentifierHashAlgorithm(), ppis.getPolicyUrlDocument());
            }

            commonInfoSignature = new CommonInfoSignature(langUI, cis.getFiltreCertificats(), username,
                    administrationID);

        }
        ss.setCommonInfoSignature(commonInfoSignature);

        {
            FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[pss.getFileInfoSignatureArray().length];
            int count = 0;
            for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {

                // Preparar pàgina
                final String idname = pfis.getName();

                final String reason = pfis.getReason();
                final String location = pfis.getLocation();
                final String signerEmail = pfis.getSignerEmail();

                final int sign_number = 1;

                final String langDoc = pfis.getLanguageSign();

                final String signID = pfis.getSignID();

                final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();

                // NO SUPORTAM GENERADORS DE SEGELL DE TEMPS EXTERNS, NOMES INTERNS
                ITimeStampGenerator timeStampGenerator = null;
                /*
                
                UsuariAplicacioConfiguracioJPA config = configBySignID.get(signID);
                
                // Ve d'un camp que indica si l'usuari vol Segellat de Temps
                boolean userRequiresTimeStamp = pfis.getUseTimeStamp2();
                
                
                {
                   PortaFIBTimeStampInfo info;
                   info = segellDeTempsPublicEjb.getTimeStampInfoForUsrApp(usuariAplicacio.getUsuariAplicacioID(),
                           entitat, perfilDeFirma, config, userRequiresTimeStamp);
                   if (info == null) {
                       timeStampGenerator = null;
                   } else {
                       timeStampGenerator = info.getTimeStampGenerator();
                       timeStampUrls.add(info.timeStampUrl);
                   }
                */

                int signTypeID = SignatureUtils.convertApiSignTypeToPortafibSignType(pfis.getSignType());

                final String mime;
                if (signTypeID == Constants.TIPUSFIRMA_PADES) {
                    // NOTA Convertir Document a PDF i Afegir Taula de Firmes ja s'ha fet durant
                    // l'startTransacction via WS
                    mime = FileInfoSignature.PDF_MIME_TYPE;
                } else {
                    mime = pfis.getFileToSign().getMime();
                }

                File pdfAdaptat = passarelaDeFirmaEjb.getFitxerAdaptatPath(signaturesSetID, signID);

                log.debug(
                        "SIGNUTILS :: SIGN_ALGO{" + count + "} [pfis.getSignAlgorithm()] = " + pfis.getSignAlgorithm());

                int signAlgorithm = getSignAlgorithmToPortaFIB(pfis.getSignAlgorithm());

                log.debug("SIGNUTILS :: SIGN_ALGO{" + count + "} [int signAlgorithm] = " + signAlgorithm);

                int signMode = pfis.getSignMode();//getSignModeToPortaFIB(pfis.getSignMode());

                // CODI ANTIC getFirmatPerFormat(config, langDoc)
                String firmatPerFormat = null; // Es firma en servidor. No n'hi ha cap 

                FileInfoSignature fis = getFileInfoSignature(signID, pdfAdaptat, mime, idname, posicioTaulaFirmesID,
                        reason, location, signerEmail, sign_number, langDoc, signTypeID, signAlgorithm, signMode,
                        firmatPerFormat, timeStampGenerator, pis, pfis.getExpedientCodi(), pfis.getExpedientNom(),
                        pfis.getExpedientUrl(), pfis.getProcedimentCodi(), pfis.getProcedimentNom());

                fileInfoSignatureArray[count] = fis;
                count++;
            }

            if (timeStampUrls.size() > 2) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi",
                        "Les diferents configuracions"
                                + " de les diferents firmes apunten a Segelladors de Temps diferents,"
                                + " cosa que PortaFIB actualment no suporta");
            }

            ss.setFileInfoSignatureArray(fileInfoSignatureArray);
            ss.setSignaturesSetID(signaturesSetID);

        }
        return ss;
    }

    public static int convertApiSignTypeToPortafibSignType(String signType) throws I18NException {

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
            return Constants.TIPUSFIRMA_PADES;
        } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
            return Constants.TIPUSFIRMA_CADES;
        } else if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
            return Constants.TIPUSFIRMA_SMIME;
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
            return Constants.TIPUSFIRMA_XADES;
        } else {
            // TODO Traduir
            throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signType);
        }
    }

    public static String convertSignAlgorithmID(long signAlgorithmID) throws I18NException {
        String signAlgorithm;
        switch ((int) signAlgorithmID) {
            case Constants.SIGN_ALGORITHM_SHA1WITHRSA:
                signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA1;
            break;
            case Constants.SIGN_ALGORITHM_SHA256WITHRSA:
                signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA256;
            break;
            case Constants.SIGN_ALGORITHM_SHA384WITHRSA:
                signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA384;
            break;
            case Constants.SIGN_ALGORITHM_SHA512WITHRSA:
                signAlgorithm = FileInfoSignature.SIGN_ALGORITHM_SHA512;
            break;

            default:
                // TODO Traduir
                throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithmID);
        }
        return signAlgorithm;
    };

    public static int getSignAlgorithmToPortaFIB(String signAlgorithm) throws I18NException {
        if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)) {
            return Constants.SIGN_ALGORITHM_SHA1WITHRSA;
        } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)) {
            return Constants.SIGN_ALGORITHM_SHA256WITHRSA;
        } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)) {
            return Constants.SIGN_ALGORITHM_SHA384WITHRSA;
        } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
            return Constants.SIGN_ALGORITHM_SHA512WITHRSA;
        } else {
            throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithm);
        }
    }

    /**
    *
    */
    public static FileInfoSignature getFileInfoSignature(String signatureID, File fileToSign, String mimeType,
            String idname, long locationSignTableID, String reason, String location, String signerEmail, int signNumber,
            String languageSign, long signTypeID, long signAlgorithmID, int signModeBool, String firmatPerFormat,
            ITimeStampGenerator timeStampGenerator, PolicyInfoSignature policyInfoSignature, String expedientCode,
            String expedientName, String expedientUrl, String procedureCode, String procedureName)
            throws I18NException {

        PdfVisibleSignature pdfInfoSignature = null;

        final String signType = convertPortafibSignTypeToApiSignType(signTypeID);
        if (signType == null) {
            throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signTypeID);
        }

        // TAULA DE FIRMES (NOMÉS EN PADES)

        int locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        /*if ((int) signTypeID == ConstantsV2.TIPUSFIRMA_PADES) {
        
           switch ((int) locationSignTableID) {
               case ConstantsV2.TAULADEFIRMES_SENSETAULA:
                   locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
               break;
               case ConstantsV2.TAULADEFIRMES_PRIMERAPAGINA:
                   locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE;
               break;
               case ConstantsV2.TAULADEFIRMES_DARRERAPAGINA:
                   locationSignTable = FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE;
               break;
               default:
                   // TODO Traduir
                   throw new I18NException("error.unknown",
                           "Posicio de taula de firmes desconeguda: " + locationSignTableID);
           }
        
           if (locationSignTable != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
        
               // PDF Visible
               pdfInfoSignature = new PdfVisibleSignature();
        
               SignBoxRectangle signBoxRectangle = SignBoxRectangle.getPositionOfVisibleSignature(signNumber);
        
               PdfRubricRectangle prr = new PdfRubricRectangle();
               prr.setLowerLeftX(signBoxRectangle.llx);
               prr.setLowerLeftY(signBoxRectangle.lly);
               prr.setUpperRightX(signBoxRectangle.urx);
               prr.setUpperRightY(signBoxRectangle.ury);
        
               IRubricGenerator rubricGenerator = new PortaFIBRubricGenerator(languageSign, firmatPerFormat, reason,
                       prr);
        
               pdfInfoSignature.setRubricGenerator(rubricGenerator);
               pdfInfoSignature.setPdfRubricRectangle(prr);
        
           }
        }
        */

        final int signMode = signModeBool; //convertPortafibSignMode2ApiSignMode(signModeBool, (int)signTypeID);

        String signAlgorithm = convertSignAlgorithmID(signAlgorithmID);
        // Ja s'ha arreglat abans
        final SignaturesTableHeader signaturesTableHeader = null;
        // Ja s'ha arreglat abans
        final SecureVerificationCodeStampInfo csvStampInfo = null;

        // #174 TODO XYZ ZZZ
        final File previusSignatureDetachedFile = null;
        final int signOperation = FileInfoSignature.SIGN_OPERATION_SIGN;

        FileInfoSignature fis = new FileInfoSignature(signatureID, fileToSign, previusSignatureDetachedFile, mimeType,
                idname, reason, location, signerEmail, signNumber, languageSign, signOperation, signType, signAlgorithm,
                signMode, locationSignTable, signaturesTableHeader, pdfInfoSignature, csvStampInfo,
                timeStampGenerator != null, timeStampGenerator, policyInfoSignature, expedientCode, expedientName,
                expedientUrl, procedureCode, procedureName);

        return fis;
    }

    public static String convertPortafibSignTypeToApiSignType(long signTypeID) throws I18NException {
        final String signType;
        switch ((int) signTypeID) {
            case Constants.TIPUSFIRMA_PADES:
                signType = FileInfoSignature.SIGN_TYPE_PADES;
            break;

            case Constants.TIPUSFIRMA_CADES:
                signType = FileInfoSignature.SIGN_TYPE_CADES;
            break;

            case Constants.TIPUSFIRMA_SMIME:
                signType = FileInfoSignature.SIGN_TYPE_SMIME;
            break;

            case Constants.TIPUSFIRMA_XADES:
                signType = FileInfoSignature.SIGN_TYPE_XADES;
            break;

            default:
                signType = null;
        }
        return signType;
    }

    public static boolean validarFirma(es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio configuracio)
            throws I18NException {
        boolean validarFirma = configuracio.isValidarFirma();
        return validarFirma;
    }

    /**
    * 
    * @param configuracio
    * @param entitatID
    * @return
    * @throws I18NException
    */
    public static boolean comprovarNifFirma(UsuariAplicacioConfiguracio configuracio,
            boolean administrationIdCanBeValidatedFromPlugin) throws I18NException {

        if (administrationIdCanBeValidatedFromPlugin == false) {
            return false;
        }

        boolean comp = configuracio.isComprovarNifFirma();

        return comp;

    }

    /**
    * 
    * @param configuracio
    * @param entitatEjb
    * @param entitatID
    * @return
    * @throws I18NException
    */
    public static boolean checkCanviatDocFirmat(UsuariAplicacioConfiguracio configuracio,
            boolean willCanCheckIfSignedDocumentWasAlteredAfterSignature) throws I18NException {

        if (willCanCheckIfSignedDocumentWasAlteredAfterSignature == false) {
            return false;
        }

        boolean comp = configuracio.isCheckCanviatDocFirmat();

        return comp;

    }

    public static int convertApiSignMode2PortafibSignMode(int signModeBool) throws I18NException {

        /* , int signType pot ser:
        *  ConstantsV2.TIPUSFIRMA_PADES;
        * ConstantsV2.TIPUSFIRMA_CADES;
        *  ConstantsV2.TIPUSFIRMA_XADES;
        */

        switch (signModeBool) {
            case FileInfoSignature.SIGN_MODE_ATTACHED_ENVELOPED:
                //case FileInfoSignature.SIGN_MODE_IMPLICIT:
                return SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPED; //ConstantsV2.SIGN_MODE_IMPLICIT;
            case FileInfoSignature.SIGN_MODE_ATTACHED_ENVELOPING:
                return SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPING; //ConstantsV2.SIGN_MODE_IMPLICIT;
            case FileInfoSignature.SIGN_MODE_DETACHED:
                //  case FileInfoSignature.SIGN_MODE_EXPLICIT:
                return SignatureConstants.SIGN_MODE_DETACHED; // ConstantsV2.SIGN_MODE_EXPLICIT;
            case FileInfoSignature.SIGN_MODE_INTERNALLY_DETACHED:
                return SignatureConstants.SIGN_MODE_INTERNALLY_DETACHED; //ConstantsV2.SIGN_MODE_IMPLICIT;
            //case FileInfoSignature.SIGN_MODE_EXTERNALLY_DETACHED:
            //    return  SignatureConstants.SIGN_MODE_EXTERNALLY_DETACHED; //ConstantsV2.SIGN_MODE_IMPLICIT;
            default:
                throw new I18NException("error.unknown", "Tipus de mode de firma no suportat " + signModeBool);
        }

        /*
        return (signModeBool == FileInfoSignature.SIGN_MODE_IMPLICIT) ? ConstantsV2.SIGN_MODE_IMPLICIT
               : ConstantsV2.SIGN_MODE_EXPLICIT;
               */
    }
    
    
    public static PolicyInfoSignature getPolicyInfoSignature(UsuariAplicacioConfiguracio config) throws I18NException {

        PolicyInfoSignature policyInfoSignature;

        int usPoliticaDeFirma = config.getUsPoliticaDeFirma();
        

        switch (usPoliticaDeFirma) {
            // 0 => no usar politica de firma,
            case Constants.US_POLITICA_DE_FIRMA_NO_USAR:
                policyInfoSignature = null;
            break;

            // 1=> usar politica d'aquesta configuracio
            case Constants.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT:
                
                    policyInfoSignature = new PolicyInfoSignature(config.getPolicyIdentifier(),
                            config.getPolicyIdentifierHash(), config.getPolicyIdentifierHashAlgorithm(),
                            config.getPolicyUrlDocument());
                
            break;

           
            default:
                policyInfoSignature = null;
                log.warn("Política de firma (" + usPoliticaDeFirma + ") no soportada");
            break;
        }

       
        return policyInfoSignature;
    }

}
