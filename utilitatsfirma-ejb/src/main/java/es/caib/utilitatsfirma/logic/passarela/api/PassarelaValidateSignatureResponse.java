package es.caib.utilitatsfirma.logic.passarela.api;

import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

/**
 * 
 * @author anadal (u80067)
 * 24 abr 2026 12:00:14
 */
public class PassarelaValidateSignatureResponse {

    protected ValidateSignatureResponse validateSignatureResponse;

    protected PassarelaNonCryptographicInformation nonCryptographicInformation;

    public PassarelaValidateSignatureResponse(ValidateSignatureResponse validateSignatureResponse,
            PassarelaNonCryptographicInformation nonCryptographicInformation) {
        super();
        this.validateSignatureResponse = validateSignatureResponse;
        this.nonCryptographicInformation = nonCryptographicInformation;
    }

    public PassarelaValidateSignatureResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ValidateSignatureResponse getValidateSignatureResponse() {
        return validateSignatureResponse;
    }

    public void setValidateSignatureResponse(ValidateSignatureResponse validateSignatureResponse) {
        this.validateSignatureResponse = validateSignatureResponse;
    }

    public PassarelaNonCryptographicInformation getNonCryptographicInformation() {
        return nonCryptographicInformation;
    }

    public void setNonCryptographicInformation(PassarelaNonCryptographicInformation nonCryptographicInformation) {
        this.nonCryptographicInformation = nonCryptographicInformation;
    }

}
