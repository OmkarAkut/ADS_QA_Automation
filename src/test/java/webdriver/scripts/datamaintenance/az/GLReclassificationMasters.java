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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GLReclassificationMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozActivityStatisticMaster = "GL Reclassification Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String deptHierarchy="Marina Department Hierarchy";
	static String glReclassificationMaster="Marina Account Hierarchy";
	static String sourceGLDataScenario="IP FY06";
	static String destGLDataScenario="IP FY06 Reclass";
	static String sourceStatisticDataScenario="IP FY06";
	static String glStatisticMaster="General Ledger Statistics";
	static String activityMaster="Activity Statistics";
	static String adHocMaster="Ad Hoc Statistics";
	static String[] glReclassificationMastersFilter= {"Name","Is","Equal To",name};
	static String[] entity={"0000 PRIVATE PAY"};
	static String updatedName;
	static String calcSeq="3";
	static String calcGroup="Group"+name;
	static String reclassType="Statistic";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLReclassificationMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"GLReclassificationMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozActivityStatisticMaster);
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
			keyInInputByName("name", name, "GL Reclassification Master");
			clickCheckboxByName("departmentHierarchy");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='GL Reclassification Master']//following::ul/li[text()='"+deptHierarchy+"']/..)")), deptHierarchy);
			clickCheckboxByName("accountHierarchy");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='GL Reclassification Master']//following::ul/li[text()='"+glReclassificationMaster+"']/..)")), glReclassificationMaster);
			doClickButtons("GL Reclassification Master", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplayUser(entity);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			clickCheckboxByName("sourceGLDataScenario");
			selectDropdown(sourceGLDataScenario,"GL Reclassification Master");
			clickCheckboxByName("destinationGLDataScenario");
			driverDelay();
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='GL Reclassification Master']//following::ul/li[text()='"+destGLDataScenario+"']/..)[2]")), destGLDataScenario);
			clickCheckboxByName("costingActivityDataScenario");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='GL Reclassification Master']//following::ul/li[text()='"+sourceStatisticDataScenario+"']/..)[3]")), sourceStatisticDataScenario);
			clickCheckboxByName("glstatisticMaster");
			selectDropdown(glStatisticMaster,"GL Reclassification Master");
			clickCheckboxByName("activityStatisticMaster");
			selectDropdown(activityMaster,"GL Reclassification Master");
			clickCheckboxByName("adhocStatisticMaster");
			selectDropdown(adHocMaster,"GL Reclassification Master");
			keyInInputByName("commentField",name, "GL Reclassification Master");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glReclassificationMastersFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(sourceGLDataScenario);
			
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddGLReclassificationGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			driverDelay();
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("GL Reclassification Groups", "New");
			keyInInputByName("name", calcGroup, "GL Reclassification Group");
			keyInInputByName("sequenceField", calcSeq, "GL Reclassification Group");
			clickCheckboxByName("reclassMethod");
			selectDropdown(reclassType,"GL Reclassification Group");
			doClickButtons("GL Reclassification Group", "Save");
			doClickButtons("GL Reclassification Group", "Save & Close");
			assertTextIsDisplayed(calcGroup);
			assertTextIsDisplayed(calcSeq);
			assertTextIsDisplayed(reclassType);
			doClickButtons("GL Reclassification Groups", "Edit");
			ExtentReport.logPass("PASS", "test02AddGLReclassificationGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddGLReclassificationGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddGLReclassifications() throws Throwable {
		try {
			
			ExtentReport.logPass("PASS", "test03AddGLReclassifications");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddGLReclassifications", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
