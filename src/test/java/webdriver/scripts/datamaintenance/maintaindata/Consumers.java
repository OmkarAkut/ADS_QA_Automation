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
public class Consumers extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozConsumers= "Consumers";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String entity="0001 PRIVATE PAY PENDING ";
	static String[] commissionBrokerFilter= {"Name","Is","Equal To",name};
	static String date;
	static String ssnCode="333"+code;
	static String homePhoneNumber="44"+code;
	static String workPhoneNumber="55"+code;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CommissionTypes", "webdriver.scripts.datamaintenance.maintaindata",
				"CommissionTypes");
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
			waitForDisplayedSpinnerToEnd();
			navigateToTab("Consumer History");
			//Consumer History
			doClick(DataMaintenanceMap.getcosumnerHistoryNewBtn());
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
			selectFormItem("00000  Goertz, Maggie", "");
			doClick("(//span[text()='Primary Care Practitioner']//following::span[text()='Select'])[2]");
			selectFormItem("1SM1 QA PPO", "");
			doClick("(//span[text()='Primary Care Practitioner']//following::span[text()='Select'])[3]");
			selectFormItem("0000 PRIVATE PAY", "");
			doClick("//div[text()='Other Fields']");
			keyInInputByName("textField1", "textField1", "New Consumer History Effective Period");
			keyInInputByName("floatField1", "2.5", "New Consumer History Effective Period");
			keyInInputByName("date1", date, "New Consumer History Effective Period");
			keyInInputByName("numberField1", "123", "New Consumer History Effective Period");
			doClickButtons("New Consumer History Effective Period", "Continue");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getcosumnerHistoryEditBtn());
			doClick("//input[@name='startDatechk']");
			doClick("//input[@name='endDatechk']");
			doClickButtons("<Open> - <Open>,lastName,firstName", "Continue");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			waitForDisplayedSpinnerToEnd();
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01GeneralTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01GeneralTab", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ConsumerHistoryTab() throws Throwable {
		try {
			
			ExtentReport.logPass("PASS", "test02ConsumerHistoryTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ConsumerHistoryTab", driver, e);
			fail(e.getMessage());
		}
	}
	/*
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "New Code");
			keyInInputByName("code", code, "New Code");
			doClickButtons("New Code", "Select");
			selectFormItem(entity, "");
			CimHelper.checkElements(DataMaintenanceMap.getsaveBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(commissionBrokerFilter);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputText("Updated"+name, DataMaintenanceMap.getcommissionBrokerName());
			CimHelper.checkElements(DataMaintenanceMap.getsaveCloseBtn());
			String[] commissionBrokerAgencyFilterUpdate= {"Name","Is","Equal To","Updated"+name};
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(commissionBrokerAgencyFilterUpdate);
			assertTextIsDisplayed("0001  PRIVATE PAY PENDING");
			assertTextIsDisplayed("Updated"+name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test03ValidateEditSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteCommissionBroker() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());

			ExtentReport.logPass("PASS", "test04DeleteCommissionBroker");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteCommissionBroker", driver, e);
			fail(e.getMessage());
		}
	}
	*/
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
