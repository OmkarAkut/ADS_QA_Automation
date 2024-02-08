package Regression;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
public class UIValidationPrepareTables extends CalculationHelper{
	private static ContractingMap modelMap;
	static final String ContractModelName = "ADS-1320 Contract Model D";
	static String currentDateTime = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String medicareCode = currentDateTime.replaceAll("\\W", "");
	static String filter[]= {"Code","Is","Equal To",medicareCode};
	/** Regression: Automated test script for ADS-6466 ,,ADS-6461,ADS-6455,ADS-6442 ,ADS-6462,ADS-6463*/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UIValidationPrepareTables",
				"webdriver.scripts.contracting",
				"UIValidationPrepareTables");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6463
	@Test
	public void AssertPrepareTables() throws Throwable {
		try {
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1000);
			assertTextIsDisplayed("Unpublished Contract Task List");
			assertTextIsDisplayed("Build Structure Elements");
			assertTextIsDisplayed("Model Contract");
			assertTextIsDisplayed("Publish Contract");
			assertTextIsDisplayed("Export Contract");
			doClickTreeItem("Build Structure Elements");
			waitForPresenceOfElementText("Prepare RBRVS Tables");
			doClickTreeItem("Prepare RBRVS Tables");
			waitForPresenceOfElementText("Prepare RBRVS RVU Tables");
			doClickTreeItem("Prepare RBRVS RVU Tables");
			waitForPresenceOfElementText("Global Periods");
			//ADS-6461
			assertTextIsDisplayed("Global Periods");
			assertTextIsDisplayed("Status Indicators");
			assertTextIsDisplayed("RBRVS RVU Tables");
			assertTextIsDisplayed("RBRVS Conversion Factor Tables");
			assertTextIsDisplayed("RBRVS DRA Outpatient Cap Tables");
			doClickTreeItem("Prepare GPCI Tables");
			waitForPresenceOfElementText("Medicare Carriers");
			assertTextIsDisplayed("Medicare Carriers");
			assertTextIsDisplayed("Medicare Localities");
			assertTextIsDisplayed("GPCI Tables");
			assertTextIsDisplayed("Site of Service Tables");
			doClickTreeItem("Update Indicators");
			assertElementIsDisplayed(ContractingMap.getUpdateIndicatorsPage());
			doClick(ContractingMap.getUpdateIndicatorsEditButton());
			waitForElementToBeVisible(ContractingMap.getUpdateIndicatorsEditPopUp());
			assertElementIsDisplayed(ContractingMap.getUpdateIndicatorsEditPopUp());
			doClick(modelMap.getContractModelRiskLimiterCancelCloseBtn());
			doClickTreeItem("Medicare Carriers");
			waitForElementToBeVisible(ContractingMap.getNewButtonMedicare());
			doClick(ContractingMap.getNewButtonMedicare());
			waitForElementToBeVisible(ContractingMap.getNewCodePopUp());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(), medicareCode);
			doClick(ContractingMap.getContractSaveCloseButton());
			doClick(ContractingMap.getNewCodeFilterButton());
			doFilterCreate(filter);
			doClick(ContractingMap.getNewCodeDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			assertTextIsDisplayed("There is no data available to display.");
			doClick("//span[text()='ADS-1320 Contract...']//following::span[@class='x-tab-close-btn']");
			ExtentReport.logPass("PASS", "AssertPrepareTables");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "AssertPrepareTables", driver, e);
			fail(e.getMessage());
		} 
		finally{
			doClosePageOnLowerBar("Contract Models");

		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
	
}
