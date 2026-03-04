package es.caib.utilitatsfirma.api.interna.multipartutils;

/**
 * 
 * @author anadal (u80067)
 * 4 mar 2026 11:58:05
 */
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
    
    
