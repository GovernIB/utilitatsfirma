package es.caib.utilitatsfirma.logic;

import javax.ejb.Local;

import es.caib.utilitatsfirma.ejb.PluginService;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginLogicaLocal extends PluginService {

    String JNDI_NAME = "java:app/utilitatsfirma-ejb/PluginLogicaEJB";

    public boolean deleteOfCache(Long pluginID);

    public void clearCache();

}
