package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;

import javax.ws.rs.FormParam;

import es.caib.utilitatsfirma.api.interna.multipartutils.IMessageBodyWriter;
import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartNameAndMime;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.UpgradedFileInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 25 feb 2026 11:59:26
 */
@Schema(description = "Resposta de l'actualització de signatura amb informació ")
public class UpgradeResponseMultipart implements IMessageBodyWriter {

    @Schema(description = "Fitxer amb la signatura actualitzada", type = "string", format = "binary", required = false)
    @FormParam("upgradedFile")
    private File upgradedFile;

    @Schema(description = "Informació del fitxer aamb la signatura actualitzada (nom i mime)", required = false)
    @FormParam("upgradeFilePartInfo")
    private MultipartNameAndMime upgradeFilePartInfo;

    @Schema(description = "Informació de la signatura actualitzada")
    @FormParam("upgradedFileInfo")
    private UpgradedFileInfo upgradedFileInfo;

    public File getUpgradedFile() {
        return upgradedFile;
    }

    public void setUpgradedFile(File upgradedFile) {
        this.upgradedFile = upgradedFile;
    }

    public UpgradedFileInfo getUpgradedFileInfo() {
        return upgradedFileInfo;
    }

    public void setUpgradedFileInfo(UpgradedFileInfo upgradedFileInfo) {
        this.upgradedFileInfo = upgradedFileInfo;
    }

    public MultipartNameAndMime getUpgradeFilePartInfo() {
        return upgradeFilePartInfo;
    }

    public void setUpgradeFilePartInfo(MultipartNameAndMime upgradeFilePartInfo) {
        this.upgradeFilePartInfo = upgradeFilePartInfo;
    }

}