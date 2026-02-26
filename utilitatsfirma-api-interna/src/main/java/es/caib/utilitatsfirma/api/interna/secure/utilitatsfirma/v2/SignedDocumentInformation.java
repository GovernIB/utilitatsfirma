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

    @Schema(description = "Nom del fitxer.", required = true)
    protected java.lang.String signedFileName;

    @Schema(description = "Tipus mime del fitxer signat.", required = false, type = "string")
    protected java.lang.String signedFileMime;

    @Schema(description = "Informació del fitxer Signat", required = true)
    protected SignedFileInfoV2 signedFileInfo;

    @Schema(description = "Informació del Plugin Utilitzat per a la realització de la Firma")
    protected SignPlugin signPlugin;

    public SignedDocumentInformation() {
        super();
    }

 
    public SignedDocumentInformation(String signID, ProcessStatus status, String signedFileName, String signedFileMime,
            SignedFileInfoV2 signedFileInfo, SignPlugin signPlugin) {
        super();
        this.signID = signID;
        this.status = status;
        this.signedFileName = signedFileName;
        this.signedFileMime = signedFileMime;
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

    public java.lang.String getSignedFileName() {
        return signedFileName;
    }

    public void setSignedFileName(java.lang.String signedFileName) {
        this.signedFileName = signedFileName;
    }

    public java.lang.String getSignedFileMime() {
        return signedFileMime;
    }

    public void setSignedFileMime(java.lang.String signedFileMime) {
        this.signedFileMime = signedFileMime;
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
