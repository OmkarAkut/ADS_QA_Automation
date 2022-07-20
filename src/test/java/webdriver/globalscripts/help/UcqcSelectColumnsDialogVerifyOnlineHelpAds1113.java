package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcSelectColumnsDialogVerifyOnlineHelpAds1113 extends PageTestHelper {

  private static CostingMap costingMap;

  /**
   * Automates test ticket Ads-1113.  User Help on Ucqc > Select Columns dialog.
   */
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("TEST CLASS: " + UcqcSelectColumnsDialogVerifyOnlineHelpAds1113.class.getSimpleName());
    evolveLoginStaticUser(Users.AutomationTester1);
    goToPage("Ucqc");
  }

  @Test
  public void testUcqcPageSelectColumnsDialogVerifyOnlineHelpLink() {
    try {
      doDropdownSelectUsingOptionText(
              costingMap.getUnitCostQuickCalculationDropdownCostModel(),
              costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),
              "QA Marina"
      );
      waitForSpinnerToEnd();
      costingMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll().click();
      costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect().click();
      testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'selectorfd.htm')]")), "Selector", printout);
      costingMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel().click();
      doClosePageOnLowerBar("Unit Cost Quick...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

}
