package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

/**
 * Test class to replicate issue 2321.
 *
 *
 * @author sambekar
 */
public class FlywayTest {
    /**
     * Main method.
     * 
     * @param args arguments
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {

        FlywayTest test = new FlywayTest();
        Properties properties = test.loadProperties();
        String jdbcUrl = properties.getProperty("oracle.url");
        String userName = properties.getProperty("oracle.user");
        String password = properties.getProperty("oracle.password");
        String licenseKey = properties.getProperty("flyway.license.key");
        ClassicConfiguration configuration = new ClassicConfiguration();
        Map< String , String > p = new HashMap<>();
        p.put("PARTITION.BY.CREATEDON", "PARTITION BY RANGE (CREATEDON) (PARTITION Y9999M99D99 VALUES LESS THAN (MAXVALUE))");
        p.put("PARTITION.BY.TIMESTAMP", "PARTITION BY RANGE (TIMESTAMP) (PARTITION Y9999M99D99 VALUES LESS THAN (MAXVALUE))");
        p.put("ORACLE.ONLY.START", " ");
        p.put("ORACLE.ONLY.END", " ");
        p.put("H2.ONLY.START", "/*");
        p.put("H2.ONLY.END", "*/");
        configuration.setLocationsAsStrings("db/migration");
        configuration.setPlaceholders(p);
        configuration.setDataSource(jdbcUrl, userName, password);
        configuration.setValidateOnMigrate(false);
        configuration.setOutOfOrder(true);
        configuration.setPlaceholderPrefix("/** ");
        configuration.setPlaceholderSuffix(" **/");
        configuration.setSqlMigrationPrefix("v");
        configuration.setTable("SCHEMA_VERSION");
        configuration.setSchemas("CONNECT_ENCODING", "CONNECT_RECEIVING", "CONNECT_TRACKING");
        configuration.setLicenseKey(licenseKey);
        configuration.setOracleSqlplus(true);
        configuration.setBaselineVersionAsString("4.4.0.00");
        configuration.setBaselineOnMigrate(true);
        Flyway f = new Flyway(configuration);
        f.repair();
        f.migrate();
    }

    /**
     * Loads property file.
     *
     * @return properties
     * @throws IOException IOException
     */
    public Properties loadProperties() throws IOException {
        Properties configProperties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        configProperties.load(inputStream);
        return configProperties;
    }
}
