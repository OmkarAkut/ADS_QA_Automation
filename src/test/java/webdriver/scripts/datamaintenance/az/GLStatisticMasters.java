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
public class GLStatisticMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLStatisticMasters = "GL Statistic Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String deptHierarchy="ASESC-1832 Hospital Hierarchy";
	static String glAcctHierarchy="ASESC-1832 Hospital Account Hierarchy";
	static String[] glStatsMasterFilter= {"Name","Is","Equal To",name};
	static String entity="<ALL>";
	static String updatedName;
	static String glStaticCode="GL"+code;
	static String glStaticName="GL"+name;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLStatisticMasters", "webdriver.scripts.datamaintenance.az",
				"GLStatisticMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLStatisticMasters);
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
			keyInInputByName("code", code, "GL Statistic Master");
			keyInInputByName("name", name, "GL Statistic Master");
			clickCheckboxByName("departmentHierarchy");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglstatisticDeptDrp(), deptHierarchy);
			clickCheckboxByName("glAccountHierarchy");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglstatisticDrp(), glAcctHierarchy);
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glStatsMasterFilter);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditGlStatisticMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("code", glStaticCode, "GL Statistic");
			keyInInputByName("name", glStaticName, "GL Statistic");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateEditGlStatisticMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditGlStatisticMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateAddMapping() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			driverDelay();
			assertElementIsDisplayedWithXpath("//div[text()='Mappings']//following::div[text()='"+glStaticCode+"']");
			assertElementIsDisplayedWithXpath("//div[text()='Mappings']//following::div[text()='"+glStaticName+"']");
			clickCheckboxByName("entityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglstatisticEntityDrp(), entity);
			doClick(DataMaintenanceMap.getglDeptGrp());
			selectFormItem("*ALLDEPTS ALLDEPTS", "");
			doClick(DataMaintenanceMap.getglStatisticGlGrp());
			selectFormItem("*BEN  Employee Benefits", "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(entity);
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("*Employee Benefits");
			ExtentReport.logPass("PASS", "test02ValidateEditGlStatisticMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditGlStatisticMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateEditMapping() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.getglDeptGrp());
			selectFormItem("*DOH DOH", "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("*DOH");
			ExtentReport.logPass("PASS", "test04ValidateEditMapping");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateEditMapping", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05ValidateDeleteMapping() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(glStaticCode);
			assertTextIsDisplayed(glStaticName);
			ExtentReport.logPass("PASS", "test05ValidateDeleteMapping");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateDeleteMapping", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06DeleteGLStatistics() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test08DeleteGLStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteGLStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteGLStatisticsMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteGLStatisticsMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteGLStatisticsMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
