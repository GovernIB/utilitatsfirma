package es.caib.utilitatsfirma.logic;

import javax.ejb.Local;

import es.caib.utilitatsfirma.logic.passarela.api.ValidacioCompletaResponse;
import es.caib.utilitatsfirma.logic.utils.ValidacioCompletaRequest;
import es.caib.utilitatsfirma.logic.utils.ValidacioException;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Local
public interface ValidacioCompletaFirmaLogicaLocal {

    String JNDI_NAME = "java:app/utilitatsfirma-ejb/ValidacioCompletaFirmaLogicaEJB";

    public ValidacioCompletaResponse validateCompletaFirma(String transaccioID,
            ValidacioCompletaRequest validacioRequest, boolean validateChangesInAttachedFiles)
            throws ValidacioException;

}
