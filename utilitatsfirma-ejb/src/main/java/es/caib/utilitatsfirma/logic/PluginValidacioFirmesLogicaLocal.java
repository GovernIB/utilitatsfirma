package es.caib.utilitatsfirma.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.validatesignature.api.IValidateSignaturePlugin;
import org.fundaciobit.pluginsib.validatesignature.api.SignatureRequestedInformation;

import es.caib.utilitatsfirma.logic.datasource.IDataSource;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaValidateSignatureResponse;
import es.caib.utilitatsfirma.logic.utils.ValidacioException;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface PluginValidacioFirmesLogicaLocal extends AbstractPluginIBLogicaLocal<IValidateSignaturePlugin> {

    String JNDI_NAME = "java:app/utilitatsfirma-ejb/PluginValidacioFirmesLogicaEJB";

    public PassarelaValidateSignatureResponse validateSignature(String signType, IDataSource signature,
            IDataSource documentDetached, String languageUI, String usuariAplicacioID, int entorn,
            SignatureRequestedInformation sri) throws ValidacioException;

    /**
     * 
     * @param languageID
     * @return
     * @throws I18NException
     * @throws ValidacioException
     */
    public SignatureRequestedInformation getSignatureRequestedInformation(String languageID)
            throws I18NException, ValidacioException;

}
