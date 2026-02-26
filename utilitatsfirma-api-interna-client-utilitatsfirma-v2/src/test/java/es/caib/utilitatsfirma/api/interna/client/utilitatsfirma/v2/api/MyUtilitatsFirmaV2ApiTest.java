package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

/**
 * API tests for MySignatureOnServerV1Api
 * @author anadal (u80067)
 * 18 feb 2026 8:13:38
 */
public class MyUtilitatsFirmaV2ApiTest extends UtilitatsFirmaV2ApiTest {

    public static void main(String[] args) {
        MyUtilitatsFirmaV2ApiTest test = new MyUtilitatsFirmaV2ApiTest();
        try {

           //test.callCommonTests();
            
           test.testSignatureServerPAdES();
            
          
            
            //test.testSignatureServerPadesWithTimestamp();
            
            //test.testSignatureServerCadesWithTimestamp();
            
            //test.testSignatureServerCAdES();
            
            //test.testUpgradePAdESSignature();
            
            
            /*
             * 
            File fileToUpgrade = new File("testfiles_validation/hola_signed_with_DNI.pdf");
            test.testUpgradePAdESSignature(fileToUpgrade);
            */
            

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    
    
    
    

    @Test
    public void testSignatureServerCAdES() throws Exception, FileNotFoundException, IOException {

        Properties prop = getConfigProperties();

        UtilitatsFirmaV2Api  api = getApi();

        final String perfil = prop.getProperty(PROFILE_CADES_PROPERTY);
        if (perfil == null) {
            logErrorPerfilBuit(PROFILE_CADES_PROPERTY);
        }

        File fileToSign = new File(prop.getProperty("cadesfile"));
        
        //String alias = prop.getProperty("alias");
        
        final String testName ="testSignatureServerCAdES";
        final Integer expectedError = null;
        boolean useTimeStamp = false;

        internalTestSignatureServerCAdES(testName, expectedError,
                api, fileToSign,  useTimeStamp );
    }
    
    

    protected void logErrorPerfilBuit(final String perfilProperty) {
        System.err.println("La propietat " + perfilProperty
                + " està buida. Això significa que si l'usuari aplicacio té més d'un perfil assignat, llavors llançarà un error.");
    }

    /*

    @Test
    public void testSignatureServerXAdESBinary() throws Exception, FileNotFoundException, IOException {

        Properties prop = getConfigProperties();

        ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

        final String perfil = prop.getProperty(PROFILE_XADES_PROPERTY);
        if (perfil == null) {
            logErrorPerfilBuit(PROFILE_XADES_PROPERTY);
        }

        FirmaSimpleFile fileToSign = getSimpleFileFromResource("foto.jpg", "image/jpeg");

        internalSignDocument(api, perfil, fileToSign, prop.getProperty("alias"));
    }

    @Test
    public void testSignatureServerXAdESXml() throws Exception, FileNotFoundException, IOException {

        Properties prop = getConfigProperties();

        ApiFirmaEnServidorSimple api = getApiFirmaEnServidorSimple(prop);

        final String perfil = prop.getProperty(PROFILE_XADES_PROPERTY);
        if (perfil == null) {
            logErrorPerfilBuit(PROFILE_XADES_PROPERTY);
        }

        FirmaSimpleFile fileToSign = getSimpleFileFromResource("sample.xml", "text/xml");

        internalSignDocument(api, perfil, fileToSign, prop.getProperty("alias"));
    }
*/
    

}
