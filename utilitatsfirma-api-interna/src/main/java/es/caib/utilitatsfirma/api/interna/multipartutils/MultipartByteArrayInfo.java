package es.caib.utilitatsfirma.api.interna.multipartutils;

/**
 * 
 * @author anadal (u80067)
 * 4 mar 2026 12:00:40
 */
public class MultipartByteArrayInfo {

    final byte[] content;
    
    final MultipartNameAndMime nameAndMime;
    
    
    public MultipartByteArrayInfo(byte[] content, String fileName, String contentType) {
        this.content = content;
        this.nameAndMime = new MultipartNameAndMime(fileName, contentType);
    }
    
    public byte[] getContent() {
        return content;
    }
    
    public String getFileName() {
        return this.nameAndMime.getFileName();
    }
    
    public String getContentType() {
        return this.nameAndMime.getContentType();
    }
    
    public MultipartNameAndMime getNameAndMime() {
        return nameAndMime;
    }
}
