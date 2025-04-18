package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResultsStoredForSelectionAvailableAds1215 extends UcqcHelper {

  static CostingMap resultsStoredFor;
  String dept = "2130  PED ICU";

  /** Test ticket ADS-1215 (Dev Story ADS-478 and 653)
  This script tests the Results Stored For drop down.
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("ResultsStoredForSelectionAvailableAds1215","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "ResultsStoredForSelectionAvailableAds1215");
		
    try {
		resultsStoredFor = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + ResultsStoredForSelectionAvailableAds1215.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	} 
    
  }

  @Test
  public void test01ConfirmResultsStoredForDisabledOnPageLoad() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertUCQCDropdownIsDisabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test01ConfirmResultsStoredForDisabledOnPageLoad");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01ConfirmResultsStoredForDisabledOnPageLoad", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ConfirmRedAsteriskDisplays() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertAsteriskIsDisplayed("Results Stored For");
      ExtentReport.logPass("PASS", "test02ConfirmRedAsteriskDisplays");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02ConfirmRedAsteriskDisplays", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmResultsStoredForDisplaysNone() throws Throwable {
    try {
      assertDropdownPlaceholder("resultStored",printout);
      ExtentReport.logPass("PASS", "test03ConfirmResultsStoredForDisplaysNone");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test03ConfirmResultsStoredForDisplaysNone", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ConfirmResultsStoredForDisabledWhenDepartmentIsNotSelected() throws Throwable {
    try {
     doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownCostModel(),resultsStoredFor.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
     assertUCQCDropdownIsDisabled("resultStored",printout);
     doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownCostModelScenario(),resultsStoredFor.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
     assertUCQCDropdownIsDisabled("resultStored",printout);
     doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownEntity(),resultsStoredFor.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
     assertUCQCDropdownIsDisabled("resultStored",printout);
     ExtentReport.logPass("PASS", "test04ConfirmResultsStoredForDisabledWhenDepartmentIsNotSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test04ConfirmResultsStoredForDisabledWhenDepartmentIsNotSelected", driver,e);
     fail(e.getMessage());
    }
  }

  @Test
  public void test05ConfirmResultsStoredForEnabled() throws Throwable {
    try {
      selectDepartment(dept);
      assertUCQCDropdownIsEnabled("resultStored",printout);
      doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownEntity(),resultsStoredFor.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      waitForAjaxExtJs();
      assertUCQCDropdownIsDisabled("resultStored",printout);
      doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownEntity(),resultsStoredFor.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      selectDepartment(dept);
      assertUCQCDropdownIsEnabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test05ConfirmResultsStoredForEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test05ConfirmResultsStoredForEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ConfirmResultsStoredForDisplayFormat() throws Throwable {
      try {
          doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationFieldResultsStoredFor(),resultsStoredFor.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Jan 2005 to Mar 2005");
          validateResultsStoredForFormat(resultsStoredFor.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
          ExtentReport.logPass("PASS", "test06ConfirmResultsStoredForDisplayFormat");
      } catch (Exception|AssertionError e) {
    	  ExtentReport.logFail("FAIL","test06ConfirmResultsStoredForDisplayFormat", driver,e);
          fail(e.getMessage());
      }
  }

  @Test
  public void test07ConfirmResultsStoredForStartMonthAscendingOrder() throws Throwable {
      try {
          waitForAjaxExtJs();
          doClick(resultsStoredFor.getUnitCostQuickCalculationFieldResultsStoredFor());
          List<String> resultsStoredForMenuActual = getDropdownMenuItemsTextAsArrayList(resultsStoredFor.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(), printout); //get string list
          assertEquals("<None>", resultsStoredForMenuActual.get(0));  //verify first list item is <None>
          resultsStoredForMenuActual.remove(0);  //remove first list item <None> prior to sorting
          SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
          ArrayList<Date> datesList = new ArrayList<>();  //create date list
          for (String dateString : resultsStoredForMenuActual) {  //convert string list to date list
              try {
                  datesList.add(sdf.parse(dateString.substring(0,8))); //create date items from substrings
              } catch (ParseException e) {
                  e.printStackTrace();
              }
          }
          List<Date> sortedDatesList = datesList.stream().collect(Collectors.toList()); //make copy of date list to sort
          Comparator<Date> dc = Comparator.comparing((Date d) -> d);  //set comparator to compare dates
          sortedDatesList.sort(dc);  //sort date list
          if(printout){
              System.out.println("Sorted List");
              sortedDatesList.forEach(System.out::println);
          }
          assertEquals(datesList,sortedDatesList);  //compare actual date list to sorted date list
          ExtentReport.logPass("PASS", "test07ConfirmResultsStoredForStartMonthAscendingOrder");
      } catch (Exception|AssertionError e){
    	  ExtentReport.logFail("FAIL","test07ConfirmResultsStoredForStartMonthAscendingOrder", driver,e);
          fail(e.getMessage());
      }
  }

  public void validateResultsStoredForFormat(WebElement element, boolean printout) {
      String expectedResultsStoredForValue;
      String actualResultsStoredForValue = element.getAttribute("value");
      System.out.println("Actual Results Stored For Value: " + actualResultsStoredForValue);
      String[] resStoForSplit = actualResultsStoredForValue.split("\\s");
      for (int i = 0; i < resStoForSplit.length; i++){
      }
      String firstDate = resStoForSplit[0] + " " + resStoForSplit[1];
      String secondDate = resStoForSplit[3] + " " + resStoForSplit[4];

      expectedResultsStoredForValue = firstDate + " to " + secondDate;
      System.out.println("Expected Results Stored For Value: " + expectedResultsStoredForValue);

      if (firstDate.matches("[a-zA-Z]{3}\\s\\d{4}")) {
          System.out.println("The format of the first date, " + firstDate + ", is MMM YYYY.");
      } else {
          System.out.println("The format of the first date, " + firstDate + ", is not MMM YYYY.");
          fail();
      }

      if (secondDate.matches("[a-zA-Z]{3}\\s\\d{4}")) {
          System.out.println("The format of the second date, " + secondDate + ", is MMM YYYY.");
      } else {
          System.out.println("The format of the second date, " + secondDate + ", is not MMM YYYY.");
          fail();
      }

      assertEquals(actualResultsStoredForValue,expectedResultsStoredForValue);
  }

  public ArrayList<String> getDropdownMenuItemsTextAsArrayList(WebElement listElement, boolean printout) throws Exception {
      waitForAjaxExtJs();
      List<WebElement> menuList = listElement.findElements(By.tagName("li"));
      ArrayList<String> menuItemsAsStrings = new ArrayList<>();
      for(WebElement item : menuList) {
          menuItemsAsStrings.add(item.getText());
          if(printout) {
              System.out.println("Menu item: " + item.getText());
          }
      }
      if(printout) {
          System.out.println("Dropdown Menu size: " + menuItemsAsStrings.size());
      }
      return menuItemsAsStrings;
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}

