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
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12903 */
public class ValidateSaveAgeInDaysGroup extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageAgeGroup = "Age In Days Groups";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String ageInDaysGroups = "Group " + currentDateTime;
	static String[] filter = { "Name", "Is", "Equal To", ageInDaysGroups };

	String[] columns = { "1", "2"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateSaveAgeInDaysGroup", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateSaveAgeInDaysGroup");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageAgeGroup);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveAgeGroup_12903() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getageGroupNewBtn());
			keyInInputText(ageInDaysGroups, DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.getageGroupSelectBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getageGroupSelectWindow());
			ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getsaveButton());
			doClick(DataMaintenanceMap.getsaveCloseButton());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getageGroupFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(ageInDaysGroups);
			ExtentReport.logPass("PASS", "test01ValidateSaveAgeGroup_12903");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveAgeGroup_12903", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02DeleteSaveAgeGroup_12903() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getageGroupDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test01ValidateSaveAgeGroup_12903");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveAgeGroup_12903", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
