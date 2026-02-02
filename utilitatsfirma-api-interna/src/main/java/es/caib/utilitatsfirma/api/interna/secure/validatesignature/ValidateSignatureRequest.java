package es.caib.utilitatsfirma.api.interna.secure.validatesignature;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.Document;

/**
 * 
 * @author anadal
 * 26 ene 2026 8:10:25
 */
public class ValidateSignatureRequest {

    protected Document detachedDocument;

    protected Document signatureDocument;

    protected SignatureRequestedInformation signatureRequestedInformation;

    public ValidateSignatureRequest() {
    }

    public ValidateSignatureRequest(Document detachedDocument, Document signatureDocument,
            SignatureRequestedInformation signatureRequestedInformation) {
        super();
        this.detachedDocument = detachedDocument;
        this.signatureDocument = signatureDocument;
        this.signatureRequestedInformation = signatureRequestedInformation;
    }

    public Document getDetachedDocument() {
        return detachedDocument;
    }

    public void setDetachedDocument(Document detachedDocument) {
        this.detachedDocument = detachedDocument;
    }

    public Document getSignatureDocument() {
        return signatureDocument;
    }

    public void setSignatureDocument(Document signatureDocument) {
        this.signatureDocument = signatureDocument;
    }

    public SignatureRequestedInformation getSignatureRequestedInformation() {
        return signatureRequestedInformation;
    }

    public void setSignatureRequestedInformation(SignatureRequestedInformation signatureRequestedInformation) {
        this.signatureRequestedInformation = signatureRequestedInformation;
    }

}
