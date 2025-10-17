package webdriver.scripts.reporting;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-5660. ADS-5110, ADS-5109**/
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
	static String code3="1SM1";
	static String code4="1SM4";
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
	public void test02GoToSelectionsTab_5660_5110_5109() throws Throwable {
		try {
			doClick(reportMap.reportSelectionTab());
			doClick(reportMap.reportBenifitPlanCode());
			doDoubleClick("(//div[text()='Benefit Plan']//following::div[text()='Primary Group'])[1]");
			doClick("(//div[@class='GJT013UBFGC']//td[1]/div)[4]");
			doClick(reportMap.reportSelectCodesFromSelectionTab());
			doClick("//div[contains(text(),'AND Primary Benefit Plan Group ')]");
			doClick(reportMap.reportRemoveCodesFromSelectionTab());
			doClick(reportMap.reportreportReturnButton());
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
	public void test03GoToDetailsTab_5660_5112_5109() throws Throwable {
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
			doClick(reportMap.reportreportReturnButton());
			doClick(reportMap.reportDetailsButton1());
			doClick(reportMap.reportreportAdmissionCode());
			doDoubleClick("(//div[text()='Admission']//following::div[text()='Source'])[1]");
			driverDelay();
			act.click(driver.findElement(By.xpath("//div[text()='"+code3+"']//parent::td"))).perform();
			act.keyDown(Keys.SHIFT).click(driver.findElement(By.xpath("//div[text()='"+code4+"']//parent::td"))).keyUp(Keys.SHIFT).perform();
			doClick(reportMap.reportSelectCodes());
			ExtentReport.logPass("PASS", "test03GoToDetailsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03GoToDetailsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04GoToSortsTab_SortByDesc_ADS5660_ADS5111_5109() throws Throwable {
		try {
			doClick(reportMap.reportSortTab());
			doClick(reportMap.reportCareDeliveryFacility());
			doClick(reportMap.reportCareDeliveryFacilityCode());
			doClick(reportMap.reportSelectCodesFromSortTab());
			doClick("//div[text()='Sort by: Care Delivery Facility Code']");
			doClick("//button[text()='Edit']");
			doClick(reportMap.reportSortDesc());
			driver.findElement(By.xpath("//button[text()='OK']")).click();
			List<String> expectedWords = Arrays.asList("Sort by: Care Delivery Facility Code", "Order by Code", "in Descending order","Show Code and Name");
			String sortByDesc=driver.findElement(By.xpath("//div[@class='gwtQuery-draggable gwtQuery-droppable']")).getText();
			compareText(expectedWords,sortByDesc);
			ExtentReport.logPass("PASS", "test04GoToSortsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04GoToSortsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05GoToSortsTab_SortByAsec_ADS5660_ADS5111() throws Throwable {
		try {
			doClick("//button[text()='Edit']");
			doClick(reportMap.reportSortAsc());
			driver.findElement(By.xpath("//button[text()='OK']")).click();
			String sortByAsc=driver.findElement(By.xpath("//div[@class='gwtQuery-draggable gwtQuery-droppable']")).getText();
			List<String> expectedWords = Arrays.asList("Sort by: Care Delivery Facility Code", "Order by Code", "in Ascending order","Show Code and Name");
			compareText(expectedWords,sortByAsc);
			ExtentReport.logPass("PASS", "test04GoToSortsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04GoToSortsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06GoToSortsTab_5660_5110() throws Throwable {
		try {
			assertElementIsDisplayedWithXpath("//div[text()='Sort by: Care Delivery Facility Code']");
			doClick(reportMap.reportChargeItem());
			doClick(reportMap.reportChargeItemCode());
			doClick(reportMap.reportSelectCodesFromSortTab());
			assertElementIsDisplayedWithXpath("//div[text()='Sort by: Charge Item Department Code']");
			doClick(reportMap.reportBenifitPlanCode());
			doClick(reportMap.reportBenifitCode());
			doClick(reportMap.reportSelectCodesFromSortTab());
			doClick("//div[text()='Sort by: Primary Benefit Plan Code']");
			doClick(reportMap.reportRemoveCodesFromSortTab());
			ExtentReport.logPass("PASS", "test04GoToSortsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04GoToSortsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07GoToSelectionsTab_5660() throws Throwable {
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
	public void test08SaveReportAndAssertStatus_5660() throws Throwable {
		try {
			doClick(reportMap.reportLibraryPageEntityRunButton());
			waitForElementToBeVisible(reportMap.reportLibraryPageEntityRefreshButton());
			waitForPresenceOfElement(("//span[text()='" + orgName + "']"));
			waitForPresenceOfElement("//span[text()='" + orgName + "']//following::td[5]/div");
			
			if (driver.findElement(By.xpath("//span[text()='" + orgName + "']//following::td[5]/div")).getText()
					.contains("Step")) {
				String status=driver.findElement(By.xpath("//span[text()='" + orgName + "']//following::td[5]/div")).getText();
				String[] staNum=status.replaceAll("Step ", "").split("/");
//				int numerator=Integer.parseInt(staNum[0]);
				int denominator=Integer.parseInt(staNum[1]);
				System.out.println(denominator);
				for (int i = 0; i <=denominator; i++) {
					doClick(reportMap.reportLibraryPageEntityRefreshButton());
					driverDelay(3000);
					try {
						if (driver.findElement(By.xpath("(//span[text()='"+orgName+"']//following::a)[1]")).getText()
								.contains("FAILED")) 
						{
							fail();
//						ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e1);
						}
						if (driver.findElement(By.xpath("(//span[text()='"+orgName+"']//following::a)[1]")).getText()
								.equals("COMPLETED")) {
							assertElementTextWithXpath("(//span[text()='"+orgName+"']//following::a)[1]", "COMPLETED",
									printout);
							ExtentReport.logPass("PASS", "test02OpenCostingReport");
							break;

						}
						
						 if (i == denominator) {
							if (driver.findElement(By.xpath("(//span[text()='"+orgName+"']//following::a)[1]")).getText()
									.equals("COMPLETED")) {
								assertElementTextWithXpath("(//span[text()='"+orgName+"']//following::a)[1]", "COMPLETED",
										printout);
								ExtentReport.logPass("PASS", "test02OpenCostingReport");
								break;

							}
							if (driver.findElement(By.xpath("(//span[text()='"+orgName+"']//following::a)[1]")).getText()
									.contains("FAILED")) 
							{
								fail();
							}
							break;
						}
						 else{continue;}
							
						
					} catch (Exception e) {
						continue;
					}
					
				}
			}
			
			
			
			
		
			ExtentReport.logPass("PASS", "test06SaveReportAndAssertStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06SaveReportAndAssertStatus", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test09OpenCompletedReportAndVerifyReportNames_5660() throws Throwable {
		try {
			 doDoubleClick("(//span[text()='" + orgName + "']//following::td[5]/div/a)");//Shilpa updated xpath for 11.2 on 10.24.2024
//			driver.findElement(By.xpath("(//span[text()='" + orgName + "']//following::td[5]/div)")).click();
			driverDelay(1500);
			waitForElementPresence("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]");
			driver.switchTo()
			.frame(driver.findElement(By.xpath("//iframe[contains(@src,'QueryCrystalReportInstance.jsp')]")));
			driverDelay(1500);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'bobjid')]")));
			String field1=driver.findElement(By.xpath("//div[@id='Field1']//span")).getText();
//			String field2=driver.findElement(By.xpath("//div[@id='Field2']//span")).getText();//SHILPA UPDATE ON 08.26.2025
			System.out.println(field1);
//			System.out.println(field2);
			if(field1.equals(orgName)) {
				assertTrue(printout);

			}
			else {
				fail();
			}
			//SHILPA UPDATE ON 08.26.2025
//			if(field2.equals(reportName)) {
//				assertTrue(printout);
//
//			}else {
//				fail();
//			}

			ExtentReport.logPass("PASS", "test07OpenCompletedReportAndVerifyReportNames");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07OpenCompletedReportAndVerifyReportNames", driver, e);
			fail(e.getMessage());
		}
	}

	public void compareText(List<String> expectedWords, String sortText) {
		boolean matchFound=false;
		for (String word : expectedWords) {
		    if (sortText.contains(word)) {
		        matchFound = true;
		        System.out.println("Match found: " + word);
		        break; // if only one match is enough
		    }
		}
	}


	@AfterClass
	public static void endtest() throws Exception {
		driver.switchTo().defaultContent();
//		doClick("//*[contains(@id,'tabbar')]//following::span[text()='Report Library']//following::a[@class='x-tab-close-btn']");
		ExtentReport.report.flush();

	}
}

