package es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.api;

import java.io.File;

import es.caib.utilitatsfirma.api.interna.client.utilitatsfirma.v2.model.FileInfoSignature;

/**
 * Conté la informació d'un fitxer a signar i el fitxer en sí mateix.
 * @author anadal (u80067)
 * 19 feb 2026 15:24:40
 */
public class FileToSign {

    private FileInfoSignature fileInfoSignatureV2;

    private File fileToSign;

    public FileInfoSignature getFileInfoSignatureV2() {
        return fileInfoSignatureV2;
    }

    public void setFileInfoSignatureV2(FileInfoSignature fileInfoSignatureV2) {
        this.fileInfoSignatureV2 = fileInfoSignatureV2;
    }

    public File getFileToSign() {
        return fileToSign;
    }

    public void setFileToSign(File fileToSign) {
        this.fileToSign = fileToSign;
    }

}
