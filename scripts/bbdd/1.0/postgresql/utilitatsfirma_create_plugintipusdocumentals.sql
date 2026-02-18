

INSERT INTO public.suf_traduccio VALUES (104);
INSERT INTO public.suf_traduccio VALUES (105);
INSERT INTO public.suf_traduccio VALUES (106);
INSERT INTO public.suf_traduccio VALUES (107);
INSERT INTO public.suf_traduccio VALUES (108);
INSERT INTO public.suf_traduccio VALUES (109);

INSERT INTO public.suf_traducciomap VALUES (104, 'ca', 'Plugin Tipus Documentals de NTI');
INSERT INTO public.suf_traducciomap VALUES (104, 'es', 'Plugin Tipos Documentals de NTI');
INSERT INTO public.suf_traducciomap VALUES (105, 'ca', 'Plugin Tipus Documentals definits en les Normes Tècniques d''Interoperabilitat (NTI)');
INSERT INTO public.suf_traducciomap VALUES (105, 'es', 'Plugin Tipos Documentales definidos en las Normas Técnicas de Interoperabilidad (NTI)');
INSERT INTO public.suf_traducciomap VALUES (106, 'ca', 'Plugin de Tipus Documentals de PortaFIB');
INSERT INTO public.suf_traducciomap VALUES (106, 'es', 'Plugin de Tipos Documentales de PortaFIB');
INSERT INTO public.suf_traducciomap VALUES (107, 'ca', 'Plugin de Tipus Documentals de PortaFIB');
INSERT INTO public.suf_traducciomap VALUES (107, 'es', 'Plugin de Tipos Documentales de PortaFIB');
INSERT INTO public.suf_traducciomap VALUES (108, 'ca', 'Plugin de Tipus Documentals d''una Base de Dades');
INSERT INTO public.suf_traducciomap VALUES (108, 'es', 'Plugin de Tipos Documentales de una Base de Datos');
INSERT INTO public.suf_traducciomap VALUES (109, 'ca', 'Plugin de Tipus Documentals d''una Base de Dades');
INSERT INTO public.suf_traducciomap VALUES (109, 'es', 'Plugin de Tipos Documentales de una Base de Datos');


INSERT INTO public.suf_plugin VALUES (100, 104, 105, 'org.fundaciobit.pluginsib.tipusdocumental.nti.PluginTipusDocumentalNormesTecniquesInteroperabilitat', '# No té propietats', true, 4, '1', 1);

INSERT INTO public.suf_plugin VALUES (102, 106, 107, 'org.fundaciobit.pluginsib.tipusdocumental.portafib.PluginTipusDocumentalPortaFIB', 'es.caib.utilitatsfirma.pluginsib.tipusdocumental.portafib.url=http://HOST:PORT/portafibapi/interna/secure
es.caib.utilitatsfirma.pluginsib.tipusdocumental.portafib.username=app_username
es.caib.utilitatsfirma.pluginsib.tipusdocumental.portafib.password=app_password', false, 4, '3', 3);


INSERT INTO public.suf_plugin VALUES (101, 108, 109, 'org.fundaciobit.pluginsib.tipusdocumental.database.PluginTipusDocumentalDatabase', '# Database For JBOSS 
#es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.jndi=java:/es.caib.seycon.db.wl

# Database For Standalone Apps
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.connection.url=jdbc:postgresql://localhost:5432/utilitatsfirma
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.connection.username=username
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.connection.password=password


# Han de retornar 4 strings: id, name, base, description 
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.select.ca=SELECT tipusdocumentalid as id, nomcatala as name, paretipusdocumentalid as base, descripciocatala as description FROM suf_tipusdocumental;
es.caib.utilitatsfirma.pluginsib.tipusdocumental.database.select.es=SELECT tipusdocumentalid as id, nomcastella as name, paretipusdocumentalid as base, descripciocastella as description FROM suf_tipusdocumental;', false, 4, '2', 2);





