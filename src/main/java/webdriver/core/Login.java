package webdriver.core;

import static org.junit.Assert.*;
import static webdriver.helperstatic.WaitStatic.waitForSpinnerToEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
//import java.lang.Enum;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.corehelpers.WaitHelper;
import webdriver.users.*;

public class Login extends Driver {

	private static String username;
	private static String password;
	static String projectPath = System.getProperty("user.dir");
	// protected static String PROPS = "/selenium/webdriver.properties";
	// //external-should work for win and unix
	protected static String PROPS = projectPath + "/src/selenium/webdriver.properties";

	public static void loginUser(String user) throws Exception {
		if (testEnvironment.contains("auto")) {
			username = UsersQaAutomation.valueOf(user).getUsername();
			password = UsersQaAutomation.valueOf(user).getPassword();
		} else if (testEnvironment.contains("evolve")) {
			username = UsersEvolve.valueOf(user).getUsername();
			password = UsersEvolve.valueOf(user).getPassword();
		} else if (testEnvironment.contains("echelon")) {
			username = UsersEchelon.valueOf(user).getUsername();
			password = UsersEchelon.valueOf(user).getPassword();
		} else if (testEnvironment.contains("appsupport")) {
			username = UsersAppSupport.valueOf(user).getUsername();
			password = UsersAppSupport.valueOf(user).getPassword();
		} else if (testEnvironment.contains("edge")) {
			username = UsersEdge.valueOf(user).getUsername();
			password = UsersEdge.valueOf(user).getPassword();
		} else if (testEnvironment.contains("multia")) {
			username = UsersMultiTenantA.valueOf(user).getUsername();
			password = UsersMultiTenantA.valueOf(user).getPassword();
		} else if (testEnvironment.contains("multib")) {
			username = UsersMultiTenantB.valueOf(user).getUsername();
			password = UsersMultiTenantB.valueOf(user).getPassword();
		} else if (testEnvironment.contains("qa3")) {
			username = Usersqa3.valueOf(user).getUsername();
			password = Usersqa3.valueOf(user).getPassword();
		} else if (testEnvironment.contains("devstage")) {
			username = Usersqa3.valueOf(user).getUsername();
			password = Usersqa3.valueOf(user).getPassword();
		} else if (testEnvironment.contains("qaprod")) {
			username = Usersqa3.valueOf(user).getUsername();
			password = Usersqa3.valueOf(user).getPassword();
		} else if (testEnvironment.contains("qastage")) {
			username = Usersqa3.valueOf(user).getUsername();
			password = Usersqa3.valueOf(user).getPassword();
		} else {
			throw new Exception("ERROR: login credentials - environment name not recognized.");
		}
		login(username, password);
		// Below code is temporarily added by Omkar on 31/5/2022 as qa3 response time is
		// more than expected
		Thread.sleep(5000);
		waitForSpinnerToEnd();
		WaitHelper.waitForJsWindowOnload();
	}

	public static void evolveLoginStaticUser(Users user) {
		login(user.getUsername(), user.getPassword());
	}

	public static void loginUser(Users user) {
		login(user.getUsername(), user.getPassword());
	}

	public static void loginStaticUser(Users user) {
		login(user.getUsername(), user.getPassword());
	}

	public static void loginUserRole(Roles role) {
		login(role.getUsername(), role.getPassword());
	}

