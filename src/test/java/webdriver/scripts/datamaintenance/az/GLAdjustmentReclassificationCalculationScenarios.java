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
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GLAdjustmentReclassificationCalculationScenarios extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLAdjustmentMaster = "GL Adjustment and Reclassification Calculation Scenarios";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String azName="GL Adjustment and Reclassification Calculation Scenario";
	static String glAdjustmentMaster="Marina FY03 Adjustment Master";
	static String glReclassificationMaster="Marina Reclass";
	static String startMonth="May 2002";
	static String endMonth="Jun 2002";
	static String[] glAdjustmentReclassificatFilter= {"Name","Is","Equal To",name};
	static String updatedName="Updated"+name;
	static String[] updatedGlAdjustmentReclassificatFilter= {"Name","Is","Equal To",updatedName};
	static String[] filterCalcGL= {"Scenario Name", "Is", "Equal To", updatedName};
	static ContractingMap contractMap;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLAdjustmentReclassificationCalculationScenarios", "webdriver.scripts.datamaintenance.maintaindata",
				"GLAdjustmentReclassificationCalculationScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLAdjustmentMaster);
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
			keyInInputByName("name", name, azName);
			clickCheckboxByName("adjustmentMasterId");
			selectDropdown(glAdjustmentMaster, azName);
			clickCheckboxByName("reclassificationGroupMasterId");
			selectDropdown(glReclassificationMaster, azName);
			clickCheckboxByName("startDateString");
			selectDropdown(startMonth, azName);
			clickCheckboxByName("endDateString");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='"+azName+"']//following::ul/li[text()='"+endMonth+"']/..)[2]")), endMonth);
			clickCheckboxByName("shareLogFlag");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glAdjustmentReclassificatFilter);
			assertTextIsDisplayed(name);
			assertElementIsDisplayedWithXpath("//*[text()='"+name+"']//following::div[text()='"+glAdjustmentMaster+"']");
			assertElementIsDisplayedWithXpath("//*[text()='"+name+"']//following::div[text()='"+glReclassificationMaster+"']");
			assertElementIsDisplayedWithXpath("//*[text()='"+name+"']//following::div[text()='"+startMonth+"']");
			assertElementIsDisplayedWithXpath("//*[text()='"+name+"']//following::div[text()='"+endMonth+"']");
			assertTextIsDisplayed("Summary");
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveAsButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", updatedName, azName);
			doClick(DataMaintenanceMap.getazSaveAsBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(updatedGlAdjustmentReclassificatFilter);
			ExtentReport.logPass("PASS", "test02ValidateSaveAsButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveAsButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButton("Detail");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClickButtons(azName, "Calculate");
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filterCalcGL);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			assertTextIsDisplayed(updatedName);
			assertElementIsDisplayedWithXpath("//*[text()='"+updatedName+"']//following::div[text()='"+glAdjustmentMaster+"']");
			assertElementIsDisplayedWithXpath("//*[text()='"+updatedName+"']//following::div[text()='"+glReclassificationMaster+"']");
			assertElementIsDisplayedWithXpath("//*[text()='"+updatedName+"']//following::div[text()='"+startMonth+"']");
			assertElementIsDisplayedWithXpath("//*[text()='"+updatedName+"']//following::div[text()='"+endMonth+"']");
			assertTextIsDisplayed("Detail");
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazCalculateBtn());
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filterCalcGL);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test03ValidateEditButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditButton", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test04DeleteGLAdjustmentReclassificationCalcScenario() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteGLAdjustmentReclassificationCalcScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteGLAdjustmentReclassificationCalcScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
