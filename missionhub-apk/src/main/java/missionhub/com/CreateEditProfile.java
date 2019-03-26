package missionhub.com;
import missionhub.com.Helper.DataProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static missionhub.com.Helper.Commons.*;
import static missionhub.com.Helper.Config.*;
import static missionhub.com.Helper.Utils.*;

public class CreateEditProfile {
    private AndroidDriver<WebElement> driver;
    @Test(dataProvider = "devicesList", dataProviderClass = DataProvider.class)
    public void addLeadAndEditProfileTest(String deviceName) {
      driver = getDriver(deviceName);
      try {
        getSessionDetailsLink(driver);
        login(driver, getAppUserName(), getAppPassword());
        By by = By.xpath("//android.widget.TextView[@text='MATT WATTS']");
        scroll(driver, by, DIRECTION.DOWN, 1, LIMITED_SWIPE_NUMBER);
        if (driver.findElements(by).size() < 1) {
          scroll(driver, by, DIRECTION.UP, 1, LIMITED_SWIPE_NUMBER);
        }
        driver.findElementByXPath("//android.widget.TextView[@text='MATT WATTS']/../android.view.ViewGroup/android.widget.TextView").click();
        removeStep(driver, "BILLY BOB"); //Remove a step
        sleep(3);
        addStepToSomeone(driver, "BILLY BOB"); //Add a new step to BILLY BOB profile
        goBack(driver);
        sleep(3);
        goBack(driver);
        addNewPerson(driver, "Joe", "Smith", "justin.smith@gmail.com");
        sleep(2);
        swipe(driver, null, DIRECTION.RIGHT, SWIPE_DURATION);
        swipe(driver, null, DIRECTION.RIGHT, SWIPE_DURATION);
        if (driver.isKeyboardShown()) {
          driver.hideKeyboard();
        }
        by = By.xpath("//android.widget.TextView[@text='HERE']");
        driver.findElement(by).click();
        sleep(3);
        WebElement ele = driver.findElementsByXPath("//android.widget.TextView[@text='\uE902']").get(1);
        attemptToSelectStep(driver, ele);
        driver.findElementByXPath("//android.widget.TextView[@text='ADD TO MY STEPS']").click();
        //Edit MATT WATTS profile
        by = By.xpath("//android.widget.TextView[@text='MATT WATTS']");
        scroll(driver, by, DIRECTION.DOWN, SWIPE_DURATION, LIMITED_SWIPE_NUMBER);
        driver.findElement(by).click();
        driver.findElementByXPath("//android.widget.TextView[@text='\uE920']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='EDIT']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Male']/../android.view.ViewGroup").click();
        driver.findElementsByXPath("//android.widget.EditText").get(3).sendKeys("2523634789");
        driver.findElementByXPath("//android.widget.TextView[@text='DONE']").click();
        goBack(driver);
        goBack(driver);
        //Edit BILLY BOB profile
        scroll(driver, By.xpath("//android.widget.TextView[@text='BILLY BOB']"), DIRECTION.UP, SWIPE_DURATION, LIMITED_SWIPE_NUMBER);
        driver.findElementByXPath("//android.widget.TextView[@text='BILLY BOB']").click();
        sleep(2);
        driver.findElementByXPath("//android.widget.TextView[@text='My Notes']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='EDIT PRIVATE NOTES']").click();
        driver.findElementByXPath("//android.widget.EditText").clear();
        driver.findElementByXPath("//android.widget.EditText").sendKeys("enjoys ice cream and reading");
        driver.findElementByXPath("//android.widget.TextView[@text='DONE']").click();
        sleep(1);
        driver.findElementByXPath("//android.widget.TextView[@text='Our Journey']").click();
        driver.findElementByXPath("//android.widget.EditText[contains(@text, 'Share something')]").sendKeys("He accepted a cup of coffee");
        driver.findElementByXPath("//android.widget.TextView[@text='?']").click();
        goBack(driver);
        sleep(2);
        driver.findElementByXPath("//android.widget.TextView[@text='BILLY BOB']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Our Journey']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='\uE920']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='EDIT']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Male']/../android.view.ViewGroup").click();
        driver.findElementByXPath("//android.widget.TextView[@text='DONE']").click();
        goBack(driver);
        goBack(driver);
        driver.findElementByXPath("//android.widget.TextView[@text='\uE91F']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='SIGN OUT']").click();
        login(driver, getAppUserName(), getAppPassword());
        removePeople(driver,"JOE SMITH");
        logout(driver);
      } finally {
        if (driver != null) {
          driver.quit();
        }
      }
    }
}