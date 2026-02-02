package es.caib.utilitatsfirma.model;

import es.caib.utilitatsfirma.model.dao.*;

public interface IUtilitatsFirmaDaoManagers {
	public IEstadisticaManager getEstadisticaManager();
	public IFitxerManager getFitxerManager();
	public IIdiomaManager getIdiomaManager();
	public IPerfilDeFirmaManager getPerfilDeFirmaManager();
	public IPerfilsPerUsuariAplicacioManager getPerfilsPerUsuariAplicacioManager();
	public IPluginManager getPluginManager();
	public ITraduccioManager getTraduccioManager();
	public IUsuariAplicacioManager getUsuariAplicacioManager();
	public IUsuariAplicacioConfiguracioManager getUsuariAplicacioConfiguracioManager();
	public IValidacioManager getValidacioManager();

}