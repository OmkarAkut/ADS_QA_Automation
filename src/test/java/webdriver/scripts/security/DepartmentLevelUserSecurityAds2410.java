package webdriver.scripts.security;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentLevelUserSecurityAds2410 extends LoginStatic {

  static ModelLibraryMap modelMap;
  String[] expectedDepts1 = {"*DEV123", "*TOPFOLDER", "DEPT2"};

  /** Automates ADS-2410 - Department Level Security. */
  @BeforeClass
  public static void setupScript() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("TEST CLASS: " + DepartmentLevelUserSecurityAds2410.class.getSimpleName());
    loginStaticUser(Users.RestrictedEntityAndDept1);
  }

  @Test
  public void test01AssertOnlyAllowedDepartmentsDisplayOnDepartmentMastersPage()
          throws InterruptedException {
    goToPage("Maintain Data");
    doMaintainDataPageSelectAtoZOption("Department Masters");
    waitForSpinnerToEnd();
    driverWait();
    tableDoubleClickCellFirstColumn("0ALLTEST");
    assertDepartmentCodesTableSize(0);
  }

  @Test
  public void test02AssertOnlyAllowedDepartmentsAreIncludedInFilterResultsStatement() {
    try {
      assertElementIsDisabled(
          driver.findElement(By.xpath(
                  "//div[2]/div/div[1]/div/div/div/div[2]/div/div/div[6]/em/button/span[text()='Filter']")),
          printout
      );
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClick(getWebElement("//button/span[text()='Cancel & Close']"));
      waitForSpinnerToEnd();
    }
  }

  @Test
  public void test03AssertOnlyAllowedDepartmentsDisplayOnDepartmentMastersPage() throws InterruptedException {
    selectAtoZOption("Department Masters");
    waitForSpinnerToEnd();
    driverDelay(2000);
    tableDoubleClickCellFirstColumn("0APR17DEPTMAST");
    try {
      driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
      driverDelay();
    } catch (Exception ee) {ee.getMessage();}
    List<String> list = javaMakeListOfStringsFromElementOptions(
            "*DEV123",
            "tr",
            5
    );
    assertEquals(3, list.size());
    List<String> expectedStrings = Arrays.asList(expectedDepts1);
    assertListOfStringsContainsExpectedStrings(list, expectedStrings);
  }

  @Test
  public void test04AssertOnlyAllowedDepartmentsAreIncludedInFilterResultsStatement() throws InterruptedException {
    try {
      assertFilterResultsStatement("3/3");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClick(getWebElement("//button/span[text()='Cancel & Close']"));
      waitForSpinnerToEnd();
    }
  }

  @Test
  public void test05aAssert150() throws InterruptedException {
    goToPage("Maintain Data");
    doMaintainDataPageSelectAtoZOption("Department Masters");
    waitForSpinnerToEnd();
    driverWait();
    tableDoubleClickCellFirstColumn("150");
    try {
      driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
      driverDelay();
    } catch (Exception ee) {ee.getMessage();}
    List<String> list = javaMakeListOfStringsFromElementOptions(
            "*03272014",
            "tr",
            5
    );
    assertEquals(8, list.size());
  }

  @Test
  public void test05bVerify150FilterValues() throws Exception {
    try {
      assertFilterResultsStatement("8/8");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClick(getWebElement("//button/span[text()='Cancel & Close']"));
    }
  }

  @Test
  public void test06aAssert1833DEPT() throws InterruptedException {
    goToPage("Maintain Data");
    doMaintainDataPageSelectAtoZOption("Department Masters");
    waitForSpinnerToEnd();
    driverWait();
    tableDoubleClickCellFirstColumn("1833DEPT");
    try {
      driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
      driverDelay();
    } catch (Exception ee) {ee.getMessage();}
    List<String> list = javaMakeListOfStringsFromElementOptions(
            "*PATCARE",
            "tr",
            5
    );
    assertEquals(1, list.size());
  }

  @Test
  public void test06bVerify1833DEPTFilterValues() throws Exception {
    try {
      assertFilterResultsStatement("1/1");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClick(getWebElement("//button/span[text()='Cancel & Close']"));
    }
  }

  @Test
  public void test07aAssertDM() throws InterruptedException {
    goToPage("Maintain Data");
    doMaintainDataPageSelectAtoZOption("Department Masters");
    waitForSpinnerToEnd();
    driverWait();
    tableDoubleClickCellFirstColumn("DM");
    try {
      driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
      driverDelay();
    } catch (Exception ee) {ee.getMessage();}
    List<String> list = javaMakeListOfStringsFromElementOptions(
            "*0001GRP",
            "tr",
            5
    );
    assertEquals(65, list.size());
  }

  @Test
  public void test07bVerifyDMFilterValues() {
    try {
      assertFilterResultsStatement("65/65");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClick(getWebElement("//button/span[text()='Cancel & Close']"));
    }
  }

  @Test
  public void test10AssertDepartmentHierarchiesAreAccurate() throws InterruptedException {
    goToPage("Maintain Data");
    doMaintainDataPageSelectAtoZOption("Department Hierarchies");
    waitForSpinnerToEnd();
    driverWait();
    tableDoubleClickCellFirstColumn("Marina Department Hierarchy");
    driverDelay();
    waitForSpinnerToEnd();
    String resultsStatement
            = getWebElement("//label[contains(text(),'Departments / Department Groups') and contains(text(),'of')]")
            .getText()
    ;
    assertThat(resultsStatement, containsString("39 of 65"));
  }

  @Test
  public void test11AssertPatCareFolderContainsExpectedNumberOfCodes() {
    try {
      doClickTreeItem("*PATCARE PATCARE");
      waitForSpinnerToEnd();
      assertTrue(getWebElement("//label[contains(text(),'2 Codes In PATCARE PATCARE')]").isDisplayed());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
    }
  }

  public void selectAtoZOption(String optionName) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//div[contains(@class, 'left_atoz')]/div[text()='" + optionName + "']")).click();
    waitForSpinnerToEnd();
  }

  public void assertDepartmentCodesTableSize(int expectedNumberOfRows) {
    int tableSize = tableGetTableRows(getCodesTable("Department Codes"), "tr").size();
    assertThat(tableSize, equalTo(expectedNumberOfRows));
  }

  private WebElement getCodesTable(String tableHeading) {
    return driver.findElement(By.xpath(
            "//div[text()='"+tableHeading+"']/ancestor::div[contains(@class,'x-panel commonTBar')]"
                    + "/following-sibling::div/descendant::table/tbody"));
  }

  private void waitLoop(WebElement element) throws InterruptedException {
    boolean loop = true;
    int counter = 0;
    while (loop) {
      try {
        assertTrue(element.isDisplayed());
        loop = false;
      } catch (Throwable e) {
        if (counter < 30) {
          Thread.sleep(1000);
          counter++;
          System.out.println("Count " + counter);
        } else {
          fail("ELEMENT NOT FOUND");
        }
      }
    }
  }

}
