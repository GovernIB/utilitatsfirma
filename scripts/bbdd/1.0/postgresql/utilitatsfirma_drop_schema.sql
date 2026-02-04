
    alter table suf_perfilsperusrapp 
       drop constraint suf_perfilsua_perfilapp_up_fk;

    alter table suf_perfilsperusrapp 
       drop constraint suf_perfilsua_usrapp_usuari_fk;

    alter table suf_plugin 
       drop constraint suf_plugin_traduccio_desc_fk;

    alter table suf_plugin 
       drop constraint suf_plugin_traduccio_nom_fk;

    alter table suf_traducciomap 
       drop constraint suf_traducmap_traduccio_fk;

    alter table suf_usuariaplicacioconfig 
       drop constraint suf_usrappcfg_plugin_fsrv_fk;

    alter table suf_usuariaplicacioperfil 
       drop constraint suf_perfilapp_usrappcfg_c1_fk;

    alter table suf_usuariaplicacioperfil 
       drop constraint suf_perfilapp_usrappcfg_c2_fk;

    alter table suf_usuariaplicacioperfil 
       drop constraint suf_perfilapp_usrappcfg_c3_fk;

    alter table suf_usuariaplicacioperfil 
       drop constraint suf_perfilapp_usrappcfg_c4_fk;

    alter table suf_usuariaplicacioperfil 
       drop constraint suf_perfilapp_usrappcfg_c5_fk;

    alter table suf_validacio 
       drop constraint suf_validacio_fitxer_det_fk;

    alter table suf_validacio 
       drop constraint suf_validacio_fitxer_sig_fk;

    drop table if exists suf_estadistica cascade;

    drop table if exists suf_fitxer cascade;

    drop table if exists suf_idioma cascade;

    drop table if exists suf_perfilsperusrapp cascade;

    drop table if exists suf_plugin cascade;

    drop table if exists suf_traduccio cascade;

    drop table if exists suf_traducciomap cascade;

    drop table if exists suf_usuariaplicacio cascade;

    drop table if exists suf_usuariaplicacioconfig cascade;

    drop table if exists suf_usuariaplicacioperfil cascade;

    drop table if exists suf_validacio cascade;

    drop sequence if exists suf_estadistica_seq;

    drop sequence if exists suf_fitxer_seq;

    drop sequence if exists suf_perfilsperusrapp_seq;

    drop sequence if exists suf_plugin_seq;

    drop sequence if exists suf_traduccio_seq;

    drop sequence if exists suf_usuariaplicacioconfig_seq;

    drop sequence if exists suf_usuariaplicacioperfil_seq;

    drop sequence if exists suf_validacio_seq;
