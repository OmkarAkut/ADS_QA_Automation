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
public class Entities extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEntities = "Entities";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] entitiesFilter= {"Name","Is","Equal To",name};
	static String entity="HOSP";
	static String updatedName;
	static String empCode="EMP"+code;
	static String empName="Emp"+name;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Entities", "webdriver.scripts.datamaintenance.maintaindata",
				"Entities");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEntities);
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
			keyInInputByName("description", name, "New Code");
			keyInInputByName("code", code, "New Code");
			keyInInputByName("shortName", "ADS", "New Code");
			keyInInputByName("npiEntity", "1087", "New Code");
			clickCheckboxByName("entityTypeObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.gettypeEntityDrp(), entity);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02ValidateCareDeliveryFacility() throws Throwable {
		try {
			clickCheckboxByName("careDeliveryFacility");
			doClickButtons("Care Delivery Facility", "Select");
			waitForFormDialog("Care Delivery Facility Role");
			closeWindowDialog("Care Delivery Facility Role");
			ExtentReport.logPass("PASS", "test02ValidateCareDeliveryFacility");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateCareDeliveryFacility", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateCostAccountingEntity() throws Throwable {
		try {
			clickCheckboxByName("costAccounting");
			doClickButtons("Cost Accounting Entity", "Select");
			waitForFormDialog("Cost Accounting Entity Role");
			closeWindowDialog("Cost Accounting Entity Role");
			clickCheckboxByName("insuranceCarrier");
			ExtentReport.logPass("PASS", "test03ValidateCostAccountingEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateCostAccountingEntity", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateSellerOfServices() throws Throwable {
		try {
			clickCheckboxByName("sellerOfService");
			doClickButtons("Seller Of Services", "Select");
			waitForFormDialog("Seller Of Services Role");
			closeWindowDialog("Seller Of Services Role");
			clickCheckboxByName("sellerOfService");
			ExtentReport.logPass("PASS", "test04ValidateSellerOfServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateSellerOfServices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05ValidateSellerOfServices() throws Throwable {
		try {
			clickCheckboxByName("buyersOfService");
			doClickButtons("Buyer Of Services", "Select");
			waitForFormDialog("Buyer Of Services Role");
			closeWindowDialog("Buyer Of Services Role");
			ExtentReport.logPass("PASS", "test05ValidateSellerOfServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateSellerOfServices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddNewEntity() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(entitiesFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed("ADS");
			assertElementIsDisplayedWithXpath("(//span[@aria-label='Care Delivery Facility'][@class='x-grid-checkcolumn x-grid-checkcolumn-checked'])");
			assertElementIsDisplayedWithXpath("(//span[@aria-label='Cost Accounting Entity Role'][@class='x-grid-checkcolumn x-grid-checkcolumn-checked'])");
			assertElementIsDisplayedWithXpath("(//span[@aria-label='Insurance Carrier'][@class='x-grid-checkcolumn x-grid-checkcolumn-checked'])");
			assertElementIsDisplayedWithXpath("(//span[@aria-label='Seller of Services'][@class='x-grid-checkcolumn'])");
			assertElementIsDisplayedWithXpath("(//span[@aria-label='Buyer of Services'][@class='x-grid-checkcolumn x-grid-checkcolumn-checked'])");
			ExtentReport.logPass("PASS", "test06AddNewEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddNewEntity", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07EditEntity() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("street1", "MG Road", "Entity");
			keyInInputByName("city", "MG Road", "Entity");
			keyInInputByName("state", "MG Road", "Entity");
			keyInInputByName("county", "MG Road", "Entity");
			keyInInputByName("country", "MG Road", "Entity");
			keyInInputByName("zipCode", "123456", "Entity");
			keyInInputByName("phoneNumber", "123456", "Entity");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test07EditEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07EditEntity", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08DeleteEntity() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test08DeleteEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteEntity", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
