package es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author anadal
 *
 */
public class SignatureRequestedInformation {

    protected Boolean returnSignatureTypeFormatProfile = null;

    protected Boolean validateCertificateRevocation = null;

    protected Boolean returnCertificateInfo = null;

    protected Boolean returnValidationChecks = null;

    protected Boolean returnCertificates = null;

    protected Boolean returnTimeStampInfo = null;

    // Default constructor
    public SignatureRequestedInformation() {
        super();
    }

    public Boolean getReturnSignatureTypeFormatProfile() {
        return returnSignatureTypeFormatProfile;
    }

    public void setReturnSignatureTypeFormatProfile(Boolean returnSignatureTypeFormatProfile) {
        this.returnSignatureTypeFormatProfile = returnSignatureTypeFormatProfile;
    }

    public Boolean getValidateCertificateRevocation() {
        return validateCertificateRevocation;
    }

    public void setValidateCertificateRevocation(Boolean validateCertificateRevocation) {
        this.validateCertificateRevocation = validateCertificateRevocation;
    }

    public Boolean getReturnCertificateInfo() {
        return returnCertificateInfo;
    }

    public void setReturnCertificateInfo(Boolean returnCertificateInfo) {
        this.returnCertificateInfo = returnCertificateInfo;
    }

    public Boolean getReturnValidationChecks() {
        return returnValidationChecks;
    }

    public void setReturnValidationChecks(Boolean returnValidationChecks) {
        this.returnValidationChecks = returnValidationChecks;
    }

    public Boolean getReturnCertificates() {
        return returnCertificates;
    }

    public void setReturnCertificates(Boolean returnCertificates) {
        this.returnCertificates = returnCertificates;
    }

    public Boolean getReturnTimeStampInfo() {
        return returnTimeStampInfo;
    }

    public void setReturnTimeStampInfo(Boolean returnTimeStampInfo) {
        this.returnTimeStampInfo = returnTimeStampInfo;
    }

    public static SignatureRequestedInformation valueOf(String json) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, SignatureRequestedInformation.class);
    }

}
