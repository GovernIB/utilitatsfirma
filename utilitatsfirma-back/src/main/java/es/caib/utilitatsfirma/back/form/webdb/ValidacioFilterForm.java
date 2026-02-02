
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseFilterForm;

import es.caib.utilitatsfirma.model.fields.ValidacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ValidacioFilterForm extends UtilitatsFirmaBaseFilterForm implements ValidacioFields {

  private java.lang.Long validacioIDDesde;

  public java.lang.Long getValidacioIDDesde() {
    return this.validacioIDDesde;
  }

  public void setValidacioIDDesde(java.lang.Long validacioIDDesde) {
    this.validacioIDDesde = validacioIDDesde;
  }


  private java.lang.Long validacioIDFins;

  public java.lang.Long getValidacioIDFins() {
    return this.validacioIDFins;
  }

  public void setValidacioIDFins(java.lang.Long validacioIDFins) {
    this.validacioIDFins = validacioIDFins;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.util.List<java.lang.Integer> resultatSelect;

  public java.util.List<java.lang.Integer> getResultatSelect() {
    return this.resultatSelect;
  }

  public void setResultatSelect(java.util.List<java.lang.Integer> resultatSelect) {
    this.resultatSelect = resultatSelect;
  }


  private java.lang.String infoResultat;

  public java.lang.String getInfoResultat() {
    return this.infoResultat;
  }

  public void setInfoResultat(java.lang.String infoResultat) {
    this.infoResultat = infoResultat;
  }


  private java.sql.Timestamp dataIniciDesde;

  public java.sql.Timestamp getDataIniciDesde() {
    return this.dataIniciDesde;
  }

  public void setDataIniciDesde(java.sql.Timestamp dataIniciDesde) {
    this.dataIniciDesde = dataIniciDesde;
  }


  private java.sql.Timestamp dataIniciFins;

  public java.sql.Timestamp getDataIniciFins() {
    return this.dataIniciFins;
  }

  public void setDataIniciFins(java.sql.Timestamp dataIniciFins) {
    this.dataIniciFins = dataIniciFins;
  }


  private java.sql.Timestamp dataFiDesde;

  public java.sql.Timestamp getDataFiDesde() {
    return this.dataFiDesde;
  }

  public void setDataFiDesde(java.sql.Timestamp dataFiDesde) {
    this.dataFiDesde = dataFiDesde;
  }


  private java.sql.Timestamp dataFiFins;

  public java.sql.Timestamp getDataFiFins() {
    return this.dataFiFins;
  }

  public void setDataFiFins(java.sql.Timestamp dataFiFins) {
    this.dataFiFins = dataFiFins;
  }


  public ValidacioFilterForm() {
  }
  
  public ValidacioFilterForm(ValidacioFilterForm __toClone) {
    super(__toClone);
    this.validacioIDDesde = __toClone.validacioIDDesde;
    this.validacioIDFins = __toClone.validacioIDFins;
    this.nom = __toClone.nom;
    this.resultatSelect = __toClone.resultatSelect;
    this.infoResultat = __toClone.infoResultat;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.mapOfValuesForResultat = __toClone.mapOfValuesForResultat;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { RESULTAT ,DATAFI }));
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
  private Map<String, String> mapOfValuesForResultat;

  public Map<String, String> getMapOfValuesForResultat() {
    return this.mapOfValuesForResultat;
  }

  public void setMapOfValuesForResultat(Map<String, String> mapOfValuesForResultat) {
    this.mapOfValuesForResultat = mapOfValuesForResultat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
