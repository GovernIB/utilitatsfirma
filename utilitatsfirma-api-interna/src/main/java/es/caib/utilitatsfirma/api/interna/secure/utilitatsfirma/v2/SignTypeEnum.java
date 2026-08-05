package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal (u80067)
 * 5 ago 2026 9:36:17
 */
@Schema(
        name = "SignTypeEnum",
        description = "Tipus firma (PAdEs, CAdES, XAdES, ...).",
        enumAsRef = true,
        format = "int",
        extensions = { @Extension(
                properties = { @ExtensionProperty(
                        name = "enum-varnames",
                        parseValue = true,
                        value = "[\"SIGN_TYPE_PADES\"," + "\"SIGN_TYPE_XADES\"," + "\"SIGN_TYPE_CADES\"," + "\"SMIME\","
                                + "\"SIGN_TYPE_FACTURAE\"," + "\"SIGN_TYPE_OOXML\"," + "\"SIGN_TYPE_ODF\","
                                + "\"SIGN_TYPE_CADES_ASICS\"," + "\"SIGN_TYPE_XADES_ASICS\"," + "\"SIGN_TYPE_PKCS\""

                                + "]"),
                        @ExtensionProperty(
                                name = "enum-descriptions",
                                parseValue = true,
                                value = "[\"PAdES\"," + "\"XAdES\"," + "\"CAdES\"," + "\"SMIME\"," + "\"FacturaE\","
                                        + "\"OOXML\"," + "\"ODF\"," + "\"CAdES-ASiC-S\"," + "\"XAdES-ASiC-S\","
                                        + "\"PKCS#1\"]") }) })
public enum SignTypeEnum {

    SIGN_TYPE_PADES(0), SIGN_TYPE_XADES(1), SIGN_TYPE_CADES(2), SIGN_TYPE_SMIME(3), SIGN_TYPE_FACTURAE(4),
    SIGN_TYPE_OOXML(5), SIGN_TYPE_ODF(6), SIGN_TYPE_CADES_ASICS(7), SIGN_TYPE_XADES_ASICS(8), SIGN_TYPE_PKCS(9);

    public final int value;

    SignTypeEnum(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @JsonValue
    public int getValue() {
        return value;
    }

    public static SignTypeEnum fromValue(int value) {
        for (SignTypeEnum b : SignTypeEnum.values()) {
            if (b.value == value) {
                return b;
            }
        }
        throw new IllegalArgumentException(
                "Unexpected value '" + value + "' for " + SignTypeEnum.class.getSimpleName());
    }
}
