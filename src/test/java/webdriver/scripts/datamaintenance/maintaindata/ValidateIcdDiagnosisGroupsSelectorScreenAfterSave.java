package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Assert;
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
/** Support Issues: Automated test script for ADS-12900 */
public class ValidateIcdDiagnosisGroupsSelectorScreenAfterSave extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageIcdGroup = "ICD Diagnosis Groups";
	static String icdGroup="ADS-12900 ICD Diagnosis Group";
	static String[] filter = { "Name", "Is", "Equal To", icdGroup };
	static String selectFirstItem;
	static String savedIcd;
	/** Support Issues: Automated test script for ADS-12900 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateIcdDiagnosisGroupsSelectorScreenAfterSave", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateIcdDiagnosisGroupsSelectorScreenAfterSave");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageIcdGroup);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveIcdGroup_12900() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getselectICDgroupFilterBtn());
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getselectICDgroupEditBtn());
			doClick(DataMaintenanceMap.getselectICD10Btn());
			waitForElementToBeVisible(DataMaintenanceMap.getselectICD10Window());
			doClick(DataMaintenanceMap.getselectICD10WindowSelectAllBtn());
			doClick(DataMaintenanceMap.getselectICDgroupFirstItem());
			selectFirstItem=DataMaintenanceMap.getselectICDgroupFirstItem().getText();
			doClick(DataMaintenanceMap.getselectICD10WindowSelectBtn());
			doClick(DataMaintenanceMap.getselectICD10WindowApplyBtn());
			doClick(DataMaintenanceMap.getselectICDgroupSaveBtn());
			savedIcd=DataMaintenanceMap.getdiagnosisCode().getText();
			if(selectFirstItem.contains(savedIcd)) {
				assertTrue(printout);
			}
			else {
				fail();
			}
			doClick(DataMaintenanceMap.getselectICD10Btn());
			waitForElementToBeVisible(DataMaintenanceMap.getselectICD10Window());
			assertElementIsDisplayed(DataMaintenanceMap.getselectICD10Window());
			doClick(DataMaintenanceMap.getselectICD10WindowApplyBtn());
			doClick(DataMaintenanceMap.getselectICDgroupSaveBtn());
			doClick(DataMaintenanceMap.getselectICDgroupSaveCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveIcdGroup_12900");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveIcdGroup_12900", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
