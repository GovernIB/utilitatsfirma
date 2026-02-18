
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.TipusDocumentalJPA;
import es.caib.utilitatsfirma.persistence.TipusDocumentalIJPAManager;
import es.caib.utilitatsfirma.model.dao.ITipusDocumentalManager;

import es.caib.utilitatsfirma.model.entity.TipusDocumental;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TipusDocumentalService extends TipusDocumentalIJPAManager,ITipusDocumentalManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/TipusDocumentalEJB!es.caib.utilitatsfirma.ejb.TipusDocumentalService";

    public TipusDocumentalJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(TipusDocumental instance, FitxerService fitxerEjb) throws I18NException;
}
