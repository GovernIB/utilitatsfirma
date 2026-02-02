
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.PluginJPA;
import es.caib.utilitatsfirma.persistence.PluginIJPAManager;
import es.caib.utilitatsfirma.model.dao.IPluginManager;

import es.caib.utilitatsfirma.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/PluginEJB!es.caib.utilitatsfirma.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Plugin instance, FitxerService fitxerEjb) throws I18NException;
}
