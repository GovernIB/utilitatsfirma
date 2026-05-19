
BEGIN;


INSERT INTO suf_idioma(idiomaid, nom, ordre, suportat) VALUES ('ca', 'Català', 0, true);
INSERT INTO suf_idioma(idiomaid, nom, ordre, suportat) VALUES ('es', 'Castellano', 1, true);
INSERT INTO suf_idioma(idiomaid, nom, ordre, suportat) VALUES ('en', 'English', 2, false);



-- ------------------------------------
--   TIPUS DOCUMENTALS EN BBDD
-- ------------------------------------

INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (1, null, 'TD01 - Resolució', 'TD01 - Resolución', 'TD01 - Resolució', 'TD01 - Resolución');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (2, null, 'TD02 - Acord', 'TD02 - Acord', 'TD02 - Acord', 'TD02 - Acord');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (3, null, 'TD03 - Contracte', 'TD03 - Contrato', 'TD03 - Contracte', 'TD03 - Contrato');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (4, null, 'TD04 - Conveni', 'TD04 - Convenio', 'TD04 - Conveni', 'TD04 - Convenio');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (5, null, 'TD05 - Declaració', 'TD05 - Declaración', 'TD05 - Declaració', 'TD05 - Declaración');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (6, null, 'TD06 - Comunicació', 'TD06 - Comunicación', 'TD06 - Comunicació', 'TD06 - Comunicación');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (7, null, 'TD07 - Notificació', 'TD07 - Notificación', 'TD07 - Notificació', 'TD07 - Notificación');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (8, null, 'TD08 - Publicació', 'TD08 - Publicación', 'TD08 - Publicació', 'TD08 - Publicación');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (9, null, 'TD09 - Justificant de Recepció', 'TD09 - Justificante de Recepción', 'TD09 - Justificant de Recepció', 'TD09 - Justificante de Recepción');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (10, null, 'TD10 - Acta', 'TD10 - Acta', 'TD10 - Acta', 'TD10 - Acta');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (11, null, 'TD11 - Certificat', 'TD11 - Certificado', 'TD11 - Certificat', 'TD11 - Certificado');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (12, null, 'TD12 - Diligencia', 'TD12 - Diligencia', 'TD12 - Diligencia', 'TD12 - Diligencia');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (13, null, 'TD13 - Informe', 'TD13 - Informe', 'TD13 - Informe', 'TD13 - Informe');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (14, null, 'TD14 - Sol·licitud', 'TD14 - Solicitud', 'TD14 - Sol·licitud', 'TD14 - Solicitud');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (15, null, 'TD15 - Denuncia', 'TD15 - Denuncia', 'TD15 - Denuncia', 'TD15 - Denuncia');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (16, null, 'TD16 - Al·legació', 'TD16 - Alegación', 'TD16 - Al·legació', 'TD16 - Alegación');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (17, null, 'TD17 - Recurs', 'TD17 - Recurso', 'TD17 - Recurs', 'TD17 - Recurso');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (18, null, 'TD18 - Comunicació Ciutadà', 'TD18 - Comunicación Ciudadano', 'TD18 - Comunicació Ciutadà', 'TD18 - Comunicación Ciudadano');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (19, null, 'TD19 - Factura', 'TD19 - Factura', 'TD19 - Factura', 'TD19 - Factura');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (20, null, 'TD20 - Altres Encautats', 'TD20 - Otros Encautados', 'TD20 - Altres Encautats', 'TD20 - Otros Encautados');
INSERT INTO suf_tipusdocumental(tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella)  VALUES (99, null, 'TD99 - Altres', 'TD99 - Otros', 'TD99 - Altres', 'TD99 - Otros');


-- ------------------------------------
--  PLUGINS DE TIPUS DOCUMENTALS
-- ------------------------------------

INSERT INTO suf_traduccio(traduccioid) VALUES (104);
INSERT INTO suf_traduccio(traduccioid) VALUES (105);
INSERT INTO suf_traduccio(traduccioid) VALUES (106);
INSERT INTO suf_traduccio(traduccioid) VALUES (107);
INSERT INTO suf_traduccio(traduccioid) VALUES (108);
INSERT INTO suf_traduccio(traduccioid) VALUES (109);

INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (104, 'ca', 'Plugin Tipus Documentals de NTI');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (104, 'es', 'Plugin Tipos Documentals de NTI');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (105, 'ca', 'Plugin Tipus Documentals definits en les Normes Tècniques d''Interoperabilitat (NTI)');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (105, 'es', 'Plugin Tipos Documentales definidos en las Normas Técnicas de Interoperabilidad (NTI)');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (106, 'ca', 'Plugin de Tipus Documentals de PortaFIB');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (106, 'es', 'Plugin de Tipos Documentales de PortaFIB');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (107, 'ca', 'Plugin de Tipus Documentals de PortaFIB');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (107, 'es', 'Plugin de Tipos Documentales de PortaFIB');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (108, 'ca', 'Plugin de Tipus Documentals d''una Base de Dades');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (108, 'es', 'Plugin de Tipos Documentales de una Base de Datos');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (109, 'ca', 'Plugin de Tipus Documentals d''una Base de Dades');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (109, 'es', 'Plugin de Tipos Documentales de una Base de Datos');


INSERT INTO suf_plugin(pluginid, nomid, descripciocurtaid, classe, propertiesadmin, actiu, tipus, codi, ordre) VALUES (100, 104, 105, 'org.fundaciobit.pluginsib.tipusdocumental.nti.PluginTipusDocumentalNormesTecniquesInteroperabilitat', '# No té propietats', true, 4, '1', 1);

