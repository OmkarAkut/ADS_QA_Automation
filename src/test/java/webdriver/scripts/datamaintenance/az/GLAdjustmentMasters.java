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
public class GLAdjustmentMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLAdjustmentMaster = "GL Adjustment Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String sourceGLDataScenario="* MHFY05 Reclass TB";
	static String destGLDataScenario="* GL IMP TXT TEST";
	static String glAccountMaster="Marina Master";
	static String[] glAdjustmentMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String validateentity="0000  PRIVATE PAY";
	static String dept="027070 PATHOLOGY LAB";
	static String glAcct="001  DR  Ded from Rev";
	static String startMonth="Apr 2004";
	static String endMonth="May 2004";
	static String comments="GL Adjustment Test";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLAdjustmentMasters", "webdriver.scripts.datamaintenance.az",
				"GLAdjustmentMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLAdjustmentMaster);
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
			keyInInputByName("name", name, "GL Adjustment Master");
			clickCheckboxByName("sourceGLDataScenarioId");
			selectDropdown(sourceGLDataScenario, "GL Adjustment Master");
			clickCheckboxByName("destGLDataScenarioId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='GL Adjustment Master']//following::ul/li[text()='"+destGLDataScenario+"']/..)[2]")), destGLDataScenario);
			clickCheckboxByName("accountMasterCode");
			selectDropdown(glAccountMaster, "GL Adjustment Master");
			keyInInputByName("comments", "Comments", "GL Adjustment Master");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glAdjustmentMasterFilter);
//			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
//			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditGLAdjustmentMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons("GL Adjustments", "New");
			clickCheckboxByName("entity");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='General Ledger Adjustment']//following::ul/li[text()='0000  PRIVATE PAY']/..")), entity);
			doClick(DataMaintenanceMap.getglGlMasterDeptSelectBtn());
			selectFormItem(dept, "General Ledger Adjustment");
			doClick(DataMaintenanceMap.getglMasterGlAcctSelectBtn());
			selectFormItem(glAcct, "General Ledger Adjustment");
			clickCheckboxByName("startDate");
			selectDropdown(startMonth,  "General Ledger Adjustment");
			clickCheckboxByName("endDate");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='General Ledger Adjustment']//following::ul/li[text()='"+endMonth+"']/..)[2]")), endMonth);
			selectDropdown(endMonth,  "General Ledger Adjustment");
			keyInInputByName("amount", "100", "General Ledger Adjustment");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(validateentity);
			assertTextIsDisplayed(dept);
			assertTextIsDisplayed(glAcct);
			assertElementIsDisplayedWithXpath("//div[text()='"+validateentity+"']//following::div[text()='"+startMonth+"']");
			assertElementIsDisplayedWithXpath("//div[text()='"+validateentity+"']//following::div[text()='"+endMonth+"']");
			ExtentReport.logPass("PASS", "test02EditGLAdjustmentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditGLAdjustmentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditGLAdjustment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputText(comments, DataMaintenanceMap.getglMasterGlAcctCommentSec());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(validateentity);
			assertTextIsDisplayed(dept);
			assertTextIsDisplayed(glAcct);
			assertElementIsDisplayedWithXpath("//div[text()='"+validateentity+"']//following::div[text()='"+startMonth+"']");
			assertElementIsDisplayedWithXpath("//div[text()='"+validateentity+"']//following::div[text()='"+endMonth+"']");
			assertTextIsDisplayed(comments);
			ExtentReport.logPass("PASS", "test02EditGLAdjustmentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditGLAdjustmentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteGLAdjustment() throws Throwable {
		try {
			doClickButtons("GL Adjustments", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04DeleteGLAdjustment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteGLAdjustment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteGLAdjustmentMaster() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteGLAdjustmentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteGLAdjustmentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
