package es.caib.utilitatsfirma.api.interna.secure;

import java.io.File;

/**
 * 
 * @author anadal (u80067)
 * 24 feb 2026 8:19:58
 */
public class FormFileInfo {

    protected final File file;
    
    protected final String fileName;
    
    protected final String contentType;
    
    
    public FormFileInfo(File file, String fileName, String contentType) {
        this.file = file;
        this.fileName = fileName;
        this.contentType = contentType;
    }


    public File getFile() {
        return file;
    }


    public String getFileName() {
        return fileName;
    }


    public String getContentType() {
        return contentType;
    }
    

}
