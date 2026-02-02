
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariAplicacioConfiguracioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariAplicacioConfiguracioQueryPath() {
  }

  protected UsuariAplicacioConfiguracioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIAPLICACIOCONFIGID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.NOM);
  }

  public IntegerField USPOLITICADEFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.USPOLITICADEFIRMA);
  }

  public StringField POLICYIDENTIFIER() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER);
  }

  public StringField POLICYIDENTIFIERHASH() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH);
  }

  public StringField POLICYIDENTIFIERHASHALGORITHM() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM);
  }

  public StringField POLICYURLDOCUMENT() {
    return new StringField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT);
  }

  public IntegerField TIPUSOPERACIOFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA);
  }

  public IntegerField TIPUSFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.TIPUSFIRMA);
  }

  public IntegerField ALGORISMEDEFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMA);
  }

  public IntegerField MODEDEFIRMA() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.MODEDEFIRMA);
  }

  public BooleanField COMPROVARNIFFIRMA() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA);
  }

  public BooleanField CHECKCANVIATDOCFIRMAT() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT);
  }

  public BooleanField VALIDARFIRMA() {
    return new BooleanField(getQueryPath(), UsuariAplicacioConfiguracioFields.VALIDARFIRMA);
  }

  public LongField PLUGINFIRMASERVIDORID() {
    return new LongField(getQueryPath(), UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID);
  }

  public IntegerField UPGRADESIGNFORMAT() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.UPGRADESIGNFORMAT);
  }

  public IntegerField POLITICASEGELLATDETEMPS() {
    return new IntegerField(getQueryPath(), UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariAplicacioConfiguracioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO1IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio1ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO2IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio2ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO3IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio3ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO4IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio4ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilDeFirmaQueryPath PERFILDEFIRMA_USRAPPCONFIGURACIO5IDS() {
    return new PerfilDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "perfilDeFirma_usrappconfiguracio5ids" + ".";
      }
    });
  }
*/

  public PluginQueryPath PLUGIN() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioConfiguracioQueryPath.this.getQueryPath() + "plugin" + ".";
      }
    });
  }

}
