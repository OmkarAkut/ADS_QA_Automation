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
import webdriver.helpers.CimHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDefinedProcedureCodeMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozUserDefinedProcCodeMaster = "User Defined Procedure Code Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] vendorMasterFilter= {"Name","Is","Equal To",name};
	static String updatedName;
	static String azName="User Defined Procedure Code Master";
	static String masterCodeName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UserDefinedProcedureCodeMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"UserDefinedProcedureCodeMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozUserDefinedProcCodeMaster);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),null,null,null,"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(vendorMasterFilter);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),null,null,null,"Save");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(vendorMasterFilter);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),null,null,null,"Save & Close");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(vendorMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddUserDefinedProcedureCodes() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			masterCodeName= "Code"+name;
			addDetailsInnerPages(code, masterCodeName, "Save & Create New","code","name");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(masterCodeName);
			ExtentReport.logPass("PASS", "test04AddUserDefinedProcedureCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddUserDefinedProcedureCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditUserDefinedProcedureCodes() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+name;
			addDetailsInnerPages(null, updatedName, "Save","code","name");
			addDetailsInnerPages(null, updatedName, "Save & Close","code","name");
			driverDelay();
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test05EditUserDefinedProcedureCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditUserDefinedProcedureCodes", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06DeleteUserDefinedProcedureCodes() throws Throwable {
		try {
			doClickButtons(azName, "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test06DeleteUserDefinedProcedureCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteUserDefinedProcedureCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteUserDefinedProcedureMaster() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07Deletetest06DeleteUserDefinedProcedureMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07Deletetest06DeleteUserDefinedProcedureMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
