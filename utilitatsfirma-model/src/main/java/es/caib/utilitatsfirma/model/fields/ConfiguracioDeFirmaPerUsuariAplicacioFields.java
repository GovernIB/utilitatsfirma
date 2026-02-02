
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ConfiguracioDeFirmaPerUsuariAplicacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "suf_configsperusrapp";


  public static final String _TABLE_MODEL = "configuracioDeFirmaPerUsuariAplicacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CONFIGSPERUSRAPPID = new LongField(_TABLE_MODEL, "configsPerUsrAppID", "configsperusrappid");  // PK
	 public static final StringField USUARIAPLICACIOID = new StringField(_TABLE_MODEL, "usuariAplicacioID", "usuariaplicacioid");
	 public static final LongField USUARIAPLICACIOCONFIGID = new LongField(_TABLE_MODEL, "usuariAplicacioConfigID", "usuariaplicacioconfigid");


  public static final Field<?>[] ALL_CONFIGURACIODEFIRMAPERUSUARIAPLICACIO_FIELDS = {
    CONFIGSPERUSRAPPID,
    USUARIAPLICACIOID,
    USUARIAPLICACIOCONFIGID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CONFIGSPERUSRAPPID
  };
}
