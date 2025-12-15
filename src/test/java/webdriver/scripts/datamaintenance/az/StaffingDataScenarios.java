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
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffingDataScenarios extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozStaffingDataScenario = "Staffing Data Scenarios";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String fiscalYear="**FY16";
	static String[] staffingDataSccerioFilter= {"Name","Is","Equal To",name};
	static String entity="150 Marina Medical Center";
	static String department="027070 PATHOLOGY LAB";
	static String[] jobCodes= {"000111  RN Level I"};
	static String[] payTypes= {"PAYTYPE1  PAYTYPE1-REGULAR-ACCYES-OVERRIDEEXPENSE3"};
	static String hrsExpenses="Hours";
	static String[] entityCode=entity.split(" ");
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("StatisticDataScenarios", "webdriver.scripts.datamaintenance.maintaindata",
				"StatisticDataScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozStaffingDataScenario);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Staffing Data Scenario");
			clickCheckboxByName("timePeriodName");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getstaffingFiscalYearDrp(), fiscalYear);
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(staffingDataSccerioFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddStaffingData() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			addStaffingData();
			doClick("((//div[text()='Job Codes']//following::a[contains(@class,'x-btn')]//span[text()='Select']))[1]");
			ContractModelsHelper.selectMultipleColumnsToDisplay(jobCodes);
			doClickButtons("Job Codes", "Apply");
			doClick("((//div[text()='Job Codes']//following::a[contains(@class,'x-btn')]//span[text()='Select']))[2]");
			ContractModelsHelper.selectMultipleColumnsToDisplay(payTypes);
			doClickButtons("Pay Types", "Apply");
			clickCheckboxByName("staffingType");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.gethoursExpensesDrp(), hrsExpenses);
			doClickButtons("Add Staffing Data", "Add");
			addStaffingDataPoints();
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02AddStaffingData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddStaffingData", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteStaffingDataScenario() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteStatisticDataScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteStatisticDataScenario", driver, e);
			fail(e.getMessage());
		}
	}
	public void addStaffingData() throws Throwable {
		doClickButtons("Staffing Data", "Add");
		waitForFormDialog("Add Staffing Data");
		doClickButtons("Entity", "Select");
		keyInInputByName("carrierfield", entityCode[0], "Add Entity");
		selectFormItem(entity, "");
		doClick("(//span[text()='Department']//following::div[contains(@class,'x-btn')]//span[text()='Select'])[2]");
		waitForFormDialog("Add Department");
		selectFormItem(department, "");
	
	}
	public void addStaffingDataPoints() throws Throwable, Throwable {
		keyInInputWithDoubleClick(driver.findElement(By.xpath("((//div[text()='Staffing Data'])[1]//following::div//table[1]//td[2]/div)[2]")), generateRandomNumber());
		driver.findElement(By.xpath("(//div[text()='Staffing Data'])[1]//following::div//table[1]//td[1]/div")).click();
		doClickButtons("Staffing Data", "Undo");
		assertElementText(driver.findElement(By.xpath("((//div[text()='Staffing Data'])[1]//following::div//table[1]//td[2]/div)[2]")), " ", printout);
		for(int i=2;i<driver.findElements(By.xpath("(//div[text()='Staffing Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div")).size();i++) {
			keyInInputWithDoubleClick(driver.findElement(By.xpath("((//div[text()='Staffing Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div)["+i+"]")),generateRandomNumber());
		}
		String total=driver.findElement(By.xpath("(//div[text()='Staffing Data'])[1]//following::div//table[1]//td[1]/div")).getText();
		int tot=Integer.parseInt(total);
		if(tot>0) {
			assertTrue(printout);
		}
		else {fail();}
		doClickButtons("Staffing Data", "Save Data");
		//Delete
		for(WebElement data:DataMaintenanceMap.getstatisticDataGrid()) {
			try {
				if(DataMaintenanceMap.getstatisticDataGrid().size()>0) {
					data.click();
					doClickButtons("Staffing Data", "Delete");
					doClickButtons("Delete", "Delete");
					
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
