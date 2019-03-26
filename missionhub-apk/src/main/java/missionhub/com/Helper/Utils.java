package missionhub.com.Helper;
import com.google.common.base.Function;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Utils {

  public static enum DIRECTION {
      DOWN, UP, RIGHT, LEFT
  }
  public static int LIMITED_SWIPE_NUMBER = 10;
  public static int SWIPE_DURATION = 2;
  public static int WAIT_TIME = 20;

  public static AndroidDriver<WebElement> getDriver(String deviceName) {
    try {
      AndroidDriver<WebElement> driver = new AndroidDriver<>(new URL(Config.getCredential()), getAndroidAppDesiredCapabilites(Config.getAppURL(), deviceName));
      driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
      return driver;
    }
    catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
  }

  public static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (Exception ex){
      ex.printStackTrace();
    }
  }

  private static DesiredCapabilities getAndroidAppDesiredCapabilites(String appUrl, String deviceName) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("sessionName", "[Java] MissionHub app testing with Kobiton");
    capabilities.setCapability("sessionDescription", "CRU android automation app test session using Kobiton");
    capabilities.setCapability("deviceOrientation", "portrait");
    capabilities.setCapability("captureScreenshots", true);
    capabilities.setCapability("autoGrantPermissions", true);
    capabilities.setCapability("deviceGroup", "KOBITON");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("app", appUrl);
    capabilities.setCapability("deviceName", deviceName);
    capabilities.setCapability("groupId", 421); // Group: MissionHub

    capabilities.setCapability("newCommandTimeout", 60);
    return capabilities;
  }

  public static void swipe(AndroidDriver<WebElement> driver, By by, DIRECTION direction, int duration) {
    WebElement ele = null;
    Dimension size = driver.manage().window().getSize();

    int startX, startY, endX, endY;

    if (by != null) {
        ele = driver.findElement(by);
    }

    switch (direction) {

      case RIGHT:
        startY = (ele != null) ? (int) (ele.getLocation().getY()) : (int) (size.height / 2);
        startX = (int) (size.width * 0.90);
        endX = (int) (size.width * 0.05);
        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();
        break;

      case LEFT:
        startY = (ele != null) ? (int) (ele.getLocation().getY()) : (int) (size.height / 2);
        startX = (int) (size.width * 0.05);
        endX = (int) (size.width * 0.90);
        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();

        break;

      case UP:
          endY = (ele != null) ? ((ele.getLocation().getY()) + 200) : (int) (size.height * 0.70);
          startY = (ele != null) ? (ele.getLocation().getY()) : (int) (size.height * 0.30);
          startX = (ele != null) ? (ele.getLocation().getX()) : (size.width / 2);
          new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
          break;

      case DOWN:
          startY = (int) (size.height * 0.70);
          endY = (int) (size.height * 0.30);
          startX = (ele != null) ? (ele.getLocation().getX()) : (size.width / 2);
          new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
          break;
    }
  }

  public static void scroll(AndroidDriver<WebElement> driver, By by, DIRECTION direction, int duration, int limitedSwipeNumber) {
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    boolean found = false;
    int count = 1;
    while(!found && count <= limitedSwipeNumber) {
      try {
        driver.findElement(by);
        found = true;
      }
      catch(Exception e)
      {
        swipe(driver,null, direction, duration);
        count++;
      }
    }
    driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
  }

  public static void getSessionDetailsLink(AndroidDriver<WebElement> driver) {
    String kobitonSessionId = driver.getCapabilities().getCapability("kobitonSessionId").toString();
    System.out.println("\nClick following link for your automation session details: ");
    System.out.println("https://portal.kobiton.com/sessions/" + kobitonSessionId + "\n");
  }

  public static WebElement waitForElement(AndroidDriver<WebElement> driver, By locatorName, int timeout){
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    return wait.until(presenceOfElementLocated(locatorName));
  }

  public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
    return new Function<WebDriver, WebElement>() {
      @Override
      public WebElement apply(WebDriver driver) {
        return driver.findElement(locator);
      }
    };
  }
}