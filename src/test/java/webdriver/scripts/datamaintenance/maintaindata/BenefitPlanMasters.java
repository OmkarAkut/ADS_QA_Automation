package webdriver.scripts.datamaintenance.maintaindata;

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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BenefitPlanMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozBenefitPlanMasters = "Benefit Plan Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String benifitPlanMaster="Scheme "+currentDateTime;
	static String[] benefitPlanMasterFilter= {"Name","Is","Equal To",benifitPlanMaster};
	static String benifitPlanList;
	static String updatedName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("BenefitPlanMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"BenefitPlanMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozBenefitPlanMasters);
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
			keyInInputByName("name", benifitPlanMaster,"Benefit Plan Master");
			keyInInputByName("code", benifitPlanMaster,"Benefit Plan Master");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(benefitPlanMasterFilter);
			assertTextIsDisplayed(benifitPlanMaster);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", benifitPlanMaster,"Benefit Plan Master");
			keyInInputByName("code", benifitPlanMaster,"Benefit Plan Master");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(benefitPlanMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", benifitPlanMaster,"Benefit Plan Master");
			keyInInputByName("code", benifitPlanMaster,"Benefit Plan Master");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(benefitPlanMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddASCGroups() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			benifitPlanList="Group"+benifitPlanMaster;
			keyInInputByName("name", benifitPlanList, "New Benefit Plan");
			keyInInputByName("code", benifitPlanList, "New Benefit Plan");
			doClickButtons("New Benefit Plan", "Select");
			selectFormItem("100 Pacific Hospital", "");
			
			doClickButtons("New ASC Group", "Save & Create New");
			doClickButtons("New ASC Group", "Cancel & Close");
			assertTextIsDisplayed(benifitPlanMaster);
			ExtentReport.logPass("PASS", "test04AddASCGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddASCGroups", driver, e);
			fail(e.getMessage());
		}
	}
//	@Test
//	public void test05EditASCGroups() throws Throwable {
//		try {
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
//			updatedName="Updated"+ascScheme;
//			keyInInputByName("name", updatedName, ascGroupName);
//			doClickButtons(ascGroupName, "Save");
//			doClickButtons(updatedName, "Save & Close");
//			assertTextIsDisplayed(updatedName);
//			ExtentReport.logPass("PASS", "test05EditASCGroups");
//		} catch (Exception | AssertionError e) {
//			ExtentReport.logFail("FAIL", "test05EditASCGroups", driver, e);
//			fail(e.getMessage());
//		}
//	}
//	@Test
//	public void test06DeleteASCGroups() throws Throwable {
//		try {
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
//			doClick(DataMaintenanceMap.getwarningDeleteBtn());
//			assertTextIsDisplayed("There is no data available to display.");
//			doClick(DataMaintenanceMap.getCancelCloseButton());
//			ExtentReport.logPass("PASS", "test06DeleteASCGroups");
//		} catch (Exception | AssertionError e) {
//			ExtentReport.logFail("FAIL", "test06DeleteASCGroups", driver, e);
//			fail(e.getMessage());
//		}
//	}
//
//	@Test
//	public void test07DeleteASCScheme() throws Throwable {
//		try {
//			doClick(DataMaintenanceMap.getazDeleteBtn());
//			doClick(DataMaintenanceMap.getwarningDeleteBtn());
//			assertTextIsDisplayed("There is no data available to display.");
//			ExtentReport.logPass("PASS", "test07DeleteASCScheme");
//		} catch (Exception | AssertionError e) {
//			ExtentReport.logFail("FAIL", "test07DeleteASCScheme", driver, e);
//			fail(e.getMessage());
//		}
//	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
