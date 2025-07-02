package webdriver.scripts.cim;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.Duration;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-21955 **/
public class EnableDragAndDropFunctionalityForCalculationsInCIMGroup extends CimHelper{
	private static CimMap cimMap;
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EnableDragAndDropFunctionalityForCalculationsInCIMGroup", "webdriver.scripts.cim",
				"EnableDragAndDropFunctionalityForCalculationsInCIMGroup");
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
	public void test01Validate_CalculateNow_20406() throws Throwable {
		try {
			createCIM(cimScenarioCreate,calcType);
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimEditBtn());
			  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			 WebElement source = driver.findElement(By.xpath("//div[text()='Activity Volume Calc Scenario: 000-NEWACTVOLCALC']"));
		        WebElement target = driver.findElement(By.xpath("//strong[@id='selectedCountLabel']//following::div//table//tr"));
		        source.click();	
		     //   target.click();
			 Actions actions = new Actions(driver);
			 actions.clickAndHold(source)
             .moveToElement(target)
             .perform();

      // Step 3: Wait for class to appear on table
//      wait.until(driver1 -> {
//          String classAttr = target.getAttribute("class");
//          return classAttr != null && classAttr.contains("x-grid-item-over");
//      });

      // Step 4: Drop
      actions.release().perform();
      driverDelay();
		        doClick(cimMap.getcimCancelCloseBtn());
		        deleteCim();
			ExtentReport.logPass("PASS", "test01Validate_Calc_Hyperlink_20053");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_Calc_Hyperlink_20053", driver, e);
			fail(e.getMessage());
		}
	}
}
