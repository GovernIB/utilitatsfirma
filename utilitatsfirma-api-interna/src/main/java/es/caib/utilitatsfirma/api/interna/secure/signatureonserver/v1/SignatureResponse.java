package es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1;


import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.Document;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 jun 2025 12:25:13
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignatureResponse extends SignedFile {

    @Schema(description = "Identificador de la firma", required = true)
    protected String signID;

    @Schema(description = "Estat del procés de firma", required = true)
    protected ProcessStatus status;

    public SignatureResponse() {
        super();
    }

    public SignatureResponse(String signID, ProcessStatus status, Document signedFile, SignedFileInfo signedFileInfo) {
        super(signedFile, signedFileInfo);
        this.signID = signID;
        this.status = status;
    }

    public String getSignID() {
        return signID;
    }

    public void setSignID(String signID) {
        this.signID = signID;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

}
