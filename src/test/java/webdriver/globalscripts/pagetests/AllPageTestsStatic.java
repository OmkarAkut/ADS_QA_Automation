package webdriver.globalscripts.pagetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import webdriver.maps.AnalyticsMap;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.ReportingMap;
import webdriver.maps.StatusMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AllPageTestsStatic extends PageTestHelperStatic {

	private static AnalyticsMap analyticsMap;
	private static ContractingMap contractingMap;
	private static CostingMap costingMap;
	private static ReportingMap reportingMap;
	private static StatusMap statusMap;
	private static SystemMaintenanceMap sysmaint;
	private static DialogsMap dialogsMap;
	private static GeneralElementsMap generalElement;
	private static ModelLibraryMap modelLibrary;

	/** This scripts verifies that the help link on each page of the application links to the
	 * user help guide and the appropriate help page in the guide displays correctly.
	 */
	@BeforeClass
	public static void setupScript() {
		analyticsMap = BuildMap.getInstance(driver, AnalyticsMap.class);
		contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		reportingMap = BuildMap.getInstance(driver, ReportingMap.class);
		statusMap = BuildMap.getInstance(driver, StatusMap.class);
		sysmaint = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
		dialogsMap = BuildMap.getInstance(driver, DialogsMap.class);
		generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
		modelLibrary = BuildMap.getInstance(driver, ModelLibraryMap.class);
		System.out.println("TEST CLASS: " + AllPageTestsStatic.class.getSimpleName());
		evolveLoginStaticUser(Users.AutomationTester1);
	}

	@Test
	public void testCostingTabCostingModelsPageTest() {
		try {
			goToPage("Costing Models");
			waitForAjaxExtJs();
			testHelpLink(modelLibrary.getModelLibraryPageHelpLink(), "Model Library", printout);
			doClosePageOnLowerBar("Model Library");
		} catch (Throwable e) {
			fail("Costing Models page test failed");
		}
	}

	@Test
	public void test01GlobalHeaderPageTest() {
		try {
			System.out.println("TEST: Global Header Page Test");
			waitForAjaxExtJs();
			testHelpLink(generalElement.getGlobalHeaderButtonHelp(), "Decision Support", printout);
			System.out.println("TEST RESULT: PASSED");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("TEST RESULT: FAILED");
			fail("Global Header Page Test Failed");
		}
	}

	@Test
	public void test02FilterDialogPageTest() {
		try {
			System.out.println("TEST: Filter Dialog (Users Page)");
			goToPage("Users");
			waitForAjaxExtJs();
			doClick(sysmaint.getUsersPageButtonFilter());
			waitForAjaxExtJs();
			waitForElementToBeVisible(dialogsMap.getFilterDialogButtonCancelAndClose());
			String dialogHeader = dialogsMap.getFilterDialogHeader().getText();
			System.out.println("Filter Dialog Header: " + dialogHeader);
			assertEquals("Filter Users", dialogHeader);
			testHelpLink(dialogsMap.getFilterDialogHelpLink(), "Filter", printout);
			doClick(dialogsMap.getFilterDialogButtonCancelAndClose());
			doClosePageOnLowerBar("Users");
			System.out.println("FILTER TEST PASSED");
		} catch (Throwable e) {
			e.printStackTrace();      System.out.println("FILTER TEST RESULT: FAILED");
			fail("Filter Dialog Test Failed - Users Page");
		}
	}

	@Test
	public void testStatusTabCalculationStatusPageTest() {
		try {
			System.out.println("START TEST: Calculation Status Page Test");
			goToPage("Calculation Status");
			waitForAjaxExtJs();
			Thread.sleep(3000);
			testHelpLink(statusMap.getCalculationStatusPageHelpLink(), "Calculation Status", printout);
			doClosePageOnLowerBar("Calculation Status");
			System.out.println("TEST PASSED");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("TEST RESULT: FAILED");
			fail("Calculation Status Page Test Failed");
		}
	}

	@Test
	public void testStatusTabImportExportStatusPageTest() {
		try {
			goToPage("Import/Export Status");
			waitForAjaxExtJs();
			testHelpLink(statusMap.getImportExportStatusPageHelpLink(),
					"Import/Export Status", printout);
			doClosePageOnLowerBar("Import/Export Status");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStatusTabUtilityStatusPageTest() {
		try {
			goToPage("Utility Status");
			waitForAjaxExtJs();
			testHelpLink(statusMap.getUtilityStatusPageHelpLink(), "Utility Status", printout);
			doClosePageOnLowerBar("Utility Status");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void testReportingTabIcd9Icd10GemsAnalysisPageTest() {
		String iframePartialXpath = "//iframe[contains(@src,'gemsAnalysis')]";
		String expectedHelpPageTitle = "GEMs Analysis";
		try {
			goToPage("Gems Analysis");
			Thread.sleep(5000);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'gemsInquiry.html')]")));
			testHelpLink(reportingMap.getReportingTabGemsAnalysisPageHelpLink(), expectedHelpPageTitle, printout);
			//      testHelpLinkWithFrames(iframePartialXpath,
			//          reportingMap.getReportingTabGemsAnalysisPageHelpLink(), expectedHelpPageTitle, printout);
			driver.switchTo().parentFrame();
			doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void testReportingTabIcd9Icd10GemsInquiryPageTest() {
		String iframePartialXpath = "//iframe[contains(@src,'gemsInquiry.html')]";
		String expectedHelpPageTitle = "GEMs Inquiry";
		try {
			goToPage("Gems Inquiry");
			Thread.sleep(5000);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'gemsInquiry.html')]")));
			testHelpLink(reportingMap.getReportingTabGemsInquiryPageHelpLink(), expectedHelpPageTitle, printout);
			//testHelpLinkWithFrames(iframePartialXpath, reportingMap.getReportingTabGemsInquiryPageHelpLink(), expectedHelpPageTitle, printout);
			driver.switchTo().parentFrame();
			doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testReportingTabReportDateMaintenancePageTest() {
		try {
			goToPage("Report Date Maintenance");
			waitForAjaxExtJs();
			testHelpLink(reportingMap.getReportingTabReportDateMaintenancePageHelpLink(), "Report Date Maintenance", printout);
			doClosePageOnLowerBar("Report Date...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testReportingTabReportMenuMaintenancePageTest() {
		try {
			goToPage("Report Menu Maintenance");
			waitForAjaxExtJs();
			testHelpLink(reportingMap.getReportingTabReportMenuMaintenancePageHelpLink(), "Report Menu Item List", printout);
			//assertTableColumnHeaders(reportingMap.getReportingTabReportMenuMaintenancePageTableCornerCell(), headers.expectedReportingTabReportMenuMaintenancePageTableHeaders, printout);
			doClosePageOnLowerBar("Report Menu...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void testReportingTabReportLibraryPageTest() {
		String expectedHelpPageTitle = "xxxxxx";
		//String firstHandle;
		try {
			goToPage("Report Library");
			Thread.sleep(5000);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
			testHelpLink(reportingMap.getReportingTabGemsInquiryPageHelpLink(), expectedHelpPageTitle, printout);

			//      waitForJsWindowOnload();
			//      firstHandle = driver.getWindowHandle();
			//      waitForElementDoWhileLoop(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")), printout);
			//      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
			//      waitForElementDoWhileLoop(reportingMap.getReportLibraryPageName(), printout);
			//      waitForSpinnerToEnd();
			//      waitForJsWindowOnload();
			//      System.out.println("Sleep 3000 1");
			//      Thread.sleep(3000);
			//      reportingMap.getReportLibraryPageHelpLink().click();
			//      waitForSpinnerToEnd();
			//      waitForJsWindowOnload();
			//      System.out.println("Sleep 5000 2");
			//      Thread.sleep(5000);
			//
			//      //on help page
			//      Set<String> handles = driver.getWindowHandles();
			//      for (String handle:handles) {
			//        System.out.println("help handles: " + handle);
			//      }
			//
			//      for (String handle : handles) {
			//        System.out.println("Current Handle: " + handle);
			//        if (!firstHandle.equals(handle)) {
			//          driver.switchTo().window(handle);
			//          System.out.println("Switched to Handle: " + handle);
			//          break;
			//        }
			//      }
			//
			//      //assert
			//      Thread.sleep(5000);
			//      waitForJsWindowOnload();
			//      driver.switchTo().frame("topic");
			//      WebElement header = driver.findElement(By.xpath("//body/h1"));
			//      String actualHeader = header.getText();
			//      String expectedHeader = "Report Library";
			//      System.out.println("Expected Header Text: " + expectedHeader);
			//      System.out.println("Actual Header Text  : " + actualHeader);
			//      assertEquals(expectedHeader, actualHeader);
			//
			//      //switch back and close
			//      for (String handle : handles) {
			//        System.out.println("Current Handle: " + handle);
			//        if (firstHandle.equals(handle)) {
			//          driver.switchTo().window(handle);
			//          System.out.println("Switched to Handle: " + handle);
			//          break;
			//        }
			//      }
			driver.switchTo().parentFrame();
			doClosePageOnLowerBar("Report Library");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAnalyticsRefreshScenariosPageTest() {
		try {
			goToPage("Analytic Refresh Scenarios");
			waitForAjaxExtJs();
			testHelpLink(analyticsMap.getAnalyticsRefreshScenariosPageHelpLink(), "Analytics Refresh Scenarios", printout);
			doClosePageOnLowerBar("Analytic Refresh...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContractingTabApcAllocationPageTest() {
		try {
			goToPage("APC Allocation");
			waitForAjaxExtJs();
			testHelpLink(contractingMap.getApcAllocationPageHelpLink(), "Utility Manager", printout);
			doClosePageOnLowerBar("APC Allocation");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testContractingTabContractualAllowanceExportPageTest() {
		try {
			goToPage("Contractual Allowance Export");
			waitForAjaxExtJs();
			testHelpLink(contractingMap.getContractualAllowanceExportPageHelpLink(), "Contractual Allowances List", printout);
			doClosePageOnLowerBar("Contractual...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCostingTabCostModelScenarioCalculationPageTest() {
		try {
			goToPage("Cost Model Scenario Calculation");
			waitForAjaxExtJs();
			testHelpLink(costingMap.getCostModelScenarioCalculationPageHelpLink(), "Cost Model Calculation Scenario List", printout);
			doClosePageOnLowerBar("Cost Model...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}


	@Test public void testRvuMaintenancePageTest() { try {
		goToPage("RVU Maintenance"); waitForAjaxExtJs();
		testHelpLink(costingMap.getRvuMaintenancePageHelpLink(),
				"RVU Maintenance List", printout); doClosePageOnLowerBar("RVU Maintenance");
	} catch (Throwable e) { fail(e.getMessage()); } }

	@Test
	public void testUnitCostQuickCalculationPageTest() {
		try {
			//doMaximizeWindow();
			goToPage("Unit Cost Quick Calculation");
			waitForAjaxExtJs();
			testHelpLink(costingMap.getUnitCostQuickCalculationPageHelpLink(), "Unit Cost Quick Calculation", printout);
			doClosePageOnLowerBar("Unit Cost Quick...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabTerminalServerSessionsPageTest() {
		try {
			goToPage("Terminal Server Sessions");
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getTerminalServerSessionsPageLinkHelp(), "Sessions", printout);
			doClosePageOnLowerBar("Terminal Server...");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabCustomizeTaskListsPageTest() {
		try {
			goToPage("Customize Task Lists");
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getCustomizeTaskListsPageLinkHelp(), "Customize Task Lists", printout);
			doClosePageOnLowerBar("Customize Task Lists");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabCustomizeMaintainDataPageTest() {
		try {
			goToPage("Customize Maintain Data");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getCustomizeMaintainDataPageLinkHelp(),"Customize Maintain Data", printout);
			doClosePageOnLowerBar("Customize Maintain Data");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabGeneralSettingsPageTest() {
		try {
			goToPage("General Settings");
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getGeneralSettingsPageHelpLink(),"General Settings", printout);
			doClosePageOnLowerBar("General Settings");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabSecuritySettingsPageTest() {
		try {
			goToPage("Security Settings");
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getSecuritySettingsPageHelpLink(),"Security Settings", printout);
			doClosePageOnLowerBar("Security Settings");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabRolesPageTest() {
		try {
			goToPage("Roles");
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getRolesPageHelpLink(),"Role List", printout);
			//assertTableColumnHeaders(sysmaint.getRolesPageTableCornerCell(), headers.expectedSystemMaintenanceTabRolesPageTableHeaders, printout);
			doClosePageOnLowerBar("Roles");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabUsersPageTest() {
		try {
			goToPage("Users");
			waitForAjaxExtJs();
			testHelpLink(sysmaint.getUsersPageHelpLink(),"User List", printout);
			//assertTableColumnHeaders(sysmaint.getUsersPageTableCornerCell(), headers.expectedSystemMaintenanceTabUsersPageTableHeaders, printout);
			doClosePageOnLowerBar("Users");
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}
}
