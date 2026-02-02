package es.caib.utilitatsfirma.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.utilitatsfirma.model.fields.PluginFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariAplicacioConfiguracioValidator<I extends UsuariAplicacioConfiguracio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements UsuariAplicacioConfiguracioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public UsuariAplicacioConfiguracioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.utilitatsfirma.model.dao.IPluginManager __pluginManager
    ,es.caib.utilitatsfirma.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USPOLITICADEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USPOLITICADEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSOPERACIOFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSOPERACIOFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ALGORISMEDEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ALGORISMEDEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MODEDEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MODEDEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,COMPROVARNIFFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COMPROVARNIFFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CHECKCANVIATDOCFIRMAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CHECKCANVIATDOCFIRMAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VALIDARFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALIDARFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLUGINFIRMASERVIDORID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINFIRMASERVIDORID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICASEGELLATDETEMPS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICASEGELLATDETEMPS)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(POLICYIDENTIFIER) == 0) {
      java.lang.String __policyidentifier = __target__.getPolicyIdentifier();
      if (__policyidentifier!= null && __policyidentifier.length() > 100) {
        __vr.rejectValue(POLICYIDENTIFIER, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIER)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(POLICYIDENTIFIERHASH) == 0) {
      java.lang.String __policyidentifierhash = __target__.getPolicyIdentifierHash();
      if (__policyidentifierhash!= null && __policyidentifierhash.length() > 256) {
        __vr.rejectValue(POLICYIDENTIFIERHASH, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIERHASH)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(POLICYIDENTIFIERHASHALGORITHM) == 0) {
      java.lang.String __policyidentifierhashalgorithm = __target__.getPolicyIdentifierHashAlgorithm();
      if (__policyidentifierhashalgorithm!= null && __policyidentifierhashalgorithm.length() > 50) {
        __vr.rejectValue(POLICYIDENTIFIERHASHALGORITHM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIERHASHALGORITHM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(POLICYURLDOCUMENT) == 0) {
      java.lang.String __policyurldocument = __target__.getPolicyUrlDocument();
      if (__policyurldocument!= null && __policyurldocument.length() > 255) {
        __vr.rejectValue(POLICYURLDOCUMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYURLDOCUMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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
    if (__vr.getFieldErrorCount(PLUGINFIRMASERVIDORID) == 0) {
      java.lang.Long __pluginfirmaservidorid = __target__.getPluginFirmaServidorID();
      Long __count_ = null;
      try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginfirmaservidorid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PLUGINFIRMASERVIDORID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginfirmaservidorid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}