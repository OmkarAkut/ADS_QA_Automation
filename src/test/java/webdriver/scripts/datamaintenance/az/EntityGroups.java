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
public class EntityGroups extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEntityGroups = "Entity Groups";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] entityGroupFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY ";
	static String updatedName;
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EmployerGroups", "webdriver.scripts.datamaintenance.az",
				"EmployerGroups");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEntityGroups);
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
			keyInInputByName("name", name, "Entity Group");
			doClickButtons("Entity Group", "Select");
			selectFormItem(entity, "services");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(entityGroupFilter);
			assertTextIsDisplayed(name);
			
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditEntityGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons("Entity Group", "Select");
			selectFormItem("0001 PRIVATE PAY PENDING ", "services");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test02EditEntityGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditEntityGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeletetEntityGroup() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03DeletetEntityGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeletetEntityGroup", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
