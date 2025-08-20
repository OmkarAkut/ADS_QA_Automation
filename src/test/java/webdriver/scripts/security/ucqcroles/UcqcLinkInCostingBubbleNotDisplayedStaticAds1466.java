package webdriver.scripts.security.ucqcroles;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@RunWith(Parameterized.class)
public class UcqcLinkInCostingBubbleNotDisplayedStaticAds1466 extends LoginRolesTesting {

  private String user;

  /**
   Covers dev story ADS-241.
   Unit Cost Quick Calculation Quick Link does not display for other standard roles
   Unit Cost Quick Calculation Quick Link does not display for Custom Roles that do not have UCQC
   Unit Cost Quick Calculation does not display as a Quick Link in Costing Bubble on Home Page
   when Costing Department Manager is not one of the user roles
   */
  public UcqcLinkInCostingBubbleNotDisplayedStaticAds1466(Users user) {
    super(user);
    System.out.println("User under test: " + user);
    this.user = String.valueOf(user);
  }

  /** Test users for current test case. */
  @Parameterized.Parameters (name = "{index}: Users={0}")
  public static Collection<Object[]> users() {
    Object[][] users = new Object[][] {
      {Users.valueOf("CostAnalyst1")},
      {Users.valueOf("CustomRoleAllStandardRolesExceptUcqc")},
    };
    return Arrays.asList(users);
  }

  @Test
  public void UcqcLinkInCostingBubbleNotDisplayedStaticAds1466() throws Throwable {
	  ExtentReport.reportCreate("UcqcLinkInCostingBubbleNotDisplayedStaticAds1466", "webdriver.scripts.security.ucqcroles","UcqcLinkInCostingBubbleNotDisplayedStaticAds1466");
	   
    try {
    	
//      waitForAjaxExtJs();
    	waitForDisplayedSpinnerToEnd();
      //Shilpa updated xpath 24.08.2022 
//      assertElementIsNotDisplayed(By.xpath("//*[@class='bubbleContent' and @id='costing']"
//              + "/following-sibling::div/descendant::ul/li/a[text()='Unit Cost Quick Calculation']"
//      ));
      assertElementIsNotDisplayed(By.xpath("//*[@class='bubbleContent' and @id='costing-bubble-content1']"
              + "/following-sibling::div/descendant::ul/li/a[text()='Unit Cost Quick Calculation']"
      ));
      
      ExtentReport.logPass("PASS", "UcqcLinkInCostingBubbleNotDisplayedStaticAds1466");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "UcqcLinkInCostingBubbleNotDisplayedStaticAds1466", driver, e);
		fail(e.getMessage());
      
    }
  }
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
