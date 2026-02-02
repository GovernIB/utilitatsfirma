
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ValidacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "suf_validacio";


  public static final String _TABLE_MODEL = "validacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField VALIDACIOID = new LongField(_TABLE_MODEL, "validacioID", "validacioid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField SIGNATURAID = new LongField(_TABLE_MODEL, "signaturaID", "signaturaid");
	 public static final LongField DETACHEDID = new LongField(_TABLE_MODEL, "detachedID", "detachedid");
	 public static final IntegerField RESULTAT = new IntegerField(_TABLE_MODEL, "resultat", "resultat");
	 public static final StringField INFORESULTAT = new StringField(_TABLE_MODEL, "infoResultat", "inforesultat");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");


  public static final Field<?>[] ALL_VALIDACIO_FIELDS = {
    VALIDACIOID,
    NOM,
    SIGNATURAID,
    DETACHEDID,
    RESULTAT,
    INFORESULTAT,
    DATAINICI,
    DATAFI
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
VALIDACIOID
  };
}
