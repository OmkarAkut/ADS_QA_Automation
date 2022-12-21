package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestUIValidationContractingValidateContractingModelCopyPasteButtons extends GoHelper{

	private static ContractingMap modelMap;
	private static String ContractModel="#fz Med IPPS Testing";
	private static String UpdatedContractModel;
	private static ContractingHelperMethods contractHelper = new ContractingHelperMethods();
	Actions action=new Actions(driver);
	static String modelName;
	static String currentDateTime = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
	/** Regression: Automated test script for ADS-6434 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TestUIValidationContractingValidateContractingModelCopyPasteButtons",
				"webdriver.scripts.contracting",
				"TestUIValidationContractingValidateContractingModelCopyPasteButtons");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println("Test Class: "
					+ TestUIValidationContractingValidateContractingModelCopyPasteButtons.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForAjaxExtJs();
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[text()='"+ContractModel+"']")));
			driver.findElement(By.xpath("//div[text()='"+ContractModel+"']")).click();
			doClick(modelMap.getContractModelButtonCopy());
			assertElementIsEnabled(modelMap.getContractModelButtonPaste(),printout);
			doClick(modelMap.getContractModelButtonPaste());
			waitForElementToBeVisible(modelMap.getContractModelPastePopup());
			driverDelay();
			modelName=modelMap.getContractModelPasteNameInput().getAttribute("value");
			if(modelName.equalsIgnoreCase("Copy of"+ContractModel+"")) {
				assertTrue(printout);
			}
			modelMap.getContractModelPastePopup().sendKeys(Keys.chord(Keys.CONTROL,"a"));
			modelMap.getContractModelPastePopup().sendKeys("Copy of"+ContractModel+""+currentDateTime);
			UpdatedContractModel=modelMap.getContractModelPasteNameInput().getAttribute("value");
			doClick(modelMap.getContractModelSaveCopy());
			doClick(CostingMap.getContractingName);
			waitForDisplayedSpinnerToEnd();
			driver.findElement(By.xpath("//input[@name='searchText']")).click();
			driverDelay();
			// Takes time to display the new contract model so just click on contracting name to refresh the grid
			doClick(CostingMap.getContractingName);
			doSearchForContractModel(UpdatedContractModel);
			driverDelay();
			assertTextIsDisplayed(UpdatedContractModel);
			doClosePageOnLowerBar("Model Library");
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
