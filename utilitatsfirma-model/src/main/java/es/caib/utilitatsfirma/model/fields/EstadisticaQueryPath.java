
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstadisticaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstadisticaQueryPath() {
  }

  protected EstadisticaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTADISTICAID() {
    return new LongField(getQueryPath(), EstadisticaFields.ESTADISTICAID);
  }

  public TimestampField DATA() {
    return new TimestampField(getQueryPath(), EstadisticaFields.DATA);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), EstadisticaFields.TIPUS);
  }

  public DoubleField VALOR() {
    return new DoubleField(getQueryPath(), EstadisticaFields.VALOR);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), EstadisticaFields.USUARIAPLICACIOID);
  }

  public IntegerField ENTORN() {
    return new IntegerField(getQueryPath(), EstadisticaFields.ENTORN);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstadisticaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
