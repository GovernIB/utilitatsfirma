
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioIJPAManager;
import es.caib.utilitatsfirma.model.dao.IUsuariAplicacioConfiguracioManager;

import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariAplicacioConfiguracioService extends UsuariAplicacioConfiguracioIJPAManager,IUsuariAplicacioConfiguracioManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/UsuariAplicacioConfiguracioEJB!es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService";

    public UsuariAplicacioConfiguracioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(UsuariAplicacioConfiguracio instance, FitxerService fitxerEjb) throws I18NException;
}
