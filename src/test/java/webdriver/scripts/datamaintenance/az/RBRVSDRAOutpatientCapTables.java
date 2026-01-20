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
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RBRVSDRAOutpatientCapTables extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozRBRVSDRAOutpatientCapTables = "RBRVS DRA Outpatient Cap Tables";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String hcpcsCodeMaster="CPT4";
	static String[] RBRVSRVUTablesFilter= {"Name","Is","Equal To",name};
	static String hcpcs ="00001A";
	static String hcpcsCodeAfterEdit ="00001T";
	static String hcpcsModifier="0B Reduced Services";
	static String hcpcsModifierEdit="1E hcpcs modifer1 OMODE";
	static String modifier="0B";
	static String modifierEdit="1E";
//	static String hcpcsName="ENDOMETRIAL CRYOABLATION";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RBRVSDRAOutpatientCapTables", "webdriver.scripts.datamaintenance.az",
				"RBRVSDRAOutpatientCapTables");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozRBRVSDRAOutpatientCapTables);
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
			keyInInputByName("name", name, "RBRVS DRA Outpatient Cap Table");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(RBRVSRVUTablesFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditRBRVSCapTable() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Items");
			selectDropdownOption("hcpcsMasterCode", DataMaintenanceMap.gethcpcsMasterCodeDrp(), hcpcsCodeMaster);
			doClick(DataMaintenanceMap.gethcpcCodeSelectBtn());
			waitForFormDialog("Add HCPCS Code");
			selectFormItem(hcpcs, "");
			doClick(DataMaintenanceMap.gethcpcModifierSelectBtn());
			waitForFormDialog("Add HCPCS Modifier");
			selectFormItem(hcpcsModifier, "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(hcpcs);
			assertTextIsDisplayed(modifier);
//			assertTextIsDisplayed(hcpcsName);
			ExtentReport.logPass("PASS", "test02ValidateEditRBRVSCapTable");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditRBRVSCapTable", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditHCPCSItem() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.gethcpcCodeSelectBtn());
			waitForFormDialog("Add HCPCS Code");
			selectFormItem(hcpcsCodeAfterEdit, "");
			doClick(DataMaintenanceMap.gethcpcModifierSelectBtn());
			waitForFormDialog("Add HCPCS Modifier");
			selectFormItem(hcpcsModifierEdit, "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(hcpcsCodeAfterEdit);
			assertTextIsDisplayed(modifierEdit);
			ExtentReport.logPass("PASS", "test03EditHCPCSItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditHCPCSItem", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteHcpcsItem() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteHcpcsItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteHcpcsItem", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteRBRVSDRACAP() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteRBRVSDRACAP");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteRBRVSDRACAP", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
