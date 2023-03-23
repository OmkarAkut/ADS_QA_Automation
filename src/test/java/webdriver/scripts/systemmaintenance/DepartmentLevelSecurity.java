package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-5791 **/
//test steps are not clear , no test data has been mentioned, automated till step13
public class DepartmentLevelSecurity extends CalculationHelper{
	
		static CostingMap costing;
		static ContractingMap contractMap;
		static ModelLibraryMap modelMap;
		final static String aTozPage = "Department Masters";
		static String costModel = "0-MarinaCostModel";
		static String costCompMaster = "*FZ Small Hierarchy Test";
		static String[] filter = { "Code", "Is", "Equal To", "300" };
		static String deptMaster="";
		@BeforeClass
		public static void setupScript() throws Exception, Throwable {
			ExtentReport.reportCreate("EntityLevelSecurityCosting", "webdriver.scripts.costing",
					"EntityLevelSecurityCosting");
			try {
				costing = BuildMap.getInstance(driver, CostingMap.class);
				contractMap = BuildMap.getInstance(driver, ContractingMap.class);
				modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
				Login.loginUser("AutomationTesterAdmin");
				goToPage("Maintain Data");
				waitForDisplayedSpinnerToEnd();
				selectMaintainDataAtoZ(aTozPage);
				ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
		}
		@Test
		public void test01DepartmentMasters() throws Throwable {
			try {
				doClick(CostingMap.getCostDeptMasterFilterButton());
				doFilterCreate(filter);
				doClick(CostingMap.getCostDeptMasterEditButton());
				waitForAjaxExtJs();
				doClick(CostingMap.getDepartmentCodeFilterButton());
				assertTextIsDisplayed("Filter to Match These Criteria 1/1");
				doClick(costing.getCostModelScenarioCalculationFilterButtonCancelAndClose());
				doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
				ExtentReport.logPass("PASS", "test01DepartmentMasters");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01DepartmentMasters", driver, e);
				fail(e.getMessage());
			}
		}
		@AfterClass
		public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
