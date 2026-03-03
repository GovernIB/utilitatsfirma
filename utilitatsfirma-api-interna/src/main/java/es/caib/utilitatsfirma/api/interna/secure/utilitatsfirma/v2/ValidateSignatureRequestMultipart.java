package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import es.caib.utilitatsfirma.api.interna.secure.FormFileInfo;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureRequestedInformation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 3 mar 2026 14:50:26
 */
public class ValidateSignatureRequestMultipart {

    @Parameter(
            required = true,
            schema = @Schema(implementation = SignatureRequestedInformation.class))
    @FormParam("signatureRequestedInformation")
    protected SignatureRequestedInformation signatureRequestedInformation;

    @Parameter(description = "Signatura", required = true)

    @FormParam(value = "signatureDocument")
    protected File signatureDocument;

    @Schema(hidden = true)
    protected FormFileInfo signatureDocumentFileInfo;

    @Parameter(
            description = "Document detached.",
            required = false)
    @FormParam("detachedDocument")
    protected File detachedDocument;

    @Schema(hidden = true)
    protected FormFileInfo detachedDocumentFileInfo;

    public SignatureRequestedInformation getSignatureRequestedInformation() {
        return signatureRequestedInformation;
    }

    public void setSignatureRequestedInformation(SignatureRequestedInformation signatureRequestedInformation) {
        this.signatureRequestedInformation = signatureRequestedInformation;
    }

    public File getSignatureDocument() {
        return signatureDocument;
    }

    public void setSignatureDocument(File signatureDocument) {
        this.signatureDocument = signatureDocument;
    }

    public FormFileInfo getSignatureDocumentFileInfo() {
        return signatureDocumentFileInfo;
    }

    public void setSignatureDocumentFileInfo(FormFileInfo signatureDocumentFileInfo) {
        this.signatureDocumentFileInfo = signatureDocumentFileInfo;
    }

    public File getDetachedDocument() {
        return detachedDocument;
    }

    public void setDetachedDocument(File detachedDocument) {
        this.detachedDocument = detachedDocument;
    }

    public FormFileInfo getDetachedDocumentFileInfo() {
        return detachedDocumentFileInfo;
    }

    public void setDetachedDocumentFileInfo(FormFileInfo detachedDocumentFileInfo) {
        this.detachedDocumentFileInfo = detachedDocumentFileInfo;
    }

}
