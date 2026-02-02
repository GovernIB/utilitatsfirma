package es.caib.utilitatsfirma.model.dao;

import es.caib.utilitatsfirma.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IConfiguracioDeFirmaPerUsuariAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<ConfiguracioDeFirmaPerUsuariAplicacio, Long> {


	public ConfiguracioDeFirmaPerUsuariAplicacio create( java.lang.String _usuariAplicacioID_, long _usuariAplicacioConfigID_) throws I18NException;

	public ConfiguracioDeFirmaPerUsuariAplicacio findByPrimaryKey(long _configsPerUsrAppID_);

	public void delete(long _configsPerUsrAppID_);

}
