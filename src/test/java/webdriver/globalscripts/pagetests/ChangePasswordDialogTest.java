package webdriver.globalscripts.pagetests;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChangePasswordDialogTest extends GoHelper {

  private static GeneralElementsMap generalElement;
  final static String warningText = "The old password you typed is not valid. Please type your old password again";
  final static String resetText = "To reset your password, provide your current password and new password";
  final static String termsOfUseText = "TERMS OF USE";
  @BeforeClass
  public static void setupScript() throws Exception {
    generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
    System.out.println("Test Class: " + ChangePasswordDialogTest.class.getSimpleName());
    /*modified by Omkar on 27/5/22 as only aadmin user is available for qa3 env
    Login.loginUser("AutomationTester1");
    */
    Login.loginUser("AutomationTesterAdmin");
 // End of modification
  }

  @Test
  public void test01OpenChangePasswordDialogAndAssert() {
    assertElementIsDisplayed(generalElement.getUserDropdown(), printout);
    generalElement.getUserDropdown().click();
    waitForPresenceOfElement("//*[@id = 'changePassword']");
    generalElement.getUserDropdownChangePassword().click();
    assertTextIsDisplayed(resetText);
  }

  @Test
  public void test02OpenChangePasswordDialogAndAssert() throws InterruptedException {
    driver.findElement(By.name("oldpassword")).sendKeys("a");
    driver.findElement(By.name("newpassword")).sendKeys("a");
    driver.findElement(By.name("confirmnewpassword")).sendKeys("a");
    doClickButton("Save & Close");
    waitForPresenceOfElement("//*[text()='" + warningText + "']");
    assertTextIsDisplayed(warningText);
    doClickButton("OK");
  }

  @Test
  public void test03CloseChangePasswordDialog() throws InterruptedException {
    doClickButton("Cancel & Close");
    driverDelay(1000);
    assertTextIsNotDisplayed(resetText);
  }

  @Test
  public void test04AssertTermsOfUse() throws InterruptedException {
    assertElementIsDisplayed(generalElement.getUserDropdown(), printout);
    generalElement.getUserDropdown().click();
    waitForPresenceOfElement("//*[@id='adiv']/descendant::*[text()='Terms of Use']");
    generalElement.getUserDropdownTermsOfUse().click();
    waitForPresenceOfElementText(termsOfUseText);
    assertTextIsDisplayed(termsOfUseText);
    doClosePageOnLowerBar("Terms of Use");
  }

}
