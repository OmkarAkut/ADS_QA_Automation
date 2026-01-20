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
public class GLAccountTypes extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLAccountTypes = "GL Account Types";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] glAccountTypeFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String updatedGlName="Updated"+name;
	static String[] updatedGlAccountTypeFilter= {"Name","Is","Equal To",updatedGlName};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLAccountTypes", "webdriver.scripts.datamaintenance.az",
				"GLAccountTypes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLAccountTypes);
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
			keyInInputByName("code", code, "GL Account Type");
			keyInInputByName("description", name, "GL Account Type");
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glAccountTypeFilter);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditGLAccountType() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("description", updatedGlName, "GL Account Type");
			clickCheckboxByName("accountCategory");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglAccountCategoryDrp(), "Non-Operating Revenue");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClickButtons("Reminder", "OK");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(updatedGlAccountTypeFilter);
			assertTextIsDisplayed(updatedGlName);
			assertTextIsDisplayed("Non-Operating Revenue");
			ExtentReport.logPass("PASS", "test02ValidateEditGLAccountType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditGLAccountType", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteGLAccountType() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteGLAccountType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteGLAccountType", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
