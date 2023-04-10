package webdriver.scripts.systemmaintenance;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-5801 **/
public class CreateaCustomTaskListwithOverheadTaskListSpecificScreens extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String folder =currentDateTime;
	static String name="Folder Name"+currentDateTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateaCustomTaskListwithOverheadTaskListSpecificScreens", "webdriver.scripts.systemmaintenance", "CreateaCustomTaskListwithOverheadTaskListSpecificScreens");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
			systemMap=BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
	public void test01VerifyClearFilter() throws Throwable {
		try {
			doClick(systemMap.getCustomTaskListFilterButton());
			waitForAjaxExtJs();
			assertTextIsDisplayed("Screen Type Is Equal To Cost");
			doClick(systemMap.getSystemMaintenanceCancelCloseButton());
			doClick(systemMap.getCustomTaskListClearFilterButton());
			doClick(systemMap.getCustomTaskListFilterButton());
			waitForAjaxExtJs();
			assertTextIsNotDisplayed("Screen Type Is Equal To Cost");
			doClick(systemMap.getSystemMaintenanceCancelCloseButton());

			ExtentReport.logPass("PASS", "test01VerifyClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyClearFilter", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
	public void test02VerifyScreensAndDataLoaderTasks() throws Throwable {
		try {
			assertElementIsDisplayedWithXpath("//div[@id='costingScreens-body']//*[text()='Allocation Exceptions']");
			assertElementIsDisplayedWithXpath("//div[@id='costingScreens-body']//*[text()='Allocation Statistic Assignments']");
			assertElementIsDisplayedWithXpath("//div[@id='costingScreens-body']//*[text()='General Information - Overhead']");
			assertElementIsDisplayedWithXpath("//div[@id='costingScreens-body']//*[text()='Overhead Model Calculation Scenarios']");
			assertElementIsDisplayedWithXpath("//div[@id='costingScreens-body']//*[text()='Overhead Account Variability Masters']");
			ExtentReport.logPass("PASS", "test02VerifyScreensAndDataLoaderTasks");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyScreensAndDataLoaderTasks", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddNewFolder() throws Throwable {
		try {
			doClick(systemMap.getSystemMaintenanceAddFolderButton());
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("(//div[@id='ctlCostingTree-body']//tr[contains(@class,'x-grid-row-focused')]/td)[2]"))).sendKeys(Keys.CLEAR).sendKeys(Keys.chord(currentDateTime))
	         .click().pause(200).perform();
			//Drag and Drop not working : move element from screens to task list is pending here
			//			act.moveToElement(driver.findElement(By.xpath("(//div[@id='ctlCostingTree-body']//table/tbody/tr[13]/td/div[text()='"+name+"'])"))).pause(200).dragAndDrop(driver.findElement(By.xpath("(//div[@id='costingScreens-body']//td)[5]")), driver.findElement(By.xpath("(//div[@id='ctlCostingTree-body']//table/tbody/tr[13]/td/div[text()='"+name+"'])"))).release().perform();
			ContractModelsHelper.scrollToView("//*[text()='"+name+"']");
			assertTextIsDisplayed(name);
			doClick(contractMap.getContractModelSaveCopy());
			doClick(ContractingMap.getSaveBenefitPlan());
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test03AddNewFolder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddNewFolder", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
