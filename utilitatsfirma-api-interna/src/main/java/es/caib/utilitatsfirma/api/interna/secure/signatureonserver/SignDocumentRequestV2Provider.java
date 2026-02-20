package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v2.SignDocumentRequestV2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 
 * @author anadal (u80067)
 * 19 feb 2026 18:34:07
 */
@Provider
public class SignDocumentRequestV2Provider implements ParamConverterProvider {

    protected Logger log = Logger.getLogger(getClass());

    public SignDocumentRequestV2Provider() {
        super();

        log.info("\n\n\n SignDocumentRequestV2Provider() CONSTRUCTOR  \n\n\n");

    }

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        log.info("\n\n Passant per SignDocumentRequestV2Provider.getConverter amb rawType: " + rawType.getName()
                + "\n\n");

        if (rawType.equals(SignDocumentRequestV2.class)) {
            return (ParamConverter<T>) new ParamConverter<SignDocumentRequestV2>() {
                @Override
                public SignDocumentRequestV2 fromString(String value) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        return mapper.readValue(value, SignDocumentRequestV2.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public String toString(SignDocumentRequestV2 value) {
                    return value.toString();
                }
            };
        }
        return null;
    }
}
