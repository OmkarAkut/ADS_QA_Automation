package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PsychCombinedComorbidityAssignment extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPsychCombinedComorbidityAssignment = "Psych Combined Comorbidity Assignments";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] psycFilter= {"Name","Is","Equal To",name};
	static String[] benefitPlan= {"0000 PRIVATE PAY (BPMarina Benefit Plan Masters) "};
	static String[] careDeliveryFacility= {"0000 PRIVATE PAY"};
	static String[] encounterType= {"2S1 Office"};
	static String[] icd10Codes= {"0020 Typhoid fever"};
	static String[] icd9Codes= {"0020 Typhoid fever"};
	static String[] psychCalcFilter= {"Scenario Name","Is","Equal To",name};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PsychCombinedComorbidityAssignment", "webdriver.scripts.datamaintenance.az",
				"PsychCombinedComorbidityAssignment");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPsychCombinedComorbidityAssignment);
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
			keyInInputByName("name", name, "Psych Combined Comorbidity Assignments");
			doClick(DataMaintenanceMap.getbenefitPlanSelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(benefitPlan);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getcareDeliveryFacilitiesSelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(careDeliveryFacility);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getencounterSelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(encounterType);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getbenefitPlanViewSelectCriteriaBtn());
			assertTextIsDisplayed("0000 PRIVATE PAY (BPMarina Benefit Plan Masters) ");
			doClick(DataMaintenanceMap.getcareDeliveryViewSelectCriteriaBtn());
			assertTextIsDisplayed("0000 PRIVATE PAY");
			doClick(DataMaintenanceMap.getencounterTypeViewSelectCriteriaBtn());
			assertTextIsDisplayed("2S1 Office");
			doClick(DataMaintenanceMap.geticd10CodesSelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(icd10Codes);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.geticd10CodesViewSelectBtn());
			assertTextIsDisplayed("0020 Typhoid fever");
			Actions action=new Actions(driver);
			action.moveToElement(ContractingMap.getContractFileSelect()).click().build().perform();
			driverDelay();
			//Shilpa: updated 7.3.2025 added robot class for file import , due to security issues with Autoit
//			fileImport(System.getProperty("user.dir")+"\\TestFiles\\IPFC22WDICD10.txt");
			fileImport(System.getProperty("user.dir")+"\\TestFiles\\IPFC25WD_ICD10.txt");
			driverDelay(3000);
			scrollToView("//input[@name='shareLog']");
			doClick("//input[@name='shareLog']");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			
			doClickButtons("Warning", "Cancel & Close");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(psycFilter);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateCalculatePsychCormorbidityAssignment() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons("Psych Combined Comorbidity Assignments", "Calculate");
			doFilterCalculationPage(psychCalcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay();
			confirmCalculationStatusDetailsContains("Process Completed");
			doClick(ContractingMap.getContractCalculationCloseViewDialog());
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test02ValidateEditPsychCormorbidityAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditPsychCormorbidityAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateResetPsychCormorbidityAssignment() throws Throwable {
		try {
			doClickButtons("Psych Combined Comorbidity Assignments", "Reset");
			doFilterCalculationPage(psychCalcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay();
			confirmCalculationStatusDetailsContains("Process Completed");
			doClick(ContractingMap.getContractCalculationCloseViewDialog());
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test03ValidateResetPsychCormorbidityAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateResetPsychCormorbidityAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateEditDiagnoses() throws Throwable {
		try {
			doClickButton("ICD9");
			clickCheckboxByName("otherDrgIndex");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//span[text()='DRG from']//following::ul/li[text()='DRG2Code']/..")), "DRG2Code");
			doClick(DataMaintenanceMap.geticd9CodesSelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(icd9Codes);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.geticd9CodesViewSelectBtn());
			assertTextIsDisplayed("0020 Typhoid fever");
			collapsePanel("Diagnoses");
			collapsePanel("Dates & Files");
			expandPanel("Diagnoses");
			expandPanel("Dates & Files");
			doClick(DataMaintenanceMap.getazSaveBtn());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			driverDelay();
			ExtentReport.logPass("PASS", "test04ValidateEditDiagnoses");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateEditDiagnoses", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeletePsychComorbidityAssignment() throws Throwable {
		try {
			driver.navigate().refresh();
			waitForDisplayedSpinnerToEnd();
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPsychCombinedComorbidityAssignment);
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(psycFilter);
//			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getmessageboxDeleteBtn());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04ValidateEditDiagnoses");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateEditDiagnoses", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}

