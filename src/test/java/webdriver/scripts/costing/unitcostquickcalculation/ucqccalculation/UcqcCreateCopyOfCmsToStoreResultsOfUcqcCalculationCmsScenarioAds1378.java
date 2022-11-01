package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExtentReport.ExtentReport;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCreateCopyOfCmsToStoreResultsOfUcqcCalculationCmsScenarioAds1378
        extends UnitCostQuickCalculationHelperStatic {

  //THIS SCRIPT NEEDS TO BE UPDATED TO RUN PROPERLY - see description below.

  private static CostingMap costingMap;
  static final String[] requiredFields = {
    "QA Cost Model",
    "ADS-1378 In Total1",
    "150 Marina Medical Center",
   //"3045  PAIN MANAGEMENT UNIT",
    "3045",//venkat update value 14.09.2022
    "Apr 2004 to Mar 2005"};
  static final String[] requiredFieldsForNewUcqcCalc = {
    "QA Cost Model",
    "ADS-1378 In Total1_UCQC",
    "150 Marina Medical Center",
 // "3045  PAIN MANAGEMENT UNIT",
    "3045",//venkat update value 14.09.2022
     "Apr 2004 to Mar 2005"};
  private static String status;

  /**Test ticket ADS-1378.  Dev Story ADS-609.  The purpose of this test is to make a copy of a
   * cost model and to verify that the copy has exactly the same values as the original.  The
   * original is created/updated on the ucqc page and the checks are on the ucqc page and the cost
   * model page.  If the original is "name" the copied version
   * will automatically be given the - name "name_ucqc". If the copy is copied, the name will be
   * "name_ucqc_ucqc".  If the name of the original "name" model is updated,
   * "name_ucqc" will be updated as well (? - need to confirm this).
 * @throws Throwable */
  
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("UcqcCreateCopyOfCmsToStoreResultsOfUcqcCalculationCmsScenarioAds1378","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "UcqcCreateCopyOfCmsToStoreResultsOfUcqcCalculationCmsScenarioAds1378");
	  try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: "+ UcqcCreateCopyOfCmsToStoreResultsOfUcqcCalculationCmsScenarioAds1378.class.getSimpleName());
		loginUser(Users.ApplicationAdministrator1);
		goToPage("Unit Cost Quick Calculation");
		ucqcDisplayChargeCodeGrid(requiredFields);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	}
  }

