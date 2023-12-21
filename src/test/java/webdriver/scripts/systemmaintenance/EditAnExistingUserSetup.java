package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.fail;
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
/** Regression test case ADS-5781 **/
public class EditAnExistingUserSetup extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	private static String userID = "testuser";
//	Omkar 12/10/2023 : Removing 0008 PAYMENT PLAN as scrolling action is not working for 11.2
//	private static String[] entities = { "0000 PRIVATE PAY", "0001 PRIVATE PAY PENDING", "0008 PAYMENT PLAN" };
	private static String[] entities = { "0000 PRIVATE PAY", "0001 PRIVATE PAY PENDING"};
	private static String dept = "150 - old master 150";
	private static String[] filters = { "ID", "Is", "Equal To", userID };
	private static String firstName = "TestName2";
	private static String lastName = "LastName2";
	private static String displayName = "DisplayName2";
	private static String initials = "ABCD2";
//	private static String newPassword = "password1234";
//	private static String confirmNewPassword = "password1234";
	//Shilpa updated pwd as per new criteria for 11.2 
	private static String newPassword = "ZAQ!2wsx@123";
	private static String confirmNewPassword = "ZAQ!2wsx@123";
	private static String email = "Name2@gmail.com";
	private static String jobFunction = "Software testing2";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EditAnExistingUserSetup", "webdriver.scripts.systemmaintenance",
				"EditAnExistingUserSetup");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			systemMap = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Users");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01OpenUserID() throws Throwable {
		try {
			doClick(systemMap.getUsersPageButtonFilter());
			driverDelay(100);
			doFilterCreate(filters);
			doClick(systemMap.getUsersPageButtonEdit());
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test01");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyDisabledFields() throws Throwable {
		try {
			assertElementForAttributeContains(systemMap.getUserID(), printout);
			assertElementForAttributeContains(systemMap.getDbUsername(), printout);
			ExtentReport.logPass("PASS", "test02VerifyDisabledFields");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyDisabledFields", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03AddUserDetails() throws Throwable {
		try {
			ContractModelsHelper.keyInValues(systemMap.getUserFirstName(), firstName);
			ContractModelsHelper.keyInValues(systemMap.getUserLastName(), lastName);
			ContractModelsHelper.keyInValues(systemMap.getUserDisplayName(), displayName);
			ContractModelsHelper.keyInValues(systemMap.getUserInitialsName(), initials);
			ContractModelsHelper.keyInValues(systemMap.getUserEmailName(), email);
			ContractModelsHelper.keyInValues(systemMap.getUserJobFunction(), jobFunction);
			ContractModelsHelper.keyInValues(systemMap.getUserNewPassword(), newPassword);
			ContractModelsHelper.keyInValues(systemMap.getUserConfirmNewPassword(), confirmNewPassword);
			assertAsteriskIsDisplayed("New Password");
			assertAsteriskIsDisplayed("Confirm New Password");
			assertAsteriskIsDisplayed("First Name");
			assertAsteriskIsDisplayed("Last Name");
			assertAsteriskIsDisplayed("Display Name");
			assertAsteriskIsDisplayed("Email");
			ExtentReport.logPass("PASS", "test03AddUserDetails");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddUserDetails", driver, e);
			fail(e.getMessage());
		}
	}

//	@Test
	public void test04SelectUserRoleThenVerifyMenuItems() throws Throwable {
		try {
			doClick(systemMap.getUserRoleSelect());
			waitForElementPresence("//span[contains(@id,'dynamicwindow')][text()='Roles']");
			doClick(systemMap.getUserRoleSelectAllLeftButton());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserRoleSelectOptions(), "ASEC2310 CustomRole9");
			doClick(systemMap.getAssignedUsersSelectButton());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserRoleSelectOptions(), "ASESC2310 CustomRole2");
			doClick(systemMap.getAssignedUsersSelectButton());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserRoleSelectOptions(), "ASESC2310 CustomRole3");
			doClick(systemMap.getAssignedUsersSelectButton());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserRoleSelectOptions(), "ASESC2310 CustomRole4");
			doClick(systemMap.getAssignedUsersSelectButton());
			doClick(systemMap.getAssignedUsersRemoveButton());
			doClick(systemMap.getUserRoleSelectAllLeftButton());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserRoleSelectOptions(), "Contract Analyst");
			doClick(systemMap.getAssignedUsersSelectButton());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserRoleSelectOptions(), "Cost Analyst");
			doClick(systemMap.getAssignedUsersSelectButton());
			doClick(contractMap.getApplySelections());
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Costing']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Contracting']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Report Publication']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='ICD9/ICD10 GEMs Inquiry']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='ICD9/ICD10 GEMs Analysis']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Costing']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Costing Models']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Costing Data Maintenance']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Contracting']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Contract Models']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Contracting Data Maintenance']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Contractual Allowance Export']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Data Maintenance']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Maintain Data']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Utilities']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='System Maintenance']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Terminal Server Sessions']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Terminal Server Desktop']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Status']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Calculation Status']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Import/Export Status']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Utility Status']");
			ExtentReport.logPass("PASS", "test04SelectUserRoleThenVerifyMenuItems");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04SelectUserRoleThenVerifyMenuItems", driver, e);
			fail(e.getMessage());
		}
	}

