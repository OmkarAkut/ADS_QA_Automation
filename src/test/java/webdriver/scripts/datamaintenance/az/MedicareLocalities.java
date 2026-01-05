package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.assertTrue;
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
public class MedicareLocalities extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozMedicareLocalities = "Medicare Localities";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String code = currentDateCode.replaceAll("\\W", "");
	static String[] medicareLocalitiesFilter= {"Name","Is","Equal To",name};
	static String[] medicareFilter= {"Name","Is","Equal To",name};
	static String localityNumber="6";
	static String updatedName="Updated"+name;
	static String[] updatedMedicareLocalitiesFilter= {"Name","Is","Equal To",updatedName};
	static String medicare;
	//Issue ADS-23702
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("MedicareLocalities", "webdriver.scripts.datamaintenance.maintaindata",
				"MedicareLocalities");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozMedicareLocalities);
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
			keyInInputByName("name", name, "Medicare Locality");
			doClickButtons("Medicare Locality", "New");
			keyInInputByName("code", code, "Medicare Carrier");
			keyInInputByName("name", name, "Medicare Carrier");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			clickCheckboxByName("medicareCarrierCode");
			medicare=code+" "+name;
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='Medicare Locality']//following::ul/li[text()='"+medicare+"']/..)")), medicare);
			doClick(DataMaintenanceMap.getlocalityNumberUpBtn());
			if(DataMaintenanceMap.getlocalityNumberInput().getAttribute("aria-valuenow").equals("1")) {
				assertTrue(printout);
			}else {
				fail();}
			doClick(DataMaintenanceMap.getlocalityNumberDwnBtn());
			if(DataMaintenanceMap.getlocalityNumberInput().getAttribute("aria-valuenow").equals("0")) {
				assertTrue(printout);
			}else {
				fail();}
			keyInInputByName("localityNumber", localityNumber, "Medicare Locality");
			keyInInputByName("name", name, "Medicare Locality");
			DataMaintenanceMap.getciteriaZipCode().sendKeys("93010-93012");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(medicareLocalitiesFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed(localityNumber);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02EditSaveCloseMedicareLocality() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", updatedName, "Medicare Locality");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test02EditSaveCloseMedicareLocality");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditSaveCloseMedicareLocality", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test03DeleteMedicareLocality() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(updatedMedicareLocalitiesFilter);
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteMedicareLocality");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteMedicareLocality", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteMedicare() throws Throwable {
		try {
			selectMaintainDataAtoZ("Medicare Carriers");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(medicareFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteMedicare");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteMedicare", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
