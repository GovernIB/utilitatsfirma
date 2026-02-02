package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseForm;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariAplicacioConfiguracioForm extends UtilitatsFirmaBaseForm {
  
  private UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio;
  
  public UsuariAplicacioConfiguracioForm() {
  }
  
  public UsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioForm __toClone) {
    super(__toClone);
      this.usuariAplicacioConfiguracio = __toClone.usuariAplicacioConfiguracio;
    this.listOfValuesForUsPoliticaDeFirma = __toClone.listOfValuesForUsPoliticaDeFirma;
    this.listOfValuesForTipusOperacioFirma = __toClone.listOfValuesForTipusOperacioFirma;
    this.listOfValuesForTipusFirma = __toClone.listOfValuesForTipusFirma;
    this.listOfValuesForAlgorismeDeFirma = __toClone.listOfValuesForAlgorismeDeFirma;
    this.listOfValuesForModeDeFirma = __toClone.listOfValuesForModeDeFirma;
    this.listOfPluginForPluginFirmaServidorID = __toClone.listOfPluginForPluginFirmaServidorID;
    this.listOfValuesForUpgradeSignFormat = __toClone.listOfValuesForUpgradeSignFormat;
    this.listOfValuesForPoliticaSegellatDeTemps = __toClone.listOfValuesForPoliticaSegellatDeTemps;
  }
  
  public UsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio, boolean nou) {
    super(nou);
    this.usuariAplicacioConfiguracio = usuariAplicacioConfiguracio;
  }
  
  public UsuariAplicacioConfiguracioJPA getUsuariAplicacioConfiguracio() {
    return usuariAplicacioConfiguracio;
  }
  public void setUsuariAplicacioConfiguracio(UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) {
    this.usuariAplicacioConfiguracio = usuariAplicacioConfiguracio;
  }
  
  
  private List<StringKeyValue> listOfValuesForUsPoliticaDeFirma;

  public List<StringKeyValue> getListOfValuesForUsPoliticaDeFirma() {
    return this.listOfValuesForUsPoliticaDeFirma;
  }

  public void setListOfValuesForUsPoliticaDeFirma(List<StringKeyValue> listOfValuesForUsPoliticaDeFirma) {
    this.listOfValuesForUsPoliticaDeFirma = listOfValuesForUsPoliticaDeFirma;
  }



  private List<StringKeyValue> listOfValuesForTipusOperacioFirma;

  public List<StringKeyValue> getListOfValuesForTipusOperacioFirma() {
    return this.listOfValuesForTipusOperacioFirma;
  }

  public void setListOfValuesForTipusOperacioFirma(List<StringKeyValue> listOfValuesForTipusOperacioFirma) {
    this.listOfValuesForTipusOperacioFirma = listOfValuesForTipusOperacioFirma;
  }



  private List<StringKeyValue> listOfValuesForTipusFirma;

  public List<StringKeyValue> getListOfValuesForTipusFirma() {
    return this.listOfValuesForTipusFirma;
  }

  public void setListOfValuesForTipusFirma(List<StringKeyValue> listOfValuesForTipusFirma) {
    this.listOfValuesForTipusFirma = listOfValuesForTipusFirma;
  }



  private List<StringKeyValue> listOfValuesForAlgorismeDeFirma;

  public List<StringKeyValue> getListOfValuesForAlgorismeDeFirma() {
    return this.listOfValuesForAlgorismeDeFirma;
  }

  public void setListOfValuesForAlgorismeDeFirma(List<StringKeyValue> listOfValuesForAlgorismeDeFirma) {
    this.listOfValuesForAlgorismeDeFirma = listOfValuesForAlgorismeDeFirma;
  }



  private List<StringKeyValue> listOfValuesForModeDeFirma;

  public List<StringKeyValue> getListOfValuesForModeDeFirma() {
    return this.listOfValuesForModeDeFirma;
  }

  public void setListOfValuesForModeDeFirma(List<StringKeyValue> listOfValuesForModeDeFirma) {
    this.listOfValuesForModeDeFirma = listOfValuesForModeDeFirma;
  }



  private List<StringKeyValue> listOfPluginForPluginFirmaServidorID;

  public List<StringKeyValue> getListOfPluginForPluginFirmaServidorID() {
    return this.listOfPluginForPluginFirmaServidorID;
  }

  public void setListOfPluginForPluginFirmaServidorID(List<StringKeyValue> listOfPluginForPluginFirmaServidorID) {
    this.listOfPluginForPluginFirmaServidorID = listOfPluginForPluginFirmaServidorID;
  }



  private List<StringKeyValue> listOfValuesForUpgradeSignFormat;

  public List<StringKeyValue> getListOfValuesForUpgradeSignFormat() {
    return this.listOfValuesForUpgradeSignFormat;
  }

  public void setListOfValuesForUpgradeSignFormat(List<StringKeyValue> listOfValuesForUpgradeSignFormat) {
    this.listOfValuesForUpgradeSignFormat = listOfValuesForUpgradeSignFormat;
  }



  private List<StringKeyValue> listOfValuesForPoliticaSegellatDeTemps;

  public List<StringKeyValue> getListOfValuesForPoliticaSegellatDeTemps() {
    return this.listOfValuesForPoliticaSegellatDeTemps;
  }

  public void setListOfValuesForPoliticaSegellatDeTemps(List<StringKeyValue> listOfValuesForPoliticaSegellatDeTemps) {
    this.listOfValuesForPoliticaSegellatDeTemps = listOfValuesForPoliticaSegellatDeTemps;
  }



  
} // Final de Classe 
