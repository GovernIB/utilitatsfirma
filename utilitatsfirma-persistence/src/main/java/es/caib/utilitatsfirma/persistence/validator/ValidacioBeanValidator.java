package es.caib.utilitatsfirma.persistence.validator;

import es.caib.utilitatsfirma.persistence.ValidacioJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ValidacioBeanValidator 
      extends AbstractBeanValidator<ValidacioJPA> {


  // EJB's
  protected final es.caib.utilitatsfirma.model.dao.IValidacioManager __validacioManager;


  public final ValidacioValidator<ValidacioJPA> _validator;


  public ValidacioBeanValidator(es.caib.utilitatsfirma.model.dao.IValidacioManager __validacioManager) { 
    this.__validacioManager = __validacioManager;
    _validator = new ValidacioValidator<ValidacioJPA>();
  }

  public ValidacioBeanValidator(ValidacioValidator<ValidacioJPA> _validator,
     es.caib.utilitatsfirma.model.dao.IValidacioManager __validacioManager) {
    this.__validacioManager = __validacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ValidacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ValidacioJPA> _bvr_ = new BeanValidatorResult<ValidacioJPA>();
    _validator.validate(_bvr_, target, isNou, __validacioManager);
    return _bvr_.getErrors();
  }
}
