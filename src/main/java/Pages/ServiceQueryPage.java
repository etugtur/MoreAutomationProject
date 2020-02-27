package Pages;

import org.openqa.selenium.By;

public class ServiceQueryPage extends MainPage{


    private static By tckn_TxtBx= By.xpath("//input[@name='tckn']");
    private static By urunler_Txt= By.xpath("//div[@class='mat-card-header-text']/mat-card-title[@class='mat-card-title']");


    public ServiceQueryPage tcknGir(String identityNo){

        try {

            clickElement(tckn_TxtBx);
            Thread.sleep(1000);
            sendKeysToElement(tckn_TxtBx,identityNo);
            reporter.Report_Info(identityNo+" TCKN olarak alana girilmistir.");

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return this;
    }

    public ServiceQueryPage tcknAra(){
        SalePage salePage=new SalePage();
        salePage.tcknAra();
        return this;
    }

    public ServiceQueryPage urunKontrol(){
        try {

            boolean elementExist= waitElementWithThreadSleepViaItem(urunler_Txt,5);

            if (elementExist){
                reporter.Report_Pass("Musteriye ait hizmetler ekranda basarili bir sekilde listelenmistir.");
            }else{
                reporter.Report_Fail("Musteriye ait hizmetler ekranda tespit edilememistir!");
            }

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return this;
    }



}
