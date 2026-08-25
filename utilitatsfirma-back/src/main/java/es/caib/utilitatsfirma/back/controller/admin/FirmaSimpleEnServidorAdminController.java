package es.caib.utilitatsfirma.back.controller.admin;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.PolicyInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.ejb.PerfilDeFirmaService;
import es.caib.utilitatsfirma.ejb.PerfilsPerUsuariAplicacioService;
import es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService;
import es.caib.utilitatsfirma.ejb.UsuariAplicacioService;
import es.caib.utilitatsfirma.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.utilitatsfirma.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFullResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureInServerResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureResult;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.utilitatsfirma.logic.utils.SignatureUtils;
import es.caib.utilitatsfirma.model.bean.FitxerBean;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacio;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import es.caib.utilitatsfirma.model.fields.PerfilDeFirmaFields;
import es.caib.utilitatsfirma.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;

/**
 * Controller d'administració que permet realitzar una firma simple en servidor
 * mitjançant un assistent web de diversos passos:
 * <ol>
 * <li>Elegir un usuari aplicació.</li>
 * <li>Elegir un perfil dels perfils assignats a l'usuari aplicació.</li>
 * <li>Elegir la configuració desitjada del perfil elegit.</li>
 * <li>Seleccionar mitjançant formulari web el fitxer a signar.</li>
 * <li>Realitzar la firma seguint el flux del mètode signDocument() de
 * UtilitatsFirmaV2Service, cridant a
 * {@link ConfiguracioUsuariAplicacioLogicaLocal#getConfiguracioFirmaPerApiFirmaSimpleEnServidor}
 * i {@link PassarelaDeFirmaEnServidorLocal#signDocuments}.</li>
 * </ol>
 *
 * @author GitHub Copilot
 */
@Controller
@RequestMapping(value = "/admin/firmasimpleenservidor")
@MenuOption(
        labelCode = "=Firma Simple en Servidor (Test)",
        order = 1100,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/firmasimpleenservidor",
        relativeLink = "/inici",
        addSeparatorBefore = true)
@Tile(
        name = FirmaSimpleEnServidorAdminController.TILE_NAME,
        extendsTile = Tab.MENU_ADMIN,
        contentJsp = "/WEB-INF/jsp/admin/firmasimpleenservidor.jsp",
        type = TileType.ANOTHER)
public class FirmaSimpleEnServidorAdminController {

    public static final String TILE_NAME = "firmaSimpleEnServidorAdmin";

    // signID fix per aquesta firma d'un únic document
    private static final String SIGN_ID = "1";

    // Claus de sessió per al document firmat pendent de descarregar
    private static final String SESSION_SIGNED_FILE_PATH = "firmaSimpleEnServidor_signedFilePath";
    private static final String SESSION_SIGNED_FILE_NAME = "firmaSimpleEnServidor_signedFileName";
    private static final String SESSION_SIGNED_FILE_MIME = "firmaSimpleEnServidor_signedFileMime";
    private static final String SESSION_SIGNED_FILE_SIZE = "firmaSimpleEnServidor_signedFileSize";

