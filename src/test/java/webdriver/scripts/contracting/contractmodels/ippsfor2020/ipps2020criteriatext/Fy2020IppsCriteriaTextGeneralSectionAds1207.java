package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020criteriatext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
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
public class Fy2020IppsCriteriaTextGeneralSectionAds1207 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Criteria Text";
  private static final String serviceModel = "OPPS 2019";

  //Expected Values
  static final String expectedDischargeCode = "3S5";
  static final String expectedOperatingImeAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 0, 2, printout);
  static final String expectedCapitalImeAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 10, 2, printout);
  static final String expectedOperatingDshAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 10, 2, printout);
  static final String expectedCapitalDshAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 10, 2, printout);
  //static final String expectedlocation = "Alaska";
  //static final String expectedColaFactor = getRandomStringAndSetDecimalPlaces(0, 1, 2, printout);
  static final String expectedMedicareYear2020 = "Oct 1, 2019 - Sept 30, 2020";
  static final String expectedMedicareYear2019 = "Oct 1, 2018 - Sept 30, 2019";

  String[] expectedCriteriaTextNew = {
      "Medicare Inpatient PPS: " + expectedMedicareYear2020,
      "Discharge status codes for transfers:",
      expectedDischargeCode,
      "Operating IME: " + expectedOperatingImeAdjustmentFactor,
      "Capital IME: " + expectedCapitalImeAdjustmentFactor,
      "Operating DSH: " + expectedOperatingDshAdjustmentFactor,
      "Capital DSH: " + expectedCapitalDshAdjustmentFactor,
      //"Location: " + expectedlocation,
      //"COLA Factor: " + expectedColaFactor,
  };

  String[] expectedCriteriaTextPrevious = {
      "Medicare Inpatient PPS: " + expectedMedicareYear2019,
      "Discharge status codes for transfers:",
      expectedDischargeCode,
      "Operating IME: " + expectedOperatingImeAdjustmentFactor,
      "Capital IME: " + expectedCapitalImeAdjustmentFactor,
      "Operating DSH: " + expectedOperatingDshAdjustmentFactor,
      "Capital DSH: " + expectedCapitalDshAdjustmentFactor,
      //"COLA Operating Factor: " + expectedColaFactor,
  };

  /**
   * Automates test ticket ADS-1207-General Section. Dev Story ADS-1405.
   * Verifies text in Criteria box displays proper data for Medicare years 2020 (new ui)
   * and 2019 and before (previous ui) after changing the year.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsCriteriaTextGeneralSectionAds1207.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
  }

  @Test
  public void test00SetAllFieldValues() throws InterruptedException {
    waitForAjaxExtJs();
    doUpdateFormFieldValue(editModelMap.getGeneralSectionOperatingImeAdjustmentFactorField(), expectedOperatingImeAdjustmentFactor, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionCapitalImeAdjustmentFactorField(), expectedCapitalImeAdjustmentFactor, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), expectedOperatingDshAdjustmentFactor, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), expectedCapitalDshAdjustmentFactor, printout);
    //doUpdateFormFieldValue(editModelMap.getGeneralSectionColaFactorField(), expectedColaFactor, printout);
  }

  @Test
  public void test03AssertDischargeStatusCodeForTransfersFieldSelectedValuePersists() {
    boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'" + expectedDischargeCode + "')]")).isDisplayed();
    assertTrue(isDisplayed == true);
  }

  @Test
  public void test04AssertMedicareYearRange() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            expectedMedicareYear2020
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test05AssertOperatingImeAdjustmentFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingImeAdjustmentFactorField(), expectedOperatingImeAdjustmentFactor);
  }

  @Test
  public void test06AssertCapitalImeAdjustmentFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalImeAdjustmentFactorField(), expectedCapitalImeAdjustmentFactor);
  }

  @Test
  public void test07AssertOperatingDshAdjustmentFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), expectedOperatingDshAdjustmentFactor);
  }

  @Test
  public void test08AssertCapitalDshAdjustmentFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), expectedCapitalDshAdjustmentFactor);
  }

//  @Test
//  public void test09AssertLocationFieldValue() throws InterruptedException {
//    assertThatDropdownSelectedValue(
//            editModelMap.getGeneralSectionLocationDropdown(),
//            editModelMap.getGeneralSectionLocationDropdownMenu(),
//            expectedlocation
//    );
//    editModelMap.getGeneralSectionLocationDropdown().click();
//  }

//  @Test
//  public void test10AssertColaFactorFieldUpdatedValue() {
//    assertThatFieldValue(editModelMap.getGeneralSectionColaFactorField(), expectedColaFactor);
//  }

  @Test
  public void test11ClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlockForMedicare2020() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextNew,
            printout
    );
  }

  @Test
  public void test12ClickEditButtonToReopenEditDialogAndUpdateMedicareYear() throws InterruptedException {
    doClickEditButtonToReopenEditDialog();
    doChangeMedicareYearTo(expectedMedicareYear2019);
  }

  @Test
  public void test13AssertDischargeStatusCodeForTransfersFieldSelectedValuePersisted() {
    boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'" + expectedDischargeCode + "')]")).isDisplayed();
    assertTrue(isDisplayed == true);
  }

  @Test
  public void test14AssertMedicareYearRangeIs2019() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            expectedMedicareYear2019
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test15AssertOperatingImeAdjustmentFactorFieldUpdatedValuePersistedFor2019() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingImeAdjustmentFactorField(), expectedOperatingImeAdjustmentFactor);
  }

  @Test
  public void test16AssertCapitalImeAdjustmentFactorFieldUpdatedValuePersistedFor2019() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalImeAdjustmentFactorField(), expectedCapitalImeAdjustmentFactor);
  }

  @Test
  public void test17AssertOperatingDshAdjustmentFactorFieldUpdatedValuePersistedFor2019() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), expectedOperatingDshAdjustmentFactor);
  }

  @Test
  public void test18AssertCapitalDshAdjustmentFactorFieldUpdatedValuePersistedFor2019() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), expectedCapitalDshAdjustmentFactor);
  }

  @Test
  public void test19aAssertLocationAndColaFieldsAreNotDisplayedFor2019() {
    //element labels not displayed
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = editModelMap.getGeneralSectionLocationDropdown().isDisplayed();
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
      elementIsDisplayed = editModelMap.getGeneralSectionColaFactorField().isDisplayed();
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
