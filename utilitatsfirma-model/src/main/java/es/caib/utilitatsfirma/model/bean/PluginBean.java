
package es.caib.utilitatsfirma.model.bean;

import es.caib.utilitatsfirma.model.entity.Plugin;


public class PluginBean implements Plugin {



	long pluginID;// PK
	long nomID;
	long descripcioCurtaID;
	java.lang.String classe;
	java.lang.String propertiesAdmin;
	boolean actiu;
	int tipus;
	java.lang.String codi;
	java.lang.Integer ordre;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , boolean actiu , int tipus , java.lang.String codi , java.lang.Integer ordre) {
    this.pluginID=pluginID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.actiu=actiu;
    this.tipus=tipus;
    this.codi=codi;
    this.ordre=ordre;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , boolean actiu , int tipus , java.lang.String codi , java.lang.Integer ordre) {
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.actiu=actiu;
    this.tipus=tipus;
    this.codi=codi;
    this.ordre=ordre;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    this.setClasse(__bean.getClasse());
    this.setPropertiesAdmin(__bean.getPropertiesAdmin());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
    this.setCodi(__bean.getCodi());
    this.setOrdre(__bean.getOrdre());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getDescripcioCurtaID() {
		return(descripcioCurtaID);
	};
	public void setDescripcioCurtaID(long _descripcioCurtaID_) {
		this.descripcioCurtaID = _descripcioCurtaID_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.String getPropertiesAdmin() {
		return(propertiesAdmin);
	};
	public void setPropertiesAdmin(java.lang.String _propertiesAdmin_) {
		this.propertiesAdmin = _propertiesAdmin_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.Integer getOrdre() {
		return(ordre);
	};
	public void setOrdre(java.lang.Integer _ordre_) {
		this.ordre = _ordre_;
	};



  // ======================================

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setPropertiesAdmin(__bean.getPropertiesAdmin());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
	}



}
