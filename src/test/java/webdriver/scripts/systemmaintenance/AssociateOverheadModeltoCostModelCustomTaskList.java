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
/** Regression test case ADS-5798 **/
public class AssociateOverheadModeltoCostModelCustomTaskList extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String folder =currentDateTime;
	static String name="Folder Name"+currentDateTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AssociateOverheadModeltoCostModelCustomTaskList", "webdriver.scripts.systemmaintenance", "AssociateOverheadModeltoCostModelCustomTaskList");
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
	public void clearNewFolderName(String name) throws Exception {
		Actions act=new Actions(driver);
		doClick(systemMap.getSystemMaintenanceAddFolderButton());
		doClick(systemMap.getSystemMaintenanceAddFoldercol());
		String newFolder=systemMap.getSystemMaintenanceAddFolder().getText();
		for(int i=0;i<=newFolder.length();i++) {
			act.moveToElement(systemMap.getSystemMaintenanceAddFolder()).doubleClick().sendKeys(Keys.BACK_SPACE).perform();
			driverDelay(200);
		}
		act.moveToElement(systemMap.getSystemMaintenanceAddFolder()).doubleClick().perform();
		act.moveToElement(systemMap.getSystemMaintenanceAddFolder()).sendKeys(Keys.chord(currentDateTime+name))
        .click().pause(200).perform();
	}
	@Test
	public void test01AddScreens() throws Throwable {
		try {
			clearNewFolderName("Allocation Exceptions");
			clearNewFolderName("Allocation Statistic Assignments");
			clearNewFolderName("Overhead Account Variability Masters");
			clearNewFolderName("General Information Overhead");
			doClick(contractMap.getContractModelSaveCopy());
			doClick(ContractingMap.getSaveBenefitPlan());
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test01AddScreens");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddScreens", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
