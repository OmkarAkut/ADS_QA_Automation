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
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChargeableActivityHCPCSCodeMappingSchemes extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozChargeableActivityHCPCSCodeMappingSchemes = "Chargeable Activity - HCPCS Code Mapping Schemes";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String chargeMaster="200 Southgate Charge Master";
	static String[] hcpcCodeMappingFilter= {"Name","Is","Equal To",name};
	static String hcpcsCodeMaster="99281 Emergency dept unit for surgery";
	static String updatedName;
	static String azName="Chargeable Activity-HCPCS Code Mapping Scheme";
	static String chargeableActivity="U DM 3645 1012806 NONE (D) NEUROPSYCH ASSESS";
	static String hcpcsActivity="99281 Emergency dept unit";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ChargeableActivityHCPCSCodeMappingSchemes", "webdriver.scripts.datamaintenance.maintaindata",
				"ChargeableActivityHCPCSCodeMappingSchemes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozChargeableActivityHCPCSCodeMappingSchemes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void addHCPCSScheme() throws Throwable {
		keyInInputByName("name", name, azName);
		keyInInputByName("code", code, azName);
		driver.findElement(By.name("chargeCode")).click();
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getchargeMasterDropdown(), chargeMaster);
		driver.findElement(By.name("hcpcsCode")).click();
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcodechargeMasterHcpcsDropdown(), hcpcsCodeMaster);
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addHCPCSScheme();
			CimHelper.checkElements(DataMaintenanceMap.getsaveCreateNew());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(hcpcCodeMappingFilter);
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
			doClick(DataMaintenanceMap.getazNewBtn());
			addHCPCSScheme();
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(hcpcCodeMappingFilter);
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
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", "Updated"+name, azName);
			CimHelper.checkElements(DataMaintenanceMap.getsaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			String[] hcpcCodeMappingFilterUpdate= {"Name","Is","Equal To","Updated"+name};
			doFilterCreate(hcpcCodeMappingFilterUpdate);
			assertTextIsDisplayed("Updated"+name);
			doClick(DataMaintenanceMap.getazEditBtn());
			
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test04AddAMappings() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClick(DataMaintenanceMap.getchargeModifierSelectBtn());
			selectFormItem(chargeableActivity, "");
			doClick(DataMaintenanceMap.gethcpcsModifierSelectBtn());
			selectFormItem(hcpcsActivity, "");
			doClick(DataMaintenanceMap.gethcpcsModCode());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmodifierDropdown(), "1SM1");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("3645");
			assertTextIsDisplayed("INPATIENT REHAB SERVICES");
			assertTextIsDisplayed("1012806");
			assertTextIsDisplayed("(D) NEUROPSYCH ASSESS");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			assertTextIsDisplayed("99281");
			assertTextIsDisplayed("Emergency dept visit");
			assertTextIsDisplayed("1SM1");
			assertTextIsDisplayed("hcpcs modifer1");
			ExtentReport.logPass("PASS", "test04AddAMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddAMappings", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditMappings() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.gethcpcsModCode());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmodifierDropdown(), "1SM2");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("3645");
			assertTextIsDisplayed("INPATIENT REHAB SERVICES");
			assertTextIsDisplayed("1012806");
			assertTextIsDisplayed("(D) NEUROPSYCH ASSESS");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			assertTextIsDisplayed("99281");
			assertTextIsDisplayed("Emergency dept visit");
			assertTextIsDisplayed("1SM2");
			assertTextIsDisplayed("hcpcs modifer1");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test05EditMappings");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditMappings", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteChargeActivittyScheme() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test06DeleteChargeActivittyScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteChargeActivittyScheme", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
