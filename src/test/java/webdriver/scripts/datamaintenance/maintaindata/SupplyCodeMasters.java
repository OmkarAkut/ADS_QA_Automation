package webdriver.scripts.datamaintenance.maintaindata;

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
public class SupplyCodeMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozSupplyCodeMaster = "Supply Code Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] supplyCodeMasterFilter= {"Name","Is","Equal To",name};
	static String updatedName;
	static String azName="Supply Code Master";
	static String supplyCodeName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("SupplyCodeMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"SupplyCodeMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozSupplyCodeMaster);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),null,null,null,"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(supplyCodeMasterFilter);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),null,null,null,"Save");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(supplyCodeMasterFilter);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),DataMaintenanceMap.getaddName(),null,null,null,"Save & Close");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(supplyCodeMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddSupplyCodes() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			supplyCodeName= "Code"+name;
			addDetailsInnerPages(code, supplyCodeName, "Save & Create New","deptCode","description");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(supplyCodeName);
			ExtentReport.logPass("PASS", "test04AddVendorCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddVendorCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditSupplyCodes() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+name;
			keyInInputText("SupplyCode",DataMaintenanceMap.getshortName());
			keyInInputText("30",DataMaintenanceMap.getsupplyCost());
			clickButton("User Defined Fields");
			keyInInputByName("textField1","UserData");
			keyInInputByName("floatField1","10");
			keyInInputByName("date1",javaGetCurrentDate("MM/dd/yyyy"));
			addDetailsInnerPages(null, updatedName, "Save","deptCode","description");
			addDetailsInnerPages(null, updatedName, "Save & Close","deptCode","description");
			driverDelay();
			assertTextIsDisplayed(updatedName);
			assertTextIsDisplayed("SupplyCode");
			assertTextIsDisplayed("30.000000");
			ExtentReport.logPass("PASS", "test05EditVendorCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditVendorCodes", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06DeleteSupplyCodes() throws Throwable {
		try {
			doClickButtons(azName, "Delete");
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test06DeleteVendorCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteVendorCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteSupplyCodeMaster() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test09DeleteVendorMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteVendorMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
