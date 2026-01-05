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
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ICDProcedureCodeMasters extends AzHelper {
	static DataMaintenanceMap dmMap;
	final static String aTozICDProcedureCodeMasters = "ICD Procedure Code Masters";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String currentDateCodeMMHH = new SimpleDateFormat("MM.HH").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String procCode = "C"+currentDateCodeMMHH.replaceAll("\\W", "");
	static String procName = "PName" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "ICD Procedure Code Master";
	static String updatedName="Updated"+name;
	static String[] filterAfterEdit= {"Name","Is","Equal To",updatedName};
	static String procNameEdit = "UpdatedName" + currentDateTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ICD10DiagnosisCodes", "webdriver.scripts.datamaintenance.maintaindata",
				"ICD10DiagnosisCodes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozICDProcedureCodeMasters);
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
			addNewScenario(code, name, null, DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), null, null,
					null, "Save & Create New");
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditICDProcedureCodeMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("name", procName, "ICD Procedure Code");
			keyInInputByName("code", procCode, "ICD Procedure Code");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(procCode);
			assertTextIsDisplayed(procName);
//			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateEditICDProcedureCodeMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditICDProcedureCodeMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteICDProcedure() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveAndCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteICDProcedureCodeMaster() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03ValidateSaveAndCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05AddICD9Procedure() throws Throwable {
		try {
			test01ValidateNewButton();
			ExtentReport.logPass("PASS", "test03ValidateSaveAndCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06EditICD9Procedure() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButton("ICD9");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("name", procName, "ICD Procedure Code");
			keyInInputByName("code", procCode, "ICD Procedure Code");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(procCode);
			assertTextIsDisplayed(procName);
			ExtentReport.logPass("PASS", "test05EditICD9Procedure");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditICD9Procedure", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07EditICDProcedureCode() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("name", procNameEdit, "ICD Procedure Code");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test06EditICDProcedureCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06EditICDProcedureCode", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08DeleteICDProcedureCode() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test07DeleteICDProcedureCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteICDProcedureCode", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09DeleteICDProcedureMaster() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test08DeleteICDProcedureMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteICDProcedureMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}	
}
