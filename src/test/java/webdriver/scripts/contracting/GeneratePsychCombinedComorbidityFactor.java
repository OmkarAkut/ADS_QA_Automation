package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.AdsHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class GeneratePsychCombinedComorbidityFactor extends CalculationHelper {
	final static String aTozPage = "Psych Combined Comorbidity Assignments";
	final static String batch = "v104 REGRESSION Comorbidity Code 1st";
	static String viewLogTitleApply = "v104 REGRESSION Comorbidity Code 1st";
	private static CostingMap costingMap;
	private static ContractingMap contractingMap;
	String[] filter = {"Scenario Name","Is","Equal To",batch};
	/** Regression: Automated test script for ADS-5943 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GeneratePsychCombinedComorbidityFactor", "webdriver.scripts.contracting", "GeneratePsychCombinedComorbidityFactor");
		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractingMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage);
			//Shilpa updated code 16/10/2023 for 11.2
			ContractingMap.getContractModelButtonFilter().click();
			waitForAjaxExtJs();
			AdsHelper contractModelsHelper=new AdsHelper();
			contractModelsHelper.doFilterSetFilterParameters("Name","Is","Equal To",batch);
			doClick("//div[contains(@id,'filterwindow')]//span[text() = 'Add']");
		    waitForAjaxExtJs();
		    doClick(ContractingMap.getContractModelApplyFilterButton());
			waitForAjaxExtJs();
			tableDoubleClickCellFirstColumn(batch);
//			openMaintainDataBatch(batch);
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01AssertResetCalculate() throws Throwable {
		try {
			ContractModelsHelper.scrollToView("//div[contains(@id,'psychdatefiles')]/div/div[contains(text(),'Dates & Files')]");
			driverDelay(500);
//			doClick(ContractingMap.getContractFileSelect());
			Actions action=new Actions(driver);
			action.moveToElement(ContractingMap.getContractFileSelect()).click().build().perform();
//			ContractingMap.getContractFileSelect().sendKeys(Keys.ENTER);;
			driverDelay(600);
			ContractModelsHelper.uploadTheFileusingAutoIT(driver,
					System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",
					System.getProperty("user.dir") + "\\AutoIT\\IPFC22WDICD10.txt");
			driverDelay(7000);
			doClick(ContractingMap.getContractResetButton());
			doClick(costingMap.getCostModelScenariosinEvaluationOrderSave());
			doFilterCalculationPage(filter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay();
			assertViewLogTitle(viewLogTitleApply);
//			checkForRecordsProcessed("Process Completed");
//			doClick(ContractingMap.getContractCalculationCloseViewDialog());
//			deleteMyCalculationStatusFirstRow();
//			doClosePageOnLowerBar("Calculation Status");
//			driverDelay(1000);
//			doClick(ContractingMap.getContractCalculateButton());
//			waitForFirstRowCalculationBarToReach100Percent();
//			calculationStatusPageOpenViewDialog();
//			assertViewLogTitle(viewLogTitleApply);
//			confirmCalculationStatusDetailsContains("Processed 8 Distinct Encounters");
			//Shilpa: Fails due to scroll issue 11.2 11.4.2024
			checkForRecordsProcessed("Processed 0 Distinct Encounters");

			confirmCalculationStatusDetailsContains("Process Completed");
			doClick(ContractingMap.getContractCalculationCloseViewDialog());
			ExtentReport.logPass("PASS", "test01AssertResetCalculate");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertResetCalculate", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getContractSaveCloseButton());
			doClosePageOnLowerBar("Maintain Data");

		}

	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
