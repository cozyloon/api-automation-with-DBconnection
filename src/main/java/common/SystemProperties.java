package common;

import util.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
    private static Properties properties = null;

    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            LoggerUtil.logERROR(e.getMessage(), e);
        }
    }

    static String getProperty(String key) {
        if (properties == null)
            loadProperties();

        String p = System.getProperty(key);
        return p != null ? p : properties.getProperty(key);
    }
}
