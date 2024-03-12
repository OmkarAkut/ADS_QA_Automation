package Regression;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RVUExportImportFunction extends GoHelper {
	static GeneralElementsMap generalMap;
	static CostingMap costing;
	static ContractingMap contracting;
	static String costModel="BC COST MODEL";
	static String[] filter= {"Cost Model Name","Is","Equal To",costModel};

	/** Automates test ticket ADS-6659. ADS-6660*/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("RVUExportImportFunction", "webdriver.scripts.costing", "RVUExportImportFunction");
		try {
			generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contracting=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForAjaxExtJs();
			waitForDisplayedSpinnerToEnd();
			goToPage("Rvu Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6659
	@Test
	public void test01VerifyRVUExportPopUp_6660() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonExport());
			waitForPageTitle("Export Data");
			assertTextIsDisplayed("Export Data");
			doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test01VerifyRVUExportPopUp");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyRVUExportPopUp", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ExportRvuAndValidateImportExportStatus_6660() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonExport());
			waitForPageTitle("Export Data");
			assertTextIsDisplayed("Export Data");
			doClick(costing.getRvuSecSelectorSelectButton());
			waitForMainPageTitle("Find Items");
			ContractModelsHelper.keyInValues(costing.getUnitCostQuickCalculationDepartmentField(), costModel);
			doClick(ContractingMap.getContractModelApplyInExportPopUp());
			selectFileLocAndaddFileName(costing.getRvuExportButton());
			ExtentReport.logPass("PASS", "test02ExportRvuAndValidateImportExportStatus");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ExportRvuAndValidateImportExportStatus", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ImportRvuAndValidateImportExportStatus_6659() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonImport());
			waitForPageTitle("Import Data");
			assertTextIsDisplayed("Import Data");
			doClick(costing.getRvuSecImportSelectButton());
			costing.getRvuSecImportSelectButton().sendKeys(Keys.ENTER);;
			driverDelay(500);
			ContractModelsHelper.uploadTheFileusingAutoIT(driver,System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",System.getProperty("user.dir")+"\\AutoIT\\RvuImportFie.txt");
			selectFileLocAndaddFileName(costing.getRvuImportButton());
			ExtentReport.logPass("PASS", "test03ImportRvuAndValidateImportExportStatus");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ImportRvuAndValidateImportExportStatus", driver, e);
			fail(e.getMessage());
		} 
	}
	public static void selectFileLocAndaddFileName(WebElement button) throws Throwable {
		doClick(costing.getRvuSharedLocDropdown());
		driverDelay(300);
		doClick(contracting.getContractModelExportFileSharedLocOption());
		ContractModelsHelper.keyInValues(costing.getRvuFileNameInput(), "Test");
		doClick(button);
		waitForSpinnerToEnd();
		assertElementIsDisplayed(contracting.getContractModelImportExportstatusPage());
		ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
		doClosePageOnLowerBar("Import/Export Status");
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("RVU Maintenance");
		ExtentReport.report.flush();

	}
}
