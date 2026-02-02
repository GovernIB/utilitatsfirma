
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariAplicacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariAplicacioQueryPath() {
  }

  protected UsuariAplicacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.USUARIAPLICACIOID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.DESCRIPCIO);
  }

  public StringField EMAILADMIN() {
    return new StringField(getQueryPath(), UsuariAplicacioFields.EMAILADMIN);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), UsuariAplicacioFields.ACTIU);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariAplicacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PerfilsPerUsuariAplicacioQueryPath PERFILSPERUSUARIAPLICACIOS() {
    return new PerfilsPerUsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariAplicacioQueryPath.this.getQueryPath() + "perfilsPerUsuariAplicacios" + ".";
      }
    });
  }
*/

}
