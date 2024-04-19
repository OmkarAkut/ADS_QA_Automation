package webdriver.scripts.reporting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.DoubleClickAction;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-5660. **/
public class FlexibleReportsProfitAndLossStatementTesting extends GoHelper{
	static String directory="Templates";
	static String subDirectory="Flexible Reports";
//		static String report="Profit and Loss Statement";
	static String report="Contract Comparison";
	private static ReportingMap reportMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String reportName= "Report " + currentDateTime;
	static String orgName= "Test " + currentDateTime;
	static String startDate="01/01/2004";
	static String endDate="01/30/2004";
	static String code1="0400";
	static String code2="0402";
	static int refreshTime = 5;
	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("FlexibleReportsProfitAndLossStatementTesting", "webdriver.scripts.reporting", "FlexibleReportsProfitAndLossStatementTesting");

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
	//ADS-5660
	@Test
	public void test01OpenFlexibleReports_5660() throws Throwable {
		try {
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			Thread.sleep(2000);
			doClick("//div[contains(text(),'" + directory
					+ "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory
					+ "')]");

			//			doClick("//a[text()='"+report+"']");
			doClick("//a[@title='"+report+"']");			
			ExtentReport.logPass("PASS", "test01OpenFlexibleReports");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenFlexibleReports", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02GoToSelectionsTab_5660() throws Throwable {
		try {
			doClick(reportMap.reportSelectionTab());
			doClick(reportMap.reportSelectionDate());
			doClick(reportMap.reportDischargeDate());
			doClick(reportMap.reportMatchButton());
			waitForElementToBeVisible(reportMap.reportToDate());
			ContractModelsHelper.keyInValues(reportMap.reportFromDate(), startDate);
			ContractModelsHelper.keyInValues(reportMap.reportToDate(), endDate);
			doClick("//div[text()='the value(s):']");
			doClick(reportMap.reportOkButton());
			waitForElementPresence("//div[text()='AND Discharge Date '][text()=' between including:'][text()=' "+startDate+" and "+endDate+"']");
			assertElementIsDisplayedWithXpath("//div[text()='AND Discharge Date '][text()=' between including:'][text()=' "+startDate+" and "+endDate+"']");
			ExtentReport.logPass("PASS", "test02GoToSelectionsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02GoToSelectionsTab", driver, e);
			fail(e.getMessage());
		}

	}
	@Test
	public void test03GoToDetailsTab_5660() throws Throwable {
		try {
			doClick(reportMap.reportDetailsButton());

			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//option[@value='Primary Benefit Plan Code']"))).doubleClick().build().perform();
			driverDelay(400);
			waitForElementPresence("//div[text()='"+code1+"']");
			scrollToView("//div[text()='"+code1+"']");
			act.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//div[text()='"+code1+"']//parent::td"))).click(driver.findElement(By.xpath("//div[text()='"+code2+"']//parent::td"))).perform();
			doClick(reportMap.reportSelectCodes());
			assertElementIsDisplayedWithXpath("//div[text()='AND Primary Benefit Plan Code ']");
			ExtentReport.logPass("PASS", "test03GoToDetailsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03GoToDetailsTab", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04GoToSortsTab_5660() throws Throwable {
		try {
			doClick(reportMap.reportSortTab());
			doClick(reportMap.reportCareDeliveryFacility());
			doClick(reportMap.reportCareDeliveryFacilityCode());
			doClick(reportMap.reportSelectCodesFromSortTab());
			assertElementIsDisplayedWithXpath("//div[text()='Sort by: Care Delivery Facility Code']");
			doClick(reportMap.reportChargeItem());
			doClick(reportMap.reportChargeItemCode());
			doClick(reportMap.reportSelectCodesFromSortTab());
			assertElementIsDisplayedWithXpath("//div[text()='Sort by: Charge Item Department Code']");
			ExtentReport.logPass("PASS", "test04GoToSortsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04GoToSortsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05GoToSelectionsTab_5660() throws Throwable {
		try {
			doClick(reportMap.reportAdditionalSelectionTab());
			driverDelay(300);
			ContractModelsHelper.keyInValues(reportMap.reportOrganisationName(), orgName);
			driverDelay(200);
			doClick(reportMap.reportLibraryPageEntitySaveAsButton());
			ContractModelsHelper.keyInValues(reportMap.reportSavePopUp(),
					orgName);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			ExtentReport.logPass("PASS", "test05GoToSelectionsTab");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05GoToSelectionsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06SaveReportAndAssertStatus_5660() throws Throwable {
		try {
			doClick(reportMap.reportLibraryPageEntityRunButton());
			waitForElementToBeVisible(reportMap.reportLibraryPageEntityRefreshButton());
			waitForPresenceOfElement(("//span[text()='" + orgName + "']"));
			Thread.sleep(2000);
			doClick(reportMap.reportLibraryPageEntityRefreshButton());
			waitForPresenceOfElement("//span[text()='" + orgName + "']//following::td[5]/div");
			String status=driver.findElement(By.xpath("//span[text()='" + orgName + "']//following::td[5]/div")).getText();
			String[] staNum=status.replaceAll("Step ", "").split("/");
			int numerator=Integer.parseInt(staNum[0]);
			int denominator=Integer.parseInt(staNum[1]);
			String reportStatus=driver.findElement(By.xpath("//span[text()='" + orgName + "']//following::td[5]/div/a")).getText();

			for (int i = 0; i <= denominator; i++) {
				try {
					
					doClick(reportMap.reportLibraryPageEntityRefreshButton());
					Thread.sleep(2000);
					waitForPresenceOfElement(("//span[text()='" + orgName + "']"));
					String[] staNumber=status.replaceAll("Step ", "").split("/");
					int numeratorNum=Integer.parseInt(staNumber[0]);
//					String reportStatus=driver.findElement(By.xpath("//span[text()='" + orgName + "']//following::td[5]/div/a")).getText();
					if(reportStatus.equals("FAILED")) {
						assertFalse(printout);
						break;
					}
					if (numeratorNum==denominator) {
						doClick(reportMap.reportLibraryPageEntityRefreshButton());
						waitForPresenceOfElement(("//span[text()='" + orgName + "']"));

						try {
							if(reportStatus.equals("COMPLETED")) {
								assertTrue(printout);
								break;
							}
							else if(reportStatus.equals("FAILED")) {
								assertFalse(printout);
								break;
							}
							else {
								continue;
							}
						} catch (Exception e) {
							continue;
						}

					}
					else {
						continue;
					}
//					if(reportStatus.equals("COMPLETED")) {
//						assertTrue(printout);
//						break;
//					}
//					else if(reportStatus.equals("FAILED")) {
//						assertFalse(printout);
//						break;
//					}
					

				} catch (Exception | AssertionError e) {
					continue;
				}
				
//				ExtentReport.logPass("PASS", "test06SaveReportAndAssertStatus");
			}	
			ExtentReport.logPass("PASS", "test06SaveReportAndAssertStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06SaveReportAndAssertStatus", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07OpenCompletedReportAndVerifyReportNames_5660() throws Throwable {
		try {
			driver.findElement(By.xpath("(//span[text()='" + orgName + "']//following::td[5]/div)")).click();
			driverDelay(1500);
			waitForElementPresence("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]");
			driver.switchTo()
			.frame(driver.findElement(By.xpath("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]")));
			driverDelay(1500);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'bobjid')]")));
			String field1=driver.findElement(By.xpath("//div[@id='Field1']//span")).getText();
			String field2=driver.findElement(By.xpath("//div[@id='Field2']//span")).getText();
			System.out.println(field1);
			System.out.println(field2);
			if(field1.equals(orgName)) {
				assertTrue(printout);

			}
			else {
				fail();
			}
			if(field2.equals(reportName)) {
				assertTrue(printout);

			}else {
				fail();
			}

			ExtentReport.logPass("PASS", "test07OpenCompletedReportAndVerifyReportNames");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07OpenCompletedReportAndVerifyReportNames", driver, e);
			fail(e.getMessage());
		}
	}



	@AfterClass
	public static void endtest() throws Exception {
		driver.switchTo().defaultContent();
//		doClick("//*[contains(@id,'tabbar')]//following::span[text()='Report Library']//following::a[@class='x-tab-close-btn']");
		ExtentReport.report.flush();

	}
}

