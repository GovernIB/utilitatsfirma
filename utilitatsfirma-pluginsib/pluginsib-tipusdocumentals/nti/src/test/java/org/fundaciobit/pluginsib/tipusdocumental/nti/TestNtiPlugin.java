package org.fundaciobit.pluginsib.tipusdocumental.nti;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;

/**
 * 
 * @author anadal (u80067)
 * 17 feb 2026 10:57:58
 */
public class TestNtiPlugin {

    public static void main(String[] args) {

        try {

            // Llegir propietats del plugin del fitxer de propietats test.properties en format UTF8

            Properties props = new Properties();
            InputStream input = new FileInputStream(new File("test.properties"));

            props.load(new InputStreamReader(input, "UTF-8"));

            PluginTipusDocumentalNormesTecniquesInteroperabilitat plugin = new PluginTipusDocumentalNormesTecniquesInteroperabilitat(
                    "es.caib.sample.", props);

            List<TipusDocumental> tipus = plugin.getTipusDocumentals(props.getProperty("language", "ca"));
            int count = 1;
            for (TipusDocumental td : tipus) {
                System.out.println(count + ".- Codi: " + td.getTipusDocumentalID() + ", Nom: " + td.getName()
                        + ", ParentID: " + td.getParentTipusDocumentalID());
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
