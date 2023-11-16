package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ValidateContractingModeExportImportButton extends GoHelper{

	private static ContractingMap modelMap;
	static String contractModel="#fz Med IPPS Testing";
	static String logFileName="SampleFile";
	/** Regression: Automated test script for ADS-6437 ,ADS-6436 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateContractingModeExportImportButton", "webdriver.scripts.contracting",
				"ValidateContractingModeExportImportButton");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(ContractingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	/*Test - UI Validation [Contracting] Validate Contracting Model � �Export� button; ADS-6437*/
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
			driverDelay(500);
			doClick(modelMap.getContractModelExportFileSharedLocOption());
			driver.findElement(By.name("logFileName")).sendKeys(logFileName);
			driverDelay(500);
			doClick(modelMap.getContractModelExportButtonInExportPopUp());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(modelMap.getContractModelImportExportstatusPage());
			ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Import/Export Status");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	
	}
	/** Test - UI Validation [Contracting] Validate Contracting Model � �Import� button; ADS-6436*/
	@Test
	public void test02FileImportContractModel() throws Throwable {
		try {
			doClick(modelMap.getContractModelImportButton());
			waitForElementToBeVisible(modelMap.getContractModelImportSelectFileButton());
			Thread.sleep(2000);
//			Omkar 22/6/2023 : Select button not getting clicked
//			modelMap.getContractModelImportSelectFileButton().sendKeys(Keys.ENTER);
//			modelMap.getContractModelImportSelectFileButton().sendKeys(Keys.RETURN);
			doClick(modelMap.getContractModelImportSelectFileButton());//Shilpa added this line for 11.2 update 
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//		    executor.executeScript("arguments[0].scrollIntoView(true);", modelMap.getContractModelImportSelectFileButton());
			driverDelay(500);
			ContractModelsHelper.uploadTheFileusingAutoIT(driver,System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",System.getProperty("user.dir")+"\\AutoIT\\fzMedIPPSTesting.xml");
			driverDelay(500);
			driver.findElement(By.name("sharedHostLocation")).click();
			driverDelay(500);
			doClick(modelMap.getContractModelExportFileSharedLocOption());
			driver.findElement(By.name("logFileName")).sendKeys(logFileName);
			driverDelay(2000);
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//		    executor.executeScript("arguments[0].scrollIntoView(true);", modelMap.getContractModelImportButtonInExportPopUp());
			doClick(modelMap.getContractModelImportButtonInExportPopUp());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(modelMap.getContractModelImportExportstatusPage());
			ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
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
