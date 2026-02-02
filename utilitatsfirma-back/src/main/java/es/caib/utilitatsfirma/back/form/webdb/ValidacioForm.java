package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseForm;
import es.caib.utilitatsfirma.persistence.ValidacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ValidacioForm extends UtilitatsFirmaBaseForm {
  
  private ValidacioJPA validacio;
  
  
  private CommonsMultipartFile signaturaID;
  private boolean signaturaIDDelete;
  
  
  private CommonsMultipartFile detachedID;
  private boolean detachedIDDelete;
  
  public ValidacioForm() {
  }
  
  public ValidacioForm(ValidacioForm __toClone) {
    super(__toClone);
      this.validacio = __toClone.validacio;
    this.listOfValuesForResultat = __toClone.listOfValuesForResultat;
  }
  
  public ValidacioForm(ValidacioJPA validacio, boolean nou) {
    super(nou);
    this.validacio = validacio;
  }
  
  public ValidacioJPA getValidacio() {
    return validacio;
  }
  public void setValidacio(ValidacioJPA validacio) {
    this.validacio = validacio;
  }
  
  
  public CommonsMultipartFile getSignaturaID() {
    return signaturaID;
  }
  
   public void setSignaturaID(CommonsMultipartFile signaturaID) {
    this.signaturaID = signaturaID;
  }
  public boolean isSignaturaIDDelete() {
    return signaturaIDDelete;
  }
  
  public void setSignaturaIDDelete(boolean signaturaIDDelete) {
    this.signaturaIDDelete = signaturaIDDelete;
   }
  public CommonsMultipartFile getDetachedID() {
    return detachedID;
  }
  
   public void setDetachedID(CommonsMultipartFile detachedID) {
    this.detachedID = detachedID;
  }
  public boolean isDetachedIDDelete() {
    return detachedIDDelete;
  }
  
  public void setDetachedIDDelete(boolean detachedIDDelete) {
    this.detachedIDDelete = detachedIDDelete;
   }
  private List<StringKeyValue> listOfValuesForResultat;

  public List<StringKeyValue> getListOfValuesForResultat() {
    return this.listOfValuesForResultat;
  }

  public void setListOfValuesForResultat(List<StringKeyValue> listOfValuesForResultat) {
    this.listOfValuesForResultat = listOfValuesForResultat;
  }



  
} // Final de Classe 
