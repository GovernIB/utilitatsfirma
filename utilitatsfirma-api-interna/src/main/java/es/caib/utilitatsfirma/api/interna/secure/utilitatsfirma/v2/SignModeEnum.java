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
        name = "SignModeEnum",
        description = "Modes de firma.",
        format = "int",
        enumAsRef = true,
        extensions = { @Extension(
                properties = {
                        @ExtensionProperty(
                                name = "enum-varnames",
                                parseValue = true,
                                value = "[\"SIGN_MODE_ATTACHED_ENVELOPED\"," + "\"SIGN_MODE_ATTACHED_ENVELOPING\","
                                        + "\"SIGN_MODE_DETACHED\"," + "\"SIGN_MODE_INTERNALLY_DETACHED\","
                                        + "\"SIGN_MODE_EXTERNALLY_DETACHED\"]"),
                        @ExtensionProperty(
                                name = "enum-descriptions",
                                parseValue = true,
                                value = "[\"El fitxer de dades resultant inclou la firma: PDF, ODT, ...\","
                                        + "\"El fitxer resultant serà la firma que incloura les dades originals\","
                                        + "\"El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original\","
                                        + "\"Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma\","
                                        + "\"Firma especial XAdES en que les dades es substitueixen per un resum del fitxer a signar. Entre les dades del resum hi ha una URL a les dades del fitxer original\"]") }) })
public enum SignModeEnum {

    SIGN_MODE_ATTACHED_ENVELOPED(Constants.SIGN_MODE_ATTACHED_ENVELOPED), // 0
    SIGN_MODE_ATTACHED_ENVELOPING(Constants.SIGN_MODE_ATTACHED_ENVELOPING), // 3
    SIGN_MODE_DETACHED(Constants.SIGN_MODE_DETACHED), // 1
    SIGN_MODE_INTERNALLY_DETACHED(Constants.SIGN_MODE_INTERNALLY_DETACHED), // 4
    SIGN_MODE_EXTERNALLY_DETACHED(Constants.SIGN_MODE_EXTERNALLY_DETACHED); // 5

    public final int value;

    SignModeEnum(int value) {
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

    public static SignModeEnum fromValue(int value) {
        for (SignModeEnum b : SignModeEnum.values()) {
            if (b.value == value) {
                return b;
            }
        }
        throw new IllegalArgumentException(
                "Unexpected value '" + value + "' for " + SignModeEnum.class.getSimpleName());
    }
}
