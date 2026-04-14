package es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1;

import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(description = "Informació de la marca de temps d'una firma")
public class TimeStampInfo {

    protected Date creationTime;

    protected String certificateIssuer;

    protected String certificateSubject;

    @Schema(type = "string", format = "byte")
    protected byte[] certificate;

    protected String algorithm;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCertificateIssuer() {
        return certificateIssuer;
    }

    public void setCertificateIssuer(String certificateIssuer) {
        this.certificateIssuer = certificateIssuer;
    }

    public String getCertificateSubject() {
        return certificateSubject;
    }

    public void setCertificateSubject(String certificateSubject) {
        this.certificateSubject = certificateSubject;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}
