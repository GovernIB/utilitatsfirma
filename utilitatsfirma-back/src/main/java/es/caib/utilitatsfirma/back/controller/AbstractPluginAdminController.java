package es.caib.utilitatsfirma.back.controller;

import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.utilitatsfirma.back.controller.webdb.PluginController;
import es.caib.utilitatsfirma.back.form.webdb.PluginFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.PluginForm;
import es.caib.utilitatsfirma.logic.AbstractPluginIBLogicaLocal;
import es.caib.utilitatsfirma.model.entity.Plugin;
import es.caib.utilitatsfirma.persistence.PluginJPA;


/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPluginAdminController<I> extends PluginController {

    public abstract int getTipusDePlugin();

    public abstract String getCodeName();

    public abstract AbstractPluginIBLogicaLocal<I> getPluginEjb();

    public boolean isAdmin() {
        return true;
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return getClass().getName() + "_FilterForm";
    }

    @Override
    public final Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return null;
    }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm;
        pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);
        if (pluginFilterForm.isNou()) {

            Field<?>[] fields = ALL_PLUGIN_FIELDS;

            HashSet<Field<?>> campsOcults = new HashSet<Field<?>>(Arrays.asList(fields));

            campsOcults.remove(NOMID);
            campsOcults.remove(ACTIU);


            pluginFilterForm.getHiddenFields().addAll(campsOcults);

            pluginFilterForm.getDefaultGroupByFields().remove(TIPUS);

            pluginFilterForm
                    .addAdditionalButtonForEachItem(new AdditionalButton("fas fa-sync-alt", "plugin.netejardecache",
                            getContextWeb() + "/netejarInstanciaDeCache/{0}", AdditionalButtonStyle.WARNING));

            // TODO Ordenar per camp Traduit
            //pluginFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy( new PluginQueryPath().NOM(). )} );
        }
        return pluginFilterForm;
    }

    @RequestMapping(value = "/netejarInstanciaDeCache/{pluginID}")
    public ModelAndView netejarInstanciaDeCache(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long pluginID) throws I18NException {

        if (getPluginEjb().deleteOfCache(pluginID)) {
            HtmlUtils.saveMessageSuccess(request, "XYZ ZZZ TRA Esborrada correctament aquesta instància de la cache.");
        } else {
            HtmlUtils.saveMessageInfo(request, "XYZ ZZZ TRA No hi havia cap instància d'aquest plugin a la cache.");
        }

        return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    }

    @Override
    public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);
        if (pluginForm.isNou()) {
            PluginJPA p = pluginForm.getPlugin();
            p.setActiu(true);
            p.setTipus(getTipusDePlugin());            
            
        }
        

        pluginForm.addHiddenField(TIPUS);

        // XYZ ZZZ pendent implementació noves dades plugin #160

        
        if (!isAdmin()) {
            pluginForm.addReadOnlyField(CODI);
            pluginForm.addReadOnlyField(ORDRE);
        }

        return pluginForm;
    }


    @Override
    public String getEntityNameCode() {
        return getCodeName();
    }

    @Override
    public String getEntityNameCodePlural() {
        return getCodeName() + ".plural";
    }

    @Override
    public PluginJPA update(HttpServletRequest request, PluginJPA plugin)
            throws I18NException, I18NValidationException {
        return (PluginJPA) getPluginEjb().update(plugin);
    }

    @Override
    public void delete(HttpServletRequest request, Plugin plugin) throws I18NException {
        getPluginEjb().delete(plugin);
    }

   
}
