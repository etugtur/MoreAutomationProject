package GeneralFiles;

import ExtendReport.ExtendReportMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static BaseFiles.DriverManager.getDriver;


public class BasePage {

    ExtendReportMethods reporter=new ExtendReportMethods();

    public WebDriver driver= getDriver();


    public WebElement waitUntilVisibleByLocator(By locator) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(30L,TimeUnit.SECONDS).pollingEvery(100L,TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
            element = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        return element;
    }


    protected WebElement waitUntilClickableByListOfElement(WebElement webElement) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(30L,TimeUnit.SECONDS).pollingEvery(100L,TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
            element = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
           Assert.fail(e.getMessage());
        }

        return element;
    }


    protected WebElement waitUntilPresenceOfElement(By locator) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(100L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = (WebElement)wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {

            //Assert.fail(e.getMessage());
        }

        return element;
    }



    public boolean checkElementIsExist(By locator){

        try {

            WebElement element=driver.findElement(locator);

            if (element.isDisplayed() || element.isEnabled()){
                return true;
            }else {
                return false;
            }

        }catch (Exception e){

            return false;
        }
    }


    public void checkPageIsReady() {

        JavascriptExecutor js = (JavascriptExecutor)driver;

        if (js.executeScript("return document.readyState").toString().equals("complete")){
            System.out.println("Page Is loaded.");
            return;
        }

        for (int i=0; i<25; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {}
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }
        }
    }


    public void clickElement(By locator) {
        WebElement element = this.waitUntilVisibleByLocator(locator);
        this.clickElement(element);
    }

    protected void clickElement(WebElement element) {
        this.waitUntilClickableByListOfElement(element).click();
    }


    public List<WebElement> getWebElementsViaAttribute(By locator){

        List<WebElement> elements=null;

        try {

            elements= driver.findElements(locator);

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }

        return elements;
    }


    protected void sendKeysToElement(By locator, String text) {
        WebElement element = this.waitUntilVisibleByLocator(locator);
        element = this.waitUntilPresenceOfElement(locator);
        element.clear();
        element.sendKeys(new CharSequence[]{text});
    }


    public int getSizeOfList(By locator){

        try {

            List<WebElement> list= driver.findElements(locator);

            return list.size();

        }catch (Exception e){
            return 0;
        }
    }

    public void clickElementInList(By locator,int index){

        try {

            List<WebElement> list= driver.findElements(locator);
            clickElement(list.get(index));

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public void pressEnter(){

        try {
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ENTER).build().perform();

        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
    }

    public boolean waitElementWithThreadSleep(By locator, int second){

        boolean elementExist=false;
        try {
            elementExist= checkElementIsExist(locator);

            if (!elementExist){

                for (int i = 0; i < second; i++) {
                    Thread.sleep(1000);
                    elementExist=checkElementIsExist(locator);
                    if (elementExist){
                        elementExist=true;
                        break;
                    }
                }
            }

            Thread.sleep(1000);
        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }

        return elementExist;
    }

    public boolean waitElementWithThreadSleepViaItem(By locator, int second){

        int item=0;
        boolean elementExist=false;
        try {
            item=getSizeOfList(locator);

            if (item==0){

                for (int i = 0; i < second; i++) {
                    Thread.sleep(1000);
                    item=getSizeOfList(locator);
                    if (item!=0){
                        elementExist=true;
                        break;
                    }
                }
            }
            Thread.sleep(1000);
        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }

        return elementExist;
    }

    public String getData(By locator){

        try {
            WebElement element= driver.findElement(locator);
            return element.getText();
        }catch (Exception e){
            reporter.Report_Fail(e.getMessage());
        }
        return "";
    }













}
