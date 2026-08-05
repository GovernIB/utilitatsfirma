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
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignDocumentRequest;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignatureRequestedInformation;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignatureTypeInfo;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignedDocumentResponseMultipart;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.UpgradeResponseMultipart;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.ValidateSignatureResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class UtilitatsFirmaV2Api {
  private ApiClient apiClient;

  public UtilitatsFirmaV2Api() {
    this(Configuration.getDefaultApiClient());
  }

  public UtilitatsFirmaV2Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna informació dels diferents  tipus de firma que pot realitzar aquest usuari aplicació.
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code Set<SignatureTypeInfo>}
   * @throws ApiException if fails to make API call
   */
  public Set<SignatureTypeInfo> getAvailableSignatureTypes(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utilitatsfirma/v2/getAvailableSignatureTypes".replaceAll("\\{format\\}","json");

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

    GenericType<Set<SignatureTypeInfo>> localVarReturnType = new GenericType<Set<SignatureTypeInfo>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
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
    String localVarPath = "/secure/utilitatsfirma/v2/getDocumentaryTypes".replaceAll("\\{format\\}","json");

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
    String localVarPath = "/secure/utilitatsfirma/v2/getLanguages".replaceAll("\\{format\\}","json");

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
    String localVarPath = "/secure/utilitatsfirma/v2/getProfiles".replaceAll("\\{format\\}","json");

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
   * Retorna el conjunt de informació que pot retornar per la validació
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code SignatureRequestedInformation}
   * @throws ApiException if fails to make API call
   */
  public SignatureRequestedInformation getSignatureRequestedInformation(String languageUI) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utilitatsfirma/v2/getSignatureRequestedInformation".replaceAll("\\{format\\}","json");

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
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<SignatureRequestedInformation> localVarReturnType = new GenericType<SignatureRequestedInformation>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio de firma simple en servidor d&#39;un document
   * 
   * @param signDocumentRequest  (optional)
   * @param fileToSign  (optional)
   * @param previousSignatureDetachedFile  (optional)
   * @return a {@code SignedDocumentResponseMultipart}
   * @throws ApiException if fails to make API call
   */
  public SignedDocumentResponseMultipart signdocument(SignDocumentRequest signDocumentRequest, File fileToSign, File previousSignatureDetachedFile) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utilitatsfirma/v2/signdocument".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    if (signDocumentRequest != null)
      localVarFormParams.put("signDocumentRequest", signDocumentRequest);
if (fileToSign != null)
      localVarFormParams.put("fileToSign", fileToSign);
if (previousSignatureDetachedFile != null)
      localVarFormParams.put("previousSignatureDetachedFile", previousSignatureDetachedFile);

    final String[] localVarAccepts = {
      "application/json", "multipart/form-data"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<SignedDocumentResponseMultipart> localVarReturnType = new GenericType<SignedDocumentResponseMultipart>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Operacio de upgrade de firma digital
   * 
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param profileCode  (optional)
   * @param signature  (optional)
   * @param detachedDocument  (optional)
   * @param targetCertificate  (optional)
   * @return a {@code UpgradeResponseMultipart}
   * @throws ApiException if fails to make API call
   */
  public UpgradeResponseMultipart upgradeSignature(String languageUI, String profileCode, File signature, File detachedDocument, File targetCertificate) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utilitatsfirma/v2/upgradeSignature".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "languageUI", languageUI));

    
    
    if (profileCode != null)
      localVarFormParams.put("profileCode", profileCode);
if (signature != null)
      localVarFormParams.put("signature", signature);
if (detachedDocument != null)
      localVarFormParams.put("detachedDocument", detachedDocument);
if (targetCertificate != null)
      localVarFormParams.put("targetCertificate", targetCertificate);

    final String[] localVarAccepts = {
      "application/json", "multipart/form-data"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<UpgradeResponseMultipart> localVarReturnType = new GenericType<UpgradeResponseMultipart>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Validació de firmes digitals
   * Validació de firmes digitals
   * @param languageUI Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param signatureRequestedInformation  (optional)
   * @param signatureDocument  (optional)
   * @param detachedDocument  (optional)
   * @return a {@code ValidateSignatureResponse}
   * @throws ApiException if fails to make API call
   */
  public ValidateSignatureResponse validateSignature(String languageUI, SignatureRequestedInformation signatureRequestedInformation, File signatureDocument, File detachedDocument) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utilitatsfirma/v2/validateSignature".replaceAll("\\{format\\}","json");

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
  /**
   * Retorna la versió d&#39;aquest Servei
   * 
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String versio() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/utilitatsfirma/v2/versio".replaceAll("\\{format\\}","json");

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
