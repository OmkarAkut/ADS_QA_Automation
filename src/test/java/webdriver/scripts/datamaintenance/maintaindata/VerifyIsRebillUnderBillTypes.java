package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class VerifyIsRebillUnderBillTypes extends CalculationHelper {
	static DataMaintenanceMap dmMap;
	final static String aTozPageBillTypes = "Bill Types";
	static String billType = "1SM1";
	static String[] filter = { "Code", "Is", "Equal To", billType };
	static String isRebillBeforeEdit;
	static String isRebillCheckEdit;

	/** Support Issues: Automated test script for ADS-12591 **/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("VerifyIsRebillUnderBillTypes", "webdriver.scripts.datamaintenance.maintaindata",
				"VerifyIsRebillUnderBillTypes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageBillTypes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateIsRebillCheckbox_12591() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getbillTypeFilterBtn());
			doFilterCreate(filter);
			if (DataMaintenanceMap.getbillTypeCheckbox().size() >= 1) {
				doClick(DataMaintenanceMap.getbillTypeEditBtn());
				waitForElementToBeVisible(DataMaintenanceMap.getbillTypeWindow());

				if (DataMaintenanceMap.getisRebill().getAttribute("class").contains("checked")) {
					assertTrue(printout);
				} else {
					fail();
				}
			} else if (!(DataMaintenanceMap.getbillTypeCheckbox().size() >= 1)) {
				doClick(DataMaintenanceMap.getbillTypeEditBtn());
				waitForElementToBeVisible(DataMaintenanceMap.getbillTypeWindow());

				isRebillCheckEdit = DataMaintenanceMap.getisRebill().getAttribute("class");
				if (isRebillCheckEdit.contains("checked")) {
					fail();
				} else {
					assertTrue(printout);
				}
			}
			doClick(DataMaintenanceMap.getisRebillCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidateIsRebillCheckbox_12591");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateIsRebillCheckbox_12591", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
