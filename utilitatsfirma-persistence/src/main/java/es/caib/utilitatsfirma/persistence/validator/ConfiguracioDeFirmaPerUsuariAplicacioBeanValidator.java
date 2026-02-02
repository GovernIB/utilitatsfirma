package es.caib.utilitatsfirma.persistence.validator;

import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioJPA;
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
public class ConfiguracioDeFirmaPerUsuariAplicacioBeanValidator 
      extends AbstractBeanValidator<ConfiguracioDeFirmaPerUsuariAplicacioJPA> {


  // EJB's
  protected final es.caib.utilitatsfirma.model.dao.IConfiguracioDeFirmaPerUsuariAplicacioManager __configuracioDeFirmaPerUsuariAplicacioManager;

  protected final es.caib.utilitatsfirma.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;

  protected final es.caib.utilitatsfirma.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager;


  public final ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacioJPA> _validator;


  public ConfiguracioDeFirmaPerUsuariAplicacioBeanValidator(es.caib.utilitatsfirma.model.dao.IConfiguracioDeFirmaPerUsuariAplicacioManager __configuracioDeFirmaPerUsuariAplicacioManager,
     es.caib.utilitatsfirma.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.utilitatsfirma.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) { 
    this.__configuracioDeFirmaPerUsuariAplicacioManager = __configuracioDeFirmaPerUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariAplicacioConfiguracioManager = __usuariAplicacioConfiguracioManager;
    _validator = new ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacioJPA>();
  }

  public ConfiguracioDeFirmaPerUsuariAplicacioBeanValidator(ConfiguracioDeFirmaPerUsuariAplicacioValidator<ConfiguracioDeFirmaPerUsuariAplicacioJPA> _validator,
     es.caib.utilitatsfirma.model.dao.IConfiguracioDeFirmaPerUsuariAplicacioManager __configuracioDeFirmaPerUsuariAplicacioManager,
     es.caib.utilitatsfirma.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.utilitatsfirma.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {
    this.__configuracioDeFirmaPerUsuariAplicacioManager = __configuracioDeFirmaPerUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariAplicacioConfiguracioManager = __usuariAplicacioConfiguracioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ConfiguracioDeFirmaPerUsuariAplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ConfiguracioDeFirmaPerUsuariAplicacioJPA> _bvr_ = new BeanValidatorResult<ConfiguracioDeFirmaPerUsuariAplicacioJPA>();
    _validator.validate(_bvr_, target, isNou, __configuracioDeFirmaPerUsuariAplicacioManager, __usuariAplicacioManager, __usuariAplicacioConfiguracioManager);
    return _bvr_.getErrors();
  }
}
