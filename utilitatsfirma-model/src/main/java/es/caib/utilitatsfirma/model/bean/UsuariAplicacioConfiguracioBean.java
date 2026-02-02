
package es.caib.utilitatsfirma.model.bean;

import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;


public class UsuariAplicacioConfiguracioBean implements UsuariAplicacioConfiguracio {



	long usuariAplicacioConfigID;// PK
	java.lang.String nom;
	int usPoliticaDeFirma;
	java.lang.String policyIdentifier;
	java.lang.String policyIdentifierHash;
	java.lang.String policyIdentifierHashAlgorithm;
	java.lang.String policyUrlDocument;
	int tipusOperacioFirma;
	int tipusFirma;
	int algorismeDeFirma;
	int modeDeFirma;
	boolean comprovarNifFirma;
	boolean checkCanviatDocFirmat;
	boolean validarFirma;
	long pluginFirmaServidorID;
	java.lang.Integer upgradeSignFormat;
	int politicaSegellatDeTemps;


  /** Constructor Buit */
  public UsuariAplicacioConfiguracioBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioBean(long usuariAplicacioConfigID , java.lang.String nom , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , int tipusOperacioFirma , int tipusFirma , int algorismeDeFirma , int modeDeFirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , long pluginFirmaServidorID , java.lang.Integer upgradeSignFormat , int politicaSegellatDeTemps) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.nom=nom;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirma=tipusFirma;
    this.algorismeDeFirma=algorismeDeFirma;
    this.modeDeFirma=modeDeFirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.upgradeSignFormat=upgradeSignFormat;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
}
  /** Constructor sense valors autoincrementals */
  public UsuariAplicacioConfiguracioBean(java.lang.String nom , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , int tipusOperacioFirma , int tipusFirma , int algorismeDeFirma , int modeDeFirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , long pluginFirmaServidorID , java.lang.Integer upgradeSignFormat , int politicaSegellatDeTemps) {
    this.nom=nom;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirma=tipusFirma;
    this.algorismeDeFirma=algorismeDeFirma;
    this.modeDeFirma=modeDeFirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.upgradeSignFormat=upgradeSignFormat;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioConfiguracioBean(long usuariAplicacioConfigID , java.lang.String nom , int usPoliticaDeFirma , int tipusOperacioFirma , int tipusFirma , int algorismeDeFirma , int modeDeFirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , long pluginFirmaServidorID , int politicaSegellatDeTemps) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.nom=nom;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirma=tipusFirma;
    this.algorismeDeFirma=algorismeDeFirma;
    this.modeDeFirma=modeDeFirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
}
  public UsuariAplicacioConfiguracioBean(UsuariAplicacioConfiguracio __bean) {
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    this.setNom(__bean.getNom());
    this.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirma(__bean.getTipusFirma());
    this.setAlgorismeDeFirma(__bean.getAlgorismeDeFirma());
    this.setModeDeFirma(__bean.getModeDeFirma());
    this.setComprovarNifFirma(__bean.isComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    this.setValidarFirma(__bean.isValidarFirma());
    this.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    this.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    this.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
	}

	public long getUsuariAplicacioConfigID() {
		return(usuariAplicacioConfigID);
	};
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_) {
		this.usuariAplicacioConfigID = _usuariAplicacioConfigID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public int getUsPoliticaDeFirma() {
		return(usPoliticaDeFirma);
	};
	public void setUsPoliticaDeFirma(int _usPoliticaDeFirma_) {
		this.usPoliticaDeFirma = _usPoliticaDeFirma_;
	};

	public java.lang.String getPolicyIdentifier() {
		return(policyIdentifier);
	};
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_) {
		this.policyIdentifier = _policyIdentifier_;
	};

	public java.lang.String getPolicyIdentifierHash() {
		return(policyIdentifierHash);
	};
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_) {
		this.policyIdentifierHash = _policyIdentifierHash_;
	};

	public java.lang.String getPolicyIdentifierHashAlgorithm() {
		return(policyIdentifierHashAlgorithm);
	};
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_) {
		this.policyIdentifierHashAlgorithm = _policyIdentifierHashAlgorithm_;
	};

	public java.lang.String getPolicyUrlDocument() {
		return(policyUrlDocument);
	};
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_) {
		this.policyUrlDocument = _policyUrlDocument_;
	};

	public int getTipusOperacioFirma() {
		return(tipusOperacioFirma);
	};
	public void setTipusOperacioFirma(int _tipusOperacioFirma_) {
		this.tipusOperacioFirma = _tipusOperacioFirma_;
	};

	public int getTipusFirma() {
		return(tipusFirma);
	};
	public void setTipusFirma(int _tipusFirma_) {
		this.tipusFirma = _tipusFirma_;
	};

	public int getAlgorismeDeFirma() {
		return(algorismeDeFirma);
	};
	public void setAlgorismeDeFirma(int _algorismeDeFirma_) {
		this.algorismeDeFirma = _algorismeDeFirma_;
	};

	public int getModeDeFirma() {
		return(modeDeFirma);
	};
	public void setModeDeFirma(int _modeDeFirma_) {
		this.modeDeFirma = _modeDeFirma_;
	};

	public boolean isComprovarNifFirma() {
		return(comprovarNifFirma);
	};
	public void setComprovarNifFirma(boolean _comprovarNifFirma_) {
		this.comprovarNifFirma = _comprovarNifFirma_;
	};

	public boolean isCheckCanviatDocFirmat() {
		return(checkCanviatDocFirmat);
	};
	public void setCheckCanviatDocFirmat(boolean _checkCanviatDocFirmat_) {
		this.checkCanviatDocFirmat = _checkCanviatDocFirmat_;
	};

	public boolean isValidarFirma() {
		return(validarFirma);
	};
	public void setValidarFirma(boolean _validarFirma_) {
		this.validarFirma = _validarFirma_;
	};

	public long getPluginFirmaServidorID() {
		return(pluginFirmaServidorID);
	};
	public void setPluginFirmaServidorID(long _pluginFirmaServidorID_) {
		this.pluginFirmaServidorID = _pluginFirmaServidorID_;
	};

	public java.lang.Integer getUpgradeSignFormat() {
		return(upgradeSignFormat);
	};
	public void setUpgradeSignFormat(java.lang.Integer _upgradeSignFormat_) {
		this.upgradeSignFormat = _upgradeSignFormat_;
	};

	public int getPoliticaSegellatDeTemps() {
		return(politicaSegellatDeTemps);
	};
	public void setPoliticaSegellatDeTemps(int _politicaSegellatDeTemps_) {
		this.politicaSegellatDeTemps = _politicaSegellatDeTemps_;
	};



  // ======================================

  public static UsuariAplicacioConfiguracioBean toBean(UsuariAplicacioConfiguracio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioConfiguracioBean __tmp = new UsuariAplicacioConfiguracioBean();
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    __tmp.setNom(__bean.getNom());
    __tmp.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirma(__bean.getTipusFirma());
    __tmp.setAlgorismeDeFirma(__bean.getAlgorismeDeFirma());
    __tmp.setModeDeFirma(__bean.getModeDeFirma());
    __tmp.setComprovarNifFirma(__bean.isComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    __tmp.setValidarFirma(__bean.isValidarFirma());
    __tmp.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    __tmp.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    __tmp.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
		return __tmp;
	}



}
