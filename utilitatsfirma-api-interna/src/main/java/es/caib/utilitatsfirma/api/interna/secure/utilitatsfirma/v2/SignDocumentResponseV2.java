package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.Document;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ProcessStatus;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignPlugin;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 13:09:25
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignDocumentResponseV2 {

    @Schema(description = "Identificador de la firma", required = true)
    protected String signID;

    @Schema(description = "Estat del procés de firma", required = true)
    protected ProcessStatus status;

    @Schema(description = "Fitxer Signat", required = true)
    protected Document signedFile;

    @Schema(description = "Informació del fitxer Signat", required = true)
    protected SignedFileInfoV2 signedFileInfo;

    @Schema(description = "Informació del Plugin Utilitzat per a la realització de la Firma")
    protected SignPlugin signPlugin;

    public SignDocumentResponseV2() {
        super();
    }

    public SignDocumentResponseV2(String signID, ProcessStatus status, Document signedFile,
            SignedFileInfoV2 signedFileInfo) {
        this.signPlugin = null;
        this.signedFile = signedFile;
        this.signedFileInfo = signedFileInfo;
        this.signID = signID;
        this.status = status;
    }

    public SignDocumentResponseV2(String signID, ProcessStatus status, Document signedFile,
            SignedFileInfoV2 signedFileInfo, SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
        this.signedFile = signedFile;
        this.signedFileInfo = signedFileInfo;
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

    public Document getSignedFile() {
        return signedFile;
    }

    public void setSignedFile(Document signedFile) {
        this.signedFile = signedFile;
    }

    public SignedFileInfoV2 getSignedFileInfo() {
        return signedFileInfo;
    }

    public void setSignedFileInfo(SignedFileInfoV2 signedFileInfo) {
        this.signedFileInfo = signedFileInfo;
    }

    public SignPlugin getSignPlugin() {
        return signPlugin;
    }

    public void setSignPlugin(SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
    }

}
