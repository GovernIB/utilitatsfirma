
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.EstadisticaJPA;
import es.caib.utilitatsfirma.persistence.EstadisticaIJPAManager;
import es.caib.utilitatsfirma.model.dao.IEstadisticaManager;

import es.caib.utilitatsfirma.model.entity.Estadistica;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EstadisticaService extends EstadisticaIJPAManager,IEstadisticaManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/EstadisticaEJB!es.caib.utilitatsfirma.ejb.EstadisticaService";

    public EstadisticaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Estadistica instance, FitxerService fitxerEjb) throws I18NException;
}
