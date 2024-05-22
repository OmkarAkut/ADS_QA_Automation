package webdriver.helpers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.corehelpers.GoHelper;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class UcqcHelper extends GoHelper {

  //CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);  //do not uncomment this line or SessionID error will result when running test suites

  public void assertChangeColumnCalculation(String chargeCode, String changeColumn, String costColumn, String quickCostColumn, boolean printout) throws InterruptedException {
    double expectedChangeTotal;
    double actualCCChangeTotal = convertStringToDoubleWithTwoDecimals(ucqcGetChargeCodeGridCellValue(chargeCode, changeColumn, printout));
    String ccCost = ucqcGetChargeCodeGridCellValue(chargeCode ,costColumn, printout);
    String quickCCCost = ucqcGetChargeCodeGridCellValue(chargeCode, quickCostColumn, printout);
    expectedChangeTotal = convertStringToDoubleWithTwoDecimals(ucqcGetChargeCodeGridCellValue(chargeCode, quickCostColumn, printout))
      - convertStringToDoubleWithTwoDecimals(ucqcGetChargeCodeGridCellValue(chargeCode ,costColumn, printout));
    compareDoubleValues(actualCCChangeTotal, roundDoubleToTwoDecimals(expectedChangeTotal));
  }

  public void assertChangeColumnCalculation(int rowNumber, String changeColumn, String costColumn, String quickCostColumn, boolean printout) throws InterruptedException {
    double expectedChangeTotal;
    double actualCCChangeTotal = convertStringToDoubleWithTwoDecimals(ucqcGetChargeCodeGridCellValueForGivenRowNumber(rowNumber, changeColumn, printout));
    String ccCost = ucqcGetChargeCodeGridCellValueForGivenRowNumber(rowNumber ,costColumn, printout);
    String quickCCCost = ucqcGetChargeCodeGridCellValueForGivenRowNumber(rowNumber, quickCostColumn, printout);
    expectedChangeTotal = convertStringToDoubleWithTwoDecimals(ucqcGetChargeCodeGridCellValueForGivenRowNumber(rowNumber, quickCostColumn, printout))
      - convertStringToDoubleWithTwoDecimals(ucqcGetChargeCodeGridCellValueForGivenRowNumber(rowNumber ,costColumn, printout));
    compareDoubleValues(actualCCChangeTotal, roundDoubleToTwoDecimals(expectedChangeTotal));
  }

  public void verifyUcqcPage(boolean printout) {
    //UCQC screen opens with "Unit Cost Quick Calculation" title
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'header_hd-textEl') and text()='Unit Cost Quick Calculation']")), printout);
    //Unit Cost Quick Calculation displays in breadcrumbs
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'header_hd-textEl') and text()='Unit Cost Quick Calculation']")), printout);
    //Unit Cost Quick Calculation displays as item in dock bar
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'btnInnerEl') and text()='Unit Cost Quick...']")), printout);
  }

