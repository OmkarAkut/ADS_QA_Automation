package webdriver.scripts.datamaintenance.maintaindata;

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
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Customer Issues: Automated test script for ADS-4935*/
public class ValidateSelectionFromMasterDropdownInTemplatesSection extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozChargeableActivityFeeScheduleMaster = "Chargeable Activity Fee Schedule Masters";
	final static String aTozHCPCSFeeScheduleMasters="HCPCS Fee Schedule Masters";
	final static String aTozICDProcedureFeeScheduleMasters="ICD Procedure Fee Schedule Masters";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String masterName = "Master " + currentDateTime;
	static String chargeMaster="150 Marina Charge Master 2";
	static String hcpcsMaster="CPT4 Marina HCPCS Master";
	static String icdProc="1833ICDPROC 1800 ICD Proc Codes";
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateSelectionFromMasterDropdownInTemplatesSection", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateSelectionFromMasterDropdownInTemplatesSection");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozChargeableActivityFeeScheduleMaster);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void openScenarioForEdit() throws Throwable {
		doClick(DataMaintenanceMap.getazGirdFirstElement());
		doClick(DataMaintenanceMap.getazEditBtn());
		doClick(DataMaintenanceMap.getazTemplatesPanel());
		doClick(DataMaintenanceMap.getazTemplatesNewBtn());
	}
	public void addMasterClassificationScheme(String masterName,String master) throws Throwable {
		keyInInputText(masterName, DataMaintenanceMap.getazMasterName());
		driver.findElement(By.name("drgClassCode")).click();
		driverDelay();
		doDropdownSelectUsingOptionText(driver.findElement(By.xpath("//input[@name='drgClassCode']")), DataMaintenanceMap.getazMasterDropdownOptions(), master);
		doClick(DataMaintenanceMap.getazMasterDropdownSaveClose());
		assertElementIsDisplayedWithXpath("//div[text()='"+masterName+"']");
		doClick("//div[text()='"+masterName+"']");
		doClick(DataMaintenanceMap.getazTemplatesDeleteBtn());
		doClick(DataMaintenanceMap.getwarningDeleteBtn());
		doClick(DataMaintenanceMap.getazCancelCloseBtn());
	}
	@Test
	public void test01ValidateMasterDropdown_ChargeableActivityMaster_ADS_4935() throws Throwable {
		try {
			openScenarioForEdit();
			addMasterClassificationScheme(masterName,chargeMaster);
			ExtentReport.logPass("PASS", "test01ValidateMasterDropdown_ChargeableActivityMaster_ADS_4935");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateMasterDropdown_ChargeableActivityMaster_ADS_4935", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateMasterDropdown_HCPCSFeeSchedule_ADS_4935() throws Throwable {
		try {
			selectMaintainDataAtoZ(aTozHCPCSFeeScheduleMasters);
			openScenarioForEdit();
			addMasterClassificationScheme(masterName,hcpcsMaster);
			ExtentReport.logPass("PASS", "test02ValidateMasterDropdown_HCPCSFeeSchedule_ADS_4935");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateMasterDropdown_HCPCSFeeSchedule_ADS_4935", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateMasterDropdown_ICDProcedure_ADS_4935() throws Throwable {
		try {
			selectMaintainDataAtoZ(aTozICDProcedureFeeScheduleMasters);
			openScenarioForEdit();
			addMasterClassificationScheme(masterName,icdProc);
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test03ValidateMasterDropdown_ICDProcedure_ADS_4935");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateMasterDropdown_ICDProcedure_ADS_4935", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
