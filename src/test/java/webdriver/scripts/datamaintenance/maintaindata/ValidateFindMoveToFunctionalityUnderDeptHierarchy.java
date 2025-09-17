package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateFindMoveToFunctionalityUnderDeptHierarchy extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageDeptHierarchies = "Department Hierarchies";
	static String deptHierarchyName="ADS-12688  DEPT Hierarchy";
	static String[] filter = { "Hierarchy Name", "Is", "Equal To", deptHierarchyName };
	static String dept="*DEPTITEM1 new department item 1";
	static String revertDept="*ALLDEPT All Departments";
	/** Support Issues: Automated test script for ADS-12688 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateFindMoveToFunctionalityUnderDeptHierarchy", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateFindMoveToFunctionalityUnderDeptHierarchy");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageDeptHierarchies);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateFindBtnUnderDeptHierarchy_12688() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdepthierarchyFilterBtn());
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getdepthierarchyEditBtn());
			doClick(DataMaintenanceMap.getdeptHierarchyFindBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdeptHierarchyFindWindow());
			doClick(DataMaintenanceMap.getdeptHierarchyFindDept());
			doClick(DataMaintenanceMap.getdeptHierarchyFindWinddApplyBtn());
			ExtentReport.logPass("PASS", "test01ValidateFindBtnUnderDeptHierarchy_12688");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateFindBtnUnderDeptHierarchy_12688", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateMoveToBtnUnderDeptHierarchy_12688() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdeptHierarchyMoveToBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdeptHierarchyMovetoWindow());
			doClick(DataMaintenanceMap.getdeptHierarchyGroupNameDrpDwn());
			doDropdownSelectUsingOptionTextWithelement(
					driver.findElement(By.xpath("(//div[text()='Move To']//following::ul[@class='x-list-plain'])[1]")),
					dept);
			doClick(DataMaintenanceMap.getdeptMoveBtn());
			doClick("//span[text()='*DEPTITEM1 new department item 1']");
			assertElementIsDisplayedWithXpath("//label[text()='2 Codes In DEPTITEM1 new department item 1']//following::div[text()='120 EMERGENCY DEPARTMENT']");
			
			ExtentReport.logPass("PASS", "test02ValidateMoveToBtnUnderDeptHierarchy_12688");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateMoveToBtnUnderDeptHierarchy_12688", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateRevertDeptUnderHierarchy_12688() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdeptHierarchyMoveToBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdeptHierarchyMovetoWindow());
			doClick(DataMaintenanceMap.getdeptHierarchyGroupNameDrpDwn());
			doDropdownSelectUsingOptionTextWithelement(
					driver.findElement(By.xpath("(//div[text()='Move To']//following::ul[@class='x-list-plain'])[1]")),
					revertDept);
			doClick(DataMaintenanceMap.getdeptMoveBtn());
			doClick("//span[text()='"+revertDept+"']");
			assertElementIsDisplayedWithXpath("//label[text()='2 Codes In ALLDEPT All Departments']//following::div[text()='120 EMERGENCY DEPARTMENT']");
			doClick(DataMaintenanceMap.getdepthierarchySaveBtn());
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test03ValidateRevertDeptUnderHierarchy_12688");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateRevertDeptUnderHierarchy_12688", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
