package es.caib.utilitatsfirma.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.utilitatsfirma.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.utilitatsfirma.persistence.validator.ConfiguracioDeFirmaPerUsuariAplicacioValidator;

import es.caib.utilitatsfirma.back.form.webdb.ConfiguracioDeFirmaPerUsuariAplicacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.utilitatsfirma.model.entity.ConfiguracioDeFirmaPerUsuariAplicacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ConfiguracioDeFirmaPerUsuariAplicacioWebValidator extends AbstractWebValidator<ConfiguracioDeFirmaPerUsuariAplicacioForm, ConfiguracioDeFirmaPerUsuariAplicacio>
     implements Validator, ConfiguracioDeFirmaPerUsuariAplicacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacio> validator = new ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.ConfiguracioDeFirmaPerUsuariAplicacioService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.ConfiguracioDeFirmaPerUsuariAplicacioService configuracioDeFirmaPerUsuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.UsuariAplicacioService usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;



  public ConfiguracioDeFirmaPerUsuariAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public ConfiguracioDeFirmaPerUsuariAplicacio getBeanOfForm(ConfiguracioDeFirmaPerUsuariAplicacioForm form) {
    return  form.getConfiguracioDeFirmaPerUsuariAplicacio();
  }

  @Override
  public Class<ConfiguracioDeFirmaPerUsuariAplicacioForm> getClassOfForm() {
    return ConfiguracioDeFirmaPerUsuariAplicacioForm.class;
  }

  @Override
  public void validate(ConfiguracioDeFirmaPerUsuariAplicacioForm __form, ConfiguracioDeFirmaPerUsuariAplicacio __bean, Errors errors) {

    WebValidationResult<ConfiguracioDeFirmaPerUsuariAplicacioForm> wvr;
    wvr = new WebValidationResult<ConfiguracioDeFirmaPerUsuariAplicacioForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(ConfiguracioDeFirmaPerUsuariAplicacioForm __form, ConfiguracioDeFirmaPerUsuariAplicacio __bean, Errors errors,
    WebValidationResult<ConfiguracioDeFirmaPerUsuariAplicacioForm> wvr, boolean isNou) {

    BeanValidatorResult<ConfiguracioDeFirmaPerUsuariAplicacio> __vr = new BeanValidatorResult<ConfiguracioDeFirmaPerUsuariAplicacio>();
    validator.validate(__vr, __bean,
      isNou, configuracioDeFirmaPerUsuariAplicacioEjb, usuariAplicacioEjb, usuariAplicacioConfiguracioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacio> getValidator() {
    return validator;
  }

  public void setValidator(ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacio> validator) {
    this.validator = validator;
  }

}