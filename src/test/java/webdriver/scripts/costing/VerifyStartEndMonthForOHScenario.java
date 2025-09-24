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
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-12614 **/
public class VerifyStartEndMonthForOHScenario extends GoHelper{
	static String costModel = "2005 Overhead Allocation-Test2023";
	static ContractingMap modelMap;
	static CostingMap costing;
	static ContractingMap contractMap;
	static String CalculationScenario = "V11.2 Overhead Scenario";
	static String[] filter = { "Name", "Is", "Equal To", CalculationScenario };
	static String startMonth;
	static String endMonth;
	static String savedStartMonth;
	static String savedEndMonth;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("VerifyStartEndMonthForOHScenario", "webdriver.scripts.costing.costingcalculations",
				"VerifyStartEndMonthForOHScenario");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01VerifyStartEndMonthForOHScenario_12614() throws Throwable {
		try {
			doSearchForModel("2005 Overhead Allocation-Test2023");
			tableDoubleClickCellFirstColumn("2005 Overhead Allocation-Test2023");
			driverDelay(4000);
			doClickTreeItem("Allocate Overhead");
			driverDelay(4000);
			doClickTreeItem("Overhead Model Calculation Scenarios");
			doClick(CostingMap.getOverheadModelFilterButton());
			doFilterCreate(filter);
			 startMonth=driver.findElement(By.xpath("//div[text()='"+CalculationScenario+"']//following::div[1]")).getText();
			 endMonth=driver.findElement(By.xpath("//div[text()='"+CalculationScenario+"']//following::div[2]")).getText();
			tableDoubleClickCellFirstColumn(CalculationScenario);
			doClick(CostingMap.getCostModelStartMonthDrpdown());
			 savedStartMonth=driver.findElement(By.xpath("(//li[contains(@class,'x-boundlist-item x-boundlist-selected')])[1]")).getText();
			assertEqualsString(startMonth, savedStartMonth);
			doClick(CostingMap.getCostModelEndMonthDrpdown());
			 savedEndMonth=driver.findElement(By.xpath("(//li[contains(@class,'x-boundlist-item x-boundlist-selected')])[2]")).getText();
			assertEqualsString(endMonth, savedEndMonth);
			doClick(costing.getEncCostModelCancelCloseButton());
			doClosePageOnLowerBar("2005 Overhead...");
			ExtentReport.logPass("PASS", "test01VerifyStartEndMonthForOHScenario_12614");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyStartEndMonthForOHScenario_12614", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
