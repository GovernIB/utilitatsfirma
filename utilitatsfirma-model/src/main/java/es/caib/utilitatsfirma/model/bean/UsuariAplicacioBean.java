
package es.caib.utilitatsfirma.model.bean;

import es.caib.utilitatsfirma.model.entity.UsuariAplicacio;


public class UsuariAplicacioBean implements UsuariAplicacio {



	java.lang.String usuariAplicacioID;// PK
	java.lang.String descripcio;
	java.lang.String emailAdmin;
	boolean actiu;


  /** Constructor Buit */
  public UsuariAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioBean(java.lang.String usuariAplicacioID , java.lang.String descripcio , java.lang.String emailAdmin , boolean actiu) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.descripcio=descripcio;
    this.emailAdmin=emailAdmin;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioBean(java.lang.String usuariAplicacioID , java.lang.String emailAdmin , boolean actiu) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.emailAdmin=emailAdmin;
    this.actiu=actiu;
}
  public UsuariAplicacioBean(UsuariAplicacio __bean) {
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setDescripcio(__bean.getDescripcio());
    this.setEmailAdmin(__bean.getEmailAdmin());
    this.setActiu(__bean.isActiu());
	}

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getEmailAdmin() {
		return(emailAdmin);
	};
	public void setEmailAdmin(java.lang.String _emailAdmin_) {
		this.emailAdmin = _emailAdmin_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  // ======================================

  public static UsuariAplicacioBean toBean(UsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioBean __tmp = new UsuariAplicacioBean();
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEmailAdmin(__bean.getEmailAdmin());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}



}
