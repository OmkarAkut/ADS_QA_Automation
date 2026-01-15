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
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedicalServiceAssignments extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozMedicalServiceAssignments= "Medical Service Assignments";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String medService="Med"+name;
	static String[] filter= {"Name","Is","Equal To",name};
	String[] encounterTypes = { "1S1 Office"};
	static String orderIndex="3";
	static String updatedOrderIndex="6";
	static String[] chargeCode={"000001  Charge Code 000001  <None>  TBCHGMSTRADLPT  2010  NURSING ADMIN  DM"};
	static String[] calcFilter= {"Scenario Name","Is","Equal To",name};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("MedicalServiceAssignments", "webdriver.scripts.datamaintenance.maintaindata",
				"MedicalServiceAssignments");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozMedicalServiceAssignments);
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
			keyInInputByName("name", name, "Medical Service Assignment");
			doClick("(//div[text()='Medical Service Assignment']//following::span[text()='Select'])[1]");
			driverDelay();
			selectFormItem("1SM100 Pacific Hospital", "");
			doClick("(//div[text()='Medical Service Assignment']//following::span[text()='Select'])[2]");
			ContractModelsHelper.selectMultipleColumnsToDisplay(encounterTypes);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			clickCheckboxByName("shareLog");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02ValidateAddMedicalServices() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			driverDelay();
			doClickButton("Assign");
			doFilterCalculationPage(calcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			doClickButton("Clear Results");
			doFilterCalculationPage(calcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("name", medService, "Medical Service");
			doClickButtons("Medical Service", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(chargeCode);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			keyInInputByName("orderIndex", orderIndex, "Medical Service");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(orderIndex);
			ExtentReport.logPass("PASS", "test02ValidateEditMedicalServiceAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditMedicalServiceAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditMedicalServices() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			
			keyInInputByName("orderIndex", updatedOrderIndex, "Medical Service");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(updatedOrderIndex);
			ExtentReport.logPass("PASS", "test03ValidateEditMedicalServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditMedicalServices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateDeleteMedicalService() throws Throwable {
		try {
			doClickButtons("Medical Services", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateEditMedicalServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditMedicalServices", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05DeleteMemberServiceAssignment() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteMemberServiceAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteMemberServiceAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
