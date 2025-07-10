package webdriver.scripts.cim;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20030 **/
public class CreateNewCostIntegrationManagerScreen extends CimHelper {
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("CreateNewCostIntegrationManagerScreen", "webdriver.scripts.cim",
				"CreateNewCostIntegrationManagerScreen");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateHeaders_Links_20030() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimHeaderTitle());
			assertElementIsDisplayed(cimMap.getcimBreadCrumbTitle());
			assertElementIsDisplayed(cimMap.getcimHelpLink());
			assertElementIsDisplayed(cimMap.getcimDockItem());
			ExtentReport.logPass("PASS", "test01ValidateHeaders_Links_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateHeaders_Links_20030", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateCim_Buttons_20030() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimNewBtn());
			assertElementIsDisplayed(cimMap.getcimEditBtn());
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimDeleteButton());
			ExtentReport.logPass("PASS", "test02ValidateCim_Buttons_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateCim_Buttons_20030", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test03ValidateCim_Columns_20030() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimNameHeader());
			assertElementIsDisplayed(cimMap.getcimDescHeader());
			assertElementIsDisplayed(cimMap.getcimCalcStatusHeader());
			assertElementIsDisplayed(cimMap.getcimLastStartTime());
			assertElementIsDisplayed(cimMap.getcimLastEndTime());
			assertElementIsDisplayed(cimMap.getcimNextStartTime());
			ExtentReport.logPass("PASS", "test03ValidateCim_Columns_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateCim_Columns_20030", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateCim_ColumnSort_20030() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimSortNameAsec());
			validateSort(cimMap.getcimNameHeader(),cimMap.getcimSortNameDesc());
			validateSort(cimMap.getcimDescHeader(),cimMap.getcimSortDescAsec());
			validateSort(cimMap.getcimDescHeader(),cimMap.getcimSortDescDesc());
			validateSort(cimMap.getcimLastStartTime(),cimMap.getcimSortLastStartTimeAsec());
			validateSort(cimMap.getcimLastStartTime(),cimMap.getcimSortLastStartTimeDesc());
			validateSort(cimMap.getcimLastEndTime(),cimMap.getcimSortLastEndTimeAsec());
			validateSort(cimMap.getcimLastEndTime(),cimMap.getcimSortLastEndTimeDesc());
			validateSort(cimMap.getcimNextStartTime(),cimMap.getcimSortNextStartTimeAsc());
			validateSort(cimMap.getcimNextStartTime(),cimMap.getcimSortNextStartTimeDesc());
			validateSort(cimMap.getcimCalcStatusHeader(),cimMap.getcimSortCalcStatusAsec());
			validateSort(cimMap.getcimCalcStatusHeader(),cimMap.getcimSortCalcStatusDesc());
			ExtentReport.logPass("PASS", "test04ValidateCim_ColumnSort_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateCim_ColumnSort_20030", driver, e);
			fail(e.getMessage());
		}
	
		}
	@Test
	public void test05ValidateCim_Pagination_Buttons_20030() throws Throwable {
		try {
			assertElementIsDisplayed(cimMap.getcimPaginInput());
			assertElementIsDisplayed(cimMap.getcimPaginGoBtn());
			assertElementIsDisplayed(cimMap.getcimPaginNextBtn());
			assertElementIsDisplayed(cimMap.getcimPaginLastBtn());
			doClick(cimMap.getcimPaginNextBtn());
			assertElementIsDisplayed(cimMap.getcimPaginPreviousBtn());
			assertElementIsDisplayed(cimMap.getcimPaginPreviousFirstBtn());
			ExtentReport.logPass("PASS", "test05ValidateCim_Pagination_Buttons_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateCim_Pagination_Buttons_20030", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06Validate_CalculationStatus_PENDING_20030() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("PENDING", cimScenarioCreate);
			deleteCim();
			ExtentReport.logPass("PASS", "test06Validate_CalculationStatus_PENDING_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06Validate_CalculationStatus_PENDING_20030", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07Validate_CalculationStatus_COMPLETED_20030() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("COMPLETED", cimScenarioCreate);
			deleteCim();
			ExtentReport.logPass("PASS", "test07Validate_CalculationStatus_COMPLETED_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07Validate_CalculationStatus_COMPLETED_20030", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08Validate_CalculationStatus_FAILED_20030() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("FAILED", cimScenarioCreate);
			deleteCim();
			ExtentReport.logPass("PASS", "test08Validate_CalculationStatus_FAILED_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08Validate_CalculationStatus_FAILED_20030", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test09Validate_CalculationStatus_CANCELLED_20030() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("CANCELLED", cimScenarioCreate);
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test09Validate_CalculationStatus_CANCELLED_20030");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09Validate_CalculationStatus_CANCELLED_20030", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
	
		ExtentReport.report.flush();

	}
}

