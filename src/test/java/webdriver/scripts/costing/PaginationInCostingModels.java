package webdriver.scripts.costing;

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
import webdriver.helperstatic.GoStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaginationInCostingModels extends GoHelper {
	static CostingMap costing;
	static GoStatic goStatic;
	static ModelLibraryMap modelLibrary;
/** Automates test ticket ADS-6652*/
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("PaginationInCostingModels", "webdriver.scripts.costing", "PaginationInCostingModels");
		try {
			modelLibrary = BuildMap.getInstance(driver, ModelLibraryMap.class);
//			goStatic=BuildMap.getInstance(driver, GoStatic.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6652 all steps
	@Test
	public void test01AssertPaginationAvailableInCostingModel_6652() throws Throwable {
		  try {
			WebElement[] modelLibraryElements = {
			         modelLibrary.getmodelLibraryTableText3PageShown(),
			          modelLibrary.getModelLibraryTableButtonFirst(),
			          modelLibrary.getModelLibraryTableButtonPrevious(),
			          modelLibrary.getModelLibraryFieldInputNumber(),
			          modelLibrary.getModelLibraryTableButtonGo(),
			          modelLibrary.getModelLibraryTableButtonNext(),
			          modelLibrary.getModelLibraryTableButtonLast()
			  };
			  assertElementsAreDisplayed(modelLibraryElements, printout);
			  ExtentReport.logPass("PASS", "test01AssertPaginationAvailableInCostingModel");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01AssertPaginationAvailableInCostingModel", driver, e);
				fail(e.getMessage());
			}
	}
	
	@Test
	public void test02ValidateNextPageAndPreviousPage_6652() throws Throwable {
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
	public void test03ValidateFirstPageAndLastPage_6652() throws Throwable {
		try {
			doClick(modelLibrary.getModelLibraryTableButtonLast());
			assertElementIsDisabled( modelLibrary.getModelLibraryTableButtonLast(), printout);
			doClick(modelLibrary.getModelLibraryTableButtonFirst());
			assertElementIsDisabled( modelLibrary.getModelLibraryTableButtonFirst(), printout);
			ExtentReport.logPass("PASS", "test01CreateNewCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewCostModel", driver, e);
			fail(e.getMessage());
		}

	}
	@Test
	public void test04ValidateGoButtonInPagination_6652() throws Throwable {
		try {
			modelLibrary.getModelLibraryFieldInputNumber().clear();
			modelLibrary.getModelLibraryFieldInputNumber().sendKeys("2");
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonFirst(), printout);
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonPrevious(), printout);
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonLast(), printout);
			assertElementIsEnabled( modelLibrary.getModelLibraryTableButtonNext(), printout);

			ExtentReport.logPass("PASS", "test01CreateNewCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewCostModel", driver, e);
			fail(e.getMessage());
		}

	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Costing Models");
		ExtentReport.report.flush();

	}
}
