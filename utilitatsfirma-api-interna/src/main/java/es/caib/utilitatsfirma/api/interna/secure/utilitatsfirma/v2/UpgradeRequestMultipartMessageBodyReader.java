// Java
package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import es.caib.utilitatsfirma.api.interna.secure.FormFileInfo;
import es.caib.utilitatsfirma.api.interna.secure.FormMethodUtils;

/**
 * 
 * @author anadal (u80067)
 * 3 mar 2026 14:18:27
 */
@Provider
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class UpgradeRequestMultipartMessageBodyReader implements MessageBodyReader<UpgradeRequestMultipart> {

    protected Logger log = Logger.getLogger(this.getClass());

    @Context
    private Providers providers;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return UpgradeRequestMultipart.class.isAssignableFrom(type);
    }

    @Override
    public UpgradeRequestMultipart readFrom(Class<UpgradeRequestMultipart> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException {

        MessageBodyReader<MultipartInput> multipartReader = providers.getMessageBodyReader(MultipartInput.class, null,
                annotations, mediaType);
        if (multipartReader == null) {
            throw new IllegalStateException("No hi ha MessageBodyReader<MultipartInput>");
        }

        MultipartInput multipartInput = multipartReader.readFrom(MultipartInput.class, null, annotations, mediaType,
                httpHeaders, entityStream);

        UpgradeRequestMultipart request = new UpgradeRequestMultipart();
        final String className = request.getClass().getName();

        /*
         * 
        
        
        
        @Parameter(description = "Document detached.", required = false)
        @FormParam("detachedDocument")
        protected File detachedDocument;
        
        @Parameter(
            description = "Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes",
            required = false)
        @FormParam("targetCertificate")
        protected File targetCertificate;
         */

        for (InputPart part : multipartInput.getParts()) {
            String disposition = part.getHeaders().getFirst("Content-Disposition");
            if (disposition == null) {
                continue;
            }

            log.info("Processant part multipart amb Content-Disposition: " + disposition);

            if (disposition.contains("name=\"profileCode\"")) {

                request.setProfileCode(FormMethodUtils.getObjectFromJsonMultipart(part, String.class));
            } else if (disposition.contains("name=\"signature\"")) {

                final String partName = "signature";
                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, partName, part);

                request.setSignatureFileInfo(fi);
                request.setSignature(fi.getFile());

            } else if (disposition.contains("name=\"detachedDocument\"")) {
                //request.setPreviousSignatureDetachedFile(copyToTemp(part, "previousSignatureDetachedFile"));

                final String partName = "detachedDocument";

                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, partName, part);

                request.setDetachedDocumentFileInfo(fi);
                request.setDetachedDocument(fi.getFile());
            } else if (disposition.contains("name=\"targetCertificate\"")) {
                //request.setPreviousSignatureDetachedFile(copyToTemp(part, "previousSignatureDetachedFile"));

                final String partName = "targetCertificate";

                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, partName, part);

                request.setTargetCertificate(fi.getFile());
                request.setTargetCertificateFileInfo(fi);
            } else {
                throw new WebApplicationException("Part desconeguda al multipart: " + disposition);
            }

        }

        if (request.getSignature() == null) {
            throw new WebApplicationException("La part amb nom 'signature' val null però es un camp obligatori", 400);
        }

        if (request.getProfileCode() == null) {
            throw new WebApplicationException("La part amb nom 'profileCode' val null però es un camp obligatori", 400);
        }

        return request;
    }

}
