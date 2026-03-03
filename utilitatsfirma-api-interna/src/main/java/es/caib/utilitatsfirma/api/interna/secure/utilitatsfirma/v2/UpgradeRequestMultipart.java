package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import es.caib.utilitatsfirma.api.interna.secure.FormFileInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request multipart per upgrade de signatures.
 * @author anadal (u80067)
 * 3 mar 2026 13:23:46
 */
public class UpgradeRequestMultipart {

    @Parameter(
            description = "Codi del perfil a utilitzar.",
            required = true,
            schema = @Schema(implementation = String.class))
    @FormParam(value = "profileCode")
    protected String profileCode;

    @Parameter(description = "Firma a actualitzar", required = true)
    @FormParam(value = "signature")
    protected File signature;

    @Parameter(description = "Document detached.", required = false)
    @FormParam("detachedDocument")
    protected File detachedDocument;

    @Parameter(
            description = "Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes",
            required = false)
    @FormParam("targetCertificate")
    protected File targetCertificate;
    
    
    @Parameter(hidden = true)
    @Schema(hidden = true)
    protected FormFileInfo signatureFileInfo;
    
    
    @Parameter(hidden = true)
    @Schema(hidden = true)
    protected FormFileInfo detachedDocumentFileInfo;
    
    @Parameter(hidden = true)
    @Schema(hidden = true)
    protected FormFileInfo targetCertificateFileInfo;
    

    public UpgradeRequestMultipart() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public File getSignature() {
        return signature;
    }

    public void setSignature(File signature) {
        this.signature = signature;
    }

    public File getDetachedDocument() {
        return detachedDocument;
    }

    public void setDetachedDocument(File detachedDocument) {
        this.detachedDocument = detachedDocument;
    }

    public File getTargetCertificate() {
        return targetCertificate;
    }

    public void setTargetCertificate(File targetCertificate) {
        this.targetCertificate = targetCertificate;
    }

    public FormFileInfo getSignatureFileInfo() {
        return signatureFileInfo;
    }

    public void setSignatureFileInfo(FormFileInfo signatureFileInfo) {
        this.signatureFileInfo = signatureFileInfo;
    }

    public FormFileInfo getDetachedDocumentFileInfo() {
        return detachedDocumentFileInfo;
    }

    public void setDetachedDocumentFileInfo(FormFileInfo detachedDocumentFileInfo) {
        this.detachedDocumentFileInfo = detachedDocumentFileInfo;
    }

    public FormFileInfo getTargetCertificateFileInfo() {
        return targetCertificateFileInfo;
    }

    public void setTargetCertificateFileInfo(FormFileInfo targetCertificateFileInfo) {
        this.targetCertificateFileInfo = targetCertificateFileInfo;
    }
    
    
    

}
