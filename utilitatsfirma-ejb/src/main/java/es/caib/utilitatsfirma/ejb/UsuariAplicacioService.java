
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioIJPAManager;
import es.caib.utilitatsfirma.model.dao.IUsuariAplicacioManager;

import es.caib.utilitatsfirma.model.entity.UsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariAplicacioService extends UsuariAplicacioIJPAManager,IUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/UsuariAplicacioEJB!es.caib.utilitatsfirma.ejb.UsuariAplicacioService";

    public UsuariAplicacioJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(UsuariAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
