package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModifierPricingTables extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozModifierPricingTables = "Modifier Pricing Tables";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String priceTable=name.replace(".", "");
	static String deptHierarchy="ASESC-1832 Hospital Hierarchy";
	static String glAcctHierarchy="ASESC-1832 Hospital Account Hierarchy";
	static String[] ModifierPricingTableFilter= {"Name","Is","Equal To",priceTable};
	static String entity="0000 PRIVATE PAY";
	static String varPercent="10.13";
	static String upVarPercent="10.16";
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ModifierPricingTables", "webdriver.scripts.datamaintenance.maintaindata",
				"ModifierPricingTables");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozModifierPricingTables);
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
			keyInInputByName("name", priceTable, "Modifier Pricing Table");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(ModifierPricingTableFilter);
			assertTextIsDisplayed(priceTable);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditModifierPricingTable() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("Modifier Price", "Select");
			driverDelay(200);
			selectFormItem("1SM1", "");
			clickCheckboxByName("paymentPercent");
			keyInInputByName("paymentPercent", varPercent, "Modifier Price");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("1SM1");
			assertTextIsDisplayed(varPercent);
			ExtentReport.logPass("PASS", "test02ValidateEditModifierPricingTable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditModifierPricingTable", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditModifierPrice() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("paymentPercent", upVarPercent, "Modifier Price");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(upVarPercent);
//			
			ExtentReport.logPass("PASS", "test03ValidateEditModifierPrice");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditModifierPrice", driver, e);
			fail(e.getMessage());
		}
	}

@Test
public void test04ValidateDeleteModifierPrice() throws Throwable {
	try {
		CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
		doClick(DataMaintenanceMap.getwarningDeleteBtn());
		assertTextIsDisplayed("There is no data available to display.");
		doClick(DataMaintenanceMap.getazSaveBtn());
		doClick(DataMaintenanceMap.getazSaveCloseBtn());
		ExtentReport.logPass("PASS", "test04ValidateDeleteModifierPrice");
	} catch (Exception | AssertionError e) {
		ExtentReport.logFail("FAIL", "test04ValidateDeleteModifierPrice", driver, e);
		fail(e.getMessage());
	}
}

	@Test
	public void test05DeleteModifierPricingTable() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteModifierPricingTable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteModifierPricingTable", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
