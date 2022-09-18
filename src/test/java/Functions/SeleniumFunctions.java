package Functions;

import StepDefinitions.Hooks;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class SeleniumFunctions {

    //Creating the object of webDriver?
    WebDriver driver;

    public SeleniumFunctions(){
        driver = Hooks.driver;
    }

    private static Logger log = Logger.getLogger(SeleniumFunctions.class);
    public static Properties pro = new Properties();
    public static InputStream in = SeleniumFunctions.class.getResourceAsStream("../test.properties");

    public static String PagesFilesPath = "src/test/resources/Pages";
    public static String FileName = "";
    public static String GetFieldBy="";
    public static String ValueToFind="";

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
            log.error("ReadEntity: No existe el Archivo " + FileName);
            throw new IllegalStateException("ReadEntity: No existe el Archivo " +FileName, e);
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
}
