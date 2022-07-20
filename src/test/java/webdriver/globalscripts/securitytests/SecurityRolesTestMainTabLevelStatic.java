package webdriver.globalscripts.securitytests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Roles;
/*
 * OVERVIEW:  This test logs in with each of the standard user roles and verifies access authorizations.
 * Currently, it includes only tests for the application main tabs, but could and should be expanded to include
 * age-level access and important actions.
 * Last Update: 03-08-19
 */

@RunWith(Parameterized.class)
public class SecurityRolesTestMainTabLevelStatic extends LoginRolesTesting {

  GeneralElementsMap generalElement;
  private String userRole;

  public SecurityRolesTestMainTabLevelStatic(Roles role) {
    super(role);
    System.out.println("Test Class: " + SecurityRolesTestMainTabLevelStatic.class.getSimpleName());
    System.out.println("Role in constructor: " + role);
    this.userRole = String.valueOf(role);
  }

  @Before
  public void setupScript() {
    generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
  }

  @Test
  public void testSecurityRolesTest() {
    try {
      System.out.println("START TEST");
      System.out.println("User Role under test: " + userRole);
      testMainTabs(); //testMainTabs array feeds into assertMainTabsRolesTest method, which compares actual to expected results
//            testGlobalHeader();
//            testUserMenu();
//            testAnalyticsTab();
//            testReportingTab();
//            testCostingTab();
//            testContractingTab();
//            testEpisodesTab();
//            testBudgetingTab();
//            testDataMaintenanceTab();
//            testSystemMaintenanceTab();
//            testStatusTab();
//            testLandingPageAnalytics();
//            testLandingPageReporting();
//            testLandingPageCosting();
//            testLandingPageContracting();
//            testLandingPageEpisodes();
//            testLandingPageBudgeting();
//            testLandingPageDataMaintenance();
//            testLandingPageSystemMaintenance();
      System.out.println("TEST COMPLETE");
    } catch (Throwable e) {
      System.out.println("<< TEST FAILED >>");
      fail();
    }
  }

  //TO RUN SELECTIVELY, comment out the roles to ignore below
  @Parameterized.Parameters (name = "{index}: Role={0}")
  public static Collection<Object[]> roles() {
    Object[][] roles = new Object[][] {
      {Roles.valueOf("Application_Administrator")},
      {Roles.valueOf("System_Administrator")},
      {Roles.valueOf("Security_Administrator")},

      {Roles.valueOf("Data_Administrator")},
      {Roles.valueOf("Cost_Analyst")},
      {Roles.valueOf("Costing_Department_Manager")},
      {Roles.valueOf("Contract_Analyst")},

      {Roles.valueOf("Contract_Administrator")},
      {Roles.valueOf("Contract_Reviewer")},
      {Roles.valueOf("Episode_Analyst")},
      {Roles.valueOf("Report_Administrator")},

      {Roles.valueOf("AdHoc_Report_Designer")},
      {Roles.valueOf("Report_User")},
      {Roles.valueOf("Web_Intelligence_Designer")},
      {Roles.valueOf("Web_Intelligence_User")},

      {Roles.valueOf("Budgeting_User")},
      {Roles.valueOf("Analytics_Administrator")},
      {Roles.valueOf("Analytics_Analyst")},
      {Roles.valueOf("Analytics_Designer")},

      {Roles.valueOf("Analytics_Executive")},
      //{Roles.valueOf("Automation_Tester")},  //should be commented out
    };
    return Arrays.asList(roles);
  }

  //Application Main Tabs Expected Values Arrays (0=Not Displayed; 1=Displayed) per Standard Role
  public Integer[] setExpectedResult(String userRole) {
    Integer[] expectedResult = new Integer[9];

    if (userRole.equals("Application_Administrator"))        { expectedResult = new Integer[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1 }; }
    else if (userRole.equals("System_Administrator"))        { expectedResult = new Integer[]{ 1, 1, 0, 0, 0, 0, 0, 1, 1 }; }
    else if (userRole.equals("Security_Administrator"))      { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 1, 1 }; }

    else if (userRole.equals("Data_Administrator"))          { expectedResult = new Integer[]{ 0, 1, 0, 1, 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Cost_Analyst"))                { expectedResult = new Integer[]{ 0, 1, 1, 0, 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Costing_Department_Manager"))  { expectedResult = new Integer[]{ 0, 1, 1, 0, 0, 0, 0, 0, 1 }; }
    else if (userRole.equals("Contract_Analyst"))            { expectedResult = new Integer[]{ 0, 1, 0, 1, 0, 0, 1, 1, 1 }; }

