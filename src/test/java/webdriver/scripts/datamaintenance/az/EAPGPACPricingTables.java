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
public class EAPGPACPricingTables extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEAPGPACPricingTables = "EAPG PAC Pricing Tables";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] EAPGPACPricingTablesFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String paymentPercent="12";
	static String updatedPaymentPercent="22";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EAPGPACPricingTables", "webdriver.scripts.datamaintenance.maintaindata",
				"EAPGPACPricingTables");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEAPGPACPricingTables);
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
			keyInInputByName("name", name, "EAPG PAC Pricing Table");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(EAPGPACPricingTablesFilter);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditButton_AddEAPGPACPrices() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickSelectButton("Payment Action Code");
			selectFormItem("01 Full payment", "");
			keyInInputByName("paymentPercent", paymentPercent, "EAPG PAC Price");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("01");
			assertTextIsDisplayed("Full payment");
			assertTextIsDisplayed(paymentPercent);
			ExtentReport.logPass("PASS", "test02ValidateEditButton_AddEAPGPACPrices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditButton_AddEAPGPACPrices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditEAPGPACPrices() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("paymentPercent",updatedPaymentPercent, "EAPG PAC Price");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("01");
			assertTextIsDisplayed("Full payment");
			assertTextIsDisplayed(updatedPaymentPercent);
			ExtentReport.logPass("PASS", "test03EditEAPGPACPrices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditEAPGPACPrices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteEAPGPACPrices() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test04DeleteEAPGPACPrices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteEAPGPACPrices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteEAPGPricingTable() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteEAPGPricingTable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteEAPGPricingTable", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
