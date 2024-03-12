package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-6616,ADS-6615,ADS-6614,ADS-6613,ADS-6612,ADS-6611
 **/
public class UIValidateImportStatuPage extends CalculationHelper{
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static StatusMap statusMap;
	static String searchText="Import";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("UIValidateImportStatuPage", "webdriver.scripts.systemmaintenance",
				"UIValidateImportStatuPage");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			statusMap = BuildMap.getInstance(driver, StatusMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Import/Export Status");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6615
	@Test
	public void test01ValidateSearchCriteriaUnderImportStatusPage_6615() throws Throwable {
		try {
			doSearchForContractModel(searchText.toUpperCase());
			waitForDisplayedSpinnerToEnd();
			assertGridElementsOnSearch(modelMap.getModelLibraryTableButtonNext(),statusMap.calcStatusPageNumber(),statusMap.importStatusPageGridElements(),searchText);
			doSearchForContractModel(" ");
			waitForAjaxExtJs();
			assertListElementsAreDisplayed(statusMap.importStatusPageGridElements(), printout);
			ExtentReport.logPass("PASS", "test01ValidateSearchCriteriaUnderUtilityStatusPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSearchCriteriaUnderUtilityStatusPage", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6614,
	@Test
	public void test02ValidateDeleteFilteredUnderImportStatusPage_6614() throws Throwable {
		try {
			doClick(statusMap.getImportExportStatusPageButtonDeleteFiltered());
			waitForElementToBeVisible(statusMap.calcStatusDeleteFilteredButton());
			assertElementIsDisplayed(statusMap.calcStatusDeleteFilteredCancelButton());
			assertElementIsDisplayed(statusMap.calcStatusDeleteFilteredButton());
			doClick(statusMap.calcStatusDeleteFilteredButton());
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test02ValidateDeleteFilteredUnderImportStatusPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateDeleteFilteredUnderImportStatusPage", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6613
	@Test
	public void test03ValidateRefreshUnderImportStatusPage_6613() throws Throwable {
		try {
			doClick(statusMap.getImportExportStatusPageButtonRefresh());
			waitForAjaxExtJs();
			assertListElementsAreDisplayed(statusMap.importStatusPageGridElements(),printout);
			assertListElementsAreDisplayed(statusMap.importStatusEndTimeGridElements(),printout);
			assertListElementsAreDisplayed(statusMap.importStatusDurationGridElements(),printout);
			ExtentReport.logPass("PASS", "test03ValidateRefreshUnderImportStatusPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateRefreshUnderImportStatusPage", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6612
	@Test
	public void test04ValidateDefaultTabInImportExportStatusPage_6612() throws Throwable {
		try {
			assertElementIsDisplayed(statusMap.getImportExportStatusPageButtonMyStatus());
			assertElementIsDisplayed(statusMap.importStatusPageScenarioNameColumnName());
			assertListElementsAreDisplayed(statusMap.importStatusDurationGridElements(), printout);
			ExtentReport.logPass("PASS", "test04ValidateDefaultTabInImportExportStatusPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateDefaultTabInImportExportStatusPage", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6611
	@Test
	public void test05ValidateAllStatusInImportStatusPage_6611() throws Throwable {
		try {
			doClick(statusMap.getImportExportStatusPageButtonAllStatus());
			assertListElementsAreDisplayed(statusMap.importStatusDurationGridElements(), printout);
			assertElementIsDisplayed(statusMap.importStatusPageUserNameColumnName());
			ExtentReport.logPass("PASS", "test05ValidateAllStatusInImportStatusPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateAllStatusInImportStatusPage", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6616
	@Test
	public void test06ValidateSortByImportExportName_6616() throws Throwable {
		try {
			scrollToView(statusMap.importStatusPageScenarioNameColumnName());
			doClick(statusMap.importStatusPageScenarioNameColumnName());
			ContractModelsHelper.sortTableGridDescending(statusMap.importStatusDurationGridElements());
			ExtentReport.logPass("PASS", "test10ValidateSortByImportExportName");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ValidateSortByImportExportName", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
