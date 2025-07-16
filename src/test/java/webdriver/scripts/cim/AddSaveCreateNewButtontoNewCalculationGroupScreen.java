package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-21326 **/
public class AddSaveCreateNewButtontoNewCalculationGroupScreen extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("AddSaveCreateNewButtontoNewCalculationGroupScreen", "webdriver.scripts.cim",
				"AddSaveCreateNewButtontoNewCalculationGroupScreen");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01Validate_SaveAndCreateNew_ButtonDisabled_21326() throws Throwable {
		try {
			doClick(cimMap.getcimNewBtn());
			assertTheElementIsDisabled(cimMap.getcimSaveCreateNewBtn(), printout);
			cimMap.getcimName().sendKeys(Keys.chord(Keys.CONTROL, "a"));
			cimMap.getcimName().sendKeys(Keys.BACK_SPACE);
			cimMap.getcimName().sendKeys(cimScenarioCreate);
			assertTheElementIsDisabled(cimMap.getcimSaveCreateNewBtn(), printout);
			doClick("((//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div)[1]");
			doClick(cimMap.getcalcTypeSelectBtn());
			assertTheElementIsDisabled(cimMap.getcimSaveCreateNewBtn(), printout);
			doClick(cimMap.gethostLocation());
			doClick(cimMap.getcimSharedLoc());
			cimMap.getcimLocation().sendKeys("ADS-21326");
			driverDelay(200);
			assertTheElementIsEnabled(cimMap.getcimSaveCreateNewBtn());
			doClick(cimMap.getcimCancelCloseBtn());
			doClick(cimMap.getcancelCloseWarningBtn());
			ExtentReport.logPass("PASS", "test01Validate_SaveAndCreateNew_ButtonDisabled_21326");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_SaveAndCreateNew_ButtonDisabled_21326", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_SaveAndCreateNew_21326() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			assertTextIsDisplayed(cimScenarioCreate);
			ExtentReport.logPass("PASS", "test02Validate_SaveAndCreateNew_21326");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_SaveAndCreateNew_21326", driver, e);
			fail(e.getMessage());
		}
		
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		deleteCim();
		doClosePageOnLowerBar("Cost Integration Manager (CIM)");
		ExtentReport.report.flush();
	}
}
