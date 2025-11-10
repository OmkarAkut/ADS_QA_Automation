package webdriver.globalstatic;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webdriver.users.Roles;
import webdriver.users.Users;

public class LoginStatic extends BeforeAfterStatic {

  public String username;
  public String password;

  public LoginStatic() {
  }

  public LoginStatic(Users user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    loginStatic(username, password);
  }

  public LoginStatic(Roles role) {
    this.username = role.getUsername();
    this.password = role.getPassword();
    loginStatic(username, password);
  }

  public static void evolveLoginStaticUser(Users user) {
    loginStatic(user.getUsername(), user.getPassword());
  }

  public static void loginUser(Users user) {
    loginStatic(user.getUsername(), user.getPassword());
  }

  public static void loginStaticUser(Users user) {
    loginStatic(user.getUsername(), user.getPassword());
  }

  public static void loginUserRole(Roles role) {
    loginStatic(role.getUsername(), role.getPassword());
  }

  private static void loginStatic(String username, String password) {
	 
    try {
      if (printout) {
        System.out.println("Login Username: " + username);
      }
      waitForPresenceOfElement("//*[@id='username-inputEl']");
      //Thread.sleep(3000); //replace with something better
      waitUntilElementIsVisible(driver.findElement(By.id("username-inputEl")));
      try {
		WebElement harrisUsername = driver.findElement(By.id("username-inputEl"));
		  harrisUsername.sendKeys(username);
	} catch (NoSuchElementException|ElementNotInteractableException e) {
	
	}
      try {
		WebElement harrisPassword = driver.findElement(By.id("password-inputEl"));
		  harrisPassword.sendKeys(password);
		  Actions action = new Actions(driver);
		  action.sendKeys(Keys.ENTER);
		  action.perform();
	}  catch (NoSuchElementException|ElementNotInteractableException e) {
		fail();
	}
      driverDelay(3000);
      waitForSpinnerToEnd();
      isLoggedIn();
      if (printout) {
        System.out.println("Login Time: " + setupFinalTimerResult(timerStart, false));
      }
    } catch (Throwable e) {
      try {
		fail(e.getMessage());
	} catch (AssertionError e1) {

	}
    }
  }

  public static void isLoggedIn() {
    try {
      waitForJsWindowOnload();
      waitForSpinnerToEnd();
      waitUntilElementIsVisible(driver.findElement(By.className("footerText")));
      assertElementIsDisplayed(driver.findElement(By.className("footerText")), false);
      String footer = driver.findElement(By.className("footerText")).getText();
      assertTrue(
         footer.contains("Picis Clinical Solutions, Inc. All rights reserved.")
         //footer.contains("Contents Copyright © 2020 Picis Clinical Solutions, Inc. All rights reserved.")
      );
    } catch (Throwable e) {
      fail("isLoggedIn Failed");
    }
  }
  
}




