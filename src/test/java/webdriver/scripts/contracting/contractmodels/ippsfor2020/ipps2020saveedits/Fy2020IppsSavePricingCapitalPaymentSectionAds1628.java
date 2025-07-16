package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020saveedits;

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
public class Fy2020IppsSavePricingCapitalPaymentSectionAds1628 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  private final String testSection = "Capital Payment";
 // private static double colaOperatingFactor;
  private static double colaCapitalFactor;

  /**
   * Automates test ticket ADS-1628. Dev Story ADS-1501 and 1494.
   * For Updated Label changes, see ADS-2093:  For new UI for FY2020 and beyond,
   *    Blended Rate label replaced by "COLA Geographic Adjusted Rate".
   * Adjusted Rate User updates field values and they should remain after clicking Continue & Close.
   * Also, values should persist after clicking Save on main page. *
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsSavePricingCapitalPaymentSectionAds1628.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
    //colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
    colaCapitalFactor = Double.parseDouble(driver.findElement(By.name("colaCapitalFactor")).getAttribute("value"));
    System.out.println("colaCapitalFactor: " + colaCapitalFactor);
    navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");

//    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
//    System.out.println("colaOperatingFactor: " + colaOperatingFactor);
//    navigateCloseGeneralSectionOpenNewSection("Capital Payment");
  }

  //Expected Values
  final static String expectedCapitalGeographicAdjFactorField = getRandomStringAndSetDecimalPlaces(-999, 999, 4, printout);
  final static String expectedNationalCapitalRate = getRandomStringAndSetDecimalPlaces(-999999, 999999, 2, printout);

  @Test
  public void test00SetAllFieldValues() {
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField, printout);
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), expectedNationalCapitalRate, printout);
    editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField().click();  //click in field to trigger updated calculation in Cola GAR field
  }

  @Test
  public void test01AssertCapitalGeographicAdjFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField);
  }

  @Test
  public void test02AssertNationalCapitalRateFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), expectedNationalCapitalRate);
  }

  @Test
  public void test03AssertColaGeographicAdjustedRateCalculatedValue() {
    editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField().click();  //off-click to trigger calculation
    assertElementText(editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField(), calculateCapitalPaymentBlendedRate(colaCapitalFactor), printout);
  }

  @Test
  public void test05ClickContinueAndCloseButtonThenReopenEditDialog() throws InterruptedException {
    doClickCloseAndContinueButtonThenReopenEditDialog();
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  @Test
  public void test06AssertCapitalGeographicAdjFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField);
  }

  @Test
  public void test07AssertNationalCapitalRateFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), expectedNationalCapitalRate);
  }

  @Test
  public void test08AssertColaGeographicAdjustedRateCalculatedValueAfterClickingContinueAndCloseButton() {
    assertElementText(editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField(), calculateCapitalPaymentBlendedRate(colaCapitalFactor), printout);
  }

  @Test
  public void test10SaveUpdatedFieldsAndCloseThenReopenEditDialog() throws InterruptedException {
    doCloseEditSectionAndClickSaveButton(serviceModel);
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  @Test
  public void test11AssertCapitalGeographicAdjFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField);
  }

  @Test
  public void test12AssertNationalCapitalRateFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), expectedNationalCapitalRate);
  }

   @Test
  public void test13AssertColaGeographicAdjustedRateCalculatedValueAfterClickingSaveButton() {
    assertElementText(editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField(), calculateCapitalPaymentBlendedRate(colaCapitalFactor), printout);
  }

}
