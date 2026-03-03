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
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureRequestedInformation;

/**
 * 
 * @author anadal (u80067)
 * 3 mar 2026 15:03:23
 */
@Provider
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ValidateSignatureRequestMultipartMessageBodyReader
        implements MessageBodyReader<ValidateSignatureRequestMultipart> {

    private static final Logger log = Logger.getLogger(ValidateSignatureRequestMultipartMessageBodyReader.class);

    @Context
    private Providers providers;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return ValidateSignatureRequestMultipart.class.isAssignableFrom(type);
    }

    @Override
    public ValidateSignatureRequestMultipart readFrom(Class<ValidateSignatureRequestMultipart> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException {

        MessageBodyReader<MultipartInput> multipartReader = providers.getMessageBodyReader(MultipartInput.class, null,
                annotations, mediaType);
        if (multipartReader == null) {
            throw new IllegalStateException("No hi ha MessageBodyReader<MultipartInput>");
        }

        MultipartInput multipartInput = multipartReader.readFrom(MultipartInput.class, null, annotations, mediaType,
                httpHeaders, entityStream);

        ValidateSignatureRequestMultipart request = new ValidateSignatureRequestMultipart();
        final String className = request.getClass().getName();

        for (InputPart part : multipartInput.getParts()) {
            String disposition = part.getHeaders().getFirst("Content-Disposition");
            if (disposition == null) {
                continue;
            }

            log.debug("Processant part multipart amb Content-Disposition: " + disposition);

            if (disposition.contains("name=\"signatureRequestedInformation\"")) {
                request.setSignatureRequestedInformation(FormMethodUtils.getObjectFromJsonMultipart(part,
                        SignatureRequestedInformation.class));

            } else if (disposition.contains("name=\"signatureDocument\"")) {
                final String partName = "signature";
                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, partName, part);
                request.setSignatureDocument(fi.getFile());
                request.setSignatureDocumentFileInfo(fi);

            } else if (disposition.contains("name=\"detachedDocument\"")) {
                final String partName = "detachedDocument";
                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, partName, part);
                request.setDetachedDocumentFileInfo(fi);
                request.setDetachedDocument(fi.getFile());

            }  else {
                throw new WebApplicationException("Part desconeguda al multipart: " + disposition);
            }
        }

        if (request.getSignatureRequestedInformation() == null) {
            throw new WebApplicationException("La part amb nom 'signatureRequestedInformation' val null però és un camp obligatori", 400);
        }
        if (request.getSignatureDocument() == null) {
            throw new WebApplicationException("La part amb nom 'signatureDocument' val null però és un camp obligatori", 400);
        }

        return request;
    }
}
