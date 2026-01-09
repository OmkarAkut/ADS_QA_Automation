package webdriver.scripts.contracting;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12493 , ADS-12494*/
public class ValidateCalculateForContractModel extends CalculationHelper{
	private static ContractingMap modelMap;
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static CreateANewContractModel model=new CreateANewContractModel();
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName ="Contract Model" + currentDateTime;;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("ValidateCalculateForContractModel", "webdriver.scripts.contracting",
				"ValidateCalculateForContractModel");
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
	public void test01ValidateWarningMessageForCalculate_12494() throws Throwable {
		try {
			model.createContractModel(contractModelName);;
			contractModelName=CreateANewContractModel.contractModelName;
			ValidateDragDropAddNewServiceUnderPricing.searchContractModelOpenTaskList(contractModelName);
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			doClick(ContractingMap.SaveOption());
			driverDelay(200);
			doClick(ContractingMap.getAssertCalculateOption());
			doClick(ContractingMap.calculateFeeCheckbox());
			doClick(ContractingMap.getreimburseScenario());
			driverDelay();
			doDropdownSelectUsingOptionTextWithelement(
					ContractingMap.getcalculateContractList(),
					"Contracted Payment 21");
			doClick(ContractingMap.getContractCalculateshareLog());
			doClick(ContractingMap.getCalculateButton());
			assertElementIsDisplayed(ContractingMap.getsaveContinueBtn());
			doClick(ContractingMap.getCancelBtn());
			ExtentReport.logPass("PASS", "test01ValidateCalculate_12493");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCalculate_12493", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test02ValidateCalculateButto_12493() throws Throwable {
		try {
			doClick(ContractingMap.getsaveContractList());
			driverDelay(200);
			doClick(ContractingMap.getCalculateButton());
			String[] filterCalc= {"Scenario Name","Is","Equal To",contractModelName};
			doFilterCalculationPage(filterCalc);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			checkForRecordsProcessed("Process Completed");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getCloseContractBtn);
			model.test02DeleteContractModel_ADS6435_ADS6412();
			ExtentReport.logPass("PASS", "test01ValidateCalculate_12493");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCalculate_12493", driver, e);
			fail(e.getMessage());

		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
