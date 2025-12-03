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
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostComponentVariabilityMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozCostComponentVariabilityMasters = "Cost Component Variability Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] costComponentVarMasterFilter= {"Name","Is","Equal To",name};
	static String updatedName;
	static String azName="Cost Component Variability Master";
	static String entity="150 Marina Medical Center";
	static String[]  entities= {"0000 PRIVATE PAY"};
	static String compMaster="*FZ Small Hierarchy";
	static String deptHierarchy="*FZ Small Hierarchy Test";
	static String costComponentMaster="*FZ Small Hierarchy";
	static String costComponent="SW";
	static String varPercent="95";
	static String updatedvarPercent="96";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CostComponentVariabilityMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"CostComponentVariabilityMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozCostComponentVariabilityMasters);
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
			clickCheckboxByName("cstCompObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostComponentMasterDrpdwn(), compMaster);
			assertElementIsDisplayedWithXpath("//span[text()='Department Hierarchy']//following::div[text()='*FZ Small Hierarchy Test']");
			assertElementIsDisplayedWithXpath("//div[text()='Entities']//following::div[text()='150  Marina Medical Center']");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costComponentVarMasterFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(deptHierarchy);
			assertTextIsDisplayed(costComponentMaster);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditSaveCloseComponentVarMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			updatedName="Cost Component"+name;
			keyInInputByName("name", updatedName, azName);
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			String[] costComponentfilter= {"Name","Is","Equal To",updatedName};
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costComponentfilter);
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test02EditSaveCloseComponentVarMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditSaveCloseComponentVarMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test03AddCostComponentVarAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			clickCheckboxByName("entityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostAssignEntityDrp(), entity);
			doClick(DataMaintenanceMap.getdeptGroup());
			selectFormItem("*CBDEPT CBDEPT", "");
			doClick("(//input[@name='cstCompObjectId'])[2]");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostComponentDrpdwn(), costComponent);
			keyInInputByName("variablePercent", varPercent, "New Cost Component Variability Assignment");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertElementIsDisplayedWithXpath("(//*[text()='150  Marina Medical Center'])[2]");
			assertTextIsDisplayed("*CBDEPT  CBDEPT");
			assertTextIsDisplayed("SW");
			assertTextIsDisplayed(varPercent);
			ExtentReport.logPass("PASS", "test03AddCostComponentVarAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddCostComponentVarAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04EditCostComponentVarAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("variablePercent", updatedvarPercent, "Cost Component Variability Assignment");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertElementIsDisplayedWithXpath("(//*[text()='150  Marina Medical Center'])[2]");
			assertTextIsDisplayed("*CBDEPT  CBDEPT");
			assertTextIsDisplayed("SW");
			assertTextIsDisplayed(updatedvarPercent);
			ExtentReport.logPass("PASS", "test04EditCostComponentVarAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04EditCostComponentVarAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteCostComponentVarAssignment() throws Throwable {
		try {
			doClickButtons(azName, "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test05DeleteCostComponentVarAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteCostComponentVarAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteCostComponentVarMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteCostComponentVarMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteCostComponentVarMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
