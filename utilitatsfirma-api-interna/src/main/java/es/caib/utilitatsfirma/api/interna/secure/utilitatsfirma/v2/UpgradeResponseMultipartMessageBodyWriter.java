// Java
package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.ByteArrayInputStream;

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
 * 26 feb 2026 14:03:29
 */
@Provider
@Produces(MediaType.MULTIPART_FORM_DATA)
public class UpgradeResponseMultipartMessageBodyWriter
        implements MessageBodyWriter<UpgradeResponseMultipart> {

    protected final Logger log = Logger.getLogger(this.getClass());

    @Context
    private Providers providers;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        log.info("\nUpgradeResponseMultipartMessageBodyWriter.isWriteable: type=" + type.getName()
                + ", mediaType=" + mediaType + "\n");
        return UpgradeResponseMultipart.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(UpgradeResponseMultipart entity, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {

        log.info("\nUpgradeResponseMultipartMessageBodyWriter.writeTo: type=" + type.getName() + ", mediaType="
                + mediaType + "\n");

        MultipartFormDataOutput multipart = new MultipartFormDataOutput();
        addUpgradeFilePart(multipart, entity);
        addUpgradeInformationPart(multipart, entity);

        MessageBodyWriter<MultipartFormDataOutput> delegate = providers.getMessageBodyWriter(
                MultipartFormDataOutput.class, MultipartFormDataOutput.class, annotations,
                MediaType.MULTIPART_FORM_DATA_TYPE);

        if (delegate == null) {
            throw new WebApplicationException("No s'ha trobat un escriptor per MultipartFormDataOutput");
        }

        delegate.writeTo(multipart, MultipartFormDataOutput.class, MultipartFormDataOutput.class, annotations,
                MediaType.MULTIPART_FORM_DATA_TYPE, httpHeaders, entityStream);
    }

    private void addUpgradeFilePart(MultipartFormDataOutput multipart, UpgradeResponseMultipart entity)
            throws IOException {
        byte[] upgradedFile = entity.getUpgradedFile();
        UpgradedFileInfo info = entity.getUpgradedFileInfo();
        if (upgradedFile == null || info == null) {
            return;
        }
        String fileName = info.getFileName() != null ? info.getFileName() : "unknowfilename.bin";
        String mime = info.getMimeType() != null ? info.getMimeType() : MediaType.APPLICATION_OCTET_STREAM;
        multipart.addFormData("upgradeFile", new ByteArrayInputStream(upgradedFile), MediaType.valueOf(mime)).getHeaders()
                .putSingle("Content-Disposition", "form-data; name=\"upgradedFile\"; filename=\"" + fileName + "\"");
    }

    private void addUpgradeInformationPart(MultipartFormDataOutput multipart, UpgradeResponseMultipart entity)
            throws IOException {
        UpgradedFileInfo info = entity.getUpgradedFileInfo();
        if (info == null) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        multipart.addFormData("upgradedFileInfo", json.getBytes(), MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public long getSize(UpgradeResponseMultipart entity, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }
}
