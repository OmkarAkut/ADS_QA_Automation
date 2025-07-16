package webdriver.scripts.datamaintenance.datamaintenancehelpers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginStatic;

public class DataMaintenanceHelper extends LoginStatic {

  public void assertTreeListSectionContainsAllItems(
          String folderName,
          String firstOptionInList,
          String[] expectedList) throws InterruptedException {
    driver.findElement(By.xpath("//div[text()='"+folderName+"']/img[contains(@class,'x-tree-expander')]")).click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    List<WebElement> contractingList = javaMakeListOfWebElements(
            driver.findElement(By.xpath("//div[text()='"+firstOptionInList+"']/img[contains(@class,'x-tree-icon x-tree-icon-leaf')]/ancestor::tbody")),
            "tr"
    );
    List<String> actualListForContractingPages = javaMakeListOfStringsFromElementText(
            contractingList,
            "class",
            "x-grid-tree-node-leaf",
            "td[contains(@class,'x-grid-cell-treecolumn')]/div"
    );
    assertEquals(Arrays.asList(expectedList), actualListForContractingPages);
  }

  public void
  assertPageLoads(List<String> testList) {
    String actualText;
    boolean allTestsPassed = true;
    for (String expectedText : testList) {
      if (expectedText.equals("User Defined Fields And Relations")) {
        continue;
      }
      try {
        driver.findElement(By.xpath("//div[text() = '" + expectedText + "']")).click();
        waitForAjaxExtJs();
        waitForSpinnerToEnd();
        if (expectedText.equals("Activity Volume Data Scenarios")
          //actualText = getElementText(driver.findElement(By.xpath("//span[contains(@id,'activityvolumedatalist')]")), false);
                || expectedText.equals("Charge Item Service Classification Schemes")
                || expectedText.equals("Consumers")
                || expectedText.equals("Encounter Service Classification Schemes")
                || expectedText.equals("Encounters")
                || expectedText.equals("GL Reclassification Masters")
                || expectedText.equals("Group Data Scenarios")
                || expectedText.equals("Labor Cost Components")
                || expectedText.equals("Labor RVU Calculation")
                || expectedText.equals("Microcosting Wage Rates")
                || expectedText.equals("RVU Maintenance")
                || expectedText.equals("Staffing Data Scenarios")
                || expectedText.equals("Statistic Data Scenarios")) {
          actualText = getElementText(driver.findElement(By.xpath("//div[contains(@class,'x-panel-header')]/span[not(contains(@id,'leftview'))]")), false);
      } else if (expectedText.equals("Populations")
                || expectedText.equals("Services")
                || expectedText.equals("Psych Combined Comorbidity Assignments")) {
          String formattedExpectedText = expectedText.toLowerCase().replaceAll("\\s+", "");
          actualText = getElementText(driver.findElement(By.xpath("//span[contains(@id,'"+formattedExpectedText+"maingrid')]")), false);
        } else {
          actualText = getElementText(driver.findElement(By.xpath("//div[contains(@class,'areaTitle')]")), false);
        }
        assertThat(actualText, CoreMatchers.equalTo(expectedText));
        assertFirstRowNumberFromResultsTable(printout);
      } catch (Throwable e) {
        System.out.println("<< FAILED >> on item: " + expectedText);
        allTestsPassed = false;
      }
    }
    if (allTestsPassed == false) {
      fail("There are test failures. Check console output.");
    }
  }

  public List<String> printTreeListSectionPages(
          String folderName,
          String firstOptionInList,
          String[] expectedList) throws InterruptedException {
    driver.findElement(By.xpath("//div[text()='"+folderName+"']/img[contains(@class,'x-tree-expander')]")).click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    List<WebElement> contractingList = javaMakeListOfWebElements(
            driver.findElement(By.xpath("//div[text()='"+firstOptionInList+"']/img[contains(@class,'x-tree-icon x-tree-icon-leaf')]/ancestor::tbody")),
            "tr"
    );
    List<String> actualListForContractingPages = javaMakeListOfStringsFromElementText(
            contractingList,
            "class",
            "x-grid-tree-node-leaf",
            "td[contains(@class,'x-grid-cell-treecolumn')]/div"
    );
    return actualListForContractingPages;
  }
}
