package webdriver.scripts.costing.costingmodels;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingModelsSmokeTest extends GoHelper {
  String costModel = "QA Marina";
  String[] filter = {"Cost Model Name", "Is", "Equal To", costModel};
  static GeneralElementsMap generalMap;
  static CostingMap costing;
  DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
  final static String newCellValue = Integer.toString(javaGetRandomNumber(99,true));

  /** Automates test ticket ADS-1492. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("CostingModelsSmokeTest", "webdriver.scripts.costing.costingmodels", "CostingModelsSmokeTest");
    try {
		generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
		costing = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + CostingModelsSmokeTest.class.getSimpleName());
   // Login.loginUser("CostingDepartmentManager1");
   //Shilpa 06.09.2022 updated loginUser
		Login.loginUser("CostAnalyst1");
		goToPage("costing models");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01AssertCostModelPageHeader()
          throws Throwable {
    try {
		waitForAjaxExtJs();
		assertPageInformation("Costing Model Library");
		ExtentReport.logPass("PASS", "test01AssertCostModelPageHeader");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01AssertCostModelPageHeader", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02AssertCostModelNewDialog()
          throws InterruptedException,Throwable {
	/* Modified by Omkar on 24/5/2022 as 2 elements are found with same xpath the below method will not work
    doClickButton("New");
    */
	 try {
		 //Shilpa updated xpath for 11.2 on 12.27.2023
		doClick("//div[contains(@id,'adynamicgrid')]//following::span[text()='New']");
		 
		 //End of modification
		assertElementIsDisplayedWithXpath("//div[contains(@id,'dynamicwindow')]//following::div[text()='New Cost Model']");
		doClickButton("Cancel & Close");
		ExtentReport.logPass("PASS", "test02AssertCostModelNewDialog");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssertCostModelNewDialog", driver, e);
			fail(e.getMessage());
		}
  }

  @Test
  public void test03AssertCostModelOpenTaskList()
          throws InterruptedException,Throwable {
	try {
		driverWait();
		doClickButton("Open Task List");
		/*Modified by Omkar on 23-5-22 : Change in xpath
		//assertElementIsDisplayedWithXpath("//table/tbody/tr[contains(@class, 'tree-node')]/descendant::*[text()='Cost Model Task List']");
		 assertElementIsDisplayedWithXpath(
		        "//table/tbody/tr[contains(@class, 'tree-node')]" +
		        "/following-sibling::tr/descendant::*[text()='Prepare Costing Elements']"
   
		*/
		waitForAjaxExtJs();
//driver.switchTo().frame(2);
		waitForElementToBeVisible(driver.findElement(By.xpath("//div[contains(@id,'taskfolder')]//following::span[text()='Prepare Costing Elements']")));
		//Shilpa 06.09.2022 updated xpath
		assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::span[text()='Prepare Costing Elements']");
		//assertElementIsDisplayedWithXpath("//*[contains(@class ,'x-grid-row undefined x-grid-tree-node-expanded x-grid-row-selected x-grid-row-focused')]");
   //Commented below line , now it's not showing 06.09.2022
		// assertElementIsDisplayedWithXpath("//*[contains(text(),'Prepare Costing Elements')]");
		ExtentReport.logPass("PASS", "test03AssertCostModelOpenTaskList");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03AssertCostModelOpenTaskList", driver, e);
		fail(e.getMessage());
	}
   // End of modification
  }

  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
