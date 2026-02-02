
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariAplicacioConfiguracioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "suf_usuariaplicacioconfig";


  public static final String _TABLE_MODEL = "usuariAplicacioConfiguracio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIAPLICACIOCONFIGID = new LongField(_TABLE_MODEL, "usuariAplicacioConfigID", "usuariaplicacioconfigid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final IntegerField USPOLITICADEFIRMA = new IntegerField(_TABLE_MODEL, "usPoliticaDeFirma", "uspoliticadefirma");
	 public static final StringField POLICYIDENTIFIER = new StringField(_TABLE_MODEL, "policyIdentifier", "policyidentifier");
	 public static final StringField POLICYIDENTIFIERHASH = new StringField(_TABLE_MODEL, "policyIdentifierHash", "policyidentifierhash");
	 public static final StringField POLICYIDENTIFIERHASHALGORITHM = new StringField(_TABLE_MODEL, "policyIdentifierHashAlgorithm", "policyidentifierhashalgorithm");
	 public static final StringField POLICYURLDOCUMENT = new StringField(_TABLE_MODEL, "policyUrlDocument", "policyurldocument");
	 public static final IntegerField TIPUSOPERACIOFIRMA = new IntegerField(_TABLE_MODEL, "tipusOperacioFirma", "tipusoperaciofirma");
	 public static final IntegerField TIPUSFIRMA = new IntegerField(_TABLE_MODEL, "tipusFirma", "tipusfirmaid");
	 public static final IntegerField ALGORISMEDEFIRMA = new IntegerField(_TABLE_MODEL, "algorismeDeFirma", "algorismedefirmaid");
	 public static final IntegerField MODEDEFIRMA = new IntegerField(_TABLE_MODEL, "modeDeFirma", "modedefirma");
	 public static final BooleanField COMPROVARNIFFIRMA = new BooleanField(_TABLE_MODEL, "comprovarNifFirma", "comprovarniffirma");
	 public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkCanviatDocFirmat", "checkcanviatdocfirmat");
	 public static final BooleanField VALIDARFIRMA = new BooleanField(_TABLE_MODEL, "validarFirma", "validarfirma");
	 public static final LongField PLUGINFIRMASERVIDORID = new LongField(_TABLE_MODEL, "pluginFirmaServidorID", "pluginfirmaservidorid");
	 public static final IntegerField UPGRADESIGNFORMAT = new IntegerField(_TABLE_MODEL, "upgradeSignFormat", "upgradesignformat");
	 public static final IntegerField POLITICASEGELLATDETEMPS = new IntegerField(_TABLE_MODEL, "politicaSegellatDeTemps", "politicaSegellatDeTemps");


  public static final Field<?>[] ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS = {
    USUARIAPLICACIOCONFIGID,
    NOM,
    USPOLITICADEFIRMA,
    POLICYIDENTIFIER,
    POLICYIDENTIFIERHASH,
    POLICYIDENTIFIERHASHALGORITHM,
    POLICYURLDOCUMENT,
    TIPUSOPERACIOFIRMA,
    TIPUSFIRMA,
    ALGORISMEDEFIRMA,
    MODEDEFIRMA,
    COMPROVARNIFFIRMA,
    CHECKCANVIATDOCFIRMAT,
    VALIDARFIRMA,
    PLUGINFIRMASERVIDORID,
    UPGRADESIGNFORMAT,
    POLITICASEGELLATDETEMPS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIAPLICACIOCONFIGID
  };
}
