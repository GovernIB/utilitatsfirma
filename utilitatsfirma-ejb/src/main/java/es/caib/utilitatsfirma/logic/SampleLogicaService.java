package es.caib.utilitatsfirma.logic;

//import java.util.Set;

import javax.ejb.Local;

/*
import es.caib.utilitatsfirma.ejb.AnnexService;
import es.caib.utilitatsfirma.persistence.AnnexJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
*/
/**
 * 
 * @author anadal
 *
 */
@Local
public interface SampleLogicaService /* extends AnnexService */ {

    public static final String JNDI_NAME = "java:app/utilitatsfirma-ejb/SampleLogicaEJB!es.caib.utilitatsfirma.logic.SampleLogicaService";
/*
    public void deleteFull(AnnexJPA annex) throws I18NException;

    
     * public AnnexJPA createFull(AnnexJPA annex) throws I18NException;
     */
}
