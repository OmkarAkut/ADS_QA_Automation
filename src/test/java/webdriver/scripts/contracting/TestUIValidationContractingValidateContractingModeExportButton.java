package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestUIValidationContractingValidateContractingModeExportButton extends GoHelper{

	private static ContractingMap modelMap;
	/** Regression: Automated test script for ADS-6437 ,ADS-6436 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("TestUIValidationContractingValidateContractingModeExportButton", "webdriver.scripts.contracting",
				"TestUIValidationContractingValidateContractingModeExportButton");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);

			System.out.println("Test Class: " + TestUIValidationContractingValidateContractingModeExportButton.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForAjaxExtJs();
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(ContractingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ExportContractModel() {
		
		
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
