package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.DocumentaryType;
import java.io.File;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.KeyValue;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.Profile;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.RestExceptionInfo;
import java.util.Set;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignDocumentRequestV2;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignDocumentResponseV2;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.UpgradeResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class SignatureOnServerV2Api {
  private ApiClient apiClient;

  public SignatureOnServerV2Api() {
    this(Configuration.getDefaultApiClient());
  }

  public SignatureOnServerV2Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<DocumentaryType>}
   * @throws ApiException if fails to make API call
   */
  public Set<DocumentaryType> getDocumentaryTypes(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v2/getDocumentaryTypes".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<DocumentaryType>> localVarReturnType = new GenericType<Set<DocumentaryType>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna els idiomes disponibles.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<KeyValue>}
   * @throws ApiException if fails to make API call
   */
  public Set<KeyValue> getLanguages(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v2/getLanguages".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<KeyValue>> localVarReturnType = new GenericType<Set<KeyValue>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna els perfils de firma.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<Profile>}
   * @throws ApiException if fails to make API call
   */
  public Set<Profile> getProfiles(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v2/getProfiles".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Set<Profile>> localVarReturnType = new GenericType<Set<Profile>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio de firma simple en servidor d&#39;un document
   * 
   * @param signDocumentRequest  (required)
   * @param fileToSign Document a signar (required)
   * @param previusSignatureDetachedFile Document detached. Només s&#39;usa per les validacions (optional)
   * @return a {@code SignDocumentResponseV2}
   * @throws ApiException if fails to make API call
   */
  public SignDocumentResponseV2 signdocument(SignDocumentRequestV2 signDocumentRequest, File fileToSign, File previusSignatureDetachedFile) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'signDocumentRequest' is set
    if (signDocumentRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'signDocumentRequest' when calling signdocument");
    }
    
    // verify the required parameter 'fileToSign' is set
    if (fileToSign == null) {
      throw new ApiException(400, "Missing the required parameter 'fileToSign' when calling signdocument");
    }
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v2/signdocument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    if (signDocumentRequest != null)
      localVarFormParams.put("signDocumentRequest", signDocumentRequest);
if (fileToSign != null)
      localVarFormParams.put("fileToSign", fileToSign);
if (previusSignatureDetachedFile != null)
      localVarFormParams.put("previusSignatureDetachedFile", previusSignatureDetachedFile);

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<SignDocumentResponseV2> localVarReturnType = new GenericType<SignDocumentResponseV2>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio de upgrade de firma digital
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code UpgradeResponse}
   * @throws ApiException if fails to make API call
   */
  public UpgradeResponse upgradeSignature(String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v2/upgradeSignature".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<UpgradeResponse> localVarReturnType = new GenericType<UpgradeResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna la versió d&#39;aquest Servei
   * 
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String versio() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/signatureonserver/v2/versio".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
