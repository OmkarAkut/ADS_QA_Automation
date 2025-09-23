package webdriver.globalscripts.accessibilitytests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginStatic;
import webdriver.scripts.datamaintenance.datamaintenancedata.DataMaintenanceAtoZPagesData;
import webdriver.utilities.Axe;

public class DataMaintenanceAccessibilityHelper extends LoginStatic {

  private Axe ax = new Axe();

  @Rule
  public TestName name = new TestName();

  public List<String> actualAtoZPagesList(List<String> actualPageList) {
    List<WebElement> pageListElements = javaMakeListOfWebElements(
            driver.findElement(By.id("dataviewId")),
            "div"
    );
    List<String> actualPagesList = javaMakeListOfStrings(pageListElements);
    return actualPagesList;
  }

  public void assertAtoZPagesLength(List<String> actualAtoZPagesList) {
      System.out.println("Expected list length: "
              + DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon.length);
      System.out.println("Actual list length  : " + actualAtoZPagesList.size());
      assertEquals(
              Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon),
              actualAtoZPagesList
      );
  }

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

  public void navigateToPageAndRunAxeTest(List<String> pagesList) {
    String actualText;
    boolean allTestsPassed = true;
    for (String expectedText : pagesList) {
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
        ax.runAxeAccessibilityTestOfPage(driver, expectedText, this.getClass().getSimpleName());
        //assertFirstRowNumberFromResultsTable(printout);
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
