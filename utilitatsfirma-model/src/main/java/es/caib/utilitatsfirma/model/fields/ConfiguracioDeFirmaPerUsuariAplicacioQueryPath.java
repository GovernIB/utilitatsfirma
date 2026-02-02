
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ConfiguracioDeFirmaPerUsuariAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ConfiguracioDeFirmaPerUsuariAplicacioQueryPath() {
  }

  protected ConfiguracioDeFirmaPerUsuariAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CONFIGSPERUSRAPPID() {
    return new LongField(getQueryPath(), ConfiguracioDeFirmaPerUsuariAplicacioFields.CONFIGSPERUSRAPPID);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOID);
  }

  public LongField USUARIAPLICACIOCONFIGID() {
    return new LongField(getQueryPath(), ConfiguracioDeFirmaPerUsuariAplicacioFields.USUARIAPLICACIOCONFIGID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ConfiguracioDeFirmaPerUsuariAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ConfiguracioDeFirmaPerUsuariAplicacioQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

  public UsuariAplicacioConfiguracioQueryPath USUARIAPLICACIOCONFIGURACIO() {
    return new UsuariAplicacioConfiguracioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ConfiguracioDeFirmaPerUsuariAplicacioQueryPath.this.getQueryPath() + "usuariAplicacioConfiguracio" + ".";
      }
    });
  }

}
