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
        name = "SignAlgorithmEnum",
        description = "Possibles algorismes de firma.",
        format = "int",
        enumAsRef = true,
        extensions = { @Extension(
                properties = {
                        @ExtensionProperty(
                                name = "enum-varnames",
                                parseValue = true,
                                value = "[\"SIGN_ALGORITHM_SHA1WITHRSA\"," + "\"SIGN_ALGORITHM_SHA256WITHRSA\","
                                        + "\"SIGN_ALGORITHM_SHA384WITHRSA\"," + "\"SIGN_ALGORITHM_SHA512WITHRSA\"]"),
                        @ExtensionProperty(
                                name = "enum-descriptions",
                                parseValue = true,
                                value = "[\"SHA1WITHRSA\"," + "\"SHA256WITHRSA\"," + "\"SHA384WITHRSA\","
                                        + "\"SHA512WITHRSA\"]") }) })
public enum SignAlgorithmEnum {

    SIGN_ALGORITHM_SHA1WITHRSA(Constants.SIGN_ALGORITHM_SHA1WITHRSA),
    SIGN_ALGORITHM_SHA256WITHRSA(Constants.SIGN_ALGORITHM_SHA256WITHRSA),
    SIGN_ALGORITHM_SHA384WITHRSA(Constants.SIGN_ALGORITHM_SHA384WITHRSA),
    SIGN_ALGORITHM_SHA512WITHRSA(Constants.SIGN_ALGORITHM_SHA512WITHRSA);

    public final int value;

    SignAlgorithmEnum(int value) {
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

    public static SignAlgorithmEnum fromValue(int value) {
        for (SignAlgorithmEnum b : SignAlgorithmEnum.values()) {
            if (b.value == value) {
                return b;
            }
        }
        throw new IllegalArgumentException(
                "Unexpected value '" + value + "' for " + SignAlgorithmEnum.class.getSimpleName());
    }
}
