package webdriver.globalstatic;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.users.Roles;
import webdriver.users.Users;

public class LoginRolesTesting extends BeforeAfterRolesTesting {

  //Actions actions;
  //This Before method adds the login steps to the test framework
  @Before
  public void login() {
    evolveLogin(username, password);
  }

  // ======= Username and Password section ========== //
  protected String username;
  protected String password;

  protected LoginRolesTesting(Users user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
  }

  protected LoginRolesTesting(Roles role) {
    this.username = role.getUsername();
    this.password = role.getPassword();
  }
  //======= End Username and Password section ====== //

  // ===== Login Section ===== //
  public void evolveLogin(String username, String password) {
    try {
      Thread.sleep(3000); //figure out a way to wait on the initial application load without thread sleep
      waitUntilElementIsVisible(driver.findElement(By.id("username-inputEl")));
      WebElement harrisUsername = driver.findElement(By.id("username-inputEl"));
      harrisUsername.sendKeys(username);
      WebElement harrisPassword = driver.findElement(By.id("password-inputEl"));
      harrisPassword.sendKeys(password);

      Actions action = new Actions(driver);
      action.sendKeys(Keys.ENTER);
      action.perform();
      waitForSpinnerToEnd();
      Thread.sleep(1000);
      WebElement iAgree = driver.findElement(By.xpath("//*[normalize-space()='I Agree'][@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"));
        if(iAgree.isDisplayed()) 
      	  iAgree.click();
        else {
      	  System.out.println("This is else");
        }
        
      isLoggedIn();
      System.out.println("You have logged in successfully.");
      //System.out.println("Time to Login: " + setupFinalTimerResult(timerStart));
      System.out.println("Screen Resolution: "+ driver.manage().window().getSize());

      //below is the original sequence - replaced by above steps 1-26-19
      //Thread.sleep(5000);
      //waitUntilElementIsVisible(driver.findElement(By.id("loginBtn")));
      //driver.findElement(By.id("loginBtn")).click();
      //verifyLogin();
    } catch (Throwable e) {
      System.out.println("WARNING: Login Attempt Failed");
    }
  }

  // ===== End Login Section ===== //

  private void isLoggedIn() {
    try {
      waitForJsWindowOnload();
      waitForSpinnerToEnd();
      waitUntilElementIsVisible(driver.findElement(By.className("footerText")));
      assertElementIsDisplayed(driver.findElement(By.className("footerText")));
      //String footer = driver.findElement(By.className("footerText")).getText();
    } catch (Throwable e) {
      System.out.println("isLoggedIn Failed");
    }
  }
}
