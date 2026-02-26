package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Resposta de la petició de firma en servidor en format multipart, amb informació del document signat i el fitxer signat
 * @author anadal (u80067)
 * 26 feb 2026 10:18:47
 */
public class SignedDocumentResponseMultipart {

    @FormParam("signedDocumentInformation")
    @Schema(description = "Informació del Document Signat", required = true)
    protected SignedDocumentInformation signedDocumentInformation;

    @FormParam("signedFile")
    @Schema(description = "Contingut del Document Signat", required = true, type = "string", format = "binary")
    protected File signedFile;

    public SignedDocumentResponseMultipart() {
        super();
    }

    public SignedDocumentResponseMultipart(SignedDocumentInformation signedDocumentInformation, File signedFile) {
        super();
        this.signedDocumentInformation = signedDocumentInformation;
        this.signedFile = signedFile;
    }

    public SignedDocumentInformation getSignedDocumentInformation() {
        return signedDocumentInformation;
    }

    public void setSignedDocumentInformation(SignedDocumentInformation signedDocumentInformation) {
        this.signedDocumentInformation = signedDocumentInformation;
    }

    public File getSignedFile() {
        return signedFile;
    }

    public void setSignedFile(File signedFile) {
        this.signedFile = signedFile;
    }

}
