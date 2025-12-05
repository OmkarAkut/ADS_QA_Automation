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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BusinessOfficeContacts extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozBusinessOfficeContacts = "Business Office Contacts";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String firstName="FName"+currentDateTime;
	static String lastName="LName"+currentDateTime;
	static String phoneNumber=code;
	static String[] businessOffice= {"First Name","Is","Equal To",firstName};
	static String[] updatedBusinessOffice;
	static String updatedName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("BusinessOfficeContacts", "webdriver.scripts.datamaintenance.maintaindata",
				"BusinessOfficeContacts");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozBusinessOfficeContacts);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void addDetails() throws Throwable {
		keyInInputByName("firstName", firstName, "New Contact");
		keyInInputByName("lastName", lastName, "New Contact");
		keyInInputByName("workPhoneNumber", code, "New Contact");
		keyInInputByName("faxNumber", code, "New Contact");
		keyInInputByName("organization", "ADS", "New Contact");
		driver.findElement(By.name("contactType")).click();
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcontactTypeDrpdown(), "ACTUAL Signed contracts");
		keyInInputByName("emailAddress", "ads@gmail.com", "New Contact");
		driver.findElement(By.name("sexCode")).click();
		doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getgenderDrpdown(), "M MALE");
		keyInInputByName("street1", "Street", "New Contact");
		keyInInputByName("street2", "street2", "New Contact");
		keyInInputByName("zipCode", code, "New Contact");
		keyInInputByName("city", "city", "New Contact");
		keyInInputByName("state", "state", "New Contact");
		keyInInputByName("county", "county", "New Contact");
		keyInInputByName("country", "country", "New Contact");
	}
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addDetails();
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(businessOffice);
			assertTextIsDisplayed(firstName);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveClose() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addDetails();
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(businessOffice);
			doClick(DataMaintenanceMap.getazEditBtn());
			updatedName="Updated"+firstName;
			keyInInputByName("firstName", updatedName, "Business Office Contact");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			String[] updatedBusinessOffice={"First Name","Is","Equal To",updatedName};
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(updatedBusinessOffice);
			assertTextIsDisplayed(updatedName);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveClose");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveClose", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
