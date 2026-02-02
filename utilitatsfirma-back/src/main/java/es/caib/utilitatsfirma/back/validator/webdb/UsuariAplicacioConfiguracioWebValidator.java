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
import es.caib.utilitatsfirma.persistence.validator.UsuariAplicacioConfiguracioValidator;

import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioConfiguracioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariAplicacioConfiguracioWebValidator extends AbstractWebValidator<UsuariAplicacioConfiguracioForm, UsuariAplicacioConfiguracio>
     implements Validator, UsuariAplicacioConfiguracioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio> validator = new UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.PluginService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;



  public UsuariAplicacioConfiguracioWebValidator() {
    super();    
  }
  
  @Override
  public UsuariAplicacioConfiguracio getBeanOfForm(UsuariAplicacioConfiguracioForm form) {
    return  form.getUsuariAplicacioConfiguracio();
  }

  @Override
  public Class<UsuariAplicacioConfiguracioForm> getClassOfForm() {
    return UsuariAplicacioConfiguracioForm.class;
  }

  @Override
  public void validate(UsuariAplicacioConfiguracioForm __form, UsuariAplicacioConfiguracio __bean, Errors errors) {

    WebValidationResult<UsuariAplicacioConfiguracioForm> wvr;
    wvr = new WebValidationResult<UsuariAplicacioConfiguracioForm>(errors);

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


  public void validate(UsuariAplicacioConfiguracioForm __form, UsuariAplicacioConfiguracio __bean, Errors errors,
    WebValidationResult<UsuariAplicacioConfiguracioForm> wvr, boolean isNou) {

    BeanValidatorResult<UsuariAplicacioConfiguracio> __vr = new BeanValidatorResult<UsuariAplicacioConfiguracio>();
    validator.validate(__vr, __bean,
      isNou, pluginEjb, usuariAplicacioConfiguracioEjb);

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

  public UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio> getValidator() {
    return validator;
  }

  public void setValidator(UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio> validator) {
    this.validator = validator;
  }

}