package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

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
public class ContractBatches extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozContractBatches = "Contract Batches";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] contractBatchFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	String[] contracts = {"0000 Contract Model"};
	String[] encounterTypes = {"1S1 Office"};
	String[] lumpsumContracts = {"00 Contract Model PJ"};
	static String updatedName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ContractBatches", "webdriver.scripts.datamaintenance.maintaindata",
				"ContractBatches");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozContractBatches);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ContractBatchWithNoLumpsumPayment() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Contract Batch");
			doClickButtons("Contract Batch", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(contracts);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			clickCheckboxByName("calcFfs");
			clickCheckboxByName("calcusingOverride");
			clickCheckboxByName("reimbursementScenario");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getdestReimburseScenario(), "Contracted Payment 2");
			expandPanel("Fee for Service/Lump Sum");
			clickCheckboxByName("allEncounterTypes");
			doClickButtons("Encounter Types to Calculate", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(encounterTypes);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			expandPanel("Error Options");
			clickCheckboxByName("recalcingErrors");
			clickCheckboxByName("recalcingWarnings");
			clickCheckboxByName("shareLog");
			CimHelper.checkElements(DataMaintenanceMap.getsaveCreateNew());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(contractBatchFilter);
			assertTextIsDisplayed(name);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ContractBatchWithNoLumpsumPayment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ContractBatchWithNoLumpsumPayment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ContractBatchWithLumpsumPayment() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Contract Batch");
			doClick("//label[text()='Unpublished']//preceding::input[1]");
			doClickButtons("Contract Batch", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(lumpsumContracts);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			clickCheckboxByName("calcFfs");
			clickCheckboxByName("calcusingOverride");
			clickCheckboxByName("reimbursementScenario");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getdestReimburseScenario(), "Contracted Payment 22");
			clickCheckboxByName("calcLumpSum");
			expandPanel("Fee for Service/Lump Sum");
			doClickButtons("Populations to Calculate", "Select");
			selectFormItem("# ASESC-2471", "");
			driverDelay();
			doClickButtons("Populations to Calculate", "Enter Lump Sum Amounts");
			for(int i=1;i<=DataMaintenanceMap.getlumpSumAmountTable().size();i++) {
				Actions actions = new Actions(driver);
				actions.doubleClick(driver.findElement(By.xpath("(//div[text()='Enter Lump Sum Amounts']//following::table//td[2]/div)["+i+"]")))
				       .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)  
				       .sendKeys(Keys.DELETE)                                     
				       .sendKeys("10")                                 
				       .build()
				       .perform();
				
			}
			doClickButtons("Enter Lump Sum Amounts", "Save");
			doClickButtons("Enter Lump Sum Amounts", "Save & Close");
			expandPanel("Error Options");
			clickCheckboxByName("recalcingErrors");
			clickCheckboxByName("recalcingWarnings");
			clickCheckboxByName("shareLog");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getsaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(contractBatchFilter);
			assertTextIsDisplayed(name);
			
			ExtentReport.logPass("PASS", "test02ContractBatchWithLumpsumPayment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ContractBatchWithLumpsumPayment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveAsFunction() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClick(DataMaintenanceMap.getazSaveAsBtn());
			String copyContract="Copy"+name;
			String[] copyContractFilter= {"Name","Is","Equal To",copyContract};
			driver.findElement(By.xpath("//div[text()='Save As']//following::input[@name='name']")).sendKeys(copyContract);
			doClickButtons("Save As", "Save & Close");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(copyContractFilter);
			assertTextIsDisplayed(copyContract);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveAsFunction");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveAsFunction", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteContractBatchFunction() throws Throwable {
		try {
			
			ExtentReport.logPass("PASS", "test04DeleteContractBatchFunction");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteContractBatchFunction", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
