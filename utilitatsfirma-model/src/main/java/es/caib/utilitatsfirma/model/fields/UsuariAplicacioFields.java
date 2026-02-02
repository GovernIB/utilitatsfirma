
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariAplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "suf_usuariaplicacio";


  public static final String _TABLE_MODEL = "usuariAplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");  // PK
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField EMAILADMIN = new StringField(_TABLE_MODEL, "emailAdmin", "emailadmin");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");


  public static final Field<?>[] ALL_USUARIAPLICACIO_FIELDS = {
    USUARIAPLICACIOID,
    DESCRIPCIO,
    EMAILADMIN,
    ACTIU
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIAPLICACIOID
  };
}
