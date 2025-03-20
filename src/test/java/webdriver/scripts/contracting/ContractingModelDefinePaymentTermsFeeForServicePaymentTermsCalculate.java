package webdriver.scripts.contracting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate extends CalculationHelper {

	private static ContractingMap modelMap;
	private static EditContractingModelMap editModelMap;
	private static final String contractFolder = "Imran - Test Folder";// Shilpa: 1.08.2022 updated test data, updated
																		// again on 24.2.2024
	private static final String contractModelName = "v10.5 REGRESSION 2023 IPPS DC A";// Shilpa updated data 24.4.2024
//	private static final String serviceName = "MCR IPPS 2021";
	private static final String serviceName = "MCR IPPS 2020";// Shilpa updated to 24.4.2026
	private static final String serviceName1 = "ASESC-2836 IPPS KCentra A1";
	private static final String serviceName2 = "ASESC-2836 IPPS KCentra A2";

	private static final String serviceName3 = "ASESC-2836 IPPS KCentra A3";

	final static String[] columnsToSelect = { "ASESC-2836 IPPS KCentra A1", "ASESC-2836 IPPS KCentra A2",
			"ASESC-2836 IPPS KCentra A3" };
	 String[] filterCalc= {"Scenario Name","Is","Equal To",contractModelName};
//	private static final String serviceModel = "MCR IPPS 2021";
	private static final String serviceModel = "MCR IPPS 2020";// Shilpa updated 24.4.2024
	private static final String priceMethodOption = "Medicare Inpatient PPS";
	static String[] filter = { "Name", "Is", "Equal To", serviceName };
	static String[] filterServiceASESC2836IPPSKCentraA1 = { "Name", "Is", "Equal To", serviceName1 };
	static String[] filterServiceASESC2836IPPSKCentraA2 = { "Name", "Is", "Equal To", "ASESC-2836 IPPS KCentra A2" };
	static String[] filterServiceASESC2836IPPSKCentraA3 = { "Name", "Is", "Equal To", "ASESC-2836 IPPS KCentra A3" };
	static String[] filterContractModel = { "Name", "Is", "Equal To", contractModelName };
	private static final String medicareYear = "Oct 1, 2020 - Sept 30, 2021";
	private static String operatingIMEAdjustmentFactor = "0.0264";
	private static String capitalIMEAdjustmentFactor = "0.0227";
	private static String operatingDSHAdjustmentFactor = "0.112";
	private static String capitalDSHAdjustmentFactor = "0.0554";
	private static String areaWageIndex = "0.8729";
	private static String nationalLaborRate = "3609.31";
	private static String nationalNonLaborRate = "2212.16";
	private static String hospitalReadmissionFactor = "0.9903";
	private static String uncompensatedCarePayment = "472.62";
	private static String valueBasedPurchasingFactor = "0.9946324313";
	private static String Reduction = "1.0";
	private static String locationAndOperatingFactor = "1.0";
	private static String capitalGeographicAdjustmentFactor = "0.9111";
	private static String capitalColaFactor = "1";
	private static String nationalCapitalRate = "462.61";
	private static String operatingRatioOfCostCharge = "0.116";
	private static String capitalRatioOfCostCharge = "0.011";
	private static String nonBurnMarginalCostFactor = "0.8";
	private static String fixedLossThreshold = "22539";
	private static String thresholdLaborPortion = "13974.17";
	private static String validateService01MaxValue = "1537.50";
	private static String validateService02MaxValue = "153.75";
	private static String validateService03MaxValue = "153750.00";
	static String viewLogTitleApply = "v105 REGRESSION 2021 IPPS DC A";

	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/**
	 * Test - [CMS Regs: FY2023 IPPS] - Create New Contracting Model with FY2023
	 * Medicare Year ADS-6277 ,ADS-6775
	 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		try {
			ExtentReport.reportCreate("ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate",
					"webdriver.scripts.contracting",
					"ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate");
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
			loginUser(Users.AutomationTesterAdmin);
			waitForDisplayedSpinnerToEnd();
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(CostingMap.getContractingName);
//			navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
//			navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}

//ADS-6775,ADS-6782 [both test cases covered here]
	@Test
	public void FeeForServicePaymentTermsCalculate_ADS_6775_ADS_6782() throws Throwable {
		try {
			//Shilpa commented 24.4.2026 has scroll issue, once scroll issue is fixed below lines can be uncommented
//			ContractModelsHelper.scrollToView("(//span[text()='Imran - Test Folder'])[2]");
//			doClick("//div[text()='" + contractFolder + "']/img[2]");
			doSearchForContractModel(contractModelName);
			tableDoubleClickCellFirstColumn(contractModelName);
//			waitForPageTitle(contractModelName);
			assertTextIsDisplayed("Unpublished Contract Task List");
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			assertElementIsDisplayed(modelMap.getContractServiceModel());
			assertElementIsDisplayed(modelMap.getContractPricingMethod());
			assertElementIsDisplayed(modelMap.getContractRiskLimiterModel());
			doClick(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
			waitForAjaxExtJs();
			Thread.sleep(200);
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServices());
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServiceModel());
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filter);
			// Shilpa updated xpath 23.4.2024
			driver.findElement(By.xpath(
					"//label[text()='Service Model']/ancestor::div/descendant::span[text() = '" + serviceModel + "']"))
					.click();
			contractModelsHelper
					.navigateCloseOpenSection(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
			navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
			Thread.sleep(2000);
			waitForAjaxExtJs();

			// assert pay default value
			String defaultExpectedPayPercentage = "100";
			String actualPayPercentage = driver.findElement(By.name("pay")).getAttribute("value");

			assertThat(actualPayPercentage, equalTo(defaultExpectedPayPercentage));
			// Edit price method option
			webdriverClick(driver.findElement(By.name("pricemethodoption")));
			waitForAjaxExtJs();
			webdriverClick(driver.findElement(By
					.xpath("//div[contains(@class,'x-boundlist-list-ct')]/ul/li[text()='" + priceMethodOption + "']")));
			Thread.sleep(200);
			// shilpa 01.08.2022 added above steps
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
			assertElementIsDisplayed(modelMap.getContractEditPricePopUp());
			doClick(modelMap.getContractEditPricePopUpDischargeStatus());
			waitForElementToBeVisible(modelMap.getContractEditPricePopUpDischargeStatusPopUp());
			try {
				assertTextIsDisplayed("0 Item(s) Selected");
				doClick(modelMap.getContractEditPricePopUpDischargeStatusSelectAll());
				driverDelay(1000);
			} catch (Exception | AssertionError e1) {
//				assertTextIsDisplayed("159 Item(s) Selected");
				assertTextIsDisplayed("191 Item(s) Selected");// Shilpa updated 24.4.2024 for 11.2

			}
			doClick(modelMap.getContractEditPricePopUpDischargeStatusApply());
			driverDelay(100);
//			int expectedDischargeItems = 159;
			int expectedDischargeItems = 100;// Shilpa updated 23.4.2024 for 11.2 can have only 100 elements in dialog
												// due to limitation
			int dischargeItems = ContractingMap.getContractEditPricePopUpDischargeStatusItemCount().size();
			System.out.println(dischargeItems);
//			doClick(ContractingMap.getContractEditPricePopUpDischargeStatusLastPage());
//			int totalDischargeItems = ContractingMap.getContractEditPricePopUpDischargeStatusItemCount().size();
//			int totalDischargeItems = dischargeItems + dischargeItemsLastPage;
//			System.out.println(totalDischargeItems);
//			System.out.println(dischargeItems);

			if (expectedDischargeItems == dischargeItems) {
				assertTrue("All discharge items has been added", printout);
			} else {
				assertFalse(printout);
			}
			doDropdownSelectUsingOptionText(ContractingMap.getContractEditPricePopUpDischargeStatusMedicareYearDrpdwn(),
					ContractingMap.getContractEditPricePopUpDischargeStatusMedicareYearDrpdwnList(), medicareYear);
//			//add inputs into different category
			ContractModelsHelper.keyInValues(ContractingMap.getoperatingIMEAdjustmentFactor(),
					operatingIMEAdjustmentFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getcapitalIMEAdjustmentFactor(),
					capitalIMEAdjustmentFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getoperatingDSHAdjustmentFactor(),
					operatingDSHAdjustmentFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getcapitalDSHAdjustmentFactor(),
					capitalDSHAdjustmentFactor);
			navigateCloseSectionOpenNewSection("General", "Operating Payment");
			ContractModelsHelper.keyInValues(ContractingMap.getareaWageIndex(), areaWageIndex);
			ContractModelsHelper.keyInValues(ContractingMap.getnationalLaborRate(), nationalLaborRate);
			ContractModelsHelper.keyInValues(ContractingMap.getnationalNonLaborRate(), nationalNonLaborRate);
			ContractModelsHelper.keyInValues(ContractingMap.gethospitalReadmissionFactor(), hospitalReadmissionFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getuncompensatedCarePayment(), uncompensatedCarePayment);
			ContractModelsHelper.keyInValues(ContractingMap.getvalueBasedPurchasingFactor(),
					valueBasedPurchasingFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getReduction(), Reduction);
			ContractModelsHelper.keyInValues(ContractingMap.getlocationAndOperatingFactor(),
					locationAndOperatingFactor);
			navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
			ContractModelsHelper.keyInValues(ContractingMap.getcapitalGeographicAdjustmentFactor(),
					capitalGeographicAdjustmentFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getcapitalColaFactor(), capitalColaFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getnationalCapitalRate(), nationalCapitalRate);
			navigateCloseSectionOpenNewSection("Capital Payment", "Cost Outlier Payment");
			ContractModelsHelper.keyInValues(ContractingMap.getoperatingRatioOfCostCharge(),
					operatingRatioOfCostCharge);
			ContractModelsHelper.keyInValues(ContractingMap.getcapitalRatioOfCostCharge(), capitalRatioOfCostCharge);
			ContractModelsHelper.keyInValues(ContractingMap.getnonBurnMarginalCostFactor(), nonBurnMarginalCostFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getfixedLossThreshold(), fixedLossThreshold);
			ContractModelsHelper.keyInValues(ContractingMap.getthresholdLaborPortion(), thresholdLaborPortion);
			navigateCloseSectionOpenNewSection("Cost Outlier Payment", "Add On Technology Payment");
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), serviceName1,
					filterServiceASESC2836IPPSKCentraA1);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), serviceName2,
					filterServiceASESC2836IPPSKCentraA2);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), serviceName3,
					filterServiceASESC2836IPPSKCentraA3);
			ContractingMap.services.put(serviceName1, validateService01MaxValue);
			ContractingMap.services.put(serviceName2, validateService02MaxValue);
			ContractingMap.services.put(serviceName3, validateService03MaxValue);
			ContractModelsHelper.enterServicesMaxValue(ContractingMap.services);
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForElementToBeVisible(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			assertElementIsDisplayed(modelMap.getContractModelCancelButtonInPopUp());
			assertElementIsDisplayed(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			driverDelay(3000);
			doClick(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
//			doClosePageOnLowerBar("v105 REGRESSION...");//Shilpa updated for 11.2 on 24.2.2024
			doClick("(//span[@class='x-tab-close-btn'])[2]");
			/*
			 * doClick(ContractingMap.getContractModelButtonFilter()); waitForAjaxExtJs();
			 * Thread.sleep(200); doFilterCreate(filterContractModel);
			 */
			doClick(ContractingMap.getContractFeeForServicePaymentCalculateButton());
			doFilterCalculationPage(filterCalc);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			assertViewLogTitle(contractModelName);
//			confirmCalculationStatusDetailsContains("Total Items Processed: 12");
//			confirmCalculationStatusDetailsContains("Process Completed");
			checkForRecordsProcessed("Total Items Processed: 7");
			checkForRecordsProcessed("Process Completed");
//			closeViewDialog();
			doClick("(//div[contains(@class,'x-toolbar x-docked')]//span[text()='Cancel'])[3]");
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "FeeForServicePaymentTermsCalculate");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "FeeForServicePaymentTermsCalculate", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
