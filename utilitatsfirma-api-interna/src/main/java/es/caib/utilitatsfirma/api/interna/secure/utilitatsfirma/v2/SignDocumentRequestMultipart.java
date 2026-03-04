package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import es.caib.utilitatsfirma.api.interna.multipartutils.IMessageBodyReader;
import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartNameAndMime;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * 
 * @author anadal (u80067)
 * 3 mar 2026 9:50:00
 */
@Schema(
        description = "Petició de firma en servidor en format multipart,"
                + " amb informació de la firma i el document a firmar")
public class SignDocumentRequestMultipart implements IMessageBodyReader {

    @Parameter(
            name = "signDocumentRequest",
            description = "Dades de la firma i informació associada",
            required = true,
            schema = @Schema(implementation = SignDocumentRequestV2.class))
    @FormParam("signDocumentRequest")
    protected SignDocumentRequestV2 signDocumentRequest;

    @Parameter(description = "Document a signar", required = true)
    @FormParam(value = "fileToSign")
    protected File fileToSign;

    @Schema(hidden = true)
    protected MultipartNameAndMime fileToSignPartInfo;

    @Parameter(description = "Document detached. Només s'usa per les validacions", required = false)
    @FormParam("previousSignatureDetachedFile")
    protected File previousSignatureDetachedFile;

    @Schema(hidden = true)
    protected MultipartNameAndMime previousSignatureDetachedPartInfo;

    public SignDocumentRequestMultipart() {
        super();
    }

    public SignDocumentRequestV2 getSignDocumentRequest() {
        return signDocumentRequest;
    }

    public void setSignDocumentRequest(SignDocumentRequestV2 signDocumentRequest) {
        this.signDocumentRequest = signDocumentRequest;
    }

    public File getFileToSign() {
        return fileToSign;
    }

    public void setFileToSign(File fileToSign) {
        this.fileToSign = fileToSign;
    }

    public File getPreviousSignatureDetachedFile() {
        return previousSignatureDetachedFile;
    }

    public void setPreviousSignatureDetachedFile(File previousSignatureDetachedFile) {
        this.previousSignatureDetachedFile = previousSignatureDetachedFile;
    }

    public MultipartNameAndMime getFileToSignPartInfo() {
        return fileToSignPartInfo;
    }

    public void setFileToSignPartInfo(MultipartNameAndMime fileToSignPartInfo) {
        this.fileToSignPartInfo = fileToSignPartInfo;
    }

    public MultipartNameAndMime getPreviousSignatureDetachedPartInfo() {
        return previousSignatureDetachedPartInfo;
    }

    public void setPreviousSignatureDetachedPartInfo(MultipartNameAndMime previousSignatureDetachedPartInfo) {
        this.previousSignatureDetachedPartInfo = previousSignatureDetachedPartInfo;
    }

}
