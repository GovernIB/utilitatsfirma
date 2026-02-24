# SignatureValidationV2Api

All URIs are relative to */utilitatsfirmaapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**validateSignature**](SignatureValidationV2Api.md#validateSignature) | **POST** /secure/signaturevalidation/v2/validateSignature | Operacio de firma simple en servidor d&#39;un document |



## validateSignature

> ValidateSignatureResponse validateSignature(signatureRequestedInformation, signatureDocument, languageUI, detachedDocument)

Operacio de firma simple en servidor d&#39;un document

Operacio de firma simple en servidor d&#39;un document

### Example

```java
// Import classes:
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.Configuration;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.auth.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.models.*;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api.SignatureValidationV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/utilitatsfirmaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        SignatureValidationV2Api apiInstance = new SignatureValidationV2Api(defaultClient);
        SignatureRequestedInformation signatureRequestedInformation = new SignatureRequestedInformation(); // SignatureRequestedInformation | 
        File signatureDocument = new File("/path/to/file"); // File | Signatura
        String languageUI = "ca"; // String | Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')
        File detachedDocument = new File("/path/to/file"); // File | Document detached.
        try {
            ValidateSignatureResponse result = apiInstance.validateSignature(signatureRequestedInformation, signatureDocument, languageUI, detachedDocument);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SignatureValidationV2Api#validateSignature");
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
| **signatureRequestedInformation** | [**SignatureRequestedInformation**](SignatureRequestedInformation.md)|  | |
| **signatureDocument** | **File**| Signatura | |
| **languageUI** | **String**| Idioma en que s&#39;han de retornar les dades i errors(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |
| **detachedDocument** | **File**| Document detached. | [optional] |

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