    else if (userRole.equals("Contract_Administrator"))      { expectedResult = new Integer[]{ 0, 1, 0, 1, 0, 0, 1, 1, 1 }; }
    else if (userRole.equals("Contract_Reviewer"))           { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Episode_Analyst"))             { expectedResult = new Integer[]{ 0, 1, 0, 0, 1, 0, 1, 1, 1 }; }
    else if (userRole.equals("Report_Administrator"))        { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("AdHoc_Report_Designer"))       { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 1, 0 }; }
    else if (userRole.equals("Report_User"))                 { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_Designer"))   { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Web_Intelligence_User"))       { expectedResult = new Integer[]{ 0, 1, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Budgeting_User"))              { expectedResult = new Integer[]{ 0, 0, 0, 0, 0, 1, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Administrator"))     { expectedResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Analyst"))           { expectedResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    else if (userRole.equals("Analytics_Designer"))          { expectedResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }

    else if (userRole.equals("Analytics_Executive"))         { expectedResult = new Integer[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0 }; }
    //else if(userRole.equals("Automation_Tester"))           { expectedResult = new Integer[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1 };} //should be commented out

    else { System.out.println("Role not found"); }
    return expectedResult;
  }


  public void assertMainTabsRolesTest(WebElement[] elements, String userRole, boolean printout) throws InterruptedException {

    //1-this statement gets the expected array from "setExpectedResult" for a given role
    List<Integer> roleExpectedTabs = new ArrayList<>(Arrays.asList(setExpectedResult(userRole)));

    //2-this section creates results list object and adds results from each test to the object to form a final
    //set of outputs to compare to the expected results.  The "elements" is a set of WebElements to test for, such
    //as tabs, but could be any set.
    ArrayList<Integer> resultsList = new ArrayList<>();
    waitForAjaxExtJs();
    waitForElementDoWhileLoop(generalElement.getUserDropdown(), printout);
    for (WebElement element : elements) {
      try {
        assertTrue(element.isDisplayed());
        if (printout) {
          System.out.println("ELEMENT FOUND: " + element);
        }
        resultsList.add(1);
      } catch (Throwable e) {
        resultsList.add(0);
        if (printout) {
          System.out.println("ELEMENT NOT FOUND: " + element);
        }
      }
    }

    if (printout) {
      System.out.println("Expected Result for " + userRole + ": " + roleExpectedTabs);
      System.out.println("Actual Result for " + userRole + ":   " + resultsList);
    }

    //3-assert actual and expected results
    try {
      assertEquals(roleExpectedTabs, resultsList);
      System.out.println("TEST PASSED");
    } catch (Throwable e) {
      System.out.println("TEST FAILED");
      fail("Failed: " + userRole);
    }
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
    assertMainTabsRolesTest(mainTabs, userRole, printout);
  }

  public void testLandingPageSystemMaintenance() {
    WebElement[] landingPageSystemMaintenanceElements = {
      generalElement.getLandingPageBubbleSystemMaintenance(),
      generalElement.getLandingPageBubbleSystemMaintenanceHeader(),
      generalElement.getLandingPageBubbleSystemMaintenanceImage(),
      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkUsers(),
      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkRoles(),
      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings(),
      generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings()
    };
    assertElementsAreDisplayed(landingPageSystemMaintenanceElements,printout);
  }

  public void testLandingPageDataMaintenance() {
    WebElement[] landingPageDataMaintenanceElements = {
      generalElement.getLandingPageBubbleDataMaintenance(),
      generalElement.getLandingPageBubbleDataMaintenanceHeader(),
      generalElement.getLandingPageBubbleDataMaintenanceImage(),
      generalElement.getLandingPageBubbleDataMaintenanceQuickLinkMaintainData(),
      generalElement.getLandingPageBubbleDataMaintenanceQuickLinkLoadData(),
      generalElement.getLandingPageBubbleDataMaintenanceQuickLinkUtilities()
    };
    assertElementsAreDisplayed(landingPageDataMaintenanceElements,printout);
  }

  public void testLandingPageBudgeting() {
    WebElement[] landingPageBudgetingElements = {
      generalElement.getLandingPageBubbleBudgeting(),
      generalElement.getLandingPageBubbleBudgetingHeader(),
      generalElement.getLandingPageBubbleBudgetingImage(),
      generalElement.getLandingPageBubbleBudgetingQuickLinkBudgeting()
    };
    assertElementsAreDisplayed(landingPageBudgetingElements,printout);
  }

  public void testLandingPageEpisodes() {
    WebElement[]  landingPageEpisodesElements = {
      generalElement.getLandingPageBubbleEpisode(),
      generalElement.getLandingPageBubbleEpisodeHeader(),
      generalElement.getLandingPageBubbleEpisodeImage(),
      generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeModels(),
      generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance()
    };
    assertElementsAreDisplayed(landingPageEpisodesElements,printout);
  }

  public void testLandingPageContracting() {
    WebElement[] landingPageContractingElements = {
      generalElement.getLandingPageBubbleContracting(),
      generalElement.getLandingPageBubbleContractingHeader(),
      generalElement.getLandingPageBubbleContractingContent(),
      generalElement.getLandingPageBubbleCostingContentText(),
      generalElement.getLandingPageBubbleContractingImage(),
      generalElement.getLandingPageBubbleContractingQuickLinkContractModels(),
      generalElement.getLandingPageBubbleContractingQuickLinkContractingDataMaintenance()
    };
    assertElementsAreDisplayed(landingPageContractingElements,printout);
  }

  public void testLandingPageCosting() {
    WebElement[] landingPageCostingElements = {
      generalElement.getLandingPageBubbleCosting(),
      generalElement.getLandingPageBubbleCostingHeader(),
      generalElement.getLandingPageBubbleCostingContent(),
      generalElement.getLandingPageBubbleCostingContentText(),
      generalElement.getLandingPageBubbleCostingImage(),
      generalElement.getLandingPageBubbleCostingQuickLinkCostingModels(),
      generalElement.getLandingPageBubbleCostingQuickLinkCostingDataMaintenance()
    };
    assertElementsAreDisplayed(landingPageCostingElements,printout);
  }

  public void testLandingPageReporting() {
    WebElement[] landingPageReportingElements = {
      generalElement.getLandingPageBubbleReporting(),
      generalElement.getLandingPageBubbleReportingHeader(),
      generalElement.getLandingPageBubbleReportingContent(),
      generalElement.getLandingPageBubbleReportingContentText(),
      generalElement.getLandingPageBubbleReportingImage(),
      generalElement.getLandingPageBubbleReportingQuickLinkReportLibrary(),
      generalElement.getLandingPageBubbleReportingQuickLinkWebIntelligence(),
      generalElement.getLandingPageBubbleReportingQuickLinkAdHocReportDesign()
    };
    assertElementsAreDisplayed(landingPageReportingElements,printout);
  }

  public void testLandingPageAnalytics() {
    WebElement[] landingPageAnalyticsElements = {
      generalElement.getLandingPageBubbleAnalytics(),
      generalElement.getLandingPageBubbleAnalyticsHeader(),
      generalElement.getLandingPageBubbleAnalyticsContent(),
      generalElement.getLandingPageBubbleAnalyticsContentText(),
      generalElement.getLandingPageBubbleAnalyticsImage(),
      generalElement.getLandingPageBubbleAnalyticsQuickLinkExecutiveDashboard(),
      generalElement.getLandingPageBubbleAnayticsQuickLinkAnalyticDashobaords()
    };
    assertElementsAreDisplayed(landingPageAnalyticsElements,printout);
  }

  public void testStatusTab() {
    //Clicks the Status Tab in order to open the dropdown menu
    doClick(generalElement.getStatusTab());
    WebElement[] statusTabElement = {
      generalElement.getCalculationStatusSubTab(),
      generalElement.getImportExportStatusSubTab(),
      generalElement.getUtilityStatusSubTab()
    };
    assertElementsAreDisplayed(statusTabElement,printout);
  }

  public void testSystemMaintenanceTab() {
    //Clicks the System Maintenance Tab in order to open the dropdown menu
    doClick(generalElement.getSystemMaintenanceTab());
    WebElement[] systemMaintenanceTabElement = {
      generalElement.getUsersSubTab(),
      generalElement.getRolesSubTab(),
      generalElement.getSecuritySettingsSubTab(),
      generalElement.getGeneralSettingsSubTab(),
      generalElement.getCustomizeMaintainDataSubTab(),
      generalElement.getCustomizeTaskListSubTab(),
      generalElement.getTerminalServerSessionsSubTab(),
      generalElement.getTerminalServerDesktopSubTab()
    };
    assertElementsAreDisplayed(systemMaintenanceTabElement,printout);
  }

  public void testDataMaintenanceTab() {
    //Clicks the Data Maintenance Tab in order to open the dropdown menu
    doClick(generalElement.getDataMaintenanceTab());
    WebElement[] dataMaintenanceTabElement = {
      generalElement.getMaintainDataSubTab(),
      generalElement.getLoadDataSubTab(),
      generalElement.getUtilitiesSubTab()
    };
    assertElementsAreDisplayed(dataMaintenanceTabElement,printout);
  }

  public void testBudgetingTab() {
    //Clicks the Budgeting Tab in order to open the dropdown menu
    doClick(generalElement.getBudgetingTab());
    assertElementIsDisplayed(generalElement.getBudgetingSubTab(),printout);
  }

  public void testEpisodesTab() {
    //Clicks the Episodes Tab in order to open the dropdown menu
    doClick(generalElement.getEpisodesTab());
    WebElement[] episodesTabElement = {
      generalElement.getEpisodeModelsSubTab(),
      generalElement.getEpisodeDataMaintenanceSubTab()
    };
    assertElementsAreDisplayed(episodesTabElement,printout);
  }

  public void testContractingTab() {
    //Clicks the Contracting Tab in order to open the dropdown menu
    doClick(generalElement.getContractingTab());
    WebElement[] contractingTabElements = {
      generalElement.getContractModelsSubTab(),
      generalElement.getContractingDataMaintenanceSubTab(),
      generalElement.getContractualAllowanceExportSubTab(),
      generalElement.getApcAllocationSubTab()
    };
    assertElementsAreDisplayed(contractingTabElements,printout);
  }

  public void testCostingTab() {
    //Clicks the Costing Tab in order to open the dropdown menu
    doClick(generalElement.getCostingTab());
    WebElement[] costingTabElements = {
      generalElement.getCostingModelsSubTab(),
      generalElement.getCostingDataMaintenanceSubTab(),
      generalElement.getRVUMaintenanceSubTab(),
      generalElement.getCostModelScenarioCalculationSubTab(),
      generalElement.getUnitCostQuickCalculationSubTab()
    };
    assertElementsAreDisplayed(costingTabElements,printout);
  }

  public void testReportingTab() {
    //Clicks the Reporting Tab in order to open the dropdown menu
    doClick(generalElement.getReportingTab());
    WebElement[] reportingTabElements = {
      generalElement.getReportingLibrarySubTab(),
      generalElement.getWebIntelligenceSubTab(),
      generalElement.getAdHocReportDesignSubTab(),
      generalElement.getReportMenuMaintenanceSubTab(),
      generalElement.getReportDateMaintenanceSubTab(),
      generalElement.getReportPublicationSubTab(),
      generalElement.getAdHocBusinessViewMaintenanceSubTab(),
      generalElement.getWebIntelligenceUniverseMaintenanceSubTab(),
      generalElement.getIcd9icd10GemsInquirySubTab(),
      generalElement.getIcd9icd10GemsAnalysisSubTab(),
      generalElement.getCostingReportsSubTab()
    };
    assertElementsAreDisplayed(reportingTabElements,printout);
  }

  public void testAnalyticsTab() {
    //Clicks the Analytics Tab in order to open the dropdown menu
    doClick(generalElement.getAnalyticsTab());
    WebElement[] analyticsTabElements = {
      generalElement.getExecutiveDashboardSubTab(),
      generalElement.getAnalyticDashboardSubTab(),
      generalElement.getAnalyticRefreshScenariosSubTab(),
      generalElement.getAnalyticsAdministrationSubTab(),
      generalElement.getCustomizeAnalyticsSubTab(),
      generalElement.getCustomizeAnalyticsSessionsSubTab(),
      generalElement.getAnalyticsServerDesktopSubTab(),
      generalElement.getAnalyticsServerDesktopSessionsSubTab()
    };
    assertElementsAreDisplayed(analyticsTabElements,printout);
  }

  public void testUserMenu() {
    assertElementIsDisplayed(generalElement.getUserDropdown(), printout);
    doClick(generalElement.getUserDropdown());
    WebElement[] userMenuElements = {
      generalElement.getUserDropdownChangePassword(),
      generalElement.getUserDropdownUseOurTermsYes(),
      generalElement.getUserDropdownUseOurTermsNo(),
      generalElement.getUserDropdownLogOut(),
      generalElement.getUserDropdownTermsOfUse()
    };
    assertElementsAreDisplayed(userMenuElements,printout);
  }

  public void testGlobalHeader() {
    WebElement[] globalHeaderElements = {
      generalElement.getGlobalHeaderLogo(),
      generalElement.getGlobalHeaderButtonHelp(),
      generalElement.getGlobalHeaderButtonContactUs(),
      generalElement.getGlobalHeaderButtonLogout()
    };
    assertElementsAreDisplayed(globalHeaderElements,printout);
  }

}