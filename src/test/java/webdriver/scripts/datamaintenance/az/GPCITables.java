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
public class GPCITables extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGPCITables = "GPCI Tables";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] GPCITablesFilter= {"Name","Is","Equal To",name};
	static String work="5.4";
	static String expense="6.5";
	static String malPractice="2.3";
	static String globalValue="4.5";
	static String updatedWork="5.8";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GPCITables", "webdriver.scripts.datamaintenance.az",
				"GPCITables");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGPCITables);
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
			keyInInputByName("name", name, aTozGPCITables);
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(GPCITablesFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditGPCITables() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("GPCI Tables", "Select");
			selectFormItem("1 Medicare Locality Table", "");
			keyInInputByName("work", work, aTozGPCITables);
			keyInInputByName("practiceExpense", expense, aTozGPCITables);
			keyInInputByName("malPractice", malPractice, aTozGPCITables);
			clickCheckboxByName("useGlobal");
			keyInInputByName("globalValue", globalValue, aTozGPCITables);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			driverDelay();
			assertTextIsDisplayed(work);
			assertTextIsDisplayed(expense);
			assertTextIsDisplayed(malPractice);
			assertTextIsDisplayed(globalValue);
			assertTextIsDisplayed("05535");
			assertTextIsDisplayed("Medicare Locality Table");
			ExtentReport.logPass("PASS", "test02ValidateEditGPCITables");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditGPCITables", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditGPCostIndices() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("work", updatedWork, aTozGPCITables);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(updatedWork);
			assertTextIsDisplayed(expense);
			assertTextIsDisplayed(malPractice);
			assertTextIsDisplayed(globalValue);
			assertTextIsDisplayed("05535");
			assertTextIsDisplayed("Medicare Locality Table");
			ExtentReport.logPass("PASS", "test03EditGPCostIndices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditGPCostIndices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteGPCostIndices() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04DeleteGPCostIndices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteGPCostIndices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteGPCITable() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteGPCITable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteGPCITable", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
