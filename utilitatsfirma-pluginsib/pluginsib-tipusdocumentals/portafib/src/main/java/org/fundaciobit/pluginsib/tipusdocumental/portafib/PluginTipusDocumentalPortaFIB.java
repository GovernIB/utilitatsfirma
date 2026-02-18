package org.fundaciobit.pluginsib.tipusdocumental.portafib;

import java.util.List;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.v3.utils.AbstractPluginProperties;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import com.google.gson.Gson;

/**
 *  
 * @author anadal
 * 17 feb 2026 10:53:12
 */
public class PluginTipusDocumentalPortaFIB extends AbstractPluginProperties implements ITipusDocumentalPlugin {

    public static final String PLUGIN_PORTAFIB_BASE = PLUGIN_TIPUSDOCUMENTAL_BASE + "portafib.";

    protected final Logger log = Logger.getLogger(getClass());

    public PluginTipusDocumentalPortaFIB() {
        super();
    }

    public PluginTipusDocumentalPortaFIB(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    public PluginTipusDocumentalPortaFIB(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public String getPluginName() {
        return "PluginTipusDocumentalPortaFIB";
    }

    @Override
    public List<TipusDocumental> getTipusDocumentals(String language) throws Exception {

        final String baseUrl = getPropertyRequired(PLUGIN_PORTAFIB_BASE + "url");
        final String username = getPropertyRequired(PLUGIN_PORTAFIB_BASE + "username");
        final String password = getPropertyRequired(PLUGIN_PORTAFIB_BASE + "password");

        DocumentaryType[] types = getDocumentaryTypes(baseUrl, username, password, language);

        List<TipusDocumental> tipusDocumentals = new ArrayList<>();

        for (DocumentaryType type : types) {

            if (type.getDocumentType() < 0) {
                continue; // Saltar tipus negatius en català
            }

            TipusDocumental td = new TipusDocumental();

            td.setDescription(type.getName());
            td.setName(type.getName());
            td.setParentTipusDocumentalID(type.getDocumentTypeBase() == null ? null : type.getDocumentTypeBase());
            td.setTipusDocumentalID(type.getDocumentType());

            tipusDocumentals.add(td);
        }

        // Ordenar llista pel camp tipusdocumentalid
        tipusDocumentals.sort((td1, td2) -> Long.compare(td1.getTipusDocumentalID(), td2.getTipusDocumentalID()));

        return tipusDocumentals;

    }

    /**
     * Crida al servei REST de PortaFIB per obtenir els tipus documentals
     * @param baseUrl
     * @param username
     * @param password
     * @param language
     * @return
     * @throws Exception
     */
    public DocumentaryType[] getDocumentaryTypes(String baseUrl, String username, String password, String language)
            throws Exception {
        String urlString = baseUrl + "/signatureonserver/v1/getDocumentaryTypes?language=" + language;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        // Autenticación básica
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP error code: " + responseCode);
        }

        // Leer respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        // Deserializar JSON a array de DocumentaryType con Gson
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), DocumentaryType[].class);
    }

}
