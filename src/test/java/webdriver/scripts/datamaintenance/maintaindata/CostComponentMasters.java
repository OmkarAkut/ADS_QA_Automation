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
public class CostComponentMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozCostComponentMasters = "Cost Component Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] costComponentMasterFilter= {"Name","Is","Equal To",name};
	static String updatedName;
	static String azName="Cost Component Master";
	static String[]  entities= {"0000 PRIVATE PAY"};
	static String deptHierarchy="*FZ Small Hierarchy Test";
	static String glAccountHierarchy="ASESC-1832 Hospital Account Hierarchy";
	static String typeExpenseSource="Calculated Overhead";
	static String entity="0000 PRIVATE PAY";
	static String deptGroup="*CBDEPT CBDEPT";
	static String glAcctGroup="*TOTREV Total Revenue";
	
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CostComponentMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"CostComponentMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozCostComponentMasters);
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
			clickCheckboxByName("deptHierarchyObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getdeptDropdown(), deptHierarchy);
			clickCheckboxByName("accountHierarchyId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglAccountDropdown(), glAccountHierarchy);
			doClickButtons("Hierarchies and Entities", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(entities);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costComponentMasterFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(deptHierarchy);
			assertTextIsDisplayed(glAccountHierarchy);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditSaveCloseComponentMaster() throws Throwable {
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
			ExtentReport.logPass("PASS", "test02EditSaveCloseComponentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditSaveCloseComponentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddCostComponents() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("Warning", "Continue");
			keyInInputByName("name", "Cost Component"+name, "Cost Components");
//			addDetailsInnerPages(null, "Cost Component"+name, "Save & Create New", null, "name");
			keyInInputByName("rvuOrderIndex", "2", "Cost Components");
			clickCheckboxByName("typeExpensesource");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getexpenseType(), typeExpenseSource);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(updatedName);
			assertTextIsDisplayed(typeExpenseSource);
			assertElementIsDisplayedWithXpath("(//span[text()='RVU and Rpt Order']//following::div[text()='2'])");
			ExtentReport.logPass("PASS", "test03AddCostComponents");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddCostComponents", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddCostComponentAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			clickCheckboxByName("entityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostComponentAssignmentEntity(), entity);
			doClick(DataMaintenanceMap.getdeptGroup());
			selectFormItem(deptGroup, "");
			doClick(DataMaintenanceMap.getglAcctGroup());
			selectFormItem(glAcctGroup, "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(entity);
			assertTextIsDisplayed("*CBDEPT  CBDEPT");
			assertTextIsDisplayed("*TOTREV  Total Revenue");
			ExtentReport.logPass("PASS", "test04AddCostComponentAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddCostComponentAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditCostComponentAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("entityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostComponentAssignmentEntity(), "<ALL>");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("<ALL>");
			assertTextIsDisplayed("*CBDEPT  CBDEPT");
			assertTextIsDisplayed("*TOTREV  Total Revenue");
			ExtentReport.logPass("PASS", "test05EditCostComponentAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditCostComponentAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteCostComponentAssignment() throws Throwable {
		try {
			doClickButtons("Cost Component", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test05EditCostComponentAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditCostComponentAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteCostComponent() throws Throwable {
		try {
			doClickButtons(azName, "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test07DeleteCostComponent");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteCostComponent", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08DeleteCostComponentMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test08DeleteCostComponentMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteCostComponentMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
