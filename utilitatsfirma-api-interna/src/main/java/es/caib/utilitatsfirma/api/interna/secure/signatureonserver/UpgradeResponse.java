package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.Document;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Resultat d'una actualització de firma
 * 
 * @author anadal
 *
 */
public class UpgradeResponse {
    
    @Schema(
            description = "Firma actualitzada",
            example = "",
            required = true)
	protected Document upgradedFile;
    
    @Schema(
            description = "Informació de la firma actualitzada",
            example = "",
            required = false)
	protected UpgradedFileInfo upgradedFileInfo;

	/**
	 * 
	 */
	public UpgradeResponse() {
		super();
	}

	public UpgradeResponse(Document upgradedFile, UpgradedFileInfo upgradedFileInfo) {
		super();
		this.upgradedFile = upgradedFile;
		this.upgradedFileInfo = upgradedFileInfo;
	}

	public Document getUpgradedFile() {
		return upgradedFile;
	}

	public void setUpgradedFile(Document upgradedFile) {
		this.upgradedFile = upgradedFile;
	}

	public UpgradedFileInfo getUpgradedFileInfo() {
		return upgradedFileInfo;
	}

	public void setUpgradedFileInfo(UpgradedFileInfo upgradedFileInfo) {
		this.upgradedFileInfo = upgradedFileInfo;
	}

}
