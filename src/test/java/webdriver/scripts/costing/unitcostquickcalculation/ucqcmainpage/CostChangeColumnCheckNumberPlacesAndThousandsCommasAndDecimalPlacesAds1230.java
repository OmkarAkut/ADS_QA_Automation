package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230 extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap costingMap;
  static final String[] requiredFields = {
    "Marina",
    "*DM ADS-673 C",
    "150 Marina Medical Center",
    "2110  ICU",
    "Apr 2004 to Mar 2005"
  };

  /**
   * Test ticket: ADS-1230 (Dev Story ADS-673).
   * This script tests that the CC Change columns allow places up to 100 million */
  @BeforeClass
  public static void setupScript () throws InterruptedException {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230.class.getSimpleName());
    evolveLoginStaticUser(Users.CostingDepartmentManager1);
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @Test
  public void test01AssertLeadingDecimalPlaceForChangeColumnValueThatIsLessThanOne() {
    try {
      getCellValueAndAssertDecimalPlace("1100130", "Hospital Overhead Change");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02GetCellValueAndAssertOnesPlace() throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100148", "Hospital Overhead Change", printout),
      "1", 2, printout);
  }

  @Test
  public void test03GetCellValueAndAssertTensPlace() throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100171", "Hospital Overhead Change", printout),
      "10", 2, printout);
  }

  @Test
  public void test04GetCellValueAndAssertHundredsPlace() throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100189", "Hospital Overhead Change", printout),
      "100", 2, printout);
  }

  @Test
  public void test05GetCellValueAndAssertThousandsPlace() throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100221", "Hospital Overhead Change", printout),
      "1,000", 2, printout);
  }

  @Test
  public void test06GetCellValueAndAssertTenThousandsPlace () throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100247", "Hospital Overhead Change", printout),
      "10,000", 2, printout);
  }

  @Test
  public void test07GetCellValueAndAssertHundredThousandsPlace () throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100270", "Hospital Overhead Change", printout),
      "100,000", 2, printout);
  }

  @Test
  public void test08GetCellValueAndAssertMillionsPlace () throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100528", "Hospital Overhead Change", printout),
      "1,000,000", 2, printout);
  }

  @Test
  public void test09GetCellValueAndAssertTenMillionsPlace () throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100569", "Hospital Overhead Change", printout),
      "10,000,000", 2, printout);
  }

  @Test
  public void test10GetCellValueAndAssertHundredMillionsPlace () throws InterruptedException {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100650", "Hospital Overhead Change", printout),
      "100,000,000", 2, printout);
  }
}
