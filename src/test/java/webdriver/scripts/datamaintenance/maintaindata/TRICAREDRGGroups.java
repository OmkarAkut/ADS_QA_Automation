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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TRICAREDRGGroups extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozTriCareCodeGroup= "TRICARE DRG Groups";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "TRICARE DRG Group";
	static ContractingMap contract;
	String[] columns = {"0001  HEART TRANSPLANT OR IMPLANT OF HEART ASSIST SYSTEM W MCC  (2016TRICARE)" ,"0001  HEART TRANSPLANT OR IMPLANT OF HEART ASSIST SYSTEM W/ MCC  (2021TRICARE)"};
	String[] columnsEdit = { "0001  HEART TRANSPLANT OR IMPLANT OF HEART ASSIST SYSTEM W MCC  (2020TRICARE)"};
	String[] validateAgesBeforeEdit= {"2016TRICARE" ,"2021TRICARE"};
	String[] validateAgesAfterEdit=  {"2016TRICARE" ,"2021TRICARE","2020TRICARE"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PractitionerSpecialtyGroups", "webdriver.scripts.datamaintenance.maintaindata",
				"PractitionerSpecialtyGroups");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contract=BuildMap.getInstance(driver,ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozTriCareCodeGroup);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveAndCreateNewGroup() throws Throwable {
		try {
			addNewScenario(validateAgesBeforeEdit,name,aTozTriCareCodeGroup,columns);
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
			addNewScenario(validateAgesAfterEdit,name,aTozTriCareCodeGroup,columns);
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
			doClickButtons(azName, "Select");
			waitForFormDialog("Add TRICARE DRG Code Group Members");
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
