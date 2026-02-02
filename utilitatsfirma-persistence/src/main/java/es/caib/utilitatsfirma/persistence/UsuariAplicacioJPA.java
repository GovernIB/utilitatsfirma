
package es.caib.utilitatsfirma.persistence;
import es.caib.utilitatsfirma.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "UsuariAplicacioJPA")
@Table(name = "suf_usuariaplicacio" , indexes = { 
        @Index(name="suf_usuariaplicacio_pk_i", columnList = "usuariaplicacioid")})
@SequenceGenerator(name="USUARIAPLICACIO_SEQ", sequenceName="suf_usuariaplicacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariAplicacioJPA implements UsuariAplicacio {

    @Id
    @Column(name="usuariaplicacioid",nullable = false,length = 101)
    java.lang.String usuariAplicacioID;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

  /** Correu de la persona encarregada d'aquest usuari-Màquina */
    @Column(name="emailadmin",nullable = false,length = 100)
    java.lang.String emailAdmin;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu = true;



  /** Constructor Buit */
  public UsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioJPA(java.lang.String usuariAplicacioID , java.lang.String descripcio , java.lang.String emailAdmin , boolean actiu) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.descripcio=descripcio;
    this.emailAdmin=emailAdmin;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioJPA(java.lang.String usuariAplicacioID , java.lang.String emailAdmin , boolean actiu) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.emailAdmin=emailAdmin;
    this.actiu=actiu;
}
  public UsuariAplicacioJPA(UsuariAplicacio __bean) {
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



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof UsuariAplicacio) {
            UsuariAplicacio __instance = (UsuariAplicacio)__obj;
            __result = true;
      if (this.getUsuariAplicacioID() == null) {
        __result = __result && (__instance.getUsuariAplicacioID() == null);
      } else {
        __result = __result && this.getUsuariAplicacioID().equals(__instance.getUsuariAplicacioID()) ;
      }

        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:usuariaplicacioid | Table: suf_perfilsperusrapp | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacio")
    private Set<PerfilsPerUsuariAplicacioJPA> perfilsPerUsuariAplicacios = new HashSet<PerfilsPerUsuariAplicacioJPA>(0);
    public  Set<PerfilsPerUsuariAplicacioJPA> getPerfilsPerUsuariAplicacios() {
    return this.perfilsPerUsuariAplicacios;
  }

    public void setPerfilsPerUsuariAplicacios(Set<PerfilsPerUsuariAplicacioJPA> perfilsPerUsuariAplicacios) {
      this.perfilsPerUsuariAplicacios = perfilsPerUsuariAplicacios;
    }



 // ---------------  STATIC METHODS ------------------
  public static UsuariAplicacioJPA toJPA(UsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioJPA __tmp = new UsuariAplicacioJPA();
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEmailAdmin(__bean.getEmailAdmin());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}


  public static UsuariAplicacioJPA copyJPA(UsuariAplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariAplicacioJPA> copyJPA(java.util.Set<UsuariAplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariAplicacioJPA> __tmpSet = (java.util.Set<UsuariAplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariAplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariAplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariAplicacioJPA copyJPA(UsuariAplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariAplicacioJPA __tmp = (UsuariAplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PerfilsPerUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilsPerUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilsPerUsuariAplicacios())) ) {
      __tmp.setPerfilsPerUsuariAplicacios(PerfilsPerUsuariAplicacioJPA.copyJPA(__jpa.getPerfilsPerUsuariAplicacios(), __alreadyCopied,"UsuariAplicacioJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
