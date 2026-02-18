package es.caib.utilitatsfirma.persistence.validator;

import es.caib.utilitatsfirma.persistence.TipusDocumentalJPA;
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
public class TipusDocumentalBeanValidator 
      extends AbstractBeanValidator<TipusDocumentalJPA> {


  // EJB's
  protected final es.caib.utilitatsfirma.model.dao.ITipusDocumentalManager __tipusDocumentalManager;


  public final TipusDocumentalValidator<TipusDocumentalJPA> _validator;


  public TipusDocumentalBeanValidator(es.caib.utilitatsfirma.model.dao.ITipusDocumentalManager __tipusDocumentalManager) { 
    this.__tipusDocumentalManager = __tipusDocumentalManager;
    _validator = new TipusDocumentalValidator<TipusDocumentalJPA>();
  }

  public TipusDocumentalBeanValidator(TipusDocumentalValidator<TipusDocumentalJPA> _validator,
     es.caib.utilitatsfirma.model.dao.ITipusDocumentalManager __tipusDocumentalManager) {
    this.__tipusDocumentalManager = __tipusDocumentalManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusDocumentalJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusDocumentalJPA> _bvr_ = new BeanValidatorResult<TipusDocumentalJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusDocumentalManager);
    return _bvr_.getErrors();
  }
}
