package utils;

import aquality.selenium.core.logging.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtils {
    public static Properties loadProperties(String path) {
        Properties properties = null;
        try (FileInputStream fis = new FileInputStream(path)) {
            Logger.getInstance().debug("Loading configuration file: " + path);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            Logger.getInstance().debug("Properties can't be loaded", e);
        }
        return properties;
    }
}
