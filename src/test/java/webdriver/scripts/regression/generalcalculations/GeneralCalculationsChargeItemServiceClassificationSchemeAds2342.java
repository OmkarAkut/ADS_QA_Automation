package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsChargeItemServiceClassificationSchemeAds2342 extends CalculationHelper {

  static String viewLogTitleRemove = "Remove Charge Item Service Classification";
  static String viewLogTitleAssign = "Charge Item Service Classification Scheme";
  static String xRecordsProcessed = "26";
  static DataMaintenanceMap dmMap;
  final static String aTozPage = "Charge Item Service Classification Schemes";
  final static String batch = "V10.2 REGRESSION ChgItem Serv Class Sch";
  private static List<WebElement> encountersTable;
  static String encounter = "OPPS2020DCA005";
  List<String> expectedEncounters = Arrays.asList(
          "QA REGRESSION CHG>0",
          "QA REGRESSION CHG>0"
  );
  static final String recordsProcessedXpath = "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[15]/div";

  /** Regression: Test script for ADS-2341. Updated: 7-8-21. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println(
      "Test Class: " + GeneralCalculationsChargeItemServiceClassificationSchemeAds2342.class.getSimpleName()
    );
    Login.loginUser("ContractAnalyst1");
    goToPage("Maintain Data");
    selectMaintainDataAtoZ(aTozPage);
    openMaintainDataBatch(batch);
  }

  @Test
  public void test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully()
          throws InterruptedException {
    try {
      waitForPresenceOfElement("//button/span[text()='Remove']");
      doClick(driver.findElement(By.xpath("//button/span[text()='Remove']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleRemove);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
      waitForPresenceOfElement("//button/span[text()='Save & Close']");
      doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
      waitForSpinnerToEnd();
    }
  }

  @Test
  public void test03VerifyNoServicesAppearOnEncountersPage()
          throws InterruptedException {
    doMaintainDataPageSelectAtoZOption("Encounters");
    doSearchForModel(encounter);
    openMaintainDataBatch(encounter);
    waitForAjaxExtJs();
    waitForSpinnerToEnd();
    doClick(driver.findElement(By.xpath("//button/span[text()='Charges']")));
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::input");
    doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::input"));
    waitForSpinnerToEnd();
    assertTextIsDisplayed("There is no data available to display.");
    doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
  }

  @Test
  public void test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected()
          throws InterruptedException {
    try {
      doMaintainDataPageSelectAtoZOption(aTozPage);
      openMaintainDataBatch(batch);
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Assign']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      String recordsProcessedText = getWebElement(recordsProcessedXpath).getText();
      assertEquals(xRecordsProcessed, recordsProcessedText);
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleAssign);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
      waitForSpinnerToEnd();
    }
  }

  @Test
  public void test05AssertServicesCountOnEncountersPage() throws InterruptedException {
      goToPage("Maintain Data");
      doMaintainDataPageSelectAtoZOption("Encounters");
      doSearchForModel(encounter);
      openMaintainDataBatch(encounter);
      waitForAjaxExtJs();
    waitForSpinnerToEnd();
      doClick(driver.findElement(By.xpath("//button/span[text()='Charges']")));
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::input");
      doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::input"));
      waitForSpinnerToEnd();
      encountersTable = getEncountersTableRows();
      assertThat(encountersTable.size(), equalTo(2));  //expected value includes header row
  }

  @Test
  public void test06VerifyServicesNowAppearOnEncountersPage() throws InterruptedException {
    try {
      List<String> encountersTableStrings =
              javaMakeListOfStrings(encountersTable, "//td[3]/div");
      assertThat(encountersTableStrings, equalTo(expectedEncounters));
      doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Maintain Data");
      waitForLandingPageFooter();
    }
  }

  private List<WebElement> getEncountersTableRows() {
    return tableGetTableRows(
            driver.findElement(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]" +
                    "/following-sibling::div/descendant::table/tbody")),
            "tr")
    ;
  }

}

