package webdriver.scripts.security.ucqcroles;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

/**
 Automates sections of test ticket ADS-1466 (dev story ADS-241).  1 of 3 test scripts.
 ADS-241 AC/Requirements covered:
 Unit Cost Quick Calculation displays as Quick Link in Costing Bubble
 on Home Page as Costing Department Manager
 Unit Cost Quick Calculation displays as Quick Link in Costing Bubble
 on Home Page as Custom Role where UCQC was added
 When Unit Cost Quick Calculation accessed from Costing Bubble
 as Costing Department Manager or Custom Role wUcqc:
 UCQC screen opens using entire horizontal real estate with "Unit Cost Quick Calculation" title
 Unit Cost Quick Calculation displays in breadcrumbs
 Unit Cost Quick Calculation displays as item in dock bar
 */

@RunWith(Parameterized.class)
public class UcqcLinkInCostingBubbleDisplayedAds1466 extends LoginRolesTesting {

  //private String userRole;

  public UcqcLinkInCostingBubbleDisplayedAds1466(Users user) {
    super(user);
    System.out.println("User under test: " + user);
    //this.userRole = String.valueOf(user);
  }

  @Parameterized.Parameters (name = "{index}: Users={0}")
  public static Collection<Object[]> users() {
    Object[][] users = new Object[][] {
            {Users.valueOf("CostingDepartmentManager1")},
            {Users.valueOf("CustomRoleWithUcqcAdded")},
    };
    return Arrays.asList(users);
  }

  @Test
  public void testAssertUcqcLinkInCostingBubbleIsDisplayed() throws Throwable {
	  ExtentReport.reportCreate("UcqcLinkInCostingBubbleDisplayedAds1466","webdriver.scripts.security.ucqcroles","UcqcLinkInCostingBubbleDisplayedAds1466");
    
	  
	
    try {
    	GeneralElementsMap geMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
      waitForAjaxExtJs();
      Thread.sleep(2500);
      assertThatElementIsDisplayed(geMap.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation());
//      String[] expectedLinks = {"Unit Cost Quick Calculation"};
//      int index = 1;
//      while (index < 2) {
//        System.out.println(index);
//        WebElement link = driver.findElement(By.xpath(
//                "//h1[text()='Costing']/../descendant::div[@class='bubbleQuickLinks']"
//                        + "/descendant::a[text()='Unit Cost Quick Calculation']"));
//        assertElementText(link, expectedLinks[index-1], true);
//        index++;
//      }
      doClick(geMap.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation());
      waitForAjaxExtJs();
      assertPageInformation("Unit Cost Quick Calculation");
      doClosePageOnLowerBar("Unit Cost Quick...");
      ExtentReport.logPass("PASS", "testAssertUcqcLinkInCostingBubbleIsDisplayed");
    } catch (Exception|AssertionError e){
    	ExtentReport.logFail("FAIL", "testAssertUcqcLinkInCostingBubbleIsDisplayed", driver, e);
		fail(e.getMessage());
   
    }
  }
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}

//while (index < 2) {
//        System.out.println(index);
//        WebElement link = driver.findElement(By.xpath(
//        "//h1[text()='Costing']/../descendant::div[@class='bubbleQuickLinks']"
//        + "/descendant::a[text()='Unit Cost Quick Calculation']"));
//        assertElementText(link, expectedLinks[index-1], true);
//        index++;
//        }
