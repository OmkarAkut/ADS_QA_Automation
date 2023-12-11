package webdriver.globalscripts.help;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.*;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelpLinksMainPagesAds2524 extends PageTestHelper {

  private static AnalyticsMap analyticsMap;
  private static ContractingMap contractingMap;
  private static CostingMap costingMap;
  private static ReportingMap reportingMap;
  private static StatusMap statusMap;
  private static SystemMaintenanceMap sysmaint;
  private static DialogsMap dialogsMap;
  private static GeneralElementsMap generalElement;
  private static ModelLibraryMap modelLibrary;

  /** Automates ADS-2524. Verifies that the help link on each page of the application links to the
   * user help guide and the appropriate help page in the guide displays correctly.
 * @throws Throwable 
   */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("HelpLinksMainPagesAds2524", "webdriver.globalscripts.help", "HelpLinksMainPagesAds2524");
    try {
		analyticsMap = BuildMap.getInstance(driver, AnalyticsMap.class);
		contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		reportingMap = BuildMap.getInstance(driver, ReportingMap.class);
		statusMap = BuildMap.getInstance(driver, StatusMap.class);
		sysmaint = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
		dialogsMap = BuildMap.getInstance(driver, DialogsMap.class);
		generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
		modelLibrary = BuildMap.getInstance(driver, ModelLibraryMap.class);
		System.out.println("TEST CLASS: " + HelpLinksMainPagesAds2524.class.getSimpleName());
		Login.loginUser("ApplicationAdministrator1");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
	}
  }


  @Test
  public void test01GlobalHeaderPageTest() throws Throwable {
    try {
      //waitForAjaxExtJs();
      testHelpLink(generalElement.getGlobalHeaderButtonHelp(), "Decision Support", printout);
      ExtentReport.logPass("PASS", "test01GlobalHeaderPageTest");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test01GlobalHeaderPageTest", driver, e);
      fail("Global Header Page Test Failed");
    }
  }

  @Test
  public void test02FilterDialogPageTest() throws Throwable {
    try {
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
      ExtentReport.logPass("PASS", "test02FilterDialogPageTest");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test02FilterDialogPageTest", driver, e);
    	fail("Filter Dialog Test Failed - Users Page");
    }
  }

  @Test
  public void testStatusTabCalculationStatusPageTest() throws Throwable {
    try {
      goToPage("Calculation Status");
      //waitForAjaxExtJs();
      statusMap.getCalculationStatusPageFormFieldSearch().click();
      testHelpLink(statusMap.getCalculationStatusPageHelpLink(), "Calculation Status", printout);
      doClosePageOnLowerBar("Calculation Status");
      ExtentReport.logPass("PASS", "testStatusTabCalculationStatusPageTest");

    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test02FilterDialogPageTest", driver, e);
      fail("Calculation Status Page Test Failed");
    }
  }

  @Test
  public void testStatusTabImportExportStatusPageTest() throws Throwable {
    try {
      goToPage("Import/Export Status");
      //waitForAjaxExtJs();
      statusMap.getImportExportStatusPageFormFieldSearch().click();
      testHelpLink(statusMap.getImportExportStatusPageHelpLink(),
          "Import/Export Status", printout);
      doClosePageOnLowerBar("Import/Export Status");
      ExtentReport.logPass("PASS", "testStatusTabImportExportStatusPageTest");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testStatusTabImportExportStatusPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testStatusTabUtilityStatusPageTest() throws Throwable {
    try {
      goToPage("Utility Status");
      //waitForAjaxExtJs();
      statusMap.getUtilityStatusPageFormFieldSearch().click();
      testHelpLink(statusMap.getUtilityStatusPageHelpLink(), "Utility Status", printout);
      doClosePageOnLowerBar("Utility Status");
      ExtentReport.logPass("PASS", "testStatusTabUtilityStatusPageTest");

    }catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testStatusTabUtilityStatusPageTest", driver, e);

        fail(e.getMessage());
      }
  }
// The BELOW COMMENTED TESTS are no longer relevant, so leaving them in for historic purposes
//  @Test
//  public void testReportingTabIcd9Icd10GemsAnalysisPageTest() throws InterruptedException {
//    String iframePartialXpath = "//iframe[contains(@src,'gemsAnalysis')]";
//    String expectedHelpPageTitle = "GEMs Analysis";
//    try {
//      goToPage("Gems Analysis");
//      Thread.sleep(5000);
//      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'gemsInquiry.html')]")));
//      testHelpLink(reportingMap.getReportingTabGemsAnalysisPageHelpLink(), expectedHelpPageTitle, printout);
////      testHelpLinkWithFrames(iframePartialXpath,
////          reportingMap.getReportingTabGemsAnalysisPageHelpLink(), expectedHelpPageTitle, printout);
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//    } catch (Throwable e) {
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//      fail(e.getMessage());
//    }
//  }

//  @Test
//  public void testReportingTabIcd9Icd10GemsInquiryPageTest() throws InterruptedException {
//    String iframePartialXpath = "//iframe[contains(@src,'gemsInquiry.html')]";
//    String expectedHelpPageTitle = "GEMs Inquiry";
//    try {
//      goToPage("Gems Inquiry");
//      Thread.sleep(5000);
//      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'gemsInquiry.html')]")));
//      testHelpLink(reportingMap.getReportingTabGemsInquiryPageHelpLink(), expectedHelpPageTitle, printout);
//      //testHelpLinkWithFrames(iframePartialXpath, reportingMap.getReportingTabGemsInquiryPageHelpLink(), expectedHelpPageTitle, printout);
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//    } catch (Throwable e) {
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//      fail(e.getMessage());
//    }
//  }

  @Test
  public void testReportingTabReportDateMaintenancePageTest() throws Throwable {
    try {
      goToPage("Report Date Maintenance");
      //waitForAjaxExtJs();
      testHelpLink(reportingMap.getReportingTabReportDateMaintenancePageHelpLink(), "Report Date Maintenance", printout);
      doClosePageOnLowerBar("Report Date Maintenance");
      ExtentReport.logPass("PASS", "testReportingTabReportDateMaintenancePageTest");
    } catch(Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testStatusTabUtilityStatusPageTest", driver, e);

        fail(e.getMessage());
      }
  }

  @Test
  public void testReportingTabReportMenuMaintenancePageTest() throws Throwable {
    try {
      goToPage("Report Menu Maintenance");
      //waitForAjaxExtJs();
      testHelpLink(reportingMap.getReportingTabReportMenuMaintenancePageHelpLink(), "Report Menu Item List", printout);
      //assertTableColumnHeaders(reportingMap.getReportingTabReportMenuMaintenancePageTableCornerCell(), headers.expectedReportingTabReportMenuMaintenancePageTableHeaders, printout);
      doClosePageOnLowerBar("Report Menu Maintenance");
      ExtentReport.logPass("PASS", "testReportingTabReportMenuMaintenancePageTest");

    } catch(Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testReportingTabReportMenuMaintenancePageTest", driver, e);

        fail(e.getMessage());
      }
  }

  //The below test is now obsolete and won't be fixed - so leaving for historic purposes only
//   @Test
//  public void testReportingTabReportLibraryPageTest() throws InterruptedException {
//    String expectedHelpPageTitle = "xxxxxx";
//    //String firstHandle;
//    try {
//      goToPage("Report Library");
//      Thread.sleep(5000);
//      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
//      testHelpLink(reportingMap.getReportingTabGemsInquiryPageHelpLink(), expectedHelpPageTitle, printout);
//
////      waitForJsWindowOnload();
////      firstHandle = driver.getWindowHandle();
////      waitForElementDoWhileLoop(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")), printout);
////      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
////      waitForElementDoWhileLoop(reportingMap.getReportLibraryPageName(), printout);
////      waitForSpinnerToEnd();
////      waitForJsWindowOnload();
////      System.out.println("Sleep 3000 1");
////      Thread.sleep(3000);
////      reportingMap.getReportLibraryPageHelpLink().click();
////      waitForSpinnerToEnd();
////      waitForJsWindowOnload();
////      System.out.println("Sleep 5000 2");
////      Thread.sleep(5000);
////
////      //on help page
////      Set<String> handles = driver.getWindowHandles();
////      for (String handle:handles) {
////        System.out.println("help handles: " + handle);
////      }
////
////      for (String handle : handles) {
////        System.out.println("Current Handle: " + handle);
////        if (!firstHandle.equals(handle)) {
////          driver.switchTo().window(handle);
////          System.out.println("Switched to Handle: " + handle);
////          break;
////        }
////      }
////
////      //assert
////      Thread.sleep(5000);
////      waitForJsWindowOnload();
////      driver.switchTo().frame("topic");
////      WebElement header = driver.findElement(By.xpath("//body/h1"));
////      String actualHeader = header.getText();
////      String expectedHeader = "Report Library";
////      System.out.println("Expected Header Text: " + expectedHeader);
////      System.out.println("Actual Header Text  : " + actualHeader);
////      assertEquals(expectedHeader, actualHeader);
////
////      //switch back and close
////      for (String handle : handles) {
////        System.out.println("Current Handle: " + handle);
////        if (firstHandle.equals(handle)) {
////          driver.switchTo().window(handle);
////          System.out.println("Switched to Handle: " + handle);
////          break;
////        }
////      }
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("Report Library");
//    } catch (Throwable e) {
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("Report Library");
//      fail(e.getMessage());
//    }
//  }

  @Test
  public void testAnalyticsRefreshScenariosPageTest() throws Throwable {
    try {
      goToPage("Analytic Refresh Scenarios");
      //waitForAjaxExtJs();
      testHelpLink(analyticsMap.getAnalyticsRefreshScenariosPageHelpLink(), "Analytics Refresh Scenarios", printout);
      doClosePageOnLowerBar("Analytic Refresh Scenarios");
      ExtentReport.logPass("PASS", "testAnalyticsRefreshScenariosPageTest");
    } catch(Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testAnalyticsRefreshScenariosPageTest", driver, e);

        fail(e.getMessage());
      }
  }

  @Test
  public void testContractingTabApcAllocationPageTest() throws Throwable {
    try {
      goToPage("APC Allocation");
      //waitForAjaxExtJs();
      testHelpLink(contractingMap.getApcAllocationPageHelpLink(), "Utility Manager", printout);
      doClosePageOnLowerBar("APC Allocation");
      ExtentReport.logPass("PASS", "testContractingTabApcAllocationPageTest");

    } catch(Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testContractingTabApcAllocationPageTest", driver, e);

        fail(e.getMessage());
      }
  }

  @Test
  public void testContractingTabContractualAllowanceExportPageTest() throws Throwable {
    try {
      goToPage("Contractual Allowance Export");
      //waitForAjaxExtJs();
      testHelpLink(contractingMap.getContractualAllowanceExportPageHelpLink(), "Contractual Allowances List", printout);
      doClosePageOnLowerBar("Contractual Allowance Export");
      ExtentReport.logPass("PASS", "testContractingTabContractualAllowanceExportPageTest");
    } catch(Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testContractingTabContractualAllowanceExportPageTest", driver, e);

        fail(e.getMessage());
      }
  }

  @Test
  public void testCostingTabCostingModelsPageTest() throws Throwable {
    try {
      goToPage("Costing Models");
      //waitForAjaxExtJs();
      testHelpLink(modelLibrary.getModelLibraryPageHelpLink(), "Model Library", printout);
      doClosePageOnLowerBar("Costing Models");
      ExtentReport.logPass("PASS", "testCostingTabCostingModelsPageTest");
      
    } catch (Exception|AssertionError e) {
    ExtentReport.logFail("FAIL", "testCostingTabCostingModelsPageTest", driver, e);

      fail("Costing Models page test failed");
    }
  }

  @Test
  public void testCostingTabCostModelScenarioCalculationPageTest() throws Throwable {
    try {
      goToPage("Cost Model Scenario Calculation");
      //waitForAjaxExtJs();
      testHelpLink(costingMap.getCostModelScenarioCalculationPageHelpLink(), "Cost Model Calculation Scenario List", printout);
      doClosePageOnLowerBar("Cost Model Calculation Scenarios");
      ExtentReport.logPass("PASS", "testCostingTabCostModelScenarioCalculationPageTest");
    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testCostingTabCostModelScenarioCalculationPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testCostingTabRvuMaintenancePageTest() throws Throwable {
    try {
      goToPage("RVU Maintenance");
      //waitForAjaxExtJs();
      testHelpLink(costingMap.getRvuMaintenancePageHelpLink(), "RVU Maintenance List", printout);
      doClosePageOnLowerBar("RVU Maintenance");
      ExtentReport.logPass("PASS", "testCostingTabRvuMaintenancePageTest");

    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testCostingTabRvuMaintenancePageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testCostingTabUnitCostQuickCalculationPageTest() throws Throwable {
    try {
      //doMaximizeWindow();
      goToPage("Unit Cost Quick Calculation");
      //waitForAjaxExtJs();
      testHelpLink(costingMap.getUnitCostQuickCalculationPageHelpLink(), "Unit Cost Quick Calculation", printout);
      doClosePageOnLowerBar("Unit Cost Quick Calculation");
      ExtentReport.logPass("PASS", "testCostingTabUnitCostQuickCalculationPageTest");
    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testCostingTabUnitCostQuickCalculationPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabTerminalServerSessionsPageTest() throws Throwable {
    try {
      goToPage("Terminal Server Sessions");
      waitForAjaxExtJs();
      waitForPresenceOfElement(getPageHeaderPath("Terminal Server Sessions"));
      doClick(getPageHeaderPath("Terminal Server Sessions"));
      testHelpLink(sysmaint.getTerminalServerSessionsPageLinkHelp(), "Sessions", printout);
      doClosePageOnLowerBar("Terminal Server Sessions");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabTerminalServerSessionsPageTest");
    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabTerminalServerSessionsPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabCustomizeTaskListsPageTest() throws Throwable {
    try {
      goToPage("Customize Task Lists");
      //waitForAjaxExtJs();
      waitForPresenceOfElement(getPageHeaderPath("Customize Task Lists"));
      doClick(getPageHeaderPath("Customize Task Lists"));
      testHelpLink(sysmaint.getCustomizeTaskListsPageLinkHelp(), "Customize Task Lists", printout);
      doClosePageOnLowerBar("Customize Task Lists");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabCustomizeTaskListsPageTest");

    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabCustomizeTaskListsPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabCustomizeMaintainDataPageTest() throws Throwable {
    try {
      goToPage("Customize Maintain Data");
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      waitForPresenceOfElement(getPageHeaderPath("Customize Maintain Data"));
      doClick(getPageHeaderPath("Customize Maintain Data"));
      testHelpLink(sysmaint.getCustomizeMaintainDataPageLinkHelp(),"Customize Maintain Data", printout);
      doClosePageOnLowerBar("Customize Maintain Data");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabCustomizeMaintainDataPageTest");
    }  catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabCustomizeMaintainDataPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabGeneralSettingsPageTest() throws Throwable {
    try {
      goToPage("General Settings");
      //waitForAjaxExtJs();
      testHelpLink(sysmaint.getGeneralSettingsPageHelpLink(),"General Settings", printout);
      doClosePageOnLowerBar("General Settings");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabGeneralSettingsPageTest");
    }  catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabGeneralSettingsPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabSecuritySettingsPageTest() throws Throwable {
    try {
      goToPage("Security Settings");
      //waitForAjaxExtJs();
      testHelpLink(sysmaint.getSecuritySettingsPageHelpLink(),"Security Settings", printout);
      doClosePageOnLowerBar("Security Settings");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabSecuritySettingsPageTest");
    }  catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabSecuritySettingsPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabRolesPageTest() throws Throwable {
    try {
      goToPage("Roles");
      //waitForAjaxExtJs();
      testHelpLink(sysmaint.getRolesPageHelpLink(),"Role List", printout);
      //assertTableColumnHeaders(sysmaint.getRolesPageTableCornerCell(), headers.expectedSystemMaintenanceTabRolesPageTableHeaders, printout);
      doClosePageOnLowerBar("Roles");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabRolesPageTest");
    }  catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabRolesPageTest", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void testSystemMaintenanceTabUsersPageTest() throws Throwable {
    try {
      goToPage("Users");
      //waitForAjaxExtJs();
      testHelpLink(sysmaint.getUsersPageHelpLink(),"User List", printout);
      //assertTableColumnHeaders(sysmaint.getUsersPageTableCornerCell(), headers.expectedSystemMaintenanceTabUsersPageTableHeaders, printout);
      doClosePageOnLowerBar("Users");
      ExtentReport.logPass("PASS", "testSystemMaintenanceTabUsersPageTest");
    }  catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "testSystemMaintenanceTabUsersPageTest", driver, e);

      fail(e.getMessage());
    }
  }
  @AfterClass
  public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
