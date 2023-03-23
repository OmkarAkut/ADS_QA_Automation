package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ValidateBenefitPlanInfoUpdate extends CalculationHelper {
	private static ContractingMap modelMap;
	private static String ContractModelA = "ADS 1321 FY2020 Test D";
	String[] columns = { "0000 PRIVATE PAY", "0001 PRIVATE PAY PENDING", "0002 APP PENDING", "0003 PRIVATE PAY INSUFF",
			"0004 PRIVATE PAY BAL" };
	String[] columnsToCompare = { "PRIVATE PAY", "PRIVATE PAY PENDING", "APP PENDING", "PRIVATE PAY INSUFF",
			"PRIVATE PAY BAL" };

	/** Regression: Automated test script for ADS-6076 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateBenefitPlanInfoUpdate", "webdriver.scripts.contracting",
				"ValidateBenefitPlanInfoUpdate");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");

			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doSearchForContractModel(ContractModelA);
			tableDoubleClickCellFirstColumn(ContractModelA);
			driverDelay(300);
			doClickTreeItem("Model Contract");
			waitForMainPageTitle("General Information - Unpublished Contract");
			doClickTreeItemWithCheckbox("General Information - Unpublished Contract");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01AddMultipleBenefitPlan() throws Throwable {
		try {
			doClick(ContractingMap.getDefinitionElementC1());
			doClick("(//span[contains(text(),'Benefit Plans')]//following::img)[1]");
			ContractModelsHelper.scrollToView(ContractingMap.getDefinitionElementAddBtn());
			doClick(ContractingMap.getDefinitionElementAddBtn());
			waitForMainPageTitle("Add Benefit Plans");
			ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
			doClick(ContractingMap.getContractModelApplyInExportPopUp());
			ContractModelsHelper.CompareListToArray(ContractingMap.getBenefitPlans(), columnsToCompare);
			doClick(ContractingMap.getSaveBenefitPlan());
			ContractModelsHelper.toggleBetweenTheDockBar("//span[text()='Model Library']//parent::button");
			ContractModelsHelper.toggleBetweenTheDockBar("//span[text()='ADS 1321 FY2020 Test D']//parent::button");
			ContractModelsHelper.scrollToView(ContractingMap.getDefinitionElementAddBtn());
			doClick(ContractingMap.getDefinitionElementAddBtn());
			waitForMainPageTitle("Add Benefit Plans");
			doClick(ContractingMap.getSelectAllBenefitPlans());
			doClick(ContractingMap.getContractModelApplyInExportPopUp());
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			assertElementIsDisplayed(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01AddMultipleBenefitPlan");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddMultipleBenefitPlan", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02ClearAllBenefitPlans() throws Throwable {
		try {
			doClickTreeItemWithCheckbox("General Information - Unpublished Contract");
			doClick(ContractingMap.getDefinitionElementC1());
			doClick("(//span[contains(text(),'Benefit Plans')]//following::img)[1]");
			ContractModelsHelper.CompareListToArray(ContractingMap.getBenefitPlans(), columnsToCompare);
			doClick(ContractingMap.getDefinitionElementAddBtn());
			waitForMainPageTitle("Add Benefit Plans");
			doClick(ContractingMap.getSelectAllBenefitPlans());
			doClick(ContractingMap.getSelectAllBenefitPlans());
			doClick(ContractingMap.getContractModelApplyInExportPopUp());
			doClick(ContractingMap.getSaveBenefitPlan());
			doClosePageOnLowerBar("ADS 1321 FY2020 Test D");
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test01AddMultipleBenefitPlan");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddMultipleBenefitPlan", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
