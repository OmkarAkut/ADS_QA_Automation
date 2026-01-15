package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CimMap;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SiteOfServiceTables extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozSiteOfServiceTables = "Site of Service Tables";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String hcpcsMaster="CPT4 Marina HCPCS Master";
	static String[] siteOfServiceTableFilter= {"Name","Is","Equal To",name};
	String[] columns = {"0002F  TOBACCO USE, SMOKING, ASSESS" ,"0003F  TOBACCO USE, NON-SMOKING"};
	String[] validatCodes= {"0002F" ,"0003F"};
	String[] validateNames= {"TOBACCO USE, SMOKING, ASSESS" ,"TOBACCO USE, NON-SMOKING"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("SiteOfServiceTables", "webdriver.scripts.datamaintenance.maintaindata",
				"SiteOfServiceTables");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozSiteOfServiceTables);
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
			keyInInputByName("name", name, "Site of Service Table");
			clickCheckboxByName("hcpcsCodeMasterLi");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.gethcpcsMasterDrp(), hcpcsMaster);
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(siteOfServiceTableFilter);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddHCPCSCodes() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageAddBtn());
			waitForFormDialog("Add HCPCS Code");
			ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			assertElements(validatCodes, "Site of Service Table");
			for(String name:validateNames) {
				assertTextIsDisplayed(name);
			}
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test02AddHCPCSCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddHCPCSCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteHCPCSCodes() throws Throwable {
		try {
			for(WebElement element:driver.findElements(By.xpath("//h1[text()='HCPCS Codes']//following::table"))) {
				element.click();
				doClickButtons("Site of Service Table", "Delete");
			}
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test03DeleteHCPCSCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteHCPCSCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteSiteOfServiceTable() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteSiteOfServiceTable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteSiteOfServiceTable", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
