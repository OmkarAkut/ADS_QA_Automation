package webdriver.scripts.contracting;
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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfirmAddNewDeleteAPCFeeScheduleMasters extends CalculationHelper {
	private static ContractingMap modelMap;
	static final String ContractModelName = "ADS-1320 Contract Model D";
	static String currentDateTime = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String APCCode = currentDateTime.replaceAll("\\W", "");
	static String filter[] = { "Code", "Is", "Equal To", APCCode };

	/** Regression: Automated test script for ADS-6458,ADS-6457[to be auto],ADS-6455 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ConfirmAddNewDeleteAPCFeeScheduleMasters", "webdriver.scripts.contracting",
				"ConfirmAddNewDeleteAPCFeeScheduleMasters");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6458
	@Test
	public void test01AssertContractTaskList_ADS_6458() throws Throwable {
		try {

			// ADS-6455
			assertTextIsDisplayed("Unpublished Contract Task List");
			assertTextIsDisplayed("Build Structure Elements");
			assertTextIsDisplayed("Model Contract");
			assertTextIsDisplayed("Publish Contract");
			assertTextIsDisplayed("Export Contract");

			ExtentReport.logPass("PASS", "test01ConfirmAddandDeleteNewCodeUnderAPCFeeScheduleMasters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ConfirmAddandDeleteNewCodeUnderAPCFeeScheduleMasters", driver, e);
			fail(e.getMessage());
		}

	}
//ADS-6442,ADS-6455
	@Test
	public void test02AssertBuildStructureElements_ADS_6442_ADS_6455() throws Throwable {
		try {
			doClickTreeItem("Build Structure Elements");
			driverDelay(300);
			assertTextIsDisplayed("Contract Types");
			assertTextIsDisplayed("Services");
			assertTextIsDisplayed("Level of Care Tables");
			assertTextIsDisplayed("Populations");
			assertTextIsDisplayed("Membership Classification Schemes");
			assertTextIsDisplayed("ASC Schemes");
			assertTextIsDisplayed("Fee Schedule Masters");
			assertTextIsDisplayed("Price Lists");
			assertTextIsDisplayed("Prepare RBRVS Tables");
			doClickTreeItem("Build Structure Elements");
			ExtentReport.logPass("PASS", "test02AssertBuildStructureElements");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssertBuildStructureElements", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03AssertFeeScheduleMasters() throws Throwable {
		try {
			doClickTreeItem("Build Structure Elements");
			driverDelay(120);
			doClickTreeItem("Fee Schedule Masters");
			driverDelay(300);
			assertTextIsDisplayed("APC Fee Schedule Masters");
			assertTextIsDisplayed("APG Fee Schedule Masters");
			assertTextIsDisplayed("Chargeable Activity Fee Schedule Masters");
			ExtentReport.logPass("PASS", "test03AssertFeeScheduleMasters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertFeeScheduleMasters", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04AssertPriceLists() throws Throwable {
		try {
			doClickTreeItem("Price Lists");
			driverDelay(300);
			assertTextIsDisplayed("Price Lists");
			assertTextIsDisplayed("Price List Calculation Scenarios");
			ExtentReport.logPass("PASS", "test04AssertPriceLists");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AssertPriceLists", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05AssertPrepareRBRVSTables() throws Throwable {
		try {
			doClickTreeItem("Prepare RBRVS Tables");
			driverDelay(300);
			assertTextIsDisplayed("Update Indicators");
			assertTextIsDisplayed("Prepare RBRVS RVU Tables");
			assertTextIsDisplayed("Prepare GPCI Tables");
			assertTextIsDisplayed("Site of Service Tables");
			ExtentReport.logPass("PASS", "test05AssertPrepareRBRVSTables");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AssertPrepareRBRVSTables", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6457
		@Test
		public void test05CreateNewAPDRGScheduleMasters_ADS_6457() throws Throwable {
			doClickTreeItem("DRG Fee Schedule Masters");
			doClickTreeItem("AP DRG Fee Schedule Masters");
			driverDelay();
			doClick("(//h1[text()='AP DRG Fee Schedule Masters']//following::span[text()='New'])[1]");
			ContractModelsHelper.keyInValues(driver.findElement(By.name("code")), APCCode);
			doClick("(//h1[text()='AP DRG Fee Schedule Masters']//following::span[text()='Save & Close'])[1]");
			doClick(ContractingMap.getAPDRGFilterButton());
			doFilterCreate(filter);
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='" + APCCode + "']")));
			doClick(ContractingMap.getAPDRGDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			assertTextIsDisplayed("There is no data available to display.");
		}
//ADS-6458
	@Test
	public void test06CreateNewandDeleteAPCCode_ADS_6458() throws Throwable {
		try {
			doClickTreeItem("APC Fee Schedule Masters");
			waitForElementToBeVisible(ContractingMap.getApcFeeScheduleHeader());
			doClick(ContractingMap.getNewButtonAPC());
			waitForElementToBeVisible(ContractingMap.getMedicareCode());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(), APCCode);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			doClick(ContractingMap.getNewAPCodeFilterButton());
			doFilterCreate(filter);
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='" + APCCode + "']")));
			
			doClick(ContractingMap.getNewAPCodeDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			assertTextIsDisplayed("There is no data available to display.");
//			doClosePageOnLowerBar("ADS-1320 Contract...");
			doClick("//span[@class='x-tab-close-btn'][contains(text(),'removable')]");
			ExtentReport.logPass("PASS", "test06CreateNewandDeleteAPCCode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06CreateNewandDeleteAPCCode", driver, e);
			fail(e.getMessage());
		} finally {
//			Omkar 22/5/2023 : xpath/name changes for 11.2
//			doClosePageOnLowerBar("Model Library"); 
			doClosePageOnLowerBar("Contract Models");
		}
	}
	//ADS-6462
	

	@AfterClass
	public static void endtest() {
		ExtentReport.report.flush();
	}
}
