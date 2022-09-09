package webdriver.globalscripts.pagetests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.core.Login;
import webdriver.data.AdsStandardData;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.*;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BuildVerificationTestScript extends UcqcHelper {

  private static GeneralElementsMap generalElement;
  private static AnalyticsMap analyticsMap;
  private static ContractingMap contractingMap;
  private static CostingMap costingMap;
  private static DataMaintenanceMap dataMaintenance;
  private static DialogsMap dialogsMap;
  private static ModelLibraryMap modelLibrary;
  private static ReportingMap reportingMap;
  private static StatusMap statusMap;
  private static SystemMaintenanceMap sysmaint;
  WebDriverWait wait = new WebDriverWait(driver, 30);
  String expectedReleaseVersion = version;  //only checks version, not date

  /** The local pages map test is a test of the elements on all of the individual functional area (local) page maps -
   * that the elements on the map display on the page.
   */
  @BeforeClass
  public static void setupScript() {
    generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
    analyticsMap = BuildMap.getInstance(driver, AnalyticsMap.class);
    contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    statusMap = BuildMap.getInstance(driver, StatusMap.class);
    dialogsMap = BuildMap.getInstance(driver, DialogsMap.class);
    dataMaintenance = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    modelLibrary = BuildMap.getInstance(driver, ModelLibraryMap.class);
    reportingMap = BuildMap.getInstance(driver, ReportingMap.class);
    sysmaint = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
    System.out.println("Test Class: " + BuildVerificationTestScript.class.getSimpleName());
  }

  //Login page tests
  @Test
  public void test0000VerifyLoginPageIsDisplayed() {	
    System.out.println("Testing Login Page");
    try {
      waitForSpinnerToEnd();
      Thread.sleep(1000);
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt = 'Harris Affinity Logo']")));
    } catch (Throwable e) {
      fail("LOGIN PAGE NOT DISPLAYED");
    }
  }

  @Test
  public void test0000aVerifyLoginPageElementsAreDisplayed() {
    WebElement[] logInPageElements = {
            generalElement.getGlobalHeaderLogo(),
            generalElement.getLoginPageHeaderLogIn(),
            generalElement.getLoginPageDate(),
            generalElement.getLoginPageLogInHeaderSignIn(),
            generalElement.getLoginPageFieldUsername(),
            generalElement.getLoginPageFieldPassword(),
            generalElement.getLoginPageButtonLogIn(),
            generalElement.getLoginPageReleaseVersion()
    };
    assertElementsAreDisplayed(logInPageElements, printout);
  }

  @Test
  public void test0000bLoginPageVerifyCurrentDateIsDisplayed() {
    LocalDateTime now = LocalDateTime.now();
    if (printout) {
      System.out.println(DateTimeFormatter.ofPattern("EEEE MMMM d, yyy HH:mm a").format(now));
    }
    //String expectedDate = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyy HH:mm a").format(now);
    String expectedDate = DateTimeFormatter.ofPattern("EEEE MMMM d, yyy").format(now);
    String actualLoginPageDate = generalElement.getLoginPageDate().getText();
    assertThat(actualLoginPageDate, containsString(expectedDate));
  }

  @Test
  public void test0000cLoginPageVerifyReleaseVersion() {
    String actualReleaseVersion = generalElement.getLoginPageReleaseVersion().getText();
    if (printout) {
      System.out.println("Expected release version: " + expectedReleaseVersion);
      System.out.println("Actual release version: " + actualReleaseVersion);
    }
    assertThat(actualReleaseVersion, containsString(expectedReleaseVersion));
  }

  @Test
  public void test0000dLoginPageVerifyHarrisLogoImage() {
    if (printout) {
      System.out.println("Actual Login Page Logo id: " + generalElement.getLoginPageLogo().getAttribute("id"));
      System.out.println("Actual Login Page partial Logo src: " + generalElement.getLoginPageLogo().getAttribute("src").substring(23));
    }
    assertThat(generalElement.getLoginPageLogo().getAttribute("id"), containsString("image-1013"));
    assertThat(generalElement.getLoginPageLogo().getAttribute("src").substring(23), containsString("/resources/images/app/Harris_Affinity_Logo.png"));
  }

  @Test
  public void test0000eLoginPageVerifyLogInPageHeaderIsLogIn() {
    if (printout) {
      System.out.println("Login Page Header: " + generalElement.getLoginPageHeaderLogIn().getText());
    }
    assertThat(generalElement.getLoginPageHeaderLogIn().getText(), containsString("Log In"));
  }

  @Test
  public void test0000fLoginPageVerifySignInSectionHeaderIsSignIn() {
    if (printout) {
      System.out.println("Login Page Sign In section header: " + generalElement.getLoginPageLogInHeaderSignIn().getText());
    }
    assertThat(generalElement.getLoginPageLogInHeaderSignIn().getText(), containsString("Sign In"));
  }

  @Test
  public void test0000gLoginPageVerifyUsernameAndPasswordFieldLabelsAreDisplayed() {
    if (printout) {
      System.out.println("Login Page Username label: " + driver.findElement(By.xpath("//label[text() = 'Username']")));
      System.out.println("Login Page Password label: " + driver.findElement(By.xpath("//label[text() = 'Password']")));
    }
    assertThat(driver.findElement(By.xpath("//label[text() = 'Username']")).isDisplayed(), equalTo(true));
    assertThat(driver.findElement(By.xpath("//label[text() = 'Password']")).isDisplayed(), equalTo(true));
  }

  @Test
  public void test0000hLoginPageVerifyLoginButtonTextIsLogIn() {
    if (printout) {
      System.out.println("Login Button text: " + generalElement.getLoginPageButtonLogIn().getText());
    }
    assertThat(generalElement.getLoginPageButtonLogIn().getText(), equalTo("Log In"));
  }

  public static void assertElementsAreDisplayed(WebElement[] elements, boolean printout) {
    for (WebElement element : elements) {
      try {
        assertTrue(element.isDisplayed());
        if(printout){
          System.out.println(element);
        }
      } catch (Throwable e) {
        fail(e.getMessage());
      }
    }
  }

  //===== Global Tests ======//
  @Test
  public void test0001LandingPageSystemMaintenance() throws Exception {
    System.out.println("Logging In");
    
    /*modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
    Login.loginUser("AutomationTester1");
    */
    Login.loginUser("AutomationTesterAdmin");
    // End of modification
    waitForSpinnerToEnd();
    waitForJsWindowOnload();
    System.out.println("Testing Global Pages");
    WebElement[] landingPageSystemMaintenanceElements = {
            generalElement.getLandingPageBubbleSystemMaintenance(),
            generalElement.getLandingPageBubbleSystemMaintenanceHeader(),
            generalElement.getLandingPageBubbleSystemMaintenanceImage(),
            generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkUsers(),
            generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkRoles(),
            generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings(),
            generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings()
    };
    assertElementsAreDisplayed(landingPageSystemMaintenanceElements, printout);
  }

  @Test
  public void test0002LandingPageDataMaintenance() {
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

  @Test
  public void test0003LandingPageBudgeting() {
    WebElement[] landingPageBudgetingElements = {
            generalElement.getLandingPageBubbleBudgeting(),
            generalElement.getLandingPageBubbleBudgetingHeader(),
            generalElement.getLandingPageBubbleBudgetingImage(),
            generalElement.getLandingPageBubbleBudgetingQuickLinkBudgeting()
    };
    assertElementsAreDisplayed(landingPageBudgetingElements, printout);
  }

  @Test
  public void test0004LandingPageEpisodes() {
    WebElement[] landingPageEpisodesElements = {
            generalElement.getLandingPageBubbleEpisode(),
            generalElement.getLandingPageBubbleEpisodeHeader(),
            generalElement.getLandingPageBubbleEpisodeImage(),
            generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeModels(),
            generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance()
    };
    assertElementsAreDisplayed(landingPageEpisodesElements, printout);
  }

  @Test
  public void test0005LandingPageContracting() {
    try {
      WebElement[] landingPageContractingElements = {
              generalElement.getLandingPageBubbleContracting(),
              generalElement.getLandingPageBubbleContractingHeader(),
              generalElement.getLandingPageBubbleContractingContent(),
              generalElement.getLandingPageBubbleCostingContentText(),
              generalElement.getLandingPageBubbleContractingImage(),
              generalElement.getLandingPageBubbleContractingQuickLinkContractModels(),
              generalElement.getLandingPageBubbleContractingQuickLinkContractingDataMaintenance()
      };
      assertElementsAreDisplayed(landingPageContractingElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0006LandingPageCosting() {
    try {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0007LandingPageReporting() {
    try {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0008LandingPageAnalytics() {
    try {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0009aGlobalHeader() {
    try {
      WebElement[] globalHeaderElements = {
              generalElement.getGlobalHeaderLogo(),
              generalElement.getGlobalHeaderButtonHelp(),
              generalElement.getGlobalHeaderButtonContactUs(),
              generalElement.getGlobalHeaderButtonLogout()
      };
      assertElementsAreDisplayed(globalHeaderElements,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0009bUserMenu() {
    try {
      waitForPresenceOfElement("//*[@id = 'dropdown']");
      doClick(generalElement.getUserDropdown());
      WebElement[] userMenuElements = {
              generalElement.getUserDropdownChangePassword(),
              generalElement.getUserDropdownLogOut(),
              generalElement.getUserDropdownTermsOfUse()
      };
      assertElementsAreDisplayed(userMenuElements,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0009cMainTabs() {
    try {
      WebElement[] mainTabElements = {
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
      assertElementsAreDisplayed(mainTabElements,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0010AdsMainTabOrder() {
    WebElement menuBar = driver.findElement(By.xpath("//div[@id='menubar-body']/ul"));
    List<WebElement> actualTabs = javaMakeListOfWebElements2(menuBar, "/li");
    List<String> actualTabsText = javaMakeListOfStrings(actualTabs);
    assertEquals(AdsStandardData.expectedTabs, actualTabsText);
  }

  @Test
  public void test0010bAnalyticsSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getAnalyticsTab(),
            "analytics_subtab",
            AdsStandardData.expectedAnalyticsSubTabs
    );
  }

  @Test
  public void test0010cReportingSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getReportingTab(),
            "reporting_subtab",
            AdsStandardData.expectedReportingSubTabs
    );
//    String costingReports = driver.findElement(By.xpath("//*[@id='Costing Reports']/a")).getText();
//    assertEquals("Costing Reports", costingReports);
  }

  @Test
  public void test0010dCostingSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getCostingTab(),
            "costing_subtab",
            AdsStandardData.expectedCostingSubTabs
    );
  }

  @Test
  public void test0010eContractingSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getContractingTab(),
            "contracting_subtab",
            AdsStandardData.expectedContractingSubTabs
    );
  }

  @Test
  public void test0010fEpisodesSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getEpisodesTab(),
            "episode_subtab",
            AdsStandardData.expectedEpisodesSubTabs
    );
  }

  @Test
  public void test0010gBudgetingSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getBudgetingTab(),
            "budgeting_subtab",
            AdsStandardData.expectedBudgetingSubTabs
    );
  }

  @Test
  public void test0010hDataMaintenanceSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getDataMaintenanceTab(),
            "datamaintenance_subtab",
            AdsStandardData.expectedDataMaintenanceSubTabs
    );
  }

  @Test
  public void test0010iSystemMaintenanceSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getSystemMaintenanceTab(),
            "systemmaintenance_subtab",
            AdsStandardData.expectedSystemMaintenanceSubTabs
    );
  }

  @Test
  public void test0010jStatusSubTabsOrder() throws InterruptedException {
    verifySubTabOrder(
            generalElement.getStatusTab(),
            "status_subtab",
            AdsStandardData.expectedStatusSubTabs
    );
  }

  @Test
  public void test0012AnalyticsTab() {
    try {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0013ReportingTab() {
    try {
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
              //generalElement.getIcd9icd10GemsInquirySubTab(), //page removed from ADS for 10.3
              //generalElement.getIcd9icd10GemsAnalysisSubTab(),  //page removed from ADS for 10.3
              //generalElement.getCostingReportsSubTab()
      };
      assertElementsAreDisplayed(reportingTabElements,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0014CostingTab() {
    try {
      doClick(generalElement.getCostingTab());
      WebElement[] costingTabElements = {
              generalElement.getCostingModelsSubTab(),
              generalElement.getCostingDataMaintenanceSubTab(),
              generalElement.getRVUMaintenanceSubTab(),
              generalElement.getCostModelScenarioCalculationSubTab(),
              generalElement.getUnitCostQuickCalculationSubTab()
      };
      assertElementsAreDisplayed(costingTabElements,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0015ContractingTab() {
    try {
      //Clicks the Contracting Tab in order to open the dropdown menu
      doClick(generalElement.getContractingTab());
      WebElement[] contractingTabElements = {
              generalElement.getContractModelsSubTab(),
              generalElement.getContractingDataMaintenanceSubTab(),
              generalElement.getContractualAllowanceExportSubTab(),
              generalElement.getApcAllocationSubTab()
      };
      assertElementsAreDisplayed(contractingTabElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0016EpisodesTab() {
    try {
      //Clicks the Episodes Tab in order to open the dropdown menu
      doClick(generalElement.getEpisodesTab());
      WebElement[] episodesTabElement = {
              generalElement.getEpisodeModelsSubTab(),
              generalElement.getEpisodeDataMaintenanceSubTab()
      };
      assertElementsAreDisplayed(episodesTabElement,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0017BudgetingTab() {
    try {
      //Clicks the Budgeting Tab in order to open the dropdown menu
      doClick(generalElement.getBudgetingTab());
      assertElementIsDisplayed(generalElement.getBudgetingSubTab(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0018DataMaintenanceTab() {
    try {
      //Clicks the Data Maintenance Tab in order to open the dropdown menu
      doClick(generalElement.getDataMaintenanceTab());
      WebElement[] dataMaintenanceTabElement = {
              generalElement.getMaintainDataSubTab(),
              generalElement.getLoadDataSubTab(),
              generalElement.getUtilitiesSubTab()
      };
      assertElementsAreDisplayed(dataMaintenanceTabElement,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0019SystemMaintenanceTab() {
    try {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0020StatusTab() {
    try {
      //Clicks the Status Tab in order to open the dropdown menu
      doClick(generalElement.getStatusTab());
      WebElement[] statusTabElement = {
              generalElement.getCalculationStatusSubTab(),
              generalElement.getImportExportStatusSubTab(),
              generalElement.getUtilityStatusSubTab()
      };
      assertElementsAreDisplayed(statusTabElement,printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0050MainPageHelpLinkAndHelpModule() {
    
	  try {	
		  Thread.sleep(3000);
		  waitForAjaxExtJs();
      String firstHandle = webSwitchToNewWindow(generalElement.getGlobalHeaderButtonHelp(), printout);
      Thread.sleep(5000);
      assertHelpPageHeader("Decision Support", printout);
      driver.close();
      driver.switchTo().window(firstHandle);
      //assertHelpLink(generalElement.getGlobalHeaderButtonHelp(), "Decision Support", printout);

    } catch (Throwable e) {
      e.printStackTrace();
      fail("Global Header Page Test Failed");
    }
  }


  private void verifySubTabOrder(WebElement tab, String subTabId, List<String> expectedSubTabs)
          throws InterruptedException {
    tab.click();
    waitForAjaxExtJs();
    WebElement listContainerElement = driver.findElement(By.id(subTabId));
    List<WebElement> actualTabs = javaMakeListOfWebElements2(listContainerElement, "//li/a");
    List<String> actualTabsText = javaMakeListOfStrings(actualTabs);
    assertEquals(expectedSubTabs, actualTabsText);
  }

  //===== Analytics Map =====//
  @Test
  public void test0100AnalyticsRefreshScenarios() throws InterruptedException {
    System.out.println("Testing Local Pages");
    try {
      goToPage("Analytic Refresh Scenarios");
      waitForAjaxExtJs();
      waitForPresenceOfElement("//h1[text()='Analytics Refresh Scenarios']");
      waitForPresenceOfElement(
              "//label[contains(@class, 'areaHeaderTitle') and text()='Active Refresh Scenarios']"
      );
      //Thread.sleep(10000);
      WebElement[] analyticsRefreshScenariosElements = {
        analyticsMap.getAnalyticsRefreshScenariosButtonNew(),
        analyticsMap.getAnalyticsRefreshScenariosButtonEdit(),
        analyticsMap.getAnalyticsRefreshScenariosButtonCopy(),
        analyticsMap.getAnalyticsRefreshScenariosButtonDelete()
      };
      Thread.sleep(1000);
      assertElementsAreDisplayed(analyticsRefreshScenariosElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Analytic Refresh...");
    }
  }

  @Test
  public void test0200ReportingTabReportLibraryPageMap() throws InterruptedException {
    try {
      goToPage("Report Library");
      waitForPresenceOfElement("//iframe[contains(@src,'reporting/main.html')]");
      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
      waitForPresenceOfElement("//div[contains(@class,'gwtQuery-draggable')]/a[@title]");
      WebElement[] reportingTabReportLibraryPageElements = {
              reportingMap.getReportLibraryPageName(),
              reportingMap.getReportLibraryPageHelpLink(),
              reportingMap.getReportLibraryPageFormFieldSearch(),
              reportingMap.getReportLibraryPageSectionHeaderReportStatus(),
              reportingMap.getReportLibraryPageSectionReportStatusTableHeaderName(),
              reportingMap.getReportLibraryPageButtonRename(),
              //uncomment this       reportingMap.getReportLibraryPageButtonDelete(),
              reportingMap.getReportLibraryPageSectionReportStatusFirstReportListed(),
              reportingMap.getReportLibraryPageLinkExpandAll(),
              reportingMap.getReportLibraryPageLinkCollapseAll(),
              reportingMap.getReportLibraryPageExpandTemplates(),
              reportingMap.getReportLibraryPageExpandReports(),
              reportingMap.getReportLibraryPageExpandFolders(),
      };
      assertElementsAreDisplayed(reportingTabReportLibraryPageElements, printout);
      driver.switchTo().parentFrame();
      doClosePageOnLowerBar("Report Library");
    } catch (Throwable e) {
      try {
        waitForPresenceOfElement("//table/tbody/tr[@class='dialogMiddle']/descendant::*[text()='Test']");
        driver.switchTo().parentFrame();
        doClosePageOnLowerBar("Report Library");
        fail("Report Library not available for this environment.");
      } catch (Exception ee) {
        driver.switchTo().parentFrame();
        doClosePageOnLowerBar("Report Library");
        fail(e.getMessage());
      }

    }
  }

  @Test
  public void test0210ReportingTabReportMenuMaintenancePageMap() {
    try {
      goToPage("Report Menu Maintenance");
      waitForAjaxExtJs();
      WebElement[] reportingTabReportMenuMaintenancePagePageElements = {
              reportingMap.getReportingTabReportMenuMaintenancePageName(),
              reportingMap.getReportingTabReportMenuMaintenancePageHelpLink(),
              reportingMap.getReportingTabReportMenuMaintenancePageTableCornerCell(),
              getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "Rename", printout),
              getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "Edit", printout),
              getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "New", printout),
              //uncomment this                 getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "Delete", printout),
      };
      assertElementsAreDisplayed(reportingTabReportMenuMaintenancePagePageElements, printout);
      assertGridTableLoads();
      doClosePageOnLowerBar("Report Menu...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0220ReportingTabReportDateMaintenancePageMap() {
    try {
      goToPage("Report Date Maintenance");
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//*[contains(@id, 'header')][text()='Dates']")).click();
      WebElement[] reportingTabReportDateMaintenancePageElements = {
              reportingMap.getReportingTabReportDateMaintenancePageHelpLink(),
              reportingMap.getReportingTabReportDateMaintenancePageSectionHeaderDates(),
              reportingMap.getReportingTabReportDateMaintenancePageButtonSave(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldCurrentDate(),
              reportingMap.getReportingTabReportDateMaintenancePageComboBoxCurrentMonthYear(),
              reportingMap.getReportingTabReportDateMaintenancePageComboBoxFiscalYearEndMonth(),
              getWebElementButtonWithElementText(reportingMap.reportingTabXpathDateMaintenancePage, "General", printout),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldConsumerHistoryReferenceDate(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeEnd(),
      };
      assertElementsAreDisplayed(reportingTabReportDateMaintenancePageElements, printout);

      doClick(getWebElementButtonWithElementText(reportingMap.reportingTabXpathDateMaintenancePage, "Costing", printout));
      waitForAjaxExtJs();
      WebElement[] reportDateMaintenancePageElementsCostingSubTab = {
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldAdjustmentAndReclassificationDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldAdjustmentAndReclassificationDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldCostItemDataSetDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldCostItemDataSetDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageComboBoxCostItemDataSetMonthYearRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageComboBoxCostItemDataSetMonthYearRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldCostingActivityStatisticDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldCostingActivityStatisticDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldGlDataItemDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldGlDataItemDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadModelScenarioDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadModelScenarioDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadTransactionDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadTransactionDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldRvuEffectiveDate(),
      };
      assertElementsAreDisplayed(reportDateMaintenancePageElementsCostingSubTab, printout);

      doClick(getWebElementButtonWithElementText(reportingMap.reportingTabXpathDateMaintenancePage, "Contracting and A/R", printout));
      waitForAjaxExtJs();
      WebElement[] reportDateMaintenancePageElementsContractingAndArSubTab = {
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldBillDropDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldBillDropDateRangeEnd(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractStartDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractStartDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractExpirationDateRangeStart(),
              reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractExpirationDateRangeEnd(),
      };
      assertElementsAreDisplayed(reportDateMaintenancePageElementsContractingAndArSubTab, printout);
      doClosePageOnLowerBar("Report Date...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  //===== Reporting Tab =====//
//  @Ignore  //The ICD sections were removed from ADS for 10.3
//  @Test
//  public void testReportingTabIcd9Icd10GemsAnalysisPageMap() throws InterruptedException {
//    try {
//      goToPage("Gems Analysis");
//      Thread.sleep(3000);
//      WebDriverWait wait = new WebDriverWait(driver, 30);
//      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'gemsAnalysis')]")));
//      WebElement[] reportingTabGemsAnalysisPageElements = {
//        reportingMap.getReportingTabGemsAnalysisPageName(),
//        reportingMap.getReportingTabGemsAnalysisPageHelpLink(),
//      };
//      assertElementsAreDisplayed(reportingTabGemsAnalysisPageElements, printout);
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//    } catch (Throwable e) {
//      fail(e.getMessage());
//    }
//  }
//
//  @Ignore  //The ICD sections were removed from ADS for 10.3
//  @Test
//  public void testReportingTabIcd9Icd10GemsInquiryPageMap() {
//    try {
//      goToPage("Gems Inquiry");
//      Thread.sleep(3000);
//      driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'gemsInquiry.html')]")));
//      WebElement[] reportingTabGemsInquiryPageElements = {
//        reportingMap.getReportingTabGemsInquiryPageName(),
//        reportingMap.getReportingTabGemsInquiryPageHelpLink(),
//      };
//      assertElementsAreDisplayed(reportingTabGemsInquiryPageElements, printout);
//      driver.switchTo().parentFrame();
//      doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//    } catch (Throwable e) {
//      fail(e.getMessage());
//    }
//  }

  //===== Costing Tab =====//
  @Test
  public void test0300CostingTabCostModelLibraryPage() throws InterruptedException {
    try {
      goToPage("Costing Models");
      waitForAjaxExtJs();
      waitForElementToBeVisible(modelLibrary.getModelLibraryTableButtonFirst());
      WebElement[] modelLibraryElements = {
              modelLibrary.getModelLibraryFieldSearch(),
              modelLibrary.getModelLibraryButtonSearch(),
              modelLibrary.getModelLibraryCostModelButtonNew(),
              modelLibrary.getModelLibraryButtonOpenTaskList(),
              modelLibrary.getModelLibraryButtonFilter(),
              modelLibrary.getModelLibraryButtonClearFilter(),
              modelLibrary.getModelLibraryCostModelButtonDelete(),
              modelLibrary.getModelLibraryButtonNewFolder(),
              modelLibrary.getModelLibraryButtonRenameFolder(),
              modelLibrary.getModelLibraryButtonDeleteFolder(),
              modelLibrary.getModelLibraryTreeColumnCosting(),
              modelLibrary.getModelLibraryTreeColumnArrowCosting(),
              modelLibrary.getModelLibraryTreeColumnContracting(),
              modelLibrary.getModelLibraryTreeColumnArrowContracting(),
              modelLibrary.getModelLibraryTreeColumnEpisodes(),
              modelLibrary.getModelLibraryTreeColumnArrowEpisodes(),
              modelLibrary.getModelLibraryTableButtonFirst(),
              modelLibrary.getModelLibraryTableButtonPrevious(),
              modelLibrary.getModelLibraryFieldInputNumber(),
              modelLibrary.getModelLibraryTableButtonGo(),
              modelLibrary.getModelLibraryTableButtonNext(),
              modelLibrary.getModelLibraryTableButtonLast()
      };
      assertElementsAreDisplayed(modelLibraryElements, printout);
      //doClick(modelLibrary.getModelLibraryTreeColumnContracting());
      WebElement[] modelLibraryContractingElements = {
              modelLibrary.getModelLibraryFieldSearch(),
              modelLibrary.getModelLibraryButtonSearch(),
              modelLibrary.getModelLibraryButtonOpenTaskList(),
              modelLibrary.getModelLibraryCostModelButtonNew(),
              modelLibrary.getModelLibraryCostModelButtonDelete(),
              modelLibrary.getModelLibraryButtonFilter(),
              modelLibrary.getModelLibraryButtonClearFilter()
      };
      assertElementsAreDisplayed(modelLibraryContractingElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Model Library");
    }
  }

  @Test
  public void test0310CostingTabDataMaintenancePage() throws InterruptedException {
    try {
      goToPage("Costing Data Maintenance");
      waitForSpinnerToEnd();
      assertTrue(driver.findElement(By.xpath("//span[text() = 'A - Z']")).isDisplayed());
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Maintain Data");
    }
  }

  @Test
  public void test0320CostingTabRvuMaintenanceMap() throws InterruptedException {
    try {
      goToPage("RVU Maintenance");
      waitForAjaxExtJs();
      WebElement[] rvuMaintenanceElements = {
              costingMap.getRvuMaintenanceButtonMaintainRVUs(),
              costingMap.getRvuMaintenanceButtonFilter(),
              //costingMap.getRvuMaintenanceButtonClearFilter(), //inactive by default-greyed out
              costingMap.getRvuMaintenanceButtonImport(),
              costingMap.getRvuMaintenanceButtonExport(),
              costingMap.getCostingMapTableButtonFirst(),
              costingMap.getCostingMapTableButtonPrevious(),
              costingMap.getCostingMapTableFieldInputNumber(),
              costingMap.getCostingMapTableButtonGo(),
              costingMap.getCostingMapTableButtonNext(),
              costingMap.getCostingMapTableButtonLast()
      };
      assertElementsAreDisplayed(rvuMaintenanceElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("RVU Maintenance");
    }
  }

  @Test
  public void test0330CostingTabCostModelScenarioCalculationMap() throws InterruptedException {
    try {
      goToPage("Cost Model Scenario Calculation");
      waitForAjaxExtJs();
      WebElement[] costModelScenarioCalculationElements = {
              costingMap.getCostModelScenarioCalculationButtonEdit(),
              costingMap.getCostModelScenarioCalculationButtonFilter(),
              costingMap.getCostModelScenarioCalculationButtonClearFilter(),
              costingMap.getCostModelScenarioCalculationButtonCalculate(),
              costingMap.getCostModelScenarioCalculationButtonResults(),
              costingMap.getCostingMapTableButtonFirst(),
              costingMap.getCostingMapTableButtonPrevious(),
              costingMap.getCostingMapTableFieldInputNumber(),
              costingMap.getCostingMapTableButtonGo(),
              costingMap.getCostingMapTableButtonNext(),
              costingMap.getCostingMapTableButtonLast()
      };
      Thread.sleep(1000);
      assertElementsAreDisplayed(costModelScenarioCalculationElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Cost Model...");
    }
  }

  @Test
  public void test0340aCostingTabUnitCostQuickCalculationPage() {
    try {
      goToPage("Unit Cost Quick Calculation");
      waitForSpinnerToEnd();
      //doMaximizeWindow();
      waitForAjaxExtJs();
      WebElement[] costModelScenarioCalculationElements = {
              costingMap.getUnitCostQuickCalculationDropdownCostModel(),
              costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
              costingMap.getUnitCostQuickCalculationDropdownEntity(),
              costingMap.getUnitCostQuickCalculationButtonSelect(),
              costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
              costingMap.getUnitCostQuickCalculationButtonApplySelections(),
              costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),
              costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),
              costingMap.getUnitCostQuickCalculationButtonUndo(),
              costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs(),
              costingMap.getUnitCostQuickCalculationButtonCalculate(),
              costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),
              costingMap.getUnitCostQuickCalculationButtonHide()
      };
      assertElementsAreDisplayed(costModelScenarioCalculationElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  String costModel = "QA Cost Model";
  String costModelScenario = "QA MHFY05 After Vol Change";
  String entity = "150 Marina Medical Center";

  @Test
  public void test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog() {
    try {
      waitForAjaxExtJs();
      triggerDepartmentDialog(costModel, costModelScenario, entity);
      String style = costingMap.getUnitCostQuickCalculationDepartmentButtonCancelAndClose().getAttribute("style");
      WebElement[] departmentModalElements = {
              costingMap.getUnitCostQuickCalculationDepartmentField(),
              costingMap.getUnitCostQuickCalculationDepartmentButtonFilter(),
              costingMap.getUnitCostQuickCalculationDepartmentButtonApply(),
              costingMap.getUnitCostQuickCalculationDepartmentButtonClose(),
              costingMap.getUnitCostQuickCalculationDepartmentButtonCancelAndClose()
      };
      assertElementsAreDisplayed(departmentModalElements, printout);
      doClick(costingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
      waitForAjaxExtJs();
      WebElement[] departmentFilterElements = {
              costingMap.getUnitCostQuickCalculationDepartmentFilterField(),
              costingMap.getUnitCostQuickCalculationDepartmentFilterOperator(),
              costingMap.getUnitCostQuickCalculationDepartmentFilterCondition(),
              costingMap.getUnitCostQuickCalculationDepartmentFilterValue(),
              costingMap.getUnitCostQuickCalculationDepartmentFilterButtonAdd(),
              costingMap.getUnitCostQuickCalculationDepartmentFilterButtonRemoveAll(),
              costingMap.getUnitCostQuickCalculationDepartmentFilterButtonApplyFilter()
      };
      assertElementsAreDisplayed(departmentFilterElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClick(costingMap.getUnitCostQuickCalculationDepartmentButtonClose());
    }
  }

  @Test
  public void test0340cCostingTabUnitCostQuickCalculationPageShowHideFields()
          throws InterruptedException {
    try {
      waitForAjaxExtJs();
      costingMap.getUnitCostQuickCalculationButtonHide().click();
      waitForAjaxExtJs();
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonShow(), printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Unit Cost Quick...");
    }
  }
  //------------------------------------------------------------------------

  //===== Contracting Tab =====/
  @Test
  public void test0400ContractingTabContractModelsPage() throws InterruptedException {
    try {
      goToPage("Contract Models");
      waitForAjaxExtJs();
      waitForSpinnerToEnd();
      WebElement[] modelLibraryContractingElements = {
              modelLibrary.getModelLibraryContractingButtonCalculate(),
              modelLibrary.getModelLibraryContractingButtonCopy(),
              modelLibrary.getModelLibraryContractingButtonPaste(),
              modelLibrary.getModelLibraryContractingButtonImport(),
              modelLibrary.getModelLibraryContractingButtonExport()
      };
      assertElementsAreDisplayed(modelLibraryContractingElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Model Library");
    }
  }

  @Test
  public void test0410ContractingTabApcAllocationPageMap() throws InterruptedException {
    try {
      goToPage("APC Allocation");
      WebElement[] contractingTabApcAllocationPageElements = {
        contractingMap.getApcAllocationPageHelpLink(),
        contractingMap.getApcAllocationPageDataViewEncountersWithNoChargesReport(),
        contractingMap.getApcAllocationPageDataViewEncountersWithZeroChargeBalanceReport(),
        contractingMap.getApcAllocationPageDataViewEncountersWithNegativeChargeBalanceReport(),
        contractingMap.getApcAllocationPageDataViewEncountersWithNoEfrsReport(),
        contractingMap.getApcAllocationPageDataViewAllocateHcpcsAndBundledCharges(),
        contractingMap.getApcAllocationPageDataViewResetEncounterApcCharges(),
        contractingMap.getApcAllocationPageDataViewMatchEncounterId(),
      };
      assertElementsAreDisplayed(contractingTabApcAllocationPageElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("APC Allocation");
    }
  }

  @Test
  public void test0420ContractingTabContractualAllowanceExportPageMap() {
    try {
      goToPage("Contractual Allowance Export");
      WebElement[] contractingTabContractualAllowanceExportPageElements = {
        contractingMap.getContractualAllowanceExportPageButtonNew(),
        contractingMap.getContractualAllowanceExportPageButtonEdit(),
        contractingMap.getContractualAllowanceExportPageButtonFilter(),
        contractingMap.getContractualAllowanceExportPageButtonClearFilter(),
        //uncomment this        contractingMap.getContractualAllowanceExportPageButtonDelete(),
        contractingMap.getContractualAllowanceExportPageHelpLink(),
        contractingMap.getContractualAllowanceExportPageTableCornerCell(),
      };
      assertElementsAreDisplayed(contractingTabContractualAllowanceExportPageElements, printout);
      doClosePageOnLowerBar("Contractual...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  //------------------------------------------------------------------------

  @Test
  public void test0700DataMaintenanceTabMaintainDataPage() {
    try {
      goToPage("Maintain Data");
      waitForAjaxExtJs();
      waitForElementToBeVisible(dataMaintenance.getDataMaintenanceTreeExpanderContracting());
      WebElement[] maintainDataElements = {
              dataMaintenance.getDataMaintenanceTree(),
              dataMaintenance.getDataMaintenanceTreeExpanderContracting(),
              dataMaintenance.getDataMaintenanceTreeExpanderCosting(),
              dataMaintenance.getDataMaintenanceTreeExpanderEpisode(),
              dataMaintenance.getDataMaintenanceTreeExpanderGeneral(),
              dataMaintenance.getDataMaintenanceAZ()
      };
      assertElementsAreDisplayed(maintainDataElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories()
          throws InterruptedException {
    try {
      String[] mainFolders = {"Contracting", "Costing", "Episode", "General"};
      for (String mainFolder : mainFolders) {
        assertThatElementIsDisplayed(
                driver.findElement(By.xpath("//div[text()='" + mainFolder + "']"
                        + "/img[contains(@class,'x-tree-icon x-tree-icon-parent ')]")));
      }
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Maintain Data");
    }
  }

  @Test
  public void test0720DataMaintenanceTabUtilitiesPage() throws InterruptedException {
    try {
      goToPage("Utilities");
      waitForAjaxExtJs();
      WebElement[] getUtilitiesElements = {
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With No Charges Report", printout),
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With Zero Charge Balance Report", printout),
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With Negative Charge Balance Report", printout),
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With No EFRs Report", printout),
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Allocate HCPCS and Bundled Charges", printout),
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Reset Encounter APC Charges", printout),
              getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Match Encounter ID", printout)
      };
      assertElementsAreDisplayed(getUtilitiesElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Utilities");
    }
  }

  //------------------------------------------------------------------------
  @Test
  public void test0800SystemMaintenanceTabUsersPageMap() {
    try {
      goToPage("Users");
      waitForAjaxExtJs();
      WebElement[] userPageElements = {
              sysmaint.getUsersPageButtonNew(),
              sysmaint.getUsersPageButtonEdit(),
              sysmaint.getUsersPageButtonFilter(),
              sysmaint.getUsersPageButtonSynchToBoeServer(),
              sysmaint.getUsersPageButtonImport(),
              sysmaint.getUsersPageButtonExport(),
              sysmaint.getUsersPageButtonClearFilter(),
              sysmaint.getUsersPageHelpLink(),
              sysmaint.getUsersPageTableCornerCell(),
      };
      assertElementsAreDisplayed(userPageElements, printout);
      doClosePageOnLowerBar("Users");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0810SystemMaintenanceTabRolesPageMap() {
    try {
      goToPage("Roles");
      waitForAjaxExtJs();
      WebElement[] rolesPageElements = {
              sysmaint.getRolesPageButtonNew(),
              sysmaint.getRolesPageButtonEdit(),
              sysmaint.getRolesPageHelpLink(),
              sysmaint.getRolesPageTableCornerCell(),
      };
      assertElementsAreDisplayed(rolesPageElements, printout);
      doClosePageOnLowerBar("Roles");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0820SystemMaintenanceTabSecuritySettingsPageMap() {
    try {
      goToPage("Security Settings");
      waitForAjaxExtJs();
      WebElement[] securitySettingsPageElements = {
              sysmaint.getSecuritySettingsPageHelpLink(),
              sysmaint.getSecuritySettingsPageFormFieldAuthenticationType(),
              sysmaint.getSecuritySettingsPageFormFieldInactivityTimeOutPeriod(),
              sysmaint.getSecuritySettingsPageFormFieldFailedLoginAttempts(),
              sysmaint.getSecuritySettingsPageFormFieldAutomaticAccountReactivationPeriod(),
              sysmaint.getSecuritySettingsPageFormFieldPasswordMinimumLength(),
              sysmaint.getSecuritySettingsPageFormFieldPasswordExpirationPeriod(),
              sysmaint.getSecuritySettingsPageFormFieldPasswordGraceLoginsAfterExpirationPeriod(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeAtLeastOneLetter(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeBothUpperAndLowerCaseLetters(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeAtLeastOneNumber(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeAtLeastOneSpecialCharacter(),
              sysmaint.getSecuritySettingsPageFormFieldBusinessObjectsEnterpriseSynchInterval(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersAll(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersNone(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersAll(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersNone(),
              sysmaint.getSecuritySettingsPageCheckboxAuditLoggingEnabled(),
              sysmaint.getSecuritySettingsPageFormFieldAuditLogRetentionPeriod(),
              sysmaint.getSecuritySettingsPageButtonSave(),
      };
      assertElementsAreDisplayed(securitySettingsPageElements, printout);
      doClosePageOnLowerBar("Security Settings");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0830SystemMaintenanceTabGeneralSettingsPageMap() {
    try {
      goToPage("General Settings");
      waitForAjaxExtJs();
      WebElement[] generalSettingsPageElements = {
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosPublishedStart(),
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosPublishedEnd(),
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedStart(),
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedEnd(),
              sysmaint.getGeneralSettingsPageCheckboxRvuMaintenanceShowPrice(),
              sysmaint.getGeneralSettingsPageCheckboxRvuMaintenanceShowRevenue(),

              sysmaint.getGeneralSettingsPageFormFieldUiPageSize(),
              sysmaint.getGeneralSettingsPageFormFieldCalculationStatusUiPageSize(),
              sysmaint.getGeneralSettingsPageFormFieldImportExportAndUtilityStatusUiPageSize(),
              sysmaint.getGeneralSettingsPageFormFieldMaximumDockItems(),

              sysmaint.getGeneralSettingsPageFormFieldContractingContractBatch(),
              sysmaint.getGeneralSettingsPageFormFieldContractingContractAllowances(),
              sysmaint.getGeneralSettingsPageFormFieldContractingPsychCombinedComorbidityAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldContractingPublishedContractCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldContractingReimbursementScenarioAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldContractingUnpublishedContractCalculation(),

              sysmaint.getGeneralSettingsPageFormFieldCostingActivityVolumeDataCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingEncounterCostCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingClearEncounterCosts(),
              sysmaint.getGeneralSettingsPageFormFieldCostingCostModelScenarioCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingGlAdjustmentsAndReclassificationCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingGroupAllocationCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingOverheadModelScenarioCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingRvuCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingStatisticDataCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingUnitCostQuickCalculation(),

              sysmaint.getGeneralSettingsPageFormFieldGeneralClearMedicalServiceAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralChargeItemServiceClassificationScheme(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralEncounterServiceClassificationScheme(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralMedicalServiceAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralPriceListCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralPriceListEncountersAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralPriceListEncountersAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralRemoveChargeItemServiceClassification(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralRemoveEncounterServiceClassification(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralAllImportsAndExports(),
              sysmaint.getGeneralSettingsPageButtonSave(),
      };
      assertElementsAreDisplayed(generalSettingsPageElements, printout);
      doClosePageOnLowerBar("General Settings");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0840SystemMaintenanceTabCustomizeMaintainDataPageMap() throws InterruptedException {
    try {
      goToPage("Customize Maintain Data");
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      waitForElementDoWhileLoop(sysmaint.getCustomizeMaintainDataRadioButtonShowSelected(),
              printout);
      WebElement[] customizeMaintainDataPageElements = {
              sysmaint.getCustomizeMaintainDataRadioButtonShowSelected(),
              sysmaint.getCustomizeMaintainDataRadioButtonShowAll(),
              sysmaint.getCustomizeMaintainDataPageButtonSave(),
              sysmaint.getCustomizeMaintainDataPageButtonSaveAndClose(),
              sysmaint.getCustomizeMaintainDataPageButtonCancelAndClose(),
              sysmaint.getCustomizeMaintainDataPageLinkHelp(),
              sysmaint.getCustomizeMaintainDataCollapsibleSectionMaintainData(),
              sysmaint.getCustomizeMaintainDataCollapsibleSectionEncounterTabs(),
              sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataButtonFilter(),
              sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataButtonClearFilter(),
              sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll(),
              sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll(),
              sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters(),
              sysmaint.getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxShowTabSelectAll(),
              sysmaint.getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxTotals(),
      };
      assertElementsAreDisplayed(customizeMaintainDataPageElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Customize Maintain Data");
    }
  }

  @Test
  public void test0850SystemMaintenanceTabCustomizeTaskListsPageMap() throws InterruptedException {
    try {
      goToPage("Customize Task Lists");
      waitForAjaxExtJs();
      WebElement[] customizeTaskListsPageElements = {
              sysmaint.getCustomizeTaskListsPageLinkHelp(),
              sysmaint.getCustomizeTaskListsPageSubTabCost(),
              sysmaint.getCustomizeTaskListsPageSubTabOverhead(),
              sysmaint.getCustomizeTaskListsPageSubTabUnpublishedContract(),
              sysmaint.getCustomizeTaskListsPageSubTabPublishedContract(),
              sysmaint.getCustomizeTaskListsPageSubTabEpisode(),
      };
      assertElementsAreDisplayed(customizeTaskListsPageElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Customize Task Lists");
    }
  }

  @Test
  public void test0860SystemMaintenanceTabTerminalServerSessionsPageMap() throws InterruptedException {
    try {
      goToPage("Terminal Server Sessions");
      waitForAjaxExtJs();
      WebElement[] terminalServerSessionsPageElements = {
              sysmaint.getTerminalServerSessionsPageLinkHelp(),
              sysmaint.getTerminalServerSessionsPageButtonContinue(),
              sysmaint.getTerminalServerSessionsPageButtonClose(),
      };
      assertElementsAreDisplayed(terminalServerSessionsPageElements, printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Terminal Server...");
    }
  }

  //-------------------------------------
  @Test
  public void test0900StatusTabCalculationStatusPage() {
    try {
      goToPage("Calculation Status");
      doClick(statusMap.getCalculationStatusPageButtonAllStatus());
      waitUntilElementIsClickable(statusMap.getCalculationStatusPageButtonFilter());
      WebElement[] statusPageElements = {
        statusMap.getCalculationStatusPageButtonMyStatus(),
        statusMap.getCalculationStatusPageButtonAllStatus(),
        statusMap.getCalculationStatusPageButtonRefresh(),
        statusMap.getCalculationStatusPageFormFieldSearch(),
        statusMap.getCalculationStatusPageButtonSearchGlass(),
        statusMap.getCalculationStatusPageButtonFilter(),
        statusMap.getCalculationStatusPageButtonClearFilter(),
        statusMap.getCalculationStatusPageTableCornerCell(),
        //statusMap.getCalculationStatusPageButtonDeleteFiltered(),
      };
      assertElementsAreDisplayed(statusPageElements, printout);
      doClosePageOnLowerBar("Calculation Status");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0910StatusTabImportExportStatusPage() {
    try {
      goToPage("Import/Export Status");
      doClick(statusMap.getImportExportStatusPageButtonAllStatus());
      waitUntilElementIsClickable(statusMap.getImportExportStatusPageButtonFilter());
      WebElement[] statusPageElements = {
        statusMap.getImportExportStatusPageButtonMyStatus(),
        statusMap.getImportExportStatusPageButtonAllStatus(),
        statusMap.getImportExportStatusPageButtonRefresh(),
        statusMap.getImportExportStatusPageFormFieldSearch(),
        statusMap.getImportExportStatusPageButtonSearchGlass(),
        statusMap.getImportExportStatusPageButtonFilter(),
        statusMap.getImportExportStatusPageButtonClearFilter(),
        //statusMap.getImportExportStatusPageTableCornerCell(), //need to add to map
        //statusMap.getImportExportStatusPageButtonDeleteFiltered(),
      };
      assertElementsAreDisplayed(statusPageElements, printout);
      doClosePageOnLowerBar("Import/Export Status");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test0920StatusTabUtilityStatusPage() {
    try {
      goToPage("Utility Status");
      doClick(statusMap.getUtilityStatusPageButtonAllStatus());
      waitUntilElementIsClickable(statusMap.getUtilityStatusPageButtonFilter());
      WebElement[] statusPageElements = {
        statusMap.getUtilityStatusPageButtonMyStatus(),
        statusMap.getUtilityStatusPageButtonAllStatus(),
        statusMap.getUtilityStatusPageButtonRefresh(),
        statusMap.getUtilityStatusPageFormFieldSearch(),
        statusMap.getUtilityStatusPageButtonSearchGlass(),
        statusMap.getUtilityStatusPageButtonFilter(),
        statusMap.getUtilityStatusPageButtonClearFilter(),
        //statusMap.getUtilityStatusPageButtonDeleteFiltered(),
      };
      assertElementsAreDisplayed(statusPageElements, printout);
      doClosePageOnLowerBar("Utility Status");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  //------------------------------------------------------------------------

  //===== Filter Dialog Map =====//
  @Test
  public void test1000FilterDialogMap() throws InterruptedException {
    try {
      goToPage("Users");
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      doClick(sysmaint.getUsersPageButtonFilter());
      waitForAjaxExtJs();
      waitUntilElementIsClickable(dialogsMap.getFilterDialogButtonCancelAndClose());
      String dialogHeader = dialogsMap.getFilterDialogHeader().getText();
      assertEquals("Filter Users", dialogHeader);
      WebElement[] filterDialogElements = {
              dialogsMap.getFilterDialogHelpLink(),
              dialogsMap.getFilterDialogDropdownField(),
              dialogsMap.getFilterDialogDropdownOperator(),
              dialogsMap.getFilterDialogDropdownCondition(),
              dialogsMap.getFilterDialogFormFieldValue(),
              dialogsMap.getFilterDialogButtonAdd(),
              dialogsMap.getFilterDialogButtonApplyFilter(),
      };
      assertElementsAreDisplayed(filterDialogElements, printout);
    } catch (Throwable e) {
      throw e;
    } finally {
      try {
        doClick(dialogsMap.getFilterDialogButtonCancelAndClose());
        waitForAjaxExtJs();
      } catch (Throwable ee) {}
      doClosePageOnLowerBar("Users");
    }
  }

  private void triggerDepartmentDialog(String costModel, String costModelScenario, String entity) throws InterruptedException {
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),costModel);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),costModelScenario);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),entity);
    doClick(costingMap.getUnitCostQuickCalculationButtonSelect());
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(500);
  }
}
