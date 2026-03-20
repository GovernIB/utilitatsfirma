package es.caib.utilitatsfirma.logic.passarela.api;

import java.util.Map;


/**
 * 
 * @author anadal(u80067)
 *
 */
public class PassarelaSignatureInServerResults {

    protected final PassarelaFullResults passarelaFullResults;

    protected final Map<String, PassarelaValidacioCompletaResponse> validacioResponseBySignID;

    protected final Long pluginFirmaEnServidorId;

    @Deprecated
    public PassarelaSignatureInServerResults(PassarelaFullResults passarelaFullResults,
            Map<String, PassarelaValidacioCompletaResponse> validacioResponseBySignID) {
        super();
        this.passarelaFullResults = passarelaFullResults;
        this.validacioResponseBySignID = validacioResponseBySignID;
        pluginFirmaEnServidorId = null;
    }

    public PassarelaSignatureInServerResults(PassarelaFullResults passarelaFullResults,
            Map<String, PassarelaValidacioCompletaResponse> validacioResponseBySignID, Long pluginFirmaEnServidorId) {
        super();
        this.passarelaFullResults = passarelaFullResults;
        this.validacioResponseBySignID = validacioResponseBySignID;
        this.pluginFirmaEnServidorId = pluginFirmaEnServidorId;
    }

    public PassarelaFullResults getPassarelaFullResults() {
        return passarelaFullResults;
    }

    public Map<String, PassarelaValidacioCompletaResponse> getValidacioResponseBySignID() {
        return validacioResponseBySignID;
    }

    public Long getPluginFirmaEnServidorId() {
        return pluginFirmaEnServidorId;
    }

}
