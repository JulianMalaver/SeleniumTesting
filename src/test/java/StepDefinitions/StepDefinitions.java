package StepDefinitions;

import Functions.CreateDriver;
import cucumber.api.java.en.Given;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StepDefinitions {

    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");
    private static String MainAppUrlBase;

    WebDriver driver;

    Logger log = Logger.getLogger(StepDefinitions.class);

    public StepDefinitions(){
        driver = Hooks.driver;
    }

    @Given("^I am in the App main site")
    public void iAmInTheAppMainSite() throws IOException{
        prop.load(in);
        String url = prop.getProperty("MainAppUrlBase");
        log.info("Navigate to " + url);
    }

}
