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
/** Regression test case ADS-6591 **/
public class CreateCustomTaskListwithCostingTaskListSpecificScreens extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String folder =currentDateTime;
	static String name="Folder Name"+currentDateTime;
	Actions act=new Actions(driver);
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
	
	@Test
	public void test01AddNewFolder() throws Throwable {
		try {
			doClick(systemMap.getSystemMaintenanceAddFolderButton());
//			Omkar 12/5/2023 : xpath changes for 11.2
//			act.moveToElement(driver.findElement(By.xpath("(//div[@id='ctlCostingTree-body']//tr[contains(@class,'x-grid-row-focused')]/td)[2]"))).sendKeys(Keys.CLEAR).sendKeys(Keys.chord(currentDateTime))
//	         .click().pause(200).perform();
			act.moveToElement(driver.findElement(By.xpath("(//span[text() = 'Folder Name'])[1]"))).sendKeys(Keys.CLEAR).sendKeys(Keys.chord(currentDateTime))
	         .click().pause(200).perform();
			
			ContractModelsHelper.scrollToView("//*[text()='"+name+"']");
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01AddNewFolder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddNewFolder", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02SelectScreensUsingControlKey() throws Throwable {
		try {
			act.keyDown(Keys.LEFT_CONTROL).click(driver.findElement(By.xpath("(//div[@id='costingScreens-body']//following::div[text()='Charge Masters'])[1]"))).
			click(driver.findElement(By.xpath("(//div[@id='costingScreens-body']//following::div[text()='Department Masters'])[1]"))).click(driver.findElement(By.xpath("(//div[@id='costingScreens-body']//following::div[text()='GL Account Masters'])[1]"))).keyUp(Keys.LEFT_CONTROL).build().perform();
			ExtentReport.logPass("PASS", "test02SelectScreens");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02SelectScreens", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
