package es.caib.utilitatsfirma.api.interna.multipartutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.InternalServerErrorException;
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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 4 mar 2026 11:15:43
 */
@Provider
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class GenericMessageBodyReader implements MessageBodyReader<IMessageBodyReader>  {

    
    
    protected Logger log = Logger.getLogger(this.getClass());

    @Context
    private Providers providers;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        
        
        boolean result = IMessageBodyReader.class.isAssignableFrom(type);
        log.info("\n ------------------- GENERIC ISREADABLE -----------------------"
        + "\n * IMessageBodyReader.class.isAssignableFrom('" + type.getName() + "') =  " + result 
        + "\n * genericType=" + genericType 
        + "\n * mediaType=" + mediaType + "\n");
        
        for (Annotation ann : annotations) {
            log.info("  - Annotation: " + ann.annotationType().getName());
        }

        log.info("------------------- FINAL GENERIC ISREADABLE -----------------------\n");
 
        
        
        
        
        return result;
    }

    @Override
    public IMessageBodyReader readFrom(Class<IMessageBodyReader> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException {
        try {
        
        log.info("\n  - GENERIC: type=" + type.getName()
               + "\n  - GENERIC: genericType=" + genericType
               + "\n  - GENERIC: mediaType=" + mediaType + "\n");
        

        MessageBodyReader<MultipartInput> multipartReader = providers.getMessageBodyReader(MultipartInput.class, null,
                annotations, mediaType);
        if (multipartReader == null) {
            throw new InternalServerErrorException("No hi ha MessageBodyReader<MultipartInput>");
        }

        MultipartInput multipartInput = multipartReader.readFrom(MultipartInput.class, null, annotations, mediaType,
                httpHeaders, entityStream);
        
        
        // Instanciar un Objecte de tipus "type" (que es el que s'ha de retornar)
        IMessageBodyReader requestObj;
        try {
            requestObj = type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IOException("[" + e.getClass().getName()+ "] Error instanciant objecte de tipus " + type.getName() + ": " + e.getMessage(), e);
        } 
        
        Map<String, FieldInfo> fieldInfoByFormName = new HashMap<>();
        
        Map<String, Field> partInfoFieldsByFieldName = new HashMap<>();
        
        
        log.info("\n\n ------------------------ ANALITZANT CAMPS DE LA CLASSE " + type.getName() + " ------------------------\n\n");
        
        
        
        for (Field field : type.getDeclaredFields()) {
            Class<?> fieldType = field.getType();

            if (File.class.equals(fieldType) || byte[].class.equals(fieldType)) {
                String formParamName = readFormParamValue(field);
                
                fieldInfoByFormName.put(formParamName, new FieldInfo(formParamName, field, File.class.equals(fieldType) ? 1 : 2));
                
                
                
                
            } else if (MultipartNameAndMime.class.isAssignableFrom(fieldType)) {                
                Schema schema = field.getAnnotation(Schema.class);
                if (schema == null || !schema.hidden()) {
                    throw new InternalServerErrorException("@Schema(hidden=true) required on field " + field.getName());
                }
                
                String name = field.getName();
                
                if (!name.endsWith("PartInfo")) {
                    throw new InternalServerErrorException("Field name "+ name + " should end with 'FileInfo'. This class is reserved for attach additional information of a file field");
                }
                
                
                partInfoFieldsByFieldName.put(name, field);
            } else {
                String formParamName = readFormParamValue(field);
                fieldInfoByFormName.put(formParamName, new FieldInfo(formParamName, field, 4));
                
                log.info(" GENERIC CAMP: trobat camp filed=" + field + " amb formparam name " + formParamName);
              
            }
        }
        
        

        log.info("\n\n ------------------------ ANALITZANT PARTS DE LA PETICIO HTTP  ------------------------\n\n");
        
        

        //SignDocumentRequestMultipart request = new SignDocumentRequestMultipart();
        final String className = type.getName();

        for (InputPart part : multipartInput.getParts()) {
            String disposition = part.getHeaders().getFirst("Content-Disposition");
            if (disposition == null) {
                continue;
            }

            log.info("GENERIC: Processant part multipart amb Content-Disposition: " + disposition + "\n" );
            
            
            // Extraure el nom del form param de la part multipart
            String formParamName = MultipartUtils.getFormParamNameFromContentDisposition(disposition);
            
            log.info("GENERIC: formParamName= " + formParamName + "\n");
            
            FieldInfo fi = fieldInfoByFormName.get(formParamName);
            
            if (fi == null) {
                throw new InternalServerErrorException("No s'ha trobat cap camp de la classe " + type.getName() 
                   + " associat amb @FormParam '" + formParamName + "'.");
                
            }
            
            log.info("GENERIC: fi.getType()= " + fi.getType() + "\n");
            
            log.info("GENERIC: fi.getField()= " + fi.getField() + "\n");
            
            switch (fi.getType()) {
                case 1: // File
                {
                    MultipartFileInfo fileInfo =  MultipartUtils.getMultipartFileInfo(className, formParamName, part);
                    fi.getField().setAccessible(true);
                    try {
                        fi.getField().set(requestObj, fileInfo.getFile());
                    } catch (IllegalAccessException e) {
                        throw new InternalServerErrorException("Error setting File field " + fi.getField().getName() + ": " + e.getMessage(), e);
                    }
                    
                    // Si hi ha un camp FormFileInfo associat, també el setegem
                    Field formPartInfoField = partInfoFieldsByFieldName.get(fi.getField().getName() + "PartInfo");
                    if (formPartInfoField != null) {
                        formPartInfoField.setAccessible(true);
                        try {
                            formPartInfoField.set(requestObj, fileInfo.getNameAndMime());
                        } catch (Exception e) {
                            throw new InternalServerErrorException("Error setting FormFileInfo field " + formPartInfoField.getName() + ": " + e.getMessage(), e);
                        }
                    }
                }
                    break;
                case 2: // byte[]
                    try {

                        MultipartByteArrayInfo byteArrayInfo = MultipartUtils.getMultipartByteArrayInfo(className,formParamName, part);
                        
                        fi.getField().setAccessible(true);
                        fi.getField().set(requestObj, byteArrayInfo.getContent());
                        
                        
                        // Si hi ha un camp FormFileInfo associat, també el setegem
                        Field formPartInfoField = partInfoFieldsByFieldName.get(fi.getField().getName() + "PartInfo");
                        if (formPartInfoField != null) {
                            formPartInfoField.setAccessible(true);
                            try {
                                formPartInfoField.set(requestObj, byteArrayInfo.getNameAndMime());
                            } catch (IllegalAccessException e) {
                                throw new InternalServerErrorException("Error setting FormFileInfo field " + formPartInfoField.getName() + ": " + e.getMessage(), e);
                            }
                        }
                        
                        
                    } catch (Exception e) {
                        throw new InternalServerErrorException("Error processing byte[] field de la Part amb nom  " + formParamName + ": " + e.getMessage(), e);
                    }
                    break;
                case 4: // JSON Object
                    try {
                        
                        
                        
                        log.info("\n\nProcessing JSON Object field[" + formParamName + "] " + fi.getField());
                        
                        
                        log.info("\n\nProcessing JSON Object field " + fi.getField().getName() + " from multipart part with formParamName: " + formParamName + "\n\n");
                        
                        Object jsonObj = MultipartUtils.getObjectFromJsonMultipart(part, fi.getField().getType());
                        
                        log.info("\n\n jsonObj=" + jsonObj + "\n\n");
                        
                        fi.getField().setAccessible(true);
                        fi.getField().set(requestObj, jsonObj);
                    } catch (Exception e) {
                        String msg = "Error processing JSON Object de la Part amb nom  " + formParamName + ": " + e.getMessage();
                        log.error(msg, e);
                        throw new InternalServerErrorException(msg, e);
                    }
                    break;
                default:
                    throw new InternalServerErrorException("Tipo no soportado en FieldInfo: " + fi.getType());
            }
            

            /*
            if (disposition.contains("name=\"signDocumentRequest\"")) {

                request.setSignDocumentRequest(
                        FormMethodUtils.getObjectFromJsonMultipart(part, SignDocumentRequestV2.class));
            } else if (disposition.contains("name=\"fileToSign\"")) {

                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, "fileToSign", part);

                request.setFileToSign(fi.getFile());
                request.setFileToSignFileInfo(fi);

            } else if (disposition.contains("name=\"previousSignatureDetachedFile\"")) {

                FormFileInfo fi = FormMethodUtils.getFormFileInfo(className, "previousSignatureDetachedFile", part);

                request.setPreviousSignatureDetachedFile(fi.getFile());
                request.setPreviousSignatureDetachedFileInfo(fi);
            } else {
                throw new WebApplicationException("Part desconeguda al multipart: " + disposition);
            }
            */

        }
/*
        if (request.getSignDocumentRequest() == null) {
            throw new WebApplicationException(
                    "La part amb nom 'signDocumentRequest' val null però es un camp obligatori", 400);
        }

        if (request.getFileToSign() == null) {
            throw new WebApplicationException("La part amb nom 'fileToSign' val null però es un camp obligatori", 400);
        }
*/
        return requestObj;
        } catch (Throwable e) {
            log.error("Error en GenericMessageBodyReader: " + e.getMessage() + "[" + e.getClass() + "]", e);
            
            if (WebApplicationException.class.isAssignableFrom(e.getClass())) {
                throw e;
            }
            
            throw new InternalServerErrorException("Error No controlat en GenericMessageBodyReader: " + e.getMessage(), e);
        }
    }
    
    
    private static String readFormParamValue(Field field) {
        FormParam formParam = field.getAnnotation(FormParam.class);
        if (formParam == null || formParam.value().isEmpty()) {
            throw new InternalServerErrorException("@FormParam missing on field " + field.getName());
        }
        return formParam.value();
    }
    
    
    public class FieldInfo {
        
        protected String formParamName;
        
        protected Field field;
        
        protected int type; // 1 = File, 2 = byte[], 4 = JSON Object
        
        
        
        

        public FieldInfo(String formParamName, Field field, int type) {
            super();
            this.formParamName = formParamName;
            this.field = field;
            this.type = type;
        }

        public String getFormParamName() {
            return formParamName;
        }

        public void setFormParamName(String formParamName) {
            this.formParamName = formParamName;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
        
        
        
        
        
    }

}
