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
public class UserDefinedIndustryClassificationSchemeTypes extends AzHelper {

	static DataMaintenanceMap dmMap;
	final static String aTozUserDefinedIndustryClassifcationSchemeType = "User Defined Industry Classification Scheme Types";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "User Defined Industry Classification Scheme Types";
	static String updatedName="Updated"+name;
	static String[] filterAfterEdit= {"Name","Is","Equal To",updatedName};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UserDefinedIndustryClassificationSchemeTypes", "webdriver.scripts.datamaintenance.az",
				"UserDefinedIndustryClassificationSchemeTypes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozUserDefinedIndustryClassifcationSchemeType);
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
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
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
			doClickButtons(azName, "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
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
			addNewScenario(null, updatedName, null, DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(), null, null,
					null, "Save & Close");
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterAfterEdit);
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
