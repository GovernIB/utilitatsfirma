package es.caib.utilitatsfirma.model.dao;

import es.caib.utilitatsfirma.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IValidacioManager extends org.fundaciobit.genapp.common.query.ITableManager<Validacio, Long> {


	public Validacio create( java.lang.String _nom_, long _signaturaID_, java.lang.Long _detachedID_, java.lang.Integer _resultat_, java.lang.String _infoResultat_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_) throws I18NException;

	public Validacio findByPrimaryKey(long _validacioID_);

	public void delete(long _validacioID_);

}
