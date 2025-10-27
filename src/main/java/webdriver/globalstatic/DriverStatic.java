package webdriver.globalstatic;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverStatic extends SetupStatic {

	public static WebDriver driver;
	private static final String chromeDriver = "chromedriver";
	private static final String ieDriverServer = "IEDriverServer32";
	private static final String geckoDriver = "geckodriver";
	protected static final String edgeDriver = "msedgedriver";

	/** Sets the Driver object (driver) depending on the selected browser. */
	public static WebDriver setDriver(String browser) {
		try {
			browser = browser.toLowerCase();
			if (browser.contains("headless")) {
				System.out.println("Chrome is running in headless mode");
				// Shilpa added below line for 11.2 on 12.12.2023
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--window-size=1920,1080", "--ignore-certificate-errors", "--headless");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} else if (browser.equals("chrome")) {
				// Shilpa added below line for 11.2 on 12.12.2023
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				final Map<String, Object> chromePrefs = new HashMap<>();
				// Shilpa : Below lines to disable password pop up 4.14.2025
				chromePrefs.put("credentials_enable_service", false);
				chromePrefs.put("profile.password_manager_enabled", false);
				chromePrefs.put("profile.password_manager_leak_detection", false);
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--ignore-certificate-errors", "start-maximized");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--disable-features=ClipboardRead,ClipboardWrite");
				driver = new ChromeDriver(options);
			} else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equals("ie")) {
				driver = new InternetExplorerDriver();
				// Omkar 15/12/2023 : Code addition for edge browser execution
				// Shilpa added below line for 11.2 on 12.12.2023
			} else if (browser.equals("edge")) {
				/*
				 * WebDriverManager.edgedriver().setup(); EdgeOptions options =new
				 * EdgeOptions(); options.addArguments("--disable-mobile-upload");
				 * 
				 * options.addArguments("--remote-allow-origins=*");
				 * options.addArguments("--ignore-certificate-errors", "start-maximized");
				 * driver = new EdgeDriver(options);
				 */
				// Shilpa updated the auto nstall of edge driver 8.12.2025
				System.setProperty("wdm.edgeDriverUrl", "https://msedgedriver.microsoft.com/");
				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
//			  options.addArguments("--disable-mobile-upload");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--ignore-certificate-errors", "start-maximized");
				options.addArguments("--disable-features=ClipboardRead,ClipboardWrite");
				driver = new EdgeDriver(options);
				options.addArguments("--disable-mobile-upload");
			} else {
				fail("ERROR: Driver object not set.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * Sets the path to the appropriate browser server exe file depending on the
	 * selected browser. By default, the browser server files are in the main >
	 * resources project directory and the paths are set accordingly. These can be
	 * overridden for local configurations, but this isn't recommended.
	 */
	public static String setsBrowserDriver(String browser) {
		String browserDriver = null;//10.24.2025
	
		if (browser.contains("headless")) {
			System.out.println("Chrome is running in headless mode");
			// Shilpa added below line for 11.2 on 12.12.2023
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1920,1080", "--ignore-certificate-errors", "--headless");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
		}
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", "C:/ads/apps/Selenium/" + geckoDriver + ".exe");
			browserDriver = System.getProperty("webdriver.firefox.driver");
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/" + ieDriverServer + ".exe");
			browserDriver = System.getProperty("webdriver.ie.driver");
		}
//		Omkar 15/12/2023 : addition of edge driver
		else if (browser.equals("edge")) {
			/*
			 * WebDriverManager.edgedriver().setup();
			 * System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/" +
			 * edgeDriver + ".exe"); browserDriver =
			 * System.getProperty("webdriver.edge.driver");
			 */
			WebDriverManager.edgedriver().setup();
			browserDriver = System.setProperty("wdm.edgeDriverUrl", "https://msedgedriver.microsoft.com/");

		}
//		else {
			// System.setProperty("webdriver.chrome.driver",
			// "src/main/resources/drivers/" + chromeDriver + ".exe");
			/*
			 * WebDriverManager.chromedriver().setup();
			 * System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/" +
			 * chromeDriver + ".exe"); browserDriver =
			 * System.getProperty("webdriver.chrome.driver");
			 * System.setProperty("webdriver.chrome.silentOutput", "true");
			 */ // turns off chromedriver logging to console
//			WebDriverManager.chromedriver().setup();
//			browserDriver = System.getProperty("webdriver.chrome.driver");

//		}
		else if (browser.equals("chrome")) {
			// Shilpa added below line for 11.2 on 12.12.2023
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			final Map<String, Object> chromePrefs = new HashMap<>();
			// Shilpa : Below lines to disable password pop up 4.14.2025
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--ignore-certificate-errors", "start-maximized");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-features=ClipboardRead,ClipboardWrite");
			driver = new ChromeDriver(options);
		}
		return browserDriver;
	}

	/** Quits/Shuts Down all existing Driver objects (driver). */
	public static void closesDriverObject() {
		try {
			driver.quit();
			driver = null;
		} catch (NoSuchSessionException e) {

		}
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