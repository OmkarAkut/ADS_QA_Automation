package webdriver.core;

import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Saves properties from file as variables for test framework */
public class Driver {

  public static List dbCredentials;
  static String projectPath = System.getProperty("user.dir");
 // protected static String PROPS = "/selenium/webdriver.properties"; //external-should work for win and unix
  protected static String PROPS = projectPath + "/src/selenium/webdriver.properties";
  protected static Properties properties = new Properties();

  public static String testEnvironment;
  protected static String browser;
  protected static boolean printout;
  protected static String downloads;
  protected static String drivers;
  protected static String version;
//below line is commented by Omkar as there are isues while running on Jenkins
  protected static final String chromeDriver = "chromedriver";
 // protected static final String chromeDriver = "C:/ADS_automation/AffinityDecisionSupport/src/selenium/chromedriver";
  protected static final String ieDriverServer = "IEDriverServer32";
  protected static final String geckoDriver = "C:/ads/apps/Selenium/geckoDriver.exe";
  public static WebDriver driver;
  static long timerStart;
  protected static String localOs;

  /**
   * The setup() method creates global framework objects.
   */
  @BeforeClass
  public static void setup() throws IOException {
	  //System.out.println("kjsncafldjflrsejg");
    System.out.println("START FRAMEWORK");
    localOs = getLocalOs();
    System.out.println("Local OS: " + localOs);
    //System.out.println("Local OS: cghjgfhfhfhfdhgdfgdghdg");
    getPropertiesFromFile();
    System.out.println("Test Environment: " + testEnvironment);
    System.out.println("Browser: " + browser);
    System.out.println("Printout: " + printout);
    System.out.println("Downloads: " + downloads);
    System.out.println("Version: " + version);
    setBrowserDriver(browser);
    driver = setDriver(browser);
    System.out.println("Screen Resolution: " + driver.manage().window().getSize());
    timerStart = setupStartTimer();
    driver.get(getTestEnvironmentUrl(testEnvironment));
    if (browser.toLowerCase().equals("ie")) {
      clickThroughIeCertificateScreens();
    }
    System.out.println("Release Version: " + getReleaseVersionFromLoginPage());
  }

