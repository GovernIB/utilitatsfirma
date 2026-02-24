package es.caib.utilitatsfirma.api.interna.secure;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response.Status;

import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureRequestedInformation;

/**
 * Utilidades para el manejo de métodos de formulario.
 * @author anadal (u80067)
 * 24 feb 2026 8:15:20
 */
public class FormMethodUtils {

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
        InputStream fileToSignInputStream = filePart.getBody(InputStream.class, null);

        File file = File.createTempFile(className + "_" + methodName + "_", "_" + partName);
        Files.copy(fileToSignInputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        String fileName = getFileName(filePart.getHeaders());

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
    public static <T> T getJsonMultipartObj(MultipartFormDataInput input, Class<T> classe, String partName)
            throws Exception {

        SignatureRequestedInformation signatureRequestedInformation;
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        List<InputPart> requestParts = uploadForm.get(partName);

        InputPart requestPart = requestParts.get(0);

        //log.info("\n XYZ ZZZ eNTRA A signDocuments => signatureRequestedInformation: " + requestPart + "\n");

        String json = requestPart.getBodyAsString();

        //log.info("\n XYZ ZZZ eNTRA A signDocuments => signatureRequestedInformation as String: " + json + "\n");

        ObjectMapper mapper = new ObjectMapper();
        T obj = mapper.readValue(json, classe);

        return obj;

    }

}
