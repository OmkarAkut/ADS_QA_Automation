package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
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
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IppsUi2020LocationAndColaFactorFunctionalityTestAds2603WasAds1624 extends ContractModelsHelper {

  private static ModelLibraryMap modelMap;
  private static final String contractModel = "AFT IPPS 2020";
  private static final String serviceModel = "OPPS 2019";

  /**Automates sections of ADS-2603.  This functionality was previously in
   * test ticket ADS-1624, but has been superceded.  Sections of Dev Story ADS-1321.
   * Automates updates to Operating Payment section, Location and COLA Factor section functionality.
   * Previously these fields were in the General section.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("Test Class: " + IppsUi2020LocationAndColaFactorFunctionalityTestAds2603WasAds1624.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  @Test
  public void test10AssertLocationAndColaFactorFieldLabelIsDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[text()='Location and Operating COLA Factor']");
  }

  @Test
  public void test11AssertLocationDropdownAndColaFactorFieldAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertTrue(driver.findElement(By.xpath("//input[@name='location']")).isDisplayed());
    assertTrue(driver.findElement(By.xpath("//input[@name='colaOperatingFactor']")).isDisplayed());
  }

  @Test
  public void test12AssertLocationCountyOfHawaiiColaFactorDefaultValueIs1dot21() throws InterruptedException {
    waitForAjaxExtJs();
    doClick(driver.findElement(By.name("colaOperatingFactor")));
    waitForAjaxExtJs();
    String optionText = "County of Hawaii";
    String expectedText = "1.21";
    doClick(driver.findElement(By.name("location")));
    Thread.sleep(1000);
    List<WebElement> menu = driver.findElement(By.xpath("//div[contains(@class,'boundlist')]/div/ul")).findElements(By.tagName("li"));
    for(WebElement option : menu) {
      if(option.getText().equals(optionText)) {
        option.click();
        break;
      }
    }
    assertFieldDefaultValue(driver.findElement(By.name("colaOperatingFactor")), expectedText);
  }

  @Test
  public void test13AssertLocationAlaskaColaFactorDefaultValueIs1dot25() throws InterruptedException {
    waitForAjaxExtJs();
    String optionText = "Alaska";
    String expectedText = "1.25";
    doDropdownSelectUsingOptionText(driver.findElement(By.name("location")), driver.findElement(By.xpath("//div[contains(@class,'boundlist')]/div/ul")), optionText);
    assertFieldDefaultValue(driver.findElement(By.name("colaOperatingFactor")), expectedText);
  }

  @Test
  public void test14AssertLocationHawaiiOtherThanCountyOfHawaiiColaFactorDefaultValueIs1dot25() throws InterruptedException {
    waitForAjaxExtJs();
    String optionText = "Hawaii other than County of Hawaii";
    String expectedText = "1.25";
    doDropdownSelectUsingOptionText(driver.findElement(By.name("location")), driver.findElement(By.xpath("//div[contains(@class,'boundlist')]/div/ul")), optionText);
    assertFieldDefaultValue(driver.findElement(By.name("colaOperatingFactor")), expectedText);
  }

  @Test
  public void test15AssertLocationOutsideAlaskaAndHawaiiColaFactorDefaultValueIs1dot0() throws InterruptedException {
    waitForAjaxExtJs();
    String optionText = "Outside Alaska and Hawaii";
    String expectedText = "1.0";
    doDropdownSelectUsingOptionText(driver.findElement(By.name("location")), driver.findElement(By.xpath("//div[contains(@class,'boundlist')]/div/ul")), optionText);
    assertFieldDefaultValue(driver.findElement(By.name("colaOperatingFactor")), expectedText);
  }

  @Test
  public void test16AssertUserUpdatedColaFactorFieldValueIsDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    String newValue = "1.21";
    doUpdateFormFieldValue(driver.findElement(By.name("colaOperatingFactor")), newValue, printout);
    assertFieldDefaultValue(driver.findElement(By.name("colaOperatingFactor")), newValue);
  }

  @Test
  public void test17AssertLocationSelectionHasNotChangedAfterManuallyUpdatedColaFactor() throws InterruptedException {
    waitForAjaxExtJs();
    String expectedText = "Outside Alaska and Hawaii";
    driver.findElement(By.name("location")).click();
    waitForAjaxExtJs();
    WebElement listElement = driver.findElement(By.xpath("//div[contains(@class,'boundlist')]/div/ul"));
    List<WebElement> list = listElement.findElements(By.tagName("li"));
    for (WebElement item : list) {
      String clss = item.getAttribute("class");
      if (clss.contains("selected")) {
        assertThat(item.getText(), equalTo(expectedText));
        break;
      }
    }
    driver.findElement(By.name("colaOperatingFactor")).click();
  }

  @Test
  public void test18AssertLocationHawaiiOtherThanCountyOfHawaiiColaFactorDefaultValueIs1dot25AfterManuallyUpdatingFactorValueForAnotherLocation() throws InterruptedException {
    waitForAjaxExtJs();
    String optionText = "Hawaii other than County of Hawaii";
    String expectedText = "1.25";
    doDropdownSelectUsingOptionText(driver.findElement(By.name("location")), driver.findElement(By.xpath("//div[contains(@class,'boundlist')]/div/ul")), optionText);
    assertFieldDefaultValue(driver.findElement(By.name("colaOperatingFactor")), expectedText);
  }
}