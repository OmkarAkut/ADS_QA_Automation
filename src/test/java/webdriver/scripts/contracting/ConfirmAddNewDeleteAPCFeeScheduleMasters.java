package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ConfirmAddNewDeleteAPCFeeScheduleMasters extends CalculationHelper {
	
	private static ContractingMap modelMap;
	static final String ContractModelName = "ADS-1320 Contract Model D";
	static String currentDateTime = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String APCCode = currentDateTime.replaceAll("\\W", "");
	static String filter[]= {"Code","Is","Equal To",APCCode};
	/** Regression: Automated test script for ADS-6458,ADS-6457 ,ADS-6455 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("APCFeeScheduleMasters",
				"webdriver.scripts.contracting",
				"APCFeeScheduleMasters");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ConfirmAddandDeleteNewCodeUnderAPCFeeScheduleMasters() throws Throwable {
		try {
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			//ADS-6455
			assertTextIsDisplayed("Unpublished Contract Task List");
			assertTextIsDisplayed("Build Structure Elements");
			assertTextIsDisplayed("Model Contract");
			assertTextIsDisplayed("Publish Contract");
			assertTextIsDisplayed("Export Contract");
			doClickTreeItem("Build Structure Elements");
			driverDelay(300);
			assertTextIsDisplayed("Contract Types");
			assertTextIsDisplayed("Services");
			assertTextIsDisplayed("Level of Care Tables");
			assertTextIsDisplayed("Populations");
			assertTextIsDisplayed("Membership Classification Schemes");
			assertTextIsDisplayed("ASC Schemes");
			assertTextIsDisplayed("Fee Schedule Masters");
			doClickTreeItem("Fee Schedule Masters");
			driverDelay(300);
			assertTextIsDisplayed("APC Fee Schedule Masters");
			assertTextIsDisplayed("APG Fee Schedule Masters");
			assertTextIsDisplayed("Chargeable Activity Fee Schedule Masters");
			doClickTreeItem("Price Lists");
			driverDelay(300);
			assertTextIsDisplayed("Price Lists");
			assertTextIsDisplayed("Price List Calculation Scenarios");
			doClickTreeItem("Prepare RBRVS Tables");
			driverDelay(300);
			assertTextIsDisplayed("Update Indicators");
			assertTextIsDisplayed("Prepare RBRVS RVU Tables");
			assertTextIsDisplayed("Prepare GPCI Tables");
			assertTextIsDisplayed("Site of Service Tables");
			
			doClickTreeItemWithCheckbox("APC Fee Schedule Masters");
			waitForElementToBeVisible(ContractingMap.getApcFeeScheduleHeader());
			doClick(ContractingMap.getNewButtonAPC());
			waitForElementToBeVisible(ContractingMap.getMedicareCode());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(), APCCode);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			doClick(ContractingMap.getNewAPCodeFilterButton());
			doFilterCreate(filter);
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='"+APCCode+"']")));
			//ADS-6457
			doClick(ContractingMap.getNewAPCodeDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			assertTextIsDisplayed("There is no data available to display.");
			doClosePageOnLowerBar("ADS-1320 Contract...");
			ExtentReport.logPass("PASS", "test01ConfirmAddandDeleteNewCodeUnderAPCFeeScheduleMasters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ConfirmAddandDeleteNewCodeUnderAPCFeeScheduleMasters", driver, e);
			fail(e.getMessage());
		} 
		finally{
			doClosePageOnLowerBar("Model Library");

		}
	}
}
