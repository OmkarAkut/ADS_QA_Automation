package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
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
/** Regression test case ADS-5783 **/
public class EditExistingRoleSetup extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String folder = currentDateTime;
	static String name = "Folder Name" + currentDateTime;
	static String userRole = "ASEC2310 CustomRole9";
	static String assignedUserSelect = "AdHoc_Report_Designer   automationadhocrepdes1";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EditExistingRoleSetup", "webdriver.scripts.systemmaintenance",
				"EditExistingRoleSetup");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			systemMap = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Roles");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01EditUserRole() throws Throwable {
		try {
			doClick("//div[text()='" + userRole + "']");
			doClick(systemMap.getRolesPageButtonEdit());
//			Omkar 24/4/2023 : xpath changes for 11.2
//			waitForPresenceOfElement("//span[contains(@id,'dynamicwindow')][text()='" + userRole + "']");
			waitForPresenceOfElement("//div[contains(@id,'dynamicwindow')][text()='" + userRole + "']");
			assertElementForAttributeContains(systemMap.getrolesPageEditName(), printout);
			assertElementIsDisplayedWithXpath(
					"//div[contains(@id,'rolesForm')]//label[contains(@class,'fieldLabelCls')] [text()='Assigned Users']");
//			Omkar 24/4/2023 : xpath changes for 11.2
//			assertElementIsDisplayedWithXpath(
//					"//div[contains(@id,'rolesForm')]//table[contains(@class,'itemSelctorPanelNoPad')]//following::div[contains(@class,'x-btn windowbtn x-btn-default-small')]//span[text()='Select']//parent::button");
			
			assertElementIsDisplayedWithXpath(
					"//span[text()='Select']//parent::span");
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test01EditUserRole");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01EditUserRole", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02AddAssignedUsers() throws Throwable {
		try {
//			Omkar 25/4/2023 : xpath changes for 11.2
//			doClick("//div[contains(@id,'rolesForm')]//table[contains(@class,'itemSelctorPanelNoPad')]//following::div[contains(@class,'x-btn windowbtn x-btn-default-small')]//span[text()='Select']//parent::button");
			doClick("(//span[@data-ref='btnInnerEl'][text()='Select']//parent::span)[1]");
//			Omkar 9/5/2023 : xpath changes for 11.2
//			assertElementIsDisplayedWithXpath("//span[contains(@id,'dynamicwindow')][text()='Assigned Users']");
//			doClick("(//div[contains(@class,'x-btn windowbtn x-box-item x-btn-default-small x-icon-text-left')]//span[text()='All']//parent::button)[2]");
			Thread.sleep(300);
			assertElementIsDisplayedWithXpath("//div[contains(@id,'dynamicwindow')]//div[text()='Assigned Users']");
			doClick(systemMap.getUserRoleSelectAllLeftButton());
			
//			Omkar 10/5/2023 : xpath changes for 11.2
//			doDropdownSelectUsingOptionText(driver.findElement(By.xpath(
//					"//div[contains(@id,'rolesForm')]//table[contains(@class,'itemSelctorPanelNoPad')]//following::div[contains(@class,'x-btn windowbtn x-btn-default-small')]//span[text()='Select']//parent::button")),
//					systemMap.getAssignedUsers(), "AdHoc_Report_Designer   automationadhocrepdes1");
			doDropdownSelectUsingOptionText(driver.findElement(By.xpath(
					"(//div[contains(@id,'buttonsContainer-innerCt')]//span[text()='Select'])[1]/..")),
					systemMap.getAssignedUsers(), "AdHoc_Report_Designer   automationadhocrepdes1");
			doClick(systemMap.getAssignedUsersSelectButton());
			doClick(contractMap.getApplySelections());
			assertTextIsDisplayed(assignedUserSelect);
			ExtentReport.logPass("PASS", "test02AddAssignedUsers");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddAssignedUsers", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03AddMenuItems() throws Throwable {
		try {
			doClick(systemMap.getMenuItemsSelectButton());
			waitForMainPageTitle("Menu Items");
			doClick(systemMap.getAssignedUsersRemoveButton());
//			Omkar 29/5/2023 : xpath changes for 11.2
//			doClick("(//div[contains(@class,'x-btn windowbtn x-box-item x-btn-default-small x-icon-text-left')]//span[text()='All']//parent::button)[2]");
			doClick(systemMap.getUserRoleSelectAllLeftButton());
			ContractModelsHelper.doDropdownSelectUsingOptionTextOnly(systemMap.getAssignedUsers(),
					"Ad Hoc Report Design");
			doClick(systemMap.getAssignedUsersSelectButton());
			ContractModelsHelper.doDropdownSelectUsingOptionTextOnly(systemMap.getAssignedUsers(), "Costing Reports");
			doClick(systemMap.getAssignedUsersSelectButton());
			ContractModelsHelper.doDropdownSelectUsingOptionTextOnly(systemMap.getAssignedUsers(),
					"Calculation Status");
			doClick(systemMap.getAssignedUsersSelectButton());
			doClick(contractMap.getApplySelections());
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//*[text()='Ad Hoc Report Design']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//*[text()='Costing Reports']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//*[text()='Calculation Status']");
			ExtentReport.logPass("PASS", "test03AddMenuItems");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddMenuItems", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04AddActionPermission() throws Throwable {
		try {
//			doClick("//div[text()='Costing']");
//			doClick("//span[text()='De-Select All']//parent::button");
//			doClick("//span[text()='Select All']//parent::button");
//			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			//Shilpa updated xpath for 11.2 on 12.20.2023
			doClick("//span[text()='Costing']");
			doClick("//span[text()='De-Select All']");
			doClick("//span[text()='Select All']");
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			assertElementIsNotDisplayed("//span[contains(@id,'dynamicwindow')][text()='" + userRole + "']");
			ExtentReport.logPass("PASS", "test03AddMenuItems");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddMenuItems", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04VerifyUpdatedRole() throws Throwable {
		try {
			doClick("//div[text()='" + userRole + "']");
			doClick(systemMap.getRolesPageButtonEdit());
			Thread.sleep(300);
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'treepanel')]//*[text()='Ad Hoc Report Design']"))).perform();
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//*[text()='Ad Hoc Report Design']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//*[text()='Costing Reports']");
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//*[text()='Calculation Status']");
			//Shilpa updated xpath for 11.2 on 12.20.2023
			assertElementIsDisplayedWithXpath("(//div[contains(@id,'treepanel')]//*[text()='Calculation Status'])[1]");
			assertTextIsDisplayed(assignedUserSelect);
//			doClick("//div[text()='Costing']");
			//Shilpa updated xpath for 11.2 on 12.20.2023
			doClick("//span[text()='Costing']");
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04VerifyUpdatedRole");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyUpdatedRole", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
