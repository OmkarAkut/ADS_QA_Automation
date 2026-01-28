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
public class RevenueCodeFeeScheduleMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozRevenueCodeFeeScheduleMasters = "Revenue Code Fee Schedule Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String masterClassificationScheme="UB92 Marina Revenue Master";
	static String[] aTozRevenueCodeFeeScheduleMastersFilter= {"Name","Is","Equal To",name};
	static String sellerOfServices="0000  PRIVATE PAY";
	static String updatedName;
	static String azName="Revenue Code Fee Schedule Master";
	static String templateName;
	static String updatedTemplateName;
	static String newTemplateSchedule;
	static String newSchedule;
	static String feeScheduleEntries[]= {code,"12"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RevenueCodeFeeScheduleMasters", "webdriver.scripts.datamaintenance.az",
				"RevenueCodeFeeScheduleMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozRevenueCodeFeeScheduleMasters);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01RevenueCodeValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"Revenue Code Fee Schedule Master");
			keyInInputByName("name", name,"Revenue Code Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozRevenueCodeFeeScheduleMastersFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01RevenueCodeValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01RevenueCodeValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02RevenueCodeValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"Revenue Code Fee Schedule Master");
			keyInInputByName("name", name,"Revenue Code Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozRevenueCodeFeeScheduleMastersFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02RevenueCodeValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02RevenueCodeValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03RevenueCodeValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"Revenue Code Fee Schedule Master");
			keyInInputByName("name", name,"Revenue Code Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozRevenueCodeFeeScheduleMastersFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03RevenueCodeValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03RevenueCodeValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04RevenueCodeAddSaveCreateNewTemplates() throws Throwable {
		try {
			doClick("//div[text()='Templates']");
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			templateName="RTemplate";
			keyInInputByName("name", templateName,"Revenue Code Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrevenueFeeScheduleMasterScheme(), masterClassificationScheme);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(templateName);
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazEditBtn());
			driverDelay();
			//ADS-24670 : Uncomment below lines after this issue is resolved 
//			doClick("//div[text()='Templates']");
//			doClickButtons("Templates", "Delete");
//			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
//			doClick(DataMaintenanceMap.getwarningDeleteBtn());
//			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04RevenueCodeAddSaveCreateNewTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04RevenueCodeAddSaveCreateNewTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-24670 : Uncomment below test case after this issue is resolved 
//	@Test
	public void test05RevenueCodeSaveCloseTemplates() throws Throwable {
		try {
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrevenueFeeScheduleMasterScheme(), masterClassificationScheme);
			
			templateName="RevenuScheduleTemplate";
			addDetailsInnerPages(null, templateName, "Save","code","name");
			doClickButtons(templateName, "Cancel & Close");
			doClickButtons("Templates", "Edit");
			driverDelay();
			updatedTemplateName="UpdatedRevenuCodeScheduleTemplate";
			addDetailsInnerPages(null, updatedTemplateName, "Save & Close","code","name");
			assertTextIsDisplayed(updatedTemplateName);
			doClickButtons("Templates", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05RevenueCodeSaveCloseTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05RevenueCodeSaveCloseTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06RevenueCodeAddNewTemplateUnderSchedules() throws Throwable {
		try {
			doClick("//div[text()='Schedules']");
			doClickButtons("Schedules", "New");
			waitForFormDialog("New Schedule");
			ExtentReport.logPass("PASS", "test06RevenueCodeAddNewTemplateUnderSchedules");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06RevenueCodeAddNewTemplateUnderSchedules", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07RevenueCodeSaveCloseTemplatesUnderSchedule() throws Throwable {
		try {
			doClickButtons("Revenue Code Fee Schedule", "New");
			waitForFormDialog("New Template");
			newTemplateSchedule="RevenueCodeSchedule";
			keyInInputByName("name", newTemplateSchedule,"Revenue Code Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrevenueFeeScheduleMasterScheme(), masterClassificationScheme);
			doClickButtons("New Template", "Save & Create New");
			doClickButtons("New Template", "Cancel & Close");
			doClickButtons("Warning", "Cancel & Close");
			doClickButtons("Revenue Code Fee Schedule", "Cancel & Close");
			
			ExtentReport.logPass("PASS", "test07RevenueCodeSaveCloseTemplatesUnderSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07RevenueCodeSaveCloseTemplatesUnderSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08RevenueCodeAddNewSchedule() throws Throwable {
		try {
			doClickButtons("Schedules", "New");
			waitForFormDialog("New Schedule");
			doClick(DataMaintenanceMap.gettemplateDropdown());
			doClick("//li[text()='"+newTemplateSchedule+"']");
			String startDate=DataMaintenanceMap.geteffectiveperiodStartDate().getAttribute("value");
			String endDate=DataMaintenanceMap.geteffectiveperiodEndDate().getAttribute("value");
			doClickButtons("New Schedule", "Save & Create New");
			doClickButtons("New Schedule", "Cancel & Close");
			assertElementIsDisplayedWithXpath("//div[text()='"+startDate+"']");
			assertElementIsDisplayedWithXpath("//div[text()='"+endDate+"']");
			assertElementIsDisplayedWithXpath("//div[text()='Schedules']//following::div[text()='"+newTemplateSchedule+"']");
			ExtentReport.logPass("PASS", "test08RevenueCodeAddNewSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08RevenueCodeAddNewSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09RevenueCodeEditScheduleAddFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			addEntries("Fee Schedule Entries", "New", feeScheduleEntries);
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveBtn());
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test09RevenueCodeEditScheduleAddFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09RevenueCodeEditScheduleAddFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test10RevenueCodeDeleteFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsDisplayedWithXpath("(//*[text()='There is no data available to display.'])[2]");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test10RevenueCodeDeleteFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10RevenueCodeDeleteFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test11RevenueCodeDeleteSchedule() throws Throwable {
		try {
			doClick("//div[text()='"+newTemplateSchedule+"']");
			doClickButtons("Schedules", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsNotDisplayed("//div[text()='"+newTemplateSchedule+"']");
//			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test11RevenueCodeDeleteSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11RevenueCodeDeleteSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12RevenueFeeScheduleMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsNotDisplayed("//div[text()='"+name+"']");
//			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test12RevenueFeeScheduleMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12RevenueFeeScheduleMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
