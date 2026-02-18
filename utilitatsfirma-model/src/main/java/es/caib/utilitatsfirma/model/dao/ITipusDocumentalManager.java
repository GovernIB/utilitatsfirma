package es.caib.utilitatsfirma.model.dao;

import es.caib.utilitatsfirma.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusDocumentalManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusDocumental, Long> {


	public TipusDocumental create( long _tipusDocumentalID_, java.lang.Long _pareTipusDocumentalID_, java.lang.String _nomCatala_, java.lang.String _nomCastella_, java.lang.String _descripcioCatala_, java.lang.String _descripcioCastella_) throws I18NException;

	public TipusDocumental findByPrimaryKey(long _tipusDocumentalID_);

	public void delete(long _tipusDocumentalID_);

}
