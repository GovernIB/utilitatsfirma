
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.utilitatsfirma.ejb.ValidacioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.utilitatsfirma.model.fields.ValidacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ValidacioRefList extends RefListBase implements ValidacioFields {

    @EJB(mappedName = ValidacioService.JNDI_NAME)
    private ValidacioService validacioEjb;

    public ValidacioRefList(ValidacioRefList __clone) {
        super(__clone);
        this.validacioEjb = __clone.validacioEjb;
    }

    public ValidacioRefList() {
        setSelects(new Select<?>[] { NOM.select });
    }

    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
        Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
        List<StringKeyValue> list = validacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
    }
}
