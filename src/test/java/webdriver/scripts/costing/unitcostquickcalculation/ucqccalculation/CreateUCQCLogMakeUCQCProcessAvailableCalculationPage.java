package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.JavaHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class CreateUCQCLogMakeUCQCProcessAvailableCalculationPage extends UcqcHelper {
	private static CostingMap costingMap;
	static ContractingMap contractingMap;
//	static final String[] requiredFields = { "Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
//			"2130", "Jan 2005 to Mar 2005" };
	//Shilpa :Update 15.01.2026
	static final String[] requiredFields = { "Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
			"2130", "Jan 2005 to Mar 2005" };
	CalculationHelper calculationHelper = new CalculationHelper();
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costModel = "Testing" + currentDateTime;
	String[] filterScenario = { "Scenario Name", "Is", "Equal To", "*CM1 TB MHFY05 After Vol Change_UCQC" };
	CalculationHelper helper=new CalculationHelper();
	// Regression Test ADS-5923 **/
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("CreateUCQCLogMakeUCQCProcessAvailableCalculationPage",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation",
				"CreateUCQCLogMakeUCQCProcessAvailableCalculationPage");

		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
			
//			Login.loginUser("CostingDepartmentManager1"); check in stage if calculation status updated
			Login.loginUser("AutomationTesterAdmin");
			webdriverMaximizeWindow();
			goToPage("Unit Cost Quick Calculation");
			waitForAjaxExtJs();
			ucqcDisplayChargeCodeGrid(requiredFields);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}
//ADS-5923
	@Test
	public void test01ChangeValues_ADS_5923() throws Throwable {
		int value = JavaHelper.javaGetRandomNumber(12, printout);
		ucqcUpdateGridCellValue("2200343", "Quick Salaries and Wages RVU", String.valueOf(value), printout);
		doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
		driverDelay(3000);
		doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
		waitForDisplayedSpinnerToEnd();
		driverDelay(10000);
		goToPage("Calculation Status");
		waitForDisplayedSpinnerToEnd();
		helper.doFilterCalculationPage(filterScenario);
		CalculationHelper.waitForFirstRowCalculationBarToReach100Percent();
		driverDelay();
		//Shilpa updated 10.18.2024 ,sometimes file directly downloads 
		try {
			doClick("(//a[text()='Download'])[1]");
			//Shilpa: script update 11.2 on 20.5.2024
			waitForPresenceOfElementText("Download Log");
			doactionClick(driver.findElement(By.xpath("(//input[@name='hostLocation']/../..)")));
			doClick(contractingMap.getContractModelCalcFileSharedLocOption());
			doClick("(//input[@name='logLocation'])");
			ContractModelsHelper.keyInValues(contractingMap.getContractCalcFilename(), "Testing"+currentDateTime);
//			doactionClick(contractingMap.getContractModelCalcContinueBtn());
			//Shilpa Xpath update for 11.2 on 28.5.2024
			doClick("(//span[text()='Continue']/../../..)");
			waitForDisplayedSpinnerToEnd();
			driverDelay();
//			calculationHelper.calculationStatusPageOpenViewDialog();
			//Shilpa: Update for 11.2 : 17.03.2025
			calculationHelper.calculationStatusPageOpenViewDialog();
			calculationHelper.closeViewDialog();
		}catch(Exception e) {
			calculationHelper.calculationStatusPageOpenViewDialog();
			calculationHelper.closeViewDialog();
		}
		

		
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Calculation Status");
		doClosePageOnLowerBar("Unit Cost Quick Calculation");
		ExtentReport.report.flush();

	}
}
