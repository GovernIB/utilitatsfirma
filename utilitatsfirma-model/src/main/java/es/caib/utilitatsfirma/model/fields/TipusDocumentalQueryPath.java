
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusDocumentalQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusDocumentalQueryPath() {
  }

  protected TipusDocumentalQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIPUSDOCUMENTALID() {
    return new LongField(getQueryPath(), TipusDocumentalFields.TIPUSDOCUMENTALID);
  }

  public LongField PARETIPUSDOCUMENTALID() {
    return new LongField(getQueryPath(), TipusDocumentalFields.PARETIPUSDOCUMENTALID);
  }

  public StringField NOMCATALA() {
    return new StringField(getQueryPath(), TipusDocumentalFields.NOMCATALA);
  }

  public StringField NOMCASTELLA() {
    return new StringField(getQueryPath(), TipusDocumentalFields.NOMCASTELLA);
  }

  public StringField DESCRIPCIOCATALA() {
    return new StringField(getQueryPath(), TipusDocumentalFields.DESCRIPCIOCATALA);
  }

  public StringField DESCRIPCIOCASTELLA() {
    return new StringField(getQueryPath(), TipusDocumentalFields.DESCRIPCIOCASTELLA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusDocumentalFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
