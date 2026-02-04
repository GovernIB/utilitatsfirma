package es.caib.utilitatsfirma.back.controller.admin;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.utilitatsfirma.back.controller.webdb.PerfilsPerUsuariAplicacioController;
import es.caib.utilitatsfirma.back.form.webdb.PerfilsPerUsuariAplicacioForm;
import es.caib.utilitatsfirma.back.form.webdb.UsuariAplicacioRefList;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields;
import es.caib.utilitatsfirma.persistence.PerfilsPerUsuariAplicacioJPA;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = PerfilsDeUsuariAplicacioAdminController.CONTEXT_WEB)
@SessionAttributes(types = { PerfilsPerUsuariAplicacioForm.class })
@Tile(
        name = "perfilsPerUsuariAplicacioFormAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/perfilsPerUsuariAplicacioForm.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_FORM,
        attributes = { @TileAttribute(name = "titol", value = "perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio") })
@Tile(
        name = "perfilsPerUsuariAplicacioListAdmin",
        contentJsp = "/WEB-INF/jsp/webdb/perfilsPerUsuariAplicacioList.jsp",
        extendsTile = Tab.MENU_ADMIN,
        type = TileType.WEBDB_LIST,
        attributes = { @TileAttribute(name = "titol", value = "perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio") })
public class PerfilsDeUsuariAplicacioAdminController extends PerfilsPerUsuariAplicacioController {

    public static final String CONTEXT_WEB = "/admin/perfilsPerUsuariAplicacio";

    public static final String SESSION_USUARIAPLICACIOID = "SESSION_USUARIAPLICACIOID_EN_CONFIG";

    @PostConstruct
    public void init() {
        UsuariAplicacioRefList uaRefList = new UsuariAplicacioRefList(this.usuariAplicacioRefList);
        uaRefList.setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
        this.usuariAplicacioRefList = uaRefList;
    }

    @Override
    public PerfilsPerUsuariAplicacioForm getPerfilsPerUsuariAplicacioForm(PerfilsPerUsuariAplicacioJPA _jpa,
            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm;
        perfilsPerUsuariAplicacioForm = super.getPerfilsPerUsuariAplicacioForm(_jpa, __isView, request, mav);

        String usuariAplicacioID = (String) request.getSession().getAttribute(SESSION_USUARIAPLICACIOID);
        if (usuariAplicacioID == null) {

            log.warn(
                    "S'ha cridat a crear configuració d'Aplicació però no s'ha guardat el USUARIAPLICACIOID dins la sessió.");

            mav.setView(new RedirectView(UsuariAplicacioAdminController.CONTEXTWEB + "/list", true));
            return perfilsPerUsuariAplicacioForm;
        }

        perfilsPerUsuariAplicacioForm.getPerfilsPerUsuariAplicacio().setUsuariAplicacioID(usuariAplicacioID);
        perfilsPerUsuariAplicacioForm.addReadOnlyField(USUARIAPLICACIOID);

        return perfilsPerUsuariAplicacioForm;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, PerfilsPerUsuariAplicacioForm form) {
        return getRedirectWhenCancel(request, form.getPerfilsPerUsuariAplicacio().getPerfilsPerUsrAppID());
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long perfilPerUsrAppID) {
        return "redirect:" + UsuariAplicacioAdminController.CONTEXTWEB + "/list";
    }

    @Override
    public String getTileForm() {
        return "perfilsPerUsuariAplicacioFormAden";
    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

}
