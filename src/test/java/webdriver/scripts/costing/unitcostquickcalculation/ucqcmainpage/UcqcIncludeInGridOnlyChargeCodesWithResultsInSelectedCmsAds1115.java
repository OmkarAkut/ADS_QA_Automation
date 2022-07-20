package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.UcqcData;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcIncludeInGridOnlyChargeCodesWithResultsInSelectedCmsAds1115
        extends UnitCostQuickCalculationHelperStatic {

  private static List<String> chargeCodesStrings;
  private static CostingMap ucqcMap;
  String[] requiredFields = {
    "Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
    "2130  PED ICU", "Jan 2005 to Jan 2005"
  };
  private static String sqlQuery =
      "SELECT ac.actdscrcode"
      + " FROM ActivityCostItem2 ac, CostModelScenario2 cm, EntDeptCostContainer2 ed, CostItemDataSet2 ci"
      + " WHERE ci.ObjectID = ac.MASTERID and cm.ObjectID = ed.cstmodscenid and ci.containerID = ed.ObjectID"
      + " AND cm.name = '*CM1 TB MHFY05 After Vol Change'"
      + " AND ed.EntityCode = '150'"
      + " And ed.DeptCode = '2130'"
      + " And ci.startdate >= TO_DATE('2005 01 01 00 00 00', 'YYYY MM DD HH24 MI SS')"
      + " And ci.enddate <= TO_DATE('2005 01 31 23 59 59', 'YYYY MM DD HH24 MI SS')"
  ;

  /** Zephyr test ticket ADS-1115.  Last Updated 4-8-20.
  * Verifies that only charges codes that are "complete" (as determined by the
  * database query) for the given cost model parameters are displayed on the
  * ucqc results table (grid).
  */
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println(
            "Test Class: " + UcqcIncludeInGridOnlyChargeCodesWithResultsInSelectedCmsAds1115
            .class.getSimpleName()
    );
    evolveLoginStaticUser(Users.CostingDepartmentManager1);
    goToPage("Unit Cost Quick Calculation");
  }

  @Test
  public void test01AssertUcqcGridDisplaysExpectedNumberOfChargeCodes() {
    try {
      waitForAjaxExtJs();
      ucqcDisplayChargeCodeGrid(requiredFields);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      List<WebElement> chargeCodes = javaMakeListOfWebElements2(
          driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div/div/div[1]/div[2]"
                 + "/div[contains(@class, 'x-grid-view')]/table/tbody")),
          "//tr[contains(@class,'x-grid-row')]/td[2][contains(@class,'x-grid-cell-gridcolumn')]"
                 + "/div[contains(@class,'x-grid-cell-inner')]"
      );
      chargeCodesStrings = javaListConvertListOfWebElementsToStrings(chargeCodes, printout);
      assertThat(chargeCodesStrings.size(), equalTo(UcqcData.ucqcChargeCodesExpectedList.size()));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02AssertUcqcGridDisplaysExpectedChargeCodes() {
    assertEquals(UcqcData.ucqcChargeCodesExpectedList, chargeCodesStrings);
  }

  @Test
  public void test03QueryDatabaseAndAssertResultsMatchDisplayedChargeCodes()
          throws ClassNotFoundException {
    getStringDataFromDatabaseAndAssertExpectedValues(
        sqlQuery,
        1,
        chargeCodesStrings
    );
  }
}
