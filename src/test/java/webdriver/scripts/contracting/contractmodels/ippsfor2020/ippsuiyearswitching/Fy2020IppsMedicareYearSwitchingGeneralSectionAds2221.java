package webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching;

import static org.junit.Assert.assertFalse;
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
public class Fy2020IppsMedicareYearSwitchingGeneralSectionAds2221 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  String newMedicareYear = "Oct 1, 2020 - Sept 30, 2021";  //"Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";

  /** Automates test ticket ADS-2221-General Section. Dev Story ADS-1501.
   * Verifies that ui functions properly when Medicare year range is switched
   * between the previous ui (pre 2019 years) and the new updated ui
   * (2020 and beyond).  This test starts with the new year, then switches to old years,
   * then switches back to the new years ui.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsMedicareYearSwitchingGeneralSectionAds2221.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
  }

  //Expected Values
  String expectedIndustryClassificationScheme = "MSDRG4";
  String expectedReadAlternativeDrg = "MS DRG3";
  final static String expectedOperatingImeAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 0, 2, printout);
  final static String expectedCapitalImeAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 10, 2, printout);
  final static String operatingDshAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 10, 2, printout);
  final static String capitalDshAdjustmentFactor = getRandomStringAndSetDecimalPlaces(-10, 10, 2, printout);
  //final static String colaFactor = getRandomStringAndSetDecimalPlaces(0, 1, 2, printout);

  @Test
  public void test00aAssertEditDialogGeneralSectionExpandedByDefaultAndFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    waitForPresenceOfElement("//label[text()='Industry Classification Scheme']");
    assertElementIsDisplayedWithXpath("//label[text()='Industry Classification Scheme']");
    assertElementIsDisplayedWithXpath("//label[text()='Read Alternative DRG']");
    assertElementIsDisplayedWithXpath("//label[text()='Discharge Status Code for Transfers']");
    assertElementIsDisplayedWithXpath("//label[text()='Medicare Year']");
    assertElementIsDisplayedWithXpath("//label[text()='Operating IME Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital IME Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Operating DSH Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital DSH Adjustment Factor']");
    //assertElementIsDisplayedWithXpath("//label[text()='Location and COLA Factor']");
  }



  @Test
  public void test00bSetAllFieldValues() throws InterruptedException {
    waitForAjaxExtJs();
    doDropdownSelectUsingOptionText(
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdown(),
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdownMenu(),
            expectedIndustryClassificationScheme
    );
    waitForAjaxExtJs();
    doDropdownSelectUsingOptionText(
            editModelMap.getGeneralSectionReadAlternativeDrgDropdown(),
            editModelMap.getGeneralSectionReadAlternativeDrgDropdownMenu(),
            expectedReadAlternativeDrg
    );
    doUpdateFormFieldValue(editModelMap.getGeneralSectionOperatingImeAdjustmentFactorField(), expectedOperatingImeAdjustmentFactor, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionCapitalImeAdjustmentFactorField(), expectedCapitalImeAdjustmentFactor, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), operatingDshAdjustmentFactor, printout);
    doUpdateFormFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), capitalDshAdjustmentFactor, printout);
    //doUpdateFormFieldValue(editModelMap.getGeneralSectionColaFactorField(), colaFactor, printout);
  }

  @Test
  public void test01AssertIndustryClassificationSchemeFieldUpdatedValue() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdown(),
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdownMenu(),
            expectedIndustryClassificationScheme
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test02AssertReadAlternativeDrgDropdownUpdatedValue() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionReadAlternativeDrgDropdown(),
            editModelMap.getGeneralSectionReadAlternativeDrgDropdownMenu(),
            expectedReadAlternativeDrg
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test03AssertDischargeStatusCodeForTransfersFieldSelectedValuePersists() {
    boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'DISCHARGED HOME')]")).isDisplayed();
    assertTrue(isDisplayed == true);
  }

  @Test
  public void test04AssertMedicareYearRange() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            newMedicareYear
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
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), operatingDshAdjustmentFactor);
  }

  @Test
  public void test08AssertCapitalDshAdjustmentFactorFieldUpdatedValue() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), capitalDshAdjustmentFactor);
  }

  @Test
  public void test09AssertColaFactorFieldUpdatedValue() { //this test supports ADS-2603
    assertFalse(editModelMap.getGeneralSectionColaFactorField().isDisplayed());
    assertFalse(editModelMap.getGeneralSectionLocationDropdown().isDisplayed());

    //assertThatFieldValue(editModelMap.getGeneralSectionColaFactorField(), colaFactor);
  }

  @Test
  public void test10ChangeMedicareYearToPreviousYearAndVerifyProperFieldsDisplay() throws InterruptedException {
    waitForAjaxExtJs();
    doChangeMedicareYearTo(previousMedicareYear);
    assertElementIsDisplayedWithXpath("//label[text()='Industry Classification Scheme']");
    assertElementIsDisplayedWithXpath("//label[text()='Read Alternative DRG']");
    assertElementIsDisplayedWithXpath("//label[text()='Discharge Status Code for Transfers']");
    assertElementIsDisplayedWithXpath("//label[text()='Medicare Year']");
    assertElementIsDisplayedWithXpath("//label[text()='Operating IME Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital IME Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Operating DSH Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital DSH Adjustment Factor']");
  }

  @Test
  public void test11AssertIndustryClassificationSchemeFieldUpdatedValueAfterClickingContinueAndCloseButton() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdown(),
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdownMenu(),
            expectedIndustryClassificationScheme
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test12AssertReadAlternativeDrgDropdownUpdatedValueAfterClickingContinueAndCloseButton() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionReadAlternativeDrgDropdown(),
            editModelMap.getGeneralSectionReadAlternativeDrgDropdownMenu(),
            expectedReadAlternativeDrg
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test13AssertDischargeStatusCodeForTransfersFieldSelectedValuePersistsAfterClickingContinueAndCloseButton() {
    boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'DISCHARGED HOME')]")).isDisplayed();
    assertTrue(isDisplayed == true);
  }

  @Test
  public void test14AssertMedicareYearRangeAfterClickingContinueAndCloseButton() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            previousMedicareYear
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test15AssertOperatingImeAdjustmentFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingImeAdjustmentFactorField(), expectedOperatingImeAdjustmentFactor);
  }

  @Test
  public void test16AssertCapitalImeAdjustmentFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalImeAdjustmentFactorField(), expectedCapitalImeAdjustmentFactor);
  }

  @Test
  public void test17AssertOperatingDshAdjustmentFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), operatingDshAdjustmentFactor);
  }

  @Test
  public void test18AssertCapitalDshAdjustmentFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), capitalDshAdjustmentFactor);
  }

  @Test
  public void test19AssertColaFactorFieldUpdatedValueAfterClickingContinueAndCloseButton() {  //this test supports ADS-2603
    assertFalse(editModelMap.getGeneralSectionColaFactorField().isDisplayed());
    assertFalse(editModelMap.getGeneralSectionLocationDropdown().isDisplayed());

    //assertThatFieldValue(editModelMap.getGeneralSectionColaFactorField(), colaFactor);
  }

  @Test
  public void test20ChangeMedicareYearToNewYearAndVerifyProperFieldsDisplay() throws InterruptedException {
    waitForAjaxExtJs();
    doChangeMedicareYearTo(newMedicareYear);
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[text()='Industry Classification Scheme']");
    assertElementIsDisplayedWithXpath("//label[text()='Read Alternative DRG']");
    assertElementIsDisplayedWithXpath("//label[text()='Discharge Status Code for Transfers']");
    assertElementIsDisplayedWithXpath("//label[text()='Medicare Year']");
    assertElementIsDisplayedWithXpath("//label[text()='Operating IME Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital IME Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Operating DSH Adjustment Factor']");
    assertElementIsDisplayedWithXpath("//label[text()='Capital DSH Adjustment Factor']");
    //assertElementIsDisplayedWithXpath("//label[text()='Location and COLA Factor']");
  }

  @Test
  public void test21AssertIndustryClassificationSchemeFieldUpdatedValueAfterClickingSaveButton() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdown(),
            editModelMap.getGeneralSectionIndustryClassificationSchemeDropdownMenu(),
            expectedIndustryClassificationScheme
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test22AssertReadAlternativeDrgDropdownUpdatedValueAfterClickingSaveButton() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionReadAlternativeDrgDropdown(),
            editModelMap.getGeneralSectionReadAlternativeDrgDropdownMenu(),
            expectedReadAlternativeDrg
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test23AssertDischargeStatusCodeForTransfersFieldSelectedValuePersistsAfterClickingSaveButton() {
    boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'DISCHARGED HOME')]")).isDisplayed();
    assertTrue(isDisplayed == true);
  }

  @Test
  public void test24AssertMedicareYearRangeAfterClickingSaveButton() throws InterruptedException {
    assertThatDropdownSelectedValue(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            newMedicareYear
    );
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test25AssertOperatingImeAdjustmentFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingImeAdjustmentFactorField(), expectedOperatingImeAdjustmentFactor);
  }

  @Test
  public void test26AssertCapitalImeAdjustmentFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalImeAdjustmentFactorField(), expectedCapitalImeAdjustmentFactor);
  }

  @Test
  public void test27AssertOperatingDshAdjustmentFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionOperatingDshAdjustmentFactorField(), operatingDshAdjustmentFactor);
  }

  @Test
  public void test28AssertCapitalDshAdjustmentFactorFieldUpdatedValueAfterClickingSaveButton() {
    assertThatFieldValue(editModelMap.getGeneralSectionCapitalDshAdjustmentFactorField(), capitalDshAdjustmentFactor);
  }

  @Test
  public void test29AssertColaFactorFieldUpdatedValueAfterClickingSaveButton() { //this test supports ADS-2603
    assertFalse(editModelMap.getGeneralSectionColaFactorField().isDisplayed());
    assertFalse(editModelMap.getGeneralSectionLocationDropdown().isDisplayed());

    //assertThatFieldValue(editModelMap.getGeneralSectionColaFactorField(), colaFactor);
  }
}
