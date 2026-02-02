
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseFilterForm;

import es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariAplicacioConfiguracioFilterForm extends UtilitatsFirmaBaseFilterForm implements UsuariAplicacioConfiguracioFields {

  private java.lang.Long usuariAplicacioConfigIDDesde;

  public java.lang.Long getUsuariAplicacioConfigIDDesde() {
    return this.usuariAplicacioConfigIDDesde;
  }

  public void setUsuariAplicacioConfigIDDesde(java.lang.Long usuariAplicacioConfigIDDesde) {
    this.usuariAplicacioConfigIDDesde = usuariAplicacioConfigIDDesde;
  }


  private java.lang.Long usuariAplicacioConfigIDFins;

  public java.lang.Long getUsuariAplicacioConfigIDFins() {
    return this.usuariAplicacioConfigIDFins;
  }

  public void setUsuariAplicacioConfigIDFins(java.lang.Long usuariAplicacioConfigIDFins) {
    this.usuariAplicacioConfigIDFins = usuariAplicacioConfigIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.util.List<java.lang.Integer> usPoliticaDeFirmaSelect;

  public java.util.List<java.lang.Integer> getUsPoliticaDeFirmaSelect() {
    return this.usPoliticaDeFirmaSelect;
  }

  public void setUsPoliticaDeFirmaSelect(java.util.List<java.lang.Integer> usPoliticaDeFirmaSelect) {
    this.usPoliticaDeFirmaSelect = usPoliticaDeFirmaSelect;
  }


  private java.lang.String policyIdentifier;

  public java.lang.String getPolicyIdentifier() {
    return this.policyIdentifier;
  }

  public void setPolicyIdentifier(java.lang.String policyIdentifier) {
    this.policyIdentifier = policyIdentifier;
  }


  private java.lang.String policyIdentifierHash;

  public java.lang.String getPolicyIdentifierHash() {
    return this.policyIdentifierHash;
  }

  public void setPolicyIdentifierHash(java.lang.String policyIdentifierHash) {
    this.policyIdentifierHash = policyIdentifierHash;
  }


  private java.lang.String policyIdentifierHashAlgorithm;

  public java.lang.String getPolicyIdentifierHashAlgorithm() {
    return this.policyIdentifierHashAlgorithm;
  }

  public void setPolicyIdentifierHashAlgorithm(java.lang.String policyIdentifierHashAlgorithm) {
    this.policyIdentifierHashAlgorithm = policyIdentifierHashAlgorithm;
  }


  private java.lang.String policyUrlDocument;

  public java.lang.String getPolicyUrlDocument() {
    return this.policyUrlDocument;
  }

  public void setPolicyUrlDocument(java.lang.String policyUrlDocument) {
    this.policyUrlDocument = policyUrlDocument;
  }


  private java.util.List<java.lang.Integer> tipusOperacioFirmaSelect;

  public java.util.List<java.lang.Integer> getTipusOperacioFirmaSelect() {
    return this.tipusOperacioFirmaSelect;
  }

  public void setTipusOperacioFirmaSelect(java.util.List<java.lang.Integer> tipusOperacioFirmaSelect) {
    this.tipusOperacioFirmaSelect = tipusOperacioFirmaSelect;
  }


  private java.util.List<java.lang.Integer> tipusFirmaSelect;

  public java.util.List<java.lang.Integer> getTipusFirmaSelect() {
    return this.tipusFirmaSelect;
  }

  public void setTipusFirmaSelect(java.util.List<java.lang.Integer> tipusFirmaSelect) {
    this.tipusFirmaSelect = tipusFirmaSelect;
  }


  private java.util.List<java.lang.Integer> algorismeDeFirmaSelect;

  public java.util.List<java.lang.Integer> getAlgorismeDeFirmaSelect() {
    return this.algorismeDeFirmaSelect;
  }

  public void setAlgorismeDeFirmaSelect(java.util.List<java.lang.Integer> algorismeDeFirmaSelect) {
    this.algorismeDeFirmaSelect = algorismeDeFirmaSelect;
  }


  private java.util.List<java.lang.Integer> modeDeFirmaSelect;

  public java.util.List<java.lang.Integer> getModeDeFirmaSelect() {
    return this.modeDeFirmaSelect;
  }

  public void setModeDeFirmaSelect(java.util.List<java.lang.Integer> modeDeFirmaSelect) {
    this.modeDeFirmaSelect = modeDeFirmaSelect;
  }


  private java.lang.Long pluginFirmaServidorIDDesde;

  public java.lang.Long getPluginFirmaServidorIDDesde() {
    return this.pluginFirmaServidorIDDesde;
  }

  public void setPluginFirmaServidorIDDesde(java.lang.Long pluginFirmaServidorIDDesde) {
    this.pluginFirmaServidorIDDesde = pluginFirmaServidorIDDesde;
  }


  private java.lang.Long pluginFirmaServidorIDFins;

  public java.lang.Long getPluginFirmaServidorIDFins() {
    return this.pluginFirmaServidorIDFins;
  }

  public void setPluginFirmaServidorIDFins(java.lang.Long pluginFirmaServidorIDFins) {
    this.pluginFirmaServidorIDFins = pluginFirmaServidorIDFins;
  }


  private java.util.List<java.lang.Integer> upgradeSignFormatSelect;

  public java.util.List<java.lang.Integer> getUpgradeSignFormatSelect() {
    return this.upgradeSignFormatSelect;
  }

  public void setUpgradeSignFormatSelect(java.util.List<java.lang.Integer> upgradeSignFormatSelect) {
    this.upgradeSignFormatSelect = upgradeSignFormatSelect;
  }


  private java.util.List<java.lang.Integer> politicaSegellatDeTempsSelect;

  public java.util.List<java.lang.Integer> getPoliticaSegellatDeTempsSelect() {
    return this.politicaSegellatDeTempsSelect;
  }

  public void setPoliticaSegellatDeTempsSelect(java.util.List<java.lang.Integer> politicaSegellatDeTempsSelect) {
    this.politicaSegellatDeTempsSelect = politicaSegellatDeTempsSelect;
  }


  public UsuariAplicacioConfiguracioFilterForm() {
  }
  
  public UsuariAplicacioConfiguracioFilterForm(UsuariAplicacioConfiguracioFilterForm __toClone) {
    super(__toClone);
    this.usuariAplicacioConfigIDDesde = __toClone.usuariAplicacioConfigIDDesde;
    this.usuariAplicacioConfigIDFins = __toClone.usuariAplicacioConfigIDFins;
    this.nom = __toClone.nom;
    this.usPoliticaDeFirmaSelect = __toClone.usPoliticaDeFirmaSelect;
    this.policyIdentifier = __toClone.policyIdentifier;
    this.policyIdentifierHash = __toClone.policyIdentifierHash;
    this.policyIdentifierHashAlgorithm = __toClone.policyIdentifierHashAlgorithm;
    this.policyUrlDocument = __toClone.policyUrlDocument;
    this.tipusOperacioFirmaSelect = __toClone.tipusOperacioFirmaSelect;
    this.tipusFirmaSelect = __toClone.tipusFirmaSelect;
    this.algorismeDeFirmaSelect = __toClone.algorismeDeFirmaSelect;
    this.modeDeFirmaSelect = __toClone.modeDeFirmaSelect;
    this.pluginFirmaServidorIDDesde = __toClone.pluginFirmaServidorIDDesde;
    this.pluginFirmaServidorIDFins = __toClone.pluginFirmaServidorIDFins;
    this.upgradeSignFormatSelect = __toClone.upgradeSignFormatSelect;
    this.politicaSegellatDeTempsSelect = __toClone.politicaSegellatDeTempsSelect;
    this.mapOfValuesForUsPoliticaDeFirma = __toClone.mapOfValuesForUsPoliticaDeFirma;
    this.mapOfValuesForTipusOperacioFirma = __toClone.mapOfValuesForTipusOperacioFirma;
    this.mapOfValuesForTipusFirma = __toClone.mapOfValuesForTipusFirma;
    this.mapOfValuesForAlgorismeDeFirma = __toClone.mapOfValuesForAlgorismeDeFirma;
    this.mapOfValuesForModeDeFirma = __toClone.mapOfValuesForModeDeFirma;
    this.mapOfPluginForPluginFirmaServidorID = __toClone.mapOfPluginForPluginFirmaServidorID;
    this.mapOfValuesForUpgradeSignFormat = __toClone.mapOfValuesForUpgradeSignFormat;
    this.mapOfValuesForPoliticaSegellatDeTemps = __toClone.mapOfValuesForPoliticaSegellatDeTemps;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfValuesForUsPoliticaDeFirma;

  public Map<String, String> getMapOfValuesForUsPoliticaDeFirma() {
    return this.mapOfValuesForUsPoliticaDeFirma;
  }

  public void setMapOfValuesForUsPoliticaDeFirma(Map<String, String> mapOfValuesForUsPoliticaDeFirma) {
    this.mapOfValuesForUsPoliticaDeFirma = mapOfValuesForUsPoliticaDeFirma;
  }



  private Map<String, String> mapOfValuesForTipusOperacioFirma;

  public Map<String, String> getMapOfValuesForTipusOperacioFirma() {
    return this.mapOfValuesForTipusOperacioFirma;
  }

  public void setMapOfValuesForTipusOperacioFirma(Map<String, String> mapOfValuesForTipusOperacioFirma) {
    this.mapOfValuesForTipusOperacioFirma = mapOfValuesForTipusOperacioFirma;
  }



  private Map<String, String> mapOfValuesForTipusFirma;

  public Map<String, String> getMapOfValuesForTipusFirma() {
    return this.mapOfValuesForTipusFirma;
  }

  public void setMapOfValuesForTipusFirma(Map<String, String> mapOfValuesForTipusFirma) {
    this.mapOfValuesForTipusFirma = mapOfValuesForTipusFirma;
  }



  private Map<String, String> mapOfValuesForAlgorismeDeFirma;

  public Map<String, String> getMapOfValuesForAlgorismeDeFirma() {
    return this.mapOfValuesForAlgorismeDeFirma;
  }

  public void setMapOfValuesForAlgorismeDeFirma(Map<String, String> mapOfValuesForAlgorismeDeFirma) {
    this.mapOfValuesForAlgorismeDeFirma = mapOfValuesForAlgorismeDeFirma;
  }



  private Map<String, String> mapOfValuesForModeDeFirma;

  public Map<String, String> getMapOfValuesForModeDeFirma() {
    return this.mapOfValuesForModeDeFirma;
  }

  public void setMapOfValuesForModeDeFirma(Map<String, String> mapOfValuesForModeDeFirma) {
    this.mapOfValuesForModeDeFirma = mapOfValuesForModeDeFirma;
  }



  private Map<String, String> mapOfPluginForPluginFirmaServidorID;

  public Map<String, String> getMapOfPluginForPluginFirmaServidorID() {
    return this.mapOfPluginForPluginFirmaServidorID;
  }

  public void setMapOfPluginForPluginFirmaServidorID(Map<String, String> mapOfPluginForPluginFirmaServidorID) {
    this.mapOfPluginForPluginFirmaServidorID = mapOfPluginForPluginFirmaServidorID;
  }



  private Map<String, String> mapOfValuesForUpgradeSignFormat;

  public Map<String, String> getMapOfValuesForUpgradeSignFormat() {
    return this.mapOfValuesForUpgradeSignFormat;
  }

  public void setMapOfValuesForUpgradeSignFormat(Map<String, String> mapOfValuesForUpgradeSignFormat) {
    this.mapOfValuesForUpgradeSignFormat = mapOfValuesForUpgradeSignFormat;
  }



  private Map<String, String> mapOfValuesForPoliticaSegellatDeTemps;

  public Map<String, String> getMapOfValuesForPoliticaSegellatDeTemps() {
    return this.mapOfValuesForPoliticaSegellatDeTemps;
  }

  public void setMapOfValuesForPoliticaSegellatDeTemps(Map<String, String> mapOfValuesForPoliticaSegellatDeTemps) {
    this.mapOfValuesForPoliticaSegellatDeTemps = mapOfValuesForPoliticaSegellatDeTemps;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
