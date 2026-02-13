package webdriver.scripts.cim;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
	static String calcType1="*CM1 TB MHFY05 After Vol Change";
	static String calcType2="15PLCALC";
	static String[] calcTypes= {"*CM1 TB MHFY05 After Vol Change","15PLCALC","ADS-4383 RVU Calc Scenario"};
	static String[] calcTypeForAssert= {"Cost Model Calc: *CM1 TB MHFY05 After Vol Change","Price List Calc Scenario: 15PLCALC","RVU Calc Scenario: ADS-4383 RVU Calc Scenario"};
	static List<String> expSelectedItemsAfterDragDrop= Arrays.asList("RVU Calc Scenario: ADS-4383 RVU Calc Scenario","Price List Calc Scenario: 15PLCALC","Cost Model Calc: *CM1 TB MHFY05 After Vol Change","Activity Volume Calc Scenario: ADS-262 Vol Calc");
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
	public void test01Validate_New_DragDrop_21955() throws Throwable {
		try {
			doClick(cimMap.getcimNewBtn());
			waitForElementToBeVisible(cimMap.getcimName());
			cimMap.getcimName().sendKeys(Keys.chord(Keys.CONTROL, "a"));
			cimMap.getcimName().sendKeys(Keys.BACK_SPACE);
			cimMap.getcimName().sendKeys(cimScenarioCreate);
			driverDelay();
			String[] input=calcType.split(": ");
			cimMap.getcimScenarioSearchInput().sendKeys(input[1]);
			cimMap.getsearchIcon().click();
			driverDelay();
			WebElement source = driver.findElement(By.xpath(
					"(//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div[contains(text(),'"+calcType+"')]"));
			WebElement target = cimMap.getselectedItemGrid();
			dragAndDrop(source, target);
			doClick(cimMap.gethostLocation());
			doClick(cimMap.getcimSharedLoc());
			cimMap.getcimLocation().sendKeys("ADS-20031");
			doClick(cimMap.getcimSaveCloseBtn());
			waitForDisplayedSpinnerToEnd();
			
			ExtentReport.logPass("PASS", "test01Validate_New_DragDrop_21955");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_New_DragDrop_21955", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Validate_Edit_DragDrop_21955() throws Throwable {
		try {
			doClick(cimMap.getcimFilterButton());
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimEditBtn());
			waitForElementToBeVisible(cimMap.getcimScenarioSearchInput());
			for(int i=0;i<calcTypes.length;i++) {
				cimMap.getcimScenarioSearchInput().sendKeys(calcTypes[i]);
				cimMap.getsearchIcon().click();
				driverDelay();
				WebElement source = driver.findElement(By.xpath(
						"(//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div[contains(text(),'"+calcTypes[i]+"')]"));
				WebElement target = driver.findElement(
						By.xpath("(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div[contains(text(),'"
								+ calcType + "')]"));
				dragAndDrop(source, target);
				assertElementIsDisplayedWithXpath("(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div[contains(text(),'"+calcTypes[i]+"')]");
			for(int j=i;j<calcTypeForAssert.length;j++) {
				assertElementIsNotDisplayed("(//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div[text()='"+calcTypeForAssert[j]+"']");

			}
				cimMap.getcimScenarioSearchInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
				cimMap.getcimScenarioSearchInput().sendKeys(Keys.DELETE);
				
			}
			ExtentReport.logPass("PASS", "test02Validate_Edit_DragDrop_21955");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02Validate_Edit_DragDrop_21955", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_RearrangeCalcTypes_DragDrop_21955() throws Throwable {
		try {
			WebElement source = driver.findElement(By.xpath(
					"(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div[contains(text(),'"+calcTypes[1]+"')]"));
			WebElement target = driver.findElement(
					By.xpath("(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div[contains(text(),'"
							+ calcType+ "')]"));
			dragAndDrop(source, target);
			WebElement sourceNew = driver.findElement(By.xpath(
					"(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div[contains(text(),'"+calcTypes[0]+"')]"));
			WebElement targetNew = driver.findElement(By.xpath(
					"(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div[contains(text(),'"+calcType+"')]"));
			dragAndDrop(sourceNew, targetNew);
			compareList(cimMap.getselectedCalcList(), expSelectedItemsAfterDragDrop);
			doClick(cimMap.getcimSaveCloseBtn());
			waitForDisplayedSpinnerToEnd();
			deleteCim();
			ExtentReport.logPass("PASS", "test03Validate_RearrangeCalcTypes_DragDrop_21955");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_RearrangeCalcTypes_DragDrop_21955", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
