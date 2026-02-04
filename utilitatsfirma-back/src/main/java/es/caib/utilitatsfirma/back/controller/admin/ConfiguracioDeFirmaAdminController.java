package es.caib.utilitatsfirma.back.controller.admin;

import es.caib.utilitatsfirma.back.controller.webdb.UsuariAplicacioConfiguracioController;
import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioConfiguracioFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioConfiguracioForm;

import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;
import es.caib.utilitatsfirma.model.fields.PluginFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioConfiguracioFields;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.pluginsib.utils.signature.SignatureCommonUtils;
import org.fundaciobit.pluginsib.utils.signature.SignatureConstants;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author anadal
 * 3 feb 2026 11:40:41
 */
@Controller
@RequestMapping(value = ConfiguracioDeFirmaAdminController.CONTEXT_WEB)
@SessionAttributes(types = { UsuariAplicacioConfiguracioForm.class, UsuariAplicacioConfiguracioFilterForm.class })
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = UsuariAplicacioConfiguracioFields._TABLE_MODEL + "."
                + UsuariAplicacioConfiguracioFields._TABLE_MODEL + ".plural",
        addSeparatorAfter = true,
        order = 30)
@Tile(
        name = "usuariAplicacioConfiguracioFormAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/usuariAplicacioConfiguracioForm.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_FORM,
        attributes = {
                @TileAttribute(name = "titol", value = "usuariAplicacioConfiguracio.usuariAplicacioConfiguracio") })
@Tile(
        name = "usuariAplicacioConfiguracioListAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/usuariAplicacioConfiguracioList.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_LIST,
        attributes = {
                @TileAttribute(name = "titol", value = "usuariAplicacioConfiguracio.usuariAplicacioConfiguracio") })
public class ConfiguracioDeFirmaAdminController extends UsuariAplicacioConfiguracioController {

    public static final String CONTEXT_WEB = "/admin/configdefirma";

    @EJB(mappedName = UsuariAplicacioConfiguracioService.JNDI_NAME)
    private UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioLogicaEjb;

    @Override
    public UsuariAplicacioConfiguracioForm getUsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioJPA _jpa,
            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {

        UsuariAplicacioConfiguracioForm form = super.getUsuariAplicacioConfiguracioForm(_jpa, __isView, request, mav);

        if (form.isNou()) {
            // Creació
            UsuariAplicacioConfiguracio uac = form.getUsuariAplicacioConfiguracio();

            uac.setTipusOperacioFirma(Constants.TIPUS_OPERACIO_FIRMA_FIRMAR);
            uac.setTipusFirma(Constants.TIPUSFIRMA_PADES);
            uac.setModeDeFirma(Constants.SIGN_MODE_ATTACHED_ENVELOPED);

            uac.setComprovarNifFirma(false);
            uac.setCheckCanviatDocFirmat(false);
            uac.setValidarFirma(false);

            // XYZ ZZZ Falta valors per politiques de custodia, taula i segell de temps !!!!
            // XYZ ZZZ ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET

            uac.setPoliticaSegellatDeTemps(Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR);

        }

        if (__isView) {
            form.setCancelButtonVisible(false);
            form.addAdditionalButton(new AdditionalButton("far fa-edit", "genapp.edit",
                    CONTEXT_WEB + "/" + form.getUsuariAplicacioConfiguracio().getUsuariAplicacioConfigID() + "/edit",
                    AdditionalButtonStyle.WARNING));
        }

        form.addAdditionalButton(new AdditionalButton(IconUtils.ICON_INFO, MODEDEFIRMA.fullName,
                "javascript:window.open('https://ec.europa.eu/digital-building-blocks/DSS/webapp-demo/doc/dss-documentation.html#Packaging', true);",
                AdditionalButtonStyle.WARNING));

        // Codi comu
        form.addHelpToField(MODEDEFIRMA,
                "Més informació aquí: https://ec.europa.eu/digital-building-blocks/DSS/webapp-demo/doc/dss-documentation.html#Packaging");

        form.addReadOnlyField(TIPUSOPERACIOFIRMA);

        if (!__isView) {
            form.setAttachedAdditionalJspCode(true);
        }

        log.info(
                "\n ============   MODE DE FIRMA {FORM} ====> " + form.getUsuariAplicacioConfiguracio().getModeDeFirma()

                        + "\n ============  ID = " + form.getUsuariAplicacioConfiguracio().getUsuariAplicacioConfigID()
                        + "\n ============  NOU = " + form.isNou() + "=======\n");

        return form;
    }

