package webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsMedicareYearSwitchingOperatingPaymentSectionAds2222 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  private static double colaOperatingFactor;
  String newMedicareYear = "Oct 1, 2020 - Sept 30, 2021";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";
  private static String savedNationalLaborRate = "4071.49";
  private static String savedNationalNonLaborRate = "1889.70";

  /** Automates test ticket ADS-2222-Operating Payment Section. Dev Story ADS-1501.
   * Verifies that ui functions properly when Medicare year range is switched
   * between the previous ui (pre 2019 years) and the new updated ui
   * (2020 and beyond).  This test starts with the new year, then switches to old years,
   * then switches back to the new years ui.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsMedicareYearSwitchingOperatingPaymentSectionAds2222.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
    System.out.println("colaOperatingFactor: " + colaOperatingFactor);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  //Generate Expected Values
  final static String expectedAreaWageIndex = getRandomStringAndSetDecimalPlaces(0, 999, 2, "expectedAreaWageIndex", printout);
  final static String expectedNationalLaborRate = getRandomStringAndSetDecimalPlaces(0, 999, 2, "expectedNationalLaborRate", printout);
  final static String expectedNationalNonLaborRate = getRandomStringAndSetDecimalPlaces(0, 999, 2, "expectedNationalNonLaborRate", printout);
  final static String expectedHospitalReadmissionFactor = getRandomStringAndSetDecimalPlaces(0, 1, 4, "expectedHospitalReadmissionFactor", printout);
  final static String expectedUncompensatedCarePayment = getRandomStringAndSetDecimalPlaces(0, 999999, 2, "expectedUncompensatedCarePayment", printout);
  final static String expectedValueBasedPurchasingFactor = getRandomStringAndSetDecimalPlaces(0, 1, 4, "expectedValueBasedPurchasingFactor", printout);
  final static String expectedReduction = getRandomStringAndSetDecimalPlaces(0, 2, 4, "expectedReduction", printout);

  @Test
  public void test00SetAllFieldValues() {
    //savedLaborWageRate = getFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField());
    //savedNonLaborWageRate = getFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField());
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
  public void test01aVerifyOperatingPaymentSectionFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[text()='Area Wage Index']");
    assertElementIsDisplayedWithXpath("//label[text()='Quality Data Submitter']");
    assertElementIsDisplayedWithXpath("//label[text()='Meaningful EHR User']");
    assertElementIsDisplayedWithXpath("//label[text()='National Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[text()='National Non-Labor Rate']");

    assertElementIsDisplayedWithXpath("//label[text()='Hospital Readmission Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Uncompensated Care Payment']");
    assertElementIsDisplayedWithXpath("//label[text()='Value Based Purchasing Factor']");
    //assertElementIsDisplayedWithXpath("//label[text()='Worst-Performing Quartile for HAC']");
    assertElementIsDisplayedWithXpath("//label[text()='Reduction ']");
    assertElementIsDisplayedWithXpath("//label[text()='%']");

    assertElementIsDisplayedWithXpath("//label[text()='COLA Wage Adjusted Rate']");
    assertElementIsDisplayedWithXpath("//label[text()='Wage Adjusted Rate']");

    //element labels not displayed
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='National Relative Weight']")).isDisplayed();
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
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='COLA Operating Factor']")).isDisplayed();
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
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Hospital-Acquired Condition Reduction']")).isDisplayed();
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
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Federal Operating Percent']")).isDisplayed();
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
  public void test01bAssertAreaWageIndexFieldUpdatedValue() {
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
  public void test09aAssertColaWageAdjustedRateFieldUpdatedValue() {
    assertElementText(editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField(), calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor), printout);
  }

  @Test
  public void test09bAssertWageAdjustedRateFieldUpdatedValue() {
    assertElementText(editModelMap.getOperatingPaymentSectionWageAdjustedRateField(), calculateOperatingPaymentSectionWageAdjustedFederalRate(), printout);
  }

  double nationalCapitalRate;
  double capitalGeographicAdjustmentFactor;
  double largeUrbanAddOnFactor;

  @Test
  public void test09zGetFieldValuesForCalculation() throws InterruptedException {
    navigateOpenNewSection("Capital Payment");
    nationalCapitalRate = Double.parseDouble(editModelMap.getCapitalPaymentSectionNationalCapitalRateField().getAttribute("value"));
    capitalGeographicAdjustmentFactor = Double.parseDouble(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField().getAttribute("value"));
  }

  @Test
  public void test10aChangeMedicareYearToPreviousYear() throws InterruptedException {
    waitForAjaxExtJs();
    navigateOpenNewSection("General");
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='Operating Payment']/parent::div/following-sibling::div/img"));
    navigateOpenNewSection(op.get(1));
    largeUrbanAddOnFactor = Double.parseDouble(editModelMap.getCapitalPaymentSectionLargeUrbanAddOnFactorField().getAttribute("value"));
  }

  @Ignore
  @Test
  public void test10bVerifyOperatingPaymentSectionFieldLabelsAreDisplayedForPreviousYear() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Area Wage Index']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='National Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='National Non-Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='National Relative Weight']");

    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='COLA Operating Factor']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Hospital Readmission Factor']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Uncompensated Care Payment']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Value Based Purchasing Factor']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Hospital-Acquired Condition Reduction']");
    assertElementIsDisplayedWithXpath("//label[text()='%']");

    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Blended Rate']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Federal Operating Percent']");
    assertElementIsDisplayedWithXpath("//label[contains(@id,'numberfield')][text()='Wage Adjusted Federal Rate']");

    //element labels not displayed
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Quality Data Submitter']")).isDisplayed();
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
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Meaningful EHR User']")).isDisplayed();
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
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Worst-Performing Quartile for HAC']")).isDisplayed();
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
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Reduction']")).isDisplayed();
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
  public void test18AssertHospitalAcquiredConditionReductionPercentFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionReductionPercentField(), expectedReduction);
  }

  @Test
  public void test19aAssertBlendedRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
//    String expectedAdjFedRate2019 = calculateOperatingPaymentSectionBlendedRateForPriorYears(
//            nationalCapitalRate,
//            capitalGeographicAdjustmentFactor,
//            largeUrbanAddOnFactor
//    );
    assertElementText(
            editModelMap.getOperatingPaymentSectionBlendedRateField(),
            calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor),
            printout
    );
  }

  @Test
  public void test19bAssertWageAdjustedFederalRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    // (Nat Labor Rate x Wage Index) + Nat Non-Lab Rate
 //   String expectedAdjFedRate2019 = calculateOperatingPaymentSectionWageAdjustedFederalRate();
//            Double.parseDouble(editModelMap.getOperatingPaymentSectionNationalLaborRateField().getAttribute("value")),
//            Double.parseDouble(editModelMap.getOperatingPaymentSectionAreaWageIndexField().getAttribute("value")),
//            Double.parseDouble(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField().getAttribute("value"))
//    );
    assertElementText(
            editModelMap.getOperatingPaymentSectionWageAdjustedFederalRateField(),
            //driver.findElement(By.xpath("//label[text()='Wage Adjusted Federal Rate'][contains(@class,'x-form-item-label-left')]/../following-sibling::td/div[contains(@class,'display-field')]")),
            //expectedAdjFedRate2019,
            calculateOperatingPaymentSectionWageAdjustedFederalRate(),
            printout
    );
  }

  @Test
  public void test20ChangeMedicareYearToNewYear() throws InterruptedException {
    waitForAjaxExtJs();
    doChangeMedicareYearTo(newMedicareYear);
  }

  @Test
  public void test21AssertAreaWageIndexFieldAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionAreaWageIndexField(), expectedAreaWageIndex);
  }

  @Test
  public void test22AssertNationalLaborRateFieldAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalLaborRateField(), savedNationalLaborRate);
  }

  @Test
  public void test23AssertNationalNonLaborRateFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getOperatingPaymentSectionNationalNonLaborRateField(), savedNationalNonLaborRate);
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
  public void test29aAssertColaWageAdjustedRateFieldUpdatedValueAfterClickingSaveButton() {
    assertElementTextContains(editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField(), calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor), printout);
  }

  @Test
  public void test29bAssertWageAdjustedRateFieldUpdatedValueAfterClickingSaveButton() {
    assertElementTextContains(editModelMap.getOperatingPaymentSectionWageAdjustedRateField(), calculateOperatingPaymentSectionWageAdjustedFederalRate(), printout);
  }

}
