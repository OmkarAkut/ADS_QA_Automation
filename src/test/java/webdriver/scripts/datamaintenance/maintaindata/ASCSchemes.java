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
public class ASCSchemes extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozASCSchemes = "ASC Schemes";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String ascScheme="Scheme "+currentDateTime;
	static String hcpcCodeMaster="CPT4";
	static String[] ascSchemeFilter= {"ASC Scheme Name","Is","Equal To",ascScheme};
	String[] columns = { "00160  Anesth nose/sinus surgery", "00162  Anesth nose/sinus surgery"};
	static String ascGroupName;
	static String updatedName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ASCSchemes", "webdriver.scripts.datamaintenance.maintaindata",
				"ASCSchemes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozASCSchemes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getascSchemeNewBtn());
			keyInInputByName("name", ascScheme,"ASC Scheme");
			doClick(DataMaintenanceMap.gethcpcsCodeMaster());
			driverDelay(100);
			doDropdownSelectUsingOptionTextWithelement(DataMaintenanceMap.gethcpcsCodeMasterDrpdwn(),
					hcpcCodeMaster);
			doClick(DataMaintenanceMap.getapplyChargesRadioBtn());
			doClickButtons("ASC Scheme", "Select");
			selectFormItem("# 02 Charge Item Svc","");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getascSchemeFilterBtn());
			doFilterCreate(ascSchemeFilter);
			assertTextIsDisplayed(ascScheme);
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
			doClick(DataMaintenanceMap.getascSchemeNewBtn());
			keyInInputByName("name", ascScheme,"ASC Scheme");
			doClick(DataMaintenanceMap.gethcpcsCodeMaster());
			driverDelay(100);
			doDropdownSelectUsingOptionTextWithelement(DataMaintenanceMap.gethcpcsCodeMasterDrpdwn(),
					hcpcCodeMaster);
			doClick(DataMaintenanceMap.getapplyChargesRadioBtn());
			doClickButtons("ASC Scheme", "Select");
			selectFormItem("# 02 Charge Item Svc","");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(ascSchemeFilter);
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
			keyInInputByName("name", ascScheme,"ASC Scheme");
			doClick(DataMaintenanceMap.gethcpcsCodeMaster());
			driverDelay(100);
			doDropdownSelectUsingOptionTextWithelement(DataMaintenanceMap.gethcpcsCodeMasterDrpdwn(),
					hcpcCodeMaster);
			doClick(DataMaintenanceMap.getapplyChargesRadioBtn());
			doClickButtons("ASC Scheme", "Select");
			selectFormItem("# 02 Charge Item Svc","");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(ascSchemeFilter);
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
			ascGroupName="Group"+ascScheme;
			keyInInputByName("name", ascGroupName, "New ASC Group");
			doClickButtons("New ASC Group", "Select");
			ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
			doClickButtons("Add HCPCS Code", "Apply");
		
			doClickButtons("New ASC Group", "Save & Create New");
			doClickButtons("New ASC Group", "Cancel & Close");
			assertTextIsDisplayed(ascGroupName);
			ExtentReport.logPass("PASS", "test04AddASCGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddASCGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditASCGroups() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedName="Updated"+ascScheme;
			keyInInputByName("name", updatedName, ascGroupName);
			doClickButtons(ascGroupName, "Save");
			doClickButtons(updatedName, "Save & Close");
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test05EditASCGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditASCGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteASCGroups() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test06DeleteASCGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteASCGroups", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07DeleteASCScheme() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteASCScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteASCScheme", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
