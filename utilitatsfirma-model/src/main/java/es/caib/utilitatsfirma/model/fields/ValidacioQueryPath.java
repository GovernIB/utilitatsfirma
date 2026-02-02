
package es.caib.utilitatsfirma.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ValidacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ValidacioQueryPath() {
  }

  protected ValidacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField VALIDACIOID() {
    return new LongField(getQueryPath(), ValidacioFields.VALIDACIOID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), ValidacioFields.NOM);
  }

  public LongField SIGNATURAID() {
    return new LongField(getQueryPath(), ValidacioFields.SIGNATURAID);
  }

  public LongField DETACHEDID() {
    return new LongField(getQueryPath(), ValidacioFields.DETACHEDID);
  }

  public IntegerField RESULTAT() {
    return new IntegerField(getQueryPath(), ValidacioFields.RESULTAT);
  }

  public StringField INFORESULTAT() {
    return new StringField(getQueryPath(), ValidacioFields.INFORESULTAT);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), ValidacioFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), ValidacioFields.DATAFI);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ValidacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FitxerQueryPath SIGNATURA() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ValidacioQueryPath.this.getQueryPath() + "signatura" + ".";
      }
    });
  }

  public FitxerQueryPath DETACHED() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ValidacioQueryPath.this.getQueryPath() + "detached" + ".";
      }
    });
  }

}
