package webdriver.deployment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExtentReport.ExtentReport;
import webdriver.core.Driver;
import webdriver.core.Login;
import webdriver.globalscripts.pagetests.PageTestHelperStatic;
import webdriver.globalstatic.LoginRolesTesting;
import webdriver.globalstatic.SetupStatic;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.contracting.GeneralContractInfo;
import webdriver.users.Roles;
import webdriver.users.Users;

@RunWith(Parameterized.class)
public class UserLoginTest extends LoginRolesTesting {
	 private String userRole;
	 static GeneralElementsMap generalElement;
	 public UserLoginTest(Roles role) throws Exception {
		    super(role);
		    System.out.println("Role in constructor: " + role);
		    this.userRole = String.valueOf(role);
	 }
		    @Before
		    public void setupScript() throws Exception,Throwable {
		  	ExtentReport.reportCreate("SecurityRolesTestMainTabLevelStatic", "webdriver.globalscripts.securitytests", "SecurityRolesTestMainTabLevelStatic");
		      try {
		  		generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
		  		ExtentReport.logPass("PASS", "setupScript");
		  	} catch (Exception|AssertionError e) {
		  		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		  		fail(e.getMessage());
		  	}


		    }
	
	@Test
	public void testLoginUser() throws Throwable {
		
			 try {
			      System.out.println("START TEST");
			      System.out.println("User Login test: " + userRole);
			   		driverDelay();    			
			WebElement logoutoption = driver.findElement(By.xpath("//div[text()='Log Out']"));
//			Assert.assertTrue("User Login is successful", printout);
			if(logoutoption.isDisplayed()) {
				//Assert.assertTrue("User Login is successful", printout);
				ExtentReport.logPass("PASS", "testLoginUser");
				Assert.assertTrue(true);
				System.out.println("USer Login is successfull");
				
			}
//			
			

		} catch (Exception|AssertionError e) {
			
			ExtentReport.logFail("FAIL", "testLoginUser", driver, e);
			fail("User login not successfull");
		}
		}

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
//	      {Roles.valueOf("Automation_Tester")},  //should be commented out
	    };
	    return Arrays.asList(roles);
	  }

	  @After
	  public  void endtest() {
	      driver.findElement(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")).click();

	 	 ExtentReport.report.flush();
	  }
}
