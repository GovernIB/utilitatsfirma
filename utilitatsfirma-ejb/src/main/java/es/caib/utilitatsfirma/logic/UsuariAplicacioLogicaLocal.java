package es.caib.utilitatsfirma.logic;

import es.caib.utilitatsfirma.ejb.UsuariAplicacioService;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import java.util.Set;

/**
 * 
 * @author anadal
 * 3 feb 2026 11:49:08
 */
@Local
public interface UsuariAplicacioLogicaLocal extends UsuariAplicacioService {

  String JNDI_NAME = "java:app/utilitatsfirma-ejb/UsuariAplicacioLogicaEJB";

  @PermitAll
  public UsuariAplicacioJPA findByPrimaryKeyFull(String _usuariAplicacioID_);

  public UsuariAplicacioJPA checkForDeletion(String usuariAplicacioID)
      throws Exception, I18NException;

  public void checkForDisable(String usuariAplicacioID) throws Exception, I18NException;

  public Set<Long> deleteFull(String usuariAplicacio) throws I18NException;

  public void activar(String usuariAplicacioID) throws I18NException, Exception;

  public void desactivar(String usuariAplicacioID) throws I18NException, Exception;

  public UsuariAplicacioJPA createFull(UsuariAplicacioJPA _usuariAplicacio_)
      throws I18NException, I18NValidationException;

  public UsuariAplicacioJPA checkBasicUsuariAplicacioID(String usuariAplicacioID)
      throws I18NException;

}
