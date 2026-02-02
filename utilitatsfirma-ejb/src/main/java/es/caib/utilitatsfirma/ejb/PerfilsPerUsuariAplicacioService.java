
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.PerfilsPerUsuariAplicacioJPA;
import es.caib.utilitatsfirma.persistence.PerfilsPerUsuariAplicacioIJPAManager;
import es.caib.utilitatsfirma.model.dao.IPerfilsPerUsuariAplicacioManager;

import es.caib.utilitatsfirma.model.entity.PerfilsPerUsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PerfilsPerUsuariAplicacioService extends PerfilsPerUsuariAplicacioIJPAManager,IPerfilsPerUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/PerfilsPerUsuariAplicacioEJB!es.caib.utilitatsfirma.ejb.PerfilsPerUsuariAplicacioService";

    public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PerfilsPerUsuariAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
