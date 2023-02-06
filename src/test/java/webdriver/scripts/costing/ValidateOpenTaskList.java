package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ValidateOpenTaskList extends GoHelper {
	static CostingMap costing;
	static ContractingMap modelMap;
	static String costModel="Actual Cost Model";
/** Automates test ticket ADS-6642*/
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateOpenTaskList", "webdriver.scripts.costing", "ValidateOpenTaskList");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ExpandCosting() throws Throwable {
		try {
			ContractModelsHelper.doClickTreeData("Costing");
			driverDelay(3000);
			ContractModelsHelper.scrollToView("//div[text()='Tinesha']");
			ContractModelsHelper.doClickTreeData("Tinesha");
			driverDelay(3000);
			doClick("//div[text()='Actual Cost Method']");
			ExtentReport.logPass("PASS", "test01ExpandCosting");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ExpandCosting", driver, e);
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void test02OpenTaskList() throws Throwable {
		try {
			doSearchForModel(costModel);
			tableClickCellFirstColumn(costModel);
			doClickButton("Open Task List");
			waitForPageTitle(costModel);
			assertThatString(driver.findElement(By.xpath("//span[contains(@class, 'header') and text()='"+costModel+"']")), costModel, printout);
			ExtentReport.logPass("PASS", "test02OpenTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenTaskList", driver, e);
			fail(e.getMessage());
		}
	
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar(costModel);
		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}
}
