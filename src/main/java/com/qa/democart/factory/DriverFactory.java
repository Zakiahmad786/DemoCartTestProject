package com.qa.democart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    public static String highlight;
    private OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
    public WebDriver initDriver(Properties prop){

        String browserName=prop.getProperty("browser");
        highlight=prop.getProperty("highlight");
        optionsManager=new OptionsManager(prop);
        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
           // driver=new ChromeDriver(optionsManager.getChromeOptions());
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            //driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
            tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        }
        else if (browserName.equalsIgnoreCase("safari")){
            //driver=new SafariDriver();
            tlDriver.set(new SafariDriver());
        }
        else{
            System.out.println("---Please provide the correct browser---");
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();

        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public WebDriver getDriver(){
        return tlDriver.get();
    }
    public Properties initProp() throws Exception {
        FileInputStream file;
        String env=prop.getProperty("env");   // mvn clean install -Denv="qa"
        if(env==null){
            file = new FileInputStream("./src/test/resources/config/config.properties");
        }
        switch(env){
            case "qa":
                      file = new FileInputStream("./src/test/resources/config/qa_config.properties");
                      break;
            case "dev":
                file = new FileInputStream("./src/test/resources/config/dev_config.properties");
                break;
            case "stage":
                file = new FileInputStream("./src/test/resources/config/stage_config.properties");
                break;
            default:
                throw new Exception("NOENVFOUNDEXCEPTION");
        }
        try {
            file = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(file);
        }
        catch (FileNotFoundException fileException){
            fileException.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return prop;
    }
    public  String getScreenshot() throws IOException {
        File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path=System.getProperty("user.dir")+"/screenshot"+System.currentTimeMillis()+".png";
        File destination=new File(path);
        try{

            FileUtils.copyFile(srcFile,destination);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return path;
    }
}
