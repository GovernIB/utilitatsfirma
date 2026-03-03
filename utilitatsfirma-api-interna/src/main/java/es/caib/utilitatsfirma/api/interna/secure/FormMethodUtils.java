package es.caib.utilitatsfirma.api.interna.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;

import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilidades para el manejo de métodos de formulario.
 * @author anadal (u80067)
 * 24 feb 2026 8:15:20
 */
public class FormMethodUtils {

    protected static final Logger log = Logger.getLogger(FormMethodUtils.class);

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

    /**
     * Obtiene la información de un archivo enviado en un multipart/form-data a través de un método de formulario.
     * @param input
     * @param className
     * @param methodName
     * @param partName
     * @return
     * @throws Exception
     */
    public static FormFileInfo getFormFileInfo(MultipartFormDataInput input, String className, String methodName,
            String partName, boolean optional) throws Exception {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        List<InputPart> fileParts = uploadForm.get(partName);

        if (fileParts == null || fileParts.size() == 0) {
            if (optional) {
                return null;
            } else {
                // XYZ ZZZ TRA
                String errMsg = className + "::" + methodName + ": No s'ha trobat cap part amb el name " + partName
                        + " en el multipart/form-data.";
                throw new RestException(Status.BAD_REQUEST, errMsg, partName);
            }
        }

        InputPart filePart = fileParts.get(0);

        return getFormFileInfo(className + "_" + methodName, partName, filePart);

    }

    public static FormFileInfo getFormFileInfo(String info, String partName, InputPart filePart) throws IOException {
        InputStream fileToSignInputStream = filePart.getBody(InputStream.class, null);

        String fileName = getFileName(filePart.getHeaders());

        File file = File.createTempFile(info + "_" + partName, "_" + fileName);
        Files.copy(fileToSignInputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        String contentType = filePart.getMediaType().toString();
        //System.out.println("\n XYZ ZZZ eNTRA A signDocuments => fileToSignName: " + fileToSignName + "\n");

        return new FormFileInfo(file, fileName, contentType);
    }

    /**
     * Obtiene un objeto de un multipart/form-data a través de un método de formulario, a partir de una parte que contiene un JSON.
     * @param <T>
     * @param input
     * @param classe
     * @param partName
     * @return
     * @throws Exception
     */
    public static <T> T getObjectFromJsonMultipart(MultipartFormDataInput input, Class<T> classe, String partName)
            throws Exception {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        List<InputPart> requestParts = uploadForm.get(partName);

        InputPart requestPart = requestParts.get(0);

        //log.info("\n XYZ ZZZ eNTRA A signDocuments => signatureRequestedInformation: " + requestPart + "\n");

        return getObjectFromJsonMultipart(requestPart, classe);

    }

    public static <T> T getObjectFromJsonMultipart(InputPart requestPart, Class<T> classe)
            throws IOException, JsonParseException, JsonMappingException {
        String json = requestPart.getBodyAsString();

        //log.info("\n XYZ ZZZ getJsonMultipartObj(class: " + classe + " | Partname: " + partName + " | JSON: ]" + json + "[\n");

        ObjectMapper mapper = new ObjectMapper();
        T obj = mapper.readValue(json, classe);

        //log.info("\n XYZ ZZZ getJsonMultipartObj(Resultat: ]" + obj + "[\n");

        return obj;
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

            addFileToMultipartForm(multipart, partName, fis, fileName, mime);
        }
    }

    public static void addFileToMultipartForm(MultipartFormDataOutput multipart, String partName, InputStream is,
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

}
