
package es.caib.utilitatsfirma.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.utilitatsfirma.model.entity.*;
import es.caib.utilitatsfirma.model.fields.*;
import es.caib.utilitatsfirma.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UsuariAplicacioConfiguracioJPAManager
         extends AbstractJPAManager<UsuariAplicacioConfiguracio, Long>
         implements UsuariAplicacioConfiguracioIJPAManager, IUsuariAplicacioConfiguracioManager, UsuariAplicacioConfiguracioFields {



    public static final TableName<UsuariAplicacioConfiguracio> _TABLENAME =  new TableName<UsuariAplicacioConfiguracio>("UsuariAplicacioConfiguracioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public UsuariAplicacioConfiguracioJPAManager() {
    }

    protected UsuariAplicacioConfiguracioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return UsuariAplicacioConfiguracioJPA. class;
    }



    public TableName<UsuariAplicacioConfiguracio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public UsuariAplicacioConfiguracio[] listToArray(List<UsuariAplicacioConfiguracio> list)  {
        if(list == null) { return null; };
        return list.toArray(new UsuariAplicacioConfiguracio[list.size()]);
    };

    public UsuariAplicacioConfiguracio create( java.lang.String _nom_, int _usPoliticaDeFirma_, java.lang.String _policyIdentifier_, java.lang.String _policyIdentifierHash_, java.lang.String _policyIdentifierHashAlgorithm_, java.lang.String _policyUrlDocument_, int _tipusOperacioFirma_, int _tipusFirma_, int _algorismeDeFirma_, int _modeDeFirma_, boolean _comprovarNifFirma_, boolean _checkCanviatDocFirmat_, boolean _validarFirma_, long _pluginFirmaServidorID_, java.lang.Integer _upgradeSignFormat_, int _politicaSegellatDeTemps_) throws I18NException {
        UsuariAplicacioConfiguracioJPA __bean =  new UsuariAplicacioConfiguracioJPA(_nom_,_usPoliticaDeFirma_,_policyIdentifier_,_policyIdentifierHash_,_policyIdentifierHashAlgorithm_,_policyUrlDocument_,_tipusOperacioFirma_,_tipusFirma_,_algorismeDeFirma_,_modeDeFirma_,_comprovarNifFirma_,_checkCanviatDocFirmat_,_validarFirma_,_pluginFirmaServidorID_,_upgradeSignFormat_,_politicaSegellatDeTemps_);
        return create(__bean);
    }



 public void delete(long _usuariAplicacioConfigID_) {
   delete(findByPrimaryKey(_usuariAplicacioConfigID_));
 }




    public UsuariAplicacioConfiguracio findByPrimaryKey(long _usuariAplicacioConfigID_) {
        return __em.find(UsuariAplicacioConfiguracioJPA.class, _usuariAplicacioConfigID_);  
    }
    @Override
    protected UsuariAplicacioConfiguracio getJPAInstance(UsuariAplicacioConfiguracio __bean) {
        return convertToJPA(__bean);
    }


    public static UsuariAplicacioConfiguracioJPA convertToJPA(UsuariAplicacioConfiguracio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof UsuariAplicacioConfiguracioJPA) {
        return (UsuariAplicacioConfiguracioJPA)__bean;
      }
      
      return UsuariAplicacioConfiguracioJPA.toJPA(__bean);
    }


}