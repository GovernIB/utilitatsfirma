package es.caib.utilitatsfirma.api.interna.multipartutils;


/**
 * Interface que han d'implementar totes les classes que representin un objecte Multipart si volen que la lectura per part de RestEasy es faci de forma automàtica.
 * 
 * (1) Els camps de les classes que pertanyin al multipart han d'incloure l'aanotació @ParamForm amb el nom del "Part"
 * (2) Si representen un fitxer han de ser de tipus File o tipus byte[]
 * (3) Els camps de tipus File o byte[] poden tenir un camp addicional amb el mateix nom amb sufix "PartInfo" de tipus
 *      MultipartFileInfo (per File) o MultipartByteArrayInfo(per byte[]) i anotació '@Schema(hidden = true)' 
 *      que inclourà informació del nom del fitxer i mime. Exemple:
 *      
 *           @FormParam(value = "signatureDocument")
 *           protected File signatureDocument;
 *       
 *           @Schema(hidden = true)
 *           protected MultipartFileInfo signatureDocumentPartInfo;
 *           
 *           
 *           
 * 
 * @author anadal (u80067)
 * 4 mar 2026 10:47:32
 */
public interface IMessageBodyReader {

}
