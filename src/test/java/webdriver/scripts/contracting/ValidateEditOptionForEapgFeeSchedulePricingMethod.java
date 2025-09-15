package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12621 */
public class ValidateEditOptionForEapgFeeSchedulePricingMethod extends GoHelper {
	private static ContractingMap modelMap;
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String criteria;
	static String serviceModelExist;
	static String pricingOption = "EAPG Fee Schedule";
	static String eapgClassificationScheme = "ASESC2918EAPG";
	static String payRate;

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("ValidateEditOptionForEapgFeeSchedulePricingMethod", "webdriver.scripts.contracting",
				"ValidateEditOptionForEapgFeeSchedulePricingMethod");
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

	public static void savePricingMethod(String pricingOption) throws Throwable {
		webdriverClick(driver.findElement(By.name("pricemethodoption")));
		waitForAjaxExtJs();
		webdriverClick(driver.findElement(
				By.xpath("//div[contains(@class,'x-boundlist-list-ct')]/ul/li[text()='" + pricingOption + "']")));
		Thread.sleep(200);
		// shilpa 01.08.2022 added above steps
		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();

	}

	public static String  validateIfContractModelHasServiceModel(String pricingOption) throws Throwable {
		serviceModelExist = driver.findElement(By.xpath("(//div[contains(@class,'x-tree-view')])[3]//span"))
				.getText();
		if (serviceModelExist.equals("Root")) {
			System.out.println("Element is not displayed or not present");
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			String serviceModelAddNew = driver
					.findElement(By.xpath("(//div[contains(@id,'treepanel')]//table//div/span)[2]")).getText();
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelAddNew + "']");
			savePricingMethod(pricingOption);
			assertElementIsDisplayed(driver.findElement(By.xpath(
					"//div[contains(text(),'Edit Price for " + serviceModelAddNew + " " + "[Encounter]" + "')]")));

		} else {
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelExist + "']");
			System.out.println("Element is displayed");
			savePricingMethod(pricingOption);
			assertElementIsDisplayed(driver.findElement(By.xpath(
					"//div[contains(text(),'Edit Price for " + serviceModelExist + " " + "[Encounter]" + "')]")));
		}
		return serviceModelExist;
	}
	@Test
	public void test01ValidateSavePricing_12621() throws Throwable {
		try {
			doClick(ContractingMap.getopenTaskList());
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelHeaderText());
			validateIfContractModelHasServiceModel(pricingOption);
			driver.findElement(By.name("drgClassificationSchemeCode")).click();
			driverDelay();
			doDropdownSelectUsingOptionTextWithelement(
					driver.findElement(By.xpath("(//div[text()='EAPG Fee Schedule']//following::ul)[2]")),
					eapgClassificationScheme);
			keyInInputText(generateRandomNumber(), ContractingMap.getbaseRate());
			driverDelay(100);
			payRate = ContractingMap.getbaseRate().getAttribute("aria-valuenow");
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidateSavePricing_12621");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSavePricing_12621", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test02ValidateEditButtonAfterSave_12621() throws Throwable {
		try {
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(text(),'Edit Price for')]")));
			doClick(ContractingMap.geteditPriceCancelCloseBtn());
			String criteria = ContractingMap.getpricingCriteria().getAttribute("value");
			if (criteria.contains("Pay rate " + payRate + " times weight in fee schedule")
					&& criteria.contains(eapgClassificationScheme)) {
				assertTrue(printout);
			} else {
				fail();
			}
			doClick(ContractingMap.SaveOption());
			doClick(modelMap.getContractModelContinue());
			driverDelay(100);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			doClickTreeItem("Fee For Service Payment Terms");
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelExist + "']");
			if (criteria.contains("Pay rate " + payRate + " times weight in fee schedule")
					&& criteria.contains(eapgClassificationScheme)) {
				assertTrue(printout);
			} else {
				fail();
			}
			doClick(ContractingMap.getCloseContractBtn);
			ExtentReport.logPass("PASS", "test02ValidateEditButtonAfterSave_12621");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditButtonAfterSave_12621", driver, e);
			fail(e.getMessage());

		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
