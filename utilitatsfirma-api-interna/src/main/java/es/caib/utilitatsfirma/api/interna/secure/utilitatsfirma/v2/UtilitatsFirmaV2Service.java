package es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.ISignaturePlugin;
import org.fundaciobit.pluginsib.signature.api.PolicyInfoSignature;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureFormForUpgrade;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.validatecertificate.InformacioCertificat;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo;

import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartFileInfo;
import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartNameAndMime;
import es.caib.utilitatsfirma.api.interna.multipartutils.MultipartUtils;
import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.Document;
import es.caib.utilitatsfirma.api.interna.secure.signaturecommons.v1.KeyValue;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.CommonInfo;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.DocumentaryType;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.DocumentaryTypeConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ProcessStatus;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.Profile;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignAlgorithmConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignModeConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignOperationConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignPlugin;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignProfileConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignTypeConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignaturesTableLocationConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.SignerInfo;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.StatusConstants;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.UpgradedFileInfo;
import es.caib.utilitatsfirma.api.interna.secure.signatureonserver.v1.ValidationInfo;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.CertificateTypeEidasConstants;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.CertificateTypeMineturConstants;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureRequestedInformation;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureValidationService;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.ValidationStatusConstants;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.ejb.IdiomaService;
import es.caib.utilitatsfirma.ejb.PerfilDeFirmaService;
import es.caib.utilitatsfirma.ejb.PerfilsPerUsuariAplicacioService;
import es.caib.utilitatsfirma.ejb.UsuariAplicacioService;
import es.caib.utilitatsfirma.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.utilitatsfirma.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.utilitatsfirma.logic.PluginTipusDocumentalsLogicaLocal;
import es.caib.utilitatsfirma.logic.PluginValidacioFirmesLogicaLocal;
import es.caib.utilitatsfirma.logic.generator.IdGeneratorFactory;
import es.caib.utilitatsfirma.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.utilitatsfirma.logic.passarela.api.NoCompatibleSignaturePluginException;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFullResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaKeyValue;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaNonCryptographicInformation;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureInServerResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureResult;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidationInfo;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaUpgradeResponse;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidacioCompletaResponse;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidateSignatureResponse;
import es.caib.utilitatsfirma.logic.utils.I18NLogicUtils;
import es.caib.utilitatsfirma.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.utilitatsfirma.logic.utils.SignType;
import es.caib.utilitatsfirma.logic.utils.SignatureUtils;
import es.caib.utilitatsfirma.model.bean.FitxerBean;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import es.caib.utilitatsfirma.model.fields.IdiomaFields;
import es.caib.utilitatsfirma.model.fields.PerfilDeFirmaFields;
import es.caib.utilitatsfirma.model.fields.PerfilsPerUsuariAplicacioFields;

import es.caib.utilitatsfirma.persistence.PluginJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Servei de firma en servidor v2. Aquesta versió és una evolució de la v1, amb canvis importants a nivell de disseny 
 * i implementació. La v1 es mantindrà per compatibilitat però no s'hi afegiran noves funcionalitats.
 * 
 * Afegeix eficiència en l'enviament de fitxers
 * 
 * @author anadal (u80067)
 * 19 feb 2026 12:24:24
 */
@Path(UtilitatsFirmaV2Service.PATH)
@OpenAPIDefinition(

        info = @Info(
                title = "Utilitats Firma Swagger v2",
                version = "2.0",
                description = "API Interna d'Utilitats Firma V2 que ofereix serveis de firma en servidor i validació de firmes.",
                contact = @Contact(name = "Suport Utilitats Firma", email = "governdigital.firma@ibdigital.caib.es")),

        tags = @Tag(
                name = UtilitatsFirmaV2Service.TAG_NAME,
                description = "Utilitats Firma Swagger v2. API Interna d'Utilitats Firma V2 que ofereix serveis de firma en servidor i validació de firmes."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = UtilitatsFirmaV2Service.SECURITY_NAME, scheme = "basic")
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
                                        schema = @Schema(implementation = ValidationStatusConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = CertificateTypeEidasConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = CertificateTypeMineturConstants.class)),
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = RestExceptionInfo.class)) }) })
public class UtilitatsFirmaV2Service extends RestUtils {

    protected Logger log = Logger.getLogger(this.getClass());

    protected static Logger logStatic = Logger.getLogger(UtilitatsFirmaV2Service.class);

    public static final String PATH = "/secure/utilitatsfirma/v2";

    public static final String TAG_NAME = "UtilitatsFirma v2";

    public static final String SECURITY_NAME = "BasicAuth";

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

