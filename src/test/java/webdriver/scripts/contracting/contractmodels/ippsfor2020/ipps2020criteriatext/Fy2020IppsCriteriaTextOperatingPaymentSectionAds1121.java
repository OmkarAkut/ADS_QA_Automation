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
public class Fy2020IppsCriteriaTextOperatingPaymentSectionAds1121 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Criteria Text";
  private static final String serviceModel = "OPPS 2019";

  //Expected Values
  String newMedicareYear = "Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";
  static final String expectedAreaWageIndex = getRandomStringAndSetDecimalPlaces(0, 999, 2, "expectedAreaWageIndex", printout);
  static final String expectedNationalLaborRate = getRandomStringAndSetDecimalPlaces(999999, 999999, 2, "expectedNationalLaborRate", printout);
  static final String expectedNationalNonLaborRate = getRandomStringAndSetDecimalPlaces(999, 999, 2, "expectedNationalNonLaborRate", printout);
  static final String expectedHospitalReadmissionFactor = getRandomStringAndSetDecimalPlaces(0, 1, 4, "expectedHospitalReadmissionFactor", printout);
  static final String expectedUncompensatedCarePayment = getRandomStringAndSetDecimalPlaces(-999999, 999999, 2, "expectedUncompensatedCarePayment", printout);
  static final String expectedValueBasedPurchasingFactor = getRandomStringAndSetDecimalPlaces(0, 1, 4, "expectedValueBasedPurchasingFactor", printout);
  static final String expectedReduction = getRandomStringAndSetDecimalPlaces(0, 2, 4, "expectedReduction", printout);

  String[] expectedUncompensatedCarePaymentText = {
      "Uncompensated Care Payment: " + expectedUncompensatedCarePayment,
  };

  String[] expectedCriteriaTextNew = {
      "Medicare Inpatient PPS: " + newMedicareYear,
      "Area Wage Index: " + expectedAreaWageIndex,
      "Quality Data Submitter: Yes",
      "Meaningful EHR User: Yes",
      "Operating National Labor Rate: " + expectedNationalLaborRate,
      "Operating National Non-Labor Rate: " + expectedNationalNonLaborRate,
      "Operating National Weight: 100",
      "Hospital Readmissions Factor: " + expectedHospitalReadmissionFactor,
      "Value Based Purchasing Factor: " + expectedValueBasedPurchasingFactor,
      "Worst-Performing Quartile for HAC: No",
      "Hospital-Acquired Condition Reduction: " + expectedReduction + " %",
  };

  String[] expectedCriteriaTextPrevious = {
      "Medicare Inpatient PPS: " + previousMedicareYear,
      "Area Wage Index: " + expectedAreaWageIndex,
      "Operating National Labor Rate: " + expectedNationalLaborRate,
      "Operating National Non-Labor Rate: " + expectedNationalNonLaborRate,
      "Operating National Weight: ",
      "COLA Operating Factor: " + expectedUncompensatedCarePayment,
      "Hospital Readmissions Factor: " + expectedValueBasedPurchasingFactor,
      "Value Based Purchasing Factor: " + expectedAreaWageIndex,
      "Hospital-Acquired Condition Reduction: " + expectedReduction + " %",
      "Capital Payment Method: FullPPS",
      "Geographic Adj Factor: 1",
      "Case Mix Index Adj Factor: 1",
      "Large Urban Add-On Factor: 1",
      "Capital National Rate:",
      "Capital National Weight: 100",
      "COLA Capital Factor:",
  };

  /**
   * Automates test ticket ADS-1121-Operating Payment Section. Dev Story ADS-1405.
   * Verifies text in Criteria box displays proper data for Medicare years 2020 (new ui)
   * and 2019 and before (previous ui) after changing the year.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsCriteriaTextOperatingPaymentSectionAds1121.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  @Test
  public void test00SetAllFieldValues() {
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), expectedNationalLaborRate, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), expectedNationalNonLaborRate, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionHospitalReadmissionFactorField(), expectedHospitalReadmissionFactor, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionValueBasedPurchasingFactorField(), expectedValueBasedPurchasingFactor, printout);
    doUpdateFormFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction, printout);
    doClick(driver.findElement(By.xpath("//label[text()='%']")));
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
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment);
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
  public void test10aClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlock() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextNew,
            printout
    );
  }

  @Test
  public void test10bAssertPricingMethodsCriteriaTextBlockValueForUncompensatedCarePayment() {
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedUncompensatedCarePaymentText,
            printout
    );
  }

  @Test
  public void test11aClickEditButtonToReopenEditDialogAndUpdateMedicareYear() throws InterruptedException {
    doClickEditButtonToReopenEditDialog();
    waitForAjaxExtJs();
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> op = getListOfSections("Operating Payment");
    //List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='Operating Payment']/parent::div"));
    navigateOpenNewSection(op.get(1));
  }

  @Test
  public void test11bAssertAreaWageIndexFieldForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex);
  }

  @Test
  public void test12AssertNationalLaborRateFieldUpdatedValueForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), expectedNationalLaborRate);
  }

  @Test
  public void test13AssertNationalNonLaborRateFieldUpdatedValueForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), expectedNationalNonLaborRate);
  }

  @Test
  public void test15AssertHospitalReadmissionFactorFieldUpdatedValueForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionHospitalReadmissionFactorField(), expectedHospitalReadmissionFactor);
  }

  @Test
  public void test16AssertUncompensatedCarePaymentFieldUpdatedValueForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionUncompensatedCarePaymentField(), expectedUncompensatedCarePayment);
  }

  @Test
  public void test17AssertValueBasedPurchasingFactorFieldUpdatedValueForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionValueBasedPurchasingFactorField(), expectedValueBasedPurchasingFactor);
  }

  @Test
  public void test18AssertHospitalAcquiredConditionReductionPercentFieldUpdatedValueForPreviousMedicareYear2019() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction);
  }

  @Test
  public void test19cClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlock() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextPrevious,
            printout
    );
  }
}
