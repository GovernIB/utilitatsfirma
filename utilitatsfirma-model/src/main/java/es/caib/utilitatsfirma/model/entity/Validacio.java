package es.caib.utilitatsfirma.model.entity;

public interface Validacio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getValidacioID();
	public void setValidacioID(long _validacioID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public long getSignaturaID();
	public void setSignaturaID(long _signaturaID_);

	public java.lang.Long getDetachedID();
	public void setDetachedID(java.lang.Long _detachedID_);

	public java.lang.Integer getResultat();
	public void setResultat(java.lang.Integer _resultat_);

	public java.lang.String getInfoResultat();
	public void setInfoResultat(java.lang.String _infoResultat_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

  // Fitxer
  public <F extends Fitxer> F getSignatura();
  // Fitxer
  public <F extends Fitxer> F getDetached();


  // ======================================

}
