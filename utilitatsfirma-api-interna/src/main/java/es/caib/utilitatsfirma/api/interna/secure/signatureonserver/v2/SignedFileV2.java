package es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v2;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.Document;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignedFileInfoV2;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 13:09:48
 */
@Schema(description = "Representa una fitxer firmat i informació associada")
public class SignedFileV2 {

    @Schema(description = "Fitxer Signat", required = true)
    protected Document signedFile;

    @Schema(description = "Informació del fitxer Signat", required = true)
    protected SignedFileInfoV2 signedFileInfo;

    public SignedFileV2() {
        super();
    }

    public SignedFileV2(Document signedFile, SignedFileInfoV2 signedFileInfo) {
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

    public SignedFileInfoV2 getSignedFileInfo() {
        return signedFileInfo;
    }

    public void setSignedFileInfo(SignedFileInfoV2 signedFileInfo) {
        this.signedFileInfo = signedFileInfo;
    }
}
