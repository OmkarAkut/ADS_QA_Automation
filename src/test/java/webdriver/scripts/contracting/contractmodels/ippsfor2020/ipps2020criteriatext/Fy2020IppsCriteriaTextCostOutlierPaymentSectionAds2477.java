package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020criteriatext;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsCriteriaTextCostOutlierPaymentSectionAds2477 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Criteria Text";
  private static final String serviceModel = "OPPS 2019";
  private static final String testSection = "Cost Outlier Payment";
  String newMedicareYear = "Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";

  /**
   * Automates test ticket ADS-2477-Cost Outlier Payment Section. Dev Story ADS-1405.
   * Verifies text in Criteria box displays proper data for Medicare years 2020 (new ui)
   * and 2019 and before (previous ui) after changing the year.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsCriteriaTextCostOutlierPaymentSectionAds2477.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  //Expected Values
  static final String expectedOperatingRatioOfCostCharge = getRandomStringAndSetDecimalPlaces(-999, 999, 4, printout);
  static final String expectedCapitalRatioOfCostCharge = getRandomStringAndSetDecimalPlaces(1, 999, 4, printout);
  static final String expectedNonBurnMarginalCostFactor = getRandomStringAndSetDecimalPlaces(1, 999, 4, printout);
  static final String expectedFixedLossThreshold = getRandomStringAndSetDecimalPlaces(1, 9999999, 2, printout);
  static final String expectedThresholdLaborPortion = getRandomStringWithNoDecimalPlaces(1, 999999, 0, printout);

  String[] expectedCriteriaTextNew = {
      "Medicare Inpatient PPS: " + newMedicareYear,
      "Operating RCC: " + expectedOperatingRatioOfCostCharge,
      "Capital RCC: " + expectedCapitalRatioOfCostCharge,
      "Non-Burn Marginal Cost Factor: " + expectedNonBurnMarginalCostFactor,
      "Fixed Loss Threshold: " + expectedFixedLossThreshold,
      "Threshold Labor Portion: " + expectedThresholdLaborPortion + " %",
  };

  String[] expectedCriteriaTextPrevious = {
      "Medicare Inpatient PPS: " + previousMedicareYear,
      "Operating RCC: " + expectedOperatingRatioOfCostCharge,
      "Capital RCC: " + expectedCapitalRatioOfCostCharge,
      "Payment Factor: " + expectedNonBurnMarginalCostFactor,
      "Fixed Loss Threshold: " + expectedFixedLossThreshold,
      "Labor Portion: " + expectedThresholdLaborPortion,
  };

  @Test
  public void test00aSetAllFieldValues() {
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion, printout);
    driver.findElement(By.xpath("//*[contains(text(),'Labor Portion')]")).click();
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
  public void test07ClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlockForMedicare2020() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextNew,
            printout
    );
  }

  @Test
  public void test08ClickEditButtonToReopenEditDialogAndUpdateMedicareYear() throws InterruptedException {
    doClickEditButtonToReopenEditDialog();
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> op = getListOfSections(testSection);
    //List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='" + testSection + "']/parent::div"));
    navigateOpenNewSection(op.get(1));
  }

  @Test
  public void test11AssertOperatingRatioOfCostChargeFieldFieldUpdatedValueAfterSwitchingMedicareYearToPrevious() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge);
  }

  @Test
  public void test12AssertCapitalRatioOfCostChargeFieldUpdatedValueAfterSwitchingMedicareYearToPrevious() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge);
  }

  @Test
  public void test13AssertNonBurnMarginalCostFactorFieldUpdatedValueAfterSwitchingMedicareYearToPrevious() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor);
  }

  @Test
  public void test14AssertFixedLossThresholdFieldUpdatedValueAfterSwitchingMedicareYearToPrevious() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold);
  }

  @Test
  public void test15AssertThresholdLaborPortionFieldUpdatedValueAfterSwitchingMedicareYearToPrevious() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion);
  }

  @Test
  public void test20ClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlockFor2019() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextPrevious,
            printout
    );
  }
}
