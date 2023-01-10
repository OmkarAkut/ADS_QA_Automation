package webdriver.scripts.contracting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate extends CalculationHelper {

	private static ContractingMap modelMap;
	private static EditContractingModelMap editModelMap;
	private static final String contractFolder = "Test Imran Folder 2";// Shilpa: 1.08.2022 updated test data
	private static final String contractModelName = "v105 REGRESSION 2021 IPPS DC A1";
	private static final String serviceName = "MCR IPPS 2021";
	private static final String serviceName1 = "ASESC-2836 IPPS KCentra A1";
	private static final String serviceName2 = "ASESC-2836 IPPS KCentra A2";

	private static final String serviceName3 = "ASESC-2836 IPPS KCentra A3";

	final static String[] columnsToSelect= {"ASESC-2836 IPPS KCentra A1","ASESC-2836 IPPS KCentra A2","ASESC-2836 IPPS KCentra A3"};

	private static final String serviceModel = "MCR IPPS 2021";
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
	static String viewLogTitleApply = "v105 REGRESSION 2021 IPPS DC A1";

	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
/** Test - [CMS Regs: FY2023 IPPS] - Create New Contracting Model with FY2023 Medicare Year ADS-6277,
 * Test - [Contracting Calc] Contracting Model – Define Payment Terms > Fee For Service Payment Terms - "Calculate”.ADS-6775 -contract model is Test Regression 2022 IPPS A1 , the steps are similar ADS-6277**/
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

	@Test
	public void FeeForServicePaymentTermsCalculate() throws Throwable {
		try {
			ContractModelsHelper.scrollToView("//div[text()='" + contractFolder + "']//img[3]");
			doClick("//div[text()='" + contractFolder + "']/img[2]");
			tableDoubleClickCellFirstColumn(contractModelName);
			waitForPageTitle(contractModelName);
			assertTextIsDisplayed("Unpublished Contract Task List");
			contractModelsHelper.navigateFeeForServicePaymentTerms();
//			assertElementTextContains(contractingMap.getContractModelLeftPane(), "Unpublished Contract Task List", printout);
			assertElementIsDisplayed(modelMap.getContractServiceModel());
			assertElementIsDisplayed(modelMap.getContractPricingMethod());
			assertElementIsDisplayed(modelMap.getContractRiskLimiterModel());
			doClick(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
			waitForAjaxExtJs();
			Thread.sleep(200);
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServices());
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServiceModel());
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filter);

			driver.findElement(By.xpath(
					"//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "']"))
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
			webdriverClick(driver.findElement(
					By.xpath("//div[@class='x-boundlist-list-ct']/ul/li[text()='" + priceMethodOption + "']")));
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
			} catch (Exception|AssertionError e1) {
				assertTextIsDisplayed("159 Item(s) Selected");

			}
			doClick(modelMap.getContractEditPricePopUpDischargeStatusApply());
			driverDelay(100);
			int expectedDischargeItems = 159;
			int dischargeItems = ContractingMap.getContractEditPricePopUpDischargeStatusItemCount().size();
			System.out.println(dischargeItems);
			doClick(ContractingMap.getContractEditPricePopUpDischargeStatusLastPage());
			int dischargeItemsLastPage = ContractingMap.getContractEditPricePopUpDischargeStatusItemCount().size();
			int totalDischargeItems = dischargeItems + dischargeItemsLastPage;
			System.out.println(totalDischargeItems);
			System.out.println(dischargeItems);

			if (expectedDischargeItems == totalDischargeItems) {
				assertTrue("All discharge items has been added", printout);
			} else {
				assertFalse(printout);
			}
			doDropdownSelectUsingOptionText(ContractingMap.getContractEditPricePopUpDischargeStatusMedicareYearDrpdwn(),
					ContractingMap.getContractEditPricePopUpDischargeStatusMedicareYearDrpdwnList(), medicareYear);
//			//add inputs into different category
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getoperatingIMEAdjustmentFactor(),operatingIMEAdjustmentFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getcapitalIMEAdjustmentFactor(),capitalIMEAdjustmentFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getoperatingDSHAdjustmentFactor(),operatingDSHAdjustmentFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getcapitalDSHAdjustmentFactor(),capitalDSHAdjustmentFactor);
			navigateCloseSectionOpenNewSection("General", "Operating Payment");
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getareaWageIndex(),areaWageIndex);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getnationalLaborRate(),nationalLaborRate);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getnationalNonLaborRate(),nationalNonLaborRate);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.gethospitalReadmissionFactor(),hospitalReadmissionFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getuncompensatedCarePayment(),uncompensatedCarePayment);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getvalueBasedPurchasingFactor(),valueBasedPurchasingFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getReduction(),Reduction);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getlocationAndOperatingFactor(),locationAndOperatingFactor);
			navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getcapitalGeographicAdjustmentFactor(),capitalGeographicAdjustmentFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getcapitalColaFactor(),capitalColaFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getnationalCapitalRate(),nationalCapitalRate);
			navigateCloseSectionOpenNewSection("Capital Payment", "Cost Outlier Payment");
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getoperatingRatioOfCostCharge(),operatingRatioOfCostCharge);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getcapitalRatioOfCostCharge(),capitalRatioOfCostCharge);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getnonBurnMarginalCostFactor(),nonBurnMarginalCostFactor);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getfixedLossThreshold(),fixedLossThreshold);
			ContractModelsHelper.keyInValuesUnderPricingMethod(ContractingMap.getthresholdLaborPortion(),thresholdLaborPortion);
			navigateCloseSectionOpenNewSection("Cost Outlier Payment", "Add On Technology Payment");

			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(serviceName1,filterServiceASESC2836IPPSKCentraA1);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(serviceName2,filterServiceASESC2836IPPSKCentraA2);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(serviceName3,filterServiceASESC2836IPPSKCentraA3);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesMaxValue(serviceName1,validateService01MaxValue);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesMaxValue(serviceName2,validateService02MaxValue);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesMaxValue(serviceName3,validateService03MaxValue);
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForElementToBeVisible(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			assertElementIsDisplayed(modelMap.getContractModelCancelButtonInPopUp());
			assertElementIsDisplayed(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			driverDelay(3000);
			doClick(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			doClosePageOnLowerBar("v105 REGRESSION...");
			doClick(modelMap.getContractModelButtonFilter());
			waitForAjaxExtJs();
			Thread.sleep(200);
			doFilterCreate(filterContractModel);
			doClick(ContractingMap.getContractFeeForServicePaymentCalculateButton());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			assertViewLogTitle(viewLogTitleApply);
			confirmCalculationStatusDetailsContains("Total Items Processed: 12");
			confirmCalculationStatusDetailsContains("Process Completed");
			doClick(ContractingMap.getContractCalculationCloseViewDialog());
			deleteMyCalculationStatusFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "FeeForServicePaymentTermsCalculate");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "FeeForServicePaymentTermsCalculate", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
