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
/** Regression test case ADS-20029 **/
public class AddCostIntegrationManagerCIMtoHomePageCostingsection extends UcqcHelper {
	static CimMap CimMap;
	private static String BackgroundColorCosting = "rgba(0, 86, 26, 1)";
	private static String FontColorCim="rgba(255, 255, 255, 1)";
	private static GeneralElementsMap generalElement;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("AddCostIntegrationManagerCIMtoHomePageCostingsection", "webdriver.scripts.cim",
				"AddCostIntegrationManagerCIMtoHomePageCostingsection");
		try {
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			CimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateCimQuickLink_20029() throws Throwable {
		try {
			assertElementIsDisplayed(CimMap.getcimQuickLink());
			validateBackgroundColor(BackgroundColorCosting, generalElement.getlandingPageBubbleCostingBgColor());
			String cimFontColor=getFontColor(CimMap.getcimQuickLink());
			assertEquals(cimFontColor,FontColorCim);
			ExtentReport.logPass("PASS", "test01ValidateCimQuickLink");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateCimQuickLink", driver, e);
			fail(e.getMessage());
		}
	}

	

	@Test
	public void test02ValidateKeyboardNavigation_20029() throws Throwable {
		try {
			keyboardNavig(21);//tab TO CIM hyperlink in the home page
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayed(CimMap.getcimHeader());
			assertElementIsDisplayed(CimMap.getcimDockItem());
			keyboardNavig(11);//tab TO CLOSE ICON OF CIM DOCK ITEM
			ExtentReport.logPass("PASS", "test02VerifyCimScreenIsVisible_20029");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyCimScreenIsVisible_20029", driver, e);
			fail(e.getMessage());
		} 
		
	}
	
	@Test
	public void test03VerifyCimScreenIsVisible_20029() throws Throwable {
		try {
			doClick(CimMap.getcimQuickLink());
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayed(CimMap.getcimHeader());
			assertElementIsDisplayed(CimMap.getcimDockItem());
			doClosePageOnLowerBar("Cost Integration Manager (CIM)");
			ExtentReport.logPass("PASS", "test02VerifyCimScreenIsVisible_20029");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyCimScreenIsVisible_20029", driver, e);
			fail(e.getMessage());
		} 
		
	}

	@AfterClass
	public static void endtest() throws Exception {
	
		ExtentReport.report.flush();

	}
}
