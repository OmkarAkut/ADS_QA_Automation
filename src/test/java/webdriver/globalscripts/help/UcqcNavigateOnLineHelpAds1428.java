package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import ExtentReport.ExtentReport;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcNavigateOnLineHelpAds1428
        extends UcqcHelper {
	
  static String firstHandle;
  static CostingMap ucqcMap;
  static String[] ucqcRequiredFields = {
      "Marina",
      "*CM1 TB MHFY05 After Vol Change_UCQC",
//      "*CM2 TB MHFY05 No Price List - 2",
      "150 Marina Medical Center",
     // "3145  ENDOSCOPY", 
        "2130",           //Venkat Exact value not taking in search field
//      "Jan 2005 to Jan 2005" //Shilp updated test data 28.5.2024
        "Apr 2004 to Apr 2004"
  };

  
  /**
   Zephyr Test Ticket: ADS-1428 (no dev story-regression only).
   This script tests that the Help links function properly across the UCQC pages.
 * @throws Throwable 
  */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UcqcNavigateOnLineHelpAds1428","webdriver.globalscripts.help", "UcqcNavigateOnLineHelpAds1428");
	  
    try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println(
		    "Test Class: " + UcqcNavigateOnLineHelpAds1428.class.getSimpleName()
		);
		loginStaticUser(Users.CostingDepartmentManager1);
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
		ucqcDisplayChargeCodeGrid(ucqcRequiredFields);
		ExtentReport.logPass("PASS", "setupScript");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	}
    
    
  }

  @AfterClass
  public static void teardownScript() {
	  try {
			driver.switchTo().window(firstHandle);
		} catch (Exception e) {
			
			
		}
	    ExtentReport.report.flush();
  }

 @Test
  public void test01VerifyHelpLinkForCopyToQuickRvuDialog() throws Throwable {
    try {
      doClick(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
      waitForAjaxExtJs();
      assertHelpLink(getWebElement("//*[contains(@onclick, 'csucqccpfd.htm')]"),"Copy To Quick RVUs",printout);
      doClickButton("Cancel & Close");
      ExtentReport.logPass("PASS", "test01VerifyHelpLinkForCopyToQuickRvuDialog");
    } catch (Exception|AssertionError e) {
    ExtentReport.logFail("FAIL", "test01VerifyHelpLinkForCopyToQuickRvuDialog", driver, e);
      fail(e.getMessage());
    }
  }

 @Test
  public void test02VerifyHelpLinkForOverwriteRvuMaintenanceDialog() throws Throwable {
    try {
		doClick(ucqcMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
		waitForAjaxExtJs();
		assertHelpLink(getWebElement("//*[contains(@onclick,'csucqcovfd.htm')]"),"Overwrite RVU Maintenance",printout);
		doClickButton("Cancel & Close");
		ExtentReport.logPass("PASS", "test02VerifyHelpLinkForOverwriteRvuMaintenanceDialog");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02VerifyHelpLinkForOverwriteRvuMaintenanceDialog", driver, e);
	      fail(e.getMessage());
		
	}
    
    
    
  }

  //NOTE: This test is also in ADS-1113.
 @Test
  public void test03VerifyHelpLinkForColumnsToDisplayDialog() throws Throwable {
    try {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		
		//Venkat  added scrollIntoView in zoom in chrome browser and wait condition 13.09.2022
//		Thread.sleep(2000);
//	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", ucqcMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
//	    Thread.sleep(2000);
//		
//		
//		
//		
//		waitForSpinnerToEnd();
//		waitForElementToBeVisible(ucqcMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
		
		ucqcMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll().click();
		ucqcMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect().click();
	
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		assertHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'selectorfd.htm')]")),"Selector", printout);
		//Shilpa added jscript update for 11.2 on 11.22.2023
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", ucqcMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
	    Thread.sleep(2000);
		ucqcMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel().click();
		 driver.manage().window().maximize();
			Thread.sleep(1000);
		 
		ExtentReport.logPass("PASS", "test03VerifyHelpLinkForColumnsToDisplayDialog");
	
	} catch (Exception|AssertionError e) {
		 driver.manage().window().maximize();
		ExtentReport.logFail("FAIL", "test03VerifyHelpLinkForColumnsToDisplayDialog", driver, e);
	      fail(e.getMessage());
	}
    
   
  }

  
 @Test
  public void test04VerifyOnlineHelpPagesForUcqcSection() throws Throwable {
    //Thread.sleep(500);
    try {
		waitForAjaxExtJs();
		firstHandle = webSwitchToNewWindow(ucqcMap.getUnitCostQuickCalculationPageHelpLink(),printout);
		assertHelpPageHeader("Unit Cost Quick Calculation", printout);
		ExtentReport.logPass("PASS", "test04VerifyOnlineHelpPagesForUcqcSection");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04VerifyOnlineHelpPagesForUcqcSection", driver, e);
	      fail(e.getMessage());
	}
    
    
    
    
    
    
  }

 @Test
  public void test07aVerifyOnlineHelpPagesForUcqcSectionProceduresDisplayAndUpdateValues()throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Costing")));
		driverDelay(2000);//venkat adding delay 27.09.2022
		
		doClick(getWebElement(getHelpTocXpath("Procedures")));
		driverDelay();
		doClick(getWebElement(getHelpTocXpath("Unit Cost Quick Calculation")));
		driverWait();
		doClick(getWebElement(getHelpTocXpath("Display and Update Values")));
		assertHelpPageHeader("Display and Update Values on the Unit Cost Quick Calculation Screen",printout);
		ExtentReport.logPass("PASS", "test07aVerifyOnlineHelpPagesForUcqcSectionProceduresDisplayAndUpdateValues");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07aVerifyOnlineHelpPagesForUcqcSectionProceduresDisplayAndUpdateValues", driver, e);
	      fail(e.getMessage());
	}
    
    
    
    
  }

 @Test
  public void test07bVerifyOnlineHelpPagesForUcqcSectionProceduresCopyDataToQuickRvus() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Copy Data to Quick RVUs")));
		assertHelpPageHeader("Copy Data to Quick RVUs",printout);
		ExtentReport.logPass("PASS", "test07bVerifyOnlineHelpPagesForUcqcSectionProceduresCopyDataToQuickRvus");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07bVerifyOnlineHelpPagesForUcqcSectionProceduresCopyDataToQuickRvus", driver, e);
	      fail(e.getMessage());
	}
    
    
    
    
  }

 @Test
  public void test07cVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Clear Quick RVUs")));
		assertHelpPageHeader("Clear Quick RVUs",printout);
		ExtentReport.logPass("PASS", "test07cVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07cVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus", driver, e);
	      fail(e.getMessage());
		
	}
  }

 @Test
  public void test07dVerifyOnlineHelpPagesForUcqcSectionProceduresCalculateQuickCosts() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Calculate Quick Costs")));
		assertHelpPageHeader("Calculate Quick Costs",printout);
		ExtentReport.logPass("PASS", "test07dVerifyOnlineHelpPagesForUcqcSectionProceduresCalculateQuickCosts");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07dVerifyOnlineHelpPagesForUcqcSectionProceduresCalculateQuickCosts", driver, e);
	      fail(e.getMessage());
	}
    
    
    
  }

