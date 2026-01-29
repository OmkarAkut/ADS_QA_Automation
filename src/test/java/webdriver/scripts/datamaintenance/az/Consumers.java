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
public class Consumers extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozConsumers= "Consumers";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String entity="0001 PRIVATE PAY PENDING ";
	static String[] consumerFilter= {"Consumer Number","Is","Equal To",code};
	static String date;
	static String ssnCode="333"+code;
	static String homePhoneNumber="44"+code;
	static String workPhoneNumber="55"+code;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Consumers", "webdriver.scripts.datamaintenance.az",
				"Consumers");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozConsumers);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void addConsumer() throws Throwable {
		keyInInputByName("consumerNumber", code, "Consumer");
		keyInInputByName("ssn", ssnCode, "Consumer");
		date=javaGetCurrentDate("MM/dd/yyy");
		keyInInputByName("originalEnrollmentDate", date, "Consumer");
		doClick(DataMaintenanceMap.getethnicityCode());
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getethnicityDropdown(), "11 ETHNICITY1 2");
		keyInInputByName("birthDate", date, "Consumer");
		doClick(DataMaintenanceMap.getbirthDateCheckbox());
		keyInInputByName("homePhoneNumber", homePhoneNumber, "Consumer");
		keyInInputByName("workPhoneNumber",workPhoneNumber, "Consumer");
		
	}
	
	@Test
	public void test01GeneralTab() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addConsumer();
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
		driverDelay(10000);
			//Member History
			
			
