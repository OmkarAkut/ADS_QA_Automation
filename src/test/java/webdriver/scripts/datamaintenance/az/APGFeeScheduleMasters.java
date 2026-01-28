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
public class APGFeeScheduleMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozAPGFeeScheduleMasters = "APG Fee Schedule Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String masterClassificationScheme="APG08 APG 2008";
	static String[] aTozAPCFeeScheduleMasterFilter= {"Name","Is","Equal To",name};
	static String sellerOfServices="0000  PRIVATE PAY";
	static String updatedName;
	static String azName="APG Fee Schedule Master";
	static String templateName;
	static String updatedTemplateName;
	static String newTemplateSchedule;
	static String newSchedule;
	static String feeScheduleEntries[]= {code,"12"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("APGFeeScheduleMasters", "webdriver.scripts.datamaintenance.az",
				"APGFeeScheduleMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozAPGFeeScheduleMasters);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01APGValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"APG Fee Schedule Master");
			keyInInputByName("name", name,"APG Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozAPCFeeScheduleMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01APGValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01APGValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02APGValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"APG Fee Schedule Master");
			keyInInputByName("name", name,"APG Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozAPCFeeScheduleMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02APGValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02APGValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03APGValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"APG Fee Schedule Master");
			keyInInputByName("name", name,"APG Fee Schedule Master");
			clickButton("Select");
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozAPCFeeScheduleMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03APGValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03APGValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04APGAddSaveCreateNewTemplates() throws Throwable {
		try {
			doClick("//div[text()='Templates']");
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			templateName="APGTemplate";
			keyInInputByName("name", templateName,"APG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmasterAPGClassificationScheme(), masterClassificationScheme);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(templateName);
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazEditBtn());
			driverDelay();
			doClick("//div[text()='Templates']");
			doClickButtons("Templates", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			ExtentReport.logPass("PASS", "test04APGAddSaveCreateNewTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04APGAddSaveCreateNewTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05APGSaveCloseTemplates() throws Throwable {
		try {
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmasterAPGClassificationScheme(), masterClassificationScheme);
			templateName="APGScheduleTemplate";
			addDetailsInnerPages(null, templateName, "Save","code","name");
			doClickButtons(templateName, "Cancel & Close");
			doClickButtons("Templates", "Edit");
			driverDelay();
			updatedTemplateName="UpdatedAPGScheduleTemplate";
			addDetailsInnerPages(null, updatedTemplateName, "Save & Close","code","name");
			assertTextIsDisplayed(updatedTemplateName);
			doClickButtons("Templates", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			ExtentReport.logPass("PASS", "test05APGSaveCloseTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05APGSaveCloseTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06APGAddNewTemplateUnderSchedules() throws Throwable {
		try {
			doClick("//div[text()='Schedules']");
			doClickButtons("Schedules", "New");
			waitForFormDialog("New Schedule");
			ExtentReport.logPass("PASS", "test06APGAddNewTemplateUnderSchedules");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06APGAddNewTemplateUnderSchedules", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07APGSaveCloseTemplatesUnderSchedule() throws Throwable {
		try {
			doClickButtons("APG Fee Schedule", "New");
			waitForFormDialog("New Template");
			newTemplateSchedule="TricareSchedule";
			keyInInputByName("name", newTemplateSchedule,"APG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmasterAPGClassificationScheme(), masterClassificationScheme);
			doClickButtons("New Template", "Save & Create New");
			doClickButtons("New Template", "Cancel & Close");
			doClickButtons("Warning", "Cancel & Close");
			doClickButtons("APG Fee Schedule", "Cancel & Close");
			ExtentReport.logPass("PASS", "test07APGSaveCloseTemplatesUnderSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07APGSaveCloseTemplatesUnderSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08APGAddNewSchedule() throws Throwable {
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
			ExtentReport.logPass("PASS", "test08APGAddNewSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08APGAddNewSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09APGEditScheduleAddFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			addEntries("Fee Schedule Entries", "New", feeScheduleEntries);
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveBtn());
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test09APGEditScheduleAddFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09APGEditScheduleAddFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10APGDeleteFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test10APGDeleteFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10APGDeleteFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11APGDeleteSchedule() throws Throwable {
		try {
			doClick("//div[text()='"+newTemplateSchedule+"']");
			doClickButtons("Schedules", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			ExtentReport.logPass("PASS", "test11APGDeleteSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11APGDeleteSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12APGFeeScheduleMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			ExtentReport.logPass("PASS", "test12APGFeeScheduleMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12APGFeeScheduleMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
