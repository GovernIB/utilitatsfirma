package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import es.caib.utilitatsfirma.api.interna.multipartutils.IMessageBodyReader;
import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartNameAndMime;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request multipart per upgrade de signatures.
 * @author anadal (u80067)
 * 3 mar 2026 13:23:46
 */
public class UpgradeRequestMultipart implements IMessageBodyReader {

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

    @Schema(hidden = true)
    protected MultipartNameAndMime signaturePartInfo;

    @Schema(hidden = true)
    protected MultipartNameAndMime detachedDocumentPartInfo;

    @Schema(hidden = true)
    protected MultipartNameAndMime targetCertificatePartInfo;

    public UpgradeRequestMultipart() {
        super();
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

    public MultipartNameAndMime getSignaturePartInfo() {
        return signaturePartInfo;
    }

    public void setSignaturePartInfo(MultipartNameAndMime signaturePartInfo) {
        this.signaturePartInfo = signaturePartInfo;
    }

    public MultipartNameAndMime getDetachedDocumentPartInfo() {
        return detachedDocumentPartInfo;
    }

    public void setDetachedDocumentPartInfo(MultipartNameAndMime detachedDocumentPartInfo) {
        this.detachedDocumentPartInfo = detachedDocumentPartInfo;
    }

    public MultipartNameAndMime getTargetCertificatePartInfo() {
        return targetCertificatePartInfo;
    }

    public void setTargetCertificatePartInfo(MultipartNameAndMime targetCertificatePartInfo) {
        this.targetCertificatePartInfo = targetCertificatePartInfo;
    }

}
