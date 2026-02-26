package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 25 feb 2026 11:59:26
 */
@Schema(description = "Resposta de l'actualització de signatura amb informació ")
public class UpgradeResponseMultipart {

    @Schema(description = "Fitxer amb la signatura actualitzada", type = "string", format = "binary")
    private byte[] upgradedFile;

    @Schema(description = "Informació de la signatura actualitzada")
    private UpgradedFileInfoV2 upgradedFileInfoV2;

    public byte[] getUpgradedFile() {
        return upgradedFile;
    }

    public void setUpgradedFile(byte[] upgradedFile) {
        this.upgradedFile = upgradedFile;
    }

    public UpgradedFileInfoV2 getUpgradedFileInfoV2() {
        return upgradedFileInfoV2;
    }

    public void setUpgradedFileInfoV2(UpgradedFileInfoV2 upgradedFileInfoV2) {
        this.upgradedFileInfoV2 = upgradedFileInfoV2;
    }


}