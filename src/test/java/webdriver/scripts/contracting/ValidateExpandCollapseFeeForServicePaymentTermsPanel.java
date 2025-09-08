package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateExpandCollapseFeeForServicePaymentTermsPanel extends GoHelper{
	private static ContractingMap modelMap;
	static String contractModelName;
	String[] columnsToSelect = {"150  Marina Medical Center" };
	CreateANewContractModel model=new CreateANewContractModel();
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	/** Regression: Automated test script for ADS-12498 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateExpandCollapseFeeForServicePaymentTermsPanel",
				"webdriver.scripts.contracting",
				"ValidateExpandCollapseFeeForServicePaymentTermsPanel");
		try {
			
			
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
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
	public void test01expandPanelsWithHeaderText_12498_12499() throws Throwable {
		try {
			model.test01CreateNewContractModel_6413();
			contractModelName=CreateANewContractModel.contractModelName;
			doSearchForContractModel(contractModelName);
			driverDelay(200);
			tableDoubleClickCellFirstColumn(contractModelName);
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelHeaderText());
			WebElement sourceBefore = driver.findElement(By.xpath(
					"(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[2]"));
			WebElement targetBefore = driver.findElement(By.xpath("(//div[@class='x-grid-item-container'])[5]"));
			doClick("(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[2]");
			assertElementIsDisplayed(driver.findElement(By.xpath("(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[2]//ancestor::table[contains(@class,'x-grid-item-selected')]")));//Validates ADS-12499
			CimHelper.dragAndDrop(sourceBefore, targetBefore);
			doClick(ContractingMap.getContractFeeForServicePaymentRiskLimiterModelHeaderText());
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			ExtentReport.logPass("PASS", "test01expandPanelsWithHeaderText_12498");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01expandPanelsWithHeaderText_12498", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test02expandPanelsWithArrowButtons_12498() throws Throwable {
		try {
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelExpandBtn());
			doClick(ContractingMap.getContractFeeForServicePaymentRiskLimiterModelExpandBtn());
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodExpandBtn());
//			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());// Fail becoz of ADS-23072, Uncommment once issue is fixed
//			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());//Fail becoz of ADS-23072, Uncommment once issue is fixed
			doClick(ContractingMap.getCloseContractBtn());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());//Remove this line once ADS-230722 is fixed
			model.test02DeleteContractModel_ADS6435_ADS6412();
			ExtentReport.logPass("PASS", "test02expandPanelsWithArrowButtons_12498");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02expandPanelsWithArrowButtons_12498", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
