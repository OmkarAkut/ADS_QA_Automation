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
public class OverheadAccountVariabilityMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozOverheadAccountVariabilityMasters = "Overhead Account Variability Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String deptHierarchy="ASESC-1832 Hospital Hierarchy";
	static String glAcctHierarchy="ASESC-1832 Hospital Account Hierarchy";
	static String[] overheadAcctVariabilityMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String varPercent="10.13";
	static String upVarPercent="10.16";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("OverheadAccountVariabilityMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"OverheadAccountVariabilityMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozOverheadAccountVariabilityMasters);
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
			keyInInputByName("name", name, "Overhead Account Variability Master");
			clickCheckboxByName("deptHierarchyObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadDetMasterDrp(), deptHierarchy);
			clickCheckboxByName("accountHierarchyId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadGlAcctDrp(), glAcctHierarchy);
			doClickButtons("Overhead Account Variability Master", "Select");
			selectFormItem(entity, "services");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(overheadAcctVariabilityMasterFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(deptHierarchy);
			assertTextIsDisplayed(glAcctHierarchy);
//			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
//			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditOverheadMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("Warning", "Continue");
			driverDelay(200);
			clickCheckboxByName("entityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadOvhassignDrp(), entity);
			doClick(DataMaintenanceMap.getoverheadOvhassignDeptGrp());
			selectFormItem("*ALLDEPTS ALLDEPTS", "");
			doClick(DataMaintenanceMap.getoverheadOvhassignGlGrp());
			selectFormItem("*BEN  Employee Benefits", "");
			keyInInputByName("variablePercent", varPercent, "Overhead Account Variability Assignment");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("0000  PRIVATE PAY");
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("*Employee Benefits");
			assertTextIsDisplayed(varPercent);
			ExtentReport.logPass("PASS", "test02ValidateEditOverheadMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditOverheadMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditOverheadAccountVarAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("variablePercent", upVarPercent, "Overhead Account Variability Assignment");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(upVarPercent);
//			
			ExtentReport.logPass("PASS", "test03ValidateEditOverheadAccountVarAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditOverheadAccountVarAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateDeleteOverheadAccountVarAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateDeleteOverheadAccountVarAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateDeleteOverheadAccountVarAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteOverheadVariabilityMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteOverheadVariabilityMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteOverheadVariabilityMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
