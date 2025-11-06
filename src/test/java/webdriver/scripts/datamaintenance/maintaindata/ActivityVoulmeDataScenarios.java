package webdriver.scripts.datamaintenance.maintaindata;

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
public class ActivityVoulmeDataScenarios extends AzHelper {
	static DataMaintenanceMap dmMap;
	final static String aTozActivityVolDataScenario = "Activity Volume Data Scenarios";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String fiscalYear = "**FY16";
	static String[] activityVolDataScenario = { "Name", "Is", "Equal To", name };
	static String updatedName;
	static String azName = "Activity Volume Data Scenario";
	static String volumeData = "50.6";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ActivityVoulmeDataScenarios", "webdriver.scripts.datamaintenance.maintaindata",
				"ActivityVoulmeDataScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozActivityVolDataScenario);
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
			addNewScenario(code, name, "Fiscal Year", DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), fiscalYear, DataMaintenanceMap.gettimePeriodName(),
					DataMaintenanceMap.getactivityVolDataScenarioDropdownList(), "Save & Create New");
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityVolDataScenario);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getmessageboxDeleteBtn());
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
			addNewScenario(code, name, "Fiscal Year", DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), fiscalYear, DataMaintenanceMap.gettimePeriodName(),
					DataMaintenanceMap.getactivityVolDataScenarioDropdownList(), "Save");
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityVolDataScenario);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getmessageboxDeleteBtn());
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
			addNewScenario(code, name, "Fiscal Year", DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), fiscalYear, DataMaintenanceMap.gettimePeriodName(),
					DataMaintenanceMap.getactivityVolDataScenarioDropdownList(), "Save & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityVolDataScenario);
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04ddActivityVolumeData() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazPageEditBtn());
			doClickButtons(azName, "Add");
			waitForFormDialog("Add Activity Volume Data");
			doClickSelectButton("Entity");
			waitForFormDialog("Add Entity");
			selectFormItem("0000 PRIVATE PAY");
			doClickSelectButton("Department");
			waitForFormDialog("Add Department");
			selectFormItem("0001 0001 Department");
			doClickSelectButton("Charge Code");
			waitForFormDialog("Add Charge Code");
			selectFormItem("0ALL  0ALL Charge associated with ALL Dept");
			doClickButtons("Add Activity Volume Data", "Add");
			assertTextIsDisplayed("0000  PRIVATE PAY");
			assertTextIsDisplayed("ALL  ALL");
			assertTextIsDisplayed("0ALL");
			assertTextIsDisplayed("0ALL Charge associated with ALL Dept");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05AddActivityVolumeTableData() throws Throwable {
		try {

			keyInInputWithActionClass(DataMaintenanceMap.getactivityVolData(), "10");
			doClickButtons("Activity Volume Data", "Undo");
			keyInInputWithActionClass(DataMaintenanceMap.getactivityVolData(), volumeData);
			doClickButtons("Activity Volume Data", "Save Data");
			assertTextIsDisplayed(volumeData);
			ExtentReport.logPass("PASS", "test04AddActivityVolumeTableData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddActivityVolumeTableData", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06DeleteActivityVolumeTableData() throws Throwable {
		try {
			doClickButtons("Activity Volume Data", "Delete");
			doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display");
			doClickButtons(azName, "Cancel & Close");
			ExtentReport.logPass("PASS", "test05DeleteActivityVolumeTableData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteActivityVolumeTableData", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07DeleteActivityVolumeScenario() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(activityVolDataScenario);
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test06DeleteActivityVolumeScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteActivityVolumeScenario", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
