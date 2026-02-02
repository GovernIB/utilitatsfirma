package es.caib.utilitatsfirma.back.controller.admin;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pluginsib.validatesignature.api.IValidateSignaturePlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.utilitatsfirma.back.controller.AbstractPluginAdminController;
import es.caib.utilitatsfirma.back.form.webdb.PluginFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.PluginForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.AbstractPluginIBLogicaLocal;
import es.caib.utilitatsfirma.logic.PluginValidacioFirmesLogicaLocal;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/validaciofirmes")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = "validaciodefirmes.gestio",
        order = 100,
        addSeparatorAfter = true)
@Tile(name="pluginFormAdmin", contentJsp="/WEB-INF/jsp/webdb/pluginForm.jsp", extendsTile=Tab.MENU_ADMIN,
type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="plugin.plugin")})
@Tile(name="pluginListAdmin", contentJsp="/WEB-INF/jsp/webdb/pluginList.jsp", extendsTile=Tab.MENU_ADMIN,
 type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="plugin.plugin") })
public class ValidacioFirmesAdminController extends AbstractPluginAdminController<IValidateSignaturePlugin> {

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal validacioFirmesEnServidorEjb;

    @Override
    public AbstractPluginIBLogicaLocal<IValidateSignaturePlugin> getPluginEjb() {
        return validacioFirmesEnServidorEjb;
    }



    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_VALIDACIOFIRMES;
    }

    @Override
    public String getCodeName() {
        return "validaciodefirmes.plantilla";
    }

}
