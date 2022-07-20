package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020saveedits;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020SavePricingCostOutlierPaymentSectionAds1629 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  private final String testSection = "Cost Outlier Payment";

  /**
   * Automates test ticket ADS-1629.  Dev Story ADS-1501 and 1494.
   * User updates field values and they should remain after clicking Continue & Close.
   * Also, values should persist after clicking Save on main page.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020SavePricingCostOutlierPaymentSectionAds1629.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Cost Outlier Payment");
//    goToPage("Contract Models");
//    waitForSpinnerToEnd();
//    waitForAjaxExtJs();
//    displayPricingMethodsSectionForContractModelAndServiceModel(contractModel, serviceModel);
//    waitForAjaxExtJs();
//    driver.findElement(By.xpath("//*[text()='Edit']")).click();
//    waitForAjaxExtJs();
//    navigateCloseGeneralSectionOpenNewSection("Cost Outlier Payment");
  }

  //Expected Values
  final static String expectedOperatingRatioOfCostCharge = getRandomStringAndSetDecimalPlaces(-999, 999, 4, printout);
  final static String expectedCapitalRatioOfCostCharge = getRandomStringAndSetDecimalPlaces(-999, 999, 4, printout);
  final static String expectedNonBurnMarginalCostFactor = getRandomStringAndSetDecimalPlaces(-999, 999, 4, printout);
  final static String expectedFixedLossThreshold = getRandomStringAndSetDecimalPlaces(-9999999, 9999999, 2, printout);
  final static String expectedThresholdLaborPortion = getRandomStringWithNoDecimalPlaces(-999999, 999999, 0, printout);

  @Test
  public void test00SetAllFieldValues() throws InterruptedException {
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion, printout);
    driver.findElement(By.xpath("//*[text()='Threshold Labor Portion']")).click();
  }

  @Test
  public void test01AssertOperatingRatioOfCostChargeFieldFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge);
  }

  @Test
  public void test02AssertCapitalRatioOfCostChargeFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge);
  }

  @Test
  public void test03AssertNonBurnMarginalCostFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor);
  }

  @Test
  public void test04AssertFixedLossThresholdFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold);
  }

  @Test
  public void test05AssertThresholdLaborPortionFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion);
  }

  @Test
  public void test06AssertPreviouslySelectedServiceIsDisplayed() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'# CI PC Service')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test10ClickContinueAndCloseButtonThenReopenEditDialog() throws InterruptedException {
    doClickCloseAndContinueButtonThenReopenEditDialog();
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  @Test
  public void test11AssertOperatingRatioOfCostChargeFieldFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge);
  }

  @Test
  public void test12AssertCapitalRatioOfCostChargeFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge);
  }

  @Test
  public void test13AssertNonBurnMarginalCostFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor);
  }

  @Test
  public void test14AssertFixedLossThresholdFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold);
  }

  @Test
  public void test15AssertThresholdLaborPortionFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion);
  }

  @Test
  public void test16AssertPreviouslySelectedServiceIsDisplayedAfterClickingContinueAndCloseButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'# CI PC Service')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test20SaveUpdatedFieldsAndCloseThenReopenEditDialog() throws InterruptedException {
    doCloseEditSectionAndClickSaveButton(serviceModel);
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  @Test
  public void test21AssertOperatingRatioOfCostChargeFieldFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge);
  }

  @Test
  public void test22AssertCapitalRatioOfCostChargeFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge);
  }

  @Test
  public void test23AssertNonBurnMarginalCostFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor);
  }

  @Test
  public void test24AssertFixedLossThresholdFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold);
  }

  @Test
  public void test25AssertThresholdLaborPortionFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion);
  }

  @Test
  public void test26AssertPreviouslySelectedServiceIsDisplayedAfterClickingSaveButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'# CI PC Service')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

}
