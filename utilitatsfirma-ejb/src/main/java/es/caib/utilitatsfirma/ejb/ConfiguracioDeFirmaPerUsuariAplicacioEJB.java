
package es.caib.utilitatsfirma.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.utilitatsfirma.model.entity.ConfiguracioDeFirmaPerUsuariAplicacio;
import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioJPA;
import es.caib.utilitatsfirma.persistence.ConfiguracioDeFirmaPerUsuariAplicacioJPAManager;

import es.caib.utilitatsfirma.commons.utils.Constants;

@Stateless
public class ConfiguracioDeFirmaPerUsuariAplicacioEJB extends ConfiguracioDeFirmaPerUsuariAplicacioJPAManager implements ConfiguracioDeFirmaPerUsuariAplicacioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void delete(ConfiguracioDeFirmaPerUsuariAplicacio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public ConfiguracioDeFirmaPerUsuariAplicacio create(ConfiguracioDeFirmaPerUsuariAplicacio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public ConfiguracioDeFirmaPerUsuariAplicacio update(ConfiguracioDeFirmaPerUsuariAplicacio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public void deleteIncludingFiles(ConfiguracioDeFirmaPerUsuariAplicacio instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS, Constants.ROLE_EJB_WS_ACCESS})
    public ConfiguracioDeFirmaPerUsuariAplicacioJPA findByPrimaryKey(Long _ID_) {
        return (ConfiguracioDeFirmaPerUsuariAplicacioJPA)super.findByPrimaryKey(_ID_);
    }

}
