package es.caib.utilitatsfirma.logic.passarela.api;

import java.io.File;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PassarelaUpgradeResponse {

    protected final File upgradedSignature;

    protected final PassarelaValidacioCompletaResponse validacioResponse;

    public PassarelaUpgradeResponse(File upgradedSignature, PassarelaValidacioCompletaResponse validacioResponse) {
        super();
        this.upgradedSignature = upgradedSignature;
        this.validacioResponse = validacioResponse;
    }

    public File getUpgradedSignature() {
        return upgradedSignature;
    }

    public PassarelaValidacioCompletaResponse getValidacioResponse() {
        return validacioResponse;
    }

}
