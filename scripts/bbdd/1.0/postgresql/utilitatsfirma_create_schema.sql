--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2026-02-18 08:25:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 188 (class 1259 OID 162921)
-- Name: suf_estadistica_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_estadistica_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_estadistica_seq OWNER TO utilitatsfirma;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 189 (class 1259 OID 162923)
-- Name: suf_estadistica; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_estadistica (
    estadisticaid bigint DEFAULT nextval('public.suf_estadistica_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    data timestamp without time zone NOT NULL,
    valor double precision DEFAULT 1 NOT NULL,
    parametres character varying(3000),
    usuariaplicacioid character varying(101),
    usuarientitatid character varying(101)
);


ALTER TABLE public.suf_estadistica OWNER TO utilitatsfirma;

--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN suf_estadistica.tipus; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_estadistica.tipus IS 'Ha de ser combobox';


--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN suf_estadistica.usuariaplicacioid; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_estadistica.usuariaplicacioid IS 'No te la clau forània amb pfi_usuariaplicacio ja que si s''esborra l''usuari aplicació, haurien de quedar les estadistiques.';


--
-- TOC entry 171 (class 1259 OID 162588)
-- Name: suf_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_fitxer_seq OWNER TO utilitatsfirma;

--
-- TOC entry 173 (class 1259 OID 162592)
-- Name: suf_fitxer; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_fitxer (
    fitxerid bigint DEFAULT nextval('public.suf_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.suf_fitxer OWNER TO utilitatsfirma;

--
-- TOC entry 174 (class 1259 OID 162600)
-- Name: suf_idioma; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.suf_idioma OWNER TO utilitatsfirma;

--
-- TOC entry 184 (class 1259 OID 162787)
-- Name: suf_perfilsperusrapp_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_perfilsperusrapp_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_perfilsperusrapp_seq OWNER TO utilitatsfirma;

--
-- TOC entry 185 (class 1259 OID 162789)
-- Name: suf_perfilsperusrapp; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_perfilsperusrapp (
    perfilsperusrappid bigint DEFAULT nextval('public.suf_perfilsperusrapp_seq'::regclass) NOT NULL,
    usuariaplicacioperfilid bigint NOT NULL,
    usuariaplicacioid character varying(50) NOT NULL
);


ALTER TABLE public.suf_perfilsperusrapp OWNER TO utilitatsfirma;

--
-- TOC entry 177 (class 1259 OID 162633)
-- Name: suf_plugin_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_plugin_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_plugin_seq OWNER TO utilitatsfirma;

--
-- TOC entry 178 (class 1259 OID 162635)
-- Name: suf_plugin; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_plugin (
    pluginid bigint DEFAULT nextval('public.suf_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    propertiesadmin text,
    actiu boolean NOT NULL,
    tipus integer NOT NULL,
    codi character varying(255) NOT NULL,
    ordre integer NOT NULL
);


ALTER TABLE public.suf_plugin OWNER TO utilitatsfirma;

--
-- TOC entry 190 (class 1259 OID 172096)
-- Name: suf_tipusdocumental; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_tipusdocumental (
    tipusdocumentalid bigint NOT NULL,
    paretipusdocumentalid bigint,
    nomcatala character varying(255) NOT NULL,
    nomcastella character varying(256) NOT NULL,
    descripciocatala character varying(256),
    descripciocastella character varying(256)
);


ALTER TABLE public.suf_tipusdocumental OWNER TO utilitatsfirma;

--
-- TOC entry 172 (class 1259 OID 162590)
-- Name: suf_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_traduccio_seq OWNER TO utilitatsfirma;

--
-- TOC entry 175 (class 1259 OID 162605)
-- Name: suf_traduccio; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_traduccio (
    traduccioid bigint DEFAULT nextval('public.suf_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.suf_traduccio OWNER TO utilitatsfirma;

--
-- TOC entry 176 (class 1259 OID 162609)
-- Name: suf_traducciomap; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.suf_traducciomap OWNER TO utilitatsfirma;

--
-- TOC entry 181 (class 1259 OID 162692)
-- Name: suf_usuariaplicacio; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_usuariaplicacio (
    usuariaplicacioid character varying(101) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    emailadmin character varying(100) NOT NULL,
    actiu boolean DEFAULT true NOT NULL
);


ALTER TABLE public.suf_usuariaplicacio OWNER TO utilitatsfirma;

--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 181
-- Name: TABLE suf_usuariaplicacio; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON TABLE public.suf_usuariaplicacio IS 'Usuari de tipus màquina que realitzarà peticions a SUF';


--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN suf_usuariaplicacio.emailadmin; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_usuariaplicacio.emailadmin IS 'Correu de la persona encarregada d''aquest usuari-Màquina';


--
-- TOC entry 182 (class 1259 OID 162697)
-- Name: suf_usuariaplicacioconfig_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_usuariaplicacioconfig_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_usuariaplicacioconfig_seq OWNER TO utilitatsfirma;

--
-- TOC entry 183 (class 1259 OID 162715)
-- Name: suf_usuariaplicacioconfig; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_usuariaplicacioconfig (
    usuariaplicacioconfigid bigint DEFAULT nextval('public.suf_usuariaplicacioconfig_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    policyidentifier character varying(100),
    policyidentifierhash character varying(256),
    policyidentifierhashalgorithm character varying(50),
    policyurldocument character varying(255),
    tipusoperaciofirma integer DEFAULT 0 NOT NULL,
    tipusfirmaid integer NOT NULL,
    algorismedefirmaid integer NOT NULL,
    modedefirma integer NOT NULL,
    comprovarniffirma boolean NOT NULL,
    checkcanviatdocfirmat boolean NOT NULL,
    validarfirma boolean NOT NULL,
    pluginfirmaservidorid bigint NOT NULL,
    upgradesignformat integer,
    politicasegellatdetemps integer NOT NULL,
    uspoliticadefirma integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.suf_usuariaplicacioconfig OWNER TO utilitatsfirma;

--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN suf_usuariaplicacioconfig.tipusoperaciofirma; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_usuariaplicacioconfig.tipusoperaciofirma IS '0 firma, 1 contrafirma 2, cofirma';


--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN suf_usuariaplicacioconfig.comprovarniffirma; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_usuariaplicacioconfig.comprovarniffirma IS 'Null => Valor definit a l''entitat';


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN suf_usuariaplicacioconfig.checkcanviatdocfirmat; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_usuariaplicacioconfig.checkcanviatdocfirmat IS '-- Null => Valor definit a l''entitat';


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN suf_usuariaplicacioconfig.validarfirma; Type: COMMENT; Schema: public; Owner: utilitatsfirma
--

COMMENT ON COLUMN public.suf_usuariaplicacioconfig.validarfirma IS 'Indica si validar la firma amb el Plugin de validació definit a l''entitat';


--
-- TOC entry 186 (class 1259 OID 162793)
-- Name: suf_usuariaplicacioperfil_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_usuariaplicacioperfil_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_usuariaplicacioperfil_seq OWNER TO utilitatsfirma;

--
-- TOC entry 187 (class 1259 OID 162795)
-- Name: suf_usuariaplicacioperfil; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_usuariaplicacioperfil (
    usuariaplicacioperfilid bigint DEFAULT nextval('public.suf_usuariaplicacioperfil_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(500),
    condicio character varying(4000),
    usrappconfiguracio1id bigint NOT NULL,
    usrappconfiguracio2id bigint,
    usrappconfiguracio3id bigint,
    codi character varying(100) NOT NULL,
    usrappconfiguracio4id bigint,
    usrappconfiguracio5id bigint,
    urlbase character varying(255)
);


ALTER TABLE public.suf_usuariaplicacioperfil OWNER TO utilitatsfirma;

--
-- TOC entry 179 (class 1259 OID 162661)
-- Name: suf_validacio_seq; Type: SEQUENCE; Schema: public; Owner: utilitatsfirma
--

CREATE SEQUENCE public.suf_validacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.suf_validacio_seq OWNER TO utilitatsfirma;

--
-- TOC entry 180 (class 1259 OID 162663)
-- Name: suf_validacio; Type: TABLE; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE TABLE public.suf_validacio (
    validacioid bigint DEFAULT nextval('public.suf_validacio_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    signaturaid bigint NOT NULL,
    detachedid bigint,
    resultat integer,
    inforesultat text,
    datainici timestamp without time zone NOT NULL,
    datafi timestamp without time zone
);


ALTER TABLE public.suf_validacio OWNER TO utilitatsfirma;

--
-- TOC entry 1955 (class 2606 OID 162932)
-- Name: suf_estadistica_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_estadistica
    ADD CONSTRAINT suf_estadistica_pk PRIMARY KEY (estadisticaid);


--
-- TOC entry 1906 (class 2606 OID 162616)
-- Name: suf_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_fitxer
    ADD CONSTRAINT suf_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1909 (class 2606 OID 162618)
-- Name: suf_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_idioma
    ADD CONSTRAINT suf_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1941 (class 2606 OID 162803)
-- Name: suf_perfilsperusrapp_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_perfilsperusrapp
    ADD CONSTRAINT suf_perfilsperusrapp_pk PRIMARY KEY (perfilsperusrappid);


--
-- TOC entry 1943 (class 2606 OID 162878)
-- Name: suf_perfilsua_multiple_uk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_perfilsperusrapp
    ADD CONSTRAINT suf_perfilsua_multiple_uk UNIQUE (usuariaplicacioperfilid, usuariaplicacioid);


--
-- TOC entry 1919 (class 2606 OID 172106)
-- Name: suf_plugin_codi_tipus_uk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_plugin
    ADD CONSTRAINT suf_plugin_codi_tipus_uk UNIQUE (codi, tipus);


--
-- TOC entry 1923 (class 2606 OID 172108)
-- Name: suf_plugin_ordre_tipus_uk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_plugin
    ADD CONSTRAINT suf_plugin_ordre_tipus_uk UNIQUE (ordre, tipus);


--
-- TOC entry 1925 (class 2606 OID 162643)
-- Name: suf_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_plugin
    ADD CONSTRAINT suf_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 1958 (class 2606 OID 172110)
-- Name: suf_tipusdocumental_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_tipusdocumental
    ADD CONSTRAINT suf_tipusdocumental_pk PRIMARY KEY (tipusdocumentalid);


--
-- TOC entry 1912 (class 2606 OID 162620)
-- Name: suf_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_traduccio
    ADD CONSTRAINT suf_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1917 (class 2606 OID 162622)
-- Name: suf_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_traducciomap
    ADD CONSTRAINT suf_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1933 (class 2606 OID 162738)
-- Name: suf_usuariaplicacio_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_usuariaplicacio
    ADD CONSTRAINT suf_usuariaplicacio_pk PRIMARY KEY (usuariaplicacioid);


--
-- TOC entry 1937 (class 2606 OID 162745)
-- Name: suf_usuariaplicacioconfig_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_usuariaplicacioconfig
    ADD CONSTRAINT suf_usuariaplicacioconfig_pk PRIMARY KEY (usuariaplicacioconfigid);


--
-- TOC entry 1953 (class 2606 OID 162807)
-- Name: suf_usuariaplicacioperfil_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_usuariaplicacioperfil
    ADD CONSTRAINT suf_usuariaplicacioperfil_pk PRIMARY KEY (usuariaplicacioperfilid);


--
-- TOC entry 1929 (class 2606 OID 162671)
-- Name: suf_validacio_pk; Type: CONSTRAINT; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

ALTER TABLE ONLY public.suf_validacio
    ADD CONSTRAINT suf_validacio_pk PRIMARY KEY (validacioid);


--
-- TOC entry 1939 (class 1259 OID 162813)
-- Name: pfi_perfilsperusrapp_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX pfi_perfilsperusrapp_pk_i ON public.suf_perfilsperusrapp USING btree (perfilsperusrappid);


--
-- TOC entry 1946 (class 1259 OID 162816)
-- Name: pfi_usuariaplicacioperfil_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX pfi_usuariaplicacioperfil_pk_i ON public.suf_usuariaplicacioperfil USING btree (usuariaplicacioperfilid);


--
-- TOC entry 1956 (class 1259 OID 162933)
-- Name: suf_estadistica_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_estadistica_pk_i ON public.suf_estadistica USING btree (estadisticaid);


--
-- TOC entry 1907 (class 1259 OID 162623)
-- Name: suf_fitxer_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_fitxer_pk_i ON public.suf_fitxer USING btree (fitxerid);


--
-- TOC entry 1910 (class 1259 OID 162624)
-- Name: suf_idioma_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_idioma_pk_i ON public.suf_idioma USING btree (idiomaid);


--
-- TOC entry 1947 (class 1259 OID 162808)
-- Name: suf_perfilapp_usrconf1_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilapp_usrconf1_fk_i ON public.suf_usuariaplicacioperfil USING btree (usrappconfiguracio1id);


--
-- TOC entry 1948 (class 1259 OID 162809)
-- Name: suf_perfilapp_usrconf2id_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilapp_usrconf2id_fk_i ON public.suf_usuariaplicacioperfil USING btree (usrappconfiguracio2id);


--
-- TOC entry 1949 (class 1259 OID 162810)
-- Name: suf_perfilapp_usrconf3id_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilapp_usrconf3id_fk_i ON public.suf_usuariaplicacioperfil USING btree (usrappconfiguracio3id);


--
-- TOC entry 1950 (class 1259 OID 162811)
-- Name: suf_perfilapp_usrconf4id_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilapp_usrconf4id_fk_i ON public.suf_usuariaplicacioperfil USING btree (usrappconfiguracio4id);


--
-- TOC entry 1951 (class 1259 OID 162812)
-- Name: suf_perfilapp_usrconf5id_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilapp_usrconf5id_fk_i ON public.suf_usuariaplicacioperfil USING btree (usrappconfiguracio5id);


--
-- TOC entry 1944 (class 1259 OID 162815)
-- Name: suf_perfilsua_uaid_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilsua_uaid_fk_i ON public.suf_perfilsperusrapp USING btree (usuariaplicacioid);


--
-- TOC entry 1945 (class 1259 OID 162814)
-- Name: suf_perfilsua_uaperfil_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_perfilsua_uaperfil_fk_i ON public.suf_perfilsperusrapp USING btree (usuariaplicacioperfilid);


--
-- TOC entry 1920 (class 1259 OID 162656)
-- Name: suf_plugin_descrid_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_plugin_descrid_fk_i ON public.suf_plugin USING btree (descripciocurtaid);


--
-- TOC entry 1921 (class 1259 OID 162655)
-- Name: suf_plugin_nomid_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_plugin_nomid_fk_i ON public.suf_plugin USING btree (nomid);


--
-- TOC entry 1926 (class 1259 OID 162654)
-- Name: suf_plugin_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_plugin_pk_i ON public.suf_plugin USING btree (pluginid);


--
-- TOC entry 1959 (class 1259 OID 172111)
-- Name: suf_tipusdocumental_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_tipusdocumental_pk_i ON public.suf_tipusdocumental USING btree (tipusdocumentalid);


--
-- TOC entry 1913 (class 1259 OID 162625)
-- Name: suf_traduccio_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_traduccio_pk_i ON public.suf_traduccio USING btree (traduccioid);


--
-- TOC entry 1914 (class 1259 OID 162626)
-- Name: suf_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_traducciomap_idiomaid_fk_i ON public.suf_traducciomap USING btree (idiomaid);


--
-- TOC entry 1915 (class 1259 OID 162627)
-- Name: suf_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_traducciomap_pk_i ON public.suf_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1935 (class 1259 OID 162776)
-- Name: suf_usrappcfg_plugfirma_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_usrappcfg_plugfirma_fk_i ON public.suf_usuariaplicacioconfig USING btree (pluginfirmaservidorid);


--
-- TOC entry 1934 (class 1259 OID 162769)
-- Name: suf_usuariaplicacio_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_usuariaplicacio_pk_i ON public.suf_usuariaplicacio USING btree (usuariaplicacioid);


--
-- TOC entry 1938 (class 1259 OID 162770)
-- Name: suf_usuariaplicacioconfig_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_usuariaplicacioconfig_pk_i ON public.suf_usuariaplicacioconfig USING btree (usuariaplicacioconfigid);


--
-- TOC entry 1927 (class 1259 OID 162684)
-- Name: suf_validacio_detachedid_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_validacio_detachedid_fk_i ON public.suf_validacio USING btree (detachedid);


--
-- TOC entry 1930 (class 1259 OID 162682)
-- Name: suf_validacio_pk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_validacio_pk_i ON public.suf_validacio USING btree (validacioid);


--
-- TOC entry 1931 (class 1259 OID 162683)
-- Name: suf_validacio_signaturaid_fk_i; Type: INDEX; Schema: public; Owner: utilitatsfirma; Tablespace: 
--

CREATE INDEX suf_validacio_signaturaid_fk_i ON public.suf_validacio USING btree (signaturaid);


--
-- TOC entry 1968 (class 2606 OID 162889)
-- Name: suf_perfilapp_usrappcfg_c1_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_usuariaplicacioperfil
    ADD CONSTRAINT suf_perfilapp_usrappcfg_c1_fk FOREIGN KEY (usrappconfiguracio1id) REFERENCES public.suf_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 1969 (class 2606 OID 162894)
-- Name: suf_perfilapp_usrappcfg_c2_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_usuariaplicacioperfil
    ADD CONSTRAINT suf_perfilapp_usrappcfg_c2_fk FOREIGN KEY (usrappconfiguracio2id) REFERENCES public.suf_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 1970 (class 2606 OID 162899)
-- Name: suf_perfilapp_usrappcfg_c3_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_usuariaplicacioperfil
    ADD CONSTRAINT suf_perfilapp_usrappcfg_c3_fk FOREIGN KEY (usrappconfiguracio3id) REFERENCES public.suf_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 1971 (class 2606 OID 162904)
-- Name: suf_perfilapp_usrappcfg_c4_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_usuariaplicacioperfil
    ADD CONSTRAINT suf_perfilapp_usrappcfg_c4_fk FOREIGN KEY (usrappconfiguracio4id) REFERENCES public.suf_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 1972 (class 2606 OID 162909)
-- Name: suf_perfilapp_usrappcfg_c5_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_usuariaplicacioperfil
    ADD CONSTRAINT suf_perfilapp_usrappcfg_c5_fk FOREIGN KEY (usrappconfiguracio5id) REFERENCES public.suf_usuariaplicacioconfig(usuariaplicacioconfigid);


--
-- TOC entry 1967 (class 2606 OID 162914)
-- Name: suf_perfilsua_perfilapp_up_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_perfilsperusrapp
    ADD CONSTRAINT suf_perfilsua_perfilapp_up_fk FOREIGN KEY (usuariaplicacioperfilid) REFERENCES public.suf_usuariaplicacioperfil(usuariaplicacioperfilid);


--
-- TOC entry 1966 (class 2606 OID 162884)
-- Name: suf_perfilsua_usrapp_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_perfilsperusrapp
    ADD CONSTRAINT suf_perfilsua_usrapp_usuari_fk FOREIGN KEY (usuariaplicacioid) REFERENCES public.suf_usuariaplicacio(usuariaplicacioid);


--
-- TOC entry 1962 (class 2606 OID 162649)
-- Name: suf_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_plugin
    ADD CONSTRAINT suf_plugin_traduccio_desc_fk FOREIGN KEY (descripciocurtaid) REFERENCES public.suf_traduccio(traduccioid);


--
-- TOC entry 1961 (class 2606 OID 162644)
-- Name: suf_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_plugin
    ADD CONSTRAINT suf_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES public.suf_traduccio(traduccioid);


--
-- TOC entry 1960 (class 2606 OID 162628)
-- Name: suf_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_traducciomap
    ADD CONSTRAINT suf_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.suf_traduccio(traduccioid);


--
-- TOC entry 1965 (class 2606 OID 162751)
-- Name: suf_usrappcfg_plugin_fsrv_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_usuariaplicacioconfig
    ADD CONSTRAINT suf_usrappcfg_plugin_fsrv_fk FOREIGN KEY (pluginfirmaservidorid) REFERENCES public.suf_plugin(pluginid);


--
-- TOC entry 1964 (class 2606 OID 162677)
-- Name: suf_validacio_fitxer_det_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_validacio
    ADD CONSTRAINT suf_validacio_fitxer_det_fk FOREIGN KEY (detachedid) REFERENCES public.suf_fitxer(fitxerid);


--
-- TOC entry 1963 (class 2606 OID 162672)
-- Name: suf_validacio_fitxer_sig_fk; Type: FK CONSTRAINT; Schema: public; Owner: utilitatsfirma
--

ALTER TABLE ONLY public.suf_validacio
    ADD CONSTRAINT suf_validacio_fitxer_sig_fk FOREIGN KEY (signaturaid) REFERENCES public.suf_fitxer(fitxerid);


--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2026-02-18 08:25:57

--
-- PostgreSQL database dump complete
--

