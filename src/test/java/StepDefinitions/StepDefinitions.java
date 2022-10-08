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

    SeleniumFunctions functions = new SeleniumFunctions();

    private static Properties prop = new Properties();
    private static String MainAppUrlBase;

    WebDriver driver;

    Logger log = Logger.getLogger(StepDefinitions.class);

    public StepDefinitions(){
        driver = Hooks.driver;
    }

    @Given("^I am in the App main site")
    public void iAmInTheAppMainSite() throws IOException{
        String url = functions.readProperties("MainAppUrlBase");
        log.info("Go to: "+url);
        log.info("Navigate to " + url);
        driver.get(url);
    }

    @Given("^I go to site (.*)")
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


    @Then("^I load the DOM information (.*)")
    public void iLoadTheDomInformation(String json) throws Exception{
        SeleniumFunctions.FileName = json;
        SeleniumFunctions.readJson();
        log.info("initialize file: "+json);

    }

    @And("I do a click in element (.*)$")
    public void iDoAClickInElement(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).click();
        log.info("Click on element by " + element);

    }

    @And("^I set (.*) with text (.*)")
    public void iSetWithText(String element, String text)throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        driver.findElement(SeleniumElement).sendKeys(text);
        log.info("Send text" + text + "to element " + element);
    }



}
