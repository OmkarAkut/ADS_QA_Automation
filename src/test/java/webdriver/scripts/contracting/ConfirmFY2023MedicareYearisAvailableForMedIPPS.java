package webdriver.scripts.contracting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class ConfirmFY2023MedicareYearisAvailableForMedIPPS extends CalculationHelper {

	private static final String contractModelName = "Contract Model 6278";
	private static final String serviceModel = "MCR IPPS 2023";
	private static final String serviceName = "MCR IPPS 2023";

	private static final String priceMethodOption = "Medicare Inpatient PPS";
	private static final String medicareYear = "Oct 1, 2022 - Sept 30, 2023";
	private static ContractingMap modelMap;
	static String[] filter = { "Name", "Is", "Equal To", serviceName };
	private static String IndustryClassSchemeDefaultValue = "MSDRG1";
	private static String IndustryClassSchemeUpdateValue = "MSDRG2";
	private static String operatingIMEAdjustmentFactor = "2";
	private static String capitalIMEAdjustmentFactor = "3";
	private static String operatingDSHAdjustmentFactor = "4";
	private static String capitalDSHAdjustmentFactor = "5";
	private static String areaWageIndex = ".9999";
	private static String expNationalLaborRate = "3952.96";
	private static String expNationalNonLaborRate = "2422.78";
	private static String hospitalReadmissionFactor = "1.2";
	private static String uncompensatedCarePayment = "1.3";
	private static String valueBasedPurchasingFactor = "1.5";
	private static String Reduction = "1.8";
	private static String capitalGeographicAdjustmentFactor = "1.5";
	private static String validateCapitalColaFactor = "1";
	private static String validateOperatingRatioOfCostCharge = "1";
	private static String validateCapitalRatioOfCostCharge = "1";
	private static String validateNonBurnMarginalCostFactor = "0.8";
	private static String validateFixedLossThreshold = "38859";
	private static String validateThresholdLaborPortion = "24092.59";
	private static String colaWageAdjustedRate = "5960.82";
	private static String WageAdjustedRate = "5960.82";
	private static String colaGeographicAdjustedRate = "725.64";
	private static String costPaymentService1 = "IP/OP Drugs RevCd 274 (CH)";
	private static String costPaymentService2 = "IP/OP Drugs RevCd 275 (CH)";
	private static String addPaymentService1 = "Bariatric Services (DRG 288)";
	private static String addPaymentService2 = "DRG 302 Kidney Transplant";
	private static String addPaymentService3 = "Drug Abuse (DRGs 433, 521-523)";
	private static String addPaymentService4 = "HIV (DRGs 488-490)";
	private static String addPaymentService5 = "Heart Transplant (DRG 103)";
	static String[] costFilterPaymentService1 = { "Name", "Is", "Contains", "IP/OP Drugs RevCd 274 (CH)" };
	static String[] costFilterPaymentService2 = { "Name", "Is", "Contains", "IP/OP Drugs RevCd 275 (CH)" };
	static String[] addFilterPaymentService1 = { "Name", "Is", "Contains", "Bariatric Services (DRG 288)" };
	static String[] addFilterPaymentService2 = { "Name", "Is", "Contains", "DRG 302 Kidney Transplant" };
	static String[] addFilterPaymentService3 = { "Name", "Is", "Contains", "Drug Abuse (DRGs 433, 521-523)" };
	static String[] addFilterPaymentService4 = { "Name", "Is", "Contains", "HIV (DRGs 488-490)" };
	static String[] addFilterPaymentService5 = { "Name", "Is", "Contains", "Heart Transplant (DRG 103)" };

	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/**
	 * Test - [CMS Regs: FY2023 IPPS] - Create New Contracting Model with FY2023
	 * Medicare Year ADS-6277
	 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		try {
			ExtentReport.reportCreate("ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate",
					"webdriver.scripts.contracting",
					"ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate");
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
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
	public void ConfirmMedicareYearForMedIPPS() throws Throwable {
		try {
			doSearchForContractModel(contractModelName);
			tableDoubleClickCellFirstColumn(contractModelName);
			waitForPageTitle(contractModelName);
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
			assertThatString(ContractingMap.getindustryClassificationInput(), IndustryClassSchemeDefaultValue,
					printout);
			doDropdownSelectUsingOptionText(ContractingMap.getindustryClassificationInput(),
					ContractingMap.industryClassificationList(), IndustryClassSchemeUpdateValue);
			doClick(modelMap.getContractEditPricePopUpDischargeStatus());
			waitForElementToBeVisible(modelMap.getContractEditPricePopUpDischargeStatusPopUp());
			try {
				assertTextIsDisplayed("0 Item(s) Selected");
				doClick(modelMap.getContractEditPricePopUpDischargeStatusSelectAll());
				driverDelay(1000);
			} catch (Exception | AssertionError e1) {
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
			assertThatAttributeValue(ContractingMap.getnationalLaborRate(), expNationalLaborRate, printout);
			assertThatAttributeValue(ContractingMap.getnationalNonLaborRate(), expNationalNonLaborRate, printout);
			ContractModelsHelper.keyInValues(ContractingMap.gethospitalReadmissionFactor(),
					hospitalReadmissionFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getuncompensatedCarePayment(),
					uncompensatedCarePayment);
			ContractModelsHelper.keyInValues(ContractingMap.getvalueBasedPurchasingFactor(),
					valueBasedPurchasingFactor);
			ContractModelsHelper.keyInValues(ContractingMap.getReduction(), Reduction);
			assertThatString(ContractingMap.getcolaWageAdjustedRate(), colaWageAdjustedRate, printout);
			assertThatString(ContractingMap.getWageAdjustedRate(), WageAdjustedRate, printout);
			navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
			ContractModelsHelper.keyInValues(ContractingMap.getcapitalGeographicAdjustmentFactor(),
					capitalGeographicAdjustmentFactor);
			assertThatString(ContractingMap.getcolaGeographicAdjustedRate(), colaGeographicAdjustedRate, printout);
			assertThatAttributeValue(ContractingMap.getcapitalColaFactor(), validateCapitalColaFactor, printout);
			navigateCloseSectionOpenNewSection("Capital Payment", "Cost Outlier Payment");
			assertThatAttributeValue(ContractingMap.getoperatingRatioOfCostCharge(), validateOperatingRatioOfCostCharge,
					printout);
			assertThatAttributeValue(ContractingMap.getcapitalRatioOfCostCharge(), validateCapitalRatioOfCostCharge,
					printout);
			assertThatAttributeValue(ContractingMap.getnonBurnMarginalCostFactor(), validateNonBurnMarginalCostFactor,
					printout);
			assertThatAttributeValue(ContractingMap.getfixedLossThreshold(), validateFixedLossThreshold, printout);
			assertThatAttributeValue(ContractingMap.getthresholdLaborPortion(), validateThresholdLaborPortion,
					printout);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpCostPaymentServicesSelectButton(), costPaymentService1,
					costFilterPaymentService1);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpCostPaymentServicesSelectButton(), costPaymentService2,
					costFilterPaymentService2);
			navigateCloseSectionOpenNewSection("Cost Outlier Payment", "Add On Technology Payment");
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), addPaymentService1,
					addFilterPaymentService1);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), addPaymentService2,
					addFilterPaymentService2);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), addPaymentService3,
					addFilterPaymentService3);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), addPaymentService4,
					addFilterPaymentService4);
			contractModelsHelper.AssertAddOnPaymentTechnologyServicesDisplayed(
					ContractingMap.getContractEditPricePopUpAddPaymentServicesSelectButton(), addPaymentService5,
					addFilterPaymentService5);
			ContractingMap.services.put("0000 Bariatric Services (DRG 288)", "10");
			ContractingMap.services.put("0000 DRG 302 Kidney Transplant", "2");
			ContractingMap.services.put("0000 Drug Abuse (DRGs 433, 521-523)", "3");
			ContractingMap.services.put("0000 HIV (DRGs 488-490)", "4");
			ContractingMap.services.put("0000 Heart Transplant (DRG 103)", "5");
			ContractModelsHelper.enterServicesMaxValue(ContractingMap.services);
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForElementToBeVisible(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			doClick(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			doClick(ContractingMap.getfeeForServicePayementTerms());
			driverDelay(1000);
			doClick(ContractingMap.getpricingMethod());
			driverDelay(1000);
			driver.findElement(By.xpath("(//div[text()='" + serviceModel + "'])[2]")).click();
			assertElementIsDisplayed(ContractingMap.getCriteriaBox());
			ExtentReport.logPass("PASS", "ConfirmMedicareYearForMedIPPS");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "ConfirmMedicareYearForMedIPPS", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar(contractModelName);
			doClosePageOnLowerBar("Model Library");
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
