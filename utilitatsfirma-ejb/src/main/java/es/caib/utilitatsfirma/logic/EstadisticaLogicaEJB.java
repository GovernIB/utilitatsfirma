package es.caib.utilitatsfirma.logic;

import es.caib.utilitatsfirma.commons.utils.Configuracio;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.ejb.EstadisticaEJB;
import es.caib.utilitatsfirma.persistence.EstadisticaJPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.fundaciobit.genapp.common.i18n.I18NException;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal (u80067)
 * 20 mar 2026 12:52:43
 */
@Stateless
public class EstadisticaLogicaEJB extends EstadisticaEJB implements EstadisticaLogicaService {

    @Override
    public void addEstadistica(int tipus, double valor, String usuariAplicacioID, int entorn) {
        try {
            EstadisticaJPA est = new EstadisticaJPA();
            est.setValor(valor);
            est.setTipus(tipus);
            est.setUsuariAplicacioID(usuariAplicacioID);
            est.setData(new Timestamp(System.currentTimeMillis()));
            est.setEntorn(entorn);
            this.create(est);
        } catch (Throwable th) {
            log.error("Error afegint estadistiques de Peticio Finalitzada: " + th.getMessage(), th);
        }
    }

}
