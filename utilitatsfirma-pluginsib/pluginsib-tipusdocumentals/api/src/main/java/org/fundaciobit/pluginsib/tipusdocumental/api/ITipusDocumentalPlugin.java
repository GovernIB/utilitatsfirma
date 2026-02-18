package org.fundaciobit.pluginsib.tipusdocumental.api;

import java.util.List;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

/**
 * 
 * @author anadal
 * 17 feb 2026 10:07:13
 */
public interface ITipusDocumentalPlugin extends IPluginIB {
    
    
    public static final String PLUGIN_TIPUSDOCUMENTAL_BASE = "pluginsib.tipusdocumental.";
    

    /**
     * 
     * @return
     */
    public String getPluginName();

    /**
     * 
     * @param languageUI
     * @return
     * @throws Exception
     */
    public List<TipusDocumental> getTipusDocumentals(String languageUI) throws Exception;

}
