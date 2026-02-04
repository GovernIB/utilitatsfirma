package es.caib.utilitatsfirma.ejb.utils;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;

import org.jboss.logging.Logger;

import es.caib.utilitatsfirma.commons.utils.Constants;

/**
 * 
 * @author anadal
 * 3 feb 2026 15:12:46
 */
public class SignatureUtils {

    protected static final Logger log = Logger.getLogger(SignatureUtils.class);

    public static int convertApiSignTypeToPortafibSignType(String signType) throws I18NException {

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
            return Constants.TIPUSFIRMA_PADES;
        } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
            return Constants.TIPUSFIRMA_CADES;
        } else if (FileInfoSignature.SIGN_TYPE_SMIME.equals(signType)) {
            return Constants.TIPUSFIRMA_SMIME;
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
            return Constants.TIPUSFIRMA_XADES;
        } else {
            // TODO Traduir
            throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signType);
        }
    }

}
