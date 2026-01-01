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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Populations extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPopulations = "Populations";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] populationFilter= {"Name","Is","Equal To",name};
	static String source="Member History";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Populations", "webdriver.scripts.datamaintenance.maintaindata",
				"Populations");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPopulations);
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
			keyInInputByName("name", name, "Population");
			clickCheckboxByName("sourceClass");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Population']//following::ul/li[text()='Member History']/..")), "Member History");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(populationFilter);
			assertTextIsDisplayed(name);
			
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditPopulation() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClick(DataMaintenanceMap.getcriteriaFielldDrpPopulation());
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Population']//following::ul/li[text()='Benefit Plan Code']/..")), "Care Delivery Facility Code");
			keyInInputByName("value", "10", "Population");
			doClickButtons("Population", "Add");
			doClick("//div[text()='Edit']");
			keyInInputByName("value", "20", "Population");
			doClickButtons("Population", "Update");
			String criteria=driver.findElement(By.xpath("(//div[text()='Filter to Match These Criteria']//following::table//td/div)[2]")).getText();
			if(criteria.equals("[Care Delivery Facility Code] is equal to '20'")) {
				assertTrue(printout);
			}
			doClick(DataMaintenanceMap.getremoveBtn());
			doClick(DataMaintenanceMap.getcriteriaFielldDrpPopulation());
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Population']//following::ul/li[text()='Benefit Plan Code']/..")), "Care Delivery Facility Code");
			keyInInputByName("value", "10", "Population");
			doClickButtons("Population", "Add");
			doClick(DataMaintenanceMap.getremoveAllBtn());
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateEditPopulation");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditPopulation", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeletePopulation() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeletePopulation");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeletePopulation", driver, e);
			fail(e.getMessage());
		}
	}
	
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
