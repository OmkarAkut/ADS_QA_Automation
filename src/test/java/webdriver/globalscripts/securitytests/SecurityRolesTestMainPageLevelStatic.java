package webdriver.globalscripts.securitytests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Roles;

/**
 * OVERVIEW:  This test logs in with each of the standard user roles and verifies access authorizations.
 * This is the expanded version that includes testing on the application main tabs and each main page (the
 * pages that appear in the main tab dropdown menus).
 * Last Update: 11-12-19
 */
@RunWith(Parameterized.class)
public class SecurityRolesTestMainPageLevelStatic extends LoginRolesTesting {

  boolean printout = false;
  GeneralElementsMap generalElement;
  private String userRole;
 
  public SecurityRolesTestMainPageLevelStatic(Roles role) throws Exception {
    super(role);
    System.out.println("Role in constructor: " + role);
    this.userRole = String.valueOf(role);
  }

  @Before
  public void setupScript() throws Exception,Throwable {
	  	ExtentReport.reportCreate("SecurityRolesTestMainPageLevelStatic", "webdriver.globalscripts.securitytests", "SecurityRolesTestMainPageLevelStatic");
	     try {
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			ExtentReport.logPass("PASS", "setupScript");
	     } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		fail(e.getMessage());
		}
  }

  @Test
  public void testSecurityRolesTest() throws InterruptedException,Throwable {
    try {
		System.out.println("Testing User Role: " + userRole);
		testMainTabs();
		testAnalyticsTab();
		testReportingTab();
		testCostingTab();
		testContractingTab();
		testEpisodesTab();
		testBudgetingTab();
		testDataMaintenanceTab();
		testSystemMaintenanceTab();
		testStatusTab();
		ExtentReport.logPass("PASS", "testSecurityRolesTest");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testSecurityRolesTest", driver, e);
		fail(e.getMessage());
	}
  }

  //TO RUN SELECTIVELY, comment out the roles to ignore below
  @Parameterized.Parameters(name = "{index}: Role={0}")
  public static Collection<Object[]> roles() {
    Object[][] roles = new Object[][]{
            {Roles.valueOf("Application_Administrator")},
            {Roles.valueOf("System_Administrator")},
            {Roles.valueOf("Security_Administrator")},

//            {Roles.valueOf("Data_Administrator")},//Uncomment once login issue for this user is fixed 
            {Roles.valueOf("Cost_Analyst")},
            {Roles.valueOf("Costing_Department_Manager")},
            {Roles.valueOf("Contract_Analyst")},

            {Roles.valueOf("Contract_Administrator")},
            {Roles.valueOf("Contract_Reviewer")},
            {Roles.valueOf("Episode_Analyst")},
            {Roles.valueOf("Report_Administrator")},

            {Roles.valueOf("AdHoc_Report_Designer")},
            {Roles.valueOf("Report_User")},
//            {Roles.valueOf("Web_Intelligence_Designer")},//Uncomment once login issue for this user is fixed
//            {Roles.valueOf("Web_Intelligence_User")},//Uncomment once login issue for this user is fixed

            {Roles.valueOf("Budgeting_User")},
            {Roles.valueOf("Analytics_Administrator")},
            {Roles.valueOf("Analytics_Analyst")},
            {Roles.valueOf("Analytics_Designer")},

            {Roles.valueOf("Analytics_Executive")},
    };
    return Arrays.asList(roles);
  }


  //a test set of elements - the assert method asserts that these are or are not displayed
  //and assembles a set of actual values
  public void testMainTabs() throws InterruptedException {
    WebElement[] mainTabs = {
            generalElement.getAnalyticsTab(),
            generalElement.getReportingTab(),
            generalElement.getCostingTab(),
            generalElement.getContractingTab(),
            generalElement.getEpisodesTab(),
            generalElement.getBudgetingTab(),
            generalElement.getDataMaintenanceTab(),
            generalElement.getSystemMaintenanceTab(),
            generalElement.getStatusTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedTabResult(userRole)));
    assertPagesRolesTest(roleExpectedPages, mainTabs, userRole, "Main Tabs", printout);
   
  }

  public void testStatusTab() throws InterruptedException {
    //Clicks the Status Tab in order to open the dropdown menu
    WebElement[] statusTabPages = {
            generalElement.getCalculationStatusSubTab(),
            generalElement.getImportExportStatusSubTab(),
            generalElement.getUtilityStatusSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedStatusTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getStatusTab().isDisplayed());
      doClick(generalElement.getStatusTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(statusTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Status Tab", printout);
  }

  public void testSystemMaintenanceTab() throws InterruptedException {
    //Clicks the System Maintenance Tab in order to open the dropdown menu
    WebElement[] systemMaintenanceTabPages = {
            generalElement.getUsersSubTab(),
            generalElement.getRolesSubTab(),
            generalElement.getSecuritySettingsSubTab(),
            generalElement.getGeneralSettingsSubTab(),
            generalElement.getCustomizeMaintainDataSubTab(),
            generalElement.getCustomizeTaskListSubTab(),
            generalElement.getTerminalServerSessionsSubTab(),
            generalElement.getTerminalServerDesktopSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedMaintenanceTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getSystemMaintenanceTab().isDisplayed());
      doClick(generalElement.getSystemMaintenanceTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(systemMaintenanceTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "System Maintenance Tab", printout);
  }

  public void testDataMaintenanceTab() throws InterruptedException {
    //Clicks the Data Maintenance Tab in order to open the dropdown menu
    WebElement[] dataMaintenanceTabPages = {
            generalElement.getMaintainDataSubTab(),
            generalElement.getLoadDataSubTab(),
            generalElement.getUtilitiesSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedDataMaintenanceTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getDataMaintenanceTab().isDisplayed());
      doClick(generalElement.getDataMaintenanceTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(dataMaintenanceTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Data Maintenance Tab", printout);
  }

  public void testBudgetingTab() throws InterruptedException {
    //Clicks the Budgeting Tab in order to open the dropdown menu
    WebElement[] budgetingTabPages = {
            generalElement.getBudgetingSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedBudgetingTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getBudgetingTab().isDisplayed());
      doClick(generalElement.getBudgetingTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(budgetingTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Budgeting Tab", printout);
  }

  public void testEpisodesTab() throws InterruptedException {
    //Clicks the Episodes Tab in order to open the dropdown menu
    WebElement[] episodesTabPages = {
            generalElement.getEpisodeModelsSubTab(),
            generalElement.getEpisodeDataMaintenanceSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedEpisodesTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getEpisodesTab().isDisplayed());
      doClick(generalElement.getEpisodesTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(episodesTabPages, printout);

    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Episodes Tab", printout);
  }

  public void testContractingTab() throws InterruptedException {
    //Clicks the Contracting Tab in order to open the dropdown menu
    WebElement[] contractingTabPages = {
            generalElement.getContractModelsSubTab(),
            generalElement.getContractingDataMaintenanceSubTab(),
            generalElement.getContractualAllowanceExportSubTab(),
            generalElement.getApcAllocationSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedContractingTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getContractingTab().isDisplayed());
      doClick(generalElement.getContractingTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(contractingTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Contracting Tab", printout);
  }

  public void testCostingTab() throws InterruptedException {
    //Clicks the Costing Tab in order to open the dropdown menu
    WebElement[] costingTabPages = {
            generalElement.getCostingModelsSubTab(),
            generalElement.getCostingDataMaintenanceSubTab(),
            generalElement.getRVUMaintenanceSubTab(),
            generalElement.getCostModelScenarioCalculationSubTab(),
            generalElement.getUnitCostQuickCalculationSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedCostingTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getCostingTab().isDisplayed());
      doClick(generalElement.getCostingTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(costingTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Costing Tab", printout);
  }

  public void testReportingTab() throws InterruptedException {
    //Report Library, Web Intelligence, Ad Hoc Report Design, Report Menu Maintenance,
    //Report Date Maintenance, Report Publication, Ad Hoc Business View Maintenance, Web Intelligence Universe Maintenance
    //ICD9/ICD10 GEMs Inquiry, ICD9/ICD10 GEMs Analysis
    WebElement[] reportingTabPages = {
            generalElement.getReportingLibrarySubTab(),
            generalElement.getWebIntelligenceSubTab(),
            generalElement.getAdHocReportDesignSubTab(),
            generalElement.getReportMenuMaintenanceSubTab(),
            generalElement.getReportDateMaintenanceSubTab(),
            generalElement.getReportPublicationSubTab(),
            generalElement.getAdHocBusinessViewMaintenanceSubTab(),
            generalElement.getWebIntelligenceUniverseMaintenanceSubTab(),
            generalElement.getIcd9icd10GemsInquirySubTab(),
            generalElement.getIcd9icd10GemsAnalysisSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedReportingTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getReportingTab().isDisplayed());
      doClick(generalElement.getReportingTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(reportingTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Reporting Tab", printout);
  }

  public void testAnalyticsTab() throws InterruptedException {
    //Clicks the Analytics Tab in order to open the dropdown menu
    WebElement[] analyticsTabPages = {
            generalElement.getExecutiveDashboardSubTab(),
            generalElement.getAnalyticDashboardSubTab(),
            generalElement.getAnalyticRefreshScenariosSubTab(),
            generalElement.getAnalyticsAdministrationSubTab(),
            generalElement.getCustomizeAnalyticsSubTab(),
            generalElement.getCustomizeAnalyticsSessionsSubTab(),
            generalElement.getAnalyticsServerDesktopSubTab(),
            generalElement.getAnalyticsServerDesktopSessionsSubTab()
    };
    List<Integer> roleExpectedPages = new ArrayList<>(Arrays.asList(SecurityRolesTestDataSets.setExpectedAnalyticsTabResult(userRole)));
    List<Integer> actualList;
    try {
      assertTrue(generalElement.getAnalyticsTab().isDisplayed());
      doClick(generalElement.getAnalyticsTab());
      waitForAjaxExtJs();
      actualList = getPagesRolesTestActualResults(analyticsTabPages, printout);
    } catch (Throwable e) {
      actualList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0));
    }
    waitForAjaxExtJs();
    assertExpectedListEqualsActualArrayList(roleExpectedPages, actualList, userRole, "Analytics Tab", printout);
  }

  public void assertPagesRolesTest(List expectedList, WebElement[] elements, String userRole, String mainTab, boolean printout) throws InterruptedException {
    ArrayList<Integer> actualList = new ArrayList<>();  //create actual list object
    waitForAjaxExtJs();
    waitForElementDoWhileLoop(generalElement.getUserDropdown(), printout);
    for (WebElement element : elements) {  //populate list if viewable (1) or not viewable (0)
      try {
        if (element.isDisplayed() == true) {
          if (printout) {
            System.out.println("ELEMENT FOUND: " + element);
          }
          actualList.add(1);
        } else {
          actualList.add(0);
          if (printout) {
            System.out.println("ELEMENT NOT FOUND: " + element);
          }
        }
      } catch (Throwable e) {
        e.getMessage();
        actualList.add(0);
        if (printout) {
          System.out.println("ELEMENT NOT FOUND: " + element);
        }
      }
    }
    assertExpectedListEqualsActualArrayList(expectedList, actualList, userRole, mainTab, printout);
  }

  public List getPagesRolesTestActualResults(WebElement[] elements, boolean printout) throws InterruptedException {
    ArrayList<Integer> actualList = new ArrayList<>();  //create actual list object
    waitForAjaxExtJs();
    waitForElementDoWhileLoop(generalElement.getUserDropdown(), printout);
    for (WebElement element : elements) {  //populate list if viewable (1) or not viewable (0)
      try {
        if (element.isDisplayed() == true) {
          if (printout) {
            System.out.println("ELEMENT FOUND: " + element);
          }
          actualList.add(1);
        } else {
          actualList.add(0);
          if (printout) {
            System.out.println("ELEMENT NOT FOUND: " + element);
          }
        }
      } catch (Throwable e) {
        e.getMessage();
        actualList.add(0);
        if (printout) {
          System.out.println("ELEMENT NOT FOUND: " + element);
        }
      }
    }
    return actualList;
  }

  public void assertExpectedListEqualsActualArrayList(List expectedList, List actualList, String userRole, String tab, boolean printout) {
    System.out.println("Expected Result for " + userRole + " on " + tab + ": " + expectedList);
    System.out.println("Actual   Result for " + userRole + " on " + tab + ": " + actualList);
    assertThat(actualList, equalTo(expectedList));
  }

  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
//  public void assertPagesRolesTest(List expectedList, WebElement[] elements, String userRole, boolean printout) throws InterruptedException {
//    ArrayList<Integer> actualList = new ArrayList<>();  //create actual list object
//    waitForAjaxExtJs();
//    waitForElementDoWhileLoop(generalElement.getUserDropdown(), printout);
//    for (WebElement element : elements) {  //populate list if viewable (1) or not viewable (0)
//      try {
//        assertTrue(element.isDisplayed());
//        if (printout) {
//          System.out.println("ELEMENT FOUND: " + element);
//        }
//        actualList.add(1);
//      } catch (Throwable e) {
//        actualList.add(0);
//        if (printout) {
//          System.out.println("ELEMENT NOT FOUND: " + element);
//        }
//      }
//    }
//    assertExpectedListEqualsActualArrayList(expectedList, actualList, userRole, printout);
//  }

//  public void assertExpectedListEqualsActualArrayList(List expectedList, ArrayList actualList, String userRole, boolean printout) {
//    if (printout) {
//      System.out.println("Expected Result for " + userRole + ": " + expectedList);
//      System.out.println("Actual Result for " + userRole + ":   " + actualList);
//    }
//    assertThat(expectedList, equalTo(actualList));
//  }
}


//  public void testUserMenu() {
//    assertElementIsDisplayed(generalElement.getUserDropdown(), printout);
//    doClick(generalElement.getUserDropdown());
//    WebElement[] userMenuElements = {
//      generalElement.getUserDropdownChangePassword(),
//      generalElement.getUserDropdownUseOurTermsYes(),
//      generalElement.getUserDropdownUseOurTermsNo(),
//      generalElement.getUserDropdownLogOut(),
//      generalElement.getUserDropdownTermsOfUse()
//    };
//    assertElementsAreDisplayed(userMenuElements,printout);
//  }
//
//  public void testGlobalHeader() {
//    WebElement[] globalHeaderElements = {
//      generalElement.getGlobalHeaderLogo(),
//      generalElement.getGlobalHeaderButtonHelp(),
//      generalElement.getGlobalHeaderButtonContactUs(),
//      generalElement.getGlobalHeaderButtonLogout()
//    };
//    assertElementsAreDisplayed(globalHeaderElements,printout);
//  }
//
//}

//  public void testLandingPageSystemMaintenance() {
//    WebElement[] landingPageSystemMaintenanceElements = {
//      generalElement.getLandingPageBubbleSystemMaintenance(),
//      generalElement.getLandingPageBubbleSystemMaintenanceHeader(),
//      generalElement.getLandingPageBubbleSystemMaintenanceImage(),
//      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkUsers(),
//      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkRoles(),
//      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings(),
//      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings()
//    };
//    assertElementsAreDisplayed(landingPageSystemMaintenanceElements,printout);
//  }
//
//  public void testLandingPageDataMaintenance() {
//    WebElement[] landingPageDataMaintenanceElements = {
//      generalElement.getLandingPageBubbleDataMaintenance(),
//      generalElement.getLandingPageBubbleDataMaintenanceHeader(),
//      generalElement.getLandingPageBubbleDataMaintenanceImage(),
//      generalElement.getLandingPageBubbleDataMaintenanceQuickLinkMaintainData(),
//      generalElement.getLandingPageBubbleDataMaintenanceQuickLinkLoadData(),
//      generalElement.getLandingPageBubbleDataMaintenanceQuickLinkUtilities()
//    };
//    assertElementsAreDisplayed(landingPageDataMaintenanceElements,printout);
//  }
//
//  public void testLandingPageBudgeting() {
//    WebElement[] landingPageBudgetingElements = {
//      generalElement.getLandingPageBubbleBudgeting(),
//      generalElement.getLandingPageBubbleBudgetingHeader(),
//      generalElement.getLandingPageBubbleBudgetingImage(),
//      generalElement.getLandingPageBubbleBudgetingQuickLinkBudgeting()
//    };
//    assertElementsAreDisplayed(landingPageBudgetingElements,printout);
//  }
//
//  public void testLandingPageEpisodes() {
//    WebElement[]  landingPageEpisodesElements = {
//      generalElement.getLandingPageBubbleEpisode(),
//      generalElement.getLandingPageBubbleEpisodeHeader(),
//      generalElement.getLandingPageBubbleEpisodeImage(),
//      generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeModels(),
//      generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance()
//    };
//    assertElementsAreDisplayed(landingPageEpisodesElements,printout);
//  }
//
//  public void testLandingPageContracting() {
//    WebElement[] landingPageContractingElements = {
//      generalElement.getLandingPageBubbleContracting(),
//      generalElement.getLandingPageBubbleContractingHeader(),
//      generalElement.getLandingPageBubbleContractingContent(),
//      generalElement.getLandingPageBubbleCostingContentText(),
//      generalElement.getLandingPageBubbleContractingImage(),
//      generalElement.getLandingPageBubbleContractingQuickLinkContractModels(),
//      generalElement.getLandingPageBubbleContractingQuickLinkContractingDataMaintenance()
//    };
//    assertElementsAreDisplayed(landingPageContractingElements,printout);
//  }
//
//  public void testLandingPageCosting() {
//    WebElement[] landingPageCostingElements = {
//      generalElement.getLandingPageBubbleCosting(),
//      generalElement.getLandingPageBubbleCostingHeader(),
//      generalElement.getLandingPageBubbleCostingContent(),
//      generalElement.getLandingPageBubbleCostingContentText(),
//      generalElement.getLandingPageBubbleCostingImage(),
//      generalElement.getLandingPageBubbleCostingQuickLinkCostingModels(),
//      generalElement.getLandingPageBubbleCostingQuickLinkCostingDataMaintenance()
//    };
//    assertElementsAreDisplayed(landingPageCostingElements,printout);
//  }
//
//  public void testLandingPageReporting() {
//    WebElement[] landingPageReportingElements = {
//      generalElement.getLandingPageBubbleReporting(),
//      generalElement.getLandingPageBubbleReportingHeader(),
//      generalElement.getLandingPageBubbleReportingContent(),
//      generalElement.getLandingPageBubbleReportingContentText(),
//      generalElement.getLandingPageBubbleReportingImage(),
//      generalElement.getLandingPageBubbleReportingQuickLinkReportLibrary(),
//      generalElement.getLandingPageBubbleReportingQuickLinkWebIntelligence(),
//      generalElement.getLandingPageBubbleReportingQuickLinkAdHocReportDesign()
//    };
//    assertElementsAreDisplayed(landingPageReportingElements,printout);
//  }
//
//  public void testLandingPageAnalytics() {
//    WebElement[] landingPageAnalyticsElements = {
//      generalElement.getLandingPageBubbleAnalytics(),
//      generalElement.getLandingPageBubbleAnalyticsHeader(),
//      generalElement.getLandingPageBubbleAnalyticsContent(),
//      generalElement.getLandingPageBubbleAnalyticsContentText(),
//      generalElement.getLandingPageBubbleAnalyticsImage(),
//      generalElement.getLandingPageBubbleAnalyticsQuickLinkExecutiveDashboard(),
//      generalElement.getLandingPageBubbleAnayticsQuickLinkAnalyticDashobaords()
//    };
//    assertElementsAreDisplayed(landingPageAnalyticsElements,printout);
//  }

