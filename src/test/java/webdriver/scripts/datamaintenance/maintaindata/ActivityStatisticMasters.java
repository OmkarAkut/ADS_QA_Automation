package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

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
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AddServiceToEncounterServiceClassificationScheme", "webdriver.scripts.datamaintenance.maintaindata",
				"AddServiceToEncounterServiceClassificationScheme");
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
	
//	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			selectMaintainDataAtoZ(aTozActivityStatisticMaster);
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			deleteScenario();
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			deleteScenario();
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,"Department Hierarchy",DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),deptHierarchy,DataMaintenanceMap.getactivityStatsMasterDropdown(),DataMaintenanceMap.getactivityStatsMasterDropdownList(),"Save & Close");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityStatsMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			addDetailsInnerPages(code, name, "Save & Create New");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditActivityStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			String updatedName="Updated"+name;
			addDetailsInnerPages(null, updatedName, "Save");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05AddNewMappings() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
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
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
}
