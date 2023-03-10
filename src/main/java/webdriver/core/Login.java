package webdriver.core;

import static org.junit.Assert.*;
import static webdriver.helperstatic.WaitStatic.waitForSpinnerToEnd;
//import java.lang.Enum;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.corehelpers.WaitHelper;
import webdriver.users.*;

public class Login extends Driver {

	private static String username;
	private static String password;


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
		} 
		else if (testEnvironment.contains("ads11")) {
			username = Usersqa3.valueOf(user).getUsername();
			password = Usersqa3.valueOf(user).getPassword();
		}
		else if (testEnvironment.contains("staging_11.1.1")) {
			username = Usersqa3.valueOf(user).getUsername();
			password = Usersqa3.valueOf(user).getPassword();
		}
		else {
			throw new Exception("ERROR: login credentials - environment name not recognized.");
		}
		login(username, password);
		//Below code is temporarily added by Omkar on 31/5/2022 as qa3 response time is more than expected
		Thread.sleep(5000);
		waitForSpinnerToEnd();
		WaitHelper wh = new WaitHelper();
		wh.waitForJsWindowOnload();
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

	private static void login(String username, String password) {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			if (printout) {
				System.out.println("Login Username: " + username);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='password-inputEl']")));
			driver.findElement(By.id("username-inputEl")).sendKeys(username);
			driver.findElement(By.id("password-inputEl")).sendKeys(password);
			//driver.findElement(By.id("loginBtn-btnInnerEl")).click();
			action.sendKeys(Keys.ENTER).perform();
			waitForSpinnerToEnd();
			Thread.sleep(500);
			WebElement iAgree;
			if(driver.findElement(By.xpath("//*[normalize-space()='I Agree']"
					+ "[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']")).isDisplayed()) 
			{
				iAgree = driver.findElement(By.xpath("//*[normalize-space()='I Agree']"
						+ "[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"));
				iAgree.click();
			}

		} catch (Throwable e) {
			try {
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='footerText']")));
//				assertTrue(driver.findElement(By.className("footerText")).isDisplayed());
//				assertTrue(
//						driver.findElement(By.className("footerText")).getText()
//						.contains("Picis Clinical Solutions, Inc. All rights reserved.")
//						);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'reporting')]/h1")));
				assertTrue(driver.findElement(By.xpath("//div[contains(@class,'reporting')]/h1")).getText().contains("Reporting"));
				System.out.println("Login Time: " + setupFinalTimerResult(timerStart, false));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}

		}
	}

	public static void isInvalid() {
		try {
			waitForSpinnerToEnd();
			assertFalse(driver.findElement(By.xpath(
					"//*[contains(@id, 'errorWindow')]" +
							"/descendant::*[text()='You have typed an invalid username or password. " +
					"Please retype your information.']"))
					.isDisplayed());
		} catch (Throwable e) {}
	}

	public static void isLoggedIn() {
		try {
			waitForSpinnerToEnd();
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='footerText']")));
			assertTrue(driver.findElement(By.className("footerText")).isDisplayed());
			assertTrue(
					driver.findElement(By.className("footerText")).getText()
					.contains("Picis Clinical Solutions, Inc. All rights reserved.")
					);
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



