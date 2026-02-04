
    create table suf_estadistica (
       estadisticaid number(19,0) not null,
        data timestamp not null,
        parametres varchar2(3000 char),
        tipus number(10,0) not null,
        usuariaplicacioid varchar2(101 char),
        usuarientitatid varchar2(101 char),
        valor double precision default (double)1 not null
    );

    create table suf_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null
    );

    create table suf_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) default 0 not null,
        suportat number(1,0) not null
    );

    create table suf_perfilsperusrapp (
       perfilsperusrappid number(19,0) not null,
        usuariaplicacioperfilid number(19,0) not null,
        usuariaplicacioid varchar2(50 char) not null
    );

    create table suf_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char) not null,
        codi varchar2(255 char) not null,
        descripciocurtaid number(19,0) not null,
        nomid number(19,0) not null,
        ordre number(10,0) not null,
        propertiesadmin clob,
        tipus number(10,0) not null
    );

    create table suf_traduccio (
       traduccioid number(19,0) not null
    );

    create table suf_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null
    );

    create table suf_usuariaplicacio (
       usuariaplicacioid varchar2(101 char) not null,
        actiu number(1,0) not null,
        descripcio varchar2(255 char),
        emailadmin varchar2(100 char) not null
    );

    create table suf_usuariaplicacioconfig (
       usuariaplicacioconfigid number(19,0) not null,
        algorismedefirmaid number(10,0) not null,
        checkcanviatdocfirmat number(1,0) not null,
        comprovarniffirma number(1,0) not null,
        modedefirma number(10,0) not null,
        nom varchar2(255 char) not null,
        pluginfirmaservidorid number(19,0) not null,
        policyidentifier varchar2(100 char),
        policyidentifierhash varchar2(256 char),
        policyidentifierhashalgorithm varchar2(50 char),
        policyurldocument varchar2(255 char),
        politicasegellatdetemps number(10,0) not null,
        tipusfirmaid number(10,0) not null,
        tipusoperaciofirma number(10,0) default 0 not null,
        upgradesignformat number(10,0),
        uspoliticadefirma number(10,0) default 0 not null,
        validarfirma number(1,0) not null
    );

    create table suf_usuariaplicacioperfil (
       usuariaplicacioperfilid number(19,0) not null,
        codi varchar2(100 char) not null,
        condicio varchar2(4000 char),
        usrappconfiguracio1id number(19,0) not null,
        usrappconfiguracio2id number(19,0),
        usrappconfiguracio3id number(19,0),
        usrappconfiguracio4id number(19,0),
        usrappconfiguracio5id number(19,0),
        descripcio varchar2(500 char),
        nom varchar2(255 char) not null,
        urlbase varchar2(255 char)
    );

    create table suf_validacio (
       validacioid number(19,0) not null,
        datafi timestamp,
        datainici timestamp not null,
        detachedid number(19,0),
        inforesultat clob,
        nom varchar2(255 char) not null,
        resultat number(10,0),
        signaturaid number(19,0) not null
    );





