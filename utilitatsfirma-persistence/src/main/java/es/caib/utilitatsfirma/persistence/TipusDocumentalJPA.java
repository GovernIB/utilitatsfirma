
package es.caib.utilitatsfirma.persistence;
import es.caib.utilitatsfirma.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity(name = "TipusDocumentalJPA")
@Table(name = "suf_tipusdocumental" , indexes = { 
        @Index(name="suf_tipusdocumental_pk_i", columnList = "tipusdocumentalid")})
@SequenceGenerator(name="TIPUSDOCUMENTAL_SEQ", sequenceName="suf_tipusdocumental_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TipusDocumentalJPA implements TipusDocumental {

    @Id
    @Column(name="tipusdocumentalid",nullable = false,length = 19)
    long tipusDocumentalID;

    @Column(name="paretipusdocumentalid",length = 19)
    java.lang.Long pareTipusDocumentalID;

    @Column(name="nomcatala",nullable = false,length = 255)
    java.lang.String nomCatala;

    @Column(name="nomcastella",nullable = false,length = 256)
    java.lang.String nomCastella;

    @Column(name="descripciocatala",length = 256)
    java.lang.String descripcioCatala;

    @Column(name="descripciocastella",length = 256)
    java.lang.String descripcioCastella;



  /** Constructor Buit */
  public TipusDocumentalJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusDocumentalJPA(long tipusDocumentalID , java.lang.Long pareTipusDocumentalID , java.lang.String nomCatala , java.lang.String nomCastella , java.lang.String descripcioCatala , java.lang.String descripcioCastella) {
    this.tipusDocumentalID=tipusDocumentalID;
    this.pareTipusDocumentalID=pareTipusDocumentalID;
    this.nomCatala=nomCatala;
    this.nomCastella=nomCastella;
    this.descripcioCatala=descripcioCatala;
    this.descripcioCastella=descripcioCastella;
}
  /** Constructor dels valors Not Null */
  public TipusDocumentalJPA(long tipusDocumentalID , java.lang.String nomCatala , java.lang.String nomCastella) {
    this.tipusDocumentalID=tipusDocumentalID;
    this.nomCatala=nomCatala;
    this.nomCastella=nomCastella;
}
  public TipusDocumentalJPA(TipusDocumental __bean) {
    this.setTipusDocumentalID(__bean.getTipusDocumentalID());
    this.setPareTipusDocumentalID(__bean.getPareTipusDocumentalID());
    this.setNomCatala(__bean.getNomCatala());
    this.setNomCastella(__bean.getNomCastella());
    this.setDescripcioCatala(__bean.getDescripcioCatala());
    this.setDescripcioCastella(__bean.getDescripcioCastella());
	}

	public long getTipusDocumentalID() {
		return(tipusDocumentalID);
	};
	public void setTipusDocumentalID(long _tipusDocumentalID_) {
		this.tipusDocumentalID = _tipusDocumentalID_;
	};

	public java.lang.Long getPareTipusDocumentalID() {
		return(pareTipusDocumentalID);
	};
	public void setPareTipusDocumentalID(java.lang.Long _pareTipusDocumentalID_) {
		this.pareTipusDocumentalID = _pareTipusDocumentalID_;
	};

	public java.lang.String getNomCatala() {
		return(nomCatala);
	};
	public void setNomCatala(java.lang.String _nomCatala_) {
		this.nomCatala = _nomCatala_;
	};

	public java.lang.String getNomCastella() {
		return(nomCastella);
	};
	public void setNomCastella(java.lang.String _nomCastella_) {
		this.nomCastella = _nomCastella_;
	};

	public java.lang.String getDescripcioCatala() {
		return(descripcioCatala);
	};
	public void setDescripcioCatala(java.lang.String _descripcioCatala_) {
		this.descripcioCatala = _descripcioCatala_;
	};

	public java.lang.String getDescripcioCastella() {
		return(descripcioCastella);
	};
	public void setDescripcioCastella(java.lang.String _descripcioCastella_) {
		this.descripcioCastella = _descripcioCastella_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof TipusDocumental) {
            TipusDocumental __instance = (TipusDocumental)__obj;
            __result = true;
            __result = __result && (this.getTipusDocumentalID() == __instance.getTipusDocumentalID()) ;
        } else {
            __result = false;
        }
        return __result;
    }


 // ---------------  STATIC METHODS ------------------
  public static TipusDocumentalJPA toJPA(TipusDocumental __bean) {
    if (__bean == null) { return null;}
    TipusDocumentalJPA __tmp = new TipusDocumentalJPA();
    __tmp.setTipusDocumentalID(__bean.getTipusDocumentalID());
    __tmp.setPareTipusDocumentalID(__bean.getPareTipusDocumentalID());
    __tmp.setNomCatala(__bean.getNomCatala());
    __tmp.setNomCastella(__bean.getNomCastella());
    __tmp.setDescripcioCatala(__bean.getDescripcioCatala());
    __tmp.setDescripcioCastella(__bean.getDescripcioCastella());
		return __tmp;
	}


  public static TipusDocumentalJPA copyJPA(TipusDocumentalJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TipusDocumentalJPA> copyJPA(java.util.Set<TipusDocumentalJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TipusDocumentalJPA> __tmpSet = (java.util.Set<TipusDocumentalJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TipusDocumentalJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TipusDocumentalJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TipusDocumentalJPA copyJPA(TipusDocumentalJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TipusDocumentalJPA __tmp = (TipusDocumentalJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
