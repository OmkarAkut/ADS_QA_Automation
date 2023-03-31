package webdriver.scripts.contracting;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	static String modelName;
	static String currentDateTime = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

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
			doSearchForContractModel(ContractModel);
			tableDoubleClickCellFirstColumn(ContractModel);
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			ContractModelsHelper.navigateFeeForServicePaymentTermsScreenSelectionPanel("Service Model");
			
			ExtentReport.logPass("PASS", "setupScript");
			
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void AssertFeeForServicePaymentTermsScreenSelectionPanel() throws Throwable {
		try {
			assertElementIsDisplayed(modelMap.getContractServiceModel());
			assertElementIsDisplayed(modelMap.getContractPricingMethod());
			assertElementIsDisplayed(modelMap.getContractRiskLimiterModel());
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClosePageOnLowerBar("10.2.1 Medicare...");
			ExtentReport.logPass("PASS", "AssertFeeForServicePaymentTermsScreenSelectionPanel");
			
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "AssertFeeForServicePaymentTermsScreenSelectionPanel", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public  void testCopyPasteContractmodel() throws Throwable {
		try {
			doClick(modelMap.getContractModelButtonCopy());
			assertElementIsEnabled(modelMap.getContractModelButtonPaste(), printout);
			doClick(modelMap.getContractModelButtonPaste());
			waitForElementToBeVisible(modelMap.getContractModelPastePopup());
			driverDelay();
			modelName = modelMap.getContractModelPasteNameInput().getAttribute("value");
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
			ExtentReport.logPass("PASS", "testCopyPasteContractmodel");
			
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testCopyPasteContractmodel", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifyServiceModelUnderPastedContractModel() throws Throwable {
		try {
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
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClosePageOnLowerBar("Copy of 10.2.1...");
			ExtentReport.logPass("PASS", "testVerifyServiceModelUnderPastedContractModel");
			
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testVerifyServiceModelUnderPastedContractModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void testDeleteContractModel() throws Throwable {
		try {
			doClick(modelMap.getContractModelDeleteButton());
			waitForElementToBeVisible(modelMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(modelMap.getContractModelDeleteButtonInPopUp());
			assertElementIsDisplayed(modelMap.getContractModelCancelButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			ExtentReport.logPass("PASS", "testDeleteContractModel");
			
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testDeleteContractModel", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
