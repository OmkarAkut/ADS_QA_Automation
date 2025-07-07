package webdriver.scripts.cim;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20040 **/
public class AddAbilitytoDeleteaCostCalculationGroup extends CimHelper {
	private static CimMap cimMap;
	private static Actions builder = new Actions(driver);
	private static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	private static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };
	static String calcType=getProperty().replaceAll("^\"|\"$", "");
	public static List<String> cimList = new ArrayList<>();
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("AddAbilitytoDeleteaCostCalculationGroup", "webdriver.scripts.cim",
				"AddAbilitytoDeleteaCostCalculationGroup");
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
	public void test01ValidateDeleteFunction_20040() throws Throwable {
		try {
			createNewScenario(cimScenarioCreate,calcType);
			doClick(cimMap.getcimSaveCloseBtn());
			doClick(cimMap.getcimFilterButton());
			doFilterCreateCIM(filterCim);
			doClick(cimMap.getcimDeleteButton());
			assertTextIsDisplayed(
					"Decision Support will delete the data you selected. Click Delete to remove the data from the database, or click Cancel to return to the previous screen without deleting the data.");
			doClick(cimMap.getcimWarningDeleteButton());
			doClick(cimMap.getcimClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test01ValidateDeleteFunction_20040");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateDeleteFunction_20040", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02ValidateDeleteRandomSelection_20040() throws Throwable {
		try {
			createMultipleCIM(cimScenarioCreate, 5,calcType,cimList);
			List<WebElement> cimRows = cimMap.getcimGrid();
			builder.click(cimRows.get(1)).keyDown(Keys.CONTROL).click(cimRows.get(3)).keyDown(Keys.CONTROL)
					.click(cimRows.get(5)).keyDown(Keys.CONTROL).build().perform();
			doClick(cimMap.getcimDeleteButton());
			doClick(cimMap.getcimWarningDeleteButton());
			for (int k = 1; k < cimRows.size(); k++) {
				if (!(cimRows.get(k).isDisplayed()) && (cimRows.get(k + 2).isDisplayed())
						&& (cimRows.get(k + 2).isDisplayed())) {
					assertTrue(printout);
					break;
				}
			}
			ExtentReport.logPass("PASS", "test02ValidateDeleteRandomSelection_20040");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateDeleteRandomSelection_20040", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ValidateDeleteAll_20040() throws Throwable {
		try {
			deleteMultipleCim();
			ExtentReport.logPass("PASS", "test03ValidateDeleteAll_20040");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateDeleteAll_20040", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}