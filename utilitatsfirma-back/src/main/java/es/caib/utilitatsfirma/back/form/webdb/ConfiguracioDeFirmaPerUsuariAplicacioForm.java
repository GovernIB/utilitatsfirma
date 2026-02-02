package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseForm;
import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class ConfiguracioDeFirmaPerUsuariAplicacioForm extends UtilitatsFirmaBaseForm {
  
  private ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio;
  
  public ConfiguracioDeFirmaPerUsuariAplicacioForm() {
  }
  
  public ConfiguracioDeFirmaPerUsuariAplicacioForm(ConfiguracioDeFirmaPerUsuariAplicacioForm __toClone) {
    super(__toClone);
      this.configuracioDeFirmaPerUsuariAplicacio = __toClone.configuracioDeFirmaPerUsuariAplicacio;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
    this.listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID = __toClone.listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;
  }
  
  public ConfiguracioDeFirmaPerUsuariAplicacioForm(ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio, boolean nou) {
    super(nou);
    this.configuracioDeFirmaPerUsuariAplicacio = configuracioDeFirmaPerUsuariAplicacio;
  }
  
  public ConfiguracioDeFirmaPerUsuariAplicacioJPA getConfiguracioDeFirmaPerUsuariAplicacio() {
    return configuracioDeFirmaPerUsuariAplicacio;
  }
  public void setConfiguracioDeFirmaPerUsuariAplicacio(ConfiguracioDeFirmaPerUsuariAplicacioJPA configuracioDeFirmaPerUsuariAplicacio) {
    this.configuracioDeFirmaPerUsuariAplicacio = configuracioDeFirmaPerUsuariAplicacio;
  }
  
  
  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;

  public List<StringKeyValue> getListOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID() {
    return this.listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;
  }

  public void setListOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID(List<StringKeyValue> listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID) {
    this.listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID = listOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;
  }



  
} // Final de Classe 
