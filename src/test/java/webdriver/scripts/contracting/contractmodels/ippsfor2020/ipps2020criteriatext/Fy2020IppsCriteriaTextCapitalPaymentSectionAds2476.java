package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020criteriatext;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsCriteriaTextCapitalPaymentSectionAds2476 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Criteria Text";
  private static final String serviceModel = "OPPS 2019";
  private static final String testSection = "Capital Payment";
  String newMedicareYear = "Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";

  /**
   * Automates test ticket ADS-2476-Capital Payment Section. Dev Story ADS-1405.
   * Verifies text in Criteria box displays proper data for Medicare years 2020 (new ui)
   * and 2019 and before (previous ui) after changing the year.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsCriteriaTextCapitalPaymentSectionAds2476.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection(testSection);
  }

  //Expected Values
  static final String expectedCapitalGeographicAdjFactorField = getRandomStringAndSetDecimalPlaces(-999, 999, 4, printout);
  static final String expectedNationalCapitalRate = getRandomStringAndSetDecimalPlaces(-999999, 999999, 2, printout);

  String[] expectedCriteriaTextNew = {
      "Medicare Inpatient PPS: " + newMedicareYear,
      "Geographic Adj Factor: " + expectedCapitalGeographicAdjFactorField,
      "Capital National Rate: " + expectedNationalCapitalRate,
  };

  String[] expectedCriteriaTextPrevious = {
      "Medicare Inpatient PPS: " + previousMedicareYear,
      "Geographic Adj Factor: " + expectedCapitalGeographicAdjFactorField,
      "Capital National Rate: " + expectedNationalCapitalRate,
  };

  @Test
  public void test00SetAllFieldValues() {
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField(), expectedCapitalGeographicAdjFactorField, printout);
    doUpdateFormFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField(), expectedNationalCapitalRate, printout);
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
  public void test04aClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlockForMedicare2020() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextNew,
            printout
    );
  }

  @Test
  public void test04bClickEditButtonToReopenEditDialogAndUpdateMedicareYear() throws InterruptedException {
    doClickEditButtonToReopenEditDialog();
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> op = getListOfSections(testSection);
    //List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='" + testSection + "']/parent::div"));
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
  public void test10ClickContinueAndCloseButtonAndAssertPricingMethodsCriteriaTextBlockFor2019() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    editModelMap.getEditContractMainPageCriteriaTextArea().click();
    assertThatTextAreaContainsExpectedText(
            editModelMap.getEditContractMainPageCriteriaTextArea(),
            expectedCriteriaTextPrevious,
            printout
    );
  }

}
