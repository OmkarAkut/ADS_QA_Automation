package webdriver.scripts.cim;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20409**/
public class SCHEDULECalculationfunctionality  extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	static String presetOption1=null;
	static String presetOption2=null;
	static String cimNextStartTime;
	static String formatCimNextStartTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
	
		ExtentReport.reportCreate("SCHEDULECalculationfunctionality", "webdriver.scripts.cim",
				"SCHEDULECalculationfunctionality");
		try {
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Cost Integration Manager");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01Validate_SchedulePresetOption_20409() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");			
			getPresetOption();
			ExtentReport.logPass("PASS", "test02Validate_CimUpdates_20409");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_CimUpdates_20409", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_HighlightPresetOption_20409() throws Throwable {
		try {
			doClick(cimMap.getcimCalculatePreset1Btn());
			assertElementIsDisplayedWithXpath("(//a[contains(@class,'CIMButtonOptions ')]/span)[1]/ancestor::a[contains(@class,'x-btn-pressed')]");
			doClick("//div[text()='Calculate "+cimScenarioCreate+"']//following::span[text()='Cancel & Close']/ancestor::a");
			//Validate Cancel & Close after selecting preset option
			assertElementIsNotDisplayed("//*[text()='Calculate "+cimScenarioCreate+"']");
			deleteCim();
			ExtentReport.logPass("PASS", "test02Validate_HighlightPresetOption_20409");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_HighlightPresetOption_20409", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03Validate_PresetOption1_20409() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");
			presetOption1=driver.findElement(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::a[contains(@class,'CIMButtonOptions')]//child::span//span/span[2])[1]")).getText();
			if(presetOption1.contains("Today")) {
				driver.findElement(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::a[contains(@class,'CIMButtonOptions')]//child::span//span/span[2])[1]")).click();
				checkElements(driver.findElements(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Save & Close'])")));
				cimNextStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[4]/div")).getText();
				String nextStartTime=getSystemTimeToday(cimNextStartTime);
				formatCimNextStartTime=getParsedDateTimeForPresetToday(presetOption1);
				assertTextEquals(nextStartTime,formatCimNextStartTime);
				
			}
			else if(presetOption1.contains("Tomorrow")){
				driver.findElement(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::a[contains(@class,'CIMButtonOptions')]//child::span//span/span[2])[1]")).click();
				checkElements(driver.findElements(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Save & Close'])")));
				cimNextStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[4]/div")).getText();
				String nextStartTime=getParsedDateTimeForPresetTommorow(cimNextStartTime);
				formatCimNextStartTime=getParsedDateTimeForPresetTommorow(presetOption1);
				assertTextEquals(nextStartTime,formatCimNextStartTime);
			}
			deleteCim();
			doClosePageOnLowerBar("Cost Integration Manager (CIM)");
			driver.navigate().refresh();
			waitForDisplayedSpinnerToEnd();
			goToPage("Cost Integration Manager");
			ExtentReport.logPass("PASS", "test02Validate_HighlightPresetOption_20409");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_HighlightPresetOption_20409", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test04Validate_PresetOption2_20409() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			waitForPresenceOfElement("//*[text()='Calculate "+cimScenarioCreate+"']");
			presetOption2=driver.findElement(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::a[contains(@class,'CIMButtonOptions')]//child::span//span/span[2])[2]")).getText();
			if(presetOption2.contains("Today")) {
				driver.findElement(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::a[contains(@class,'CIMButtonOptions')]//child::span//span/span[2])[2]")).click();
				checkElements(driver.findElements(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Save & Close'])")));
				cimNextStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[4]/div")).getText();
				String nextStartTime=getSystemTimeToday(cimNextStartTime);
				formatCimNextStartTime=getParsedDateTimeForPresetToday(presetOption2);
				assertTextEquals(nextStartTime,formatCimNextStartTime);
				
			}
			else if(presetOption2.contains("Tomorrow")){
				driver.findElement(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::a[contains(@class,'CIMButtonOptions')]//child::span//span/span[2])[2]")).click();
				checkElements(driver.findElements(By.xpath("(//div[text()='"+cimScenarioCreate+"']//following::span[text()='Save & Close'])")));
				cimNextStartTime=driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+cimScenarioCreate+"']//following::td[4]/div")).getText();
				String nextStartTime=getSystemTimeTomorrow(cimNextStartTime);
				formatCimNextStartTime=getParsedDateTimeForPresetTommorow(presetOption2);
				assertTextEquals(nextStartTime,formatCimNextStartTime);
			}
			deleteCim();
			ExtentReport.logPass("PASS", "test04Validate_PresetOption2_20409");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_PresetOption2_20409", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test05Validate_CustomDateTime_20409() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimCalculateBtn());
			String element = checkElements(driver.findElements(By.xpath("//div[text()='Calculate " + cimScenarioCreate + "']//following::span[text()='Custom Date & Time']")));
			if(driver.findElement(By.xpath(element)).isDisplayed()) {
				assertTrue(printout);
			}
			else {
				fail();
			}
			checkElements(driver.findElements(By.xpath("//div[text()='Calculate "+cimScenarioCreate+"']//following::span[text()='Cancel & Close']")));
			validateCalcStatus("PENDING", cimScenarioCreate);
			deleteCim();
			ExtentReport.logPass("PASS", "test05Validate_CustomDateTime_20409");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_CustomDateTime_20409", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
