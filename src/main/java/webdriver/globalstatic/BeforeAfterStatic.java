package webdriver.globalstatic;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.helperstatic.CalculationsHelper;

public class BeforeAfterStatic extends CalculationsHelper {

  static long timerStart;

  /**
   * The setup() method creates global framework objects.
   * Method calls are to the SetupStatic class.
   */
  @BeforeClass
  public static void setup() throws Exception {
    //add synchronized here for parallel test execution?
	  System.out.println("kjsncafldjflrsejg");
    System.out.println("START FRAMEWORK");

    printout = setPrintoutProperty();
    System.out.println("Printout: " + printout);

    //sets browser driver
    setsBrowserDriver(setBrowser());

    //creates driver object
    setDriver(setBrowser());

    System.out.println("Screen resolution: " + driver.manage().window().getSize());

    //start login timer
    timerStart = setupStartTimer();

    //navigates to url under test
    System.out.println("Test Environment: " + setTestEnvironment());
    getUrl(setupTestEnvironmentUrl(setTestEnvironment()));

    if (browser.toLowerCase().equals("ie")
            || browser.toLowerCase().equals("internetexplorer")) {
      clickThroughIeCertificateScreens();
    }

    releaseVersion = setupGetReleaseVersionFromLoginPage(driver);

    //test context printout (to console)
    printTestParameters(getParametersProperty());
  }

  /**
   * teardown() is the global framework method to logout of the application
   * and quit/close the driver object.
   */
  @AfterClass
  public static void teardown() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    try {
//      waitForElementToBeVisible(driver.findElement(By.id("component-1022")));
//      WebElement logOut = driver.findElement(By.id("component-1022"));
//      logOut.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")));
        driver.findElement(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
        System.out.println("Logout successful");
    } catch (Throwable e) {
      System.out.println("WARNING: Logout was not successful");
    } finally {
      System.out.println("Closing Driver Object");
      closesDriverObject();
    }
  }

  public static void getUrl(String url) {
    driver.get(url);
  }

  /** Prints test context parameters to console. */
  private static void printTestParameters(String printTestParameters) throws Exception {
    if (printTestParameters.equals("true")) {
      System.out.println("===============================================");
      System.out.println("CURRENT TEST PARAMETERS CONFIGURATION");
      System.out.println("Operating System:   " + System.getProperty("os.name"));
      System.out.println("Java JRE Version:   " + System.getProperty("java.version"));
      System.out.println("Properties File:    " + PROPERTIES);
      System.out.println("Browser:            " + getBrowserProperty());
      System.out.println("Browser Driver:     " + setsBrowserDriver(setBrowser()));
      //System.out.println("Test Environment: " + environment);
      //System.out.println("Driver: " + setDriver(setBrowser()));
      //System.out.println("URL Under Test: " + getURL());
      System.out.println("===============================================");
    }
  }

//  /**
//   * Dismisses certificate screens for IE browser.
//   */
//  public static void clickThroughIeCertificateScreens() {
//    try {
//      Thread.sleep(1000);
//      driver.findElement(By.xpath("//a[text()='More information']")).click();
//      Thread.sleep(1000);
//      driver.findElement(By.id("overridelink")).click();
//    } catch (Throwable e) {
//      System.out.println("Failed to click through Internet Explorer certificate screens");
//    }
//  }

}
