package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.Document;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal(u80067)
 *
 */

public class UpgradeRequest {
    @Schema(
            description = "Codi del perfil a utilitzar. Si no es defineix, llavors requerim que quest usuari aplicación només tengui un Perfil definit.",
            example = "",
            required = false)
    String profileCode;

    @Schema(description = "Firma a actualitzar", example = "", required = false)
    Document signature;

    @Schema(
            description = "Document detached. Només s'usa per les validacions",
            example = "",
            required = false)
    Document detachedDocument;

    /**
     * Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes
     */
    @Schema(
            description = "Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes",
            example = "",
            required = false)
    Document targetCertificate;

    public UpgradeRequest() {
        super();
    }

    public UpgradeRequest(String profileCode, Document signature, Document detachedDocument,
            Document targetCertificate) {
        super();
        this.profileCode = profileCode;
        this.signature = signature;
        this.detachedDocument = detachedDocument;
        this.targetCertificate = targetCertificate;
    }

    public String getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public Document getSignature() {
        return signature;
    }

    public void setSignature(Document signature) {
        this.signature = signature;
    }

    public Document getTargetCertificate() {
        return targetCertificate;
    }

    public void setTargetCertificate(Document targetCertificate) {
        this.targetCertificate = targetCertificate;
    }

    public Document getDetachedDocument() {
        return detachedDocument;
    }

    public void setDetachedDocument(Document detachedDocument) {
        this.detachedDocument = detachedDocument;
    }

}
