package es.caib.utilitatsfirma.api.interna.multipartutils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.InternalServerErrorException;
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

import es.caib.utilitatsfirma.api.interna.multipartutils.GenericMessageBodyReader.FieldInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 mar 2026 11:23:44
 */
@Provider
@Produces(MediaType.MULTIPART_FORM_DATA)
public class GenericMessageBodyWriter implements MessageBodyWriter<IMessageBodyWriter> {

    protected Logger log = Logger.getLogger(this.getClass());

    @Context
    private Providers providers;

    @Override
    public long getSize(IMessageBodyWriter t, Class<?> type, java.lang.reflect.Type genericType,
            java.lang.annotation.Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

        //log.info("\nSignedDocumentResponseMultipartMessageBodyWriter.isWriteable: type=" + type.getName()
        //                    + ", mediaType=" + mediaType + "\n");

        return IMessageBodyWriter.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(IMessageBodyWriter entity, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {

        MultipartFormDataOutput multipart = new MultipartFormDataOutput();

        // ----------------------------------------------

        Map<String, FieldInfo> fieldInfoByFormName = new HashMap<>();

        Map<String, Field> partInfoFieldsByFieldName = new HashMap<>();

        /*log.info("\n\n ---- ANALITZANT CAMPS DE LA CLASSE " + type.getName() + " -----------------\n\n"); */

        for (Field field : type.getDeclaredFields()) {
            Class<?> fieldType = field.getType();

            String formParamName = GenericMessageBodyReader.readFormParamValue(field);

            if (File.class.equals(fieldType) || byte[].class.equals(fieldType)) {

                fieldInfoByFormName.put(formParamName,
                        new FieldInfo(formParamName, field, File.class.equals(fieldType) ? 1 : 2));

            } else if (MultipartNameAndMime.class.isAssignableFrom(fieldType)) {

                String name = field.getName();

                if (!name.endsWith("PartInfo")) {
                    throw new InternalServerErrorException("Field name " + name + " should end with 'PartInfo'."
                            + " This class is reserved for attach additional information of a file or byte[] field");
                }

                partInfoFieldsByFieldName.put(name, field);

                Schema schema = field.getAnnotation(Schema.class);
                if (schema == null || !schema.hidden()) {
                    fieldInfoByFormName.put(formParamName, new FieldInfo(formParamName, field, 3));
                }

            } else {

                fieldInfoByFormName.put(formParamName, new FieldInfo(formParamName, field, 4));

                //log.info(" GENERIC CAMP: trobat camp field=" + field + " amb formparam name " + formParamName);

            }
        }

        /* log.info("\n\n ------ GUARDANT PARTS DE LA PETICIO HTTP  -----\n\n"); */

        for (Map.Entry<String, FieldInfo> es : fieldInfoByFormName.entrySet()) {

            
            final FieldInfo fieldInfo = es.getValue();

            // final String fieldName = es.getKey();
            //log.info("GENERIC-W: Processant camp: " + fieldName + "\n");

            // Reflection Field
            final Field reflectionField = fieldInfo.getField();

            // Extraure el nom del form param de la part multipart
            String formParamName = MultipartUtils.getValueOfFormParamAnnotation(reflectionField);

            /*
            log.info("GENERIC-W: formParamName= " + formParamName + "\n");
            
            log.info("GENERIC: fi.getType()= " + fieldInfo.getType() + "\n");
            
            log.info("GENERIC: fi.getField()= " + fieldInfo.getField() + "\n");
            */

            switch (fieldInfo.getType()) {

                // File
                case 1:
                    try {

                        // A partir del campo reflectionType, obtener el valor File de este campo para la instancia t.

                        File content = MultipartUtils.getFileFromInstanceAndField((Object) entity, reflectionField);

                        if (content != null) {

                            // Miram si té informació de nom i de mime
                            Field formPartInfoField = partInfoFieldsByFieldName
                                    .get(reflectionField.getName() + "PartInfo");

                            MultipartNameAndMime mnm = null;

                            if (formPartInfoField != null) {
                                mnm = MultipartUtils.getMultipartNameAndMimeFromInstanceAndField((Object) entity,
                                        formPartInfoField);
                            }

                            String fileName = (mnm == null || mnm.getFileName() == null
                                    || mnm.getFileName().trim().length() == 0) ? content.getName() : mnm.getFileName();
                            String mime;

                            if (mnm == null || mnm.getContentType() == null
                                    || mnm.getContentType().trim().length() == 0) {

                                mime = Files.probeContentType(content.toPath());

                                if (mime == null || mime.trim().length() == 0) {
                                    mime = MediaType.APPLICATION_OCTET_STREAM;
                                }
                            } else {
                                mime = mnm.getContentType();
                            }

                            boolean deleteOnFinish = false;

                            MultipartUtils.addFileToMultipartForm(multipart, formParamName, content, fileName, mime,
                                    deleteOnFinish);
                        }

                    } catch (Exception e) {
                        throw new InternalServerErrorException("Error processing File field de la Part amb nom  "
                                + formParamName + ": " + e.getMessage(), e);
                    }
                break;
                // byte[]
                case 2:
                    try {

                        // A partir del campo reflectionType, obtener el valor File de este campo para la instancia t.

                        byte[] content = MultipartUtils.getByteArrayFromInstanceAndField((Object) entity,
                                reflectionField);

                        if (content != null) {

                            // Miram si té informació de nom i de mime
                            Field formPartInfoField = partInfoFieldsByFieldName
                                    .get(reflectionField.getName() + "PartInfo");

                            MultipartNameAndMime mnm = null;

                            if (formPartInfoField != null) {
                                mnm = MultipartUtils.getMultipartNameAndMimeFromInstanceAndField((Object) entity,
                                        formPartInfoField);
                            }

                            String fileName;
                            if (mnm == null || mnm.getFileName() == null || mnm.getFileName().trim().length() == 0) {
                                fileName = formParamName + "_file.bin";
                            } else {
                                fileName = mnm.getFileName();
                            }

                            String mime;

                            if (mnm == null || mnm.getContentType() == null
                                    || mnm.getContentType().trim().length() == 0) {

                                mime = MediaType.APPLICATION_OCTET_STREAM;

                            } else {
                                mime = mnm.getContentType();
                            }
                            MultipartUtils.addByteArrayToMultipartForm(multipart, formParamName, content, fileName,
                                    mime);
                        }

                    } catch (Exception e) {
                        throw new InternalServerErrorException("Error processing byte[] field de la Part amb nom  "
                                + formParamName + ": " + e.getMessage(), e);
                    }
                break;

                case 3: // Part info (nom i mime)
                case 4: // JSON Object
                    try {

                        Object jsonObject = MultipartUtils.getJSonObjectFromInstanceAndField((Object) entity,
                                reflectionField);

                        MultipartUtils.addJsonObjectToMultipartForm(multipart, formParamName, jsonObject);

                    } catch (Exception e) {
                        String msg = "Error processing JSON Object de la Part amb nom  " + formParamName + ": "
                                + e.getMessage();
                        log.error(msg, e);
                        throw new InternalServerErrorException(msg, e);
                    }
                break;
                default:
                    throw new InternalServerErrorException("Tipo no soportado en FieldInfo: " + fieldInfo.getType());
            }

        }

        // --------------------------------------------------------------

        MessageBodyWriter<MultipartFormDataOutput> delegate = providers.getMessageBodyWriter(
                MultipartFormDataOutput.class, MultipartFormDataOutput.class, annotations,
                MediaType.MULTIPART_FORM_DATA_TYPE);

        if (delegate == null) {
            throw new WebApplicationException("No s'ha trobat un delegate per MultipartFormDataOutput");
        }

        delegate.writeTo(multipart, MultipartFormDataOutput.class, MultipartFormDataOutput.class, annotations,
                MediaType.MULTIPART_FORM_DATA_TYPE, httpHeaders, entityStream);

    }
}
