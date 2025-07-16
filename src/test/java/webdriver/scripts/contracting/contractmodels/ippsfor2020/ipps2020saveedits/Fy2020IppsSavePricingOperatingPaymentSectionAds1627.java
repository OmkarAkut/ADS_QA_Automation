package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020saveedits;

import static org.junit.Assert.assertTrue;

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
public class Fy2020IppsSavePricingOperatingPaymentSectionAds1627 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  private final String testSection = "Operating Payment";
  private static double colaOperatingFactor;

  /** Automates test ticket ADS-1627.  Dev Story ADS-1501 and 1494.
   * Verifies that Ipps 2020 Ui Pricing user values Save and Persist.
   * 4-3-20 This script updated with ui updates in ADS-2093, which is
   * that Blended Rate field is now "COLA Wage Adjusted Rate" and the
   * Wage Adjusted Federal Rate label replaced with "Wage Adjusted Rate" for the
   * updated 2020 and beyond IPPS pricing UI.
   * NOTE: Test will often fail if National Labor Rate and National Non-labor rate are negative, which
   * makes a calculated value in another section negative and deactivates the Save & Close button (steps
   * 10 and 20 below - need to research this scenario to determine if those fields should allow negative values).
   * 9-9-20 Set expectedNationalLaborRate and expectedUncompensatedCarePayment to 1000.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsSavePricingOperatingPaymentSectionAds1627.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
   // colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
    //System.out.println("colaOperatingFactor: " + colaOperatingFactor);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
//    goToPage("Contract Models");
//    waitForSpinnerToEnd();
//    waitForAjaxExtJs();
//    displayPricingMethodsSectionForContractModelAndServiceModel(contractModel, serviceModel);
//    waitForAjaxExtJs();
//    driver.findElement(By.xpath("//*[text()='Edit']")).click();
//    waitForAjaxExtJs();
//    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
//    System.out.println("colaOperatingFactor: " + colaOperatingFactor);
//    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  //Expected Values
  final static String expectedAreaWageIndex = getRandomStringAndSetDecimalPlaces(1, 999, 2, printout);
  final static String expectedNationalLaborRate = getRandomStringAndSetDecimalPlaces(1, 100, 2, printout);
  final static String expectedNationalNonLaborRate = getRandomStringAndSetDecimalPlaces(1, 99, 2, printout);
  final static String expectedHospitalReadmissionFactor = getRandomStringAndSetDecimalPlaces(1, 1, 4, printout);
  final static String expectedUncompensatedCarePayment = getRandomStringAndSetDecimalPlaces(1, 1000, 2, printout);
  final static String expectedValueBasedPurchasingFactor = getRandomStringAndSetDecimalPlaces(0, 1, 4, printout);
  final static String expectedReduction = getRandomStringAndSetDecimalPlaces(0, 2, 4, printout);
  final static String colaFactor = getRandomStringAndSetDecimalPlaces(0, 1, 2, printout);
  @Test
  public void test00SetAllFieldValues() {
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), expectedNationalLaborRate, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), expectedNationalNonLaborRate, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionHospitalReadmissionFactorField(), expectedHospitalReadmissionFactor, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionValueBasedPurchasingFactorField(), expectedValueBasedPurchasingFactor, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionColaFactorField(), colaFactor, printout); //removed - from Ads-2603
    doClick(driver.findElement(By.xpath("//label[text()='%']")));

    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
    System.out.println("colaOperatingFactor: " + colaOperatingFactor);
  }

  @Test
  public void test01AssertAreaWageIndexFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex);
  }

  @Test
  public void test02AssertNationalLaborRateFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), expectedNationalLaborRate);
  }

  @Test
  public void test03AssertNationalNonLaborRateFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), expectedNationalNonLaborRate);
  }

  @Test
  public void test05AssertHospitalReadmissionFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionHospitalReadmissionFactorField(), expectedHospitalReadmissionFactor);
  }

  @Test
  public void test06AssertUncompensatedCarePaymentFieldUpdatedValue() {
    assertThatFieldValueContainsString(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment);
  }

  @Test
  public void test07AssertValueBasedPurchasingFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionValueBasedPurchasingFactorField(), expectedValueBasedPurchasingFactor);
  }

  @Test
  public void test08AssertReductionPercentFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction);
  }

  @Test
  public void test09aAssertBlendedRateFieldUpdatedValue() {
    assertElementTextContains(editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField(), calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor), printout);
  }

  @Test
  public void test09bAssertWageAdjustedFederalRateFieldUpdatedValue() {
    assertElementTextContains(editModelMap.getOperatingPaymentSectionWageAdjustedRateField(), calculateOperatingPaymentSectionWageAdjustedFederalRate(), printout);
  }

  @Test
  public void test09cAssertLocationAndOperatingColaFactorLabel() {  //this test supports ADS-2603
    assertTrue(driver.findElement(By.xpath("//label[text()='Location and Operating COLA Factor']")).isDisplayed());
  }

  @Test
  public void test09dAssertColaFactorFieldUpdatedValue() {  //this test supports ADS-2603
    assertElementValueAttribute(editModelMap.getGeneralSectionColaFactorField(), colaFactor, printout);
    assertTrue(editModelMap.getGeneralSectionLocationDropdown().isDisplayed());
    //assertElementTextContains(editModelMap.getGeneralSectionLocationDropdown(), location, printout);
  }

  @Test
  public void test10ClickContinueAndCloseButtonThenReopenEditDialog() throws InterruptedException {
    doClickCloseAndContinueButtonThenReopenEditDialog();
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  @Test
  public void test11AssertAreaWageIndexFieldAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex);
  }

  @Test
  public void test12AssertNationalLaborRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), expectedNationalLaborRate);
  }

  @Test
  public void test13AssertNationalNonLaborRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), expectedNationalNonLaborRate);
  }

  @Test
  public void test15AssertHospitalReadmissionFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionHospitalReadmissionFactorField(), expectedHospitalReadmissionFactor);
  }

  @Test
  public void test16AssertUncompensatedCarePaymentFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment);
  }

  @Test
  public void test17AssertValueBasedPurchasingFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionValueBasedPurchasingFactorField(), expectedValueBasedPurchasingFactor);
  }

  @Test
  public void test18AssertReductionPercentFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction);
  }

  @Test
  public void test19aAssertBlendedRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertElementTextContains(editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField(), calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor), printout);
  }

  @Test
  public void test19bAssertWageAdjustedFederalRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertElementText(editModelMap.getOperatingPaymentSectionWageAdjustedRateField(), calculateOperatingPaymentSectionWageAdjustedFederalRate(), printout);
  }

  @Test
  public void test19cAssertLocationAndOperatingColaFactorLabel() {  //this test supports ADS-2603
    assertTrue(driver.findElement(By.xpath("//label[text()='Location and Operating COLA Factor']")).isDisplayed());
  }

  @Test
  public void test019dAssertColaFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {  //this test supports ADS-2603
    assertElementValueAttribute(editModelMap.getGeneralSectionColaFactorField(), colaFactor, printout);
    assertTrue(editModelMap.getGeneralSectionLocationDropdown().isDisplayed());
    //assertElementTextContains(editModelMap.getGeneralSectionLocationDropdown(), location, printout);
  }

  @Test
  public void test20SaveUpdatedFieldsAndCloseThenReopenEditDialog() throws InterruptedException {
    doCloseEditSectionAndClickSaveButton(serviceModel);
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  @Test
  public void test21AssertAreaWageIndexFieldAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex);
  }

  @Test
  public void test22AssertNationalLaborRateFieldAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), expectedNationalLaborRate);
  }

  @Test
  public void test23AssertNationalNonLaborRateFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), expectedNationalNonLaborRate);
  }

  @Test
  public void test25AssertHospitalReadmissionFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionHospitalReadmissionFactorField(), expectedHospitalReadmissionFactor);
  }

  @Test
  public void test26AssertUncompensatedCarePaymentFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment);
  }

  @Test
  public void test27AssertValueBasedPurchasingFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionValueBasedPurchasingFactorField(), expectedValueBasedPurchasingFactor);
  }

  @Test
  public void test28AssertReductionPercentFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction);
  }

  @Test
  public void test29aAssertBlendedRateFieldUpdatedValueAfterClickingSaveButton() {
    assertElementTextContains(editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField(), calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor), printout);
  }

  @Test
  public void test29bAssertWageAdjustedFederalRateFieldUpdatedValueAfterClickingSaveButton() {
    assertElementText(editModelMap.getOperatingPaymentSectionWageAdjustedRateField(), calculateOperatingPaymentSectionWageAdjustedFederalRate(), printout);
  }

  @Test
  public void test29cAssertLocationAndOperatingColaFactorLabel() {  //this test supports ADS-2603
    assertTrue(driver.findElement(By.xpath("//label[text()='Location and Operating COLA Factor']")).isDisplayed());
  }

  @Test
  public void test029dAssertColaFactorFieldUpdatedValueAfterClickingSaveButton() {  //this test supports ADS-2603
    assertElementValueAttribute(editModelMap.getGeneralSectionColaFactorField(), colaFactor, printout);
    assertTrue(editModelMap.getGeneralSectionLocationDropdown().isDisplayed());
    //assertTrue(driver.findElement(By.xpath("//input[@name='location']")).isDisplayed());
    //assertElementTextContains(editModelMap.getGeneralSectionLocationDropdown(), location, printout);
  }

  //A future test should also be added that verifies that the cola factor value (as updated and saved)
  //is saved in the db only to the Operating COLA field and not the Capital COLA field.  This is part of the
  //ADS-2603.

}
