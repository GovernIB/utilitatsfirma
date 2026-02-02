
package es.caib.utilitatsfirma.persistence;
import es.caib.utilitatsfirma.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "ConfiguracioDeFirmaPerUsuariAplicacioJPA")
@Table(name = "suf_configsperusrapp" , indexes = { 
        @Index(name="suf_configsperusrapp_pk_i", columnList = "configsperusrappid"),
        @Index(name="suf_confipua_usrappid_fk_i", columnList = "usuariaplicacioid"),
        @Index(name="suf_confipua_uaconfigid_fk_i", columnList = "usuariaplicacioconfigid")})
@SequenceGenerator(name="CONFIGURACIODEFIRMAPERUSUARIAPLICACIO_SEQ", sequenceName="suf_configsperusrapp_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ConfiguracioDeFirmaPerUsuariAplicacioJPA implements ConfiguracioDeFirmaPerUsuariAplicacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CONFIGURACIODEFIRMAPERUSUARIAPLICACIO_SEQ")
    @Column(name="configsperusrappid",nullable = false,length = 19)
    long configsPerUsrAppID;

    @Column(name="usuariaplicacioid",nullable = false,length = 50)
    java.lang.String usuariAplicacioID;

    @Column(name="usuariaplicacioconfigid",nullable = false,length = 19)
    long usuariAplicacioConfigID;



  /** Constructor Buit */
  public ConfiguracioDeFirmaPerUsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public ConfiguracioDeFirmaPerUsuariAplicacioJPA(long configsPerUsrAppID , java.lang.String usuariAplicacioID , long usuariAplicacioConfigID) {
    this.configsPerUsrAppID=configsPerUsrAppID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
}
  /** Constructor sense valors autoincrementals */
  public ConfiguracioDeFirmaPerUsuariAplicacioJPA(java.lang.String usuariAplicacioID , long usuariAplicacioConfigID) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
}
  public ConfiguracioDeFirmaPerUsuariAplicacioJPA(ConfiguracioDeFirmaPerUsuariAplicacio __bean) {
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



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof ConfiguracioDeFirmaPerUsuariAplicacio) {
            ConfiguracioDeFirmaPerUsuariAplicacio __instance = (ConfiguracioDeFirmaPerUsuariAplicacio)__obj;
            __result = true;
            __result = __result && (this.getConfigsPerUsrAppID() == __instance.getConfigsPerUsrAppID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:usuariaplicacioid | Table: suf_usuariaplicacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_confipua_usrapp_usuaria_fk"))
    private UsuariAplicacioJPA usuariAplicacio;

    public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

    public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }

// IMP Field:usuariaplicacioconfigid | Table: suf_usuariaplicacioconfig | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariaplicacioconfigid", referencedColumnName ="usuariAplicacioConfigID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_confipua_usrappcfg_uac_fk"))
    private UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio;

    public UsuariAplicacioConfiguracioJPA getUsuariAplicacioConfiguracio() {
    return this.usuariAplicacioConfiguracio;
  }

    public  void setUsuariAplicacioConfiguracio(UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) {
    this.usuariAplicacioConfiguracio = usuariAplicacioConfiguracio;
  }


 // ---------------  STATIC METHODS ------------------
  public static ConfiguracioDeFirmaPerUsuariAplicacioJPA toJPA(ConfiguracioDeFirmaPerUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    ConfiguracioDeFirmaPerUsuariAplicacioJPA __tmp = new ConfiguracioDeFirmaPerUsuariAplicacioJPA();
    __tmp.setConfigsPerUsrAppID(__bean.getConfigsPerUsrAppID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
		return __tmp;
	}


  public static ConfiguracioDeFirmaPerUsuariAplicacioJPA copyJPA(ConfiguracioDeFirmaPerUsuariAplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ConfiguracioDeFirmaPerUsuariAplicacioJPA> copyJPA(java.util.Set<ConfiguracioDeFirmaPerUsuariAplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ConfiguracioDeFirmaPerUsuariAplicacioJPA> __tmpSet = (java.util.Set<ConfiguracioDeFirmaPerUsuariAplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ConfiguracioDeFirmaPerUsuariAplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ConfiguracioDeFirmaPerUsuariAplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ConfiguracioDeFirmaPerUsuariAplicacioJPA copyJPA(ConfiguracioDeFirmaPerUsuariAplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ConfiguracioDeFirmaPerUsuariAplicacioJPA __tmp = (ConfiguracioDeFirmaPerUsuariAplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"ConfiguracioDeFirmaPerUsuariAplicacioJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracio()) ) ) {
      __tmp.setUsuariAplicacioConfiguracio(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracio(), __alreadyCopied,"ConfiguracioDeFirmaPerUsuariAplicacioJPA"));
    }

    return __tmp;
  }




}