//	@Test
	public void test05SelectEntity() throws Throwable {
		try {
			if (systemMap.getUserEntitySelect().getAttribute("class").contains("disabled")) {
//				Omkar 18/5/2023 : xpath changes for 11.2
//				doClick("//input[contains(@id,'checkboxfield')]");
				doClick("//input[contains(@id,'checkbox')][@type='checkbox']");
				doClick(systemMap.getUserEntitySelect());
			} else {
				doClick(systemMap.getUserEntitySelect());
			}
			doClick(systemMap.getUserSelectButtonInPopUp());
			doClick(contractMap.getRemoveItem());
			doClick(systemMap.getUserSelectButtonInPopUp());
			doClick(systemMap.getUserRoleSelectAllLeftButton());
			ContractModelsHelper.selectMultipleColumnsToDisplayUser(entities);
			doClick(contractMap.getApplySelections());
			assertTextIsDisplayed("0000 PRIVATE PAY");
			assertTextIsDisplayed("0001 PRIVATE PAY PENDING");
//			assertTextIsDisplayed("0008 PAYMENT PLAN");
			ExtentReport.logPass("PASS", "test05SelectEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05SelectEntity", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06SelectMasterDepartments() throws Throwable {
		try {
			Actions act=new Actions(driver);
			//Shilpa updated code for 11.2
			act.moveToElement(driver.findElement(By.xpath("(//div[text()='" + dept + "']//following::input[contains(@id,'combo')]//parent::div)[1]"))).click().clickAndHold().pause(1000).build().perform();
//			doClick("(//div[text()='" + dept + "']//following::table[2]//td[2]//div)[1]");
//			doClick("(//div[text()='" + dept + "']//following::input[contains(@id,'combo')])[1]");
			assertElementIsDisplayedWithXpath("(//div[text()='" + dept + "']//following::li[text()='All'])");
			assertElementIsDisplayedWithXpath("(//div[text()='" + dept + "']//following::li[text()='None'])");
			assertElementIsDisplayedWithXpath("(//div[text()='" + dept + "']//following::li[text()='Selected'])");
			//Shilpa updated code for 11.2
			act.moveToElement(driver.findElement(By.xpath("//li[text()='Selected']"))).clickAndHold().pause(1000).click().build().perform();
//			doClick("//li[text()='Selected']");
//			doDropdownSelectUsingOptionTextOnly(systemMap.getUserMasterSelectDropdownButtonOptions(), "Selected");
			doClick("//div[text()='" + dept + "']//following::div[7]");
			doClick(systemMap.getUserSelectButtonInPopUp());
			doClick(systemMap.getUserRoleSelectAllLeftButton());
			doClick(systemMap.getUserSelectButtonInPopUp());
			doClick(contractMap.getRemoveItem());
			doClick(systemMap.getUserMasterSelectAllCheckbox());
			doClick(systemMap.getUserMasterSelectAllCheckbox());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserMasterSelectDropdownButtonOptions(),
					"120 EMERGENCY DEPARTMENT");
			doClick(systemMap.getUserSelectButtonInPopUp());
			doDropdownSelectUsingOptionTextOnly(systemMap.getUserMasterSelectDropdownButtonOptions(),
					"130 EMERGENCY ROOM PHYSICIANS");
			doClick(systemMap.getUserSelectButtonInPopUp());
			doClick(contractMap.getApplySelections());
			assertElementText(driver.findElement(By.xpath("(//div[text()='"+dept+"']//following::div[4]//following::div[contains(@class,'x-grid-cell-inner ')])[1]")),
					"2 Selected", printout);
			driverDelay(500);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			ExtentReport.logPass("PASS", "test06SelectMasterDepartments");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06SelectMasterDepartments", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07VerifyUpdatedDetailsOfUser() throws Throwable {
		try {
			test01OpenUserID();
			assertThatAttributeValue(systemMap.getUserFirstName(), firstName, printout);
			assertThatAttributeValue(systemMap.getUserLastName(), lastName, printout);
			assertThatAttributeValue(systemMap.getUserDisplayName(), displayName, printout);
			assertThatAttributeValue(systemMap.getUserEmailName(), email, printout);
			assertThatAttributeValue(systemMap.getUserJobFunction(), jobFunction, printout);
			assertThatAttributeValue(systemMap.getUserNewPassword(), newPassword, printout);
			assertThatAttributeValue(systemMap.getUserConfirmNewPassword(), confirmNewPassword, printout);
			assertTextIsDisplayed("Cost Analyst");
			assertTextIsDisplayed("Contract Analyst");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Report Publication']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='ICD9/ICD10 GEMs Inquiry']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='ICD9/ICD10 GEMs Analysis']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Costing']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Costing Models']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Costing Data Maintenance']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Contracting']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Contract Models']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Contracting Data Maintenance']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Contractual Allowance Export']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Data Maintenance']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Maintain Data']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Utilities']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='System Maintenance']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Terminal Server Sessions']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Terminal Server Desktop']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Status']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Calculation Status']");
			assertElementIsDisplayedWithXpath(
					"//label[text()='Menu Items']//following::*[text()='Import/Export Status']");
			assertElementIsDisplayedWithXpath("//label[text()='Menu Items']//following::*[text()='Utility Status']");
			assertTextIsDisplayed("0000 PRIVATE PAY");
			assertTextIsDisplayed("0001 PRIVATE PAY PENDING");
			assertTextIsDisplayed("0008 PAYMENT PLAN");
			assertElementText(driver.findElement(By.xpath("//div[text()='" + dept + "']//following::div[4]")),
					"2 Selected", printout);
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClick(ContractingMap.getNewRiskLimiterPopUpCancelClose());
			ExtentReport.logPass("PASS", "test07VerifyUpdatedDetailsOfUser");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07VerifyUpdatedDetailsOfUser", driver, e);
			fail(e.getMessage());
		}

	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