    @Override
    public List<StringKeyValue> getReferenceListForUsPoliticaDeFirma(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        return staticGetReferenceListForUsPoliticaDeFirma();
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusOperacioFirma(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("" + Constants.TIPUS_OPERACIO_FIRMA_FIRMAR,
                I18NUtils.tradueix("usuariaplicacioconfig.operaciofirma." + Constants.TIPUS_OPERACIO_FIRMA_FIRMAR)));
        __tmp.add(new StringKeyValue("" + Constants.TIPUS_OPERACIO_FIRMA_COFIRMAR,
                I18NUtils.tradueix("usuariaplicacioconfig.operaciofirma." + Constants.TIPUS_OPERACIO_FIRMA_COFIRMAR)));
        __tmp.add(new StringKeyValue("" + Constants.TIPUS_OPERACIO_FIRMA_CONTRAFIRMAR, I18NUtils
                .tradueix("usuariaplicacioconfig.operaciofirma." + +Constants.TIPUS_OPERACIO_FIRMA_CONTRAFIRMAR)));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForPluginFirmaServidorID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        final Where w = Where.AND(where, PluginFields.TIPUS.equal(Constants.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR));

        List<StringKeyValue> list = pluginRefList.getReferenceList(PluginFields.PLUGINID, w);

        return list;
    }

