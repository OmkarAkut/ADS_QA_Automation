package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgeInYearsGroups extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozAgeInYearsGroups = "Age In Years Groups";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "Age In Years Groups";
	static ContractingMap contract;
	String[] columns = { "1", "2"};
	String[] columnsEdit = { "3"};
	String[] validateAgesBeforeEdit={ "1", "2"};
	String[] validateAgesAfterEdit= { "1","2","3"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AgeInYearsGroups", "webdriver.scripts.datamaintenance.az",
				"AgeInYearsGroups");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contract=BuildMap.getInstance(driver,ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozAgeInYearsGroups);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateSaveAndCreateNewGroup() throws Throwable {
		try {
			addNewScenario(validateAgesBeforeEdit,name,aTozAgeInYearsGroups,columns);
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveAndCreateNewGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveAndCreateNewGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveCloseGroup() throws Throwable {
		try {
			addNewScenario(validateAgesAfterEdit,name,aTozAgeInYearsGroups,columns);
			doClick(DataMaintenanceMap.getsaveButton());
			doClick(DataMaintenanceMap.getsaveCloseButton());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test02ValidateSaveCloseGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveCloseGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditSaveGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons(aTozAgeInYearsGroups, "Select");
			waitForElementToBeVisible(DataMaintenanceMap.getageYearsGroupSelectWindow());
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsEdit);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			assertElements(validateAgesAfterEdit,azName);
			doClick(DataMaintenanceMap.getsaveCloseButton());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03ValidateEditSaveGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditSaveGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
