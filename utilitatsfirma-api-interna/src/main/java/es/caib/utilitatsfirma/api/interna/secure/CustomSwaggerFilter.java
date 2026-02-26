package es.caib.utilitatsfirma.api.interna.secure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v3.core.filter.AbstractSpecFilter;
import io.swagger.v3.oas.models.media.Schema;

/**
 * No sabem per quina raó però el plugin genera varis classes de Mòdel que els Serveis Rest no utilitzen.
 * Amb aquest filtre aconseguim eliminar-les de la inclusió en l'openapi.json
 *
 * @author anadal
 * 28 may 2025 8:31:36
 */
// https://github.com/swagger-api/swagger-core/blob/master/modules/swagger-core/src/main/java/io/swagger/v3/core/filter/AbstractSpecFilter.java
public class CustomSwaggerFilter extends AbstractSpecFilter {

    @SuppressWarnings("rawtypes")
    @Override
    public Optional<Schema> filterSchema(Schema schema, Map<String, List<String>> params, Map<String, String> cookies,
            Map<String, List<String>> headers) {

        //System.out.println("CustomSwaggerFilter::filterSchema(" + schema + ")");
        
        final String name = schema.getName();

        if (name != null && ("ValidacioCompletaResponse".equals(name)
                    // || "ValidateSignatureResponse".equals(schema.getName())
                     || "OutputPartMediaType".equals(schema.getName())
                    || "OutputPartGenericType".equals(schema.getName())
              || "OutputPart".equals(schema.getName())
              || "MultipartFormDataOutput".equals(schema.getName())
              || "ValidationStatusErrorException".equals(schema.getName())
              || "ValidationStatusErrorExceptionStackTraceInner".equals(schema.getName())
              )
        ) {
            return Optional.empty();
        } else {

            return super.filterSchema(schema, params, cookies, headers);
        }
    }

}