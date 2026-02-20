package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.DocumentaryType;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.FileInfoSignatureV2;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.KeyValue;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.Profile;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;


/**
 *
 * @author anadal
 * 30 ene 2025 9:58:20
 */
public abstract class AbstractV2ApiTest<A> extends BasicAbstractV2ApiTest<A> {

    public static final String PROFILE_PADES_PROPERTY = "PROFILE_PADES";

    public static final String PROFILE_XADES_PROPERTY = "PROFILE_XADES";

    public static final String PROFILE_CADES_PROPERTY = "PROFILE_CADES";

    public static final String PROFILE_MIX_PADES_XADES_CADES = "PROFILE_MIX_PADES_XADES_CADES";

    public void callCommonTests() throws ApiException, Exception {

        callTipusDocumentalListTest();

        callTipusDocumentalListWithNotValidAppUserTest();

        callPerfilsDeFirmaListTest();

        callAvailableLanguagesTest();
    }

    public void callTipusDocumentalListWithNotValidAppUserTest() throws ApiException, Exception {

        final Integer expectedError = 401;
        ApiClientWithJsonSupport apiClient = getApiClient();
        apiClient.setPassword("holacaracola");
        internalTestTipusDocumentalList(expectedError, "callTipusDocumentalListWithNotValidAppUserTest", apiClient);
        System.out.println("Test OK");
    }

    public void callTipusDocumentalListTest() throws ApiException, Exception {

        final Integer expectedError = null;

        ApiClientWithJsonSupport apiClient = getApiClient();

        internalTestTipusDocumentalList(expectedError, "callTipusDocumentalListTest", apiClient);
    }

    public void callPerfilsDeFirmaListTest() throws ApiException, Exception {

        Integer expectedError = null;

        internalTestPerfilsDeFirmaList(expectedError, "callPerfilsDeFirmaListTest");
    }

    public void callAvailableLanguagesTest() throws ApiException, Exception {

        Integer expectedError = null;

        internalTestAvailableLanguages(expectedError, "callAvailable LanguagesTest");
    }

    protected Set<KeyValue> internalTestAvailableLanguages(Integer expectedError, String testName)
            throws ApiException, Exception {
        System.out.println("============================ " + testName + " ============================");
        try {

            String languageUI = getLanguageUI(getConfigProperties());

            Set<KeyValue> response = getLanguages(languageUI);
            if (expectedError != null) {
                log.error(testName + ": S'espera un error " + expectedError + " i la cridada ha funcionat.");
            }
            System.out.println(response.toString());
            return response;
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }

    }

    protected Set<Profile> internalTestPerfilsDeFirmaList(Integer expectedError, String testName)
            throws ApiException, Exception {
        System.out.println("============================ " + testName + " ============================");
        try {
            String languageUI = getLanguageUI(getConfigProperties());

            Set<Profile> response = getProfiles(languageUI);
            if (expectedError != null) {
                log.error(testName + ": S'espera un error " + expectedError + " i la cridada ha funcionat.");
            }
            System.out.println(response.toString());
            return response;
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }

    }

    protected Set<DocumentaryType> internalTestTipusDocumentalList(Integer expectedError, String testName,
            ApiClientWithJsonSupport apiClient) throws ApiException, Exception {
        System.out.println("============================ " + testName + " ============================");

        try {

            String languageUI = getLanguageUI(getConfigProperties());

            //ApiClient apiClient = getApiClient();

            Set<DocumentaryType> response = getDocumentaryTypes(languageUI, apiClient);
            if (expectedError != null) {
                throw new Exception(
                        testName + ": S'espera un error " + expectedError + " però la cridada ha funcionat.");
            }

            System.out.println(response.toString());
            return response;
        } catch (ApiException e) {
            checkExpectedError(expectedError, e);
            return null;
        }

    }
    
    
    protected File getResultsDirectory() {
        File res = new File("results");
        res.mkdirs();
        return res;
    }
    

    protected List<FileToSign> getPdfFilesToSign(Properties prop, long tipusDocumentalID) throws Exception {

        List<File> documentsToSign = getPdfDocumentsToSign(prop);
        List<FileToSign> filesToSign = new ArrayList<>(documentsToSign.size());
        int count = 0;
        for(File file: documentsToSign) {
            FileInfoSignatureV2 fileInfoSignature = new FileInfoSignatureV2();

            String signID = "Firma_" + count;
            
            fileInfoSignature.setSignID(signID);
            String name = file.getName();
            fileInfoSignature.setName(name);
            String reason = "Per aprovar pressuposts - " + file.getName();
            fileInfoSignature.setReason(reason);
            String location = "Palma";
            fileInfoSignature.setLocation(location);

            int signNumber = 1;
            fileInfoSignature.setSignNumber(signNumber);
            String languageSign = getLanguageUI(prop);
            fileInfoSignature.setLanguageSign(languageSign);

            fileInfoSignature.setDocumentType(tipusDocumentalID);

            
            FileToSign fts = new FileToSign();
            fts.setFileInfoSignatureV2(fileInfoSignature);
            fts.setFileToSign(file);
            
            
            filesToSign.add(fts); 
            count++;
        }

        return filesToSign;
    }
    
    
    protected List<File> getPdfDocumentsToSign(Properties prop) throws IOException {
        List<File> documentsToSign;
        String files = prop.getProperty("pdffiles");
        String[] parts = files.split(",");
         documentsToSign = new ArrayList<>(parts.length);

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("pdffile." + parts[i] + ".name");
            System.out.println("*** FILE[" + parts[i] + "]");
            System.out.println("    Name = " + nom);
            String mime = prop.getProperty("pdffile." + parts[i] + ".mime");

            System.out.println("    Mime: ]" + mime + "[");

            File f = new File(nom);
            documentsToSign.add(f);
            System.out.println("    Mida: " + f.length() + " bytes");
        }
        return documentsToSign;
    }

    /*
    protected Document[] getDocumentsToSign(Properties prop) throws IOException {
        Document[] documentsToSign;
        String files = prop.getProperty("files");
        String[] parts = files.split(",");
         documentsToSign = new Document[parts.length];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            System.out.println("*** FILE[" + parts[i] + "]");
            System.out.println("    Name = " + nom);
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("    Mime: ]" + mime + "[");

            documentsToSign[i] = llegirFitxer(nom, mime);
            System.out.println("    Mida: " + documentsToSign[i].getData().length + " bytes");
        }
        return documentsToSign;
    }
    */

    protected abstract Set<KeyValue> getLanguages(String lang) throws Exception;

    protected abstract Set<DocumentaryType> getDocumentaryTypes(String lang, ApiClientWithJsonSupport apiClient) throws Exception;

    protected abstract Set<Profile> getProfiles(String lang) throws Exception;

}
