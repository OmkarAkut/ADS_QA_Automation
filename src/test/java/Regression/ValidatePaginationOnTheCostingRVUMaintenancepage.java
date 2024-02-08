package Regression;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidatePaginationOnTheCostingRVUMaintenancepage  extends GoHelper{
	static CostingMap costing;
	static ContractingMap modelMap;
	static ModelLibraryMap modelLibrary;
	/** Automates test ticket ADS-6508*/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidatePaginationOnTheCostingRVUMaintenancepage", "webdriver.scripts.costing", "ValidatePaginationOnTheCostingRVUMaintenancepage");
		try {
			modelLibrary = BuildMap.getInstance(driver, ModelLibraryMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("RVU Maintenance");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6508
	@Test
	public void test01AssertPaginationAvailableInRVU() throws Throwable {
		  try {
			WebElement[] modelLibraryElements = {
			          modelLibrary.modelLibraryTableText4PageShown(),
			          modelLibrary.getModelLibraryTableButtonFirst(),
			          modelLibrary.getModelLibraryTableButtonPrevious(),
			          modelLibrary.getModelLibraryFieldInputNumber(),
			          modelLibrary.getModelLibraryTableButtonGo(),
			          modelLibrary.getModelLibraryTableButtonNext(),
			          modelLibrary.getModelLibraryTableButtonLast()
			  };
			  assertElementsAreDisplayed(modelLibraryElements, printout);
			  ExtentReport.logPass("PASS", "test01AssertPaginationAvailableInRVU");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01AssertPaginationAvailableInRVU", driver, e);
				fail(e.getMessage());
			}
	}
	
	@Test
	public void test02ValidateNextPageAndPreviousPage() throws Throwable {
		try {
			doClick(modelLibrary.getModelLibraryTableButtonNext());
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonPrevious(), printout);
			doClick(modelLibrary.getModelLibraryTableButtonPrevious());
			assertElementIsDisabled( modelLibrary.getModelLibraryTableButtonPrevious(), printout);
			ExtentReport.logPass("PASS", "test02ValidateNextPageAndPreviousPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateNextPageAndPreviousPage", driver, e);
			fail(e.getMessage());
		}
}
	@Test
	public void test03ValidateFirstPageAndLastPage() throws Throwable {
		try {
			doClick(modelLibrary.getModelLibraryTableButtonLast());
			assertElementIsDisabled( modelLibrary.getModelLibraryTableButtonLast(), printout);
			doClick(modelLibrary.getModelLibraryTableButtonFirst());
			assertElementIsDisabled( modelLibrary.getModelLibraryTableButtonFirst(), printout);
			ExtentReport.logPass("PASS", "test03ValidateFirstPageAndLastPage");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateFirstPageAndLastPage", driver, e);
			fail(e.getMessage());
		}

	}
	@Test
	public void test04ValidateGoButtonInPagination() throws Throwable {
		try {
			modelLibrary.getModelLibraryFieldInputNumber().clear();
			modelLibrary.getModelLibraryFieldInputNumber().sendKeys("2");
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonFirst(), printout);
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonPrevious(), printout);
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonLast(), printout);
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonNext(), printout);

			ExtentReport.logPass("PASS", "test04ValidateGoButtonInPagination");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateGoButtonInPagination", driver, e);
			fail(e.getMessage());
		}

	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("RVU Maintenance");
		ExtentReport.report.flush();

	}
}
