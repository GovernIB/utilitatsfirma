package es.caib.utilitatsfirma.back.controller.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.utilitatsfirma.back.controller.webdb.PerfilDeFirmaController;
import es.caib.utilitatsfirma.back.form.webdb.PerfilDeFirmaFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.PerfilDeFirmaForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;

import es.caib.utilitatsfirma.persistence.PerfilDeFirmaJPA;
import es.caib.utilitatsfirma.model.fields.PerfilDeFirmaFields;

/**
 * 
 * @author anadal
 * 3 feb 2026 11:58:44
 */
@Controller
@RequestMapping(value = PerfilDeFirmaAdminController.CONTEXT_WEB)
@SessionAttributes(types = { PerfilDeFirmaForm.class, PerfilDeFirmaFilterForm.class })
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = PerfilDeFirmaFields._TABLE_MODEL + "." + PerfilDeFirmaFields._TABLE_MODEL + ".plural",
        order = 20)
@Tile(
        name = "perfilDeFirmaFormAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/perfilDeFirmaForm.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_FORM,
        attributes = { @TileAttribute(name = "titol", value = "perfilDeFirma.perfilDeFirma") })
@Tile(
        name = "perfilDeFirmaListAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/perfilDeFirmaList.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_LIST,
        attributes = { @TileAttribute(name = "titol", value = "perfilDeFirma.perfilDeFirma") })
public class PerfilDeFirmaAdminController extends PerfilDeFirmaController {

    public static final String CONTEXT_WEB = "/admin/perfildefirma";


    @Override
    public PerfilDeFirmaFilterForm getPerfilDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PerfilDeFirmaFilterForm perfilDeFirmaFilterForm;
        perfilDeFirmaFilterForm = super.getPerfilDeFirmaFilterForm(pagina, mav, request);
        if (perfilDeFirmaFilterForm.isNou()) {
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(PerfilDeFirmaFields.ALL_PERFILDEFIRMA_FIELDS));
            hiddenFields.remove(NOM);
            hiddenFields.remove(CODI);
            hiddenFields.remove(DESCRIPCIO);

            perfilDeFirmaFilterForm.setHiddenFields(hiddenFields);

            perfilDeFirmaFilterForm.setDeleteSelectedButtonVisible(false);

            perfilDeFirmaFilterForm.setVisibleMultipleSelection(false);

            perfilDeFirmaFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-info-circle icon-white", "ajuda.titol", "javascript:window.open('"
                            + request.getContextPath() + "/img/perfil_i_configuracio_de_firma.png', '_blank');",
                    AdditionalButtonStyle.INFO));
        }

        return perfilDeFirmaFilterForm;
    }

    @Override
    public PerfilDeFirmaForm getPerfilDeFirmaForm(PerfilDeFirmaJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        PerfilDeFirmaForm form = super.getPerfilDeFirmaForm(_jpa, __isView, request, mav);

        form.addHelpToField(URLBASE, I18NUtils.tradueix("perfildefirma.urlbase.ajuda"));

        if (__isView) {

            // Posam botons d'edició i d'accés directe a Configuracions
            PerfilDeFirmaJPA perfil = form.getPerfilDeFirma();

            form.addAdditionalButton(new AdditionalButton("far fa-edit", "genapp.edit",
                    CONTEXT_WEB + "/" + perfil.getUsuariAplicacioPerfilID() + "/edit", AdditionalButtonStyle.WARNING));

            Long[] configuracions = new Long[] { perfil.getConfiguracioDeFirma1ID(), perfil.getConfiguracioDeFirma2ID(),
                    perfil.getConfiguracioDeFirma3ID(), perfil.getConfiguracioDeFirma4ID(),
                    perfil.getConfiguracioDeFirma5ID() };

            for (int i = 0; i < configuracions.length; i++) {

                if (configuracions[i] != null) {
                    form.addAdditionalButton(new AdditionalButton("fas fa-info-circle", "Configuració " + (i + 1),
                            ConfiguracioDeFirmaAdminController.CONTEXT_WEB + "/view/" + configuracions[i],
                            AdditionalButtonStyle.INFO));
                }
            }

            form.setCancelButtonVisible(false);
      

        }

        return form;
    }

    @Override
    public void postValidate(HttpServletRequest request, PerfilDeFirmaForm perfilDeFirmaForm, BindingResult result)
            throws I18NException {

        PerfilDeFirmaJPA perfil = perfilDeFirmaForm.getPerfilDeFirma();

        if (perfil.getConfiguracioDeFirma2ID() != null || perfil.getConfiguracioDeFirma3ID() != null
                || perfil.getConfiguracioDeFirma4ID() != null || perfil.getConfiguracioDeFirma5ID() != null) {
            // Condicio no ha de valer null
            if (perfil.getCondicio() == null) {
                result.rejectValue(get(CONDICIO), "genapp.validation.required",
                        new String[] { I18NUtils.tradueix(get(CONDICIO)) }, null);
            }

        }

    }

    @Override
    public List<StringKeyValue> getReferenceListForConfiguracioDeFirma1ID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
        return super.getReferenceListForConfiguracioDeFirma1ID(request, mav, newWhere);
    }

    @Override
    public List<StringKeyValue> getReferenceListForConfiguracioDeFirma2ID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
        return super.getReferenceListForConfiguracioDeFirma2ID(request, mav, newWhere);
    }

    @Override
    public List<StringKeyValue> getReferenceListForConfiguracioDeFirma3ID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
        return super.getReferenceListForConfiguracioDeFirma3ID(request, mav, newWhere);
    }

    @Override
    public List<StringKeyValue> getReferenceListForConfiguracioDeFirma4ID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
        return super.getReferenceListForConfiguracioDeFirma4ID(request, mav, newWhere);
    }

    @Override
    public List<StringKeyValue> getReferenceListForConfiguracioDeFirma5ID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
        return super.getReferenceListForConfiguracioDeFirma5ID(request, mav, newWhere);
    }

    protected Where newWhereForConfiguracioUsuariAplicacio(Where where) {
        return where;
    }
}
