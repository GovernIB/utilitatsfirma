package es.caib.utilitatsfirma.api.interna.multipartutils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilidades para el manejo de métodos de formulario.
 * @author anadal (u80067)
 * 24 feb 2026 8:15:20
 */
public class MultipartUtils {

    protected static final Logger log = Logger.getLogger(MultipartUtils.class);

    public static String getFormParamNameFromContentDisposition(String disposition) {
        if (disposition != null) {
            String[] parts = disposition.split(";");
            for (String part : parts) {
                part = part.trim();
                if (part.startsWith("name=")) {
                    return part.substring(5).replaceAll("\"", "");
                }
            }
        }
        throw new IllegalArgumentException("No s'ha pogut obtenir el name del Content-Disposition: " + disposition);
    }

    // ----------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------
    // ------------------   METODES UTILITATS READER ------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------

    public static String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if (filename.trim().startsWith("filename")) {
                return filename.substring(filename.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "unknown";
    }

    public static MultipartFileInfo getMultipartFileInfo(String info, String partName, InputPart filePart)
            throws IOException {
        InputStream fileToSignInputStream = filePart.getBody(InputStream.class, null);

        String fileName = getFileName(filePart.getHeaders());

        File file = File.createTempFile(info + "_" + partName, "_" + fileName);
        Files.copy(fileToSignInputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        String contentType = filePart.getMediaType().toString();
        //System.out.println("\n XYZ ZZZ eNTRA A signDocuments => fileToSignName: " + fileToSignName + "\n");

        return new MultipartFileInfo(file, fileName, contentType);
    }

    public static MultipartByteArrayInfo getMultipartByteArrayInfo(String info, String partName, InputPart part)
            throws IOException {

        byte[] content = part.getBody(byte[].class, null);

        String fileName = getFileName(part.getHeaders());

        String contentType = part.getMediaType().toString();
        //System.out.println("\n XYZ ZZZ eNTRA A signDocuments => fileToSignName: " + fileToSignName + "\n");

        return new MultipartByteArrayInfo(content, fileName, contentType);
    }

    /**
     * Obtiene un objeto de un multipart/form-data a través de un método de formulario, a partir de una parte que contiene un JSON.
     * @param <T>
     * @param input
     * @param classe
     * @param partName
     * @return
     * @throws I18NException
     */
    public static <T> T getObjectFromJsonMultipart(MultipartFormDataInput input, Class<T> classe, String partName)
            throws I18NException {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        List<InputPart> requestParts = uploadForm.get(partName);

        InputPart requestPart = requestParts.get(0);

        //log.info("\n XYZ ZZZ eNTRA A signDocuments => signatureRequestedInformation: " + requestPart + "\n");

        return getObjectFromJsonMultipart(requestPart, classe);

    }

    public static <T> T getObjectFromJsonMultipart(InputPart requestPart, Class<T> classe) throws I18NException {

        try {

            String json = requestPart.getBodyAsString();

            //log.info("\n XYZ ZZZ getJsonMultipartObj(class: " + classe + " | Partname: " + partName + " | JSON: ]" + json + "[\n");

            ObjectMapper mapper = new ObjectMapper();
            T obj = mapper.readValue(json, classe);

            //log.info("\n XYZ ZZZ getJsonMultipartObj(Resultat: ]" + obj + "[\n");

            return obj;

        } catch (Throwable th) {
            log.error("Error al obtener el objeto del multipart a partir del JSON: " + th.getMessage(), th);
            throw new I18NException(th, "genapp.comodi",
                    "Error al obtener el objeto del multipart a partir del JSON: " + th.getMessage());
        }
    }

    // ----------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------
    // ------------------   METODES UTILITATS WRITER -------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------

    public static void addFileToMultipartForm(MultipartFormDataOutput multipart, String partName, File content,
            String fileName, String mime, final boolean deleteOnFinish) throws IOException, FileNotFoundException {

        if (content != null && content.exists() && content.isFile()) {

            final InputStream fis;
            if (deleteOnFinish) {
                fis = new FileInputStreamWithDeletionAtFinish(content);
            } else {
                fis = new FileInputStream(content);
            }

            addInputStreamToMultipartForm(multipart, partName, fis, fileName, mime);
        }
    }

    public static void addByteArrayToMultipartForm(MultipartFormDataOutput multipart, String partName, byte[] content,
            String fileName, String mime) {
        if (content != null) {
            addInputStreamToMultipartForm(multipart, partName, new ByteArrayInputStream(content), fileName, mime);
        }
    }

    public static void addInputStreamToMultipartForm(MultipartFormDataOutput multipart, String partName, InputStream is,
            String fileName, String mime) {
        if (is != null) {
            multipart.addFormData(partName, is, MediaType.valueOf(mime)).getHeaders().putSingle("Content-Disposition",
                    "form-data; name=\"" + partName + "\"; filename=\"" + fileName + "\"");
        }
    }

    public static void addJsonObjectToMultipartForm(MultipartFormDataOutput multipart, String partName, Object obj)
            throws JsonProcessingException {
        if (obj == null) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        multipart.addFormData(partName, json.getBytes(), MediaType.APPLICATION_JSON_TYPE);
    }

    /**
     * InputStream que elimina el archivo asociado al cerrarse. Útil para enviar archivos temporales en respuestas
     *  multipart/form-data, asegurando que se eliminen después de ser enviados.
     * @author anadal (u80067)
     * 3 mar 2026 13:16:04
     */
    protected static class FileInputStreamWithDeletionAtFinish extends java.io.FileInputStream {

        protected Logger log = Logger.getLogger(this.getClass());

        private final File file;

        public FileInputStreamWithDeletionAtFinish(File file) throws IOException {
            super(file);
            this.file = file;
        }

        @Override
        public void close() throws IOException {
            try {
                super.close();
            } finally {
                deleteTempFile(file);
            }
        }
    }

    public static void deleteTempFile(File file) {
        if (file != null && file.exists() && file.isFile()) {
            if (!file.delete()) {
                log.warn("No s'ha pogut eliminar el fitxer temporal: " + file);
                file.deleteOnExit();
            }
        }
    }

    /**
     * Obtiene el valor del nombre del parámetro de un campo anotado con @FormParam.
     *  Si el campo no tiene la anotación, devuelve null.
     * @param field
     * @return
     */
    public static String getValueOfFormParamAnnotation(Field field) {
        FormParam annotation = field.getAnnotation(FormParam.class);
        if (annotation != null) {
            return annotation.value();
        }
        return null;
    }

    /**
     * Obtiene el valor del campo de tipo File de una instancia, a través de reflexión, a partir del campo field
      Si el campo no es de tipo File lanza IllegalArgumentException.
      El campo debe ser accesible (public o con setAccessible(true)).
     
     * @param instance
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public static File getFileFromInstanceAndField(Object instance, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(instance);
        if (value == null) {
            return null;
        }
        if (value instanceof File) {
            return (File) value;
        } else {
            throw new IllegalArgumentException("El camp " + field.getName() + " de la classe "
                    + instance.getClass().getName() + " no és del tipus File");
        }

    }

    public static byte[] getByteArrayFromInstanceAndField(Object instance, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(instance);
        if (value == null) {
            return null;
        }
        if (value instanceof byte[]) {
            return (byte[]) value;
        } else {
            throw new IllegalArgumentException("El camp " + field.getName() + " de la classe "
                    + instance.getClass().getName() + " no és del tipus byte[]");
        }

    }

    public static Object getJSonObjectFromInstanceAndField(Object instance, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(instance);
        return value;
    }

    public static MultipartNameAndMime getMultipartNameAndMimeFromInstanceAndField(Object instance, Field field)
            throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(instance);
        if (value instanceof MultipartNameAndMime) {
            return (MultipartNameAndMime) value;
        } else {
            throw new IllegalArgumentException("El camp " + field.getName() + " de la classe "
                    + instance.getClass().getName() + " no és del tipus MultipartNameAndMime");
        }

    }

}
