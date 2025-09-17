package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class NewEditASCSchemes extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageAscSchemes = "ASC Schemes";
	static String isRebillBeforeEdit;
	static String isRebillCheckEdit;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String ascScheme="Scheme "+currentDateTime;
	static String hcpcCodeMaster="ORHCPCSMASTER";
	static String[] filter = { "ASC Scheme Name", "Is", "Equal To", ascScheme };
	/** Support Issues: Automated test script for ADS-12593 **/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("VerifyIsRebillUnderBillTypes", "webdriver.scripts.datamaintenance.maintaindata",
				"VerifyIsRebillUnderBillTypes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageAscSchemes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateNewButton_12593() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getascSchemeNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getaddName());
			keyInInputText(ascScheme,DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.gethcpcsCodeMaster());
			driverDelay(100);
			doDropdownSelectUsingOptionTextWithelement(DataMaintenanceMap.gethcpcsCodeMasterDrpdwn(),
					hcpcCodeMaster);
			doClick(DataMaintenanceMap.gethcpcsCodeMasterSaveClose());
			doClick(DataMaintenanceMap.getascSchemeFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(ascScheme);
			ExtentReport.logPass("PASS", "test01ValidateNewButton_12593");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton_12593", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditButton_12593() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getascSchemeEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getaddName());
			assertElementIsDisplayedWithXpath("//span[text()='"+ascScheme+"']");
			doClick(DataMaintenanceMap.getascSchemeCancelClose());
			driverDelay(100);
			doClick(DataMaintenanceMap.getascSchemeDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test01ValidateNewButton_12593");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton_12593", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		
		ExtentReport.report.flush();
	}
	
}
