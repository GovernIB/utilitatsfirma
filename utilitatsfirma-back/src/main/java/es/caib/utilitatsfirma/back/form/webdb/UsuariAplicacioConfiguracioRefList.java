
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariAplicacioConfiguracioRefList extends RefListBase implements UsuariAplicacioConfiguracioFields {

    @EJB(mappedName = UsuariAplicacioConfiguracioService.JNDI_NAME)
    private UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;

    public UsuariAplicacioConfiguracioRefList(UsuariAplicacioConfiguracioRefList __clone) {
        super(__clone);
        this.usuariAplicacioConfiguracioEjb = __clone.usuariAplicacioConfiguracioEjb;
    }

    public UsuariAplicacioConfiguracioRefList() {
        setSelects(new Select<?>[] { NOM.select });
    }

    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
        Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
        List<StringKeyValue> list = usuariAplicacioConfiguracioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
    }
}
