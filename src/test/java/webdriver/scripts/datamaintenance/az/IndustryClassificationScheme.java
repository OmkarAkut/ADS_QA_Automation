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
public class IndustryClassificationScheme extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozIndustryClassificationScheme = "Industry Classification Schemes";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String code = currentDateCode.replaceAll("\\W", "");
	static String industryScheme="2010 HCFA";
	static String updatedScheme="Updated"+industryScheme;
	static String[] industriesFilter= {"Name","Is","Equal To",industryScheme};
	static String[] upIndustriesFilter= {"Name","Is","Equal To",updatedScheme};
	static String  type= "MSDRG1";
	static String updatedName="Updated"+name;
	static String groupCategory="01 DISEASES & DISORDERS OF THE NERVOUS SYSTEM";
	static String costWeight="31.2";
	static String GLOS="41.2";
	static String ALOS="51.2";
	static String threshold="61.2";
	//Cannot create scheme if its created for the effective date , so only edit scheme is automated
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("IndustryClassificationScheme", "webdriver.scripts.datamaintenance.az",
				"IndustryClassificationScheme");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozIndustryClassificationScheme);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01AddGroupCategory() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(industriesFilter);
			doClick(DataMaintenanceMap.getazEditBtn());
			expandPanel("Group Categories");
			driverDelay();
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("name", name, "Group Category");
			keyInInputByName("code", code, "Group Category");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01AddGroupCategory");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddGroupCategory", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditGroupCategory() throws Throwable {
		try {
			doClick("(//div[text()='"+name+"'])");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("name", updatedName, "Group Category");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test02EditSaveCloseIndustryClassificationScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditSaveCloseIndustryClassificationScheme", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test03DeleteGroupCategory() throws Throwable {
		try {
			doClick("(//div[text()='"+updatedName+"'])");
			doClickButtons("Group Categories", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsNotDisplayed("(//div[text()='"+updatedName+"'])");
			collapsePanel("Group Categories");
			ExtentReport.logPass("PASS", "test03DeleteIndustries");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteIndustries", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddGroups() throws Throwable {
		try {
			expandPanel("Groups");
			driverDelay();
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("name", name, "Groups");
			keyInInputByName("code", code, "Groups");
			clickCheckboxByName("categoryObjectId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Group']//following::ul/li[text()='"+groupCategory+"']/..")), groupCategory);
			keyInInputByName("costWeight", costWeight, "Group");
			keyInInputByName("glos", GLOS, "Group");
			keyInInputByName("alos", ALOS, "Group");
			keyInInputByName("outlier", threshold, "Group");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04AddGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05EditGroups() throws Throwable {
		try {
			doClick("(//div[text()='"+name+"'])");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("name", updatedName, "Group");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test05EditGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteGroup() throws Throwable {
		try {
			doClick("(//div[text()='"+updatedName+"'])");
			doClickButtons("Groups", "Delete");
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsNotDisplayed("(//div[text()='"+updatedName+"'])");
			collapsePanel("Groups");
			keyInInputByName("name", updatedScheme, "Industry Classification Scheme");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(upIndustriesFilter);
			assertTextIsDisplayed(updatedScheme);
//			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test06DeleteGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07ValidateSaveCloseIndustryClassificationScheme() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", industryScheme, "Industry Classification Scheme");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(industriesFilter);
			assertTextIsDisplayed(industryScheme);
			ExtentReport.logPass("PASS", "test07ValidateSaveCloseIndustryClassificationScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ValidateSaveCloseIndustryClassificationScheme", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
