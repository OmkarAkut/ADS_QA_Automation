package webdriver.globalstatic;

import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.fail;
import static webdriver.helperstatic.WaitStatic.waitUntilElementIsVisible;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;



//Defines core framework variables, objects, and methods and pulls from driver.properties
public class SetupStatic {

  @SuppressWarnings("rawtypes")
public static List dbCredentials;

  public static String releaseVersion = null;
  protected static String testEnvironment;
  protected static final String TEST_ENVIRONMENT = "testEnvironment";

  //Setup browser
  public static String chrome = "chrome";
  public static String ie = "ie";
  public static String firefox = "firefox";
  protected static final String BROWSER = "browser";
  protected static String browser;

  //Setup test run parameters printout
  protected static String printTestParameters;
  protected static String PRINT_TEST_PARAMETERS = "parameters";
  //Setup screen resolution
  //protected static String screenResolution;
  //protected static String SCREEN_RESOLUTION = "resolution";

  //Setup savedFilesDirectoryPath
  protected static String savedFilesDirectoryPath;
  //Shilpa 11.08.2022 updated the path
  protected static String SAVED_FILES_PATH=System.getProperty("user.home")+ "\\Downloads";;

 // protected static String SAVED_FILES_PATH = "path_to_saved_files_directory";

  //Setup properties file
  protected static String PROPERTIES = "driver.properties";
  protected static final Properties properties = new Properties();

  //Setup printout
  public static boolean printout = false;
  protected static String PRINTOUT = "printout";

  //Establishes properties object and returns browser from driver.properties
  public static String setupSavedFilesDirectoryPath() throws IOException {
    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
    //Shilpa 11
    /*
    if (properties.getProperty(SAVED_FILES_PATH) != null) {
      savedFilesDirectoryPath = properties.getProperty(SAVED_FILES_PATH);
    } else {
      fail("Path to Saved Files directory not set in driver.properties");
    }
    */
	if (SAVED_FILES_PATH != null) {
		savedFilesDirectoryPath = SAVED_FILES_PATH;
	} else {
		try {
			fail("Path to Saved Files directory not set in driver.properties");
		} catch (AssertionError e) {
			ExtentReport.extenttest.log(Status.FAIL, "Path to Saved Files directory not set in driver.properties : FAIL");

			ExtentReport.extenttest.log(Status.INFO, e);
		}
	}
    return savedFilesDirectoryPath;
  }

  //Establishes properties object and returns browser from driver.properties
  public static String getBrowserProperty() throws IOException {
    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
    browser = properties.getProperty(BROWSER);
    return browser;
  }

  //Establishes properties object and returns printout test parameters value from driver.properties (null will be set to false)
  public static String getParametersProperty() {
    try {
      properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
      printTestParameters = properties.getProperty(PRINT_TEST_PARAMETERS).toLowerCase();
    } catch (Throwable e) {
      printTestParameters = "false";
    }
    return printTestParameters;
  }

  //Establishes properties object and returns printout test parameters value from driver.properties (null will be set to false)
//  public static String getScreenResolution() {
//    try {
//      properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
//      screenResolution = properties.getProperty(SCREEN_RESOLUTION).toLowerCase();
//    } catch (Throwable e) {
//      screenResolution = "";
//    }
//    return screenResolution;
//  }

  //Establishes properties object and returns printout value from driver.properties (null will be set to false)
  public static String getPrintoutProperty() {
    String printoutProperty;
    try {
      properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
      printoutProperty = properties.getProperty(PRINTOUT).toLowerCase();
    } catch (Throwable e) {
      printoutProperty = "";
    }
    return printoutProperty;
  }

  //Establishes properties object and returns testEnvironment from driver.properties
  public static String getTestEnvironmentProperty() {
    try {
      properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
      testEnvironment = properties.getProperty(TEST_ENVIRONMENT).toLowerCase();
    } catch (Throwable e) {
      testEnvironment = "";
    }
    return testEnvironment;
  }

  //Set printout from properties file
  public static boolean setPrintoutProperty() {
    boolean printoutPropertyBoolean = parseBoolean(getPrintoutProperty());
    return printoutPropertyBoolean;
  }

  //Set Browser from properties file
  public static String setBrowser() throws IOException {
    browser = getBrowserProperty();
    return browser;
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

  public static String setupGetReleaseVersionFromLoginPage(WebDriver driver) {
    try {
      Thread.sleep(1000); //figure out a way to wait on the initial application load without thread sleep
      waitUntilElementIsVisible(driver.findElement(By.id("username-inputEl")));
      releaseVersion = driver.findElement(By.id("releaseVersionNo-body")).getText();
      System.out.println("Release Version: " + releaseVersion);
    } catch (Throwable e) {
      System.out.println("Release version not found");
    }
    return releaseVersion;
  }

  //Set testEnvironment from properties file
  public static String setTestEnvironment() {
    testEnvironment = getTestEnvironmentProperty();
    return testEnvironment;
  }

  public static String setupTestEnvironmentUrl(String testEnvironment) {
    String url;
    if (testEnvironment.equals("evolve")) {
      //url = "https://192.168.214.200/alliance-webCont/login/index.jsp";
      url = "https://evolve.mdasdss.com/alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("echelon")) {
      url = "https://echelon.harrisaffinity.com/alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("edge")) {
      url = "https://qaedge.mdasdss.com";
    } else if (testEnvironment.equals("appsupport")) {
      url = "https://app-support.mdasdss.com/alliance-webCont/login/index.jsp";
    } else if (testEnvironment.equals("qa3")) {
          url = "https://qa3-dev-ap1.dev.harrispaas.com/alliance-webCont/login/index.jsp";
    } 
    else if (testEnvironment.equals("ads11")) {
        url = "https://qa3-dev-ap1.dev.harrispaas.com/alliance-webCont/alliance/index.jsp";
  } 
    else {
      url = "https://qaautomation.mdasdss.com/alliance-webCont/login/index.jsp";
    }
    return url;
  }

  @SuppressWarnings("unchecked")
public static List<String> setupDb(String testEnvironment) {
    String dbUrl;
    if (testEnvironment.equals("evolve")) {
      dbUrl = "jdbc:oracle:thin:@10.204.20.101:1522:qacurr1";
    } else if (testEnvironment.equals("echelon")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1623:echelon";
      System.out.println(dbUrl);
    } else if (testEnvironment.equals("appsupport")) {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1623:echelon"; //"jdbc:oracle:thin:@192.168.210.100:1540:qav8";
  }
    else if (testEnvironment.equals("qa3")) {
        dbUrl = "jdbc:oracle:thin:@10.204.20.101:1522:qacurr1"; //"jdbc:oracle:thin:@192.168.210.100:1540:qav8";
      	System.out.println(dbUrl);

    }
    else if (testEnvironment.equals("ads11")) {
        dbUrl = "jdbc:oracle:thin:@10.204.20.101:1528:STAGING"; //"jdbc:oracle:thin:@192.168.210.100:1540:qav8";
      	System.out.println(dbUrl);

    }
    else {
      dbUrl = "jdbc:oracle:thin:@192.168.210.100:1525:qaauto";
  	System.out.println(dbUrl);

    }
    dbCredentials = Arrays.asList(
            dbUrl,
            "qa",
            "pass"
    );
    return dbCredentials;
  }
}

