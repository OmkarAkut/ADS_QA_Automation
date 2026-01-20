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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ICDDiagnosisGroups extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozICDDiagnosisGroups= "ICD Diagnosis Groups";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "ICD Diagnosis Group";
	static ContractingMap contract;
	String[] columnsIcd10 = {"0021 Paratyphoid fever a"};
	String[] columnsIcd10Edit = { "0022 Paratyphoid fever b"};
	String[] validateIcd10CodesBeforeEdit= {"0021"};
	String[] validateIcd10CodesAfterEdit=  {"0022","0021" };
	String[] columnsIcd9 = {"0010  Cholera d/t vib cholerae"};
	String[] validateIcd10Icd9Codes= {"0021","0010","0022"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ICDProcedureCodeGroups", "webdriver.scripts.datamaintenance.az",
				"ICDProcedureCodeGroups");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contract=BuildMap.getInstance(driver,ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozICDDiagnosisGroups);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveAndCreateNewGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "ICD Diagnosis Group");
			doClickButton("Select ICD10 Dx");
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsIcd10);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			assertElements(validateIcd10CodesBeforeEdit, azName);
			assertTextIsDisplayed("ICD10");
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateSaveAndCreateNewGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveAndCreateNewGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditICD10DiagnosisGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButton("Select ICD10 Dx");
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsIcd10Edit);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			assertElements(validateIcd10CodesAfterEdit, azName);
			assertTextIsDisplayed("ICD10");
			doClick(DataMaintenanceMap.getsaveButton());
			doClick(DataMaintenanceMap.getsaveCloseButton());
			ExtentReport.logPass("PASS", "test02ValidateEditICDDiagnosisGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditICDDiagnosisGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateAddICD9diagnosisGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButton("Select ICD9 Dx");
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsIcd9);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			assertElements(validateIcd10Icd9Codes, azName);
			assertTextIsDisplayed("ICD9");
			assertTextIsDisplayed("ICD10");
			doClick(DataMaintenanceMap.getsaveButton());
			doClick(DataMaintenanceMap.getsaveCloseButton());
			ExtentReport.logPass("PASS", "test03ValidateEditSaveGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditSaveGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteICDdiagnosisGroup() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteICDdiagnosisGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteICDdiagnosisGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
