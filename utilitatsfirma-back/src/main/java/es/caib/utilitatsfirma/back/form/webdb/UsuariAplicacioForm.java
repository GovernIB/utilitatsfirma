package es.caib.utilitatsfirma.back.form.webdb;

import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseForm;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariAplicacioForm extends UtilitatsFirmaBaseForm {
  
  private UsuariAplicacioJPA usuariAplicacio;
  
  public UsuariAplicacioForm() {
  }
  
  public UsuariAplicacioForm(UsuariAplicacioForm __toClone) {
    super(__toClone);
      this.usuariAplicacio = __toClone.usuariAplicacio;
  }
  
  public UsuariAplicacioForm(UsuariAplicacioJPA usuariAplicacio, boolean nou) {
    super(nou);
    this.usuariAplicacio = usuariAplicacio;
  }
  
  public UsuariAplicacioJPA getUsuariAplicacio() {
    return usuariAplicacio;
  }
  public void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }
  
  
  
} // Final de Classe 
