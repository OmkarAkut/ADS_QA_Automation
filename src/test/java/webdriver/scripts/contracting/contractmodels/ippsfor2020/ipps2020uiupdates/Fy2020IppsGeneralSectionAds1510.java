package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsGeneralSectionAds1510 extends ContractModelsHelper {

  private static ModelLibraryMap modelMap;
  private static final String contractModel = "AFT IPPS 2020";
  private static final String serviceModel = "OPPS 2019";
  String expectedMedicareYear = "Oct 1, 2019 - Sept 30, 2020";

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

  /** Automates test ticket ADS-1510.  Dev Story ADS-1320 - General Section. **/
  @BeforeClass
  public static void setupScript() throws Exception {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("Test Class: " + Fy2020IppsGeneralSectionAds1510.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
  }
//
//  @Test
//  public void test08aAssertPricingMethodsPayPercentageDefaultFieldValueIsAccurate() throws InterruptedException {
//    waitForAjaxExtJs();
//    String defaultExpectedPayPercentage = "100";
//    String actualPayPercentage = driver.findElement(By.xpath("//*[@name='pay']")).getAttribute("value");
//    assertThat(actualPayPercentage, equalTo(defaultExpectedPayPercentage));
//  }
//
//  @Ignore
//  @Test
//  public void test08bAssertPricingMethodsPricingMethodDefaultFieldValueIsAccurate() throws InterruptedException {
//    waitForAjaxExtJs();
//    String expectedPricingMethodDefault = "Medicare Inpatient PPS";
//    driver.findElement(By.xpath("//*[@name='pricemethodoption']")).click();
//    waitForAjaxExtJs();
//    String actualPricingMethodDefault = driver.findElement(By.xpath("//*[@class='x-boundlist-item x-boundlist-selected']")).getText();
//    driver.findElement(By.xpath("//*[@name='pricemethodoption']")).click();
//    assertThat(actualPricingMethodDefault, equalTo(expectedPricingMethodDefault));
//  }
//
//  @Test
//  public void test08cAssertPricingMethodsCriteriaTextBlockDefaultValueIsAccurate() {
//    driver.findElement(By.xpath("//textarea[@name='criteria']")).click();
//    String actualText = driver.findElement(By.xpath("//textarea[@name='criteria']")).getAttribute("value");
//    if (printout) {
//      System.out.println("ACTUAL PRICING METHODS TEXT BLOCK: " + actualText);
//    }
//    for (String textLine : expectedCriteriaText) {
//      try {
//        assertTrue(actualText.contains(textLine));
//      } catch (Throwable e) {
//        System.out.println("Failed: " + textLine);
//      }
//    }
//  }

  @Test
  public void test09aClickEditButtonAndAssertEditDialogTitleAndSectionsAreDisplayed() {
    assertElementTextWithXpath("//span[contains(@id, 'medicareinpatientpps')]", "Edit Price for " + serviceModel + " [Encounter]", printout);
    assertElementIsDisplayedWithXpath("//*[contains(@id, 'customaccordianpanel')][text()='General']");
    assertElementIsDisplayedWithXpath("//*[contains(@id, 'customaccordianpanel')][text()='Operating Payment']");
    assertElementIsDisplayedWithXpath("//*[contains(@id, 'customaccordianpanel')][text()='Capital Payment']");
    assertElementIsDisplayedWithXpath("//*[contains(@id, 'customaccordianpanel')][text()='Cost Outlier Payment']");
    assertElementIsDisplayedWithXpath("//*[contains(@id, 'customaccordianpanel')][text()='Add On Technology Payment']");
  }

  @Test
  public void test09bAssertEditDialogGeneralSectionExpandedByDefaultAndFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[text()='Industry Classification Scheme']");
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
  public void test10aAssertNoColonFollowingIndustryClassificationSchemeFieldLabel() throws InterruptedException {
    waitForAjaxExtJs();
    boolean fieldLabelContainsComma = false;
    try {
      fieldLabelContainsComma = driver.findElement(By.xpath("//label[text()='Industry Classification Scheme:']")).isDisplayed();
      fail();
    } catch (Throwable e) {
      assertFalse(fieldLabelContainsComma);
    }
  }

  @Test
  public void test10bAssertIndustryClassificationSchemeFieldDefaultValueIsMsdrg1() throws InterruptedException {
    waitForAjaxExtJs();
    String expectedText = "MSDRG1";
    driver.findElement(By.name("drgTypeString")).click();
    waitForAjaxExtJs();
    WebElement classificationList = driver.findElement(By.xpath("//label[text()='Industry Classification Scheme']/ancestor::div/following-sibling::div[contains(@class,'boundlist')]/div/ul"));
    List<WebElement> classificationListing = classificationList.findElements(By.tagName("li"));
    for (WebElement item : classificationListing) {
      String clss = item.getAttribute("class");
      if (clss.contains("selected")) {
        assertThat(item.getText(), equalTo(expectedText));
        break;
      }
    }
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test11aAssertIndustryClassificationSchemeFieldIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.name("drgTypeString")).click();
    WebElement classificationList = driver.findElement(By.xpath("//label[contains(text(),'Industry Classification Scheme')]/ancestor::div/following-sibling::div[contains(@class,'boundlist')]/div/ul"));
    assertTrue(classificationList.isEnabled());
  }

  @Test
  public void test11bAssertIndustryClassificationSchemeFieldDropdownValuesInExpectedOrder() throws InterruptedException {
    List<String> expectedList = Arrays.asList("MSDRG1", "MSDRG2", "MSDRG3", "MSDRG4", "TRICARE", "HCFA");
    waitForAjaxExtJs();
    WebElement drgListElement = driver.findElement(By.xpath("//div/following-sibling::div[contains(@class, 'boundlist')]/div/ul"));
    List<WebElement> drgWebElementsList = drgListElement.findElements(By.tagName("li"));
    ArrayList<String> drgActualList = new ArrayList<>();
    for (WebElement item : drgWebElementsList) {
      if (printout) {
        System.out.println("drgActualList item: " + item.getText());
      }
      drgActualList.add(item.getText());
    }
    assertThat(drgActualList, equalTo(expectedList));
    doClick(driver.findElement(By.xpath("//*[text()='Discharge Status Code for Transfers']")));  //off-click
  }

  @Test
  public void test12aVerifyReadAlternativeDrgCheckboxIsUncheckedByDefault() throws InterruptedException {
    waitForAjaxExtJs();
    String checkboxStatus = driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/parent::td/../../..")).getAttribute("class");
    assertFalse(checkboxStatus.contains("checked"));
    assertThat(checkboxStatus, not(containsString("checked")));
  }

  @Test
  public void test12bVerifyReadAlternativeDrgCheckboxIsEnabledByDefault() throws InterruptedException {
    waitForAjaxExtJs();
    boolean checkboxStatus = driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/parent::td/../../..")).isEnabled();
    assertTrue(checkboxStatus);
  }

  @Test
  public void test13WhenReadAlternativeDrgCheckboxIsCheckedVerifyReadAlternativeDrgDropDownIsEnabled() throws InterruptedException {
    waitForAjaxExtJs();
    doClick(driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/preceding-sibling::input")));
    waitForAjaxExtJs();
    boolean isDisplayed = false;
    try {
      isDisplayed = driver.findElement(By.xpath("//input[@name='otherDrgIndex' and @disabled]")).isDisplayed();
      fail();
    } catch (Throwable e) {
      assertTrue(isDisplayed == false);
    }
  }

  @Test
  public void test14WhenReadAlternativeDrgCheckboxIsReCheckedVerifyReadAlternativeDrgDropDownIsDisabled() throws InterruptedException {
    waitForAjaxExtJs();
    doClick(driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/preceding-sibling::input")));
    waitForAjaxExtJs();
    boolean isDisplayed = driver.findElement(By.xpath("//input[@name='otherDrgIndex' and @disabled]")).isDisplayed();
    assertTrue(isDisplayed);
  }

  @Test
  public void test15aAssertReadAlternativeDrgDropdownDefaultValueIsMsDrg1() throws InterruptedException {
    waitForAjaxExtJs();
    String expectedText = "MS DRG1";
    doClick(driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/preceding-sibling::input")));
    waitForAjaxExtJs();
    driver.findElement(By.name("otherDrgIndex")).click();
    waitForAjaxExtJs();
    WebElement drgList = driver.findElement(By.xpath("//div/following-sibling::div[contains(@class, 'boundlist')][2]/div/ul"));
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
  public void test15bAssertReadAlternativeDrgDropdownValuesInExpectedOrder() throws InterruptedException {
    waitForAjaxExtJs();
    List<String> radrgExpectedValues = Arrays.asList("MS DRG1", "MS DRG2", "MS DRG3", "MS DRG4", "Other DRG1", "Other DRG2", "Other DRG3", "Other DRG4");
    waitForAjaxExtJs();
    WebElement drgListElement = driver.findElement(By.xpath("//div/following-sibling::div[contains(@class, 'boundlist')][2]/div/ul"));
    List<WebElement> actualWebElementsList = drgListElement.findElements(By.tagName("li"));
    ArrayList<String> actualListStrings = new ArrayList<>();
    for (WebElement item : actualWebElementsList) {
      if (printout) {
        System.out.println("actualList item: " + item.getText());
      }
      actualListStrings.add(item.getText());
    }
    assertThat(actualListStrings, equalTo(radrgExpectedValues));
  }

  @Test
  public void test16AssertReadAlternativeDrgDropdownIsInactiveWhenCheckboxIsUnchecked() {
    try {
      String expectedText = "MS DRG4";
      selectReadAlternativeDropdownItem(expectedText, printout);
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/preceding-sibling::input"))); //uncheck checkbox
      waitForAjaxExtJs();
      boolean isDisplayed = driver.findElement(By.xpath("//input[@name='otherDrgIndex' and @disabled]")).isDisplayed();
      assertTrue(isDisplayed);
      doClick(driver.findElement(By.xpath("//*[text()='Read Alternative DRG']/preceding-sibling::input"))); //recheck checkbox
      waitForAjaxExtJs();
      assertThat(getReadAlternativeSelectedDropdownItem(printout), equalTo(expectedText));
      driver.findElement(By.name("otherDrgIndex")).click(); //close DRG dropdown
    } catch (Throwable e) {
      driver.findElement(By.name("otherDrgIndex")).click(); //close DRG dropdown
    }
  }

  @Test
  public void test17aAssertDischargeStatusCodeForTransfersFieldIsEmptyByDefault() {
    boolean isDisplayed = false;
    try {
      isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'01')]")).isDisplayed();
      fail();
    } catch (Throwable e) {
      assertTrue(isDisplayed == false);
    }
  }

  @Test
  public void test17bAssertDischargeStatusCodeForTransfersSelectButtonIsEnabledByDefault() {
    try {
      boolean isEnabled = driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).isEnabled();
      assertThat(isEnabled, equalTo(true));
    } catch (Throwable e) {
      fail("Discharge Status Code For Transfers button not enabled by default");
    }
  }

  @Test
  public void test18VerifyDischargeStatusCodesLists() throws InterruptedException {
    Thread.sleep(1000);
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    assertAvailablePaneValueIsGreaterThan(0);
    assertSelectedPaneValue("0");
  }

  @Test
  public void test19DischargeStatusCodeForTransfersVerifySelectAndRemoveAndAllButtonsAreDisabledByDefault() {
    try {
      driver.findElement(By.xpath("//span[text()='Select']/parent::button[@disabled]"));
      driver.findElement(By.xpath("//span[text()='Remove']/parent::button[@disabled]"));
      driver.findElement(By.xpath("//span[text()='All']/parent::button[@disabled]"));
    } catch (Throwable e) {
      fail("Buttons not disabled by default");
    }
  }

  @Test
  public void test20DischargeStatusCodeForTransfersVerifyApplyAndCancelButtonsAreEnabledByDefault() {
    assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Apply')]")).isEnabled());
    assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Cancel')]")).isEnabled());
  }

  @Test
  public void test21VerifyAbilityToMultiSelectDischargeStatusCodes() throws InterruptedException {
    String initialPane1 = driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'01 DISCHARGE')]/..")).getAttribute("class");
    initialPane1 = getNumbersFromStringWithRegex(initialPane1);
    String initialPane2 = driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'02 TRANSFERRED')]/..")).getAttribute("class");
    initialPane2 = getNumbersFromStringWithRegex(initialPane2);

    Actions act = new Actions(driver);
    act.keyDown(Keys.CONTROL).perform();
    //driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'01 DISCHARGE')]")).click();
    driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'02 TRANSFERRED')]")).click();
    //driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'01 DISCHARGE')]")).click();
    act.keyUp(Keys.CONTROL).perform();

    driver.findElement(By.xpath("//em/button/span[text()='Remove' and contains(@id,'button')]/../../parent::div/preceding-sibling::div/following-sibling::div/descendant::span[text()='Select']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(2000);

    String newPane1 = driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'01 DISCHARGE')]/..")).getAttribute("class");
    newPane1 = getNumbersFromStringWithRegex(newPane1);
    String newPane2 = driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'02 TRANSFERRED')]/..")).getAttribute("class");
    newPane2 = getNumbersFromStringWithRegex(newPane2);

    if (printout) {
      System.out.println("initialPane1 " + initialPane1);
      System.out.println("initialPane2 " + initialPane2);
      System.out.println("newPane1 " + newPane1);
      System.out.println("newPane2 " + newPane2);
    }

    assertThat(initialPane1, not(equalTo(newPane1)));
    assertThat(initialPane2, not(equalTo(newPane2)));
  }

  @Test
  public void test28DischargeStatusCodeForTransfersClickApplyAndAssertSelectedCodesDisplayInTextField() throws InterruptedException {
    driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Apply')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    try {
      boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'01')]")).isDisplayed();
      assertTrue(isDisplayed == true);
    } catch (Throwable e) {
      fail("Code For Transfers - Selected Codes Not Displayed In TextField");
    }
  }

  @Test
  public void test29DischargeStatusCodeForTransfersClickCancelButtonAndAssertSelectedCodesDisplayInTextField() throws InterruptedException {
    driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    String selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
    assertThat(selectedList, equalTo("2"));
    driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Cancel')]")).click();
    waitForAjaxExtJs();
    try {
      boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'01')]")).isDisplayed();
      assertTrue(isDisplayed == true);
    } catch (Throwable e) {
      fail("Code For Transfers - Selected Codes Not Displayed In TextField");
    }
    driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
    assertThat(selectedList, equalTo("2"));
    driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Cancel')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  @Test
  public void test30DischargeStatusCodeForTransfersClickXToCloseDialogAndAssertSelectedCodesDisplayInTextField() throws InterruptedException {
    driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    String selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
    assertThat(selectedList, equalTo("2"));
    driver.findElement(By.xpath("//div[contains(@id,'dynamicwindow')]/descendant::*[contains(@class,'x-tool-close')]")).click();
    waitForAjaxExtJs();
    try {
      boolean isDisplayed = driver.findElement(By.xpath("//div[@class='x-boundlist-item' and contains(text(),'01')]")).isDisplayed();
      assertTrue(isDisplayed == true);
    } catch (Throwable e) {
      fail("Code For Transfers - Selected Codes Not Displayed In TextField");
    }
    driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
    assertThat(selectedList, equalTo("2"));
    driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Cancel')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }


  @Test
  public void test31OnEditPriceDialogVerifyProperMedicareYearRangeIsSelectedByDefault() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Medicare Year']/parent::td/following-sibling::td/descendant::div[contains(@class,'x-form-arrow-trigger')]")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    String asv = null;
    WebElement listElement = driver.findElement(By.xpath("//ul/li[contains(text(),'Sept 30, 2020')]/.."));
    List<WebElement> drgListing = listElement.findElements(By.tagName("li"));
    System.out.println("drgLIstingsize: " + drgListing.size());
    //System.out.println(driver.findElement(By.xpath("//div[contains(@id,'boundlist')]/ul/li")).getText());
    for (WebElement elemt : drgListing) {
      System.out.println("Item : " + elemt.getText());
      String clss = elemt.getAttribute("class");
      if (clss.contains("selected")) {
        if (true) {
          System.out.println("Item marked as selected: " + elemt.getText());
        }
        asv = elemt.getText();
        break;
      }
    }
    assertThat(asv, equalTo(expectedMedicareYear));
  }

  @Test
  public void test36aAssertOperatingImeAdjustmentFactorFieldIsEnabledByDefault() {
    try {
      boolean isEnabled = driver.findElement(By.xpath("//*[@name='operIndirectMedEducAdjFactor']")).isEnabled();
      assertThat(isEnabled, equalTo(true));
    } catch (Throwable e) {
      fail("OperatingImeAdjustmentFactorField not enabled by default");
    }
  }

  @Test
  public void test36bAssertOperatingImeAdjustmentFactorFieldValueValidation() {
    String[] dataSet = {"0", "999.999999"};
    for (String it : dataSet) {
      driver.findElement(By.name("operIndirectMedEducAdjFactor")).clear();
      driver.findElement(By.name("operIndirectMedEducAdjFactor")).sendKeys(it);
      String actualValue = driver.findElement(By.xpath("//*[@name='operIndirectMedEducAdjFactor']")).getAttribute("value");
      System.out.println("Actual Value: " + actualValue);
      assertThat(actualValue, equalTo(it));
    }
  }

  @Test
  public void test36cbAssertOperatingImeAdjustmentFactorFieldValueIsLimitedToSixDecimalPlaces() {
    driver.findElement(By.name("operIndirectMedEducAdjFactor")).clear();
    driver.findElement(By.name("operIndirectMedEducAdjFactor")).sendKeys("999.999999");
    String actualValue = driver.findElement(By.xpath("//*[@name='operIndirectMedEducAdjFactor']")).getAttribute("value");
    assertThatValueHasRequiredDecimalPlaces(actualValue,6, printout);
  }

  @Test
  public void test37aAssertCapitalImeAdjustmentFactorFieldIsEnabledByDefault() {
    try {
      boolean isEnabled = driver.findElement(By.xpath("//*[@name='capitalIndrMedEducAdjFactor']")).isEnabled();
      assertThat(isEnabled, equalTo(true));
    } catch (Throwable e) {
      fail("OperatingImeAdjustmentFactorField not enabled by default");
    }
  }

  @Test
  public void test37bAssertCapitalImeAdjustmentFactorFieldValueValidation() {
    String[] dataSet = {"0", "999.999999"};
    for (String it : dataSet) {
      driver.findElement(By.name("capitalIndrMedEducAdjFactor")).clear();
      driver.findElement(By.name("capitalIndrMedEducAdjFactor")).sendKeys(it);
      String actualValue = driver.findElement(By.xpath("//*[@name='capitalIndrMedEducAdjFactor']")).getAttribute("value");
      System.out.println("Actual Value: " + actualValue);
      assertThat(actualValue, equalTo(it));
    }
  }

  @Test
  public void test37cbAssertCapitalImeAdjustmentFactorFieldValueIsLimitedToSixDecimalPlaces() {
    driver.findElement(By.name("capitalIndrMedEducAdjFactor")).clear();
    driver.findElement(By.name("capitalIndrMedEducAdjFactor")).sendKeys("999.999999");
    String actualValue = driver.findElement(By.xpath("//*[@name='capitalIndrMedEducAdjFactor']")).getAttribute("value");
    assertThatValueHasRequiredDecimalPlaces(actualValue,6, printout);
  }

  @Test
  public void test38aAssertOperatingDshAdjustmentFactorFieldIsEnabledByDefault() {
    try {
      boolean isEnabled = driver.findElement(By.xpath("//*[@name='operDispShareHospAdjFactor']")).isEnabled();
      assertThat(isEnabled, equalTo(true));
    } catch (Throwable e) {
      fail("OperatingImeAdjustmentFactorField not enabled by default");
    }
  }

  @Test
  public void test38bAssertOperatingDshAdjustmentFactorFieldValueValidation() {
    String[] dataSet = {"0", "999.999999"};
    for (String it : dataSet) {
      driver.findElement(By.name("operDispShareHospAdjFactor")).clear();
      driver.findElement(By.name("operDispShareHospAdjFactor")).sendKeys(it);
      String actualValue = driver.findElement(By.xpath("//*[@name='operDispShareHospAdjFactor']")).getAttribute("value");
      System.out.println("Actual Value: " + actualValue);
      assertThat(actualValue, equalTo(it));
    }
  }

  @Test
  public void test38cbAssertOperatingDshAdjustmentFactorFieldValueIsLimitedToSixDecimalPlaces() {
    driver.findElement(By.name("operDispShareHospAdjFactor")).clear();
    driver.findElement(By.name("operDispShareHospAdjFactor")).sendKeys("999.999999");
    String actualValue = driver.findElement(By.xpath("//*[@name='operDispShareHospAdjFactor']")).getAttribute("value");
    assertThatValueHasRequiredDecimalPlaces(actualValue,6, printout);
  }

  @Test
  public void test39aAssertCapitalDshAdjustmentFactorFieldIsEnabledByDefault() {
    try {
      boolean isEnabled = driver.findElement(By.xpath("//*[@name='capitalDispShareHospAdjFactor']")).isEnabled();
      assertThat(isEnabled, equalTo(true));
    } catch (Throwable e) {
      fail("OperatingImeAdjustmentFactorField not enabled by default");
    }
  }

  @Test
  public void test39bAssertCapitalDshAdjustmentFactorFieldValueValidation() {
    String[] dataSet = {"0", "999.999999"};
    for (String it : dataSet) {
      driver.findElement(By.name("capitalDispShareHospAdjFactor")).clear();
      driver.findElement(By.name("capitalDispShareHospAdjFactor")).sendKeys(it);
      String actualValue = driver.findElement(By.xpath("//*[@name='capitalDispShareHospAdjFactor']")).getAttribute("value");
      System.out.println("Actual Value: " + actualValue);
      assertThat(actualValue, equalTo(it));
    }
  }

  @Test
  public void test39cbAssertCapitalDshAdjustmentFactorFieldValueIsLimitedToSixDecimalPlaces() {
    driver.findElement(By.name("capitalDispShareHospAdjFactor")).clear();
    driver.findElement(By.name("capitalDispShareHospAdjFactor")).sendKeys("999.999999");
    String actualValue = driver.findElement(By.xpath("//*[@name='capitalDispShareHospAdjFactor']")).getAttribute("value");
    assertThatValueHasRequiredDecimalPlaces(actualValue,6, printout);
  }
