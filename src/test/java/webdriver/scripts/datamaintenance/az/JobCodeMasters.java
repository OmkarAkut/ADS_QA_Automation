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
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobCodeMasters extends AzHelper {
	static DataMaintenanceMap dmMap;
	final static String aTozJobCodeMasters = "Job Code Masters";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "Job Codes";
	static String updatedName="Updated"+name;
	static String[] filterAfterEdit= {"Name","Is","Equal To",updatedName};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("JobCodeMasters", "webdriver.scripts.datamaintenance.az",
				"JobCodeMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozJobCodeMasters);
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
			addNewScenario(code, name, null, DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), null, null,
					null, "Save & Create New");
			doClickButtons(aTozJobCodeMasters, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
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
			addNewScenario(code, name, null, DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), null, null,
					null, "Save");
			doClickButtons(aTozJobCodeMasters, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			doClick(DataMaintenanceMap.getazEditBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveAndEditButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveAndEditButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveAndCloseButton() throws Throwable {
		try {
			addNewScenario(null, updatedName, null, DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), null, null,
					null, "Save & Close");
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterAfterEdit);
			assertTextIsDisplayed(updatedName);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveAndCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAndCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddJobCodes() throws Throwable {
		try {
			
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			addDetailsInnerPages(code, name, "Save & Create New","code","description");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04AddJobCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddJobCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditJobCodes() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+name;
			addDetailsInnerPages(null, updatedName, "Save","code","description");
			keyInInputByName("columnLabel", "Job Code Short","Job Code");
			addDetailsInnerPages(null, updatedName, "Save & Close","code","description");
			driverDelay();
			assertData(azName, updatedName);
			assertData(azName, code);
			assertData(azName, "Job Code Short");
			ExtentReport.logPass("PASS", "test05EditJobCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditJobCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteJobCodes() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test06DeleteJobCodes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteJobCodes", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteJobCodeMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteJobCodeMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteJobCodeMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}	
}
