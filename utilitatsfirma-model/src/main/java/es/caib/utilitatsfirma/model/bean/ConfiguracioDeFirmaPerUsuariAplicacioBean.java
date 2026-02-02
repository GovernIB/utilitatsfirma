
package es.caib.utilitatsfirma.model.bean;

import es.caib.utilitatsfirma.model.entity.ConfiguracioDeFirmaPerUsuariAplicacio;


public class ConfiguracioDeFirmaPerUsuariAplicacioBean implements ConfiguracioDeFirmaPerUsuariAplicacio {



	long configsPerUsrAppID;// PK
	java.lang.String usuariAplicacioID;
	long usuariAplicacioConfigID;


  /** Constructor Buit */
  public ConfiguracioDeFirmaPerUsuariAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public ConfiguracioDeFirmaPerUsuariAplicacioBean(long configsPerUsrAppID , java.lang.String usuariAplicacioID , long usuariAplicacioConfigID) {
    this.configsPerUsrAppID=configsPerUsrAppID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
}
  /** Constructor sense valors autoincrementals */
  public ConfiguracioDeFirmaPerUsuariAplicacioBean(java.lang.String usuariAplicacioID , long usuariAplicacioConfigID) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
}
  public ConfiguracioDeFirmaPerUsuariAplicacioBean(ConfiguracioDeFirmaPerUsuariAplicacio __bean) {
    this.setConfigsPerUsrAppID(__bean.getConfigsPerUsrAppID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
	}

	public long getConfigsPerUsrAppID() {
		return(configsPerUsrAppID);
	};
	public void setConfigsPerUsrAppID(long _configsPerUsrAppID_) {
		this.configsPerUsrAppID = _configsPerUsrAppID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public long getUsuariAplicacioConfigID() {
		return(usuariAplicacioConfigID);
	};
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_) {
		this.usuariAplicacioConfigID = _usuariAplicacioConfigID_;
	};



  // ======================================

  public static ConfiguracioDeFirmaPerUsuariAplicacioBean toBean(ConfiguracioDeFirmaPerUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    ConfiguracioDeFirmaPerUsuariAplicacioBean __tmp = new ConfiguracioDeFirmaPerUsuariAplicacioBean();
    __tmp.setConfigsPerUsrAppID(__bean.getConfigsPerUsrAppID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
		return __tmp;
	}



}
