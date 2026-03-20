package es.caib.utilitatsfirma.api.interna.multipartutils;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Informació del nom i mime d'una part multipart que representa un fitxer o byte[]
 * 
 * @author anadal (u80067)
 * 4 mar 2026 11:58:05
 */
@Schema(description = "Informació del nom i mime d'una part multipart que representa un fitxer o byte[]")
public class MultipartNameAndMime {

    protected final String fileName;

    protected final String contentType;

    public MultipartNameAndMime(String fileName, String contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

}
