package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateChangesToTheDepartmentHierarchy extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageContractBatches = "Department Hierarchies";
	static String deptHierarchyName="ADS-12686 DEPT Hierarchy Test";
	static String[] filter = { "Hierarchy Name", "Is", "Equal To", deptHierarchyName };
	/** Support Issues: Automated test script for ADS-12686 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateChangesToTheDepartmentHierarchy", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateChangesToTheDepartmentHierarchy");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageContractBatches);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveDeptHierarchy_12686() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdepthierarchyFilterBtn());
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getdepthierarchyEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdepthierarchyForm());
			WebElement source=driver.findElement(By.xpath("//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container']//div[text()='120 EMERGENCY DEPARTMENT']"));
			WebElement target=driver.findElement(By.xpath("//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container']//span[text()='*ALLDEPT All Departments']"));
			CimHelper.dragAndDrop(source, target);
			driverDelay();
			doClick(DataMaintenanceMap.getdepthierarchySaveBtn());
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			doClick(DataMaintenanceMap.getdepthierarchyEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdepthierarchyForm());
			doClick(DataMaintenanceMap.getdeptNode());
			assertElementIsDisplayedWithXpath("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='120 EMERGENCY DEPARTMENT']");
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Removehierarchy_12686() throws Throwable {
		try {
			WebElement source=driver.findElement(By.xpath("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='120 EMERGENCY DEPARTMENT']"));
			WebElement target=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')])[1]"));
			CimHelper.dragAndDrop(source, target);
			driverDelay(800);
			doClick(DataMaintenanceMap.getdepthierarchySaveBtn());
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
