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
public class GLAccountMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLAccountMaster = "GL Account Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] glAccountMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String updatedName;
	static String glCode="GL"+code;
	static String glname="GL"+name;
	static String updatedGlName="Updated"+name;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLAccountMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"GLAccountMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLAccountMaster);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(),null,null,null,"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glAccountMasterFilter);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditButtonAddGLAccountCode() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("code", glCode, "GL Account Code");
			keyInInputByName("name", glname, "GL Account Code");
			keyInInputByName("columnLabel", "ShortName", "GL Account Code");
			clickCheckboxByName("accountType");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglAccountTypeDrp(), "IR Inpatient Revenue");
			assertTextIsDisplayed("Operating Revenue");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(glCode);
			assertTextIsDisplayed(glname);
			assertTextIsDisplayed("IR  Inpatient Revenue");
			assertTextIsDisplayed("Operating Revenue");
			ExtentReport.logPass("PASS", "test02ValidateEditButtonAddGLAccountCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditButtonAddGLAccountCode", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditGLAccountCode() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("name", updatedGlName, "GL Account Code");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(updatedGlName);
			ExtentReport.logPass("PASS", "test03EditGLAccountCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditGLAccountCode", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteGLAccountCode() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteGLAccountCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteGLAccountCode", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
