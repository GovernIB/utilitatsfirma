// Java
package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author anadal (u80067)
 * 26 feb 2026 12:10:34
 */
@Provider
@Produces(MediaType.MULTIPART_FORM_DATA)
public class SignedDocumentResponseMultipartMessageBodyWriter
        implements MessageBodyWriter<SignedDocumentResponseMultipart> {

    protected Logger log = Logger.getLogger(this.getClass());

    @Context
    private Providers providers;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

        log.info("\nSignedDocumentResponseMultipartMessageBodyWriter.isWriteable: type=" + type.getName()
                + ", mediaType=" + mediaType + "\n");

        return SignedDocumentResponseMultipart.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(SignedDocumentResponseMultipart entity, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {

        log.info("\nSignedDocumentResponseMultipartMessageBodyWriter.writeTo: type=" + type.getName() + ", mediaType="
                + mediaType + "\n");

        MultipartFormDataOutput multipart = new MultipartFormDataOutput();
        addSignedFilePart(multipart, entity);
        addSignedDocumentInformationPart(multipart, entity);

        MessageBodyWriter<MultipartFormDataOutput> delegate = providers.getMessageBodyWriter(
                MultipartFormDataOutput.class, MultipartFormDataOutput.class, annotations,
                MediaType.MULTIPART_FORM_DATA_TYPE);

        if (delegate == null) {
            throw new WebApplicationException("No s'ha trobat un escriptor per MultipartFormDataOutput");
        }

        delegate.writeTo(multipart, MultipartFormDataOutput.class, MultipartFormDataOutput.class, annotations,
                MediaType.MULTIPART_FORM_DATA_TYPE, httpHeaders, entityStream);
    }

    private void addSignedFilePart(MultipartFormDataOutput multipart, SignedDocumentResponseMultipart entity)
            throws IOException {
        File content = entity.getSignedFile();

        SignedDocumentInformation info = entity.getSignedDocumentInformation();

        if (content == null) {
            return;
        }
        String fileName = info.getSignedFileName() != null ? info.getSignedFileName() : content.getName();
        String mime = info.getSignedFileMime() != null ? info.getSignedFileMime() : MediaType.APPLICATION_OCTET_STREAM;
        multipart.addFormData("signedFile", new FileInputStream(content), MediaType.valueOf(mime)).getHeaders()
                .putSingle("Content-Disposition", "form-data; name=\"signedFile\"; filename=\"" + fileName + "\"");
    }

    private void addSignedDocumentInformationPart(MultipartFormDataOutput multipart,
            SignedDocumentResponseMultipart entity) throws IOException {
        if (entity.getSignedDocumentInformation() == null) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(entity.getSignedDocumentInformation());
        multipart.addFormData("signedDocumentInformation", json.getBytes(), MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public long getSize(SignedDocumentResponseMultipart entity, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }
}
