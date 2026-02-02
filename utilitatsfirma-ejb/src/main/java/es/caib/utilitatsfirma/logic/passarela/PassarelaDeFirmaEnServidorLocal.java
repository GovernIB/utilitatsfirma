package es.caib.utilitatsfirma.logic.passarela;

import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.constants.SignatureTypeFormEnumForUpgrade;

import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioJPA;
import es.caib.utilitatsfirma.logic.passarela.api.NoCompatibleSignaturePluginException;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignatureInServerResults;
import es.caib.utilitatsfirma.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.utilitatsfirma.logic.passarela.api.UpgradeResponse;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.model.entity.UsuariAplicacioConfiguracio;

/**
 * 
 * @author anadal
 */
@Local
public interface PassarelaDeFirmaEnServidorLocal extends AbstractPassarelaDeFirmaLocal {

  String JNDI_NAME = "java:app/utilitatsfirma-ejb/PassarelaDeFirmaEnServidorEJB";

  PassarelaSignatureInServerResults signDocuments(PassarelaSignaturesSet signaturesSet,
      UsuariAplicacioJPA usrApp, 
      PerfilDeFirma perfilDeFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
         throws NoCompatibleSignaturePluginException;

  UpgradeResponse upgradeSignature(FirmaSimpleFile signature, FirmaSimpleFile detachedDocument,
      FirmaSimpleFile targetCertificate, SignatureTypeFormEnumForUpgrade signTypeForm,
      UsuariAplicacioJPA usrApp, PerfilDeFirma perfil, UsuariAplicacioConfiguracio config, 
      String languageUI) throws NoCompatibleSignaturePluginException, I18NException;

}