//			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
//			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01GeneralTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01GeneralTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ConsumerHistoryTab() throws Throwable {
		try {
			navigateToTab("Consumer History");
			//Consumer History
			doClick(DataMaintenanceMap.getcosumnerHistoryNewBtn());
			waitForPresenceOfElement("//div[text()='New Consumer History Effective Period']");
			keyInInputByName("firstName", "firstName", "New Consumer History Effective Period");
			keyInInputByName("lastName", "lastName",  "New Consumer History Effective Period");
			doClick(DataMaintenanceMap.getgenderCode());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getgenderDropdown(), "11 Gender1");
			keyInInputByName("street1", "street1", "New Consumer History Effective Period");
			keyInInputByName("street2", "street2", "New Consumer History Effective Period");
			keyInInputByName("city", "city", "New Consumer History Effective Period");
			keyInInputByName("state", "state", "New Consumer History Effective Period");
			keyInInputByName("zipcode", "zipcode", "New Consumer History Effective Period");
			keyInInputByName("county", "county", "New Consumer History Effective Period");
			keyInInputByName("country", "country", "New Consumer History Effective Period");
			doClick("(//span[text()='Primary Care Practitioner']//following::span[text()='Select'])[1]");
			waitForPresenceOfElement("//div[text()='00000  Goertz, Maggie']");
			selectFormItem("00000  Goertz, Maggie", "");
			doClick("(//span[text()='Primary Care Practitioner']//following::span[text()='Select'])[2]");
			waitForPresenceOfElement("//div[text()='1SM1 QA PPO']");
			selectFormItem("1SM1 QA PPO", "");
			doClick("(//span[text()='Primary Care Practitioner']//following::span[text()='Select'])[3]");
			waitForPresenceOfElement("//div[text()='0000 PRIVATE PAY']");
			selectFormItem("0000 PRIVATE PAY", "");
			doClick("//div[text()='Other Fields']");
			keyInInputByName("textField1", "textField1", "New Consumer History Effective Period");
			keyInInputByName("floatField1", "2.5", "New Consumer History Effective Period");
			keyInInputByName("date1", date, "New Consumer History Effective Period");
			keyInInputByName("numberField1", "123", "New Consumer History Effective Period");
			doClickButtons("New Consumer History Effective Period", "Continue");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
		driverDelay(10000);
			doClick(DataMaintenanceMap.getcosumnerHistoryEditBtn());
			doClick("//input[@name='startDatechk']");
			doClick("//input[@name='endDatechk']");
			doClickButtons("Consumer History Effective Period", "Continue");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			driverDelay(10000);
			assertTextIsDisplayed(date);
			ExtentReport.logPass("PASS", "test02ConsumerHistoryTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ConsumerHistoryTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03MemberHistoryTab() throws Throwable {
		try {
			navigateToTab("Member History");
			doClick(DataMaintenanceMap.getmemberHistoryNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getempSubgroupSelectBtn());
			doClick(DataMaintenanceMap.getempSubgroupSelectBtn());
			selectFormItem("0001 001 002 SD", "");
			doClick(DataMaintenanceMap.getmemberDesignation());
			selectFormItem("111 One the codess", "");
			expandPanel("Providers");
			assertElementIsDisplayedWithXpath("(//span[text()='Primary Care Practitioner']//following::span[text()='Select'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Care Delivery Network']//following::span[text()='Select'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Care Delivery Facility']//following::span[text()='Select'])[1]");
			collapsePanel("Providers");
			expandPanel("Medicare");
			collapsePanel("Medicare");
			expandPanel("Medicare");
			collapsePanel("Medicare");
			expandPanel("Medicare Part D");
			collapsePanel("Medicare Part D");
			expandPanel("Medicare Other");
			collapsePanel("Medicare Other");
			expandPanel("Selected Riders");
			collapsePanel("Selected Riders");
			expandPanel("Other Fields");
			collapsePanel("Other Fields");
			doClickButtons("New Member History", "Continue");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			driverDelay(10000);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed("0001 001 002 SD");
			doClick(DataMaintenanceMap.getcosumnerHistoryEditBtn());
			doClickButtons("Consumer History Effective Period", "Continue");
			ExtentReport.logPass("PASS", "test03MemberHistoryTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03MemberHistoryTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04MedicalRecordsTab() throws Throwable {
		try {
			navigateToTab("Medical Records");
			doClick(DataMaintenanceMap.getmedicalRecordsNewBtn1());
			waitForElementToBeVisible(DataMaintenanceMap.getmedicalRecordsNewBtn1());
			doClick(DataMaintenanceMap.getcareDeliverySelectBtn());
			selectFormItem("0000 PRIVATE PAY", "");
			keyInInputByName("medicalRecordNumber", code, "New Medical Records");
			doClickButtons("New Medical Records", "Continue");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
		driverDelay(10000);
		assertTextIsDisplayed("0000 PRIVATE PAY");
			ExtentReport.logPass("PASS", "test04MedicalRecordsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04MedicalRecordsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05OtherFieldsTab() throws Throwable {
		try {
			navigateToTab("Other Fields");
			keyInInputByName("textField1", "textField1", "Consumer");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
		driverDelay(10000);
			CimHelper.checkElements(DataMaintenanceMap.getsaveCloseBtn());
			driverDelay(10000);
			ExtentReport.logPass("PASS", "test05OtherFieldsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05OtherFieldsTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteMedicalRecords() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			waitForPresenceOfElement("//div[contains(@id,'filterwindow')][text()='Filter ']");
			doFilterCreate(consumerFilter);
			doClick(DataMaintenanceMap.getazEditBtn());
			driverDelay(10000);
			navigateToTab("Medical Records");
			driverDelay();
			deleteScenario(DataMaintenanceMap.getmedicalRecordsDeleteBtn(),DataMaintenanceMap.getokDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			
			
			
			ExtentReport.logPass("PASS", "test06DeleteMedicalRecords");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06DeleteMedicalRecords", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteMemberHistory() throws Throwable {
		try {
			navigateToTab("Member History");
			driverDelay();
			deleteScenario(DataMaintenanceMap.getmemberHistoryDeleteBtn(),DataMaintenanceMap.getokDeleteBtn());
			navigateToTab("Consumer History");
			driverDelay();
			deleteScenario(DataMaintenanceMap.getcosumnerHistoryDeleteBtn(),DataMaintenanceMap.getokDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteMemberHistory");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteMemberHistory", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08DeleteConsumer() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getsaveCloseBtn());
			driverDelay(10000);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getmessageboxDeleteBtn());
			driverDelay();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test08DeleteConsumer");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08DeleteConsumer", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
