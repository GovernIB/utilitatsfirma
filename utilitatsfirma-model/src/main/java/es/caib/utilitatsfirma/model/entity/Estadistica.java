package es.caib.utilitatsfirma.model.entity;

public interface Estadistica extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEstadisticaID();
	public void setEstadisticaID(long _estadisticaID_);

	public java.sql.Timestamp getData();
	public void setData(java.sql.Timestamp _data_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.Double getValor();
	public void setValor(java.lang.Double _valor_);

	public java.lang.String getUsuariAplicacioID();
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_);

	public int getEntorn();
	public void setEntorn(int _entorn_);



  // ======================================

}
