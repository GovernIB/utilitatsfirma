
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.utilitatsfirma.back.form.UtilitatsFirmaBaseFilterForm;

import es.caib.utilitatsfirma.model.fields.TipusDocumentalFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusDocumentalFilterForm extends UtilitatsFirmaBaseFilterForm implements TipusDocumentalFields {

  private java.lang.Long tipusDocumentalIDDesde;

  public java.lang.Long getTipusDocumentalIDDesde() {
    return this.tipusDocumentalIDDesde;
  }

  public void setTipusDocumentalIDDesde(java.lang.Long tipusDocumentalIDDesde) {
    this.tipusDocumentalIDDesde = tipusDocumentalIDDesde;
  }


  private java.lang.Long tipusDocumentalIDFins;

  public java.lang.Long getTipusDocumentalIDFins() {
    return this.tipusDocumentalIDFins;
  }

  public void setTipusDocumentalIDFins(java.lang.Long tipusDocumentalIDFins) {
    this.tipusDocumentalIDFins = tipusDocumentalIDFins;
  }


  private java.lang.Long pareTipusDocumentalIDDesde;

  public java.lang.Long getPareTipusDocumentalIDDesde() {
    return this.pareTipusDocumentalIDDesde;
  }

  public void setPareTipusDocumentalIDDesde(java.lang.Long pareTipusDocumentalIDDesde) {
    this.pareTipusDocumentalIDDesde = pareTipusDocumentalIDDesde;
  }


  private java.lang.Long pareTipusDocumentalIDFins;

  public java.lang.Long getPareTipusDocumentalIDFins() {
    return this.pareTipusDocumentalIDFins;
  }

  public void setPareTipusDocumentalIDFins(java.lang.Long pareTipusDocumentalIDFins) {
    this.pareTipusDocumentalIDFins = pareTipusDocumentalIDFins;
  }


  private java.lang.String nomCatala;

  public java.lang.String getNomCatala() {
    return this.nomCatala;
  }

  public void setNomCatala(java.lang.String nomCatala) {
    this.nomCatala = nomCatala;
  }


  private java.lang.String nomCastella;

  public java.lang.String getNomCastella() {
    return this.nomCastella;
  }

  public void setNomCastella(java.lang.String nomCastella) {
    this.nomCastella = nomCastella;
  }


  private java.lang.String descripcioCatala;

  public java.lang.String getDescripcioCatala() {
    return this.descripcioCatala;
  }

  public void setDescripcioCatala(java.lang.String descripcioCatala) {
    this.descripcioCatala = descripcioCatala;
  }


  private java.lang.String descripcioCastella;

  public java.lang.String getDescripcioCastella() {
    return this.descripcioCastella;
  }

  public void setDescripcioCastella(java.lang.String descripcioCastella) {
    this.descripcioCastella = descripcioCastella;
  }


  public TipusDocumentalFilterForm() {
  }
  
  public TipusDocumentalFilterForm(TipusDocumentalFilterForm __toClone) {
    super(__toClone);
    this.tipusDocumentalIDDesde = __toClone.tipusDocumentalIDDesde;
    this.tipusDocumentalIDFins = __toClone.tipusDocumentalIDFins;
    this.pareTipusDocumentalIDDesde = __toClone.pareTipusDocumentalIDDesde;
    this.pareTipusDocumentalIDFins = __toClone.pareTipusDocumentalIDFins;
    this.nomCatala = __toClone.nomCatala;
    this.nomCastella = __toClone.nomCastella;
    this.descripcioCatala = __toClone.descripcioCatala;
    this.descripcioCastella = __toClone.descripcioCastella;
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
