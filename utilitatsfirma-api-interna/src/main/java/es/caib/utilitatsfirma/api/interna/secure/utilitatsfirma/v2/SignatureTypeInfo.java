package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 5 ago 2026 9:27:34
 */
public class SignatureTypeInfo {

    @Schema(
            description = "Codi del perfil de firma a la que pertany aquest tipus de firma.",
            example = "PERFIL_XADES",
            required = true)
    protected String profileCode;

    /**
     * eEMGDE.Firma.Firmante.EnCalidadDe(eEMGDE17.5.3): Firmante; Cofirmante;
     * Contrafirmante
     */
    @Schema(description = "Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2).", required = true)
    protected SignOperationEnum signOperation;

    @Schema(description = "Tipus de Firma. Valors possibles veure SignTypeEnum.", required = true)
    protected SignTypeEnum signType;

    @Schema(description = "Algorisme de Firma.", required = true)
    protected SignAlgorithmEnum signAlgorithm;

    @Schema(
            description = "Mode de firma. Valors veure SignModeConstants. Exemple SignModeEnum.SIGN_MODE_ATTACHED_ENVELOPED.",
            required = true)
    protected SignModeEnum signMode;

    @Schema(description = "Indica si s'ha afegit un segell de Temps durant la firma", required = true)
    protected boolean timeStampIncluded;

    // BES o EPES
    @Schema(description = "Indica si inclou política de firma (true, EPES) o no (false)", required = true)
    protected boolean policyIncluded;

    public String getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public SignOperationEnum getSignOperation() {
        return signOperation;
    }

    public void setSignOperation(SignOperationEnum signOperation) {
        this.signOperation = signOperation;
    }

    public SignTypeEnum getSignType() {
        return signType;
    }

    public void setSignType(SignTypeEnum signType) {
        this.signType = signType;
    }

    public SignAlgorithmEnum getSignAlgorithm() {
        return signAlgorithm;
    }

    public void setSignAlgorithm(SignAlgorithmEnum signAlgorithm) {
        this.signAlgorithm = signAlgorithm;
    }

    public SignModeEnum getSignMode() {
        return signMode;
    }

    public void setSignMode(SignModeEnum signMode) {
        this.signMode = signMode;
    }

    public boolean isTimeStampIncluded() {
        return timeStampIncluded;
    }

    public void setTimeStampIncluded(boolean timeStampIncluded) {
        this.timeStampIncluded = timeStampIncluded;
    }

    public boolean isPolicyIncluded() {
        return policyIncluded;
    }

    public void setPolicyIncluded(boolean policyIncluded) {
        this.policyIncluded = policyIncluded;
    }

}
