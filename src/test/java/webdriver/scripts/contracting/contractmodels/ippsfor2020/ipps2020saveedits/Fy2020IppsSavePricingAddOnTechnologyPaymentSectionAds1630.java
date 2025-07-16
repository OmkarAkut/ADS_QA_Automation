package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020saveedits;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsSavePricingAddOnTechnologyPaymentSectionAds1630 extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static ModelLibraryMap modelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";

  //Expected Values
  String previouslySelectedService = "# 03 Encounter";
  String serviceMaximum = "867.55";

  /**
   * Automates test ticket ADS-1630.  Dev Story ADS-1501 and 1494.
   * User updates field values and they should remain after clicking Continue & Close.
   * Also, values should persist after clicking Save on main page.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("Test Class: " + Fy2020IppsSavePricingAddOnTechnologyPaymentSectionAds1630.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Add On Technology Payment");


//    goToPage("Contract Models");
//    waitForSpinnerToEnd();
//    waitForAjaxExtJs();
//    displayPricingMethodsSectionForContractModelAndServiceModel(contractModel, serviceModel);
//    waitForAjaxExtJs();
//    driver.findElement(By.xpath("//*[text()='Edit']")).click();
//    waitForAjaxExtJs();
//    navigateCloseGeneralSectionOpenNewSection("Add On Technology Payment");
  }

  @Test
  public void test01AssertPreviouslySelectedServiceIsDisplayed() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'" + previouslySelectedService + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test02AssertPreviouslySelectedServiceMaximumIsDisplayed() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-numbercolumn')]/div[contains(text(),'" + serviceMaximum + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test05ClickContinueAndCloseButtonThenReopenEditDialog() throws InterruptedException {
    doClickCloseAndContinueButtonThenReopenEditDialog();
    navigateCloseGeneralSectionOpenNewSection("Add On Technology Payment");
  }

  @Test
  public void test11AssertPreviouslySelectedServiceIsDisplayedAfterClickingContinueAndCloseButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'" + previouslySelectedService + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test12AssertPreviouslySelectedServiceMaximumIsDisplayedAfterClickingContinueAndCloseButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-numbercolumn')]/div[contains(text(),'" + serviceMaximum + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test15SaveUpdatedFieldsAndCloseThenReopenEditDialog() throws InterruptedException {
    doCloseEditSectionAndClickSaveButton(serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Add On Technology Payment");
  }

  @Test
  public void test16AssertPreviouslySelectedServiceIsDisplayedAfterClickingSaveButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'" + previouslySelectedService + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test17AssertPreviouslySelectedServiceMaximumIsDisplayedAfterClickingSaveButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-numbercolumn')]/div[contains(text(),'" + serviceMaximum + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

}
