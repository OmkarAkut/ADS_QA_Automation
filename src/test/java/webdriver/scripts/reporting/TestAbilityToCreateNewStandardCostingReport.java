package webdriver.scripts.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test for Test Ticket ADS-5654. **/
public class TestAbilityToCreateNewStandardCostingReport extends GoHelper{
	private static ReportingMap reportMap;
	static String reportName = "Cost per RVU";
	static String entity="150 PRIVATE PAY";
	static String deptHierarchy="Marina Department Hierarchy";
	static String deptGroup="TBDEPTGRP  TB Dept Group";
	static String directory="Templates";
	static String subDirectory="Costing";
	static String newReportName = "CM1 Cost";
	static int refreshTime = 10;

	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {

		ExtentReport.reportCreate("TestAbilityToCreateNewStandardCostingReport", "webdriver.scripts.reporting", "TestAbilityToCreateNewStandardCostingReport");

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
	public void test01SearchReport() throws Throwable {
		try {
			doClick(reportMap.getReportLibraryPageFormFieldSearch());
			doClick("//div[contains(text(),'" + directory + "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory + "')]");
			ContractModelsHelper.keyInValues(reportMap.getReportLibraryPageFormFieldSearch(), reportName);
			ExtentReport.logPass("PASS", "test01SearchReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SearchReport", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02OpenCostingReport() throws Throwable {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;

			doClick("//*[text()='"+reportName+"']");
			doClick(reportMap.reportLibraryPageEntityRange());
			//			Actions act=new Actions(driver);
			driverDelay(1000);
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='PRIVATE PAY']//parent::td")));
			doClick("//div[text()='PRIVATE PAY']");
			driverDelay(1000);
			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			executor.executeScript("arguments[0].click();", reportMap.reportLibraryPageEntityOkButton());
			//			driverDelay(2000);
			//			doClick(reportMap.reportLibraryPageEntityOkButton());
			//			driverDelay(10000);
			//			doClick(reportMap.reportLibraryPageEntitySaveAsButton());
			//			doClick(reportMap.reportLibraryPageDeptHierarchy());
			//			driverDelay(5000);
			//			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Marina Department Hierarchy']//parent::td")));
			//			driverDelay(600);
			//			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
			//			driverDelay(600);
			//			doClick(reportMap.reportLibraryPageEntityOkButton());			
			//			driverDelay(500);
			Actions action=new Actions(driver);
			driverDelay(500);
			action.moveToElement(reportMap.reportLibraryPageDeptGrp()).click().build().perform();
			//						doClick(reportMap.reportLibraryPageDeptGrp());
			driverDelay(1000);
			//			doClick(reportMap.reportLibraryPageEntitySelectDropdown());
			//			ContractModelsHelper.ChooseOptionFromSelectDropdown(reportMap.reportLibraryPageEntitySelectDropdown(),1);
			//			ContractModelsHelper.keyInValues(reportMap.reportLibraryPageEntitySearch(), deptGroup);
			//			doClick("//div[text()='PATCARE  PATCARE']");
			doClick("//div[text()='"+deptGroup+"']");
			doClick("//div[text()='"+deptGroup+"']");
			driverDelay(1000);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			doClick(reportMap.reportLibraryPageEntitySaveAsButton());
			driverDelay(1000);
			ContractModelsHelper.keyInValues(driver.findElement(By.xpath("//input[@class='gwt-TextBox']")), newReportName);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(400);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			doClick(reportMap.reportLibraryPageEntityRunButton());
			driverDelay(400);
					
						for(int i=0;i<=5;i++) {
							doClick(reportMap.reportLibraryPageEntityRefreshButton());
							driverDelay();
							if(driver.findElement(By.xpath("(//span[text()='CM1 Cost']//following::div[5]/a)[1]")).getText().equals("COMPLETED")) {
								ExtentReport.logPass("PASS", "test02OpenCostingReport");
								break;
							}
							else if(driver.findElement(By.xpath("(//span[text()='CM1 Cost']//following::div[5]/a)[1]")).getText().equals("FAILED")) {
								ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver);
								break;
							}
							else if(driver.findElement(By.xpath("(//span[text()='CM1 Cost']//following::div[5]/a)[1]")).getText().equals("PENDING")) {
								continue;
							}
							
						}


						assertElementTextWithXpath("(//span[text()='CM1 Cost']//following::div[5]/a)[1]","COMPLETED" , printout);
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Report Library");
		ExtentReport.report.flush();
	}
}
