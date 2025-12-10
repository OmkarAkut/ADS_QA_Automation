package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncounterType extends AzHelper {

	static DataMaintenanceMap dmMap;
	final static String aTozEncounterTypes = "Encounter Types";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "Encounter Type";
	static String updatedName="Updated"+name;
	static String[] filterAfterEdit= {"Name","Is","Equal To",updatedName};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EncounterTypes", "webdriver.scripts.datamaintenance.maintaindata",
				"EncounterTypes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEncounterTypes);
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
			keyInInputByName("name", name, azName);
			keyInInputByName("code", code, azName);
			keyInInputByName("columnLabel", "TestShortName", azName);
			clickCheckboxByName("type");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getlosType(), "3");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed("TestShortName");
			assertElementIsDisplayedWithXpath("//div[text()='"+name+"']//following::div[2][text()='3']");
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidatEditButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", updatedName, azName);
			doClickButtons(azName, "Save");
			doClickButtons(azName, "Save & Close");
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			driverDelay();
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterAfterEdit);
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test02ValidatEditButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidatEditButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteEncounterType() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03DeleteEncounterType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteEncounterType", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}	
}
