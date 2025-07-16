package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.assertTrue;
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
public class TotalQuickCostColumnPopulatesAfterCalculateDatabaseChecks extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap costingMap;
  String chargeCode = "1100544";
  String headerName = "Total Quick Cost";
  String columnToUpdate = "Quick Salaries and Wages RVU";
  private static String initialTotalQuickCostCellValue = null;
  static String[] requiredFields = {
    "Marina",
    "*CM3 TB MHFY05 Before Vol Change",
    "150 Marina Medical Center",
//    "2130  PED ICU",
    //Shilpa 15.09.2022 updated to 2130
    "2130",
    "Apr 2004 to Mar 2005"
  };

  //db connection
//  String db_url_evolve = "jdbc:oracle:thin:@192.168.210.100:1543:qacurr1";
//  String db_username = "qa";
//  String db_password = "pass";
//  private JavaDataBaseConnectivity jdbc = new JavaDataBaseConnectivity(db_url_evolve, db_username, db_password);

  //NOTE: The _UCQC in the cms.name value designates the "copy" that is necessary for the current test case (there is also a *CM3 TB MHFY05 Before Vol Change column without _UCQC)
  String sqlQuery = "SELECT a.DIR_FIXED_COST+a.DIR_VAR_COST+a.IND_FIXED_COST+a.IND_VAR_COST " +
      "FROM costmodel2 cm, costmodelscenario2 cms, entdeptcostcontainer2 ed, costitemdataset2 cids, activitycostitem2 a " +
      "WHERE cm.objectid=cms.costmodelid " +
      "and cms.objectid=ed.cstmodscenid " +
      "and ed.objectid=cids.containerid " +
      "and cids.objectid=a.masterid " +
      "AND cm.name='Marina' " +
      "and cms.name='*CM3 TB MHFY05 Before Vol Change_UCQC' " +
      "and ed.entitycode='150' " +
      "and ed.deptcode='2130' " +
      "and a.actdscrcode=1100544";

  /** Zephyr test ticket ADS-1278 (Dev Story ADS-671)
  This script confirms that the Total Quick Cost column populates after Calculate.  This script is an extension of
  TotalQuickCostColumnPopulatesAfterCalculateStatic and includes checks against the database values, which were not
  part of the acceptance criteria.  Incomplete - the sql query needs to be updated.
  Last Updated: 09-13-19 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("TotalQuickCostColumnPopulatesAfterCalculateDatabaseChecks","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "TotalQuickCostColumnPopulatesAfterCalculateDatabaseChecks");
	  
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + TotalQuickCostColumnPopulatesAfterCalculateDatabaseChecks.class.getSimpleName());
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

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("Unit Cost Quick...");
	ExtentReport.report.flush();
  }

  @Test (timeout = 300000)
  public void test01GetDefaultTotalQuickCostColumnValueAndCompareToDatabaseValue() throws Throwable {
	  
    try {
		initialTotalQuickCostCellValue = ucqcGetChargeCodeGridCellValue(chargeCode, headerName, printout);
		if (printout) {
		  System.out.println("Initial Total Quick Cost cell value: " + initialTotalQuickCostCellValue);
		}
		assertThatValueHasRequiredDecimalPlaces(initialTotalQuickCostCellValue,2, printout);
		
		//compareActualToDatabaseTotalQuickCostValues(chargeCode, headerName);
		ExtentReport.logPass("PASS", "test01GetDefaultTotalQuickCostColumnValueAndCompareToDatabaseValue");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test01GetDefaultTotalQuickCostColumnValueAndCompareToDatabaseValue", driver,e);
    	fail(e.getMessage());
	}
    
    
    
    
    
  }

  @Test (timeout = 300000)
  public void test02UpdateCellValueAndClickCalculateWaitForSpinnerToEndAndCompareUpdatedValueToDatabaseValue() throws Throwable {
	  
    try {
		ucqcGetChargeCodeGridCellValue(chargeCode, columnToUpdate, printout);
		ucqcUpdateGridCellValue(chargeCode, columnToUpdate,
		    String.valueOf(javaGetRandomNumber(100, printout)), printout);
		doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
		ucqcWaitForSpinnerToEndNoHardStop();
		waitForAjaxExtJs();
		String finalTotalQuickCostCellValue = ucqcGetChargeCodeGridCellValue(chargeCode, headerName, printout);
		assertThatValueHasRequiredDecimalPlaces(finalTotalQuickCostCellValue,2, printout);
		if (printout) {
		  System.out.println("Initial Total Quick Cost cell value: " + initialTotalQuickCostCellValue);
		  System.out.println("Final Total Quick Cost cell value: " + finalTotalQuickCostCellValue);
		}
		assertTrue(initialTotalQuickCostCellValue != finalTotalQuickCostCellValue);
		//compareActualToDatabaseTotalQuickCostValues(chargeCode, headerName);
		ExtentReport.logPass("PASS", "test02UpdateCellValueAndClickCalculateWaitForSpinnerToEndAndCompareUpdatedValueToDatabaseValue");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test02UpdateCellValueAndClickCalculateWaitForSpinnerToEndAndCompareUpdatedValueToDatabaseValue", driver,e);
    	fail(e.getMessage());
		
		
	}
    
  }

//  private void compareActualToDatabaseTotalQuickCostValues(String chargeCode, String headerName) throws InterruptedException, ClassNotFoundException {
//    double actualTotalQuickCost = Double.parseDouble(getCellValue(chargeCode, headerName));
//    jdbc.setSqlQuery(sqlQuery);
//    double dbTotalQuickCost = truncateDouble(jdbc.jdbcGetResultSetFirstRowFirstColumnsAsDouble(jdbc.getSqlQuery()), 2);
//    if (printout) {
//      System.out.println("Compare Actual value to Database value, Actual Total Quick Cost: " + actualTotalQuickCost);
//      System.out.println("Compare Actual value to Database value, db Total Quick Cost: " + dbTotalQuickCost);
//    }
//    assertTrue(dbTotalQuickCost == actualTotalQuickCost);
//  }
}

