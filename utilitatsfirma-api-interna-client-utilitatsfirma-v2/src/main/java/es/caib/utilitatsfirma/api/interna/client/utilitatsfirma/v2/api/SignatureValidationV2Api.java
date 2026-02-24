package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.CertificateTypeMineturConstants;
import java.io.File;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.RestExceptionInfo;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignatureRequestedInformation;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.ValidateSignatureResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class SignatureValidationV2Api {
  private ApiClient apiClient;

  public SignatureValidationV2Api() {
    this(Configuration.getDefaultApiClient());
  }

  public SignatureValidationV2Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Operacio de firma simple en servidor d&#39;un document
   * Operacio de firma simple en servidor d&#39;un document
   * @param signatureRequestedInformation  (required)
   * @param signatureDocument Signatura (required)
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param detachedDocument Document detached. (optional)
   * @return a {@code ValidateSignatureResponse}
   * @throws ApiException if fails to make API call
   */
  public ValidateSignatureResponse validateSignature(SignatureRequestedInformation signatureRequestedInformation, File signatureDocument, String languageUI, File detachedDocument) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'signatureRequestedInformation' is set
    if (signatureRequestedInformation == null) {
      throw new ApiException(400, "Missing the required parameter 'signatureRequestedInformation' when calling validateSignature");
    }
    
    // verify the required parameter 'signatureDocument' is set
    if (signatureDocument == null) {
      throw new ApiException(400, "Missing the required parameter 'signatureDocument' when calling validateSignature");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signaturevalidation/v2/validateSignature".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));

    
    
    if (signatureRequestedInformation != null)
      localVarFormParams.put("signatureRequestedInformation", signatureRequestedInformation);
if (signatureDocument != null)
      localVarFormParams.put("signatureDocument", signatureDocument);
if (detachedDocument != null)
      localVarFormParams.put("detachedDocument", detachedDocument);

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<ValidateSignatureResponse> localVarReturnType = new GenericType<ValidateSignatureResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
