package es.caib.utilitatsfirma.model;

public class UtilitatsFirmaDaoManager {
  
  private static IUtilitatsFirmaDaoManagers instance = null;
  
  public static void setDaoManagers(IUtilitatsFirmaDaoManagers managers) {
    instance = managers;
  }
  
  public static IUtilitatsFirmaDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode UtilitatsFirmaDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
