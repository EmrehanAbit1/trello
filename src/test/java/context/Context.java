package context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Context {

    public static String CONFIG_PROPERTIES_PATH = "config.properties";

    public static String getRepositoryObject(String propName) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(CONFIG_PROPERTIES_PATH);
        properties.load(fileInputStream);
        return properties.getProperty(propName);
    }

    public static int petId;

    public static void setPetId(int newPetId) {
        petId = newPetId;
    }

    public static int getPetId() {
        return petId;
    }
}
