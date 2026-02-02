package es.caib.utilitatsfirma.model.dao;

import es.caib.utilitatsfirma.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariAplicacioConfiguracioManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariAplicacioConfiguracio, Long> {


	public UsuariAplicacioConfiguracio create( java.lang.String _nom_, int _usPoliticaDeFirma_, java.lang.String _policyIdentifier_, java.lang.String _policyIdentifierHash_, java.lang.String _policyIdentifierHashAlgorithm_, java.lang.String _policyUrlDocument_, int _tipusOperacioFirma_, int _tipusFirma_, int _algorismeDeFirma_, int _modeDeFirma_, boolean _comprovarNifFirma_, boolean _checkCanviatDocFirmat_, boolean _validarFirma_, long _pluginFirmaServidorID_, java.lang.Integer _upgradeSignFormat_, int _politicaSegellatDeTemps_) throws I18NException;

	public UsuariAplicacioConfiguracio findByPrimaryKey(long _usuariAplicacioConfigID_);

	public void delete(long _usuariAplicacioConfigID_);

}
