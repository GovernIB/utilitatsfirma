
package es.caib.utilitatsfirma.persistence;
import es.caib.utilitatsfirma.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "UsuariAplicacioConfiguracioJPA")
@Table(name = "suf_usuariaplicacioconfig" , indexes = { 
        @Index(name="suf_usuariaplicacioconfig_pk_i", columnList = "usuariaplicacioconfigid"),
        @Index(name="suf_usrappcfg_plugfirma_fk_i", columnList = "pluginfirmaservidorid")})
@SequenceGenerator(name="USUARIAPLICACIOCONFIGURACIO_SEQ", sequenceName="suf_usuariaplicacioconfig_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariAplicacioConfiguracioJPA implements UsuariAplicacioConfiguracio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USUARIAPLICACIOCONFIGURACIO_SEQ")
    @Column(name="usuariaplicacioconfigid",nullable = false,length = 19)
    long usuariAplicacioConfigID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="uspoliticadefirma",nullable = false,length = 10)
    @org.hibernate.annotations.ColumnDefault("0")
    int usPoliticaDeFirma = 0;

    @Column(name="policyidentifier",length = 100)
    java.lang.String policyIdentifier;

    @Column(name="policyidentifierhash",length = 256)
    java.lang.String policyIdentifierHash;

    @Column(name="policyidentifierhashalgorithm",length = 50)
    java.lang.String policyIdentifierHashAlgorithm;

    @Column(name="policyurldocument",length = 255)
    java.lang.String policyUrlDocument;

  /** 0 firma, 1 contrafirma 2, cofirma */
    @Column(name="tipusoperaciofirma",nullable = false,length = 10)
    @org.hibernate.annotations.ColumnDefault("0")
    int tipusOperacioFirma = 0;

    @Column(name="tipusfirmaid",nullable = false,length = 10)
    int tipusFirma;

    @Column(name="algorismedefirmaid",nullable = false,length = 10)
    int algorismeDeFirma;

    @Column(name="modedefirma",nullable = false,length = 10)
    int modeDeFirma;

  /** Null => Valor definit a l'entitat */
    @Column(name="comprovarniffirma",nullable = false,length = 1)
    boolean comprovarNifFirma;

  /** -- Null => Valor definit a l'entitat */
    @Column(name="checkcanviatdocfirmat",nullable = false,length = 1)
    boolean checkCanviatDocFirmat;

  /** Indica si validar la firma amb el Plugin de validació definit a l'entitat */
    @Column(name="validarfirma",nullable = false,length = 1)
    boolean validarFirma;

    @Column(name="pluginfirmaservidorid",nullable = false,length = 19)
    long pluginFirmaServidorID;

    @Column(name="upgradesignformat",length = 10)
    java.lang.Integer upgradeSignFormat;

    @Column(name="politicaSegellatDeTemps",nullable = false,length = 10)
    int politicaSegellatDeTemps;



  /** Constructor Buit */
  public UsuariAplicacioConfiguracioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String nom , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , int tipusOperacioFirma , int tipusFirma , int algorismeDeFirma , int modeDeFirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , long pluginFirmaServidorID , java.lang.Integer upgradeSignFormat , int politicaSegellatDeTemps) {
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
  public UsuariAplicacioConfiguracioJPA(java.lang.String nom , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , int tipusOperacioFirma , int tipusFirma , int algorismeDeFirma , int modeDeFirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , long pluginFirmaServidorID , java.lang.Integer upgradeSignFormat , int politicaSegellatDeTemps) {
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
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String nom , int usPoliticaDeFirma , int tipusOperacioFirma , int tipusFirma , int algorismeDeFirma , int modeDeFirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , long pluginFirmaServidorID , int politicaSegellatDeTemps) {
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
  public UsuariAplicacioConfiguracioJPA(UsuariAplicacioConfiguracio __bean) {
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



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof UsuariAplicacioConfiguracio) {
            UsuariAplicacioConfiguracio __instance = (UsuariAplicacioConfiguracio)__obj;
            __result = true;
            __result = __result && (this.getUsuariAplicacioConfigID() == __instance.getUsuariAplicacioConfigID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:usrappconfiguracio1id | Table: suf_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma1ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio1ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio1ids() {
    return this.perfilDeFirma_usrappconfiguracio1ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio1ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio1ids) {
      this.perfilDeFirma_usrappconfiguracio1ids = perfilDeFirma_usrappconfiguracio1ids;
    }


// EXP  Field:usrappconfiguracio2id | Table: suf_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma2ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio2ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio2ids() {
    return this.perfilDeFirma_usrappconfiguracio2ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio2ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio2ids) {
      this.perfilDeFirma_usrappconfiguracio2ids = perfilDeFirma_usrappconfiguracio2ids;
    }


// EXP  Field:usrappconfiguracio3id | Table: suf_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma3ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio3ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio3ids() {
    return this.perfilDeFirma_usrappconfiguracio3ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio3ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio3ids) {
      this.perfilDeFirma_usrappconfiguracio3ids = perfilDeFirma_usrappconfiguracio3ids;
    }


// EXP  Field:usrappconfiguracio4id | Table: suf_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma4ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio4ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio4ids() {
    return this.perfilDeFirma_usrappconfiguracio4ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio4ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio4ids) {
      this.perfilDeFirma_usrappconfiguracio4ids = perfilDeFirma_usrappconfiguracio4ids;
    }


// EXP  Field:usrappconfiguracio5id | Table: suf_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma5ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio5ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio5ids() {
    return this.perfilDeFirma_usrappconfiguracio5ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio5ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio5ids) {
      this.perfilDeFirma_usrappconfiguracio5ids = perfilDeFirma_usrappconfiguracio5ids;
    }


// IMP Field:pluginid | Table: suf_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pluginfirmaservidorid", referencedColumnName ="pluginID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="suf_usrappcfg_plugin_fsrv_fk"))
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariAplicacioConfiguracioJPA toJPA(UsuariAplicacioConfiguracio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioConfiguracioJPA __tmp = new UsuariAplicacioConfiguracioJPA();
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


  public static UsuariAplicacioConfiguracioJPA copyJPA(UsuariAplicacioConfiguracioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariAplicacioConfiguracioJPA> copyJPA(java.util.Set<UsuariAplicacioConfiguracioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariAplicacioConfiguracioJPA> __tmpSet = (java.util.Set<UsuariAplicacioConfiguracioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariAplicacioConfiguracioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariAplicacioConfiguracioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariAplicacioConfiguracioJPA copyJPA(UsuariAplicacioConfiguracioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariAplicacioConfiguracioJPA __tmp = (UsuariAplicacioConfiguracioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio1ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio1ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio1ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio1ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio4ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio4ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio4ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio4ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio2ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio2ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio2ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio2ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio5ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio5ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio5ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio5ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio3ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio3ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio3ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio3ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }

    return __tmp;
  }




}
