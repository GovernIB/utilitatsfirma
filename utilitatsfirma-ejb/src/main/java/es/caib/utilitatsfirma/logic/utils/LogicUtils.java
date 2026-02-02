package es.caib.utilitatsfirma.logic.utils;

import org.apache.log4j.Logger;
import es.caib.utilitatsfirma.commons.utils.Configuracio;
import es.caib.utilitatsfirma.commons.utils.Version;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

	protected static Logger log = Logger.getLogger(LogicUtils.class);

	public static String getVersio() {
		return new Version().getVersion() + (Configuracio.isCAIB() ? "-caib" : "");
	}

}
