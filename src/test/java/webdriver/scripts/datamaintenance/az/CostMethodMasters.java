package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

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
public class CostMethodMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozCostMethodMasters = "Cost Method Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] costMethodMasterFilter= {"Name of the Cost Method Master","Is","Equal To",name};
	static String updatedName;
	static String azName="Cost Method Master";
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
		ExtentReport.reportCreate("CostMethodMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"CostMethodMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozCostMethodMasters);
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
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodMasterDrpdwn(), compMaster);
			assertElementIsDisplayedWithXpath("//span[text()='Department Hierarchy']//following::div[text()='*FZ Small Hierarchy Test']");
			assertElementIsDisplayedWithXpath("//div[text()='Entities']//following::div[text()='150  Marina Medical Center']");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costMethodMasterFilter);
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
	public void test02EditSaveCloseCostMethodMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			updatedName="Cost Method"+name;
			keyInInputByName("name", updatedName, azName);
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			String[] costComponentfilter= {"Name of the Cost Method Master","Is","Equal To",updatedName};
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costComponentfilter);
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test02EditSaveCloseCostMethodMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditSaveCloseCostMethodMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test03AddCostComponentAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			clickCheckboxByName("entityCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodAssignEntityDrp(), entity);
			doClick(DataMaintenanceMap.getdeptGroup());
			selectFormItem("*CBDEPT CBDEPT", "");
			clickCheckboxByName("costCompObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostComponentDrpdwn(), costComponent);
			
			ExtentReport.logPass("PASS", "test03AddCostComponentAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddCostComponentAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04SelectRCCCostMethod() throws Throwable {
		try {
			clickCheckboxByName("costMethod");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodDrpdwn(), "RCC");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("150 Marina Medical Center");
			assertTextIsDisplayed("*CBDEPT CBDEPT");
			assertTextIsDisplayed("SW");
			assertTextIsDisplayed("RCC");
			assertTextIsDisplayed("No");
			ExtentReport.logPass("PASS", "test03SelectRCCCostMethod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SelectRCCCostMethod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05SelectActualCostMethod() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("costMethod");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodDrpdwn(), "Actual");
			clickCheckboxByName("productTypeCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodProducttypeDrpdwn(), "Exams General");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("*CBDEPT CBDEPT");
			assertTextIsDisplayed("SW");
			assertTextIsDisplayed("Actual");
			assertTextIsDisplayed("No");
			ExtentReport.logPass("PASS", "test04SelectActualCostMethod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04SelectActualCostMethod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06SelectRVUCostMethod() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("costMethod");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodDrpdwn(), "RVU");
			//Select Default Method
			clickCheckboxByName("defaultMethod");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodDefaultDrpdwn(), "RCC");
			doClick("(//input[@name='studiedallocRadio'])[2]");
			keyInInputByName("studiedAllocPct", "6", "Cost Method Assignment");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("*CBDEPT CBDEPT");
			assertTextIsDisplayed("SW");
			assertTextIsDisplayed("RVU");
			assertTextIsDisplayed("RCC");
			assertTextIsDisplayed("6");
			assertTextIsDisplayed("No");
			ExtentReport.logPass("PASS", "test05SelectRVUCostMethod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05SelectRVUCostMethod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07SelectRCACCostMethod() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("useRcc");
			clickCheckboxByName("costMethod");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcostMethodDrpdwn(), "RCC");
			clickCheckboxByName("rccChgActCodeGroupObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getchargeableActivtyGroup(), "Act Cost End to End Chg Grp");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			
			
			ExtentReport.logPass("PASS", "test07SelectRCACCostMethod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07SelectRCACCostMethod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08AddRCACCostAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			clickCheckboxByName("months");
			String month = javaGetCurrentMonth();
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrcacStartMonth(), month);
			String year = javaGetCurrentYear();
			clickCheckboxByName("year");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrcacStartYear(), year);
			for(int i=1;i<DataMaintenanceMap.getrcacCostFactorList().size();i++) {
				if(i>5) {
					break;
				}
				else {
				keyInInputWithActionClass(driver.findElement(By.xpath("(//span[text()='RCAC Cost Factor List']//following::table//td/div)["+(i+1)+"]")), "1");
				}			
				}
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			System.out.println(month+" "+year);
			assertElementIsDisplayedWithXpath("//div[text()='RCAC Cost Assignments']//following::div[contains(text(),"+year+")]");
			assertElementIsDisplayedWithXpath("//div[text()='RCAC Cost Assignments']//following::div[contains(text(),"+month+")]");
			ExtentReport.logPass("PASS", "test08AddRCACCostAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08AddRCACCostAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09EditRCACCostAssignment_DeleteRCACFactor() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			try {
				CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			if(DataMaintenanceMap.getazInnerPageCancelCloseBtn().size()>0) {
				CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			}
			} catch (NoSuchElementException e) {
				
			}
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test09EditRCACCostAssignment_DeleteRCACFactor");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09EditRCACCostAssignment_DeleteRCACFactor", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10DeleteCostMethodAssignment() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test10DeleteCostMethodAssignment");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10DeleteCostMethodAssignment", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11DeleteCostMethodMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test11DeleteCostMethodMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11DeleteCostMethodMaster", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
