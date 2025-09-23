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
public class ValidateCodeIsReadonlyUnderAZItems extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static String[] aZPages= {"Activity Statistics Masters",
			"Activity Volume Data Scenarios",
			"Ad Hoc Statisc Masters",
			"Admission Sources",
			"APC Fee Schedule Masters",
			"APG Fee Schedule Masters",
			"APR DRG Fee Schedule Masters",
			"Assigned Cost Destinations",
			"Benefit Plan Masters",
			"Bill Types",
			"Care Delivery Networks",
			"Care Delivery Settings",
			"Charge Masters",
			"Chargeable Activity - HCPCS Code Mapping Schemes",
			"Chargeable Activity - Revenue Code Mapping Schemes",
			"Chargeable Activity Fee Schedule Masters",
			"Contact Types",
			"Contract Types",
			"Department Masters",
			"Discharge Statuses",
			"EAPG Fee Schedule Masters",
			"EAPG Payment Action Codes",
			"EFR Categories",
			"Encounter Types",
			"Entity Types",
			"GL Statistic Masters",
			"GPCI Tables",
			"HCFA DRG Fee Schedule Masters",
			"HCPCS Modifiers",
			"ICD Procedure Code Masters",
			"ICD Procedure Fee Schedule Masters",
			"MSDRG Fee Schedule Masters",
			"Payment Organizations",
			"Revenue Code Fee Schedule Masters",
			"TRICARE DRG Fee Schedule Masters",
};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateEncounterTabs", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateEncounterTabs");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateCodeIsReadOnly_12918() throws Throwable {
		try {
			for(int i=0;i<=aZPages.length;i++) {
				selectMaintainDataAtoZ(aZPages[i]);
				
			}
			ExtentReport.logPass("PASS", "test01ValidateCodeIsReadOnly_12918");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCodeIsReadOnly_12918", driver, e);
			fail(e.getMessage());
		}
	}
}