    @EJB(mappedName = PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
    protected PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioLogicaEjb;

    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

    @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaServidorLogicaLocal modulDeFirmaServidorEjb;

    @EJB(mappedName = PerfilDeFirmaService.JNDI_NAME)
    protected PerfilDeFirmaService perfilDeFirmaEjb;

    @EJB(mappedName = PluginTipusDocumentalsLogicaLocal.JNDI_NAME)
    protected PluginTipusDocumentalsLogicaLocal tipusDocumentalLogicaEjb;

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;

    @EJB(mappedName = es.caib.utilitatsfirma.ejb.IdiomaService.JNDI_NAME)
    protected IdiomaService idiomaEjb;

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

        Set<DocumentaryType> documentaryTypes = new TreeSet<DocumentaryType>(new Comparator<DocumentaryType>() {

            @Override
            public int compare(DocumentaryType o1, DocumentaryType o2) {
                return Long.compare(o1.getDocumentType(), o2.getDocumentType());
            }
        });

        //String usuariAplicacio = 
        checkUsuariAplicacio(request);
        languageUI = RestUtils.checkLanguage(languageUI);

        try {

            List<TipusDocumental> tipusDocumental = tipusDocumentalLogicaEjb.getTipusDocumentals(languageUI);

            for (TipusDocumental td : tipusDocumental) {
                DocumentaryType dt = new DocumentaryType();
                dt.setDocumentType(td.getTipusDocumentalID());
                dt.setDocumentTypeBase(td.getParentTipusDocumentalID());
                dt.setName(td.getName());
                documentaryTypes.add(dt);
            }

            return documentaryTypes;

        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            throw new RestException(msg);

        } catch (Throwable th) {
            String msg = "Error desconegut cridant a getDocumentaryTypes: " + th.getMessage();
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
        return "2.0";
    }

    /**
     * Operació de upgrade de firma digital. Permet actualitzar una firma digital existent a un perfil de firma superior, afegint la informació necessària per complir amb els requisits del nou perfil. Aquesta operació és especialment útil per a perfils que requereixen informació addicional com timestamps, certificats d'usuari, etc. El servei rep la firma a actualitzar, el codi del perfil al que s'ha d'actualitzar, i opcionalment un document detached i un certificat de destinació per a cofirmes o contrafirmes. Retorna la firma actualitzada en format multipart.
     * @param request
     * @param input
     * @param profileCode
     * @param signature
     * @param detachedDocument
     * @param targetCertificate
     * @param languageUI
     * @return
     */
    @Path(value = "/upgradeSignature")
    @POST
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Operation(tags = TAG_NAME, operationId = "upgradeSignature", summary = "Operacio de upgrade de firma digital")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = { @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA,
                            schema = @Schema(implementation = UpgradeResponseMultipart.class)) }) })
    public UpgradeResponseMultipart

            upgradeSignature(@Parameter(hidden = true) @Context
    HttpServletRequest request,

                    UpgradeRequestMultipart upgradeRequestMultipart,

                    @Parameter(
                            description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                            in = ParameterIn.QUERY,
                            required = false,
                            examples = { @ExampleObject(name = "Català", value = "ca"),
                                    @ExampleObject(name = "Castellano", value = "es") },
                            schema = @Schema(
                                    defaultValue = "ca",
                                    implementation = String.class)) @QueryParam("languageUI")
                    String languageUI) {

        languageUI = RestUtils.checkLanguage(languageUI);

        File signature = null;

        File detachedDocument = null;

        File targetCertificate = null;
        try {

            String usuariAplicacioID = checkUsuariAplicacio(request);
            /*
            final String className = this.getClass().getSimpleName();
            FormFileInfo signatureDocumentInfo = FormMethodUtils.getFormFileInfo(input, className, "upgradeSignature",
                    "signature", false);
            signature = signatureDocumentInfo.getFile();
            
            FormFileInfo detachedDocumentInfo = FormMethodUtils.getFormFileInfo(input, className, "upgradeSignature",
                    "detachedDocument", true);
            detachedDocument = detachedDocumentInfo != null ? detachedDocumentInfo.getFile() : null;
            
            FormFileInfo targetCertificateInfo = FormMethodUtils.getFormFileInfo(input, className, "upgradeSignature",
                    "targetCertificate", true);
            targetCertificate = targetCertificateInfo != null ? targetCertificateInfo.getFile() : null;
            
            profileCode = FormMethodUtils.getJsonMultipartObj(input, String.class, "profileCode");
            
            */

            String profileCode = upgradeRequestMultipart.getProfileCode();

            signature = upgradeRequestMultipart.getSignature();

            detachedDocument = upgradeRequestMultipart.getDetachedDocument();

            targetCertificate = upgradeRequestMultipart.getTargetCertificate();

            MultipartFileInfo signatureFileInfo = new MultipartFileInfo(signature,
                    upgradeRequestMultipart.getSignaturePartInfo());

            MultipartFileInfo detachedDocumentFileInfo = new MultipartFileInfo(detachedDocument,
                    upgradeRequestMultipart.getDetachedDocumentPartInfo());

            MultipartFileInfo targetCertificateFileInfo = new MultipartFileInfo(targetCertificate,
                    upgradeRequestMultipart.getTargetCertificatePartInfo());

            log.info(" XYZ ZZZ eNTRA A upgradeSignature => upgrade: profileCode => " + profileCode);

            if (signature == null) {
                // XYZ ZZZ TRA
                String errorMsg = "L'objecte signature val null.";
                throw new RestException(errorMsg, "signature");
            }

            if (profileCode == null || profileCode.trim().length() == 0) {
                // XYZ ZZZ TRA
                String errorMsg = "L'objecte profileCode val null.";
                throw new RestException(errorMsg, "profileCode");
            }

            // XYZ ZZZ Falta checks sobre fsur

            final PerfilDeFirma perfilDeFirma;
            String codi;
            {
                CommonInfo commonInfo = new CommonInfo();
                commonInfo.setSignProfile(profileCode);
                perfilDeFirma = getPerfilDeFirma(commonInfo, usuariAplicacioID);
                codi = perfilDeFirma.getCodi();
            }

            UsuariAplicacioConfiguracio config;

            config = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioUsuariAplicacioPerUpgrade(
                    usuariAplicacioID, perfilDeFirma, getFirmaSimpleUpgradeRequestApisib(signatureFileInfo,
                            detachedDocumentFileInfo, targetCertificateFileInfo, codi, languageUI));

            final boolean isDebug = log.isDebugEnabled();
            if (isDebug) {
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

            if (isDebug) {
                log.info("Fent UPGRADE a " + singTypeForm);
            }

            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(usuariAplicacioID);

            // TODO optimitzar per a que retorni el fitxer en un FILE i no en un byte[] per a evitar problemes de memòria amb fitxers grans. Hauria de ser un Stream.
            PassarelaUpgradeResponse upgradeResponsePassarela;
            upgradeResponsePassarela = passarelaDeFirmaEnServidorEjb.upgradeSignature(
                    getFirmaSimpleFileV2(signatureFileInfo), getFirmaSimpleFileV2(detachedDocumentFileInfo),
                    getFirmaSimpleFileV2(targetCertificateFileInfo), singTypeForm, usuariAplicacio,
                    Constants.ESTADISTICA_ENTORN_API_UTILITATS_FIRMA_V2, perfilDeFirma, config, languageUI);

            // VALIDATE
            final String mime;
            String signatureType = upgradeTypesToSimpleTypes.get(singTypeForm);
            if (FileInfoSignature.SIGN_TYPE_XADES.equals(signatureType)) {
                mime = "application/xml";
            } else {
                mime = null;
            }

            String fileName = signatureFileInfo.getFileName();
            // De fileName, abans del punt de l'extensio afegir "_upgraded"
            if (fileName != null) {
                int dotIndex = fileName.lastIndexOf(".");
                if (dotIndex > 0) {
                    fileName = fileName.substring(0, dotIndex) + "_upgraded" + fileName.substring(dotIndex);
                } else {
                    fileName = fileName + "_upgraded";
                }
            } else {
                fileName = "upgraded_signature";
            }

            UpgradedFileInfo upgradedFileInfo = constructFirmaSimpleUpgradedFileInfo(upgradeResponsePassarela,
                    signatureType, singTypeForm);

            MultipartNameAndMime upgradedFilePartInfo = new MultipartNameAndMime(fileName, mime);

            if (isDebug) {
                log.info("Surt de upgradeSignature => FINAL OK");
            }

            // TODO Convertir a FIle, mirar si es pot fer desde  upgradeResponsePassarela
            File signatureUpgradedFile = upgradeResponsePassarela.getUpgradedSignature();

            UpgradeResponseMultipart responseMultipart = new UpgradeResponseMultipart();

            responseMultipart.setUpgradedFileInfo(upgradedFileInfo);
            responseMultipart.setUpgradedFile(signatureUpgradedFile);
            responseMultipart.setUpgradeFilePartInfo(upgradedFilePartInfo);

            return responseMultipart;

        } catch (NoCompatibleSignaturePluginException nape) {

            final boolean isUpgrade = true;

            String errorMsg = getNoAvailablePluginErrorMessage(languageUI, isUpgrade, nape);
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

        } finally {
            // Esborrem els fitxers temporals creats
            MultipartUtils.deleteTempFile(signature);
            MultipartUtils.deleteTempFile(detachedDocument);
            MultipartUtils.deleteTempFile(targetCertificate);
        }

    }

    protected PerfilDeFirma getPerfilDeFirma(CommonInfo commonInfo, String username) throws I18NException {

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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.MULTIPART_FORM_DATA })
    //@Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = TAG_NAME,
            operationId = "signdocument",
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA,
                            schema = @Schema(implementation = SignedDocumentResponseMultipart.class))) })
    public SignedDocumentResponseMultipart signDocument(@Parameter(hidden = true) @Context
    HttpServletRequest request,

            /*
                    @Parameter(
                            description = "Dades de la firma i informació associada",
                            required = true,
                            schema = @Schema(implementation = SignDocumentRequestV2.class)) @FormParam("signDocumentRequest")
                    SignDocumentRequestV2 simpleSignature,
            
                    @Parameter(description = "Document a signar", required = true) @FormParam(value = "fileToSign")
                    File fileToSign,
            
                    @Parameter(
                            description = "Document detached. Només s'usa per les validacions",
                            required = false) @FormParam("previusSignatureDetachedFile")
                    File previusSignatureDetachedFile,
            
                    @Parameter(hidden = true)
                    MultipartFormDataInput input
                    
                    */

            SignDocumentRequestMultipart signDocumentRequestMultipart

    ) {

        String transactionID = null;
        String languageUI = "ca";

        File fileToSign = null;

        // TODO Check

        File previousSignatureDetachedFile = null;

        try {

            // TODO Check
            SignDocumentRequest simpleSignature = signDocumentRequestMultipart.getSignDocumentRequest();

            // TODO Check
            fileToSign = signDocumentRequestMultipart.getFileToSign();

            // TODO Check

            previousSignatureDetachedFile = signDocumentRequestMultipart.getPreviousSignatureDetachedFile();

            /*
            
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            
            {
                List<InputPart> signDocRequestParts = uploadForm.get("signDocumentRequest");
            
                InputPart signDocRequestPart = signDocRequestParts.get(0);
            
                log.info("\n XYZ ZZZ eNTRA A signDocuments => signDocRequestPart: " + signDocRequestPart + "\n");
            
                String sdrStr = signDocRequestPart.getBodyAsString();
            
                log.info("\n XYZ ZZZ eNTRA A signDocuments => signDocRequestPart as String: " + sdrStr + "\n");
            
                simpleSignature = SignDocumentRequestV2.valueOf(sdrStr);
            }
            
            //log.info("\n XYZ ZZZ eNTRA A signDocuments => simpleSignature: " + simpleSignature + "\n");
            
            
            // Obtener fileToSign
            String fileToSignName = null;
            {
            
                List<InputPart> fileParts = uploadForm.get("fileToSign");
            
                if (fileParts == null || fileParts.size() == 0) {
                    // XYZ ZZZ TRA
                    String errMsg = "No s'ha trobat cap part amb el name fileToSign en el multipart/form-data.";
                    throw new RestException(Status.BAD_REQUEST, errMsg, "fileToSign");
                }
            
                InputPart filePart = fileParts.get(0);
                InputStream fileToSignInputStream = filePart.getBody(InputStream.class, null);
            
                fileToSign = File.createTempFile("SignatureOnServerV2_", "_fileToSign");
                Files.copy(fileToSignInputStream, fileToSign.toPath(), StandardCopyOption.REPLACE_EXISTING);
                fileToSignName = FormMethodUtils.getFileName(filePart.getHeaders());
            
                //System.out.println("\n XYZ ZZZ eNTRA A signDocuments => fileToSignName: " + fileToSignName + "\n");
            }
            
            // Obtener previusSignatureDetachedFile (opcional)
            String previusSignatureDetachedFileName = null;
            {
                InputStream previusSignatureInputStream = null;
                if (uploadForm.containsKey("previusSignatureDetachedFile")) {
                    List<InputPart> prevParts = uploadForm.get("previusSignatureDetachedFile");
                    InputPart prevPart = prevParts.get(0);
                    previusSignatureInputStream = prevPart.getBody(InputStream.class, null);
            
                    previusSignatureDetachedFile = File.createTempFile("SignatureOnServerV2_",
                            "_prevSignatureDetached");
            
                    Files.copy(previusSignatureInputStream, previusSignatureDetachedFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
            
                    previusSignatureDetachedFileName = FormMethodUtils.getFileName(prevPart.getHeaders());
                }
            }
            */

            // ------------------------------------

            if (simpleSignature == null) {
                // XYZ ZZZ TRA
                String errMsg = "L´objecte SignDocumentRequest passat per paràmetre val null";
                throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest");
            }

            if (simpleSignature.getCommonInfo() == null) {
                // XYZ ZZZ TRA
                String errMsg = "El camp commonInfo de tipus CommonInfo definit dins de SignDocumentRequest val null";
                throw new RestException(Status.BAD_REQUEST, errMsg, "FirmaSimpleSignDocumentRequest.commonInfo");
            }

            if (fileToSign == null || !fileToSign.exists()) {
                // XYZ ZZZ TRA
                String errMsg = "El camp fileToSign és obligatori i no s'ha trobat en el multipart/form-data o val null";
                throw new RestException(Status.BAD_REQUEST, errMsg, "fileToSign");
            }

            if (signDocumentRequestMultipart.getFileToSignPartInfo() == null) {
                // XYZ ZZZ TRA
                String errMsg = "El camp fileToSignPartInfo de tipus SignDocumentRequestMultipart val null però el fitxer associat fileToSign no és null. Aquest camp és obligatori quan el fitxer fileToSign no és null.";
                throw new RestException(Status.BAD_REQUEST, errMsg, "SignDocumentRequestMultipart.fileToSignPartInfo");
            }

            languageUI = simpleSignature.getCommonInfo().getLanguageUI();
            if (languageUI == null || languageUI.trim().length() == 0) {
                // XYZ ZZZ TRA
                String errMsg = "El camp languageUI definit dins de FirmaSimpleSignDocumentRequest.FirmaSimpleCommonInfo està buit o val null";
                throw new RestException(Status.BAD_REQUEST, errMsg,
                        "FirmaSimpleSignDocumentRequest.commonInfo.languageUI");
            }

            languageUI = RestUtils.checkLanguage(languageUI);

            /*
            log.info("simpleSignaturesSet.getCommonInfo().getSignProfile() ==> "
                    + simpleSignature.getCommonInfo().getSignProfile());
            log.info("simpleSignaturesSet.getCommonInfo().getLanguageUI() ==> " + languageUI);
            */

            Long signaturePluginId = null;
            transactionID = internalGetTransacction();
            String username = request.getUserPrincipal().getName();

            // Si codi de Perfil val null, llavors en cerca un.
            PerfilDeFirma perfil = getPerfilDeFirma(simpleSignature.getCommonInfo(), username);

            PerfilConfiguracionsDeFirma pcf;

            MultipartFileInfo fileToSigFileInfo = new MultipartFileInfo(fileToSign,
                    signDocumentRequestMultipart.getFileToSignPartInfo());
            MultipartFileInfo previousSignatureDetachedFileInfo = new MultipartFileInfo(previousSignatureDetachedFile,
                    signDocumentRequestMultipart.getPreviousSignatureDetachedFilePartInfo());

            org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest simpleSignatureRequestApisib;
            simpleSignatureRequestApisib = getSimpleSignatureRequestApisibV2(simpleSignature, fileToSigFileInfo,
                    previousSignatureDetachedFileInfo);

            pcf = configuracioUsuariAplicacioLogicaLocalEjb.getConfiguracioFirmaPerApiFirmaSimpleEnServidor(username,
                    perfil.getCodi(), simpleSignatureRequestApisib);

            // ================== CODI COMU ==============

            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(username);

            PassarelaSignaturesSet pss;
            {
                // TODO Com optenir el nom del fitxer
                // simpleSignature.getFileToSignName()
                // simpleSignature.getPreviousSignatureDetachedFileName()
                pss = convertRestBean2PassarelaBeanServer(transactionID, simpleSignature, fileToSign,
                        fileToSign.getName(), previousSignatureDetachedFile,
                        previousSignatureDetachedFile == null ? null : previousSignatureDetachedFile.getName(),
                        username, pcf.perfilDeFirma, pcf.configBySignID);
            }

            log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");
            PassarelaSignatureInServerResults fullResults;

            fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss, usuariAplicacio,
                    Constants.ESTADISTICA_ENTORN_API_UTILITATS_FIRMA_V2, pcf.perfilDeFirma, pcf.configBySignID);

            signaturePluginId = fullResults.getPluginFirmaEnServidorId();

            // Ho podria collir de Signer però es més senzill consultar-ho de nou
            SignPlugin signPlugin;
            if (signaturePluginId != null) {
                signPlugin = getSignaturePluginInformation(languageUI, signaturePluginId);
            } else {
                signPlugin = null;
            }

            //result.setSignPlugin(signPlugin);

            ProcessStatus statusGlobal;

            List<SignedDocumentResponseMultipart> results;
            {
                PassarelaFullResults pfullResults = fullResults.getPassarelaFullResults();

                PassarelaSignatureStatus passarelaSS = pfullResults.getSignaturesSetStatus();

                statusGlobal = new ProcessStatus(passarelaSS.getStatus(), passarelaSS.getErrorMessage(),
                        passarelaSS.getErrorStackTrace());

                if (passarelaSS.getStatus() == StatusSignature.STATUS_FINAL_OK) {

                    List<PassarelaSignatureResult> passarelaSR = pfullResults.getSignResults();

                    results = new ArrayList<SignedDocumentResponseMultipart>();

                    Map<String, PassarelaFileInfoSignature> infoBySignID = new HashMap<String, PassarelaFileInfoSignature>();
                    for (PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {

                        infoBySignID.put(pfis.getSignID(), pfis);

                    }

                    PassarelaValidacioCompletaResponse validacioInfo;
                    for (PassarelaSignatureResult psr : passarelaSR) {

                        validacioInfo = fullResults.getValidacioResponseBySignID().get(psr.getSignID());

                        results.add(convertPassarelaSignatureResult2FirmaSimpleSignatureResultV2(psr,
                                pss.getCommonInfoSignature(), infoBySignID.get(psr.getSignID()), validacioInfo,
                                signaturePluginId, signPlugin));
                    }
                } else {
                    results = null;
                }
            }

            String signID = simpleSignature.getFileInfoSignature().getSignID();

            SignedDocumentResponseMultipart response;

            if (statusGlobal.getStatus() == (int) StatusConstants.STATUS_FINAL_OK.getValue()) {
                // Només hi ha d'haver una firma
                response = results.get(0);
                SignedDocumentInformation result = response.getSignedDocumentInformation();

                if (result.getStatus().getStatus() == (int) StatusConstants.STATUS_FINAL_OK.getValue()) {

                    // En API DE FIRMA SIMPE; EN SERVIDOR NOMES S'ENVIA UN DOCUMENT DE FIRMA A LA
                    // VEGADA
                    PassarelaFileInfoSignature fileInfo = pss.getFileInfoSignatureArray()[0];

                    final String profileSignType = null;

                    final boolean useSignPolicy = (pss.getCommonInfoSignature().getPolicyInfoSignature() != null);

                    UsuariAplicacioConfiguracio config = pcf.configBySignID.get(signID);

                    PassarelaValidacioCompletaResponse vcr = fullResults.getValidacioResponseBySignID()
                            .get(fileInfo.getSignID());

                    SignedFileInfo sfi = constructFirmaSimpleSignedFileInfoV2(config, fileInfo,
                            // TODO simpleSignature.getFileToSignName()
                            simpleSignature.getFileInfoSignature(), profileSignType, fileToSign, fileToSign.getName(),
                            useSignPolicy, vcr, languageUI, signaturePluginId);

                    result.setSignedFileInfo(sfi);

                    // response.getSignedFile()

                }
            } else {
                // Passam l'error general a l'error de la firma
                response = new SignedDocumentResponseMultipart();
                response.setSignedDocumentInformation(new SignedDocumentInformation(signID, statusGlobal, null, null));
            }

            log.info(" XYZ ZZZ Surt de signDocuments => FINAL");

            return response;

        } catch (NoCompatibleSignaturePluginException nape) {

            final boolean isUpgrade = false;

            String msg = getNoAvailablePluginErrorMessage(languageUI, isUpgrade, nape);

            log.error(msg);

            //throw new InternalServerErrorException(msg,  nape);

            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, nape);

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

            if (fileToSign != null) {
                if (!fileToSign.delete()) {
                    log.warn("No s'ha pogut eliminar el fitxer temporal fileToSign: " + fileToSign.getAbsolutePath());
                    fileToSign.deleteOnExit();
                }
            }

            if (previousSignatureDetachedFile != null) {
                if (!previousSignatureDetachedFile.delete()) {
                    log.warn("No s'ha pogut eliminar el fitxer temporal previousSignatureDetachedFile: "
                            + previousSignatureDetachedFile.getAbsolutePath());
                    previousSignatureDetachedFile.deleteOnExit();
                }
            }

        }

    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest getSimpleSignatureRequestApisibV2(
            SignDocumentRequest signatureRequest, MultipartFileInfo fileToSign,
            MultipartFileInfo previusSignatureDetachedFile) throws I18NException {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest signatureReuqestApisib;
        signatureReuqestApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest();

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo commonFileInfo = getCommonFileInfoApisib(
                signatureRequest.getCommonInfo());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignature = getFileInfoSignatureApisibV2(
                signatureRequest.getFileInfoSignature(), fileToSign, previusSignatureDetachedFile);
        signatureReuqestApisib.setCommonInfo(commonFileInfo);
        signatureReuqestApisib.setFileInfoSignature(fileInfoSignature);
        return signatureReuqestApisib;
    }

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest getFirmaSimpleUpgradeRequestApisib(
            MultipartFileInfo signature, MultipartFileInfo detachedDocument, MultipartFileInfo targetCertificate,
            String profileCode, String languageUI) throws I18NException {

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest signatureReuqestApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest();
        signatureReuqestApisib.setProfileCode(profileCode);

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile signatureFile = getFirmaSimpleFileV2(signature);
        signatureReuqestApisib.setSignature(signatureFile);

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile targetCertificateFile = getFirmaSimpleFileV2(
                targetCertificate);
        signatureReuqestApisib.setTargetCertificate(targetCertificateFile);

        signatureReuqestApisib.setLanguageUI(languageUI);
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile detachedDocumentFile = getFirmaSimpleFileV2(
                detachedDocument);

        signatureReuqestApisib.setDetachedDocument(detachedDocumentFile);

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

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature getFileInfoSignatureApisibV2(
            es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature fileInfoSignature,
            MultipartFileInfo fileToSign, MultipartFileInfo previusSignatureDetachedFile) throws I18NException {
        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature fileInfoSignatureApisib = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature();
        List<org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue> additionalInformationList = getAdditionalInformationList(
                fileInfoSignature.getAdditionalInformation());

        fileInfoSignatureApisib.setAdditionalInformation(additionalInformationList);
        fileInfoSignatureApisib.setDocumentType(fileInfoSignature.getDocumentType());
        fileInfoSignatureApisib.setExpedientCodi(fileInfoSignature.getExpedientCodi());
        fileInfoSignatureApisib.setExpedientNom(fileInfoSignature.getExpedientNom());
        fileInfoSignatureApisib.setExpedientUrl(fileInfoSignature.getExpedientUrl());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaType = getFirmaSimpleFileV2(fileToSign);

        fileInfoSignatureApisib.setFileToSign(newFirmaType);

        fileInfoSignatureApisib.setLanguageSign(fileInfoSignature.getLanguageSign());
        fileInfoSignatureApisib.setLocation(fileInfoSignature.getLocation());
        fileInfoSignatureApisib.setName(fileInfoSignature.getName());

        org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile previousDetachedFile = getFirmaSimpleFileV2(
                previusSignatureDetachedFile);
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

    private org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile getFirmaSimpleFileV2(
            MultipartFileInfo firmaSimpleFileInfo) throws I18NException {

        if (firmaSimpleFileInfo != null && firmaSimpleFileInfo.getFile() != null
                && firmaSimpleFileInfo.getFile().exists()) {

            File firmaSimpleFile = firmaSimpleFileInfo.getFile();

            org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile newFirmaSimpleFile = new org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile();
            try {
                newFirmaSimpleFile.setData(Files.readAllBytes(firmaSimpleFile.toPath()));
            } catch (Throwable e) {
                String msg = "Error llegint el fitxer " + firmaSimpleFile.getAbsolutePath() + ": " + e.getMessage();
                log.error(msg, e);
                throw new I18NException(e, "genapp.comodi", msg);
            }
            newFirmaSimpleFile.setMime(firmaSimpleFileInfo.getContentType());
            newFirmaSimpleFile.setNom(firmaSimpleFileInfo.getFileName());
            return newFirmaSimpleFile;
        } else {
            return null;
        }
    }

    protected String getNoAvailablePluginErrorMessage(String language, boolean isUpgrade,
            NoCompatibleSignaturePluginException ex) {
        // TODO XYZ ZZZ Traduir
        String msg;
        if (!isUpgrade) {
            msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
        } else {
            msg = "El plugin seleccionat no suporta el proces d'actualització de firma.";
        }

        if (ex != null && ex.getMessage() != null) {
            msg = msg + " (" + ex.getMessage() + ")";
        }

        return msg;
    }

    protected UpgradedFileInfo constructFirmaSimpleUpgradedFileInfo(PassarelaUpgradeResponse upgradeResponse,
            String signatureType, SignatureTypeFormEnumForUpgrade singTypeForm) throws I18NException {

        String profileSignType = singTypeForm.getName();

        log.info("Cridant a constructFirmaSimpleUpgradedFileInfo:: signatureType => " + signatureType
                + " profileSignType => " + profileSignType);

        org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse vsr;
        vsr = upgradeResponse.getValidacioResponse().getValidateSignatureResponse();

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

            es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidacioCompletaResponse vcr;
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

        File folderApi = new File(FileSystemManager.getFilesPath(), "API_UTILITATSFIRMA_V2");

        File folderTransaction = new File(folderApi, transactionID);
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
    protected SignPlugin getSignaturePluginInformation(String languageUI, Long signaturePluginId) throws I18NException {

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
     * @throws I18NException
     */
    protected SignedDocumentResponseMultipart convertPassarelaSignatureResult2FirmaSimpleSignatureResultV2(
            PassarelaSignatureResult psr, PassarelaCommonInfoSignature commonInfo,
            PassarelaFileInfoSignature infoSignature, PassarelaValidacioCompletaResponse infoValidacio,
            Long signaturePluginId, SignPlugin signPlugin) throws I18NException {

        ProcessStatus status = new ProcessStatus(psr.getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace());

        SignedFileInfo sfiV2 = null;
        //Document file = null;

        if (psr.getStatus() == StatusSignature.STATUS_FINAL_OK) {

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
            {
                eniSignerName = null;
                eniSignerAdministrationId = null;
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

            SignerInfo signerInfo;
            signerInfo = new SignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel, signDate,
                    serialNumberCert, issuerCert, subjectCert, signPlugin, additionInformation);

            final boolean timeStampIncluded = (timeStampIncluded2 == null) ? false : timeStampIncluded2.booleanValue();

            NonCryptographicInformation nonCryptographicInformation;
            nonCryptographicInformation = convertNonCryptographicInformation(
                    infoValidacio.getPassarelaNonCryptographicInformation());

            sfiV2 = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
                    timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, signerInfo, validation,
                    nonCryptographicInformation);

        }

        File signedFile;

        String signedFileName;
        String signedFileMime;

        FitxerBean fb = psr.getSignedFile();

        if (fb == null) {
            signedFile = null;
            signedFileName = null;
            signedFileMime = null;
        } else {
            DataHandler dh = fb.getData();

            if (dh == null) {
                signedFile = null;
                signedFileName = null;
                signedFileMime = null;
            } else {
                signedFileName = fb.getNom();
                signedFileMime = fb.getMime();

                // Si dh (datahandler) és de tipus file, llavors recollir-la directament i asignarla a 'signedFile'
                if (dh.getDataSource() instanceof FileDataSource) {
                    FileDataSource fds = (FileDataSource) dh.getDataSource();
                    signedFile = fds.getFile();
                } else {
                    // Si no és de tipus file, llavors recollir el contingut a un array de bytes i crear un fitxer temporal

                    InputStream is = null;
                    try {
                        is = dh.getInputStream();

                        signedFile = File.createTempFile("UtilitatsFirmaV2_signDocument_", "_signed");

                        Files.copy(is, signedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    } catch (Throwable th) {
                        String msg = "Error llegint el fitxer de la firma generada: " + th.getMessage();
                        log.error(msg, th);
                        throw new I18NException(th, "genapp.comodi", msg);
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
        }

        final String signID = psr.getSignID();

        SignedDocumentInformation sdi = new SignedDocumentInformation(signID, status, sfiV2, signPlugin);

        MultipartNameAndMime signedFilePartInfo = new MultipartNameAndMime(signedFileName, signedFileMime);

        SignedDocumentResponseMultipart response = new SignedDocumentResponseMultipart();
        response.setSignedDocumentInformation(sdi);
        response.setSignedFile(signedFile);
        response.setSignedFilePartInfo(signedFilePartInfo);

        return response;

    }

    protected Document convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws I18NException {

        if (fb == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = fb.getData().getInputStream();
            byte[] data = IOUtils.toByteArray(is);
            return new Document(fb.getNom(), fb.getMime(), data);
        } catch (Throwable th) {
            String msg = "convertFitxerBeanToFirmaSimpleFile:: Error llegint el fitxer " + fb.getNom() + ": "
                    + th.getMessage();
            log.error(msg, th);
            throw new I18NException(th, "genapp.comodi", msg);
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
            SignDocumentRequest simpleSignature, File fileToSign, String fileToSignName,
            File previousSignatureDetachedFile, String previousSignatureDetachedFileName, String usuariAplicacio,
            PerfilDeFirma perfilFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
            throws I18NException, I18NValidationException {

        FileInfoSignatureV2WithFiles fileInfoSignatureWithFiles = new FileInfoSignatureV2WithFiles();
        fileInfoSignatureWithFiles.setFileInfoSignature(simpleSignature.getFileInfoSignature());
        fileInfoSignatureWithFiles.setFileToSign(fileToSign);
        fileInfoSignatureWithFiles.setFileToSignName(fileToSignName);
        fileInfoSignatureWithFiles.setPreviousDetachedFile(previousSignatureDetachedFile);
        fileInfoSignatureWithFiles.setPreviousDetachedFileName(previousSignatureDetachedFileName);

        List<FileInfoSignatureV2WithFiles> fileInfoSignatureList = new ArrayList<FileInfoSignatureV2WithFiles>();
        fileInfoSignatureList.add(fileInfoSignatureWithFiles);

        SignDocumentsRequest simpleSignaturesSet = new SignDocumentsRequest();
        simpleSignaturesSet.setCommonInfo(simpleSignature.getCommonInfo());
        simpleSignaturesSet.setFileInfoSignatureArray(fileInfoSignatureList);

        PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, simpleSignaturesSet, usuariAplicacio,
                perfilFirma, configBySignID);

        return pss;
    }

    private PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
            SignDocumentsRequest simpleSignaturesSet, String usuariAplicacio, PerfilDeFirma perfilFirma,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException {

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
            {
                List<FileInfoSignatureV2WithFiles> simpleFileInfoSignatureArray;
                simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();

                if (simpleFileInfoSignatureArray == null || simpleFileInfoSignatureArray.size() == 0) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi", "No ha enviat fitxers a firmar.");
                }

                String signerEmail = commonInfo.getSignerEmail();

                // DADES ESPECIFIQUES DE CADA FIRMA

                PassarelaFileInfoSignature[] fileInfoSignatureArray;
                fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.size()];

                //String lastCertificate = null;
                PassarelaPolicyInfoSignature lastPolicyInfoSignature = null;
                int i = 0;

                for (FileInfoSignatureV2WithFiles sfis : simpleFileInfoSignatureArray) {

                    es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature fis;
                    fis = sfis.getFileInfoSignature();

                    String signID = fis.getSignID();
                    log.info("------------SignID => " + signID);
                    log.info("------------InfoSignatureArray => " + simpleFileInfoSignatureArray.size());
                    if (sfis.getFileToSign() != null) {
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign() => "
                                + sfis.getFileToSign());
                        if (sfis.getFileToSignName() != null)
                            log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getNom() => "
                                    + sfis.getFileToSign().getName());

                    }
                    if (sfis.getFileToSign() == null) {
                        log.info("ERROR => NO S'HA TROBAT FILE TO SIGN");
                        log.info("FileToSign =>");
                        log.info(sfis.getFileToSign().getName());
                    }

                    FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(transactionID, signID,
                            sfis.getFileToSign(), sfis.getFileToSignName());
                    if (fileToSign != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign => " + fileToSign);
                    if (fileToSign.getNom() != null)
                        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getNom() => "
                                + fileToSign.getNom());

                    // XYZ ZZZ FALTA ENCARA NO SUPORTAT
                    FitxerBean prevSign = null;
                    if (sfis.getPreviousDetachedFile() != null) {
                        prevSign = convertFirmaSimpleFileToFitxerBean(transactionID, signID,
                                sfis.getPreviousDetachedFile(), sfis.getPreviousDetachedFileName());
                    }

                    String name = fis.getName();
                    String reason = fis.getReason();
                    String location = fis.getLocation();

                    int signNumber = fis.getSignNumber();
                    String languageSign = fis.getLanguageSign();

                    final String expedientCodi = fis.getExpedientCodi();
                    final String expedientNom = fis.getExpedientNom();
                    final String expedientUrl = fis.getExpedientUrl();
                    final String procedimentCodi = fis.getProcedimentCodi();
                    final String procedimentNom = fis.getProcedimentNom();

                    final List<PassarelaKeyValue> additionalInformation;
                    {
                        List<KeyValue> additionalInfoList = fis.getAdditionalInformation();
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
                    UsuariAplicacioConfiguracioJPA config = configBySignID.get(fis.getSignID());

                    // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
                    final int signOperation = config.getTipusOperacioFirma();

                    // TIPUS DE FIRMA
                    final String signType = SignatureUtils.convertPortafibSignTypeToApiSignType(config.getTipusFirma());

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
                    final boolean useTimeStamp = getUseTimestampOfConfig(usuariAplicacioID, config,
                            fis.getUseTimeStamp());

                    log.info(" XYZ ZZZ \n\n\n  convertRestBean2PassarelaBean::useTimeStamp => " + useTimeStamp
                            + "\n\n\n");

                    // Això ja es farà a PassarelaDeFirmaWebEJB
                    //final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo = null;

                    fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign, signID, name,
                            reason, location, signerEmail, signNumber, languageSign, signOperation, signType,
                            signAlgorithm, signMode, signaturesTableLocation,
                            /* signaturesTableHeader,
                            secureVerificationCodeStampInfo, */ useTimeStamp, expedientCodi, expedientNom,
                            expedientUrl, procedimentCodi, procedimentNom, additionalInformation);
                    i++;

                    // LES DADES COMUNS DE TOTES LES CONFIGURACIONS HAN DE SER IGUALS
                    if (lastPolicyInfoSignature == null) {
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
                        if (!compare(lastPolicyInfoSignature, getPoliticaFirmaOfConfig(usuariAplicacioID, config))) {
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
                expiryDate.add(Calendar.MINUTE, 5 + simpleFileInfoSignatureArray.size());

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
            }

        } catch (Throwable th) {

            if (th instanceof I18NException) {
                throw (I18NException) th;
            } else {
                String msg = "Error no controlat en la conversió de les dades de la petició al format necessari per a la Passarela de Firma: "
                        + th.getMessage();
                log.error(msg, th);
                // XYZ ZZZ TRA
                throw new I18NException(th, "genapp.comodi", new I18NArgumentString(msg));
            }

        }

    }

    protected static class FileInfoSignatureV2WithFiles {

        protected es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature fileInfoSignature;

        protected File fileToSign;

        protected String fileToSignName;

        protected File previousDetachedFile;

        protected String previousDetachedFileName;

        public FileInfoSignatureV2WithFiles() {
            super();
            // TODO Auto-generated constructor stub
        }

        public FileInfoSignatureV2WithFiles(
                es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature fileInfoSignature,
                File fileToSign, String fileToSignName, File previousDetachedFile, String previousDetachedFileName) {
            super();
            this.fileToSign = fileToSign;
            this.fileToSignName = fileToSignName;
            this.previousDetachedFile = previousDetachedFile;
            this.previousDetachedFileName = previousDetachedFileName;
            this.fileInfoSignature = fileInfoSignature;
        }

        public File getFileToSign() {
            return fileToSign;
        }

        public void setFileToSign(File fileToSign) {
            this.fileToSign = fileToSign;
        }

        public String getFileToSignName() {
            return fileToSignName;
        }

        public void setFileToSignName(String fileToSignName) {
            this.fileToSignName = fileToSignName;
        }

        public File getPreviousDetachedFile() {
            return previousDetachedFile;
        }

        public void setPreviousDetachedFile(File previousDetachedFile) {
            this.previousDetachedFile = previousDetachedFile;
        }

        public String getPreviousDetachedFileName() {
            return previousDetachedFileName;
        }

        public void setPreviousDetachedFileName(String previousDetachedFileName) {
            this.previousDetachedFileName = previousDetachedFileName;
        }

        public es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature getFileInfoSignature() {
            return fileInfoSignature;
        }

        public void setFileInfoSignature(
                es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature fileInfoSignature) {
            this.fileInfoSignature = fileInfoSignature;
        }

    }

    protected static class SignDocumentsRequest {

        protected CommonInfo commonInfo;

        protected List<FileInfoSignatureV2WithFiles> fileInfoSignatureArray;

        public SignDocumentsRequest() {
            super();
        }

        public SignDocumentsRequest(CommonInfo commonInfo, List<FileInfoSignatureV2WithFiles> fileInfoSignatureArray) {
            super();
            this.commonInfo = commonInfo;
            this.fileInfoSignatureArray = fileInfoSignatureArray;
        }

        public List<FileInfoSignatureV2WithFiles> getFileInfoSignatureArray() {
            return fileInfoSignatureArray;
        }

        public void setFileInfoSignatureArray(List<FileInfoSignatureV2WithFiles> fileInfoSignatureArray) {
            this.fileInfoSignatureArray = fileInfoSignatureArray;
        }

        public CommonInfo getCommonInfo() {
            return commonInfo;
        }

        public void setCommonInfo(CommonInfo commonInfo) {
            this.commonInfo = commonInfo;
        }

    }

    public static FitxerBean convertFirmaSimpleFileToFitxerBean(String transactionID, String signID, File file,
            String fileName) throws I18NException {

        FitxerBean fileToSign = new FitxerBean();
        fileToSign.setDescripcio(null);

        // Get mime from file
        String mime;
        try {
            mime = Files.probeContentType(file.toPath());
        } catch (Throwable th) {
            String msg = "Error obtenint el mime type del fitxer [" + file.getAbsolutePath() + "].";
            logStatic.error(msg, th);
            throw new I18NException(th, "genapp.comodi", new I18NArgumentString(msg));
        }

        if (mime == null) {
            if (fileName.toLowerCase().endsWith(".pdf")) {
                mime = "application/pdf";
            } else if (fileName.toLowerCase().endsWith(".xml")) {
                mime = "application/xml";
            } else if (fileName.toLowerCase().endsWith(".txt")) {
                mime = "text/plain";
            } else {
                mime = "application/octet-stream";
            }
        }

        fileToSign.setMime(mime);

        fileToSign.setNom(fileName);
        fileToSign.setTamany(file.length());

        FileDataSource fds = new FileDataSource(file);

        fileToSign.setData(new DataHandler(fds));
        return fileToSign;
    }

    protected String getAlgorismeDeFirmaOfConfig(final UsuariAplicacioConfiguracio config) throws I18NException {
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
    protected boolean getUseTimestampOfConfig(final String usuariAplicacioID, final UsuariAplicacioConfiguracio config,
            final Boolean timestampIsRequiredByUsrApp) throws I18NException {
        final boolean useTimeStamp;

        int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

        switch (politicaSegellatDeTemps) {
            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:

                if (timestampIsRequiredByUsrApp != null && timestampIsRequiredByUsrApp.booleanValue()) {

                    // NOTA: Es presuposa que ja està controlat en el Validador: SignaturesSetValidator !!!!!
                    throw new I18NException("genapp.comodi", new I18NArgumentString(
                            "L'usuari aplicació ha requerit l'ús de segellat de temps pero la política de segellat de temps de la configuració de firma no ho permet."));
                }

                useTimeStamp = false;

            break;

            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
                if (timestampIsRequiredByUsrApp != null && timestampIsRequiredByUsrApp.booleanValue() == false) {

                    // NOTA: Es presuposa que ja està controlat en el Validador: SignaturesSetValidator !!!!!
                    throw new I18NException("genapp.comodi", new I18NArgumentString(
                            "L'usuari aplicació no ha requerit l'ús de segellat de temps però la política de segellat de temps de la configuració de firma l'obliga a fer-ho."));
                }
                useTimeStamp = true;
            break;

            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:

                if (timestampIsRequiredByUsrApp == null) {
                    useTimeStamp = true;
                } else {
                    useTimeStamp = timestampIsRequiredByUsrApp;
                }

            break;

            case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:

                if (timestampIsRequiredByUsrApp == null) {
                    useTimeStamp = false;
                } else {
                    useTimeStamp = timestampIsRequiredByUsrApp;
                }

            break;

            default:
                // NOTA: Es presuposa que ja està controlat en el Validador: SignaturesSetValidator !!!!!
                throw new I18NException("Política de segellat  de temps de la configuracio de firma amb ID "
                        + config.getUsuariAplicacioConfigID() + " desconeguda: " + politicaSegellatDeTemps);

        }

        return useTimeStamp;
    }

    protected SignedFileInfo constructFirmaSimpleSignedFileInfoV2(UsuariAplicacioConfiguracio config,
            PassarelaFileInfoSignature fileInfo,
            es.caib.utilitatsfirma.api.interna.secure.utilitatsfirma.v2.FileInfoSignature firmaRequest,
            String eniPerfilFirma, File fileToSign, String fileToSignName, boolean policyIncluded,
            PassarelaValidacioCompletaResponse vcr, final String languageUI, Long signaturePluginId)
            throws I18NException, Exception {

        log.info("XYZ ZZZ validateSignature::Entra a Validate Signature ...");

        String signType = fileInfo.getSignType();

        log.info("XYZ ZZZ validateSignature:: signType => " + signType);

        log.info("XYZ ZZZ validateSignature:: fileInfo.getSignMode() => " + fileInfo.getSignMode());
        /*
        @SuppressWarnings("unused")
        byte[] documentDetached = null;
        if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_DETACHED) {
        
            if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)
                    || FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
                documentDetached = documentToSign.getData();
            }
        
        }
        */

        final int signOperation = fileInfo.getSignOperation();
        final String signAlgorithm = fileInfo.getSignAlgorithm();
        final int signaturesTableLocation = fileInfo.getSignaturesTableLocation();
        final boolean timeStampIncluded = fileInfo.isUseTimeStamp2();

        SignedFileInfo signatureFileInfo;

        // Internament ja es verifica si s'ha de passar
        org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse vsr;
        vsr = vcr.getValidateSignatureResponse();

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

            String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

            if (vsr.getSignProfile() != null) {
                eniPerfilFirma = vsr.getSignProfile();
            }

            ValidationInfo validationInfo = new ValidationInfo();
            validationInfo.setCheckAdministrationIDOfSigner(vcr.getCheckAdministrationIDOfSigner());
            validationInfo.setCheckDocumentModifications(vcr.getCheckDocumentModifications());
            validationInfo.setCheckValidationSignature(vcr.getCheckValidationSignature());

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

            NonCryptographicInformation nonCryptographicInformation;
            nonCryptographicInformation = convertNonCryptographicInformation(
                    vcr.getPassarelaNonCryptographicInformation());

            signatureFileInfo = new SignedFileInfo(signOperation, signType, signAlgorithm, signMode,
                    signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
                    signerInfo, validationInfo, nonCryptographicInformation);

        }
        return signatureFileInfo;
    }

    @Path("/getSignatureRequestedInformation")
    @GET
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })

    @Operation(
            tags = { TAG_NAME },
            operationId = "getSignatureRequestedInformation",
            summary = "Retorna el conjunt de informació que pot retornar per la validació")
    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Retornada correctament la informació de la validació",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = SignatureRequestedInformation.class))) })

    public SignatureRequestedInformation getSignatureRequestedInformation(@Parameter(
            description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
            in = ParameterIn.QUERY,

            required = false,
            examples = { @ExampleObject(name = "Català", value = "ca"),
                    @ExampleObject(name = "Castellano", value = "es") },
            schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
    String languageUI, @Parameter(hidden = true) @Context
    HttpServletRequest request) {

        try {
            String username = request.getUserPrincipal().getName();
            log.info("ApiInterna::getSignatureRequestedInformation(USR: " + username + ") ...");

            org.fundaciobit.pluginsib.validatesignature.api.SignatureRequestedInformation sri_plugin = validacioFirmesEjb
                    .getSignatureRequestedInformation(languageUI);

            SignatureRequestedInformation sri = new SignatureRequestedInformation();

            sri.setReturnCertificateInfo(sri_plugin.getReturnCertificateInfo());
            sri.setReturnCertificates(sri_plugin.getReturnCertificates());
            sri.setReturnSignatureTypeFormatProfile(sri_plugin.getReturnSignatureTypeFormatProfile());
            sri.setReturnTimeStampInfo(sri_plugin.getReturnTimeStampInfo());
            sri.setReturnValidationChecks(sri_plugin.getReturnValidationChecks());
            sri.setValidateCertificateRevocation(sri_plugin.getValidateCertificateRevocation());

            return sri;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            String msgOrig = th.getMessage();

            // XYZ ZZZ TRA
            String msg = "Error desconegut recuperant la informació de retorn en un validació: " + msgOrig;
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, th);

        }

    }

    @Path("/validateSignature")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.APPLICATION_JSON })
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = UtilitatsFirmaV2Service.SECURITY_NAME)
    @Operation(
            tags = TAG_NAME,
            operationId = "validateSignature",
            description = "Operacio de firma simple en servidor d'un document",
            summary = "Operacio de firma simple en servidor d'un document")
    @ApiResponses(
            value = { @ApiResponse(
                    responseCode = "200",
                    description = "Operació realitzada correctament",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ValidateSignatureResponse.class))) })
    public ValidateSignatureResponse validateSignature(@Parameter(hidden = true) @Context
    HttpServletRequest request,
            /*
                    @Parameter(hidden = true)
                    MultipartFormDataInput input,
            */
            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,

                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI,

            ValidateSignatureRequestMultipart validateSignatureRequestMultipart

    ) throws RestException {

        languageUI = checkLanguage(languageUI);
        try {
            String username = request.getUserPrincipal().getName();
            log.info("ApiInterna::validateSignature(USR: " + username + ") ...");

            File signatureDocument = validateSignatureRequestMultipart.getSignatureDocument();
            File detachedDocument = validateSignatureRequestMultipart.getDetachedDocument();

            /*
            SignatureRequestedInformation rsi = validateSignatureRequestMultipart.getSignatureRequestedInformation();
            log.info(" --------------------");
            log.info("SignatureRequestedInformation:");
            log.info("  - getReturnCertificateInfo: " + rsi.getReturnCertificateInfo());
            log.info("  - getReturnCertificates: " + rsi.getReturnCertificates());
            log.info("  - getReturnSignatureTypeFormatProfile: " + rsi.getReturnSignatureTypeFormatProfile());
            log.info("  - getReturnTimeStampInfo: " + rsi.getReturnTimeStampInfo());
            log.info("  - getReturnValidationChecks: " + rsi.getReturnValidationChecks());
            log.info("  - getValidateCertificateRevocation: " + rsi.getValidateCertificateRevocation());
            */

            MultipartNameAndMime signatureDocumentInfo = validateSignatureRequestMultipart
                    .getSignatureDocumentPartInfo();

            log.info("ApiInterna::validateSignature::signatureDocumentInfo.getFileName() => "
                    + signatureDocumentInfo.getFileName());
            log.info("ApiInterna::validateSignature::signatureDocumentInfo.getContentType() => "
                    + signatureDocumentInfo.getContentType());

            String signType = SignType
                    .fromFile(signatureDocumentInfo.getFileName(), signatureDocumentInfo.getContentType()).typeName();

            log.info("ApiInterna::validateSignature( signType=" + signType + ", languageUI=" + languageUI
                    + ", Username=" + username);

            PassarelaValidateSignatureResponse presponse;

            es.caib.utilitatsfirma.logic.datasource.FileDataSource signature = new es.caib.utilitatsfirma.logic.datasource.FileDataSource(
                    signatureDocument);
            es.caib.utilitatsfirma.logic.datasource.FileDataSource detached = detachedDocument == null ? null
                    : new es.caib.utilitatsfirma.logic.datasource.FileDataSource(detachedDocument);

            presponse = validacioFirmesEjb.validateSignature(signType, signature, detached, languageUI, username,
                    Constants.ESTADISTICA_ENTORN_API_UTILITATS_FIRMA_V2, SignatureValidationService
                            .from(validateSignatureRequestMultipart.getSignatureRequestedInformation()));

            // Copiam els detalls de la resposta a la resposta de l'API
            List<es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureDetailInfo> signDetailList = null;

            org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse response;
            response = presponse.getValidateSignatureResponse();

            org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo[] sdiArray = response
                    .getSignatureDetailInfo();
            if (sdiArray != null) {
                signDetailList = new java.util.ArrayList<>(sdiArray.length);
                for (org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo sdi : sdiArray) {
                    signDetailList.add(SignatureValidationService.from(sdi));
                }
            }

            ValidateSignatureResponse vsr;
            vsr = new ValidateSignatureResponse();

            vsr.setSignatureDetailInfo(signDetailList);
            vsr.setSignMode(response.getSignMode());
            vsr.setSignProfile(response.getSignProfile());
            vsr.setSignType(response.getSignType());
            vsr.setValidationStatus(SignatureValidationService.from(response.getValidationStatus()));
            vsr.setNonCryptographicInformation(
                    convertNonCryptographicInformation(presponse.getNonCryptographicInformation()));

            return vsr;

        } catch (RestException re) {
            log.error(re.getMessage(), re);
            throw re;
        } catch (I18NException i18ne) {
            String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
            log.error(msg, i18ne);
            throw new RestException(msg);
        } catch (Throwable th) {
            String msgOrig = th.getMessage();

            // XYZ ZZZ TRA
            String msg = "Error desconegut iniciant  validacio de Firma: " + msgOrig;
            log.error(msg, th);
            throw new RestException(Status.INTERNAL_SERVER_ERROR, msg, th);

        }
    }

    /**
     * Converteix un PassarelaNonCryptographicInformation a NonCryptographicInformation
     * @param pni
     * @return
     */
    protected NonCryptographicInformation convertNonCryptographicInformation(PassarelaNonCryptographicInformation pni) {
        if (pni == null) {
            return null;
        } else {
            NonCryptographicInformation nci = new NonCryptographicInformation();
            nci.setAdministrationID(pni.getAdministrationID());
            nci.setDateOfSignature(pni.getDateOfSignature());
            nci.setName(pni.getName());
            nci.setNonCryptographicSignatureIdentifier(pni.getNonCryptographicSignatureIdentifier());
            nci.setNonCryptographicSystemCode(pni.getNonCryptographicSystemCode());
            nci.setNonCryptographicSystemName(pni.getNonCryptographicSystemName());
            nci.setSurname1(pni.getSurname1());
            nci.setSurname2(pni.getSurname2());
            nci.setUrlToDownloadFile(pni.getUrlToDownloadFile());
            nci.setUrlToWebInfo(pni.getUrlToWebInfo());

            Map<String, String> additionalInformation = pni.getAdditionalInformation();

            if (additionalInformation != null) {
                List<KeyValue> additionalInformationList = new java.util.ArrayList<>(additionalInformation.size());
                for (Map.Entry<String, String> entry : additionalInformation.entrySet()) {
                    additionalInformationList.add(new KeyValue(entry.getKey(), entry.getValue()));
                }
                nci.setAdditionalInformation(additionalInformationList);
            } else {
                nci.setAdditionalInformation(null);
            }

            return nci;
        }
    }

}
