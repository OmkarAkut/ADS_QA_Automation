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
public class RBRVSRVUTables extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozRBRVSRVUTables = "RBRVS RVU Tables";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String hcpcsCodeMaster="CPT4";
	static String[] RBRVSRVUTablesFilter= {"Name","Is","Equal To",name};
	static String hcpcs ="00001A";
	static String hcpcsModifier="1SM1";
	static String statusIndicator="E";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RBRVSRVUTables", "webdriver.scripts.datamaintenance.az",
				"RBRVSRVUTables");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozRBRVSRVUTables);
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
			keyInInputByName("name", name, "RBRVS RVU Table");
			selectDropdownOption("hcpcsCodeMas", DataMaintenanceMap.gethcpcscodeMasterDrp(), "CPT4");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(RBRVSRVUTablesFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditRBRVS() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New RVU");
			doClick(DataMaintenanceMap.gethcpcsCpt());
			waitForFormDialog("Add HCPCS/CPT Code");
			selectFormItem(hcpcs, "");
			selectDropdownOption("hcpcsModifierCode", DataMaintenanceMap.gethcpcsModifierDrp(), "1SM1 hcpcs modifer1");
			selectDropdownOption("statusIndIdObjectId", DataMaintenanceMap.getstatusIndicatorDrp(), "E Excluded");
			selectDropdownOption("physicianSuperVisionCode", DataMaintenanceMap.getphysicianSupervisionDrp(), "66");
			selectDropdownOption("updateIndIdObjectId", DataMaintenanceMap.getupdateIndicatorDrp(), "P Primary care services");
			doClick(DataMaintenanceMap.getendoscoicBaseCode());
			waitForFormDialog("Endoscopic Base Code");
			selectFormItem("0001F Heart failure composite", "");
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateAddRVUDetails() throws Throwable {
		try {
			expandPanel("RVU");
			keyInInputByName("work", "2", "New RVU");
			keyInInputByName("malPractice", "2", "New RVU");
			keyInInputByName("practiceExp", "2", "New RVU");
			keyInInputByName("nonFacilityPracticeExpRVU", "2", "New RVU");
			keyInInputByName("transFacilityPracExp", "2", "New RVU");
			keyInInputByName("transNonFacilityPracExp", "2", "New RVU");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(hcpcs);
			assertTextIsDisplayed(hcpcsModifier);
			assertTextIsDisplayed(statusIndicator);
			assertElementTextContainsWithXpathLocator("//div[text()='"+hcpcs+"']//following::div[3]", "2", printout);
			assertElementTextContainsWithXpathLocator("//div[text()='"+hcpcs+"']//following::div[4]", "2", printout);
			ExtentReport.logPass("PASS", "test03ValidateAddRVUDetails");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateAddRVUDetails", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04EditRBRVSRVU() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			expandPanel("Other");
			selectDropdownOption("globalPeriodObjectId", DataMaintenanceMap.getglobalPeriod(), "010 10 Days in the global period");
			selectDropdownOption("diagnosticMagingFamilyInd", DataMaintenanceMap.getdiagnosticImagingIndicator(), "88");
			keyInInputByName("preOpPercentage", "10", hcpcs);
			keyInInputByName("intraOpPercentage", "10", hcpcs);
			keyInInputByName("postOpPercentage", "10", hcpcs);
			keyInInputByName("nonFacilityPracExpOpsMnt", "10", hcpcs);
			keyInInputByName("facilityPracticeExpOpsMnt", "10", hcpcs);
			keyInInputByName("malPracticeOpsMnt", "10", hcpcs);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04EditRBRVSRVU");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04EditRBRVSRVU", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteRBRVSRVU() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteRBRVSRVU");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteRBRVSRVU", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteRBRVSRVUTable() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test06DeleteRBRVSRVUTable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteRBRVSRVUTable", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
