

CREATE SEQUENCE suf_fitxer_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;
CREATE SEQUENCE suf_traduccio_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1000;

CREATE TABLE suf_fitxer (
    fitxerid bigint DEFAULT nextval('suf_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);

CREATE TABLE suf_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);

CREATE TABLE suf_traduccio (
    traduccioid bigint DEFAULT nextval('suf_traduccio_seq'::regclass) NOT NULL
);

CREATE TABLE suf_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);

ALTER TABLE ONLY suf_fitxer
    ADD CONSTRAINT suf_fitxer_pk PRIMARY KEY (fitxerid);

ALTER TABLE ONLY suf_idioma
    ADD CONSTRAINT suf_idioma_pk PRIMARY KEY (idiomaid);

ALTER TABLE ONLY suf_traduccio
    ADD CONSTRAINT suf_traduccio_pk PRIMARY KEY (traduccioid);

ALTER TABLE ONLY suf_traducciomap
    ADD CONSTRAINT suf_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);

CREATE INDEX suf_fitxer_pk_i ON suf_fitxer USING btree (fitxerid);

CREATE INDEX suf_idioma_pk_i ON suf_idioma USING btree (idiomaid);

CREATE INDEX suf_traduccio_pk_i ON suf_traduccio USING btree (traduccioid);

CREATE INDEX suf_traducciomap_idiomaid_fk_i ON suf_traducciomap USING btree (idiomaid);

CREATE INDEX suf_traducciomap_pk_i ON suf_traducciomap USING btree (traducciomapid);

ALTER TABLE ONLY suf_traducciomap
    ADD CONSTRAINT suf_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES suf_traduccio(traduccioid);

INSERT INTO suf_idioma(idiomaid, nom, ordre) VALUES ('ca', 'Català', 0);
INSERT INTO suf_idioma(idiomaid, nom, ordre) VALUES ('es', 'Castellano', 1);
INSERT INTO suf_idioma(idiomaid, nom, ordre) VALUES ('en', 'English', 2);
    
    
