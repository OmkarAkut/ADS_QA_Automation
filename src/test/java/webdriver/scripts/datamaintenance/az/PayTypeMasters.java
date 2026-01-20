package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PayTypeMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozActivityStatisticMaster = "Pay Type Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] payTypeMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String payName="Pay"+name;
	static String payCode="PAY"+code;
	static String payShortName="Short Name";
	static String typeOfHr="Regular";
	static String accumHr="No";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PayTypeMasters", "webdriver.scripts.datamaintenance.az",
				"PayTypeMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozActivityStatisticMaster);
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
			addNewScenario(code,name,null,DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(),null,
					null,null,"Save & Create New");
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(payTypeMasterFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditPayTypeMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("code", payCode, "Pay Type");
			keyInInputByName("description", payName, "Pay Type");
			keyInInputByName("columnLabel", payShortName, "Pay Type");
			clickCheckboxByName("payTypeTypeCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.gettypeOfHrDrpdwn(), typeOfHr);
			clickCheckboxByName("accumulateHours");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getaccumHrDrpdwn(), accumHr);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed(payName);
			assertTextIsDisplayed(payCode);
			assertTextIsDisplayed(payShortName);
			assertTextIsDisplayed(accumHr);
			ExtentReport.logPass("PASS", "test02ValidateEditPayTypeMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditPayTypeMaster", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditPayType() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("accumulateHours");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getaccumHrDrpdwn(), "Yes");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("Yes");
			ExtentReport.logPass("PASS", "test03ValidateEditPayType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditPayType", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeletePayType() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04DeletePayType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeletePayType", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeletePayTypeMaster() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeletePayTypeMaster");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeletePayTypeMaster", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
