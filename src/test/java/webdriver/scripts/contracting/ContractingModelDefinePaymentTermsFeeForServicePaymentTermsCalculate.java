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
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate extends CalculationHelper {

	private static ContractingMap modelMap;
	private static EditContractingModelMap editModelMap;
	private static final String contractFolder = "Test Imran Folder 2";// Shilpa: 1.08.2022 updated test data
	private static final String contractModelName = "v105 REGRESSION 2021 IPPS DC A1";
	private static final String serviceName = "MCR IPPS 2021";
	private static final String serviceModel = "MCR IPPS 2021";
	private static final String priceMethodOption = "Medicare Inpatient PPS";
	static String[] filter = { "Name", "Is", "Equal To", serviceName };
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
	private static String validateService01 = "ASESC-2836 IPPS KCentra A1";
	private static String validateService02 = "ASESC-2836 IPPS KCentra A2";
	private static String validateService03 = "ASESC-2836 IPPS KCentra A3";
	private static String validateService01MaxValue = "1537.50";
	private static String validateService02MaxValue = "153.75";
	private static String validateService03MaxValue = "153750.00";
	static String viewLogTitleApply = "v105 REGRESSION 2021 IPPS DC A1";

	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
/** Test - [CMS Regs: FY2023 IPPS] - Create New Contracting Model with FY2023 Medicare Year ADS-6277 **/
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
			assertTextIsDisplayed("159 Item(s) Selected");
			// doClick(modelMap.getContractEditPricePopUpDischargeStatusSelectAll());
//				driverDelay(1000);
			doClick(modelMap.getContractEditPricePopUpDischargeStatusApply());
//				driverDelay(1000);
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
			//add inputs into different category
			driver.findElement(By.name("operIndirectMedEducAdjFactor")).clear();
			driver.findElement(By.name("operIndirectMedEducAdjFactor")).sendKeys(operatingIMEAdjustmentFactor);
			driver.findElement(By.name("capitalIndrMedEducAdjFactor")).clear();
			driver.findElement(By.name("capitalIndrMedEducAdjFactor")).sendKeys(capitalIMEAdjustmentFactor);
			driver.findElement(By.name("operDispShareHospAdjFactor")).clear();
			driver.findElement(By.name("operDispShareHospAdjFactor")).sendKeys(operatingDSHAdjustmentFactor);
			driver.findElement(By.name("capitalDispShareHospAdjFactor")).clear();
			driver.findElement(By.name("capitalDispShareHospAdjFactor")).sendKeys(capitalDSHAdjustmentFactor);
			navigateCloseSectionOpenNewSection("General", "Operating Payment");
			driver.findElement(By.name("areaWageIndex")).clear();
			driver.findElement(By.name("areaWageIndex")).sendKeys(areaWageIndex);
			driver.findElement(By.name("nationalOperlaborRate")).clear();
			driver.findElement(By.name("nationalOperlaborRate")).sendKeys(nationalLaborRate);
			driver.findElement(By.name("nationalOperNonLaborRate")).clear();
			driver.findElement(By.name("nationalOperNonLaborRate")).sendKeys(nationalNonLaborRate);
			driver.findElement(By.name("hospitalReadmAdjFactor")).clear();
			driver.findElement(By.name("hospitalReadmAdjFactor")).sendKeys(hospitalReadmissionFactor);
			driver.findElement(By.name("uncompensatedCarePayment")).clear();
			driver.findElement(By.name("uncompensatedCarePayment")).sendKeys(uncompensatedCarePayment);
			driver.findElement(By.name("valueBasedPurchAdjFactor")).clear();
			driver.findElement(By.name("valueBasedPurchAdjFactor")).sendKeys(valueBasedPurchasingFactor);
			driver.findElement(By.name("hacReductionPercent")).clear();
			driver.findElement(By.name("hacReductionPercent")).sendKeys(Reduction);
			driver.findElement(By.name("colaOperatingFactor")).clear();
			driver.findElement(By.name("colaOperatingFactor")).sendKeys(locationAndOperatingFactor);
			navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
			driver.findElement(By.name("capitalGeographicAdjFactor")).clear();
			driver.findElement(By.name("capitalGeographicAdjFactor")).sendKeys(capitalGeographicAdjustmentFactor);
			driver.findElement(By.name("colaCapitalFactor")).clear();
			driver.findElement(By.name("colaCapitalFactor")).sendKeys(capitalColaFactor);
			driver.findElement(By.name("nationalCapitalRate")).clear();
			driver.findElement(By.name("nationalCapitalRate")).sendKeys(nationalCapitalRate);
			navigateCloseSectionOpenNewSection("Capital Payment", "Cost Outlier Payment");
			driver.findElement(By.name("operCostChargeRatio")).clear();
			driver.findElement(By.name("operCostChargeRatio")).sendKeys(operatingRatioOfCostCharge);
			driver.findElement(By.name("capitalCostChargeRatio")).clear();
			driver.findElement(By.name("capitalCostChargeRatio")).sendKeys(capitalRatioOfCostCharge);
			driver.findElement(By.name("paymentPercentage")).clear();
			driver.findElement(By.name("paymentPercentage")).sendKeys(nonBurnMarginalCostFactor);
			driver.findElement(By.name("fixedLossThreshold")).clear();
			driver.findElement(By.name("fixedLossThreshold")).sendKeys(fixedLossThreshold);
			driver.findElement(By.name("laborPortion")).clear();
			driver.findElement(By.name("laborPortion")).sendKeys(thresholdLaborPortion);
			navigateCloseSectionOpenNewSection("Cost Outlier Payment", "Add On Technology Payment");
			doClick(ContractingMap.getContractEditPricePopUpServicesSelectButton());
			//Validate Services under Add Services Category
			waitForElementToBeVisible(ContractingMap.getContractEditPricePopUpAddServices());
			assertElementIsDisplayed(ContractingMap.getContractEditPricePopUpAddServices());
			contractModelsHelper.assertColumnsToDisplayColumnIsSelected(validateService01);
			contractModelsHelper.assertColumnsToDisplayColumnIsSelected(validateService02);
			contractModelsHelper.assertColumnsToDisplayColumnIsSelected(validateService03);
			doClick(ContractingMap.getContractFeeForServicePaymentApply());
			ContractModelsHelper.assertThatString(
					driver.findElement(By.xpath("(//div[text()='" + validateService01 + "']//following::td/div)[1]")),
					validateService01MaxValue, printout);
			ContractModelsHelper.assertThatString(
					driver.findElement(By.xpath("(//div[text()='" + validateService02 + "']//following::td/div)[1]")),
					validateService02MaxValue, printout);
			ContractModelsHelper.assertThatString(
					driver.findElement(By.xpath("(//div[text()='" + validateService03 + "']//following::td/div)[1]")),
					validateService03MaxValue, printout);

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
