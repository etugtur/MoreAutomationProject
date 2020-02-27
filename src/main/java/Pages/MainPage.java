package Pages;

import BaseFiles.DriverManager;
import BaseFiles.TestBase;
import ExtendReport.ExtendReportMethods;
import GeneralFiles.BasePage;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;


public class MainPage extends BasePage {

    private static By satisMenu_Btn= By.xpath("//a/p[text()='Satış']");
    private static By hizmetSorgulamaMenu_Btn= By.xpath("//a/p[text()='Hizmet Sorgulama']");

    ExtendReportMethods reporter=new ExtendReportMethods();

    public MainPage openMainPage(){

        try {
            DriverManager.getDriver().navigate().to(TestBase.BASEURL);
            DriverManager.getDriver().manage().timeouts().pageLoadTimeout(120L, TimeUnit.SECONDS);
            reporter.Report_Info("Web sayfasi basarili bir sekilde acilmistir. URL: "+ TestBase.BASEURL);

        }catch (Exception e){
            reporter.Report_Fail("Sayfa acilamamistir! Detay: "+e.getMessage());
        }
        return this;
    }


    public MainPage satisMenuSec(){

        try {
            waitElementWithThreadSleep(satisMenu_Btn,5);
            clickElement(satisMenu_Btn);
            reporter.Report_Info("Menuden Satis tabi secilmistir.");
        }catch (Exception e){
            reporter.Report_Fail("Menu Satis tabinda hata alinmistir! Detay: "+e.getMessage());
        }

        return this;
    }

    public MainPage hizmetSorgulamaMenuSec(){

        try {
            waitElementWithThreadSleep(hizmetSorgulamaMenu_Btn,5);
            clickElement(hizmetSorgulamaMenu_Btn);
            reporter.Report_Info("Servis Sorgulama tabi secilmistir.");
        }catch (Exception e){
            reporter.Report_Fail("Servis Sorgulama tabinda hata alinmistir! Detay: "+e.getMessage());
        }

        return this;
    }


}