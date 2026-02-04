package es.caib.utilitatsfirma.logic;



import javax.ejb.Local;

import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.utilitatsfirma.ejb.UsuariAplicacioConfiguracioService;
import es.caib.utilitatsfirma.logic.utils.PerfilConfiguracionsDeFirma;
import es.caib.utilitatsfirma.model.entity.PerfilDeFirma;
import es.caib.utilitatsfirma.persistence.UsuariAplicacioConfiguracioJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface ConfiguracioUsuariAplicacioLogicaLocal extends
    UsuariAplicacioConfiguracioService {

  String JNDI_NAME = "java:app/utilitatsfirma-ejb/ConfiguracioUsuariAplicacioLogicaEJB";

  //public UsuariAplicacioConfiguracioJPA findByPrimaryKeyUnauthorized(Long _ID_);
  
  public PerfilDeFirma getPerfilDeFirma(final String usuariAplicacioID, String codiPerfil) throws I18NException;

  public UsuariAplicacioConfiguracioJPA getConfiguracioUsuariAplicacioPerUpgrade(
      String usuariAplicacioID, PerfilDeFirma perfilDeFirma,
      FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) throws I18NException;
  
  public PerfilConfiguracionsDeFirma getConfiguracioFirmaPerApiFirmaSimpleEnServidor(
      String usuariAplicacioID, String codiPerfil,
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException;
  /*
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaSimpleWeb(
      String usuariAplicacioID,  PerfilDeFirma codiPerfil, 
      FirmaSimpleSignDocumentRequest firmaSimpleSignDocumentRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaAsyncSimple(
      String usuariAplicacioID,  String codiPerfil,
      FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest) throws I18NException;
  
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirmaSimpleWeb(String usuariAplicacioID,
          PerfilDeFirma perfilDeFirma, ConfiguracioApiFirmaSimpleUtils configuracioApiFirmaSimpleUtils)
          throws I18NException;
  
  public UsuariAplicacioConfiguracioJPA getConfiguracioFirmaPerApiFirma(
          String usuariAplicacioID,  String codiPerfil, ConfiguracioCommonUtils configuracioCommonUtils) throws I18NException;
  */
  
  public PerfilDeFirma getPerfilDeFirmaPerApiFirmaSimple(final String usuariAplicacioID) throws I18NException;
/*
  public PerfilDeFirma getPerfilDeFirmaPerApiFirmaAsyncRest(final String usuariAplicacioID) throws I18NException;

  public PerfilConfiguracionsDeFirma getConfiguracioUsuariAplicacioPerPassarela(String usuariAplicacioID,
      PassarelaSignaturesSet signaturesSet, boolean esFirmaEnServidor) throws I18NException;

  public UsuariAplicacioConfiguracioJPA getConfiguracioUsuariAplicacioPerApiPortafibWS1(
      final String usuariAplicacioID) throws I18NException;

  public void deleteFull(Long _ID_) throws I18NException;
  */

}
