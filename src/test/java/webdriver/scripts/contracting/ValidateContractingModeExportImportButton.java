package webdriver.scripts.contracting;
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
	public void test01FileExportContractModel_6437() throws Throwable {
		try {
			doClick(modelMap.getContractModelExportButton());
			waitForElementToBeVisible(modelMap.getContractModelExportPopUp());
			doClick(modelMap.getContractModelSelectFileButton());
			waitForElementToBeVisible(modelMap.getContractModelInExportPopUp());
			driver.findElement(By.name("carrierfield")).sendKeys(contractModel);
			doClick(ContractingMap.getContractModelApplyInExportPopUp());
			driver.findElement(By.name("sharedHostLocation")).click();
			driverDelay(500);
			doClick(ContractingMap.getContractModelExportFileSharedLocOption());
			driver.findElement(By.name("logFileName")).sendKeys(logFileName);
			driverDelay(500);
			doClick(modelMap.getContractModelExportButtonInExportPopUp());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(ContractingMap.getContractModelImportExportstatusPage());
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
	public void test02FileImportContractModel_6436() throws Throwable {
		try {
			doClick(modelMap.getContractModelImportButton());
			waitForElementToBeVisible(modelMap.getContractModelImportSelectFileButton());
			driverDelay(2500);
//			doactionClick(modelMap.getContractModelImportSelectFileButton());//Shilpa added this line for 11.2 update 
			//Shilpa: update on 01.23.2026
			
//			Actions action =new Actions(driver);
//			action.moveToElement(driver.findElement(By.xpath("(//span[text()='Import Into:']//following::span[text()='Select'])[2]"))).click().perform();
			//Shilpa:updated to use action class tab to element 05:02:2026
//			doactionClick(driver.findElement(By.xpath("//input[@name='importdata']")));
			keyboardNavig(2);
			driverDelay(2000);
			//Shilpa update file import using Robot instead of auto it due to security issues 7.3.2025
			fileImport(System.getProperty("user.dir")+"\\TestFiles\\fzMedIPPSTesting.xml");
			driverDelay(2000);
//			Omkar 22/6/2023 : Select button not getting clicked
//			modelMap.getContractModelImportSelectFileButton().sendKeys(Keys.ENTER);
//			modelMap.getContractModelImportSelectFileButton().sendKeys(Keys.RETURN);
//			JavascriptExecutor executor = (JavascriptExecutor) driver;
//		    executor.executeScript("arguments[0].scrollIntoView(true);", modelMap.getContractModelImportSelectFileButton());
			driver.findElement(By.name("sharedHostLocation")).click();
			driverDelay(500);
			doClick(ContractingMap.getContractModelExportFileSharedLocOption());
			driver.findElement(By.name("logFileName")).sendKeys(logFileName);
			driverDelay(2300);
			
			doClick(modelMap.getContractModelImportButtonInExportPopUp());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(ContractingMap.getContractModelImportExportstatusPage());
			ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Import/Export Status");
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test02ImportContractModel");
			//Shilpa: Updated for 11.2 3.2.2025
			/*
			if(Driver.getBrowser().equals("chrome")) {
				driver.findElement(By.name("sharedHostLocation")).click();
				driverDelay(500);
				doClick(modelMap.getContractModelExportFileSharedLocOption());
				driver.findElement(By.name("logFileName")).sendKeys(logFileName);
				driverDelay(2300);
				doClick(modelMap.getContractModelImportSelectFileButton());//Shilpa added this line for 11.2 update 
				driverDelay(2000);
				ContractModelsHelper.uploadTheFileusingAutoIT(driver,System.getProperty("user.dir") + "\\AutoIT\\ValidateContractingModeExportImportButtonChrome.exe");
				driverDelay(5000);
				doClick(modelMap.getContractModelImportButtonInExportPopUp());
				waitForSpinnerToEnd();
				assertElementIsDisplayed(modelMap.getContractModelImportExportstatusPage());
				ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
				doClosePageOnLowerBar("Import/Export Status");
				doClosePageOnLowerBar("Contract Models");
				ExtentReport.logPass("PASS", "test02ImportContractModel");
			}
			if(Driver.getBrowser().equals("edge")) {
				driver.findElement(By.name("sharedHostLocation")).click();
				driverDelay(500);
				doClick(modelMap.getContractModelExportFileSharedLocOption());
				driver.findElement(By.name("logFileName")).sendKeys(logFileName);
				driverDelay(2300);
				doClick(modelMap.getContractModelImportSelectFileButton());//Shilpa added this line for 11.2 update 
				driverDelay(2000);
				ContractModelsHelper.uploadTheFileusingAutoIT(driver,System.getProperty("user.dir") + "\\AutoIT\\ValidateContractingModeExportImportButtonEdge.exe");
				driverDelay(5000);
				doClick(modelMap.getContractModelImportButtonInExportPopUp());
				waitForSpinnerToEnd();
				assertElementIsDisplayed(modelMap.getContractModelImportExportstatusPage());
				ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
				doClosePageOnLowerBar("Import/Export Status");
				doClosePageOnLowerBar("Contract Models");
				ExtentReport.logPass("PASS", "test02ImportContractModel");
			}
			*/
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
