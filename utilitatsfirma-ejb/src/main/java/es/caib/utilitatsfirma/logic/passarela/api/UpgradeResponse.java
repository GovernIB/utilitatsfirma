package es.caib.utilitatsfirma.logic.passarela.api;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class UpgradeResponse {

  protected final byte[] upgradedSignature;

  protected final ValidacioCompletaResponse validacioResponse;

  public UpgradeResponse(byte[] upgradedSignature, ValidacioCompletaResponse validacioResponse) {
    super();
    this.upgradedSignature = upgradedSignature;
    this.validacioResponse = validacioResponse;
  }

  public byte[] getUpgradedSignature() {
    return upgradedSignature;
  }

  public ValidacioCompletaResponse getValidacioResponse() {
    return validacioResponse;
  }

}
