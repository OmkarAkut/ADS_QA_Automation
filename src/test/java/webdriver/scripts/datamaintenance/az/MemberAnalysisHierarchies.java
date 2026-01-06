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
public class MemberAnalysisHierarchies extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozMemberAnalysisHierarchies = "Member Analysis Hierarchies";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String rollUpCode="RC"+code;
	static String rollUpName="RN"+name;
	static String rollUpdatedName="Updated"+name;
	static String[] filter= {"Name","Is","Equal To",name};
	static String rollUpLabel="Label"+name;
	String[] entities = { "0003 PRIVATE PAY INSUFF "};
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("MemberAnalysisHierarchies", "webdriver.scripts.datamaintenance.maintaindata",
				"MemberAnalysisHierarchies");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozMemberAnalysisHierarchies);
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
			keyInInputByName("name", name, "Member Analysis Hierarchy");
			keyInInputByName("code", code, "Member Analysis Hierarchy");
			keyInInputByName("detailrollupLabel", rollUpLabel, "Member Analysis Hierarchy");
			doClickButtons("Member Analysis Hierarchy", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(entities);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			clickCheckboxByName("sharedLog");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed("Detail Only");
			assertTextIsDisplayed(rollUpLabel);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("code", rollUpCode, "Detail Rollup");
			keyInInputByName("name", rollUpName, "Detail Rollup");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(rollUpCode);
			assertTextIsDisplayed(rollUpName);
			
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditDetailRollUp() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("name", rollUpdatedName, "Detail Rollup");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			ExtentReport.logPass("PASS", "test03EditDetailRollUp");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditDetailRollUp", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteDetailOnly() throws Throwable {
		try {
			doClickButtons(rollUpLabel, "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04AddActivityStatistics");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddActivityStatistics", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteMemberDetailOnlyHierarchy() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteMemberDetailOnlyHierarchy");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteMemberDetailOnlyHierarchy", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
