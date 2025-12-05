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
public class ChargeableActivityRevenueCodeMappingSchemes extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozChargeableActivityRevenueCodeMappingSchemes = "Chargeable Activity - Revenue Code Mapping Schemes";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String chargeMaster="200 Southgate Charge Master";
	static String[] revenueCodeMappingFilter= {"Name","Is","Equal To",name};
	static String revenueCodeMaster="1833REV ASESC-1833 REV CODES";
	static String updatedName;
	static String azName="Chargeable Activity-Revenue Code Mapping Scheme";
	static String chargeableActivity="U DM 3645 1012806 NONE (D) NEUROPSYCH ASSESS";
	static String revenueCode="0100  ALL INCL R&B PLUS ANCIL";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ChargeableActivityRevenueCodeMappingSchemes", "webdriver.scripts.datamaintenance.maintaindata",
				"ChargeableActivityRevenueCodeMappingSchemes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozChargeableActivityRevenueCodeMappingSchemes);
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
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrevenueCodeMasterDropdown(), chargeMaster);
		driver.findElement(By.name("revCode")).click();
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getrevenuCodeDropdown(), revenueCodeMaster);
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addHCPCSScheme();
			CimHelper.checkElements(DataMaintenanceMap.getsaveCreateNew());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(revenueCodeMappingFilter);
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
			doFilterCreate(revenueCodeMappingFilter);
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
			doClick(DataMaintenanceMap.getrevenueCodeSelectBtn());
			selectFormItem(revenueCode, "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("3645");
			assertTextIsDisplayed("INPATIENT REHAB SERVICES");
			assertTextIsDisplayed("1012806");
			assertTextIsDisplayed("(D) NEUROPSYCH ASSESS");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			assertTextIsDisplayed("0100");
			assertTextIsDisplayed("ALL INCL R&B PLUS ANCIL");
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
			doClick(DataMaintenanceMap.getrevenueCodeSelectBtn());
			selectFormItem("0101  ALL INCL R&B", "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("3645");
			assertTextIsDisplayed("INPATIENT REHAB SERVICES");
			assertTextIsDisplayed("1012806");
			assertTextIsDisplayed("(D) NEUROPSYCH ASSESS");
			assertTextIsDisplayed("NONE");
			assertTextIsDisplayed("U");
			assertTextIsDisplayed("0101");
			assertTextIsDisplayed("ALL INCL R&B");
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
