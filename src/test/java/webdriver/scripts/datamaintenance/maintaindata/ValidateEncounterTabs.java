package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateEncounterTabs extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	private static String encounterId="502057159E";
	final static String aTozPageEncounters = "Encounters";
	static String[] tabs= {"Totals","Diagnoses","Procedures","DRGs","APCs & APGs","Services","Financial Records","Charges","Payments","UDFs & UDRs"};
	/** Support Issues: Automated test script for ADS-12929**/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateEncounterTabs", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateEncounterTabs");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageEncounters);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveInAllEncounterTabs_12929() throws Throwable {
		try {
			doSearchForModel(encounterId);
			tableDoubleClickCellFirstColumn(encounterId);
			for(String tab:tabs) {
				doClick("//span[text()='"+tab+"']/../../..");
				waitForDisplayedSpinnerToEnd();
				doClick(DataMaintenanceMap.getsaveButton());
			}
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateTotalTab_12929() throws Throwable {
		try {
			doClick("//span[text()='Totals']/../../..");
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
}
