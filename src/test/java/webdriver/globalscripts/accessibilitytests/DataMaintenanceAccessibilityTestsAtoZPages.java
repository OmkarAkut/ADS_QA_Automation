package webdriver.globalscripts.accessibilitytests;

import java.util.Arrays;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataMaintenanceAccessibilityTestsAtoZPages extends DataMaintenanceAccessibilityHelper {

  /**
   * Test Location: Data Maintenance > Maintain Data > A to Z tab
   * Data file: DataMaintenanceAccessibilityAtoZPagesData
   * Description: Runs axe accessibility method on A to Z pages of Data Maintenance.
   * The number of pages can be limited-expanded by changing (commenting out) the data that is used.
   */
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    System.out.println("TEST CLASS: " + DataMaintenanceAccessibilityTestsAtoZPages.class.getSimpleName());
    loginUser(Users.AppSupportUser);
    goToPage("Maintain Data");
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//span[text() = 'A - Z']")).click();
    waitForAjaxExtJs();
  }

  @Test
  public void axetest01MaintainDataxGLAccountTypes() {
    if (testEnvironment.contains("echelon") || testEnvironment.contains("appsupport")) {
      navigateToPageAndRunAxeTest(
          Arrays.asList(DataMaintenanceAccessibilityDataAtoZPages
                  .expectedMaintainDataAtoZPagesCompleteListEchelon)
      );
    } else {
//      assertPageLoads(
//              Arrays.asList(DataMaintenanceAccessibilityDataAtoZPages.expectedMaintainDataAtoZPagesCompleteListEvolve));
    }
  }

// NOTE: The method below is for reference purposes.
//  @Test
//  public void test02MaintainDataPageAssertAtoZListPagesLoadProperly() {
//    if (testEnvironment.contains("echelon")) {
//      assertPageLoads(
//          Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEchelon));
//    } else {
//      assertPageLoads(
//              Arrays.asList(DataMaintenanceAtoZPagesData.expectedMaintainDataAtoZPagesCompleteListEvolve));
//    }
//  }

}
