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
public class TransactionClassificationMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozTransactionClassificationMaster = "Transaction Classification Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] transacClassificationMasterFilter= {"Name","Is","Equal To",name};
	static String updatedName;
	static String azName="Transaction Classification Code List";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TransactionClassificationMasters", "webdriver.scripts.datamaintenance.az",
				"TransactionClassificationMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozTransactionClassificationMaster);
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
			doFilterCreate(transacClassificationMasterFilter);
			assertTextIsDisplayed(name);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(),null,
					null,null,"Save");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(transacClassificationMasterFilter);
			assertTextIsDisplayed(name);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(),null,
					null,null,"Save & Close");			
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(transacClassificationMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddTransacCode() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			String transacCode="Transac Code"+name;
			addDetailsInnerPages(code, transacCode, "Save & Create New","code","name");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(transacCode);
			ExtentReport.logPass("PASS", "test04AddTransacCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddTransacCode", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditTransacCode() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+name;
			addDetailsInnerPages(null, updatedName, "Save","code","name");
			addDetailsInnerPages(null, updatedName, "Save & Close","code","name");
			driverDelay();
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test05EditTransacCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditTransacCode", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06DeleteTransacCode() throws Throwable {
		try {
			doClick(DataMaintenanceMap.gettransactionCodeListDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test07DeleteMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteMappings", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test07DeleteTransacCodeMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test09DeleteActivityStatisticsMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteActivityStatisticsMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
