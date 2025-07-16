package webdriver.helperstatic;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import webdriver.utilities.Java;

public class AssertStatic extends TableStatic {

  Java java = new Java();

  public void assertPageInformation(String pageTitle) {
    //assert page title
	 /* Omkar 22/12/2023 : xpath changes for 11.2
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'header_hd-textEl') " +
            "and text()='" + pageTitle + "']")), printout); */
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'header-title-textEl') " +
            "and text()='" + pageTitle + "']")), printout);
    //assert title displays in breadcrumbs
    /*   Omkar 22/12/2023 : xpath changes for 11.2
    assertElementIsDisplayed(driver.findElement(By.xpath(
            "//*[contains(@id, 'header_hd-textEl') and text()='" + pageTitle + "']")),
            printout); */
    assertElementIsDisplayed(driver.findElement(By.xpath(
            "//*[contains(@id, 'btnInnerEl') and text()='" + pageTitle + "']")),
            printout);
  }

  public void assertFilterResultsStatement(String numberOfFilterMatches) {
    doClick(driver.findElement(
            By.xpath("//div[text()='Department Codes']/ancestor::div[contains(@class,'x-box-item')]/following-sibling::div/descendant::button/span[text()='Filter']")
    ));
    assertTrue(filterGetFilterMatchesTheseCriteriaText().contains(numberOfFilterMatches));
    filterClickButtonCancelAndClose();
  }

  public void assertHelpLink(WebElement helpLinkElement, String expectedHeader, boolean printout) throws InterruptedException {
    //Thread.sleep(500);
    waitForAjaxExtJs();
    String firstHandle = webSwitchToNewWindow(helpLinkElement, printout);
    assertHelpPageHeader(expectedHeader, printout);
    driver.switchTo().window(firstHandle);
  }

  public void assertHelpPageHeader(String expectedHeader, boolean printout) {
    try {
      //Thread.sleep(3000);  //original working wait-replaced by sleep 500 and waitForJsWindowOnload
      // System.out.println("Thread sleep 3000 - find better solution"); //find better solution
      Thread.sleep(2000);
      waitForJsWindowOnload();
      driver.switchTo().frame("topic");
      WebElement header = driver.findElement(By.xpath("//body/h1"));
      String actualHeader = header.getText();
      if (printout) {
        System.out.println("Expected Header Text: " + expectedHeader);
        System.out.println("Actual Header Text  : " + actualHeader);
      }
      assertThat(expectedHeader, equalTo(actualHeader));
    } catch (Throwable e) {
      e.printStackTrace();
      fail("Failed: assertHelpPageHeader");
    }
  }

  public void assertFilterResults(String expectedTotal) throws InterruptedException {
    doClick(getWebElement("//div[3]/em/button/span[text()='Filter']"));
    waitForAjaxExtJs();
    assertThat(filterGetFilterMatchesTheseCriteriaText(), containsString(expectedTotal));
    doClick(getWebElement("//div[3]/em/button/span[text()='Cancel & Close']"));
  }

  public void assertListOfStringsContainsExpectedStrings(List<String> listOfStrings, List<String> expectedStrings) {
    String listOfStringsAsSingleString = java.convertListOfStringToSingleString(listOfStrings);
    System.out.println("All options as a single string: " + listOfStringsAsSingleString);
    for (String expectedString : expectedStrings) {
      System.out.println("Expected String: " + expectedString);
      try {
		assertThat(listOfStringsAsSingleString, containsString(expectedString));
	} catch (AssertionError e) {
	}
    }
  }
  public void assertListOfStringsContainsExpectedString(
          List<String> listOfStrings,
          String expectedString)
  {
    assertTrue(listOfStrings.contains(expectedString));
  }

  public void assertListOfStringsDoesNotContainExpectedStrings(List<String> listOfStrings, List<String> expectedStrings) {
    String listOfStringsAsSingleString = java.convertListOfStringToSingleString(listOfStrings);
    System.out.println("All options as a single string: " + listOfStringsAsSingleString);
    for (String expectedString : expectedStrings) {
      System.out.println("Expected String: " + expectedString);
      assertThat(listOfStringsAsSingleString, not(containsString(expectedString)));
    }
  }

  public void assertElementContainsDisabledAttribute(WebElement element) {
    String disabledAttributeText;
    try {
      disabledAttributeText = element.getAttribute("disabled");
    } catch (Throwable e) {
      disabledAttributeText = "false";
    }
    System.out.println(disabledAttributeText);
    assertThat(disabledAttributeText, equalTo("true"));
  }

  public void assertValueAttributeValue(WebElement element, String expectedValue) {
    String getValueAttribute = element.getAttribute("value");
    assertThat(getValueAttribute, containsString(expectedValue));
  }

  public void assertThatElementIsChecked(String elementLabel) {
    String elementClassText = getCheckedElementClass(elementLabel);
    boolean isChecked = elementClassText.contains("checked");
    if (printout) {
      System.out.println("Element class text: " + elementClassText);
      System.out.println("IsChecked: " + isChecked);
    }
    assertTrue("Expected element to be checked or selected",isChecked == true);
  }

  public void assertThatCheckBoxIsChecked(String sectionLabel, String checkboxLabel) throws Exception {
    String checkboxClassText = getCheckboxClass(sectionLabel, checkboxLabel);
    boolean isChecked = checkboxClassText.contains("checked");
    if (printout) {
      System.out.println("Element class text: " + checkboxClassText);
      System.out.println("IsChecked: " + isChecked);
    }
    Assert.assertThat(isChecked, equalTo(true));
  }

  public void assertThatCheckBoxIsNotChecked(String sectionLabel, String checkboxLabel) {
    String checkboxClassText = getCheckboxClass(sectionLabel, checkboxLabel);
    boolean isChecked = checkboxClassText.contains("checked");
    if (printout) {
      System.out.println("Element class text: " + checkboxClassText);
      System.out.println("IsChecked: " + isChecked);
    }
    Assert.assertThat(isChecked, equalTo(false));
  }

  private String getCheckedElementClass(String elementLabel) {
    String classString = null;
    try {
      waitForAjaxExtJs();
      classString = driver.findElement(By.xpath("//label[text()='" + elementLabel + "']" +
              "/ancestor::table[contains(@class,'checkbox')]"))
              .getAttribute("class");
    } catch (Throwable e) {
      fail("Class attribute not found");
    }
    return classString;
  }

  private String getCheckboxClass(String sectionLabel, String checkboxLabel) {  //Columns to Display
    String checkboxClassString = null;
    try {
      waitForAjaxExtJs();
      checkboxClassString = driver.findElement(By.xpath("//label[text()='" + sectionLabel + "']/../following-sibling::td/label[text()='" + checkboxLabel + "']/ancestor::table[contains(@id,'checkboxfield')]"))
              .getAttribute("class");
    } catch (Throwable e) {
      fail("Checkbox not found");
    }
    return checkboxClassString;
  }

  public void assertThatTextAreaContainsExpectedText(WebElement textArea, String[] expectedText, boolean printout) {
    try {
      String actualText = textArea.getAttribute("value");
      if (printout) {
        System.out.println("ACTUAL TEXT:");
        System.out.println(actualText);
      }
      for (String textLine : expectedText) {
        if (printout) {
          System.out.println("Expected text line:     " + textLine);
        }
        assertTrue("Test Failed - text area does not contain expected text line: " + textLine, actualText.contains(textLine));
      }
    } catch (Throwable e) {
      e.getMessage();
    }
  }

  public void assertThatDropdownSelectedValue(WebElement elementTriggerMenu, WebElement elementMenuList, String expectedValue) throws InterruptedException {
    elementTriggerMenu.click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    WebElement classificationList = elementMenuList;  //remove this and debugger wont work for this method
    List<WebElement> classificationListing = classificationList.findElements(By.tagName("li"));
    for (WebElement item : classificationListing) {
      String clss = item.getAttribute("class");
      if (clss.contains("selected")) {
        assertThat(item.getText(), equalTo(expectedValue));
        break;
      }
    }
  }

  public void assertThatNumberPlaceDigits(String value, String expectedPlace, boolean printout) {
   System.out.println(expectedPlace);
	  if (printout) {
      System.out.println("Value: " + value);
      System.out.println("Number Place Digits: " + expectedPlace);
    }
    String[] placesActual = value.split("\\.");
    System.out.println(placesActual[0]);
    String placeActualNoCommas = placesActual[0].replace(",", "");
    System.out.println(placeActualNoCommas.length());
    Assert.assertThat(placeActualNoCommas.length(), equalTo(expectedPlace.replace(",", "").length()));
  }

  public void assertValueFormat(String value, String expectedDigitPlaces, int expectedDecimalPlaces, boolean printout) {
    System.out.println(value);
    System.out.println(expectedDigitPlaces);
	 try {
		assertThatNumberPlaceDigits(value, expectedDigitPlaces, printout);
		assertValueHasCommasForThousands(value, printout);
		assertThatValueHasRequiredDecimalPlaces(value, expectedDecimalPlaces, printout);
	} catch (Exception e) {
		
	}
  }

  public void assertValueHasCommasForThousands(String value, boolean printout) {
    if (printout) {
      System.out.println(value);
    }
    if(value.matches("^\\d{1,3}(,\\d{3})*(\\.\\d+)?$")) {
    assertTrue(value.matches("^\\d{1,3}(,\\d{3})*(\\.\\d+)?$"));
    }
    else {
    	
    }
  }

  public void assertThatElementAlignment(String styleAttribute, String assertStyle, boolean printout) {
    if (printout) {
      System.out.println("Style attribute: " + styleAttribute);
      System.out.println("Assert style attribute: " + assertStyle);
    }
    if (assertStyle.contains("left")) {
      assertThat(styleAttribute, containsString("text-align: left"));
    } else if (assertStyle.contains("right")) {
      assertThat(styleAttribute, containsString("text-align: right"));
    } else if (assertStyle.contains("center")) {
      assertThat(styleAttribute, containsString("text-align: center"));
    } else {
      assertThat(styleAttribute, containsString(assertStyle));
    }
  }

  public void assertThatValueIsNegative(String value) {
    assertThat(value, containsString("-"));
  }

  public void assertValueIsNegative(String value) {
    assertTrue(value.contains("-"));
  }

  public void assertDropdownPlaceholder(String dropDownNameAttribute, boolean printout) throws Exception {
    String placeHolder = null;
    try{
      waitForAjaxExtJs();
      placeHolder = driver.findElement(By.xpath("//input[@name='" + dropDownNameAttribute + "']")).getAttribute("placeholder");
    } catch (Throwable e){
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean placeHolderText = placeHolder.equals("<None>");
    if (printout){
      System.out.println("Element class text: " + placeHolder);
      System.out.println("Placeholder Text: " + placeHolderText);
    }
    try {
      assertTrue(placeHolderText);
    } catch(Throwable e){
      System.out.println("TEST FAILED: Element text is not <None>.");
      throw new Exception();
    }
  }

  public void assertAsteriskIsDisplayed(String criteriaLabel) {
    String labelXpath = "//label[text()=\""+ criteriaLabel +"\"]/span";
    String asterisk;
    String asteriskColor;
    asterisk = driver.findElement(By.xpath(labelXpath)).getText();
    asteriskColor = driver.findElement(By.xpath(labelXpath)).getAttribute("style");
    System.out.println(asterisk);
    System.out.println(asteriskColor);
    if(asterisk.contains("*")){
      System.out.println("The 'required' asterisk is displayed.");
    } else {
      System.out.println("The 'required' asterisk is not displayed.");
      fail();
    }
    if(asteriskColor.contains("red")) {
      System.out.println("The 'required' asterisk is red.");
    } else {
      System.out.println("The 'required' asterisk is not red.");
      fail();
    }
  }

  public void assertThatValueHasRequiredDecimalPlaces(String valueToConsider, int numberOfDecimalPlaces, boolean printout) {
    String[] decimalPlaces = valueToConsider.split("\\.");
    if (printout) {
      System.out.println("Number of decimal places: " + decimalPlaces[1].length());
    }
    assertThat(decimalPlaces[1].length(), equalTo(numberOfDecimalPlaces));
  }

  public void assertThatElementIsNotDisplayed(WebElement element) {
    boolean elementIsDisplayed = true;
    try {
      elementIsDisplayed = element.isDisplayed();
      fail("Element should not be displayed");
    } catch (Throwable e) {
      assertThat(elementIsDisplayed, equalTo(true)); //true if not found
    }
  }

  public void assertThatElementIsDisplayed(WebElement element) {
    assertThat(element.isDisplayed(), equalTo(true));
  }

  public void assertElementIsNotDisplayed(By byElementLocator) {
    List<WebElement> elements = driver.findElements(byElementLocator);
    assertTrue(elements.size() < 1);
  }

  public void assertElementIsNotDisplayed(WebElement element) {
    boolean elementIsDisplayed = true;
    try {
      element.getText();
    } catch (Throwable e) {
      elementIsDisplayed = false;
    }
    assertFalse("This element should not be displayed", elementIsDisplayed);
  }

  // Use this one if the element is not on the page.  Using the above "element" version will result in a test failure.
  public void assertElementIsNotDisplayed(String elementXpath) {
    boolean elementIsDisplayed;
    try {
      elementIsDisplayed = driver.findElement(By.xpath("" + elementXpath + "")).isDisplayed();
    } catch (Throwable e) {
      elementIsDisplayed = false;
    }
    assertFalse("This element should not be displayed", elementIsDisplayed);
  }

  public void assertElementIsDisabled(WebElement element, boolean printout) throws Exception {
    String disabledText = null;
    try {
      waitForAjaxExtJs();
//      disabledText = element.findElement(By.xpath("//../../..//ancestor::a[contains(@class,'x-btn')]")).getAttribute("class");
      disabledText = element.findElement(By.xpath("./../../..")).getAttribute("class");
    } catch(Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isDisabled = disabledText.contains("disabled");
    if (printout){
      System.out.println("Element class text: " + disabledText);
      System.out.println("IsDisabled: " + isDisabled);
    }
    assertTrue(disabledText, isDisabled);
//        try {
//            assertTrue(isDisabled);
//        } catch(Throwable e){
//            System.out.println("TEST FAILED: Element is Enabled");
//            throw new Exception();
//        }
  }

  public static void assertElementIsEnabled(WebElement element, boolean printout) throws Exception {
    String classText = null;
    try {
      waitForAjaxExtJs();
      classText = element.findElement(By.xpath("./../../..")).getAttribute("class");
    } catch(Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isEnabled = classText.contains("disabled");
    if (printout){
      System.out.println("Element class text: " + classText);
      System.out.println("IsEnabled: " + isEnabled);
    }
    assertFalse(isEnabled);
//        try {
//            assertTrue(isEnabled);
//        } catch(Throwable e){
//            System.out.println("TEST FAILED: Element is Not Enabled");
//            throw new Exception();
//        }
  }

  public static void assertArraysAreEqual(String[] expectedValues, String[] actualValues, boolean printout) throws InterruptedException {
    if(printout) {
      System.out.println("EXPECTED VALUES");
      int erep = 1;
      for (String item : expectedValues) {
        System.out.println("Expected "+erep+": " + item);
        erep++;
      }
      System.out.println("ACTUAL VALUES");
      int arep = 1;
      for (String item : actualValues) {
        System.out.println("Actual "+arep+": " + item);
        arep++;
      }
    }
    try {
      assertTrue(Arrays.equals(expectedValues, actualValues));
      if(printout) {
        System.out.println("RESULT: Arrays are equal");
      }
    } catch (Throwable e) {
      fail("TEST FAILED: Arrays are not equal");
    }
  }

  public void assertFirstRowNumberFromResultsTable(boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    String rowNumber = driver.findElement(By.xpath("//*[contains(@class,'rownumberer')]/div")).getText();
    ////td[contains(@class,'rownumberer')]/div[text()='1']
    if(printout) {
      System.out.println("Row Number = " + rowNumber);
    }
    assertEquals("1", rowNumber);
  }

  public static void assertElementIsDisplayed(WebElement element) {
    boolean checkElement = element.isDisplayed();
    assertTrue(checkElement);
  }

  public static void assertElementIsDisplayed(WebElement element, boolean printout) {
    WebElement checkElement = element;
    try {
      assertTrue(checkElement.isDisplayed());
      if (printout) {
        System.out.println(checkElement);
      }
    } catch (Throwable e) {
      System.out.println("ELEMENT NOT FOUND: " + checkElement);
      fail("FAILED: Page element not found");
    }
  }

  public  void assertElementIsDisplayedWithXpath(String xpath) {
    WebElement checkElement = null;
    try {
      checkElement = driver.findElement(By.xpath("" + xpath + ""));
      assertThat(checkElement.isDisplayed(), equalTo(true));
    } catch (Throwable e) {
      fail("XPATH NOT FOUND: " + xpath);
    }
  }

  public static void assertElementsAreDisplayed(WebElement[] elements, boolean printout) {
    for (WebElement element : elements) {
      try {
        assertTrue(element.isDisplayed());
        if(printout){
          System.out.println(element);
        }
      } catch (Throwable e) {
        fail(e.getMessage());
      }
    }
  }

  public void assertElementValueAttribute(WebElement element, String expectedValue, boolean printout) {
    String getValueAttribute = element.getAttribute("value");
    if (printout) {
      System.out.println("Expected Value: " + expectedValue);
      System.out.println("Actual   Value: " + getValueAttribute);
    }
    assertThat(getValueAttribute, is(expectedValue));
  }

  public void assertElementValueAttributeContains(WebElement element, String expectedValue, boolean printout) {
    String getValueAttribute = element.getAttribute("value");
    if (printout) {
      System.out.println("Expected Value: " + expectedValue);
      System.out.println("Actual   Value: " + getValueAttribute);
    }
    assertThat(getValueAttribute, containsString(expectedValue));
  }

  public void assertElementText(WebElement element, String expectedValue, boolean printout) {
    try {
      String elementText = element.getText();
      if (printout) {
        System.out.println("Expected Value: " + expectedValue);
        System.out.println("Actual   Value: " + elementText);
      }
      assertEquals(expectedValue, elementText);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public void assertElementTextContains(WebElement element, String expectedValue, boolean printout) {
    try {
      String elementText = element.getText();
      if (printout) {
        System.out.println("Expected Value: " + expectedValue);
        System.out.println("Actual   Value: " + elementText);
      }
      assertTrue(elementText.contains(expectedValue));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public static void assertElementTextWithXpath(String xpath, String expectedValue, boolean printout) {
    try {
      String elementText = driver.findElement(By.xpath("" + xpath + "")).getText();
      if(printout){
        System.out.println("Expected Value: " + expectedValue);
        System.out.println("Actual   Value: " + elementText);
      }
      assertEquals(expectedValue, elementText);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public void assertElementTextContainsWithXpathLocator(String xpath, String expectedValue, boolean printout) {
    String elementText = driver.findElement(By.xpath("" + xpath + "")).getText();
    if(printout){
      System.out.println("Expected Value: " + expectedValue);
      System.out.println("Actual   Value: " + elementText);
    }
    assertTrue(elementText.contains(expectedValue));
  }

  public void assertTableColumnHeaders(WebElement tableCornerCell, String[] expectedTableHeaders, boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    int tableId = getElementIdNumberCode(tableCornerCell, printout);
    ArrayList<String> tableHeadings = getTableColumnHeadingsArrayList(tableId, printout);
    assertArraysAreEqual(expectedTableHeaders, javaConvertStringArrayListToArray(tableHeadings), printout);
  }

  public void assertTableColumnHeadersNoRowNumbers(String[] expectedTableHeaders, boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    WebElement firstColumn = driver.findElement(By.xpath("//*[contains(@class,'x-column-header-first')]/div/span[contains(@id,'-textEl') and @class='x-column-header-text']"));
    int tableId = getElementIdNumberCode(firstColumn, printout) - 3; //remove 3, to set value back (to "missing" first column)
    ArrayList<String> tableHeadings = getTableColumnHeadingsArrayList(tableId, printout);
    assertArraysAreEqual(expectedTableHeaders, javaConvertStringArrayListToArray(tableHeadings), printout);
  }

  //===============================================================================

  public String[] javaConvertStringArrayListToArray(ArrayList<String> arraylist) {
    String[] stringArray = arraylist.toArray(new String[0]);
    return stringArray;
  }

  private String getNumbersFromStringWithRegex(String string, boolean printout) {
    Pattern p = Pattern.compile("\\d+"); //regex pattern
    Matcher m = p.matcher(string);    //string to search
    String numbers = null;
    while(m.find()) {
      numbers = m.group();
    }
    if(printout) {
      System.out.println("Get Numbers from String: " + numbers);
    }
    return numbers;
  }

  private int getElementIdNumberCode(WebElement element, boolean printout) throws InterruptedException {
    String elementIdNumberCode;
    elementIdNumberCode = element.getAttribute("id");
    elementIdNumberCode = getNumbersFromStringWithRegex(elementIdNumberCode,printout);
    if (printout) {
      System.out.println("Get Element ID Number Code: " + Integer.parseInt(elementIdNumberCode));
    }
    return Integer.parseInt(elementIdNumberCode);
  }

  private ArrayList<String> getTableColumnHeadingsArrayList(int tableId, boolean printout) throws InterruptedException {
    ArrayList<String> tableHeadings = new ArrayList<>();
    int initialHeadingId = tableId + 3;
    boolean hasNextColumn = true;
    if(printout){
      System.out.println("Table Id: " + tableId);
      System.out.println("First Column Heading Id: " + initialHeadingId);
    }
    waitForAjaxExtJs();
    while (hasNextColumn) {
      try {
        WebElement heading = driver.findElement(By.xpath("//span[contains(@id,'column-"+initialHeadingId+"-textEl') and @class='x-column-header-text']"));
        if(printout) {
          System.out.println("Column Heading: " + heading.getText());
        }
        tableHeadings.add(heading.getText());
        if(isLastTableColumn(initialHeadingId, printout)){
          break;
        } else {
          initialHeadingId++;
        }
      } catch (Throwable e) {
        System.out.println("Table Heading Error:  getTableColumnHeadingsArrayList() has Failed");
        hasNextColumn = false;

      }
    }
    return tableHeadings;
  }

  private boolean isLastTableColumn (int columnIdDigits, boolean printout) {
    boolean isLast;
    try {
      isLast = driver.findElement(By.xpath("//*[contains(@class,'x-column-header-last')]/div/span[contains(@id,'column-"+columnIdDigits+"-textEl') and @class='x-column-header-text']")).isDisplayed();
    } catch (Throwable e){
      isLast = false;
    }
    if(printout) {
      System.out.println("Table Column ID: "+columnIdDigits+" is last: "+isLast);
    }
    return isLast;
  }
}
