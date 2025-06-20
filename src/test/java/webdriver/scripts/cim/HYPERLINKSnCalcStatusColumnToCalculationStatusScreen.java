package webdriver.scripts.cim;

import static org.junit.Assert.assertTrue;
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
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20057 **/
public class HYPERLINKSnCalcStatusColumnToCalculationStatusScreen extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType="Activity Volume Calc Scenario: ADS-262 Vol Calc";
	private static DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("HYPERLINKSnCalcStatusColumnToCalculationStatusScreen", "webdriver.scripts.cim",
				"HYPERLINKSnCalcStatusColumnToCalculationStatusScreen");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01Validate_Calc_Hyperlink_20057() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			validateCalcStatus("COMPLETED", cimScenarioCreate);
			ExtentReport.logPass("PASS", "test01Validate_Calc_Hyperlink_20057");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_Calc_Hyperlink_20057", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_Clickhyperlink_CalcStatus_20057() throws Throwable {
		try {
			doClick("//div[text()='"+cimScenarioCreate+"']//following::a[contains(@id,'cimStatusLink')][text()='COMPLETED']");
			assertElementIsDisplayed("(//div[text()='"+cimScenarioCreate+"']//following::div[contains(@class,'x-grid-cell-inner')][text()='COMPLETED'])[1]");
			assertTheElementIsDisabled(cimMap.getstatusFilterBtn(), printout);
			assertElementIsEnabled(cimMap.getstatusClearFilterBtn(), printout);
			doClick(cimMap.getstatusClearFilterBtn());
			waitForDisplayedSpinnerToEnd();
			assertTheElementIsDisabled(cimMap.getstatusClearFilterBtn(), printout);
			if(driver.findElements(By.xpath("((//div[text()='Calculation Status']//following::div[@class='x-grid-item-container']/table)//tr/td[2]/div)")).size()>1) {
				assertTrue(printout);
			}else {fail();}
			assertElementIsDisplayed("//span[text()=' Close Calculation Status']");
			doClosePageOnLowerBar("Calculation Status");
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_Filter_CalcStatus_20057");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_Filter_CalcStatus_20057", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
	
		ExtentReport.report.flush();

	}
}
