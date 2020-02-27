package ExtendReport;


import BaseFiles.TestBase;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExtendReportMethods  {

    private static ExtentReports reports;
    public static ExtentTest testInfo;
    ExtentHtmlReporter htmlReporter;

   static WebDriver driver= TestBase.getDriver();

    public static String getscreenShot(){

        TakesScreenshot ts= (TakesScreenshot) TestBase.getDriver();

        File src= ts.getScreenshotAs(OutputType.FILE);
        String path= System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
        File destination= new File(path);

        try {
            FileUtils.copyFile(src,destination);

        }catch (IOException e){

            Assert.fail("Capture Failed : "+e.getMessage());

        }

        return path;
    }



    public static MediaEntityModelProvider TakeScreenShotForReport(){

        try {

            ITestResult result = null;
            String temp= getscreenShot();
            testInfo.log(Status.FAIL,"The Test Failure : "+result.getThrowable());

            return MediaEntityBuilder.createScreenCaptureFromPath(temp).build();

        }catch (Exception e){

        }
        return null;
    }



    public void Report_Pass(String description){

        testInfo.log(Status.PASS,description);
        Reporter.log(description);
    }

    public  void Report_Info(String description){

        testInfo.log(Status.INFO,description);
        Reporter.log(description);
    }

    public  void Report_Skip(String description){

        testInfo.log(Status.SKIP,description);
        Reporter.log(description);
    }

    public  void Report_Warning(String description){

        testInfo.log(Status.WARNING,description);
        Reporter.log(description);
    }

    public  void Report_Fail(String description){

        testInfo.log(Status.FAIL,description,TakeScreenShotForReport());

        Assert.fail(description);
    }


    public void ReportMethod(){

        htmlReporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/test-output/AutomationReport"+".html"));htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/Extent-Config.xml"));
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");


        if (reports == null){

            reports= new ExtentReports();

            reports.setSystemInfo("Enviroment","QA");
            reports.setSystemInfo("User Name", "Metin Hasdemir");
            reports.attachReporter(htmlReporter);
        }



    }

    public void CleanUp(){

        reports.flush();
    }


    public void Register(Method method){

        String testName= method.getName();
        testInfo= reports.createTest(testName);

    }


    public void CaptureStatues(ITestResult result) throws IOException {

        if (result.getStatus()==ITestResult.SUCCESS){
            testInfo.log(Status.PASS,"The Test Method named as : "+result.getName() + " is passed.");

        }else if (result.getStatus()==ITestResult.FAILURE){

            String temp= getscreenShot();
            testInfo.log(Status.FAIL,"The Test Failure : "+result.getThrowable());
            testInfo.fail("The Failed Test Screenshot :",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        }else if (result.getStatus()==ITestResult.SKIP){

            testInfo.log(Status.SKIP,"The Test Failure : "+result.getName() + " is skip! ");
        }

    }




}
