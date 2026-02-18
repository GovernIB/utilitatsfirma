package es.caib.utilitatsfirma.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.utilitatsfirma.model.entity.TipusDocumental;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.utilitatsfirma.model.fields.TipusDocumentalFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TipusDocumentalValidator<I extends TipusDocumental>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements TipusDocumentalFields {

    protected final Logger log = Logger.getLogger(getClass());


  public TipusDocumentalValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.utilitatsfirma.model.dao.ITipusDocumentalManager __tipusDocumentalManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSDOCUMENTALID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTALID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOMCATALA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMCATALA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOMCASTELLA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMCASTELLA)));

    // Check size
    if (__vr.getFieldErrorCount(NOMCATALA) == 0) {
      java.lang.String __nomcatala = __target__.getNomCatala();
      if (__nomcatala!= null && __nomcatala.length() > 255) {
        __vr.rejectValue(NOMCATALA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMCATALA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NOMCASTELLA) == 0) {
      java.lang.String __nomcastella = __target__.getNomCastella();
      if (__nomcastella!= null && __nomcastella.length() > 256) {
        __vr.rejectValue(NOMCASTELLA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMCASTELLA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIOCATALA) == 0) {
      java.lang.String __descripciocatala = __target__.getDescripcioCatala();
      if (__descripciocatala!= null && __descripciocatala.length() > 256) {
        __vr.rejectValue(DESCRIPCIOCATALA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIOCATALA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIOCASTELLA) == 0) {
      java.lang.String __descripciocastella = __target__.getDescripcioCastella();
      if (__descripciocastella!= null && __descripciocastella.length() > 256) {
        __vr.rejectValue(DESCRIPCIOCASTELLA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIOCASTELLA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
      if (__vr.getFieldErrorCount(TIPUSDOCUMENTALID) == 0) {
        java.lang.Long __tipusdocumentalid = __target__.getTipusDocumentalID();
        Long __count_ = null;
        try { __count_ = __tipusDocumentalManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCUMENTALID.equal(__tipusdocumentalid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCUMENTALID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentalid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTALID)));
        }
      }

    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}