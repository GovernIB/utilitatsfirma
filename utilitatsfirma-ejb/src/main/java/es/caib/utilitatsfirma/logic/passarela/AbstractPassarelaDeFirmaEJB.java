package es.caib.utilitatsfirma.logic.passarela;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.ISignaturePlugin;

import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import es.caib.utilitatsfirma.logic.AbstractPluginIBLogicaLocal;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.utilitatsfirma.logic.utils.SignatureUtils;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPassarelaDeFirmaEJB<T extends ISignaturePlugin> implements AbstractPassarelaDeFirmaLocal {

    protected final Logger log = Logger.getLogger(this.getClass());

    //    @Override
    //    public int getTimeStampPolicy() throws I18NException {
    //
    //        return Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR;
    //
    //    }

    protected abstract AbstractPluginIBLogicaLocal<T> getModulDeFirmaEJB();

    @Override
    public boolean providesTimeStampGenerator(String signType, List<Long> filterByPluginID,
            List<String> filterByPluginCode) {

        try {
            //Recopilació de TOTS els plugins, per filtre per ID + filtre per codis
            List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(filterByPluginID, filterByPluginCode);

            for (T iSignaturePlugin : plugins) {

                /** El propi utilitatsfirma és el que proveirà el Segellador de temps, però hem de saber
                 *  si els plugins en si, internament suporten un Segellador de Temps extern 
                 */
                if (iSignaturePlugin.acceptExternalTimeStampGenerator(signType)) {
                    return true;
                }
            }

        } catch (I18NException e) {
            log.error("Error desconegut intentant esbrinar els plugins que suporten "
                    + "Segellat de Temps per tipus de firma " + signType + ": " + e.getMessage(), e);
        }
        return false;

    }

    // Recopilació de TOTS els plugins, per filtre per ID + filtre per codis
    @Override
    public String[] getSupportedSignatureTypes(List<Long> filterByPluginID, List<String> filterByPluginCode) {
        // TODO Falta CADes, ...

        Set<String> tipus = new HashSet<String>();
        final boolean debug = log.isDebugEnabled();
        try {
            //Recopilació de TOTS els plugins, per filtre per ID més  filtre per codis
            List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(filterByPluginID, filterByPluginCode);
            
            
            log.error("\n\nAbstractPassarelaDeFirmaEJB - getSupportedSignatureTypes - plugins trobats: " + plugins.size() + "\n\n");

            for (T iSignaturePlugin : plugins) {
                String[] tipusA = iSignaturePlugin.getSupportedSignatureTypes();
                if (tipusA != null) {
                    if (debug) {
                        log.debug("getSupportedSignatureTypes()::Plugin[" + iSignaturePlugin.getName(new Locale("ca"))
                                + "]: " + Arrays.toString(tipusA));
                    }
                    tipus.addAll(Arrays.asList(tipusA));
                }
            }

            return tipus.toArray(new String[tipus.size()]);

        } catch (I18NException e) {
            log.error("Error desconegut intentant esbrinar els tipus de firma" + " que suporten els plugins: "
                    + e.getMessage(), e);
            return new String[0];
        }

    }

    // Recopilació de TOTS els plugins, per filtre per ID + filtre per codis
    @Override
    public String[] getSupportedSignatureAlgorithms(String signType, List<Long> filterByPluginID,
            List<String> filterByPluginCode) {

        Set<String> tipusAlgo = new HashSet<String>();

        try {
            //Recopilació de TOTS els plugins, per filtre per ID + filtre per codis
            List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(filterByPluginID, filterByPluginCode);

            for (T iSignatureServerPlugin : plugins) {
                String[] tipusA = iSignatureServerPlugin.getSupportedSignatureAlgorithms(signType);
                if (tipusA != null) {
                    tipusAlgo.addAll(Arrays.asList(tipusA));
                }
            }

            return tipusAlgo.toArray(new String[tipusAlgo.size()]);

        } catch (I18NException e) {
            log.error("Error desconegut intentant esbrinar els algorismes de firma acceptats " + " pel tipus de firma "
                    + signType + " que suporten els plugins: " + e.getMessage(), e);
            return new String[0];
        }

    }

    /**
     * 
     * @param locale
     * @param pfis
     * @param original
     * @param adaptat
     * @throws I18NException
     */
    public int processFileToSign(Locale locale, PassarelaFileInfoSignature pfis, File original, File adaptat,
            UsuariAplicacioJPA usrApp) throws I18NException {
        return SignatureUtils.processFileToSign(locale, pfis, original, adaptat, usrApp);

    }

    // -----------------------------------------------------------------
    // -----------------------------------------------------------------
    // -------------- DIRECTORI DE FITXERS TEMPORALS -------------------
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------

    protected abstract String getPassarelaBasePath();

    public File getFitxerOriginalPath(String transactionID, String signID) {
        File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID + File.separatorChar + signID);
        p.mkdirs();
        return new File(p, "original");
    }

    public File getFitxerAdaptatPath(String transactionID, String signID) {
        File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID + File.separatorChar + signID);
        p.mkdirs();
        return new File(p, "adaptat");
    }

    public File getFitxerFirmatPath(String transactionID, String signID) {
        File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID + File.separatorChar + signID);
        p.mkdirs();
        return new File(p, "firmat");
    }

    public File getTransactionPath(String transactionID) {
        File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID);
        p.mkdirs();
        return p;
    }

}
