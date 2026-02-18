
package es.caib.utilitatsfirma.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.utilitatsfirma.ejb.TipusDocumentalService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.utilitatsfirma.model.fields.TipusDocumentalFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusDocumentalRefList extends RefListBase implements TipusDocumentalFields {

    @EJB(mappedName = TipusDocumentalService.JNDI_NAME)
    private TipusDocumentalService tipusDocumentalEjb;

    public TipusDocumentalRefList(TipusDocumentalRefList __clone) {
        super(__clone);
        this.tipusDocumentalEjb = __clone.tipusDocumentalEjb;
    }

    public TipusDocumentalRefList() {
        setSelects(new Select<?>[] { TIPUSDOCUMENTALID.select });
    }

    public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
        Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
        List<StringKeyValue> list = tipusDocumentalEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
    }
}
