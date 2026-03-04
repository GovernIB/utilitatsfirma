package es.caib.utilitatsfirma.api.interna.multipartutils;

import java.io.File;

/**
 * 
 * @author anadal (u80067)
 * 4 mar 2026 12:07:03
 */
public class MultipartFileInfo {


    protected final File file;

    protected final MultipartNameAndMime nameAndMime;

    public MultipartFileInfo(File file, String fileName, String contentType) {
        this.file = file;
        this.nameAndMime = new MultipartNameAndMime(fileName, contentType);
    }
    
    

    public MultipartFileInfo(File file, MultipartNameAndMime nameAndMime) {
        super();
        this.file = file;
        this.nameAndMime = nameAndMime;
    }



    public File getFile() {
        return file;
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
