package es.caib.utilitatsfirma.logic;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;

import es.caib.utilitatsfirma.logic.datasource.IDataSource;
import es.caib.utilitatsfirma.logic.utils.ValidacioException;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface PluginValidacioFirmesLogicaLocal extends AbstractPluginIBLogicaLocal<IValidateSignaturePlugin> {

    String JNDI_NAME = "java:app/utilitatsfirma-ejb/PluginValidacioFirmesLogicaEJB";

    public ValidateSignatureResponse validateSignature(String signType, IDataSource signature,
            IDataSource documentDetached, String languageUI) throws ValidacioException;

}
