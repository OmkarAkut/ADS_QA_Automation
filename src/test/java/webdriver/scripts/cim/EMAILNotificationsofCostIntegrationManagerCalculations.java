package webdriver.scripts.cim;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20443**/
public class EMAILNotificationsofCostIntegrationManagerCalculations extends CimHelper{
	private static CimMap cimMap;
	 static CostingMap selectColumn;
	 private static DialogsMap dialog;
	 private static String customRole="Auto Test CIM User";
	 static String user="automationtestuser";
	 private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	 private static String cimScenarioCreate = "CIM" + currentDateTime;
	 static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	 static String[] filterUser = { "ID", "Is", "Equal To", "automationtestuser" };
	 static String calcType="Activity Volume Calc Scenario: **fz ASESC-2624 - Test";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("EMAILNotificationsofCostIntegrationManagerCalculations", "webdriver.scripts.cim",
				"EMAILNotificationsofCostIntegrationManagerCalculations");
		try {
			selectColumn = BuildMap.getInstance(driver, CostingMap.class);
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTestUser");
//			goToPage("users");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01User_With_CostAnalystRole_20443() throws Throwable {
		try {
			USERSECURITYStandardCustomRoles roles=new USERSECURITYStandardCustomRoles();
			roles.	openUserForedit(cimMap.getusersFilterBtn(),filterUser,cimMap.getusersEditBtn());
			doClick(cimMap.getusersRoleSelectBtn());
			selectRole("add", "Cost Analyst");
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			doClick("//div[contains(@id,'dynamicwindow')]//div[contains(text(),'"+user+"')]//following::span[text()='Save & Close']");
			doClick(cimMap.getLogoutBtn());
			Login.loginUser("AutomationTestUser");
			goToPage("Cost Integration Manager");
			
			ExtentReport.logPass("PASS", "test01User_With_CostAnalystRole_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01User_With_CostAnalystRole_20443", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02CreateCIM_With_Validate_Email_20443() throws Throwable {
		try {
			 createCIM(cimScenarioCreate,calcType);
			 doFilterCreateCIM(filterCim);
			 validateCalcStatus("COMPLETED", cimScenarioCreate);
			 driver.navigate().to("https://www.gmail.com");
			 driver.findElement(By.xpath("//input[@type='email']")).sendKeys("autoautomationtestuser");
			 doClick("//span[text()='Next']");
			 doClick("//input[@type='password']");
			 driver.findElement(By.xpath("//input[@type='password']")).sendKeys("P@ssword@123");
			 doClick("//span[text()='Next']");
			ExtentReport.logPass("PASS", "test02CreateCIM_With_Validate_Email_20443");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateCIM_With_Validate_Email_20443", driver, e);
			fail(e.getMessage());
		}
	}
	public void selectRole(String role,String name) throws Exception {
		if(role=="add") {
			doClick("//div[contains(text(),'Available')]//following::li[text()='"+name+"']");
			doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
		}
		if(role=="remove") {
			doClick("//div[contains(text(),'Selected')]//following::li[text()='"+name+"']");
			doClick("//div[contains(text(),'Roles')]//following::span[text()='Remove']");
	}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
	
		ExtentReport.report.flush();

	}
}
