package webdriver.scripts.status.calculationstatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.DialogsMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculationStatusFilterByTypeAds1248 extends UcqcHelper {

  private static StatusMap status;
  private static DialogsMap filter;
  static String calcStatusFilter = "//span[text()='Filter Calculation Status']";

  /** Zephyr Test Ticket: ADS-1248 (dev story ADS-757).
   * This script tests that the correct subset is visible when applying a filter
   * on the Calculation Status page.
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    status = BuildMap.getInstance(driver, StatusMap.class);
    filter = BuildMap.getInstance(driver,DialogsMap.class);
    System.out.println("Test Class: " + CalculationStatusFilterByTypeAds1248.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Calculation Status");
    waitForAjaxExtJs();
    OpenCalculationStatusFilterDialog();
  }

  @Test
  public void test02SelectCategoryAndAssertDefaultValues() {
    try {
      doSelectCalcStatusFilterCriteria("Field", "Type");
      assertCalcStatusFilterOptionText("operator", "Is");
      assertCalcStatusFilterOptionText("condition", "Equal To");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmEqualToValueAndAssertCriteriaMatch() {
    try {
      doSelectCalcStatusFilterCriteria("Value", "Unit Cost Quick Calculation");
      doClick(status.getStatusFilterDialogButtonAdd());
      waitForAjaxExtJs();
      assertCalcStatusFilterToMatchCriteriaMessage("Type Is Equal To Unit Cost Quick Calculation");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ApplyFilterAndAssertEqualToUcqc() {
    try {
      doClick(filter.getFilterDialogButtonApplyFilter());
      waitForAjaxExtJs();
      assertCalcStatusTypeValueIsEqual("Unit Cost Quick Calculation");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ConfirmIsNotOperationAndAssertCriteriaMatch() {
    try {
      doClick(status.getCalculationStatusPageButtonClearFilter());
      waitForAjaxExtJs();
      doClick(status.getCalculationStatusPageButtonFilter());
      waitForAjaxExtJs();
      doSelectCalcStatusFilterCriteria("Field", "Type");
      doSelectCalcStatusFilterCriteria("Operator", "Is not");
      assertCalcStatusFilterOptionText("condition", "Equal To");
      doSelectCalcStatusFilterCriteria("Value", "Unit Cost Quick Calculation");
      doClick(status.getStatusFilterDialogButtonAdd());
      waitForAjaxExtJs();
      assertCalcStatusFilterToMatchCriteriaMessage("Type Is not Equal To Unit Cost Quick Calculation");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ApplyFilterAndAssertIsNotEqualToUcqc() {
    try {
      doClickButton("Apply Filter");
      waitForAjaxExtJs();
      assertCalcStatusTypeValueIsNotEqual("Unit Cost Quick Calculation");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test07ConfirmContainsConditionAndAssertCriteriaMatch() {
    try {
      doClick(status.getCalculationStatusPageButtonFilter());
      waitForAjaxExtJs();
      waitForAjaxExtJs();
      doClickButton("Remove All");
      doSelectCalcStatusFilterCriteria("Field", "Type");
      assertCalcStatusFilterOptionText("operator", "Is");
      doSelectCalcStatusFilterCriteria("Condition", "Contains");
      doSelectCalcStatusFilterCriteria("Value", "RVU");
      doClick(status.getStatusFilterDialogButtonAdd());
      //            doClick(driver.findElement(By.xpath("//span[text()=\"Add\"]")));
      waitForAjaxExtJs();
      assertCalcStatusFilterToMatchCriteriaMessage("Type Is Contains RVU");

    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test08ApplyFilterAndAssertIsContainsUcqc() {
    try {
      doClickButton("Apply Filter");
      waitForAjaxExtJs();
      assertCalcStatusTypeValueIsContains("RVU");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test09ConfirmStartsWithConditionAndAssertCriteriaMatch() {
    try {
      doClick(status.getCalculationStatusPageButtonFilter());
      waitForAjaxExtJs();
      doClickButton("Remove All");
      waitForAjaxExtJs();
      doSelectCalcStatusFilterCriteria("Field", "Type");
      assertCalcStatusFilterOptionText("operator", "Is");
      doSelectCalcStatusFilterCriteria("Condition", "Starts With");
      doSelectCalcStatusFilterCriteria("Value", "Group");
      doClick(driver.findElement(By.xpath("//span[text()=\"Add\"]")));
      waitForAjaxExtJs();
      assertCalcStatusFilterToMatchCriteriaMessage("Type Is Starts With Group");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test10ApplyFilterAndAssertIsStartsWithUcqc() {
    try {
      doClickButton("Apply Filter");
      waitForAjaxExtJs();
      assertCalcStatusTypeValueIsStartsWith("Group");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test11ConfirmEndsWithConditionAndAssertCriteriaMatch() {
    try {
      doClick(status.getCalculationStatusPageButtonFilter());
      waitForAjaxExtJs();
      doClickButton("Remove All");
      waitForAjaxExtJs();
      doSelectCalcStatusFilterCriteria("Field", "Type");
      assertCalcStatusFilterOptionText("operator", "Is");
      doSelectCalcStatusFilterCriteria("Condition", "Ends With");
      doSelectCalcStatusFilterCriteria("Value", "Costs");
      doClick(status.getStatusFilterDialogButtonAdd());
      waitForAjaxExtJs();
      assertCalcStatusFilterToMatchCriteriaMessage("Type Is Ends With Costs");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test12ApplyFilterAndAssertIsEndsWithUcqc() {
    try {
      doClickButton("Apply Filter");
      waitForAjaxExtJs();
      assertCalcStatusTypeValueIsEndsWith("Costs");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test13ConfirmOneOfConditionAndAssertCriteriaMatch() {
    try {
      doClick(status.getCalculationStatusPageButtonFilter());
      waitForAjaxExtJs();
      doClickButton("Remove All");
      waitForAjaxExtJs();
      doSelectCalcStatusFilterCriteria("Field", "Type");
      assertCalcStatusFilterOptionText("operator", "Is");
      doSelectCalcStatusFilterCriteria("Condition", "One Of");
      doSelectOneOfValueField("RVU Calculation");
      doClick(status.getStatusFilterDialogButtonAddValue());
      waitForAjaxExtJs();
      doSelectOneOfValueField("Statistic Data Calculation");
      doClick(status.getStatusFilterDialogButtonAddValue());
      waitForAjaxExtJs();
      doClick(status.getStatusFilterDialogButtonAdd());
      waitForAjaxExtJs();
      assertCalcStatusFilterToMatchCriteriaMessage("Type Is One Of RVU Calculation, Statistic Data Calculation");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test14ApplyFilterAndAssertIsOneOfUcqc() {
    try {
      doClickButton("Apply Filter");
      waitForAjaxExtJs();
      assertCalcStatusTypeValueIsOneOf("RVU Calculation","Statistic Data Calculation");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public void assertCalcStatusFilterOptionText(String nameAttribute, String optionText) {
    String filterCriteria = driver.findElement(By.xpath("//input[@name='" + nameAttribute + "']")).getAttribute("value");
    assertEquals(filterCriteria, optionText);
  }

  public void assertCalcStatusFilterToMatchCriteriaMessage(String expectedCriteriaMessage) {
    String filterToMatchCriteria;
    filterToMatchCriteria = driver.findElement(By.xpath("//div[contains(@id,'filtergrid')]/descendant::label")).getText();
    System.out.println(filterToMatchCriteria);
    String actualCriteriaMessage = driver.findElement(By.xpath("//div[contains(@id,'filtergrid')]/descendant::td[contains(@class,'rowWhiteSpace')]/div")).getText();
    assertEquals(actualCriteriaMessage, expectedCriteriaMessage);
  }

  public void assertCalcStatusTypeValueIsEqual(String expectedTypeValue) {
    List<WebElement> typeColumnList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/td[contains(@class,'x-grid-cell-gridcolumn-')][contains(@class,'x-grid-cell-last')]/div"));
    ArrayList<String> typeListAsStrings = new ArrayList<>();
    for (WebElement typeValue : typeColumnList) {
      typeListAsStrings.add(typeValue.getText());
      typeListAsStrings.remove("");
      for (int i = 0; i < typeListAsStrings.size(); i++) {
        if (typeListAsStrings.get(i).equals(expectedTypeValue)) {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", is equal to, " + expectedTypeValue + ".");
        } else {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", is not equal to '" + expectedTypeValue + "'.");
          fail();
        }
      }
    }
  }

  public void assertCalcStatusTypeValueIsNotEqual(String expectedTypeValue) {
    List<WebElement> typeColumnList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/td[contains(@class,'x-grid-cell-gridcolumn-')][contains(@class,'x-grid-cell-last')]/div"));
    ArrayList<String> typeListAsStrings = new ArrayList<>();
    for (WebElement typeValue : typeColumnList) {
      typeListAsStrings.add(typeValue.getText());
      typeListAsStrings.remove("");
      for (int i = 0; i < typeListAsStrings.size(); i++) {
        if (typeListAsStrings.get(i).equals(expectedTypeValue)) {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", is equal to, " + expectedTypeValue + ".");
          fail();
        } else {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", is not equal to '" + expectedTypeValue + "'.");
        }
      }
    }
  }

  public void assertCalcStatusTypeValueIsContains(String containsText) {
    List<WebElement> typeColumnList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/td[contains(@class,'x-grid-cell-gridcolumn-')][contains(@class,'x-grid-cell-last')]/div"));
    ArrayList<String> typeListAsStrings = new ArrayList<>();
    for (WebElement typeValue : typeColumnList) {
      typeListAsStrings.add(typeValue.getText());
      typeListAsStrings.remove("");
      for (int i = 0; i < typeListAsStrings.size(); i++) {
        if (typeListAsStrings.get(i).contains(containsText)) {
          System.out.println("The Type Value is, " + typeListAsStrings.get(i) + ", and contains the text, " + containsText + ".");
        } else {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", does not contain the text '" + containsText + "'.");
          fail();
        }
      }
    }
  }

  public void assertCalcStatusTypeValueIsStartsWith(String startsWithText) {
    List<WebElement> typeColumnList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/td[contains(@class,'x-grid-cell-gridcolumn-')][contains(@class,'x-grid-cell-last')]/div"));
    ArrayList<String> typeListAsStrings = new ArrayList<>();
    for (WebElement typeValue : typeColumnList) {
      typeListAsStrings.add(typeValue.getText());
      typeListAsStrings.remove("");
      for (int i = 0; i < typeListAsStrings.size(); i++) {
        if (typeListAsStrings.get(i).contains(startsWithText)) {
          System.out.println("The Type Value is, " + typeListAsStrings.get(i) + ", and starts with the text, " + startsWithText + ".");
        } else {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", does not starts with the text '" + startsWithText + "'.");
          fail();
        }
      }
    }
  }

  public void assertCalcStatusTypeValueIsEndsWith(String endsWithText) {
    List<WebElement> typeColumnList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/td[contains(@class,'x-grid-cell-gridcolumn-')][contains(@class,'x-grid-cell-last')]/div"));
    ArrayList<String> typeListAsStrings = new ArrayList<>();
    for (WebElement typeValue : typeColumnList) {
      typeListAsStrings.add(typeValue.getText());
      typeListAsStrings.remove("");
      for (int i = 0; i < typeListAsStrings.size(); i++) {
        if (typeListAsStrings.get(i).contains(endsWithText)) {
          System.out.println("The Type Value is, " + typeListAsStrings.get(i) + ", and ends with the text, " + endsWithText + ".");
        } else {
          System.out.println("The Type Value, " + typeListAsStrings.get(i) + ", does not end with the text '" + endsWithText + "'.");
          fail();
        }
      }
    }
  }

  public void assertCalcStatusTypeValueIsOneOf(String oneOfText1, String oneOfText2) {
    List<WebElement> typeColumnList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/td[contains(@class,'x-grid-cell-gridcolumn-')][contains(@class,'x-grid-cell-last')]/div"));
    ArrayList<String> typeListAsStrings = new ArrayList<>();
    for (WebElement typeValue : typeColumnList) {
      typeListAsStrings.add(typeValue.getText());
      typeListAsStrings.remove("");
      for (int i = 0; i < typeListAsStrings.size(); i++) {
        if (typeListAsStrings.get(i).equals(oneOfText1) || typeListAsStrings.get(i).equals(oneOfText2)) {
          System.out.println("The Type Value is, " + typeListAsStrings.get(i) + ", and is equal to one of, " + oneOfText1 + " or " + oneOfText2 + ".");
        } else {
          System.out.println("The Type Value is, " + typeListAsStrings.get(i) + ", and is not equal to one of, " + oneOfText1 + " or " + oneOfText2 + ".");
          fail();
        }
      }
    }
  }

  private static void OpenCalculationStatusFilterDialog () {
    try {
      doClick(status.getCalculationStatusPageButtonAllStatus());
      waitForAjaxExtJs();
      doClick(status.getCalculationStatusPageButtonFilter());
      waitForAjaxExtJs();
      assertElementIsDisplayed(driver.findElement(By.xpath(calcStatusFilter)), printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
