package Functions;

import StepDefinitions.Hooks;
import jline.internal.Log;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.python.antlr.ast.Str;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SeleniumFunctions {

    //Creating the object of webDriver?
    WebDriver driver;

    /**Key**/

    public static Map<String, String> ScenaryData = new HashMap<>();
    public static String Environment = "";


    /**Log Attribute*/
    public SeleniumFunctions(){
        driver = Hooks.driver;
    }
    private static Logger log = Logger.getLogger(SeleniumFunctions.class);

    /** Test Properties Configuration**/
    public static Properties prop = new Properties();
    public static InputStream in = SeleniumFunctions.class.getResourceAsStream("../test.properties");

    public static String GetFieldBy="";
    public static String ValueToFind="";
    /** Page path**/
    public static String PagesFilesPath = "src/test/resources/Pages/";
    public static String FileName = "";

    //Search for the JSON file
    public static Object readJson() throws Exception{
        FileReader reader = new FileReader(PagesFilesPath + FileName);
        try{
            if(reader != null){
                JSONParser jsonParser = new JSONParser();
                return jsonParser.parse(reader);
            }else{
                return null;
            }
        }catch (FileNotFoundException | NullPointerException e){
            log.error("ReadEntity: File doesn't exist " + FileName);
            throw new IllegalStateException("ReadEntity: File Doesn't exist " +FileName, e);
        }
    }

    public static JSONObject ReadEntity(String element) throws Exception{
        JSONObject Entity = null;
        //We call the last function
        JSONObject jsonObject = (JSONObject) readJson();
        Entity = (JSONObject) jsonObject.get(element);
        log.info(Entity.toJSONString());
        return Entity;
    }


    public static By getCompleteElement(String element) throws Exception{
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if("className".equalsIgnoreCase(GetFieldBy)){
            result = By.className(ValueToFind);
        }else if("cssSelector".equalsIgnoreCase(GetFieldBy)){
            result = By.cssSelector(ValueToFind);
        }else if("id".equalsIgnoreCase(GetFieldBy)){
            result = By.id(ValueToFind);
        }else if("linkText".equalsIgnoreCase(GetFieldBy)){
            result = By.linkText(ValueToFind);
        }else if("name".equalsIgnoreCase(GetFieldBy)){
            result = By.name(ValueToFind);
        }else if("link".equalsIgnoreCase(GetFieldBy)){
            result = By.partialLinkText(ValueToFind);
        }else if("tagName".equalsIgnoreCase(GetFieldBy)){
            result = By.tagName(ValueToFind);
        }else if("XPath".equalsIgnoreCase(GetFieldBy)) {
            result = By.xpath(ValueToFind);
        }
        return result;
    }

    /**Read the properties of test.properties **/
    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);
    }

    public void SaveInScenario(String key, String text){
        if(!this.ScenaryData.containsKey(key)){
            this.ScenaryData.put(key, text);
            log.info(String.format("Save as Scenario Context Key: x%s with value %s ",key,text));
            System.out.println(String.format("Save as Scenario Context Key: x%s with value %s ",key,text));
        }else{
            this.ScenaryData.replace(key, text);
            log.info(String.format("Update Scenario Context key: %s with value: %s",key,text));
            System.out.println(String.format("Update Scenario Context key: %s with value: %s",key,text));
        }
    }

    public void RetrieveTestData(String parameter) throws IOException{
        Environment = readProperties("Environment");
        try{
            SaveInScenario(parameter, readProperties(parameter + "." + Environment));
            System.out.println("This is the value of Scenario" + parameter + ":" + this.ScenaryData.get(parameter));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**Select**/

    public void selectOptionDropDownByText(String element, String option)throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        Log.info(String.format("Waiting element: %s", element));

        Select opt = new Select(driver.findElement(SeleniumElement));
        log.info("Select option: " + option + "by text");
        opt.selectByVisibleText(option);

    }

    public ISelect selectOption(String element) throws Exception{
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        log.info(String.format("Waiting Element: %s", element));
        Select opt = new Select(driver.findElement(SeleniumElement));
        return opt;
    }
}
