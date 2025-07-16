package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20443**/
public class USERSECURITYStandardCustomRoles extends CimHelper {
	private static CimMap cimMap;
	 static CostingMap selectColumn;
	 static String user="AutomationTestUser";
	 private static String customRole="Auto Test CIM User";
	static String[] filterUser = { "ID", "Is", "Equal To", "automationtestuser" };
	List<String> expectedCalculationTypeOptions=Arrays.asList("Create","View","Edit","Delete","Calculate");
	private static DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("USERSECURITYStandardCustomRoles", "webdriver.scripts.cim",
				"USERSECURITYStandardCustomRoles");
		try {
			selectColumn = BuildMap.getInstance(driver, CostingMap.class);
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("AutomationTestUser");
			goToPage("users");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01Validate_User_WithNoCim_Permission_20443() throws Throwable {
		try {
			assertElementIsNotDisplayed(cimMap.getcimQuickLink());
			ExtentReport.logPass("PASS", "test02Validate_CimUpdates_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CimUpdates_20443", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_User_WithCostAnalystRole_20443() throws Throwable {
		try {
			openUserForedit(cimMap.getusersFilterBtn(),filterUser,cimMap.getusersEditBtn());
			doClick(cimMap.getusersRoleSelectBtn());
			selectRole("add","Cost Analyst");
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			assertElementIsDisplayedWithXpath("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::li[text()='Cost Analyst']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::span[text()='Cost Integration Manager (CIM)']");
			doClick("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::span[text()='Save & Close']");
			doClosePageOnLowerBar("Users");
			ExtentReport.logPass("PASS", "test02Validate_CimUpdates_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CimUpdates_20443", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_User_WithCostAnalystRole_20443() throws Throwable {
		try {
			loginWithTestUser(user,cimMap.getLogoutBtn());
			assertElementIsDisplayed(cimMap.getcimQuickLink());
			assertElementIsDisplayed("//li[@id='costintegrationmanager']");
			//Validate CIM screen is available
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimNewBtn());
			assertElementIsDisplayed(cimMap.getcimEditBtn());
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimDeleteButton());
			validateCIMRoles("Cost Analyst");
			doClick("//div[@data-qtip='Close dialog']");
			ExtentReport.logPass("PASS", "test03Validate_User_WithCostAnalystRole_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_User_WithCostAnalystRole_20443", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04Validate_User_WithCostDeptManagerRole_20443() throws Throwable {
		try {
			openUserForedit(cimMap.getusersFilterBtn(),filterUser,cimMap.getusersEditBtn());
			doClick(cimMap.getusersRoleSelectBtn());
			selectRole("remove","Cost Analyst");
			selectRole("add","Costing Department Manager");
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			assertElementIsDisplayedWithXpath("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::li[text()='Costing Department Manager']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::span[text()='Cost Integration Manager (CIM)']");
			doClick(cimMap.getusersSaveCloseBtn());
			loginWithTestUser(user,cimMap.getLogoutBtn());
			assertElementIsDisplayed(cimMap.getcimQuickLink());
			assertElementIsDisplayed("//li[@id='costintegrationmanager']");
			//Validate CIM screen is available
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimNewBtn());
			assertElementIsDisplayed(cimMap.getcimEditBtn());
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimDeleteButton());
			validateCIMRoles("Costing Department Manager");
			doClick("//div[@data-qtip='Close dialog']");
			openUserForedit(cimMap.getusersFilterBtn(),filterUser,cimMap.getusersEditBtn());
			doClick(cimMap.getusersRoleSelectBtn());
			selectRole("remove","Costing Department Manager");
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			doClick(cimMap.getusersSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04Validate_User_WithCostDeptManagerRole_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_User_WithCostDeptManagerRole_20443", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05Validate_User_WithCustomRole_AllCimPermissions_20443() throws Throwable {
		try {
			doClick(cimMap.getusersEditBtn());
			doClick(cimMap.getusersRoleSelectBtn());
			selectRole("add","Auto Test CIM User");
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			doClick(cimMap.getusersSaveCloseBtn());
			validateCIMRoles(customRole);//Custom Role
			doClick("(//div[text()='"+customRole+"']//following::span[text()='Select All'])");
			doClick("(//div[text()='"+customRole+"']//following::span[text()='Save & Close'])");
			loginWithTestUser(user,cimMap.getLogoutBtn());
			assertElementIsDisplayed(cimMap.getcimQuickLink());
			assertElementIsDisplayed("//li[@id='costintegrationmanager']");
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimNewBtn());
			assertElementIsDisplayed(cimMap.getcimEditBtn());
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimDeleteButton());
			

			ExtentReport.logPass("PASS", "test04Validate_User_WithCostDeptManagerRole_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_User_WithCostDeptManagerRole_20443", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test07Validate_User_WithCustomRole_NoCimPermissions_20443() throws Throwable {
		try {
			validateCIMRoles(customRole);//Custom Role
			selectPermission(customRole, "De-Select All");
			loginWithTestUser(user,cimMap.getLogoutBtn());
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsNotDisplayed(cimMap.getcimNewBtn());
			assertElementIsNotDisplayed(cimMap.getcimEditBtn());
			assertElementIsNotDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsNotDisplayed(cimMap.getcimDeleteButton());
			
			assertElementIfDisplayIsNone("//div[contains(@id,'cimmasterlist')]//a[text()='Calculation Status Screen']/../../..");
			ExtentReport.logPass("PASS", "test07Validate_User_WithCustomRole_NoCimPermissions_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07Validate_User_WithCustomRole_NoCimPermissions_20443", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08Validate_User_WithCustomRole_OnlyEdit_CimPermissions_20443() throws Throwable {
		try {
			validateCIMRoles(customRole);//Custom Role
			selectPermission(customRole, "Edit");
			loginWithTestUser(user,cimMap.getLogoutBtn());
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimEditBtn());
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsNotDisplayed(cimMap.getcimNewBtn());
			assertElementIsNotDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsNotDisplayed(cimMap.getcimDeleteButton());
			assertElementIfDisplayIsNone("//div[contains(@id,'cimmasterlist')]//a[text()='Calculation Status Screen']/../../..");
			ExtentReport.logPass("PASS", "test08Validate_User_WithCustomRole_OnlyEdit_CimPermissions_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08Validate_User_WithCustomRole_OnlyEdit_CimPermissions_20443", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09Validate_User_WithCustomRole_OnlyDelete_CimPermissions_20443() throws Throwable {
		try {
			validateCIMRoles(customRole);//Custom Role
			selectPermission(customRole, "Delete");
			loginWithTestUser(user,cimMap.getLogoutBtn());
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimDeleteButton());
			assertElementIsNotDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsNotDisplayed(cimMap.getcimEditBtn());
			assertElementIsNotDisplayed(cimMap.getcimNewBtn());
			assertElementIsNotDisplayed(cimMap.getcimCalculateBtn());
			assertElementIfDisplayIsNone("//div[contains(@id,'cimmasterlist')]//a[text()='Calculation Status Screen']/../../..");
			ExtentReport.logPass("PASS", "test09Validate_User_WithCustomRole_OnlyDelete_CimPermissions_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09Validate_User_WithCustomRole_OnlyDelete_CimPermissions_20443", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10Validate_User_WithCustomRole_OnlyCreate_CimPermissions_20443() throws Throwable {
		try {
			validateCIMRoles(customRole);//Custom Role
			selectPermission(customRole, "Create");
			loginWithTestUser(user,cimMap.getLogoutBtn());
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimNewBtn());
			assertElementIsNotDisplayed(cimMap.getcimDeleteButton());
			assertElementIsNotDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsNotDisplayed(cimMap.getcimEditBtn());
			assertElementIfDisplayIsNone("//div[contains(@id,'cimmasterlist')]//a[text()='Calculation Status Screen']/../../..");
			ExtentReport.logPass("PASS", "test10Validate_User_WithCustomRole_OnlyCreate_CimPermissions_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10Validate_User_WithCustomRole_OnlyCreate_CimPermissions_20443", driver, e);
			fail(e.getMessage());
		}
		
	}
	@Test
	public void test11Validate_User_WithCustomRole_OnlyCalculate_CimPermissions_20443() throws Throwable {
		try {
			validateCIMRoles(customRole);//Custom Role
			selectPermission(customRole, "Calculate");
			loginWithTestUser(user,cimMap.getLogoutBtn());
			goToPage("Cost Integration Manager");
			assertElementIsDisplayed(cimMap.getcimFilterButton());
			assertElementIsDisplayed(cimMap.getcimClearFilterButton());
			assertElementIsDisplayed(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed(cimMap.getcimViewBtn());
			assertElementIsDisplayed(cimMap.getcimCalcStatusSreenBtn());
			assertElementIsDisplayed(cimMap.getcimCalculateBtn());
			assertElementIsNotDisplayed(cimMap.getcimNewBtn());
			assertElementIsNotDisplayed(cimMap.getcimEditBtn());
			assertElementIsNotDisplayed(cimMap.getcimDeleteButton());
			ExtentReport.logPass("PASS", "test11Validate_User_WithCustomRole_OnlyCalculate_CimPermissions_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11Validate_User_WithCustomRole_OnlyCalculate_CimPermissions_20443", driver, e);
			fail(e.getMessage());
		}
		finally {
			openUserForedit(cimMap.getusersFilterBtn(),filterUser,cimMap.getusersEditBtn());
			doClick(cimMap.getusersRoleSelectBtn());
			selectRole("remove",customRole);
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			doClick("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::span[text()='Save & Close']");
			doClosePageOnLowerBar("Users");
			doClosePageOnLowerBar("Cost Integration Manager (CIM)");
		}
	}
	public void selectRole(String role,String name) throws Exception {
		if(role=="add") {
			doClick("//div[contains(text(),'Available')]//following::li[text()='"+name+"']");
			doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
		}
		if(role=="remove") {
			doClick("//div[contains(text(),'Selected')]//following::li[text()='"+name+"']");
			doClick("//div[contains(text(),'Roles')]//following::span[text()='Remove']");
	}
	}
	
	public void validateCIMRoles(String role) throws Throwable {
		goToPage("roles");
		scrollToView("//tbody/tr/td[2]/div[text()='"+role+"']");
		doClick("//tbody/tr/td[2]/div[text()='"+role+"']");
		doClick(cimMap.getusersRoleEditBtn());
		doClick("(//div[text()='"+role+"']//following::span[text()='Cost Integration Manager (CIM)'])[2]");
		assertElementIsDisplayedWithXpath("//label[text()='Permissions']//following::div[contains(@id,'container')]//div/div//label[@data-ref='boxLabelEl'][text()='Create']");
		assertElementIsDisplayedWithXpath("//label[text()='Permissions']//following::div[contains(@id,'container')]//div/div//label[@data-ref='boxLabelEl'][text()='View']");
		assertElementIsDisplayedWithXpath("//label[text()='Permissions']//following::div[contains(@id,'container')]//div/div//label[@data-ref='boxLabelEl'][text()='Edit']");
		assertElementIsDisplayedWithXpath("//label[text()='Permissions']//following::div[contains(@id,'container')]//div/div//label[@data-ref='boxLabelEl'][text()='Delete']");
		assertElementIsDisplayedWithXpath("//label[text()='Permissions']//following::div[contains(@id,'container')]//div/div//label[@data-ref='boxLabelEl'][text()='Calculate']");
	}
	public void loginWithTestUser(String user,WebElement element) throws Exception {
		doClick(element);
		loginUser(user);
	}
	public void openUserForedit(WebElement filterBtn,String[] filter,WebElement editBtn) throws Throwable {
		goToPage("users");
		doClick(filterBtn);
		doFilterCreateCIM(filter);
		doClick(editBtn);
	}

	public void selectPermission(String role,String permission) throws Exception {
			if(permission=="De-Select All") {
				doClick("(//div[text()='"+role+"']//following::span[text()='"+permission+"'])");
				doClick("(//div[text()='"+role+"']//following::span[text()='Save & Close'])");
			}
			else {
				doClick("(//div[text()='"+role+"']//following::span[text()='De-Select All'])");
				doClick("(//div[text()='"+role+"']//following::label[text()='"+permission+"'])");
				doClick("(//div[text()='"+role+"']//following::span[text()='Save & Close'])");
			}
		
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