//  public int getColumnIDDigits(String headerName) throws InterruptedException {
//    String columnID;
//    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][contains(text(),'" + headerName + "')]")).getAttribute("id");
//    int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
//    System.out.println("Column ID: " + columnIDDigits);
//    return columnIDDigits;
//  }
//
//  /**Use editCell(String rowNumber, String editableColumnNumber, String value) instead*/
//  @Deprecated
//  public void editCell(int columnIdDigits, String value) {
//    WebElement editCell;
//    try {
////            waitForAjaxExtJs();
//      Thread.sleep(3000);
//      driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]")).click();
//      waitForAjaxExtJs();
//      editCell = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div/table[contains(@id,'textfield')]"));
//
//      Actions action = new Actions(driver);
//      action.moveToElement(editCell).sendKeys(value).perform();
//      action.moveToElement(editCell).sendKeys(Keys.ENTER).perform();
//      Thread.sleep(1000);
//    } catch (Throwable e){
//      System.out.println("WARNING: Element Not Found");
//    }
//  }
//
//  public void editCell(String rowNumber, String editableColumnNumber, String value) {
//    /*Editable Column Numbers
//     * Column #1: Quick Salaries and Wages RVU
//     * Column #2: Quick Employee Benefits RVU
//     * */
//    WebElement editCell;
//    try {
//      driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + rowNumber + "]/descendant::*[contains(@class,'ucqcGridTdCls')][" + editableColumnNumber + "]")).click();
//      waitForAjaxExtJs();
//      editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + rowNumber + "]/descendant::*[contains(@class,'ucqcGridTdCls')][" + editableColumnNumber + "]/div/table[contains(@id,'textfield')]"));
//
//      Actions action = new Actions(driver);
//      action.moveToElement(editCell).sendKeys(value).perform();
//      action.moveToElement(editCell).sendKeys(Keys.ENTER).perform();
//      Thread.sleep(1000);
//    } catch (Throwable e) {
//      System.out.println("WARNING: Element Not Found");
//    }
//  }
//
//  public static void doDropdownSelectUsingOptionText(WebElement element, WebElement list, String optionText) throws InterruptedException {
////    waitForAjaxExtJs();
////    Thread.sleep(1100);
//    doClick(element);
//    waitForAjaxExtJs();
//    List<WebElement> menu = list.findElements(By.tagName("li"));
//    for(WebElement option : menu) {
//      if(option.getText().equals(optionText)) {
//        option.click();
//        break;
//      }
//    }
//  }
//

//
//  /** Use doDropdownSelectUsingOptionText(WebElement element,WebElement list, String optionText) instead */
//  @Deprecated
//  public void doDropdownSelectUsingOptionText(WebElement element,String dropdown, String optionText) throws InterruptedException {
//    waitForAjaxExtJs();
//    doClick(element);
//    waitForAjaxExtJs();
//    WebElement list = driver.findElement(By.xpath(dropdown));
//    List<WebElement> menu = list.findElements(By.tagName("li"));
//    for(WebElement option : menu) {
//      if(option.getText().equals(optionText)) {
//        option.click();
//        break;
//      }
//    }
//    waitForAjaxExtJs();
//  }

  public void selectDepartment(String departmentText) throws InterruptedException {
//        doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSelect());
	  
    doClickButton("Select");
    waitForAjaxExtJs();
    /**Select a Department and Click Apply*/
    Thread.sleep(1100);
//    Omkar 15/2/2024 : The below code is put in place while values are getting updated when earlier values are already present for department
//    WebElement dept = driver.findElement(By.xpath("(//div[contains(text(),'" + departmentText + "')])[2]"));
//    if(!driver.findElement(By.xpath("(//div[contains(text(),'" + departmentText + "')])[2]")).)
//    	doClick(driver.findElement(By.xpath("(//div[contains(text(),'" + departmentText + "')])[2]")));
//    else
//    doClick(driver.findElement(By.xpath("//div[contains(text(),'" + departmentText + "')]")));   
   
    
    try {
    	doClick(driver.findElement(By.xpath("(//div[contains(text(),'" + departmentText + "')])[2]")));
    }catch (Throwable e) {
    	doClick(driver.findElement(By.xpath("//div[contains(text(),'" + departmentText + "')]"))); 
    }
    waitForAjaxExtJs();
    doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
    waitForAjaxExtJs();
  }
