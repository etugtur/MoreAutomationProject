package BaseFiles;

import GeneralFiles.Data;
import GeneralFiles.OsCheck;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Chrome implements BaseBrowser {

    String chromeDriverPath="";

    public Chrome() {

    }

    public String getChromeDriverPath(){

        try {

            if (OsCheck.getOperatingSystemType().equals("MacOS")){
                chromeDriverPath= Data.driverPath+"chromedriver";
            }else if(OsCheck.getOperatingSystemType().equals("Windows")){
                chromeDriverPath= Data.driverPath+"chromedriver.exe";
            }else{
                chromeDriverPath= Data.driverPath+"chromedriver_linux";
            }

        }catch (Exception e){

            e.printStackTrace();
        }

        return chromeDriverPath;
    }

    public WebDriver runLocal() {

        System.setProperty("webdriver.chrome.driver",getChromeDriverPath());
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments(new String[]{"--disable-notifications"});
        chromeOptions.addArguments(new String[]{"--disable-cache"});

        if (TestBase.HEADLESSBROWSER.equalsIgnoreCase("true")){
            chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
        return new ChromeDriver(capabilities);
    }

    public WebDriver runRemote(String gridUrl) {

        System.setProperty("webdriver.chrome.driver",getChromeDriverPath());
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments(new String[]{"--disable-notifications"});
        chromeOptions.addArguments(new String[]{"--disable-cache"});

        if (TestBase.HEADLESSBROWSER.equalsIgnoreCase("true")){
            chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);

        try {
            WebDriver webDriver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            return webDriver;
        } catch (MalformedURLException var5) {
            throw new TestException(var5);
        }
    }


}
