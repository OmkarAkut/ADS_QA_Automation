package webdriver.scripts.datamaintenance.az;

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
public class ReimbursementScenarioAssignments extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozReimbursementScenarioAssignments = "Reimbursement Scenario Assignments";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] reimbursementScenarioAssignmentsFilter= {"Name","Is","Equal To",name};
	static String population="# ASESC-2471";
	static String reimbursementOption="Contracted Payment 3";
	static String scenario01;
	static String scenario02;
	static String assignName;
	static String[] reimburseFilter= {"Name","Is","Equal To",assignName};

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ReimbursementScenarioAssignments", "webdriver.scripts.datamaintenance.maintaindata",
				"ReimbursementScenarioAssignments");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozReimbursementScenarioAssignments);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void validateCalculationStatusPage(String[] filter) throws Throwable {
		doClick("//div[text()='Calculation Status']//following::span[text()='Filter']");
		doFilterCreate(filter);
//		driverDelay(4000);
		waitForFirstRowCalculationBarToReach100Percent();
		driverDelay();
		calculationStatusPageOpenViewDialog();
		driverDelay(2000);
		checkForRecordsProcessed("Complete Assigning Reimbursement.");
		closeViewDialog();
		doClosePageOnLowerBar("Calculation Status");
	}
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("scenarioName", name, "Reimbursement Scenario Assignment");
			doClickButtons("Reimbursement Scenario Assignment", "Select");
			selectFormItem(population, "");
			CimHelper.dragAndDrop(driver.findElement(By.xpath("(//div[text()='Contracts']//following::table//td/div)[2]")),driver.findElement(By.xpath("(//label[text()='0 Contract(s) Selected']//following::div[@class='x-tab-guard x-tab-guard-after'])[2]")));
			assertTextIsDisplayed("1 Contract(s) Selected");
			clickCheckboxByName("reimbursementScenario");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getreimbursementDrp(), reimbursementOption);
			clickCheckboxByName("overwrite");
			keyInInputByName("description", "Reimburse Assignment", "Reimbursement Scenario Assignment");
			clickCheckboxByName("shareLog");
//			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
//			doClick(DataMaintenanceMap.getazFilterBtn());
//			doFilterCreate(reimbursementScenarioAssignmentsFilter);
//			doClick(DataMaintenanceMap.getazClearFilterBtn());
//			assertTextIsDisplayed(name);
//			assertTextIsDisplayed(population);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AssignReimburseAssignment() throws Throwable {
		scenario01=driver.findElement(By.xpath("((//h1[text()='Reimbursement Scenario Assignments']//following::div[contains(@id,'dynamicGrid')])[3]//table//td[2]/div)[1]")).getText();
		scenario02=driver.findElement(By.xpath("((//h1[text()='Reimbursement Scenario Assignments']//following::div[contains(@id,'dynamicGrid')])[3]//table//td[2]/div)[3]")).getText();
		doClick(DataMaintenanceMap.getazEditBtn());

		try {
			if(driver.findElements(By.xpath("//div[contains(@id,'domainlockerrorwindow')][text()='Error']")).size()>0) {
				doClickButtons("Error", "Close");
				doClick("((//h1[text()='Reimbursement Scenario Assignments']//following::div[contains(@id,'dynamicGrid')])[3]//table)[3]");
				doClick(DataMaintenanceMap.getazEditBtn());
				assignName=scenario02;
			}
			
			else {
				

				assignName=scenario01;

			}
//			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons("Reimbursement Scenario Assignment", "Assign");
			System.out.println(assignName);
			 String[] calcFilter= {"Scenario Name","Is","Equal To",assignName};
			validateCalculationStatusPage(calcFilter);
			
			ExtentReport.logPass("PASS", "test02AssignReimburseAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssignReimburseAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03SaveCloseReimburseAssignment() throws Throwable {
		try {
			clickCheckboxByName("overwrite");
//			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(reimburseFilter);
			doClick(DataMaintenanceMap.getazAssignBtn());
			String[] calcFilter= {"Scenario Name","Is","Equal To",assignName};
			validateCalculationStatusPage(calcFilter);
			ExtentReport.logPass("PASS", "test03SaveCloseReimburseAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveCloseReimburseAssignment", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
