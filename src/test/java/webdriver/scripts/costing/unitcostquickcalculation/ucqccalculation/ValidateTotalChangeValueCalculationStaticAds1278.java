package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateTotalChangeValueCalculationStaticAds1278 extends UcqcHelper {

  private static CostingMap costingMap;
  int numberOfGridLinesToTestLimit = 11;

  static final String[] requiredFields = {
    "QA Marina",
    "ADS-302 LG By Month",
    "150 Marina Medical Center",
    "Q302  QA Dept for ADS-302",
    "Apr 2004 to Apr 2004"
  };

  //NOTE NEEDS UPDATING: This script should be updated to run the calculation and then test the result, since the
  //current approach of pointing to existing data produces issues.

  /** Test ticket: ADS-1278 (Total Change column populates as Total Quick Cost less Total Unit Cost). Dev story ADS-671.
   * This script checks the existing Total Change column values (and calculation) without running the calculate operation.
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + ValidateTotalChangeValueCalculationStaticAds1278.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @Test
  public void test01ValidateTotalChangeValueCalculationWithDefaultValues() throws InterruptedException {
    for (int i = 1; i < numberOfGridLinesToTestLimit; i++) {
      assertChangeColumnCalculation(i,
          "Total Change", "Total Unit Cost", "Total Quick Cost", printout);
      assertThatValueHasRequiredDecimalPlaces(ucqcGetChargeCodeGridCellValueForGivenRowNumber(i,
          "Total Change", printout),2, printout);
    }
  }

  @Test
  public void test02ValidateTotalChangeValueHasTwoDecimalPlaces() throws InterruptedException {
    for (int i = 1; i < numberOfGridLinesToTestLimit; i++) {
      assertThatValueHasRequiredDecimalPlaces(ucqcGetChargeCodeGridCellValueForGivenRowNumber(i,
          "Total Change", printout),2, printout);
    }
  }

  @Ignore
  @Test
  public void test03ValidateTotalChangeColumnNegativeValuesDisplayInRed() throws InterruptedException {
    //Validate that negative values are displayed in red.
    // do this manually
  }

  @Ignore
  @Test
  public void test04ValidateTotalChangeColumnZeroValuesAreNotDisplayedInRed() throws InterruptedException {
    //Validate that zero values are NOT displayed in red.
    // do this manually
  }

  @Ignore
  @Test
  public void test05ValidateThatWhenTotalUnitCostAndTotalQuickCostAreNullThenTotalChangeIsAlsoNull() throws InterruptedException {
    //Validate that when Total Unit Cost and Total Quick Cost are null that the Total Change column is also null
    //do this manually
  }

}
