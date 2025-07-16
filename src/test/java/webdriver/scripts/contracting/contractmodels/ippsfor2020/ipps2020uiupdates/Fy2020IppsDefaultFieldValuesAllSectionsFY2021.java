package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.contracting.contractmodels.ContractModelsHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsDefaultFieldValuesAllSectionsFY2021 extends ContractModelsHelper {

  private static ModelLibraryMap modelMap;
  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Defaults";
  private static final String serviceModel = "OPPS 2019";
  //String expectedNewestMedicareYearRange = "Oct 1, 2020 - Sept 30, 2021";
  private static final String expectedMedicareYearRange = "Oct 1, 2019 - Sept 30, 2020";
  //The values below have been changed in the application (12-14-2020)
  private static final String nationalOperlaborRate = "3596.70";
  private static final String nationalOperNonLaborRate = "2204.43";
  private static final String nationalCapitalRate = "462.61";
  private static final String costOutlierPaymentFixedLossThreshold = "26473";
  private static final String CostOutlierPaymentSectionThresholdLaborPortion = "16413.26";

  //default values from ADS Help are in comments at the end of each line
  String[] expectedCriteriaText = {
    "Medicare Inpatient PPS: Oct 1, 2019 - Sept 30, 2020",
    "Discharge status codes for transfers:",
    "Operating IME: 1",
    "Capital IME: 1",
    "Operating DSH: 1",
    "Capital DSH: 1",
    "Area Wage Index: 1",
    "Operating National Labor Rate: 3610.45",
    "Operating National Non-Labor Rate: 2212.85",
    "Operating National Weight: 100",
    "COLA Operating Factor: 1",
    "Hospital Readmissions Adjustment Factor: 1.0",
    "Uncompensated Care Payment: 0.00",
    "Value Based Purchasing Adjustment Factor: 1.0",
    "Hospital-Acquired Condition Reduction: 0.0 %",
    "Capital Payment Method: FullPPS",
    "Geographic Adj Factor: 1",
    "Case Mix Index Adj Factor: 1.0",
    "Large Urban Add-On Factor: 1.0",
    "Capital National Rate: 463.81",
    "Capital National Weight: 100",
    "COLA Capital Factor: 1.0",
    "Operating RCC: 1",
    "Capital RCC: 1",
    "Payment Factor: 0.8",
    "Fixed Loss Threshold: 26694",
    "Labor Portion: 16550.3",
  };

  /** Automates test ticket ADS-1510-General Section (and maybe ADS-1613). Dev Story 1320-1321.
   * This test probably includes sections of ADS-1321 that are related to the sections below.
   * Though this would have to be checked (which would be related to ADS-1613).
   **/
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsDefaultFieldValuesAllSectionsFY2021.class.getSimpleName());
    evolveLoginStaticUser(Users.ContractAnalyst1);
    navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
    navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
    setPricingMethod("Medicare/Commercial Outpatient PPS");
    setPricingMethod("Medicare Inpatient PPS");
  }

  @Test
  public void test01GeneralSectionPayPercentageDefaultFieldValueIs100() throws InterruptedException {
    Thread.sleep(2000);
    waitForAjaxExtJs();
    String defaultExpectedPayPercentage = "100";
    String actualPayPercentage = driver.findElement(By.xpath("//*[@name='pay']")).getAttribute("value");
    assertThat(actualPayPercentage, equalTo(defaultExpectedPayPercentage));
  }

  @Test
  public void test02GeneralSectionPricingMethodDefaultFieldValueIsAccurate() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//*[@name='pricemethodoption']")).click();
    waitForAjaxExtJs();
    String actualPricingMethodDefault = driver.findElement(By.xpath("//*[@class='x-boundlist-item x-boundlist-selected']")).getText();
    driver.findElement(By.xpath("//*[@name='pricemethodoption']")).click();
    String expectedPricingMethodDefault = "Medicare Inpatient PPS";
    assertThat(actualPricingMethodDefault, equalTo(expectedPricingMethodDefault));
  }

  @Test
  public void test03VerifyInitialCriteriaTextAreaIsEmpty() {
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    String initialCriteriaText = editModelMap.getEditContractMainPageCriteriaTextArea().getAttribute("value");
    System.out.println(("Initial criteria text length: " + initialCriteriaText.length()));
    assertEquals(0, initialCriteriaText.length());
  }

  @Test
  public void test04GeneralSectionAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1() throws InterruptedException {
    //go to General section
    navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
    doChangeMedicareYearTo(expectedMedicareYearRange);
    waitForAjaxExtJs();
    String expectedText = "MSDRG1";  //HCFA DRG
    driver.findElement(By.name("drgTypeString")).click();
    waitForAjaxExtJs();
    WebElement classificationList = driver.findElement(By.xpath("//label[text()='Industry Classification Scheme']/ancestor::div/following-sibling::div[contains(@class,'boundlist')][2]/div/ul"));
    List<WebElement> classificationListing = classificationList.findElements(By.tagName("li"));
    for (WebElement item : classificationListing) {
      String clss = item.getAttribute("class");
      if (clss.contains("selected")) {
        assertThat(item.getText(), equalTo(expectedText));
        break;
      }
    }
    driver.findElement(By.name("drgTypeString")).click();
    waitForAjaxExtJs();
  }

  @Test
  public void test05GeneralSectionReadAlternativeDrgCheckboxIsUncheckedByDefault() throws InterruptedException {
    waitForAjaxExtJs();
    String checkboxStatus = driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/parent::td/../../..")).getAttribute("class");
    assertFalse(checkboxStatus.contains("checked"));
    assertThat(checkboxStatus, not(containsString("checked")));
  }

  @Test
  public void test06GeneralSectionReadAlternativeDrgDropdownDefaultValueIsMsDrg1() throws InterruptedException {
    waitForAjaxExtJs();
    String expectedText = "MS DRG1";  //MS DRG1
    doClick(driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/preceding-sibling::input")));
    waitForAjaxExtJs();
    driver.findElement(By.name("otherDrgIndex")).click();
    waitForAjaxExtJs();
    WebElement drgList = driver.findElement(By.xpath("//div/following-sibling::div[contains(@class, 'boundlist')][4]/div/ul"));
    List<WebElement> drgListing = drgList.findElements(By.tagName("li"));
    for (WebElement item : drgListing) {
      String clss = item.getAttribute("class");
      if (clss.contains("selected")) {
        assertThat(item.getText(), equalTo(expectedText));
        break;
      }
    }
  }

  @Test
  public void test07GeneralSectionDischargeStatusCodeForTransfersFieldIsEmptyByDefault() {
    boolean isDisplayed = false;  //default is None
    try {
      isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item']")).isDisplayed();
      fail();
    } catch (Throwable e) {
      assertTrue(isDisplayed == false);
    }
  }

  @Test
  public void test08GeneralSectionMedicareYearRangeIsSelectedByDefault() throws InterruptedException {
    waitForSpinnerToEnd();
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            expectedMedicareYearRange
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));
  }

  @Test
  public void test09GeneralSectionOperatingImeAdjustmentFactorField() {
    assertFieldValue("//*[@name='operIndirectMedEducAdjFactor']", "1"); //default = 1
  }

  @Test
  public void test10GeneralSectionCapitalImeAdjustmentFactorField() {
    assertFieldValue("//*[@name='capitalIndrMedEducAdjFactor']", "1");
  }

  @Test
  public void test11GeneralSectionOperatingDshAdjustmentFactorField() {
    assertFieldValue("//*[@name='operDispShareHospAdjFactor']", "1");
  }

  @Test
  public void test12GeneralSectionCapitalDshAdjustmentFactorField() {
    assertFieldValue("//*[@name='capitalDispShareHospAdjFactor']", "1");
  }

  @Test
  public void test20OperatingPaymentNationalLaborRateField() throws InterruptedException {
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
    assertFieldValue("//*[@name='nationalOperlaborRate']", nationalOperlaborRate);
    //now this is 3695.94
  }

  @Test
  public void test21OperatingPaymentNationalNonLaborRateField() {
    assertFieldValue("//*[@name='nationalOperNonLaborRate']", nationalOperNonLaborRate);
    //now this is 2265.25
  }

  @Test
  public void test22OperatingPaymentAreaWageIndexField() {
    assertFieldValue("//*[@name='areaWageIndex']", "1.0000");
  }

  @Test
  public void test23OperatingPaymentHospitalReadmissionFactorField() {
    assertFieldValue("//*[@name='hospitalReadmAdjFactor']", "1.0");
  }

  @Test
  public void test24OperatingPaymentUncompensatedCarePaymentField() {
    assertFieldValue("//*[@name='uncompensatedCarePayment']", "0.00");
  }

  @Test
  public void test25OperatingPaymentValueBasedPurchasingFactorField() {
    assertFieldValue("//*[@name='valueBasedPurchAdjFactor']", "1.0");
  }

  @Test
  public void test26OperatingPaymentHospitalAcquiredConditionReductionField() {
    assertFieldValue("//*[@name='hacReductionPercent']", "0.0");
  }

  @Test
  public void test30CapitalPaymentSectionAssertCapitalGeographicAdjustmentFactor() throws InterruptedException {
    navigateCloseSectionOpenNewSection("Operating Payment", "Capital Payment");
    assertFieldValue("//*[@name='capitalGeographicAdjFactor']", "1");
  }

  @Test
  public void test31CapitalPaymentSectionAssertNationalCapitalRate() throws InterruptedException {
    //driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='Operating Payment']/parent::div")).click();
    //Thread.sleep(5000);
    //waitForAjaxExtJs();
    //driver.findElement(By.xpath("//*[contains(@id, 'customaccordianpanel')][text()='Capital Payment']/parent::div")).click();
    assertThatFieldValue(driver.findElement(By.name("nationalCapitalRate")), nationalCapitalRate);  //from ADS-1551
    //currently this is 466.22
  }

  @Test
  public void test40CostOutlierPaymentSectionVerifyOperatingRatioOfCostChargeField() throws InterruptedException {
    navigateCloseSectionOpenNewSection( "Capital Payment", "Cost Outlier Payment");
    assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge(), "1");
  }

  @Test
  public void test41CostOutlierPaymentSectionCapitalRatioOfCostChargeField() {
    assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge(), "1");
  }

  @Test
  public void test42CostOutlierPaymentSectionNonBurnMarginalCostFactorField() {
    assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor(), "0.8");
  }

  @Test
  public void test43CostOutlierPaymentSectionFixedLossThresholdField() {
    assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentFixedLossThreshold(), costOutlierPaymentFixedLossThreshold);  //from ADS-1551
    //this is currently 29051
  }

  @Test
  public void test44CostOutlierPaymentSectionThresholdLaborPortionField() {
    assertThatFieldValue(modelMap.getContractModelsCostOutlierPaymentThresholdLaborPortion(), CostOutlierPaymentSectionThresholdLaborPortion);
  }

  @Test
  public void test51AddOnTechnologyPaymentSectionAssertSelectedServicesDisplayPaneIsEmptyByDefault() throws InterruptedException {
    navigateCloseSectionOpenNewSection( "Cost Outlier Payment", "Add On Technology Payment");
    waitForAjaxExtJs();
    Thread.sleep(2000);
    boolean isDisplayed = false;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/div")).isDisplayed();
      fail("Selected services pane shouldn't be populated by default");
    } catch (Throwable e) {
      assertTrue("Selected services pane shouldn't be populated by default",isDisplayed == false);
    }
  }

  @Test
  public void test52CloseDialogAndVerifyDefaultCriteriaText() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaText,
            printout
    );
  }

  public static void setPricingMethod(String pricingMethod) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    doClick(driver.findElement(By.xpath("//*[@name='pricemethodoption']")));
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    List<WebElement> menu = driver.findElement(By.xpath("//*[@class='x-boundlist-item' and text()='" + pricingMethod + "']/..")).findElements(By.tagName("li"));
    for(WebElement option : menu) {
      if(option.getText().equals(pricingMethod)) {
        option.click();
        break;
      }
    }
  }
}
