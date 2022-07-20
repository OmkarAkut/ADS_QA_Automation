package webdriver.scripts.costing.costingcalculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

public class CostingCalculateOverheadScenario extends CalculationHelper {

  @BeforeClass
    public static void setupScript() throws Exception {
    System.out.println("Test Class: " + CostingCalculateOverheadScenario.class.getSimpleName());
    Login.loginUser("AutomationTester1");
    goToPage("Costing Models");
  }

  @Test 
    public void testAdsLoginLogout() {
    try {
      doSearchForModel("v102 REGRESSION Overhead Model");
      tableDoubleClickCellFirstColumn("v102 REGRESSION Overhead Model");
      doClickTreeItem("Allocate Overhead");
      Thread.sleep(500);
      doClickTreeItem("Overhead Model Calculation Scenarios");
      //Omkar (19/7/2022) : value v102 REGRESSION OH Scenario is not found hence changing it to v102 REGRESSION OH Calc Scenario
      //tableDoubleClickCellFirstColumn("v102 REGRESSION OH Scenario");
      tableDoubleClickCellFirstColumn("v102 REGRESSION OH Calc Scenario");
      waitForAjaxExtJs();
      Thread.sleep(1000);
      //Omkar (19/7/2022) : The below xpath is no more valid
      //doClick(driver.findElement(By.xpath("//button/span[text()='Calculate']")));
      doClick(driver.findElement(By.xpath("(//button/span[text()='Calculate'])[2]")));
      
      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
              "Number of batches to process: 1"
      );

      //click last page icon
      driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-last-button']")).click();
      waitForSpinnerToEnd();

      deleteMyCalculationStatusFirstRow();
      doClosePageOnLowerBar("Calculation Status");
      waitForAjaxExtJs();
      Thread.sleep(500);
      doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
      doClosePageOnLowerBar("v102 REGRESSION...");
      doClosePageOnLowerBar("Model Library");



      waitForAjaxExtJs();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test //(Omkar 24/5/22 : Need to review which objectr needs to be clicked in tree in step 2.The test is failing saying that object is not clickable)
  public void testAssertReportLibrary() throws InterruptedException {
    goToPage("Report Library");
    doClickTreeItem("Costing");
    doClick(driver.findElement(By.xpath("//*[@title='Overhead Received']")));

    //enter criteria
    doClick("//div[@class='gwt-Hyperlink']/a/u[text()='* TB MH FY05 Overhead']");


  }
}
