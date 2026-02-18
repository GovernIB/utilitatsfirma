package es.caib.utilitatsfirma.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;

/**
 * 
 * @author anadal (u80067)
 * 17 feb 2026 13:49:48
 */
@Local
public interface PluginTipusDocumentalsLogicaLocal extends AbstractPluginIBLogicaLocal<ITipusDocumentalPlugin> {

    String JNDI_NAME = "java:app/utilitatsfirma-ejb/PluginTipusDocumentalsLogicaEJB";

    /**
     * 
     * @param language
     * @return
     * @throws I18NException
     */
    public List<TipusDocumental> getTipusDocumentals(String language) throws I18NException;

    /**
     * Retorna la class del PLugin de Tipus Documental Actiu
     * @return
     */
    public String getTipusDocumentalPluginClassName() throws I18NException;

}