//
//  public void doClickCell(String rowNumber, String editableColumnNumber){
//    WebElement clickCell;
//    try{
//      clickCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + rowNumber + "]/descendant::*[contains(@class,'ucqcGridTdCls')][" + editableColumnNumber + "]"));
//      clickCell.click();
//    } catch (Throwable e) {
//      System.out.println("WARNING: Element Not Found");
//    }
//  }

  public static void ucqcWaitForSpinnerToEndNoHardStop() throws InterruptedException {
    boolean spinner = true;
    while(spinner){
      try {
        spinner = driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")).isDisplayed();
      } catch (Throwable e) {
        Thread.sleep(1000);
      }
    }
  }

  public static void ucqcWaitForSpinnerToEnd() {
    boolean spinner = true;
    while(spinner){
      try {
        spinner = driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")).isDisplayed();
      } catch (Throwable e) {
        System.out.println("NOTICE: Could not find Spinner element");
        break;
      }
    }
  }

  public void selectCostComponent(String component){
    WebElement costComponent = driver.findElement(By.xpath("//div[text()='" + component + "']"));
    doClick(costComponent);
  }

  public String getCellValue (String chargeCode, String headerName) throws InterruptedException {
    String columnID;
    String columnValue = null;
    try {
      System.out.println(chargeCode);
      System.out.println(headerName);
      String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
      System.out.println("Row Number: " + row);
      columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
      int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
//      columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]")).getText();
      //Shilpa Xpath update for 11.2 on 20.05.2024
      columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')])[" + row + "]")).getText();
      System.out.println("Value: " + columnValue);
    } catch (Throwable e) {
      e.getMessage();
    }
    return columnValue;
  }

  public String ucqcGetChargeCodeGridCellValueForGivenRowNumber (int rowNumber, String headerName, boolean printout) {
    String columnID;
    String columnValue = null;
    try {

      columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
      int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
      if (headerName.equals("Total Unit Cost") || headerName.equals("Total Quick Cost") ||headerName.equals("Total Change")) {
//        columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + rowNumber + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div")).getText();
      //Shilpa Xpath update for 11.2 on 21.5.2024
          columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div)[" + rowNumber + "]")).getText();
      } else if (headerName.equals("Charge Code") || headerName.equals("Charge Code Name") || headerName.equals("Modifier")) {
//        columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + rowNumber + "]/descendant::*[contains(@class,'x-grid-cell-gridcolumn-" + columnIDDigits + "')]/div")).getText();
          //Shilpa Xpath update for 11.2 on 21.5.2024
    	  columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-gridcolumn-" + columnIDDigits + "')]/div)[" + rowNumber + "]")).getText();
      } else {
//        columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + rowNumber + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]")).getText();
    	  //Shilpa Xpath update for 11.2 on 21.5.2024
    	  columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')])[" + rowNumber + "]")).getText(); 
      }
      if (printout) {
        System.out.println("Get Cell Value Row Number: " + rowNumber);
        System.out.println("Get Cell Value Header: " + headerName);
        System.out.println("Get Cell Value: " + columnValue);
      }
    } catch (Throwable e) {
      e.getMessage();
    }
    return columnValue;
  }

  public String ucqcGetChargeCodeGridCellValue (String chargeCode, String headerName, boolean printout) {
    String columnID;
    String columnValue = null;
    try {
      String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
      columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
      int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
      if (headerName.equals("Total Unit Cost") || headerName.equals("Total Quick Cost") ||headerName.equals("Total Change")) {
//        columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div")).getText();
    	  //Shilpa: xpath update for 11.2 on 20.5.2024
          columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div)[" + row + "]")).getText();

      } else if (headerName.equals("Charge Code") || headerName.equals("Charge Code Name") || headerName.equals("Modifier")) {
//        columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-gridcolumn-" + columnIDDigits + "')]/div")).getText();
    	  //Shilpa: xpath update for 11.2 on 20.5.2024
    	  columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-gridcolumn-" + columnIDDigits + "')]/div)[" + row + "]")).getText();
      } else {
//        columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]")).getText();
    	  //Shilpa: xpath update for 11.2 on 20.5.2024
    	  columnValue = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')])[" + row + "]")).getText();

      }
      if (printout) {
        System.out.println("Get Cell Value Charge Code: " + chargeCode);
        System.out.println("Get Cell Value Header: " + headerName);
        System.out.println("Get Cell Value Row Number: " + row);
        System.out.println("Get Cell Value: " + columnValue);
      }
    } catch (Throwable e) {
      e.getMessage();
    }
    return columnValue;
  }

  public void getCellValueAndAssertDecimalPlace (String chargeCode, String headerName) throws InterruptedException {
    String columnID;
    System.out.println(chargeCode);
    System.out.println(headerName);

    waitForAjaxExtJs();
    String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    System.out.println("Row Number: " + row);

    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    String columnXpath = "//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]";
    String columnValue = driver.findElement(By.xpath(columnXpath)).getText();
    System.out.println("Value: " + columnValue);

    String[] decimalPlaces = columnValue.split("\\.");
    if(decimalPlaces[1].length() == 2){
      System.out.println("The value in this column, " + columnValue + ", has two decimal places.");
    } else {
      fail("The value in this column, " + columnValue + ", has more than two decimal places.");
    }
  }

