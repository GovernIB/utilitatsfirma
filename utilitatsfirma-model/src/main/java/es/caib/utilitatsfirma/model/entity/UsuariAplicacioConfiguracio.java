package es.caib.utilitatsfirma.model.entity;

public interface UsuariAplicacioConfiguracio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getUsuariAplicacioConfigID();
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public int getUsPoliticaDeFirma();
	public void setUsPoliticaDeFirma(int _usPoliticaDeFirma_);

	public java.lang.String getPolicyIdentifier();
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_);

	public java.lang.String getPolicyIdentifierHash();
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_);

	public java.lang.String getPolicyIdentifierHashAlgorithm();
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_);

	public java.lang.String getPolicyUrlDocument();
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_);

	public int getTipusOperacioFirma();
	public void setTipusOperacioFirma(int _tipusOperacioFirma_);

	public int getTipusFirma();
	public void setTipusFirma(int _tipusFirma_);

	public int getAlgorismeDeFirma();
	public void setAlgorismeDeFirma(int _algorismeDeFirma_);

	public int getModeDeFirma();
	public void setModeDeFirma(int _modeDeFirma_);

	public boolean isComprovarNifFirma();
	public void setComprovarNifFirma(boolean _comprovarNifFirma_);

	public boolean isCheckCanviatDocFirmat();
	public void setCheckCanviatDocFirmat(boolean _checkCanviatDocFirmat_);

	public boolean isValidarFirma();
	public void setValidarFirma(boolean _validarFirma_);

	public long getPluginFirmaServidorID();
	public void setPluginFirmaServidorID(long _pluginFirmaServidorID_);

	public java.lang.Integer getUpgradeSignFormat();
	public void setUpgradeSignFormat(java.lang.Integer _upgradeSignFormat_);

	public int getPoliticaSegellatDeTemps();
	public void setPoliticaSegellatDeTemps(int _politicaSegellatDeTemps_);



  // ======================================

}
