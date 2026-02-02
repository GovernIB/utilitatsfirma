package es.caib.utilitatsfirma.logic.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.validation.IValidatorResult;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;

import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.passarela.AbstractPassarelaDeFirmaLocal;
import es.caib.utilitatsfirma.logic.passarela.PassarelaDeFirmaEnServidorLocal;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.model.bean.FitxerBean;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;

import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;

/**
 * 
 * @author anadal
 *
 * @param <T>
 */
public class SignaturesSetValidator<T extends PassarelaSignaturesSet> {

    protected final Logger log = Logger.getLogger(getClass());

    public SignaturesSetValidator() {
        super();
    }

    /** Constructor */
    public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__,
            AbstractPassarelaDeFirmaLocal passarelaDeFirmaEjb,
            Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) {

        final PassarelaSignaturesSet pss = __target__;

        // Comprovar signatureSetID #436
        if (isEmpty(pss.getSignaturesSetID())) {
            Field<?> f = getF("signaturesSetID");
            __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));
        } else {
            if (pss.getSignaturesSetID().length() > 100) {
                Field<?> f = getF("signaturesSetID");
                __vr.rejectValue(f, "genapp.validation.sizeexceeds", new I18NArgumentString(f.javaName),
                        new I18NArgumentString(String.valueOf(100)));
            }
        }

        // Valors Not Null
        if (pss.getExpiryDate() == null) {
            Field<?> f = getF("expiryDate");
            __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));
        }

        if (pss.getCommonInfoSignature() == null) {
            Field<?> f = getF("commonInfoSignature");
            __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));
        } else {
            if (pss.getCommonInfoSignature().getLanguageUI() == null) {
                Field<?> f = getF("commonInfoSignature.languageUI");
                __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));
            }
        }

        if (pss.getFileInfoSignatureArray() == null || pss.getFileInfoSignatureArray().length == 0) {
            Field<?> f = getF("fileInfoSignatureArray");
            __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));
        } else {

            // TODO Falten tots els tipus

            // TODO falta validar CSV !!!

            // No suportam Custòdia
            for (int i = 0; i < pss.getFileInfoSignatureArray().length; i++) {
                PassarelaFileInfoSignature pfis = pss.getFileInfoSignatureArray()[i];

                if (pfis == null) {
                    Field<?> f = getF("fileInfoSignatureArray");
                    __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));
                }

                // --------------------  FileToSign
                FitxerBean fitxer = pfis.getFileToSign();
                if (fitxer == null) {
                    Field<?> f = getF("fileInfoSignatureArray.fileToSign");
                    __vr.rejectValue(f, "genapp.validation.required", new I18NArgumentString(f.javaName));

                } else {

                    // Valors Not Null

                    if (isEmpty(fitxer.getNom())) {
                        Field<?> NOM = getF("fileInfoSignatureArray.fileToSign.nom");
                        __vr.rejectValue(NOM, "genapp.validation.required", new I18NArgumentString(get(NOM)));
                    }

                    if (fitxer.getTamany() <= 0) {
                        Field<?> TAMANY = getF("fileInfoSignatureArray.fileToSign.tamany");
                        __vr.rejectValue(TAMANY, "genapp.validation.required", new I18NArgumentString(get(TAMANY)));
                    }

                    if (isEmpty(fitxer.getMime())) {
                        Field<?> MIME = getF("fileInfoSignatureArray.fileToSign.mime");
                        __vr.rejectValue(MIME, "genapp.validation.required", new I18NArgumentString(get(MIME)));
                    }
                }

                //pfis.getSignID
                if (isEmpty(pfis.getSignID())) {
                    Field<?> SIGNID = getF("fileInfoSignatureArray.signID");
                    __vr.rejectValue(SIGNID, "genapp.validation.required", new I18NArgumentString(get(SIGNID)));
                }

                //pfis.getLanguageSign()
                if (isEmpty(pfis.getLanguageSign())) {
                    Field<?> FIELD = getF("fileInfoSignatureArray.languageSign");
                    __vr.rejectValue(FIELD, "genapp.validation.required", new I18NArgumentString(get(FIELD)));
                }

                //pfis.getName()
                if (isEmpty(pfis.getName())) {
                    Field<?> FIELD = getF("fileInfoSignatureArray.fileToSign.name");
                    __vr.rejectValue(FIELD, "genapp.validation.required", new I18NArgumentString(get(FIELD)));
                }

                // pfis.getReason()
                if (isEmpty(pfis.getReason())) {
                    Field<?> FIELD = getF("fileInfoSignatureArray.reason");
                    __vr.rejectValue(FIELD, "genapp.validation.required", new I18NArgumentString(get(FIELD)));
                }

                // -----------------------------------
                boolean signTypeOK = false;
                String signType = pfis.getSignType();
                {
                    Field<?> SIGNTYPE = getF("fileInfoSignatureArray.signType");

                    if (isEmpty(signType)) {

                        __vr.rejectValue(SIGNTYPE, "genapp.validation.required", new I18NArgumentString(get(SIGNTYPE)));
                    } else {
                        List<String> list = Arrays.asList(passarelaDeFirmaEjb.getSupportedSignatureTypes(null, null));
                        if (list.size() == 0) {
                            // No hi ha plugins disponibles
                            // TODO ZXC
                            __vr.rejectValue(SIGNTYPE, "plugin.signatureserver.none");
                        } else {
                            // Algun dels plugins suporta el tipus de firma 
                            if (!list.contains(signType)) {

                                // TODO ZXC
                                __vr.rejectValue(SIGNTYPE, "error.passarela.field.fixedvalues",
                                        new I18NArgumentString(get(SIGNTYPE)), new I18NArgumentString(Arrays
                                                .toString(passarelaDeFirmaEjb.getSupportedSignatureTypes(null, null))));
                            } else {
                                signTypeOK = true;
                            }
                        }
                    }
                }

                // -------------------------
                if (signTypeOK) {

                    Field<?> SIGNALGO = getF("fileInfoSignatureArray.fileToSign.signAlgorithm");
                    String signAlgo = pfis.getSignAlgorithm();
                    if (isEmpty(signType)) {
                        __vr.rejectValue(SIGNALGO, "genapp.validation.required", new I18NArgumentString(get(SIGNALGO)));
                    } else {
                        if (!Arrays.asList(passarelaDeFirmaEjb.getSupportedSignatureAlgorithms(signType, null, null))
                                .contains(signAlgo)) {
                            __vr.rejectValue(SIGNALGO, "error.passarela.field.fixedvalues",
                                    new I18NArgumentString(get(SIGNALGO)),
                                    new I18NArgumentString(Arrays.toString(passarelaDeFirmaEjb
                                            .getSupportedSignatureAlgorithms(signType, null, null))));
                        }
                    }
                }

                //        peticioDeFirma.tipusFirmaID=Tipus Firma
                //            peticioDeFirma.algorismeDeFirmaID=Algorisme de firma
                //            peticioDeFirma.modeDeFirma=Mode de firma

                // ------------------------
                if (pfis.getSignMode() != Constants.SIGN_MODE_ATTACHED_ENVELOPED // 0
                        && pfis.getSignMode() != Constants.SIGN_MODE_DETACHED // 1
                        && pfis.getSignMode() != Constants.SIGN_MODE_ATTACHED_ENVELOPING // 3;
                        && pfis.getSignMode() != Constants.SIGN_MODE_INTERNALLY_DETACHED // 4
                        && pfis.getSignMode() != Constants.SIGN_MODE_EXTERNALLY_DETACHED //5
                ) {
                    // El campo {0} solamente acepta los siguientes valores: {1}.
                    Field<?> STL = getF("fileInfoSignatureArray.fileToSign.signMode");
                    __vr.rejectValue(STL, "error.passarela.field.fixedvalues", new I18NArgumentString(get(STL)),
                            new I18NArgumentString("" + Constants.SIGN_MODE_ATTACHED_ENVELOPED + ", "
                                    + Constants.SIGN_MODE_DETACHED + ", " + Constants.SIGN_MODE_ATTACHED_ENVELOPING
                                    + ", " + Constants.SIGN_MODE_INTERNALLY_DETACHED + ", "
                                    + Constants.SIGN_MODE_EXTERNALLY_DETACHED));
                }

                // ------------------------
                int tl = pfis.getSignaturesTableLocation(); // RANG -1, 0, 1
                if (tl == FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE
                        || tl == FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE
                        || tl == FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
                    // OK
                } else {
                    // El campo {0} solamente acepta los siguientes valores: {1}.
                    Field<?> STL = getF("fileInfoSignatureArray.fileToSign.signaturesTableLocation");
                    __vr.rejectValue(STL, "error.passarela.field.fixedvalues", new I18NArgumentString(get(STL)),
                            new I18NArgumentString("" + FileInfoSignature.SIGNATURESTABLELOCATION_FIRSTPAGE + ", "
                                    + FileInfoSignature.SIGNATURESTABLELOCATION_LASTPAGE + ", "
                                    + FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT));
                }

                // ------------------------

                if (!(passarelaDeFirmaEjb instanceof PassarelaDeFirmaEnServidorLocal)) {

                    if (pfis.getSignNumber() != 1) {
                        // El campo {0} solamente acepta los siguientes valores: {1}.
                        Field<?> SIGNNUMBER = getF("fileInfoSignatureArray.fileToSign.signNumber");
                        __vr.rejectValue(SIGNNUMBER, "error.passarela.field.fixedvalues",
                                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(get(SIGNNUMBER)),
                                new I18NArgumentString("1"));
                    }
                }

                // ------------------------

                // Revisam si la els requeriments de l'usuari s'ajusten a la politica definida disn de la configuracio

                // CODI ANTIC => politicaSegellTemps = passarelaDeFirmaEjb.getTimeStampPolicy();

                UsuariAplicacioConfiguracio config = configBySignID.get(pfis.getSignID());

                int politicaSegellTemps = config.getPoliticaSegellatDeTemps();

                Boolean useTimeStamp = pfis.getUseTimeStamp2();

                switch (politicaSegellTemps) {
                    case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:

                        if (useTimeStamp != null && useTimeStamp.booleanValue()) {

                            Field<?> USETIMESTAMP = getF("fileInfoSignatureArray.fileToSign.useTimeStamp");
                            __vr.rejectValue(USETIMESTAMP, "genapp.comodi", new I18NArgumentString(
                                    "L'usuari aplicació ha requerit l'ús de segellat de temps pero la política de segellat de temps de la configuració de firma no ho permet."));
                        }
                    break;

                    case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:

                        if (useTimeStamp != null && useTimeStamp.booleanValue() == false) {

                            Field<?> USETIMESTAMP = getF("fileInfoSignatureArray.fileToSign.useTimeStamp");
                            __vr.rejectValue(USETIMESTAMP, "genapp.comodi", new I18NArgumentString(
                                    "L'usuari aplicació no ha requerit l'ús de segellat de temps però la política de segellat de temps de la configuració de firma l'obliga a fer-ho."));
                        }
                    break;

                    case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
                    case Constants.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:

                    // NO FEIM RES:  Tot OK
                    break;

                    default:
                        Field<?> USETIMESTAMP = getF("fileInfoSignatureArray.fileToSign.useTimeStamp");
                        __vr.rejectValue(USETIMESTAMP, "genapp.comodi",
                                new I18NArgumentString(
                                        "Política de segellat  de temps de la configuracio de firma amb ID "
                                                + config.getUsuariAplicacioConfigID() + " desconeguda: "
                                                + politicaSegellTemps));

                }

            }
        }

    } // Final de mètode

    public Field<?> getF(String path) {

        return new StringField("", path, path);
    }

    public String get(Field<?> field) {
        return field.javaName;
    }

    public boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
