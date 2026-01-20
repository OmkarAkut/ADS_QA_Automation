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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentOrganization extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPaymentOrganization = "Payment Organizations";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] paymentOrganizationFilter= {"Name","Is","Equal To",name};
	static String providerId="ID:"+code;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PaymentOrganization", "webdriver.scripts.datamaintenance.az",
				"PaymentOrganization");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPaymentOrganization);
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
			keyInInputByName("name", name, "Payment Organization");
			keyInInputByName("code", code, "Payment Organization");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(paymentOrganizationFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditPaymentOrganization() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("nationalProviderId", providerId, aTozPaymentOrganization);
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateEditPaymentOrganization");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditPaymentOrganization", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeletePaymentOrganization() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeletePaymentOrganization");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeletePaymentOrganization", driver, e);
			fail(e.getMessage());
		}
	}
	
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