    protected static final Logger log = Logger.getLogger(FirmaSimpleEnServidorAdminController.class);

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = PerfilsPerUsuariAplicacioService.JNDI_NAME)
    protected PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

    @EJB(mappedName = PerfilDeFirmaService.JNDI_NAME)
    protected PerfilDeFirmaService perfilDeFirmaEjb;

    @EJB(mappedName = UsuariAplicacioConfiguracioService.JNDI_NAME)
    protected UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;

    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

    @EJB(mappedName = PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
    protected PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

    // =========================================================================
    // PAS 1: Elegir usuari aplicació
    // =========================================================================
    @RequestMapping(value = "/inici", method = RequestMethod.GET)
    public ModelAndView inici(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView(TILE_NAME);
        mav.addObject("step", 1);
        // Llista de tots els usuaris aplicació
        mav.addObject("usuariAplicacions", getUsuariAplicacions());
        return mav;
    }

    // =========================================================================
    // PAS 2: Elegir un perfil dels assignats a l'usuari aplicació seleccionat
    // =========================================================================
    @RequestMapping(value = "/perfils", method = RequestMethod.POST)
    public ModelAndView perfils(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("usuariAplicacioID")
            String usuariAplicacioID) throws Exception {

        ModelAndView mav = new ModelAndView(TILE_NAME);

        if (usuariAplicacioID == null || usuariAplicacioID.trim().length() == 0) {
            mav.addObject("step", 1);
            mav.addObject("errorMsg", "Heu de seleccionar un usuari aplicació.");
            mav.addObject("usuariAplicacions", getUsuariAplicacions());
            return mav;
        }

        List<PerfilDeFirma> perfils = getPerfilsPerUsuariAplicacio(usuariAplicacioID);

        mav.addObject("step", 2);
        mav.addObject("usuariAplicacioID", usuariAplicacioID);
        mav.addObject("perfils", perfils);
        if (perfils == null || perfils.isEmpty()) {
            mav.addObject("errorMsg", "L'usuari aplicació seleccionat no té cap perfil assignat.");
        }
        
        if (perfils.size() == 1) {
            // Si només hi ha un perfil, es salta directament al pas 3
            PerfilDeFirma perfil = perfils.get(0);
            mav.addObject("step", 3);
            mav.addObject("perfilID", perfil.getUsuariAplicacioPerfilID());
            mav.addObject("perfilCodi", perfil.getCodi());
            mav.addObject("perfilNom", perfil.getNom());
            List<UsuariAplicacioConfiguracio> configuracions = getConfiguracionsDePerfil(perfil);
            mav.addObject("configuracions", configuracions);
        }
        
        return mav;
    }

    // =========================================================================
    // PAS 3: Elegir la configuració desitjada del perfil elegit
    // =========================================================================
    @RequestMapping(value = "/configuracions", method = RequestMethod.POST)
    public ModelAndView configuracions(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("usuariAplicacioID")
            String usuariAplicacioID, @RequestParam(value = "perfilID", required = false)
            String perfilIDStr) throws Exception {

        ModelAndView mav = new ModelAndView(TILE_NAME);

        Long perfilID = parseLong(perfilIDStr);
        PerfilDeFirma perfil = (perfilID == null) ? null : (PerfilDeFirma) perfilDeFirmaEjb.findByPrimaryKey(perfilID);

        if (perfil == null) {
            mav.addObject("step", 2);
            mav.addObject("usuariAplicacioID", usuariAplicacioID);
            mav.addObject("perfils", getPerfilsPerUsuariAplicacio(usuariAplicacioID));
            mav.addObject("errorMsg", "Heu de seleccionar un perfil.");
            return mav;
        }

        List<UsuariAplicacioConfiguracio> configuracions = getConfiguracionsDePerfil(perfil);

        mav.addObject("step", 3);
        mav.addObject("usuariAplicacioID", usuariAplicacioID);
        mav.addObject("perfilID", perfilID);
        mav.addObject("perfilCodi", perfil.getCodi());
        mav.addObject("perfilNom", perfil.getNom());
        mav.addObject("configuracions", configuracions);
        return mav;
    }

    // =========================================================================
    // PAS 4: Seleccionar el fitxer a signar
    // =========================================================================
    @RequestMapping(value = "/fitxer", method = RequestMethod.POST)
    public ModelAndView fitxer(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("usuariAplicacioID")
            String usuariAplicacioID, @RequestParam("perfilID")
            String perfilIDStr, @RequestParam(value = "configID", required = false)
            String configIDStr) throws Exception {

        ModelAndView mav = new ModelAndView(TILE_NAME);

        Long perfilID = parseLong(perfilIDStr);
        Long configID = parseLong(configIDStr);
        PerfilDeFirma perfil = (perfilID == null) ? null : (PerfilDeFirma) perfilDeFirmaEjb.findByPrimaryKey(perfilID);

        if (configID == null) {
            mav.addObject("step", 3);
            mav.addObject("usuariAplicacioID", usuariAplicacioID);
            mav.addObject("perfilID", perfilID);
            mav.addObject("perfilCodi", perfil == null ? null : perfil.getCodi());
            mav.addObject("perfilNom", perfil == null ? null : perfil.getNom());
            mav.addObject("configuracions", perfil == null ? null : getConfiguracionsDePerfil(perfil));
            mav.addObject("errorMsg", "Heu de seleccionar una configuració.");
            return mav;
        }

        UsuariAplicacioConfiguracioJPA config = (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioEjb
                .findByPrimaryKey(configID);

        mav.addObject("step", 4);
        mav.addObject("usuariAplicacioID", usuariAplicacioID);
        mav.addObject("perfilID", perfilID);
        mav.addObject("perfilCodi", perfil == null ? null : perfil.getCodi());
        mav.addObject("configID", configID);
        mav.addObject("configNom", config == null ? null : config.getNom());
        return mav;
    }

    // =========================================================================
    // PAS 5: Realitzar la firma (segueix el flux de signDocument() de la V2)
    // =========================================================================
    @RequestMapping(value = "/signar", method = RequestMethod.POST)
    public ModelAndView signar(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("usuariAplicacioID")
            String usuariAplicacioID, @RequestParam("perfilID")
            String perfilIDStr, @RequestParam("configID")
            String configIDStr, @RequestParam("fileToSign")
            MultipartFile fileToSignMultipart) throws Exception {

        final String languageUI = "ca";
        File fileToSign = null;

        Long perfilID = parseLong(perfilIDStr);
        Long configID = parseLong(configIDStr);

        try {

            if (fileToSignMultipart == null || fileToSignMultipart.isEmpty()) {
                return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID,
                        "Heu de seleccionar un fitxer a signar.");
            }

            // Es guarda el fitxer pujat en un fitxer temporal
            fileToSign = File.createTempFile("FirmaSimpleAdmin_", "_fileToSign");
            fileToSignMultipart.transferTo(fileToSign);
            String fileToSignName = fileToSignMultipart.getOriginalFilename();
            if (fileToSignName == null || fileToSignName.trim().length() == 0) {
                fileToSignName = fileToSign.getName();
            }

            // El perfil elegit (pas 2)
            PerfilDeFirma perfil = (PerfilDeFirma) perfilDeFirmaEjb.findByPrimaryKey(perfilID);
            if (perfil == null) {
                return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID,
                        "No s'ha trobat el perfil seleccionat.");
            }

            // La configuració elegida (pas 3)
            UsuariAplicacioConfiguracioJPA configElegida = (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioEjb
                    .findByPrimaryKey(configID);
            if (configElegida == null) {
                return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID,
                        "No s'ha trobat la configuració seleccionada.");
            }

            // ============= Seguim el flux del mètode signDocument() de la V2 ==========

            // 1) Es construeix la petició per l'API de firma simple i s'obté la
            // configuració de firma. Igual que fa signDocument() amb
            // getConfiguracioFirmaPerApiFirmaSimpleEnServidor().
            org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest simpleSignatureRequestApisib;
            simpleSignatureRequestApisib = construirFirmaSimpleSignDocumentRequest(usuariAplicacioID, languageUI,
                    perfil.getCodi(), fileToSign, fileToSignName);

            PerfilConfiguracionsDeFirma pcf = configuracioUsuariAplicacioLogicaLocalEjb
                    .getConfiguracioFirmaPerApiFirmaSimpleEnServidor(usuariAplicacioID, perfil.getCodi(),
                            simpleSignatureRequestApisib);

            // Es respecta la configuració que ha elegit explícitament l'administrador
            // (pas 3), substituint la que retorna la lògica de perfil.
            pcf.configBySignID.put(SIGN_ID, configElegida);

            // 2) Es construeix el PassarelaSignaturesSet igual que
            // convertRestBean2PassarelaBeanServer() de la V2.
            String transactionID = "FirmaSimpleAdmin-" + System.currentTimeMillis();
            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKey(usuariAplicacioID);

            PassarelaSignaturesSet pss = construirPassarelaSignaturesSet(transactionID, usuariAplicacioID, languageUI,
                    fileToSign, fileToSignName, configElegida,
                    simpleSignatureRequestApisib.getFileInfoSignature().getReason());
            pss.getCommonInfoSignature().setUsername(null);

            // 3) Es realitza la firma. Igual que signDocument() amb signDocuments().
            PassarelaSignatureInServerResults fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss,
                    usuariAplicacio, Constants.ESTADISTICA_ENTORN_WEB_FIRMA_SERVIDOR, pcf.perfilDeFirma,
                    pcf.configBySignID);

            // 4) Es processa el resultat
            PassarelaFullResults pfullResults = fullResults.getPassarelaFullResults();
            PassarelaSignatureStatus passarelaSS = pfullResults.getSignaturesSetStatus();

            if (passarelaSS.getStatus() != StatusSignature.STATUS_FINAL_OK) {
                String errMsg = "La firma ha finalitzat amb error: " + passarelaSS.getErrorMessage();
                log.error(errMsg + "\n" + passarelaSS.getErrorStackTrace());
                return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID, errMsg);
            }

            List<PassarelaSignatureResult> signResults = pfullResults.getSignResults();
            if (signResults == null || signResults.isEmpty()) {
                return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID,
                        "La firma no ha retornat cap document signat.");
            }

            PassarelaSignatureResult psr = signResults.get(0);
            if (psr.getStatus() != StatusSignature.STATUS_FINAL_OK || psr.getSignedFile() == null) {
                String errMsg = "El document no s'ha pogut signar: " + psr.getErrorMessage();
                log.error(errMsg + "\n" + psr.getErrorStackTrace());
                return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID, errMsg);
            }

            // 5) Es guarda el document firmat a un fitxer temporal (referenciat des de
            // la sessió) per poder-lo descarregar des de la pantalla de resultat.
            FitxerBean signedFile = psr.getSignedFile();
            guardarFitxerFirmatEnSessio(request, signedFile, fileToSignName);

            // 6) Es mostra la pantalla de resultat amb tota la informació de la firma
            return construirVistaResultat(usuariAplicacioID, perfilID, configID, fullResults);

        } catch (Throwable th) {
            String msg = "Error realitzant la firma: " + th.getMessage();
            log.error(msg, th);
            return tornarAPasFitxerAmbError(usuariAplicacioID, perfilID, configID, msg);
        } finally {
            if (fileToSign != null && fileToSign.exists()) {
                try {
                    fileToSign.delete();
                } catch (Throwable ignored) {
                    // res
                }
            }
        }
    }

    // =========================================================================
    // Mètodes auxiliars de consulta
    // =========================================================================

    /** Converteix un String a Long retornant null si és buit o no vàlid. */
    protected Long parseLong(String value) {
        if (value == null || value.trim().length() == 0) {
            return null;
        }
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Retorna tots els usuaris aplicació. */
    protected List<UsuariAplicacio> getUsuariAplicacions() throws I18NException {
        return usuariAplicacioEjb.select(UsuariAplicacioFields.USUARIAPLICACIOID.isNotNull());
    }

    /** Retorna els perfils assignats a un usuari aplicació. */
    protected List<PerfilDeFirma> getPerfilsPerUsuariAplicacio(String usuariAplicacioID) throws I18NException {
        List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
                PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

        if (perfilIDList == null || perfilIDList.isEmpty()) {
            return new ArrayList<PerfilDeFirma>();
        }

        return perfilDeFirmaEjb.select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));
    }

    /** Retorna les configuracions de firma associades a un perfil. */
    protected List<UsuariAplicacioConfiguracio> getConfiguracionsDePerfil(PerfilDeFirma perfil) throws I18NException {
        List<Long> configIDs = new ArrayList<Long>();
        configIDs.add(perfil.getConfiguracioDeFirma1ID());
        if (perfil.getConfiguracioDeFirma2ID() != null) {
            configIDs.add(perfil.getConfiguracioDeFirma2ID());
        }
        if (perfil.getConfiguracioDeFirma3ID() != null) {
            configIDs.add(perfil.getConfiguracioDeFirma3ID());
        }
        if (perfil.getConfiguracioDeFirma4ID() != null) {
            configIDs.add(perfil.getConfiguracioDeFirma4ID());
        }
        if (perfil.getConfiguracioDeFirma5ID() != null) {
            configIDs.add(perfil.getConfiguracioDeFirma5ID());
        }

        return usuariAplicacioConfiguracioEjb
                .select(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(configIDs));
    }

    // =========================================================================
    // Mètodes auxiliars de construcció de beans (seguint la V2)
    // =========================================================================

    /**
     * Construeix la petició per l'API de firma simple, que és el que espera
     * {@link ConfiguracioUsuariAplicacioLogicaLocal#getConfiguracioFirmaPerApiFirmaSimpleEnServidor}.
     */
    protected org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest construirFirmaSimpleSignDocumentRequest(
            String usuariAplicacioID, String languageUI, String signProfile, File fileToSign, String fileToSignName)
            throws Exception {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo commonInfo;
        commonInfo = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo();
        commonInfo.setLanguageUI(languageUI);
        commonInfo.setUsername(usuariAplicacioID);
        commonInfo.setSignProfile(signProfile);

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile firmaSimpleFile;
        firmaSimpleFile = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();
        firmaSimpleFile.setData(Files.readAllBytes(fileToSign.toPath()));
        firmaSimpleFile.setMime(obtenirMime(fileToSign, fileToSignName));
        firmaSimpleFile.setNom(fileToSignName);

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignature;
        fileInfoSignature = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature();
        fileInfoSignature.setSignID(SIGN_ID);
        fileInfoSignature.setName(fileToSignName);
        fileInfoSignature.setSignNumber(1);
        fileInfoSignature.setFileToSign(firmaSimpleFile);
        fileInfoSignature.setReason("Firma simple en servidor (test)");

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest request;
        request = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest();
        request.setCommonInfo(commonInfo);
        request.setFileInfoSignature(fileInfoSignature);
        return request;
    }

    /**
     * Construeix el {@link PassarelaSignaturesSet} a partir de la configuració de
     * firma, replicant el que fa convertRestBean2PassarelaBeanServer() de la V2.
     */
    protected PassarelaSignaturesSet construirPassarelaSignaturesSet(String transactionID, String usuariAplicacioID,
            String languageUI, File fileToSign, String fileToSignName, UsuariAplicacioConfiguracio config,
            String signReason) throws I18NException {

        // Fitxer a signar
        FitxerBean fileToSignBean = construirFitxerBean(fileToSign, fileToSignName);

        // Dades de la firma segons la configuració elegida
        final int signOperation = config.getTipusOperacioFirma();
        final String signType = SignatureUtils.convertPortafibSignTypeToApiSignType(config.getTipusFirma());
        final String signAlgorithm = SignatureUtils.convertSignAlgorithmID(config.getAlgorismeDeFirma());
        final int signMode = config.getModeDeFirma();
        final int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        final boolean useTimeStamp = getUseTimestampOfConfig(config);

        PassarelaFileInfoSignature fileInfoSignature = new PassarelaFileInfoSignature(fileToSignBean, null, SIGN_ID,
                fileToSignName, null, null, null, 1, languageUI, signOperation, signType, signAlgorithm, signMode,
                signaturesTableLocation, useTimeStamp, null, null, null, null, null, null);

        fileInfoSignature.setReason(signReason);

        PassarelaFileInfoSignature[] fileInfoSignatureArray = new PassarelaFileInfoSignature[] { fileInfoSignature };

        // Política de firma (comuna)
        PassarelaPolicyInfoSignature policyInfoSignature = getPoliticaFirmaOfConfig(config);

        // username en firma en servidor és la configuració de firma que enviam al PLugin
        // Es un mecanisme per enviar algun paràmetre al plugin.
        String username = null;
        PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(languageUI, null,
                username, null, null, null, policyInfoSignature);

        // Donam de temps 5 minuts + 1 minut per la signatura
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.MINUTE, 6);

        return new PassarelaSignaturesSet(transactionID, expiryDate.getTime(), commonInfoSignature,
                fileInfoSignatureArray);
    }

    /** Igual que convertFirmaSimpleFileToFitxerBean() de la V2. */
    protected FitxerBean construirFitxerBean(File file, String fileName) {
        FitxerBean fitxer = new FitxerBean();
        fitxer.setDescripcio(null);
        fitxer.setMime(obtenirMime(file, fileName));
        fitxer.setNom(fileName);
        fitxer.setTamany(file.length());
        FileDataSource fds = new FileDataSource(file);
        fitxer.setData(new DataHandler(fds));
        return fitxer;
    }

    /** Obté el mime del fitxer, amb fallback per extensió. */
    protected String obtenirMime(File file, String fileName) {
        String mime = null;
        try {
            mime = Files.probeContentType(file.toPath());
        } catch (Throwable th) {
            log.warn("No s'ha pogut obtenir el mime del fitxer " + file.getAbsolutePath() + ": " + th.getMessage());
        }
        if (mime == null) {
            String lower = fileName == null ? "" : fileName.toLowerCase();
            if (lower.endsWith(".pdf")) {
                mime = "application/pdf";
            } else if (lower.endsWith(".xml")) {
                mime = "application/xml";
            } else if (lower.endsWith(".txt")) {
                mime = "text/plain";
            } else {
                mime = "application/octet-stream";
            }
        }
        return mime;
    }

    /** Igual que getPoliticaFirmaOfConfig() de la V2. */
    protected PassarelaPolicyInfoSignature getPoliticaFirmaOfConfig(UsuariAplicacioConfiguracio config)
            throws I18NException {
        PolicyInfoSignature politica = SignatureUtils.getPolicyInfoSignature(config);
        if (politica == null) {
            return null;
        }
        return new PassarelaPolicyInfoSignature(politica.getPolicyIdentifier(), politica.getPolicyIdentifierHash(),
                politica.getPolicyIdentifierHashAlgorithm(), politica.getPolicyUrlDocument());
    }

    /**
     * Igual que getUseTimestampOfConfig() de la V2 però sense petició explícita de
     * l'usuari aplicació (timestampIsRequiredByUsrApp == null).
     */
    protected boolean getUseTimestampOfConfig(UsuariAplicacioConfiguracio config) throws I18NException {
        int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();
        switch (politicaSegellatDeTemps) {
            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
                return false;
            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
                return true;
            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
                return true;
            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
                return false;
            default:
                throw new I18NException("Política de segellat de temps de la configuracio de firma amb ID "
                        + config.getUsuariAplicacioConfigID() + " desconeguda: " + politicaSegellatDeTemps);
        }
    }

    /** Guarda el document firmat en un fitxer temporal referenciat des de la sessió. */
    protected void guardarFitxerFirmatEnSessio(HttpServletRequest request, FitxerBean signedFile,
            String originalFileName) throws Exception {
        String nom = signedFile.getNom();
        if (nom == null || nom.trim().length() == 0) {
            nom = "signed_" + originalFileName;
        }
        String mime = signedFile.getMime();
        if (mime == null) {
            mime = "application/octet-stream";
        }

        File signedTemp = File.createTempFile("FirmaSimpleAdmin_", "_signed");
        java.io.FileOutputStream fos = null;
        try {
            fos = new java.io.FileOutputStream(signedTemp);
            signedFile.getData().writeTo(fos);
            fos.flush();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Throwable ignored) {
                    // res
                }
            }
        }

        HttpSession session = request.getSession();
        // Si hi havia un fitxer firmat anterior, s'elimina
        esborrarFitxerFirmatDeSessio(session);

        session.setAttribute(SESSION_SIGNED_FILE_PATH, signedTemp.getAbsolutePath());
        session.setAttribute(SESSION_SIGNED_FILE_NAME, nom);
        session.setAttribute(SESSION_SIGNED_FILE_MIME, mime);
        session.setAttribute(SESSION_SIGNED_FILE_SIZE, signedTemp.length());
    }

    /** Elimina el fitxer firmat temporal guardat a la sessió (si n'hi ha). */
    protected void esborrarFitxerFirmatDeSessio(HttpSession session) {
        Object path = session.getAttribute(SESSION_SIGNED_FILE_PATH);
        if (path != null) {
            try {
                File f = new File((String) path);
                if (f.exists()) {
                    f.delete();
                }
            } catch (Throwable ignored) {
                // res
            }
        }
        session.removeAttribute(SESSION_SIGNED_FILE_PATH);
        session.removeAttribute(SESSION_SIGNED_FILE_NAME);
        session.removeAttribute(SESSION_SIGNED_FILE_MIME);
        session.removeAttribute(SESSION_SIGNED_FILE_SIZE);
    }

    // =========================================================================
    // PAS 6: Pantalla de resultat i descàrrega del document firmat
    // =========================================================================

    /**
     * Construeix la vista de resultat amb tota la informació de
     * {@link PassarelaSignatureInServerResults}.
     */
    protected ModelAndView construirVistaResultat(String usuariAplicacioID, Long perfilID, Long configID,
            PassarelaSignatureInServerResults fullResults) throws I18NException {

        ModelAndView mav = new ModelAndView(TILE_NAME);
        mav.addObject("step", 5);
        mav.addObject("usuariAplicacioID", usuariAplicacioID);
        mav.addObject("perfilID", perfilID);
        mav.addObject("configID", configID);

        // Camps de primer nivell de PassarelaSignatureInServerResults
        mav.addObject("pluginFirmaEnServidorId", fullResults.getPluginFirmaEnServidorId());
        mav.addObject("validacioResponseBySignID", fullResults.getValidacioResponseBySignID());

        // PassarelaFullResults
        PassarelaFullResults pfullResults = fullResults.getPassarelaFullResults();
        PassarelaSignatureStatus setStatus = pfullResults.getSignaturesSetStatus();
        mav.addObject("signaturesSetStatus", setStatus);
        mav.addObject("signaturesSetStatusText", statusText(setStatus.getStatus()));
        mav.addObject("signResults", pfullResults.getSignResults());

        // Text de l'estat de cada resultat de firma
        Map<String, String> statusTextBySignID = new java.util.HashMap<String, String>();
        if (pfullResults.getSignResults() != null) {
            for (PassarelaSignatureResult psr : pfullResults.getSignResults()) {
                statusTextBySignID.put(psr.getSignID(), statusText(psr.getStatus()));
            }
        }
        mav.addObject("statusTextBySignID", statusTextBySignID);

        // Informació del fitxer firmat disponible per descarregar
        mav.addObject("signedFileAvailable", Boolean.TRUE);
        return mav;
    }

    /** Descarrega el document firmat guardat a la sessió. */
    @RequestMapping(value = "/descarregar", method = RequestMethod.GET)
    public void descarregar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String path = (String) session.getAttribute(SESSION_SIGNED_FILE_PATH);
        String nom = (String) session.getAttribute(SESSION_SIGNED_FILE_NAME);
        String mime = (String) session.getAttribute(SESSION_SIGNED_FILE_MIME);

        if (path == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No hi ha cap document firmat disponible.");
            return;
        }

        File file = new File(path);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "El document firmat ja no està disponible.");
            return;
        }

        if (nom == null || nom.trim().length() == 0) {
            nom = "documentFirmat";
        }
        if (mime == null) {
            mime = "application/octet-stream";
        }

        response.setContentType(mime);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nom + "\"");
        response.setContentLength((int) file.length());

        java.io.FileInputStream input = null;
        OutputStream output = null;
        try {
            input = new java.io.FileInputStream(file);
            output = response.getOutputStream();
            byte[] buffer = new byte[8192];
            int len;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Throwable ignored) {
                    // res
                }
            }
        }
    }

    /** Retorna un text descriptiu de l'estat d'una firma. */
    protected String statusText(int status) {
        switch (status) {
            case StatusSignature.STATUS_INITIALIZING:
                return status + " - Inicialitzant";
            case StatusSignature.STATUS_IN_PROGRESS:
                return status + " - En progrés";
            case StatusSignature.STATUS_FINAL_OK:
                return status + " - Finalitzada correctament";
            case StatusSignature.STATUS_FINAL_ERROR:
                return status + " - Finalitzada amb error";
            case StatusSignature.STATUS_CANCELLED:
                return status + " - Cancel·lada";
            default:
                return String.valueOf(status);
        }
    }


    /** Torna al pas 4 (selecció de fitxer) mostrant un missatge d'error. */
    protected ModelAndView tornarAPasFitxerAmbError(String usuariAplicacioID, Long perfilID, Long configID,
            String errorMsg) throws I18NException {
        ModelAndView mav = new ModelAndView(TILE_NAME);
        mav.addObject("step", 4);
        mav.addObject("usuariAplicacioID", usuariAplicacioID);
        mav.addObject("perfilID", perfilID);
        mav.addObject("configID", configID);
        if (perfilID != null) {
            PerfilDeFirma perfil = (PerfilDeFirma) perfilDeFirmaEjb.findByPrimaryKey(perfilID);
            if (perfil != null) {
                mav.addObject("perfilCodi", perfil.getCodi());
            }
        }
        if (configID != null) {
            UsuariAplicacioConfiguracioJPA config = (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioEjb
                    .findByPrimaryKey(configID);
            if (config != null) {
                mav.addObject("configNom", config.getNom());
            }
        }
        mav.addObject("errorMsg", errorMsg);
        return mav;
    }

}
