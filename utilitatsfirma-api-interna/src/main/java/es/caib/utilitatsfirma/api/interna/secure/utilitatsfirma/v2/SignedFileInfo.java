package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;


import java.util.ArrayList;
import java.util.List;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.CommonSignedFileInfo;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignerInfo;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ValidationInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="SignedFileInfo",description = "Informació del fitxer signat")
public class SignedFileInfo extends CommonSignedFileInfo {

    
    public SignedFileInfo() {
        super();
    }

    public SignedFileInfo(int signOperation, String signType, String signAlgorithm, int signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, SignerInfo signerInfo, ValidationInfo validationInfo) {
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
        this.validationInfo = validationInfo;
    }

    public SignedFileInfo(int signOperation, String signType, String signAlgorithm, int signMode,
            int signaturesTableLocation, boolean timeStampIncluded, boolean policyIncluded, String eniTipoFirma,
            String eniPerfilFirma, List<SignerInfo> signers,  ValidationInfo validationInfo) {
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
        this.validationInfo = validationInfo;
    }
    
}
