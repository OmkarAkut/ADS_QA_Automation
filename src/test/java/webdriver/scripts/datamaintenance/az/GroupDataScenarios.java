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
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupDataScenarios extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGroupDataScenarios = "Group Data Scenarios";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String fiscalYear="**FY16";
	static String[] groupDataScenariosFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String updatedName;
	static String[] memberSubGroupFilter= {"Employer Group Code","Is","Equal To","BSM1001"};
	static String employerGroup="1, BSM1001 EmployerGroup1-1 ";
	static String employerSubGroup="BSM1001SG Sub Group 1001";
	static String memberDestination="1, BSM11 Member Designation1-1";
	static String[] memberSubGroupInnerFilter= {"Code","Is","Equal To","BSM1001"};
	static String market="1, MKT1 Market 1 - Entity";
	static String network="5HOSP QA Hospital";
	static String master="FZ Group Master";
	static String statistic="GRPSTAT  Group Stat Code";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GroupDataScenarios", "webdriver.scripts.datamaintenance.az",
				"GroupDataScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGroupDataScenarios);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void addGroupDataPoints() throws Throwable, Throwable {
		keyInInputWithDoubleClick(driver.findElement(By.xpath("((//div[text()='Group Data'])[1]//following::div//table[1]//td[2]/div)[2]")), generateRandomNumber());
		driver.findElement(By.xpath("(//div[text()='Group Data'])[1]//following::div//table[1]//td[1]/div")).click();
		doClickButtons("Group Data", "Undo");
		assertElementText(driver.findElement(By.xpath("((//div[text()='Group Data'])[1]//following::div//table[1]//td[2]/div)[2]")), "0.00", printout);
		for(int i=2;i<=driver.findElements(By.xpath("(//div[text()='Group Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div")).size();i++) {
			keyInInputWithDoubleClick(driver.findElement(By.xpath("((//div[text()='Group Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div)["+i+"]")),generateRandomNumber());
		}
		String total=driver.findElement(By.xpath("(//div[text()='Group Data'])[1]//following::div//table[1]//td[1]/div")).getText();
		int tot=Integer.parseInt(total);
		if(tot>0) {
			assertTrue(printout);
		}
		else {fail();}
		doClickButtons("Group Data", "Save Data");
		//Delete
		for(WebElement data:DataMaintenanceMap.getgroupDataGrid()) {
			try {
				if(DataMaintenanceMap.getgroupDataGrid().size()>0) {
					data.click();
					doClickButtons("Group Data", "Delete");
					doClickButtons("Delete", "Delete");
					
				}
			} catch (Exception e) {
				
			}
		}
	}
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Group Data Scenario");
			clickCheckboxByName("timePeriodName");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getgroupFiscalYearDrp(), fiscalYear);
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(groupDataScenariosFilter);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateAddGroupData() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButton("Add");
			waitForFormDialog("Add Group Data");
			doClick(DataMaintenanceMap.getemployerGrpSelectBtn());
			waitForFormDialog("Add Employer Group");
			doClickButtons("Add Employer Group", "Filter");
			doFilterCreate(memberSubGroupInnerFilter);
			selectFormItem(employerGroup, "");
			doClick(DataMaintenanceMap.getemployerSubGrpSelectBtn());
			waitForFormDialog("Add Employer Subgroup");
			selectFormItem(employerSubGroup, "");
			doClick(DataMaintenanceMap.getmemberDesignationSelectBtn());
			waitForFormDialog("Add Member Designation");
			selectFormItem(memberDestination, "");
			clickCheckboxByName("marketId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='Add Group Data']//following::ul/li[text()='"+market+"']/..)")), market);
			clickCheckboxByName("networkCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='Add Group Data']//following::ul/li[text()='"+network+"']/..)")), network);
			clickCheckboxByName("groupStatisticMasterId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='Add Group Data']//following::ul/li[text()='"+master+"']/..)")), master);
			clickCheckboxByName("groupStatisticId");
			doClick("(//div[text()='Add Group Data']//following::ul/li[text()='"+statistic+"']/..)");
			doClickButtons("Add Group Data", "Add");
			addGroupDataPoints();
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateAddGroupData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateAddGroupData", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateDeleteGroupDataScenario() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04ValidateDeleteGroupDataScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateDeleteGroupDataScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
