package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.util.Date;
import java.util.List;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 24 abr 2026 11:40:39
 */
@Schema(description = "Informació no criptogràfica associada a una firma.")
public class NonCryptographicInformation {

    protected String name;
    protected String surname1;
    protected String surname2;
    protected String administrationID;

    protected String nonCryptographicSystemName;
    protected String nonCryptographicSystemCode;
    protected String nonCryptographicSignatureIdentifier;

    protected Date dateOfSignature;

    protected String urlToWebInfo;

    protected String urlToDownloadFile;

    protected List<KeyValue> additionalInformation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getAdministrationID() {
        return administrationID;
    }

    public void setAdministrationID(String administrationID) {
        this.administrationID = administrationID;
    }

    public String getNonCryptographicSystemName() {
        return nonCryptographicSystemName;
    }

    public void setNonCryptographicSystemName(String nonCryptographicSystemName) {
        this.nonCryptographicSystemName = nonCryptographicSystemName;
    }

    public String getNonCryptographicSystemCode() {
        return nonCryptographicSystemCode;
    }

    public void setNonCryptographicSystemCode(String nonCryptographicSystemCode) {
        this.nonCryptographicSystemCode = nonCryptographicSystemCode;
    }

    public String getNonCryptographicSignatureIdentifier() {
        return nonCryptographicSignatureIdentifier;
    }

    public void setNonCryptographicSignatureIdentifier(String nonCryptographicSignatureIdentifier) {
        this.nonCryptographicSignatureIdentifier = nonCryptographicSignatureIdentifier;
    }

    public String getUrlToWebInfo() {
        return urlToWebInfo;
    }

    public void setUrlToWebInfo(String urlToWebInfo) {
        this.urlToWebInfo = urlToWebInfo;
    }

    public String getUrlToDownloadFile() {
        return urlToDownloadFile;
    }

    public void setUrlToDownloadFile(String urlToDownloadFile) {
        this.urlToDownloadFile = urlToDownloadFile;
    }

    public List<KeyValue> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<KeyValue> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Date getDateOfSignature() {
        return dateOfSignature;
    }

    public void setDateOfSignature(Date dateOfSignature) {
        this.dateOfSignature = dateOfSignature;
    }

}
