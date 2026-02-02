
package es.caib.utilitatsfirma.persistence;
import es.caib.utilitatsfirma.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "PluginJPA")
@Table(name = "suf_plugin" , indexes = { 
        @Index(name="suf_plugin_pk_i", columnList = "pluginid"),
        @Index(name="suf_plugin_nomid_fk_i", columnList = "nomid"),
        @Index(name="suf_plugin_descrid_fk_i", columnList = "descripciocurtaid")})
@SequenceGenerator(name="PLUGIN_SEQ", sequenceName="suf_plugin_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginJPA implements Plugin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGIN_SEQ")
    @Column(name="pluginid",nullable = false,length = 19)
    long pluginID;

    @Column(name="nomid",nullable = false,length = 19)
    long nomID;

    @Column(name="descripciocurtaid",nullable = false,length = 19)
    long descripcioCurtaID;

    @Column(name="classe",nullable = false,length = 255)
    java.lang.String classe;

    @Column(name="propertiesadmin",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String propertiesAdmin;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Column(name="codi",nullable = false,unique = true,length = 255)
    java.lang.String codi;

    @Column(name="ordre",nullable = false,unique = true,length = 10)
    java.lang.Integer ordre;



  /** Constructor Buit */
  public PluginJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginJPA(long pluginID , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , boolean actiu , int tipus , java.lang.String codi , java.lang.Integer ordre) {
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
  public PluginJPA(long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , boolean actiu , int tipus , java.lang.String codi , java.lang.Integer ordre) {
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.actiu=actiu;
    this.tipus=tipus;
    this.codi=codi;
    this.ordre=ordre;
}
  public PluginJPA(Plugin __bean) {
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



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Plugin) {
            Plugin __instance = (Plugin)__obj;
            __result = true;
            __result = __result && (this.getPluginID() == __instance.getPluginID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:pluginfirmaservidorid | Table: suf_usuariaplicacioconfig | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
    private Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios = new HashSet<UsuariAplicacioConfiguracioJPA>(0);
    public  Set<UsuariAplicacioConfiguracioJPA> getUsuariAplicacioConfiguracios() {
    return this.usuariAplicacioConfiguracios;
  }

    public void setUsuariAplicacioConfiguracios(Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios) {
      this.usuariAplicacioConfiguracios = usuariAplicacioConfiguracios;
    }


// IMP Field:traduccioid | Table: suf_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_plugin_traduccio_nom_fk"))
    private TraduccioJPA nom;

    public TraduccioJPA getNom() {
    return this.nom;
  }

    public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.utilitatsfirma.persistence.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.utilitatsfirma.persistence.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: suf_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "descripciocurtaid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_plugin_traduccio_desc_fk"))
    private TraduccioJPA descripcioCurta;

    public TraduccioJPA getDescripcioCurta() {
    return this.descripcioCurta;
  }

    public  void setDescripcioCurta(TraduccioJPA descripcioCurta) {
    this.descripcioCurta = descripcioCurta;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.utilitatsfirma.persistence.TraduccioMapJPA> getDescripcioCurtaTraduccions() {
    return this.descripcioCurta.getTraduccions();
  }

  public void setDescripcioCurtaTraduccions(java.util.Map<String, es.caib.utilitatsfirma.persistence.TraduccioMapJPA> __traduccions__) {
    this.descripcioCurta.setTraduccions(__traduccions__);
  }



 // ---------------  STATIC METHODS ------------------
  public static PluginJPA toJPA(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginJPA __tmp = new PluginJPA();
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


  public static PluginJPA copyJPA(PluginJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginJPA> copyJPA(java.util.Set<PluginJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PluginJPA> __tmpSet = (java.util.Set<PluginJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginJPA copyJPA(PluginJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginJPA __tmp = (PluginJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracios())) ) {
      __tmp.setUsuariAplicacioConfiguracios(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracios(), __alreadyCopied,"PluginJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcioCurta) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcioCurta()) ) ) {
      __tmp.setDescripcioCurta(TraduccioJPA.copyJPA(__jpa.getDescripcioCurta(), __alreadyCopied,"PluginJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"PluginJPA"));
    }

    return __tmp;
  }




}
