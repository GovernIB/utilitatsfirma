package org.fundaciobit.pluginsib.tipusdocumental.bbdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;
import org.fundaciobit.pluginsib.tipusdocumental.database.PluginTipusDocumentalDatabase;

/**
 * 
 * @author anadal
 * 17 feb 2026 10:57:58
 */
public class TestDatabasePlugin {

    public static void main(String[] args) {

        try {

            //generateSql();

            // Llegir propietats del plugin del fitxer de propietats test.properties en format UTF8

            Properties props = new Properties();
            InputStream input = new FileInputStream(new File("test.properties"));

            props.load(new InputStreamReader(input, "UTF-8"));

            PluginTipusDocumentalDatabase plugin = new PluginTipusDocumentalDatabase("es.caib.sample.", props);

            List<TipusDocumental> tipus = plugin.getTipusDocumentals(props.getProperty("language", "ca"));
            int count = 1;
            for (TipusDocumental td : tipus) {

                System.out.println(
                        count + ". " + td.getTipusDocumentalID() + " - " + td.getName() + " - " + td.getDescription());

                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected static void generateSql() {
        String info_ca = "RESOLUCIO	1	TipusResolució	TD01\r\n" + "ACORD	2	Acord	TD02\r\n"
                + "CONTRACTE	3	Contracte	TD03\r\n" + "CONVENI	4	Conveni	TD04\r\n"
                + "DECLARACIO	5	Declaració	TD05\r\n" + "COMUNICACIO	6	Comunicació	TD06\r\n"
                + "NOTIFICACIO	7	Notificació	TD07\r\n" + "PUBLICACIO	8	Publicació	TD08\r\n"
                + "JUSTIFICANT_RECEPCIO	9	Justificant de Recepció	TD09\r\n" + "ACTA	10	Acta	TD10\r\n"
                + "CERTIFICAT	11	Certificat	TD11\r\n" + "DILIGENCIA	12	Diligencia	TD12\r\n"
                + "INFORME	13	Informe	TD13\r\n" + "SOLICITUD	14	Sol·licitud	TD14\r\n"
                + "DENUNCIA	15	Denuncia	TD15\r\n" + "ALEGACIO	16	Al·legació	TD16\r\n"
                + "RECURS	17	Recurs	TD17\r\n" + "COMUNICACIO_CIUTADA	18	Comunicació Ciutadà	TD18\r\n"
                + "FACTURA	19	Factura	TD19\r\n" + "ALTRES_INCAUTATS	20	Altres Encautats	TD20\r\n"
                + "ALTRES	99	Altres	TD99";

        String info_es = "RESOLUCIO	1	TipusResolución	TD01\r\n" + "ACORD	2	Acord	TD02\r\n"
                + "CONTRACTE	3	Contrato	TD03\r\n" + "CONVENI	4	Convenio	TD04\r\n"
                + "DECLARACIO	5	Declaración	TD05\r\n" + "COMUNICACIO	6	Comunicación	TD06\r\n"
                + "NOTIFICACIO	7	Notificación	TD07\r\n" + "PUBLICACIO	8	Publicación	TD08\r\n"
                + "JUSTIFICANT_RECEPCIO	9	Justificante de Recepción	TD09\r\n" + "ACTA	10	Acta	TD10\r\n"
                + "CERTIFICAT	11	Certificado	TD11\r\n" + "DILIGENCIA	12	Diligencia	TD12\r\n"
                + "INFORME	13	Informe	TD13\r\n" + "SOLICITUD	14	Solicitud	TD14\r\n"
                + "DENUNCIA	15	Denuncia	TD15\r\n" + "ALEGACIO	16	Alegación	TD16\r\n"
                + "RECURS	17	Recurso	TD17\r\n" + "COMUNICACIO_CIUTADA	18	Comunicación Ciudadano	TD18\r\n"
                + "FACTURA	19	Factura	TD19\r\n" + "ALTRES_INCAUTATS	20	Otros Encautados	TD20\r\n"
                + "ALTRES	99	Otros	TD99";

        String[] tipus_ca = info_ca.split("\r\n");

        String[] tipus_es = info_es.split("\r\n");

        for (int i = 0; i < tipus_es.length; i++) {

            String[] dades_ca = tipus_ca[i].split("\t");
            String[] dades_es = tipus_es[i].split("\t");

            String tipusdocumentalid = dades_ca[1];
            //String paretipusdocumentalid = null;
            String nomcatala = dades_ca[3] + " - " + dades_ca[2];
            String nomcastella = dades_es[3] + " - " + dades_es[2];

            String descripciocatala = dades_ca[3] + " - " + dades_ca[2];
            String descripciocastella = dades_es[3] + " - " + dades_es[2];

            String sql = "INSERT INTO suf_tipusdocumental("
                    + "tipusdocumentalid, paretipusdocumentalid, nomcatala, nomcastella, descripciocatala, descripciocastella) "
                    + " VALUES ('" + tipusdocumentalid + "', null, '" + nomcatala + "', '" + nomcastella + "', '"
                    + descripciocatala + "', '" + descripciocastella + "');";
                
            System.out.println(sql);
            
        
        }
    }

}
