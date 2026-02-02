
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.utilitatsfirma.persistence.PerfilDeFirmaJPA;
import es.caib.utilitatsfirma.persistence.PerfilDeFirmaIJPAManager;
import es.caib.utilitatsfirma.model.dao.IPerfilDeFirmaManager;

import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PerfilDeFirmaService extends PerfilDeFirmaIJPAManager,IPerfilDeFirmaManager {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/PerfilDeFirmaEJB!es.caib.utilitatsfirma.ejb.PerfilDeFirmaService";

    public PerfilDeFirmaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(PerfilDeFirma instance, FitxerService fitxerEjb) throws I18NException;
}