	private static void login(String username, String password) throws NoSuchSessionException, SessionNotCreatedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			if (printout) {
				System.out.println("Login Username: " + username);
			}
			try {
				if (driver.findElement(By.xpath("//div[text()='Application Update']")).isDisplayed()) {
					driver.findElement(By.xpath("//span[text()='Yes']")).click();
				}
			} catch (Exception e) {

			}
			try {

				if ((driver.findElement(By.xpath("//*[@id='username-inputEl']")).isDisplayed())) {

				}
			} catch (Exception e) {
				FileInputStream str = null;
				System.out.println(projectPath + " this is path");
				try {
					str = new FileInputStream(PROPS);
				} catch (Exception e1) {
					fail("Cannot locate properties file");
				}
				Properties props = new Properties();
				props.load(str);
				browser = props.getProperty("BROWSER").toLowerCase();
				try {
					testEnvironment = props.getProperty("TEST_ENVIRONMENT").toLowerCase();
				} catch (Exception e2) {
					e.printStackTrace();
				}
				String url=getTestEnvironmentUrl(testEnvironment);
				driver.navigate().refresh();
				Thread.sleep(5000);
				driver.navigate().to(url);
				Thread.sleep(5000);
				/*
				driver.close();
				System.out.println("DRIVER CLOSED!!!!");
				FileInputStream str = null;
				System.out.println(projectPath + " this is path");
				try {
					str = new FileInputStream(PROPS);
				} catch (Exception e1) {
					fail("Cannot locate properties file");
				}
				Properties props = new Properties();
				props.load(str);
				browser = props.getProperty("BROWSER").toLowerCase();
				try {
					testEnvironment = props.getProperty("TEST_ENVIRONMENT").toLowerCase();
				} catch (Exception e2) {
					e.printStackTrace();
				}
//			    setBrowserDriver(browser);
//			    setDriver(browser);
				getTestEnvironmentUrl(testEnvironment);
				setup();

//			    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='password-inputEl']")));
				driver.findElement(By.id("username-inputEl")).click();
				driver.findElement(By.id("username-inputEl")).sendKeys(username);
				driver.findElement(By.id("password-inputEl")).sendKeys(password);
				driver.findElement(By.id("loginBtn-btnInnerEl")).click();
//				action.sendKeys(Keys.ENTER).perform();
				waitForSpinnerToEnd();
				Thread.sleep(500);
				WebElement iAgree;
				if (driver.findElement(By.xpath("//*[normalize-space()='I Agree']"
						+ "[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"))
						.isDisplayed()) {
					iAgree = driver.findElement(By.xpath("//*[normalize-space()='I Agree']"
							+ "[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"));
					iAgree.click();
				}

			} catch (Throwable e) {
				try {

					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//div[contains(@class,'reporting')]/h1")));
					assertTrue(driver.findElement(By.xpath("//div[contains(@class,'reporting')]/h1")).getText()
							.contains("Reporting"));
					System.out.println("Login Time: " + setupFinalTimerResult(timerStart, false));
				} catch (Exception e1) {

				}
	*/
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='password-inputEl']")));
			driver.findElement(By.id("username-inputEl")).sendKeys(username);
			driver.findElement(By.id("password-inputEl")).sendKeys(password);
//			driver.findElement(By.id("loginBtn-btnInnerEl")).click();
			action.sendKeys(Keys.ENTER).perform();
			waitForSpinnerToEnd();
			Thread.sleep(500);
			
			WebElement iAgree;
			if (driver.findElement(By.xpath("//*[normalize-space()='I Agree']"
					+ "[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"))
					.isDisplayed()) {
				iAgree = driver.findElement(By.xpath("//*[normalize-space()='I Agree']"
						+ "[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"));
				iAgree.click();
			}

		} catch (Throwable e) {
			try {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//div[contains(@class,'reporting')]/h1")));
				assertTrue(driver.findElement(By.xpath("//div[contains(@class,'reporting')]/h1")).getText()
						.contains("Reporting"));
				System.out.println("Login Time: " + setupFinalTimerResult(timerStart, false));
			
			} catch (Exception e1) {
//				driver.close();
			}

		}

	}

	public static void isInvalid() {
		try {
			waitForSpinnerToEnd();
			assertFalse(driver.findElement(By.xpath("//*[contains(@id, 'errorWindow')]"
					+ "/descendant::*[text()='You have typed an invalid username or password. "
					+ "Please retype your information.']")).isDisplayed());
		} catch (Throwable e) {
		}
	}

	public static void isLoggedIn() {
		try {
			waitForSpinnerToEnd();
			// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='footerText']")));
			assertTrue(driver.findElement(By.className("footerText")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[@class='footerText']/span")).getText()
					.contains("Picis Clinical Solutions, Inc. All rights reserved."));
		} catch (Throwable e) {
			fail("isLoggedIn Failed");
		}
	}
}

//  public Login() {
//  }
//
//  public Login(Users user) {
//    this.username = user.getUsername();
//    this.password = user.getPassword();
//    login(username, password);
//  }
//
//  public Login(Roles role) {
//    this.username = role.getUsername();
//    this.password = role.getPassword();
//    login(username, password);
//  }
