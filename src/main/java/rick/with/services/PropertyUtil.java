package rick.with.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public class PropertyUtil {

    public static final String USE_REDUCED_COMBOS = "use.reduced.combos";
    public static final String LENGTH_GOAL = "length.goal";

    private static final String CONFIG_FILE_NAME = "config.properties";

    private static final Properties PROPERTIES = new Properties();

    public static int getIntProperty(String property) {
        return Integer.valueOf(PROPERTIES.getProperty(property));
    }

    public static boolean getBooleanProperty(String property) {
        return Boolean.valueOf(PROPERTIES.getProperty(property));
    }

    public static void loadProperties() {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        try (InputStream input = new FileInputStream(rootPath + CONFIG_FILE_NAME)) {

            // load a properties file
            PROPERTIES.load(input);

            // get the property value and print it out
            System.out.println("Launching with Configuration: " + PROPERTIES);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
