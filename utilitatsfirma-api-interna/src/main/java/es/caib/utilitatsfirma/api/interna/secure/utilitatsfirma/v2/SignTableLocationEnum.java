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
        name = "SignaturesTableLocationEnum",
        description = "Posicio de la taula de firmes (Només PDF).",
        format = "int",
        enumAsRef = true,
        extensions = { @Extension(
                properties = { @ExtensionProperty(
                        name = "enum-varnames",
                        parseValue = true,
                        value = "[\"SIGNATURES_TABLE_LOCATION_WITHOUT\"," + "\"SIGNATURES_TABLE_LOCATION_FIRSTPAGE\","
                                + "\"SIGNATURES_TABLE_LOCATION_LASTPAGE\"]"),
                        @ExtensionProperty(
                                name = "enum-descriptions",
                                parseValue = true,
                                value = "[\"Sense taula de firmes\"," + "\"Taula de firmes en la primera pàgina\","
                                        + "\"Taula de firmes en la darrera pàgina\"]") }) })
public enum SignTableLocationEnum {

    SIGNATURES_TABLE_LOCATION_WITHOUT(0), //  0;
    SIGNATURES_TABLE_LOCATION_FIRSTPAGE(1), // = 1;
    SIGNATURES_TABLE_LOCATION_LASTPAGE(-1); // =-1

    public final int value;

    SignTableLocationEnum(int value) {
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

    public static SignTableLocationEnum fromValue(int value) {
        for (SignTableLocationEnum b : SignTableLocationEnum.values()) {
            if (b.value == value) {
                return b;
            }
        }
        throw new IllegalArgumentException(
                "Unexpected value '" + value + "' for " + SignTableLocationEnum.class.getSimpleName());
    }
}
