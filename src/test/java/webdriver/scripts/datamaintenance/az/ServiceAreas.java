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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceAreas extends AzHelper {

	static DataMaintenanceMap dmMap;
	final static String aTozServiceAreas = "Service Areas";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "Service Areas";
	static String updatedName="Updated"+name;
	static String[] filterAfterEdit= {"Name","Is","Equal To",updatedName};
	static String transacType="Contractual Allowance";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ServiceAreas", "webdriver.scripts.datamaintenance.az",
				"ServiceAreas");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozServiceAreas);
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
			keyInInputByName("code", code,"Service Area");
			keyInInputByName("name", name,"Service Area");	
			clickButton("Select");
			waitForFormDialog("Add Entity Code");
			selectFormItem("0001 PRIVATE PAY PENDING ","services");
			doClickButtons(azName, "Save & Create New");
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveAndEditButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("code", code,"Service Area");
			keyInInputByName("name", name,"Service Area");	
			clickButton("Select");
			waitForFormDialog("Add Entity Code");
			selectFormItem("0001 PRIVATE PAY PENDING ","services");
			doClickButtons(azName, "Save");
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			doClick(DataMaintenanceMap.getazEditBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveAndCloseButton() throws Throwable {
		try {
			keyInInputByName("name", updatedName,"Service Area");
			doClickButtons(azName, "Save & Close");
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterAfterEdit);
			assertTextIsDisplayed(updatedName);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveAndCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}	
}
