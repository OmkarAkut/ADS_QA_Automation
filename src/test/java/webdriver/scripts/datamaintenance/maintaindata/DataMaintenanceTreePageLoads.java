package webdriver.scripts.datamaintenance.maintaindata;

import java.util.Arrays;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.DataMaintenanceHelper;
import webdriver.scripts.datamaintenance.datamaintenancedata.DataMaintenanceTreePagesData;
import webdriver.users.Users;
import webdriver.utilities.Print;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataMaintenanceTreePageLoads extends DataMaintenanceHelper {

  Print printer = new Print();

  /**
   * Test Location: Data Maintenance > Maintain Data > Tree structure (left side)
   * Data file: DataMaintenanceTreePagesData
   * Description: Verifies that page names display in proper directories and
   * that individual pages load properly (in main pane).
   * There is a short and complete version.
   * Short tests the first few pages, complete tests all the pages.
   * This is changed by changing the data that is used.
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("TEST CLASS: " + DataMaintenanceTreePageLoads.class.getSimpleName());
    Login.loginUser("ApplicationAdministrator1");
    goToPage("Maintain Data");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  @Test
  public void test10MaintainDataPageAssertTreeListContainsTheFourMainDirectories() {
    String[] mainFolders = {"Contracting", "Costing", "Episode", "General"};
    for (String mainFolder : mainFolders) {
      assertThatElementIsDisplayed(
              driver.findElement(By.xpath("//div[text()='" + mainFolder + "']"
                      + "/img[contains(@class,'x-tree-icon x-tree-icon-parent ')]")));
    }
  }

  @Ignore
  @Test
  public void test20MaintainDataPageAssertTreeListContractingSectionContainsAllItems()
          throws InterruptedException {
    assertTreeListSectionContainsAllItems(
            "Contracting",
            "ASC Schemes",
            DataMaintenanceTreePagesData.expectedContractingTreeSectionPages
    );
  }

  @Ignore
  @Test
  public void test30MaintainDataPageAssertTreeListContractingSectionPagesLoad()
          throws InterruptedException {
    assertPageLoads(
        Arrays.asList(DataMaintenanceTreePagesData.expectedContractingTreeSectionPages)
    );
  }

}
