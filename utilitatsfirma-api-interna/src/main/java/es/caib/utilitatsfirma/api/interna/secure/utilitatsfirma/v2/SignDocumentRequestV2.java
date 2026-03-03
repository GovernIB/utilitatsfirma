package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.FileInfoSignatureV2;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.CommonInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 9:52:37
 */
@Schema(description = "Petició de firma en servidor")
public class SignDocumentRequestV2 {

    @Schema(
            description = "Configuracions generals de firma i identificacio del solicitant i solicitat",
            example = "",
            required = true)
    protected CommonInfo commonInfo;

    @Schema(description = "Informació especifica per a realitzar la firma", example = "", required = true)
    protected FileInfoSignatureV2 fileInfoSignature;
    /*
    @Schema(description = "Nom del fitxer a signar. Només s'usa per les validacions", example = "", required = true)
    protected String fileToSignName;
    
    @Schema(description = "Nom del fitxer detached de la firma anterior. Només s'usa per les validacions", example = "", required = false)
    protected String previousSignatureDetachedFileName;
*/
    public SignDocumentRequestV2() {

        super();

    }

    public static SignDocumentRequestV2 valueOf(String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        SignDocumentRequestV2 sdr = mapper.readValue(json, SignDocumentRequestV2.class);
        return sdr;

    }

    public SignDocumentRequestV2(CommonInfo commonInfo, FileInfoSignatureV2 fileInfoSignature) {
        super();
        this.commonInfo = commonInfo;
        this.fileInfoSignature = fileInfoSignature;
    }

    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    public void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }

    public FileInfoSignatureV2 getFileInfoSignature() {
        return fileInfoSignature;
    }

    public void setFileInfoSignature(FileInfoSignatureV2 fileInfoSignature) {
        this.fileInfoSignature = fileInfoSignature;
    }
/*
    public String getFileToSignName() {
        return fileToSignName;
    }

    public void setFileToSignName(String fileToSignName) {
        this.fileToSignName = fileToSignName;
    }

    public String getPreviousSignatureDetachedFileName() {
        return previousSignatureDetachedFileName;
    }

    public void setPreviousSignatureDetachedFileName(String previousSignatureDetachedFileName) {
        this.previousSignatureDetachedFileName = previousSignatureDetachedFileName;
    }
*/
}
