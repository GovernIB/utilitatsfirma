# UtilitatsFirmaV2Api

All URIs are relative to */utilitatsfirmaapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAvailableSignatureTypes**](UtilitatsFirmaV2Api.md#getAvailableSignatureTypes) | **GET** /secure/utilitatsfirma/v2/getAvailableSignatureTypes | Retorna informació dels diferents  tipus de firma que pot realitzar aquest usuari aplicació. |
| [**getDocumentaryTypes**](UtilitatsFirmaV2Api.md#getDocumentaryTypes) | **GET** /secure/utilitatsfirma/v2/getDocumentaryTypes | Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació |
| [**getLanguages**](UtilitatsFirmaV2Api.md#getLanguages) | **GET** /secure/utilitatsfirma/v2/getLanguages | Retorna els idiomes disponibles. |
| [**getProfiles**](UtilitatsFirmaV2Api.md#getProfiles) | **GET** /secure/utilitatsfirma/v2/getProfiles | Retorna els perfils de firma. |
| [**getSignatureRequestedInformation**](UtilitatsFirmaV2Api.md#getSignatureRequestedInformation) | **GET** /secure/utilitatsfirma/v2/getSignatureRequestedInformation | Retorna el conjunt de informació que pot retornar per la validació |
| [**signdocument**](UtilitatsFirmaV2Api.md#signdocument) | **POST** /secure/utilitatsfirma/v2/signdocument | Operacio de firma simple en servidor d&#39;un document |
| [**upgradeSignature**](UtilitatsFirmaV2Api.md#upgradeSignature) | **POST** /secure/utilitatsfirma/v2/upgradeSignature | Operacio de upgrade de firma digital |
| [**validateSignature**](UtilitatsFirmaV2Api.md#validateSignature) | **POST** /secure/utilitatsfirma/v2/validateSignature | Validació de firmes digitals |
| [**versio**](UtilitatsFirmaV2Api.md#versio) | **GET** /secure/utilitatsfirma/v2/versio | Retorna la versió d&#39;aquest Servei |



## getAvailableSignatureTypes

> Set&lt;SignatureTypeInfo&gt; getAvailableSignatureTypes(language)

Retorna informació dels diferents  tipus de firma que pot realitzar aquest usuari aplicació.

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<SignatureTypeInfo> result = apiInstance.getAvailableSignatureTypes(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#getAvailableSignatureTypes");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;SignatureTypeInfo&gt;**](SignatureTypeInfo.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getDocumentaryTypes

> Set&lt;DocumentaryType&gt; getDocumentaryTypes(language)

Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l&#39;entitat i tipus documentals de l&#39;usuari aplicació

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<DocumentaryType> result = apiInstance.getDocumentaryTypes(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#getDocumentaryTypes");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;DocumentaryType&gt;**](DocumentaryType.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getLanguages

> Set&lt;KeyValue&gt; getLanguages(language)

Retorna els idiomes disponibles.

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<KeyValue> result = apiInstance.getLanguages(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#getLanguages");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;KeyValue&gt;**](KeyValue.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getProfiles

> Set&lt;Profile&gt; getProfiles(language)

Retorna els perfils de firma.

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            Set<Profile> result = apiInstance.getProfiles(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#getProfiles");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**Set&lt;Profile&gt;**](Profile.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## getSignatureRequestedInformation

> SignatureRequestedInformation getSignatureRequestedInformation(languageUI)

Retorna el conjunt de informació que pot retornar per la validació

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        try {
            SignatureRequestedInformation result = apiInstance.getSignatureRequestedInformation(languageUI);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#getSignatureRequestedInformation");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**SignatureRequestedInformation**](SignatureRequestedInformation.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Retornada correctament la informació de la validació |  -  |


## signdocument

> SignedDocumentResponseMultipart signdocument(signDocumentRequest, fileToSign, previousSignatureDetachedFile)

Operacio de firma simple en servidor d&#39;un document

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        SignDocumentRequest signDocumentRequest = new SignDocumentRequest(); // SignDocumentRequest | 
        File fileToSign = new File("/path/to/file"); // File | 
        File previousSignatureDetachedFile = new File("/path/to/file"); // File | 
        try {
            SignedDocumentResponseMultipart result = apiInstance.signdocument(signDocumentRequest, fileToSign, previousSignatureDetachedFile);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#signdocument");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **signDocumentRequest** | [**SignDocumentRequest**](SignDocumentRequest.md)|  | [optional] |
| **fileToSign** | **File**|  | [optional] |
| **previousSignatureDetachedFile** | **File**|  | [optional] |

### Return type

[**SignedDocumentResponseMultipart**](SignedDocumentResponseMultipart.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: multipart/form-data
- **Accept**: application/json, multipart/form-data


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## upgradeSignature

> UpgradeResponseMultipart upgradeSignature(languageUI, profileCode, signature, detachedDocument, targetCertificate)

Operacio de upgrade de firma digital

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        String profileCode = "profileCode_example"; // String | 
        File signature = new File("/path/to/file"); // File | 
        File detachedDocument = new File("/path/to/file"); // File | 
        File targetCertificate = new File("/path/to/file"); // File | 
        try {
            UpgradeResponseMultipart result = apiInstance.upgradeSignature(languageUI, profileCode, signature, detachedDocument, targetCertificate);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#upgradeSignature");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |
| **profileCode** | **String**|  | [optional] |
| **signature** | **File**|  | [optional] |
| **detachedDocument** | **File**|  | [optional] |
| **targetCertificate** | **File**|  | [optional] |

### Return type

[**UpgradeResponseMultipart**](UpgradeResponseMultipart.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: multipart/form-data
- **Accept**: application/json, multipart/form-data


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## validateSignature

> ValidateSignatureResponse validateSignature(languageUI, signatureRequestedInformation, signatureDocument, detachedDocument)

Validació de firmes digitals

Validació de firmes digitals

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        SignatureRequestedInformation signatureRequestedInformation = new SignatureRequestedInformation(); // SignatureRequestedInformation | 
        File signatureDocument = new File("/path/to/file"); // File | 
        File detachedDocument = new File("/path/to/file"); // File | 
        try {
            ValidateSignatureResponse result = apiInstance.validateSignature(languageUI, signatureRequestedInformation, signatureDocument, detachedDocument);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#validateSignature");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |
| **signatureRequestedInformation** | [**SignatureRequestedInformation**](SignatureRequestedInformation.md)|  | [optional] |
| **signatureDocument** | **File**|  | [optional] |
| **detachedDocument** | **File**|  | [optional] |

### Return type

[**ValidateSignatureResponse**](ValidateSignatureResponse.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: multipart/form-data
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Operació realitzada correctament |  -  |


## versio

> String versio()

Retorna la versió d&#39;aquest Servei

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.UtilitatsFirmaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        UtilitatsFirmaV2Api apiInstance = new UtilitatsFirmaV2Api(defaultClient);
        try {
            String result = apiInstance.versio();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UtilitatsFirmaV2Api#versio");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No autoritzat |  -  |
| **500** | Error no controlat |  -  |
| **200** | Retornada correctament la versió d&#39;aquest Servei |  -  |

