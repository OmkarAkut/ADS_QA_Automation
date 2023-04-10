package webdriver.deployment;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import webdriver.globalscripts.pagetests.BuildVerificationTestScript;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.*;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmokeTest extends UcqcHelper {
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
	  String expectedReleaseVersion = version;
	  private static String BackgroundColorCosting= "rgba(0, 86, 26, 1)";

	  /** Automates test ticket ADS-6642(test0006LandingPageCosting(): includes only costing bubble validation and color in this)*/
	 @BeforeClass
	  public static void setupScript() throws Throwable {
			ExtentReport.reportCreate("SmokeTest", "webdriver.deployment", "SmokeTest");

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
			System.out.println("Test Class: " + SmokeTest.class.getSimpleName());
			ExtentReport.logPass("PASS", "setupScript");
		 } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript method", driver, e);
			fail(e.getMessage());
		}
	  }

	  //Login page tests
	  @Test
	  public void test0000VerifyLoginPageIsDisplayed() throws Throwable {	
	    System.out.println("Testing Login Page");
	    try {
	      waitForSpinnerToEnd();
	      Thread.sleep(1000);
	      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt = 'Harris Affinity Logo']")));
	      ExtentReport.logPass("PASS", "test0000VerifyLoginPageIsDisplayed");
	    } catch (Exception|AssertionError e) {
	    	ExtentReport.logFail("FAIL", "test0000VerifyLoginPageIsDisplayed", driver, e);
	      fail("LOGIN PAGE NOT DISPLAYED");
	    }
	  }

	  @Test
	  public void test0000aVerifyLoginPageElementsAreDisplayed() throws Throwable {
	    try {
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
			ExtentReport.logPass("PASS", "test0000aVerifyLoginPageElementsAreDisplayed");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000aVerifyLoginPageElementsAreDisplayed", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0000bLoginPageVerifyCurrentDateIsDisplayed() throws Throwable {
	    try {
			LocalDateTime now = LocalDateTime.now();
			if (printout) {
			  System.out.println(DateTimeFormatter.ofPattern("EEEE MMMM d, yyy HH:mm a").format(now));
			}
			//String expectedDate = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyy HH:mm a").format(now);
			String expectedDate = DateTimeFormatter.ofPattern("EEEE MMMM d, yyy").format(now);
			String actualLoginPageDate = generalElement.getLoginPageDate().getText();
			assertThat(actualLoginPageDate, containsString(expectedDate));
			ExtentReport.logPass("PASS", "test0000bLoginPageVerifyCurrentDateIsDisplayed");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000bLoginPageVerifyCurrentDateIsDisplayed", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0000cLoginPageVerifyReleaseVersion() throws Throwable {
	    try {
			String actualReleaseVersion = generalElement.getLoginPageReleaseVersion().getText();
			if (printout) {
			  System.out.println("Expected release version: " + expectedReleaseVersion);
			  System.out.println("Actual release version: " + actualReleaseVersion);
			}
			if(actualReleaseVersion.equals(expectedReleaseVersion)) {
				System.out.println("Version number not changed");
				assertFalse(printout);
				ExtentReport.logFail("FAIL", "test0000cLoginPageVerifyReleaseVersion",driver,null);
			}else {
				System.out.println("Version got changed");
				assertTrue(printout);
				ExtentReport.logPass("PASS", "test0000cLoginPageVerifyReleaseVersion");
			}
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000cLoginPageVerifyReleaseVersion", driver, e);
			fail(e.getMessage());
		}
	   // assertTrue(actualReleaseVersion, containsString(expectedReleaseVersion));
	  }

	  @Test
	  public void test0000dLoginPageVerifyHarrisLogoImage() throws Throwable {
	    try {
			if (printout) {
			  System.out.println("Actual Login Page Logo id: " + generalElement.getLoginPageLogo().getAttribute("id"));
			  System.out.println("Actual Login Page partial Logo src: " + generalElement.getLoginPageLogo().getAttribute("src").substring(23));
			}
			assertThat(generalElement.getLoginPageLogo().getAttribute("id"), containsString("image-1013"));
			assertThat(generalElement.getLoginPageLogo().getAttribute("src").substring(23), containsString("/resources/images/app/Harris_Affinity_Logo.png"));
			ExtentReport.logPass("PASS", "test0000dLoginPageVerifyHarrisLogoImage");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000dLoginPageVerifyHarrisLogoImage", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0000eLoginPageVerifyLogInPageHeaderIsLogIn() throws Throwable {
	    try {
			if (printout) {
			  System.out.println("Login Page Header: " + generalElement.getLoginPageHeaderLogIn().getText());
			}
			assertThat(generalElement.getLoginPageHeaderLogIn().getText(), containsString("Log In"));
			ExtentReport.logPass("PASS", "test0000eLoginPageVerifyLogInPageHeaderIsLogIn");
	    	} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000eLoginPageVerifyLogInPageHeaderIsLogIn", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0000fLoginPageVerifySignInSectionHeaderIsSignIn() throws Throwable {
	    try {
			if (printout) {
			  System.out.println("Login Page Sign In section header: " + generalElement.getLoginPageLogInHeaderSignIn().getText());
			}
			assertThat(generalElement.getLoginPageLogInHeaderSignIn().getText(), containsString("Sign In"));
			ExtentReport.logPass("PASS", "test0000fLoginPageVerifySignInSectionHeaderIsSignIn");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000fLoginPageVerifySignInSectionHeaderIsSignIn", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0000gLoginPageVerifyUsernameAndPasswordFieldLabelsAreDisplayed() throws Throwable {
	    try {
			if (printout) {
			  System.out.println("Login Page Username label: " + driver.findElement(By.xpath("//label[text() = 'Username']")));
			  System.out.println("Login Page Password label: " + driver.findElement(By.xpath("//label[text() = 'Password']")));
			}
			assertThat(driver.findElement(By.xpath("//label[text() = 'Username']")).isDisplayed(), equalTo(true));
			assertThat(driver.findElement(By.xpath("//label[text() = 'Password']")).isDisplayed(), equalTo(true));
			ExtentReport.logPass("PASS", "test0000gLoginPageVerifyUsernameAndPasswordFieldLabelsAreDisplayed");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000gLoginPageVerifyUsernameAndPasswordFieldLabelsAreDisplayed", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0000hLoginPageVerifyLoginButtonTextIsLogIn() throws Throwable {
	    try {
			if (printout) {
			  System.out.println("Login Button text: " + generalElement.getLoginPageButtonLogIn().getText());
			}
			assertThat(generalElement.getLoginPageButtonLogIn().getText(), equalTo("Log In"));
			ExtentReport.logPass("PASS", "test0000hLoginPageVerifyLoginButtonTextIsLogIn");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0000gLoginPageVerifyUsernameAndPasswordFieldLabelsAreDisplayed", driver, e);
			fail(e.getMessage());
		}
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
	  @Test
	  public void test0001LandingPageSystemMaintenance() throws Throwable {
	    try {
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
			ExtentReport.logPass("PASS", "test0001LandingPageSystemMaintenance");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0001LandingPageSystemMaintenance", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0002LandingPageDataMaintenance() throws Throwable {
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
			ExtentReport.logPass("PASS", "test0002LandingPageDataMaintenance");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0002LandingPageDataMaintenance", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0003LandingPageBudgeting() throws Throwable {
		  driverDelay(3000);
	    try {
			WebElement[] landingPageBudgetingElements = {
			        generalElement.getLandingPageBubbleBudgeting(),
			        generalElement.getLandingPageBubbleBudgetingHeader(),
			        generalElement.getLandingPageBubbleBudgetingImage(),
			        generalElement.getLandingPageBubbleBudgetingQuickLinkBudgeting()
			};
			assertElementsAreDisplayed(landingPageBudgetingElements, printout);
			ExtentReport.logPass("PASS", "test0003LandingPageBudgeting");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0003LandingPageBudgeting", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0004LandingPageEpisodes() throws Throwable {
	    try {
			WebElement[] landingPageEpisodesElements = {
			        generalElement.getLandingPageBubbleEpisode(),
			        generalElement.getLandingPageBubbleEpisodeHeader(),
			        generalElement.getLandingPageBubbleEpisodeImage(),
			        generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeModels(),
			        generalElement.getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance()
			};
			assertElementsAreDisplayed(landingPageEpisodesElements, printout);
			ExtentReport.logPass("PASS", "test0004LandingPageEpisodes");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0004LandingPageEpisodes", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0005LandingPageContracting() throws Throwable {
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
			ExtentReport.logPass("PASS", "test0005LandingPageContracting");

	    }  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0005LandingPageContracting", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0006LandingPageCosting() throws Throwable {
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
			ExtentReport.logPass("PASS", "test0006LandingPageCosting");

	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0006LandingPageCosting", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0007LandingPageReporting() throws Throwable {
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
	      ExtentReport.logPass("PASS", "test0007LandingPageReporting");
	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0007LandingPageReporting", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0008LandingPageAnalytics() throws Throwable {
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
	      ExtentReport.logPass("PASS", "test0008LandingPageAnalytics");

	    } catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0008LandingPageAnalytics", driver, e);
			fail(e.getMessage());
		}
	  }

	  @Test
	  public void test0009aGlobalHeader() throws Throwable {
	    try {
	      WebElement[] globalHeaderElements = {
	              generalElement.getGlobalHeaderLogo(),
	              generalElement.getGlobalHeaderButtonHelp(),
	              generalElement.getGlobalHeaderButtonContactUs(),
	              generalElement.getGlobalHeaderButtonLogout()
	      };
	      assertElementsAreDisplayed(globalHeaderElements,printout);
	      ExtentReport.logPass("PASS", "test0009aGlobalHeader");

	    }catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test0009aGlobalHeader", driver, e);
			fail(e.getMessage());
		}
	  }

	 
	  @AfterClass
	  public static void teardown() {
		    @SuppressWarnings("deprecation")
			WebDriverWait wait = new WebDriverWait(driver, 10);
		    try {
		      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")));
		      driver.findElement(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")).click();
		      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
		      System.out.println("Logout successful");
		    } catch (Throwable e) {
		      System.out.println("WARNING: Logout was not successful");
		    } finally {
		      System.out.println("Closing Driver");
		      driver.quit();
		      driver = null;
		    }
		    ExtentReport.report.flush();
	  }
}
