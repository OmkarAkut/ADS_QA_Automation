package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostModelScenarioResults extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozCostModelScenarioResults = "Cost Model Scenario Results";
	public static DialogsMap dialog;
	static String[] costModelScenarioResultsFilter= {"Cost Model Scenario Name","Is","Equal To","*CM1 TB MHFY05 After Vol Change"};
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CostModelScenarioResults", "webdriver.scripts.datamaintenance.az",
				"CostModelScenarioResults");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozCostModelScenarioResults);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateViewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costModelScenarioResultsFilter);
			doClick(DataMaintenanceMap.getazViewBtn());
			ExtentReport.logPass("PASS", "test01ValidateViewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateViewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateApplySelectionsButton() throws Throwable {
		try {
			clickCheckboxByName("enttityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostScenarioEntitycode(), "150 Marina Medical Center");
			clickCheckboxByName("deptName");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostScenarioDept(), "2130 PED ICU");
			clickCheckboxByName("resultStored");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostScenarioResults(), "Jan 2005 to Jan 2005");
			clickCheckboxByName("costComp");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostScenarioComponent(), "Salaries and Wages");
			doClickButton("Apply Selections");
			assertElementIsDisplayedWithXpath("(//span[text()='Volume']//following::div[text()][1])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Charges']//following::div[text()][1])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Direct']//following::div[text()][1])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Overhead']//following::div[text()][1])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Total Costs']//following::div[text()][1])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Cost']//following::div[text()][1])[1]");
			assertListElementsAreDisplayed(driver.findElements(By.xpath("//div[text()='Cost Details']//following::div[@class='x-grid-item-container']//table")), printout);
			assertElementIsDisplayedWithXpath("//span[text()='Cost Model']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='Cost Model Scenario']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='Cost Component Master']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='GL Data Scenario']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='Activity Volume Data Scenario']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='Cost Method Master']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='Cost Component Variability Master']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='Start Month']//following::div[text()][1]");
			assertElementIsDisplayedWithXpath("//span[text()='End Month']//following::div[text()][1]");
			doClick(DataMaintenanceMap.gethideButton());
			assertElementIsDisplayed(DataMaintenanceMap.getshowButton());
			doClick(DataMaintenanceMap.gethideButton());
			assertElementIsDisplayed(DataMaintenanceMap.getshowButton2());
			doClick(DataMaintenanceMap.getshowButton());
			doClick(DataMaintenanceMap.getshowButton());
			doClickButton("Close");
			ExtentReport.logPass("PASS", "test02ValidateApplySelectionsButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateApplySelectionsButton", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
