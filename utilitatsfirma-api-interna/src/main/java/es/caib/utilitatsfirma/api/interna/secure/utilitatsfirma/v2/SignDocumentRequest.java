package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.CommonInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 9:52:37
 */
@Schema(description = "Petició de firma en servidor")
public class SignDocumentRequest {

    @Schema(
            description = "Configuracions generals de firma i identificacio del solicitant i solicitat",
            example = "",
            required = true)
    protected CommonInfo commonInfo;

    @Schema(description = "Informació especifica per a realitzar la firma", example = "", required = true)
    protected FileInfoSignature fileInfoSignature;

    public SignDocumentRequest() {

        super();

    }

    public static SignDocumentRequest valueOf(String json)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        SignDocumentRequest sdr = mapper.readValue(json, SignDocumentRequest.class);
        return sdr;

    }

    public SignDocumentRequest(CommonInfo commonInfo, FileInfoSignature fileInfoSignature) {
        super();
        this.commonInfo = commonInfo;
        this.fileInfoSignature = fileInfoSignature;
    }

    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    public void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }

    public FileInfoSignature getFileInfoSignature() {
        return fileInfoSignature;
    }

    public void setFileInfoSignature(FileInfoSignature fileInfoSignature) {
        this.fileInfoSignature = fileInfoSignature;
    }

}
