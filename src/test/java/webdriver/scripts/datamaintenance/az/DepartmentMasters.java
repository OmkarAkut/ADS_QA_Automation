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
public class DepartmentMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozDepartmentMaster = "Department Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] deptMasterFilter= {"Name","Is","Equal To",name};
	static String deptCode;
	static String deptName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("DepartmentMasters", "webdriver.scripts.datamaintenance.az",
				"DepartmentMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozDepartmentMaster);
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
					DataMaintenanceMap.getaddName(),null,
					null,null,"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(deptMasterFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditAddDeptCodes() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			deptCode="CODE"+code;
			deptName="DEPT"+name;
			addDetailsInnerPages(deptCode, deptName, "Save & Create New","deptCode","description");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(deptCode);
			assertTextIsDisplayed(deptName);
			assertTextIsDisplayed("Patient Care Department");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("policy");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getdeptCodeDrp(), "Other Operating Department");
			keyInInputByName("shortName", "Short Name", "Department Code");
			doClickButtons("Department Code","User Defined Fields");
			keyInInputByName("textField1", "textField1", "Department Code");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(deptCode);
			assertTextIsDisplayed(deptName);
			assertTextIsDisplayed("Other Operating Department");
			ExtentReport.logPass("PASS", "test02ValidateEditAddDeptCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditAddDeptCodes", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test04DeleteDeptCodes() throws Throwable {
		try {
			doClickButtons("Department Master", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04DeleteDeptCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteDeptCodes", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test05DeleteDepartmentMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteDepartmentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteDepartmentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