//  //@Ignore
//  @Test
  public void test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds() {
    try {
      final String[][] pairs = {
        {"3260148", "0.000000"},
        {"3260155", "2.00000"},
        {"3260163", "999.000000"},
        {"3260171", "9,999.00000"},
        {"3260189", "99,999.00000"},
        {"3260197", "999,999.00000"},
        {"3260213", "9,999,999.00000"},
        {"3260221", "99,999,999.000000"},
        {"3260239", "999,999,999.000000"},
        {"3260254", "1,234,567,890.123456"},
      };
      //populate Salaries & Wages Quick RVUs column cells with above values
      for (String[] pair : pairs) {
        try {
          String header = "Quick Salaries and Wages RVU";
          ucqcUpdateGridCellValue(pair[0], header, pair[1], printout);
        } catch (Throwable e) {
          fail(e.getMessage());
        }
      }
      doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
      driverDelay(5000);
      waitForSpinnerToEnd();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

//  //@Ignore
//  @Test
  public void test03GoToCalculationStatusPageAndAssertCalculationCompleted() {
    try {
      goToPage("Calculation Status");
      waitForAjaxExtJs();
      waitForDisplayedSpinnerToEnd();
        status = getCalculationStatusMyStatusFirstRow();
      assertThat(status, not(containsString("Failed")));
      waitForFirstRowCalculationBarToReach100Percent();
      status = getCalculationStatusMyStatusFirstRow();
      assertThat(status, containsString("Completed"));
//      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
//              ""
//      );
//      deleteMyCalculationStatusFirstRow();
//      doClosePageOnLowerBar("Unit Cost...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04CostModelCalculationScenarioPageVerifyNameField() throws Throwable {
    try {
    	
      goToPage("Costing Models");
      waitForSpinnerToEnd();
      
      //Venkata Added wait 06-09-2022
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40,0));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='searchText']")));
	
      doSearchForModel("QA Cost Model");
      waitForJsReadyState();
      tableDoubleClickCellFirstColumn("QA Cost Model");
      waitForJsReadyState();
   // doClickTreeItem("Assign Unit Costs");
      doClickTreeItem("CM Test");// Venkat added text 06-09-2022
      doClickTreeItem("Cost Scnenarios");// Venkat added text 06-09-2022
      driverDelay(5000);
      doClickTreeItemWithCheckbox("Cost Model Calculation Scenarios");
      driverDelay(5000);
      doubleClickTableNameColumn("ADS-1378 In Total1_UCQC");
      ExtentReport.logPass("PASS", "test04CostModelCalculationScenarioPageVerifyNameField");
    } catch (Exception |AssertionError e) {
      ExtentReport.logFail("FAIL","test04CostModelCalculationScenarioPageVerifyNameField", driver,e);
      fail(e.getMessage());
      
    }
  }

  private void doubleClickTableNameColumn(String name) throws InterruptedException  {
	  
    
		Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')][2]/*[text()='" + name + "']"));
		act.doubleClick(element).perform();
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		
    
    
    
  }

 @Test
  public void test05CostModelCalculationScenarioPageVerifyGlDataScenarioValue()throws Throwable {
    driverDelay(5000);
    try {
   
    doClick("//*[@name='gLDataDescription']/parent::td/following-sibling::td[contains(@class,'trigger')]");
    assertThatDropdownSelectedValue(javaGetListContainerElementFromFirstOptionText("&23q2"), "DM MHFY05 Reclass TB");
    ExtentReport.logPass("PASS", "test05CostModelCalculationScenarioPageVerifyGlDataScenarioValue");
   	} catch (Exception|AssertionError e) {
   		 ExtentReport.logFail("FAIL","test05CostModelCalculationScenarioPageVerifyGlDataScenarioValue", driver,e);
   		fail(e.getMessage());
   		 
   	}
  
  }

  @Test
  public void test06CostModelCalculationScenarioPageVerifyActivityVolumeDataScenarioValue()
          throws Throwable {
	  
    try {
		doClick("//*[@name='actStatCalcCode']/parent::td/following-sibling::td[contains(@class,'trigger')]");
		assertThatDropdownSelectedValue(
		        javaGetListContainerElementFromFirstOptionText("0TBACTVOLCALC"),"DM2TBMHFY05VOL");
		 ExtentReport.logPass("PASS", "test06CostModelCalculationScenarioPageVerifyActivityVolumeDataScenarioValue");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test06CostModelCalculationScenarioPageVerifyActivityVolumeDataScenarioValue", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test08CostModelCalculationScenarioPageVerifyVariabilityMasterValue()
          throws Throwable {
	  try {
    doClick("//*[@name='variabilityMasterId']/parent::td/following-sibling::td[contains(@class,'trigger')]");
    assertThatDropdownSelectedValue(
            javaGetListContainerElementFromAnyOptionText("ASESC2060 CC Var Master"),
            "ASESC2060 CC Var Master"
    );
    
    ExtentReport.logPass("PASS", "test08CostModelCalculationScenarioPageVerifyVariabilityMasterValue");
   	} catch (Exception|AssertionError e) {
   		ExtentReport.logFail("FAIL","test08CostModelCalculationScenarioPageVerifyVariabilityMasterValue", driver,e);
   		fail(e.getMessage());
   	}
    
  }

  @Test
  public void test09CostModelCalculationScenarioPageVerifyEntity() throws Throwable {
	  
	  try {
    String entity = "150 Marina Medical Center";
    List<String> entities = javaMakeListOfStringsFromOptionTextAndTagName(entity,"li");
    assertListOfStringsContainsExpectedString(entities, entity);
    
    ExtentReport.logPass("PASS", "test09CostModelCalculationScenarioPageVerifyEntity");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL","test09CostModelCalculationScenarioPageVerifyEntity", driver,e);
 		fail(e.getMessage());
 	}
    
    
  }

  @Test
  public void test10CostModelCalculationScenarioPageVerifyDepartment() throws Throwable {
	  try {
    String department = "3045 PAIN MANAGEMENT UNIT";
    List<String> departments = javaMakeListOfStringsFromOptionTextAndTagName(department,"li");
    assertListOfStringsContainsExpectedString(departments, department);
    ExtentReport.logPass("PASS", "test10CostModelCalculationScenarioPageVerifyDepartment");
	 	} catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test10CostModelCalculationScenarioPageVerifyDepartment", driver,e);
	 		fail(e.getMessage());
	 	}
    
  }

  @Test
  public void test11CostModelCalculationScenarioPageVerifyPriceList()throws Throwable {
	  try {
    String expectedValue = "150 Marina Medical Center";
    doClick("//*[@name='priceList']/parent::td/following-sibling::td[contains(@class,'trigger')]");
    assertThatDropdownSelectedValue(javaGetListContainerElementFromAnyOptionText("0TB  Test"),expectedValue);
//    assertThatDropdownSelectedValue(javaGetListContainerElementFromAnyOptionText("0TB  Testsd"),expectedValue);//venkat updated text data 22.09.2022
    ExtentReport.logPass("PASS", "test11CostModelCalculationScenarioPageVerifyPriceList");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test11CostModelCalculationScenarioPageVerifyPriceList", driver,e);
	 		fail(e.getMessage());
	 	}
  }

  //@Ignore
  @Test
  public void test12CostModelCalculationScenarioPageVerifyStartMonth()
          throws Throwable {
	  try {
    doClick("//*[@name='savedStartMonth']/parent::td/following-sibling::td[contains(@class,'trigger')]");
    assertThatDropdownSelectedValue(
            javaGetListContainerElementFromFirstOptionText("<None>"),
            "ASESC2060 CC Var Master");
    
    ExtentReport.logPass("PASS", "test12CostModelCalculationScenarioPageVerifyStartMonth");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test12CostModelCalculationScenarioPageVerifyStartMonth", driver,e);
	 		fail(e.getMessage());
	 	}
    
  }

  //@Ignore
  @Test
  public void test13CostModelCalculationScenarioPageVerifyEndMonth()throws Throwable {
	  try {
    doClick("//*[@name='savedEndMonth']/parent::td/following-sibling::td[contains(@class,'trigger')]");
    assertThatDropdownSelectedValue(
            javaGetListContainerElementFromFirstOptionText("<None>"),
            "ASESC2060 CC Var Master");
    ExtentReport.logPass("PASS", "test13CostModelCalculationScenarioPageVerifyEndMonth");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test13CostModelCalculationScenarioPageVerifyEndMonth", driver,e);
	 		fail(e.getMessage());
	 	}

  }

 @Test
  public void test14CostModelCalculationScenarioPageVerifyCalculateInTotalRadioButtonIsSelected() throws Throwable {
	  try {
    assertThatElementIsChecked("In Total");
    ExtentReport.logPass("PASS", "test14CostModelCalculationScenarioPageVerifyCalculateInTotalRadioButtonIsSelected");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test14CostModelCalculationScenarioPageVerifyCalculateInTotalRadioButtonIsSelected", driver,e);
	 		fail(e.getMessage());
	 	}
    
  }

 @Test
  public void test15CostModelCalculationScenarioPageVerifyRvusBasedOn() throws Throwable {
	  try {
    assertThatElementIsChecked("Calculation Start Month");
    ExtentReport.logPass("PASS", "test15CostModelCalculationScenarioPageVerifyRvusBasedOn");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test15CostModelCalculationScenarioPageVerifyRvusBasedOn", driver,e);
	 		fail(e.getMessage());
	 	}
  }

  @Test
  public void test16CostModelCalculationScenarioPageVerifyWhenNoPrice() throws Throwable {
	  try {
    assertThatElementIsChecked("Use $0 as Price and Continue");
    ExtentReport.logPass("PASS", "test16CostModelCalculationScenarioPageVerifyWhenNoPrice");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test16CostModelCalculationScenarioPageVerifyWhenNoPrice", driver,e);
	 		fail(e.getMessage());
	 	}
  }

 @Test
  public void test17aGoToUcqcPageAndVerifyCostModelValuesOnLowerGridTable()
          throws Throwable {
	  try {
    //doClosePageOnLowerBar("QA Cost Model");
    goToPage("UCQC");
    ucqcDisplayChargeCodeGrid(requiredFieldsForNewUcqcCalc);
    
    ExtentReport.logPass("PASS", "test17aGoToUcqcPageAndVerifyCostModelValuesOnLowerGridTable");
	 	} 
	  catch (Exception|AssertionError e) {
	 		ExtentReport.logFail("FAIL","test17aGoToUcqcPageAndVerifyCostModelValuesOnLowerGridTable", driver,e);
	 		fail(e.getMessage());
	 	}
    
    
    
    
    
    
  }

 @Test
  public void test17bUpdateQuickEmployeeBenefitsValuesAndAssertCalculationEnds() throws Throwable{
    try {
      final String[][] pairs = {
          {"3260148", "0.000000"},
          {"3260155", "2.00000"},
          {"3260163", "999.000000"},
          {"3260171", "9,999.00000"},
          {"3260189", "99,999.00000"},
          {"3260197", "999,999.00000"},
          {"3260213", "9,999,999.00000"},
          {"3260221", "99,999,999.000000"},
          {"3260239", "999,999,999.000000"},
          {"3260254", "1,234,567,890.123456"},};
      
      //populate Salaries & Wages Quick RVUs column cells with above values
      for (String[] pair : pairs){
        try {
          String header = "Quick Employee Benefits RVU";
          ucqcUpdateGridCellValue(pair[0], header, pair[1], printout);
        } catch (Exception e) {
          
          ExtentReport.logFail("FAIL","test17bUpdateQuickEmployeeBenefitsValuesAndAssertCalculationEnds", driver,e);
          fail(e.getMessage());
        }
      }
      doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
      waitForSpinnerToEnd();
      ExtentReport.logPass("PASS", "test17bUpdateQuickEmployeeBenefitsValuesAndAssertCalculationEnds");
      
    } catch (Exception|AssertionError e) {
      
      ExtentReport.logFail("FAIL","test17bUpdateQuickEmployeeBenefitsValuesAndAssertCalculationEnds", driver,e);
      fail(e.getMessage());
      
    }
  }

  @Test
  public void test18GoToCalculationStatusPageAndAssertCalculationCompleted() throws Throwable {
    try {
      goToPage("Calculation Status");
      waitForAjaxExtJs();
      waitForDisplayedSpinnerToEnd();
      status = getCalculationStatusMyStatusFirstRow();
      Thread.sleep(500);
      assertThat(status, not(containsString("Failed")));
      waitForFirstRowCalculationBarToReach100Percent();
      status = getCalculationStatusMyStatusFirstRow();
      System.out.println(status);
      assertThat(status, containsString("Completed"));
      
//      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
//              ""
//      );
//      deleteMyCalculationStatusFirstRow();
//      doClosePageOnLowerBar("Unit Cost...");
      
      ExtentReport.logPass("PASS", "test18GoToCalculationStatusPageAndAssertCalculationCompleted");
    } catch (Exception|AssertionError e) {
      
      ExtentReport.logFail("FAIL","test18GoToCalculationStatusPageAndAssertCalculationCompleted", driver,e);
      fail(e.getMessage());
      
    }
  }

  public void assertThatDropdownSelectedValue(WebElement elementMenuList, String expectedValue) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    WebElement classificationList = elementMenuList;
    List<WebElement> classificationListing = classificationList.findElements(By.tagName("li"));
    for (WebElement item : classificationListing) {
      String clss = item.getAttribute("class");
   //   if (clss.contains("selected")) {
    	 if (clss.contains("required")) {// venkat update required selected from 07-09-2022
        MatcherAssert.assertThat(item.getText(), equalTo(expectedValue));
        System.out.println("Selected option = " + item.getText());
        break;
      }
    }
  }
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
  
  

}
