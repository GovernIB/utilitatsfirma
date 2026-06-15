package es.caib.utilitatsfirma.api.interna.secure.comanda.v1;

import java.net.URL;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.jboss.logging.Logger;

import es.caib.comanda.model.server.monitoring.AppInfo;
import es.caib.comanda.model.server.monitoring.ContextInfo;
import es.caib.comanda.model.server.monitoring.EstatSalut;
import es.caib.comanda.model.server.monitoring.EstatSalutEnum;
import es.caib.comanda.model.server.monitoring.IntegracioInfo;
import es.caib.comanda.model.server.monitoring.IntegracioSalut;
import es.caib.comanda.model.server.monitoring.MissatgeSalut;
import es.caib.comanda.model.server.monitoring.SalutInfo;
import es.caib.comanda.model.server.monitoring.SubsistemaInfo;
import es.caib.comanda.model.server.monitoring.SubsistemaSalut;
import es.caib.comanda.ms.salut.helper.IntegracioApp;
import es.caib.comanda.ms.salut.helper.MonitorHelper;
import es.caib.comanda.ms.salut.helper.SalutHelper;
import es.caib.comanda.ms.salut.helper.SalutHelper.BuildInfo;
import es.caib.utilitatsfirma.commons.utils.Configuracio;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.commons.utils.Version;
import es.caib.utilitatsfirma.logic.EstadisticaLogicaService;
import es.caib.utilitatsfirma.logic.PluginLogicaLocal;
import es.caib.utilitatsfirma.model.fields.EstadisticaFields;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * 
 * @author anadal
 * 27 ene 2026 14:13:10
 */
@Path("/secure/")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = ComandaSalutService.SECURITY_NAME, scheme = "basic")
public class ComandaSalutService extends RestUtils implements es.caib.comanda.api.server.monitoring.ComandaAppSalutApi {

    protected Logger log = Logger.getLogger(ComandaSalutService.class);

    protected static final String SECURITY_NAME = "BasicAuth";

    protected static IntegracioApp[] INTEGRACIONS_UTILITATSFIRMA = { IntegracioApp.EML, IntegracioApp.SIG,
            IntegracioApp.VFI, IntegracioApp.USR };

    protected static final Map<String, String> subsistemaNameById = new HashMap<>();

    protected static final Map<String, Integer> subsistemaEntornById = new HashMap<>();

    protected static final Map<String, Where> subsistemaTipusOkById = new HashMap<>();

    protected static final Map<String, Where> subsistemaTipusErrorById = new HashMap<>();

    public static final String SUBSISTEMA_API_FIRMA_SERVIDOR = "SUF_API_FIRMA_SERVIDOR_V1";

    public static final String SUBSISTEMA_API_VALIDACIO_FIRMES = "SUF_API_VALIDACIO_FIRMA_V1";

    public static final String SUBSISTEMA_API_UTILITATS_FIRMA = "SUF_API_UTILITATS_FIRMA_V2";

    public static final String SUBSISTEMA_WEB_FIRMA_SERVIDOR = "SUF_WEB_FIRMA_SERVIDOR";

    public static final String SUBSISTEMA_WEB_VALIDACIO_FIRMES = "SUF_WEB_VALIDACIO_FIRMA";

