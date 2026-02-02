package es.caib.utilitatsfirma.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

    public static final String UTILITATSFIRMA_PROPERTY_BASE = "es.caib.utilitatsfirma.";

    public static final String MAIL_SERVICE = "java:/es.caib.utilitatsfirma.mail";

    public static final String PDF_FILE_EXTENSION = "pdf";

    public static final String MIME_TYPE_PDF = "application/pdf";

    public static final String MIME_TYPE_BINARY = "application/octet-stream";

    public static final String MIME_TYPE_XML = "application/xml";

    // TRUE ROLES
    public static final String SUF_ADMIN = "SUF_ADMIN";
    public static final String SUF_USER = "SUF_USER";
    public static final String SUF_WS = "SUF_WS";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS = SUF_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = SUF_USER;
    public static final String ROLE_EJB_WS_ACCESS = SUF_WS;

    // Plugin types
    public static final int TIPUS_PLUGIN_VALIDACIOFIRMES = 1;
    public static final int TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR = 2;
    public static final int TIPUS_PLUGIN_SEGELLDETEMPS = 3;

    // Validation results
    public static final int RESULTAT_VALIDACIO_CORRECTA = 1;
    public static final int RESULTAT_VALIDACIO_INCORRECTA = 2;
    public static final int RESULTAT_VALIDACIO_ERROR = 3;

    public static final int TIPUSFIRMA_PADES = 0;
    public static final int TIPUSFIRMA_XADES = 1;
    public static final int TIPUSFIRMA_CADES = 2;
    public static final int TIPUSFIRMA_SMIME = 3;

    // --------------------------------------------------------------
    // AQUESTS VALORS ESTAN COPIATS DE PLUGINSIB DE FIRMA EN SERVIDOR
    // --------------------------------------------------------------

    /** Identificador de la firma PAdES. */
    public static final String SIGN_TYPE_PADES = "PAdES";
    /** Identificador de la firma XAdES por defecto. */
    public static final String SIGN_TYPE_XADES = "XAdES";
    /** Identificador de la firma CAdES. */
    public static final String SIGN_TYPE_CADES = "CAdES";

    // --------------------------------------------------------------
    // Modes de firma 
    // --------------------------------------------------------------

    /** El fitxer de dades resultant inclou la firma: PDF, ODT, ... */
    public static final int SIGN_MODE_ATTACHED_ENVELOPED = 0;

    /** El fitxer resultant serà la firma que incloura les dades originals */
    public static final int SIGN_MODE_ATTACHED_ENVELOPING = 3;

    /**
     * El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de
     * firma i el fitxer original
     */
    public static final int SIGN_MODE_DETACHED = 1;

    /**
     * Firma especial XAdES en que la firma i les dades estan al mateix nivell dins
     * de l'XML: ni la firma inclou les dades ni les dades inclouen la firma
     */
    public static final int SIGN_MODE_INTERNALLY_DETACHED = 4;

    /** Firma especial XAdES en que les dades es substitueixen per un resum del fitxer a signar. 
     * Entre les dades del resum hi ha una URL a les dades del fitxer original.
     */
    public static final int SIGN_MODE_EXTERNALLY_DETACHED = 5;

    // ========================================================
    // ----- CONSTANTS ESPECIFIQUES DE CADA FIRMA ------
    // ========================================================

    public static final int SIGN_ALGORITHM_SHA1WITHRSA = 0;
    public static final int SIGN_ALGORITHM_SHA256WITHRSA = 1;
    public static final int SIGN_ALGORITHM_SHA384WITHRSA = 2;
    public static final int SIGN_ALGORITHM_SHA512WITHRSA = 3;

    // ========================================================
    // ----- POLITICA DE SEGELLAT DE TEMPS ------
    // ========================================================

    // TODO esborrar aquesta constant
    // Aquest cas ja no té sentit
    //public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT=-1;

    public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR = 0;
    public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI = 1;
    public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI = 2;
    public static final int POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO = 3;
    
    
    
    //========================================================
    // ----- ESTADISTICA #168 ------
    // ========================================================

    public static final int ESTADISTICA_TIPUS_PETICIO_INICI = 0;

    public static final int ESTADISTICA_TIPUS_PETICIO_FINAL = 1;

    public static final int ESTADISTICA_TIPUS_PETICIO_REBUTJADA = 2;

    public static final int ESTADISTICA_TIPUS_PETICIO_FIRMES = 3;
    
    
    //==============================================================
    // --- US DE POLITICA DE FIRMA - PORTAFIB v2.0 #148 -----
    // =============================================================

    
    // 0 => no usar politica de firma, 
    public static final int US_POLITICA_DE_FIRMA_NO_USAR = 0;

    // 1=> usar politica d'aquesta configuracio 
    public static final int US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT = 1;
    

    
   
    
    
    // API FIRMA SIMPLE STATUS VALUES
    public static final int STATUS_INITIALIZING = 0;
    public static final int STATUS_IN_PROGRESS = 1;
    public static final int STATUS_FINAL_OK = 2;
    public static final int STATUS_FINAL_ERROR = -1;
    public static final int STATUS_CANCELLED = -2;
    
    
    //==============================================================
    // --- TIPUS US FIRMA DE CONFIGURACIO USUARI APLICACIO -----
    // ==============================================================


    public static final int US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR = 0;
    
    
}
