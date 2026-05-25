package es.caib.utilitatsfirma.logic.passarela.api;

import java.util.Date;
import java.util.Map;

/**
 * 
 * @author anadal (u80067)
 * 24 abr 2026 11:40:39
 */

public class PassarelaNonCryptographicInformation {

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

    protected Map<String, String> additionalInformation;

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

    public Date getDateOfSignature() {
        return dateOfSignature;
    }

    public void setDateOfSignature(Date dateOfSignature) {
        this.dateOfSignature = dateOfSignature;
    }

    public Map<String, String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
