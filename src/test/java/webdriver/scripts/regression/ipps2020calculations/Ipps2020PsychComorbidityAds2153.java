package webdriver.scripts.regression.ipps2020calculations;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ipps2020PsychComorbidityAds2153 extends CalculationHelper {

  private Ipps2020Data data = new Ipps2020Data();
  private static String status;

  /** Regression: Test script for ADS-2153 - Generate Psych Combined Comorbidity Factor - 2020.
   *  Updated: 12/4/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Ipps2020PsychComorbidityAds2153.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToPage("Maintain Data");
    selectMaintainDataAtoZ("Psych Combined Comorbidity Assignments");
    openMaintainDataBatch("v101 REGRESSION Comorbidity and Code 1st");
  }

  @Test
  public void test01ClickResetAndVerifyDatabaseValuesAreNull() throws ClassNotFoundException, InterruptedException {
    waitForAjaxExtJs();
    assertEquals(
            "IPFC20WD_ICD10.txt",
            driver.findElement(By.xpath("//*[text()='Last Upload File Name:']/../following-sibling::td/div")).getText()
    );
    doClick(driver.findElement(By.xpath("//*[text()='Reset']")));
    waitForSpinnerToEnd();
    waitForFirstRowCalculationBarToReach100Percent(2000);
    deleteMyCalculationStatusFirstRow();
    getStringDataFromDatabaseAndAssertValuesAreNull(
            data.sqlQueryIpps2020PsychComorbidity,
            2
    );
    doClosePageOnLowerBar("Calculation Status");
  }

  @Test
  public void test02ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected() {
    try {
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//div/em/button/span[text()='Calculate']")));
      waitForDisplayedSpinnerToEnd();
      status = getCalculationStatusMyStatusFirstRow();
      assertThat(status, not(containsString("Failed")));
      waitForFirstRowCalculationBarToReach100Percent();
      status = getCalculationStatusMyStatusFirstRow();
      assertThat(status, containsString("Completed"));
      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
              "Name: v101 REGRESSION Comorbidity and Code 1st"
      );
      deleteMyCalculationStatusFirstRow();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2020PsychComorbidity() throws ClassNotFoundException {
    assertThat(status, containsString("Completed"));
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020PsychComorbidity,
            2,
            data.expectedValuesIpps2020PsychComorbidity
    );
  }

  public String getCalculationStatusMyStatusFirstRow() {
    String status = getWebElement("//table/tbody/tr[2]/td[10]/div").getText();
    return status;
  }

}
