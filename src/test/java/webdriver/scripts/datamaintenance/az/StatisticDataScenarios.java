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
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatisticDataScenarios extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozStatisticDataScenario = "Statistic Data Scenarios";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String fiscalYear="**FY16";
	static String[] statisticDataSccerioFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String[] columns= {"027070  PATHOLOGY LAB","027140  RADIOLOGY DIAGNOSTIC"};
	static String adHocstatisticMaster="Ad Hoc Statistics";
	static String adHocstatistic="1CSM2  Rehabilitation Rev";
	static String activityStatisticMaster="Activity Statistic Master";
	static String activityStatisticOption="Activity Statistics";
	static String activityStatistic="1CSM1  Pediatric Ambulatory Rev";
	static String glStatisticMaster="GL Statistic Master";
	static String glStatsMasterOption="ASESC-2518 GL Stats";
	static String glStatistic="CANCEXP  Cancer Expense";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("StatisticDataScenarios", "webdriver.scripts.datamaintenance.maintaindata",
				"StatisticDataScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozStatisticDataScenario);
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
			keyInInputByName("name", name, "Statistic Data Scenario");
			clickCheckboxByName("timePeriodName");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfiscalYearDrp(), fiscalYear);
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(statisticDataSccerioFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddStatisticDataAdHocStatisticMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			addStatisticData();
			selectStatisticMasterType(adHocstatisticMaster,adHocstatistic);
			ExtentReport.logPass("PASS", "test02AddStatisticData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddStatisticData", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddStatisticDataActivityStatisticMaster() throws Throwable {
		try {
			addStatisticData();
			doClick("//label[text()='"+activityStatisticMaster+"']//preceding::input[1]");
			selectStatisticMasterType(activityStatisticOption,activityStatistic);
			ExtentReport.logPass("PASS", "test03AddStatisticDataActivityStatisticMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddStatisticDataActivityStatisticMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddStatisticDataGLStatisticMaster() throws Throwable {
		try {
			addStatisticData();
			doClick("//label[text()='"+glStatisticMaster+"']//preceding::input[1]");
			selectStatisticMasterType(glStatsMasterOption,glStatistic);
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03AddStatisticDataActivityStatisticMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddStatisticDataActivityStatisticMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteStatisticDataScenario() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteStatisticDataScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteStatisticDataScenario", driver, e);
			fail(e.getMessage());
		}
	}
	public void addStatisticData() throws Throwable {
		doClickButtons("Statistic Data", "Add");
		waitForFormDialog("Add Statistic Data");
		doClickButtons("Entity", "Select");
		selectFormItem(entity, "");
		doClickButtons(entity, "Select");
		ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
		doClickButtons("Department", "Apply");
	}
	public void selectStatisticMasterType(String Master,String selectItem) throws Throwable, Throwable {
		clickCheckboxByName("statisticMaster");
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getstatisticMasterDrp(), Master);
		doClickButtons("Ad Hoc Statistic Master", "Select");
		waitForFormDialog("Add Statistic");
		selectFormItem(selectItem, "");
		doClickButtons("Add Statistic Data", "Add");
		keyInInputWithActionClass(driver.findElement(By.xpath("((//div[text()='Statistic Data'])[1]//following::div//table[1]//td[2]/div)[2]")), generateRandomNumber());
		driver.findElement(By.xpath("(//div[text()='Statistic Data'])[1]//following::div//table[1]//td[1]/div")).click();
		doClickButtons("Statistic Data", "Undo");
		assertElementText(driver.findElement(By.xpath("((//div[text()='Statistic Data'])[1]//following::div//table[1]//td[2]/div)[2]")), "0", printout);
		for(int i=2;i<driver.findElements(By.xpath("(//div[text()='Statistic Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div")).size();i++) {
			keyInInputWithActionClass(driver.findElement(By.xpath("((//div[text()='Statistic Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div)["+i+"]")), generateRandomNumber());
		}
		String total=driver.findElement(By.xpath("(//div[text()='Statistic Data'])[1]//following::div//table[1]//td[1]/div")).getText();
		int tot=Integer.parseInt(total);
		if(tot>0) {
			assertTrue(printout);
		}
		else {fail();}
		doClickButtons("Statistic Data", "Save Data");
		//Delete
		for(WebElement data:DataMaintenanceMap.getstatisticDataGrid()) {
			try {
				if(DataMaintenanceMap.getstatisticDataGrid().size()>0) {
					DataMaintenanceMap.getstatisticDataGrid().get(0).click();
					doClickButtons("Statistic Data", "Delete");
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
