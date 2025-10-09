package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Customer Issue Test for  ADS-6915. **/
public class RunFlexStandardHCPCReports extends GoHelper{
	static String directory="Templates";
	static String subDirectory="General Reports";
	static String report="HCPCS Modifiers";
	private static ReportingMap reportMap;
	int retry=0;
	int refreshTime=20;
	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("RunFlexStandardHCPCReports", "webdriver.scripts.reporting", "RunFlexStandardHCPCReports");

		try {
			reportMap = BuildMap.getInstance(driver, ReportingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Report Library");
			waitForDisplayedSpinnerToEnd();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01OpenHCPCSModifiers_ADS_6915() throws Throwable {
		try {
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			doClick("//div[contains(text(),'" + directory
					+ "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory
					+ "')]");

			//			doClick("//a[text()='"+report+"']");
			doClick("//a[@title='"+report+"']");
			waitForElementToBeVisible(reportMap.reportLibraryPageEntityRunButton());
			doClick(reportMap.reportLibraryPageEntityRunButton());
			waitForElementToBeVisible(reportMap.reportLibraryPageEntityRefreshButton());
			driverDelay(1500);
			String reportTime=driver.findElement(By.xpath("//div[@class='GJT013UBNJB']")).getText().replaceFirst("^\\s+", "");;
			while(retry<=refreshTime) {
				doClick(reportMap.reportLibraryPageEntityRefreshButton());
				
				try {
					if(driver.findElement(By.xpath("//div[text()='"+reportTime+"']//following::div[4]")).getText().contains("COMPLETED")){
						doDoubleClick("//div[text()='"+reportTime+"']//following::div[4]");
						assertElementIsDisplayedWithXpath("//span[contains(text(),'HCPCS Modifiers')]");
						break;
					}
				} catch (Exception e) {
					doClick(reportMap.reportLibraryPageEntityRefreshButton());
					retry++;
					
				}
				if (retry == refreshTime) {
					System.out.println("❌ Status did not become 'Completed' after retries.");
					fail();
				}
			}
			doClosePageOnLowerBar("Report Library");
			ExtentReport.logPass("PASS", "test01OpenFlexibleReports");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenFlexibleReports", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		driver.switchTo().defaultContent();
		ExtentReport.report.flush();

	}
}
