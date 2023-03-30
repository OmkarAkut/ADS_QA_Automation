package webdriver.globalstatic;


import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverStatic extends SetupStatic {

  public static WebDriver driver;
  private static final  String chromeDriver = "chromedriver";
  private static final  String ieDriverServer = "IEDriverServer32";
  private static final  String geckoDriver = "geckodriver";

  /** Sets the Driver object (driver) depending on the selected browser. */
  public static WebDriver setDriver(String browser) {
//    //add synchronized here for parallel test execution?//
//    if (browser.equals("firefox")) {
//      driver = new FirefoxDriver();
//    } else if (browser.equals("ie")) {
//      driver = new InternetExplorerDriver();
//    } else if (browser.toLowerCase().contains("headless")) {
//      System.out.println("Chrome is running in headless mode");
//      ChromeOptions options = new ChromeOptions();
//      options.addArguments("--window-size=1920,1080", "--ignore-certificate-errors", "--headless");
//      driver = new ChromeDriver(options);
//    } else {
//      ChromeOptions options = new ChromeOptions();
//      options.addArguments("--ignore-certificate-errors", "start-maximized");
//      //suppress 'chrome is controlled...' toolbar
//      //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//      //options.setExperimentalOption("useAutomationExtension", false);
//      driver = new ChromeDriver(options);
//    }
//    return driver;
//  }
	  try {
			browser = browser.toLowerCase();
			if (browser.contains("headless")) {
			  System.out.println("Chrome is running in headless mode");
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--window-size=1920,1080", "--ignore-certificate-errors", "--headless");
			  driver = new ChromeDriver(options);
			} else if (browser.equals("chrome")) {
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--ignore-certificate-errors", "start-maximized");
			  options.addArguments("--remote-allow-origins=*");
			 driver = new ChromeDriver(options);
			} else if (browser.equals("firefox")) {
			  driver = new FirefoxDriver();
			} else if (browser.equals("ie")) {
			  driver = new InternetExplorerDriver();
			} else {
			  fail("ERROR: Driver object not set.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return driver;
	  }

  /** Sets the path to the appropriate browser server exe file depending on the selected browser.
   *  By default, the browser server files are in the main > resources project directory and the
   *  paths are set accordingly.  These can be overridden for local configurations, but this isn't
   *  recommended.
   */
  public static String setsBrowserDriver(String browser) {
    String browserDriver;
    if (browser.equals("firefox")) {
      System.setProperty("webdriver.firefox.driver",
              "C:/ads/apps/Selenium/" + geckoDriver + ".exe");
      browserDriver = System.getProperty("webdriver.firefox.driver");
    } else if (browser.equals("ie")) {
      System.setProperty("webdriver.ie.driver",
              "src/main/resources/drivers/" + ieDriverServer + ".exe");
      browserDriver = System.getProperty("webdriver.ie.driver");
    } else {
//      System.setProperty("webdriver.chrome.driver",
//              "src/main/resources/drivers/" + chromeDriver + ".exe");
    	 System.setProperty("webdriver.chrome.driver",
               "src/main/resources/drivers/" + chromeDriver + ".exe");
      browserDriver = System.getProperty("webdriver.chrome.driver");
      System.setProperty("webdriver.chrome.silentOutput", "true");  //turns off chromedriver logging to console
    }
    return browserDriver;
  }

  /** Quits/Shuts Down all existing Driver objects (driver). */
  public static void closesDriverObject() {
    driver.quit();
    driver = null;
  }

  /**
   * Dismisses certificate screens for IE browser.
   */
  public static void clickThroughIeCertificateScreens() {
    try {
      Thread.sleep(1000);
      driver.findElement(By.xpath("//a[text()='More information']")).click();
      Thread.sleep(1000);
      driver.findElement(By.id("overridelink")).click();
    } catch (Throwable e) {
      System.out.println("Failed to click through Internet Explorer certificate screens");
    }
  }

}