
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseFilterForm;

import es.caib.utilitatsfirma.model.fields.ConfiguracioDeFirmaPerUsuariAplicacioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ConfiguracioDeFirmaPerUsuariAplicacioFilterForm extends UtilitatsFirmaBaseFilterForm implements ConfiguracioDeFirmaPerUsuariAplicacioFields {

  private java.lang.Long configsPerUsrAppIDDesde;

  public java.lang.Long getConfigsPerUsrAppIDDesde() {
    return this.configsPerUsrAppIDDesde;
  }

  public void setConfigsPerUsrAppIDDesde(java.lang.Long configsPerUsrAppIDDesde) {
    this.configsPerUsrAppIDDesde = configsPerUsrAppIDDesde;
  }


  private java.lang.Long configsPerUsrAppIDFins;

  public java.lang.Long getConfigsPerUsrAppIDFins() {
    return this.configsPerUsrAppIDFins;
  }

  public void setConfigsPerUsrAppIDFins(java.lang.Long configsPerUsrAppIDFins) {
    this.configsPerUsrAppIDFins = configsPerUsrAppIDFins;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


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


  public ConfiguracioDeFirmaPerUsuariAplicacioFilterForm() {
  }
  
  public ConfiguracioDeFirmaPerUsuariAplicacioFilterForm(ConfiguracioDeFirmaPerUsuariAplicacioFilterForm __toClone) {
    super(__toClone);
    this.configsPerUsrAppIDDesde = __toClone.configsPerUsrAppIDDesde;
    this.configsPerUsrAppIDFins = __toClone.configsPerUsrAppIDFins;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.usuariAplicacioConfigIDDesde = __toClone.usuariAplicacioConfigIDDesde;
    this.usuariAplicacioConfigIDFins = __toClone.usuariAplicacioConfigIDFins;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID = __toClone.mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;
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
  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }



  private Map<String, String> mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID() {
    return this.mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID(Map<String, String> mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID) {
    this.mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID = mapOfUsuariAplicacioConfiguracioForUsuariAplicacioConfigID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