INSERT INTO suf_plugin(pluginid, nomid, descripciocurtaid, classe, propertiesadmin, actiu, tipus, codi, ordre) VALUES (102, 106, 107, 'org.fundaciobit.pluginsib.tipusdocumental.portafib.PluginTipusDocumentalPortaFIB', 'es.caib.utilitatsfirma.pluginsib.tipusdocumental.portafib.url=http://HOST:PORT/portafibapi/interna/secure
es.caib.utilitatsfirma.pluginsib.tipusdocumental.portafib.username=app_username
es.caib.utilitatsfirma.pluginsib.tipusdocumental.portafib.password=app_password', false, 4, '3', 3);


INSERT INTO suf_plugin(pluginid, nomid, descripciocurtaid, classe, propertiesadmin, actiu, tipus, codi, ordre) VALUES (101, 108, 109, 'org.fundaciobit.pluginsib.tipusdocumental.database.PluginTipusDocumentalDatabase', '# Database For JBOSS 
#es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.jndi=java:/es.caib.seycon.db.wl

# Database For Standalone Apps
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.connection.url=jdbc:postgresql://localhost:5432/utilitatsfirma
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.connection.username=username
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.connection.password=password


# Han de retornar 4 strings: id, name, base, description 
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.select.ca=SELECT tipusdocumentalid as id, nomcatala as name, paretipusdocumentalid as base, descripciocatala as description FROM suf_tipusdocumental;
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.select.es=SELECT tipusdocumentalid as id, nomcastella as name, paretipusdocumentalid as base, descripciocastella as description FROM suf_tipusdocumental;', false, 4, '2', 2);


-- ------------------------------------
--  PLUGIN DE VALIDACIÓ DE FIRMES
-- ------------------------------------

INSERT INTO suf_traduccio(traduccioid) VALUES (200);
INSERT INTO suf_traduccio(traduccioid) VALUES (201);


INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (200, 'ca', 'Plugin Validació de Firmes emprant Serveis Web d''@firma');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (200, 'es', 'Plugin Validación de Firmas utilizando Servicios Web de @firma');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (201, 'ca', 'Plugin Validació de Firmes emprant Serveis Web d''@firma');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (201, 'es', 'Plugin Validación de Firmas utilizando Servicios Web de @firma');



INSERT INTO suf_plugin(pluginid, nomid, descripciocurtaid, classe, propertiesadmin, actiu, tipus, codi, ordre) VALUES (200, 200, 201, 'org.fundaciobit.pluginsib.validatesignature.afirmacxf.AfirmaCxfValidateSignaturePlugin', '# Class org.fundaciobit.plugins.validatesignature.afirmacxf.AfirmaCxfValidateSignaturePlugin

es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.TransformersTemplatesPath=C:/dades/dev/ProgramacioPortaFIB2/pluginsib-validatesignature/afirmacxf/config/transformersTemplates

es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.debug=true
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.printxml=false

es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.connectTimeout=50000
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.readTimeout=50000


es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.ignoreservercertificates=true


# Obligatiori. Aplicació definida dins "Gestión de Aplicaciones" de @firma federat
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.applicationID=gobbal.fbit.portafib
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.applicationID=gobbal.fbit.portafib
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.applicationID=appPrueba
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.applicationID=CAIBDEV2.PORTAFIB

#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.endpoint=https://afirma.redsara.es/afirmaws/services/DSSAfirmaVerify
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.endpoint=http://des-afirma.redsara.es/afirmaws/services/DSSAfirmaVerify
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.endpoint=http://localhost:9090/afirmaws/services/DSSAfirmaVerify
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.endpoint=https://afirmades.caib.es:4430/afirmaws/services/DSSAfirmaVerify
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.endpoint=https://afirmades2.caib.es/afirmaws/services/DSSAfirmaVerify



# USERNAME-PASSWORD Token
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.username=<<username>>
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.password=<<password>>
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.username=userTest
#es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.password=12345


# CERTIFICATE Token
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.ks.path=C:\\dades\\dev\\Programacio/portafib-files/afirma/nou/proves-dgidt.jks
#C:\\dades\\dev\\Programacio/PortaFIB/plugins/plugins-certificate/afirma/proves-dgidt.jks
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.ks.type=JKS
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.ks.password=x7E7f9vU8QH8
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.ks.cert.alias=1
es.caib.utilitatsfirma.pluginsib.validatesignature.afirmacxf.authorization.ks.cert.password=x7E7f9vU8QH8', true, 1, 'valida_afirma', 1);



-- ------------------------------------
--   PLUGIN DE FIRMA EN SERVIDOR 
-- ------------------------------------

INSERT INTO suf_traduccio(traduccioid) VALUES (302);
INSERT INTO suf_traduccio(traduccioid) VALUES (303);

INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (302, 'ca', 'Plugin de Firma en Servidor emprant @firma federat');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (302, 'es', 'Plugin de Firma en Servidor emprant @firma federat');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (303, 'ca', 'Plugin de Firma en Servidor emprant @firma federat');
INSERT INTO suf_traducciomap(traducciomapid, idiomaid, valor) VALUES (303, 'es', 'Plugin de Firma en Servidor emprant @firma federat');



INSERT INTO suf_plugin(pluginid, nomid, descripciocurtaid, classe, propertiesadmin, actiu, tipus, codi, ordre) VALUES (301, 302, 303, 'org.fundaciobit.pluginsib.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin', '# Obligatiori. Aplicació definida dins "Gestión de Aplicaciones" de @firma federat, on
# en l''apartat ''Parametros de la aplicación'' en el camp "Política de TimeStamp" té
# definit el valor "Sin TimeStamp" 
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.applicationID=CAIBDEV2.PORTAFIB
# Opcional. Aplicació definida dins "Gestión de Aplicaciones" de @firma federat, on 
# en l''apartat ''Parametros de la aplicación'' en el camp "Política de TimeStamp" té definit
# el valor "Con TimeStamp". Si aquest valor no està definit llavors no es poden
# fer firmes amb segell de temps.
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.applicationID_TimeStamp=CAIBDEV2.PORTAFIB_TS


es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.connectTimeout=300000
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.readTimeout=300000

es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.debug=false
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.printxml=false

# Si signaturesSet.getCommonInfoSignature().getUsername() es null, llavors
# s''utilitza aquest valor com a sistema de selecció del certificat amb el que firmar
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.defaultAliasCertificate=afirmades-firma

es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.TransformersTemplatesPath=C:/dades/dev/ProgramacioPortaFIB3/pluginsib-signatureserver-4.1/afirmaserver/config/transformersTemplates

#Propiedades para la invocación de los servicios de la aplicación de @Firma
#Propiedades de comunicacion con el repositorio de servicios
#es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.endpoint=http://localhost:9090/afirmaws/services
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.endpoint=https://afirmades2.caib.es/afirmaws/services/DSSAfirmaSign

es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.endpoint_upgrade=https://afirmades2.caib.es/afirmaws/services/DSSAfirmaVerify

#Usuari-contrasenya
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.username=<<username>>
#Password del usuario o de la clave privada del certificado
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.password=<<password>>

# CERTIFICATE Token
#es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.ks.path=D:/dades/dades/proves-dgidt.jks
#C:\\dades\\dev\\Programacio/PortaFIB/plugins/plugins-certificate/afirma/proves-dgidt.jks
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.ks.type=JKS
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.ks.password=<<PASSWORD>>
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.ks.cert.alias=1
es.caib.utilitatsfirma.pluginsib.signatureserver.afirmaserver.authorization.ks.cert.password=<<PASSWORD>>', true, 2, 'FIRMA_FEDERAT', 2);


COMMIT;