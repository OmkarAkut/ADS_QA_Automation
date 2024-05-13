package Regression;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ValidateContractingTreeTab extends CalculationHelper{
	private static ContractingMap modelMap;
	static final String ContractType = "Contract Types";
	static  String ContractTypeName;
	/** Regression: Automated test script for ADS-6444 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateContractingTreeTab",
				"webdriver.scripts.contracting",
				"ValidateContractingTreeTab");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			doClick(ContractingMap.getContractDataMaintenance());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6444
	@Test
	public void test01VerifyEditContractingTreeTab_6444() throws Throwable {
		try {
			doClickTreeData("Contracting");
			driverDelay(200);
			doClickTreeSubItem(ContractType);
			ContractTypeName=ContractingMap.getContractTypeColName().getText();
			doClick(ContractingMap.ContractTypeEditButton());
			assertThatAttributeValueIsEqual(ContractingMap.getInputName(), ContractTypeName, printout);
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01VerifyEditContractingTreeTab");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyEditContractingTreeTab", driver, e);
			fail(e.getMessage());
		} 
		finally {
			doClosePageOnLowerBar("Maintain Data");
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
