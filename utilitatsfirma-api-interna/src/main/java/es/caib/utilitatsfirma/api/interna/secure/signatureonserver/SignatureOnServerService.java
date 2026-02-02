package es.caib.utilitatsfirma.api.interna.secure.signatureonserver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.ISignaturePlugin;
import org.fundaciobit.pluginsib.signature.api.PolicyInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureFormForUpgrade;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.Document;
import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.KeyValue;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.ejb.IdiomaService;
import es.caib.utilitatsfirma.ejb.PerfilDeFirmaService;
import es.caib.utilitatsfirma.ejb.PerfilsPerUsuariAplicacioService;
import es.caib.utilitatsfirma.ejb.UsuariAplicacioService;
import es.caib.utilitatsfirma.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.utilitatsfirma.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.utilitatsfirma.logic.generator.IdGeneratorFactory;
import es.caib.utilitatsfirma.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.utilitatsfirma.logic.passarela.api.NoCompatibleSignaturePluginException;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFullResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaKeyValue;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureInServerResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureResult;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidationInfo;
import es.caib.utilitatsfirma.logic.passarela.api.UpgradeResponse;
import es.caib.utilitatsfirma.logic.passarela.api.ValidacioCompletaResponse;
import es.caib.utilitatsfirma.logic.utils.I18NLogicUtils;
import es.caib.utilitatsfirma.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.utilitatsfirma.logic.utils.SignatureUtils;
import es.caib.utilitatsfirma.model.bean.FitxerBean;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import es.caib.utilitatsfirma.model.fields.IdiomaFields;
import es.caib.utilitatsfirma.model.fields.PerfilDeFirmaFields;
import es.caib.utilitatsfirma.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields;
import es.caib.utilitatsfirma.persistence.PluginJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author anadal
 * 2 may 2025 14:01:35
 */
@Path(SignatureOnServerService.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = SignatureOnServerService.TAG_NAME,
                description = "Firma Server Swagger v1. API Interna de PortaFIB que ofereix serveis de firma en servidor."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = SignatureOnServerService.SECURITY_NAME, scheme = "basic")
@ApiResponses(
        value = {
                @ApiResponse(
                        responseCode = "400",
                        description = "Paràmetres incorrectes",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = RestExceptionInfo.class))),
                @ApiResponse(
                        responseCode = "401",
                        description = "No Autenticat",
                        content = { @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = RestExceptionInfo.class)) }),
                @ApiResponse(
                        responseCode = "403",
                        description = "No autoritzat",
                        content = { @Content(
                                mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = RestExceptionInfo.class)) }),
                @ApiResponse(
                        responseCode = "500",
                        description = "Error no controlat",
                        content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignTypeConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignAlgorithmConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignModeConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignOperationConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignaturesTableLocationConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = SignProfileConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = StatusConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = DocumentaryTypeConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = RestExceptionInfo.class)) }) })
