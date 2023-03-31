package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	static String deptGroup="PATCARE";
	static String directory="Templates";
	static String subDirectory="Costing";
	@BeforeClass
	public static void setupScript() throws InterruptedException, Throwable {
		ExtentReport.reportCreate("SearchTheReportLibrary", "webdriver.scripts.reporting", "SearchTheReportLibrary");

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
			Actions act=new Actions(driver);
			driverDelay(1000);
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='PRIVATE PAY']//parent::td")));
			driverDelay(1000);
			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			executor.executeScript("arguments[0].click();", reportMap.reportLibraryPageEntityOkButton());
			driverDelay(2000);
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
			action.moveToElement(reportMap.reportLibraryPageDeptGrp()).click().build().perform();
//						doClick(reportMap.reportLibraryPageDeptGrp());
//						driverDelay(1000);
//			doClick(reportMap.reportLibraryPageEntitySelectDropdown());
//			ContractModelsHelper.ChooseOptionFromSelectDropdown(reportMap.reportLibraryPageEntitySelectDropdown(),1);
//			ContractModelsHelper.keyInValues(reportMap.reportLibraryPageEntitySearch(), deptGroup);
			doClick("//div[text()='PATCARE  PATCARE']");
			driverDelay(1000);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			doClick(reportMap.reportLibraryPageEntitySaveAsButton());
			
			ContractModelsHelper.keyInValues(driver.findElement(By.xpath("//input[@class='gwt-TextBox']")), "CM1 Cost");
			doClick(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(400);
			doClick(reportMap.reportLibraryPageEntityOkButton());
			doClick(reportMap.reportLibraryPageEntityRunButton());
			driverDelay(400);
		
			for(int i=0;i<=5;i++) {
				doClick(reportMap.reportLibraryPageEntityRefreshButton());
				if(driver.findElement(By.xpath("//span[text()='CM1 Cost']//following::div[5]")).getText().equals("COMPLETED")) {
					break;
				}
			}
			ExtentReport.logPass("PASS", "test02OpenCostingReport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e);
			fail(e.getMessage());
		}
	}
}
