package es.caib.utilitatsfirma.logic;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;

import es.caib.utilitatsfirma.ejb.UsuariAplicacioEJB;
import es.caib.utilitatsfirma.ejb.UsuariAplicacioService;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacio;
import es.caib.utilitatsfirma.model.fields.UsuariAplicacioFields;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;

/**
 * 
 * @author anadal
 * 3 feb 2026 11:52:45
 */
@Stateless
public class UsuariAplicacioLogicaEJB extends UsuariAplicacioEJB implements UsuariAplicacioLogicaLocal {

    @Override
    public UsuariAplicacioJPA createFull(UsuariAplicacioJPA _usuariAplicacio_)
            throws I18NException, I18NValidationException {
        /*
        UsuariAplicacioBeanLogicValidator uabv;
        uabv = new UsuariAplicacioBeanLogicValidator(custodiaInfoEjb, entitatEjb, idiomaEjb, this);
        
        final boolean isNou = true;
        List<I18NFieldError> fieldErrors = uabv.validate(_usuariAplicacio_, isNou);
        
        if (!fieldErrors.isEmpty()) {
          throw new I18NValidationException(fieldErrors);
        }
        */

        _usuariAplicacio_ = (UsuariAplicacioJPA) create(_usuariAplicacio_);

        return _usuariAplicacio_;
    }

    /**
     * 
     */
    @Override
    @PermitAll
    public UsuariAplicacioJPA findByPrimaryKeyFull(String _usuariAplicacioID_) {
        return findByPrimaryKeyFull(this, _usuariAplicacioID_);
    }

    @PermitAll
    public static UsuariAplicacioJPA findByPrimaryKeyFull(UsuariAplicacioService usuariAplicacioEjb,
            String _usuariAplicacioID_) {

        UsuariAplicacioJPA uaJPA = usuariAplicacioEjb.findByPrimaryKey(_usuariAplicacioID_);

        return uaJPA;
    }

    @Override
    @PermitAll
    public UsuariAplicacioJPA findByPrimaryKey(String _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    public void checkForDisable(String usuariAplicacioID) throws I18NException {

    }

    @Override
    public UsuariAplicacioJPA checkForDeletion(String usuariAplicacioID) throws I18NException {

        if (usuariAplicacioID == null) {
            return null;
        }

        UsuariAplicacio ua = findByPrimaryKey(usuariAplicacioID);
        if (ua == null) {
            return null;
        }

        // (a) És l'usuari aplicació per defecte de l'entitat
        checkForDisable(usuariAplicacioID);

        // (c) Té Notificacions pendents d'enviar
        // TODO Que feim amb això? Si és una notificacio d'un usuari app que 
        // volem esborrar i que no s'envia, llavors la esborram i punt

        // (e) Existeixen peticions de firma d'usuaris entitat amb
        // tipus de document associats al seu usuari-aplicació
        // (NOTA: En algun moment va ser usuari per defecte de l'entitat)
        // Els tipus de document d'un usuari aplicació només es permeten en peticions on 
        // es té el mateix usuari aplicació. Llavors estan inclosos en el cas (b)

        return (UsuariAplicacioJPA) ua;
    }

    @Override
    public UsuariAplicacioJPA checkBasicUsuariAplicacioID(String usuariAplicacioID) throws I18NException {
        if (usuariAplicacioID == null) {
            // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
            throw new I18NException("error.notfound", new I18NArgumentCode(UsuariAplicacioFields._TABLE_TRANSLATION),
                    new I18NArgumentCode(UsuariAplicacioFields.USUARIAPLICACIOID.fullName),
                    new I18NArgumentString(usuariAplicacioID));
        }

        UsuariAplicacio ua = findByPrimaryKey(usuariAplicacioID);
        if (ua == null) {
            // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
            throw new I18NException("error.notfound", new I18NArgumentCode(UsuariAplicacioFields._TABLE_TRANSLATION),
                    new I18NArgumentCode(UsuariAplicacioFields.USUARIAPLICACIOID.fullName),
                    new I18NArgumentString(usuariAplicacioID));
        }
        return (UsuariAplicacioJPA) ua;
    }

    @Override
    public Set<Long> deleteFull(String usuariAplicacioID) throws I18NException {

        Set<Long> fitxers = new HashSet<Long>();

        // (0) Checks
        UsuariAplicacioJPA usuariAplicacioJPA = checkForDeletion(usuariAplicacioID);
        if (usuariAplicacioJPA == null) {
            return fitxers;
        }

        delete(usuariAplicacioID);

        return fitxers;
    }

    @Override
    public void activar(String usuariAplicacioID) throws I18NException, Exception {
        UsuariAplicacioJPA usuariAplicacioJPA;
        usuariAplicacioJPA = findByPrimaryKey(usuariAplicacioID);

        if (usuariAplicacioJPA != null) {
            if (!usuariAplicacioJPA.isActiu()) {
                usuariAplicacioJPA.setActiu(true);
                update(usuariAplicacioJPA);
            }
        }
    }

    @Override
    public void desactivar(String usuariAplicacioID) throws I18NException, Exception {
        UsuariAplicacioJPA usuariAplicacioJPA;
        usuariAplicacioJPA = findByPrimaryKey(usuariAplicacioID);

        if (usuariAplicacioJPA != null) {

            if (usuariAplicacioJPA.isActiu()) {

                checkForDisable(usuariAplicacioID);

                usuariAplicacioJPA.setActiu(false);
                update(usuariAplicacioJPA);
            }
        }

    }
}
