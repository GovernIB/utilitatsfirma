package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import com.fasterxml.jackson.databind.ObjectMapper;

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
                if (param.getValue() instanceof File) {
                    File file = (File) param.getValue();
                    try {
                        multipart.addFormData(param.getKey(), new FileInputStream(file),
                                MediaType.APPLICATION_OCTET_STREAM_TYPE, file.getName());
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

}
