
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.utilitatsfirma.ejb.ConfiguracioDeFirmaPerUsuariAplicacioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.utilitatsfirma.model.fields.ConfiguracioDeFirmaPerUsuariAplicacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ConfiguracioDeFirmaPerUsuariAplicacioRefList extends RefListBase implements ConfiguracioDeFirmaPerUsuariAplicacioFields {

    @EJB(mappedName = ConfiguracioDeFirmaPerUsuariAplicacioService.JNDI_NAME)
    private ConfiguracioDeFirmaPerUsuariAplicacioService configuracioDeFirmaPerUsuariAplicacioEjb;

    public ConfiguracioDeFirmaPerUsuariAplicacioRefList(ConfiguracioDeFirmaPerUsuariAplicacioRefList __clone) {
        super(__clone);
        this.configuracioDeFirmaPerUsuariAplicacioEjb = __clone.configuracioDeFirmaPerUsuariAplicacioEjb;
    }

    public ConfiguracioDeFirmaPerUsuariAplicacioRefList() {
        setSelects(new Select<?>[] { USUARIAPLICACIOID.select, USUARIAPLICACIOCONFIGID.select });
    }

    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
        Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
        List<StringKeyValue> list = configuracioDeFirmaPerUsuariAplicacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
    }
}
