package webdriver.scripts.costing;

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
import webdriver.maps.CostingMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting extends GoHelper {
	static CostingMap costing;
	static ContractingMap modelMap;
	private static SystemMaintenanceMap systemMap;
	private static String costModel="BC COST MODEL";
	private static String deptHierarchyName="BCDEPTHIERARCHY";
	static String[] filter= {"Name","Is","Equal To",costModel};
	static String[] filterByDeptHierarchy= {"Hierarchy Name","Is","Equal To",deptHierarchyName};
	/** Automates test ticket ADS-6589 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting", "webdriver.scripts.costing",
				"UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			systemMap=BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("CM Test");
			assertTextIsDisplayed("All Masters");
//			assertTextIsDisplayed("Cost Scenarios");
			assertTextIsDisplayed("Groupings");
			assertTextIsDisplayed("Miscellaneous");
			driver.findElement(By.xpath("(//input[@name='costingOption'])[2]")).click();
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			doClick(ContractingMap.getSaveBenefitPlan());
			doClick("(//div[contains(@id,'button')]//following::span[text()='Save'])[2]");
			waitForAjaxExtJs();
			doClick("//div[text()='Log Out']");
			
			/*
			Login.loginUser("CostAnalyst1");
			goToPage("Costing Models");
			waitForAjaxExtJs();
			*/
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6589
	@Test
	public void test01VerifyFoldersShownUnderTaskList_6589() throws Throwable {
		try {
			Login.loginUser("CostAnalyst1");
			goToPage("Costing Models");
			waitForAjaxExtJs();
			doClick(costing.getCostModelFilterButton());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);
			assertTextIsDisplayed("CM Test");
			doClickTreeItem("CM Test");
			assertTextIsDisplayed("All Masters");
//			assertTextIsDisplayed("Cost Scnenarios");
			assertTextIsDisplayed("Groupings");
			assertTextIsDisplayed("Miscellaneous");
			doClickTreeItem("All Masters");
			waitForMainPageTitle("Activity Statistic Masters");
			assertTextIsDisplayed("Activity Statistic Masters");
			assertTextIsDisplayed("Ad Hoc Statistic Masters");
			assertTextIsDisplayed("Cost Component Group Masters");
			assertTextIsDisplayed("Department Masters");
			assertTextIsDisplayed("Cost Component Variability Masters");
			assertTextIsDisplayed("GL Adjustment Masters");
			assertTextIsDisplayed("GL Reclassification Masters");
			assertTextIsDisplayed("Vendor Masters");
			assertTextIsDisplayed("GL Statistic Masters");
//			doClickTreeItemWithCheckbox("Department Masters");
			doClickTreeItem("Department Masters");//Shilpa update on 24.5.2024
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'masterlist')]//following::div[contains(@id,'dynamicGrid')]//table//tr");
//			doClickTreeItem("All Masters");
			doClickTreeItem("Groupings");// scroll issue
			waitForMainPageTitle("Chargeable Activity Groups");
			assertTextIsDisplayed("Chargeable Activity Groups");
			assertTextIsDisplayed("Department Groups");
			assertTextIsDisplayed("Department Hierarchies");
			assertTextIsDisplayed("GL Account Hierarchies");
			doClickTreeItem("Department Hierarchies");
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'glaccounthierarchy')]//following::div[contains(@id,'dynamicGrid')]//table//tr");
			ExtentReport.logPass("PASS", "test01VerifyFoldersShownUnderTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyFoldersShownUnderTaskList", driver, e);
			fail(e.getMessage());
		}
	
	}
	@Test
	public void test02VerifyDepartmentHierarchy_6589() throws Throwable {
		try {
			doClick(systemMap.getDeptHierarchyFilterButton());
			doFilterCreate(filterByDeptHierarchy);
			tableDoubleClickCellFirstColumn(deptHierarchyName);
			assertTextIsDisplayed("*ALLDEPTS All Departments");
			assertTextIsDisplayed("*GROUP1 Group 1");
			assertTextIsDisplayed("*GROUP2 Group 2");
			assertTextIsDisplayed("*UNUSED Unused depts");
			doClick("//*[text()='*GROUP1 Group 1']");
			assertTextIsDisplayed("BCDEPT1 BCDEPT1");
			assertTextIsDisplayed("BCDEPT2 BCDEPT2");
			doClick("//*[text()='*GROUP2 Group 2']");
			assertTextIsDisplayed("BCDEPT3 BCDEPT3");
			assertTextIsDisplayed("BCDEPT4 BCDEPT4");
			doClick("//*[text()='*UNUSED Unused depts']");
			assertElementIsDisplayedWithXpath("//label[text()='0 Codes In UNUSED Unused depts']");
			doClick("//div[text()='Log Out']");
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			waitForDisplayedSpinnerToEnd();
			driver.findElement(By.xpath("(//input[@name='costingOption'])[1]")).click();
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			doClick(ContractingMap.getSaveBenefitPlan());
			doClick("(//div[contains(@id,'button')]//following::span[text()='Save'])[2]");
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test02VerifyDepartmentHierarchy");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyDepartmentHierarchy", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
