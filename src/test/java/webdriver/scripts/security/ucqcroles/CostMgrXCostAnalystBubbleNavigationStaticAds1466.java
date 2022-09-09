package webdriver.scripts.security.ucqcroles;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.UcqcCalculateButtonEnableAndDisableAds1152;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostMgrXCostAnalystBubbleNavigationStaticAds1466
        extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap costingMap;
  private static GeneralElementsMap geMap;

  /** Automates sections of test ticket ADS-1466 (dev story ADS-241).  1 of 3 test scripts.
   AC/Requirements covered.
   Unit Cost Quick Calculation displays as 3rd Quick Link in Costing Bubble on Home Page as Cost Analyst and Costing Department Manager
   3 links in following order: Costing Models, Costing Data Maintenance, Unit Cost Quick Calculation
   ADDED ON THIS SCRIPT:
   When Unit Cost Quick Calculation accessed from Costing Bubble as Costing Department Manager
   UCQC screen opens using entire horizontal real estate with "Unit Cost Quick Calculation" title
   Unit Cost Quick Calculation displays in breadcrumbs
   Unit Cost Quick Calculation displays as item in dock bar
   **/

  @BeforeClass
  public static void setupScript() {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    geMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
    System.out.println("Test Class: " + UcqcCalculateButtonEnableAndDisableAds1152.class.getSimpleName());
    
    /*modified by Omkar on 27/5/22 as only aadmin user is available for qa3 env
    loginStaticUser(Users.AutoTestCostMgrXCostAnalyst);
    */
    loginStaticUser(Users.AutomationTesterAdmin);
 // End of modification
    
  }

  @Test
  public void testAssertLandingPageNavBubbleContainsUcqcLinkForCombinedRoleWithCostManagerAndCostAnalyst() {
    try {
      String[] expectedLinks = {"Costing Models", "Costing Data Maintenance",
              "Unit Cost Quick Calculation"};
      int index = 1;
      while (index < 4) {
        System.out.println(index);
        //Shilpa updated xpath 24.08.2022 
//        WebElement link = driver.findElement(By.xpath("//*[@class='bubbleContent' and @id='costing']" +
//                "/following-sibling::div/descendant::ul/li[" + index + "]/a"));
      WebElement link = driver.findElement(By.xpath("//*[@class='bubbleContent' and @id='costing-bubble-content1']" +
      "/following-sibling::div/descendant::ul/li[" + index + "]/a"));
        assertElementText(link, expectedLinks[index - 1], printout);
        index++;
      }
      doClick(geMap.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation());
      waitForAjaxExtJs();
      verifyUcqcPage(printout);
    } catch (Throwable e) {
      System.out.println(e.getMessage());
    }
  }
}
