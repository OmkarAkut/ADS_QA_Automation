package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

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
public class UIValidationContracting extends CalculationHelper{
	
	private static ContractingMap modelMap;
	static final String ContractModelName = "ADS-1320 Contract Model D";
	private static final String serviceModel = "OPPS 2019";
	

	/** Regression: Automated test script for ADS-6466 ,ADS-6465 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UIValidationContracting",
				"webdriver.scripts.contracting",
				"UIValidationContracting");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println("Test Class: "
					+ ClearFilterbuttonModels.class.getSimpleName());
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
	public void test01AssertContractModelPage_ADS_6466() throws Throwable {
		try {
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			assertTextIsDisplayed("Unpublished Contract Task List");
			assertTextIsDisplayed("Build Structure Elements");
			assertTextIsDisplayed("Model Contract");
			assertTextIsDisplayed("Publish Contract");
			assertTextIsDisplayed("Export Contract");
			doClickTreeItem("Model Contract");
			driverDelay(300);
			assertTextIsDisplayed("General Information - Unpublished Contract");
			assertTextIsDisplayed("Define Payment Terms");
			assertElementIsDisplayed(ContractingMap.getAssertCalculateOption());
			doClickTreeItem("Define Payment Terms");
			driverDelay(200);
			assertTextIsDisplayed("Fee For Service Payment Terms");
			assertTextIsDisplayed("Patient Financial Responsibility");
			assertTextIsDisplayed("Lump Sum Payment Allocation Rules");
			doClickTreeItem("Fee For Service Payment Terms");
			assertElementIsDisplayed(modelMap.getContractServiceModel());
			assertElementIsDisplayed(modelMap.getContractPricingMethod());
			assertElementIsDisplayed(modelMap.getContractRiskLimiterModel());
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Service Model");
//			doClick(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
//			waitForAjaxExtJs();
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServices());
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServiceModel());
			doClick(ContractingMap.getSaveButtonFeePaymentTerm());
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Pricing Method");
			doClick(ContractingMap.getSaveButtonFeePaymentTerm());
			driverDelay(200);
			waitForAjaxExtJs();			
			assertElementIsDisplayed(ContractingMap.getPricingLabelServiceModel());
			assertElementIsDisplayed(ContractingMap.getPricingLabelPricingMethods());
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Risk Limiter Model");
			assertElementIsDisplayed(ContractingMap.getPricingNewButton());
			doClick(ContractingMap.getPricingNewButton());
			waitUntilElementIsVisible(ContractingMap.getNewRiskLimiterPopUp());		
			assertElementIsDisplayed(ContractingMap.getNewRiskLimiterPopUp());
			doClick(ContractingMap.getNewRiskLimiterPopUpCancelClose());
			doClick(ContractingMap.getSaveButtonFeePaymentTerm());
			doClick(ContractingMap.getFeeForPaymentCancelClose());

			ExtentReport.logPass("PASS", "AssertContractModelPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "AssertContractModelPage", driver, e);
			fail(e.getMessage());
		} 
		
	
	}
	@Test
	public void test02AssertContractModelPage_ADS_6465() throws Throwable {
		try {
			doClickTreeItem("Calculate");
			driverDelay(100);
			assertTextIsDisplayed("General");
			assertTextIsDisplayed("Fee for Service/Lump Sum");
			assertTextIsDisplayed("Error Options");
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Error Options");
			assertTextIsDisplayed("Fee for Service error options");
			assertTextIsDisplayed("Recalculate Encounters with Errors/Warnings");
			assertTextIsDisplayed("Fee for Service/Lump Sum");
			assertTextIsDisplayed("Calculation Log");
			doClick(ContractingMap.getFeeForPaymentCancelClose());
//			assertTextIsDisplayed("Complete a model using a list of assignments.");
//			doClosePageOnLowerBar("ADS-1320 Contract...");
			//shilpa updated xpath for 11.2
//			driverDelay(500);
			doClick("//span[text()='ADS-1320 Contract...']//following::span[@class='x-tab-close-btn']");
			ExtentReport.logPass("PASS", "AssertContractModelPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "AssertContractModelPage", driver, e);
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
