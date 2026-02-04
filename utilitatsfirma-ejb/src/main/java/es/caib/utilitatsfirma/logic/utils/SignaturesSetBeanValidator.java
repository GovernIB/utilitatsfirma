package es.caib.utilitatsfirma.logic.utils;

import java.util.List;
import java.util.Map;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;

import es.caib.utilitatsfirma.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;

/**
 * 
 * @author anadal
 *
 */
public class SignaturesSetBeanValidator extends AbstractBeanValidator<PassarelaSignaturesSet> {

    protected final SignaturesSetValidator<PassarelaSignaturesSet> _validator;

    protected AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb;

    protected Map<String, UsuariAplicacioConfiguracioJPA> configBySignID;


    public SignaturesSetBeanValidator(SignaturesSetValidator<PassarelaSignaturesSet> _validator,
            AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) {
        this._validator = _validator;
        this.passarelaDeFirmaEjb = passarelaDeFirmaEjb;
        this.configBySignID = configBySignID;
    }

    @Override
    public List<I18NFieldError> validate(PassarelaSignaturesSet target, boolean isNou) throws I18NException {
        BeanValidatorResult<PassarelaSignaturesSet> _bvr_ = new BeanValidatorResult<PassarelaSignaturesSet>();
        _validator.validate(_bvr_, target, isNou, this.passarelaDeFirmaEjb, configBySignID);
        return _bvr_.getErrors();
    }

}
