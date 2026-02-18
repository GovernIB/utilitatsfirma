
package es.caib.utilitatsfirma.model.bean;

import es.caib.utilitatsfirma.model.entity.TipusDocumental;


public class TipusDocumentalBean implements TipusDocumental {



	long tipusDocumentalID;// PK
	java.lang.Long pareTipusDocumentalID;
	java.lang.String nomCatala;
	java.lang.String nomCastella;
	java.lang.String descripcioCatala;
	java.lang.String descripcioCastella;


  /** Constructor Buit */
  public TipusDocumentalBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusDocumentalBean(long tipusDocumentalID , java.lang.Long pareTipusDocumentalID , java.lang.String nomCatala , java.lang.String nomCastella , java.lang.String descripcioCatala , java.lang.String descripcioCastella) {
    this.tipusDocumentalID=tipusDocumentalID;
    this.pareTipusDocumentalID=pareTipusDocumentalID;
    this.nomCatala=nomCatala;
    this.nomCastella=nomCastella;
    this.descripcioCatala=descripcioCatala;
    this.descripcioCastella=descripcioCastella;
}
  /** Constructor dels valors Not Null */
  public TipusDocumentalBean(long tipusDocumentalID , java.lang.String nomCatala , java.lang.String nomCastella) {
    this.tipusDocumentalID=tipusDocumentalID;
    this.nomCatala=nomCatala;
    this.nomCastella=nomCastella;
}
  public TipusDocumentalBean(TipusDocumental __bean) {
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



  // ======================================

  public static TipusDocumentalBean toBean(TipusDocumental __bean) {
    if (__bean == null) { return null;}
    TipusDocumentalBean __tmp = new TipusDocumentalBean();
    __tmp.setTipusDocumentalID(__bean.getTipusDocumentalID());
    __tmp.setPareTipusDocumentalID(__bean.getPareTipusDocumentalID());
    __tmp.setNomCatala(__bean.getNomCatala());
    __tmp.setNomCastella(__bean.getNomCastella());
    __tmp.setDescripcioCatala(__bean.getDescripcioCatala());
    __tmp.setDescripcioCastella(__bean.getDescripcioCastella());
		return __tmp;
	}



}
