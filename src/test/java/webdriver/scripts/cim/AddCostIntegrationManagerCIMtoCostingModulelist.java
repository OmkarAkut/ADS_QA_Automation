package webdriver.scripts.cim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CimMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20028 **/
public class AddCostIntegrationManagerCIMtoCostingModulelist extends UcqcHelper {
	static CimMap CimMap;
	private static String BackgroundColorCosting = "rgba(0, 86, 26, 1)";
	private static String FontColorCim="rgba(255, 255, 255, 1)";
	private static GeneralElementsMap generalElement;
	static GeneralElementsMap tab;
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("AddCostIntegrationManagerCIMtoCostingModulelist", "webdriver.scripts.cim",
				"AddCostIntegrationManagerCIMtoCostingModulelist");
		try {
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			CimMap = BuildMap.getInstance(driver, CimMap.class);
			tab = BuildMap.getInstance(driver, GeneralElementsMap.class);
			Login.loginUser("CostAnalyst1");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateCimInCostingDropdown_20028() throws Throwable {
		try {
			tab.getCostingTab().click();
			assertElementIsDisplayed(tab.getCostingDataMaintenanceSubTab());
			validateBackgroundColor(BackgroundColorCosting, generalElement.getlandingPageBubbleCostingBgColor());
			String cimFontColor=getFontColor(CimMap.getcimQuickLink());
			assertEquals(cimFontColor,FontColorCim);
			keyboardNavig(3);////tab TO CIM dropdown
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayed(CimMap.getcimHeader());
			assertElementIsDisplayed(CimMap.getcimDockItem());
			keyboardNavig(20);//tab TO CLOSE ICON OF CIM DOCK ITEM
			ExtentReport.logPass("PASS", "test01ValidateCimInCostingDropdown_20028");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCimInCostingDropdown_20028", driver, e);
			fail(e.getMessage());
		}
	}

	

	@Test
	public void test02ValidateKeyboardNavigation_20029() throws Throwable {
		try {
			tab.getCostingTab().click();
			tab.getcimSubTab().click();
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayed(CimMap.getcimHeader());
			assertElementIsDisplayed(CimMap.getcimDockItem());
			doClosePageOnLowerBar("Cost Integration Manager (CIM)");
			ExtentReport.logPass("PASS", "test02ValidateKeyboardNavigation_20029");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateKeyboardNavigation_20029", driver, e);
			fail(e.getMessage());
		} 
		
	}

	@AfterClass
	public static void endtest() throws Exception {
	
		ExtentReport.report.flush();

	}
}
