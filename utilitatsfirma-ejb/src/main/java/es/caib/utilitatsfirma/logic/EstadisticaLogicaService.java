package es.caib.utilitatsfirma.logic;

import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.utilitatsfirma.ejb.EstadisticaService;

/**
 * 
 * @author anadal (u80067)
 * 20 mar 2026 12:52:54
 */
@Local
public interface EstadisticaLogicaService extends EstadisticaService {

    String JNDI_NAME = "java:app/utilitatsfirma-ejb/EstadisticaLogicaEJB";

    /**
     * Afegir una estadística al sistema.
     * @param tipus
     * @param valor
     * @param usuariAplicacioID
     */
    public void addEstadistica(int tipus, double valor, String usuariAplicacioID, int entorn);
    
    
    

}
