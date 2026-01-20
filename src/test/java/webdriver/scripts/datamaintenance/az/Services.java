package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Services extends AzHelper {

	static DataMaintenanceMap dmMap;
	final static String aTozServices = "Services";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String unitType="Patient Day";
	static String[] unitTypeEncounterOptions= {"<Either>","Stay","Visit"};
	static String[] unitTypePatientDayOptions= {"Stay","Visit"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Services", "webdriver.scripts.datamaintenance.az",
				"Services");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozServices);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateStayOptionsForUnitType_Encounter() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
//			keyInInputByName("name", name,"Service");	
//			clickCheckboxByName("unitType");
//			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getunitTypeDrpOptions(), "Encounter");
			driverDelay();
			clickCheckboxByName("stayOrVisit");
			for(String option:unitTypeEncounterOptions) {
				System.out.println(option);
				assertElementIsDisplayedWithXpath("//div[text()='Service']//following::ul/li[text()='"+option+"']");
			}
			
			ExtentReport.logPass("PASS", "test02ValidateStayOptionsForUnitType_Encounter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateStayOptionsForUnitType_Encounter", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateStayOptionsForUnitType_PatientDay() throws Throwable {
		try {
			clickCheckboxByName("unitType");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getunitTypeDrpOptions(), "Patient Day");
			clickCheckboxByName("stayOrVisit");
			for(String option:unitTypePatientDayOptions) {
				System.out.println(option);
				assertElementIsDisplayedWithXpath("//div[text()='Service']//following::ul/li[text()='"+option+"']");
				if(!(driver.findElements(By.xpath("//div[text()='Service']//following::ul/li[text()='<Either>']")).size()>0)) {
					assertTrue(true);
				}else {fail();}
			}
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateStayOptionsForUnitType_PatientDay");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateStayOptionsForUnitType_PatientDay", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03SaveCreateNew_AddService() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			driverDelay();
			clickCheckboxByName("feeScheduleType");
			driverDelay();
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfeeScheduleTypeOptions(), "AP DRG");
		
			assertElementIsDisplayedWithXpath("//input[@name='msdrgIndex'][@aria-disabled='true']");
			assertElementIsDisplayedWithXpath("//input[@name='feeScheduleTemplateId'][@aria-disabled='true']");
			clickCheckboxByName("feeScheduleMasterId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Service']//following::ul/li[text()='APDRG APDRG Fee Schedule']/..")), "APDRG APDRG Fee Schedule");
			clickCheckboxByName("feeScheduleTemplateId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Service']//following::ul/li[text()='APDRG Fee Schedule 2008']/..")), "APDRG Fee Schedule 2008");
			
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03SaveCreateNew_AddService");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveCreateNew_AddService", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04SaveCreateNew_AddService() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name,"Service");
			clickCheckboxByName("feeScheduleType");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfeeScheduleTypeOptions(), "MSDRG");
			clickCheckboxByName("msdrgIndex");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getmsdrgOptions(), "MSDRG1CODE");
			clickCheckboxByName("feeScheduleMasterId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfeeScheduleMasterOptions(), "ASESC2174");
			clickCheckboxByName("feeScheduleTemplateId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfeeScheduleOptions(), "ASESC-2174 txt");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			clickCheckboxByName("field");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getfieldOptions(), "Name");
			clickCheckboxByName("operator");
			driver.findElement(By.name("valuefield")).sendKeys(name);
			doClick(DataMaintenanceMap.getfilterAddButton());
			doClick(DataMaintenanceMap.getfilterApplyFilterButton());
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test03SaveCreateNew_AddService");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveCreateNew_AddService", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05EditServices() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClick(DataMaintenanceMap.getencountersDRGFeeSchedule());
			doClick(DataMaintenanceMap.getencountersDRGInFeeSchedule());
			doClick(DataMaintenanceMap.getcriteriaFielldDrp());
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Service']//following::ul/li[text()='Encounter Level Estimated Charges 14']/..")), "Encounter Level Estimated Charges 14");
			keyInInputByName("value", "10", "Service");
			doClickButtons("Service", "Add");
			doClick("//div[text()='Edit']");
			keyInInputByName("value", "20", "Service");
			doClickButtons("Service", "Update");
			assertElementIsDisplayedWithXpath("//div[text()='[Encounter Level Estimated Charges 14] is equal to 20']");
			doClick(DataMaintenanceMap.getremoveBtn());
			doClick(DataMaintenanceMap.getcriteriaFielldDrp());
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Service']//following::ul/li[text()='Encounter Level Estimated Charges 14']/..")), "Encounter Level Estimated Charges 14");
			keyInInputByName("value", "10", "Service");
			doClickButtons("Service", "Add");
			doClick(DataMaintenanceMap.getremoveAllBtn());
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test05EditServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05EditServices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteServices() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test06DeleteServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteServices", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}	
}
