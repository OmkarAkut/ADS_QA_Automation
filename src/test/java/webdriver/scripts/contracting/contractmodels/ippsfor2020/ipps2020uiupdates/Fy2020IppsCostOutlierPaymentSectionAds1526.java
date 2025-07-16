package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.scripts.contracting.contractmodels.ContractModelsHelper;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsCostOutlierPaymentSectionAds1526 extends ContractModelsHelper {

  private static final String contractModel = "AFT IPPS 2020";
  private static final String serviceModel = "OPPS 2019";
  private static ModelLibraryMap modelMap;

  /** Automates test ticket ADS-1526. Dev Story ADS-1320 - Cost Outlier Payment section.
   *  Last Update: 8-7-19.
   **/
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("Test Class: " + Fy2020IppsCostOutlierPaymentSectionAds1526.class.getSimpleName());
    loginUser(Users.ContractAnalyst1);
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Cost Outlier Payment");
  }

  @Test
  public void test00AssertCostOutlierPaymentSectionFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    //form fields
    assertElementIsDisplayedWithXpath("//label[text()='Operating Ratio of Cost/Charge']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital Ratio of Cost/Charge']");
    assertElementIsDisplayedWithXpath("//label[text()='Non-Burn Marginal Cost Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Fixed Loss Threshold']");
    assertElementIsDisplayedWithXpath("//label[text()='Threshold Labor Portion']");
    assertElementIsDisplayedWithXpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]");
    //buttons
    assertElementIsDisplayedWithXpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::button/span[text()='Select']");
    //Service section
    assertElementIsDisplayedWithXpath("//span[text()='Cost Outlier Payment']/ancestor::div[contains(@class, 'x-panel-header')]/following-sibling::div/descendant::span[text()='Service'] ");
  }

  @Test
  public void test01AssertCostOutlierPaymentSectionDefaultFormFieldsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    WebElement[] formFields = {
            driver.findElement(By.name("operCostChargeRatio")),
            driver.findElement(By.name("capitalCostChargeRatio")),
            driver.findElement(By.name("paymentPercentage")),
            driver.findElement(By.name("fixedLossThreshold")),
            driver.findElement(By.name("laborPortion")),
    };
    for (WebElement element : formFields) {
      assertTrue(element.isEnabled());
    }
  }

  @Test
  public void test11aAssertOperatingRatioOfCostChargeFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertEnabled(modelMap.getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge());
  }

  @Test
  public void test11bAssertOperatingRatioOfCostChargeFieldOnlyAllowsIntegerValuesBetweenNegativeAndPositive999() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"999.9999", "-999.9999","0", "1"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge(), validValues);
  }

  @Test
  public void test11cAssertOperatingRatioOfCostChargeFieldValuesAreRestrictedTo4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] invalidValues = {"1.12345"};
    assertInvalidFieldValues(modelMap.getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge(), invalidValues);
  }

  @Test
  public void test11dAssertOperatingRatioOfCostChargeFieldValuesAllows4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String testValue = "1.9999";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(modelMap.getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge(), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,4, printout);
  }

  @Test
  public void test12aAssertCapitalRatioOfCostChargeFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertEnabled(modelMap.getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge());
  }

  @Test
  public void test12bAssertCapitalRatioOfCostChargeFieldOnlyAllowsIntegerValuesBetweenZeroAnd999() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"999.9999", "-999.9999","0", "1"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge(), validValues);
  }

  @Test
  public void test12dAssertCapitalRatioOfCostChargeFieldAllowsValuesWith4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"1.1234"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge(), validValues);
  }

  @Test
  public void test12cAssertCapitalRatioOfCostChargeFieldValuesAreRestrictedTo4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] invalidValues = {"1.12345"};  //"999.99999"
    assertInvalidFieldValues(modelMap.getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge(), invalidValues);
  }

  @Test
  public void test13aAssertNonBurnMarginalCostFactorFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertEnabled(modelMap.getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor());
  }

  @Test
  public void test13bAssertNonBurnMarginalCostFactorFieldOnlyAllowsIntegerValuesBetweenNegativeAndPositive999() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"999.9999", "-999.9999","0", "1"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor(), validValues);
  }

  @Test
  public void test13cAssertNonBurnMarginalCostFactorFieldAllowsValuesWith4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"1.9999"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor(), validValues);
  }

  @Test
  public void test13dAssertNonBurnMarginalCostFactorFieldValuesAreRestrictedTo4DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] invalidValues = {"1.12345"};
    assertInvalidFieldValues(modelMap.getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor(), invalidValues);
  }

  @Test
  public void test14aAssertFixedLossThresholdFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertEnabled(modelMap.getContractModelsCostOutlierPaymentFixedLossThreshold());
  }

  @Test
  public void test14bAssertFixedLossThresholdFieldOnlyAllowsIntegerValuesBetweenPositiveAndNegative999999() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"-9999999.99", "9999999.99", "0", "1", "10.01"};  //max=999999.99
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentFixedLossThreshold(), validValues);
  }

  @Test
  public void test14eAssertFixedLossThresholdFieldAllowsValuesTo2DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"1.99" };
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentFixedLossThreshold(), validValues);
  }

  @Test
  public void test14dAssertFixedLossThresholdFieldValuesRestrictedTo2DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] invalidValues = {"0.054", "10.551", "1.999"};
    assertInvalidFieldValues(modelMap.getContractModelsCostOutlierPaymentFixedLossThreshold(), invalidValues);
  }

  @Test
  public void test15aAssertThresholdLaborPortionFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertEnabled(modelMap.getContractModelsCostOutlierPaymentThresholdLaborPortion());
  }

  @Test
  public void test15bAssertThresholdLaborPortionFieldOnlyAllowsIntegerValuesBetweenNegativeAndPositive999() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"999", "-999","0", "-1", "1"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentThresholdLaborPortion(), validValues);
  }

  @Test
  public void test15cAssertThresholdLaborPortionFieldAllowsValuesWith0DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] validValues = {"1"};
    assertValidFieldValues(modelMap.getContractModelsCostOutlierPaymentThresholdLaborPortion(), validValues);
  }

  @Ignore
  @Test
  public void test15dAssertThresholdLaborPortionFieldDoesNotAllowValuesWithMoreThan0DecimalPlaces() throws InterruptedException {
    waitForAjaxExtJs();
    String[] invalidValues = {"1.1"};
    assertInvalidFieldValuesWithClickAwayFromField(modelMap.getContractModelsCostOutlierPaymentThresholdLaborPortion(), driver.findElement(By.name("fixedLossThreshold")), invalidValues);
  }

  @Test
  public void test16aAssertServiceSectionButtonIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    assertEnabled(driver.findElement(By.xpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::button/span[text()='Select']")));
  }

  private double calculateThresholdLaborPortionDefault() {
    //default = (National Labor Rate / (National Labor Rate + National Non-Labor Rate)) x Fixed Loss Threshold
    double laborRate = Integer.parseInt(driver.findElement(By.name("nationalOperlaborRate")).getAttribute("value"));
    double nonLaborRate = Integer.parseInt(driver.findElement(By.name("nationalOperNonLaborRate")).getAttribute("value"));
    double fixedLossThreshold = Integer.parseInt(driver.findElement(By.name("fixedLossThreshold")).getAttribute("value"));
    return (laborRate / (laborRate + nonLaborRate)) * fixedLossThreshold;
  }
}
