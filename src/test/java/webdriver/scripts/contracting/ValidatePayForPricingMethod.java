package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
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
/** Support Issues: Automated test script for ADS-12536 , ADS- 12503 **/
public class ValidatePayForPricingMethod extends GoHelper {
	private static ContractingMap modelMap;
	CreateANewContractModel model = new CreateANewContractModel();
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	 static String contractModelName = "Contract Model" + currentDateTime;
	static String serviceModelNew;
	static String priceMethodOption = "AP DRG Fee Schedule";
	static String pay = "30";
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateDialogBoxForPricingMethods", "webdriver.scripts.contracting",
				"ValidateDialogBoxForPricingMethods");
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
	public void test01ValidatePaySavedForPricingMethod_ADS_12536() throws Throwable {
		try {
			model.createContractModel(contractModelName);
			ValidateDragDropAddNewServiceUnderPricing.searchContractModelOpenTaskList(contractModelName);
			serviceModelNew = driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')]//table//div)[1]"))
					.getText();
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodExpandBtn());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelNew + "']");
			webdriverClick(driver.findElement(By.name("pricemethodoption")));
			waitForAjaxExtJs();
			webdriverClick(driver.findElement(By
					.xpath("//div[contains(@class,'x-boundlist-list-ct')]/ul/li[text()='" + priceMethodOption + "']")));
			Thread.sleep(200);
			// shilpa 01.08.2022 added above steps
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
			assertElementIsDisplayed(modelMap.getContractEditPricePopUp());
			doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
			keyInInputText("30", driver.findElement(By.name("pay")));
			doClick(ContractingMap.SaveOption());
			doClick(modelMap.getContractModelContinue());
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelNew + "']");
			assertElementValueAttributeContains(driver.findElement(By.name("pay")), pay, printout);//fails becoz of ADS-23305

			ExtentReport.logPass("PASS", "test01ValidatePaySavedForPricingMethod_ADS_12536");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidatePaySavedForPricingMethod_ADS_12536", driver, e);
			fail(e.getMessage());

		}

	}

	@Test
	public void test02ValidateRiskLimiter_ADS_12503() throws Throwable {
		try {
			doClick(ContractingMap.getContractFeeForServicePaymentRiskLimiterModelExpandBtn());
			String cautionMark = driver.findElement(By.xpath("//div[@class='x-grid-empty']")).getCssValue("background");
			if (cautionMark.contains("grid-emptytext-icon.png")) {
				assertTextIsDisplayed("There is no data available to display");
			}
			doClick(ContractingMap.getCloseContractBtn());
			model.test02DeleteContractModel_ADS6435_ADS6412();
			ExtentReport.logPass("PASS", "test02ValidateRiskLimiter_ADS_12536");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateRiskLimiter_ADS_12536", driver, e);
			fail(e.getMessage());

		}

	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