    {
        subsistemaNameById.put(SUBSISTEMA_API_FIRMA_SERVIDOR, "API de Firma en Servidor V1");
        subsistemaNameById.put(SUBSISTEMA_API_VALIDACIO_FIRMES, "API de Validació de Firmes V1");
        subsistemaNameById.put(SUBSISTEMA_API_UTILITATS_FIRMA, "API de Utilitats de Firma V2");
        subsistemaNameById.put(SUBSISTEMA_WEB_VALIDACIO_FIRMES, "Validacio de Firmes des de Back");
        subsistemaNameById.put(SUBSISTEMA_WEB_FIRMA_SERVIDOR, "Firma en Servidor des de Back");

        subsistemaEntornById.put(SUBSISTEMA_API_FIRMA_SERVIDOR, Constants.ESTADISTICA_ENTORN_API_FIRMA_SERVIDOR_V1);
        subsistemaEntornById.put(SUBSISTEMA_API_VALIDACIO_FIRMES, Constants.ESTADISTICA_ENTORN_API_VALIDACIO_FIRMA_V1);
        subsistemaEntornById.put(SUBSISTEMA_API_UTILITATS_FIRMA, Constants.ESTADISTICA_ENTORN_API_UTILITATS_FIRMA_V2);
        subsistemaEntornById.put(SUBSISTEMA_WEB_VALIDACIO_FIRMES, Constants.ESTADISTICA_ENTORN_WEB_VALIDACIO);
        subsistemaEntornById.put(SUBSISTEMA_WEB_FIRMA_SERVIDOR, Constants.ESTADISTICA_ENTORN_WEB_FIRMA_SERVIDOR);

        subsistemaTipusOkById.put(SUBSISTEMA_API_FIRMA_SERVIDOR,
                Where.OR(EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_FIRMA_SERVIDOR_OK),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_UPGRADE_OK)));

        subsistemaTipusErrorById.put(SUBSISTEMA_API_FIRMA_SERVIDOR,
                Where.OR(EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_FIRMA_SERVIDOR_ERROR),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_UPGRADE_ERROR)));

        subsistemaTipusOkById.put(SUBSISTEMA_API_VALIDACIO_FIRMES,
                Where.OR(EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_VALIDA),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_INVALIDA)));

        subsistemaTipusErrorById.put(SUBSISTEMA_API_VALIDACIO_FIRMES,
                Where.OR(EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_VALIDACIO_ERROR)));

        subsistemaTipusOkById.put(SUBSISTEMA_API_UTILITATS_FIRMA,
                Where.OR(EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_FIRMA_SERVIDOR_OK),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_UPGRADE_OK),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_VALIDA),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_VALIDACIO_OK_INVALIDA)));

        subsistemaTipusErrorById.put(SUBSISTEMA_API_UTILITATS_FIRMA,
                Where.OR(EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_FIRMA_SERVIDOR_ERROR),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_UPGRADE_ERROR),
                        EstadisticaFields.TIPUS.equal(Constants.ESTADISTICA_TIPUS_VALIDACIO_ERROR)));

    }

    @EJB(mappedName = PluginLogicaLocal.JNDI_NAME)
    protected PluginLogicaLocal pluginLogicaEjb;

    @EJB(mappedName = EstadisticaLogicaService.JNDI_NAME)
    protected EstadisticaLogicaService estadisticaEjb;

    /**
     * Obtenir informació de l&#39;aplicació
     *
     * Retorna dades bàsiques de l&#39;aplicació (codi, nom, versió, data de build, etc.) i contextos exposats.
     *
     */
    @GET
    @Path("/salut/v1/info")
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtenir informació de l'aplicació", tags = { "COMANDA → APP / Salut" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = AppInfo.class) })
    @SecurityRequirement(name = SECURITY_NAME)
    @RolesAllowed({ Constants.SUF_WS })
    @Override
    public AppInfo salutInfo() {

        AppInfo a = new AppInfo();

        Version version = new Version();
        {

            // IMPORTANT: Requereix afegir el plugin de git-commit-id-maven-plugin al pom.xml arrel
            // Veure documentació integració comanda.
            
            BuildInfo infoTmp = SalutHelper.getBuildInfo();
            
            a.setJdkVersion(infoTmp.getBuildJDK());
            a.setRevisio(infoTmp.getCommitId());
            a.setData(infoTmp.getBuildDate());

        }

        String urlBase = Configuracio.getBackUrl().replaceAll("/utilitatsfirmaback","");

        a.codi("SUF");
        a.nom("Servidor Utilitats de Firma (SUF)");

        {

            List<ContextInfo> contexts = new ArrayList<>();
            {
                ContextInfo back = new ContextInfo();

                back.setApi(null);
                back.setCodi("SUF_BACK");
                // TODO Falta Manual
                /*
                Manual manual = new Manual();
                manual.setNom("Manual_de_Usuari_de_Utilitats de Firma");
                manual.setPath(
                        "https://github.com/GovernIB/utilitatsfirma/raw/refs/heads/utilitatsfirma-3.0/doc/Manual_de_Usuari_de_Utilitats de Firma.odt");
                back.setManuals(List.of(manual));
                */
                back.setNom("Utilitats de Firma Backoffice/Frontoffice");
                back.setPath(urlBase + "/utilitatsfirmaback");

                contexts.add(back);
            }

            {
                ContextInfo apiinterna = new ContextInfo();
                apiinterna.setApi(urlBase + "/utilitatsfirmaapi/interna");
                apiinterna.setCodi("SUF_API_INTERNA");

                // TODO Falta Manual
                /*
                Manual manual = new Manual();
                manual.setNom("Manual_de_Migració_de_APIsIB_a_Api_Interna");
                manual.setPath(
                        "https://github.com/GovernIB/utilitatsfirma/raw/refs/heads/utilitatsfirma-3.0/doc/Manual_de_Migraci%C3%B3_de_APIsIB_a_Api_Interna.odt");
                apiinterna.setManuals(List.of(manual));
                */

                apiinterna.setNom("Utilitats de Firma Api Interna Swagger");
                apiinterna.setPath(urlBase + "/utilitatsfirmaapi/interna");

                contexts.add(apiinterna);
            }

            // Afegir contexts
            a.setContexts(contexts);

        }

        {
            List<IntegracioInfo> list = new ArrayList<>();

            for (IntegracioApp ia : INTEGRACIONS_UTILITATSFIRMA) {
                IntegracioInfo i1 = new IntegracioInfo();
                i1.setCodi(ia.name());
                i1.setNom(ia.getNom());
                list.add(i1);
            }

            a.setIntegracions(list);

        }

        {
            List<SubsistemaInfo> subsistemes = new java.util.ArrayList<>();
            for (Map.Entry<String, String> entry : subsistemaNameById.entrySet()) {
                SubsistemaInfo ss = new SubsistemaInfo();
                ss.setCodi(entry.getKey());
                ss.setNom(entry.getValue());
                subsistemes.add(ss);
            }
            a.setSubsistemes(subsistemes);
        }

        a.versio(version.getVersion());

        a.setVersioJboss(getJBossVersion());

        return a;

    }

    /**
     * Obtenir informació de l&#39;estat de salut de l&#39;aplicació
     *
     * Retorna l&#39;estat de salut funcional i integracions, amb metadades de versió.
     *
     */
    @GET
    @Path("/salut/v1")
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtenir informació de l'estat de salut de l'aplicació", tags = { "COMANDA → APP / Salut" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = SalutInfo.class) })
    //    @RolesAllowed({ Constants.SUF_WS })
    //    @SecurityRequirement(name = SECURITY_NAME)
    @Override
    public SalutInfo salut(@QueryParam("dataPeriode") @ApiParam(
            value = "Data mínima de la que es demana informació per període",
            example = "2025-12-31T23:59:59Z")
            java.time.OffsetDateTime dataPeriode,
            @QueryParam("dataTotal") @ApiParam(
                    value = "Data mínima de la que demana informació per totals",
                    example = "2025-01-01T00:00:00Z")
            java.time.OffsetDateTime dataTotal) {
        SalutInfo sInfo = new SalutInfo();
        sInfo.setCodi("SUF");
        sInfo.setData(getDateTime());

        {
            EstatSalut estatBaseDeDades = new EstatSalut();

            long start = System.currentTimeMillis();
            try {
                final Where where = null;
                final Integer firstResult = 1;
                final Integer maxResults = 1;
                pluginLogicaEjb.select(where, firstResult, maxResults);
                estatBaseDeDades.setEstat(EstatSalutEnum.UP);
            } catch (Exception e) {
                estatBaseDeDades.setEstat(EstatSalutEnum.ERROR);
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();

            estatBaseDeDades.setLatencia((int) (end - start));

            sInfo.setEstatBaseDeDades(estatBaseDeDades);
        }

        {
            EstatSalut estatGlobal = new EstatSalut();

            long start = System.currentTimeMillis();
            try {
                String url = Configuracio.getBackUrl();

                // Fer una petició a l'endpoint de info per comprovar que respon correctament
                // emprant URL
                URL urlObj = new URL(url);
                urlObj.openStream().close();

                estatGlobal.setEstat(EstatSalutEnum.UP);
            } catch (Exception e) {
                log.error("Error comprovant l'estat global de l'aplicació: " + e.getMessage(), e);

                estatGlobal.setEstat(EstatSalutEnum.ERROR);
            }
            long end = System.currentTimeMillis();

            estatGlobal.setLatencia((int) (end - start));
            sInfo.setEstatGlobal(estatGlobal);

            sInfo.setInformacioSistema(MonitorHelper.getInfoSistema());
        }

        {
            List<IntegracioSalut> integracions = new java.util.ArrayList<>();

            for (IntegracioApp integracioApp : INTEGRACIONS_UTILITATSFIRMA) {

                IntegracioSalut integracio = new IntegracioSalut();
                integracio.setCodi(integracioApp.name());
                integracio.setEstat(EstatSalutEnum.UP);
                // TODO calcular latència
                integracio.setLatencia(null);

                /*
                Timestamp avui = new Timestamp(System.currentTimeMillis());
                
                Calendar cal = Calendar.getInstance();
                
                cal.add(Calendar.MONTH, -1);
                
                Timestamp faunmes;
                if (dataPeriode != null) {
                faunmes = new Timestamp(dataPeriode.toInstant().toEpochMilli());
                } else {
                
                faunmes = new Timestamp(cal.getTimeInMillis());
                }
                
                cal.add(Calendar.MONTH, -11);
                
                Timestamp faunany;
                
                if (dataTotal != null) {
                faunany = new Timestamp(dataTotal.toInstant().toEpochMilli());
                } else {
                faunany = new Timestamp(cal.getTimeInMillis());
                }
                
                // Cercar peticions d'aquesta integració 
                IntegracioPeticions peticions = new IntegracioPeticions();
                peticions.setEndpoint("/secure/asyncsignatureonweb/v1/");
                peticions.setPeticionsErrorUltimPeriode(
                    calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT, faunmes, avui));
                peticions.setPeticionsOkUltimPeriode(
                    calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT, faunmes, avui));
                peticions.setPeticionsPerEntorn(null); // TODO calcular map
                peticions.setTempsMigUltimPeriode(-1);
                peticions.setTotalError(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT, faunany, avui));
                peticions.setTotalOk(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT, faunany, avui));
                peticions.setTotalTempsMig(-1);
                integracio.setPeticions(peticions);
                */

                integracions.add(integracio);

            }

            sInfo.setIntegracions(integracions);
        }

        {

            List<MissatgeSalut> missatges = new ArrayList<MissatgeSalut>();

            /*
            
            Timestamp faDosDies = new Timestamp(System.currentTimeMillis() - 2L * 24 * 3600 * 1000);
            
            // Peticions caducades
            try {
                Long count = peticioDeFirmaEjb.count(Where.AND(
                        PeticioDeFirmaFields.DATACADUCITAT.lessThan(new Timestamp(System.currentTimeMillis())),
                        PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID
                                .equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES)));
            
                if (count != null && count > 0) {
                    MissatgeSalut ms = new MissatgeSalut();
                    ms.setNivell(SalutNivell.ERROR);
                    ms.setData(getDateTime());
                    ms.setMissatge("Hi ha " + count + " peticions de firma caducades.");
                    missatges.add(ms);
                }
            
            } catch (I18NException e) {
            
                String msg = "Error consultant les peticions de firma caducades: "
                        + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
            
                log.error(msg, e);
            
                MissatgeSalut ms = new MissatgeSalut();
                ms.setNivell(SalutNivell.ERROR);
                ms.setData(getDateTime());
                ms.setMissatge(msg);
                missatges.add(ms);
            }
            
            // Calcular CallBacks pendents 
            try {
                Long count = notificacioLogicaEjb.count(Where.AND(NotificacioWSFields.DATACREACIO.lessThan(faDosDies),
                        NotificacioWSFields.BLOQUEJADA.equal(false)));
            
                if (count != null && count > 0) {
                    MissatgeSalut ms = new MissatgeSalut();
                    ms.setNivell(SalutNivell.ERROR);
                    ms.setData(getDateTime());
                    ms.setMissatge("Hi ha " + count + " notificacions ws (Callback) pendents de més de 2 dies");
                    missatges.add(ms);
                }
            
            } catch (I18NException e) {
            
                String msg = "Error consultant les notificacions ws (Callback) pendents: "
                        + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
            
                log.error(msg, e);
            
                MissatgeSalut ms = new MissatgeSalut();
                ms.setNivell(SalutNivell.ERROR);
                ms.setData(getDateTime());
                ms.setMissatge(msg);
                missatges.add(ms);
            }
            
            // Missatges agrupats pendents des de fa més de 2 dies
            try {
                Long count = correuAgrupatLogicaEjb.count(CorreuAgrupatFields.DATACREACIO.lessThan(faDosDies));
                if (count != null && count > 0) {
                    MissatgeSalut ms = new MissatgeSalut();
                    ms.setNivell(SalutNivell.ERROR);
                    ms.setData(getDateTime());
                    ms.setMissatge("Hi ha " + count + " missatges agrupats pendents de més de 2 dies");
                    missatges.add(ms);
                }
            } catch (I18NException e) {
            
                String msg = "Error consultant el missatges agrupats pendents de més de 2 dies: "
                        + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));
            
                log.error(msg, e);
            
                MissatgeSalut ms = new MissatgeSalut();
                ms.setNivell(SalutNivell.ERROR);
                ms.setData(getDateTime());
                ms.setMissatge(msg);
                missatges.add(ms);
            }
            */

            sInfo.setMissatges(missatges);
        }

        {

            List<SubsistemaSalut> subsistemesList = new java.util.ArrayList<>();

            for (Map.Entry<String, String> entry : subsistemaNameById.entrySet()) {

                String codiSubsistema = entry.getKey();

                final int entornID = subsistemaEntornById.get(codiSubsistema);

                SubsistemaSalut subSystemApiFirmaAsinc = new SubsistemaSalut();
                subSystemApiFirmaAsinc.setCodi(codiSubsistema);
                subSystemApiFirmaAsinc.setEstat(EstatSalutEnum.UP);
                // TODO calcular latència
                subSystemApiFirmaAsinc.setLatencia(null);

                Timestamp avui = new Timestamp(System.currentTimeMillis());

                Calendar cal = Calendar.getInstance();

                cal.add(Calendar.MONTH, -1);

                Timestamp faunmes;
                if (dataPeriode != null) {
                    faunmes = new Timestamp(dataPeriode.toInstant().toEpochMilli());
                } else {

                    faunmes = new Timestamp(cal.getTimeInMillis());
                }

                cal.add(Calendar.MONTH, -11);

                Timestamp faunany;

                if (dataTotal != null) {
                    faunany = new Timestamp(dataTotal.toInstant().toEpochMilli());
                } else {
                    faunany = new Timestamp(cal.getTimeInMillis());
                }

                // Cercar peticions per aquest subsistemes 

                subSystemApiFirmaAsinc.setPeticionsErrorUltimPeriode(
                        calculPeticions(entornID, faunmes, avui, subsistemaTipusErrorById.get(codiSubsistema)));
                subSystemApiFirmaAsinc.setPeticionsOkUltimPeriode(
                        calculPeticions(entornID, faunmes, avui, subsistemaTipusOkById.get(codiSubsistema)));
                subSystemApiFirmaAsinc.setTempsMigUltimPeriode(-1);
                subSystemApiFirmaAsinc.setTotalError(
                        calculPeticions(entornID, faunany, avui, subsistemaTipusErrorById.get(codiSubsistema)));
                subSystemApiFirmaAsinc.setTotalOk(
                        calculPeticions(entornID, faunany, avui, subsistemaTipusOkById.get(codiSubsistema)));
                subSystemApiFirmaAsinc.setTotalTempsMig(-1);

                subsistemesList.add(subSystemApiFirmaAsinc);

            }

            sInfo.setSubsistemes(subsistemesList);
        }

        sInfo.setVersio(new Version().getVersion());

        return sInfo;
    }

    protected long calculPeticions(int codiEntorn, Timestamp from, Timestamp to, Where whereTipus) {
        long totalOK;

        Where w1 = EstadisticaFields.USUARIAPLICACIOID.isNotNull();
        Where w2 = EstadisticaFields.DATA.between(from, to);
        Where w3 = EstadisticaFields.ENTORN.equal(codiEntorn);

        Where w = Where.AND(w1, w2, w3, whereTipus);
        try {
            totalOK = estadisticaEjb.count(w);
        } catch (I18NException e) {
            log.error("Error calculant consultes a l'entorn amb ID " + codiEntorn + ": "
                    + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage())));
            totalOK = -1;
        }
        return totalOK;
    }

    protected OffsetDateTime getDateTime() {
        return OffsetDateTime.now();
    }

    public static String jbossVersionCache = null;

    public String getJBossVersion() {

        if (jbossVersionCache == null) {
            String jbossVersion = null;
            try {
                ObjectName rootNameObjectName = new ObjectName("jboss.as:management-root=server");
                for (MBeanServer server : MBeanServerFactory.findMBeanServer(null)) {
                    if (server.isRegistered(rootNameObjectName)) {
                        jbossVersion = (String) server.getAttribute(rootNameObjectName, "product-version");
                        break;
                    }
                }

                if (jbossVersion == null) {
                    log.warn("JBOSS VERSION: No s'ha trobat el camp 'product-version'");
                }

            } catch (Exception e) {
                log.error("JBOSS VERSION: error no controlat " + e.getMessage(), e);
            }

            log.info("JBOSS VERSION: " + jbossVersion);
            if (jbossVersion != null) {
                jbossVersionCache = jbossVersion;
            }
        }

        return jbossVersionCache;

    }

}
