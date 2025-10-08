package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class FilterGLAccountTypeCodes extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageAscSchemes = "GL Account Masters";
	static String currentDateTimeCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateTimeCode.replaceAll("\\W", "");
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String glName="GL Master"+currentDateTime;
	static String[] glAccType={"MS  Medical Supplies","EB  Employee Benefits","SW  Salaries & Wages"};
	static String[] filterGLAccount= {"Code","Is","Equal To",code};
	/** Customer Issues: Automated test script for ADS-18668 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("FilterGLAccountTypeCodes", "webdriver.scripts.datamaintenance.maintaindata",
				"FilterGLAccountTypeCodes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageAscSchemes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01AddGLAccountMaster_18668() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getGlAcctMasterNewBtn());
			keyInInputText(code, DataMaintenanceMap.getinputCode());
			keyInInputText(glName, DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.getGlAcctMasterSaveBtn());
			doClick(DataMaintenanceMap.getGlAcctCodesNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getGlAcctCode());
			for(int i=0;i<glAccType.length;i++) {
				keyInInputText(code,DataMaintenanceMap.getGlAcctCode());
				keyInInputText(glName, DataMaintenanceMap.getGlAcctName());
				doClick(DataMaintenanceMap.getGlAccType());
//				driver.findElement(By.name("accountType")).click();
				doClick("//li[text()='" + glAccType[i] + "']");
//				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccTypeOptions(), glAccType[i]);
				
//				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccTypeOptions(), glAccType[i]);
				doClick(DataMaintenanceMap.getGlAccTypeSaveCreateNewBtn());
				
			}
			doClick(DataMaintenanceMap.getGlAccTypeCancelCloseBtn());
			doClick(DataMaintenanceMap.getGlAcctMasterCancelCloseBtn());
			doClick(DataMaintenanceMap.getGlAcctMasterFilterBtn());
			doFilterCreate(filterGLAccount);
			assertTextIsDisplayed(glName);
			doClick(DataMaintenanceMap.getGlAcctMasterEditBtn());
			
			for(int i=0;i<glAccType.length;i++) {
				doClick(DataMaintenanceMap.getGlAcctCodesFilterBtn());
				doClick(DataMaintenanceMap.getGlAccCodefieldDrp());
				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccCodefieldDrpList(), "GL Account Type Code");
				doClick(DataMaintenanceMap.getGlAccOpfieldDrp());
				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccCodeOpDrpList(), "Is");
				doClick(DataMaintenanceMap.getGlAccConditionDrp());
				doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getGlAccCodeCondDrpList(), "Equal To");
				doClick(DataMaintenanceMap.getGlAccValueList());
				doClick("//li[text()='" + glAccType[i] + "']");
				doClick(DataMaintenanceMap.getGlAccCodeAddBtn());
				assertTextIsDisplayed("Filter to Match These Criteria 1/3");//Depends on the number of Account codes in grid
				doClick(DataMaintenanceMap.getGlAccCodeApplyFilterBtn());
				assertTextIsDisplayed(glAccType[i]);
				doClick(DataMaintenanceMap.getGlAcctCodesClearFilterBtn());
				driverDelay(200);
				
			}
			for(int i=1;i<=DataMaintenanceMap.getGlAcctCodesGridList().size();i++) {
				doClick("((//h1[text()='GL Account Codes']//following::div[contains(@id,'dynamicGrid')])[3]//table)[1]");
				doClick(DataMaintenanceMap.getGlAcctCodesDeleteBtn());
				doClick(DataMaintenanceMap.getwarningDeleteBtn());
				waitForDisplayedSpinnerToEnd();
			}
			doClick(DataMaintenanceMap.getGlAcctMasterCancelCloseBtn());
			doClick(DataMaintenanceMap.getGlAcctMasterDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			
			
			
			ExtentReport.logPass("PASS", "test01AddGLAccountMaster_18668");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddGLAccountMaster_18668", driver, e);
			fail(e.getMessage());
		}
	}
}
