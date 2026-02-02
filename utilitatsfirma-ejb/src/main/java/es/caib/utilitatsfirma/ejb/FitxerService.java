
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.FitxerJPA;
import es.caib.utilitatsfirma.persistence.FitxerIJPAManager;
import es.caib.utilitatsfirma.model.dao.IFitxerManager;

import es.caib.utilitatsfirma.model.entity.Fitxer;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/FitxerEJB!es.caib.utilitatsfirma.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Fitxer instance, FitxerService fitxerEjb) throws I18NException;
}
