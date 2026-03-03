// Java
package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.file.Files;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.utilitatsfirma.api.interna.secure.FormMethodUtils;

/**
 * 
 * @author anadal (u80067)
 * 3 mar 2026 10:50:07
 */
@Provider
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class SignDocumentRequestMultipartMessageBodyReader implements MessageBodyReader<SignDocumentRequestMultipart> {

    protected Logger log = Logger.getLogger(SignDocumentRequestMultipartMessageBodyReader.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Context
    private Providers providers;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return SignDocumentRequestMultipart.class.isAssignableFrom(type);
    }

    @Override
    public SignDocumentRequestMultipart readFrom(Class<SignDocumentRequestMultipart> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException {

        MessageBodyReader<MultipartInput> multipartReader = providers.getMessageBodyReader(MultipartInput.class, null,
                annotations, mediaType);
        if (multipartReader == null) {
            throw new IllegalStateException("No hi ha MessageBodyReader<MultipartInput>");
        }

        MultipartInput multipartInput = multipartReader.readFrom(MultipartInput.class, null, annotations, mediaType,
                httpHeaders, entityStream);

        SignDocumentRequestMultipart request = new SignDocumentRequestMultipart();

        for (InputPart part : multipartInput.getParts()) {
            String disposition = part.getHeaders().getFirst("Content-Disposition");
            if (disposition == null) {
                continue;
            }

            log.info("Processant part multipart amb Content-Disposition: " + disposition);

            if (disposition.contains("name=\"signDocumentRequest\"")) {
                request.setSignDocumentRequest(readRequest(part));
            } else if (disposition.contains("name=\"fileToSign\"")) {
                request.setFileToSign(copyToTemp(part, "fileToSign"));
            } else if (disposition.contains("name=\"previousSignatureDetachedFile\"")) {
                request.setPreviousSignatureDetachedFile(copyToTemp(part, "previousSignatureDetachedFile"));
            } else {
                throw new WebApplicationException("Part desconeguda al multipart: " + disposition);
            }
        }

        if (request.getSignDocumentRequest() == null) {
            throw new WebApplicationException(
                    "La part amb nom 'signDocumentRequest' val null però es un camp obligatori", 400);
        }

        if (request.getFileToSign() == null) {
            throw new WebApplicationException("La part amb nom 'fileToSign' val null però es un camp obligatori", 400);
        }

        return request;
    }

    private SignDocumentRequestV2 readRequest(InputPart part) throws IOException {
        // Comentari en català: deserialitzem el JSON a SignDocumentRequestV2
        try (InputStream is = part.getBody(InputStream.class, null)) {
            return objectMapper.readValue(is, SignDocumentRequestV2.class);
        }
    }

    private File copyToTemp(InputPart part, String name) throws IOException {
        // Comentari en català: guardem el contingut binari a un fitxer temporal
        try (InputStream is = part.getBody(InputStream.class, null)) {

            String fileName = FormMethodUtils.getFileName(part.getHeaders());

            File temp = File.createTempFile("SignDocumentRequestMultipart_" + name, "_" + fileName);

            log.info("\n\n\nCopiant contingut de la part '" + name + "' a fitxer temporal: " + temp.getAbsolutePath()
                    + "\n\n\n");

            Files.copy(is, temp.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return temp;
        }
    }
}
