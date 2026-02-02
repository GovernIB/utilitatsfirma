package es.caib.utilitatsfirma.model.dao;

import es.caib.utilitatsfirma.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariAplicacioManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariAplicacio, String> {


	public UsuariAplicacio create( java.lang.String _usuariAplicacioID_, java.lang.String _descripcio_, java.lang.String _emailAdmin_, boolean _actiu_) throws I18NException;

	public UsuariAplicacio findByPrimaryKey(java.lang.String _usuariAplicacioID_);

	public void delete(java.lang.String _usuariAplicacioID_);

}
