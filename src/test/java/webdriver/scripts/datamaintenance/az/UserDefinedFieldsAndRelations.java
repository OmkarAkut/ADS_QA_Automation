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
public class UserDefinedFieldsAndRelations extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozUserDefinedFieldsAndRelations = "User Defined Fields And Relations";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UserDefinedFieldsAndRelations", "webdriver.scripts.datamaintenance.az",
				"UserDefinedFieldsAndRelations");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozUserDefinedFieldsAndRelations);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewFieldBtn());
			doClickButtons("Warning", "Continue");
			doClick(DataMaintenanceMap.getazNewFieldBtn());
			keyInInputByName("label", name, "New User Defined Field");
			clickCheckboxByName("owner");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getuserDefinedTable(), "Charge Item");
			keyInInputByName("fieldName", code, "New User Defined Field");
			keyInInputByName("description", "ADS", "New User Defined Field");
			keyInInputByName("dataType", "Integer", "New User Defined Field");
			doClick(DataMaintenanceMap.getuserDefinedTableSpinnerUp());
			doClick(DataMaintenanceMap.getuserDefinedTableSpinnerDown());
			keyInInputByName("dataType", "Float", "New User Defined Field");
			doClick(DataMaintenanceMap.getuserDefinedTableSpinnerUp());
			doClick(DataMaintenanceMap.getuserDefinedTableSpinnerDown());
			doClick(DataMaintenanceMap.getuserDefinedTableDecimalSpinnerUp());
			doClick(DataMaintenanceMap.getuserDefinedTableDecimalSpinnerdown());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AssignReimburseAssignment() throws Throwable {
		doClick(DataMaintenanceMap.getazEditBtn());
		try {
			if(driver.findElements(By.xpath("//div[contains(@id,'domainlockerrorwindow')][text()='Error']")).size()>0) {
				doClickButtons("Error", "Close");
				doClick("(//div[text()='User Defined Fields And Relations'])[2]//following::div[contains(@class,'x-grid-item-container')]//table[2]");
			}
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("description", "ADS", "User Defined Relation");
			doClick(DataMaintenanceMap.getazSaveBtn());
			driverDelay();
			doClickButtons("Warning", "OK");
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02AssignReimburseAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssignReimburseAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