  /**
   * teardown() is the global framework method to logout of the application
   * and quit/close the driver object.
   */
  @AfterClass
  public static void teardown() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")));
      driver.findElement(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")).click();
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
      System.out.println("Logout successful");
    } catch (Throwable e) {
      System.out.println("WARNING: Logout was not successful");
    } finally {
      System.out.println("Closing Driver");
      driver.quit();
      driver = null;
    }
  }

  private static void getPropertiesFromFile() throws IOException {
    FileInputStream str = null;
    System.out.println(projectPath+ " this is path");
    try {
      str = new FileInputStream(PROPS);
    } catch (Exception e) {
      fail("Cannot locate properties file");
    }
    Properties props = new Properties();
    props.load(str);
    try {
      testEnvironment = props.getProperty("TEST_ENVIRONMENT").toLowerCase();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (props.getProperty("DRIVERS") != null) {
      drivers = props.getProperty("DRIVERS");
    } else {
      fail("ERROR: Drivers path property not available.");
    }
    if (props.getProperty("BROWSER") != null) {
      browser = props.getProperty("BROWSER").toLowerCase();
    } else {
      fail("ERROR: Browser property not available.");
    }
    if (props.getProperty("VERSION") != null) {
      version = props.getProperty("VERSION");
    } else {
      fail("ERROR: Version property not available.");
    }
    if (props.getProperty("PRINTOUT") != null) {
      printout = parseBoolean(props.getProperty("PRINTOUT").toLowerCase());
    } else {
      fail("ERROR: Printout property not available.");
    }
  }

  public static String getTestEnvironmentUrl(String testEnvironment) {
    String url;
    if (testEnvironment.equals("evolve")) {
      url = "https://evolve.mdasdss.com/alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("echelon")) {
      url = "https://echelon.harrisaffinity.com/alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("appsupport")) {
      url = "https://app-support.mdasdss.com/alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("edge")) {
      url = "https://qaedge.mdasdss.com";
    } else if (testEnvironment.equals("multia")) {
      url = "https://dss41vqmulapp01.mdasdss.com/hospitala-alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("multib")) {
      url = "https://dss41vqmulapp01.mdasdss.com/hospitalb-alliance-webCont/login/index.jsp";
    } else if(testEnvironment.equals("qa3")) {
    	url = "https://qa3-dev-ap1.dev.harrispaas.com/alliance-webCont/alliance/index.jsp"; 
    	
      }
    else if(testEnvironment.equals("staging")) {
    	url = "http://stgrhl-dev.harrispaas.com/alliance-webCont/alliance/index.jsp";  
      }
    else if(testEnvironment.equals("staging_11.1.1")) {
    	url = "http://qaprod-dev.harrispaas.com/alliance-webCont/login/index.jsp";  
      }
    else if(testEnvironment.equals("qa3prod")) {
    	url = "http://qaprod-dev.harrispaas.com/alliance-webCont/login/index.jsp";  
      }
     else {
      url = "https://qaautomation.mdasdss.com/alliance-webCont/login/index.jsp";
    }
    return url;
  }

  public static String getLocalOs() {
    String localOs = System.getProperty("os.name").toLowerCase();
    return localOs;
  }

  public static void setBrowserDriver(String browser) {
	//Omkar: Drivers value is overwritten to avoid having a fixed path from property file
	drivers = projectPath + "/src/main/resources/drivers/";
    System.out.println("Browser drivers path: " + drivers + chromeDriver + ".exe");
    browser = browser.toLowerCase();
    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
      if (browser.equals("chrome") || browser.contains("headless")) {
    	  
        System.setProperty("webdriver.chrome.driver", drivers + chromeDriver + ".exe");
        System.setProperty("webdriver.chrome.silentOutput", "true"); //chromedriver logging to console
      } else if (browser.equals("ie")) {
        System.setProperty("webdriver.ie.driver", drivers + ieDriverServer + ".exe");
      } else if (browser.equals("firefox")) {
        System.setProperty("webdriver.firefox.driver", drivers + geckoDriver + ".exe");
      } else {
        fail("ERROR: Driver object not set.");
      }
    } else {
      if (browser.equals("chrome") || browser.contains("headless")) {
    	 System.out.println(drivers + " " + chromeDriver);
        System.setProperty("webdriver.chrome.driver", drivers + chromeDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true"); //chromedriver logging to console
      } else if (browser.equals("ie")) {
        System.setProperty("webdriver.ie.driver", drivers + ieDriverServer);
      } else if (browser.equals("firefox")) {
        System.setProperty("webdriver.firefox.driver", drivers + geckoDriver);
      } else {
        fail("ERROR: Browser driver not set.");
      }
    }
  }

  public  static WebDriver setDriver(String browser) {
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
		 driver = new ChromeDriver(options);
		 //Clear browser cache
		 driver.manage().deleteAllCookies();
		    driver.get("chrome://settings/clearBrowserData");
		    driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
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

  public static String getReleaseVersionFromLoginPage() {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    String releaseVersion = null;
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='releaseVersionNo-body']")));
      releaseVersion = driver.findElement(By.id("releaseVersionNo-body")).getText();
    } catch (Throwable e) {
      System.out.println("Release version not found");
    }
    return releaseVersion;
  }

  public static long setupStartTimer() {
    long startTiming = System.currentTimeMillis();
    return startTiming;
  }

  public static String setupFinalTimerResult(long timerStart, boolean printout) {
    long finalTiming = System.currentTimeMillis();
    double totalRunTime = (finalTiming - timerStart) / 1000;
    if (printout) {
      System.out.println("Final Timing: " + finalTiming);
      System.out.println("Total Run Time: " + totalRunTime);
    }
    return "" + totalRunTime + " seconds";
  }

  //If a database is repointed, the proper dbUrl should be moved to the appropriate if statement
  public static List<String> setupDb(String testEnvironment) {
    String dbUrl;
    if (testEnvironment.equals("evolve")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1543:qacurr1";
    } else if (testEnvironment.equals("echelon")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1623:echelon";
    } else if (testEnvironment.equals("appsupport")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1623:echelon"; //"jdbc:oracle:thin:@192.168.210.100:1540:qav8";
      //dbUrl = "jdbc:oracle:thin:@192.168.210.100:1540:qav8";
    } else if (testEnvironment.contains("auto")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1525:qaauto";
    } else if (testEnvironment.contains("edge")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1524:qarpt12";
    } 
    //Shilpa 09.09.2022 added dbURL for testEnvironment 
    else if (testEnvironment.equals("qa3")) {
        dbUrl = "jdbc:oracle:thin:@10.204.20.101:1522:qacurr1"; //"jdbc:oracle:thin:@192.168.210.100:1540:qav8";
      	System.out.println(dbUrl);

    } else if (testEnvironment.equals("ads11")) {
        dbUrl = "jdbc:oracle:thin:@10.204.20.101:1528:STAGING"; //"jdbc:oracle:thin:@192.168.210.100:1540:qav8";
      	System.out.println(dbUrl);

    }
    else {
      dbUrl = null;
      System.out.println("dbUrl not set");
    }
    dbCredentials = Arrays.asList(
            dbUrl,
            "qa",
            "pass"
    );
    return dbCredentials;
  }
}
