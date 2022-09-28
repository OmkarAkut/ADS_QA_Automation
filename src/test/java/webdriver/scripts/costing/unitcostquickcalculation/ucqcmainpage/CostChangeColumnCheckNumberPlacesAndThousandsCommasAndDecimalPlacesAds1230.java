package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
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
//    "2110  ICU",
    "2110",
    "Apr 2004 to Mar 2005"
  };

  /**
   * Test ticket: ADS-1230 (Dev Story ADS-673).
   * This script tests that the CC Change columns allow places up to 100 million 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript () throws Throwable {
	  
	  ExtentReport.reportCreate("CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230");
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230.class.getSimpleName());
		evolveLoginStaticUser(Users.CostingDepartmentManager1);
		doMaximizeWindow();
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
		ucqcDisplayChargeCodeGrid(requiredFields);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01AssertLeadingDecimalPlaceForChangeColumnValueThatIsLessThanOne() throws Throwable {
    try {
      getCellValueAndAssertDecimalPlace("1100130", "Hospital Overhead Change");
      ExtentReport.logPass("PASS", "test01AssertLeadingDecimalPlaceForChangeColumnValueThatIsLessThanOne");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01AssertLeadingDecimalPlaceForChangeColumnValueThatIsLessThanOne", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02GetCellValueAndAssertOnesPlace() throws Throwable {
	  
    try {
    	Thread.sleep(3000);
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100148", "Hospital Overhead Change", printout),"1", 2, printout);
		ExtentReport.logPass("PASS", "test02GetCellValueAndAssertOnesPlace");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test02GetCellValueAndAssertOnesPlace", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03GetCellValueAndAssertTensPlace() throws Throwable {
	  try {
    assertValueFormat(ucqcGetChargeCodeGridCellValue("1100171", "Hospital Overhead Change", printout),"10", 2, printout);
    ExtentReport.logPass("PASS", "test03GetCellValueAndAssertTensPlace");
	  }
	  catch(Exception|AssertionError e) {
		  ExtentReport.logFail("FAIL","test03GetCellValueAndAssertTensPlace", driver,e);
			fail(e.getMessage());
	  }
  }

  @Test
  public void test04GetCellValueAndAssertHundredsPlace() throws Throwable {
	  
    try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100189", "Hospital Overhead Change", printout),"100", 2, printout);
		 ExtentReport.logPass("PASS", "test04GetCellValueAndAssertHundredsPlace");
	} catch (Exception|AssertionError e) {
		 ExtentReport.logFail("FAIL","test04GetCellValueAndAssertHundredsPlace", driver,e);
			fail(e.getMessage());
	}
  }

  @Test
  public void test05GetCellValueAndAssertThousandsPlace() throws Throwable {
	  
    try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100221", "Hospital Overhead Change", printout),"1,000", 2, printout);
		 ExtentReport.logPass("PASS", "test05GetCellValueAndAssertThousandsPlace");
	} catch (Exception|AssertionError e) {
		 ExtentReport.logFail("FAIL","test05GetCellValueAndAssertThousandsPlace", driver,e);
			fail(e.getMessage());
	}
  }

  @Test
  public void test06GetCellValueAndAssertTenThousandsPlace () throws Throwable {
   
	  try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100247", "Hospital Overhead Change", printout),
		  "10,000", 2, printout);
		ExtentReport.logPass("PASS", "test06GetCellValueAndAssertTenThousandsPlace");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test06GetCellValueAndAssertTenThousandsPlace", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test07GetCellValueAndAssertHundredThousandsPlace () throws Throwable {
    try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100270", "Hospital Overhead Change", printout),
		  "100,000", 2, printout);
		ExtentReport.logPass("PASS", "test07GetCellValueAndAssertHundredThousandsPlace");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test07GetCellValueAndAssertHundredThousandsPlace", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test08GetCellValueAndAssertMillionsPlace () throws Throwable {
    try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100528", "Hospital Overhead Change", printout),
		  "1,000,000", 2, printout);
		ExtentReport.logPass("PASS", "test08GetCellValueAndAssertMillionsPlace");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test08GetCellValueAndAssertMillionsPlace", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test09GetCellValueAndAssertTenMillionsPlace () throws Throwable {
    try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100569", "Hospital Overhead Change", printout),
		  "10,000,000", 2, printout);
		ExtentReport.logPass("PASS", "test09GetCellValueAndAssertTenMillionsPlace");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test09GetCellValueAndAssertTenMillionsPlace", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test10GetCellValueAndAssertHundredMillionsPlace () throws Throwable {
    try {
		assertValueFormat(ucqcGetChargeCodeGridCellValue("1100650", "Hospital Overhead Change", printout),
		  "100,000,000", 2, printout);
		ExtentReport.logPass("PASS", "test10GetCellValueAndAssertHundredMillionsPlace");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test10GetCellValueAndAssertHundredMillionsPlace", driver,e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
