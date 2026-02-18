package es.caib.utilitatsfirma.back.form.webdb;

import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseForm;
import es.caib.utilitatsfirma.persistence.TipusDocumentalJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class TipusDocumentalForm extends UtilitatsFirmaBaseForm {
  
  private TipusDocumentalJPA tipusDocumental;
  
  public TipusDocumentalForm() {
  }
  
  public TipusDocumentalForm(TipusDocumentalForm __toClone) {
    super(__toClone);
      this.tipusDocumental = __toClone.tipusDocumental;
  }
  
  public TipusDocumentalForm(TipusDocumentalJPA tipusDocumental, boolean nou) {
    super(nou);
    this.tipusDocumental = tipusDocumental;
  }
  
  public TipusDocumentalJPA getTipusDocumental() {
    return tipusDocumental;
  }
  public void setTipusDocumental(TipusDocumentalJPA tipusDocumental) {
    this.tipusDocumental = tipusDocumental;
  }
  
  
  
} // Final de Classe 
