package Regression;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

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
/** Regression test case ADS-6610,ADS-6609,ADS-6607,ADS-6606,ADS-6605,ADS-6604,ADS-6603
ADS-6602
 **/
public class ValidateCalculationStatusAllStatus extends CalculationHelper{
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static StatusMap statusMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String folder = currentDateTime;
	static String name = "Folder Name" + currentDateTime;
	static String userRole = "ASEC2310 CustomRole9";
	static String assignedUserSelect = "AdHoc_Report_Designer   automationadhocrepdes1";
	static String searchText;
	static String[] filter;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateCalculationStatusAllStatus", "webdriver.scripts.systemmaintenance",
				"ValidateCalculationStatusAllStatus");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			statusMap = BuildMap.getInstance(driver, StatusMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Calculation Status");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6603
	@Test
	public void test01ValidateDefaultTabInCalculationStatus_6603() throws Throwable {
		try {
			assertElementIsDisplayed(statusMap.getCalculationStatusPageButtonMyStatus());
			assertElementIsDisplayed(statusMap.calculationStatusPageScenarioNameColumnName());
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			ExtentReport.logPass("PASS", "test01ValidateDefaultTabInCalculationStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateDefaultTabInCalculationStatus", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6602
	@Test
	public void test02ValidateAllStatus_6602() throws Throwable {
		try {
			doClick(statusMap.getCalculationStatusPageButtonAllStatus());
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			assertElementIsDisplayed(statusMap.calculationStatusPageUserNameColumnName());
			ExtentReport.logPass("PASS", "test02ValidateAllStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateAllStatus", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateAllStatusByPagination() throws Throwable {
		try {
			doClick(modelMap.getModelLibraryTableButtonLast());
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			assertElementIsDisplayed(statusMap.calculationStatusPageUserNameColumnName());
			doClick(modelMap.getModelLibraryTableButtonPrevious());
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			assertElementIsDisplayed(statusMap.calculationStatusPageUserNameColumnName());
			doClick(modelMap.getModelLibraryTableButtonNext());
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			assertElementIsDisplayed(statusMap.calculationStatusPageUserNameColumnName());
			ExtentReport.logPass("PASS", "test03ValidateAllStatusByPagination");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateAllStatusByPagination", driver, e);
			fail(e.getMessage());
		}

	}
	//ADS-6604
	@Test
	public void test04ValidateCalculationStatusPageAfterRefresh_6604() throws Throwable {
		try {
			doClick(statusMap.getCalculationStatusPageButtonRefresh());
			assertListElementsAreDisplayed(statusMap.calculationStatusProgressBarPageGridElements(),printout);
			assertListElementsAreDisplayed(statusMap.calcStatusPageGridElements(),printout);
			assertListElementsAreDisplayed(statusMap.calcStatusEndTimeGridElements(),printout);
			assertListElementsAreDisplayed(statusMap.calcStatusDurationGridElements(),printout);
			ExtentReport.logPass("PASS", "test04ValidateCalculationStatusPageAfterRefresh");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateCalculationStatusPageAfterRefresh", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6605
	@Test
	public void test05ValidateSearchUnderCalcStatusPage_6605() throws Throwable {
		try {
			doSearchForContractModel(searchText.toUpperCase());
			waitForAjaxExtJs();
			assertGridElementsOnSearch(modelMap.getModelLibraryTableButtonNext(),statusMap.calcStatusPageNumber(),statusMap.calculationStatusPageGridElements(),searchText);
			ExtentReport.logPass("PASS", "test05ValidateSearchUnderCalcStatusPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateSearchUnderCalcStatusPage", driver, e);
			fail(e.getMessage());
		}
	}
		@Test
	public void test06ValidateAfterClearSearch() throws Throwable {
		try {
			doSearchForContractModel(" ");
			waitForAjaxExtJs();
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			ExtentReport.logPass("PASS", "test06ValidateAfterClearSearch");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ValidateAfterClearSearch", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6606
	@Test
	public void test07ValidateFilter_6606() throws Throwable {
		try {
			Actions action=new Actions(driver);
			doClick(statusMap.getCalculationStatusPageButtonFilter());
			//Shilpa updated for 11.2 take  the text from existing records then search for the text
			searchText=getElementText(driver.findElement(By.xpath("(//td[contains(@class,'x-grid-cell')]/div)[3]")), printout);
			doFilterSetFilterParameters("Scenario Name", "Is", "Equal To", searchText);
			doClick(ContractingMap.getaddOnServicesPopUpFilterAddButton());
			waitForAjaxExtJs();
			action.moveToElement(contractMap.getContractModelEditFilterButton()).click().pause(10).perform();
			driver.findElement(By.name("valuefield")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.name("valuefield")).sendKeys(searchText);
			doClick(contractMap.getContractModelUpdateFilterButton());
			action.moveToElement(contractMap.getContractModelRemoveFilterButton()).click().pause(10).perform();
			String[] filter= {"Scenario Name","Is","Equal To",searchText};
			doFilterCreate(filter);
			assertGridElementsOnSearch(modelMap.getModelLibraryTableButtonNext(),statusMap.calcStatusPageNumber(),statusMap.calculationStatusPageGridElements(),searchText);
			ExtentReport.logPass("PASS", "test07ValidateFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ValidateFilter", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6607
	@Test
	public void test08ClearFilter_6607() throws Throwable {
		try {
			doClick(statusMap.getCalculationStatusPageButtonClearFilter());
			waitForAjaxExtJs();
			assertListElementsAreDisplayed(statusMap.calculationStatusPageGridElements(), printout);
			ExtentReport.logPass("PASS", "test08ClearFilter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ClearFilter", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09ValidateDefaultSort() throws Throwable {
		try {
			scrollToView(statusMap.calcStatusDefaultSortColumn());
			assertElementIsDisplayed(statusMap.calcStatusDefaultSortColumn());
			ContractModelsHelper.sortTableGridAscending(statusMap.calcStatusPageGridElements());
			ExtentReport.logPass("PASS", "test01ValidateDefaultSort");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateDefaultSort", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10ValidateSortByScenarioName() throws Throwable {
		try {
			scrollToView(statusMap.calculationStatusPageScenarioNameColumnName());
			doClick(statusMap.calculationStatusPageScenarioNameColumnName());
			ContractModelsHelper.sortTableGridDescending(statusMap.calcStatusPageGridElements());
			ExtentReport.logPass("PASS", "test02ValidateSortByScenarioName");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSortByScenarioName", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6609
	@Test
	public void test11ValidateSortByCalcStartTime_6609() throws Throwable {
		try {
			scrollToView(statusMap.calcStatusCalcStartTimeSortColumn());
			doClick(statusMap.calcStatusCalcStartTimeSortColumn());
			ContractModelsHelper.sortTableGridAscending(statusMap.calcStatusPageGridElements());
			ExtentReport.logPass("PASS", "test03ValidateSortByCalcStartTime");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSortByCalcStartTime", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6610
	@Test
	public void test12ValidateDeleteFiltered_6610() throws Throwable {
		try {
			doClick(statusMap.getCalculationStatusPageButtonDeleteFiltered());
			waitForElementToBeVisible(statusMap.calcStatusDeleteFilteredButton());
			assertElementIsDisplayed(statusMap.calcStatusDeleteFilteredCancelButton());
			assertElementIsDisplayed(statusMap.calcStatusDeleteFilteredButton());
			doClick(statusMap.calcStatusDeleteFilteredButton());
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test12ValidateDeleteFiltered");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12ValidateDeleteFiltered", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
