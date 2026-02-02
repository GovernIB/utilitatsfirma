package es.caib.utilitatsfirma.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.utilitatsfirma.model.entity.ConfiguracioDeFirmaPerUsuariAplicacio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.utilitatsfirma.model.fields.ConfiguracioDeFirmaPerUsuariAplicacioFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ConfiguracioDeFirmaPerUsuariAplicacioValidator<I extends ConfiguracioDeFirmaPerUsuariAplicacio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements ConfiguracioDeFirmaPerUsuariAplicacioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public ConfiguracioDeFirmaPerUsuariAplicacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.utilitatsfirma.model.dao.IConfiguracioDeFirmaPerUsuariAplicacioManager __configuracioDeFirmaPerUsuariAplicacioManager
    ,es.caib.utilitatsfirma.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
    ,es.caib.utilitatsfirma.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOCONFIGID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOCONFIGID)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = __target__.getUsuariAplicacioID();
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 50) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = __target__.getUsuariAplicacioID();
      Long __count_ = null;
      try { __count_ = __usuariAplicacioManager.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(__usuariaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIAPLICACIOCONFIGID) == 0) {
      java.lang.Long __usuariaplicacioconfigid = __target__.getUsuariAplicacioConfigID();
      Long __count_ = null;
      try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__usuariaplicacioconfigid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIAPLICACIOCONFIGID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioconfigid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}