package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import com.fasterxml.jackson.annotation.JsonValue;

import es.caib.utilitatsfirma.commons.utils.Constants;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 5 ago 2026 9:36:17
 */

@Schema(
        name = "SignOperationEnum",
        description = "Possibles operacions de firma: Firma (0), Cofirma (1) o Contrafirma (2).",
        format = "int",
        enumAsRef = true,
        extensions = { @Extension(
                properties = {
                        @ExtensionProperty(
                                name = "enum-varnames",
                                parseValue = true,
                                value = "[\"SIGN_OPERATION_SIGN\"," + "\"SIGN_OPERATION_COSIGN\","
                                        + "\"SIGN_OPERATION_COUNTERSIGN\"]"),
                        @ExtensionProperty(
                                name = "enum-descriptions",
                                parseValue = true,
                                value = "[\"Firma simple\"," + "\"Cofirma\"," + "\"Contrafirma\"]") }) })
public enum SignOperationEnum {

    SIGN_OPERATION_SIGN(Constants.TIPUS_OPERACIO_FIRMA_FIRMAR), //  0;
    SIGN_OPERATION_COSIGN(Constants.TIPUS_OPERACIO_FIRMA_COFIRMAR), // = 1;
    SIGN_OPERATION_COUNTERSIGN(Constants.TIPUS_OPERACIO_FIRMA_CONTRAFIRMAR); // =2

    public final int value;

    SignOperationEnum(int value) {
        this.value = value;
    }

    @JsonValue 
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static SignOperationEnum fromValue(int value) {
        for (SignOperationEnum b : SignOperationEnum.values()) {
            if (b.value == value) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for SignOperationEnum.");
    }
}
