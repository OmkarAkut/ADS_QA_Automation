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
public class ActivityStatisticMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozActivityStatisticMaster = "Activity Statistic Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String deptHierarchy="ASESC-1832 Hospital Hierarchy";
	static String[] activityStatsMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String updatedName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ActivityStatisticMasters", "webdriver.scripts.datamaintenance.az",
				"ActivityStatisticMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozActivityStatisticMaster);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01_ActivityStatisticMasters_ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(),deptHierarchy,
					DataMaintenanceMap.getactivityStatsMasterDropdown(),
					DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01_ActivityStatisticMasters_ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01_ActivityStatisticMasters_ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02_ActivityStatisticMasters_ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02_ActivityStatisticMasters_ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02_ActivityStatisticMasters_ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03_ActivityStatisticMasters_ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save & Close");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03_ActivityStatisticMasters_ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03_ActivityStatisticMasters_ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04_ActivityStatisticMasters_AddActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			addDetailsInnerPages(code, name, "Save & Create New","code","name");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04_ActivityStatisticMasters_AddActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04_ActivityStatisticMasters_AddActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05_ActivityStatisticMasters_EditActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+name;
			addDetailsInnerPages(null, updatedName, "Save","code","name");
			addDetailsInnerPages(null, updatedName, "Save & Close","code","name");
			driverDelay();
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test05_ActivityStatisticMasters_EditActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05_ActivityStatisticMasters_EditActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06_ActivityStatisticMasters_AddNewMappings() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			assertElementValueAttribute(DataMaintenanceMap.getactivityStatsInputName(), updatedName, printout);
			doClick(DataMaintenanceMap.getmappingNewButton());
			doClick(DataMaintenanceMap.getcostCompAssignEntityBtn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getentityDropdownList(), entity);
			doClick(DataMaintenanceMap.getdeptGroupSelectBtn());
			waitForPresenceOfElementText("Add Department / Group");
			doClick("//div[text()='*ALLDEPTS ALLDEPTS']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getModifierSelectBtn());
			doClick("//div[text()='ALL 0ALL 0ALL Charge associated with ALL Dept ']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			keyInInputText("50", driver.findElement(By.name("convFactor")));
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			DataMaintenanceMap.getmappingCancelCloseButton().click();
			assertTextIsDisplayed("0000  PRIVATE PAY");
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("0ALL");
			assertTextIsDisplayed("0ALL  0ALL Charge associated with ALL Dept");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			assertTextIsDisplayed("50.0000");
			ExtentReport.logPass("PASS", "test06_ActivityStatisticMasters_AddNewMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06_ActivityStatisticMasters_AddNewMappings", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07_ActivityStatisticMasters_DeleteMappings() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getmappingDeleteButton());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test07_ActivityStatisticMasters_DeleteMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07_ActivityStatisticMasters_DeleteMappings", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08_ActivityStatisticMasters_DeleteActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test08_ActivityStatisticMasters_DeleteActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08_ActivityStatisticMasters_DeleteActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09_ActivityStatisticMasters_DeleteActivityStatisticsMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test09_ActivityStatisticMasters_DeleteActivityStatisticsMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09_ActivityStatisticMasters_DeleteActivityStatisticsMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
