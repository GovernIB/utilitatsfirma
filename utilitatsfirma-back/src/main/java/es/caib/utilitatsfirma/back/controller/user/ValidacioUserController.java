package es.caib.utilitatsfirma.back.controller.user;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.fundaciobit.pluginsib.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.validatesignature.api.ValidationStatus;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.caib.utilitatsfirma.back.controller.webdb.ValidacioController;
import es.caib.utilitatsfirma.back.form.webdb.ValidacioFilterForm;
import es.caib.utilitatsfirma.back.form.webdb.ValidacioForm;
import es.caib.utilitatsfirma.back.utils.Tab;
import es.caib.utilitatsfirma.commons.utils.Constants;
import es.caib.utilitatsfirma.logic.PluginValidacioFirmesLogicaLocal;
import es.caib.utilitatsfirma.logic.datasource.FileDataSource;
import es.caib.utilitatsfirma.logic.datasource.IDataSource;
import es.caib.utilitatsfirma.model.entity.Validacio;
import es.caib.utilitatsfirma.model.fields.ValidacioFields;
import es.caib.utilitatsfirma.persistence.ValidacioJPA;

/**
 * 
 * @author anadal
 * 23 ene 2026 11:32:49
 */
@MenuOption(labelCode = "validacio.validacio.plural", order = 50, group = Tab.MENU_USER)
@Controller
@RequestMapping(value = "/user/validacio")
@SessionAttributes(types = { ValidacioForm.class, ValidacioFilterForm.class })
@Tile(name = "validacioFormUser", extendsTile = Tab.MENU_USER, type = TileType.WEBDB_FORM)
@Tile(name = "validacioListUser", extendsTile = Tab.MENU_USER, type = TileType.WEBDB_LIST)
public class ValidacioUserController extends ValidacioController {

    @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
    protected PluginValidacioFirmesLogicaLocal pluginValidacioFirmesLogicaEjb;

    @Override
    public ValidacioJPA create(HttpServletRequest request, ValidacioJPA validacio)
            throws I18NException, I18NValidationException {

        validacio.setDataFi(new Timestamp(System.currentTimeMillis()));

        validacio = (ValidacioJPA) validacioEjb.create(validacio);

        try {

            String signType = "PADES"; // TODO: Per defecte es PADES
            IDataSource signature = new FileDataSource(FileSystemManager.getFile(validacio.getSignaturaID()));

            IDataSource documentDetached = null;

            if (validacio.getDetachedID() != null) {
                documentDetached = new FileDataSource(FileSystemManager.getFile(validacio.getDetachedID()));
            }

            String languageUI = LocaleContextHolder.getLocale().getLanguage();
            ValidateSignatureResponse vsr = pluginValidacioFirmesLogicaEjb.validateSignature(signType, signature,
                    documentDetached, languageUI);

            int status = vsr.getValidationStatus().getStatus();

            switch (status) {
                case ValidationStatus.SIGNATURE_VALID:

                    validacio.setResultat(Constants.RESULTAT_VALIDACIO_CORRECTA);

                break;
                case ValidationStatus.SIGNATURE_INVALID:

                    validacio.setResultat(Constants.RESULTAT_VALIDACIO_INCORRECTA);
                break;
                default:

                    validacio.setResultat(Constants.RESULTAT_VALIDACIO_ERROR);
                break;
            }

            // Crear Gson con format llegible
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convertir a JSON

            validacio.setInfoResultat(gson.toJson(vsr));

        } catch (I18NException e) {

            validacio.setResultat(Constants.RESULTAT_VALIDACIO_INCORRECTA);

            validacio.setInfoResultat("Error no controlat I18NException: " + I18NUtils.getMessage(e) + "\nStacktrace:\n"
                    + ExceptionUtils.getStackTrace(e)
                    + (e.getCause() == null ? "" : "\nCaused by: \n" + ExceptionUtils.getStackTrace(e.getCause())));

        } catch (Throwable e) {

            validacio.setResultat(Constants.RESULTAT_VALIDACIO_INCORRECTA);

            validacio.setInfoResultat("Error no controlat Throwable: " + e.getMessage() + "\nStacktrace:\n"
                    + ExceptionUtils.getStackTrace(e)
                    + (e.getCause() == null ? "" : "\nCaused by: \n" + ExceptionUtils.getStackTrace(e.getCause())));
        }

        validacio.setDataFi(new Timestamp(System.currentTimeMillis()));
        this.update(request, validacio);

        return validacio;
    }

    @Override
    public ValidacioForm getValidacioForm(ValidacioJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        ValidacioForm validacioForm = super.getValidacioForm(_jpa, __isView, request, mav);

        if (validacioForm.isNou()) {
            // Ocultam tots els camps excepte els obligatoris
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(ValidacioFields.ALL_VALIDACIO_FIELDS));

            hiddenFields.remove(ValidacioFields.NOM);
            hiddenFields.remove(ValidacioFields.SIGNATURAID);
            hiddenFields.remove(ValidacioFields.DETACHEDID);

            validacioForm.setHiddenFields(hiddenFields);

            // Donam un valor aleatori a nom per evitar que l'usuari el pugui deixar buit
            validacioForm.getValidacio().setNom("Validacio_" + System.currentTimeMillis());
            // Per a que no no doni error la validació del formulari
            validacioForm.getValidacio().setDataInici(new Timestamp(System.currentTimeMillis()));

        }

        return validacioForm;
    }

    @Override
    public ValidacioFilterForm getValidacioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        ValidacioFilterForm validacioFilterForm = super.getValidacioFilterForm(pagina, mav, request);

        if (validacioFilterForm.isNou()) {

            // Ocultar camp infoResultat
            validacioFilterForm.addHiddenField(ValidacioFields.INFORESULTAT);
            validacioFilterForm.addHiddenField(ValidacioFields.VALIDACIOID);

        }

        return validacioFilterForm;
    }

    @Override
    public void delete(HttpServletRequest request, Validacio validacio) throws I18NException {
        // A l'hora d'esborrar també volem esborrar els fitxers associats
        validacioEjb.deleteIncludingFiles(validacio, this.fitxerEjb);
    }

}
