package es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v2;


import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.Document;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ProcessStatus;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignedFileInfoV2;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 13:09:55
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignatureResponseV2 extends SignedFileV2 {

    @Schema(description = "Identificador de la firma", required = true)
    protected String signID;

    @Schema(description = "Estat del procés de firma", required = true)
    protected ProcessStatus status;

    public SignatureResponseV2() {
        super();
    }

    public SignatureResponseV2(String signID, ProcessStatus status, Document signedFile, SignedFileInfoV2 signedFileInfo) {
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
