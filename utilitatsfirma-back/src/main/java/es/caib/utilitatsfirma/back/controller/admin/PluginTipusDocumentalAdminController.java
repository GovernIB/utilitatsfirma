package es.caib.utilitatsfirma.back.controller.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.utilitatsfirma.back.controller.AbstractPluginAdminController;
import es.caib.utilitatsfirma.back.form.webdb.PluginFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.PluginForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.AbstractPluginIBLogicaLocal;
import es.caib.utilitatsfirma.logic.PluginTipusDocumentalsLogicaLocal;
import es.caib.utilitatsfirma.model.entity.Plugin;

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

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PluginFilterForm filterForm, List<Plugin> list)
            throws I18NException {

        super.postList(request, mav, filterForm, list);

        for (Plugin plugin : list) {
            
            //log.info("Plugin " + plugin.getPluginID() + " amb classe " + plugin.getClasse());

            if ("org.fundaciobit.pluginsib.tipusdocumental.database.PluginTipusDocumentalDatabase"
                    .equals(plugin.getClasse())) {
                
                //log.info("Afegint botó de gestió de tipus documental al plugin " + plugin.getPluginID());
                
                if (filterForm.getAdditionalButtonsByPK().get(plugin.getPluginID()) == null) {
                   
                    filterForm.addAdditionalButtonByPK(plugin.getPluginID(), new AdditionalButton(IconUtils.ICON_LIST,
                        "genapp.list", "/admin/tipusDocumental/list", AdditionalButtonStyle.INFO));
                }
            }
        }

    }

}
