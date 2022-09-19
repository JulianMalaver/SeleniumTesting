package StepDefinitions;

import Functions.CreateDriver;
import Functions.SeleniumFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

    @Given("^I go to site(.*)")
    public void iGoToSite(String URL){
        log.info("Navigate to: "+URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }


    @Then("^I quit the application$")
    public void quitApp(){
        driver.quit();
    }

    @Then("^I close the window")
    public void closeApp(){
        driver.close();
    }


    @Then("^I load the DOM information (.*)$")
    public void iLoadTheDomInformation(String json) throws Exception{
        SeleniumFunctions.FileName = json;
        SeleniumFunctions.readJson();
        log.info("initialize file: "+json);

        JSONObject Entity = SeleniumFunctions.ReadEntity("Title");
        System.out.println(Entity);
    }

    @And("I do a click in element (.*)$")
    public void iDoAClickInElement(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).click();
        log.info("Click on element by " + element);

    }



}
