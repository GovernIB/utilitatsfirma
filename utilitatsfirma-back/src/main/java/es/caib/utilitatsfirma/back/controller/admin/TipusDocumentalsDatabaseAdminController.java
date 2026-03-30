package es.caib.utilitatsfirma.back.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.utilitatsfirma.back.controller.webdb.TipusDocumentalController;
import es.caib.utilitatsfirma.back.form.webdb.TipusDocumentalFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.TipusDocumentalForm;
import es.caib.utilitatsfirma.back.utils.Tab;

/**
 * 
 * @author anadal (u80067)
 * 17 feb 2026 14:21:11
 */
/*
@MenuOption(
        labelCode = "tipusDocumental.tipusDocumental.plural",
        order = 105,
        group = Tab.MENU_ADMIN,
        addSeparatorAfter = true)
        */
@Controller
@RequestMapping(value = "/admin/tipusDocumental")
@SessionAttributes(types = { TipusDocumentalForm.class, TipusDocumentalFilterForm.class })
@Tile(name = "tipusDocumentalFormAdmin", extendsTile = Tab.MENU_ADMIN, type = TileType.WEBDB_FORM)
@Tile(name = "tipusDocumentalListAdmin", extendsTile = Tab.MENU_ADMIN, type = TileType.WEBDB_LIST)
public class TipusDocumentalsDatabaseAdminController extends TipusDocumentalController {

    @Override
    public TipusDocumentalFilterForm getTipusDocumentalFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        TipusDocumentalFilterForm tipusDocumentalFilterForm = super.getTipusDocumentalFilterForm(pagina, mav, request);

        if (tipusDocumentalFilterForm.isNou()) {
            tipusDocumentalFilterForm.setSubTitleCode(
                    "=IMPORTANT: Aquest tipus documentals només s'utilitzen si està actiu el plugin de tipus documentals org.fundaciobit.pluginsib.tipusdocumental.database.PluginTipusDocumentalDatabase i si a més la Base de Dades és la de utilitats de firma.");
        }

        return tipusDocumentalFilterForm;
    }

}
