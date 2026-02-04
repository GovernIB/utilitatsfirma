package es.caib.utilitatsfirma.back.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.utilitatsfirma.back.controller.webdb.UsuariAplicacioController;
import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioConfiguracioFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.ejb.PerfilDeFirmaService;
import es.caib.utilitatsfirma.ejb.PerfilsPerUsuariAplicacioService;
import es.caib.utilitatsfirma.logic.UsuariAplicacioLogicaLocal;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacio;
import es.caib.utilitatsfirma.model.fields.PerfilDeFirmaFields;
import es.caib.utilitatsfirma.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;

/**
 * 
 * @author anadal
 * 3 feb 2026 11:41:05
 */
@Controller
@RequestMapping(value = UsuariAplicacioAdminController.CONTEXTWEB)
@SessionAttributes(types = { UsuariAplicacioForm.class, UsuariAplicacioFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "usuariaplicacio.gestio", order = 10)
@Tile(
        name = "usuariAplicacioFormAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/usuariAplicacioForm.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_FORM,
        attributes = { @TileAttribute(name = "titol", value = "usuariAplicacio.usuariAplicacio") })
@Tile(
        name = "usuariAplicacioListAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/usuariAplicacioList.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_LIST,
        attributes = { @TileAttribute(name = "titol", value = "usuariAplicacio.usuariAplicacio") })
public class UsuariAplicacioAdminController extends UsuariAplicacioController {

    public static final String CONTEXTWEB = "/admin/usuariAplicacio";

    protected static final int PERFILS = 1;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @EJB(mappedName = PerfilsPerUsuariAplicacioService.JNDI_NAME)
    protected PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

    @EJB(mappedName = PerfilDeFirmaService.JNDI_NAME)
    protected PerfilDeFirmaService usuariAplicacioPerfilEjb;

    @PostConstruct
    public void init() {
        //this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
        //setWebValidator(usuariAplicacioWebLogicValidator);
    }

    @Override
    public UsuariAplicacioForm getUsuariAplicacioForm(UsuariAplicacioJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {

        UsuariAplicacioForm usuariAplicacioForm = super.getUsuariAplicacioForm(_jpa, __isView, request, mav);

        if (usuariAplicacioForm.isNou()) {

            UsuariAplicacioJPA aplicacio = usuariAplicacioForm.getUsuariAplicacio();
            aplicacio.setActiu(true);

        } else {
            usuariAplicacioForm.addReadOnlyField(USUARIAPLICACIOID);
            usuariAplicacioForm.addReadOnlyField(ACTIU);
        }

        usuariAplicacioForm.setAttachedAdditionalJspCode(true);

        if (usuariAplicacioForm.getUsuariAplicacio().isActiu()) {
            usuariAplicacioForm.addAdditionalButton(new AdditionalButton("fas fa-ban", "desactivar",
                    getContextWeb() + "/desactivar/{0}", AdditionalButtonStyle.WARNING));
        } else {
            usuariAplicacioForm.addAdditionalButton(new AdditionalButton("fas fa-play", "activar",
                    getContextWeb() + "/activar/{0}", AdditionalButtonStyle.SUCCESS));
        }

        usuariAplicacioForm.setAttachedAdditionalJspCode(true);

        return usuariAplicacioForm;
    }

    @Override
    public UsuariAplicacioFilterForm getUsuariAplicacioFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        UsuariAplicacioFilterForm usuariAplicacioFilterForm;
        usuariAplicacioFilterForm = super.getUsuariAplicacioFilterForm(pagina, mav, request);

        if (usuariAplicacioFilterForm.isNou()) {

            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(UsuariAplicacioFields.ALL_USUARIAPLICACIO_FIELDS));

            hiddenFields.remove(USUARIAPLICACIOID);
            hiddenFields.remove(ACTIU);

            usuariAplicacioFilterForm.setHiddenFields(hiddenFields);

            usuariAplicacioFilterForm.setTitleCode("usuariaplicacio.llistat");

            usuariAplicacioFilterForm.setDeleteSelectedButtonVisible(false);

            usuariAplicacioFilterForm.setVisibleMultipleSelection(false);

            usuariAplicacioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("far fa-check-square",
                    "validar.urlcallback", getContextWeb() + "/validarurlcallback/{0}", AdditionalButtonStyle.INFO));

            usuariAplicacioFilterForm
                    .setActionsRenderer(UsuariAplicacioConfiguracioFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

            List<Field<?>> filterBy = new ArrayList<Field<?>>();
            filterBy.add(USUARIAPLICACIOID);
            usuariAplicacioFilterForm.setFilterByFields(filterBy);

            usuariAplicacioFilterForm.addGroupByField(ACTIU);

            {
                AdditionalField<String, String> adfield4 = new AdditionalField<String, String>();
                adfield4.setCodeName("perfils");
                adfield4.setPosition(PERFILS);
                // Els valors s'ompliran al mètode postList()
                adfield4.setValueMap(new HashMap<String, String>());
                adfield4.setEscapeXml(false);
                usuariAplicacioFilterForm.addAdditionalField(adfield4);

                usuariAplicacioFilterForm.addAdditionalButtonForEachItem(
                        new AdditionalButton("/img/config_add.png", "usuariaplicacio.config.new",
                                getContextWeb() + "/newconfig/{0}", AdditionalButtonStyle.SUCCESS));
            }

        }

        usuariAplicacioFilterForm.setVisibleExportList(true);

        return usuariAplicacioFilterForm;
    }

    @RequestMapping(value = "/deleteperfilusrapp/{usrAppID}/{perfilID}", method = RequestMethod.GET)
    public String deleteUserAppPerfil(HttpServletRequest request, HttpServletResponse response, @PathVariable
    String usrAppID, @PathVariable
    Long perfilID) throws I18NException {

        perfilsPerUsuariAplicacioEjb.delete(Where.AND(PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usrAppID),
                PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID.equal(perfilID)));

        // request.getSession().setAttribute("USUARI_APLICAIO_PER_AFEGIR_PERFIL", usrappid);

        return "redirect:" + getContextWeb() + "/list";

    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariAplicacioFilterForm filterForm,
            List<UsuariAplicacio> list) throws I18NException {

        List<String> usuariAplicacioIds = new ArrayList<String>(list.size());
        for (UsuariAplicacio usuariAplicacio : list) {
            usuariAplicacioIds.add(usuariAplicacio.getUsuariAplicacioID());
        }

        // PERFILS
        {
            Map<String, String> map;
            map = (Map<String, String>) filterForm.getAdditionalField(PERFILS).getValueMap();
            map.clear();

            for (UsuariAplicacio usuariAplicacio : list) {
                String key = usuariAplicacio.getUsuariAplicacioID();

                SubQuery<PerfilsPerUsuariAplicacio, Long> subquery;
                subquery = perfilsPerUsuariAplicacioEjb.getSubQuery(PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                        PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(key));

                //List<Long> perfilsID = perfilsPerUsuariAplicacioEjb.executeQuery(
                //    PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                //    PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(key));

                List<PerfilDeFirma> perfils = usuariAplicacioPerfilEjb
                        .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(subquery));

                if (perfils == null || perfils.size() == 0) {
                    map.put(key, "");
                } else {

                    StringBuilder str = new StringBuilder("<table>\n");
                    for (PerfilDeFirma perfil : perfils) {
                        str.append(
                                // Edit -> Link Nom i Codi
                                "<tr><td>\n" + "<a href=\"" + request.getContextPath()

                                        + PerfilDeFirmaAdminController.CONTEXT_WEB + "/view/"
                                        + perfil.getUsuariAplicacioPerfilID() + "\"> " + perfil.getNom() + " (<b>"
                                        + perfil.getCodi() + "</b>)</a>" + "</td><td>\n"
                                        // Delete => Icon trash
                                        + "<a style=\"padding: 0px; margin-bottom: 4px; margin-right: 4px\" href=\""
                                        + request.getContextPath() + getContextWeb() + "/deleteperfilusrapp/" + key
                                        + "/" + perfil.getUsuariAplicacioPerfilID()
                                        + "\" class=\"btn btn-sm btn-danger\" type=\"button\">"
                                        + "<i style=\"padding: 0px 4px 4px 0px; margin: 4px 0px 0px 3px \" class=\"fas fa-trash icon-white\"></i></a>\n"
                                        + "</td></tr>\n");
                    }
                    str.append("</table>");
                    map.put(key, str.toString());
                }

            }

        }

    }

    @RequestMapping(value = "/newconfig/{usuariAplicacioID}", method = RequestMethod.GET)
    public String newConfiguracioUsuariAplicacio(@PathVariable("usuariAplicacioID")
    String usuariAplicacioID, HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute(PerfilsDeUsuariAplicacioAdminController.SESSION_USUARIAPLICACIOID,
                usuariAplicacioID);
        return "redirect:" + PerfilsDeUsuariAplicacioAdminController.CONTEXT_WEB + "/new";
    }

    @Override
    public void delete(HttpServletRequest request, UsuariAplicacio usuariAplicacio) throws I18NException {
        Set<Long> fitxers = usuariAplicacioLogicaEjb.deleteFull(usuariAplicacio.getUsuariAplicacioID());
        this.borrarFitxers(fitxers);
    }

    @RequestMapping(value = "/activar/{usuariAplicacioID}", method = RequestMethod.GET)
    public ModelAndView activarUsuariEntitat(@PathVariable("usuariAplicacioID")
    String usuariAplicacioID, HttpServletRequest request, HttpServletResponse response) throws Exception {

        activarDesactivarUsauriApp(usuariAplicacioID, request, true);

        ModelAndView mav = new ModelAndView(
                new RedirectView(getContextWeb() + "/" + usuariAplicacioID + "/edit", true));
        return mav;
    }

    @RequestMapping(value = "/desactivar/{usuariAplicacioID}", method = RequestMethod.GET)
    public ModelAndView desactivarUsuariEntitat(@PathVariable("usuariAplicacioID")
    String usuariAplicacioID, HttpServletRequest request, HttpServletResponse response) {

        activarDesactivarUsauriApp(usuariAplicacioID, request, false);

        ModelAndView mav = new ModelAndView(
                new RedirectView(getContextWeb() + "/" + usuariAplicacioID + "/edit", true));
        return mav;
    }

    private void activarDesactivarUsauriApp(String usuariAplicacioID, HttpServletRequest request, boolean activar) {
        try {
            if (activar) {
                usuariAplicacioLogicaEjb.activar(usuariAplicacioID);
            } else {
                usuariAplicacioLogicaEjb.desactivar(usuariAplicacioID);
            }
        } catch (I18NException i18ne) {
            String msg = I18NUtils.getMessage(i18ne);
            log.error(msg, i18ne);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
    }

    @Override
    public UsuariAplicacioJPA create(HttpServletRequest request, UsuariAplicacioJPA usuariAplicacio)
            throws I18NException, I18NValidationException {
        return usuariAplicacioLogicaEjb.createFull(usuariAplicacio);
    }

}
