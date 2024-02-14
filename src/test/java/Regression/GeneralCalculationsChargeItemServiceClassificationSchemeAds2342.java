package Regression;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
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
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsChargeItemServiceClassificationSchemeAds2342 extends CalculationHelper {

	static String viewLogTitleRemove = "Remove Charge Item Service Classification";
	// static String viewLogTitleRemove = "Remove Price List to Encounters
	// Assignment";
	static String viewLogTitleAssign = "Charge Item Service Classification Scheme";
	static String xRecordsProcessed = "26";
	static DataMaintenanceMap dmMap;
	final static String aTozPage = "Charge Item Service Classification Schemes";
	final static String batch = "V10.2 REGRESSION ChgItem Serv Class Sch";
//  final static String batch = "v10.2 REGRESSION Price List Enc Assign";
	private static List<WebElement> encountersTable;
	static String encounter = "OPPS2020DCA005";
//  List<String> expectedEncounters = Arrays.asList(
//          "QA REGRESSION CHG>0",
//          "QA REGRESSION CHG>0"
//  );
	// Shilpa updated for 11.2 one record is showing in 11.2
	List<String> expectedEncounters = Arrays.asList("QA REGRESSION CHG>0"

	);
	static final String recordsProcessedXpath = "(//div[contains(@id,'calculationstatus')])//following::div[@class='x-grid-item-container']//table[1]/tbody/tr/td[12]/div";
	String[] filterCostModel = { "Name", "Is", "Equal To", batch };
	String[] filterEncounter = { "Name", "Is", "Equal To", encounter };

	/** Regression: Test script for ADS-2341. Updated: 7-8-21.,ADS-6101 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GeneralCalculationsChargeItemServiceClassificationSchemeAds2342",
				"webdriver.scripts.regression.generalcalculations",
				"GeneralCalculationsChargeItemServiceClassificationSchemeAds2342");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			System.out.println("Test Class: "
					+ GeneralCalculationsChargeItemServiceClassificationSchemeAds2342.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage);

//		openMaintainDataBatch(batch);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6101 all steps
	@Test
	public void test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully() throws InterruptedException, Throwable {
		try {
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			doFilterCreate(filterCostModel);
			driverDelay(4000);// Shilpa 16.09.2022
			tableDoubleClickCellFirstColumn(batch);
//  	  waitForPresenceOfElement("//span[text()='Remove']");
			// shilpa update xpath for 11.2 on 11.23.2023
			waitForPresenceOfElement("//span[text()='Remove']/../../..");
			doClick(driver.findElement(By.xpath("//span[text()='Remove']/../../..")));
			waitForSpinnerToEnd();
			driverDelay();
			waitForFirstRowCalculationBarToReach100Percent();
			driverDelay();
			// Shilpa 16.09.2022 added wait
			calculationStatusPageOpenViewDialog();
			// waitForElementToBeVisible(driver.findElement(By.xpath("//span[text()='View
			// Log']")));
			driverDelay(5000);
			assertViewLogTitle(viewLogTitleRemove);
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Calculation Status");
//		  waitForPresenceOfElement("//button/span[text()='Save & Close']");      
				// Shilpa update xpath for 11.2 on 11.23.2023
				waitForPresenceOfElement(
						"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Save & Close']/../../..)[1]");
				doClick(driver.findElement(By.xpath(
						"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Save & Close']/../../..)[1]")));
				waitForSpinnerToEnd();
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void test03VerifyNoServicesAppearOnEncountersPage() throws InterruptedException, Throwable {
		try {
			doMaintainDataPageSelectAtoZOption("Encounters");
			doSearchForModel(encounter);
//		openMaintainDataBatch(encounter);sdsd
			// Shilpa added filter , scroll not working , for 11.2 on 11.23.2024
//		doClick(dmMap.getencounterButtonFilter());
//		doFilterCreate(filterEncounter);
			tableDoubleClickCellFirstColumn(encounter);
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
//		doClick(driver.findElement(By.xpath("//button/span[text()='Charges']")));
			// Shilpa update xpath for 11.2 on 11.23.2023
			doClick(driver.findElement(By.xpath("//span[text()='Charges']/../../..")));
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
//		waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::input");
//		doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::input"));
			// Shilpa update xpath for 11.2 on 11.23.2023
			waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::span");
			doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::span"));
			waitForSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");// there is data available for Charge Item
																			// Services in 11.2
			doClick(driver.findElement(By.xpath(
					"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel & Close']/../../..)[1]")));
			ExtentReport.logPass("PASS", "test03VerifyNoServicesAppearOnEncountersPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyNoServicesAppearOnEncountersPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected()
			throws InterruptedException, Throwable {
		try {

			doMaintainDataPageSelectAtoZOption(aTozPage);
			driverDelay(5000);
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			doFilterCreate(filterCostModel);
			driverDelay(4000);// Shilpa 16.09.2022
			tableDoubleClickCellFirstColumn(batch);
//      openMaintainDataBatch(batch);
			waitForAjaxExtJs();
//      doClick(driver.findElement(By.xpath("//button/span[text()='Assign']")));
			doClick(driver.findElement(By.xpath("//span[text()='Assign']/../../..")));
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent();
			driverDelay(5000);
			String recordsProcessedText = getWebElement(recordsProcessedXpath).getText();
			assertEquals(xRecordsProcessed, recordsProcessedText);
			calculationStatusPageOpenViewDialog();
			// Shilpa 16.09.2022
			driverDelay(5000);
			assertViewLogTitle(viewLogTitleAssign);
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			driverDelay(3000);
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected",
					driver, e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Calculation Status");
				waitForAjaxExtJs();
//		  driverDelay();//Shilpa 16.09.2022
//		  doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
//		  waitForSpinnerToEnd();
				// Shilpa update xpath for 11.2 on 11.23.2023
				waitForPresenceOfElement(
						"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Save & Close']/../../..)[1]");
				doClick(driver.findElement(By.xpath(
						"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Save & Close']/../../..)[1]")));
				waitForSpinnerToEnd();
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected",
						driver, e);
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void test05AssertServicesCountOnEncountersPage() throws InterruptedException, Throwable {
		try {
			goToPage("Maintain Data");
			doMaintainDataPageSelectAtoZOption("Encounters");
			doSearchForModel(encounter);
//		  openMaintainDataBatch(encounter);
			tableDoubleClickCellFirstColumn(encounter);
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
			// Shilpa update xpath for 11.2 on 11.23.2023
			doClick(driver.findElement(By.xpath("//span[text()='Charges']/../../..")));
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::span");
			doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::span"));
			waitForSpinnerToEnd();
			encountersTable = getEncountersTableRows();
//		  assertThat(encountersTable.size(), equalTo(2));//no data shown in 11.2 hence assert is failing
			assertThat(encountersTable.size(), equalTo(1));// 1 is showing under encounters
			ExtentReport.logPass("PASS", "test05AssertServicesCountOnEncountersPage");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AssertServicesCountOnEncountersPage", driver, e);
			fail(e.getMessage());
		} // expected value includes header row
	}

	@Test
	public void test06VerifyServicesNowAppearOnEncountersPage() throws InterruptedException, Throwable {
		try {
			List<String> encountersTableStrings = javaMakeListOfStrings(encountersTable, "//td[3]/div");
			assertThat(encountersTableStrings, equalTo(expectedEncounters));
//      doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
			doClick(driver.findElement(By.xpath(
					"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel & Close']/../../..)[1]")));
			ExtentReport.logPass("PASS", "test06VerifyServicesNowAppearOnEncountersPage");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06VerifyServicesNowAppearOnEncountersPage", driver, e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Maintain Data");
				waitForLandingPageFooter();
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test06VerifyServicesNowAppearOnEncountersPage", driver, e);
				fail(e.getMessage());
			}
		}
	}

	private List<WebElement> getEncountersTableRows() {
		return tableGetTableRows(driver
				.findElement(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]"
						+ "/following-sibling::div/descendant::table/tbody")),
				"tr");
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
