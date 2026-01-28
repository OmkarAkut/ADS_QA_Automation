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
public class EAPGFeeScheduleMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEAPGFeeScheduleMasters = "EAPG Fee Schedule Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String masterClassificationScheme="ASESC2918EAPG V95 ASESC-2918 EAPG Master";
	static String[] aTozEAPGFeeScheduleMasterFilter= {"Name","Is","Equal To",name};
	static String sellerOfServices="0000  PRIVATE PAY";
	static String updatedName;
	static String azName="EAPG Fee Schedule Master";
	static String templateName;
	static String updatedTemplateName;
	static String newTemplateSchedule;
	static String newSchedule;
	static String feeScheduleEntries[]= {code,"12"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EAPGFeeScheduleMasters", "webdriver.scripts.datamaintenance.az",
				"EAPGFeeScheduleMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEAPGFeeScheduleMasters);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01EAPGValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"EAPG Fee Schedule Master");
			keyInInputByName("name", name,"EAPG Fee Schedule Master");
			clickButton("Select");
			driverDelay();
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozEAPGFeeScheduleMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01EAPGValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01EAPGValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EAPGValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"EAPG Fee Schedule Master");
			keyInInputByName("name", name,"EAPG Fee Schedule Master");
			clickButton("Select");
			driverDelay();
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozEAPGFeeScheduleMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02EAPGValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EAPGValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EAPGValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"EAPG Fee Schedule Master");
			keyInInputByName("name", name,"EAPG Fee Schedule Master");
			clickButton("Select");
			driverDelay();
			waitForFormDialog("Select Sellers of Services");
			
			selectFormItem(sellerOfServices,"services");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(aTozEAPGFeeScheduleMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			driverDelay();
			ExtentReport.logPass("PASS", "test03EAPGValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EAPGValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04EAPGAddSaveCreateNewTemplates() throws Throwable {
		try {
			doClick("//div[text()='Templates']");
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			templateName="EAPGTemplate";
			keyInInputByName("name", templateName,"EAPG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.geteapgFeeScheduleMasterScheme(), masterClassificationScheme);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(templateName);
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazEditBtn());
//			doClick("//div[text()='Templates']");
//			doClick("(//div[text()='"+templateName+"']//following::span[text()='Delete']//preceding::span[text()='Delete'])[2]");
			//ADS-24670 : Uncomment below lines after this issue is resolved 
//			doClickButtons("Templates", "Delete");
//			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
//			doClick(DataMaintenanceMap.getwarningDeleteBtn());
//			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");

			ExtentReport.logPass("PASS", "test04EAPGAddSaveCreateNewTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04EAPGAddSaveCreateNewTemplates", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
	public void test05EAPGSaveCloseTemplates() throws Throwable {
		try {
			doClickButtons("Templates", "New");
			waitForFormDialog("New Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.geteapgFeeScheduleMasterScheme(), masterClassificationScheme);
			
			templateName="EAPGFeeTemplate";
			addDetailsInnerPages(null, templateName, "Save","code","name");
			doClickButtons(templateName, "Cancel & Close");
			doClickButtons("Templates", "Edit");
			updatedTemplateName="UpdatedEAPGTemplate"+name;
			addDetailsInnerPages(null, updatedTemplateName, "Save & Close","code","name");
			assertTextIsDisplayed(updatedTemplateName);
//			doClick("//div[text()='"+updatedTemplateName+"']");
//			doClick("(//div[text()='"+updatedTemplateName+"']//following::span[text()='Delete'])");

			doClickButtons("Templates", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");

			ExtentReport.logPass("PASS", "test05EAPGSaveCloseTemplates");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EAPGSaveCloseTemplates", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06EAPGAddNewTemplateUnderSchedules() throws Throwable {
		try {
			doClick("//div[text()='Schedules']");
			doClickButtons("Schedules", "New");
			driverDelay();
			waitForFormDialog("New Schedule");
			ExtentReport.logPass("PASS", "test06EAPGAddNewTemplateUnderSchedules");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06EAPGAddNewTemplateUnderSchedules", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07EAPGSaveCloseTemplatesUnderSchedule() throws Throwable {
		try {
			doClickButtons("EAPG Fee Schedule", "New");
			waitForFormDialog("New Template");
			newTemplateSchedule="EAPGSchedule";
			keyInInputByName("name", newTemplateSchedule,"EAPG Fee Schedule Template");
			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.geteapgFeeScheduleMasterScheme(), masterClassificationScheme);
			doClickButtons("New Template", "Save & Create New");
			doClickButtons("New Template", "Cancel & Close");
			doClickButtons("Warning", "Cancel & Close");
			doClickButtons("EAPG Fee Schedule", "Cancel & Close");
			
//			doClickButtons("Templates", "New");
//			driverDelay();
//			waitForFormDialog("New Template");
//			newTemplateSchedule="EAPGTemplate"+name;
//			keyInInputByName("name", newTemplateSchedule,"EAPG Fee Schedule Template");
//			doClick(DataMaintenanceMap.getazMasterClassificationDrpDwn());
//			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.geteapgFeeScheduleMasterScheme(), masterClassificationScheme);
//			doClickButtons("New Template", "Save & Create New");
//			doClickButtons("New Template", "Cancel & Close");
//			doClickButtons("New Schedule", "Cancel & Close");
//			doClick("//div[text()='"+updatedTemplateName+"']");
//			doClickButtons("Templates", "Delete");
//			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
//			doClick(DataMaintenanceMap.getwarningDeleteBtn());
//			doClick("//div[text()='Templates']/..");
			ExtentReport.logPass("PASS", "test07EAPGSaveCloseTemplatesUnderSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07EAPGSaveCloseTemplatesUnderSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08EAPGAddNewSchedule() throws Throwable {
		try {
			doClickButtons("Schedules", "New");
			driverDelay();
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
			ExtentReport.logPass("PASS", "test08EAPGAddNewSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08EAPGAddNewSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09EAPGEditScheduleAddFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			driverDelay();
			addEntries("Fee Schedule Entries", "New", feeScheduleEntries);
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveBtn());
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test09EAPGEditScheduleAddFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09EAPGEditScheduleAddFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10EAPGDeleteFeeScheduleEntries() throws Throwable {
		try {
			doClickButtons("Schedules", "Edit");
			driverDelay();
			doClick(DataMaintenanceMap.getfeeScheduleEntriesDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			doClick(DataMaintenanceMap.getfeeScheduleEntriesSaveCloseBtn());
			ExtentReport.logPass("PASS", "test10EAPGDeleteFeeScheduleEntries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10EAPGDeleteFeeScheduleEntries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11EAPGDeleteSchedule() throws Throwable {
		try {
			doClick("//div[text()='"+newTemplateSchedule+"']");
			doClickButtons("Schedules", "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			ExtentReport.logPass("PASS", "test11EAPGDeleteSchedule");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11EAPGDeleteSchedule", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12EAPGFeeScheduleMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertMultipleElementsForSameText(DataMaintenanceMap.getassertTextForNoElement(), "There is no data available to display.");
			ExtentReport.logPass("PASS", "test12EAPGFeeScheduleMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12EAPGFeeScheduleMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
