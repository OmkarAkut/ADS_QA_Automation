package Regression;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-6622,ADS-6621,ADS-6620,ADS-6619,ADS-6618,ADS-6617
 **/
public class UIValidationStatusUIValidateUtilityStatusPage extends CalculationHelper{
			static CostingMap costing;
			static ContractingMap contractMap;
			static ModelLibraryMap modelMap;
			private static StatusMap statusMap;
			static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			static String folder = currentDateTime;
			static String name = "Folder Name" + currentDateTime;
			static String searchText="Charge";
			static String[] filter= {"Scenario Name","Is","Equal To",searchText};
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
					goToPage("Utility Status");
					ExtentReport.logPass("PASS", "setupScript");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
					fail(e.getMessage());
				}
			}
		//ADS-6621	
			@Test
			public void test01ValidateSearchCriteriaUnderUtilityStatusPage() throws Throwable {
				try {
					doSearchForContractModel(searchText.toUpperCase());
					waitForDisplayedSpinnerToEnd();
					assertGridElementsOnSearch(modelMap.getModelLibraryTableButtonNext(),statusMap.calcStatusPageNumber(),statusMap.utilityStatusPageGridElements(),searchText);
					doSearchForContractModel(" ");
					 waitForAjaxExtJs();
					ExtentReport.logPass("PASS", "test01ValidateSearchCriteriaUnderUtilityStatusPage");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "test01ValidateSearchCriteriaUnderUtilityStatusPage", driver, e);
					fail(e.getMessage());
				}
			}
			//ADS-6620
			@Test
			public void test02ValidateDeleteFilteredUnderUtilityStatusPage() throws Throwable {
				try {
					doClick(statusMap.getUtilityStatusPageButtonDeleteFiltered());
					waitForElementToBeVisible(statusMap.calcStatusDeleteFilteredButton());
					assertElementIsDisplayed(statusMap.calcStatusDeleteFilteredCancelButton());
					assertElementIsDisplayed(statusMap.calcStatusDeleteFilteredButton());
					doClick(statusMap.calcStatusDeleteFilteredButton());
					waitForAjaxExtJs();
					ExtentReport.logPass("PASS", "test02ValidateDeleteFilteredUnderUtilityStatusPage");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "test02ValidateDeleteFilteredUnderUtilityStatusPage", driver, e);
					fail(e.getMessage());
				}
			}
			//ADS-6619
			@Test
			public void test03ValidateRefreshUnderUtilityStatusPage() throws Throwable {
				try {
					doClick(statusMap.getUtilityStatusPageButtonRefresh());
					waitForAjaxExtJs();
					assertListElementsAreDisplayed(statusMap.utilityStatusPageGridElements(),printout);
					assertListElementsAreDisplayed(statusMap.utilityStatusEndTimeGridElements(),printout);
					assertListElementsAreDisplayed(statusMap.utilityStatusDurationGridElements(),printout);
					ExtentReport.logPass("PASS", "test02ValidateRefreshUnderUtilityStatusPage");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "test02ValidateRefreshUnderUtilityStatusPage", driver, e);
					fail(e.getMessage());
				}
			}
			//ADS-6518
			@Test
			public void test04ValidateDefaultTabInUtilityStatusPage() throws Throwable {
				try {
					assertElementIsDisplayed(statusMap.getUtilityStatusPageButtonMyStatus());
					assertElementIsDisplayed(statusMap.utilityStatusPageScenarioNameColumnName());
					assertListElementsAreDisplayed(statusMap.utilityStatusDurationGridElements(), printout);
					ExtentReport.logPass("PASS", "test04ValidateDefaultTabInUtilityStatusPage");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "test04ValidateDefaultTabInUtilityStatusPage", driver, e);
					fail(e.getMessage());
				}
			}
			//ADS-6517
			@Test
			public void test05ValidateAllStatusInUtilityStatusPage() throws Throwable {
				try {
					doClick(statusMap.getUtilityStatusPageButtonAllStatus());
					assertListElementsAreDisplayed(statusMap.utilityStatusDurationGridElements(), printout);
					assertElementIsDisplayed(statusMap.utilityStatusPageUserNameColumnName());
					ExtentReport.logPass("PASS", "test05ValidateAllStatusInUtilityStatusPage");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "test05ValidateAllStatusInUtilityStatusPage", driver, e);
					fail(e.getMessage());
				}
			}
			//ADS-6518
			@Test
			public void test06ValidateAllStatusByPaginationInUtilityStatusPage() throws Throwable {
				try {
					doClick(modelMap.getModelLibraryTableButtonLast());
					assertListElementsAreDisplayed(statusMap.utilityStatusDurationGridElements(), printout);
					assertElementIsDisplayed(statusMap.utilityStatusPageUserNameColumnName());
					doClick(modelMap.getModelLibraryTableButtonPrevious());
					assertListElementsAreDisplayed(statusMap.utilityStatusDurationGridElements(), printout);
					assertElementIsDisplayed(statusMap.utilityStatusPageUserNameColumnName());
					doClick(modelMap.getModelLibraryTableButtonNext());
					assertListElementsAreDisplayed(statusMap.utilityStatusDurationGridElements(), printout);
					assertElementIsDisplayed(statusMap.utilityStatusPageUserNameColumnName());
					ExtentReport.logPass("PASS", "test06ValidateAllStatusByPaginationInUtilityStatusPage");
				} catch (Exception | AssertionError e) {
					ExtentReport.logFail("FAIL", "test06ValidateAllStatusByPaginationInUtilityStatusPage", driver, e);
					fail(e.getMessage());
				}
				
			}
}
