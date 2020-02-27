package BaseFiles;

import ExtendReport.ExtendReportMethods;
import GeneralFiles.PropertiesFileReader;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;


public class TestBase {

    ExtendReportMethods extendReportMethods= new ExtendReportMethods();

    public static String BASEURL=null;
    public static String BROWSER=null;
    public static String REMOTE= null;
    public static String GRIDURL="";
    public static int RETRY=0;
    public static String HEADLESSBROWSER=null;


    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }


    public void initializeConfig(){

        try {

            PropertiesFileReader obj= new PropertiesFileReader();
            Properties properties= obj.getProperty();

            BROWSER=properties.getProperty("browser");
            BASEURL=properties.getProperty("baseUrl");
            REMOTE= properties.getProperty("remote");
            GRIDURL=properties.getProperty("gridUrl");
            RETRY=Integer.parseInt((properties.getProperty("reTry")).trim());
            HEADLESSBROWSER=properties.getProperty("headlessBrowser");

        }catch (Exception e){

            Assert.assertTrue(false,"main/resouces/config.properties dosyasi icerisindekileri kontrol ediniz.");
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context){
        initializeConfig();

        Iterator var8 = context.getSuite().getAllMethods().iterator();

        while(var8.hasNext()) {
            ITestNGMethod method = (ITestNGMethod)var8.next();
            method.setRetryAnalyzer(new ReTryTestCase());
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method){

        extendReportMethods.Register(method);

        try {
            DriverManager.setDriver(Browsers.prepareDriver());
            driver= DriverManager.getDriver();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result)  {

        try {
            extendReportMethods.CaptureStatues(result);

            if (DriverManager.getDriver() != null && !DriverManager.getDriver().toString().contains("null")) {
                DriverManager.getDriver().quit();
            }

            IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer();
            if (retry != null) {
                if (result.getStatus() == 1) {
                    result.getTestContext().getSkippedTests().removeResult(result.getMethod());
                    result.getTestContext().getFailedTests().removeResult(result.getMethod());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeTest
    public void beforeTest(){
        extendReportMethods.ReportMethod();
    }

    @AfterTest
    public void afterTest(){
        extendReportMethods.CleanUp();
    }





}
