package es.caib.utilitatsfirma.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import es.caib.utilitatsfirma.back.controller.AbstractPluginAdminController;
import es.caib.utilitatsfirma.back.form.webdb.PluginFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.PluginForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.AbstractPluginIBLogicaLocal;
import es.caib.utilitatsfirma.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.utilitatsfirma.persistence.PluginJPA;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/moduldefirmaenservidor")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = "moduldefirmaenservidor.gestio",
        order = 120)
@Tile(name="pluginFirmaServidorFormAdmin", contentJsp="/WEB-INF/jsp/webdb/pluginForm.jsp", extendsTile=Tab.MENU_ADMIN,
type=TileType.WEBDB_FORM , attributes={ @TileAttribute(name="titol", value="plugin.plugin")})
@Tile(name="pluginFirmaServidorListAdmin", contentJsp="/WEB-INF/jsp/webdb/pluginList.jsp", extendsTile=Tab.MENU_ADMIN,
 type=TileType.WEBDB_LIST, attributes={ @TileAttribute(name="titol", value="plugin.plugin") })
public class ModulDeFirmaEnServidorAdminController extends AbstractPluginAdminController<ISignatureServerPlugin> {

    @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaServidorLogicaLocal modulDeFirmaEnServidorEjb;

    @Override
    public AbstractPluginIBLogicaLocal<ISignatureServerPlugin> getPluginEjb() {
        return modulDeFirmaEnServidorEjb;
    }


    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR;
    }

    @Override
    public String getCodeName() {
        return "moduldefirmaenservidor";
    }

    @Override
    public PluginJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pluginID) throws I18NException {
        log.info("\n\n\n   Entra a ModulDeFirmaEnServidorAdminController.findByPrimaryKey amb ID: " + pluginID + "\n\n\n");
        return (PluginJPA) modulDeFirmaEnServidorEjb.findByPrimaryKey(pluginID);
      }




}
