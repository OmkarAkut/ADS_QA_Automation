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
//ADS-23747
public class Practitioners extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPractitioners = "Practitioners";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String firstName="FName"+currentDateTime;
	static String lastName="LName"+currentDateTime;

	static String chargeMaster="350 Island Park Charge Master";
	static String[] practitionerFilter= {"First Name","Is","Equal To",firstName};
	static String[] updatedPractitionerFilter= {"First Name","Is","Equal To","UpdatedFirstName"};
	static String speciality="003 Allergey & Immunology";
	static String primarySpeciality="005 Anesthesiology";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Practitioners", "webdriver.scripts.datamaintenance.az",
				"Practitioners");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPractitioners);
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
			keyInInputByName("code", code, aTozPractitioners);
			keyInInputByName("firstName", firstName, aTozPractitioners);
			keyInInputByName("lastName", lastName, aTozPractitioners);
			clickCheckboxByName("practSpecialtyObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getspecialityDrpdwn(), speciality);
			clickCheckboxByName("primCareSpecialtyObjectId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getprimarySpecialityDrpdwn(), primarySpeciality);
			expandPanel("User Defined Fields");
			collapsePanel("User Defined Fields");
			expandPanel("Offices");
			doClickButtons("Offices", "Select");
			waitForFormDialog("Offices");
			doClickButtons("Offices", "Cancel");
			collapsePanel("Offices");
			expandPanel("Primary Care Patient Panel");
			collapsePanel("Primary Care Patient Panel");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			if(DataMaintenanceMap.getwarningCancelClose().size()>0) {
				doClick(DataMaintenanceMap.getwarningCancelClose().get(0));
			}
			
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(practitionerFilter);
			assertTextIsDisplayed(firstName);
			assertTextIsDisplayed(lastName);
			assertTextIsDisplayed(code);
			assertTextIsDisplayed(speciality);
			assertTextIsDisplayed(primarySpeciality);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditPractitioner() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("firstName", "UpdatedFirstName", aTozPractitioners);
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(updatedPractitionerFilter);
			assertTextIsDisplayed("UpdatedFirstName");
			ExtentReport.logPass("PASS", "test02ValidateEditPractitioner");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditPractitioner", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeletePractitioner() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03DeletePractitioner");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeletePractitioner", driver, e);
			fail(e.getMessage());
		}
	}
	
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
