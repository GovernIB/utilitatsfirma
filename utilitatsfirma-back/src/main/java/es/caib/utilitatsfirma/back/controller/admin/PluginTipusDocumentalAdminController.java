package es.caib.utilitatsfirma.back.controller.admin;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.utilitatsfirma.back.controller.AbstractPluginAdminController;
import es.caib.utilitatsfirma.back.form.webdb.PluginFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.PluginForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.AbstractPluginIBLogicaLocal;
import es.caib.utilitatsfirma.logic.PluginTipusDocumentalsLogicaLocal;

/**
 * 
 * @author anadal (u80067)
 * 17 feb 2026 14:20:51
 */
@Controller
@RequestMapping(value = "/admin/tipusdocumental")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "tipusdocumental.gestio", order = 100)
@Tile(name = "pluginTipusDocumentalFormAdmin", extendsTile = Tab.MENU_ADMIN, type = TileType.WEBDB_FORM)
@Tile(name = "pluginTipusDocumentalListAdmin", extendsTile = Tab.MENU_ADMIN, type = TileType.WEBDB_LIST)
public class PluginTipusDocumentalAdminController extends AbstractPluginAdminController<ITipusDocumentalPlugin> {

    @EJB(mappedName = PluginTipusDocumentalsLogicaLocal.JNDI_NAME)
    protected PluginTipusDocumentalsLogicaLocal tipusDocumentalLogicaEjb;

    @Override
    public AbstractPluginIBLogicaLocal<ITipusDocumentalPlugin> getPluginEjb() {
        return tipusDocumentalLogicaEjb;
    }

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_TIPUS_DOCUMENTAL;
    }

    @Override
    public String getCodeName() {
        return "tipusdocumental";
    }

}
