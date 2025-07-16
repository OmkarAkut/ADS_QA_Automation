package webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class Fy2020IppsMedicareYearSwitchingCapitalPaymentSectionAds2223 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  private final String testSection = "Capital Payment";
  private static double colaCapitalFactor;
  private static double colaOperatingFactor;
  //String newMedicareYear = "Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";
  static String newMedicareYear = "Oct 1, 2020 - Sept 30, 2021";  //this shouldn't be changed
  String nationalCapitalRateStartingValue = "462.61";

  /** Automates test ticket ADS-2223-Capital Payment Section. Dev Story ADS-1501.
   * Verifies that ui functions properly when Medicare year range is switched
   * between the previous ui (pre 2019 years) and the new updated ui
   * (2020 and beyond).  This test starts with the new year, then switches to old years,
   * then switches back to the new years ui.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsMedicareYearSwitchingCapitalPaymentSectionAds2223.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
    System.out.println("colaOperatingFactor: " + colaOperatingFactor);
    navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
  }

  //Expected Values
  final static String expectedCapitalGeographicAdjFactorField = getRandomStringAndSetDecimalPlaces(1, 999, 4, printout);
  final static String expectedNationalCapitalRate = getRandomStringAndSetDecimalPlaces(1, 999, 2, printout);
  final static String expectedCapitalColaFactor = getRandomStringAndSetDecimalPlaces(0.01, .99, 2, printout);

  @Test
  public void test00SetAllFieldValues() {
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField, printout);
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), expectedNationalCapitalRate, printout);
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionCapitalColaFactorField(), expectedCapitalColaFactor, printout);
    editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField().click();  //off-click to trigger calculation
    editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField().click();  //off-click to trigger calculation
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
    colaCapitalFactor = Double.parseDouble(driver.findElement(By.name("colaCapitalFactor")).getAttribute("value"));
    System.out.println("colaCapitalFactor: " + colaCapitalFactor);
    assertElementTextContains(editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField(), calculateCapitalPaymentBlendedRate(colaCapitalFactor), printout);
  }

  @Test
  public void test04aAssertCapitalColaFactorLabel() {  //this test supports ADS-2603
    assertTrue(driver.findElement(By.xpath("//label[text()='Capital Cola Factor']")).isDisplayed());
  }

  @Test
  public void test04bAssertColaFactorFieldUpdatedValue() {  //this test supports ADS-2603
    assertElementValueAttribute(editModelMap.getCapitalPaymentSectionCapitalColaFactorField(), expectedCapitalColaFactor, printout);
  }

  @Test
  public void test05ChangeMedicareYearToPreviousYear() throws InterruptedException {
    waitForAjaxExtJs();
    navigateOpenNewSection("General");
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='Capital Payment']/parent::div/following-sibling::div/img"));
    navigateOpenNewSection(op.get(1));
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
    driver.findElement(By.xpath("//*[@name='nationalCapitalWeight']")).click();  //clicking here triggers calculation to update
    assertElementTextContains(editModelMap.getCapitalPaymentSectionBlendedRateField(), calculateCapitalPaymentBlendedRate(colaOperatingFactor), printout);
  }

  @Test
  public void test09cAssertColaCapitalFactorLabel() {  //this test supports ADS-2603 - note this is the old field-not same value as new Capital Cola Factor field
    assertTrue(driver.findElement(By.xpath("//label[text()='COLA Capital Factor']")).isDisplayed());
  }

  @Test
  public void test09dAssertColaFactorFieldUpdatedValue() {  //this test supports ADS-2603
    assertFalse(editModelMap.getGeneralSectionColaFactorField().getAttribute("value").equals(colaCapitalFactor));
  }

  @Test
  public void test10ChangeMedicareYearToNewYear() throws InterruptedException {
    waitForAjaxExtJs();
    doChangeMedicareYearTo(newMedicareYear);
  }

  @Test
  public void test11AssertCapitalGeographicAdjFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField);
  }

  @Test
  public void test12AssertNationalCapitalRateFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), nationalCapitalRateStartingValue);
  }

  @Test
  public void test13AssertColaGeographicAdjustedRateCalculatedValueAfterClickingSaveButton() {
    driver.findElement(By.xpath("//*[@name='nationalCapitalWeight']")).click();  //clicking here triggers calculation to update
    assertElementText(editModelMap.getCapitalPaymentSectionColaGeographicAdjustedRateField(), calculateCapitalPaymentBlendedRate(colaCapitalFactor), printout);
  }

  @Test
  public void test14aAssertCapitalColaFactorLabel() {  //this test supports ADS-2603
    assertTrue(driver.findElement(By.xpath("//label[text()='Capital Cola Factor']")).isDisplayed());
  }

  @Test
  public void test14bAssertColaFactorFieldUpdatedValue() {  //this test supports ADS-2603
    assertElementValueAttribute(editModelMap.getCapitalPaymentSectionCapitalColaFactorField(), String.valueOf(colaCapitalFactor), printout);
  }

}
