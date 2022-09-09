package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.DataMaintenanceHelper;
import webdriver.scripts.datamaintenance.datamaintenancedata.DataMaintenanceTreePagesData;
import webdriver.utilities.Print;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataMaintenanceTreePageLoads extends DataMaintenanceHelper {

  Print printer = new Print();

  /**
   * Test Location: Data Maintenance > Maintain Data > Tree structure (left side)
   * Data file: DataMaintenanceTreePagesData
   * Description: Verifies that page names display in proper directories and
   * that individual pages load properly (in main pane).
   * There is a short and complete version.
   * Short tests the first few pages, complete tests all the pages.
   * This is changed by changing the data that is used.
   */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("DataMaintenanceTreePageLoads", "webdriver.scripts.datamaintenance.maintaindata","DataMaintenanceTreePageLoads");
    try {
		System.out.println("TEST CLASS: " + DataMaintenanceTreePageLoads.class.getSimpleName());
		Login.loginUser("ApplicationAdministrator1");
		goToPage("Maintain Data");
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test10MaintainDataPageAssertTreeListContainsTheFourMainDirectories() throws Throwable {
    String[] mainFolders = {"Contracting", "Costing", "Episode", "General"};
    for (String mainFolder : mainFolders) {
      try {
		assertThatElementIsDisplayed(
		          driver.findElement(By.xpath("//div[text()='" + mainFolder + "']"
		                  + "/img[contains(@class,'x-tree-icon x-tree-icon-parent ')]")));
		ExtentReport.logPass("PASS", "test10MaintainDataPageAssertTreeListContainsTheFourMainDirectories"+" : "+"Tree List Contains : "+mainFolder);

	} catch (Exception|AssertionError e) {
		System.out.println(e.getMessage());
		ExtentReport.logFail("FAIL", "test10MaintainDataPageAssertTreeListContainsTheFourMainDirectories", driver, e);
		fail(e.getMessage());
	}
     
    }
  }

  @Ignore
  @Test
  public void test20MaintainDataPageAssertTreeListContractingSectionContainsAllItems()
          throws Throwable {
    try {
		assertTreeListSectionContainsAllItems(
		        "Contracting",
		        "ASC Schemes",
		        DataMaintenanceTreePagesData.expectedContractingTreeSectionPages
		);
		ExtentReport.logPass("PASS", "test20MaintainDataPageAssertTreeListContractingSectionContainsAllItems");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test20MaintainDataPageAssertTreeListContractingSectionContainsAllItems", driver, e);
		fail(e.getMessage());

	}
  }

  @Ignore
  @Test
  public void test30MaintainDataPageAssertTreeListContractingSectionPagesLoad()
          throws Throwable {
    try {
		assertPageLoads(
		    Arrays.asList(DataMaintenanceTreePagesData.expectedContractingTreeSectionPages)
		);
		ExtentReport.logPass("PASS", "test30MaintainDataPageAssertTreeListContractingSectionPagesLoad");
	}catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test30MaintainDataPageAssertTreeListContractingSectionPagesLoad", driver, e);
		fail(e.getMessage());

	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
