package Regression;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TableColumnSorting extends GoHelper {
	static CostingMap costing;
	static ContractingMap modelMap;

	/** Automates test ticket ADS-6654,ADS-6509 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("TableColumnSorting", "webdriver.scripts.costing",
				"TableColumnSorting");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6654
	@Test
	public void test01AssertTableOnSortByNameColumnCostingModel_6654() throws Throwable {
		try {
			SortByNameColumn();
			ExtentReport.logPass("PASS", "test01AssertTableOnSortByNameColumn");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertTableOnSortByNameColumn", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6654
	@Test
	public void test02AssertTableOnSortByDateCreatedColumn_6654() throws Throwable {
		try {
			SortByDateCreatedColumn();
			ExtentReport.logPass("PASS", "test01AssertTableOnSortByDateCreatedColumn");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertTableOnSortByDateCreatedColumn", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6509
	@Test
	public void test03AssertTableOnSortByNameColumnEpisode_6509() throws Throwable {
		try {
			goToPage("Episode Models");
			SortByNameColumn();
			ExtentReport.logPass("PASS", "test03AssertTableOnSortByNameColumnEpisode");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertTableOnSortByNameColumnEpisode", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6509
	@Test
	public void test04AssertTableOnSortByDateCreatedColumnEpisode_6509() throws Throwable {
		try {
			SortByDateCreatedColumn();
			ExtentReport.logPass("PASS", "test04AssertTableOnSortByDateCreatedColumnEpisode");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AssertTableOnSortByDateCreatedColumnEpisode", driver, e);
			fail(e.getMessage());
		}
	}
	

	
	public void SortByNameColumn() throws Throwable {
		try {
			doClick(costing.getlandingPageNameSortAsc());
			ContractModelsHelper.sortTableGridDescending(CostingMap.getlandingPageGridList());
			doClick(costing.getlandingPageNameSortDesc());
			ContractModelsHelper.sortTableGridAscending(CostingMap.getlandingPageGridList());
			doClick("//span[text()='Date Created']");
			doClick(costing.getlandingPageDateSortAsc());
			ContractModelsHelper.sortTableGridDescending(CostingMap.getlandingPageGridList());
			doClick(costing.getlandingPageDateSortDesc());
			ContractModelsHelper.sortTableGridAscending(CostingMap.getlandingPageGridList());
			ExtentReport.logPass("PASS", "test01AssertTableOnSortByNameColumn");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertTableOnSortByNameColumn", driver, e);
			fail(e.getMessage());
		}

	}

	
	public void SortByDateCreatedColumn() throws Throwable {
		try {
			doClick("//span[text()='Date Created']");
			driverDelay(100);
			doClick(costing.getlandingPageDateSortDesc());
			ContractModelsHelper.sortTableGridDescending(CostingMap.getlandingPageGridList());
			doClick(costing.getlandingPageDateSortAsc());
			ContractModelsHelper.sortTableGridAscending(CostingMap.getlandingPageGridList());
			ExtentReport.logPass("PASS", "test01AssertTableOnSortByDateCreatedColumn");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertTableOnSortByDateCreatedColumn", driver, e);
			fail(e.getMessage());
		}

	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}
}
