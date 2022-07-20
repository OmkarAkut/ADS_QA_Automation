package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
public class IppsUi2020WorstPerformingQuartileFunctionalityTestAds1625 extends ContractModelsHelper {

  private static final String serviceModel = "OPPS 2019";
  private static final String contractModel = "AFT IPPS 2020";
  private static EditContractingModelMap editMap;

  /**
   * Automates test ticket ADS-1625.  Sections of Dev Story ADS-1321.
   * Operating Payment section. Updated 10-8-19.
   * Automates Worst-Performing Quartile for HAC functionality.
   * When checkbox is unchecked, default is 0.0.
   * When checked, default is 1.0.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + IppsUi2020WorstPerformingQuartileFunctionalityTestAds1625.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  @Test
  public void test00ExpandOperatingPaymentSectionAndAssertWorstPerformingQuartileForHacAndReductionFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath(editMap.hac);
    assertElementIsDisplayedWithXpath("//label[text()='%']");
  }

  @Test
  public void test01OperatingPaymentSectionAssertWorstPerformingQuartileForHacCheckboxIsDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayed(editMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox(), printout);
  }

  @Test
  public void test02OperatingPaymentSectionAssertReductionFieldDefaultsTo1Dot0WhenChecked() throws InterruptedException {
    waitForAjaxExtJs();
    assertThat(getCheckboxStatus(editMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox()), equalTo(false));
    assertFieldDefaultValue(driver.findElement(By.name("hacReductionPercent")), "0.0");
  }

  @Test
  public void test03OperatingPaymentSectionAssertReductionFieldDefaultsTo0Dot0() throws InterruptedException {
    waitForAjaxExtJs();
    editMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox().click();
    assertThat(getCheckboxStatus(editMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox()), equalTo(false));
    assertFieldDefaultValue(driver.findElement(By.name("hacReductionPercent")), "1.0");
  }

  @Test
  public void test04OperatingPaymentSectionManuallyUpdateReductionPercentageFieldAndAssertUpdatedValueIsDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    String testValue = "2.999";
    String actualValue = doUpdateFormFieldValueAndGetValueAttribute(driver.findElement(By.name("hacReductionPercent")), testValue, printout);
    assertThat(actualValue, equalTo(testValue));
  }

  @Test
  public void test05OperatingPaymentSectionUncheckBoxAndAssertReductionFieldDefaultsTo0Dot0() throws InterruptedException {
    waitForAjaxExtJs();
    editMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox().click();
    assertThat(getCheckboxStatus(editMap.getOperatingPaymentSectionWorstPerformingQuartileForHacReductionCheckbox()), equalTo(false));
    assertFieldDefaultValue(driver.findElement(By.name("hacReductionPercent")), "0.0");
  }
}