package es.caib.utilitatsfirma.persistence;

import es.caib.utilitatsfirma.model.*;
import es.caib.utilitatsfirma.model.dao.*;
import javax.persistence.EntityManager;

public final class UtilitatsFirmaJPADaoManagers implements IUtilitatsFirmaDaoManagers{

   private final EstadisticaJPAManager suf_estadistica;
   private final FitxerJPAManager suf_fitxer;
   private final IdiomaJPAManager suf_idioma;
   private final PerfilDeFirmaJPAManager suf_usuariaplicacioperfil;
   private final PerfilsPerUsuariAplicacioJPAManager suf_perfilsperusrapp;
   private final PluginJPAManager suf_plugin;
   private final TraduccioJPAManager suf_traduccio;
   private final UsuariAplicacioJPAManager suf_usuariaplicacio;
   private final UsuariAplicacioConfiguracioJPAManager suf_usuariaplicacioconfig;
   private final ValidacioJPAManager suf_validacio;

  public  UtilitatsFirmaJPADaoManagers(EntityManager __em) {
    this.suf_estadistica = new EstadisticaJPAManager(__em);
    this.suf_fitxer = new FitxerJPAManager(__em);
    this.suf_idioma = new IdiomaJPAManager(__em);
    this.suf_usuariaplicacioperfil = new PerfilDeFirmaJPAManager(__em);
    this.suf_perfilsperusrapp = new PerfilsPerUsuariAplicacioJPAManager(__em);
    this.suf_plugin = new PluginJPAManager(__em);
    this.suf_traduccio = new TraduccioJPAManager(__em);
    this.suf_usuariaplicacio = new UsuariAplicacioJPAManager(__em);
    this.suf_usuariaplicacioconfig = new UsuariAplicacioConfiguracioJPAManager(__em);
    this.suf_validacio = new ValidacioJPAManager(__em);
  }

    public IEstadisticaManager getEstadisticaManager() {
        return this.suf_estadistica;
    };

    public IFitxerManager getFitxerManager() {
        return this.suf_fitxer;
    };

    public IIdiomaManager getIdiomaManager() {
        return this.suf_idioma;
    };

    public IPerfilDeFirmaManager getPerfilDeFirmaManager() {
        return this.suf_usuariaplicacioperfil;
    };

    public IPerfilsPerUsuariAplicacioManager getPerfilsPerUsuariAplicacioManager() {
        return this.suf_perfilsperusrapp;
    };

    public IPluginManager getPluginManager() {
        return this.suf_plugin;
    };

    public ITraduccioManager getTraduccioManager() {
        return this.suf_traduccio;
    };

    public IUsuariAplicacioManager getUsuariAplicacioManager() {
        return this.suf_usuariaplicacio;
    };

    public IUsuariAplicacioConfiguracioManager getUsuariAplicacioConfiguracioManager() {
        return this.suf_usuariaplicacioconfig;
    };

    public IValidacioManager getValidacioManager() {
        return this.suf_validacio;
    };


}