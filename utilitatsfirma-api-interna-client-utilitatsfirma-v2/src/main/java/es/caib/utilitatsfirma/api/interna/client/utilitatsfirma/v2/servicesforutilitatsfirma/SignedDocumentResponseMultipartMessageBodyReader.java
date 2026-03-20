// Java
package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.servicesforutilitatsfirma;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.MultipartNameAndMime;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignedDocumentInformation;
import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.SignedDocumentResponseMultipart;

/**
 * 
 * @author anadal (u80067)
 * 26 feb 2026 13:41:43
 */
@Provider
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class SignedDocumentResponseMultipartMessageBodyReader
        implements MessageBodyReader<SignedDocumentResponseMultipart> {

    @Context
    private Providers providers;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return SignedDocumentResponseMultipart.class.isAssignableFrom(type);
    }

    @Override
    public SignedDocumentResponseMultipart readFrom(Class<SignedDocumentResponseMultipart> type, Type genericType,
            Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException {

        // Obtain the existing MessageBodyReader<MultipartInput> registered by RESTEasy
        MessageBodyReader<MultipartInput> multipartReader = providers.getMessageBodyReader(MultipartInput.class, null,
                annotations, mediaType);

        if (multipartReader == null) {
            throw new IllegalStateException("No MessageBodyReader<MultipartInput> found. "
                    + "Ensure resteasy-multipart-provider is on the client classpath.");
        }

        MultipartInput multipartInput = multipartReader.readFrom(MultipartInput.class, null, annotations, mediaType,
                httpHeaders, entityStream);

        List<InputPart> parts = multipartInput.getParts();
        //System.out.println("UpgradeResponseMultipartReader.readFrom: parts count=" + parts.size());

        SignedDocumentInformation info = null;
        MultipartNameAndMime signedFilePartInfo = null;
        File signedFile = null;

        for (InputPart part : parts) {
            MultivaluedMap<String, String> headers = part.getHeaders();
            String contentDisposition = headers.getFirst("Content-Disposition");
            // System.out.println("UpgradeResponseMultipartReader.readFrom: part contentDisposition=" + contentDisposition);

            if (contentDisposition == null) {
                continue;
            }

            if (contentDisposition.contains("name=\"signedDocumentInformation\"")) {
                if (part != null) {
                    try {
                        info = part.getBody(SignedDocumentInformation.class, SignedDocumentInformation.class);
                    } catch (Exception e) {
                        throw new IOException("No s'ha pogut deserialitzar signedDocumentInformation: " + e.getMessage(), e);
                    }
                }
            } else if (contentDisposition.contains("name=\"signedFilePartInfo\"")) {
                
                if (part != null) {
                    try {
                        signedFilePartInfo = part.getBody(MultipartNameAndMime.class, MultipartNameAndMime.class);
                    } catch (Exception e) {
                        throw new IOException("No s'ha pogut deserialitzar signedFilePartInfo: " + e.getMessage(), e);
                    }
                }                
            } else if (contentDisposition.contains("name=\"signedFile\"")
                    || contentDisposition.contains("name=\"signedDocument\"")) {

                InputStream is = part.getBody(InputStream.class, null);

                File destFile = File.createTempFile("ApiClient_UtilitatsFirma_V2", "signDocument");

                destFile.deleteOnExit();

                Files.copy(is, destFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                signedFile = destFile;

            } else {
                throw new WebApplicationException("Part desconeguda al multipart: " + contentDisposition);

            }
        }

        if (info == null) {
            throw new WebApplicationException(
                    "La part signedDocumentInformation és obligatòria en la resposta de SignedDocumentResponseMultipart");
        }
        SignedDocumentResponseMultipart response = new SignedDocumentResponseMultipart();
        response.setSignedDocumentInformation(info);
        response.setSignedFile(signedFile);
        response.setSignedFilePartInfo(signedFilePartInfo);
        return response;

    }

    

}
