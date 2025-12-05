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
public class CommissionBrokers extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozCommissionBroker= "Commission Brokers";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String entity="0001 PRIVATE PAY PENDING ";
	static String[] commissionBrokerFilter= {"Name","Is","Equal To",name};
	static String[] commisionDialog=entity.split(" ");
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CommissionBrokers", "webdriver.scripts.datamaintenance.maintaindata",
				"CommissionBrokers");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozCommissionBroker);
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
			keyInInputByName("name", name, "New Code");
			keyInInputByName("code", code, "New Code");
			doClickButtons("New Code", "Select");
			selectFormItem(entity, "");
			CimHelper.checkElements(DataMaintenanceMap.getsaveCreateNew());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(commissionBrokerFilter);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "New Code");
			keyInInputByName("code", code, "New Code");
			doClickButtons("New Code", "Select");
			selectFormItem(entity, "");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(commissionBrokerFilter);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputText("Updated"+name, DataMaintenanceMap.getcommissionBrokerName());
			CimHelper.checkElements(DataMaintenanceMap.getsaveCloseBtn());
			String[] commissionBrokerAgencyFilterUpdate= {"Name","Is","Equal To","Updated"+name};
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(commissionBrokerAgencyFilterUpdate);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			assertTextIsDisplayed("Updated"+name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test03ValidateEditSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteCommissionBroker() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());

			ExtentReport.logPass("PASS", "test04DeleteCommissionBroker");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteCommissionBroker", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
