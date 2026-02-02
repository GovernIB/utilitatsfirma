
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PluginQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PluginQueryPath() {
  }

  protected PluginQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PLUGINID() {
    return new LongField(getQueryPath(), PluginFields.PLUGINID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), PluginFields.NOMID);
  }

  public LongField DESCRIPCIOCURTAID() {
    return new LongField(getQueryPath(), PluginFields.DESCRIPCIOCURTAID);
  }

  public StringField CLASSE() {
    return new StringField(getQueryPath(), PluginFields.CLASSE);
  }

  public StringField PROPERTIESADMIN() {
    return new StringField(getQueryPath(), PluginFields.PROPERTIESADMIN);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), PluginFields.ACTIU);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), PluginFields.TIPUS);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), PluginFields.CODI);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), PluginFields.ORDRE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PluginFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIOS() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracios" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

  public TraduccioQueryPath DESCRIPCIOCURTA() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PluginQueryPath.this.getQueryPath() + "descripcioCurta" + ".";
      }
    });
  }

}
