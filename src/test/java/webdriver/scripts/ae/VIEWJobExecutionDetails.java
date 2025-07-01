package webdriver.scripts.ae;

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AeHelper;
import webdriver.maps.AeMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20251 **/
public class VIEWJobExecutionDetails extends AeHelper{
	 static ModelLibraryMap modelMap;
	 private static AeMap aeMap;
	 static List<String> getHeaderList = new ArrayList<>();
	 static String originalHandle = driver.getWindowHandle();
	 List<String> expHeaderList = Arrays.asList("Task","Status","Last Execution","Next Execution");
	 @BeforeClass
		public static void setupScript() throws Exception, Throwable {

			ExtentReport.reportCreate("VIEWJobExecutionDetails", "webdriver.scripts.ae",
					"VIEWJobExecutionDetails");
			try {
				aeMap=BuildMap.getInstance(driver, AeMap.class);
				Login.loginUser("AutomationTesterAdmin");
				goToPage("Automation Engine Job Manager");
				switchToNewTab(driver);
				waitForElementToBeVisible(aeMap.getexecDetailsBtn());
				ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test01Validate_ExecutionDetails_Popup_20251() throws Throwable {
			try {
				
				
				doClick(aeMap.getexecDetailsBtn());
				waitForElementToBeVisible(aeMap.getexecDetailsPopUp());
				for(WebElement header: aeMap.getexecDetailsHeader()) {
					getHeaderList.add(header.getText());
				}
				assertListOfStringsContainsExpectedStrings(getHeaderList, expHeaderList);
				
				ExtentReport.logPass("PASS", "test01Validate_ExecutionDetails_Popup_20251");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01Validate_ExecutionDetails_Popup_20251", driver, e);
				fail(e.getMessage());
			}
		}
	 @Test
		public void test02Validate_CloseBtn_20251() throws Throwable {
			try {
				doClick(aeMap.getcloseBtn());
				doClick(aeMap.getexecDetailsBtn());
				waitForElementToBeVisible(aeMap.getexecDetailsPopUp());
				doClick(aeMap.getcloseIcon());
				closeNewTabAndReturn(driver, originalHandle);
				ExtentReport.logPass("PASS", "test02Validate_CloseBtn_20251");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02Validate_CloseBtn_20251", driver, e);
				fail(e.getMessage());
			}
		}
	 @AfterClass
		public static void endtest() throws Exception {
			ExtentReport.report.flush();

		}
}
