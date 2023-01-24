package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;


public class CopyPasteButtons extends GoHelper {

	private static ContractingMap modelMap;
	private static String ContractModel = "10.2.1 Medicare IPPS FY2020 Test";
	private static String serviceModel = "MCR IPPS 2020";
	private static String UpdatedContractModel;
	Actions action = new Actions(driver);
	static String modelName;
	static String currentDateTime = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/** Regression: Automated test script for ADS-6434 ,ADS-6084*/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CopyPasteButtons", "webdriver.scripts.contracting", "CopyPasteButtons");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			System.out.println("Test Class: " + CopyPasteButtons.class.getSimpleName());
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
	public void test01CopyPasteContractModel() throws Throwable {
		try {
			doSearchForContractModel(ContractModel);
			tableDoubleClickCellFirstColumn(ContractModel);
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Service Model");
			assertElementIsDisplayed(modelMap.getContractServiceModel());
			assertElementIsDisplayed(modelMap.getContractPricingMethod());
			assertElementIsDisplayed(modelMap.getContractRiskLimiterModel());
			doClick(modelMap.getContractModelRiskLimiterCancelCloseBtn());
			doClosePageOnLowerBar("10.2.1 Medicare...");
			doClick(modelMap.getContractModelButtonCopy());
			assertElementIsEnabled(modelMap.getContractModelButtonPaste(), printout);
			doClick(modelMap.getContractModelButtonPaste());
			waitForElementToBeVisible(modelMap.getContractModelPastePopup());
			driverDelay();
			modelName = modelMap.getContractModelPasteNameInput().getAttribute("value");
			System.out.println(modelName);
			if (modelName.equalsIgnoreCase("Copy of " + " " + ContractModel + "")) {
				assertTrue(printout);
			}
			modelMap.getContractModelPastePopup().sendKeys(Keys.chord(Keys.CONTROL, "a"));
			modelMap.getContractModelPastePopup().sendKeys("Copy of " + ContractModel + " " + currentDateTime);
			UpdatedContractModel = modelMap.getContractModelPasteNameInput().getAttribute("value");
			doClick(modelMap.getContractModelSaveCopy());
			waitForDisplayedSpinnerToEnd();
			goToPage("Contract Models");
			doSearchForContractModel(UpdatedContractModel);
			driverDelay();
			assertTextIsDisplayed(UpdatedContractModel);
			tableDoubleClickCellFirstColumn(UpdatedContractModel);
			driverDelay(1000);
			driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='Model Contract']"))
					.click();
			driverDelay(300);
			driver.findElement(
					By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='Define Payment Terms']"))
					.click();
			driverDelay(300);
			driver.findElement(By.xpath(
					"//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='Fee For Service Payment Terms']"))
					.click();
			driverDelay(300);
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Service Model");
			assertTextIsDisplayed(serviceModel);
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Pricing Method");
			assertTextIsDisplayed(serviceModel);
			doClick(modelMap.getContractModelRiskLimiterCancelCloseBtn());
			doClosePageOnLowerBar("Copy of 10.2.1...");
			doClick(modelMap.getContractModelDeleteButton());
			waitForElementToBeVisible(modelMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(modelMap.getContractModelDeleteButtonInPopUp());
			assertElementIsDisplayed(modelMap.getContractModelCancelButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			ExtentReport.logPass("PASS", "test01CopyPasteContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CopyPasteContractModel", driver, e);
			fail(e.getMessage());
		}

	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
