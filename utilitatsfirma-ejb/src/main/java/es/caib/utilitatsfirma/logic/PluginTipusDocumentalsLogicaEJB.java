package es.caib.utilitatsfirma.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;

import es.caib.utilitatsfirma.commons.utils.Constants;

import java.util.List;

import javax.ejb.Stateless;

/**
 * 
 * @author anadal (u80067)
 * 17 feb 2026 13:50:10
 */
@Stateless(name = "PluginTipusDocumentalsLogicaEJB")
public class PluginTipusDocumentalsLogicaEJB extends AbstractPluginIBLogicaEJB<ITipusDocumentalPlugin>
        implements PluginTipusDocumentalsLogicaLocal {

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_TIPUS_DOCUMENTAL;
    }

    @Override
    protected String getName() {
        return "Plugins de Tipus Documentals";
    }

    public List<TipusDocumental> getTipusDocumentals(String language) throws I18NException {

        long pluginIdActiu = this.executeQueryOne(PLUGINID, getWhere());

        // Seleccionar PLugin de Tipus Documentals Actiu
        ITipusDocumentalPlugin plugin = getInstanceByPluginID(pluginIdActiu);

        try {
            return plugin.getTipusDocumentals(language);
        } catch (Exception e) {
            String msg = "Error obtenint els tipus documentals del plugin " + pluginIdActiu + ": " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }

    }

    @Override
    public String getTipusDocumentalPluginClassName() throws I18NException {

        String classe = this.executeQueryOne(CLASSE, getWhere());

        return classe;

    }

}
