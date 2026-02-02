package es.caib.utilitatsfirma.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public long getDescripcioCurtaID();
	public void setDescripcioCurtaID(long _descripcioCurtaID_);

	public java.lang.String getClasse();
	public void setClasse(java.lang.String _classe_);

	public java.lang.String getPropertiesAdmin();
	public void setPropertiesAdmin(java.lang.String _propertiesAdmin_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getCodi();
	public void setCodi(java.lang.String _codi_);

	public java.lang.Integer getOrdre();
	public void setOrdre(java.lang.Integer _ordre_);



  // ======================================

}
