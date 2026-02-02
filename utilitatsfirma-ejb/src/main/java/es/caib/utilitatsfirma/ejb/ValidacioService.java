
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.ValidacioJPA;
import es.caib.utilitatsfirma.persistence.ValidacioIJPAManager;
import es.caib.utilitatsfirma.model.dao.IValidacioManager;

import es.caib.utilitatsfirma.model.entity.Validacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ValidacioService extends ValidacioIJPAManager,IValidacioManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/ValidacioEJB!es.caib.utilitatsfirma.ejb.ValidacioService";

    public ValidacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Validacio instance, FitxerService fitxerEjb) throws I18NException;
}
