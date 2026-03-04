package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import es.caib.utilitatsfirma.api.interna.multipartutils.IMessageBodyReader;
import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartNameAndMime;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureRequestedInformation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 3 mar 2026 14:50:26
 */
@JsonPropertyOrder({
    "signatureRequestedInformation",
    "signatureDocument",
    "detachedDocument"
  })
public class ValidateSignatureRequestMultipart implements IMessageBodyReader {

    @Parameter(
            required = true,
            schema = @Schema(implementation = SignatureRequestedInformation.class))
    @FormParam("signatureRequestedInformation")
    protected SignatureRequestedInformation signatureRequestedInformation;

    @Parameter(description = "Signatura", required = true)

    @FormParam(value = "signatureDocument")
    protected File signatureDocument;

    @Schema(hidden = true)
    protected MultipartNameAndMime signatureDocumentPartInfo;

    @Parameter(
            description = "Document detached.",
            required = false)
    @FormParam("detachedDocument")
    protected File detachedDocument;

    @Schema(hidden = true)
    protected MultipartNameAndMime detachedDocumentPartInfo;

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

    public File getDetachedDocument() {
        return detachedDocument;
    }

    public void setDetachedDocument(File detachedDocument) {
        this.detachedDocument = detachedDocument;
    }

    public MultipartNameAndMime getSignatureDocumentPartInfo() {
        return signatureDocumentPartInfo;
    }

    public void setSignatureDocumentPartInfo(MultipartNameAndMime signatureDocumentPartInfo) {
        this.signatureDocumentPartInfo = signatureDocumentPartInfo;
    }

    public MultipartNameAndMime getDetachedDocumentPartInfo() {
        return detachedDocumentPartInfo;
    }

    public void setDetachedDocumentPartInfo(MultipartNameAndMime detachedDocumentPartInfo) {
        this.detachedDocumentPartInfo = detachedDocumentPartInfo;
    }

    

}