    /**
     * #148
     */
    @Override
    public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        return staticGetReferenceListForPoliticaSegellatDeTemps();
    }

    @Override
    public void postValidate(HttpServletRequest request,
            UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, BindingResult result)
            throws I18NException {

        UsuariAplicacioConfiguracio uac = usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio();

        //log.info("\n\n ============   MODE DE FIRMA {POST_VALIDATE}  ====> " + uac.getModeDeFirma());

        // Politica de Firma
        int usPoliticaDeFirma = uac.getUsPoliticaDeFirma();

        if (usPoliticaDeFirma == Constants.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT) {

            Field<?>[] fields = { POLICYIDENTIFIER, POLICYIDENTIFIERHASH, POLICYIDENTIFIERHASHALGORITHM,
                    POLICYURLDOCUMENT };

            for (Field<?> field : fields) {
                String value = (String) result.getFieldValue(get(field));

                log.info(" VALOR CAMP[" + field.getFullName() + "] => " + value);

                if (value == null || value.trim().length() == 0) {
                    // El camp {0} és obligatori.
                    result.rejectValue(get(field), "genapp.validation.required",
                            new String[] { I18NUtils.tradueix(get(field)) }, null);
                }
            }

        } else {
            uac.setPolicyIdentifier(null);
            uac.setPolicyIdentifierHash(null);
            uac.setPolicyIdentifierHashAlgorithm(null);
            uac.setPolicyUrlDocument(null);
        }
        /*
        // Segellat de temps
        int politicaSegellat = uac.getPoliticaSegellatDeTemps();
        
        if (politicaSegellat == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR) {
            uac.setPluginSegellatID(null);
        } else {
            if (uac.getPluginSegellatID() == null) {
                // El camp {0} és obligatori.
                result.rejectValue(get(PLUGINSEGELLATID), "genapp.validation.required",
                        new String[] { I18NUtils.tradueix(get(PLUGINSEGELLATID)) }, null);
            }
        }
        
        // Comprovar que la configuracio de firma inclou el Plugin de Firma en Servidor
        {
            if (uac.isUsEnFirmaApiSimpleServidor() || uac.isUsEnFirmaPassarelaServidor()) {
                if (uac.getPluginFirmaServidorID() == null) {
                    result.rejectValue(get(PLUGINFIRMASERVIDORID), "conf.nofirmaservidor", new String[] {}, null);
                }
            }
        }
        */

    }

    @Override
    public List<StringKeyValue> getReferenceListForUpgradeSignFormat(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (SignatureTypeFormEnumForUpgrade up : SignatureTypeFormEnumForUpgrade.values()) {
            __tmp.add(new StringKeyValue(String.valueOf(up.getId()), up.getName()));
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusFirma(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = staticGetReferenceListForTipusFirmaID();
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForAlgorismeDeFirma(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return staticGetReferenceListForAlgorismeDeFirmaID();
    }

    @Override
    public List<StringKeyValue> getReferenceListForModeDeFirma(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return getReferenceListForModeDeFirma();
    }

    @Override
    public UsuariAplicacioConfiguracioFilterForm getUsuariAplicacioConfiguracioFilterForm(Integer pagina,
            ModelAndView mav, HttpServletRequest request) throws I18NException {
        UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm;
        usuariAplicacioConfiguracioFilterForm = super.getUsuariAplicacioConfiguracioFilterForm(pagina, mav, request);

        if (usuariAplicacioConfiguracioFilterForm.isNou()) {
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(UsuariAplicacioConfiguracioFields.ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS));
            hiddenFields.remove(NOM);
            // hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICACUSTODIA);
            hiddenFields.remove(UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID);
            hiddenFields.remove(UsuariAplicacioConfiguracioFields.TIPUSFIRMA);
            hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICASEGELLATDETEMPS);

            usuariAplicacioConfiguracioFilterForm.setHiddenFields(hiddenFields);

            usuariAplicacioConfiguracioFilterForm.setDeleteSelectedButtonVisible(false);

            usuariAplicacioConfiguracioFilterForm.setVisibleMultipleSelection(false);

            usuariAplicacioConfiguracioFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-info-circle icon-white", "ajuda.titol", "javascript:window.open('"
                            + request.getContextPath() + "/img/perfil_i_configuracio_de_firma.png', '_blank');",
                    AdditionalButtonStyle.INFO));

        }

        return usuariAplicacioConfiguracioFilterForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return null;
    }

    /*
    Sobreescriure operacions de crear/actualitzar/esborrar per emprar UsuariAplicacioConfiguracioLogica que genera bitàcola
     */

    @Override
    public UsuariAplicacioConfiguracioJPA create(HttpServletRequest request,
            UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) throws I18NException, I18NValidationException {
        return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioLogicaEjb
                .create(usuariAplicacioConfiguracio);
    }

    @Override
    public UsuariAplicacioConfiguracioJPA update(HttpServletRequest request,
            UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio) throws I18NException, I18NValidationException {
        return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioLogicaEjb
                .update(usuariAplicacioConfiguracio);
    }

    @Override
    public void delete(HttpServletRequest request, UsuariAplicacioConfiguracio usuariAplicacioConfiguracio)
            throws I18NException {
        usuariAplicacioConfiguracioLogicaEjb.delete(usuariAplicacioConfiguracio);
    }

    public static List<StringKeyValue> staticGetReferenceListForUsPoliticaDeFirma() {
        final int[] myArray = { Constants.US_POLITICA_DE_FIRMA_NO_USAR,
                Constants.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT };

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < myArray.length; i++) {
            String val = String.valueOf(myArray[i]);
            __tmp.add(new StringKeyValue(val, I18NUtils.tradueix("usuariaplicacioconfig.uspoliticafirma." + val)));
        }
        return __tmp;
    }

    public static List<StringKeyValue> staticGetReferenceListForPoliticaSegellatDeTemps() {

        final int[] myArray = { Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR,
                Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI,
                Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI,
                Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO };

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < myArray.length; i++) {
            String val = String.valueOf(myArray[i]);
            __tmp.add(new StringKeyValue(val, I18NUtils.tradueix("politicadesegellatdetemps." + val)));
        }
        return __tmp;

    }

    public static List<StringKeyValue> staticGetReferenceListForTipusFirmaID() {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        final int[] tipusDeFirma = { Constants.TIPUSFIRMA_PADES, Constants.TIPUSFIRMA_XADES,
                Constants.TIPUSFIRMA_CADES };

        for (int tipus : tipusDeFirma) {
            __tmp.add(new StringKeyValue(String.valueOf(tipus), I18NUtils.tradueix("tipusfirma." + tipus)));
        }
        return __tmp;

    }

    public static List<StringKeyValue> staticGetReferenceListForAlgorismeDeFirmaID() {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        __tmp.add(new StringKeyValue(String.valueOf(Constants.SIGN_ALGORITHM_SHA1WITHRSA), "SHA-1"));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.SIGN_ALGORITHM_SHA256WITHRSA), "SHA-256"));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.SIGN_ALGORITHM_SHA384WITHRSA), "SHA-384"));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.SIGN_ALGORITHM_SHA512WITHRSA), "SHA-512"));
        return __tmp;
    }

    public static List<StringKeyValue> getReferenceListForModeDeFirma() {

        final int[] modes = new int[] {

                /** El document inclou la Firma */
                SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPED, // = 0

                /** El fitxer resultant serà la firma que incloura les dades originals */
                SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPING, // = 3;

                /** El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original */
                SignatureConstants.SIGN_MODE_DETACHED, // = 1;

                /** Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma */
                SignatureConstants.SIGN_MODE_INTERNALLY_DETACHED, // = 4;

                /** Firma especial XAdES també anomenada XAdES-Manifest
                * 
                * https://www.linkedin.com/pulse/art%C3%ADculo-t%C3%A9cnico-firmas-electr%C3%B3nicas-xades-de-con-tom%C3%A1s-garc%C3%ADa-mer%C3%A1s/
                * https://administracionelectronica.gob.es/ctt/resources/Soluciones/323/Descargas/Sistema%20de%20referenciacion%20de%20documentos%20en%20las%20AAPP-v11.docx?idIniciativa=323&idElemento=16433
                * https://www.w3.org/TR/2000/WD-xmldsig-core-20000510/#sec-o-Manifest
                */
                SignatureConstants.SIGN_MODE_EXTERNALLY_DETACHED // = 5;
        };

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < modes.length; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(modes[i]), SignatureCommonUtils.signModeToString(modes[i])));
        }

        return __tmp;

    }

}
