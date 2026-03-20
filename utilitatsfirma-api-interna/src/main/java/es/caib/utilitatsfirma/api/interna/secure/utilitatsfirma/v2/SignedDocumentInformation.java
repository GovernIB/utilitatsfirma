package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ProcessStatus;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignPlugin;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 13:09:25
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignedDocumentInformation {

    @Schema(description = "Identificador de la firma", required = true)
    protected String signID;

    @Schema(description = "Estat del procés de firma", required = true)
    protected ProcessStatus status;

    @Schema(description = "Informació del fitxer Signat", required = true)
    protected SignedFileInfo signedFileInfo;

    @Schema(description = "Informació del Plugin Utilitzat per a la realització de la Firma")
    protected SignPlugin signPlugin;

    public SignedDocumentInformation() {
        super();
    }

    public SignedDocumentInformation(String signID, ProcessStatus status, SignedFileInfo signedFileInfo,
            SignPlugin signPlugin) {
        super();
        this.signID = signID;
        this.status = status;
        this.signedFileInfo = signedFileInfo;
        this.signPlugin = signPlugin;
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

    public SignedFileInfo getSignedFileInfo() {
        return signedFileInfo;
    }

    public void setSignedFileInfo(SignedFileInfo signedFileInfo) {
        this.signedFileInfo = signedFileInfo;
    }

    public SignPlugin getSignPlugin() {
        return signPlugin;
    }

    public void setSignPlugin(SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
    }

}
