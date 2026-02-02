package es.caib.utilitatsfirma.logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.caib.utilitatsfirma.persistence.PluginJPA;
import es.caib.utilitatsfirma.commons.utils.Configuracio;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.model.entity.Plugin;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 *
 * @author anadal
 *
 */
public abstract class AbstractPluginIBLogicaEJB<I extends IPluginIB> extends PluginLogicaEJB
        implements AbstractPluginIBLogicaLocal<I> {

    protected abstract int getTipusDePlugin();

    protected abstract String getName();

    @Override
    public List<Plugin> getAllPlugins() throws I18NException {
        Where where = getWhere();
        return select(where);
    }

    @Override
    public List<Plugin> getAllPluginsSenseEntitat() throws I18NException {
        Where where = Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true));
        return select(where);
    }

    @Override
    public Where getWhere() {
        
    
            // Plugins de l'entitat o plugins per totes les entitats
            return Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true));
       
    }

    @Override
    public I getInstanceByPluginID(long pluginID) throws I18NException {

        IPluginIB pluginInstance = (IPluginIB) getPluginFromCache(pluginID);

        if (pluginInstance == null) {

            PluginJPA plugin = (PluginJPA) findByPrimaryKey(pluginID);

            if (plugin == null) {
                return null;
            }

            Properties prop = new Properties();

            if (plugin.getPropertiesAdmin() != null && plugin.getPropertiesAdmin().trim().length() != 0) {
                try {

                    prop.load(new StringReader(plugin.getPropertiesAdmin()));

                } catch (Exception e) {
                    // TODO Crec que no es cridarà mai
                }
            }

            
            try {
                StringWriter writer = new StringWriter();
                prop.store(writer, "");

                String propietats = writer.getBuffer().toString().replace("[\\=", "[=");

                Properties propietatsProp = Configuracio.getSystemAndFileProperties();

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("SP", propietatsProp);

                String t = TemplateEngine.processExpressionLanguageSquareBrackets(propietats, parameters);

                prop.load(new ByteArrayInputStream(t.getBytes(StandardCharsets.UTF_8)));

            } catch (IOException ex) {
                log.error("Error substituint propietats. Revisi la configuració del plugin " + pluginID, ex);
            }

            pluginInstance = (IPluginIB) PluginsManager.instancePluginByClassName(plugin.getClasse(),
                    Constants.UTILITATSFIRMA_PROPERTY_BASE, prop);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiate", getName() + " (" + plugin.getClasse() + ")");
            }

            addPluginToCache(pluginID, pluginInstance);

        }
        return (I) pluginInstance;

    }


    @Override
    public List<I> getPluginInstancesBy(List<Long> filterByPluginID, List<String> filterByPluginCode)
            throws I18NException {

        List<I> plugins = new ArrayList<I>();

        Where where = getWhere(); //Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true),getPluginsPerEntitat(entitatID));

        if (filterByPluginID != null && filterByPluginID.size() != 0) {
            where = Where.AND(where, PLUGINID.in(filterByPluginID));
        }

        // TODO XYZ pendent afegir camp codi dins plugin
        //    if (filterByPluginCode != null && filterByPluginCode.size() != 0) {
        //      where = Where.AND(where, CODI.in(filterByPluginID));
        //    }
        List<Plugin> modulsdefirma = select(where);

        for (Plugin mf : modulsdefirma) {
            plugins.add(getInstanceByPluginID(mf.getPluginID()));
        }

        return plugins;

    }

}
