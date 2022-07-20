package webdriver.scripts.security;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityLevelUserSecurityAds2409 extends LoginStatic {

  static ModelLibraryMap modelMap;
  static String costModel = "0-MarinaCostModel";
  static String testContractModel1 = "#fz Med IPPS Testing";

  /**
   * Automates ADS-2409 - Entity Level Security.
   */
  @BeforeClass
  public static void setupScript() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("TEST CLASS: " + EntityLevelUserSecurityAds2409.class.getSimpleName());
    evolveLoginStaticUser(Users.RestrictedEntityAndDept1);
  }

  @AfterClass
  public static void teardownScript() {
    try {
      doClosePageOnLowerBar(testContractModel1);
      waitForSpinnerToEnd();
    } catch (Exception ee) {
      ee.printStackTrace();
    }
  }

  @Test
  public void test01AssertOnlyAllowedEntitiesDisplayOnMaintainDataEntitiesPage() throws InterruptedException {
    goToPage("Maintain Data");
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//span[text() = 'A - Z']")).click();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//*[@class='itemWrap' and text()='Entities'] ")).click();
    waitForSpinnerToEnd();
    driverWait();
    assertElementIsDisplayed(
            driver.findElement(
                    By.xpath("//td[contains(@class,'x-grid-cell')]/div[text()='150']")), printout)
    ;
    try {
      assertEquals(1, getTableNumberOfNumberedRows(true));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClosePageOnLowerBar("Maintain Data");
    }

  }

  @Test
  public void test02CostingCostModelsPageAssertRestrictedEntitiesOnlyDisplay() throws InterruptedException {
    goToPage("Costing Models");
    waitForSpinnerToEnd();
    driverDelay();
    waitUntilElementIsClickable(modelMap.getModelLibraryFieldSearch());
    doSearchForModel(costModel);
    List<String> list = javaMakeListOfStringsFromElementOptions(
        costModel,
        "tr",
        3
    );
    assertEquals(1, list.size());
    List<String> expectedStrings = Arrays.asList(costModel);
    assertListOfStringsContainsExpectedStrings(list, expectedStrings);
  }

  @Test
  public void test03CostingCostModelsPageAssertRestrictedEntitiesDisplay() throws InterruptedException {
    tableDoubleClickCellFirstColumn(costModel);
    waitForSpinnerToEnd();
    waitForPageTitle("0-MarinaCostModel");
    waitUntilTreeOptionIsClickable("Assign Costs to Encounters");
    doClickTreeItem("Assign Unit Costs");
    waitUntilTreeOptionIsClickable("Cost Model Scenario Results");
    doClickTreeItem("General Information");
    driverPause();
    try {
      try {
        driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
        driverDelay();
      } catch (Exception ee) {ee.getMessage();}
      waitForSpinnerToEnd();
      waitUntilElementIsClickable(driver.findElement(
              By.xpath("//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/.."))
      );
      List<WebElement> list = javaMakeListOfWebElements(
              driver.findElement(By.xpath("//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/..")),
              "li"
      );
      assertEquals(1, list.size());
      List<String> entities = javaListConvertListOfWebElementsToStrings(list, printout);
      List<String> expectedStrings = Arrays.asList("150 Marina Medical Center");
      assertListOfStringsContainsExpectedStrings(entities, expectedStrings);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
      waitForSpinnerToEnd();
      waitUntilTreeOptionIsClickable("Assign Costs to Encounters");
    }
  }

  @Test
  public void test04CostingCostModelsPageAssertRestrictedEntitiesDisplayOnCostComponentMaster() throws InterruptedException {
    waitForSpinnerToEnd();
    driverDelay();
    doClickTreeItem("Assign Unit Costs");
    doClickTreeItem("Cost Component Masters");
    try {
      driverPause();
      try {
        driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
        driverDelay();
      } catch (Exception e) {
        e.getMessage();
      }
      tableDoubleClickCellFirstColumn("Marina Health System");
      waitForAjaxExtJs();
      waitUntilElementIsClickable(driver.findElement(
              By.xpath("//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/.."))
      );
      List<WebElement> list = javaMakeListOfWebElements(
              driver.findElement(By.xpath("//div/ul/li[contains(@class,'x-boundlist-item') and text()='150 Marina Medical Center']/..")),
              "li"
      );
      assertEquals(1, list.size());
      List<String> entities = javaListConvertListOfWebElementsToStrings(list, printout);
      List<String> expectedStrings = Arrays.asList("150 Marina Medical Center");
      assertListOfStringsContainsExpectedStrings(entities, expectedStrings);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
      waitForSpinnerToEnd();
    }
  }

  @Test
  public void test05ContractModelsPageAssertRestrictedEntitiesDisplay() throws InterruptedException {
    try {
      goToPage("Contract Models");
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      waitUntilElementIsClickable(driver.findElement(By.xpath("//div[not(contains(@class,'ccBtnCls'))]/em/button/span[text()='New']")));
      driver.findElement(By.xpath("//div[not(contains(@class,'ccBtnCls'))]/em/button/span[text()='New']")).click();
      waitForSpinnerToEnd();
      driverWait();
      waitUntilElementIsVisible(driver.findElement(By.xpath("//button/span[text()='Add Providers']")));
      driver.findElement(By.xpath("//button/span[text()='Add Providers']")).click();
      waitForSpinnerToEnd();
      driverWait();
      List<String> actualOptions = javaMakeListOfStringsFromElementOptions("150  Marina Medical Center", "");
      assertEquals(1, actualOptions.size());
      List<String> expectedOptions = Arrays.asList("150  Marina Medical Center");
      assertListOfStringsContainsExpectedStrings(expectedOptions, actualOptions);
    } catch (Throwable e) {
      e.printStackTrace();
      driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
    } finally {
      try {
        driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        driver.findElement(By.xpath("//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']")).click();
        waitForAjaxExtJs();
      }
    }
  }

  @Test
  public void test06ContractModelsPageAssertRestrictedEntityIsNotDisplayed() throws InterruptedException {
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    List<String> displayedContractNames = javaMakeListOfStringsFromElementOptions(
            "#asesc2310a v86 Chg Fields", ""
    );
    List<String> notDisplayedList = Arrays.asList("*EAPG");
    assertListOfStringsDoesNotContainExpectedStrings(displayedContractNames, notDisplayedList);
  }

  @Test
  public void test07ContractModelsPageAssertDefinitionElementsProvidersTabContainsOnlyRestrictedEntity() throws InterruptedException {
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    waitUntilElementIsClickable(modelMap.getModelLibraryFieldSearch());
    doSearchForModel(testContractModel1);
    tableDoubleClickCellFirstColumn(testContractModel1);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverWait();
    try {
      driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
      driverDelay();
    } catch (Exception e) {
      e.getMessage();
    }
   // doClickTreeItem("Published Contract Task List");
    //div[2]/div[1]/div[3]/div/table/tbody/tr[3]/td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='General Information']
    doClickTreeItemUsingXpathLocator(
            "//div[2]/div[1]/div[3]/div/table/tbody/tr[3]/td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='General Information']"
    );
    waitForSpinnerToEnd();
    driverDelay();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//button/span[text()='Definition Elements']")).click();
    waitForSpinnerToEnd();
    //expand Providers accordion
    driver.findElement(By.xpath("//div[contains(@class,'x-tool')]/img[contains(@class, 'tool-expand-bottom')]")).click();
    List<String> actualOptions = javaMakeListOfStringsFromElementOptions(
            "150", ""
    );
    assertEquals(1, actualOptions.size());
    List<String> expectedOptions = Arrays.asList("150");
    assertListOfStringsContainsExpectedStrings(expectedOptions, actualOptions);
  }


  @Test
  public void test08AssertDefinitionElementsAddProvidersSelectorContainsOnlyRestrictedEntity() throws InterruptedException {
    try {
      driverDelay();
      waitForAjaxExtJs();
      waitUntilElementIsClickable(driver.findElement(By.xpath("//button/span[text()='Add Providers']")));
      driver.findElement(By.xpath("//button/span[text()='Add Providers']")).click();
      waitForAjaxExtJs();
      doClick("//label[text()='Select All']/preceding-sibling::input");
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      driverWait();
      List<String> actualOptions = javaMakeListOfStringsFromElementOptions("150 Marina Medical Center ", "");
      assertEquals(1, actualOptions.size());
      List<String> expectedOptions = Arrays.asList("150 Marina Medical Center ");
      assertListOfStringsContainsExpectedStrings(expectedOptions, actualOptions);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']")).click();
    }
  }

}
