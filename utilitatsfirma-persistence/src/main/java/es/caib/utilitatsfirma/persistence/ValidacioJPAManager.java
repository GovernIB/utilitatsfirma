
package es.caib.utilitatsfirma.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.utilitatsfirma.model.entity.*;
import es.caib.utilitatsfirma.model.fields.*;
import es.caib.utilitatsfirma.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ValidacioJPAManager
         extends AbstractJPAManager<Validacio, Long>
         implements ValidacioIJPAManager, IValidacioManager, ValidacioFields {



    public static final TableName<Validacio> _TABLENAME =  new TableName<Validacio>("ValidacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ValidacioJPAManager() {
    }

    protected ValidacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ValidacioJPA. class;
    }



    public TableName<Validacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Validacio[] listToArray(List<Validacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Validacio[list.size()]);
    };

    public Validacio create( java.lang.String _nom_, long _signaturaID_, java.lang.Long _detachedID_, java.lang.Integer _resultat_, java.lang.String _infoResultat_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_) throws I18NException {
        ValidacioJPA __bean =  new ValidacioJPA(_nom_,_signaturaID_,_detachedID_,_resultat_,_infoResultat_,_dataInici_,_dataFi_);
        return create(__bean);
    }



 public void delete(long _validacioID_) {
   delete(findByPrimaryKey(_validacioID_));
 }




    public Validacio findByPrimaryKey(long _validacioID_) {
        return __em.find(ValidacioJPA.class, _validacioID_);  
    }
    @Override
    protected Validacio getJPAInstance(Validacio __bean) {
        return convertToJPA(__bean);
    }


    public static ValidacioJPA convertToJPA(Validacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ValidacioJPA) {
        return (ValidacioJPA)__bean;
      }
      
      return ValidacioJPA.toJPA(__bean);
    }


}