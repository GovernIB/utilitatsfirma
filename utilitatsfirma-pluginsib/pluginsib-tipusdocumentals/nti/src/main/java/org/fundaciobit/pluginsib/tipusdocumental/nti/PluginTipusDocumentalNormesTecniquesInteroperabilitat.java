package org.fundaciobit.pluginsib.tipusdocumental.nti;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.v3.utils.AbstractPluginProperties;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;
import org.jboss.logging.Logger;

/**
 *  
 * @author anadal (u80067)
 * 17 feb 2026 10:53:12
 */
public class PluginTipusDocumentalNormesTecniquesInteroperabilitat extends AbstractPluginProperties
        implements ITipusDocumentalPlugin {

    public static final List<TipusDocumental> es = new ArrayList<TipusDocumental>();

    public static final List<TipusDocumental> ca = new ArrayList<TipusDocumental>();

    static {

        ca.add(new TipusDocumental(1L, null, "TD01 - TipusResolució", "TD01 - TipusResolució"));
        es.add(new TipusDocumental(1L, null, "TD01 - TipusResolución", "TD01 - TipusResolución"));
        ca.add(new TipusDocumental(2L, null, "TD02 - Acord", "TD02 - Acord"));
        es.add(new TipusDocumental(2L, null, "TD02 - Acord", "TD02 - Acord"));
        ca.add(new TipusDocumental(3L, null, "TD03 - Contracte", "TD03 - Contracte"));
        es.add(new TipusDocumental(3L, null, "TD03 - Contrato", "TD03 - Contrato"));
        ca.add(new TipusDocumental(4L, null, "TD04 - Conveni", "TD04 - Conveni"));
        es.add(new TipusDocumental(4L, null, "TD04 - Convenio", "TD04 - Convenio"));
        ca.add(new TipusDocumental(5L, null, "TD05 - Declaració", "TD05 - Declaració"));
        es.add(new TipusDocumental(5L, null, "TD05 - Declaración", "TD05 - Declaración"));
        ca.add(new TipusDocumental(6L, null, "TD06 - Comunicació", "TD06 - Comunicació"));
        es.add(new TipusDocumental(6L, null, "TD06 - Comunicación", "TD06 - Comunicación"));
        ca.add(new TipusDocumental(7L, null, "TD07 - Notificació", "TD07 - Notificació"));
        es.add(new TipusDocumental(7L, null, "TD07 - Notificación", "TD07 - Notificación"));
        ca.add(new TipusDocumental(8L, null, "TD08 - Publicació", "TD08 - Publicació"));
        es.add(new TipusDocumental(8L, null, "TD08 - Publicación", "TD08 - Publicación"));
        ca.add(new TipusDocumental(9L, null, "TD09 - Justificant de Recepció", "TD09 - Justificant de Recepció"));
        es.add(new TipusDocumental(9L, null, "TD09 - Justificante de Recepción", "TD09 - Justificante de Recepción"));
        ca.add(new TipusDocumental(10L, null, "TD10 - Acta", "TD10 - Acta"));
        es.add(new TipusDocumental(10L, null, "TD10 - Acta", "TD10 - Acta"));
        ca.add(new TipusDocumental(11L, null, "TD11 - Certificat", "TD11 - Certificat"));
        es.add(new TipusDocumental(11L, null, "TD11 - Certificado", "TD11 - Certificado"));
        ca.add(new TipusDocumental(12L, null, "TD12 - Diligencia", "TD12 - Diligencia"));
        es.add(new TipusDocumental(12L, null, "TD12 - Diligencia", "TD12 - Diligencia"));
        ca.add(new TipusDocumental(13L, null, "TD13 - Informe", "TD13 - Informe"));
        es.add(new TipusDocumental(13L, null, "TD13 - Informe", "TD13 - Informe"));
        ca.add(new TipusDocumental(14L, null, "TD14 - Sol·licitud", "TD14 - Sol·licitud"));
        es.add(new TipusDocumental(14L, null, "TD14 - Solicitud", "TD14 - Solicitud"));
        ca.add(new TipusDocumental(15L, null, "TD15 - Denuncia", "TD15 - Denuncia"));
        es.add(new TipusDocumental(15L, null, "TD15 - Denuncia", "TD15 - Denuncia"));
        ca.add(new TipusDocumental(16L, null, "TD16 - Al·legació", "TD16 - Al·legació"));
        es.add(new TipusDocumental(16L, null, "TD16 - Alegación", "TD16 - Alegación"));
        ca.add(new TipusDocumental(17L, null, "TD17 - Recurs", "TD17 - Recurs"));
        es.add(new TipusDocumental(17L, null, "TD17 - Recurso", "TD17 - Recurso"));
        ca.add(new TipusDocumental(18L, null, "TD18 - Comunicació Ciutadà", "TD18 - Comunicació Ciutadà"));
        es.add(new TipusDocumental(18L, null, "TD18 - Comunicación Ciudadano", "TD18 - Comunicación Ciudadano"));
        ca.add(new TipusDocumental(19L, null, "TD19 - Factura", "TD19 - Factura"));
        es.add(new TipusDocumental(19L, null, "TD19 - Factura", "TD19 - Factura"));
        ca.add(new TipusDocumental(20L, null, "TD20 - Altres Encautats", "TD20 - Altres Encautats"));
        es.add(new TipusDocumental(20L, null, "TD20 - Otros Encautados", "TD20 - Otros Encautados"));
        ca.add(new TipusDocumental(99L, null, "TD99 - Altres", "TD99 - Altres"));
        es.add(new TipusDocumental(99L, null, "TD99 - Otros", "TD99 - Otros"));

    }

    public static final String PLUGIN_PORTAFIB_BASE = PLUGIN_TIPUSDOCUMENTAL_BASE + "nti.";

    protected final Logger log = Logger.getLogger(getClass());

    public PluginTipusDocumentalNormesTecniquesInteroperabilitat() {
        super();
    }

    public PluginTipusDocumentalNormesTecniquesInteroperabilitat(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    public PluginTipusDocumentalNormesTecniquesInteroperabilitat(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public String getPluginName() {
        return "PluginTipusDocumentalPortaFIB";
    }

    @Override
    public List<TipusDocumental> getTipusDocumentals(String language) throws Exception {
        if ("es".equalsIgnoreCase(language)) {
            return new ArrayList<TipusDocumental>(es);
        } else {
            return new ArrayList<TipusDocumental>(ca);
        }
    }

}
