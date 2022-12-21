package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestUIValidationContractingCreatingaNewContractingFolder extends GoHelper {

	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractFolderName = "Folder" + currentDateTime;

	/** Regression: Automated test script for ADS-6410 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TestUIValidationContractingCreatingaNewContractingFolder",
				"webdriver.scripts.contracting", "TestUIValidationContractingCreatingaNewContractingFolder");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println(
					"Test Class: " + TestUIValidationContractingCreatingaNewContractingFolder.class.getSimpleName());
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
	public void test01CreateNewContractFolder() throws Throwable {
		try {
			doClick(modelMap.getNewContractFolderBtn());
			waitForElementToBeVisible(modelMap.getNewFolderPopUp());
			doClick(modelMap.getNewFolderNameInput());
			modelMap.getNewFolderNameInput().sendKeys(contractFolderName);
			driverPause();
			doClick(modelMap.getNewFolderNameSave());
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
			doClick(modelMap.getContractingTreeExpand());
			driverPause();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//div[text()='" + contractFolderName + "']")));
			Thread.sleep(500);
			assertTextIsDisplayed(contractFolderName);
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test01CreateNewContractFolder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractFolder", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
