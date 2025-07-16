package webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import org.hamcrest.MatcherAssert;
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
public class Fy2020IppsMedicareYearSwitchingCostOutlierPaymentSectionAds1631
        extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  private final String testSection = "Cost Outlier Payment";
  String newMedicareYear = "Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";
  private static String defaultThresholdLaborPortionField;
  static String savedFixedLossThreshold = "26473";
  static String savedThresholdLaborPortion = "18081.05";
  /*For 2019 and before, Threshold Labor Portion is not a calculated field, but it is for 2020
  * and after.  The "saved" 2019 value will persist to 2020 until one of the field values used in
  * the calculation is updated (National Labor Rate, National Non-labor rate, or Fixed Loss
  * Threshold.  If the 2020 Threshold Labor Portion field is updated through calculation, is the
  * updated value persisted back to 2019 (or before) if the template is changed "backward"?
  * Yes, the test script was updated.
  */

  /** Automates test ticket ADS-1631-Cost Outlier Payment Section. Dev Story ADS-1514.
   * Verifies that ui functions properly when Medicare year range is switched
   * between the previous ui (pre 2019 years) and the new updated ui
   * (2020 and beyond).  This test starts with the new year, then switches to old years,
   * then switches back to the new years ui.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsMedicareYearSwitchingCostOutlierPaymentSectionAds1631.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Cost Outlier Payment");
  }

  //Expected Values
  final static String expectedOperatingRatioOfCostCharge = getRandomStringAndSetDecimalPlaces(1, 999, 4, printout);
  final static String expectedCapitalRatioOfCostCharge = getRandomStringAndSetDecimalPlaces(1, 999, 4, printout);
  final static String expectedNonBurnMarginalCostFactor = getRandomStringAndSetDecimalPlaces(1, 999, 4, printout);
  final static String expectedFixedLossThreshold = getRandomStringAndSetDecimalPlaces(1, 99999, 2, printout);
  final static String expectedThresholdLaborPortion = getRandomStringWithNoDecimalPlaces(1, 999999, 0, printout);

  @Test
  public void test00aSetAllFieldValues() {
    defaultThresholdLaborPortionField = editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField().getAttribute("value");
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), expectedFixedLossThreshold, printout);
    doUpdateFormFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion, printout);
    driver.findElement(By.xpath("//*[text()='Threshold Labor Portion']")).click();
  }

  @Test
  public void test00bVerifyOperatingPaymentSectionFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[text()='Operating Ratio of Cost/Charge']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital Ratio of Cost/Charge']");
    assertElementIsDisplayedWithXpath("//label[text()='Non-Burn Marginal Cost Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Fixed Loss Threshold']");
    assertElementIsDisplayedWithXpath("//label[text()='Threshold Labor Portion']");
    assertElementIsDisplayedWithXpath("//label[text()='Services/Charges excluded from Outlier Calculations ']");
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

  //@Ignore
  @Test
  public void test05AssertThresholdLaborPortionFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), expectedThresholdLaborPortion);
  }

  @Test
  public void test06AssertPreviouslySelectedServiceIsDisplayed() {
    try {
      assertThatElementIsDisplayed(getWebElement("//div[6]/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/div[text()='# CI PC Service']"));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test10aChangeMedicareYearToPreviousYear() throws InterruptedException {
    waitForAjaxExtJs();
    navigateOpenNewSection("General");
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> sections = getListOfSections(testSection);
    //List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='" + testSection + "']/parent::div/following-sibling::div/img"));
    navigateOpenNewSection(sections.get(1));
  }

  @Test
  public void test10bVerifyOperatingPaymentSectionFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayed(getListOfSameElementAndSelectOne("//label[text()='Operating Ratio of Cost/Charge']", 1), printout);
    assertElementIsDisplayed(getListOfSameElementAndSelectOne("//label[text()='Capital Ratio of Cost/Charge']",1), printout);
    assertElementIsDisplayedWithXpath("//label[text()='Payment Factor']");
    assertElementIsDisplayed(getListOfSameElementAndSelectOne("//label[text()='Fixed Loss Threshold']",1), printout);
    assertElementIsDisplayedWithXpath("//label[text()='Labor Portion']");
    assertElementIsDisplayed(getListOfSameElementAndSelectOne("//label[text()='Services/Charges excluded from Outlier Calculations ']",1), printout);

    //element labels not displayed
    String elementIsDisplayed = null;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Non-Burn Marginal Cost Factor']")).getAttribute("class");
      MatcherAssert.assertThat(elementIsDisplayed, containsString("disablFieldLabl"));
    } catch (Throwable e) {
      System.out.println(e.getMessage());
    }

    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Threshold Labor Portion']")).getAttribute("class");
      MatcherAssert.assertThat(elementIsDisplayed, containsString("disablFieldLabl"));
    } catch (Throwable e) {
      System.out.println(e.getMessage());
    }

    try {
      elementIsDisplayed = driver.findElement(By.xpath("//label[text()='Fixed Loss Threshold']")).getAttribute("class");
      MatcherAssert.assertThat(elementIsDisplayed, containsString("disablFieldLabl"));
    } catch (Throwable e) {
      System.out.println(e.getMessage());
    }
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

  //@Ignore
  @Test
  public void test15AssertThresholdLaborPortionFieldUpdatedValueAfterSwitchingMedicareYearToPrevious() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), defaultThresholdLaborPortionField);
  }

  @Test
  public void test16AssertPreviouslySelectedServiceIsDisplayedAfterSwitchingMedicareYearToPrevious() {
    try {
      assertThatElementIsDisplayed(getWebElement("//div[7]/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td/div[text()='# CI PC Service']"));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test20ChangeMedicareYearToNewYear() throws InterruptedException {
    waitForAjaxExtJs();
    doChangeMedicareYearTo(newMedicareYear);
  }

  @Test
  public void test21AssertOperatingRatioOfCostChargeFieldFieldUpdatedValueAfterSwitchingMedicareYearToMostCurrent() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionOperatingRatioOfCostChargeField(), expectedOperatingRatioOfCostCharge);
  }

  @Test
  public void test22AssertCapitalRatioOfCostChargeFieldUpdatedValueAfterSwitchingMedicareYearToMostCurrent() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionCapitalRatioOfCostChargeField(), expectedCapitalRatioOfCostCharge);
  }

  @Test
  public void test23AssertNonBurnMarginalCostFactorFieldUpdatedValueAfterSwitchingMedicareYearToMostCurrent() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionNonBurnMarginalCostFactorField(), expectedNonBurnMarginalCostFactor);
  }

  @Test
  public void test24AssertFixedLossThresholdFieldUpdatedValueAfterSwitchingMedicareYearToMostCurrent() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionFixedLossThresholdField(), savedFixedLossThreshold);
  }

  //@Ignore
  @Test
  public void test25AssertThresholdLaborPortionFieldUpdatedValueAfterSwitchingMedicareYearToMostCurrent() {
    assertThatFieldValue(editModelMap.getCostOutlierPaymentSectionThresholdLaborPortionField(), savedThresholdLaborPortion);
  }

  @Test
  public void test26AssertPreviouslySelectedServiceIsDisplayedAfterSwitchingMedicareYearToMostCurrent() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'# CI PC Service')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

}
