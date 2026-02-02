package es.caib.utilitatsfirma.logic;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;

/**
 * 
 * @author anadal
 * 28 ene 2026 14:50:55
 */
@Local
public interface ModulDeFirmaServidorLogicaLocal extends AbstractPluginIBLogicaLocal<ISignatureServerPlugin> {

    String JNDI_NAME = "java:app/utilittatsfirma-ejb/ModulDeFirmaServidorLogicaEJB";

}
