
package es.caib.utilitatsfirma.model.bean;

import es.caib.utilitatsfirma.model.entity.Validacio;


public class ValidacioBean implements Validacio {



	long validacioID;// PK
	java.lang.String nom;
	long signaturaID;
	java.lang.Long detachedID;
	java.lang.Integer resultat;
	java.lang.String infoResultat;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;


  /** Constructor Buit */
  public ValidacioBean() {
  }

  /** Constructor amb tots els camps  */
  public ValidacioBean(long validacioID , java.lang.String nom , long signaturaID , java.lang.Long detachedID , java.lang.Integer resultat , java.lang.String infoResultat , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi) {
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
  public ValidacioBean(java.lang.String nom , long signaturaID , java.lang.Long detachedID , java.lang.Integer resultat , java.lang.String infoResultat , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi) {
    this.nom=nom;
    this.signaturaID=signaturaID;
    this.detachedID=detachedID;
    this.resultat=resultat;
    this.infoResultat=infoResultat;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
}
  /** Constructor dels valors Not Null */
  public ValidacioBean(long validacioID , java.lang.String nom , long signaturaID , java.sql.Timestamp dataInici) {
    this.validacioID=validacioID;
    this.nom=nom;
    this.signaturaID=signaturaID;
    this.dataInici=dataInici;
}
  public ValidacioBean(Validacio __bean) {
    this.setValidacioID(__bean.getValidacioID());
    this.setNom(__bean.getNom());
    this.setSignaturaID(__bean.getSignaturaID());
    this.setDetachedID(__bean.getDetachedID());
    this.setResultat(__bean.getResultat());
    this.setInfoResultat(__bean.getInfoResultat());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    // Fitxer
    this.setSignatura(FitxerBean.toBean(__bean.getSignatura()));
    // Fitxer
    this.setDetached(FitxerBean.toBean(__bean.getDetached()));
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



  // ======================================

  public static ValidacioBean toBean(Validacio __bean) {
    if (__bean == null) { return null;}
    ValidacioBean __tmp = new ValidacioBean();
    __tmp.setValidacioID(__bean.getValidacioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSignaturaID(__bean.getSignaturaID());
    __tmp.setDetachedID(__bean.getDetachedID());
    __tmp.setResultat(__bean.getResultat());
    __tmp.setInfoResultat(__bean.getInfoResultat());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    // Fitxer
    __tmp.setSignatura(FitxerBean.toBean(__bean.getSignatura()));
    // Fitxer
    __tmp.setDetached(FitxerBean.toBean(__bean.getDetached()));
		return __tmp;
	}

  protected FitxerBean signatura;
  public FitxerBean getSignatura() {
    return signatura;
  }
  public void setSignatura(FitxerBean __field) {
    this. signatura = __field;
  }
  protected FitxerBean detached;
  public FitxerBean getDetached() {
    return detached;
  }
  public void setDetached(FitxerBean __field) {
    this. detached = __field;
  }


}
