
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusDocumentalFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "suf_tipusdocumental";


  public static final String _TABLE_MODEL = "tipusDocumental";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIPUSDOCUMENTALID = new LongField(_TABLE_MODEL, "tipusDocumentalID", "tipusdocumentalid");  // PK
	 public static final LongField PARETIPUSDOCUMENTALID = new LongField(_TABLE_MODEL, "pareTipusDocumentalID", "paretipusdocumentalid");
	 public static final StringField NOMCATALA = new StringField(_TABLE_MODEL, "nomCatala", "nomcatala");
	 public static final StringField NOMCASTELLA = new StringField(_TABLE_MODEL, "nomCastella", "nomcastella");
	 public static final StringField DESCRIPCIOCATALA = new StringField(_TABLE_MODEL, "descripcioCatala", "descripciocatala");
	 public static final StringField DESCRIPCIOCASTELLA = new StringField(_TABLE_MODEL, "descripcioCastella", "descripciocastella");


  public static final Field<?>[] ALL_TIPUSDOCUMENTAL_FIELDS = {
    TIPUSDOCUMENTALID,
    PARETIPUSDOCUMENTALID,
    NOMCATALA,
    NOMCASTELLA,
    DESCRIPCIOCATALA,
    DESCRIPCIOCASTELLA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSDOCUMENTALID
  };
}
