/*
 * API Interna de utilitatsfirma de consulta de serveis per Firma en Servidor
 * Conjunt de Serveis REST de utilitatsfirma per atendre consultes de Firma en Servidor de utilitatsfirma
 *
 * The version of the OpenAPI document: 1.0-SNAPSHOT
 * Contact: firma@fundaciobit.org

 */

package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClientWithJsonSupport;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.CertificateTypeEidasConstants;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.CertificateTypeMineturConstants;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignatureRequestedInformation;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.ValidateSignatureResponse;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.ValidationStatusConstants;

import java.io.File;
import java.util.Properties;

/**
 * API tests for FirmaEnServidorV2Api
 *
 * @author anadal
 * 
 */
public class SignatureValidationV2ApiTest extends BasicAbstractV2ApiTest<SignatureValidationV2Api> {

    public static void main(String[] args) {
        SignatureValidationV2ApiTest test = new SignatureValidationV2ApiTest();
        try {

            test.testValidateSignatures();

        } catch (ApiException e) {
            test.processApiException(e, "Tests de Firma en Servidor", true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void testValidateSignatures() throws ApiException, Exception {

        SignatureRequestedInformation sri = new SignatureRequestedInformation();
        sri.setReturnValidationChecks(true);
        sri.setReturnCertificateInfo(true);
        sri.setReturnSignatureTypeFormatProfile(true);
        sri.setReturnTimeStampInfo(true);
        sri.setValidateCertificateRevocation(true);
        sri.setReturnCertificates(true);

        File[][] files = getFilesToValidate(getConfigProperties());

        for (int i = 0; i < files.length; i++) {

            // Per cada fitxer, fem una validació de la firma
            // i mostrem el resultat de la validació.
            File signatureDocument = files[i][0];

            System.out.println(" ======================= " + files[i][0].getName() + " ======================= ");

            File detachedDocument = null;
            if (files[i][1] == null) {
                detachedDocument = null;
            } else {
                detachedDocument = files[i][1];
            }
           

            ValidateSignatureResponse response = getApi().validateSignature(sri, signatureDocument, getLanguageUI(),
                    detachedDocument);

            if (response != null && response.getValidationStatus() != null) {

                if (response.getValidationStatus().getStatus() != null) {
                    System.out.println("** Estat Validació: "
                            + ValidationStatusConstants.fromValue(response.getValidationStatus().getStatus()).name());
                }

                if (response.getValidationStatus().getStatus() != ValidationStatusConstants.SIGNATURE_ERROR
                        .getValue()) {

                    if (response.getSignMode() != null) {
                        System.out.println("** Mode de firma: " + response.getSignMode());
                    }

                    if (response.getSignatureDetailInfo() != null) {
                        Integer clasification = response.getSignatureDetailInfo().get(0).getCertificateInfo()
                                .getCertificateTypeMinetur();
                        if (clasification != null) {
                            System.out.println("** Tipus Certificat Minetur: "
                                    + CertificateTypeMineturConstants.fromValue(clasification).name());
                        }

                        String clasificationEidas = response.getSignatureDetailInfo().get(0).getCertificateInfo()
                                .getCertificateTypeEidas();
                        if (clasificationEidas != null) {
                            System.out.println("** Tipus Certificat EIDAS: "
                                    + CertificateTypeEidasConstants.fromValue(clasificationEidas).name());
                        }

                    }

                }
            }
            System.out.println("---------------");
            System.out.println(response);
        }

    }

    @Override
    public SignatureValidationV2Api getApi() throws Exception {
        return getApi(getApiClient());
    }

    @Override
    public SignatureValidationV2Api getApi(ApiClientWithJsonSupport client) throws Exception {
        SignatureValidationV2Api api = new SignatureValidationV2Api(client);
        return api;
    }

    protected File[][] getFilesToValidate(Properties prop) throws Exception {

        String files = prop.getProperty("files");
        String[] parts = files.split(",");
        File[][] filesToSign = new File[parts.length][];

        for (int i = 0; i < parts.length; i++) {

            String nom = prop.getProperty("file." + parts[i] + ".name");
            System.out.println("*** FILE[" + parts[i] + "]");
            System.out.println("    Name = " + nom);
            String mime = prop.getProperty("file." + parts[i] + ".mime");

            System.out.println("    Mime: ]" + mime + "[");

            File fileToSign = new File(nom); //, mime);
            //System.out.println("    Mida: " + fileToSign.getData().length + " bytes");

            filesToSign[i] = new File[2];
            filesToSign[i][0] = fileToSign;

            String detached = prop.getProperty("file." + parts[i] + ".detached");

            if (detached == null) {
                filesToSign[i][1] = null;
            } else {
                //Document detachedDoc = llegirFitxer(detached, "application/octet-stream");
                filesToSign[i][1] = new File(detached);
            }

        }

        return filesToSign;
    }

    protected String getConfigPropertiesFile() {
        return "./signaturevalidation.properties";
    }

}
