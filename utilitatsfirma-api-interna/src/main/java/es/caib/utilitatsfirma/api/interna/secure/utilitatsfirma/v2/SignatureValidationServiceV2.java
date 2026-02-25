package es.caib.utilitatsfirma.api.interna.secure.validatesignature.v2;

import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import es.caib.utilitatsfirma.api.interna.secure.FormFileInfo;
import es.caib.utilitatsfirma.api.interna.secure.FormMethodUtils;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.CertificateTypeEidasConstants;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.CertificateTypeMineturConstants;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.CommonsSwaggerOperations;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureDetailInfo;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureRequestedInformation;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.SignatureValidationService;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.ValidateSignatureResponse;
import es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1.ValidationStatusConstants;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.PluginValidacioFirmesLogicaLocal;
import es.caib.utilitatsfirma.logic.datasource.FileDataSource;

import es.caib.utilitatsfirma.logic.utils.I18NLogicUtils;
import es.caib.utilitatsfirma.logic.utils.SignType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API Interna de utilitats de firma que ofereix serveis de validació de firmes versió 2.
 * @author anadal (u80067)
 * 24 feb 2026 8:08:08
 */
@Path(SignatureValidationServiceV2.PATH)
@OpenAPIDefinition(
        tags = @Tag(
                name = SignatureValidationServiceV2.TAG_NAME,
                description = "Firma Validació Swagger v2. API Interna de utilitatsfirma que ofereix serveis de validació de firmes."))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = CommonsSwaggerOperations.SECURITY_NAME, scheme = "basic")
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
                                        schema = @Schema(implementation = RestExceptionInfo.class)),

                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = ValidationStatusConstants.class)),

                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = CertificateTypeEidasConstants.class)),

                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = CertificateTypeMineturConstants.class)),

                        }) })
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SignatureValidationServiceV2 extends RestUtils {

    protected Logger log = Logger.getLogger(SignatureValidationServiceV2.class);

    public static final String PATH = "/secure/signaturevalidation/v2";

    public static final String TAG_NAME = "SignatureValidation v2"; // => SignatureValidationV1Api

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;

    //@EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    //protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @Path("/validateSignature")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({ MediaType.APPLICATION_JSON })
    @RolesAllowed({ Constants.SUF_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
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

            @Parameter(hidden = true)
            MultipartFormDataInput input,

            @Parameter(
                    description = "Idioma en que s'han de retornar les dades i errors(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,

                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(defaultValue = "ca", implementation = String.class)) @QueryParam("languageUI")
            String languageUI,

            @Parameter(
                    required = true,

                    schema = @Schema(implementation = SignatureRequestedInformation.class))

            @FormParam("signatureRequestedInformation")
            SignatureRequestedInformation signatureRequestedInformation,

            @Parameter(description = "Signatura", required = true)

            @FormParam(value = "signatureDocument")
            File signatureDocument,

            @Parameter(
                    description = "Document detached.",

                    required = false) @FormParam("detachedDocument")
            File detachedDocument

    ) throws RestException {

        languageUI = checkLanguage(languageUI);
        try {
            String username = request.getUserPrincipal().getName();
            log.info("ApiInterna::validateSignature(USR: " + username + ") ...");

            signatureRequestedInformation = FormMethodUtils.getJsonMultipartObj(input,
                    SignatureRequestedInformation.class, "signatureRequestedInformation");

            FormFileInfo signatureDocumentInfo = FormMethodUtils.getFormFileInfo(input, this.getClass().getSimpleName(),
                    "validateSignature", "signatureDocument", false);

            FormFileInfo detachedDocumentInfo = FormMethodUtils.getFormFileInfo(input, this.getClass().getSimpleName(),
                    "validateSignature", "detachedDocument", true);

            String signType = SignType
                    .fromFile(signatureDocumentInfo.getFileName(), signatureDocumentInfo.getContentType()).typeName();

            log.info("ApiInterna::validateSignature( signType=" + signType + ", languageUI=" + languageUI
                    + ", Username=" + username);

            org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse response;

            FileDataSource signature = new FileDataSource(signatureDocumentInfo.getFile());
            FileDataSource detached = detachedDocumentInfo== null ? null : new FileDataSource(detachedDocumentInfo.getFile());

            response = validacioFirmesEjb.validateSignature(signType, signature, detached, languageUI);

            // TODO FALTA CODI !!!!
            List<SignatureDetailInfo> signDetailList = null;

            org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo[] sdiArray = response
                    .getSignatureDetailInfo();
            if (sdiArray != null) {
                signDetailList = new java.util.ArrayList<>(sdiArray.length);
                for (org.fundaciobit.pluginsib.validatesignature.api.SignatureDetailInfo sdi : sdiArray) {
                    signDetailList.add(SignatureValidationService.from(sdi));
                }
            }

            ValidateSignatureResponse vsr = new ValidateSignatureResponse();

            vsr.setSignatureDetailInfo(signDetailList);
            vsr.setSignMode(response.getSignMode());
            vsr.setSignProfile(response.getSignProfile());
            vsr.setSignType(response.getSignType());
            vsr.setValidationStatus(SignatureValidationService.from(response.getValidationStatus()));
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

}
