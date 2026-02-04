package es.caib.utilitatsfirma.api.interna.secure.validatesignature.v1;



/**
 * 
 * @return
 */
public interface CommonsSwaggerOperations {

    public static final String SECURITY_NAME = "BasicAuth";

    
    /*
    
    public static final String GETDOCUMENTARYTYPES_SUMMARY = "Retorna una llista dels Tipus Documentals disponibles en el servidor: tipus documentals base, tipus documentals de l'entitat i tipus documentals de l'usuari aplicació";

    @Path(value = "/getDocumentaryTypes")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })

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
            String languageUI) throws RestException;

    @Path("/getLanguages")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)

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
            String language) throws RestException;

    @Path("/getProfiles")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(MediaType.APPLICATION_JSON)

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
            String language) throws RestException;

    @Path("/versio")
    @GET
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = CommonsSwaggerOperations.SECURITY_NAME)
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })

    @ApiResponses({ @ApiResponse(
            responseCode = "200",
            description = "Retornada correctament la versió d'aquest Servei",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = String.class))) })
    public String versio();
    
    */
}
