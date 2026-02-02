package es.caib.utilitatsfirma.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.utilitatsfirma.model.entity.Plugin;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.utilitatsfirma.model.fields.PluginFields;
import es.caib.utilitatsfirma.model.fields.TraduccioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PluginValidator<I extends Plugin>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PluginFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PluginValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.utilitatsfirma.model.dao.IPluginManager __pluginManager
    ,es.caib.utilitatsfirma.model.dao.ITraduccioManager __traduccioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOMID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DESCRIPCIOCURTAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIOCURTAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CLASSE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CLASSE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ORDRE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORDRE)));

    // Check size
    if (__vr.getFieldErrorCount(CLASSE) == 0) {
      java.lang.String __classe = __target__.getClasse();
      if (__classe!= null && __classe.length() > 255) {
        __vr.rejectValue(CLASSE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CLASSE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PROPERTIESADMIN) == 0) {
      java.lang.String __propertiesadmin = __target__.getPropertiesAdmin();
      if (__propertiesadmin!= null && __propertiesadmin.length() > 2147483647) {
        __vr.rejectValue(PROPERTIESADMIN, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROPERTIESADMIN)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = __target__.getCodi();
      if (__codi!= null && __codi.length() > 255) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODI) == 0) {
        java.lang.String __codi = __target__.getCodi();
        Long __count_ = null;
        try { __count_ = __pluginManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODI.equal(__codi))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }

      if (__vr.getFieldErrorCount(ORDRE) == 0) {
        java.lang.Integer __ordre = __target__.getOrdre();
        Long __count_ = null;
        try { __count_ = __pluginManager.count(org.fundaciobit.genapp.common.query.Where.AND(ORDRE.equal(__ordre))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ORDRE, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__ordre)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORDRE)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODI) == 0 && __vr.getFieldErrorCount(PLUGINID) == 0) {
        java.lang.String __codi = __target__.getCodi();
        java.lang.Long __pluginid = __target__.getPluginID();
        Long __count_ = null;
        try { __count_ = __pluginManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODI.equal(__codi), PLUGINID.notEqual(__pluginid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }

      if (__vr.getFieldErrorCount(ORDRE) == 0 && __vr.getFieldErrorCount(PLUGINID) == 0) {
        java.lang.Integer __ordre = __target__.getOrdre();
        java.lang.Long __pluginid = __target__.getPluginID();
        Long __count_ = null;
        try { __count_ = __pluginManager.count(org.fundaciobit.genapp.common.query.Where.AND(ORDRE.equal(__ordre), PLUGINID.notEqual(__pluginid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ORDRE, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__ordre)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORDRE)));
        }
      }

    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(NOMID) == 0) {
      java.lang.Long __nomid = __target__.getNomID();
      Long __count_ = null;
      try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__nomid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(NOMID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nomid)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIOCURTAID) == 0) {
      java.lang.Long __descripciocurtaid = __target__.getDescripcioCurtaID();
      Long __count_ = null;
      try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__descripciocurtaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(DESCRIPCIOCURTAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__descripciocurtaid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}