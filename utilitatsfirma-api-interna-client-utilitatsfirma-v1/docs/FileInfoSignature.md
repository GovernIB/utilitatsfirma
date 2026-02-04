

# FileInfoSignature

Informació especifica per a realitzar la firma

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**fileToSign** | [**Document**](Document.md) |  |  |
|**previusSignatureDetachedFile** | [**Document**](Document.md) |  |  [optional] |
|**signID** | **String** | Identificador de la Firma |  |
|**name** | **String** | Nom descriptiu de la firma. Pot ser el nom del fitxer o un nom associat a la tasca per a la que es requereix la firma. |  |
|**reason** | **String** | Raó de la realització de la firma. |  |
|**location** | **String** | Lloc on es realitza la firma. |  |
|**signNumber** | **Integer** | Posició de la firma dins el flux de firma. |  |
|**languageSign** | **String** | Idioma del document. |  |
|**expedientCodi** | **String** | Codi de l&#39;expedient. |  [optional] |
|**expedientNom** | **String** | Nom de l&#39;expedient. |  [optional] |
|**expedientUrl** | **String** | URL de l&#39;expedient. |  [optional] |
|**procedimentCodi** | **String** | Codi del Procediment. |  [optional] |
|**procedimentNom** | **String** | Nom del Procediment. |  [optional] |
|**documentType** | **Long** | Tipus Documental. Si val null se li assigna 99 |  [optional] |
|**additionalInformation** | [**List&lt;KeyValue&gt;**](KeyValue.md) | Informació Addicional. |  [optional] |
|**useTimeStamp** | **Boolean** | Indica si s&#39;ha d&#39;utilitzar segell de temps en la firma. Només farà cas a aquest valor si la configuració de firma té definit el camp &#39;Politica de Segell de Temps&#39; com a &#39;L´usuari elegirà si vol segellat de temps&#39;  |  [optional] |



