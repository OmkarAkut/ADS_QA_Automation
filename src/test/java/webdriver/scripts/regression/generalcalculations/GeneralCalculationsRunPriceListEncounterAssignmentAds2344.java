package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

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
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunPriceListEncounterAssignmentAds2344 extends CalculationHelper {

//  static String viewLogTitle = "Remove Encounter Service Classification";
	static String viewLogTitle = "Remove Price List to Encounters Assignment";
	static String viewLogTitleAssign = "Price List Encounters Assignment";
	// final static String aTozPage = "Price List to Encounters Assignment";
	final static String aTozPage = "Price List to Encounters Assignments";
	final static String batch = "v10.2 REGRESSION Price List Enc Assign";
//  final static String batch = "v105 REGRESSION IPPS 2023 DC A";
	final static String xSourcePriceList = "QAASSIGNPL";
	final static String xDestinationChargeScenario = "Estimated Charges 15";
	String[] filterEncounterData = { "Name", "Is", "Equal To", batch };
	private static List<WebElement> encountersTable;

	/** Regression: Test script for ADS-2343. Updated: 7-7-21. */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GeneralCalculationsRunPriceListEncounterAssignmentAds2344",
				"webdriver.scripts.regression.generalcalculations",
				"GeneralCalculationsRunPriceListEncounterAssignmentAds2344");
		try {
			System.out.println(
					"Test Class: " + GeneralCalculationsRunPriceListEncounterAssignmentAds2344.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage);
//		openMaintainDataBatch(batch);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure to setupScript", driver, e);
			fail(e.getMessage());
		}
	}

//  @Test
	public void test00ConfirmParametersOnPage() {
		String sourcePriceList = null;
		String destinationChargeScenario = null;
		assertThat(sourcePriceList, equalTo(xSourcePriceList));
		assertThat(destinationChargeScenario, equalTo(xDestinationChargeScenario));
	}
//ADS-6099
	@Test
	public void test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails_6099()
			throws InterruptedException, Throwable {
		try {
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			doFilterCreate(filterEncounterData);
			driverDelay(4000);
			waitForAjaxExtJs();
			tableDoubleClickCellFirstColumn(batch);
			waitForAjaxExtJs();
//      waitForPresenceOfElement("(//span[text()='Remove'])");
//      driver.findElement(By.xpath("(//span[text()='Remove'])")).click();
			// Shilpa update for xpath on 11.2 on 11.27.2023
			waitForPresenceOfElement("(//span[text()='Remove']/../../..)[2]");
			driver.findElement(By.xpath("(//span[text()='Remove']/../../..)[2]")).click();
			// doClick(getButton("Clear Results"));
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent();
			driverDelay(5000);
			calculationStatusPageOpenViewDialog();
			driverDelay(5000);
			assertViewLogTitle(viewLogTitle);
			confirmCalculationStatusDetailsContains("Process Completed");
			confirmCalculationStatusDetailsContains("Total number of charge items reset: 25");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails", driver,
					e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Calculation Status");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails",
						driver, e);
				fail(e.getMessage());
			}
		}
	}

//  @Test
	public void test02VerifyDatabaseValueCountIsZero() throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getPriceListEncounterAssignmentSql,
//            "equal to",
//            0
//    );
	}

	@Test
	public void test02ClickAssignButtonAndAssertCalculationStatusPageDetails_6099() throws InterruptedException, Throwable {
		try {
			waitForAjaxExtJs();
//      waitForPresenceOfElement("(//span[text()='Assign'])");
//      doClick(driver.findElement(By.xpath("(//span[text()='Assign'])")));
			// Shilpa update xpath for 11.2 on 11.27.2023
			waitForPresenceOfElement("(//span[text()='Assign']/../../..)[2]");
			doClick(driver.findElement(By.xpath("(//span[text()='Assign']/../../..)[2]")));
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent();
			driverDelay(6000);
			calculationStatusPageOpenViewDialog();
			driverDelay(7000);
			assertViewLogTitle(viewLogTitleAssign);
//      confirmCalculationStatusDetailsContains("Total number of charge items reset: 25");
			confirmCalculationStatusDetailsContains("Total number of charge items processed: 25");
			clickLastPageIconOnCalculationStatusViewLog();
			driverDelay(5000);
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "test02ClickAssignButtonAndAssertCalculationStatusPageDetails");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ClickAssignButtonAndAssertCalculationStatusPageDetails", driver, e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Calculation Status");
//		  doClick(getWebElement("//button/span[text()='Cancel & Close']"));
				// Shilpa update xpath for 11.2 on 11.27.2023
				waitForPresenceOfElement(
						"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel & Close']/../../..)[1]");

				doClick(getWebElement(
						"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel & Close']/../../..)[1]"));

				doClosePageOnLowerBar("Maintain Data");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02ClickAssignButtonAndAssertCalculationStatusPageDetails", driver, e);

				fail(e.getMessage());
			}
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
//  @Test
//  public void test03QueryDatabaseAndAssertValuesAreCorrectForPriceListEncounterAssignments()
//          throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getPriceListEncounterAssignmentSql,
//            "equal",
//            GeneralCalculationsData.getPriceListEncounterAssignmentExpectedValue
//    );
//  }

}
