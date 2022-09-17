package Functions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CreateDriver {
    public static String browser;
    public static String os;
    public static String logLevel;

    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static Logger log = Logger.getLogger(CreateDriver.class);

    private CreateDriver() throws IOException {
        CreateDriver.initConfig();
    }

    public static WebDriver initConfig() throws IOException {
        WebDriver driver;

        try{
            log.info("************************************************************************************");
            log.info("[ POM Configuration ] - Reading basic bases of ../test.properties ");
            prop.load(in);
            browser = prop.getProperty("browser");
            os = prop.getProperty("os");
            logLevel = prop.getProperty("logLevel");
        } catch (IOException e) {
            log.error("initConfig error",e);
        }
        /****** POM Information ******/
        log.info("[POM Configuration] - OS: " + os + " | Browser: " + browser + " |" );
        log.info("[POM Configuration] - Logger Level: " + logLevel);

        /** Call the driver **/
        driver = WebDriverFactory.createNewDriver(browser,os);

        return driver;
    }
}
