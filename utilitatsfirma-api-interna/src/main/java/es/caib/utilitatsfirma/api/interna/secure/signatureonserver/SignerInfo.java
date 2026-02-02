package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Informació del signant",required = true)
public class SignerInfo {

    /**
     * - eEMGDE.Firma.RolFirma (eEMGDE17.2): Esquemas desarrollados a nivel local y
     * que pueden incluir valores como válida, autentica, refrenda, visa,
     * representa, testimonia, etc..
     */
    @Schema(
            description = "Esquemas desarrollados a nivel local y que pueden incluir valores como válida, autentica, refrenda, visa, representa, testimonia, etc..",
            required = false)
    protected String eniRolFirma;

    /**
     * eEMGDE.Firma.Firmante.NombreApellidos (eEMGDE17.5.1): Texto libre. Nombre o
     * razón social de los firmantes.
     */

    @Schema(description = "Nombre o razón social de los firmantes.", required = false)
    protected String eniSignerName;

    /**
     * eEMGDE.Firma.Firmante (eEMGDE17.5.2). Número Identificacion Firmantes (NIF)
     */
    @Schema(description = "NIF del firmant.", required = false)
    protected String eniSignerAdministrationId;

    /**
     * eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el
     * grado de confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano,
     * Firma electrónica avanzada, Claves concertadas, Firma electrónica avanzada
     * basada en certificados, CSV, ..
     */

    @Schema(
            description = "Indicador normalizado que refleja el grado de  confianza de la firma utilizado. "
                    + "Ejemplos: Nick, PIN ciudadano, Firma electrónica avanzada, Claves concertadas, "
                    + "Firma electrónica avanzada basada en certificados, CSV, ..",
            required = false)
    protected String eniSignLevel;

    //@Schema(description = "Data en que es va realitzar la firma", required = false)
    // protected Date signDate;
    @Schema(
            required = false,
            description = "Data en que es va realitzar la firma",
            type = "string",
            format = "date-time",
            pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected Timestamp signDate;

    @Schema(
            description = "Número de Sèrie del Certificat utilitzat en la firma",
            required = false)
    protected String serialNumberCert;

    @Schema(description = "Issuer del Certificat utilitzat en la firma", required = false)
    protected String issuerCert;

    @Schema(description = "Subject del Certificat utilitzat en la firma", required = false)
    protected String subjectCert;
    
    @Schema(description="Informació del Plugin Utilitzat per a la realització de la Firma")
    protected SignPlugin signPlugin;
    

    /**
     * eEMGDE.Firma.InformacionAdicional (eEMGDE17.5.5) Ofrecer cualquier otra
     * información que se considere útil acerca del firmante.
     */
    @Schema(
            description = "Ofrecer cualquier otra información que se considere útil acerca del firmante.",
            required = false)
    protected List<KeyValue> additionalInformation = null;

    public SignerInfo() {
        super();
    }

    public SignerInfo(String eniRolFirma, String eniSignerName, String eniSignerAdministrationId, String eniSignLevel,
            Timestamp signDate, String serialNumberCert, String issuerCert, String subjectCert, SignPlugin signPlugin,
            List<KeyValue> additionalInformation) {
        super();
        this.eniRolFirma = eniRolFirma;
        this.eniSignerName = eniSignerName;
        this.eniSignerAdministrationId = eniSignerAdministrationId;
        this.eniSignLevel = eniSignLevel;
        this.signDate = signDate;
        this.serialNumberCert = serialNumberCert;
        this.issuerCert = issuerCert;
        this.subjectCert = subjectCert;
        this.signPlugin = signPlugin;
        this.additionalInformation = additionalInformation;
    }

    public String getEniRolFirma() {
        return eniRolFirma;
    }

    public void setEniRolFirma(String eniRolFirma) {
        this.eniRolFirma = eniRolFirma;
    }

    public String getEniSignerName() {
        return eniSignerName;
    }

    public void setEniSignerName(String eniSignerName) {
        this.eniSignerName = eniSignerName;
    }

    public String getEniSignerAdministrationId() {
        return eniSignerAdministrationId;
    }

    public void setEniSignerAdministrationId(String eniSignerAdministrationId) {
        this.eniSignerAdministrationId = eniSignerAdministrationId;
    }

    public String getEniSignLevel() {
        return eniSignLevel;
    }

    public void setEniSignLevel(String eniSignLevel) {
        this.eniSignLevel = eniSignLevel;
    }

    public Timestamp getSignDate() {
        return signDate;
    }

    public void setSignDate(Timestamp signDate) {
        this.signDate = signDate;
    }

    public String getSerialNumberCert() {
        return serialNumberCert;
    }

    public void setSerialNumberCert(String serialNumberCert) {
        this.serialNumberCert = serialNumberCert;
    }

    public String getIssuerCert() {
        return issuerCert;
    }

    public void setIssuerCert(String issuerCert) {
        this.issuerCert = issuerCert;
    }

    public String getSubjectCert() {
        return subjectCert;
    }

    public void setSubjectCert(String subjectCert) {
        this.subjectCert = subjectCert;
    }

    public List<KeyValue> getAdditionalInformation() {
        return additionalInformation;
    }
    
    public SignPlugin getSignPlugin() {
        return signPlugin;
    }

    public void setSignPlugin(SignPlugin signPlugin) {
        this.signPlugin = signPlugin;
    }

    public void setAdditionalInformation(List<KeyValue> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }



    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("        + eniRolFirma:\t" + getEniRolFirma());
        str.append("\n").append("        + eniSignerName:\t" + getEniSignerName());
        str.append("\n").append("        + eniSignerAdministrationId:\t" + getEniSignerAdministrationId());
        str.append("\n").append("        + eniSignLevel:\t" + getEniSignLevel());
        str.append("\n").append("        + Sign Date:\t" + new SimpleDateFormat().format(getSignDate()));
        str.append("\n").append("        + Subject Cert:\t" + getSubjectCert());
        str.append("\n").append("        + Issuer Cert:\t" + getIssuerCert());

        List<KeyValue> additionalInformation = getAdditionalInformation();

        if (additionalInformation != null && additionalInformation.size() != 0) {
            str.append("\n").append("        + INFORMACIO ADDICIONAL:");
            for (KeyValue KeyValue : additionalInformation) {
                str.append("\n").append("          >> KEY[" + KeyValue.getKey() + "]: " + KeyValue.getValue());
            }
        }

        return str.toString();

    }

}
