package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployerGroups extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEmployerGroups = "Employer Groups";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] employerGroupFilter= {"Name","Is","Equal To",name};
	static String entity="0001 PRIVATE PAY PENDING ";
	static String updatedName;
	static String empCode="EMP"+code;
	static String empName="Emp"+name;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EmployerGroups", "webdriver.scripts.datamaintenance.az",
				"EmployerGroups");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEmployerGroups);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Employer Group");
			keyInInputByName("code", code, "Employer Group");
			clickCheckboxByName("groupTypeId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getemployerGroupTypeDrp(), "0002,WALMART WalMart Northside");
			clickCheckboxByName("financialCategoryId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfinancialCategoryDrp(), "2, F2 Financial Category 2");
			doClickButtons("Employer Group", "Select");
			driverDelay();
			selectFormItem("0001 PRIVATE PAY PENDING ", "");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(employerGroupFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			assertTextIsDisplayed("0002,WALMART  WalMart Northside");
			assertTextIsDisplayed("2,F2  Financial Category 2");
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddEmployerSubgroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			expandPanel("Employer Subgroup");
			driverDelay();
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("code", empCode, "New Employer Subgroup");
			keyInInputByName("name", empName, "New Employer Subgroup");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(empCode);
			assertTextIsDisplayed(empName);
			ExtentReport.logPass("PASS", "test02AddEmployerSubgroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddEmployerSubgroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditEmployerSubgroupAddHealthRepresentative() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			driverDelay();
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("Health Plan Representative History", "Select");
			driverDelay();
			selectFormItem("1,4SM1  HealthPlanRep1", "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03EditEmployerSubgroupAddHealthRepresentative");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditEmployerSubgroupAddHealthRepresentative", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04EditHealthRepresentative() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("1,4SM1  HealthPlanRep1");
			ExtentReport.logPass("PASS", "test04EditHealthRepresentative");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04EditHealthRepresentative", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteHealthRepresentative() throws Throwable {
		try {
			doClickButtons("User Defined Fields", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsDisplayedWithXpath("(//*[text()='There is no data available to display.'])[2]");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			collapsePanel("Employer Subgroup");
			ExtentReport.logPass("PASS", "test05DeleteHealthRepresentative");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteHealthRepresentative", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddCommissionBrokerHistories() throws Throwable {
		try {
			expandPanel("Commission Broker Histories");
			driverDelay();
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("Commission Broker History", "Select");
			driverDelay();
			selectFormItem("1,1SMCBRAG1  CommissionBrokerAgency1", "");
			doClick("(//div[text()='Commission Broker History']//following::div[contains(@class,'x-panel')]//span[text()='Select'])[2]");
			driverDelay();
			selectFormItem("1,1SMCMBR1  CommissionBroker1", "");
			doClick("(//div[text()='Commission Broker History']//following::div[contains(@class,'x-panel')]//span[text()='Select'])[3]");
			driverDelay();
			selectFormItem("RLS150,RLSCT1  Commission Type RLS 150", "");
			expandPanel("User Defined Fields");
			keyInInputByName("textField1", "ADS", "Commission Broker History");
			collapsePanel("User Defined Fields");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("1,1SMCBRAG1  CommissionBrokerAgency1");
			assertTextIsDisplayed("1,1SMCMBR1  CommissionBroker1");
			assertTextIsDisplayed("RLS150,RLSCT1  Commission Type RLS 150");
			ExtentReport.logPass("PASS", "test03AddCommissionBrokerHistories");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddCommissionBrokerHistories", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07EditCommissionBrokerHistories() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			expandPanel("User Defined Fields");
			keyInInputByName("textField1", "ADS1", "Commission Broker History");
			collapsePanel("User Defined Fields");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			ExtentReport.logPass("PASS", "test03AddCommissionBrokerHistories");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddCommissionBrokerHistories", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08DeleteCommissionBrokerHistories() throws Throwable {
		try {
			doClickButtons("Commission Broker Histories", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsDisplayedWithXpath("(//div[contains(@id,'commissionBrokerHistory')]//following::div[text()='There is no data available to display.'])[1]");
			collapsePanel("Commission Broker Histories");
			ExtentReport.logPass("PASS", "test08DeleteCommissionBrokerHistories");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteCommissionBrokerHistories", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09DeleteEmployerSubgroup() throws Throwable {
		try {
			expandPanel("Employer Subgroup");
			doClickButtons("Employer Subgroup", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsDisplayedWithXpath("(//div[contains(@id,'employerSubGroup')]//following::div[text()='There is no data available to display.'])[1]");
			ExtentReport.logPass("PASS", "test09DeleteEmployerSubgroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteEmployerSubgroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10DeleteAddEmployerGroups() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test10DeleteAddEmployerGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10DeleteAddEmployerGroups", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
