package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;


import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature;
import io.swagger.v3.oas.annotations.media.Schema;

public class SignDocumentRequest {
    
    @Schema(
            description = "Configuracions generals de firma i identificacio del solicitant i solicitat",
            example = "",
           required = true)
	CommonInfo commonInfo;

    @Schema(
            description = "Informació especifica per a realitzar la firma",
            example = "",
           required = true)
	FileInfoSignature fileInfoSignature;

	public SignDocumentRequest() {
		super();
	}

	public SignDocumentRequest(CommonInfo commonInfo,
			FileInfoSignature fileInfoSignature) {
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

	public FileInfoSignature getFileInfoSignature() {
		return fileInfoSignature;
	}

	public void setFileInfoSignature(FileInfoSignature fileInfoSignature) {
		this.fileInfoSignature = fileInfoSignature;
	}

}
