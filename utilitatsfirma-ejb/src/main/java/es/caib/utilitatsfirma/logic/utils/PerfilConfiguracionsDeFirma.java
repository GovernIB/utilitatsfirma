package es.caib.utilitatsfirma.logic.utils;

import java.util.Map;

import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;


/**
 * 
 * @author anadal(u80067)
 *
 */
public class PerfilConfiguracionsDeFirma {

  public final PerfilDeFirma perfilDeFirma;
  public final Map<String, UsuariAplicacioConfiguracioJPA> configBySignID;

  public PerfilConfiguracionsDeFirma(PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) {
    super();
    this.perfilDeFirma = perfilDeFirma;
    this.configBySignID = configBySignID;
  }

}
