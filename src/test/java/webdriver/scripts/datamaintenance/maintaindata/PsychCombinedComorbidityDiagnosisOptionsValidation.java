package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class PsychCombinedComorbidityDiagnosisOptionsValidation extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageAscSchemes = "Psych Combined Comorbidity Assignments";
	static String scenario="Psych Comorbidity 2023";
	static String[] filter = { "Name", "Is", "Equal To", scenario };
	/** Support Issues: Automated test script for ADS-12612 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PsychCombinedComorbidityDiagnosisOptionsValidation", "webdriver.scripts.datamaintenance.maintaindata",
				"PsychCombinedComorbidityDiagnosisOptionsValidation");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageAscSchemes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void validateICDOptions(WebElement element,WebElement selectOption,WebElement viewCriteria) throws Throwable {
	
			element.click();
			if(selectOption.getAttribute("class").contains("diabled") && viewCriteria.getAttribute("class").contains("diabled")) {
				fail();
			}
			else {
				assertTrue(printout);
			}
		
	}
	@Test
	public void test01ValidateDiagnosisTabs_12612() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getpsychFilterButton());
			waitForElementToBeVisible(DataMaintenanceMap.getpsychFilterWindow());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(scenario);
			doClick(DataMaintenanceMap.geticd9DiagnosisBtn());
			validateICDOptions(DataMaintenanceMap.geticd9DiagnosisBtn(),DataMaintenanceMap.geticd9SelectBtn(),DataMaintenanceMap.geticd9ViewCriteriaBtn());
			validateICDOptions(DataMaintenanceMap.geticd9DiagnosisBtn(),DataMaintenanceMap.geticd10SelectBtn(),DataMaintenanceMap.geticd10ViewCriteriaBtn());
			doClick(DataMaintenanceMap.getpsychCancelCloseBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidateDiagnosisTabs_12612");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateDiagnosisTabs_12612", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
