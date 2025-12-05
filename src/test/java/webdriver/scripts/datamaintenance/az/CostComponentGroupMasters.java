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
public class CostComponentGroupMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozCostComponentGroupMasters = "Cost Component Group Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] costComponentGroupMastersFilter= {"Name","Is","Equal To",name};
	static String updatedName;
	static String azName="Cost Component Group Master";
	static String[]  groupMembers= {"A01 CC Master with GL Acct Hierarchy"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CostComponentGroupMasters", "webdriver.scripts.datamaintenance.maintaindata",
				"CostComponentGroupMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozCostComponentGroupMasters);
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
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(costComponentGroupMastersFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditSaveCloseButton() throws Throwable {
		try {
			updatedName="Group"+name;
			String[] updatedFilterGroup= {"Name","Is","Equal To",updatedName};
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", updatedName,azName);
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(updatedFilterGroup);
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test02EditSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddCostComponentGroups() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			addDetailsInnerPages(null, "CGroup"+name, "Save & Create New", null, "name");
			doClick("//label[text()='Overhead']//preceding::input[1]");
			doClick("//label[text()='Average']//preceding::input[1]");
			doClickButtons("New Cost Component Group", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(groupMembers);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			assertTextIsDisplayed("CC Master with GL Acct Hierarchy");
			assertTextIsDisplayed("A01");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("CGroup"+name);
			assertElementIsDisplayedWithXpath("(//span[text()='Report Order']//following::div[text()='1'])[2]");
			ExtentReport.logPass("PASS", "test03AddCostComponentGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddCostComponentGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04EditCostComponentGroups() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick("//label[text()='Direct']//preceding::input[1]");
			assertTextIsDisplayed("You changed the Cost Component Group Type. Decision Support will remove all cost component members from the Cost Component Group. Click Continue to proceed with the change; or, click Cancel to return to the screen without completing the change.");
			doClickButtons("Warning", "Continue");
			keyInInputByName("orderIndex", "2", "Cost Component Group");
			doClickButtons("CGroup"+name, "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(groupMembers);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertElementIsDisplayedWithXpath("(//span[text()='Report Order']//following::div[text()='2'])[1]");
			ExtentReport.logPass("PASS", "test04EditCostComponentGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04EditCostComponentGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteCostComponentGroups() throws Throwable {
		try {
			doClickButtons(azName, "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04DeleteCostComponentGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteCostComponentGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteCostComponentGroupMaster() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test06DeleteCostComponentGroupMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteCostComponentGroupMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
