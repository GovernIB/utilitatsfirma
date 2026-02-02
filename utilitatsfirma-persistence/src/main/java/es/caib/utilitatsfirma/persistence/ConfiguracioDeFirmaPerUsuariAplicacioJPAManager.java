
package es.caib.utilitatsfirma.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.utilitatsfirma.model.entity.*;
import es.caib.utilitatsfirma.model.fields.*;
import es.caib.utilitatsfirma.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ConfiguracioDeFirmaPerUsuariAplicacioJPAManager
         extends AbstractJPAManager<ConfiguracioDeFirmaPerUsuariAplicacio, Long>
         implements ConfiguracioDeFirmaPerUsuariAplicacioIJPAManager, IConfiguracioDeFirmaPerUsuariAplicacioManager, ConfiguracioDeFirmaPerUsuariAplicacioFields {



    public static final TableName<ConfiguracioDeFirmaPerUsuariAplicacio> _TABLENAME =  new TableName<ConfiguracioDeFirmaPerUsuariAplicacio>("ConfiguracioDeFirmaPerUsuariAplicacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ConfiguracioDeFirmaPerUsuariAplicacioJPAManager() {
    }

    protected ConfiguracioDeFirmaPerUsuariAplicacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ConfiguracioDeFirmaPerUsuariAplicacioJPA. class;
    }



    public TableName<ConfiguracioDeFirmaPerUsuariAplicacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public ConfiguracioDeFirmaPerUsuariAplicacio[] listToArray(List<ConfiguracioDeFirmaPerUsuariAplicacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new ConfiguracioDeFirmaPerUsuariAplicacio[list.size()]);
    };

    public ConfiguracioDeFirmaPerUsuariAplicacio create( java.lang.String _usuariAplicacioID_, long _usuariAplicacioConfigID_) throws I18NException {
        ConfiguracioDeFirmaPerUsuariAplicacioJPA __bean =  new ConfiguracioDeFirmaPerUsuariAplicacioJPA(_usuariAplicacioID_,_usuariAplicacioConfigID_);
        return create(__bean);
    }



 public void delete(long _configsPerUsrAppID_) {
   delete(findByPrimaryKey(_configsPerUsrAppID_));
 }




    public ConfiguracioDeFirmaPerUsuariAplicacio findByPrimaryKey(long _configsPerUsrAppID_) {
        return __em.find(ConfiguracioDeFirmaPerUsuariAplicacioJPA.class, _configsPerUsrAppID_);  
    }
    @Override
    protected ConfiguracioDeFirmaPerUsuariAplicacio getJPAInstance(ConfiguracioDeFirmaPerUsuariAplicacio __bean) {
        return convertToJPA(__bean);
    }


    public static ConfiguracioDeFirmaPerUsuariAplicacioJPA convertToJPA(ConfiguracioDeFirmaPerUsuariAplicacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ConfiguracioDeFirmaPerUsuariAplicacioJPA) {
        return (ConfiguracioDeFirmaPerUsuariAplicacioJPA)__bean;
      }
      
      return ConfiguracioDeFirmaPerUsuariAplicacioJPA.toJPA(__bean);
    }


}