package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.util.List;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.KeyValue;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ValidationInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 25 feb 2026 13:59:34
 */
@Schema(description = "Informació de la signatura actualitzada amb informació amb informació del nom del fitxer i mimeType")
public class UpgradedFileInfo extends es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.UpgradedFileInfo {

    private String fileName;
    private String mimeType;

    public UpgradedFileInfo() {
        super();
    }

    public UpgradedFileInfo(String signType, String signAlgorithm, Integer signMode, String eniTipoFirma,
            String eniPerfilFirma, ValidationInfo validationInfo, List<KeyValue> additionInformation, String fileName,
            String mimeType) {
        super(signType, signAlgorithm, signMode, eniTipoFirma, eniPerfilFirma, validationInfo, additionInformation);

        this.fileName = fileName;
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

}
