package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.servicesforutilitatsfirma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Map.Entry;
import org.jboss.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.internal.ClientConfiguration;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiClient;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.ApiException;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services.StringUtil;

/**
 * 
 * @author anadal (u80067)
 * 20 feb 2026 10:59:10
 */
public class ApiClientWithJsonSupport extends ApiClient {

    public ApiClientWithJsonSupport() {
        super();
    }

    /**
     * Serialize the given Java object into string entity according the given
     * Content-Type (only JSON is supported for now).
     * @param obj the object to serialize
     * @param formParams the form parameters
     * @param contentType the content type
     * @return an {@code Entity}
     * @throws ApiException on failure to serialize
     */
    @Override
    public Entity<?> serialize(Object obj, Map<String, Object> formParams, String contentType) throws ApiException {
        Entity<?> entity = null;
        if (contentType.startsWith("multipart/form-data")) {
            MultipartFormDataOutput multipart = new MultipartFormDataOutput();
            //MultiPart multiPart = new MultiPart();
            for (Entry<String, Object> param : formParams.entrySet()) {
                if (param.getValue() != null && param.getValue() instanceof File) {
                    File file = (File) param.getValue();
                    try {

                        String mime;
                        try {
                            mime = Files.probeContentType(file.toPath());
                        } catch (IOException e) {
                            mime = MediaType.APPLICATION_OCTET_STREAM;
                        }

                        MediaType mediaType = mime != null ? MediaType.valueOf(mime)
                                : MediaType.APPLICATION_OCTET_STREAM_TYPE;

                        System.out.println("Serializing multipart/form-data parameter (file): " + param.getKey()
                                + " with file name: " + file.getName() + " and media type: " + mediaType);

                        multipart.addFormData(param.getKey(), new FileInputStream(file), mediaType, file.getName());
                    } catch (FileNotFoundException e) {
                        throw new ApiException("Could not serialize multipart/form-data " + e.getMessage(), e, 500,
                                null);
                    }
                } else {

                    //System.out.println("Serializing multipart/form-data parameter: " + param.getKey());

                    String key = param.getKey();
                    if (key.equals("signDocumentRequest") || key.equals("signatureRequestedInformation")
                            || key.equals("profileCode")) {
                        try {

                            ObjectMapper mapper = getJSON().getContext(null);
                            //ObjectMapper mapper = new ObjectMapper();

                            String json = mapper.writeValueAsString(param.getValue());

                            //System.out.println("Serialized JSON 2222 for parameter " + param.getKey() + ": " + json);

                            multipart.addFormData(param.getKey(), json, MediaType.APPLICATION_JSON_TYPE);
                        } catch (Exception e) {
                            throw new ApiException(e);
                        }
                    } else {

                        multipart.addFormData(param.getKey(), param.getValue().toString(),
                                MediaType.APPLICATION_OCTET_STREAM_TYPE);
                    }
                }
            }
            GenericEntity<MultipartFormDataOutput> genericEntity = new GenericEntity<MultipartFormDataOutput>(
                    multipart) {
            };
            entity = Entity.entity(genericEntity, MediaType.MULTIPART_FORM_DATA_TYPE);
        } else if (contentType.startsWith("application/x-www-form-urlencoded")) {
            Form form = new Form();
            for (Entry<String, Object> param : formParams.entrySet()) {
                form.param(param.getKey(), parameterToString(param.getValue()));
            }
            entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
        } else {
            // We let jersey handle the serialization
            entity = Entity.entity(obj, contentType);
        }
        return entity;
    }

    /**
     * S'ha modificat el comportament per acceptar tot el que diu l'Operació
     */
    @Override
    public String selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) {
            return null;
        }
        return StringUtil.join(accepts, ",");
    }

    /**
     * Build the Client used to make HTTP requests.
     */
    @Override
    protected Client buildHttpClient(boolean debugging) {
        final ClientConfiguration clientConfig = new ClientConfiguration(ResteasyProviderFactory.getInstance());
        clientConfig.register(getJSON());
        // Registram els lectors multipart personalitzats. En un client JAX-RS creat de forma
        // programàtica els providers anotats amb @Provider no s'autodescobreixen, cal registrar-los.
        clientConfig.register(SignedDocumentResponseMultipartMessageBodyReader.class);
        clientConfig.register(UpgradeResponseMultipartMessageBodyReader.class);
        if (debugging) {
            clientConfig.register(Logger.class);
        }
        return ClientBuilder.newClient(clientConfig);
    }

}
