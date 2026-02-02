
package es.caib.utilitatsfirma.persistence;
import es.caib.utilitatsfirma.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "ValidacioJPA")
@Table(name = "suf_validacio" , indexes = { 
        @Index(name="suf_validacio_pk_i", columnList = "validacioid"),
        @Index(name="suf_validacio_signaturaid_fk_i", columnList = "signaturaid"),
        @Index(name="suf_validacio_detachedid_fk_i", columnList = "detachedid")})
@SequenceGenerator(name="VALIDACIO_SEQ", sequenceName="suf_validacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ValidacioJPA implements Validacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="VALIDACIO_SEQ")
    @Column(name="validacioid",nullable = false,length = 19)
    long validacioID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="signaturaid",nullable = false,length = 19)
    long signaturaID;

    @Column(name="detachedid",length = 19)
    java.lang.Long detachedID;

    @Column(name="resultat",length = 10)
    java.lang.Integer resultat;

    @Column(name="inforesultat",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String infoResultat;

    @Column(name="datainici",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataInici;

    @Column(name="datafi",length = 29,precision = 6)
    java.sql.Timestamp dataFi;



  /** Constructor Buit */
  public ValidacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public ValidacioJPA(long validacioID , java.lang.String nom , long signaturaID , java.lang.Long detachedID , java.lang.Integer resultat , java.lang.String infoResultat , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi) {
    this.validacioID=validacioID;
    this.nom=nom;
    this.signaturaID=signaturaID;
    this.detachedID=detachedID;
    this.resultat=resultat;
    this.infoResultat=infoResultat;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
}
  /** Constructor sense valors autoincrementals */
  public ValidacioJPA(java.lang.String nom , long signaturaID , java.lang.Long detachedID , java.lang.Integer resultat , java.lang.String infoResultat , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi) {
    this.nom=nom;
    this.signaturaID=signaturaID;
    this.detachedID=detachedID;
    this.resultat=resultat;
    this.infoResultat=infoResultat;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
}
  /** Constructor dels valors Not Null */
  public ValidacioJPA(long validacioID , java.lang.String nom , long signaturaID , java.sql.Timestamp dataInici) {
    this.validacioID=validacioID;
    this.nom=nom;
    this.signaturaID=signaturaID;
    this.dataInici=dataInici;
}
  public ValidacioJPA(Validacio __bean) {
    this.setValidacioID(__bean.getValidacioID());
    this.setNom(__bean.getNom());
    this.setSignaturaID(__bean.getSignaturaID());
    this.setDetachedID(__bean.getDetachedID());
    this.setResultat(__bean.getResultat());
    this.setInfoResultat(__bean.getInfoResultat());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    // Fitxer
    this.setSignatura(FitxerJPA.toJPA(__bean.getSignatura()));
    // Fitxer
    this.setDetached(FitxerJPA.toJPA(__bean.getDetached()));
	}

	public long getValidacioID() {
		return(validacioID);
	};
	public void setValidacioID(long _validacioID_) {
		this.validacioID = _validacioID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getSignaturaID() {
		return(signaturaID);
	};
	public void setSignaturaID(long _signaturaID_) {
		this.signaturaID = _signaturaID_;
	};

	public java.lang.Long getDetachedID() {
		return(detachedID);
	};
	public void setDetachedID(java.lang.Long _detachedID_) {
		this.detachedID = _detachedID_;
	};

	public java.lang.Integer getResultat() {
		return(resultat);
	};
	public void setResultat(java.lang.Integer _resultat_) {
		this.resultat = _resultat_;
	};

	public java.lang.String getInfoResultat() {
		return(infoResultat);
	};
	public void setInfoResultat(java.lang.String _infoResultat_) {
		this.infoResultat = _infoResultat_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Validacio) {
            Validacio __instance = (Validacio)__obj;
            __result = true;
            __result = __result && (this.getValidacioID() == __instance.getValidacioID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:fitxerid | Table: suf_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "signaturaid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_validacio_fitxer_sig_fk"))
    private FitxerJPA signatura;

    public FitxerJPA getSignatura() {
    return this.signatura;
  }

    public  void setSignatura(FitxerJPA signatura) {
    this.signatura = signatura;
  }

// IMP Field:fitxerid | Table: suf_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detachedid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_validacio_fitxer_det_fk"))
    private FitxerJPA detached;

    public FitxerJPA getDetached() {
    return this.detached;
  }

    public  void setDetached(FitxerJPA detached) {
    this.detached = detached;
  }


 // ---------------  STATIC METHODS ------------------
  public static ValidacioJPA toJPA(Validacio __bean) {
    if (__bean == null) { return null;}
    ValidacioJPA __tmp = new ValidacioJPA();
    __tmp.setValidacioID(__bean.getValidacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSignaturaID(__bean.getSignaturaID());
    __tmp.setDetachedID(__bean.getDetachedID());
    __tmp.setResultat(__bean.getResultat());
    __tmp.setInfoResultat(__bean.getInfoResultat());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    // Fitxer
    __tmp.setSignatura(FitxerJPA.toJPA(__bean.getSignatura()));
    // Fitxer
    __tmp.setDetached(FitxerJPA.toJPA(__bean.getDetached()));
		return __tmp;
	}


  public static ValidacioJPA copyJPA(ValidacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ValidacioJPA> copyJPA(java.util.Set<ValidacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ValidacioJPA> __tmpSet = (java.util.Set<ValidacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ValidacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ValidacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ValidacioJPA copyJPA(ValidacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ValidacioJPA __tmp = (ValidacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
