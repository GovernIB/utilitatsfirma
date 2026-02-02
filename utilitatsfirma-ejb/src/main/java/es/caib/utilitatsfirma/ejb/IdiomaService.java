
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.IdiomaJPA;
import es.caib.utilitatsfirma.persistence.IdiomaIJPAManager;
import es.caib.utilitatsfirma.model.dao.IIdiomaManager;

import es.caib.utilitatsfirma.model.entity.Idioma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/IdiomaEJB!es.caib.utilitatsfirma.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Idioma instance, FitxerService fitxerEjb) throws I18NException;
}
