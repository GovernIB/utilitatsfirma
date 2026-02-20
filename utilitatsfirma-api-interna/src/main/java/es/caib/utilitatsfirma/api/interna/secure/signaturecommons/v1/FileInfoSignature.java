package es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({
    "fileToSign",
    "previusSignatureDetachedFile",
    "signID",
    "name",
    "reason",
    "location",
    "signNumber",
    "languageSign",
    "expedientCodi",
    "expedientNom",
    "expedientUrl",
    "procedimentCodi",
    "procedimentNom",
    "documentType",
    "additionalInformation",
    "useTimeStamp"
  })
@Schema(description = "Informació especifica per a realitzar la firma")
public class FileInfoSignature extends FileInfoSignatureV2 {

    @Schema(description = "Document a signar", required = true)
    protected Document fileToSign;

    /**
     * Només per CAdES i XAdEs Detached amb firma prèvia
     */
    @Schema(description = "Només per CAdES i XAdEs Detached amb firma prèvia", required = false)
    protected Document previusSignatureDetachedFile = null;

    /**
     * 
     */
    public FileInfoSignature() {
        super();
    }

    /**
     * @param fileToSign
     * @param signID
     * @param name
     * @param reason
     * @param location
     * @param signerEmail
     * @param signNumber
     * @param languageSign
     */
    public FileInfoSignature(Document fileToSign, String signID, String name, String reason, String location,
            int signNumber, String languageSign, Long documentType, Boolean useTimeStamp) {
        super(signID, name, reason, location, signNumber, languageSign, null, null, null, null, null, documentType,
                null, useTimeStamp);
        this.fileToSign = fileToSign;

    }

    /**
     * @param fileToSign
     * @param previusSignatureFile
     * @param signID
     * @param name
     * @param reason
     * @param location
     * @param signerEmail
     * @param signNumber
     * @param languageSign
     * @param operationSign
     * @param additionalInformation
     */
    public FileInfoSignature(Document fileToSign, Document previusSignatureDetachedFile, String signID, String name,
            String reason, String location, String signerEmail, int signNumber, String languageSign,
            List<KeyValue> additionalInformation, Boolean useTimeStamp) {
        super(signID, name, reason, location, signNumber, languageSign, null, null, null, null, null, null,
                additionalInformation, useTimeStamp);
        this.fileToSign = fileToSign;
        this.previusSignatureDetachedFile = previusSignatureDetachedFile;

    }
    
    
    public FileInfoSignature(Document fileToSign, Document previusSignatureDetachedFile, FileInfoSignatureV2 fileInfoSignatureV2) {
        super(fileInfoSignatureV2.getSignID(), fileInfoSignatureV2.getName(), fileInfoSignatureV2.getReason(),
                fileInfoSignatureV2.getLocation(), fileInfoSignatureV2.getSignNumber(),
                fileInfoSignatureV2.getLanguageSign(), fileInfoSignatureV2.getExpedientCodi(),
                fileInfoSignatureV2.getExpedientNom(), fileInfoSignatureV2.getExpedientUrl(),
                fileInfoSignatureV2.getProcedimentCodi(), fileInfoSignatureV2.getProcedimentNom(),
                fileInfoSignatureV2.getDocumentType(), fileInfoSignatureV2.getAdditionalInformation(),
                fileInfoSignatureV2.getUseTimeStamp());
        this.fileToSign = fileToSign;
        this.previusSignatureDetachedFile = previusSignatureDetachedFile;

    }

    public Document getFileToSign() {
        return fileToSign;
    }

    public void setFileToSign(Document fileToSign) {
        this.fileToSign = fileToSign;
    }

    public Document getPreviusSignatureDetachedFile() {
        return previusSignatureDetachedFile;
    }

    public void setPreviusSignatureDetachedFile(Document previusSignatureDetachedFile) {
        this.previusSignatureDetachedFile = previusSignatureDetachedFile;
    }

}