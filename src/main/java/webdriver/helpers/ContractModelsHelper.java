package webdriver.helpers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webdriver.corehelpers.AdsHelper;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ContractModelsHelper extends GoHelper {

  private static ModelLibraryMap modelMap;
  private static EditContractingModelMap editModelMap;
  private static ContractingMap contractMap;


  /** Helper Class for Contract Models pages - individual test scripts should extend this one to use it.
   */
  @BeforeClass
  public static void setupHelper() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    contractMap=BuildMap.getInstance(driver, ContractingMap.class);
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
  public static void selectMultipleColumnsToDisplayUser(String[] columnsToSelect) throws InterruptedException,Throwable{
	    for (String selectedColumns: columnsToSelect) {
	    	System.out.println(selectedColumns);
	      highlightColumnsToDisplayColumn(selectedColumns);
//	      Omkar 19/5/2023 : xpath changes for 11.2
//	      doClick("(//div[contains(@class,'x-window-header-draggable')]//following::span[text()='Select']//parent::button)[3]");
	      doClick("//div[contains(@class,'x-container x-box-item x-container-default x-box-layout-ct')]//span[text()='Select']/..");
	      assertColumnsToDisplayColumnIsSelected(selectedColumns);
	      Thread.sleep(300);
	    }
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
//Shilpa 12.22.2022
  static ContractingMap selectColumn =BuildMap.getInstance(driver, ContractingMap.class);
	 private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);

	public static void highlightColumnsToDisplayColumn(String column) throws InterruptedException,Throwable {
	    String columnPath = "//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='"+column+"']";
	   //Shilpa 02.09.2022 added dimension , scroll to element 
//	    addDimension(1000,1000);
	    WebElement element = driver.findElement(By.xpath(columnPath));
	   scrollToView(element);
	    Thread.sleep(1000); 
		doClick(columnPath);
//		driver.manage().window().maximize();
		Thread.sleep(2000);
	    waitForAjaxExtJs();
	  }


	  public static void selectMultipleColumnsToDisplay(String[] columnsToSelect) throws InterruptedException,Throwable{
	    for (String selectedColumns: columnsToSelect) {
	    	System.out.println(selectedColumns);
	      highlightColumnsToDisplayColumn(selectedColumns);
	      doClick(selectColumn.getSelectItem());
	      assertColumnsToDisplayColumnIsSelected(selectedColumns);
	      Thread.sleep(300);
	    }
	  }

	  public String[] removeAllColumnsToDisplayColumns() throws InterruptedException,Throwable {
	    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};
	    for (String selectedColumns: columns) {
	      highlightColumnsToDisplayColumn(selectedColumns);
	      doClick(selectColumn.getContractModelButtonColumnsToDisplayModalRemove());
	    }
	    return columns;
	  }

	  public void removeMultipleColumnsToDisplay(String[] columnsToRemove) throws InterruptedException,Throwable {
	    for (String selectedColumns: columnsToRemove) {
	      highlightColumnsToDisplayColumn(selectedColumns);
	      doClick(selectColumn.getRemoveItem());
	    }
	  }

	  public void assertColumnsToDisplayColumnOrder() {
	    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};

	    String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
	    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
	    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
	    List<String> actualSelectedColumnNames = new ArrayList<>();

	    for (WebElement selectedColumns: actualSelectedColumns) {
	      actualSelectedColumnNames.add(selectedColumns.getText());
	      for (int i = 0; i < columns.length; i++) {
	        if (selectedColumns.getText().equals(columns[i])) {
	          System.out.println(selectedColumns.getText() + " = " + columns[i]);
	        }
	      }
	    }
	  }

	  public void compareAvailableColumnToSelectedColumn() {
	    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
	    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
	    List<String> actualAvailableColumnNames = new ArrayList<>();

	    String selectedColumnsXpath = "//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
	    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
	    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
	    List<String> actualSelectedColumnNames = new ArrayList<>();
	    for (WebElement selectedColumns: actualSelectedColumns) {
	      actualSelectedColumnNames.add(selectedColumns.getText());
	      if (selectedColumns.getText().equals("")) {
	        continue;
	      }
	    }
	    actualSelectedColumnNames.remove(0);
	    if (actualAvailableColumnNames.equals(actualSelectedColumnNames)) {
	      System.out.println("The Available and Selected Columns have elements in common.");
	      fail();
	    } else {
	      System.out.println("The Available and Selected Columns do not have elements in common.");
	    }
	  }

	  public static List<String> getSelectedColumnList() {
	    String selectedColumnsXpath = "(//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody)[1]";
	    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
	    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
	    List<String> actualSelectedColumnNames = new ArrayList<>();
	    for (WebElement selectedColumns: actualSelectedColumns) {
	      actualSelectedColumnNames.add(selectedColumns.getText());
	      if (selectedColumns.getText().equals("")) {
	        continue;
	      }
	      System.out.println(selectedColumns.getText());
	    }
	    actualSelectedColumnNames.remove(0);
	    System.out.println(actualSelectedColumnNames.size());
	    return actualSelectedColumnNames;
	  }

	  public void getAvailableColumnList() {
	    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
	    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
	    List<String> actualAvailableColumnNames = new ArrayList<>();
	    for (WebElement availableColumns: actualAvailableColumns) {
	      actualAvailableColumnNames.add(availableColumns.getText());
	      if (availableColumns.getText().equals("")) {
	        continue;
	      }
	      System.out.println(availableColumns.getText());
	    }
	    actualAvailableColumnNames.remove(0);
	    System.out.println(actualAvailableColumnNames.size());
	  }

	  public void assertAvailableColumnIsEmpty() {
	    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    //        String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody/tr/th";
	    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
	    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
	    List<String> actualAvailableColumnNames = new ArrayList<>();
	    for (WebElement availableColumns: actualAvailableColumns) {
	      actualAvailableColumnNames.add(availableColumns.getText());
	      if (availableColumns.getText().equals("")) {
	        continue;
	      }
	      System.out.println(availableColumns.getText());
	    }
	    actualAvailableColumnNames.remove(0);
	    System.out.println(actualAvailableColumnNames.size());

	    if (actualAvailableColumnNames.size() == 0) {
	      System.out.println("The Available box in the Select Columns window is empty.");
	    } else {
	      System.out.println("The Available box in the Select Columns window is not empty.");
	      fail();
	    }
	  }

	  public void assertAvailableColumnIsNotEmpty() {
	    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
	    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
	    List<String> actualAvailableColumnNames = new ArrayList<>();
	    //Not Empty
	    for (WebElement availableColumns: actualAvailableColumns) {
	      actualAvailableColumnNames.add(availableColumns.getText());
	      if (availableColumns.getText().equals("")) {
	        continue;
	      }
	      System.out.println(availableColumns.getText());
	    }
	    actualAvailableColumnNames.remove(0);
	    System.out.println(actualAvailableColumnNames.size());

	    if (actualAvailableColumnNames.size() != 0) {
	      System.out.println("The Available box in the Select Columns window is not empty.");
	    } else {
	      System.out.println("The Available box in the Select Columns window is empty.");
	      fail();
	    }
	  }

	  public void assertColumnsToDisplayAllCheckBoxIsNotChecked() throws Exception {
	    String columnsToDisplayCheckBox = null;
	    try {
	      waitForAjaxExtJs();
	      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
	    } catch (Throwable e) {
	      System.out.println("Element Not Found");
	      fail("element not found");
	    }
	    boolean isNotChecked = columnsToDisplayCheckBox.contains("dirty");
	    if (printout) {
	      System.out.println("Element class text: " + columnsToDisplayCheckBox);
	      System.out.println("IsNotChecked: " + isNotChecked);
	    }
	    try {
	      assertTrue(isNotChecked);
	    } catch (Throwable e) {
	      System.out.println("TEST FAILED: Element is Checked");
	      throw new Exception();
	    }
	  }

	  public void assertColumnsToDisplayAllCheckBoxIsChecked() throws Exception {
	    String columnsToDisplayCheckBox = null;
	    try {
	      waitForAjaxExtJs();
	      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
	    } catch (Throwable e) {
	      System.out.println("Element Not Found");
	      fail("element not found");
	    }
	    boolean isChecked = columnsToDisplayCheckBox.contains("checked");
	    if (printout) {
	      System.out.println("Element class text: " + columnsToDisplayCheckBox);
	      System.out.println("IsChecked: " + isChecked);
	    }
	    try {
	      assertTrue(isChecked);
	    } catch (Throwable e) {
	      System.out.println("TEST FAILED: Element is not checked");
	      throw new Exception();
	    }
	  }

	  public void assertColumnsToDisplayAllCheckBoxIsDisabled() throws Exception {
	    String columnsToDisplayCheckBox = null;
	    try {
	      waitForAjaxExtJs();
	      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
	    } catch (Throwable e) {
	      System.out.println("Element Not Found");
	      fail("element not found");
	    }
	    boolean isDisabled = columnsToDisplayCheckBox.contains("disabled");
	    if (printout) {
	      System.out.println("Element class text: " + columnsToDisplayCheckBox);
	      System.out.println("IsDisabled: " + isDisabled);
	    }
	    try {
	      assertTrue(isDisabled);
	    } catch (Throwable e) {
	      System.out.println("TEST FAILED: Element is Enabled");
	      throw new Exception();
	    }
	  }

	  public static void assertColumnsToDisplayColumnIsSelected(String column) throws Exception {
	    String columnIsSelected = null;
	    try {
	      waitForAjaxExtJs();
//	      Omkar 30/5/2023 : xpath chages for 11.2
//	      scrollToView("//*[contains(@class,'x-grid-table')]/descendant::*[text()='"+column+"']/../..");
//	      columnIsSelected = driver.findElement(By.xpath("//*[contains(@class,'x-grid-table')]/descendant::*[text()='"+column+"']/../..")).getAttribute("class");
	      scrollToView("//*[text()='"+column+"']/../..");
	      columnIsSelected = driver.findElement(By.xpath("//*[text()='"+column+"']/../..")).getAttribute("class");
	    } catch (Throwable e) {
	      System.out.println("Element Not Found");
	      fail("element not found");
	    }

	  }
	  
	  public void selectColumnsToDisplayAvailableColumn(String column) throws Exception {
	      String columnPath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::*[text()='" + column + "']";
	      WebElement element = driver.findElement(By.xpath(columnPath));
	      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	      Thread.sleep(500); 
	      assertElementIsDisplayed(element);
	  }

	   /* public void selectColumnsToDisplaySelectedColumn(String column) {
	        String columnPath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::*[text()='" + column + "']";
	        doClick(driver.findElement(By.xpath(columnPath)));
	    }
	    public void selectColumnsToDisplayAvailableColumn(String column) {
	        String columnPath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::*[text()='" + column + "']";
	        doClick(driver.findElement(By.xpath(columnPath)));
	    }*/

	  public void assertColumnsToDisplayColumn(String column) {
	    String selectedColumnsXpath = "//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    //        String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
	    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
	    List<String> actualSelectedColumnNames = new ArrayList<>();

//	        String columnPath = "//label[text()='Available']/following::*[contains(@class,'glAccountsGrid')]/descendant::*[text()='" + column + "']";

	    for (WebElement selectedColumns: actualSelectedColumns) {
	      actualSelectedColumnNames.add(selectedColumns.getText());
	      if (selectedColumns.getText().equals(column)) {
	        System.out.println("Element, " + column + ", is found.");
	        break;
	      }
	    }
	  }

	  public void assertIfColumnIsInAvailableOrSelectedBox(String columnName) {
	    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
	    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
	    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
	    List<String> actualAvailableColumnNames = new ArrayList<>();
	    for (WebElement availableColumns: actualAvailableColumns) {
	      actualAvailableColumnNames.add(availableColumns.getText());
	      if (availableColumns.getText().equals("")) {
	        continue;
	      }
	      System.out.println(availableColumns.getText());
	      if (availableColumns.getText().equals(columnName)) {
	        System.out.println(columnName + " is in the Available box");
	        break;
	      } else {
	        System.out.println(columnName + " is in the Selected box");
	        break;
	      }
	    }
	    actualAvailableColumnNames.remove(0);
	  }
	 
	  public static void filterByCalculationStartTimeInCalculationStatusPage() throws Throwable {
		  Format f = new SimpleDateFormat("MM/dd/yyyy");
		  String strDate = f. format(new Date());
		  CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
		    doClick(driver.findElement(By.xpath("//div[contains(@id,'statustoolbar')]//span[text()='Filter']/parent::button")));
		    doDropdownSelectUsingOptionText(CostingMap.getcalculationFilterPopUpFilterDrop(),costingMap.getCalculationFilterDropdownMenuList(),"Import/Export Start Time");
		  
		    doClick(driver.findElement(By.xpath("//input[@name='valuedate']")));
		    driver.findElement(By.xpath("//input[@name='valuedate']")).sendKeys(strDate);
		    doClick(driver.findElement(By.xpath("//div[contains(@id,'filter')]//span[text()='Add']/parent::button")));
		    doClick(driver.findElement(By.xpath("//span[text()='Apply Filter']/parent::button")));
	  }
	  public static void waitForFirstRowCalculationBarToReach100Percent() throws Exception {
		    boolean calculate = true;
		    String percent;
		    byte counter = 0;
		    try {
				filterByCalculationStartTimeInCalculationStatusPage();
			} catch (Throwable e1) {
				
			}
		    while (calculate) {
		      try {
//		    	  Omkar 21/6/2023 : xpath changes for 11.2
//		        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
		        driver.findElement(By.xpath("//span[text()='Refresh']")).click();
		        waitForSpinnerToEnd();
//		        Omkar 21/6/2023 : xpath changes for 11.2
//		        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
		        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')][1]")).getText();
		        System.out.println("Percent complete: " + percent);
		        assertTrue(percent.contains("100%"));
		        break;
		      } catch (Throwable e) {
		        System.out.println("percent less than 100");
		        Thread.sleep(1000);
		        if (counter == 120) {
		          fail("Calculation did not finish in allotted time");
		        }
		        counter++;
		      }
		    }
		    Thread.sleep(5000);
		  }
	  
	  //File upload
	  public static void uploadTheFileusingAutoIT(WebDriver driver, String exeFile, String uploadFile) {
			try {
				Runtime.getRuntime().exec(exeFile + " " + uploadFile);
			} catch (Exception e) {
			}
		}
	  
	  public static void getContractElementList(String contractModel) {
		  List<WebElement> elementList=ContractingMap.getCostingModelElementList();
		  for (WebElement costingElement : elementList) {
				assertThatString(costingElement, contractModel, printout);
			}
	  }
	  
	  public static void navigateFeeForServicePaymentTermsScreenSelectionSelectServiceModel(String Model,String serviceModel) throws InterruptedException {
		    try {
		      waitForSpinnerToEnd();
		      waitForAjaxExtJs();
//		      Omkar 10/7/2023 : xpath changes for 11.2
//		      driver.findElement(By.xpath("//span[contains(@class,'x-panel-header-text')][text()='"+Model+"']/../following-sibling::div")).click();
		      driver.findElement(By.xpath("//div[text()='"+Model+"']/../following-sibling::div")).click();
		      waitForAjaxExtJs();
		      Thread.sleep(2000);
		      driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "']")).click();
		      Thread.sleep(2000);
		    } catch (Exception e) {
		    	JavascriptExecutor executor = (JavascriptExecutor)driver;
		    	executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + serviceModel + "']"))
		);
		    }
		  }
	  public static void navigateFeeForServicePaymentTermsScreenSelectionPanel(String Model) throws Throwable {
		    try {
		      waitForSpinnerToEnd();
		      waitForAjaxExtJs();
//		      Omkar 30/5/2023 : xpath changes for 11.2
//		      scrollToView("//span[contains(@class,'x-panel-header-text')][text()='"+Model+"']/../following-sibling::div");
//		      driver.findElement(By.xpath("//span[contains(@class,'x-panel-header-text')][text()='"+Model+"']/../following-sibling::div")).click();
		      scrollToView("//div[contains(@class,'default x-title-item')][text()='"+Model+"']/../following-sibling::div");
		     
		      driver.findElement(By.xpath("//div[contains(@class,'default x-title-item')][text()='"+Model+"']/../following-sibling::div")).click();
		      waitForAjaxExtJs();
		      Thread.sleep(300);
		    } catch (Exception e) {
		    	JavascriptExecutor executor = (JavascriptExecutor)driver;
//		    	 Omkar 30/5/2023 : xpath changes for 11.2
//			      scrollToView("//span[contains(@class,'x-panel-header-text')][text()='"+Model+"']/../following-sibling::div");
			      scrollToView("//div[contains(@class,'default x-title-item')][text()='"+Model+"']/../following-sibling::div");
		    	executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[text()='Service Model']/ancestor::div/descendant::div[text() = '" + Model + "']"))
		);
		    	 Thread.sleep(300);
		    }
		  }
	  public static void navigateFeeForServicePaymentTermsPageRiskLimiterSectionClickEditButtonToOpenEditDialog() {
		    try {
		      waitForAjaxExtJs();
//		      Omkar 13/7/2023 : xpath changes for 11.2
//		      driver.findElement(By.xpath("//div[contains(@class,'x-toolbar-item')]//span[text()='Edit']")).click();
		      driver.findElement(By.xpath("//a[contains(@class,'x-toolbar-item x-btn-default-small')]//span[text()='Edit']")).click();
		      waitForAjaxExtJs();
		      Thread.sleep(1000);
		      //assertElementTextWithXpath("//span[contains(@id, 'medicareinpatientpps')]", "Edit Price for " + serviceModel + " [Encounter]", printout);
		    } catch (Throwable e) {
		      e.getMessage();
		    }
		  }
	  
	 public  void navigateFeeForServicePaymentTermsPageServiceModel(String[] Service) throws InterruptedException {
		 try {
			doClick(ContractingMap.getContractFeeForServicePaymentFilter());		   
				waitForAjaxExtJs();

				doFilterCreate(Service);
//				doClick(ContractingMap.getContractModelApplyFilterButton());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.getMessage();
		}
	 }
	 public void navigateFeeForServicePaymentTerms() {
		 try {
			doClickTreeItem("Model Contract");
				waitUntilTreeOptionIsClickable("Define Payment Terms");
				doClickTreeItem("Define Payment Terms");
				waitForMainPageTitle("Fee For Service Payment Terms");
				doClickTreeItem("Fee For Service Payment Terms");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.getMessage();
		}
	 }
	 
	 public void navigateCloseOpenSection(WebElement element) {
		 try {
				doClick(element);		      

		 }catch(Exception e) {
			 
		 }
	 }
	 public static void keyInValues(WebElement element, String input) {
		 try {
//			 element.click();	
//			 Thread.sleep(1000);
			 element.clear();
			 Thread.sleep(1000);
			 element.sendKeys(input);
			 Thread.sleep(1000);
			 element.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			
		}

	 }
	 
		  public void doFilterCreateForServices(String[] filterParameters) throws Throwable {
			  System.out.println(filterParameters[0]);
		    doFilterSetFilterParameterServices(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
		    waitForAjaxExtJs();
		    doClick(ContractingMap.getaddOnServicesPopUpFilterAddButton());
		    waitForAjaxExtJs();
		    doClick(ContractingMap.getFilterDialogButtonApplyFilter());
		    waitForDisplayedSpinnerToEnd();
		  }
		  public void doFilterCreateAndAddFilter(String[] filterParameters,WebElement element) throws Throwable {
			  System.out.println(filterParameters[0]);
			  doFilterSetFilterParameterPassElementForCondition(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3],element);
		    waitForAjaxExtJs();
		   		  }
		  public void doFilterCreateOnly(String[] filterParameters) throws Throwable {
			  System.out.println(filterParameters[0]);
		    doFilterSetFilterParameterServices(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
		    waitForAjaxExtJs();
		    doClick(ContractingMap.getaddOnServicesPopUpFilterAddButton());
		    waitForAjaxExtJs();
		  }
		  public void doFilterSetFilterParameterServices(String field, String operator, String condition, String value) throws InterruptedException {
			    waitForAjaxExtJs();
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), field);
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), operator);
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), condition);

			    doClick(dialog.getFilterDialogFormFieldValue());
			    dialog.getFilterDialogFormFieldValue().sendKeys(value);
			  } 
		  public void doFilterSetFilterParameterPassElementForCondition(String field, String operator, String condition, String value,WebElement element) throws InterruptedException {
			    waitForAjaxExtJs();
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), field);
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), operator);
			    doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), condition);

			    doClick(element);
			    element.sendKeys(value);
			  } 
		  public void doDropdownSelectUsingOptionTextServices(WebElement dropdownList,WebElement element, String optionText) throws InterruptedException {
		        waitForAjaxExtJs();
		        doClick(element);
		        waitForAjaxExtJs();
		        driverDelay(300);
		       List<WebElement> menu = dropdownList.findElements(By.tagName("li"));
		        System.out.println(optionText);
		       for(WebElement option : menu) {
		        	 System.out.println("Value"+option.getText());
		            if(option.getText().equals(optionText)) {
		                option.click();
		                break;
		            }
		       }
		      
		    }
		  public static void highlightColumnsToDisplayColumnServices() throws InterruptedException,Throwable {
			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ContractingMap.getselectAddServicesButton());
			    Thread.sleep(1000); 
			    doClick(ContractingMap.getselectAddServicesButton());
			  }
		  
		  public void AssertAddOnPaymentTechnologyServicesDisplayed(WebElement element, String serviceName,String[] filterParameters) throws Throwable {
			  try {
					doClick(element);
					waitForElementToBeVisible(ContractingMap.getContractEditPricePopUpAddServices());
					//Validate Services under Add Services Category
					waitForElementToBeVisible(ContractingMap.getContractEditPricePopUpAddServices());
					assertElementIsDisplayed(ContractingMap.getContractEditPricePopUpAddServices());
					assertColumnsToDisplayColumnIsSelected(serviceName);
					doClick(ContractingMap.getContractFeeForServicePaymentApply());
				} catch (Exception|AssertionError e) {
					doClick(ContractingMap.getaddOnServicesPopUpFilterButton());
					doFilterCreateForServices(filterParameters);
					highlightColumnsToDisplayColumnServices();
					doClick(ContractingMap.getContractFeeForServicePaymentApply());
					}
			  
		  }
		  public void AssertAddOnPaymentTechnologyServicesMaxValue(String serviceName,String value) throws Throwable {
			  try {
				  scrollToView("(//div[text()='" + serviceName + "']//following::td/div)[1]");
				  System.out.println(driver.findElement(By.xpath("(//div[text()='"+serviceName +"']//following::td/div)[1]")).getText());
				  	if(!((driver.findElement(By.xpath("(//div[text()='"+serviceName +"']//following::td/div)[1]")).getText().equals(value)))) {
					  driver.findElement(By.xpath("(//div[text()='"+serviceName +"']//following::td/div)[1]")).click();
					  Thread.sleep(500);
					  keyInValues(driver.findElement(By.xpath("//div[text()='"+serviceName+"']//following::div")), 
								value);
				  }
				  				} catch (Exception|AssertionError e) {
					
					}
			  
		  }
		  public static void dragAndDropElement(WebElement from,WebElement to) throws InterruptedException {
				Actions action=new Actions(driver);
				action.clickAndHold(from).moveToElement(to).release(from).build();
				Thread.sleep(2000);
				action.dragAndDrop(from,to).perform();		;	
			}
		  
		  public static void enterServicesMaxValue(HashMap<String,String> services) throws Exception {
		
			  for (Map.Entry<String, String> set :
				  services.entrySet()) {
				  System.out.println(set.getKey().toString());
				 
				  scrollToView("(//div[text()='" + set.getKey().toString() + "']//following::td/div)[1]");
				  System.out.println(driver.findElement(By.xpath("(//div[text()='"+set.getKey().toString() +"']//following::td/div)[1]")).getText());
				  	if(!((driver.findElement(By.xpath("(//div[text()='"+set.getKey().toString() +"']//following::td/div)[1]")).getText().equals(set.getValue().toString())))) {
//					  driver.findElement(By.xpath("(//div[text()='"+set.getKey().toString() +"']//following::td/div)[1]")).click();
					  Thread.sleep(1500);
					  Actions act=new Actions(driver);
					  System.out.println(set.getValue().toString());
					  //Shilpa updated for 11.2 on 24.2.2024
					  act.moveToElement(driver.findElement(By.xpath("(//div[text()='"+set.getKey().toString() +"']//following::td/div)[1]"))).click().sendKeys(Keys.BACK_SPACE).sendKeys(set.getValue().toString()).sendKeys(Keys.ENTER).perform();
//					  keyInValues(driver.findElement(By.xpath("(//div[text()='"+set.getKey().toString() +"']//following::td/div)[1]")), 
//								set.getValue().toString());
				  }
				
			  }
		  }
		  
		 public static void toggleBetweenTheDockBar(String xpath) {
			 try {
				doClick(xpath);
			} catch (Exception e) {
				
//				e.printStackTrace();
			}
			 
		 }
		 
		 public static void CompareListToArray(List<WebElement> elements,String[] names) {
			 
			 try {
				int j=0;
				 for(WebElement element:elements ){
						System.out.println(element.getText());

						ContractModelsHelper.scrollToView(element);
						List<String> arrayList = Arrays.asList(names);
						for(int i=j;i<=arrayList.size();) {
							System.out.println(arrayList.get(i));
						if(element.getText().equals(arrayList.get(i))) {
							assertTrue(printout);
											}
						j=i+1;
						break;

						}
 }
			} catch (Exception e) {
				
			}
		 }
		 
		 public static void sortTableGridAscending(List<WebElement> elements) throws Exception {
			 ArrayList<String> obtainedList = new ArrayList<>(); 
				List<WebElement> elementList= elements;
				for(WebElement we:elementList){
				   obtainedList.add(we.getText());
				}
				driverDelay(1000);
				ArrayList<String> sortedList = new ArrayList<>();   
				for(String s:obtainedList){
				sortedList.add(s);
				}
				Collections.sort(sortedList);
				Collections.sort(obtainedList);
				if(sortedList.equals(obtainedList)) {
				assertTrue(printout);
			}
		 }
		 public static void sortTableGridDescending(List<WebElement> elements) throws Exception {
			 ArrayList<String> obtainedList = new ArrayList<>(); 
				List<WebElement> elementList= elements;
				for(WebElement we:elementList){
				   obtainedList.add(we.getText());
				}
				driverDelay(1000);
				ArrayList<String> sortedList = new ArrayList<>();   
				for(String s:obtainedList){
				sortedList.add(s);
				}
				Collections.reverse(sortedList);
				Collections.reverse(obtainedList);
			if(sortedList.equals(obtainedList)) {
				assertTrue(printout);
			}
		 }
		 
	public static void applyMultipleFilters(String code,String name) throws Throwable {
		AdsHelper adsHelper = new AdsHelper();
		adsHelper.doFilterSetFilterParameterswithElement(code, "Is", "Equal To", name);
		doClick(ContractingMap.getaddOnServicesPopUpFilterAddButton());
		ContractModelsHelper.scrollToView(ContractingMap.getContractModelApplyFilterButton());
		doClick(ContractingMap.getContractModelApplyFilterButton());
//		ContractingMap.getFilterDialogButtonApplyFilter();
		ContractModelsHelper.highlightColumnsToDisplayColumnServices();
		//Shilpa updated for 11.2 on 25.4.2024
		doClick("(//span[text()='All']/..)[1]");
	    doClick("//div[contains(@class,'x-toolbar-footer')]//span[text()='Apply']");
	}
	
	public static void groupSelectApplyFilters(String code,String name) throws Throwable {
	
		 ContractModelsHelper.scrollToView(CostingMap.getDeptGroupsSelect());
			doClick(CostingMap.getDeptGroupsSelect());
			doClick(CostingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
			applyMultipleFilters(code,name);
	}
	public static int getPopUpElementListInGrid(List<WebElement> element) {
		int elementSize;
		 elementSize=element.size();
		 return elementSize;
	}

	public static int getPopUpElementListInGrid(WebElement[] element) {
		int elementSize;
		 elementSize=element.length;
		 return elementSize;
	}
	public static void gotToSpecifiedPage(WebElement input,WebElement goOption,String number) throws InterruptedException {
		ContractModelsHelper.keyInValues(input, number);
		goOption.click();
		driverDelay(1000);
	}
	public  String getCellValue (String chargeCode,String columnNamepath) throws Throwable {
	    String columnID;
	    String columnValue = null;
	    try {
	    	
	      ContractModelsHelper.scrollToView(driver.findElement(By.xpath("(//*[text()='" + chargeCode + "']/../../descendant::div[1])[2]")));
	      String row = driver.findElement(By.xpath("(//*[text()='" + chargeCode + "']/../../descendant::div[1])[2]")).getText();
	      System.out.println("Row Number: " + row);
	      columnID = driver.findElement(By.xpath(columnNamepath)).getAttribute("id");
	      int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
	      columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]")).getText();
	      System.out.println("Value: " + columnValue);
	    } catch (Throwable e) {
	      e.getMessage();
	    }
	    return columnValue;
	  }
	public static void updateDepartment(String departmentText) throws InterruptedException {
	     doClick("((//label[contains(@id,'singleselectorform')])[2]//following::span[text()='Select'])[1]");
	   waitForDisplayedSpinnerToEnd();
	    waitForAjaxExtJs();
	    //Thread.sleep(1100);  //original value, which works
	    Thread.sleep(500);  //alternative value, to reduce run time - reset to original value if there are false positives with this one
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
		   jse.executeScript("arguments[0].value='"+ departmentText +"';",  driver.findElement(By.name("carrierfield")));
			waitForSpinnerToEnd();
			try {
				driver.findElement(By.name("carrierfield")).sendKeys(" ");
				Thread.sleep(1000);
				driver.findElement(By.name("carrierfield")).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);

			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner') and contains(text()," + departmentText +")]")));
			waitForAjaxExtJs();
			doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
			waitForAjaxExtJs();
		} catch (Exception|AssertionError e) {
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner') and contains(text()," + departmentText +")]")));

			doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
			waitForAjaxExtJs();
		}
	  }

	public static void saveCustomSettings(String setName,String modelName) {
		try {
			goToPage("Customize Task Lists");
			doClick("//label[text()='"+setName+"']//preceding-sibling::span");
			driverDelay(1000);
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			driverDelay(1000);
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			waitForDisplayedSavingSpinnerToEnd();
//			waitForAjaxExtJs();
			driverDelay(3000);
			goToPage(modelName);
			
		} catch (Exception e) {
			
		}
	}
	public static void saveCustomSettingsLogOut(String setName,String modelName) {
		try {
			goToPage("Customize Task Lists");
			doClick("//label[text()='"+setName+"']//preceding-sibling::span");
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			waitForDisplayedSpinnerToEnd();
			doClick("//div[text()='Log Out']");
			
		} catch (Exception e) {
			
		}
	}
	public static void revertCustomSettings() {
		try {
			goToPage("Customize Task Lists");
			doClick("//label[text()='Use Default']//preceding-sibling::span");
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			waitForDisplayedSpinnerToEnd();
			doClosePageOnLowerBar("Customize Task Lists");
		} catch (Exception e) {
		
		}
	}
}
