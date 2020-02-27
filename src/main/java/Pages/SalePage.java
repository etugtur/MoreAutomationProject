package Pages;

import org.openqa.selenium.By;


public class SalePage extends MainPage {

    private static By tckn_TxtBx= By.xpath("//input[@formcontrolname='tckn']");
    private static By sonrakiAdim_MusteriSecimi_Btn= By.xpath("//button[@type='submit'][@id='next-step-1']");
    private static By sonrakiAdim_UrunSecimi_Btn= By.xpath("//button[@type='submit'][@id='next-step-2']");
    private static By sec_Btn=By.xpath("//button[starts-with(@id,'offer-select')]");
    private static By sepettenCikar_Btn= By.xpath("//button[@class='btn btn-danger btn-sm btn-round'][text()=' Sepetten Çıkar ']");
    private static By siparisiBaslat_Btn= By.xpath("//button/span[@class='mat-button-wrapper'][text()=' Siparişi Başlat ']");
    private static By siparisPopUpMessage_Txt= By.xpath("//simple-snack-bar[@class='mat-simple-snackbar ng-star-inserted']/span");

    public SalePage tcknGir(String identityNo){

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

    public SalePage tcknAra(){
        pressEnter();
        checkPageIsReady();
        return this;
    }

    public SalePage sonrakiAdimButonTikla_MusteriSecimi(){

        sonrakiAdimButonTikla(sonrakiAdim_MusteriSecimi_Btn);
        return this;
    }

    public SalePage sonrakiAdimButonTikla_UrunSecimi(){

        sonrakiAdimButonTikla(sonrakiAdim_UrunSecimi_Btn);
        return this;
    }

    private SalePage sonrakiAdimButonTikla(By locator){

        try {

            waitElementWithThreadSleep(locator,5);

            clickElement(locator);
            checkPageIsReady();
            reporter.Report_Info("Sonraki Adim butonuna tiklanmistir.");

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return this;
    }

    public SalePage paketSec(int item){

        try {

            waitElementWithThreadSleepViaItem(sec_Btn,5);
            int getNumberOfPacket= getSizeOfList(sec_Btn);

            if (!(getNumberOfPacket>=item)){
                reporter.Report_Fail("Secilmek istenen urun sayisi mevcut urun sayisindan fazladir!"
                        + " Mevcut urun sayisi: "+getNumberOfPacket);
            }else{

                for (int i = 0; i <item ; i++) {

                    clickElementInList(sec_Btn,0);
                    Thread.sleep(1500);
                }

            }

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return this;
    }

    public SalePage urunKontrol_GozdenGecir(int numberOfChosenProduct){

        try {

            waitElementWithThreadSleepViaItem(sepettenCikar_Btn,5);

            int getChosenProductItem= getSizeOfList(sepettenCikar_Btn);

            if (getChosenProductItem==numberOfChosenProduct){
                reporter.Report_Info("Secilen paketler ekran uzerine dogru yansimistir.");
            }else{
                reporter.Report_Fail("Secilen paketler ekrana eksik yansimistir!");
            }

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }

        return this;
    }

    public SalePage siparisiBaslatButonTikla(){

        try {
            waitElementWithThreadSleep(siparisiBaslat_Btn,5);
            clickElement(siparisiBaslat_Btn);
            reporter.Report_Info("Siparisi baslat butonuna tiklanmistir.");
            checkPageIsReady();

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return this;
    }

    public SalePage siparisMesajKontrol(){

        try {

            String message= "nolu sipariş oluşturulmuştur.";
            waitElementWithThreadSleep(siparisPopUpMessage_Txt,5);
            String getData= getData(siparisPopUpMessage_Txt);

            if (getData.contains(message)){
                reporter.Report_Pass("Siparis basarili bir sekilde olusmustur. Ekranda alinan Mesaj: "
                        +getData);
            }else{
                reporter.Report_Fail("Siparis olusturulurken hata alinmistir! ");
            }

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return this;
    }
}
