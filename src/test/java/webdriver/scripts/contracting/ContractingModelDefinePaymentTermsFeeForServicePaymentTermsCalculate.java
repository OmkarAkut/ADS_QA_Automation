package webdriver.scripts.contracting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

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
	private static final String contractModelName="Test v10 REGRESSION Published Contract";
	private static final String serviceName = "MCR IPPS 2021";
	private static final String serviceModel = "OPPS 2019";
	private static final String priceMethodOption = "Medicare Inpatient PPS";
	static String[] filter = { "Name", "Is", "Equal To", serviceName };
    ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		try {
			ExtentReport.reportCreate("ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate","webdriver.scripts.contracting" ,"ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate");
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
			loginUser(Users.ContractAnalyst1);
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
			ContractModelsHelper.scrollToView("//div[text()='"+contractFolder+"']//img[3]");
			doClick("//div[text()='"+contractFolder+"']/img[2]");
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

		      driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "']")).click();
				contractModelsHelper.navigateCloseOpenSection(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
				navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
				Thread.sleep(2000);
				waitForAjaxExtJs();
				
				//assert pay default value
				String defaultExpectedPayPercentage = "100";
				String actualPayPercentage = driver.findElement(By.name("pay")).getAttribute("value");

				assertThat(actualPayPercentage, equalTo(defaultExpectedPayPercentage));
				//Edit price method option
				webdriverClick(driver.findElement(By.name("pricemethodoption")));
				waitForAjaxExtJs();
				webdriverClick(driver
						.findElement(By.xpath("//div[@class='x-boundlist-list-ct']/ul/li[text()='"+priceMethodOption+"']")));
				Thread.sleep(200);
				// shilpa 01.08.2022 added above steps
				navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
				assertElementIsDisplayed(modelMap.getContractEditPricePopUp());
				doClick(modelMap.getContractEditPricePopUpDischargeStatus());
				waitForElementToBeVisible(modelMap.getContractEditPricePopUpDischargeStatusPopUp());
				doClick(modelMap.getContractEditPricePopUpDischargeStatusSelectAll());
				doClick(modelMap.getContractEditPricePopUpDischargeStatusApply());
				int expectedDischargeItems=159;
				
			ExtentReport.logPass("PASS", "FeeForServicePaymentTermsCalculate");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "FeeForServicePaymentTermsCalculate", driver, e);
			fail(e.getMessage());
		}
	}
}
