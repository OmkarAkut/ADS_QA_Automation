package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.DataMaintenanceHelper;
import webdriver.scripts.datamaintenance.datamaintenancedata.DataMaintenanceAtoZPagesData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataMaintenanceAtoZTabPageLoads extends DataMaintenanceHelper {
  //"OPPS Non-Device Trigger Combinations"
//  public static String[] expectedList = {
//          "OPPS Non-Device Trigger Combinations"
//  };

  /**
   * Test Location: Data Maintenance > Maintain Data > A to Z tab
   * Data file: DataMaintenanceAtoZPagesData
   * Description: Verifies that page names display in proper directories and
   * that individual pages load properly (in main pane).
   * There is a short and complete version.
   * Short tests the first few pages, complete tests all the pages.
   * This is changed by changing the data that is used.
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("TEST CLASS: " + DataMaintenanceAtoZTabPageLoads.class.getSimpleName());
    /*modified by Omkar on 1/6/22 as only aadmin user is available for qa3 env
    Login.loginUser("ApplicationAdministrator1");
    */
    Login.loginUser("AutomationTesterAdmin");
 // End of modification
    
    goToPage("Maintain Data");
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//span[text() = 'A - Z']")).click();
    waitForAjaxExtJs();
  }

  @Test
  public void test01MaintainDataPageAssertAtoZDisplayedListContainsAllItems() {
    List<WebElement> pageListElements = javaMakeListOfWebElements(
            driver.findElement(By.id("dataviewId")),
            "div"
    );
    List<String> actualPageList = javaMakeListOfStrings(pageListElements);
    if (testEnvironment.contains("echelon")) {
      System.out.println("Expected list length: "
              + DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon.length);
      System.out.println("Actual list length  : " + actualPageList.size());
      assertEquals(
              Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon),
              actualPageList
      );
    } else {
      System.out.println("Expected list length: "
              + DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve.length);
      System.out.println("Actual list length  : " + actualPageList.size());
      assertEquals(
              Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve),
              actualPageList
      );
    }
  }

  @Test
  public void test02MaintainDataPageAssertAtoZListPagesLoadProperly() throws InterruptedException {
    if (testEnvironment.contains("echelon")) {
      assertPageLoads(
          Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon));
    } else if (testEnvironment.contains("evolve")) {
      assertPageLoads(
              Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve));
    } else {
      assertPageLoads(
              Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteList));
              //Arrays.asList(expectedList));
    }
  }

}
