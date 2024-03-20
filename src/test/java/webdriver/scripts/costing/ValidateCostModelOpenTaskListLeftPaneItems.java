package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateCostModelOpenTaskListLeftPaneItems extends GoHelper{
	static CostingMap costing;
	static ContractingMap modelMap;
	static String costModel="0-MarinaCostModel";
	/** Automates test ticket ADS-6669[ steps missing]*/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateCostModelOpenTaskListLeftPaneItems", "webdriver.scripts.costing", "ValidateCostModelOpenTaskListLeftPaneItems");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
}
