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
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContractualAllowances extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozContractualAllowances = "Contractual Allowances";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String deptHierarchy="ASESC-1832 Hospital Hierarchy";
	static String[] contractAllowancesFilter= {"Name","Is","Equal To",name};
	static String[] benifitCodes= {"0000 PRIVATE PAY (BP Marina Benefit Plan Masters) "};
	static String[] billType= {"1SM1 Final Bill Type"};
	static String[] careDelivery= {"0000 PRIVATE PAY"};
	static String[] contractTypes= {"010244"};
	static String[] contracts= {"* ICD10 UDF Fields"};
	static String[] efr= {"1SM1 Pre-Admit"};
	static String[] encounterType= {"1S1 Office"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ContractualAllowances", "webdriver.scripts.datamaintenance.maintaindata",
				"ContractualAllowances");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozContractualAllowances);
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
			keyInInputByName("name", name, "Contractual Allowance");
			keyInInputByName("description", name, "Contractual Allowance");
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(contractAllowancesFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddBenifitPlanCode() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			expandPanel("Allowances");
			clickCheckboxByName("benefitPlanCodesCheck");
			doClick(DataMaintenanceMap.getbenifitPlanCodeSelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(benifitCodes);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getbenifitPlanCodeView());
			assertTextIsDisplayed(benifitCodes[0]);
			doClick(DataMaintenanceMap.getbenifitPlanCodeView());
			ExtentReport.logPass("PASS", "test02AddBenifitPlanCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddBenifitPlanCode", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddBillType() throws Throwable {
		try {
			clickCheckboxByName("billTypesCheck");
			doClick(DataMaintenanceMap.getbilltypesSelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(billType);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getbilltypesView());
			assertTextIsDisplayed(billType[0]);
			doClick(DataMaintenanceMap.getbilltypesView());
			ExtentReport.logPass("PASS", "test03AddBillType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddBillType", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddCareDeliveryFacility() throws Throwable {
		try {
			clickCheckboxByName("careDeiveryFacilityCheck");
			doClick(DataMaintenanceMap.getcareDeliveryFacilitySelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(careDelivery);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getcareDeliveryFacilityView());
			assertTextIsDisplayed(careDelivery[0]);
			doClick(DataMaintenanceMap.getcareDeliveryFacilityView());
			ExtentReport.logPass("PASS", "test04AddCareDeliveryFacility");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddCareDeliveryFacility", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05AddContractType() throws Throwable {
		try {
			clickCheckboxByName("contractTypesCheck");
			doClick(DataMaintenanceMap.getcontractTypesSelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(contractTypes);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getcontractTypesView());
			assertTextIsDisplayed(contractTypes[0]);
			doClick(DataMaintenanceMap.getcontractTypesView());
			ExtentReport.logPass("PASS", "test05AddContractType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AddContractType", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddContracts() throws Throwable {
		try {
			clickCheckboxByName("contractsCheck");
			doClick(DataMaintenanceMap.getcontractSelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(contracts);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getcontractView());
			assertTextIsDisplayed(contracts[0]);
			doClick(DataMaintenanceMap.getcontractView());
			ExtentReport.logPass("PASS", "test06AddContracts");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddContracts", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07AddEFR() throws Throwable {
		try {
			clickCheckboxByName("efrCategoriesCheck");
			doClick(DataMaintenanceMap.getefrCategorySelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(efr);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getefrCategoryView());
			assertTextIsDisplayed(efr[0]);
			doClick(DataMaintenanceMap.getefrCategoryView());
			ExtentReport.logPass("PASS", "test06AddContracts");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddContracts", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08AddEnconterTypes() throws Throwable {
		try {
			clickCheckboxByName("encounterTypesCheck");
			doClick(DataMaintenanceMap.getencounterTypeSelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(encounterType);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getencounterTypeView());
			assertTextIsDisplayed(encounterType[0]);
			doClick(DataMaintenanceMap.getencounterTypeView());doClick(DataMaintenanceMap.getencounterTypeView());
			collapsePanel("Allowances");
			ExtentReport.logPass("PASS", "test06AddContracts");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddContracts", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09AddUDFs() throws Throwable {
		try {
			expandPanel("UDFs");
			clickCheckboxByName("udf1Id");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//span[text()='UDF 1']//following::ul)[3]")), "Fiscal Year");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			
			ExtentReport.logPass("PASS", "test09AddUDFs");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09AddUDFs", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10DeleteContractAllowance() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test10DeleteContractAllowance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10DeleteContractAllowance", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
