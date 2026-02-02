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
import es.caib.utilitatsfirma.persistence.validator.ValidacioValidator;

import es.caib.utilitatsfirma.back.form.webdb.ValidacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.utilitatsfirma.model.entity.Validacio;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ValidacioWebValidator extends AbstractWebValidator<ValidacioForm, Validacio>
     implements Validator, ValidacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ValidacioValidator<Validacio> validator = new ValidacioValidator<Validacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.utilitatsfirma.ejb.ValidacioService.JNDI_NAME)
  protected es.caib.utilitatsfirma.ejb.ValidacioService validacioEjb;



  public ValidacioWebValidator() {
    super();    
  }
  
  @Override
  public Validacio getBeanOfForm(ValidacioForm form) {
    return  form.getValidacio();
  }

  @Override
  public Class<ValidacioForm> getClassOfForm() {
    return ValidacioForm.class;
  }

  @Override
  public void validate(ValidacioForm __form, Validacio __bean, Errors errors) {

    WebValidationResult<ValidacioForm> wvr;
    wvr = new WebValidationResult<ValidacioForm>(errors);

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


  public void validate(ValidacioForm __form, Validacio __bean, Errors errors,
    WebValidationResult<ValidacioForm> wvr, boolean isNou) {

    BeanValidatorResult<Validacio> __vr = new BeanValidatorResult<Validacio>();
    validator.validate(__vr, __bean,
      isNou, validacioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(SIGNATURAID))){
            CommonsMultipartFile signaturaID = ((ValidacioForm)__form).getSignaturaID();
            if (signaturaID == null || signaturaID.isEmpty()) {
                errors.rejectValue(get(SIGNATURAID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(SIGNATURAID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ValidacioValidator<Validacio> getValidator() {
    return validator;
  }

  public void setValidator(ValidacioValidator<Validacio> validator) {
    this.validator = validator;
  }

}