public class SignatureOnServerService
        extends RestUtils/*extends AbstractSignatureService /* implements CommonsSwaggerOperations*/ {

    private static final boolean esFirmaEnServidor = true;

    public static final String PATH = "/secure/signatureonserver/v1";

    public static final String TAG_NAME = "SignatureOnServer v1"; // => FirmaEnServidorV1Api

    public static final String SECURITY_NAME = "BasicAuth";

    public static final String TIPUS_EN_SERVIDOR = "SERVER";

    public static final Map<SignatureTypeFormEnumForUpgrade, String> upgradeTypesToSimpleTypes = new HashMap<SignatureTypeFormEnumForUpgrade, String>();

    //  SignatureFormForUpgrade to EniPerfilDeFirma
    public static final Map<String, String> upgradeTypesToSignatureFormForUpgrade = new HashMap<String, String>();

    static {

        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_T, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_C, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_X, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_X1, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_X2, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_XL, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_XL1, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_XL2, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_A, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_T_LEVEL, FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_LT_LEVEL,
                FileInfoSignature.SIGN_TYPE_XADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.XAdES_LTA_LEVEL,
                FileInfoSignature.SIGN_TYPE_XADES);

        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_T, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_X, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_X1, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_X2, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_XL, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_XL1, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_XL2, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_A, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_T_LEVEL, FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_LT_LEVEL,
                FileInfoSignature.SIGN_TYPE_CADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.CAdES_LTA_LEVEL,
                FileInfoSignature.SIGN_TYPE_CADES);

        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_LTV, FileInfoSignature.SIGN_TYPE_PADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_T_LEVEL, FileInfoSignature.SIGN_TYPE_PADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_LT_LEVEL,
                FileInfoSignature.SIGN_TYPE_PADES);
        upgradeTypesToSimpleTypes.put(SignatureTypeFormEnumForUpgrade.PAdES_LTA_LEVEL,
                FileInfoSignature.SIGN_TYPE_PADES);

        // -----------------------------------

        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.T, "T");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.C, "C");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.X, "X");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.X_1, "X");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.X_2, "X");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.X_L, "XL");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.X_L_1, "XL");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.X_L_2, "XL");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.A, "A");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.T_LEVEL, "BASELINE T-Level");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.LT_LEVEL, "BASELINE LT-Level");
        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.LTA_LEVEL, "BASELINE LTA-Level 2");

        // EPES,   BASELINE B-Level,

        upgradeTypesToSignatureFormForUpgrade.put(SignatureFormForUpgrade.PADES_LTV, "LTV");

    }
    
    
    @EJB(mappedName = PerfilsPerUsuariAplicacioService.JNDI_NAME)
    protected PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

    
    @EJB(mappedName =  PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
    protected PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;
    
    /*
    @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
    protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;
    
    @EJB(mappedName = UsuariPersonaService.JNDI_NAME, beanName = "UsuariPersonaEJB")
    protected UsuariPersonaService usuariPersonaEjb;
    
    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME) // , beanName = "EntitatEJB")
    protected EntitatLogicaLocal entitatLogicaEjb;
       
    */ 
    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioLogicaEjb;
    
    

    
    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;
    
    @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaServidorLogicaLocal modulDeFirmaServidorEjb;
    
    @EJB(mappedName = PerfilDeFirmaService.JNDI_NAME)
    protected PerfilDeFirmaService perfilDeFirmaEjb;
    

    @EJB(mappedName = es.caib.utilitatsfirma.ejb.IdiomaService.JNDI_NAME)
    protected IdiomaService idiomaEjb;

    protected final Logger log = Logger.getLogger(getClass());

    public static final String GETDOCUMENTARYTYPES_SUMMARY = "Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l'entitat i tipus documentals de l'usuari aplicació";

    @Path(value = "/getDocumentaryTypes")
    @GET
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = { TAG_NAME },
            operationId = "getDocumentaryTypes",
            summary = "Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l'entitat i tipus documentals de l'usuari aplicació")

    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(
                                    uniqueItems = true,
                                    schema = @Schema(implementation = DocumentaryType.class)))),

            })
    public Set<DocumentaryType> getDocumentaryTypes(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String languageUI) throws RestException {

        String usuariAplicacio = checkUsuariAplicacio(request);

        try {

            // TODO XXX Cridar a portafib per obtenir els tipus documentals
            if (false) {
                throw new I18NException(
                        "Error obtenint els tipus documentals per a l'usuari aplicació " + usuariAplicacio);
            }

            return null;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

            throw new RestException(msg);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getTypesOfDocumentsAvailable: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    @Path("/getLanguages")
    @GET
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)

    @Operation(tags = { TAG_NAME }, operationId = "getLanguages", summary = "Retorna els idiomes disponibles.")

    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(
                                    uniqueItems = true,
                                    schema = @Schema(implementation = KeyValue.class)))) })
    public Set<KeyValue> getLanguages(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException {

        // Check Idioma
        language = RestUtils.checkLanguage(language);

        try {
            SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(IdiomaFields.IDIOMAID.select,
                    IdiomaFields.NOM.select);

            List<StringKeyValue> idiomes = idiomaEjb.executeQuery(smskv, IdiomaFields.SUPORTAT.equal(true));

            Set<KeyValue> languages = new HashSet<KeyValue>();
            for (StringKeyValue skv : idiomes) {
                languages.add(new KeyValue(skv.getKey(), skv.getValue()));
            }

            return languages;

        } catch (I18NException i18ne) {

            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(language));

            throw new RestException(msg, i18ne);

        } catch (Throwable th) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut cridant a getLanguages: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }

    @Path("/getProfiles")
    @GET
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)

    @Operation(tags = { TAG_NAME }, operationId = "getProfiles", summary = "Retorna els perfils de firma.")

    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(
                                    uniqueItems = true,
                                    schema = @Schema(implementation = Profile.class)))) })
    public Set<Profile> getProfiles(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("language")
            String language) throws RestException {

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => ENTRA");

        String usrApp = checkUsuariAplicacio(request);

        // Check Idioma
        language = RestUtils.checkLanguage(language);

        log.info("XYZ ZZZ REST_SERVIDOR:: getAvailableProfiles() => LANG: " + language);

        try {

            // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2

            //String userApp = getUserApp(request);
            List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
                    PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                    PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usrApp));

            List<PerfilDeFirma> perfils = perfilDeFirmaEjb
                    .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));

            Set<Profile> profiles = new HashSet<Profile>();

            for (PerfilDeFirma perfil : perfils) {

                String codiPerfil = perfil.getCodi();

                String descripcio = perfil.getDescripcio();

                // Falta llegir-ho de la BBDD
                Profile ap = new Profile(codiPerfil, perfil.getNom(), descripcio, null);

                profiles.add(ap);
            }

            return profiles;

        } catch (Throwable th) {

            // XYZ ZZZ Traduir
            String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }

    }

    @Path("/versio")
    @GET
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })

    @Operation(tags = { TAG_NAME }, operationId = "versio", summary = "Retorna la versió d'aquest Servei")
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Retornada correctament la versió d'aquest Servei",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = String.class))) })
    public String versio() {
        return "1.0";
    }

    @Path(value = "/upgradeSignature")
    @POST
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "upgradeSignature",
            requestBody = @RequestBody(
                    description = "Funcio de upgrade se firma digital",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "upgradeSimpleSignDocumentRequest",
                                    required = true,
                                    implementation = UpgradeRequest.class))),
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = UpgradeResponse.class))) })
    public es.caib.utilitatsfirma.api.interna.secure.signatureonserver.UpgradeResponse upgradeSignature(
            @Parameter(hidden = true) @Context
            HttpServletRequest request, @RequestBody
            UpgradeRequest fsur,
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI) {

        languageUI = RestUtils.checkLanguage(languageUI);

        try {

            log.info(" XYZ ZZZ eNTRA A upgradeSignature => upgrade: " + fsur);
            String usuariAplicacioID = checkUsuariAplicacio(request);

            if (fsur == null) {
                // XYZ ZZZ TRA
                String errorMsg = "L'objecte FirmaSimpleUpgradeRequest val null.";
                throw new RestException(errorMsg, "FirmaSimpleUpgradeRequest");
            }

            // XYZ ZZZ Falta checks sobre fsur

            Document signature = fsur.getSignature();

            final PerfilDeFirma perfilDeFirma;
            {
                CommonInfo commonInfo = new CommonInfo();
                commonInfo.setSignProfile(fsur.getProfileCode());
                perfilDeFirma = getPerfilDeFirma(commonInfo, esFirmaEnServidor, usuariAplicacioID);
                fsur.setProfileCode(perfilDeFirma.getCodi());
            }

            UsuariAplicacioConfiguracio config;

            config = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioUsuariAplicacioPerUpgrade(
                    usuariAplicacioID, perfilDeFirma, getFirmaSimpleUpgradeRequestApisib(fsur, languageUI));

            if (log.isDebugEnabled()) {
                log.info("UPGRADE CONFIG  " + config.getNom());
            }

            SignatureTypeFormEnumForUpgrade singTypeForm = null;

            Integer upgradeID = config.getUpgradeSignFormat();

            for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
                if (upgradeID == up.getId()) {
                    singTypeForm = up;
                    break;
                }
            }

            if (singTypeForm == null) {
                // XYZ ZZZ Traduir
                String errorMsg = "El identificador d'Extensió de Firma " + upgradeID + " no existeix.";
                throw new RestException(Status.INTERNAL_SERVER_ERROR, errorMsg);
            }

            final boolean isDebug = log.isDebugEnabled();

            if (isDebug) {
                log.info("Fent UPGRADE a " + singTypeForm);
            }

            UpgradeResponse upgradeResponse;

            //String entitatId = getEntitatId(usuariAplicacioID, languageUI);
            //EntitatJPA entitat = getEntitatJpa(entitatId);

            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(usuariAplicacioID);

            upgradeResponse = passarelaDeFirmaEnServidorEjb.upgradeSignature(getFirmaSimpleFile(signature),
                    getFirmaSimpleFile(fsur.getDetachedDocument()), getFirmaSimpleFile(fsur.getTargetCertificate()),
                    singTypeForm, usuariAplicacio, perfilDeFirma, config, languageUI);

            // VALIDATE
            final String mime;
            String signatureType = upgradeTypesToSimpleTypes.get(singTypeForm);
            if (FileInfoSignature.SIGN_TYPE_XADES.equals(signatureType)) {
                mime = "application/xml";
            } else {
                mime = null;
            }

            UpgradedFileInfo upgradedFileInfo = constructFirmaSimpleUpgradedFileInfo(upgradeResponse, signatureType,
                    singTypeForm);

            Document signedFile = new Document(null, mime, upgradeResponse.getUpgradedSignature());

            es.caib.utilitatsfirma.api.interna.secure.signatureonserver.UpgradeResponse fsuresp;
            fsuresp = new es.caib.utilitatsfirma.api.interna.secure.signatureonserver.UpgradeResponse(signedFile,
                    upgradedFileInfo);

            //HttpHeaders headers = addAccessControllAllowOrigin();
            //ResponseEntity<?> re = new ResponseEntity<FirmaSimpleUpgradeResponse>(fsuresp, headers, HttpStatus.OK);

            if (isDebug) {
                log.info("Surt de upgradeSignature => FINAL OK");
            }

            return fsuresp;

        } catch (NoCompatibleSignaturePluginException nape) {

            String errorMsg = getNoAvailablePluginErrorMessage(languageUI, false, nape);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, errorMsg);

        } catch (I18NException i18ne) {
            // XYZ ZZZ
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg);

        } catch (Throwable th) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut durant el procés d'actualització de firma: " + th.getMessage();
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg);

        }

    }
    
    
    

    protected PerfilDeFirma getPerfilDeFirma(CommonInfo commonInfo, final boolean esFirmaEnServidor, String username)
            throws I18NException {

        String codiPerfil = commonInfo.getSignProfile();

        PerfilDeFirma perfil;
        String usrAppID = username;
        if (codiPerfil == null || codiPerfil.trim().length() == 0) {
            perfil = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirmaPerApiFirmaSimple(usrAppID);
            codiPerfil = perfil.getCodi();
            commonInfo.setSignProfile(codiPerfil);
        } else {
            perfil = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirma(usrAppID, codiPerfil);
        }
        return perfil;
    }
    
    

    @Path("/signdocument")
    @POST
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Operation(
            tags = TAG_NAME,
            operationId = "signdocument",
            requestBody = @RequestBody(
                    description = "Operacio de firma simple en servidor d'un document",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    name = "firmaSimpleSignDocumentRequest",
                                    required = true,
                                    implementation = SignDocumentRequest.class))),
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SignDocumentResponse.class))) })
    public SignDocumentResponse signDocument(@Parameter(hidden = true) @Context
    HttpServletRequest request, @RequestBody
    SignDocumentRequest simpleSignature) throws RestException {

        String transactionID = null;
        String languageUI = "ca";
        try {
            log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignature: " + simpleSignature);

            // Validar simpleSignature
            // XYZ ZZZ Canviar per idioma per defecte

            if (simpleSignature == null) {
                // XYZ ZZZ TRA
                String errMsg = "L´objecte FirmaSimpleSignDocumentRequest passat per paràmetre val null";
                throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest");
            }

            if (simpleSignature.getCommonInfo() == null) {
                // XYZ ZZZ TRA
                String errMsg = "L´objecte commonInfo(FirmaSimpleCommonInfo) definit dins de FirmaSimpleSignDocumentRequest val null";
                throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest.commonInfo");
            }

            languageUI = simpleSignature.getCommonInfo().getLanguageUI();
            if (languageUI == null || languageUI.trim().length() == 0) {
                // XYZ ZZZ TRA
                String errMsg = "El camp languageUI definit dins de FirmaSimpleSignDocumentRequest.FirmaSimpleCommonInfo està buit o val null";
                throw new RestException(Status.BAD_REQUEST, errMsg,
                        "FirmaSimpleSignDocumentRequest.commonInfo.languageUI");
            }

            languageUI = RestUtils.checkLanguage(languageUI);

            log.info("simpleSignaturesSet.getCommonInfo().getSignProfile() ==> "
                    + simpleSignature.getCommonInfo().getSignProfile());
            log.info("simpleSignaturesSet.getCommonInfo().getLanguageUI() ==> " + languageUI);

            Long signaturePluginId = null;
            transactionID = internalGetTransacction();
            String username = request.getUserPrincipal().getName();

            // Si codi de Perfil val null, llavors en cerca un.
            PerfilDeFirma perfil = getPerfilDeFirma(simpleSignature.getCommonInfo(), esFirmaEnServidor, username);

            PerfilConfiguracionsDeFirma pcf;

            org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest simpleSignatureRequestApisib;
            simpleSignatureRequestApisib = getSimpleSignatureRequestApisib(simpleSignature);

            pcf = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioFirmaPerApiFirmaSimpleEnServidor(username,
                    perfil.getCodi(), simpleSignatureRequestApisib);

            // ================== CODI COMU ==============

           
            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(username);

            PassarelaSignaturesSet pss = convertRestBean2PassarelaBeanServer(transactionID, simpleSignature, username,
                     pcf.perfilDeFirma, pcf.configBySignID);

            log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");
            PassarelaSignatureInServerResults fullResults;

            fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss,  usuariAplicacio, pcf.perfilDeFirma,
                    pcf.configBySignID);

            signaturePluginId = fullResults.getPluginFirmaEnServidorId();

            //SignDocumentsResponse fssfrFull = processPassarelaResults(fullResults, pss, isSignatureInServer,
            //        fullResults.getPluginFirmaEnServidorId());
            // private SignDocumentsResponse processPassarelaResults(PassarelaSignatureInServerResults completeResults,
            // PassarelaSignaturesSet pss, boolean isSignatureInServer, Long signaturePluginID) throws Exception {
            //
            ProcessStatus statusGlobal;
            List<SignatureResponse> results;
            {
                PassarelaFullResults pfullResults = fullResults.getPassarelaFullResults();

                PassarelaSignatureStatus passarelaSS = pfullResults.getSignaturesSetStatus();

                statusGlobal = new ProcessStatus(
                        passarelaSS.getStatus(), passarelaSS.getErrorMessage(), passarelaSS.getErrorStackTrace());

                if (passarelaSS.getStatus() == StatusSignature.STATUS_FINAL_OK) {

                    List<PassarelaSignatureResult> passarelaSR = pfullResults.getSignResults();

                    results = new ArrayList<SignatureResponse>();

                    Map<String, PassarelaFileInfoSignature> infoBySignID = new HashMap<String, PassarelaFileInfoSignature>();
                    for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {

                        infoBySignID.put(pfis.getSignID(), pfis);

                    }

                    ValidacioCompletaResponse validacioInfo;
                    for (PassarelaSignatureResult psr : passarelaSR) {

                        validacioInfo = fullResults.getValidacioResponseBySignID().get(psr.getSignID());

                        results.add(convertPassarelaSignatureResult2FirmaSimpleSignatureResult(psr,
                                pss.getCommonInfoSignature(), infoBySignID.get(psr.getSignID()), validacioInfo,
                                esFirmaEnServidor, signaturePluginId));
                    }
                } else {
                    results = null;
                }
            }

            //SignDocumentsResponse fssfr;
            //fssfr = new SignDocumentsResponse(statusSignatureProcess, results);
            //return fssfr;

            //ProcessStatus statusGlobal = fssfrFull.getStatusSignatureProcess();

            SignatureResponse result;

            String signID = simpleSignature.getFileInfoSignature().getSignID();

            if (statusGlobal.getStatus() == (int) StatusConstants.STATUS_FINAL_OK.getValue()) {
                // Només hi ha d'haver una firma
                result = results.get(0);

                if (result.getStatus().getStatus() == (int) StatusConstants.STATUS_FINAL_OK.getValue()) {

                    // En API DE FIRMA SIMPE; EN SERVIDOR NOMES S'ENVIA UN DOCUMENT DE FIRMA A LA
                    // VEGADA
                    PassarelaFileInfoSignature fileInfo = pss.getFileInfoSignatureArray()[0];

                    final String profileSignType = null;

                    final boolean useSignPolicy = (pss.getCommonInfoSignature().getPolicyInfoSignature() != null);

                    UsuariAplicacioConfiguracio config = pcf.configBySignID.get(signID);

                    ValidacioCompletaResponse vcr = fullResults
                            .getValidacioResponseBySignID().get(fileInfo.getSignID());

                    SignedFileInfo sfi = constructFirmaSimpleSignedFileInfo(config, fileInfo,
                            simpleSignature.getFileInfoSignature(), profileSignType, result.getSignedFile(), 
                            useSignPolicy, vcr, languageUI, signaturePluginId);

                    result.setSignedFileInfo(sfi);

                }
            } else {
                // Passam l'error general a l'error de la firma

                result = new SignatureResponse(signID, statusGlobal, null, null);
            }

            // Ho podria collir de Signer però es més senzill consultar-ho de nou
            SignPlugin signPlugin;
            if (signaturePluginId != null) {
                signPlugin = getSignaturePluginInformation( languageUI, signaturePluginId);
            } else {
                signPlugin = null;
            }

            log.info(" XYZ ZZZ Surt de signDocuments => FINAL");

            return new SignDocumentResponse(result, signPlugin);

        } catch (NoCompatibleSignaturePluginException nape) {

            throw new RestException(Status.INTERNAL_SERVER_ERROR,
                    getNoAvailablePluginErrorMessage(languageUI, esFirmaEnServidor, nape), nape);

        } catch (Throwable th) {

            if (th instanceof RestException) {
                throw (RestException) th;
            }

            String msgOrig;
            if (th instanceof I18NException) {
                I18NException i18ne = (I18NException) th;
                msgOrig = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            } else {
                msgOrig = th.getMessage();
            }

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant el proces de Firma: " + msgOrig;
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, th);

        } finally {
            if (transactionID != null) {
                try {
                    File transactionFolder = getTransactionFolder(transactionID);
                    org.apache.commons.io.FileUtils.deleteDirectory(transactionFolder);
                } catch (Exception e) {
                    log.error("Error desconegut fent neteja dels fitxers "
                            + "de ApiFirmaEnServidorSimple de la transacció " + transactionID + ":" + e.getMessage(),
                            e);
                }
            }

        }
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest getFirmaSimpleUpgradeRequestApisib(
            UpgradeRequest signatureRequest, String languageUI) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest signatureReuqestApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest();
        signatureReuqestApisib.setProfileCode(signatureRequest.getProfileCode());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile signatureFile = getFirmaSimpleFile(
                signatureRequest.getSignature());
        signatureReuqestApisib.setSignature(signatureFile);

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile targetCertificate = getFirmaSimpleFile(
                signatureRequest.getTargetCertificate());
        signatureReuqestApisib.setTargetCertificate(targetCertificate);

        signatureReuqestApisib.setLanguageUI(languageUI);
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile detachedDocument = getFirmaSimpleFile(
                signatureRequest.getDetachedDocument());

        signatureReuqestApisib.setDetachedDocument(detachedDocument);

        return signatureReuqestApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest getSimpleSignatureRequestApisib(
            SignDocumentRequest signatureRequest) {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest signatureReuqestApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest();

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo commonFileInfo = getCommonFileInfoApisib(
                signatureRequest.getCommonInfo());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignature = getFileInfoSignatureApisib(
                signatureRequest.getFileInfoSignature());
        signatureReuqestApisib.setCommonInfo(commonFileInfo);
        signatureReuqestApisib.setFileInfoSignature(fileInfoSignature);
        return signatureReuqestApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo getCommonFileInfoApisib(
            CommonInfo commonFileInfo) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo commonFileInfoApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo();
        commonFileInfoApisib.setAdministrationID(commonFileInfo.getAdministrationID());
        commonFileInfoApisib.setLanguageUI(commonFileInfo.getLanguageUI());
        commonFileInfoApisib.setOrganizationID(commonFileInfo.getOrganizationID());
        commonFileInfoApisib.setSignerEmail(commonFileInfo.getSignerEmail());
        commonFileInfoApisib.setSignProfile(commonFileInfo.getSignProfile());
        commonFileInfoApisib.setUsername(commonFileInfo.getUsername());
        return commonFileInfoApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature getFileInfoSignatureApisib(
            es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature fileInfoSignature) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignatureApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature();
        List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> additionalInformationList = getAdditionalInformationList(
                fileInfoSignature.getAdditionalInformation());

        fileInfoSignatureApisib.setAdditionalInformation(additionalInformationList);
        fileInfoSignatureApisib.setDocumentType(fileInfoSignature.getDocumentType());
        fileInfoSignatureApisib.setExpedientCodi(fileInfoSignature.getExpedientCodi());
        fileInfoSignatureApisib.setExpedientNom(fileInfoSignature.getExpedientNom());
        fileInfoSignatureApisib.setExpedientUrl(fileInfoSignature.getExpedientUrl());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaType = getFirmaSimpleFile(
                fileInfoSignature.getFileToSign());

        fileInfoSignatureApisib.setFileToSign(newFirmaType);

        fileInfoSignatureApisib.setLanguageSign(fileInfoSignature.getLanguageSign());
        fileInfoSignatureApisib.setLocation(fileInfoSignature.getLocation());
        fileInfoSignatureApisib.setName(fileInfoSignature.getName());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile previousDetachedFile = getFirmaSimpleFile(
                fileInfoSignature.getPreviusSignatureDetachedFile());
        fileInfoSignatureApisib.setPreviusSignatureDetachedFile(previousDetachedFile);

        fileInfoSignatureApisib.setProcedimentCodi(fileInfoSignature.getProcedimentCodi());
        fileInfoSignatureApisib.setProcedimentNom(fileInfoSignature.getProcedimentNom());
        fileInfoSignatureApisib.setReason(fileInfoSignature.getReason());
        fileInfoSignatureApisib.setSignID(fileInfoSignature.getSignID());
        fileInfoSignatureApisib.setSignNumber(fileInfoSignature.getSignNumber());

        return fileInfoSignatureApisib;
    }

    private List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> getAdditionalInformationList(
            List<KeyValue> aditionalInformation) {
        List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> result = new ArrayList<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue>();
        if (aditionalInformation != null) {

            for (KeyValue keyValue : aditionalInformation) {
                org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue newKeyValue = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue();
                newKeyValue.setKey(keyValue.getKey());
                newKeyValue.setValue(keyValue.getValue());
                result.add(newKeyValue);
            }
        }
        return result;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile getFirmaSimpleFile(
            Document firmaSimpleFile) {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaSimpleFile = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();
        if (firmaSimpleFile != null && firmaSimpleFile.getData() != null) {
            newFirmaSimpleFile.setData(firmaSimpleFile.getData());
            newFirmaSimpleFile.setMime(firmaSimpleFile.getMime());
            newFirmaSimpleFile.setNom(firmaSimpleFile.getName());
        }
        return newFirmaSimpleFile;
    }

    protected String getNoAvailablePluginErrorMessage(String language, boolean firma,
            NoCompatibleSignaturePluginException ex) {
        // TODO XYZ ZZZ Traduir
        String msg;
        if (firma) {
            msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
        } else {
            msg = "El plugin seleccionat no suporta el proces d'actualització de firma.";
        }

        if (ex != null && ex.getMessage() != null) {
            msg = msg + " (" + ex.getMessage() + ")";
        }

        return msg;
    }

    protected UpgradedFileInfo constructFirmaSimpleUpgradedFileInfo(UpgradeResponse upgradeResponse,
            String signatureType, SignatureTypeFormEnumForUpgrade singTypeForm) throws I18NException {

        String profileSignType = singTypeForm.getName();

        log.info("Cridant a constructFirmaSimpleUpgradedFileInfo:: signatureType => " + signatureType
                + " profileSignType => " + profileSignType);

        ValidateSignatureResponse vsr = upgradeResponse.getValidacioResponse().getValidateSignatureResponse();

        UpgradedFileInfo upgradedFileInfo;

        if (vsr == null || vsr.getValidationStatus() == null) {
            // No s'ha fet validacio
            upgradedFileInfo = new UpgradedFileInfo();

            upgradedFileInfo.setSignType(signatureType);
            upgradedFileInfo.setValidationInfo(new ValidationInfo());

            upgradedFileInfo.setEniPerfilFirma(profileSignType);

            //singTypeForm.getFormat()

            // SI es PADES llavors el signMode es attached
            if (FileInfoSignature.SIGN_TYPE_PADES.equals(signatureType)) {
                int signMode = Constants.SIGN_MODE_ATTACHED_ENVELOPED;
                upgradedFileInfo.setSignMode(signMode);

                String eniTipoFirma = SignatureUtils.getEniTipoFirma(signatureType, signMode);
                upgradedFileInfo.setEniTipoFirma(eniTipoFirma);

            }

        } else {

            final String signType = vsr.getSignType();
            final String signAlgorithm = null;

            int signFormat = vsr.getSignMode();

            int signMode = signFormat;

            // XYZ ZZZ
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            final String eniPerfilFirma = null; // vsr.getSignProfile();

            ValidationInfo validationInfo = new ValidationInfo();

            es.caib.utilitatsfirma.logic.passarela.api.ValidacioCompletaResponse vcr;
            vcr = upgradeResponse.getValidacioResponse();
            validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());
            validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
            validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());

            final List<KeyValue> additionInformation = null;

            upgradedFileInfo = new UpgradedFileInfo(signType, signAlgorithm, signMode, eniTipoFirma, eniPerfilFirma,
                    validationInfo, additionInformation);

        }

        /**
         *  Para las firmas XADES y CADES: EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level,
         *                                        BASELINE LT-Level, BASELINE LTA-Level 2.
         *  Para las firmas PADES: EPES, LTV, BASELINE B-Level, BASELINE T.
         */
        upgradedFileInfo.setEniPerfilFirma(upgradeTypesToSignatureFormForUpgrade.get(singTypeForm.getFormat()));
        return upgradedFileInfo;
    }

    protected String checkUsuariAplicacio(HttpServletRequest request) {

        return request.getUserPrincipal().getName();

        //UsuariAplicacioJPA full = checkUsuariAplicacioFull(request);
        //return full.getUsuariAplicacioID();
    }

    protected String internalGetTransacction() {
        String transactionID = IdGeneratorFactory.getGenerator().generate();
        if (log.isDebugEnabled()) {
            log.debug("Creada transacció amb ID = [" + transactionID + "]");
        }
        return transactionID;
    }

    public static File getTransactionFolder(String transactionID) {
        
        
        final String type = TIPUS_EN_SERVIDOR;
        
        File folderApiFirmaSimple = new File(FileSystemManager.getFilesPath(), "APIFIRMASIMPLE");

        File folderType = new File(folderApiFirmaSimple, type);

        File folderTransaction = new File(folderType, transactionID);
        return folderTransaction;
    }
    

    /**
     *  Obté la información del plugin de firma
     * @param isSignatureInServer
     * @param languageUI
     * @param signaturePluginId
     * @return
     * @throws RestException
     */
    protected SignPlugin getSignaturePluginInformation(String languageUI,
            Long signaturePluginId) throws Exception {

        if (signaturePluginId == null) {
            return null;
        }

        String langUI = RestUtils.checkLanguage(languageUI);

        PluginJPA plugin;
        ISignaturePlugin signaturePlugin;
         {
            plugin = modulDeFirmaServidorEjb.findByPrimaryKey(signaturePluginId);
            if (plugin == null) {
                log.warn("No s'ha trobat el plugin de firma en servidor amb ID: " + signaturePluginId);
                return null;
            }
            signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(signaturePluginId);
        } 

        try {
            {
                signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(signaturePluginId);
                if (signaturePlugin == null) {
                    log.warn("No s'ha pogut instanciar plugin de firma en servidor amb ID: " + signaturePluginId);
                    return null;
                }
            } 
        } catch (Throwable e) {
            log.error("Error no controlat instanciant el plugin de firma amb ID: " + signaturePluginId, e);
            return null;
        }

        SignPlugin sp = new SignPlugin();

        sp.setSignaturePluginId(String.valueOf(signaturePluginId));

        sp.setSignaturePluginCode(plugin.getCodi());

        sp.setSignaturePluginNameInternal(signaturePlugin.getName(new Locale(langUI)));

        sp.setSignaturePluginNamePublic(plugin.getNom().getTraduccio(langUI).getValor());

        sp.setSignaturePluginDescriptionPublic(plugin.getDescripcioCurta().getTraduccio(langUI).getValor());

        return sp;

    }
    
    

    /**
     * 
     * @param psr
     * @param commonInfo
     * @param infoSignature
     * @param infoValidacio
     * @param isSignatureInServer
     * @return
     * @throws Exception
     */
    protected SignatureResponse convertPassarelaSignatureResult2FirmaSimpleSignatureResult(PassarelaSignatureResult psr,
            PassarelaCommonInfoSignature commonInfo, PassarelaFileInfoSignature infoSignature,
            ValidacioCompletaResponse infoValidacio, boolean isSignatureInServer,
            Long signaturePluginId) throws Exception {

        ProcessStatus status = new ProcessStatus(psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace());

        SignedFileInfo sfi = null;
        Document file = null;

        if (psr.getStatus() == StatusSignature.STATUS_FINAL_OK) {

            file = convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile());

            final int signOperation = infoSignature.getSignOperation();
            final String signType = infoSignature.getSignType();
            final String signAlgorithm = infoSignature.getSignAlgorithm();
            final int signMode = infoSignature.getSignMode();
            final int signaturesTableLocation = infoSignature.getSignaturesTableLocation();
            final Boolean timeStampIncluded2 = infoSignature.getUseTimeStamp2();
            final boolean policyIncluded = (commonInfo.getPolicyInfoSignature() != null);

            /*
             * eEMGDE.Firma.TipoFirma.FormatoFirma (eEMGDE17.1.1): TF01 (CSV), TF02 (XAdES
             * internally detached signature), TF03 (XAdES enveloped signature), TF04 (CAdES
             * detached/explicit signature), TF05 (CAdES attached/implicit signature), TF06
             * (PAdES)
             */
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);
/*
            if (eniTipoFirma == null) {
                if (psr.getCustodyInfo() != null && psr.getCustodyInfo().getCustodyFileCSV() != null) {
                    eniTipoFirma = "TF01";
                }
            }
            */

            String eniPerfilFirma = null;
            if (infoValidacio != null) {
                eniPerfilFirma = infoValidacio.getPerfilDeFirma();
            }

            if (eniPerfilFirma == null) {
                // HO INTENTAM CALCULAR

                // EPES T C X XL A 'BASELINE B-Level' 'BASELINE LT-Level' 'BASELINE
                // LTA-Level' 'BASELINE
                // T-Level' LTV
                if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
                    // 2.- Para las firmas PADES: EPES, LTV, BASELINE B-Level, BASELINE T-Level
                    // TODO XYZ ZZZ Falta LTV
                    if (timeStampIncluded2 != null && timeStampIncluded2.booleanValue() == true) {
                        eniPerfilFirma = "BASELINE T-Level";
                    } else if (policyIncluded) {
                        eniPerfilFirma = "EPES";
                    } else {
                        eniPerfilFirma = "BASELINE B-Level";
                    }
                } else {
                    // 1.- Para las firmas XADES y CADES:
                    // EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level, BASELINE LT-Level,
                    // BASELINE
                    // LTA-Level.
                    // TODO XYZ ZZZ ZZZ Falta EPES, T, C, X, XL, A, BASELINE LTA-Level.
                    if (timeStampIncluded2 != null && timeStampIncluded2.booleanValue() == true) {
                        eniPerfilFirma = "BASELINE T-Level";
                    } else if (policyIncluded) {
                        eniPerfilFirma = "EPES";
                    } else {
                        eniPerfilFirma = "BASELINE B-Level";
                    }

                }
            }

            // válida, autentica, refrenda, visa, representa, testimonia, ..
            final String eniRolFirma = "firma"; // ???

            String eniSignerName;
            String eniSignerAdministrationId;
            if (isSignatureInServer) {
                eniSignerName = null;
                eniSignerAdministrationId = null;
            } else {

                // Ha de passar el NIF de la Firma !!!!
                if (infoValidacio != null && infoValidacio.getNifFirmant() != null) {
                    eniSignerAdministrationId = infoValidacio.getNifFirmant();
                } else {
                    eniSignerAdministrationId = commonInfo.getAdministrationID();
                }

                eniSignerName = null;
                if (infoValidacio != null) {

                    ValidateSignatureResponse validateSignatureResponse = infoValidacio.getValidateSignatureResponse();
                    if (validateSignatureResponse != null) {

                        SignatureDetailInfo[] sdi = validateSignatureResponse.getSignatureDetailInfo();
                        if (sdi != null && sdi.length != 0) {
                            InformacioCertificat ic = sdi[0].getCertificateInfo();
                            if (ic != null) {
                                eniSignerName = ic.getNomCompletResponsable();
                            }
                        }
                    }

                    X509Certificate cert = infoValidacio.getCertificateLastSign();
                    if (cert != null) {
                        eniSignerName = CertificateUtils.getSubjectCorrectName(cert);
                    }
                }

                if (eniSignerName == null) {
                    eniSignerName = commonInfo.getUsername();
                }

            }

            // eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el
            // grado de
            // confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma
            // electrónica
            // avanzada, Claves concertadas, Firma electrónica avanzada basada en
            // certificados, CSV,
            // ..
            // TODO XYZ ZZZ Aixó ha de venir del plugin
            String eniSignLevel = null;

            CustodyInfo custody = null;
            /*
            {
                PassarelaCustodyInfo pci = psr.getCustodyInfo();
                if (pci != null) {
                    custody = new CustodyInfo(pci.getCustodyFileID(), pci.getCustodyFileCSV(),
                            pci.getCustodyFileCSVValidationWeb(), pci.getCustodyFileURL(),
                            pci.getCustodyFileCSVGenerationDefinition(), pci.getCustodyFileOriginalFileDirectURL(),
                            pci.getCustodyFilePrintableFileDirectUrl(), pci.getCustodyFileEniFileDirectUrl());
                }
            }
            */

            ValidationInfo validation = null;
            {
                if (infoValidacio != null) {
                    validation = new ValidationInfo(infoValidacio.getCheckAdministrationIDOfSigner(),
                            infoValidacio.getCheckDocumentModifications(), infoValidacio.getCheckValidationSignature(),
                            null);
                } else {

                    PassarelaValidationInfo pvi = psr.getValidationInfo();
                    if (pvi != null) {
                        validation = new ValidationInfo(pvi.getCheckAdministrationIDOfSigner(),
                                pvi.getCheckDocumentModifications(), pvi.getCheckValidationSignature(),
                                pvi.getNoCheckValidationReason());
                    }
                }

            }

            final List<KeyValue> additionInformation = null;
            final Timestamp signDate = new Timestamp(System.currentTimeMillis());

            // XYZ ZZZ ZZZ Que passarela retorni dades de la validació de la firma
            // i que aqui es puguin usar !!!!
            String serialNumberCert = null;
            String issuerCert = null;
            String subjectCert = null;
            if (infoValidacio != null) {
                BigInteger ns = infoValidacio.getNumeroSerieCertificat();
                serialNumberCert = (ns != null) ? ns.toString() : null;
                issuerCert = infoValidacio.getEmissorCertificat();
                subjectCert = infoValidacio.getSubjectCertificat();

            }

            SignPlugin signPlugin;
            if (signaturePluginId != null) {
                signPlugin = getSignaturePluginInformation(commonInfo.getLanguageUI(),
                        signaturePluginId);
            } else {
                signPlugin = null;
            }

            SignerInfo signerInfo;
            signerInfo = new SignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel, signDate,
                    serialNumberCert, issuerCert, subjectCert, signPlugin, additionInformation);
            
            
            final boolean timeStampIncluded =  (timeStampIncluded2 == null)? false : timeStampIncluded2.booleanValue();

            sfi = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
                    timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, signerInfo, custody, validation);

        }

        return new SignatureResponse(psr.getSignID(), status, file, sfi);

    }
    
    

    protected Document convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws Exception {

        if (fb == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = fb.getData().getInputStream();
            byte[] data = IOUtils.toByteArray(is);
            return new Document(fb.getNom(), fb.getMime(), data);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
        }
    }
    
    

    /**
     * Firma en Servidor
     */
    protected PassarelaSignaturesSet convertRestBean2PassarelaBeanServer(String transactionID,
            SignDocumentRequest simpleSignature, String usuariAplicacio,  PerfilDeFirma perfilFirma,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException, I18NValidationException {

        

        SignDocumentsRequest simpleSignaturesSet;
        simpleSignaturesSet = new SignDocumentsRequest(simpleSignature.getCommonInfo(),
                new es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature[] {
                        simpleSignature.getFileInfoSignature() });

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet,
                 usuariAplicacio, perfilFirma, configBySignID);

        return pss;
    }
    
    
    private PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
            SignDocumentsRequest simpleSignaturesSet, String usuariAplicacio,
            PerfilDeFirma perfilFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
            throws I18NException {

        String languageUI = "ca";

        final String usuariAplicacioID = usuariAplicacio;

   
        try {

            // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
            if (simpleSignaturesSet == null) {
                // Traduir
                throw new I18NException("genapp.comodi", "FirmaSimpleSignDocumentsRequest val null");
            }

            CommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();
            if (commonInfo == null) {
                throw new I18NException("genapp.comodi", "L'atribut commonInfo val null");
            }

            languageUI = commonInfo.getLanguageUI();
            log.info(" XYZ ZZZ LanguageUI() => " + languageUI);
            if (languageUI == null || languageUI.trim().length() == 0) {
                throw new I18NException("genapp.comodi",
                        "El camp languageUI de l'atribut commonInfo val null o està buit");
            }

            // TODO XYZ FALTA CHECK
            if (simpleSignaturesSet.getFileInfoSignatureArray() != null) {
                es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature[] simpleFileInfoSignatureArray;
                simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();

                if (simpleFileInfoSignatureArray == null || simpleFileInfoSignatureArray.length == 0) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No ha enviat fitxers a firmar.");
                }

                

                String signerEmail = commonInfo.getSignerEmail();

                // DADES ESPECIFIQUES DE CADA FIRMA

                PassarelaFileInfoSignature[] fileInfoSignatureArray;
                fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

                //String lastCertificate = null;
                PassarelaPolicyInfoSignature lastPolicyInfoSignature = null;

                for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

                    es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature sfis = simpleFileInfoSignatureArray[i];

                    String signID = sfis.getSignID();
                    log.info("------------SignID => " + signID);
                    log.info("------------InfoSignatureArray => " + simpleFileInfoSignatureArray.length);
                    if (sfis.getFileToSign() != null) {
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign() => "
                                + sfis.getFileToSign());
                        if (sfis.getFileToSign().getName() != null)
                            log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getNom() => "
                                    + sfis.getFileToSign().getName());

                    }
                    if (sfis.getFileToSign() == null) {
                        log.info("ERROR => NO S'HA TROBAT FILE TO SIGN");
                        log.info("FileToSign =>");
                        log.info(simpleFileInfoSignatureArray[0].getFileToSign().getName());
                    }

                    FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), transactionID, signID);
                    if (fileToSign != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign => " + fileToSign);
                    if (fileToSign.getNom() != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getNom() => "
                                + fileToSign.getNom());

                    // XYZ ZZZ FALTA ENCARA NO SUPORTAT
                    FitxerBean prevSign = null;
                    if (sfis.getPreviusSignatureDetachedFile() != null) {
                        prevSign = convertFirmaSimpleFileToFitxerBean(
                                sfis.getPreviusSignatureDetachedFile(), transactionID, signID);
                    }

                    String name = sfis.getName();
                    String reason = sfis.getReason();
                    String location = sfis.getLocation();

                    int signNumber = sfis.getSignNumber();
                    String languageSign = sfis.getLanguageSign();

                    final String expedientCodi = sfis.getExpedientCodi();
                    final String expedientNom = sfis.getExpedientNom();
                    final String expedientUrl = sfis.getExpedientUrl();
                    final String procedimentCodi = sfis.getProcedimentCodi();
                    final String procedimentNom = sfis.getProcedimentNom();

                    final List<PassarelaKeyValue> additionalInformation;
                    {
                        List<KeyValue> additionalInfoList = sfis.getAdditionalInformation();
                        if (additionalInfoList == null || additionalInfoList.size() == 0) {
                            additionalInformation = null;
                        } else {
                            additionalInformation = new ArrayList<PassarelaKeyValue>();
                            for (KeyValue firmaSimpleKeyValue : additionalInfoList) {
                                additionalInformation.add(new PassarelaKeyValue(firmaSimpleKeyValue.getKey(),
                                        firmaSimpleKeyValue.getValue()));
                            }
                        }
                    }

                    // ============ FIRMA
                    UsuariAplicacioConfiguracioJPA config = configBySignID.get(sfis.getSignID());

                    // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
                    final int signOperation = config.getTipusOperacioFirma();

                    // TIPUS DE FIRMA
                    final String signType = SignatureUtils
                            .convertPortafibSignTypeToApiSignType(config.getTipusFirma());

                    // Algorisme de Firma
                    String signAlgorithm = getAlgorismeDeFirmaOfConfig(config);

                    // Mode de Firma
                    final int signMode = config.getModeDeFirma();
                    /*
                     * if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) { // SI és una
                     * pADES llavors val implicit signMode = FileInfoSignature.SIGN_MODE_IMPLICIT; }
                     * else { signMode =
                     * SignatureUtils.convertPortafibSignMode2ApiSignMode(config.isModeDeFirma(),
                     * config.getTipusFirmaID()); }
                     */

                    // TAULA DE FIRMES
                    final int signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT; 
                            

                    // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. #
                    // PENDENT: Configuració etiquetes de la Taula de Firmes #176
                    // Camp config.getPropietatsTaulaFirmes()
                    //PassarelaSignaturesTableHeader signaturesTableHeader = null;

                    // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
                    // DADES DE l'ENTITAT
                    final boolean useTimeStamp = getUseTimestampOfConfig(usuariAplicacioID, config);

                    // Això ja es farà a PassarelaDeFirmaWebEJB
                    //final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo = null;

                    fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign, signID, name,
                            reason, location, signerEmail, signNumber, languageSign, signOperation, signType,
                            signAlgorithm, signMode, signaturesTableLocation, /* signaturesTableHeader,
                            secureVerificationCodeStampInfo, */ useTimeStamp, expedientCodi, expedientNom, expedientUrl,
                            procedimentCodi, procedimentNom, additionalInformation);

                    // LES DADES COMUNS DE TOTES LES CONFIGURACIONS HAN DE SER IGUALS
                    if (i == 0) {
                        //lastCertificate = config.getFiltreCertificats();
                        lastPolicyInfoSignature = getPoliticaFirmaOfConfig(usuariAplicacioID, config);
                    } else {
                        // Comparar lastCertificate amb actual a veure si són iguals
                        /*
                        if (!compare(lastCertificate, config.getFiltreCertificats())) {
                            // XYZ ZZZ TRA
                            throw new I18NException("genapp.comodi",
                                    "El camp Filtre de Certificats" + " de les diferents configuracions del Perfil "
                                            + perfilFirma.getCodi()
                                            + " haurien de tenir el mateix valor i no el tenen.");

                        }
                        */

                        // Comparar lastPolicyInfoSignature amb actual a veure si són iguals
                        if (!compare(lastPolicyInfoSignature,
                                getPoliticaFirmaOfConfig(usuariAplicacioID, config))) {
                            // XYZ ZZZ TRA
                            throw new I18NException("genapp.comodi",
                                    "Els camps de Politica de Firma " + " de les diferents configuracions del Perfil "
                                            + perfilFirma.getCodi()
                                            + " haurien de tenir el mateix valor i no el tenen.");
                        }

                    }

                } // FINAL FOR DE TOTS

                // DADES COMUNS

                // final String entitatID = entitatJPA.getEntitatID();

                // Donam de temps 5 minuts més un minut per cada signatura
                // Proporcional al numero de firmes !!!!
                Calendar expiryDate = Calendar.getInstance();
                expiryDate.add(Calendar.MINUTE, 5 + simpleFileInfoSignatureArray.length);

                // ========== FILTRE DE CERTIFICATS
                // Cercar-ho a info de l'usuari-app.Si val null o buit cercar-ho de les
                // DADES DE l'ENTITAT
                String filtreCertificats = null;
                /*
                String filtreCertificats = lastCertificate;
                if (filtreCertificats == null || filtreCertificats.trim().length() == 0) {
                    filtreCertificats = entitatJPA.getFiltreCertificats();
                }
                */

                // ========== POLITICA DE FIRMA
                // Cercar l'ús de la politica de firma i actuar al respecte
                final PassarelaPolicyInfoSignature policyInfoSignature = lastPolicyInfoSignature;

                final String username = commonInfo.getUsername();
                final String administrationID = commonInfo.getAdministrationID();
                final String organizationID = commonInfo.getOrganizationID();

                PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(languageUI,
                        filtreCertificats, username, administrationID, organizationID, null, policyInfoSignature);

                // OBJECTE FINAL

                return new PassarelaSignaturesSet(transactionID, expiryDate.getTime(), commonInfoSignature,
                        fileInfoSignatureArray);
            } else {
                return null;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // XYZ ZZZ TRA
            throw new I18NException(e, "genapp.comodi", new I18NArgumentString(e.getMessage()));
        }

    }

    
    

    protected static class SignDocumentsRequest {

        CommonInfo commonInfo;

        es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature[] fileInfoSignatureArray;

        /**
         * 
         */
        public SignDocumentsRequest() {
            super();
        }

        public SignDocumentsRequest(CommonInfo commonInfo,
                es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature[] fileInfoSignatureArray) {
            super();
            this.commonInfo = commonInfo;
            this.fileInfoSignatureArray = fileInfoSignatureArray;
        }

        public es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature[] getFileInfoSignatureArray() {
            return fileInfoSignatureArray;
        }

        public void setFileInfoSignatureArray(
                es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature[] fileInfoSignatureArray) {
            this.fileInfoSignatureArray = fileInfoSignatureArray;
        }

        public CommonInfo getCommonInfo() {
            return commonInfo;
        }

        public void setCommonInfo(CommonInfo commonInfo) {
            this.commonInfo = commonInfo;
        }

    }
    
    

    public static FitxerBean convertFirmaSimpleFileToFitxerBean(Document asf, String transactionID,
            String signID) throws Exception {
        FitxerBean fileToSign = new FitxerBean();
        fileToSign.setDescripcio(null);
        if (asf.getMime() != null) {
            final String mime = asf.getMime();
            fileToSign.setMime(mime);
        }

        fileToSign.setNom(asf.getName());

        byte[] data = asf.getData();
        fileToSign.setTamany(data.length);

        File folderTransaction = getTransactionFolder(transactionID);
        folderTransaction.mkdirs();

        File file = new File(folderTransaction, "IN_" + signID);

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();

        FileDataSource fds = new FileDataSource(file);

        fileToSign.setData(new DataHandler(fds));
        return fileToSign;
    }
    
    
    

    protected String getAlgorismeDeFirmaOfConfig(final UsuariAplicacioConfiguracio config)
            throws I18NException {
        int signAlgorithmID = getAlgorismeDeFirmaIDOfConfig(config);

        // ALGORISME DE FIRMA
        String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
        log.info(" XYZ ZZZ REST: getAlgorismeDeFirmaOfConfig [SignAlgorithm] = " + signAlgorithm);
        return signAlgorithm;
    }


    protected int getAlgorismeDeFirmaIDOfConfig(final UsuariAplicacioConfiguracio config) {
        int signAlgorithmID = config.getAlgorismeDeFirma();        
        return signAlgorithmID;
    }

    

    protected PassarelaPolicyInfoSignature getPoliticaFirmaOfConfig(final String usuariAplicacioID,
            final UsuariAplicacioConfiguracio config) throws I18NException {

        PolicyInfoSignature politica = SignatureUtils.getPolicyInfoSignature(config);

        final PassarelaPolicyInfoSignature policyInfoSignature;
        if (politica == null) {
            policyInfoSignature = null;
        } else {
            policyInfoSignature = new PassarelaPolicyInfoSignature(politica.getPolicyIdentifier(),
                    politica.getPolicyIdentifierHash(), politica.getPolicyIdentifierHashAlgorithm(),
                    politica.getPolicyUrlDocument());
        }
        return policyInfoSignature;
    }


    public static boolean compare(PassarelaPolicyInfoSignature pp1, PassarelaPolicyInfoSignature pp2) {
        if (pp1 == null) {
            return pp2 == null;
        } else {
            if (pp2 == null) {
                return false;
            }

            if (compare(pp1.getPolicyIdentifier(), pp2.getPolicyIdentifier())
                    && compare(pp1.getPolicyIdentifierHash(), pp2.getPolicyIdentifierHash())
                    && compare(pp1.getPolicyIdentifierHashAlgorithm(), pp2.getPolicyIdentifierHashAlgorithm())
                    && compare(pp1.getPolicyUrlDocument(), pp2.getPolicyUrlDocument())) {
                return true;
            } else {
                return false;
            }
        }
    }

    
    public static boolean compare(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }
    
    

    /**
     * 
     * @param usuariAplicacioID
     * @param config
     * @param entitatJPA
     * @return
     * @throws I18NException
     */
    protected boolean getUseTimestampOfConfig(final String usuariAplicacioID, final UsuariAplicacioConfiguracio config) throws I18NException {
        final boolean useTimeStamp;
        {
            int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

          

            switch (politicaSegellatDeTemps) {
                case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
                    useTimeStamp = false;
                break;

                case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
                    useTimeStamp = true;
                break;

                case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
                    useTimeStamp = true;
                break;
                case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
                    useTimeStamp = false;
                break;

                default:
                    // XYZ ZZZ Traduir
                    throw new I18NException("genapp.comodi", "Politica de segellat de temps desconeguda ("
                            + politicaSegellatDeTemps + ") en usuari aplicació " + usuariAplicacioID);
            }
        }
        return useTimeStamp;
    }

    
    

    protected SignedFileInfo constructFirmaSimpleSignedFileInfo(UsuariAplicacioConfiguracio config,
            PassarelaFileInfoSignature fileInfo,
            es.caib.utilitatsfirma.api.interna.secure.signaturecommons.FileInfoSignature firmaRequest,
            String eniPerfilFirma, Document signedFile,  boolean policyIncluded,
            ValidacioCompletaResponse vcr, final String languageUI,
            Long signaturePluginId) throws I18NException, Exception {

        log.info("XYZ ZZZ validateSignature::Entra a Validate Signature ...");

        String signType = fileInfo.getSignType();

        log.info("XYZ ZZZ validateSignature:: signType => " + signType);

        log.info("XYZ ZZZ validateSignature:: fileInfo.getSignMode() => " + fileInfo.getSignMode());

        @SuppressWarnings("unused")
        byte[] documentDetached = null;
        if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_DETACHED) {

            if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
                    || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
                documentDetached = firmaRequest.getFileToSign().getData();
            }

        }

        final int signOperation = fileInfo.getSignOperation();
        final String signAlgorithm = fileInfo.getSignAlgorithm();
        final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
        final boolean timeStampIncluded = fileInfo.isUseTimeStamp2();

        SignedFileInfo signatureFileInfo;

        // Internament ja es verifica si s'ha de passar
        ValidateSignatureResponse vsr = vcr.getValidateSignatureResponse();

        if (vsr == null || vsr.getValidationStatus() == null) {
            // No s'ha fet validacio
            signatureFileInfo = new SignedFileInfo();
            signatureFileInfo.setSignOperation(signOperation);
            signatureFileInfo.setSignType(signType);

            signatureFileInfo.setSignMode(fileInfo.getSignMode());
            signatureFileInfo.setSignAlgorithm(signAlgorithm);
            signatureFileInfo.setValidationInfo(new ValidationInfo());
            signatureFileInfo.setEniPerfilFirma(eniPerfilFirma);
            signatureFileInfo.setTimeStampIncluded(timeStampIncluded);
            signatureFileInfo.setPolicyIncluded(policyIncluded);

            // SI es PADES llavors el signMode es attached
            if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
                signatureFileInfo.setSignMode(Constants.SIGN_MODE_ATTACHED_ENVELOPED);
            }

            signatureFileInfo.setEniTipoFirma(
                    SignatureUtils.getEniTipoFirma(signatureFileInfo.getSignType(), signatureFileInfo.getSignMode()));

        } else {

            if (vsr.getSignType() != null) {
                signType = vsr.getSignType();
            }

            int signFormat = vsr.getSignMode();

            int signMode = signFormat;
            /*
             * if (signFormat == null) {
             * log.warn("Ens ha arribat un signFormat = null: es retorna signMode null");
             * signMode = null; } else if
             * (ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPED_ATTACHED.equals(
             * signFormat) ||
             * ValidateSignatureResponse.SIGNFORMAT_IMPLICIT_ENVELOPING_ATTACHED.equals(
             * signFormat)) { signMode =
             * FirmaSimpleSignedFileInfo.SIGN_MODE_IMPLICIT_ATTACHED; } else if
             * (ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_DETACHED.equals(signFormat) ||
             * ValidateSignatureResponse.SIGNFORMAT_EXPLICIT_EXTERNALLY_DETACHED.equals(
             * signFormat)) { signMode =
             * FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED; } else {
             * 
             * log.error("Ens ha arribat un signFormat = " + signFormat +
             * ". S'hauria de comunicar aquest fet als desenvolupadors !!!!!");
             * 
             * signMode = null; }
             */
            // XYZ ZZZ
            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            if (vsr.getSignProfile() != null) {
                eniPerfilFirma = vsr.getSignProfile();
            }

            ValidationInfo validationInfo = new ValidationInfo();
            validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());
            validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
            validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());

            CustodyInfo custodyInfo = null;

            SignatureDetailInfo[] detailInfoArray = vsr.getSignatureDetailInfo();

            final SignerInfo signerInfo;

            if (detailInfoArray == null || detailInfoArray.length == 0) {
                signerInfo = null;
            } else {

                InformacioCertificat info = detailInfoArray[0].getCertificateInfo();

                if (info == null) {
                    signerInfo = null;
                } else {

                    // XYZ ZZZ ZZZ
                    String eniRolFirma = null;
                    String eniSignLevel = null;
                    String serialNumberCert = null;

                    String eniSignerName = info.getNomCompletResponsable();
                    String eniSignerAdministrationId = info.getNifResponsable();
                    Timestamp signDate = new Timestamp(System.currentTimeMillis());

                    String issuerCert = info.getEmissorID();
                    String subjectCert = info.getSubject();

                    List<KeyValue> additionalInformation = null;

                    SignPlugin signPlugin;
                    if (signaturePluginId != null) {
                        signPlugin = getSignaturePluginInformation(languageUI, signaturePluginId);
                    } else {
                        signPlugin = null;
                    }

                    signerInfo = new SignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel,
                            signDate, serialNumberCert, issuerCert, subjectCert, signPlugin, additionalInformation);
                }
            }

            signatureFileInfo = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode,
                    signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
                    signerInfo, custodyInfo, validationInfo);

        }
        return signatureFileInfo;
    }

    
    

}
