
 -- INICI PKs
    alter table suf_estadistica add constraint suf_estadistica_pk primary key (estadisticaid);

    alter table suf_fitxer add constraint suf_fitxer_pk primary key (fitxerid);

    alter table suf_idioma add constraint suf_idioma_pk primary key (idiomaid);

    alter table suf_perfilsperusrapp add constraint suf_perfilsperusrapp_pk primary key (perfilsperusrappid);

    alter table suf_plugin add constraint suf_plugin_pk primary key (pluginid);

    alter table suf_traduccio add constraint suf_traduccio_pk primary key (traduccioid);

    alter table suf_traducciomap add constraint suf_traducmap_pk primary key (traducciomapid, idiomaid);

    alter table suf_usuariaplicacio add constraint suf_usuariaplicacio_pk primary key (usuariaplicacioid);

    alter table suf_usuariaplicacioconfig add constraint suf_usuariaplicacioconfig_pk primary key (usuariaplicacioconfigid);

    alter table suf_usuariaplicacioperfil add constraint suf_usuariaplicacioperfil_pk primary key (usuariaplicacioperfilid);

    alter table suf_validacio add constraint suf_validacio_pk primary key (validacioid);

 -- FINAL PKs


 -- INICI FKs

    alter table suf_perfilsperusrapp 
       add constraint suf_perfilsua_perfilapp_up_fk 
       foreign key (usuariaplicacioperfilid) 
       references suf_usuariaplicacioperfil;

    alter table suf_perfilsperusrapp 
       add constraint suf_perfilsua_usrapp_usuari_fk 
       foreign key (usuariaplicacioid) 
       references suf_usuariaplicacio;

    alter table suf_plugin 
       add constraint suf_plugin_traduccio_desc_fk 
       foreign key (descripciocurtaid) 
       references suf_traduccio;

    alter table suf_plugin 
       add constraint suf_plugin_traduccio_nom_fk 
       foreign key (nomid) 
       references suf_traduccio;

    alter table suf_traducciomap 
       add constraint suf_traducmap_traduccio_fk 
       foreign key (traducciomapid) 
       references suf_traduccio;

    alter table suf_usuariaplicacioconfig 
       add constraint suf_usrappcfg_plugin_fsrv_fk 
       foreign key (pluginfirmaservidorid) 
       references suf_plugin;

    alter table suf_usuariaplicacioperfil 
       add constraint suf_perfilapp_usrappcfg_c1_fk 
       foreign key (usrappconfiguracio1id) 
       references suf_usuariaplicacioconfig;

    alter table suf_usuariaplicacioperfil 
       add constraint suf_perfilapp_usrappcfg_c2_fk 
       foreign key (usrappconfiguracio2id) 
       references suf_usuariaplicacioconfig;

    alter table suf_usuariaplicacioperfil 
       add constraint suf_perfilapp_usrappcfg_c3_fk 
       foreign key (usrappconfiguracio3id) 
       references suf_usuariaplicacioconfig;

    alter table suf_usuariaplicacioperfil 
       add constraint suf_perfilapp_usrappcfg_c4_fk 
       foreign key (usrappconfiguracio4id) 
       references suf_usuariaplicacioconfig;

    alter table suf_usuariaplicacioperfil 
       add constraint suf_perfilapp_usrappcfg_c5_fk 
       foreign key (usrappconfiguracio5id) 
       references suf_usuariaplicacioconfig;

    alter table suf_validacio 
       add constraint suf_validacio_fitxer_det_fk 
       foreign key (detachedid) 
       references suf_fitxer;

    alter table suf_validacio 
       add constraint suf_validacio_fitxer_sig_fk 
       foreign key (signaturaid) 
       references suf_fitxer;
 -- FINAL FKs


 -- INICI UNIQUEs

    alter table suf_perfilsperusrapp 
       add constraint suf_perfilsua_multiple_uk unique (usuariaplicacioperfilid, usuariaplicacioid);

    alter table suf_plugin 
       add constraint UK_of2dbxt9swnneq24qx1hxcse1 unique (codi);

    alter table suf_plugin 
       add constraint UK_hghw13tu3fns66n90qq7n9qc0 unique (ordre);
 -- FINAL UNIQUEs

