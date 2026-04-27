package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

/**
 * 
 * @author anadal (u80067)
 * 24 abr 2026 11:38:52
 */
public class ValidateSignatureResponse
        extends es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.ValidateSignatureResponse {

    protected NonCryptographicInformation nonCryptographicInformation;

    public NonCryptographicInformation getNonCryptographicInformation() {
        return nonCryptographicInformation;
    }

    public void setNonCryptographicInformation(NonCryptographicInformation nonCryptographicInformation) {
        this.nonCryptographicInformation = nonCryptographicInformation;
    }

}
