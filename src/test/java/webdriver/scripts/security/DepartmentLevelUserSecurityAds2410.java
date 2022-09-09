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
    tableDoubleClickCellFirstColumn("112233");
    assertDepartmentCodesTableSize(0);//Shilpa 22.08.2022 , there are 3 rows inside tables earlier it was 0
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
            "*JKLJK",//Shilpa 22.08.2022 updated from *DEV123 to *JKLJK
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
            "*JKLJK",//Shilpa 22.08.2022 updated from *03272014 to *JKLJK
            "tr",
            5
    );
    assertEquals(3, list.size());//Shilpa 22.08.2022 table row is 3 now not 8
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
            "*JKLJK",//updated from *PATCARE to *JKLJK
            "tr",
            5
    );
    assertEquals(3, list.size());//Shilpa 22.08.2022 update table row to 3 from 1
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
//      List<String> list = javaMakeListOfStringsFromElementOptions(
//              "*0001GRP",//Shilpa 22.08.2022 added list inside try if Read only option shown
//              "tr",
//              5
//      );
//      assertEquals(100, list.size());//Shilpa 22.08.2022 updated from 65 to 100
    } catch (Exception ee) {}
    List<String> list = javaMakeListOfStringsFromElementOptions(
            "*JKLJK",//Shilpa 22.08.2022 updated from *0001GRP to *JKLJK
            "tr",
            5
    );
    assertEquals(3, list.size());//Shilpa 22.08.2022 updated from 65 to 3
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
    try {
    	 driver.findElement(By.xpath("//button/span[text()='Read Only']")).click();
         driverDelay();
    }catch (Exception ee) {}
    String resultsStatement
            = getWebElement("//label[contains(text(),'Departments / Department Groups') and contains(text(),'of')]")
            .getText()
    ;
    System.out.println(resultsStatement);
    assertThat(resultsStatement, containsString("7833 of 7838"));//Shilpa updated to 7833 of 7838 , now its not 39 of 65
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
//    return driver.findElement(By.xpath(
//            "(//div[text()='"+tableHeading+"']/ancestor::div[contains(@class,'x-panel commonTBar')]"
//                    + "/following-sibling::div/descendant::table/tbody"));
	  //Shilpa 22.08.2022 updated xpath
	  return driver.findElement(By.xpath("(//h1[text()='"+tableHeading+"']/ancestor::div[contains(@class,'x-panel commonTBar')]/following-sibling::div/descendant::table/tbody)[1]"));
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
