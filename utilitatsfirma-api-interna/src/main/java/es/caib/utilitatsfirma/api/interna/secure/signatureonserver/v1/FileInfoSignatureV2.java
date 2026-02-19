package es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 9:59:47
 */
@JsonPropertyOrder({ "signID", "name", "reason", "location", "signNumber", "languageSign", "expedientCodi",
        "expedientNom", "expedientUrl", "procedimentCodi", "procedimentNom", "documentType", "additionalInformation",
        "useTimeStamp" })
@Schema(description = "Informació especifica per a realitzar la firma sense incloure Fitxers")
public class FileInfoSignatureV2 {

    @Schema(description = "Identificador de la Firma", example = "1", required = true)
    protected String signID;

    @Schema(
            description = "Nom descriptiu de la firma. Pot ser el nom del fitxer o un nom associat a la tasca per a la que es requereix la firma.",
            example = "test.pdf",
            required = true)
    protected String name;

    @Schema(description = "Raó de la realització de la firma.", example = "Exemple de firma", required = true)
    protected String reason;

    @Schema(description = "Lloc on es realitza la firma.", example = "Palma", required = true)
    protected String location;

    @Schema(description = "Posició de la firma dins el flux de firma.", example = "1", required = true)
    protected int signNumber;

    @Schema(description = "Idioma del document.", example = "ca", required = true)
    protected String languageSign;

    @Schema(description = "Codi de l'expedient.", example = "ca", required = false)
    protected String expedientCodi;

    @Schema(description = "Nom de l'expedient.", example = "ca", required = false)
    protected String expedientNom;

    @Schema(description = "URL de l'expedient.", example = "ca", required = false)
    protected String expedientUrl;

    @Schema(description = "Codi del Procediment.", example = "ca", required = false)
    protected String procedimentCodi;

    @Schema(description = "Nom del Procediment.", example = "ca", required = false)
    protected String procedimentNom;

    @Schema(description = "Tipus Documental. Si val null se li assigna 99", example = "TD99", required = false)
    protected Long documentType;

    @Schema(description = "Informació Addicional.", example = "ca", required = false)
    protected List<KeyValue> additionalInformation = null;

    @Schema(
            description = "Indica si s'ha d'utilitzar segell de temps en la firma. Només farà cas a aquest valor si la configuració de firma té definit el camp 'Politica de Segell de Temps' com a 'L´usuari elegirà si vol segellat de temps' ",
            example = "true",
            required = false)
    protected Boolean useTimeStamp;

    public FileInfoSignatureV2() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FileInfoSignatureV2(String signID, String name, String reason, String location, int signNumber,
            String languageSign, String expedientCodi, String expedientNom, String expedientUrl, String procedimentCodi,
            String procedimentNom, Long documentType, List<KeyValue> additionalInformation, Boolean useTimeStamp) {
        super();
        this.signID = signID;
        this.name = name;
        this.reason = reason;
        this.location = location;
        this.signNumber = signNumber;
        this.languageSign = languageSign;
        this.expedientCodi = expedientCodi;
        this.expedientNom = expedientNom;
        this.expedientUrl = expedientUrl;
        this.procedimentCodi = procedimentCodi;
        this.procedimentNom = procedimentNom;
        this.documentType = documentType;
        this.additionalInformation = additionalInformation;
        this.useTimeStamp = useTimeStamp;
    }

    public String getSignID() {
        return signID;
    }

    public void setSignID(String signID) {
        this.signID = signID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(int signNumber) {
        this.signNumber = signNumber;
    }

    public String getLanguageSign() {
        return languageSign;
    }

    public void setLanguageSign(String languageSign) {
        this.languageSign = languageSign;
    }

    public List<KeyValue> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<KeyValue> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getExpedientCodi() {
        return expedientCodi;
    }

    public void setExpedientCodi(String expedientCodi) {
        this.expedientCodi = expedientCodi;
    }

    public String getExpedientNom() {
        return expedientNom;
    }

    public void setExpedientNom(String expedientNom) {
        this.expedientNom = expedientNom;
    }

    public String getExpedientUrl() {
        return expedientUrl;
    }

    public void setExpedientUrl(String expedientUrl) {
        this.expedientUrl = expedientUrl;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public void setProcedimentCodi(String procedimentCodi) {
        this.procedimentCodi = procedimentCodi;
    }

    public String getProcedimentNom() {
        return procedimentNom;
    }

    public void setProcedimentNom(String procedimentNom) {
        this.procedimentNom = procedimentNom;
    }

    public Long getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Long documentType) {
        this.documentType = documentType;
    }

    public Boolean getUseTimeStamp() {
        return useTimeStamp;
    }

    public void setUseTimeStamp(Boolean useTimeStamp) {
        this.useTimeStamp = useTimeStamp;
    }

}
