package org.fundaciobit.pluginsib.tipusdocumental.database;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.fundaciobit.pluginsib.core.v3.utils.AbstractPluginProperties;
import org.fundaciobit.pluginsib.tipusdocumental.api.ITipusDocumentalPlugin;
import org.fundaciobit.pluginsib.tipusdocumental.api.TipusDocumental;
import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author anadal
 * 17 feb 2026 11:52:23
 */
public class PluginTipusDocumentalDatabase extends AbstractPluginProperties implements ITipusDocumentalPlugin {

    public static final String PLUGIN_BBDD_BASE = PLUGIN_TIPUSDOCUMENTAL_BASE + "database.";

    private static final String DATABASE_JNDI = PLUGIN_BBDD_BASE + "jndi";

    private static final String DATABASE_CONNECTION_URL = PLUGIN_BBDD_BASE + "connection.url";
    private static final String DATABASE_CONNECTION_USERNAME = PLUGIN_BBDD_BASE + "connection.username";
    private static final String DATABASE_CONNECTION_PASSWORD = PLUGIN_BBDD_BASE + "connection.password";

    protected final Logger log = Logger.getLogger(getClass());

    public PluginTipusDocumentalDatabase() {
        super();
    }

    public PluginTipusDocumentalDatabase(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    public PluginTipusDocumentalDatabase(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public String getPluginName() {
        return "PluginTipusDocumentalPortaFIB";
    }

    @Override
    public List<TipusDocumental> getTipusDocumentals(String language) throws Exception {

        Connection c = getConnection();

        // id, name, base, description 

        String select = getPropertyRequired(PLUGIN_BBDD_BASE + "select." + language);

        // Ejecutar consulta
        PreparedStatement ps = c.prepareStatement(select);

        // El resultat és un conjunt de files amb camps id, name, base, description
        ResultSet rs = ps.executeQuery();

        List<TipusDocumental> tipus = new ArrayList<>();

        while (rs.next()) {
            TipusDocumental td = new TipusDocumental();
            td.setTipusDocumentalID(rs.getLong("id"));
            td.setName(rs.getString("name"));
            
            Long tmpBase = rs.getLong("base");
            if(rs.wasNull()) {
                td.setParentTipusDocumentalID(null);
            } else {
                td.setParentTipusDocumentalID(tmpBase);
            }
            td.setDescription(rs.getString("description"));
            tipus.add(td);
        }

        return tipus;

    }

    private static DataSource datasource = null;

    private static Boolean useJndi = null;

    protected Connection getConnection() throws Exception {

        if (useJndi == null) {

            String jndi = getProperty(DATABASE_JNDI);

            if (jndi == null || jndi.trim().isEmpty()) {
                // Usar comunicació JDBC

                getPropertyRequired(DATABASE_CONNECTION_URL);
                getPropertyRequired(DATABASE_CONNECTION_USERNAME);
                getPropertyRequired(DATABASE_CONNECTION_PASSWORD);

                useJndi = false;
            } else {
                // Usar Comunicacio JNDI
                useJndi = true;
            }

        }

        // es.caib.portafib.plugins.userinformation.database.

        Connection c;
        if (useJndi) {
            if (datasource == null) {
                String jndi = getPropertyRequired(DATABASE_JNDI);
                Context ctx = new InitialContext();
                datasource = (DataSource) ctx.lookup(jndi);
            }
            c = datasource.getConnection();
        } else {

            String url = getPropertyRequired(DATABASE_CONNECTION_URL);
            String username = getPropertyRequired(DATABASE_CONNECTION_USERNAME);
            String password = getPropertyRequired(DATABASE_CONNECTION_PASSWORD);

            c = DriverManager.getConnection(url, username, password);

        }

        return c;

    }

    protected void closeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
        }
    }

    protected void closePreparedStatement(PreparedStatement ps) {
        try {
            ps.close();
        } catch (Exception e) {
        }
    }

    protected void closeConnection(Connection c) {
        try {
            c.close();
        } catch (Exception e) {
        }
    }
}
