
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioJPA;
import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioIJPAManager;
import es.caib.utilitatsfirma.model.dao.IConfiguracioDeFirmaPerUsuariAplicacioManager;

import es.caib.utilitatsfirma.model.entity.ConfiguracioDeFirmaPerUsuariAplicacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface ConfiguracioDeFirmaPerUsuariAplicacioService extends ConfiguracioDeFirmaPerUsuariAplicacioIJPAManager,IConfiguracioDeFirmaPerUsuariAplicacioManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/ConfiguracioDeFirmaPerUsuariAplicacioEJB!es.caib.utilitatsfirma.ejb.ConfiguracioDeFirmaPerUsuariAplicacioService";

    public ConfiguracioDeFirmaPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(ConfiguracioDeFirmaPerUsuariAplicacio instance, FitxerService fitxerEjb) throws I18NException;
}
