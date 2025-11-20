package webdriver.scripts.datamaintenance.maintaindata;

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
public class APRDRGFeeScheduleMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozAPRDRGFeeScheduleMasters = "APR DRG Fee Schedule Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String masterClassificationScheme="2010APRDRG 2010 APR DRG";
	static String[] aTozAPCFeeScheduleMasterFilter= {"Name","Is","Equal To",name};
	static String sellerOfServices="0000  PRIVATE PAY";
	static String updatedName;
	static String azName="APR DRG Fee Schedule Master";
	static String templateName;
	static String updatedTemplateName;
	static String newTemplateSchedule;
	static String newSchedule;
	static String feeScheduleEntries[]= {code,"12"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("APRDRGFeeScheduleMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"APRDRGFeeScheduleMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozAPRDRGFeeScheduleMasters);
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
			keyInInputByName("code", code,"APR DRG Fee Schedule Master");
			keyInInputByName("name", name,"APR DRG Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozAPCFeeScheduleMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"APR DRG Fee Schedule Master");
			keyInInputByName("name", name,"APR DRG Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozAPCFeeScheduleMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"APR DRG Fee Schedule Master");
			keyInInputByName("name", name,"APR DRG Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozAPCFeeScheduleMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddSaveCreateNewTemplates() throws Throwable {
		try {
			doClick("//div[text()='Templates']");
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			keyInInputByName("name", name,"APR DRG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmasterAPRDRGClassificationScheme(), masterClassificationScheme);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			templateName="Template"+name;
			addDetailsInnerPages(null, templateName, "Save & Create New","code","name");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(templateName);
			doClickButtons("Templates", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04AddSaveCreateNewTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddSaveCreateNewTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05SaveCloseTemplates() throws Throwable {
		try {
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			keyInInputByName("name", name,"APR DRG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmasterAPRDRGClassificationScheme(), masterClassificationScheme);
			templateName="Template"+name;
			addDetailsInnerPages(null, templateName, "Save","code","name");
			doClickButtons(templateName, "Cancel & Close");
			doClickButtons("Templates", "Edit");
			updatedTemplateName="APRDRGTemplate"+name;
			addDetailsInnerPages(null, updatedTemplateName, "Save & Close","code","name");
			assertTextIsDisplayed(updatedTemplateName);
			ExtentReport.logPass("PASS", "test05SaveCloseTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05SaveCloseTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddNewTemplateUnderSchedules() throws Throwable {
		try {
			doClick("//div[text()='Schedules']");
			doClickButtons("Schedules", "New");
			waitForFormDialog("New Schedule");
			ExtentReport.logPass("PASS", "test06AddSaveCreateNewSchedules");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddSaveCreateNewSchedules", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07SaveCloseTemplatesUnderSchedule() throws Throwable {
		try {
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			newTemplateSchedule="APRDRGSchedule"+name;
			keyInInputByName("name", newTemplateSchedule,"APR DRG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmasterAPRDRGClassificationScheme(), masterClassificationScheme);
			doClickButtons("New Template", "Save & Create New");
			doClickButtons("New Template", "Cancel & Close");
			doClickButtons("New Schedule", "Cancel & Close");
			doClick("//div[text()='"+updatedTemplateName+"']");
			doClickButtons("Templates", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			doClick("//div[text()='Templates']/..");
			ExtentReport.logPass("PASS", "test05SaveCloseTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05SaveCloseTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08AddNewSchedule() throws Throwable {
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
			ExtentReport.logPass("PASS", "test08AddNewSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08AddNewSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09EditScheduleAddFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			addEntries("Fee Schedule Entries", "New", feeScheduleEntries);
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveBtn());
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test09EditScheduleAddFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09EditScheduleAddFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10DeleteFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test10DeleteFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10DeleteFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11DeleteSchedule() throws Throwable {
		try {
			doClick("//div[text()='"+newTemplateSchedule+"']");
			doClickButtons("Schedules", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test11DeleteAPDRGFeeScheduleMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11DeleteAPDRGFeeScheduleMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12APDRGFeeScheduleMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test12APDRGFeeScheduleMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12APDRGFeeScheduleMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
