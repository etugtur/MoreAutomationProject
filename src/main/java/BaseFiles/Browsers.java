package BaseFiles;

import GeneralFiles.OsCheck;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class Browsers {

    public static void maximizeScreen(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Point position = new org.openqa.selenium.Point(0, 0);
        driver.manage().window().setPosition(position);
        Dimension maximizedScreenSize =
                new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(maximizedScreenSize);
    }


    public static WebDriver prepareDriver() {
        BaseBrowser browser = Browser.prepareBrowser();
        WebDriver driver;
        if (TestBase.REMOTE != null && !TestBase.REMOTE.equals("false")) {
            driver = browser.runRemote(getGridUrl());
        } else {
            driver = browser.runLocal();
        }

        if (OsCheck.getOperatingSystemType().equals("MacOS")) {
            maximizeScreen(driver);
        } else{
            driver.manage().window().maximize();
        }

        return driver;
    }

    private static String getGridUrl() {

        return TestBase.GRIDURL+"/wd/hub";
    }

}
