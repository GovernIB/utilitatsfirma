package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import javax.ws.rs.FormParam;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 25 feb 2026 11:59:26
 */
@Schema(description = "Resposta de l'actualització de signatura amb informació ")
public class UpgradeResponseMultipart {

    @Schema(description = "Fitxer amb la signatura actualitzada", type = "string", format = "binary")
    @FormParam("upgradedFile")
    private byte[] upgradedFile;

    @Schema(description = "Informació de la signatura actualitzada")
    @FormParam("upgradedFileInfo")
    private UpgradedFileInfo upgradedFileInfo;

    public byte[] getUpgradedFile() {
        return upgradedFile;
    }

    public void setUpgradedFile(byte[] upgradedFile) {
        this.upgradedFile = upgradedFile;
    }

    public UpgradedFileInfo getUpgradedFileInfo() {
        return upgradedFileInfo;
    }

    public void setUpgradedFileInfo(UpgradedFileInfo upgradedFileInfo) {
        this.upgradedFileInfo = upgradedFileInfo;
    }


}