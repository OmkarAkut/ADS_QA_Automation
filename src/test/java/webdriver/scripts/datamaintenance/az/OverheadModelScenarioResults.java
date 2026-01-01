package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.assertTrue;
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
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OverheadModelScenarioResults extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozOverheadModelScenarioResults = "Overhead Model Scenario Results";
	public static DialogsMap dialog;
	static String[] overheadModelScenarioResultFilter= {"Overhead Model Scenario Name","Is","Equal To","* TB MH FY05 Overhead"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("OverheadModdeScenarioResults", "webdriver.scripts.datamaintenance.maintaindata",
				"OverheadModdeScenarioResults");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozOverheadModelScenarioResults);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateViewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(overheadModelScenarioResultFilter);
			doClick(DataMaintenanceMap.getazViewBtn());
			doClick(DataMaintenanceMap.gethideButton());
			assertElementIsDisplayed(DataMaintenanceMap.getshowButton());
			doClick(DataMaintenanceMap.gethideButton());
			assertElementIsDisplayed(DataMaintenanceMap.getshowButton2());
			doClick(DataMaintenanceMap.getshowButton());
			doClick(DataMaintenanceMap.getshowButton());
			
			ExtentReport.logPass("PASS", "test01ValidateViewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateViewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateReceivingSummary() throws Throwable {
		try {
			doClickButtons("Overhead Model Scenario Information", "Select");
			waitForFormDialog("Add Allocation Statistic Assignment");
			driverDelay();
			selectFormItem("150,  2010 NURSING ADMIN, *TOTEXP Total Expense", "");
			clickCheckboxByName("costCompSending");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getresultsStoredDrp(), "Apr 2004 to Mar 2005");
			doClickButton("Apply Selections");
			if(driver.findElements(By.xpath("//div[contains(@class,'costDetailsGrid ')]//table//td[1]/div")).size()>0) {
				assertTrue(printout);
			}else {fail();}
			ExtentReport.logPass("PASS", "test02ValidateReceivingSummary");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateReceivingSummary", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSendingSummary() throws Throwable {
		try {
			doClickButton("Receiving Summary");
			clickCheckboxByName("receivingEntity");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadEntitycode(), "150 Marina Medical Center");
			clickCheckboxByName("receivingDeptGrp");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadDept(), "2010 NURSING ADMIN");
			clickCheckboxByName("receivingGlAcnt");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadReceivingGLEntity(), "*TOTEXP Total Expense");
			clickCheckboxByName("costCompReceiving");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getoverheadResult(), "Apr 2004 to Mar 2005");
			doClickButton("Apply Selections");
			if(driver.findElements(By.xpath("//div[contains(@class,'costDetailsGrid ')]//table//td[1]/div")).size()>0) {
				assertTrue(printout);
			}else {fail();}
			doClickButton("Close");
			ExtentReport.logPass("PASS", "test03ValidateSendingSummary");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSendingSummary", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
