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
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsOperatingPaymentSectionAds1504 extends ContractModelsHelper {

  private static final String serviceModel = "OPPS 2019";
  private static final String contractModel = "AFT IPPS 2020";
  private static double colaOperatingFactor;
  private static EditContractingModelMap editModelMap;

  /** Dev Story ADS-1320 - Operating Payment section (Automates test ticket ADS-1504) 9-27-19.
   * See ADS-2093 for field name updates.  Field names updated: 1-22-20
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsOperatingPaymentSectionAds1504.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    colaOperatingFactor = Double.parseDouble(driver.findElement(By.name("colaOperatingFactor")).getAttribute("value"));
    System.out.println("colaOperatingFactor: " + colaOperatingFactor);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  @Test
  public void test00ExpandOperatingPaymentSectionAndAssertProperFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    //form fields
    assertElementIsDisplayedWithXpath("//label[text()='Area Wage Index']");
    assertElementIsDisplayedWithXpath("//label[text()='National Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[text()='National Non-Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[text()='Hospital Readmission Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Uncompensated Care Payment']");
    assertElementIsDisplayedWithXpath("//label[text()='Value Based Purchasing Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Reduction ']");
    assertElementIsDisplayedWithXpath("//label[text()='%']");
    //checkboxes
    assertElementIsDisplayedWithXpath("//label[text()='Quality Data Submitter']");
    assertElementIsDisplayedWithXpath("//label[text()='Meaningful EHR User']");
    assertElementIsDisplayedWithXpath(editModelMap.hac); //HAC checkbox
    //read-only fields
    assertElementIsDisplayedWithXpath(
            "//span[text()='Operating Payment']/ancestor::div[contains(@id, 'customaccordianpanel')]" +
                    "/descendant::*[text()='COLA Wage Adjusted Rate']"); //12-6-19 this is now 'COLA Wage Adjusted Rate'
    assertElementIsDisplayedWithXpath(
            "//span[text()='Operating Payment']/ancestor::div[contains(@id, 'customaccordianpanel')]" +
                    "/descendant::*[text()='Wage Adjusted Rate']");  //12-6-19 this is now 'Wage Adjusted Rate'
  }

  @Test
  public void test01AssertOperatingPaymentSectionFormFieldsCheckboxesAndReadOnlyFieldsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    WebElement[] formFields = {
            driver.findElement(By.name("areaWageIndex")),
            driver.findElement(By.name("nationalOperlaborRate")),
            driver.findElement(By.name("nationalOperNonLaborRate")),
            driver.findElement(By.name("hospitalReadmAdjFactor")),
            driver.findElement(By.name("uncompensatedCarePayment")),
            driver.findElement(By.name("valueBasedPurchAdjFactor")),
            driver.findElement(By.name("hacReductionPercent")),
    };
    for (WebElement element : formFields) {
      assertTrue(element.isEnabled());
    }
    WebElement[] checkboxes = {
            driver.findElement(By.xpath("//label[text()='Quality Data Submitter']/preceding-sibling::input[contains(@class,'checkbox')]")),
            driver.findElement(By.xpath("//label[text()='Meaningful EHR User']/preceding-sibling::input[contains(@class,'checkbox')]")),
            editModelMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox(),
            //driver.findElement(By.xpath("//label[text()='Worst-Performing Quartile for HAC']/preceding-sibling::input[contains(@class,'checkbox')]")),
    };
    for (WebElement element : checkboxes) {
      assertTrue(element.isEnabled());
    }
    WebElement[] readonlyfields = {
            driver.findElement(By.xpath("//label[text()='Blended Rate']/../following-sibling::td/div[contains(@class,'display-field')]")),
            driver.findElement(By.xpath("//label[text()='Wage Adjusted Federal Rate']/../following-sibling::td/div[contains(@class,'display-field')]")),
    };
    for (WebElement element : readonlyfields) {
      assertTrue(element.isEnabled());
    }
  }

  @Test
  public void test10aAssertOperatingPaymentNationalLaborRateFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("nationalOperlaborRate")).isEnabled());
  }

  @Test
  public void test10bValidateOperatingPaymentNationalLaborRateFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"0", "999999.99", "10.33"}; //"-1.99",
    assertValidFieldValues(driver.findElement(By.name("nationalOperlaborRate")), validTestValueSet);
  }

  @Test
  public void test10dValidateOperatingPaymentNationalLaborRateFieldValueAllows2DecimalPlaces() {
    String testValue = "1.99";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("nationalOperlaborRate")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,2, printout);
  }

  @Test
  public void test10cValidateOperatingPaymentNationalLaborRateFieldValueDoesNotAllow3DecimalPlaces() throws InterruptedException {
    String[] testValue = {"1.994"};
    assertInvalidFieldValues(driver.findElement(By.name("nationalOperlaborRate")), testValue);
  }

  @Test
  public void test11aAssertOperatingPaymentNationalNonLaborRateFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("nationalOperNonLaborRate")).isEnabled());
  }

  @Test
  public void test11bValidateOperatingPaymentNationalNonLaborRateFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"0", "999999.99"}; //"-1.99",
    assertValidFieldValues(driver.findElement(By.name("nationalOperNonLaborRate")), validTestValueSet);
  }

  @Test
  public void test11dValidateOperatingPaymentNationalNonLaborRateFieldValueAllows2DecimalPlaces() throws InterruptedException {
    String testValue = "1.99";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("nationalOperNonLaborRate")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,2, printout);
  }

  @Test
  public void test11cValidateOperatingPaymentNationalNonLaborRateFieldValueDoesNotAllow3DecimalPlaces() throws InterruptedException {
    String[] testValue = {"1.994"};
    assertInvalidFieldValues(driver.findElement(By.name("nationalOperNonLaborRate")), testValue);
  }

  @Test
  public void test12AssertNationalRelativeWeightFieldLabelIsNotDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    boolean elementIsDisplayed = false;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='National Relative Weight']")).isDisplayed();
      fail("Element should not be displayed");
    } catch (Throwable e) {
      assertThat(elementIsDisplayed, equalTo(false));
    }
  }

  @Test
  public void test09aAssertOperatingPaymentAreaWageIndexFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("areaWageIndex")).isEnabled());
  }

  @Test
  public void test09bValidateOperatingPaymentAreaWageIndexValueAllows4DecimalPlaces() {
    String testValue = "1.9999";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("areaWageIndex")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,4, printout);
  }

  @Ignore
  @Test
  public void test09cValidateOperatingPaymentAreaWageIndexValueDoesNotAllow5DecimalPlaces() throws InterruptedException {
    //although in the requirements, this was determined to not be a priority at this time
    String[] testValue = {"1.99994"};
    assertInvalidFieldValues(driver.findElement(By.name("areaWageIndex")), testValue);
  }

  @Test
  public void test09dValidateOperatingPaymentAreaWageIndexFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"0", "999.9999", "100.1"};
    assertValidFieldValues(driver.findElement(By.name("areaWageIndex")), validTestValueSet);
  }

  @Test
  public void test15aAssertOperatingPaymentHospitalReadmissionFactorFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("hospitalReadmAdjFactor")).isEnabled());
  }

  @Test
  public void test15bValidateOperatingPaymentHospitalReadmissionFactorFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"0.0000", "0", "2.0000"};
    assertValidFieldValues(driver.findElement(By.name("hospitalReadmAdjFactor")), validTestValueSet);
  }

  @Test
  public void test15dValidateOperatingPaymentHospitalReadmissionFactorValueAllows4DecimalPlaces() {
    String testValue = "1.9999";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("hospitalReadmAdjFactor")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,4, printout);
  }

  @Ignore
  @Test
  public void test15cValidateOperatingPaymentHospitalReadmissionFactorInvalidValues() throws InterruptedException {
    //determined to not be a priority at this time
    String[] testValue = {"10.00"}; //"1.99994",  "-1.4444", "3",
    assertInvalidFieldValues(driver.findElement(By.name("hospitalReadmAdjFactor")), testValue);
  }

  @Test
  public void test16aAssertOperatingPaymentUncompensatedCarePaymentFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("uncompensatedCarePayment")).isEnabled());
  }

  @Test
  public void test16bValidateOperatingPaymentUncompensatedCarePaymentFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"-999999.99", "0", "-1.44", "999999.99"};
    assertValidFieldValues(driver.findElement(By.name("uncompensatedCarePayment")), validTestValueSet);
  }

  @Test
  public void test16dValidateOperatingPaymentUncompensatedCarePaymentAllows2DecimalPlaces() {
    String testValue = "1.99";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("uncompensatedCarePayment")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,2, printout);
  }

  @Test
  public void test16cValidateOperatingPaymentUncompensatedCarePaymentInvalidValues() throws InterruptedException {
    String[] testValue = {"1.99994", "1000000", "-1.4444"};
    assertInvalidFieldValues(driver.findElement(By.name("uncompensatedCarePayment")), testValue);
  }

  @Test
  public void test17aAssertOperatingPaymentValueBasedPurchasingFactorFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("valueBasedPurchAdjFactor")).isEnabled());
  }

  @Test
  public void test17bValidateOperatingPaymentValueBasedPurchasingFactorFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"0.0000", "0", "2.0000"};
    assertValidFieldValues(driver.findElement(By.name("valueBasedPurchAdjFactor")), validTestValueSet);
  }

  @Test
  public void test17dValidateOperatingPaymentValueBasedPurchasingFactorAllows4DecimalPlaces() {
    String testValue = "1.9999";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("valueBasedPurchAdjFactor")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,4, printout);
  }

  @Test
  public void test17cValidateOperatingPaymentValueBasedPurchasingFactorInvalidValues() throws InterruptedException {
    String[] testValue = {"-1.4444", "10.00"};  //"1.99994", "3",
    assertInvalidFieldValues(driver.findElement(By.name("valueBasedPurchAdjFactor")), testValue);
  }

  @Test
  public void test18aAssertOperatingPaymentHospitalAcquiredConditionReductionFieldIsEnabled() {
    assertTrue(driver.findElement(By.name("hacReductionPercent")).isEnabled());
  }

  @Test
  public void test18bValidateOperatingPaymentHospitalAcquiredConditionReductionFieldValues() throws InterruptedException {
    String[] validTestValueSet = {"0.0000", "0", "3.0000"}; //"-1.44",
    assertValidFieldValues(driver.findElement(By.name("hacReductionPercent")), validTestValueSet);
  }

  @Test
  public void test18dValidateOperatingPaymentHospitalAcquiredConditionReductionAllows4DecimalPlaces() {
    String testValue = "1.9999";
    String valueToTest = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("hacReductionPercent")), testValue, printout);
    assertThatValueHasRequiredDecimalPlaces(valueToTest,4, printout);
  }

  @Test
  public void test18cValidateOperatingPaymentHospitalAcquiredConditionReductionInvalidValues() throws InterruptedException {
    String[] testValue = {"1.99994", "4", "10.00"};
    assertInvalidFieldValues(driver.findElement(By.name("hacReductionPercent")), testValue);
  }

  @Test
  public void test19aAssertOperatingPaymentColaWageAdjustedRateFieldIsReadOnly() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='COLA Wage Adjusted Rate']"));
    String actualBlendedRate = editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField().getAttribute("class");  //field value
    assertThat(actualBlendedRate, containsString("display"));
  }

  @Test
  public void test19bValidateOperatingPaymentColaWageAdjustedRateFieldCalculation() throws InterruptedException {
    //note: previously this field was called Blended Rate
    waitForAjaxExtJs();
    Thread.sleep(1000);
    String actualRate = editModelMap.getOperatingPaymentSectionColaWageAdjustedRateField().getText();  //field value
    String expectedRate = calculateOperatingPaymentSectionBlendedRate(colaOperatingFactor);
    assertThat(actualRate, containsString(expectedRate));
  }

  @Test
  public void test20aValidateOperatingPaymentWageAdjustedRateFieldIsReadOnly() throws InterruptedException {
    //note: previously this field was called Wage Adjusted Federal Rate
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Wage Adjusted Rate']"));
    String actualBlendedRate = editModelMap.getOperatingPaymentSectionWageAdjustedRateField().getAttribute("class");  //field value
    assertThat(actualBlendedRate, containsString("display"));
  }

  @Test
  public void test20bValidateOperatingPaymentWageAdjustedRateFieldCalculation() throws InterruptedException {
    waitForAjaxExtJs();
    Thread.sleep(1000);
    //String actualRate = driver.findElement(By.xpath("//label[text()='Wage Adjusted Federal Rate' and contains(@class,'left')]/../following-sibling::td/div")).getText();  //field value
    String actualRate = editModelMap.getOperatingPaymentSectionWageAdjustedRateField().getText();  //field value
    double natLaborRate = Double.parseDouble(driver.findElement(By.name("nationalOperlaborRate")).getAttribute("value"));
    double wageIndex = Double.parseDouble(driver.findElement(By.name("areaWageIndex")).getAttribute("value"));
    double natNonLaborRate = Double.parseDouble(driver.findElement(By.name("nationalOperNonLaborRate")).getAttribute("value"));
    //Calculation formula: (Nat Labor Rate x Wage Index) + Nat Non-Lab Rate
    Double calculatedBlendedRate = (natLaborRate * wageIndex) + natNonLaborRate;
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(calculatedBlendedRate);
    if (printout) {
      System.out.println("Expected Wage Adjusted Federal Rate = " + expectedRate);
      System.out.println("Actual Wage Adjusted Federal Rate = " + actualRate);
    }
    assertThat(actualRate, containsString(expectedRate));
  }
}
