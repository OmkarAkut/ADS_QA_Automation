package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsCapitalPaymentSectionAds1523 extends ContractModelsHelper {

  private static final String contractModel = "AFT IPPS 2020";
  private static final String serviceModel = "OPPS 2019";
  private static double capitalColaFactor = 1.4;

  /** Automates test ticket ADS-1523.  Dev Story ADS-1320 - Capital Payment section.
   * Last Update: 9-25-19.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Fy2020IppsCapitalPaymentSectionAds1523.class.getSimpleName());
    Login.loginUser("ContractAdministrator1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
//    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor"))
//            .getAttribute("value"));
//    System.out.println("colaOperatingFactor: " + colaOperatingFactor);

//    assertThatDropdownSelectedValue(
//            editModelMap.getGeneralSectionMedicareYearDropdown(),
//            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
//            currentMedicareYear
//    );
//    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
    navigateCloseGeneralSectionOpenNewSection("Capital Payment");

}

  @Test
  public void test11aAssertCapitalPaymentMethodologyFieldAndLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();

    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Capital Payment Methodology']")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }

    elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.name("capitalPaymentMethodology")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
       if (e.getMessage().contains("Unable to locate element")) {
          assertTrue("Element is not displayed", elementIsDisplayed);
       } else {
          fail("This element should not be displayed");
       }
    }
  }

  @Ignore
  @Test
  public void test11bAssertCapitalPaymentMethodologyColumnStillExistsInDbTableMedicareDrgPricingMechanism2()
          throws InterruptedException {
  }

  @Test
  public void test12aAssertCapitalGeographicAdjustmentFactorIsEnabledAndDefaultsToTheValueOne()
          throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Capital Geographic Adjustment Factor']"));
    int defaultCgaf = Integer.parseInt(driver.findElement(By.name("capitalGeographicAdjFactor"))
            .getAttribute("value"));
    assertThat(defaultCgaf, equalTo(1));
  }

  @Test
  public void test12bAssertCapitalGeographicAdjustmentFactorAllowsOnlyIntegersNegativeToPositive999()
          throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"1", "1.1", "1.12", "1.123", "1.1234", "-999.9999", "999.9999"};  // "999999.9999"
    assertValidFieldValues(driver.findElement(By.name("capitalGeographicAdjFactor")), validValues);
  }

  @Test
  public void test12cAssertCapitalGeographicAdjustmentFactorIsRestrictedTo4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] invalidValues = {"1.99994"};  //"999999.99999", "1000000"
    assertInvalidFieldValues(driver.findElement(By.name("capitalGeographicAdjFactor")), invalidValues);
  }

  @Test
  public void test13AssertCaseMixIndexAdjustmentFactorFieldAndLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath(
              "//label[text()='Case Mix Index Adjustment Factor']"))
              .isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
    elementIsDisplayed = true;
    try {
      elementIsDisplayed = elementIsDisplayed = driver.findElement(By.name("caseMixIndexAdjFactor")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
  }

  @Test
  public void test14AssertLargeUrbanAddOnAdjustmentFactorFieldAndLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Large Urban Add-On Factor']")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
    elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.name("largeUrbanAddOnFactor")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
  }

  @Test
  public void test15aAssertCapitalColaFactorFieldAndLabelIsDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = false;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Capital Cola Factor']")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(true));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
    elementIsDisplayed = false;
    try {
      elementIsDisplayed = driver.findElement(By.name("colaCapitalFactor")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(true));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
  }

  @Test
  public void test15cAssertColaFactorFieldUpdatedValue() {  //this test supports ADS-2603
    //assertElementValueAttribute(driver.findElement(By.), capitalColaFactor, printout);
    //assertTrue(driver.findElement(By.).isDisplayed());
    //assertElementTextContains(editModelMap.getGeneralSectionLocationDropdown(), location, printout);
  }


  @Test
  public void test16aAssertNationalCapitalRateFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertThatElementIsNotDisplayed(driver.findElement(By.name("nationalCapitalRate")));
  }

  @Test
  public void test16bAssertNationalCapitalRateFieldValuesFallWithinExpectedRange() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"-999999.99", "999999.99","1", "1.1", "1.12", "0.1", "0.99"};
    assertValidFieldValues(driver.findElement(By.name("nationalCapitalRate")), validValues);
  }

  @Test
  public void test16cAssertNationalCapitalRateFieldIsRestrictedTo2DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.name("nationalCapitalRate")).clear();
    driver.findElement(By.name("nationalCapitalRate")).sendKeys("1.99");
    driver.findElement(By.xpath("//label[text()='National Capital Rate']")).click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    String actualValue = driver.findElement(By.name("nationalCapitalRate")).getAttribute("value");
    assertThatValueHasRequiredDecimalPlaces(actualValue, 2, true);
  }

  @Test
  public void test16dEnterValueWith4DecimalPlacesAndAssertNationalCapitalRateFieldIsRestrictedTo2DecimalPlaces()
          throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.name("nationalCapitalRate")).clear();
    driver.findElement(By.name("nationalCapitalRate")).sendKeys("1.9944");
    driver.findElement(By.xpath("//label[text()='National Capital Rate']")).click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    String actualNatCapRate = driver.findElement(By.name("nationalCapitalRate")).getAttribute("value");
    assertThatValueHasRequiredDecimalPlaces(actualNatCapRate, 2, true);
  }

  @Test
  public void test17aAssertNationalCapitalWeightFieldAndLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='National Capital Weight']")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
    elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.name("nationalCapitalWeight")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
  }

  @Test
  public void test17bAssertFederalCapitalPercentFieldAndLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Federal Capital Percent']")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
    elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath(
              "//label[text()='Federal Capital Percent']/parent::td/following-sibling::td/div"))
              .isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
  }

  @Test
  public void test18aAssertColaGeographicAdjustedRateFieldIsReadOnly() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='COLA Geographic Adjusted Rate']"));
    String actualRateClassAttribute = driver.findElement(By.xpath(
            "//label[text()='COLA Geographic Adjusted Rate']/parent::td/following-sibling::td/div"))
            .getAttribute("class");
    assertThat(actualRateClassAttribute, containsString("display"));
  }

  @Test
  public void test18bAssertColaGeographicAdjustedRateFieldCalculation() throws InterruptedException {
    waitForAjaxExtJs();
    Thread.sleep(1000);
    String actualRate = driver.findElement(By.xpath(
            "//label[text()='COLA Geographic Adjusted Rate']/ancestor::table[contains(@class,'autocontainer')]" +
                    "/descendant::td/div"))
            .getText();  //field value
    double capGeoAdjFactor = Double.parseDouble(driver.findElement(By.name("capitalGeographicAdjFactor"))
            .getAttribute("value"));
    double colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor"))
            .getAttribute("value"));
    double natCapitalRate = Double.parseDouble(driver.findElement(By.name("nationalCapitalRate"))
            .getAttribute("value"));
    //Calculation Formula = Cap Geographic Adjustment Factor x COLA Operating Factor x National Capital Rate
    Double calculatedBlendedRate = capGeoAdjFactor * colaOperatingFactor * natCapitalRate;
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(calculatedBlendedRate);
    if (printout) {
      System.out.println("Expected COLA Geographic Adjusted Rate = " + expectedRate);
      System.out.println("Actual COLA Geographic Adjusted Rate   = " + actualRate);
    }
    assertThat(actualRate, equalTo(expectedRate));
  }

  @Test
  public void test19AssertFederalCapitalRateFieldAndLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Federal Capital Rate']")).isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
    elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath(
              "//label[text()='Federal Capital Rate']/parent::td/following-sibling::td/div"))
              .isDisplayed();
      assertThat(elementIsDisplayed, equalTo(false));
    } catch (Throwable e) {
      if (e.getMessage().contains("Unable to locate element")) {
        assertTrue("Element is not displayed", elementIsDisplayed);
      } else {
        fail("This element should not be displayed");
      }
    }
  }
}
