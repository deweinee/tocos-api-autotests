package org.tocos.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApiConfig.class.getClassLoader().getResourceAsStream("api-config.properties")) {
            if (input == null) {
                throw new IOException("Sorry, unable to find api-config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("base.uri");
    }
}
