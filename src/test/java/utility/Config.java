package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    protected static InputStream input = null;

    static Properties prop = new Properties();

    private static Config instance = null;

    public final static String CONFIG_PROPERTIES_PATH = "config.properties";

    //Path to chrome driver for ubuntu to run in GitHub Actions
    public final static String CHROME_DRIVER_PATH_UBUNTU = "/usr/bin/chromedriver";
    //Path to test reports
    public final static String SCREENSHOTS_ON_FAILURE_PATH = "/target/TestResults/";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void getProperties(String path) throws IOException {
        input = new FileInputStream(path);
        prop = new Properties();
        prop.load(input);
    }

    public Config() {
        try {
            getProperties(CONFIG_PROPERTIES_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return prop.getProperty("url");
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getBaseUri() {
        return prop.getProperty("baseURI");
    }

    public String getPetAdditionJsonPath() {
        return prop.getProperty("addPetJsonPath");
    }

    public String getPetUpdateJsonPath() {
        return prop.getProperty("updatePetJson");
    }

    public String getUserName() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }
}
