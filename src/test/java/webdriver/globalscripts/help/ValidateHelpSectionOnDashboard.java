package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

public class ValidateHelpSectionOnDashboard extends PageTestHelper{
	private static CostingMap costingMap;
	private static GeneralElementsMap generalElement;
	String firsthandle;
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("UcqcSelectColumnsDialogVerifyOnlineHelpAds1113", "webdriver.globalscripts.help",
				"UcqcSelectColumnsDialogVerifyOnlineHelpAds1113");

		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			System.out.println("TEST CLASS: " + UcqcSelectColumnsDialogVerifyOnlineHelpAds1113.class.getSimpleName());
			evolveLoginStaticUser(Users.AutomationTester1);
		
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test01AssertHelpSectionOnDashboard() throws Throwable {
	      try {
	    	   firsthandle = webSwitchToNewWindow(generalElement.getGlobalHeaderButtonHelp(),printout);
			assertTextIsDisplayed("TOC");
			doClick(CostingMap.getHelpModelLibraryLink());
			assertTextIsDisplayed("Model Library");
			doClick(CostingMap.getHelpIndexLink());
			assertElementIsDisplayed(CostingMap.getHelpSearchIndex());
			doClick(CostingMap.getHelpGlossaryTab());
			assertElementIsDisplayed(CostingMap.getHelpGlossarySearch());
			
			ExtentReport.logPass("PASS", "test01AssertHelpSectionOnDashboard");

	      } catch (Exception|AssertionError e) {
	      	ExtentReport.logFail("FAIL", "test01AssertHelpSectionOnDashboard", driver, e);
	        fail("Calculation Status Page Test Failed");
	      }

	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
