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
import es.caib.utilitatsfirma.persistence.validator.TipusDocumentalValidator;

import es.caib.utilitatsfirma.back.form.webdb.TipusDocumentalForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.utilitatsfirma.model.entity.TipusDocumental;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusDocumentalWebValidator extends AbstractWebValidator<TipusDocumentalForm, TipusDocumental>
     implements Validator, TipusDocumentalFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TipusDocumentalValidator<TipusDocumental> validator = new TipusDocumentalValidator<TipusDocumental>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.TipusDocumentalService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.TipusDocumentalService tipusDocumentalEjb;



  public TipusDocumentalWebValidator() {
    super();    
  }
  
  @Override
  public TipusDocumental getBeanOfForm(TipusDocumentalForm form) {
    return  form.getTipusDocumental();
  }

  @Override
  public Class<TipusDocumentalForm> getClassOfForm() {
    return TipusDocumentalForm.class;
  }

  @Override
  public void validate(TipusDocumentalForm __form, TipusDocumental __bean, Errors errors) {

    WebValidationResult<TipusDocumentalForm> wvr;
    wvr = new WebValidationResult<TipusDocumentalForm>(errors);

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


  public void validate(TipusDocumentalForm __form, TipusDocumental __bean, Errors errors,
    WebValidationResult<TipusDocumentalForm> wvr, boolean isNou) {

    BeanValidatorResult<TipusDocumental> __vr = new BeanValidatorResult<TipusDocumental>();
    validator.validate(__vr, __bean,
      isNou, tipusDocumentalEjb);

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

  public TipusDocumentalValidator<TipusDocumental> getValidator() {
    return validator;
  }

  public void setValidator(TipusDocumentalValidator<TipusDocumental> validator) {
    this.validator = validator;
  }

}