package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.servicesforutilitatsfirma;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.MultipartNameAndMime;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.UpgradeResponseMultipart;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.UpgradedFileInfo;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

@Provider
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class UpgradeResponseMultipartMessageBodyReader implements MessageBodyReader<UpgradeResponseMultipart> {

    @Context
    private Providers providers;

    // ObjectMapper de Jackson reutilitzable (thread-safe). Reutilitzam la MATEIXA configuració
    // que fa servir l'ApiClient (classe JSON): registra JavaTimeModule, JsonNullableModule, RFC3339,
    // FAIL_ON_UNKNOWN_PROPERTIES=false, etc. Així deserialitzam explícitament les parts JSON del
    // multipart i no depenem de quin MessageBodyReader (Jackson vs JSON-B) triï RESTEasy segons
    // l'entorn (classpath/JDK/subsistema), evitant l'error "RESTEASY008200: JSON Binding deserialization error".
    private static final com.fasterxml.jackson.databind.ObjectMapper JACKSON_MAPPER =
            new es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.JSON().getContext(null);

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

        //System.out.println("UpgradeResponseMultipartReader.isReadable: type=" + type.getName() + ", mediaType=" + mediaType);

        return UpgradeResponseMultipart.class.isAssignableFrom(type);

    }

    @Override
    public UpgradeResponseMultipart readFrom(Class<UpgradeResponseMultipart> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException {

        UpgradeResponseMultipart response = new UpgradeResponseMultipart();

        try {
            // Obtain the existing MessageBodyReader<MultipartInput> registered by RESTEasy
            MessageBodyReader<MultipartInput> multipartReader = providers.getMessageBodyReader(MultipartInput.class,
                    null, annotations, mediaType);

            if (multipartReader == null) {
                throw new IllegalStateException("No MessageBodyReader<MultipartInput> found. "
                        + "Ensure resteasy-multipart-provider is on the client classpath.");
            }

            MultipartInput multipartInput = multipartReader.readFrom(MultipartInput.class, null, annotations, mediaType,
                    httpHeaders, entityStream);

            List<InputPart> parts = multipartInput.getParts();
            //System.out.println("UpgradeResponseMultipartReader.readFrom: parts count=" + parts.size());

            for (InputPart part : parts) {
                MultivaluedMap<String, String> headers = part.getHeaders();
                String contentDisposition = headers.getFirst("Content-Disposition");
                // System.out.println("UpgradeResponseMultipartReader.readFrom: part contentDisposition=" + contentDisposition);

                if (contentDisposition == null) {
                    continue;
                }

                if (contentDisposition.contains("name=\"upgradedFile\"")) {

                    if (part != null) {

                        InputStream is = part.getBody(InputStream.class, null);

                        File destFile = File.createTempFile("ApiClient_UtilitatsFirma_V2", "upgrade");

                        destFile.deleteOnExit();

                        Files.copy(is, destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                        response.setUpgradedFile(destFile);
                    }

                } else if (contentDisposition.contains("name=\"upgradedFileInfo\"")) {
                    if (part != null) {
                        // Deserialitzam amb Jackson explícitament (no delegam en el provider triat per RESTEasy)
                        String json = part.getBodyAsString();
                        UpgradedFileInfo fileInfo = JACKSON_MAPPER.readValue(json, UpgradedFileInfo.class);
                        response.setUpgradedFileInfo(fileInfo);
                    }
                } else if (contentDisposition.contains("name=\"upgradeFilePartInfo\"")) {
                    if (part != null) {
                        // Deserialitzam amb Jackson explícitament (no delegam en el provider triat per RESTEasy)
                        String json = part.getBodyAsString();
                        MultipartNameAndMime partInfo = JACKSON_MAPPER.readValue(json, MultipartNameAndMime.class);
                        response.setUpgradeFilePartInfo(partInfo);
                    }
                } else {
                    throw new WebApplicationException("Part desconeguda al multipart: " + contentDisposition);
                }
            }

        } catch (Exception e) {
            throw new IOException("Error deserializando respuesta multipart", e);
        }

        return response;
    }
}
