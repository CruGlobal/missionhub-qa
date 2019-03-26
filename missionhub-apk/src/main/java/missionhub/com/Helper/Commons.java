package missionhub.com.Helper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static missionhub.com.Helper.Utils.*;

public class Commons {

    public static void login(AndroidDriver driver, String username, String password) {
      try{
        driver.findElementByXPath("//android.widget.TextView[@text='SIGN IN']").click();
        driver.findElementByXPath("//android.widget.EditText[@text='Email']").sendKeys(username);
        driver.findElementByXPath("//android.widget.EditText[@password='true']").sendKeys(password);
        driver.findElementByXPath("//*[@text='LOGIN']").click();
        waitForElement(driver, By.xpath("//android.widget.TextView[@text='Steps']"), WAIT_TIME);
      }catch (Exception ex){
          ex.printStackTrace();
      }
  }

  public static void goBack(AndroidDriver driver) {
    By by = By.xpath("//android.widget.TextView[@text='\uE903']");
    if (driver.findElements(by).size() > 0) {
      driver.findElement(by).click();
    }
  }

  public static void removeStep(AndroidDriver driver, String stepName) {
    driver.findElementByXPath("//android.widget.TextView[@text='Steps']").click();
    By by = By.xpath("//android.widget.TextView[@text='" + stepName + "']");
    if (driver.findElements(by).size() > 0) {
      sleep(3);
      swipe(driver, by, DIRECTION.RIGHT, SWIPE_DURATION);
      by = By.xpath("//android.widget.TextView[@text='" + stepName + "']/../../../android.view.ViewGroup");
      WebElement e = (WebElement) driver.findElements(by).get(1);
      e.click();
    }

  }

  public static void removePeople(AndroidDriver driver, String name) {
    driver.findElementByXPath("//android.widget.TextView[@text='People']").click();
    By by = By.xpath("//android.widget.TextView[@text='" + name + "']");
    if (driver.findElements(by).size() > 0) {
      sleep(1);
      driver.findElement(by).click();
      sleep(2);
      driver.findElementByXPath("//android.widget.TextView[@text='\uE920']").click();
      driver.findElementByXPath("//android.widget.TextView[@text='DELETE PERSON']").click();
      driver.findElementById("android:id/button1").click();
    }

  }

  public static void logout(AndroidDriver driver) {
    driver.findElementByXPath("//android.widget.TextView[@text='\uE91F']").click();
    driver.findElementByXPath("//android.widget.TextView[@text='SIGN OUT']").click();
  }

  public static void addStepToSomeone(AndroidDriver driver, String personName) {
    driver.findElementByXPath("//android.widget.TextView[@text='People']").click();
    driver.findElementByXPath("//android.widget.TextView[@text='" + personName + "']").click();
    driver.findElementByXPath("//android.widget.TextView[@text='ADD A STEP OF FAITH']").click();
    WebElement element = null;
    By by = By.xpath("//android.widget.TextView[@text='\uE902']");
    if (driver.findElements(by).size() < 2) {
      sleep(2);
      swipe(driver, null, DIRECTION.DOWN, SWIPE_DURATION);
      driver.findElementByXPath("//android.widget.TextView[@text='SHOW MORE STEPS']").click();
    }
    element = (WebElement) driver.findElements(by).get(1);
    attemptToSelectStep(driver, element);
    driver.findElementByXPath("//android.widget.TextView[@text='ADD TO MY STEPS']").click();
  }

  public static void addNewPerson(AndroidDriver driver, String firstName, String lastName, String email) {
    driver.findElementByXPath("//android.widget.TextView[@text='People']").click();
    driver.findElementByXPath("//android.widget.TextView[@text='Personal Ministry']/../android.view.ViewGroup/android.widget.TextView").click();
    driver.findElementByXPath("//android.widget.EditText[@text='First Name (Required)']").sendKeys(firstName);
    driver.findElementByXPath("//android.widget.EditText[@text='Last Name']").sendKeys(lastName);
    driver.findElementByXPath("//android.widget.EditText[@text='Email']").sendKeys(email);
    driver.findElementByXPath("//android.widget.TextView[@text='DONE']").click();
  }

  public static void attemptToSelectStep(AndroidDriver driver, WebElement element) {
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    By by = By.xpath("//android.widget.TextView[@text='\uE924']");
    List eles = new ArrayList();
    int count = 0;
    while (count < 3 && eles.size() == 0) {
      try {
        element.click();
        driver.findElement(by);
        eles = driver.findElements(by);
      }
      catch (Exception ex) {
        System.out.println("Try to click on step name");
      }
      count++;
    }
    driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
  }
}