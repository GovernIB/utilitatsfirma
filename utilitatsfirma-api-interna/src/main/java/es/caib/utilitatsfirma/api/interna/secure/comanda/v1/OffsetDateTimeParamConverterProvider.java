package es.caib.utilitatsfirma.api.interna.secure.comanda.v1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 * ParamConverterProvider per a OffsetDateTime, que permet convertir entre String i OffsetDateTime en els paràmetres de les peticions REST.
 * @author anadal (u80067)
 * 24 mar 2026 8:33:27
 */
@Provider
public class OffsetDateTimeParamConverterProvider implements ParamConverterProvider {

    private static final OffsetDateTimeParamConverter CONVERTER = new OffsetDateTimeParamConverter();

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (OffsetDateTime.class.equals(rawType)) {
            ParamConverter<T> pc = (ParamConverter<T>) CONVERTER;
            return pc;
        }
        return null;
    }
    
    
    protected static class OffsetDateTimeParamConverter implements ParamConverter<OffsetDateTime> {

        @Override
        public OffsetDateTime fromString(String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }
            return OffsetDateTime.parse(value);
        }

        @Override
        public String toString(OffsetDateTime value) {
            return value == null ? null : value.toString();
        }
    }
}
