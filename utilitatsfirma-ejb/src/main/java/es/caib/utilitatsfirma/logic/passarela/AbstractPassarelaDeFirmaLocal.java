package es.caib.utilitatsfirma.logic.passarela;

import java.io.File;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public interface AbstractPassarelaDeFirmaLocal {

    // Linia 255 de SignaturesSetValidator
    // public int getTimeStampPolicy() throws I18NException;

    public boolean providesTimeStampGenerator(String signType, List<Long> filterByPluginID,
            List<String> filterByPluginCode);

    public String[] getSupportedSignatureTypes(List<Long> filterByPluginID, List<String> filterByPluginCode);

    public String[] getSupportedSignatureAlgorithms(String signType, List<Long> filterByPluginID,
            List<String> filterByPluginCode);

    //public List<String> getSupportedBarCodeTypes() throws I18NException;

    public File getFitxerAdaptatPath(String transactionID, String signID);

}