@Test
  public void test07eVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Calculate Quick Costs")));
		assertHelpPageHeader("Calculate Quick Costs",printout);
		ExtentReport.logPass("PASS", "test07eVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07eVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus", driver, e);
	      fail(e.getMessage());
		
	}
  
  
  
  
  }

 @Test
  public void test07fVerifyOnlineHelpPagesForUcqcSectionProceduresOverwriteRvusWithDataFromQuickRvus() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Overwrite RVUs with Data from Quick RVUs")));
		assertHelpPageHeader("Overwrite RVUs with Data from Quick RVUs",printout);
		ExtentReport.logPass("PASS", "test07fVerifyOnlineHelpPagesForUcqcSectionProceduresOverwriteRvusWithDataFromQuickRvus");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07fVerifyOnlineHelpPagesForUcqcSectionProceduresOverwriteRvusWithDataFromQuickRvus", driver, e);
	      fail(e.getMessage());
		
	}
    
    
  }

 @Test
  public void test08aVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsUcqc()throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
		doClick(getWebElement(getHelpTocXpath("Field Descriptions")));
		driverDelay(2000);
//		doClick(getWebElement("//li[28]/div/span/a[text()='Unit Cost Quick Calculation']"));
		//Shilpa:Xpath Update: 11.2 :18.03.2025
		doClick(getWebElement("//a[text()='Field Descriptions']//following::ul[@class='tree inner']//following-sibling::li/div/span/a[text()='Unit Cost Quick Calculation']"));
		driverDelay(3000);
		doClick(getWebElement("//a[text()='Field Descriptions']//following::ul[@class='tree inner']//following-sibling::li/div/span/a[text()='Unit Cost Quick Calculation']//following::li/div/span/a[text()='Unit Cost Quick Calculation']"));
		driverDelay(2000);
		assertHelpPageHeader("Unit Cost Quick Calculation",printout);
		ExtentReport.logPass("PASS", "test08aVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsUcqc");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test08aVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsUcqc", driver, e);
	      fail(e.getMessage());
	}
  }

 @Test
  public void test08bVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsCopyToQuickRvus() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
//		doClick(getWebElement("//li[28]/ul/li[2]/div/span/a[text()='Copy To Quick RVUs']"));
		//Shilpa:Xpath update: 11.2 :18.03.2025
				doClick(getWebElement("//a[text()='Unit Cost Quick Calculation']//following::ul[@class='tree inner']/li/div/span/a[text()='Copy To Quick RVUs']"));
		assertHelpPageHeader("Copy To Quick RVUs",printout);
		ExtentReport.logPass("PASS", "test08bVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsCopyToQuickRvus");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test08bVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsCopyToQuickRvus", driver, e);
	      fail(e.getMessage());
	}
    
    
    
  }

 @Test
  public void test08cVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsOverwriteRvuMaintenance() throws Throwable {
	  
    try {
		driver.switchTo().parentFrame();
//		doClick(getWebElement("//li[28]/ul/li[3]/div/span/a[text()='Overwrite RVU Maintenance']"));
		//Shilpa:Xpath update: 11.2 :18.03.2025
		doClick(getWebElement("//a[text()='Unit Cost Quick Calculation']//following::ul[@class='tree inner']/li/div/span/a[text()='Overwrite RVU Maintenance']"));
		assertHelpPageHeader("Overwrite RVU Maintenance",printout);
		ExtentReport.logPass("PASS", "test08cVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsOverwriteRvuMaintenance");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test08cVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsOverwriteRvuMaintenance", driver, e);
	      fail(e.getMessage());
	}
  }

  public String getHelpTocXpath(String sectionName) {
    return "//*[@id='toc']/descendant::a[text()='" + sectionName + "']";
  }

  
  
	
	 
  
  
  
  
  
  
  
  
  
  
  
  
}
