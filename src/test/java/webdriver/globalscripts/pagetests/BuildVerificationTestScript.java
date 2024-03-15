package webdriver.globalscripts.pagetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.data.AdsStandardData;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.AnalyticsMap;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.ReportingMap;
import webdriver.maps.StatusMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BuildVerificationTestScript extends UcqcHelper {
	/**
	  Regression test
	 ADS-6584,ADS-6582,ADS-6586,ADS-6592,ADS-6593,ADS-6594,ADS-6595,ADS-6596,ADS-6597,ADS-6598,ADS-6599
	 ADS-6503,ADS-6502,ADS-6501,ADS-6500,ADS-6498,ADS-6497,ADS-6496,ADS-6495,ADS-6601
	 */
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
	private static String systemMaintenanceSubTabsBackgroundColor1 = "rgba(165, 165, 165, 1)";
	private static String systemMaintenanceSubTabsBackgroundColor2 = "rgba(204, 204, 204, 1)";
	private static String BackgroundColorStatusTab = "rgba(158, 105, 0, 1)";
	private static String BackgroundColorReportingTab = "rgba(0, 119, 171, 1)";
	private static String BackgroundColorAnalyticsTab = "rgba(0, 63, 96, 1)";
	private static String BackgroundColorContractingTab = "rgba(127, 35, 111, 1)";
	private static String BackgroundColorCostingTab = "rgba(0, 86, 26, 1)";
	private static String BackgroundColorDataMaintenanceTab = "rgba(210, 70, 15, 1)";
	private static String BackgroundColorEpisodeTab = "rgba(159, 29, 53, 1)";
	private static String BackgroundColorBudgetingTab = "rgba(102, 102, 102, 1)";
	private static String BackgroundColorSystemMaintenanceTab = "rgba(135, 85, 64, 1)";
	 private static String BackgroundColorCosting= "rgba(0, 86, 26, 1)";
	  private static String BackgroundColorReporting= "rgba(0, 119, 171, 1)";
	  private static String BackgroundColorAnalytics= "rgba(0, 63, 96, 1)";
	  private static String BackgroundColorSystemMaintenance= "rgba(135, 85, 64, 1)";
	  private static String BackgroundColorEpisodes= "rgba(159, 29, 53, 1)";
	  private static String BackgroundColorContracting= "rgba(127, 35, 111, 1)";
	  private static String BackgroundColorDataMaintenance= "rgba(210, 70, 15, 1)";
	  private static String BackgroundColorDockBarReporting= "rgba(0, 119, 171, 1)";
	  private static String BackgroundColorDockBarWebIntel= "rgba(0, 119, 171, 1)";
	  private static String BackgroundColorDockBarAdHoc= "rgba(0, 119, 171, 1)";
	  private static String BackgroundColorDockBarSystemMaintainenance= "rgba(101, 55, 47, 1)";
	  private static String BackgroundColorDockEisodes="rgba(246, 246, 246, 1)";

	String expectedReleaseVersion = version; // only checks version, not date


	/** The local pages map test is a test of the elements on all of the individual functional area (local) page maps -
	 * that the elements on the map display on the page.
	 * @throws Exception 
	 */
	/** Regression test //ADS-6584,ADS-6582,ADS-6586,ADS-6592,ADS-6593,ADS-6594,ADS-6595,ADS-6596,ADS-6597,ADS-6598,ADS-6599
	 //ADS-6601,ADS-6600
*/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("BuildVerificationTestScript", "webdriver.globalscripts.pagetests", "BuildVerificationTestScript");
		try {
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
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	 
	// ===== Global Tests ======//
	//ADS-6586
	@Test
	public void test0001LandingPageSystemMaintenance_ADS_6500() throws Throwable {
		try {
			System.out.println("Logging In");

			/*
			 * modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
			 * Login.loginUser("AutomationTester1");
			 */
			Login.loginUser("AutomationTesterAdmin");
			// End of modification
			waitForSpinnerToEnd();
			waitForJsWindowOnload();
			isLoggedIn();
			System.out.println(driver.findElement(By.xpath("//div[@class='footerText']/span")).getText());
//			assertTrue(driver.findElement(By.xpath("//div[@class='footerText']/span")).getText()
//					.contains("Contents Copyright © 2023 Picis Clinical Solutions, Inc. All rights reserved."));
			//Shilpa updated text for 11.2 on 1.2.2024
			System.out.println(driver.findElement(By.xpath("//div[@class='footerText']/span")).getText());
			assertTrue(driver.findElement(By.xpath("//div[@class='footerText']/span")).getText()
//					 Omkar 20/2/2024 : Extra spaces added in 11.2
					.contains("Contents Copyright © 2024 Picis Clinical Solutions, Inc. All rights reserved."));
//					.contains("Contents Copyright © 2024 Picis Clinical Solutions, Inc.  All rights reserved. "));
			System.out.println("Testing Global Pages");
			WebElement[] landingPageSystemMaintenanceElements = {
					generalElement.getLandingPageBubbleSystemMaintenance(),
					generalElement.getLandingPageBubbleSystemMaintenanceHeader(),
					generalElement.getLandingPageBubbleSystemMaintenanceImage(),
					generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkUsers(),
					generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkRoles(),
					generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings(),
					generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings() };
			assertElementsAreDisplayed(landingPageSystemMaintenanceElements, printout);
			validateBackgroundColor(BackgroundColorSystemMaintenance, generalElement.getlandingPageSystemMaintenanceBgColor());
			//Users
			doClick(generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkUsers());
			validateBgColorAndPage(
					driver.findElement(By.xpath("//h1[text()='Users']")),
					BackgroundColorDockBarSystemMaintainenance,generalElement.getdockUsersbar(),"Users");
			
//			Roles
			doClick(generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkRoles());
			validateBgColorAndPage(
					driver.findElement(By.xpath("//div[text()='Roles']")),
					BackgroundColorDockBarSystemMaintainenance,generalElement.getdockRolesbar(),"Roles");
			//Security Settings
			doClick(generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings());
			validateBgColorAndPage(
					driver.findElement(By.xpath("//div[text()='Security Settings']")),
					BackgroundColorDockBarSystemMaintainenance,generalElement.getdockSecuritySetbar(),"Security Settings");
			//General Settings
			doClick(generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings());
			validateBgColorAndPage(
					driver.findElement(By.xpath("//div[text()='General Settings']")),
					BackgroundColorDockBarSystemMaintainenance,generalElement.getdockSecurityGenSetbar(),"General Settings");
			ExtentReport.logPass("PASS", "test0001LandingPageSystemMaintenance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0001LandingPageSystemMaintenance", driver, e);
			fail(e.getMessage());
		}
	}
	 @Test
	  public void test0002LandingPageDataMaintenance_ADS_6498() throws Throwable {
	    try {
			WebElement[] landingPageDataMaintenanceElements = {
			        generalElement.getLandingPageBubbleDataMaintenance(),
			        generalElement.getLandingPageBubbleDataMaintenanceHeader(),
			        generalElement.getLandingPageBubbleDataMaintenanceImage(),
			        generalElement.getLandingPageBubbleDataMaintenanceQuickLinkMaintainData(),
			        generalElement.getLandingPageBubbleDataMaintenanceQuickLinkLoadData(),
			        generalElement.getLandingPageBubbleDataMaintenanceQuickLinkUtilities()
			};
			assertElementsAreDisplayed(landingPageDataMaintenanceElements,printout);
			validateBackgroundColor(BackgroundColorDataMaintenance, generalElement.getlandingPageDataMaintenanceBgColor());
			doClick(generalElement.getLandingPageBubbleDataMaintenanceQuickLinkMaintainData());
			validateBgColorAndPage(driver.findElement(By.xpath("//div[text()='Maintain Data']")), BackgroundColorDataMaintenance,generalElement.getdockDataMaintenancebar(), "Maintain Data");
			doClick(generalElement.getLandingPageBubbleDataMaintenanceQuickLinkLoadData());
			 
			 try {
				 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
				String windowHandle = driver.getWindowHandle();
			      ArrayList tabs = new ArrayList (driver.getWindowHandles());
		    	  driver.switchTo().window((String) tabs.get(1));
			      if(driver.getCurrentUrl().contains("Myrtille")) {
			    	  assertTrue(printout);
			    	   driver.close();
			    	   driver.switchTo().window(windowHandle );
			      }
		      }catch(Exception e) {
		    	  assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='Load Data Sessions']")));
		    	  validateBackgroundColor(BackgroundColorDataMaintenance, generalElement.getdockLoadDatabar());
			      doClosePageOnLowerBar("Load Data");
		      }
				doClick(generalElement.getLandingPageBubbleDataMaintenanceQuickLinkUtilities());
				validateBgColorAndPage(driver.findElement(By.xpath("//div[text()='Utilities']")), BackgroundColorDataMaintenance,generalElement.getdockUtilitiesbar(), "Utilities");
			ExtentReport.logPass("PASS", "test0002LandingPageDataMaintenance");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0002LandingPageDataMaintenance", driver, e);
			fail(e.getMessage());
		}
	  }
	//ADS-6497[add step4 -12]
	  @Test
	  public void test0004LandingPageEpisodes_ADS_6497() throws Throwable {
	    try {
			WebElement[] landingPageEpisodesElements = {
			        generalElement.getLandingPageBubbleEpisode(),
			        generalElement.getLandingPageBubbleEpisodeHeader(),
			        generalElement.getLandingPageBubbleEpisodeImage(),
			        generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeModels(),
			        generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance()
			};
			assertElementsAreDisplayed(landingPageEpisodesElements, printout);
			validateBackgroundColor(BackgroundColorEpisodes,generalElement.getlandingPageEpisodesBgColor());
			doClick(generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeModels());
			doClick("(//span[text()='Episodes'])[2]//preceding::div[contains(@class,'x-tree-expander')]");
			assertListElementsAreDisplayed(driver.findElements(By.xpath("(//div[@class='x-grid-item-container'])[1]//table//tr//td//div//span")), printout);
			doClick("(//span[text()='Episodes'])[2]//preceding::div[contains(@class,'x-tree-expander')]");
			validateBgColorAndPage(driver.findElement(By.xpath("(//span[text()='Episodes'])[2]")), BackgroundColorDockEisodes, generalElement.getdockdockEpisodeModelsbar(), "Episode Models");
			doClick(generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance());
			validateBgColorAndPage(driver.findElement(By.xpath("((//div[text()='Maintain Data'])[1]")), BackgroundColorDataMaintenance, generalElement.getdockDataMaintenancebar(), "Maintain Data");
		ExtentReport.logPass("PASS", "test0004LandingPageEpisodes");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0004LandingPageEpisodes", driver, e);
			fail(e.getMessage());
		}
	  }
	//ADS-6496[add step4 -12],ADS-6582
	  @Test
	  public void test0005LandingPageContracting_ADS_6496_ADS_6582() throws Throwable {
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
	      validateBackgroundColor(BackgroundColorContracting,generalElement.getlandingPageContractingBgColor());
	      doClick(generalElement.getLandingPageBubbleContractingQuickLinkContractModels());
	      validateBgColorAndPage(driver.findElement(By.xpath("(//span[text()='Contracting'])[1]")), BackgroundColorDockEisodes, generalElement.getdockdockEpisodeModelsbar(), "Model Library");
	      doClick(generalElement.getLandingPageBubbleContractingQuickLinkContractingDataMaintenance());
	      validateBgColorAndPage(driver.findElement(By.xpath("(//div[text()='Maintain Data'])[1]")), BackgroundColorDataMaintenance, generalElement.getdockDataMaintenancebar(), "Maintain Data");

	      ExtentReport.logPass("PASS", "test0005LandingPageContracting");

	    }  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0005LandingPageContracting", driver, e);
			fail(e.getMessage());
		}
	  }
	//ADS-6495[add step 4-8],ADS-6642[add step 4-8]
	  @Test
	  public void test0006LandingPageCosting_ADS_6495_ADS_6642() throws Throwable {
	    try {
	      WebElement[] landingPageCostingElements = {
	              generalElement.getLandingPageBubbleCosting(),
	              generalElement.getLandingPageBubbleCostingHeader(),
	              generalElement.getLandingPageBubbleCostingContent(),
	              generalElement.getLandingPageBubbleCostingContentText(),
	              generalElement.getLandingPageBubbleCostingImage(),
	              generalElement.getLandingPageBubbleCostingQuickLinkCostingModels(),
	              generalElement.getLandingPageBubbleCostingQuickLinkCostingDataMaintenance(),
	              generalElement.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation()

	      };
	      assertElementsAreDisplayed(landingPageCostingElements,printout);
	      validateBackgroundColor(BackgroundColorCosting,generalElement.getlandingPageBubbleCostingBgColor());
	      doClick(generalElement.getLandingPageBubbleCostingQuickLinkCostingModels());
	      validateBgColorAndPage(driver.findElement(By.xpath("(//span[text()='Costing'])[2]")), BackgroundColorEpisodes,generalElement.getdockdockEpisodeModelsbar(), "Costing Models");
	      doClick(generalElement.getLandingPageBubbleCostingQuickLinkCostingDataMaintenance());
	      validateBgColorAndPage(driver.findElement(By.xpath("(//div[text()='Maintain Data'])[1]")), BackgroundColorDataMaintenance, generalElement.getdockDataMaintenancebar(), "Maintain Data");
	      doClick(generalElement.getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation());
	      validateBgColorAndPage(driver.findElement(By.xpath("(//div[text()='Unit Cost Quick Calculation'])")), BackgroundColorCosting, generalElement.getdockUnitCostQuickModelsbar(), "Unit Cost Quick Calculation");
    
	      ExtentReport.logPass("PASS", "test0006LandingPageCosting");

	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0006LandingPageCosting", driver, e);
			fail(e.getMessage());
		}
	  }
	  //ADS-6502[add step 4-11]
	  @Test
	  public void test0007LandingPageReporting_ADS_6502() throws Throwable {
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
	      validateBackgroundColor(BackgroundColorReporting,generalElement.getlandingPageReportingBgColor());
	      doClick(generalElement.getLandingPageBubbleReportingQuickLinkReportLibrary());
	      validateBackgroundColor(BackgroundColorDockBarReporting, generalElement.getdockReportingbar());
	      doClosePageOnLowerBar("Report Library");
	      doClick(generalElement.getLandingPageBubbleReportingQuickLinkWebIntelligence());
	      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	      String windowHandle = driver.getWindowHandle();
	      ArrayList tabs = new ArrayList (driver.getWindowHandles());
	      driver.switchTo().window((String) tabs.get(1));
	      driver.switchTo().frame("servletBridgeIframe");
	      assertElementIsDisplayed(driver.findElement(By.xpath("//img[@title='SAP']")));
	      driver.switchTo().defaultContent();
	      driver.close();
	      driver.switchTo().window(windowHandle );
	      doClick(generalElement.getLandingPageBubbleReportingQuickLinkAdHocReportDesign());
	      try {
	    	  driver.switchTo().window((String) tabs.get(1));
		      if(driver.getCurrentUrl().contains("Myrtille")) {
		    	  assertTrue(printout);
		    	   driver.close();
		    	   driver.switchTo().window(windowHandle );
		      }
	      }catch(Exception e) {
	    	  assertElementIsDisplayed(driver.findElement(By.xpath("//div[text()='Ad Hoc Report Design']")));
	    	  validateBackgroundColor(BackgroundColorDockBarReporting, generalElement.getdockAdhocbar());
		      doClosePageOnLowerBar("Ad Hoc Report Design");
	      }
	   
	   
	    
	      ExtentReport.logPass("PASS", "test0007LandingPageReporting");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0007LandingPageReporting", driver, e);
			fail(e.getMessage());
		}
	  }
	  public void ValidateQlikView(WebElement element) {
		  try {
			doClick(element);
			  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			   
			     
			  String windowHandle = driver.getWindowHandle();
			  ArrayList tabs = new ArrayList (driver.getWindowHandles());
			  driver.switchTo().window((String) tabs.get(1));
			  if(driver.getCurrentUrl().contains("qlikview")) {
				  assertTrue(printout);
				  
				  driver.switchTo().defaultContent();
				  driver.close();
				  driver.switchTo().window(windowHandle );
			  }
			  else {
				  fail();
			  }
		} catch (Exception e) {
			
		}
		 
	  }
	//ADS-6501[ add step 4-7]
	  @Test
	  public void test0008LandingPageAnalytics_ADS_6501() throws Throwable {
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
	      validateBackgroundColor(BackgroundColorAnalytics, generalElement.getlandinglandingPageAnalyticsBgColor());
	      ValidateQlikView(generalElement.getLandingPageBubbleAnalyticsQuickLinkExecutiveDashboard());
	      ValidateQlikView(generalElement.getLandingPageBubbleAnayticsQuickLinkAnalyticDashobaords());

	      ExtentReport.logPass("PASS", "test0008LandingPageAnalytics");

	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0008LandingPageAnalytics", driver, e);
			fail(e.getMessage());
		}
	  }
	 public void validateBgColorAndPage(WebElement PageTextxpath,String bgColor,WebElement dockElement,String closeBarElement){
		  try {
			 
				waitForElementToBeVisible(PageTextxpath);
				assertElementIsDisplayed(PageTextxpath);
				validateBackgroundColor(bgColor,dockElement);
				doClosePageOnLowerBar(closeBarElement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	@Test
	public void test0009bUserMenu() throws Throwable {
		try {
			waitForPresenceOfElement("//*[@id = 'dropdown']");
			doClick(generalElement.getUserDropdown());
			WebElement[] userMenuElements = { generalElement.getUserDropdownChangePassword(),
					generalElement.getUserDropdownLogOut(), generalElement.getUserDropdownTermsOfUse() };
			assertElementsAreDisplayed(userMenuElements, printout);
			ExtentReport.logPass("PASS", "test0009bUserMenu");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0009bUserMenu", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6592
	@Test
	public void test0009cMainTabs_6592() throws Throwable {
		try {
			WebElement[] mainTabElements = { generalElement.getAnalyticsTab(), generalElement.getReportingTab(),
					generalElement.getCostingTab(), generalElement.getContractingTab(), generalElement.getEpisodesTab(),
					generalElement.getBudgetingTab(), generalElement.getDataMaintenanceTab(),
					generalElement.getSystemMaintenanceTab(), generalElement.getStatusTab() };
			assertElementsAreDisplayed(mainTabElements, printout);
			ExtentReport.logPass("PASS", "test0009cMainTabs");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0009cMainTabs", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010AdsMainTabOrder() throws Throwable {
		try {
			WebElement menuBar = driver.findElement(By.xpath("//div[@id='menubar-body']/ul"));
			List<WebElement> actualTabs = javaMakeListOfWebElements2(menuBar, "/li");
			List<String> actualTabsText = javaMakeListOfStrings(actualTabs);
			assertEquals(AdsStandardData.expectedTabs, actualTabsText);
			ExtentReport.logPass("PASS", "test0010AdsMainTabOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010AdsMainTabOrder", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6593
	@Test
	public void test0010bAnalyticsSubTabsOrder_6593() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getAnalyticsTab(), "analytics_subtab",
					AdsStandardData.expectedAnalyticsSubTabs);
			ExtentReport.logPass("PASS", "test0010bAnalyticsSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010bAnalyticsSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010cReportingSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getReportingTab(), "reporting_subtab",
					AdsStandardData.expectedReportingSubTabs);
			ExtentReport.logPass("PASS", "test0010cReportingSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010cReportingSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
		// String costingReports = driver.findElement(By.xpath("//*[@id='Costing
		// Reports']/a")).getText();
		// assertEquals("Costing Reports", costingReports);
	}

	@Test
	public void test0010dCostingSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getCostingTab(), "costing_subtab", AdsStandardData.expectedCostingSubTabs);
			ExtentReport.logPass("PASS", "test0010dCostingSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010dCostingSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010eContractingSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getContractingTab(), "contracting_subtab",
					AdsStandardData.expectedContractingSubTabs);
			ExtentReport.logPass("PASS", "test0010eContractingSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010eContractingSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010fEpisodesSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getEpisodesTab(), "episode_subtab",
					AdsStandardData.expectedEpisodesSubTabs);
			ExtentReport.logPass("PASS", "test0010fEpisodesSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010fEpisodesSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010gBudgetingSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getBudgetingTab(), "budgeting_subtab",
					AdsStandardData.expectedBudgetingSubTabs);
			ExtentReport.logPass("PASS", "test0010gBudgetingSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010gBudgetingSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010hDataMaintenanceSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getDataMaintenanceTab(), "datamaintenance_subtab",
					AdsStandardData.expectedDataMaintenanceSubTabs);
			ExtentReport.logPass("PASS", "test0010hDataMaintenanceSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010hDataMaintenanceSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010iSystemMaintenanceSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getSystemMaintenanceTab(), "systemmaintenance_subtab",
					AdsStandardData.expectedSystemMaintenanceSubTabs);
			ExtentReport.logPass("PASS", "test0010iSystemMaintenanceSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010iSystemMaintenanceSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0010jStatusSubTabsOrder() throws InterruptedException, Throwable {
		try {
			verifySubTabOrder(generalElement.getStatusTab(), "status_subtab", AdsStandardData.expectedStatusSubTabs);
			ExtentReport.logPass("PASS", "test0010jStatusSubTabsOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0010jStatusSubTabsOrder", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6582
	@Test
	public void test0012AnalyticsTab_6582() throws Throwable {
		try {
			// Clicks the Analytics Tab in order to open the dropdown menu
			doClick(generalElement.getAnalyticsTab());
			WebElement[] analyticsTabElements = { generalElement.getExecutiveDashboardSubTab(),
					generalElement.getAnalyticDashboardSubTab(), generalElement.getAnalyticRefreshScenariosSubTab(),
					generalElement.getAnalyticsAdministrationSubTab(), generalElement.getCustomizeAnalyticsSubTab(),
					generalElement.getCustomizeAnalyticsSessionsSubTab(),
					generalElement.getAnalyticsServerDesktopSubTab(),
					generalElement.getAnalyticsServerDesktopSessionsSubTab() };
			assertElementsAreDisplayed(analyticsTabElements, printout);
			validateBackgroundColor(BackgroundColorAnalyticsTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble1 medium analytics']")));

			ExtentReport.logPass("PASS", "test0012AnalyticsTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0012AnalyticsTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6594
	@Test
	public void test0013ReportingTab_6594() throws Throwable {
		try {
			// Clicks the Reporting Tab in order to open the dropdown menu
			doClick(generalElement.getReportingTab());
			WebElement[] reportingTabElements = { generalElement.getReportingLibrarySubTab(),
					generalElement.getWebIntelligenceSubTab(), generalElement.getAdHocReportDesignSubTab(),
					generalElement.getReportMenuMaintenanceSubTab(), generalElement.getReportDateMaintenanceSubTab(),
					generalElement.getReportPublicationSubTab(), generalElement.getAdHocBusinessViewMaintenanceSubTab(),
					generalElement.getWebIntelligenceUniverseMaintenanceSubTab(),
					// generalElement.getIcd9icd10GemsInquirySubTab(), //page removed from ADS for
					// 10.3
					// generalElement.getIcd9icd10GemsAnalysisSubTab(), //page removed from ADS for
					// 10.3
					// generalElement.getCostingReportsSubTab()
			};
			assertElementsAreDisplayed(reportingTabElements, printout);
			validateBackgroundColor(BackgroundColorReportingTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble2 medium reporting']")));

			ExtentReport.logPass("PASS", "test0013ReportingTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0013ReportingTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6595
	@Test
	public void test0014CostingTab_6595() throws Throwable {
		try {
			doClick(generalElement.getCostingTab());
			WebElement[] costingTabElements = { generalElement.getCostingModelsSubTab(),
					generalElement.getCostingDataMaintenanceSubTab(), generalElement.getRVUMaintenanceSubTab(),
					generalElement.getCostModelScenarioCalculationSubTab(),
					generalElement.getUnitCostQuickCalculationSubTab() };
			assertElementsAreDisplayed(costingTabElements, printout);
			validateBackgroundColor(BackgroundColorCostingTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble3 medium costing']")));

			ExtentReport.logPass("PASS", "test0014CostingTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0014CostingTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6596
	@Test
	public void test0015ContractingTab_6596() throws Throwable {
		try {
			// Clicks the Contracting Tab in order to open the dropdown menu
			doClick(generalElement.getContractingTab());
			WebElement[] contractingTabElements = { generalElement.getContractModelsSubTab(),
					generalElement.getContractingDataMaintenanceSubTab(),
					generalElement.getContractualAllowanceExportSubTab(), generalElement.getApcAllocationSubTab() };
			assertElementsAreDisplayed(contractingTabElements, printout);
			validateBackgroundColor(BackgroundColorContractingTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble4 medium contracting']")));

			ExtentReport.logPass("PASS", "test0015ContractingTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0015ContractingTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6597
	@Test
	public void test0016EpisodesTab_6597() throws Throwable {
		try {
			// Clicks the Episodes Tab in order to open the dropdown menu
			doClick(generalElement.getEpisodesTab());
			WebElement[] episodesTabElement = { generalElement.getEpisodeModelsSubTab(),
					generalElement.getEpisodeDataMaintenanceSubTab() };
			assertElementsAreDisplayed(episodesTabElement, printout);
			validateBackgroundColor(BackgroundColorEpisodeTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble5 small episode']")));

			ExtentReport.logPass("PASS", "test0016EpisodesTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0016EpisodesTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6598
	@Test
	public void test0017BudgetingTab_6598() throws Throwable {
		try {
			// Clicks the Budgeting Tab in order to open the dropdown menu
			doClick(generalElement.getBudgetingTab());
			assertElementIsDisplayed(generalElement.getBudgetingSubTab(), printout);
			validateBackgroundColor(BackgroundColorBudgetingTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble7 small budgeting']")));

			ExtentReport.logPass("PASS", "test0017BudgetingTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0017BudgetingTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6599
	@Test
	public void test0018DataMaintenanceTab_6599() throws Throwable {
		try {
			// Clicks the Data Maintenance Tab in order to open the dropdown menu
			doClick(generalElement.getDataMaintenanceTab());
			WebElement[] dataMaintenanceTabElement = { generalElement.getMaintainDataSubTab(),
					generalElement.getLoadDataSubTab(), generalElement.getUtilitiesSubTab() };
			assertElementsAreDisplayed(dataMaintenanceTabElement, printout);
			validateBackgroundColor(BackgroundColorDataMaintenanceTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble6 small datamaintenance']")));

			ExtentReport.logPass("PASS", "test0018DataMaintenanceTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0018DataMaintenanceTab", driver, e);
			fail(e.getMessage());
		}
	}

	/*
	 * Test - [Main Page UI] Validate �System Maintenance Menu bar� on dashboard.
	 * ADS-6600
	 */
	//ADS-6600
	@Test
	public void test0019SystemMaintenanceTab_6600() throws Throwable {
		try {
			// Clicks the System Maintenance Tab in order to open the dropdown menu
			doClick(generalElement.getSystemMaintenanceTab());
			WebElement[] systemMaintenanceTabElement = { generalElement.getUsersSubTab(),
					generalElement.getRolesSubTab(), generalElement.getSecuritySettingsSubTab(),
					generalElement.getGeneralSettingsSubTab(), generalElement.getCustomizeMaintainDataSubTab(),
					generalElement.getCustomizeTaskListSubTab(), generalElement.getTerminalServerSessionsSubTab(),
					generalElement.getTerminalServerDesktopSubTab() };

			assertElementsAreDisplayed(systemMaintenanceTabElement, printout);
			// Hover on sub tab and validate background color
			validateBackgroundColor(BackgroundColorSystemMaintenanceTab,
					driver.findElement(By.xpath("//div[@class='bubble bubble8 small systemmaintenance']")));

			validateBackgroundColorOnHoverForSubTabs(systemMaintenanceSubTabsBackgroundColor1,
					systemMaintenanceSubTabsBackgroundColor2, systemMaintenanceTabElement);
			ExtentReport.logPass("PASS", "test0019SystemMaintenanceTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0019SystemMaintenanceTab", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6601
	@Test
	public void test0020StatusTab_6601() throws Throwable {
		try {
			// Clicks the Status Tab in order to open the dropdown menu
			doClick(generalElement.getStatusTab());
			validateBackgroundColor(BackgroundColorStatusTab, generalElement.getStatusTab());
			WebElement[] statusTabElement = { generalElement.getCalculationStatusSubTab(),
					generalElement.getImportExportStatusSubTab(), generalElement.getUtilityStatusSubTab() };
			assertElementsAreDisplayed(statusTabElement, printout);
			validateBackgroundColorOnHoverForSubTabs(BackgroundColorStatusTab, BackgroundColorStatusTab,
					statusTabElement);
			ExtentReport.logPass("PASS", "test0020StatusTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0020StatusTab", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0050MainPageHelpLinkAndHelpModule() throws Throwable {

		try {
			Thread.sleep(3000);
			waitForAjaxExtJs();
			String firstHandle = webSwitchToNewWindow(generalElement.getGlobalHeaderButtonHelp(), printout);
			Thread.sleep(5000);
			assertHelpPageHeader("Decision Support", printout);
			driver.close();
			driver.switchTo().window(firstHandle);
			// assertHelpLink(generalElement.getGlobalHeaderButtonHelp(), "Decision
			// Support", printout);
			ExtentReport.logPass("PASS", "test0050MainPageHelpLinkAndHelpModule");

		} catch (Throwable e) {
			ExtentReport.logFail("FAIL", "test0050MainPageHelpLinkAndHelpModule", driver, e);

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
//		Omkar 28/11/2023 : In some cases such as Reporting tab, all the values need not be verified as most of tem have been created by test Users.AdHocReportDesigner1 Thus validating only expected values
//		assertEquals(expectedSubTabs, actualTabsText);
		assertListOfStringsContainsExpectedStrings(actualTabsText, expectedSubTabs);
	}

	// ===== Analytics Map =====//
	@Test
	public void test0100AnalyticsRefreshScenarios() throws InterruptedException, Throwable {
		System.out.println("Testing Local Pages");
		try {
			goToPage("Analytic Refresh Scenarios");
			waitForAjaxExtJs();
			waitForPresenceOfElement("//h1[text()='Analytics Refresh Scenarios']");
			waitForPresenceOfElement(
					"//label[contains(@class, 'areaHeaderTitle') and text()='Active Refresh Scenarios']");
			// Thread.sleep(10000);
			WebElement[] analyticsRefreshScenariosElements = { analyticsMap.getAnalyticsRefreshScenariosButtonNew(),
					analyticsMap.getAnalyticsRefreshScenariosButtonEdit(),
					analyticsMap.getAnalyticsRefreshScenariosButtonCopy(),
					analyticsMap.getAnalyticsRefreshScenariosButtonDelete() };
			Thread.sleep(1000);
			assertElementsAreDisplayed(analyticsRefreshScenariosElements, printout);
			ExtentReport.logPass("PASS", "test0100AnalyticsRefreshScenarios");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test0100AnalyticsRefreshScenarios", driver, e);
			fail(e.getMessage());
		} finally {
			try {
//				Omkar 12/04/2023 : Xpath change for 11.2
//				doClosePageOnLowerBar("Analytic Refresh...");
				doClosePageOnLowerBar("Analytic Refresh Scenarios");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test0100AnalyticsRefreshScenarios", driver, e);
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void test0200ReportingTabReportLibraryPageMap() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Report Library");
				waitForPresenceOfElement("//iframe[contains(@src,'reporting/main.html')]");
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
				waitForPresenceOfElement("//div[contains(@class,'gwtQuery-draggable')]/a[@title]");
				WebElement[] reportingTabReportLibraryPageElements = { reportingMap.getReportLibraryPageName(),
						reportingMap.getReportLibraryPageHelpLink(), reportingMap.getReportLibraryPageFormFieldSearch(),
						reportingMap.getReportLibraryPageSectionHeaderReportStatus(),
						reportingMap.getReportLibraryPageSectionReportStatusTableHeaderName(),
						reportingMap.getReportLibraryPageButtonRename(),
						// uncomment this reportingMap.getReportLibraryPageButtonDelete(),
						reportingMap.getReportLibraryPageSectionReportStatusFirstReportListed(),
						reportingMap.getReportLibraryPageLinkExpandAll(),
						reportingMap.getReportLibraryPageLinkCollapseAll(),
						reportingMap.getReportLibraryPageExpandTemplates(),
						reportingMap.getReportLibraryPageExpandReports(),
						reportingMap.getReportLibraryPageExpandFolders(), };
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
			ExtentReport.logPass("PASS", "test0200ReportingTabReportLibraryPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0200ReportingTabReportLibraryPageMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0210ReportingTabReportMenuMaintenancePageMap() throws Throwable {
		try {
			goToPage("Report Menu Maintenance");
			waitForAjaxExtJs();
			WebElement[] reportingTabReportMenuMaintenancePagePageElements = {
					reportingMap.getReportingTabReportMenuMaintenancePageName(),
					reportingMap.getReportingTabReportMenuMaintenancePageHelpLink(),
					reportingMap.getReportingTabReportMenuMaintenancePageTableCornerCell(),
					getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "Rename",
							printout),
					getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "Edit",
							printout),
					getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage, "New",
							printout),
					// uncomment this
					// getWebElementButtonWithElementText(reportingMap.reportingTabXpathMenuMaintenancePage,
					// "Delete", printout),
			};
			assertElementsAreDisplayed(reportingTabReportMenuMaintenancePagePageElements, printout);
			assertGridTableLoads();
//			Omkar 12/04/2023 : Xpath change for 11.2
//			doClosePageOnLowerBar("Report Menu...");
			doClosePageOnLowerBar("Report Menu Maintenance");
			ExtentReport.logPass("PASS", "test0210ReportingTabReportMenuMaintenancePageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0210ReportingTabReportMenuMaintenancePageMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0220ReportingTabReportDateMaintenancePageMap() throws Throwable {
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
					getWebElementButtonWithElementText(reportingMap.reportingTabXpathDateMaintenancePage, "General",
							printout),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldConsumerHistoryReferenceDate(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeEnd(), };
			assertElementsAreDisplayed(reportingTabReportDateMaintenancePageElements, printout);

			doClick(getWebElementButtonWithElementText(reportingMap.reportingTabXpathDateMaintenancePage, "Costing",
					printout));
			waitForAjaxExtJs();
			WebElement[] reportDateMaintenancePageElementsCostingSubTab = { reportingMap
					.getReportingTabReportDateMaintenancePageDateFieldAdjustmentAndReclassificationDateRangeStart(),
					reportingMap
							.getReportingTabReportDateMaintenancePageDateFieldAdjustmentAndReclassificationDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldCostItemDataSetDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldCostItemDataSetDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageComboBoxCostItemDataSetMonthYearRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageComboBoxCostItemDataSetMonthYearRangeEnd(),
					reportingMap
							.getReportingTabReportDateMaintenancePageDateFieldCostingActivityStatisticDateRangeStart(),
					reportingMap
							.getReportingTabReportDateMaintenancePageDateFieldCostingActivityStatisticDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldGlDataItemDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldGlDataItemDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadModelScenarioDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadModelScenarioDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadTransactionDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldOverheadTransactionDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldRvuEffectiveDate(), };
			assertElementsAreDisplayed(reportDateMaintenancePageElementsCostingSubTab, printout);

			doClick(getWebElementButtonWithElementText(reportingMap.reportingTabXpathDateMaintenancePage,
					"Contracting and A/R", printout));
			waitForAjaxExtJs();
			WebElement[] reportDateMaintenancePageElementsContractingAndArSubTab = {
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldBillDropDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldBillDropDateRangeEnd(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractStartDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractStartDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractExpirationDateRangeStart(),
					reportingMap.getReportingTabReportDateMaintenancePageDateFieldContractExpirationDateRangeEnd(), };
			assertElementsAreDisplayed(reportDateMaintenancePageElementsContractingAndArSubTab, printout);
//			Omkar 12/04/2023 : Xpath change for 11.2
//			doClosePageOnLowerBar("Report Date...");
			doClosePageOnLowerBar("Report Date Maintenance");
			ExtentReport.logPass("PASS", "test0220ReportingTabReportDateMaintenancePageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0220ReportingTabReportDateMaintenancePageMap", driver, e);
			fail(e.getMessage());
		}
	}

	// ===== Reporting Tab =====//
	// @Ignore //The ICD sections were removed from ADS for 10.3
	// @Test
	// public void testReportingTabIcd9Icd10GemsAnalysisPageMap() throws
	// InterruptedException {
	// try {
	// goToPage("Gems Analysis");
	// Thread.sleep(3000);
	// WebDriverWait wait = new WebDriverWait(driver, 30);
	// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'gemsAnalysis')]")));
	// WebElement[] reportingTabGemsAnalysisPageElements = {
	// reportingMap.getReportingTabGemsAnalysisPageName(),
	// reportingMap.getReportingTabGemsAnalysisPageHelpLink(),
	// };
	// assertElementsAreDisplayed(reportingTabGemsAnalysisPageElements, printout);
	// driver.switchTo().parentFrame();
	// doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
	// } catch (Throwable e) {
	// fail(e.getMessage());
	// }
	// }
	//
	// @Ignore //The ICD sections were removed from ADS for 10.3
	// @Test
	// public void testReportingTabIcd9Icd10GemsInquiryPageMap() {
	// try {
	// goToPage("Gems Inquiry");
	// Thread.sleep(3000);
	// driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'gemsInquiry.html')]")));
	// WebElement[] reportingTabGemsInquiryPageElements = {
	// reportingMap.getReportingTabGemsInquiryPageName(),
	// reportingMap.getReportingTabGemsInquiryPageHelpLink(),
	// };
	// assertElementsAreDisplayed(reportingTabGemsInquiryPageElements, printout);
	// driver.switchTo().parentFrame();
	// doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
	// } catch (Throwable e) {
	// fail(e.getMessage());
	// }
	// }

	// ===== Costing Tab =====//
	@Test
	public void test0300CostingTabCostModelLibraryPage() throws Throwable {
		try {
			try {
				goToPage("Costing Models");
				waitForAjaxExtJs();
				waitForElementToBeVisible(modelLibrary.getModelLibraryTableButtonFirst());
				WebElement[] modelLibraryElements = { modelLibrary.getModelLibraryFieldSearch(),
						modelLibrary.getModelLibraryButtonSearch(), modelLibrary.getModelLibraryCostModelButtonNew(),
						modelLibrary.getModelLibraryButtonOpenTaskList(), modelLibrary.getModelLibraryButtonFilter(),
						modelLibrary.getModelLibraryButtonClearFilter(),
						modelLibrary.getModelLibraryCostModelButtonDelete(),
						modelLibrary.getModelLibraryButtonNewFolder(), modelLibrary.getModelLibraryButtonRenameFolder(),
						modelLibrary.getModelLibraryButtonDeleteFolder(),
						modelLibrary.getModelLibraryTreeColumnCosting(),
						modelLibrary.getModelLibraryTreeColumnArrowCosting(),
						modelLibrary.getModelLibraryTreeColumnContracting(),
						modelLibrary.getModelLibraryTreeColumnArrowContracting(),
						modelLibrary.getModelLibraryTreeColumnEpisodes(),
						modelLibrary.getModelLibraryTreeColumnArrowEpisodes(),
						modelLibrary.getModelLibraryTableButtonFirst(),
						modelLibrary.getModelLibraryTableButtonPrevious(),
						modelLibrary.getModelLibraryFieldInputNumber(), modelLibrary.getModelLibraryTableButtonGo(),
						modelLibrary.getModelLibraryTableButtonNext(), modelLibrary.getModelLibraryTableButtonLast() };
				assertElementsAreDisplayed(modelLibraryElements, printout);
				// doClick(modelLibrary.getModelLibraryTreeColumnContracting());
				WebElement[] modelLibraryContractingElements = { modelLibrary.getModelLibraryFieldSearch(),
						modelLibrary.getModelLibraryButtonSearch(), modelLibrary.getModelLibraryButtonOpenTaskList(),
						modelLibrary.getModelLibraryCostModelButtonNew(),
						modelLibrary.getModelLibraryCostModelButtonDelete(), modelLibrary.getModelLibraryButtonFilter(),
						modelLibrary.getModelLibraryButtonClearFilter() };
				assertElementsAreDisplayed(modelLibraryContractingElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
//				Omkar 12/04/2023 : Xpath change for 11.2
//				doClosePageOnLowerBar("Model Library");
				doClosePageOnLowerBar("Costing Models");
			}
			ExtentReport.logPass("PASS", "test0300CostingTabCostModelLibraryPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0300CostingTabCostModelLibraryPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0310CostingTabDataMaintenancePage() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Costing Data Maintenance");
				waitForSpinnerToEnd();
				assertTrue(driver.findElement(By.xpath("//span[text() = 'A - Z']")).isDisplayed());
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
				doClosePageOnLowerBar("Maintain Data");
			}
			ExtentReport.logPass("PASS", "test0310CostingTabDataMaintenancePage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0310CostingTabDataMaintenancePage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0320CostingTabRvuMaintenanceMap() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("RVU Maintenance");
				waitForAjaxExtJs();
				WebElement[] rvuMaintenanceElements = { costingMap.getRvuMaintenanceButtonMaintainRVUs(),
						costingMap.getRvuMaintenanceButtonFilter(),
						// costingMap.getRvuMaintenanceButtonClearFilter(), //inactive by default-greyed
						// out
						costingMap.getRvuMaintenanceButtonImport(), costingMap.getRvuMaintenanceButtonExport(),
						costingMap.getCostingMapTableButtonFirst(), costingMap.getCostingMapTableButtonPrevious(),
						costingMap.getCostingMapTableFieldInputNumber(), costingMap.getCostingMapTableButtonGo(),
						costingMap.getCostingMapTableButtonNext(), costingMap.getCostingMapTableButtonLast() };
				assertElementsAreDisplayed(rvuMaintenanceElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
				doClosePageOnLowerBar("RVU Maintenance");
			}
			ExtentReport.logPass("PASS", "test0320CostingTabRvuMaintenanceMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0320CostingTabRvuMaintenanceMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0330CostingTabCostModelScenarioCalculationMap() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Cost Model Scenario Calculation");
				waitForAjaxExtJs();
				WebElement[] costModelScenarioCalculationElements = {
						costingMap.getCostModelScenarioCalculationButtonEdit(),
						costingMap.getCostModelScenarioCalculationButtonFilter(),
						costingMap.getCostModelScenarioCalculationButtonClearFilter(),
						costingMap.getCostModelScenarioCalculationButtonCalculate(),
						costingMap.getCostModelScenarioCalculationButtonResults(),
						costingMap.getCostModelCalcTableButtonFirst(), costingMap.getCostModelCalcTableButtonPrevious(),
						costingMap.getCostModelCalcTableButtonInputNumber(), costingMap.getCostModelCalcTableButtonGo(),
						costingMap.getCostModelCalcTableButtonNext(), costingMap.getCostModelCalcTableButtonLast() };
				Thread.sleep(1000);
				assertElementsAreDisplayed(costModelScenarioCalculationElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
//				Omkar 12/04/2023 : Xpath change for 11.2
//				doClosePageOnLowerBar("Cost Model...");
				doClosePageOnLowerBar("Cost Model Calculation Scenarios");
			}
			ExtentReport.logPass("PASS", "test0330CostingTabCostModelScenarioCalculationMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0330CostingTabCostModelScenarioCalculationMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0340aCostingTabUnitCostQuickCalculationPage() throws Throwable {
			try {
		
			goToPage("Unit Cost Quick Calculation");
			waitForSpinnerToEnd();
			// doMaximizeWindow();
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
					costingMap.getUnitCostQuickCalculationButtonHide() };
			assertElementsAreDisplayed(costModelScenarioCalculationElements, printout);
			ExtentReport.logPass("PASS", "test0340aCostingTabUnitCostQuickCalculationPage");
			
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0340aCostingTabUnitCostQuickCalculationPage", driver, e);
			fail(e.getMessage());
		}
	}

	String costModel = "QA Cost Model";
	String costModelScenario = "QA MHFY05 After Vol Change";
	String entity = "150 Marina Medical Center";

	@Test
	public void test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog() throws Throwable {
		try {
			try {
				waitForAjaxExtJs();
				triggerDepartmentDialog(costModel, costModelScenario, entity);
				String style = costingMap.getUnitCostQuickCalculationDepartmentButtonCancelAndClose()
						.getAttribute("style");
				WebElement[] departmentModalElements = { costingMap.getUnitCostQuickCalculationDepartmentField(),
						costingMap.getUnitCostQuickCalculationDepartmentButtonFilter(),
						costingMap.getUnitCostQuickCalculationDepartmentButtonApply(),
//						costingMap.getUnitCostQuickCalculationDepartmentButtonClose(),
						costingMap.getUnitCostQuickCalculationDepartmentButtonCancelAndClose() };
				assertElementsAreDisplayed(departmentModalElements, printout);
				doClick(costingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
				waitForAjaxExtJs();
				WebElement[] departmentFilterElements = { costingMap.getUnitCostQuickCalculationDepartmentFilterField(),
						costingMap.getUnitCostQuickCalculationDepartmentFilterOperator(),
						costingMap.getUnitCostQuickCalculationDepartmentFilterCondition(),
						costingMap.getUnitCostQuickCalculationDepartmentFilterValue(),
						costingMap.getUnitCostQuickCalculationDepartmentFilterButtonAdd(),
						costingMap.getUnitCostQuickCalculationDepartmentFilterButtonRemoveAll(),
						costingMap.getUnitCostQuickCalculationDepartmentFilterButtonApplyFilter() };
				assertElementsAreDisplayed(departmentFilterElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} 
			ExtentReport.logPass("PASS", "test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0340cCostingTabUnitCostQuickCalculationPageShowHideFields() throws InterruptedException, Throwable {
		try {
			try {
				waitForAjaxExtJs();
				costingMap.getUnitCostQuickCalculationButtonHide().click();
				waitForAjaxExtJs();
				assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonShow(), printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
//				Omkar 12/04/2023 : Xpath change for 11.2
//				doClosePageOnLowerBar("Unit Cost Quick...");
				doClosePageOnLowerBar("Unit Cost Quick Calculation");
			}
			ExtentReport.logPass("PASS", "test0340cCostingTabUnitCostQuickCalculationPageShowHideFields");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0340cCostingTabUnitCostQuickCalculationPageShowHideFields", driver, e);
			fail(e.getMessage());
		}
	}
	// ------------------------------------------------------------------------

	// ===== Contracting Tab =====/
	@Test
	public void test0400ContractingTabContractModelsPage() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Contract Models");
				waitForAjaxExtJs();
				waitForSpinnerToEnd();
				WebElement[] modelLibraryContractingElements = {
						modelLibrary.getModelLibraryContractingButtonCalculate(),
						modelLibrary.getModelLibraryContractingButtonCopy(),
						modelLibrary.getModelLibraryContractingButtonPaste(),
						modelLibrary.getModelLibraryContractingButtonImport(),
						modelLibrary.getModelLibraryContractingButtonExport() };
				assertElementsAreDisplayed(modelLibraryContractingElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
//				Omkar 12/04/2023 : Xpath change for 11.2
//				doClosePageOnLowerBar("Model Library");
				doClosePageOnLowerBar("Contract Models");
			}
			ExtentReport.logPass("PASS", "test0400ContractingTabContractModelsPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0400ContractingTabContractModelsPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0410ContractingTabApcAllocationPageMap() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("APC Allocation");
				WebElement[] contractingTabApcAllocationPageElements = { contractingMap.getApcAllocationPageHelpLink(),
						contractingMap.getApcAllocationPageDataViewEncountersWithNoChargesReport(),
						contractingMap.getApcAllocationPageDataViewEncountersWithZeroChargeBalanceReport(),
						contractingMap.getApcAllocationPageDataViewEncountersWithNegativeChargeBalanceReport(),
						contractingMap.getApcAllocationPageDataViewEncountersWithNoEfrsReport(),
						contractingMap.getApcAllocationPageDataViewAllocateHcpcsAndBundledCharges(),
						contractingMap.getApcAllocationPageDataViewResetEncounterApcCharges(),
						contractingMap.getApcAllocationPageDataViewMatchEncounterId(), };
				assertElementsAreDisplayed(contractingTabApcAllocationPageElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
				doClosePageOnLowerBar("APC Allocation");
			}
			ExtentReport.logPass("PASS", "test0410ContractingTabApcAllocationPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0410ContractingTabApcAllocationPageMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0420ContractingTabContractualAllowanceExportPageMap() throws Throwable {
		try {
			try {
				goToPage("Contractual Allowance Export");
				WebElement[] contractingTabContractualAllowanceExportPageElements = {
						contractingMap.getContractualAllowanceExportPageButtonNew(),
						contractingMap.getContractualAllowanceExportPageButtonEdit(),
						contractingMap.getContractualAllowanceExportPageButtonFilter(),
						contractingMap.getContractualAllowanceExportPageButtonClearFilter(),
						// uncomment this
						 contractingMap.getContractualAllowanceExportPageButtonDelete(),
						contractingMap.getContractualAllowanceExportPageHelpLink(),
						contractingMap.getContractualAllowanceExportPageTableCornerCell(),
						 };
				assertElementsAreDisplayed(contractingTabContractualAllowanceExportPageElements, printout);
//				Omkar 12/04/2023 : Xpath change for 11.2
//				doClosePageOnLowerBar("Contractual...");
			} catch (Throwable e) {
				fail(e.getMessage());
			}
			ExtentReport.logPass("PASS", "test0420ContractingTabContractualAllowanceExportPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0420ContractingTabContractualAllowanceExportPageMap", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Contractual Allowance Export");

		}
	}

	// ------------------------------------------------------------------------

	@Test
	public void test0700DataMaintenanceTabMaintainDataPage() throws Throwable {
		try {
			goToPage("Maintain Data");
			waitForAjaxExtJs();
			waitForElementToBeVisible(dataMaintenance.getDataMaintenanceTreeExpanderContracting());
			WebElement[] maintainDataElements = { dataMaintenance.getDataMaintenanceTree(),
					dataMaintenance.getDataMaintenanceTreeExpanderContracting(),
					dataMaintenance.getDataMaintenanceTreeExpanderCosting(),
					dataMaintenance.getDataMaintenanceTreeExpanderEpisode(),
					dataMaintenance.getDataMaintenanceTreeExpanderGeneral(), dataMaintenance.getDataMaintenanceAZ() };
			assertElementsAreDisplayed(maintainDataElements, printout);
			ExtentReport.logPass("PASS", "test0700DataMaintenanceTabMaintainDataPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0700DataMaintenanceTabMaintainDataPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories()
			throws InterruptedException, Throwable {
		try {
			try {
				String[] mainFolders = { "Contracting", "Costing", "Episode", "General" };
				for (String mainFolder : mainFolders) {
//					assertThatElementIsDisplayed(driver.findElement(By.xpath("//s[text()='" + mainFolder + "']"
//							+ "/img[contains(@class,'x-tree-icon x-tree-icon-parent ')]")));
					//Shilpa update xpath for 11.2 on 1.2.2024
					assertThatElementIsDisplayed(driver.findElement(By.xpath("//span[text()='" + mainFolder + "']"
							+ "//preceding::div[contains(@class,'x-tree-elbow-img')]")));
				}
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {

				doClosePageOnLowerBar("Maintain Data");
			}
			ExtentReport.logPass("PASS",
					"test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL",
					"test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories", driver,
					e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0720DataMaintenanceTabUtilitiesPage() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Utilities");
				waitForAjaxExtJs();
				WebElement[] getUtilitiesElements = {
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(),
								"Encounters With No Charges Report", printout),
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(),
								"Encounters With Zero Charge Balance Report", printout),
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(),
								"Encounters With Negative Charge Balance Report", printout),
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(),
								"Encounters With No EFRs Report", printout),
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(),
								"Allocate HCPCS and Bundled Charges", printout),
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(),
								"Reset Encounter APC Charges", printout),
						getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Match Encounter ID",
								printout) };
				assertElementsAreDisplayed(getUtilitiesElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
				doClosePageOnLowerBar("Utilities");
			}
			ExtentReport.logPass("PASS", "test0720DataMaintenanceTabUtilitiesPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0720DataMaintenanceTabUtilitiesPage", driver, e);
			fail(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------
	@Test
	public void test0800SystemMaintenanceTabUsersPageMap() throws Throwable {
		try {
			goToPage("Users");
			waitForAjaxExtJs();
			WebElement[] userPageElements = { sysmaint.getUsersPageButtonNew(), sysmaint.getUsersPageButtonEdit(),
					sysmaint.getUsersPageButtonFilter(), sysmaint.getUsersPageButtonSynchToBoeServer(),
					sysmaint.getUsersPageButtonImport(), sysmaint.getUsersPageButtonExport(),
					sysmaint.getUsersPageButtonClearFilter(), sysmaint.getUsersPageHelpLink(),
					sysmaint.getUsersPageTableCornerCell(), };
			assertElementsAreDisplayed(userPageElements, printout);

			doClosePageOnLowerBar("Users");
			ExtentReport.logPass("PASS", "test0800SystemMaintenanceTabUsersPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0800SystemMaintenanceTabUsersPageMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0810SystemMaintenanceTabRolesPageMap() throws Throwable {
		try {
			goToPage("Roles");
			waitForAjaxExtJs();
			WebElement[] rolesPageElements = { sysmaint.getRolesPageButtonNew(), sysmaint.getRolesPageButtonEdit(),
					sysmaint.getRolesPageHelpLink(), sysmaint.getRolesPageTableCornerCell(), };
			assertElementsAreDisplayed(rolesPageElements, printout);
			doClosePageOnLowerBar("Roles");
			ExtentReport.logPass("PASS", "test0810SystemMaintenanceTabRolesPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0810SystemMaintenanceTabRolesPageMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0820SystemMaintenanceTabSecuritySettingsPageMap() throws Throwable {
		try {
			//fail due to scroll issue
			goToPage("Security Settings");
			waitForAjaxExtJs();
			if(sysmaint.getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersAll().isDisplayed()) {
				System.out.println("PASS");
			}
			WebElement[] securitySettingsPageElements = { sysmaint.getSecuritySettingsPageHelpLink(),
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
					sysmaint.getSecuritySettingsPageButtonSave(), };
			assertElementsAreDisplayed(securitySettingsPageElements, printout);
			
			ExtentReport.logPass("PASS", "test0820SystemMaintenanceTabSecuritySettingsPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0820SystemMaintenanceTabSecuritySettingsPageMap", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Security Settings");
		}
	}

	@Test
	public void test0830SystemMaintenanceTabGeneralSettingsPageMap() throws Throwable {
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
					sysmaint.getGeneralSettingsPageButtonSave(), };
			assertElementsAreDisplayed(generalSettingsPageElements, printout);
			
			ExtentReport.logPass("PASS", "test0830SystemMaintenanceTabGeneralSettingsPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0830SystemMaintenanceTabGeneralSettingsPageMap", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("General Settings");
		}
	}

	@Test
	public void test0840SystemMaintenanceTabCustomizeMaintainDataPageMap() throws InterruptedException, Throwable {
		try {
			try {
//				Login.loginUser("AutomationTesterAdmin");
				goToPage("Customize Maintain Data");
				waitForSpinnerToEnd();
				waitForAjaxExtJs();
				driverDelay();
				waitForElementDoWhileLoop(sysmaint.getCustomizeMaintainDataRadioButtonShowSelected(), printout);
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
						sysmaint.getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxTotals(), };
				assertElementsAreDisplayed(customizeMaintainDataPageElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			}
			ExtentReport.logPass("PASS", "test0840SystemMaintenanceTabCustomizeMaintainDataPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0840SystemMaintenanceTabCustomizeMaintainDataPageMap", driver, e);
			fail(e.getMessage());
		}
		 finally {
				doClosePageOnLowerBar("Customize Maintain Data");
			}
	}

	@Test
	public void test0850SystemMaintenanceTabCustomizeTaskListsPageMap() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Customize Task Lists");
				waitForAjaxExtJs();
				WebElement[] customizeTaskListsPageElements = { sysmaint.getCustomizeTaskListsPageLinkHelp(),
						sysmaint.getCustomizeTaskListsPageSubTabCost(),
						sysmaint.getCustomizeTaskListsPageSubTabOverhead(),
						sysmaint.getCustomizeTaskListsPageSubTabUnpublishedContract(),
						sysmaint.getCustomizeTaskListsPageSubTabPublishedContract(),
						sysmaint.getCustomizeTaskListsPageSubTabEpisode(), };
				assertElementsAreDisplayed(customizeTaskListsPageElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
				doClosePageOnLowerBar("Customize Task Lists");
			}
			ExtentReport.logPass("PASS", "test0850SystemMaintenanceTabCustomizeTaskListsPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0850SystemMaintenanceTabCustomizeTaskListsPageMap", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0860SystemMaintenanceTabTerminalServerSessionsPageMap() throws InterruptedException, Throwable {
		try {
			try {
				goToPage("Terminal Server Sessions");
				waitForAjaxExtJs();
				WebElement[] terminalServerSessionsPageElements = { sysmaint.getTerminalServerSessionsPageLinkHelp(),
						sysmaint.getTerminalServerSessionsPageButtonContinue(),
						sysmaint.getTerminalServerSessionsPageButtonClose(), };
				assertElementsAreDisplayed(terminalServerSessionsPageElements, printout);
			} catch (Throwable e) {
				fail(e.getMessage());
			} finally {
//				Omkar 28/11/2023 : Changes in xpath
//				doClosePageOnLowerBar("Terminal Server...");
				doClosePageOnLowerBar("Terminal Server Sessions");
			}
			ExtentReport.logPass("PASS", "test0860SystemMaintenanceTabTerminalServerSessionsPageMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0860SystemMaintenanceTabTerminalServerSessionsPageMap", driver, e);
			fail(e.getMessage());
		}
	}

	// -------------------------------------
	@Test
	public void test0900StatusTabCalculationStatusPage() throws Throwable {
		try {
			goToPage("Calculation Status");
			doClick(statusMap.getCalculationStatusPageButtonAllStatus());
			waitUntilElementIsClickable(statusMap.getCalculationStatusPageButtonFilter());
			WebElement[] statusPageElements = { statusMap.getCalculationStatusPageButtonMyStatus(),
					statusMap.getCalculationStatusPageButtonAllStatus(),
					statusMap.getCalculationStatusPageButtonRefresh(),
					statusMap.getCalculationStatusPageFormFieldSearch(),
					statusMap.getCalculationStatusPageButtonSearchGlass(),
					statusMap.getCalculationStatusPageButtonFilter(),
					statusMap.getCalculationStatusPageButtonClearFilter(),
					statusMap.getCalculationStatusPageTableCornerCell(),
					// statusMap.getCalculationStatusPageButtonDeleteFiltered(),
			};
			assertElementsAreDisplayed(statusPageElements, printout);
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test0900StatusTabCalculationStatusPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0900StatusTabCalculationStatusPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0910StatusTabImportExportStatusPage() throws Throwable {
		try {
			goToPage("Import/Export Status");
			doClick(statusMap.getImportExportStatusPageButtonAllStatus());
			waitUntilElementIsClickable(statusMap.getImportExportStatusPageButtonFilter());
			WebElement[] statusPageElements = { statusMap.getImportExportStatusPageButtonMyStatus(),
					statusMap.getImportExportStatusPageButtonAllStatus(),
					statusMap.getImportExportStatusPageButtonRefresh(),
					statusMap.getImportExportStatusPageFormFieldSearch(),
					statusMap.getImportExportStatusPageButtonSearchGlass(),
					statusMap.getImportExportStatusPageButtonFilter(),
					statusMap.getImportExportStatusPageButtonClearFilter(),
					// statusMap.getImportExportStatusPageTableCornerCell(), //need to add to map
					// statusMap.getImportExportStatusPageButtonDeleteFiltered(),
			};
			assertElementsAreDisplayed(statusPageElements, printout);
			doClosePageOnLowerBar("Import/Export Status");
			ExtentReport.logPass("PASS", "test0910StatusTabImportExportStatusPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0910StatusTabImportExportStatusPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test0920StatusTabUtilityStatusPage() throws Throwable {
		try {
			goToPage("Utility Status");
			doClick(statusMap.getUtilityStatusPageButtonAllStatus());
			waitUntilElementIsClickable(statusMap.getUtilityStatusPageButtonFilter());
			WebElement[] statusPageElements = { statusMap.getUtilityStatusPageButtonMyStatus(),
					statusMap.getUtilityStatusPageButtonAllStatus(), statusMap.getUtilityStatusPageButtonRefresh(),
					statusMap.getUtilityStatusPageFormFieldSearch(), statusMap.getUtilityStatusPageButtonSearchGlass(),
					statusMap.getUtilityStatusPageButtonFilter(), statusMap.getUtilityStatusPageButtonClearFilter(),
					// statusMap.getUtilityStatusPageButtonDeleteFiltered(),
			};
			assertElementsAreDisplayed(statusPageElements, printout);
			doClosePageOnLowerBar("Utility Status");
			ExtentReport.logPass("PASS", "test0920StatusTabUtilityStatusPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test0920StatusTabUtilityStatusPage", driver, e);
			fail(e.getMessage());
		}
	}

	// ------------------------------------------------------------------------

	// ===== Filter Dialog Map =====//
	@Test
	public void test1000FilterDialogMap() throws Throwable {
		try {
			try {
				goToPage("Users");
				waitForSpinnerToEnd();
				waitForAjaxExtJs();
				doClick(sysmaint.getUsersPageButtonFilter());
				waitForAjaxExtJs();
				// waitUntilElementIsClickable(dialogsMap.getFilterDialogButtonCancelAndClose());
				waitUntilElementIsClickable(dialogsMap.getFilterDialogHeader());
				String dialogHeader = dialogsMap.getFilterDialogHeader().getText();
				assertEquals("Filter Users", dialogHeader);
				WebElement[] filterDialogElements = { dialogsMap.getFilterDialogHelpLink(),
						dialogsMap.getFilterDialogDropdownField(), dialogsMap.getFilterDialogDropdownOperator(),
						dialogsMap.getFilterDialogDropdownCondition(), dialogsMap.getFilterDialogFormFieldValue(),
						dialogsMap.getFilterDialogButtonAdd(), dialogsMap.getFilterDialogButtonApplyFilter(), };
				assertElementsAreDisplayed(filterDialogElements, printout);
			} catch (Throwable e) {
				throw e;
			} finally {
				try {
					doClick(dialogsMap.getFilterDialogButtonCancelAndClose());
					waitForAjaxExtJs();
				} catch (Throwable ee) {
				}

				doClosePageOnLowerBar("Users");
			}
			ExtentReport.logPass("PASS", "test1000FilterDialogMap");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test1000FilterDialogMap", driver, e);
			fail(e.getMessage());
		}
	}

	//ADS-6584
	@Test
	public void test1002ValidateContactUsPage() throws Throwable {
		try {
			doClick(generalElement.getGlobalHeaderButtonContactUs());
			assertElementIsDisplayedWithXpath("(//*[contains(@src,'Harris_Affinity_Logo.png')])[2]");
			assertElementIsDisplayedWithXpath("(//a[@class='contactUsLink'])[1]");
			assertElementIsDisplayedWithXpath("(//a[@class='contactUsLink'])[2]");
			System.out.println(driver.findElement(By.xpath("//pre")).getAttribute("innerHTML").replaceAll("\\W", ""));
			if (driver.findElement(By.xpath("//pre")).getAttribute("innerHTML").replaceAll("\\W", "").contains(
					"ForcriticaldownissuespleasecontactSupportat8665693375forUScustomers8669648196forNonUScustomersFornoncriticalissuespleaseusetheCustomerSupportPortalataclasscontactUsLinktarget_blankhrefhttpssupportharrishealthcarecomAffinityhttpssupportharrishealthcarecomAffinityaFormoreinformationaboutourcompanyproductsandservicespleasevisitaclasscontactUsLinktarget_blankhrefhttpwwwHarrisAffinitycomwwwHarrisAffinitycoma")) {
				assertTrue(printout);
			}
			ExtentReport.logPass("PASS", "test1002ValidateContactUsPage");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "test1002ValidateContactUsPage", driver, e);
			fail(e.getMessage());
		}
	}

	private void triggerDepartmentDialog(String costModel, String costModelScenario, String entity)
			throws InterruptedException {
		doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),
				costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
		doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
				costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), costModelScenario);
		doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),
				costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(), entity);
		doClick(costingMap.getUnitCostQuickCalculationButtonSelect());
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		Thread.sleep(500);
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
