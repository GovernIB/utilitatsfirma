package es.caib.utilitatsfirma.logic;

import es.caib.utilitatsfirma.commons.utils.Constants;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;

/**
 * 
 * @author anadal
 * 28 ene 2026 14:51:44
 */
@PermitAll
@Stateless(name = "ModulDeFirmaServidorLogicaEJB")
public class ModulDeFirmaServidorLogicaEJB extends AbstractPluginIBLogicaEJB<ISignatureServerPlugin>
        implements ModulDeFirmaServidorLogicaLocal {

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_MODULDEFIRMA_SERVIDOR;
    }

    @Override
    protected String getName() {
        return "Modul de Firma en Servidor";
    }

}
