package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.Document;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 may 2025 14:03:57
 */
@Schema(description = "Representa una fitxer firmat i informació associada")
public class SignedFile {

    @Schema(description = "Fitxer Signat", required = true)
    protected Document signedFile;

    @Schema(description = "Informació del fitxer Signat", required = true)
    protected SignedFileInfo signedFileInfo;

    public SignedFile() {
        super();
    }

    public SignedFile(Document signedFile, SignedFileInfo signedFileInfo) {
        super();
        this.signedFile = signedFile;
        this.signedFileInfo = signedFileInfo;
    }

    public Document getSignedFile() {
        return signedFile;
    }

    public void setSignedFile(Document signedFile) {
        this.signedFile = signedFile;
    }

    public SignedFileInfo getSignedFileInfo() {
        return signedFileInfo;
    }

    public void setSignedFileInfo(SignedFileInfo signedFileInfo) {
        this.signedFileInfo = signedFileInfo;
    }
}
