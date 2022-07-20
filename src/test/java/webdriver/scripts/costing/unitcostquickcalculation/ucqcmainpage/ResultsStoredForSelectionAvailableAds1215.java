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
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResultsStoredForSelectionAvailableAds1215 extends UcqcHelper {

  static CostingMap resultsStoredFor;
  String dept = "2130  PED ICU";

  /** Test ticket ADS-1215 (Dev Story ADS-478 and 653)
  This script tests the Results Stored For drop down.*/
  @BeforeClass
  public static void setupScript() throws Exception {
    resultsStoredFor = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + ResultsStoredForSelectionAvailableAds1215.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
  }

  @Test
  public void test01ConfirmResultsStoredForDisabledOnPageLoad() {
    try {
      waitForAjaxExtJs();
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ConfirmRedAsteriskDisplays() {
    try {
      waitForAjaxExtJs();
      assertAsteriskIsDisplayed("Results Stored For");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmResultsStoredForDisplaysNone() {
    try {
      assertDropdownPlaceholder("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ConfirmResultsStoredForDisabledWhenDepartmentIsNotSelected() {
    try {
     doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownCostModel(),resultsStoredFor.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
     assertUCQCDropdownIsDisabled("resultStored",printout);
     doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownCostModelScenario(),resultsStoredFor.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
     assertUCQCDropdownIsDisabled("resultStored",printout);
     doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownEntity(),resultsStoredFor.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
     assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
     fail(e.getMessage());
    }
  }

  @Test
  public void test05ConfirmResultsStoredForEnabled() {
    try {
      selectDepartment(dept);
      assertUCQCDropdownIsEnabled("resultStored",printout);
      doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownEntity(),resultsStoredFor.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      waitForAjaxExtJs();
      assertUCQCDropdownIsDisabled("resultStored",printout);
      doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationDropdownEntity(),resultsStoredFor.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      selectDepartment(dept);
      assertUCQCDropdownIsEnabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ConfirmResultsStoredForDisplayFormat() {
      try {
          doDropdownSelectUsingOptionText(resultsStoredFor.getUnitCostQuickCalculationFieldResultsStoredFor(),resultsStoredFor.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
          validateResultsStoredForFormat(resultsStoredFor.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      } catch (Throwable e) {
          fail(e.getMessage());
      }
  }

  @Test
  public void test07ConfirmResultsStoredForStartMonthAscendingOrder() {
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
      } catch (Throwable e){
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
}