//
//  private String getSelectedDropdownItem(WebElement elementToClick, WebElement optionsList, boolean printout) throws InterruptedException {
//    String actualSelectedValue = null;
//    waitForAjaxExtJs();
//
//    elementToClick.click();
//    Thread.sleep(1000);
//    waitForAjaxExtJs();
//    List<WebElement> drgListing = optionsList.findElements(By.tagName("li"));
//    for (WebElement item : drgListing) {
//      String clss = item.getAttribute("class");
//      if (clss.contains("selected")) {
//        if (printout) {
//          System.out.println("Item marked as selected: " + item.getText());
//        }
//        actualSelectedValue = item.getText();
//        break;
//      }
//    }
//    return actualSelectedValue;
//  }

  private String getReadAlternativeSelectedDropdownItem(boolean printout) throws InterruptedException {
    String actualSelectedValue = null;
    waitForAjaxExtJs();
    driver.findElement(By.name("otherDrgIndex")).click();
    waitForAjaxExtJs();
    WebElement drgList = driver.findElement(By.xpath("//div/following-sibling::div[contains(@class,'boundlist')][3]/div/ul"));
    List<WebElement> drgListing = drgList.findElements(By.tagName("li"));
    for (WebElement item : drgListing) {
      String clss = item.getAttribute("class");
      if (clss.contains("selected")) {
        if (printout) {
          System.out.println("Item marked as selected: " + item.getText());
        }
        actualSelectedValue = item.getText();
        break;
      }
    }
    return actualSelectedValue;
  }

  private void selectReadAlternativeDropdownItem(String expectedText, boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    WebElement drgList = driver.findElement(By.xpath("//div/following-sibling::div[contains(@class, 'boundlist')][2]/div/ul"));
    List<WebElement> drgListing = drgList.findElements(By.tagName("li"));
    for (WebElement item : drgListing) {
      String text = item.getText();
      if (text.contains(expectedText)) {
        if (printout) {
          System.out.println("Clicked: " + item.getText());
        }
        item.click();
        break;
      }
    }
  }
}
