package webdriver.helpers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ContractModelsHelper extends GoHelper {

  private static ModelLibraryMap modelMap;
  private static EditContractingModelMap editModelMap;

  /** Helper Class for Contract Models pages - individual test scripts should extend this one to use it.
   */
  @BeforeClass
  public static void setupHelper() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
  }

  @AfterClass
  public static void closeContractingTab() throws InterruptedException {
    int index;
    try {
      System.out.println("Closing page on lower bar");
      doClosePageOnLowerBar("AFT");
      try {
        driver.findElement(By.xpath("//span[text()='Warning']/ancestor::div/descendant::button/span[text()='Cancel & Close']")).click();
        waitForAjaxExtJs();
      } catch (Throwable ee) {}
    } catch (Throwable e) {  //remove below if no longer needed - if all tests pass
//      List<WebElement> elements = getListOfAllOfSameElementWhenMoreThanOneOnPage("//*[text() = 'Cancel & Close']");
//      index = elements.size();
//      if (index == 0) {
//        fail("Element not found");
//      } else if (index == 2) {
//        getListOfSameElementAndSelectOne("//*[text() = 'Cancel & Close']", index - 1).click();
//        doClosePageOnLowerBar("AFT");
//      } else if (index > 2) {
//        getListOfSameElementAndSelectOne("//*[text() = 'Cancel & Close']", index - 1).click();
//        getListOfSameElementAndSelectOne("//*[text() = 'Cancel & Close']", index - 2).click();
//        doClosePageOnLowerBar("AFT");
//      } else {
//      }
    }
  }

  public List<WebElement> getListOfSections(String testSection) {
    List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='" + testSection + "']/parent::div/following-sibling::div/img"));
    return op;
  }

  public void doChangeMedicareYearTo(String medicareYearRange) throws InterruptedException {
    doDropdownSelectUsingOptionText(
            editModelMap.getGeneralSectionMedicareYearDropdown(),
            editModelMap.getGeneralSectionMedicareYearDropdownMenu(),
            medicareYearRange
    );
  }

  public String calculateOperatingPaymentSectionBlendedRate(double colaOperatingFactor) {
    double natLaborRate = Double.parseDouble(driver.findElement(By.name("nationalOperlaborRate")).getAttribute("value"));
    double wageIndex = Double.parseDouble(driver.findElement(By.name("areaWageIndex")).getAttribute("value"));
    double natNonLaborRate = Double.parseDouble(driver.findElement(By.name("nationalOperNonLaborRate")).getAttribute("value"));
    double natRelativeWeight = 100; //get from db table: MedicareDRGPricingMechanism2 table

    //Calculation Formula = ( (Nat Labor Rate x Wage Index) + (Nat Non-Lab Rate x COLA Operating Factor) ) x (National Relative Weight / 100)
    Double calculatedBlendedRate = ((natLaborRate * wageIndex) + (natNonLaborRate * colaOperatingFactor)) * (natRelativeWeight / 100);
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(calculatedBlendedRate);
    if (printout) {
      System.out.println("Operating Payment section calculated Blended Rate = " + expectedRate);
    }
    return expectedRate;
  }

  public String calculateOperatingPaymentSectionBlendedRateForPriorYears(double hospitalCapitalRate, double hospitalCapitalWeight, double regionalCapitalRate, double regionalCapitalWeight, double federalCapitalRate, double nationalCapitalWeight) {

    //Calculation Formula = (Hospital Capital Rate x (Hospital Capital Weight/100)) + (Regional Capital Rate x (Regional Capital Rate x (Regional Capital Weight/100)) + (Federal Capital Rate x (National Capital Weight/100))
    Double calculatedBlendedRate = (hospitalCapitalRate * (hospitalCapitalWeight/100)) + (regionalCapitalRate * (regionalCapitalRate * (regionalCapitalWeight/100))) + (federalCapitalRate * (nationalCapitalWeight/100));
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(calculatedBlendedRate);
    if (printout) {
      System.out.println("Operating Payment section calculated Blended Rate = " + expectedRate);
    }
    return expectedRate;
  }

  public String calculateOperatingPaymentSectionWageAdjustedFederalRate() {
    //Display-only calculated field: (Nat Labor Rate x Wage Index) + Nat Non-Lab Rate
    double natLaborRate = Double.parseDouble(driver.findElement(By.name("nationalOperlaborRate")).getAttribute("value"));
    double wageIndex = Double.parseDouble(driver.findElement(By.name("areaWageIndex")).getAttribute("value"));
    double natNonLaborRate = Double.parseDouble(driver.findElement(By.name("nationalOperNonLaborRate")).getAttribute("value"));

    //Calculation Formula = ( (Nat Labor Rate x Wage Index) + (Nat Non-Lab Rate x COLA Operating Factor) ) x (National Relative Weight / 100)
    Double calculatedBlendedRate = (natLaborRate * wageIndex) + natNonLaborRate;
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(calculatedBlendedRate);
    if (printout) {
      System.out.println("Operating Payment section calculated Wage Adjusted Federal Rate = " + expectedRate);
    }
    return expectedRate;
  }

  public String calculateOperatingPaymentSectionWageAdjustedFederalRateForPriorYears(double nationalCapitalRate, double capitalGeographicAdjustmentFactor, double largeUrbanAddOnFactor) {
    //Calculation Formula = nationalCapitalRate x capitalGeographicAdjustmentFactor x largeUrbanAddOnFactor
    Double calculatedBlendedRate = nationalCapitalRate * capitalGeographicAdjustmentFactor * largeUrbanAddOnFactor;
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(calculatedBlendedRate);
    if (printout) {
      System.out.println("Operating Payment section calculated Wage Adjusted Federal Rate For Prior Years = " + expectedRate);
    }
    return expectedRate;
  }

  public void doCloseEditSectionAndClickSaveButton(String serviceModel) throws InterruptedException {
    editModelMap.getEditContractMainPageContinueAndCloseButton().click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[text()='Save']")).click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[text()='Continue']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(2000);
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "'][2]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//span[text()='Edit']")).click();
    waitForAjaxExtJs();
    Thread.sleep(2000);
  }

  public String calculateCapitalPaymentBlendedRate(Double colaFactor) {
    Double capGeoAdj = Double.parseDouble(getFieldValue(editModelMap.getCapitalPaymentSectionCapitalGeographicAdjFactorField()));
    Double natCapRate = Double.parseDouble(getFieldValue(editModelMap.getCapitalPaymentSectionNationalCapitalRateField()));
    Double capitalPaymentCalculatedBlendedRate = capGeoAdj * natCapRate * colaFactor;
    DecimalFormat df = new DecimalFormat("#####.##");
    String expectedRate = df.format(capitalPaymentCalculatedBlendedRate);
    if (printout) {
      System.out.println("Operating Payment section calculated Blended Rate = " + expectedRate);
    }
    return expectedRate;
  }

  public void doClickCloseAndContinueButtonThenReopenEditDialog() throws InterruptedException {
    doClickCloseAndContinueButtonOnEditDialog();
    doClickEditButtonToReopenEditDialog();
  }

  public void doClickEditButtonToReopenEditDialog() throws InterruptedException {
    driver.findElement(By.xpath("//*[text()='Edit']")).click();
    waitForAjaxExtJs();
  }

  public void doClickCloseAndContinueButtonOnEditDialog() throws InterruptedException {
    editModelMap.getEditContractMainPageContinueAndCloseButton().click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
  }

  public void assertThatDropdownSelectedValue(WebElement dropdownTriggerElement, WebElement dropdownMenu, String expectedValue) {
    try {
		waitForAjaxExtJs();
		dropdownTriggerElement.click();
		waitForAjaxExtJs();
		List<WebElement> menuList = dropdownMenu.findElements(By.tagName("li"));
		for (WebElement menuItem : menuList) {
		  String clss = menuItem.getAttribute("class");
		  if (clss.contains("selected")) {
		    assertThat(menuItem.getText(), equalTo(expectedValue));
		    break;
		  }
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  public boolean getCheckboxStatus(WebElement element) {
    boolean checkbox;
    String meuStatus = element.getAttribute("class");
    if (meuStatus.contains("checked")) {
      checkbox = true;
    } else {
      checkbox = false;
    }
    return checkbox;
  }

  public String getFieldValue(WebElement element) {
    String elementValue = element.getAttribute("value");
    return elementValue;
  }

  public void assertFieldValue(String elementLocatorXpath, String expectedValue) {
    String defaultValue = driver.findElement(By.xpath(elementLocatorXpath)).getAttribute("value");
    assertThat(defaultValue, equalTo(expectedValue));
  }

  public void assertThatFieldValueContainsString(WebElement element, String expectedValue) {
    String defaultValue = element.getAttribute("value");
    assertThat(defaultValue, containsString(expectedValue));
  }

  public void assertThatFieldValue(WebElement element, String expectedValue) {
    String defaultValue = element.getAttribute("value");
    assertThat(defaultValue, equalTo(expectedValue));
  }

  public void verifyServiceIsDisplayedInTextfieldOnMainPage(String availableService1) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    try {
      boolean isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/div[contains(text(),'" + availableService1 + "')]")).isDisplayed();
      assertTrue(isDisplayed == true);
    } catch (Throwable e) {
      fail("Selected Codes Not Displayed in textfield on main page");
    }
  }

  public int getFilterNumberOfExpectedMatches() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    String filterResultsRatio = driver.findElement(By.xpath("//*[contains(text(),'Filter to Match These Criteria')]")).getText();
    String[] filterResults = filterResultsRatio.split(" ");
    String[] filterResultsValue = filterResults[5].split("/");
    return Integer.parseInt(filterResultsValue[0]);
  }

  public void assertFilterNumberOfExpectedMatches(int numberOfExpectedMatches) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    String filterResultsRatio = driver.findElement(By.xpath("//*[contains(text(),'Filter to Match These Criteria')]")).getText();
    String[] filterResults = filterResultsRatio.split(" ");
    assertTrue(filterResults[5].contains(numberOfExpectedMatches + "/"));
  }

  public String doUpdateFormFieldValueAndGetValueAttribute(WebElement element, String newTestValue, boolean printout) {
    doUpdateFormFieldValue(element, newTestValue, printout);
    String actualValue = element.getAttribute("value");
    return actualValue;
  }

  public void doUpdateFormFieldValue(WebElement element, String newTestValue, boolean printout) {
    element.clear();
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("arguments[0].value='"+newTestValue+"';", element);
  }

  public void assertSelectedPaneValue(String selectedDisplayedValue) {
    String selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText();
    System.out.println("selectedList: " + selectedList);
    Assert.assertThat(selectedList, containsString(selectedDisplayedValue));
  }

  public void assertAvailablePaneValueIsGreaterThan(int availablePaneValue) {
    int availableList = Integer.parseInt(driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]")).getText().substring(0,1));
    System.out.println("available list: " + availableList);
    assertTrue(availableList > availablePaneValue);
  }

  public void assertAvailablePaneValueIsZero() {
    int availableList = Integer.parseInt(driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]")).getText().substring(0,1));
    System.out.println("available list: " + availableList);
    assertTrue(availableList == 0);
  }

  public void assertEnabled(WebElement element) {
    try {
      element.getAttribute("disabled");
      fail("Element is not active: " + element);
    } catch (Throwable e) {
      if (printout) {
        System.out.println(element + " is enabled");
      }
    }
  }

  public void assertReadOnly(WebElement element) {
    try {
      element.getAttribute("disabled");
    } catch (Throwable e) {
      fail("Element is not read-only: " + element);
    }
  }

  public void assertFormFieldExpectedValue(WebElement element, String expectedValue, boolean printout) {
    String actualValueAttribute = element.getAttribute("value");
    if (printout) {
      System.out.println("Form Field Expected Value: " + expectedValue);
      System.out.println("Form Field Actual Value: " + actualValueAttribute);
    }
    MatcherAssert.assertThat(actualValueAttribute, equalTo(expectedValue));
  }

  public void assertFieldDefaultValue(WebElement element, String expectedValue) {
    String actualValueAttribute = element.getAttribute("value");
    MatcherAssert.assertThat(actualValueAttribute, equalTo(expectedValue));
  }

  public void assertValidFieldValues(WebElement element, String[] validExpectedValues) throws InterruptedException {
    for (String value : validExpectedValues) {
      waitForAjaxExtJs();
      element.clear();
      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("arguments[0].value='"+value+"';", element);
      //element.sendKeys(value);
      waitForAjaxExtJs();
      Thread.sleep(1000);
      String actualMessage = element.getAttribute("data-errorqtip");
      System.out.println(actualMessage);
      assertTrue("This value should be valid: " + value, actualMessage.equals(""));
    }
  }

  public void assertInvalidFieldValuesWithClickAwayFromField(WebElement element, WebElement elementToClick, String[] invalidExpectedValues) throws InterruptedException {
    for (String value : invalidExpectedValues) {
      waitForAjaxExtJs();
      element.clear();
      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("arguments[0].value='"+value+"';", element);
      //element.sendKeys(value);
      elementToClick.click();
      waitForAjaxExtJs();
      Thread.sleep(1000);
      String actualInvalidMessage = element.getAttribute("data-errorqtip");
      assertTrue("This value should be invalid: " + value, actualInvalidMessage.contains("The value in this field is invalid"));
    }
  }

  public void assertInvalidFieldValues(WebElement element, String[] invalidExpectedValues) throws InterruptedException {
    for (String value : invalidExpectedValues) {
      waitForAjaxExtJs();
      element.clear();
      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("arguments[0].value='"+value+"';", element);
      //element.sendKeys(value);
      waitForAjaxExtJs();
      Thread.sleep(1000);
      String actualInvalidMessage = element.getAttribute("data-errorqtip");
      assertTrue("This value should be invalid: " + value, actualInvalidMessage.contains("The value in this field is invalid"));
    }
  }

  public static void displayPricingMethodsSectionForContractModelAndServiceModel(String contractModel, String serviceModel) {
    try {
      goToPage("Contract Models");
      doMaximizeWindow();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
      modelMap.getModelLibraryButtonSearch().click();
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
      driver.findElement(By.xpath("//span[text()='Open Task List']")).click();
      //span[contains(@id,'domainlockerrorwindow')]
      checkForModelLibraryUserLock();
      waitForAjaxExtJs();
      Thread.sleep(2000);
      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Model Contract']")).click();
      Thread.sleep(5000);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Define Payment Terms']")).click();
      waitForAjaxExtJs();
      Thread.sleep(5000);
      String feeCheckbox = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Fee For Service Payment Terms']/input")).getAttribute("class");
      assertTrue(feeCheckbox.contains("checked"));
      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Fee For Service Payment Terms']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      System.out.println(5000);
      driver.findElement(By.xpath("//span[contains(@class,'x-panel-header-text')][text()='Pricing Method']/../following-sibling::div")).click();
      waitForAjaxExtJs();
      System.out.println(1000);
      driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "'][2]")).click();
    } catch (Throwable e) {
      e.getMessage();
    }
  }
//
//  public static void navigateToFeeForServicePaymentTermsMainPage(String contractModel) {
//    try {
//      driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
//      modelMap.getModelLibraryButtonSearch().click();
//      waitForAjaxExtJs();
//      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
//      driver.findElement(By.xpath("//span[text()='Open Task List']")).click();
//      //span[contains(@id,'domainlockerrorwindow')]
//      checkForModelLibraryUserLock();
//      waitForAjaxExtJs();
//      Thread.sleep(2000);
//      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Model Contract']")).click();
//      Thread.sleep(5000);
//      waitForSpinnerToEnd();
//      waitForAjaxExtJs();
//      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Define Payment Terms']")).click();
//      waitForAjaxExtJs();
//      Thread.sleep(5000);
//      String feeCheckbox = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Fee For Service Payment Terms']/input")).getAttribute("class");
//      assertTrue(feeCheckbox.contains("checked"));
//      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Fee For Service Payment Terms']")).click();
//      waitForSpinnerToEnd();
//      waitForAjaxExtJs();
//      System.out.println(5000);
//    } catch (Throwable e) {
//      e.getMessage();
//    }
//  }

}
