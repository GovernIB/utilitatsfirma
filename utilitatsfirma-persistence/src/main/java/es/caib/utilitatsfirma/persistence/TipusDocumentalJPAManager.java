
package es.caib.utilitatsfirma.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.utilitatsfirma.model.entity.*;
import es.caib.utilitatsfirma.model.fields.*;
import es.caib.utilitatsfirma.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TipusDocumentalJPAManager
         extends AbstractJPAManager<TipusDocumental, Long>
         implements TipusDocumentalIJPAManager, ITipusDocumentalManager, TipusDocumentalFields {



    public static final TableName<TipusDocumental> _TABLENAME =  new TableName<TipusDocumental>("TipusDocumentalJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TipusDocumentalJPAManager() {
    }

    protected TipusDocumentalJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TipusDocumentalJPA. class;
    }



    public TableName<TipusDocumental> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TipusDocumental[] listToArray(List<TipusDocumental> list)  {
        if(list == null) { return null; };
        return list.toArray(new TipusDocumental[list.size()]);
    };

    public TipusDocumental create( long _tipusDocumentalID_, java.lang.Long _pareTipusDocumentalID_, java.lang.String _nomCatala_, java.lang.String _nomCastella_, java.lang.String _descripcioCatala_, java.lang.String _descripcioCastella_) throws I18NException {
        TipusDocumentalJPA __bean =  new TipusDocumentalJPA(_tipusDocumentalID_,_pareTipusDocumentalID_,_nomCatala_,_nomCastella_,_descripcioCatala_,_descripcioCastella_);
        return create(__bean);
    }



 public void delete(long _tipusDocumentalID_) {
   delete(findByPrimaryKey(_tipusDocumentalID_));
 }




    public TipusDocumental findByPrimaryKey(long _tipusDocumentalID_) {
        return __em.find(TipusDocumentalJPA.class, _tipusDocumentalID_);  
    }
    @Override
    protected TipusDocumental getJPAInstance(TipusDocumental __bean) {
        return convertToJPA(__bean);
    }


    public static TipusDocumentalJPA convertToJPA(TipusDocumental __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TipusDocumentalJPA) {
        return (TipusDocumentalJPA)__bean;
      }
      
      return TipusDocumentalJPA.toJPA(__bean);
    }


}