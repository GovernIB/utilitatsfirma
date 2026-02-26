package es.caib.utilitatsfirma.api.interna.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

/**
 *
 * @author anadal
 *
 */
@OpenAPIDefinition(
        servers = { @Server(
                url = "/utilitatsfirmaapi/interna",
                description = "Servidor local (sense protocol ni host) per a desplegaments en entorns on el context de l'aplicació és /utilitatsfirmaapi/interna)"),
                @Server(url = "http://localhost:8080/utilitatsfirmaapi/interna"),
                @Server(url = "https://dev.caib.es/utilitatsfirmaapi/interna"),
                @Server(url = "https://proves.caib.es/utilitatsfirmaapi/interna"),
                @Server(url = "https://se.caib.es/utilitatsfirmaapi/interna"),
                @Server(url = "https://www.caib.es/utilitatsfirmaapi/interna") })
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {

    protected Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    /**
     * Les aplicacions JAX-RS necessiten un constructor buid.
     */
    public JAXRSConfiguration() {
    }

    /**
     * Podem introduir tasques a realitzar per la inicialització de l'API REST.
     */
    @PostConstruct
    private void init() {
        log.info("\n\nIniciant API REST INTERNA de UtilitatsFirma\n\n");
    }

    /*
    @Override
    public Set<Class<?>> getClasses() {
        
        
        log.info("\n\nIniciant API REST INTERNA de UtilitatsFirma: passa per GetClasses()\n\n");
        
        Set<Class<?>> classes = new HashSet<>(super.getClasses());
        classes.add(SignedDocumentResponseMultipartMessageBodyWriter.class);
        return classes;
    }
    */

}
