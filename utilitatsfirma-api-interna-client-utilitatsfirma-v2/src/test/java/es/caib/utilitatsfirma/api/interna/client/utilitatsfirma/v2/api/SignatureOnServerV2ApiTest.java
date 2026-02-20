
package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.CommonInfo;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.Document;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.DocumentaryType;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.FileInfoSignatureV2;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.KeyValue;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.ProcessStatus;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.Profile;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignDocumentRequestV2;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignDocumentResponseV2;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignModeConstants;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignedFileInfo;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.StatusConstants;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.UpgradeResponse;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;

/**
 * Tests de Firma en Servidor V2
 * @author anadal (u80067)
 * 19 feb 2026 17:37:34
 */
public class SignatureOnServerV2ApiTest extends AbstractV2ApiTest<SignatureOnServerV2Api> {

    public static void main(String[] args) {
        SignatureOnServerV2ApiTest test = new SignatureOnServerV2ApiTest();
        try {

            test.callCommonTests();

            test.testSignatureServerPAdES();
                  
            test.testSignatureServerPAdESStatus401_Unathorized();
            
            test.testSignatureServerPAdESErrorFirmant();
            
            test.testUpgradePAdESSignature();
            
        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void testSignatureServerPAdESStatus401_Unathorized() throws ApiException, Exception {

        final String testName = "Firma en Servidor - Error 401- UNAUTHORIZED";
        final Integer expectedError = 401;

        ApiClientWithJsonSupport apiClient = getApiClient();
        apiClient.setPassword("badpassword");
        SignatureOnServerV2Api apiError = new SignatureOnServerV2Api(apiClient);
        
        final boolean useTimeStamp = false;

        //Document fileToSign = llegirFitxer("./testfiles/hola.pdf", "application/pdf");
        File fileToSign = new File("./testfiles_/hola.pdf"); 
        internalTestSignatureServerPAdES(testName, expectedError, apiError, fileToSign, useTimeStamp);

        System.out.println("Test OK");

    }

    public void testSignatureServerPAdESErrorFirmant() throws ApiException, Exception {

        final String testName = "Firma en Servidor - Error Firmant xml amb PAdES";
        final Integer expectedError = null;

        SignatureOnServerV2Api api = getApi();
        
        final boolean useTimeStamp = false;

        try {
            //Document fileToSign = llegirFitxer("./src/main/resources/sample.xml", "application/xml");
            File fileToSign = new File("./src/main/resources/sample.xml");
            internalTestSignatureServerPAdES(testName, expectedError, api, fileToSign, useTimeStamp);
            throw new Exception("S'ha enviat un fitxer XML per firma en format PAdES i s'esperava un error.");
        } catch (EstatFinalNoOK e) {

            if (StatusConstants.STATUS_FINAL_ERROR.getValue().equals(e.getInternalCode())) {
                System.out.println("Test OK");
            } else {
                throw new Exception("S'ha rebut un error de EstatFinalNoOK però s'esperava un internalCode "
                        + StatusConstants.STATUS_FINAL_ERROR.getValue() + " però s'ha rebut un " + e.getInternalCode());
            }
        }

    }

    /**
     * Operacio de firma simple en servidor d&#39;un document PDF
     *
     * @throws ApiException if the Api call fails
     */
    public void testSignatureServerPAdES() throws ApiException, Exception {

        final String testName = "Firma PAdES en Servidor";
        final Integer expectedError = null;

        SignatureOnServerV2Api api = getApi();
        
        final boolean useTimeStamp = false;

        List<File> documents = getPdfDocumentsToSign(getConfigProperties());
        for (File document : documents) {
            internalTestSignatureServerPAdES(testName, expectedError, api, document,  useTimeStamp);
        }

    }
    
    
    public void testSignatureServerPadesWithTimestamp() throws ApiException, Exception {

        final String testName = "Firma PAdES en Servidor amb Segell de Temps";
        final Integer expectedError = null;

        SignatureOnServerV2Api api = getApi();
        
        final boolean useTimeStamp = true;

        List<File> documents = getPdfDocumentsToSign(getConfigProperties());
        for (File document : documents) {
            internalTestSignatureServerPAdES(testName, expectedError, api, document,  useTimeStamp);
        }

    }


    protected SignDocumentResponseV2 internalTestSignatureServerPAdES(final String testName, final Integer expectedError,
            SignatureOnServerV2Api api, File file,  boolean useTimeStamp) throws Exception, ApiException {
        Properties prop = getConfigProperties();

        String languageUI = prop.getProperty("languageUI", "ca");

        String perfil = prop.getProperty(PROFILE_PADES_PROPERTY);
        if (perfil == null || perfil.trim().isEmpty()) {
            avisPerPerfilBuit(PROFILE_PADES_PROPERTY);
            perfil = null;
        }

        // Document fileToSign = llegirFitxer(file == null ? "src/main/resources/hola-test.pdf" : file, "application/pdf");

        System.out.println(" PERFIL => " + perfil);
        System.out.println(" FILE NOM => " + file.getName());
        return internalSignDocument(api, perfil, file, languageUI, testName, expectedError,  useTimeStamp);
    }
    
    
    
    protected SignDocumentResponseV2 internalTestSignatureServerCAdES(final String testName, final Integer expectedError,
            SignatureOnServerV2Api api, File file,  boolean useTimeStamp) throws Exception, ApiException {
        Properties prop = getConfigProperties();

        String languageUI = prop.getProperty("languageUI", "ca");

        String perfil = prop.getProperty(PROFILE_CADES_PROPERTY);
        if (perfil == null || perfil.trim().isEmpty()) {
            avisPerPerfilBuit(PROFILE_CADES_PROPERTY);
            perfil = null;
        }

        // Document fileToSign = llegirFitxer(file == null ? "src/main/resources/hola-test.pdf" : file, "application/pdf");

        System.out.println(" PERFIL => " + perfil);
        System.out.println(" FILE NOM => " + file.getName());
        return internalSignDocument(api, perfil, file, languageUI, testName, expectedError,  useTimeStamp);
    }
    
    

    protected SignDocumentResponseV2 internalSignDocument(SignatureOnServerV2Api api, final String perfil,
            File file, String languageUI, String testName, Integer expectedError, boolean useTimeStamp)
            throws ApiException, Exception {

        System.out.println("============================ " + testName + " ============================");
        try {
            String signID = "1";
            String name = file.getName();
            String reason = "Per aprovar pressuposts";
            String location = "Palma";

            int signNumber = 1;
            String languageSign = "ca";
            long tipusDocumentalID = 99; // =TD99

            String alias = getConfigProperties().getProperty("alias");

            FileInfoSignatureV2 fileInfoSignature = new FileInfoSignatureV2();
            //fileInfoSignature.setFileToSign(fileToSign);
            fileInfoSignature.setSignID(signID);
            fileInfoSignature.setName(name);
            fileInfoSignature.setReason(reason);
            fileInfoSignature.setLocation(location);
            fileInfoSignature.setSignNumber(signNumber);
            fileInfoSignature.setLanguageSign(languageSign);

            fileInfoSignature.setDocumentType(tipusDocumentalID);
            
            fileInfoSignature.setUseTimeStamp(useTimeStamp);

            // Es la configuració del Servidor (deixam el valor per defecte)
            String username = alias;
            String administrationID = null;
            String signerEmail = null;

            CommonInfo commonInfo = new CommonInfo();
            commonInfo.setSignProfile(perfil);
            commonInfo.setLanguageUI(languageUI);
            commonInfo.setUsername(username);
            commonInfo.setAdministrationID(administrationID);
            commonInfo.setSignerEmail(signerEmail);

            System.out.println("languageUI = |" + languageUI + "|");

            SignDocumentRequestV2 signature = new SignDocumentRequestV2();

            signature.setCommonInfo(commonInfo);
            signature.setFileInfoSignature(fileInfoSignature);
            
            
            System.out.println("\n\nEnviant petició de firma al servidor " + signature.getCommonInfo());

            SignDocumentResponseV2 fullResults = api.signdocument(signature, file, null);

            System.out.println(fullResults.getSignPlugin());

            ProcessStatus transactionStatus = fullResults.getStatus();

            int status = transactionStatus.getStatus();

            if (status == (int) StatusConstants.STATUS_INITIALIZING.getValue()) {
                throw new EstatFinalNoOK(status, "Rebut estat Initializing ...Unknown Error (???)");

            } else if (status == (int) StatusConstants.STATUS_IN_PROGRESS.getValue()) {
                throw new EstatFinalNoOK(status, "Rebut estat IN_PROGRESS ... Unknown Error (????) ");

            } else if (status == (int) StatusConstants.STATUS_FINAL_ERROR.getValue()) {

                throw new EstatFinalNoOK(status, "Rebut estat ERROR: " + transactionStatus.getErrorMessage(),
                        transactionStatus.getErrorStackTrace());

            } else if (status == (int) StatusConstants.STATUS_CANCELLED.getValue()) {
                throw new EstatFinalNoOK(status, "Rebut estat CANCELED: S'ha cancel·lat el procés de firmat.");

            } else if (status == (int) StatusConstants.STATUS_FINAL_OK.getValue()) {

                System.out.println(" ========= RESULTAT  =========");

                {

                    SignedFileInfo signedFileInfo = fullResults.getSignedFileInfo();
                    if (signedFileInfo != null) {
                        System.out.println(fullResults.getSignedFileInfo());
                    } else {
                        System.out.println("  Signed File Info: NULL");
                    }

                    System.err.println("  RESULT: OK");
                    Document fsf = fullResults.getSignedFile();
                    File result = new File(getResultsDirectory(), testName.replace(' ', '-') + "_" + fsf.getName());
                    FileOutputStream fos = new FileOutputStream(result);
                    fos.write(fsf.getData());
                    fos.flush();
                    fos.close();
                    System.out.println("  RESULT: Fitxer signat guardat en '" + result.getAbsolutePath() + "'");

                    return fullResults;

                } // Final for de fitxers firmats
            } else {
                throw new EstatFinalNoOK(null, "Rebut estat desconegut (" + status + ")");
            } // Final for de fitxers firmats
              // Final Case Firma OK
              // Final Switch Firma
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);

            return null;
        }

    }

    protected void avisPerPerfilBuit(final String perfilProperty) {
        System.out.println("           ================= AVIS ==============\n" + "La propietat " + perfilProperty
                + " està buida.\n"
                + "Això significa que si l'usuari aplicacio té més d'un perfil assignat, llavors llançarà un error.\n"
                + "          =====================================\n");
    }

    public void testUpgradePAdESSignature() throws ApiException, Exception {

        final String testName = "testUpgradePAdESSignature";
        final Integer expectedError = null;

        Document fileToUpgrade = llegirFitxer("testfiles/hola_signed.pdf", "application/pdf");

        File upgradedFileName = new File("results/hola_signed-upgraded.pdf");

        internalTestUpgrade(PROFILE_PADES_PROPERTY, fileToUpgrade, null, upgradedFileName, testName, expectedError);

    }

    protected UpgradeResponse internalTestUpgrade(final String perfilProperty, Document fileToUpgrade,
            Document documentDetached, File upgradedFileName, String testName, Integer expectedError)
            throws Exception, ApiException {

        System.out.println("============================ " + testName + " ============================");
        try {

            SignatureOnServerV2Api api = getApi();

            Properties prop = getConfigProperties();

            String perfil = prop.getProperty(perfilProperty);

            if (perfil == null || perfil.trim().isEmpty()) {
                avisPerPerfilBuit(PROFILE_PADES_PROPERTY);
                perfil = null;
            }

            /*
            UpgradeRequest upgradeRequest = new UpgradeRequest();

            upgradeRequest.setProfileCode(perfil);
            upgradeRequest.setDetachedDocument(documentDetached);
            upgradeRequest.setSignature(fileToUpgrade);
            */

            String languageUI = prop.getProperty("languageUI", "ca");

            // TODO FALTA !ª!!!!!!
            
            UpgradeResponse upgradeResponse = api.upgradeSignature(languageUI);
            
            System.out.println("============ SIGN MODE VALUES ============");
            for (SignModeConstants mode : SignModeConstants.values()) {
                System.out.println(mode.getValue() + " => " + mode.name());
            }
            
            System.out.println("==========================================");
            

            System.out.println(upgradeResponse.getUpgradedFileInfo().toString());

            Document upgraded = upgradeResponse.getUpgradedFile();

            guardarFitxer(upgraded.getData(), upgradedFileName);

            return upgradeResponse;

        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }
    }

    @Override
    public SignatureOnServerV2Api getApi() throws Exception {
        return getApi(getApiClient());
    }

    @Override
    public SignatureOnServerV2Api getApi(ApiClientWithJsonSupport client) throws Exception {
        SignatureOnServerV2Api api = new SignatureOnServerV2Api(client);
        return api;
    }

    public class EstatFinalNoOK extends Exception {

        protected final Integer internalCode;

        protected final String errorStackTrace;

        public EstatFinalNoOK(Integer internalCode, String message) {
            this(internalCode, message, null);
        }

        public EstatFinalNoOK(Integer internalCode, String message, String errorStackTrace) {
            super(message);
            this.errorStackTrace = errorStackTrace;
            this.internalCode = internalCode;
        }

        public String getErrorStackTrace() {
            return errorStackTrace;
        }

        public Integer getInternalCode() {
            return internalCode;
        }

    }

    @Override
    protected Set<KeyValue> getLanguages(String lang) throws Exception {
        return getApi().getLanguages(lang);
    }

    @Override
    protected Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClientWithJsonSupport apiClient) throws Exception {
        return getApi(apiClient).getDocumentaryTypes(lang);
    }

    @Override
    protected Set<Profile> getProfiles(String lang) throws Exception {
        return getApi().getProfiles(lang);
    }

    @Override
    protected String getConfigPropertiesFile() {
        return "signatureonserver.properties";
    }

}
