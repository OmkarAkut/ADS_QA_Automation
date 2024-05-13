package Regression;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ValidateContracDataMaintenanceBubble extends CalculationHelper {

	private static ContractingMap modelMap;
	static final String ContractType = "Fee Schedule Masters";
	static final String ContractTypeTest="end-to-end tests";
	static final String ContractModel = "ASESC-20 1";
	static final String ContractTypeAPC = "APC Fee Schedule Masters";
	static String bubbleColorExpected="rgba(127, 35, 111, 1)";
	/** Regression: Automated test script for ADS-6430 ,ADS-6428 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateContracDataMaintenanceBubble", "webdriver.scripts.contracting",
				"ValidateContracDataMaintenanceBubble");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
//			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
//			doClick(ContractingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6430,ADS-6428[color verification ]
	@Test
	public void test01ValidateContractingBubleColor_6430_6428_6429() throws Throwable {
		try {
			UcqcHelper.validateBackgroundColor(bubbleColorExpected,ContractingMap.getContractingBubble());
			assertElementIsDisplayed(ContractingMap.getContractingBubble());
			assertElementIsDisplayed(ContractingMap.getLandingPageContractModelBubble());
			doClick(ContractingMap.getContractDataMaintenance());
			driverDelay(600);
			doClickTreeData("Contracting");
			driverDelay(1200);
			doClickTreeData(ContractType);
			doClickTreeSubItem(ContractTypeAPC);
//			Omkar 8/6/2023 : xpath changes for 11.2
//			doClick(ContractingMap.ContractTypeEditButton());
			doClick(ContractingMap.apcFeeScheduleMastersPageEditButton());
			/*Omkar 4/7/2023 : Below validation is wrongly put as per ADS-6430
			Changing the validation to see if the expected tabs are available after clicking on edit button
			assertThatAttributeValue(ContractingMap.getInputName(), ContractTypeAPC, printout); */
			assertElementIsDisplayed(ContractingMap.CodeFieldLabel());
			assertElementIsDisplayed(ContractingMap.FieldSpecificationTab());
			assertElementIsDisplayed(ContractingMap.TemplatesTab());
			assertElementIsDisplayed(ContractingMap.SchedulesTab());
			doClick(modelMap.getContractModelRiskLimiterCancelCloseBtn());
			goToPage("Contract Models");
			waitForElementToBeVisible(modelMap.getContractingTreeExpand());
			doClick(modelMap.getContractingTreeExpand());
			driverDelay(500);
			doClickTreeData("Jordan");
			driverDelay(200);
//			Omkar 4/8/2023 : xpath changes for 11.2
//			driver.findElement(By.xpath("//div[text()='"+ContractTypeTest+"']"));
			driver.findElement(By.xpath("//span[text()='"+ContractTypeTest+"']"));
			doSearchForContractModel(ContractModel);
			tableDoubleClickCellFirstColumn(ContractModel);
			assertTextIsDisplayed("Unpublished Contract Task List");
			doClick("//span[text()='ASESC-20 1']//following::span[@class='x-tab-close-btn']");
			ExtentReport.logPass("PASS", "test01ValidateContractingBubleColor");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateContractingBubleColor", driver, e);
			fail(e.getMessage());
		} 
		finally {
			doClosePageOnLowerBar("Contract Models");
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
