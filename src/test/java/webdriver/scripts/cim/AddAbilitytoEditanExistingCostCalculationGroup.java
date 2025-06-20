package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20055 **/
public class AddAbilitytoEditanExistingCostCalculationGroup extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType="Activity Volume Calc Scenario: ADS-262 Vol Calc";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
	
		ExtentReport.reportCreate("AddAbilitytoEditanExistingCostCalculationGroup", "webdriver.scripts.cim",
				"AddAbilitytoEditanExistingCostCalculationGroup");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01Validate_EditCim_20055() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimEditBtn());
			cimMap.getcimDesc().sendKeys(cimScenarioCreate);
			driverDelay();
			doClick("((//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div)[2]");
			doClick(cimMap.getcalcTypeSelectBtn());
			waitForPresenceOfElement("//strong[text()='2 Item(s) Selected']");
			doClick(cimMap.getcimSaveCloseBtn());
			ExtentReport.logPass("PASS", "test01Validate_EditCim_20055");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_EditCim_20055", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_CimUpdates_20055() throws Throwable {
		try {
			assertElementIsDisplayedWithXpath("(//div[@class='x-grid-item-container']//td/div)[3]");
			doClick(cimMap.getcimEditBtn());
			assertElementIsDisplayedWithXpath("//strong[text()='2 Item(s) Selected']");
			doClick(cimMap.getcimCancelCloseBtn());
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_CimUpdates_20055");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CimUpdates_20055", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
