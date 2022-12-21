package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestUIValidationContractingValidateContractingModeExportImportButton extends GoHelper{

	private static ContractingMap modelMap;
	static String contractModel="#fz Med IPPS Testing";
	static String logFileName="SampleFile";
	/** Regression: Automated test script for ADS-6437 ,ADS-6436 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TestUIValidationContractingValidateContractingModeExportButton", "webdriver.scripts.contracting",
				"TestUIValidationContractingValidateContractingModeExportButton");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println("Test Class: " + TestUIValidationContractingValidateContractingModeExportImportButton.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForAjaxExtJs();
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(ContractingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	/*Test - UI Validation [Contracting] Validate Contracting Model – “Export” button; ADS-6437*/
	@Test
	public void test01FileExportContractModel() throws Throwable {
		try {
			doClick(modelMap.getContractModelExportButton());
			waitForElementToBeVisible(modelMap.getContractModelExportPopUp());
			doClick(modelMap.getContractModelSelectFileButton());
			waitForElementToBeVisible(modelMap.getContractModelInExportPopUp());
			driver.findElement(By.name("carrierfield")).sendKeys(contractModel);
			doClick(modelMap.getContractModelApplyInExportPopUp());
			driver.findElement(By.name("sharedHostLocation")).click();
			driverPause();
			doClick(modelMap.getContractModelExportFileSharedLocOption());
			driver.findElement(By.name("logFileName")).sendKeys(logFileName);
			Thread.sleep(200);
			doClick(modelMap.getContractModelExportButtonInExportPopUp());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(modelMap.getContractModelImportExportstatusPage());
			ContractingHelperMethods.waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Import/Export Status");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	
	}
	/** Test - UI Validation [Contracting] Validate Contracting Model – “Import” button; ADS-6436*/
	@Test
	public void test02FileImportContractModel() throws Throwable {
		try {
			doClick(modelMap.getContractModelImportButton());
			waitForElementToBeVisible(modelMap.getContractModelImportSelectFileButton());
			modelMap.getContractModelImportSelectFileButton().sendKeys(Keys.ENTER);;
			driverPause();
			ContractingHelperMethods.uploadTheFileusingAutoIT(driver,System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",System.getProperty("user.dir")+"\\AutoIT\\fzMedIPPSTesting.xml");
			driver.findElement(By.name("sharedHostLocation")).click();
			driverPause();
			doClick(modelMap.getContractModelExportFileSharedLocOption());
			driver.findElement(By.name("logFileName")).sendKeys(logFileName);
			Thread.sleep(200);
			driverDelay();
			doClick(modelMap.getContractModelImportButtonInExportPopUp());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(modelMap.getContractModelImportExportstatusPage());
			ContractingHelperMethods.waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Import/Export Status");
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test02ImportContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ImportContractModel", driver, e);
			fail(e.getMessage());
		}
			
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
