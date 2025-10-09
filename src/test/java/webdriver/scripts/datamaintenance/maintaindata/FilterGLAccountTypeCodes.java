package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilterGLAccountTypeCodes extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageGLAccMaster = "GL Account Masters";
	static String currentDateTimeCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateTimeCode.replaceAll("\\W", "");
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String glName="GL Master"+currentDateTime;
	static String[] glAccType={"MS Medical Supplies","EB Employee Benefits","SW Salaries & Wages"};//note add single space inbetween words there is issue with text in the dropdown options
	static String updateGLAccType="IR Inpatient Revenue";
	static String[] filterGLAccount= {"Code","Is","Equal To",code};
	static int accountCodes;
	/** Customer Issues: Automated test script for ADS-18668 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("FilterGLAccountTypeCodes", "webdriver.scripts.datamaintenance.maintaindata",
				"FilterGLAccountTypeCodes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageGLAccMaster);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01AddGLAccountMaster_ADS_18668() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getGlAcctMasterNewBtn());
			keyInInputText(code, DataMaintenanceMap.getinputCode());
			keyInInputText(glName, DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.getGlAcctMasterSaveBtn());
			doClick(DataMaintenanceMap.getGlAcctMasterSaveCloseBtn());
     		ExtentReport.logPass("PASS", "test01AddGLAccountMaster_ADS_18668");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddGLAccountMaster_ADS_18668", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddGLAccountCodes_ADS_18668() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getGlAcctMasterFilterBtn());
			doFilterCreate(filterGLAccount);
			assertTextIsDisplayed(glName);
			doClick(DataMaintenanceMap.getGlAcctMasterEditBtn());
			doClick(DataMaintenanceMap.getGlAcctCodesNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getGlAcctCode());
			for(int i=0;i<glAccType.length;i++) {
				keyInInputText(code,DataMaintenanceMap.getGlAcctCode());
				keyInInputText(glName, DataMaintenanceMap.getGlAcctName());
				doClick(DataMaintenanceMap.getGlAccType());
				doClick("//li[normalize-space(text())='" + glAccType[i] + "']");
				doClick(DataMaintenanceMap.getGlAccTypeSaveCreateNewBtn());
			}
			doClick(DataMaintenanceMap.getGlAccTypeCancelCloseBtn());
			accountCodes=DataMaintenanceMap.getGlAcctCodesGridList().size();
			ExtentReport.logPass("PASS", "test02AddGLAccountCodes_ADS_18668");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddGLAccountCodes_ADS_18668", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03FilterGLAccountCodes_ADS_18668() throws Throwable {
		try {
			for(int i=0;i<glAccType.length;i++) {
				doClick(DataMaintenanceMap.getGlAcctCodesFilterBtn());
				doClick(DataMaintenanceMap.getGlAccCodefieldDrp());
				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccCodefieldDrpList(), "GL Account Type Code");
				doClick(DataMaintenanceMap.getGlAccOpfieldDrp());
				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccCodeOpDrpList(), "Is");
				doClick(DataMaintenanceMap.getGlAccConditionDrp());
				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccCodeCondDrpList(), "Equal To");
				doClick(DataMaintenanceMap.getGlAccValueList());
				doClick("//li[normalize-space(text())='" + glAccType[i] + "']");
				doClick(DataMaintenanceMap.getGlAccCodeAddBtn());
				assertTextIsDisplayed("Filter to Match These Criteria 1/"+accountCodes+"");//Depends on the number of Account codes in grid
				doClick(DataMaintenanceMap.getGlAccCodeApplyFilterBtn());
				assertElementIsDisplayed("//div[normalize-space(text())='" + glAccType[i] + "']");
				doClick(DataMaintenanceMap.getGlAcctCodesClearFilterBtn());
				driverDelay(200);
			}
			ExtentReport.logPass("PASS", "test03FilterGLAccountCodes_ADS_18668");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03FilterGLAccountCodes_ADS_18668", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04UpdateGLaccountType_ADS_4511() throws Throwable {
		try {
			doClick("//div[normalize-space(text())='" + glAccType[0] + "']");
			doClick(DataMaintenanceMap.getGlAcctCodesEditBtn());
			doClick(DataMaintenanceMap.getGlAccType());
			doClick("//li[normalize-space(text())='" + updateGLAccType + "']");
			waitForElementToBeVisible(DataMaintenanceMap.getGlAcctUpdateWarning());
			doClick(DataMaintenanceMap.getGlAcctUpdateWarningOkBtn());
			doClick(DataMaintenanceMap.getGlAccTypeSaveCloseBtn());
			assertElementIsDisplayed("//div[normalize-space(text())='" + updateGLAccType + "']");
			ExtentReport.logPass("PASS", "test04UpdateGLaccountType_ADS_4511");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04UpdateGLaccountType_ADS_4511", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteGLAccountCodes_ADS_18668() throws Throwable {
		try {
			for(int i=1;i<=DataMaintenanceMap.getGlAcctCodesGridList().size();i++) {
				doClick("((//h1[text()='GL Account Codes']//following::div[contains(@id,'dynamicGrid')])[3]//table)[1]");
				doClick(DataMaintenanceMap.getGlAcctCodesDeleteBtn());
				doClick(DataMaintenanceMap.getwarningDeleteBtn());
				waitForDisplayedSpinnerToEnd();
			}
			doClick(DataMaintenanceMap.getGlAcctMasterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04DeleteGLAccountCodes_ADS_18668");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteGLAccountCodes_ADS_18668", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteGLAccountMaster_ADS_18668() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getGlAcctMasterDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test05DeleteGLAccountMaster_ADS_18668");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteGLAccountMaster_ADS_18668", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
