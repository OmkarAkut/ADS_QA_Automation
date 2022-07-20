package webdriver.globalscripts.pagetests;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GlobalHeaderPageTest extends PageTestHelper {

  private static GeneralElementsMap genmap;
  final static String warningText = "The old password you typed is not valid. Please type your old password again";
  final static String resetText = "To reset your password, provide your current password and new password";
  final static String termsOfUseText = "TERMS OF USE";

  /** Automates test ticket ADS-1555.  Dev Story ADS-1528. Updated 7-1-21.
   * Verifies working links on Contact Us global page.
   */
  @BeforeClass
    public static void setupScript() throws Exception {
    genmap = BuildMap.getInstance(driver, GeneralElementsMap.class);
    System.out.println("Test Class: " + GlobalHeaderPageTest.class.getSimpleName());
    /*modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
    Login.loginUser("AutomationTester1");
    */
    Login.loginUser("AutomationTesterAdmin");
 // End of modification
    genmap.getGlobalHeaderButtonContactUs().click();
  }

  /* The below TC's are already present in Change password dislogue test
   * Modified/Commented by Omkar on 27th May 2022
   
  @Test
  public void test01OpenChangePasswordDialogAndAssert() {
    assertElementIsDisplayed(genmap.getUserDropdown(), printout);
    genmap.getUserDropdown().click();
    waitForPresenceOfElement("//*[@id = 'changePassword']");
    genmap.getUserDropdownChangePassword().click();
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
    assertElementIsDisplayed(genmap.getUserDropdown(), printout);
    genmap.getUserDropdown().click();
    waitForPresenceOfElement("//*[@id='adiv']/descendant::*[text()='Terms of Use']");
    genmap.getUserDropdownTermsOfUse().click();
    waitForPresenceOfElementText(termsOfUseText);
    assertTextIsDisplayed(termsOfUseText);
    doClosePageOnLowerBar("Terms of Use");
  }
 */
  
  
  @Test
  public void test05ContactUsPageHelpLink() throws InterruptedException {
    final String helpLinkHeader = "Contact Us";
    waitForAjaxExtJs();
    testHelpLinkAndCloseNewWindow(
            genmap.getContactUsPageHelpLink(),
            helpLinkHeader,
            printout
    );
  }

  @Test
  public void test06HarrisAffinityLinkText() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementText(
            genmap.getContactUsPageHarrisAffinityLink(),
            "www.HarrisAffinity.com",
            printout
    );
  }


  @Test
  public void test07HarrisAffinityLink() {
    String firstHandle = null;
    try {
      waitForAjaxExtJs();
      firstHandle = webSwitchToNewWindow(
              genmap.getContactUsPageHarrisAffinityLink(),
              printout
      );
      assertElementIsDisplayed(
              driver.findElement(By.xpath("//*[@class='logo-header affix']")),
              printout
      );
      driver.close();
    } catch (Throwable e) {
      fail("ERROR: Could not locate Harris Affinity window");
    } finally {
      driver.switchTo().window(firstHandle);
    }
  }

  @Test
  public void test08PortalLinkText() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementText(
            genmap.getContactUsPageSupportPortalLink(),
            "https://support.harrishealthcare.com/Affinity/",
            printout
    );
  }

  @Test
  public void test09PortalLinkSignInPage() {
    String firstHandle = null;
    try {
      waitForAjaxExtJs();
      firstHandle = webSwitchToNewWindow(
              genmap.getContactUsPageSupportPortalLink(),
              printout
      );
      assertElementIsDisplayed(
              driver.findElement(By.xpath("//*[text()='Sign in with a local account']")),
              printout
      );
      driver.close();
    } catch (Throwable e) {
      fail("ERROR: Could not locate Portal Login window");
    } finally {
      driver.switchTo().window(firstHandle);
    }
  }

}