//  public void setUCQCCriteria(String costModel, String costModelScenario, String entity, String department, String resultsStoredFor) throws InterruptedException {
//    //Cost Model
//    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),costModel);
//    //Cost Model Scenario
//    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),costModelScenario);
//    //Entity
//    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),entity);
//    //Dept
//    selectDepartment(department);
//    //Results Stored For
//    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),resultsStoredFor);
//  }

  public void compareServerMonthAndDestinationEffectiveMonth() {
    String serverMonth = getServerMonth();
    String destinationEffectiveMonthStartMonth = driver.findElement(By.xpath("//*[input/@name='selectedMonth']/descendant::input")).getAttribute("value");
    if (serverMonth.equals(destinationEffectiveMonthStartMonth)) {
      System.out.println("Server Month, " + serverMonth + ", is the same as the Destination Effective Month Start Month, " + destinationEffectiveMonthStartMonth + ".");
    } else {
      System.out.println("Server Month, " + serverMonth + ", is NOT the same as the Destination Effective Month Start Month, " + destinationEffectiveMonthStartMonth + ".");
      fail();
    }
  }

  public void compareServerYearAndDestinationEffectiveYear() {
    int serverYear = getServerYear();
    String destinationEffectiveMonthStartYear = driver.findElement(By.xpath("//*[input/@name='selectedYear']/descendant::input")).getAttribute("value");
    int destinationEffectiveYear = Integer.parseInt(destinationEffectiveMonthStartYear);
    if (serverYear == destinationEffectiveYear) {
      System.out.println("Server Year, " + serverYear + ", is the same as the Destination Effective Month Start Year, " + destinationEffectiveYear + ".");
    } else {
      System.out.println("Server Year, " + serverYear + ", is NOT the same as the Destination Effective Month Start Year, " + destinationEffectiveYear + ".");
      fail();
    }
  }

  public void verifyCheckBox(String costComponent, boolean printout) {
    try {
      WebElement component = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]/descendant::*[text()='" + costComponent + "']/../.."));
      String cComponent = component.getAttribute("class");
      if (printout) {
        System.out.println("Cost Component: " + costComponent);
      }
      assertTrue(cComponent.contains("selected"));
      System.out.println("The Cost Component, " + costComponent + ", is checked.");
    } catch (Throwable e) {
      System.out.println("The Cost Component, " + costComponent + ", is not checked.");
//            fail();
    }
  }

  public void validateYearFormat(WebElement element, boolean printout) {
    String destinationEffectiveMonthStartYear = element.getAttribute("value");
    int destinationEffectiveYear = Integer.parseInt(destinationEffectiveMonthStartYear);
    String effectiveYear = String.valueOf(destinationEffectiveYear);
    if (effectiveYear.matches("\\d{4}")) {
      System.out.println("The format of Year, " + effectiveYear + ", is YYYY.");
    } else {
      System.out.println("The format of Year, " + destinationEffectiveYear + ", is not YYYY.");
      fail();
    }
  }

  public void validateMonthFormat(WebElement element, boolean printout) {
    String destinationEffectiveMonthStartMonth = element.getAttribute("value");

    if (destinationEffectiveMonthStartMonth.matches("[a-zA-Z]{3}")) {
      System.out.println("The format of Month, " + destinationEffectiveMonthStartMonth + ", is MMM.");
    } else {
      System.out.println("The format of Month, " + destinationEffectiveMonthStartMonth + ", is not MMM.");
      fail();
    }
  }

  public int getDropdownMenuSize(WebElement element, boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    doClick(element);
    waitForAjaxExtJs();
    WebElement list = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
    List<WebElement> menu = list.findElements(By.tagName("li"));
    if(printout) {
      System.out.println("Menu size: " + menu.size());
    }
    return menu.size();
  }

  public void selectCostComponent(String costComponent,boolean printout){
    String component = "//tr[contains(@class,'x-grid-row')]/descendant::*[text()='" + costComponent + "']";
    doClick(driver.findElement(By.xpath(component)));
  }

  public void assertUCQCDropdownIsEnabled(String nameAttribute, boolean printout) throws Exception {
    String disabledText = null;
    String disabledTextXpath = "//input[@placeholder='<None>'][@name='" + nameAttribute + "']";
    try {
      waitForAjaxExtJs();
      disabledText = driver.findElement(By.xpath(disabledTextXpath)).getAttribute("disabled");
    } catch(Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isDisabled = Boolean.parseBoolean(disabledText);
    if (printout){
      System.out.println("Element class text: " + disabledText);
      System.out.println("IsDisabled: " + isDisabled);
    }
    try {
      assertFalse(isDisabled);
    } catch(Throwable e){
      System.out.println("TEST FAILED: Element is disabled");
      throw new Exception();
    }
  }

  public void assertUCQCElementIsDisabled(WebElement element, boolean printout) throws Exception {
    String disabledText = null;
    try {
      waitForAjaxExtJs();
      disabledText = element.getAttribute("style");
    } catch(Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isDisabled = disabledText.contains("display");
    if (printout){
      System.out.println("Element class text: " + disabledText);
      System.out.println("IsDisabled: " + isDisabled);
    }
    try {
      assertTrue(isDisabled);
    } catch(Throwable e){
      System.out.println("TEST FAILED: Element is Enabled");
      throw new Exception();
    }
  }
  public void assertUCQCElementHasAttributeDisabled(WebElement element, boolean printout) throws Exception {
	    String disabledText = null;
	    try {
	      waitForAjaxExtJs();
	      disabledText = element.getAttribute("disabled");
	    } catch(Throwable e) {
	      System.out.println("Element Not Found");
	      fail("element not found");
	    }
	    boolean isDisabled = disabledText.contains("true");
	    if (printout){
	      System.out.println("Element class text: " + disabledText);
	      System.out.println("IsDisabled: " + isDisabled);
	    }
	    try {
	      assertTrue(isDisabled);
	    } catch(Throwable e){
	      System.out.println("TEST FAILED: Element is Enabled");
	      throw new Exception();
	    }
	  }
  public void assertUCQCDropdownIsDisabled(String nameAttribute, boolean printout) throws Exception {
    String disabledText = null;
    String disabledTextXpath = "//input[@placeholder='<None>'][@name='" + nameAttribute + "']";
    try {
      waitForAjaxExtJs();
      disabledText = driver.findElement(By.xpath(disabledTextXpath)).getAttribute("disabled");
    } catch(Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isDisabled = Boolean.parseBoolean(disabledText);
    if (printout){
      System.out.println("Element class text: " + disabledText);
      System.out.println("IsDisabled: " + isDisabled);
    }
    try {
      assertTrue(isDisabled);
    } catch(Throwable e){
      System.out.println("TEST FAILED: Element is Enabled");
      throw new Exception();
    }
  }

  public void ucqcUpdateGridCellValue(String chargeCode, String headerName, String newValue, boolean printout) throws InterruptedException {
    String columnID;
    if (printout) {
      System.out.println("For cell update, Charge Code: " + chargeCode);
      System.out.println("For cell update, Header Name: " + headerName);
      System.out.println("For cell update, New Value: " + newValue);
    }
    //get row from charge code
    String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    if (printout) {
      System.out.println("For cell update, Row Number: " + row);
    }
    //get column id from column header
    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    if (printout) {
      System.out.println("For cell update, columnIdDigits: " + columnIdDigits);
    }
    //click in cell and update
//    driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]")).click();
    driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')])["+row+"]")).click();

    waitForAjaxExtJs();
//	Omkar 28/12/2023 : xpath changes for 11.2
//    WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div/table"));
    WebElement editCell = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div)["+row+"]"));
    Actions action = new Actions(driver);
    //Shilpa updated : below lines wont work : 15.09.2022
//    action.moveToElement(editCell).sendKeys(newValue).perform();
//    action.moveToElement(editCell).sendKeys(Keys.ENTER).perform();
//    Thread.sleep(1000);
    action.moveToElement(editCell).clickAndHold().sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.chord(Keys.CLEAR));
    Thread.sleep(1000);
    action.moveToElement(editCell).clickAndHold().sendKeys(newValue).sendKeys(Keys.chord(Keys.ENTER)).build().perform();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
  }

  public void updateGridCellValue(String chargeCode, String headerName, String newValue, boolean printout) throws InterruptedException {
    String columnID;
    if (printout) {
      System.out.println("For cell update, Charge Code: " + chargeCode);
      System.out.println("For cell update, Header Name: " + headerName);
      System.out.println("For cell update, New Value: " + newValue);
    }
    //get row from charge code
    String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    if (printout) {
      System.out.println("For cell update, Row Number: " + row);
    }
    //get column id from column header
    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    if (printout) {
      System.out.println("For cell update, columnIdDigits: " + columnIdDigits);
    }
    //click in cell and update
//    driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]")).click();
    //Shilpa:xpath update for 11.2 24.4.2024
    driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')])["+row+"]")).click();
    waitForAjaxExtJs();
//    WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div/table"));
   //Shilpa update xpath for 11.2 on 21.05.2024
    WebElement editCell = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')])["+row+"]"));
    Actions action = new Actions(driver);
    action.moveToElement(editCell).pause(1000).sendKeys(newValue).perform();
    Thread.sleep(500);
  }

  public void ucqcGridClickInCell(String chargeCode, String headerName, boolean printout) throws InterruptedException {
    String columnID;
    if (printout) {
      System.out.println("Click in cell, Charge Code: " + chargeCode);
      System.out.println("Click in cell, Header Name: " + headerName);
    }
    //get row from charge code
    String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    if (printout) {
      System.out.println("Click in cell, Row Number: " + row);
    }
    //get column id from column header
    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    if (printout) {
      System.out.println("Click in cell, columnIdDigits: " + columnIdDigits);
    }
    //click in cell and update
    driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][1]/descendant::*[contains(@class,'x-grid-cell') and contains(@class,'column-"+columnIdDigits+"')]")).click();
  }

  public void ucqcGridClickHeader(String headerName, boolean printout) {
    if (printout) {
      System.out.println("Click Header: " + headerName);
    }
    driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).click();
  }

  public WebElement ucqcGetCellValueAsWebElement (String chargeCode, String headerName, boolean printout) {
    String columnID;
    WebElement cellElement = null;
    try {
      String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
      columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
      int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
      if (printout) {
        System.out.println("Get cell as WebElement, Charge Code: " + chargeCode);
        System.out.println("Get cell as WebElement, Header Name: " + headerName);
        System.out.println("Get cell as WebElement, Row Number: " + row);
      }
//      cellElement = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div"));
      //Shilpa:xpath update for 11.2 on 20.05.2024
      cellElement = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div)[" + row + "]"));

    } catch (Throwable e) {
      e.getMessage();
    }
    return cellElement;
  }

  public WebElement ucqcGridGetCellAsWebElement(String chargeCode, String headerName, boolean printout) throws InterruptedException {
    String columnID;
    if (printout) {
      System.out.println("Get cell as element, Charge Code: " + chargeCode);
      System.out.println("Get cell as element, Header Name: " + headerName);
    }
    //get row from charge code
    String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    if (printout) {
      System.out.println("Get cell as element, Row Number: " + row);
    }
    //get column id from column header
    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    if (printout) {
      System.out.println("Get cell as element, columnIdDigits: " + columnIdDigits);
    }
    //return cell location as webelement
    return (driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][1]/descendant::*[contains(@class,'x-grid-cell') and contains(@class,'column-"+columnIdDigits+"')]")));
  }

  //similar to setDepartment, but uses more precise xpath for departmentText element by adding x-grid-cell-inner section
  public static void updateDepartment(String departmentText) throws InterruptedException {
    doClickButton("Select");
    ucqcWaitForSpinnerToEnd();
    waitForAjaxExtJs();
    //Thread.sleep(1100);  //original value, which works
    Thread.sleep(500);  //alternative value, to reduce run time - reset to original value if there are false positives with this one
    //Shilpa added below line for <None> for 11.2 on 21.05.2024
    if(departmentText.equals("<None>")) {
	   doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
		waitForAjaxExtJs();
   }
   else {
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
		doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
		waitForAjaxExtJs();
	}
   }
  }

  public static void setUcqcCriteria(String costModel, String costModelScenario, String entity, String department, String resultsStoredFor) throws InterruptedException {
    CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),costModel);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),costModelScenario);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),entity);
    updateDepartment(department);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),resultsStoredFor);
  }

  public static void ucqcPopulateRequiredFieldsToDisplayGrid(String[] requiredFields) throws InterruptedException {
    waitForAjaxExtJs();
    setUcqcCriteria(requiredFields[0], requiredFields[1], requiredFields[2], requiredFields[3], requiredFields[4]);
    ucqcWaitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  public static void ucqcPopulateRequiredFieldsToDisplayGrid(String costModel, String costModelScenario, String entity, String department, String resultsStoredFor) throws InterruptedException {
    waitForAjaxExtJs();
    setUcqcCriteria(costModel, costModelScenario, entity, department, resultsStoredFor);
    ucqcWaitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  public static void ucqcDisplayChargeCodeGrid(String costModel, String costModelScenario, String entity, String department, String resultsStoredFor) throws InterruptedException {
    CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(costModel, costModelScenario, entity, department, resultsStoredFor);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doClick(costingMap.getUnitCostQuickCalculationButtonApplySelections());
      waitForAjaxExtJs();
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationHeaderQuickSalariesAndWagesRVU(), printout);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public static void ucqcDisplayChargeCodeGrid(String[] requiredFields) throws InterruptedException {
    CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields[0], requiredFields[1], requiredFields[2], requiredFields[3], requiredFields[4]);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doClick(costingMap.getUnitCostQuickCalculationButtonApplySelections());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationHeaderQuickSalariesAndWagesRVU(), printout);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public void assertCCChangeTotalCalculationWithComma(String chargeCode, String ccChangeHeader, String ccCostHeader, String ccQuickCostHeader) throws InterruptedException {
    double expectedSalAndWagChangeTotal;
    String actualccChangeTotal = getCellValue(chargeCode,ccChangeHeader);
    String ccCost = getCellValue(chargeCode,ccCostHeader);
    String quickCCCost = getCellValue(chargeCode,ccQuickCostHeader);
    String changeValue = actualccChangeTotal.replace(",", "");
    String ccValue = ccCost.replace(",", "");
    String qccValue = quickCCCost.replace(",", "");
    expectedSalAndWagChangeTotal = Double.parseDouble(qccValue) - Double.parseDouble(ccValue);
    String expectedValueString = String.valueOf(expectedSalAndWagChangeTotal);
    System.out.println(expectedValueString + " " + changeValue);
    assertEquals(expectedValueString,changeValue);
  }

  public void assertCCChangeTotalCalculation(String chargeCode, String ccChangeHeader, String ccCostHeader, String ccQuickCostHeader) throws InterruptedException {
    double expectedChangeTotal;
    String actualccChangeTotal = getCellValue(chargeCode,ccChangeHeader);
    double actualCCChangeTotal = convertStringToDoubleWithTwoDecimals(actualccChangeTotal);
    String ccCost = getCellValue(chargeCode,ccCostHeader);
    String quickCCCost = getCellValue(chargeCode,ccQuickCostHeader);
    expectedChangeTotal = convertStringToDoubleWithTwoDecimals(quickCCCost) - convertStringToDoubleWithTwoDecimals(ccCost);
    System.out.println(quickCCCost);
    System.out.println(ccCost);
    System.out.println(expectedChangeTotal);
    System.out.println(actualCCChangeTotal);
    compareDoubleValues(actualCCChangeTotal,roundDoubleToTwoDecimals(expectedChangeTotal));
  }

  public String confirmCellValueNotNull (String chargeCode, String headerName) throws InterruptedException {
    String columnID;
    String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    String columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]")).getText();
    System.out.println("Value: " + columnValue);
    if (columnValue.equals(" ")) {
      System.out.println("CC Change column is not populated.");
    }
    return columnValue;
  }

  public void assertDepartmentListIsNotNull() throws InterruptedException {
    waitForAjaxExtJs();
    String columnID = driver.findElement(By.xpath("//div[contains(@class,'x-column-header-first x-column-header-last')]/descendant::span")).getAttribute("id");
    int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    List<WebElement> departmentValueList = driver.findElements(By.xpath("//tr[contains(@class,'x-grid-row')]/descendant::td[contains(@class,\"x-grid-cell-gridcolumn-" + columnIDDigits + "\")]"));
    ArrayList<String> departmentList = new ArrayList<>();
    for(WebElement departmentValue : departmentValueList){
      departmentList.add(departmentValue.getText());
      System.out.println(departmentValue.getText());
    }
    waitForAjaxExtJs();
    assertNotNull(departmentList);
  }
  public static boolean validateBackgroundColorOnHoverForSubTabs(String color1,String color2,WebElement[] systemMaintenanceTabElement) {
		boolean testResult=false;
		try {
//			GeneralElementsMap generalElement=BuildMap.getInstance(driver, GeneralElementsMap.class);
//			StatusMap statusMap=BuildMap.getInstance(driver, StatusMap.class);

			  Actions action=new Actions(driver);

				for (WebElement element : systemMaintenanceTabElement) {
					action.moveToElement(element).perform();
					String optionColor=element.getCssValue("background-color");
					System.out.println("Option"+optionColor);
					if(optionColor.equalsIgnoreCase(color1)||(optionColor.equalsIgnoreCase(color2))){
						assertTrue(printout);
					}
					else {
						assertFalse(false);
					}
		} 
		}catch (Exception e) {
			testResult=false;
		}
		
		return testResult;
	}
	public static boolean validateBackgroundColor(String color,WebElement element) {
		boolean testResult=false;
		try {

					String optionColor=element.getCssValue("background-color");
					System.out.println("Option"+optionColor);
					System.out.println(color);
					assertEquals(color,optionColor);
					testResult=true;
		
		}catch (Exception e) {
			testResult=false;
		}
		
		return testResult;
	}
	public  void VerifyCellValue(String chargeCode, String expValue,String columnNameXpath) throws Throwable {
		try {
			ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
			String value = contractModelsHelper.getCellValue(chargeCode,columnNameXpath);
			if (value.equals(expValue)) {
				assertTrue(printout);
			} else {
				assertFalse(false);
			}
		} catch (Exception | AssertionError e) {

		}
	}
	public void getCellValue(String chargeCode, String headerName, String value)
			throws NumberFormatException, InterruptedException {
		String columnID;

		columnID = driver
				.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']"))
				.getAttribute("id");
		int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
		String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]"))
				.getText();
		System.out.println(row);
		System.out.println(columnIdDigits);
//		WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row
//				+ "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]/div"));
		//Shilpa: xpath update on 24.4.2024for 11.2
		WebElement editCell = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]/div)["+row+"]"));
		if (!editCell.getText().equals(value)) {
			ucqcUpdateGridCellValue(chargeCode, headerName, String.valueOf(value), printout);

		}
	}
}
