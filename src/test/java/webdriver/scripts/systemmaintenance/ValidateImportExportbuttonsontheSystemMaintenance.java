package webdriver.scripts.systemmaintenance;

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
import webdriver.maps.ContractingMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-6511
 **/
public class ValidateImportExportbuttonsontheSystemMaintenance extends GoHelper{
	private static SystemMaintenanceMap sysmaint;
	private static ContractingMap contractMap;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateImportExportbuttonsontheSystemMaintenance", "webdriver.scripts.systemmaintenance",
				"ValidateImportExportbuttonsontheSystemMaintenance");
		try {
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			sysmaint = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Users");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6511
	@Test
	public void test01ValidateImportOption_6511() throws Throwable {
		try {
			doClick(sysmaint.getUsersPageButtonImport());
			waitForMainPageTitle("Multiple File Import");
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='Multiple File Import']//following::span[text()='Cancel & Close']")),printout);
			assertElementIsDisplayed(contractMap.getContractModelImportButtonInExportPopUp());
			doClick("//div[text()='Multiple File Import']//following::span[text()='Cancel & Close']");
			ExtentReport.logPass("PASS", "test01ValidateImportOption");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateImportOption", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateExportOption_6511() throws Throwable {
		try {
			doClick(sysmaint.getUsersPageButtonExport());
			waitForMainPageTitle("Multiple File Export");
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='Multiple File Export']//following::span[text()='Cancel & Close']")),printout);
			assertElementIsDisplayed(contractMap.getContractModelExportButtonInExportPopUp());
			doClick("//div[text()='Multiple File Export']//following::span[text()='Cancel & Close']");
			ExtentReport.logPass("PASS", "test02ValidateExportOption");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateExportOption", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
