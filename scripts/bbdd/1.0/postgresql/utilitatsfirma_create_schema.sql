create sequence suf_estadistica_seq start 1000 increment 1;
create sequence suf_fitxer_seq start 1000 increment 1;
create sequence suf_perfilsperusrapp_seq start 1000 increment 1;
create sequence suf_plugin_seq start 1000 increment 1;
create sequence suf_traduccio_seq start 1000 increment 1;
create sequence suf_usuariaplicacioconfig_seq start 1000 increment 1;
create sequence suf_usuariaplicacioperfil_seq start 1000 increment 1;
create sequence suf_validacio_seq start 1000 increment 1;

    create table suf_estadistica (
       estadisticaid int8 not null,
        data timestamp not null,
        parametres varchar(3000),
        tipus int4 not null,
        usuariaplicacioid varchar(101),
        usuarientitatid varchar(101),
        valor float8 default (double)1 not null,
        primary key (estadisticaid)
    );

    create table suf_fitxer (
       fitxerid int8 not null,
        descripcio varchar(1000),
        mime varchar(255) not null,
        nom varchar(255) not null,
        tamany int8 not null,
        primary key (fitxerid)
    );

    create table suf_idioma (
       idiomaid varchar(5) not null,
        nom varchar(50) not null,
        ordre int4 default 0 not null,
        suportat boolean not null,
        primary key (idiomaid)
    );

    create table suf_perfilsperusrapp (
       perfilsperusrappid int8 not null,
        usuariaplicacioperfilid int8 not null,
        usuariaplicacioid varchar(50) not null,
        primary key (perfilsperusrappid)
    );

    create table suf_plugin (
       pluginid int8 not null,
        actiu boolean not null,
        classe varchar(255) not null,
        codi varchar(255) not null,
        descripciocurtaid int8 not null,
        nomid int8 not null,
        ordre int4 not null,
        propertiesadmin text,
        tipus int4 not null,
        primary key (pluginid)
    );

    create table suf_traduccio (
       traduccioid int8 not null,
        primary key (traduccioid)
    );

    create table suf_traducciomap (
       traducciomapid int8 not null,
        valor varchar(4000),
        idiomaid varchar(255) not null,
        primary key (traducciomapid, idiomaid)
    );

    create table suf_usuariaplicacio (
       usuariaplicacioid varchar(101) not null,
        actiu boolean not null,
        descripcio varchar(255),
        emailadmin varchar(100) not null,
        primary key (usuariaplicacioid)
    );

    create table suf_usuariaplicacioconfig (
       usuariaplicacioconfigid int8 not null,
        algorismedefirmaid int4 not null,
        checkcanviatdocfirmat boolean not null,
        comprovarniffirma boolean not null,
        modedefirma int4 not null,
        nom varchar(255) not null,
        pluginfirmaservidorid int8 not null,
        policyidentifier varchar(100),
        policyidentifierhash varchar(256),
        policyidentifierhashalgorithm varchar(50),
        policyurldocument varchar(255),
        politicasegellatdetemps int4 not null,
        tipusfirmaid int4 not null,
        tipusoperaciofirma int4 default 0 not null,
        upgradesignformat int4,
        uspoliticadefirma int4 default 0 not null,
        validarfirma boolean not null,
        primary key (usuariaplicacioconfigid)
    );

    create table suf_usuariaplicacioperfil (
       usuariaplicacioperfilid int8 not null,
        codi varchar(100) not null,
        condicio varchar(4000),
        usrappconfiguracio1id int8 not null,
        usrappconfiguracio2id int8,
        usrappconfiguracio3id int8,
        usrappconfiguracio4id int8,
        usrappconfiguracio5id int8,
        descripcio varchar(500),
        nom varchar(255) not null,
        urlbase varchar(255),
        primary key (usuariaplicacioperfilid)
    );

    create table suf_validacio (
       validacioid int8 not null,
        datafi timestamp,
        datainici timestamp not null,
        detachedid int8,
        inforesultat text,
        nom varchar(255) not null,
        resultat int4,
        signaturaid int8 not null,
        primary key (validacioid)
    );
create index suf_estadistica_pk_i on suf_estadistica (estadisticaid);
create index suf_fitxer_pk_i on suf_fitxer (fitxerid);
create index suf_idioma_pk_i on suf_idioma (idiomaid);
create index pfi_perfilsperusrapp_pk_i on suf_perfilsperusrapp (perfilsperusrappid);
create index suf_perfilsua_uaperfil_fk_i on suf_perfilsperusrapp (usuariaplicacioperfilid);
create index suf_perfilsua_uaid_fk_i on suf_perfilsperusrapp (usuariaplicacioid);

    alter table suf_perfilsperusrapp 
       add constraint suf_perfilsua_multiple_uk unique (usuariaplicacioperfilid, usuariaplicacioid);
create index suf_plugin_pk_i on suf_plugin (pluginid);
create index suf_plugin_nomid_fk_i on suf_plugin (nomid);
create index suf_plugin_descrid_fk_i on suf_plugin (descripciocurtaid);

    alter table suf_plugin 
       add constraint UK_of2dbxt9swnneq24qx1hxcse1 unique (codi);

    alter table suf_plugin 
       add constraint UK_hghw13tu3fns66n90qq7n9qc0 unique (ordre);
create index suf_traduccio_pk_i on suf_traduccio (traduccioid);
create index suf_usuariaplicacio_pk_i on suf_usuariaplicacio (usuariaplicacioid);
create index suf_usuariaplicacioconfig_pk_i on suf_usuariaplicacioconfig (usuariaplicacioconfigid);
create index suf_usrappcfg_plugfirma_fk_i on suf_usuariaplicacioconfig (pluginfirmaservidorid);
create index pfi_usuariaplicacioperfil_pk_i on suf_usuariaplicacioperfil (usuariaplicacioperfilid);
create index suf_perfilapp_usrconf1_fk_i on suf_usuariaplicacioperfil (usrappconfiguracio1id);
create index suf_perfilapp_usrconf2id_fk_i on suf_usuariaplicacioperfil (usrappconfiguracio2id);
create index suf_perfilapp_usrconf3id_fk_i on suf_usuariaplicacioperfil (usrappconfiguracio3id);
create index suf_perfilapp_usrconf4id_fk_i on suf_usuariaplicacioperfil (usrappconfiguracio4id);
create index suf_perfilapp_usrconf5id_fk_i on suf_usuariaplicacioperfil (usrappconfiguracio5id);
create index suf_validacio_pk_i on suf_validacio (validacioid);
create index suf_validacio_signaturaid_fk_i on suf_validacio (signaturaid);
create index suf_validacio_detachedid_fk_i on suf_validacio (detachedid);

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
