package es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="SignedFileInfo", description = "Informació del fitxer signat amb informació de Custòdia.")
public class SignedFileInfo extends SignedFileInfoV2 {

    /**
     * Informacio de Custòdia
     */
    @Schema(description = "Informació de Custòdia", required = false)
    protected CustodyInfo custodyInfo = null;

    public SignedFileInfo() {
        super();
    }

    public SignedFileInfo(int signOperation, String signType, String signAlgorithm, int signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, SignerInfo signerInfo, CustodyInfo custodyInfo, ValidationInfo validationInfo) {
        super();
        this.signOperation = signOperation;
        this.signType = signType;
        this.signAlgorithm = signAlgorithm;
        this.signMode = signMode;
        this.signaturesTableLocation = signaturesTableLocation;
        this.timeStampIncluded = timeStampIncluded;
        this.policyIncluded = policyIncluded;
        this.eniTipoFirma = eniTipoFirma;
        this.eniPerfilFirma = eniPerfilFirma;
        this.signers = new ArrayList<SignerInfo>();
        this.signers.add(signerInfo);
        this.custodyInfo = custodyInfo;
        this.validationInfo = validationInfo;
    }

    public SignedFileInfo(int signOperation, String signType, String signAlgorithm, int signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, List<SignerInfo> signers, CustodyInfo custodyInfo, ValidationInfo validationInfo) {
        super();
        this.signOperation = signOperation;
        this.signType = signType;
        this.signAlgorithm = signAlgorithm;
        this.signMode = signMode;
        this.signaturesTableLocation = signaturesTableLocation;
        this.timeStampIncluded = timeStampIncluded;
        this.policyIncluded = policyIncluded;
        this.eniTipoFirma = eniTipoFirma;
        this.eniPerfilFirma = eniPerfilFirma;
        this.signers = signers;
        this.custodyInfo = custodyInfo;
        this.validationInfo = validationInfo;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public int getSignOperation() {
        return signOperation;
    }

    public void setSignOperation(int signOperation) {
        this.signOperation = signOperation;
    }

    public String getSignAlgorithm() {
        return signAlgorithm;
    }

    public void setSignAlgorithm(String signAlgorithm) {
        this.signAlgorithm = signAlgorithm;
    }

    public int getSignMode() {
        return signMode;
    }

    public void setSignMode(int signMode) {
        this.signMode = signMode;
    }

    public int getSignaturesTableLocation() {
        return signaturesTableLocation;
    }

    public void setSignaturesTableLocation(int signaturesTableLocation) {
        this.signaturesTableLocation = signaturesTableLocation;
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

    public String getEniTipoFirma() {
        return eniTipoFirma;
    }

    public void setEniTipoFirma(String eniTipoFirma) {
        this.eniTipoFirma = eniTipoFirma;
    }

    public String getEniPerfilFirma() {
        return eniPerfilFirma;
    }

    public void setEniPerfilFirma(String eniPerfilFirma) {
        this.eniPerfilFirma = eniPerfilFirma;
    }

    public List<SignerInfo> getSigners() {
        return signers;
    }

    public void setSigners(List<SignerInfo> signers) {
        this.signers = signers;
    }

    public CustodyInfo getCustodyInfo() {
        return custodyInfo;
    }

    public void setCustodyInfo(CustodyInfo custodyInfo) {
        this.custodyInfo = custodyInfo;
    }

    public ValidationInfo getValidationInfo() {
        return validationInfo;
    }

    public void setValidationInfo(ValidationInfo validationInfo) {
        this.validationInfo = validationInfo;
    }

}
