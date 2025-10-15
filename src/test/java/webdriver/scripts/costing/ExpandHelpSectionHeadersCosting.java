package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
//Customer Issue: Automated Test Script ADS-20780
public class ExpandHelpSectionHeadersCosting extends PageTestHelper{
	String firsthandle;
	private static GeneralElementsMap generalElement;
	private static CostingMap costingMap;
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("ExpandHelpSectionHeadersCosting", "webdriver.globalscripts.help",
				"ExpandHelpSectionHeadersCosting");

		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}
	@Test
	public void test01ExpandCostingHeadersInHelpDoc_20780() throws Throwable {
	      try {
	    	 firsthandle = webSwitchToNewWindow(generalElement.getGlobalHeaderButtonHelp(),printout);
	    	 driver.manage().window().maximize();
			 assertTextIsDisplayed("TOC");
			 doClick(CostingMap.getCostingHelpIndexLink());
			 doClick(CostingMap.getFieldDesc());
			for(int j=1;j<=CostingMap.getCostingDocList().size();j++) {
				doClick("(//a[text()='Field Descriptions']//following::ul[1]/li)["+j+"]");
				if(driver.findElements(By.xpath("(//a[text()='Field Descriptions']//following::ul[1]/li)["+j+"]/ul/li")).size()>0) {
					try {
						for(int k=1;k<=driver.findElements(By.xpath("(//a[text()='Field Descriptions']//following::ul[1]/li)["+j+"]/ul/li")).size();k++) {
							driver.findElement(By.xpath("(//a[text()='Field Descriptions']//following::ul[1]/li)["+j+"]/ul/li["+k+"]")).click();
							driver.switchTo().frame(driver.findElement(By.id("topic")));
							if(CostingMap.getCostingDocDrpDwn().size()==0) {
								assertElementIsDisplayedWithXpath("//body[@id='mc-main-content']");
							}
							try {
								if(CostingMap.getCostingDocDrpDwn().size()>0) {
									for(int i=0;i<CostingMap.getCostingDocDrpDwn().size();i++) {
										doClick(CostingMap.getCostingDocDrpDwn().get(i));
										assertElementIsDisplayedWithXpath("(//div[contains(@class,'dropDownBody')])["+(i+1)+"]");
									}
								}
							} catch (Exception e) {
							}
							driver.switchTo().defaultContent();
						}
					} catch (Exception e) {
						continue;
					}
			}
				driver.findElement(By.xpath("(//a[text()='Field Descriptions']//following::ul[1]/li)["+j+"]//a")).click();
			}
			ExtentReport.logPass("PASS", "test01ExpandCostingHeadersInHelpDoc_20780");

	      } catch (Exception|AssertionError e) {
	    	  ExtentReport.logFail("FAIL", "test01ExpandCostingHeadersInHelpDoc_20780", driver, e);
	        fail();
	      }

	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
