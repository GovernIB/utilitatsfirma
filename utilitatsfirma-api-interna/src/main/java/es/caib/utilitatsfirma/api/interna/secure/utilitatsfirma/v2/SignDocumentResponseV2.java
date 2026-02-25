package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;


import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.Document;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ProcessStatus;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignPlugin;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignedFileInfoV2;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 13:09:25
 */
@Schema(description = "Resposta de la petició de firma en servidor")
public class SignDocumentResponseV2 extends SignatureResponseV2 {

    @Schema(description = "Informació del Plugin Utilitzat per a la realització de la Firma")
    protected SignPlugin signPlugin;

    public SignDocumentResponseV2() {
        super();
    }

    public SignDocumentResponseV2(SignatureResponseV2 sr, SignPlugin signPlugin) {
        super(sr.getSignID(), sr.getStatus(), sr.getSignedFile(), sr.getSignedFileInfo());
        this.signPlugin = signPlugin;
    }

    public SignDocumentResponseV2(String signID, ProcessStatus status, Document signedFile, SignedFileInfoV2 signedFileInfo,
            SignPlugin signPlugin) {
        super(signID, status, signedFile, signedFileInfo);
        this.signPlugin = signPlugin;
    }

    public SignPlugin getSignPlugin() {
        return signPlugin;
    }

    public void setSignPlugin(SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
    }

}
