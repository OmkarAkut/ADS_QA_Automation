package webdriver.helpers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import webdriver.corehelpers.GoHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.utilities.JavaDataBaseConnectivity;

public class CimHelper extends CalculationHelper {
	private static DialogsMap dialog;
	public final static String setStandardDateFormat = "yyyy-MM-dd:HH:mm:ss";
	private static Actions builder;
	private static CimMap cimMap;
	private static CostingMap costing;
	private static String cimGroupName;
	private static Random random = new Random();
	
	public static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	public static String cimScenarioCreate = "CIM" + currentDateTime;
	static String[] filterCim = { "Name", "Is", "Equal To", cimScenarioCreate };

	@BeforeClass
	public static void setupHelper() {
		dialog = BuildMap.getInstance(driver, DialogsMap.class);
		cimMap = BuildMap.getInstance(driver, CimMap.class);
		costing = BuildMap.getInstance(driver, CostingMap.class);
		;
		builder = new Actions(driver);
	}

	public static String getProperty() {
		String PROPS =System.getProperty("user.dir") + "/src/selenium/webdriver.properties";
		Properties props=new Properties();
		String calctype = null;
		try {
			FileInputStream input=new FileInputStream(PROPS);
			props.load(input);
			calctype=props.getProperty("CALCTYPE");
		}catch(Exception e ) {
			
		}
		System.out.println(calctype);
		return calctype;
		
	}
	public static String getPageHeaderPath(String headerText) {
//		String xpath = "//*[contains(@class, 'areaTitle') and text() = '"+headerText+"']";
		String xpath = "//*[contains(@id, 'container') and text() = '" + headerText + "']";
		return xpath;
	}

	public void setDataGridCellValue(String chargeCode, String headerName, String newValue, boolean printout)
			throws InterruptedException {
		String columnID;
		if (printout) {
			System.out.println("For cell update, Charge Code: " + chargeCode);
			System.out.println("For cell update, Header Name: " + headerName);
			System.out.println("For cell update, New Value: " + newValue);
		}
		// get row from charge code
		String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]"))
				.getText();
		if (printout) {
			System.out.println("For cell update, Row Number: " + row);
		}
		// get column id from column header
		columnID = driver
				.findElement(
						By.xpath("//*[contains(@class,'column-header-text')][contains(text(),'" + headerName + "')]"))
				.getAttribute("id");
		int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
		if (printout) {
			System.out.println("For cell update, columnIdDigits: " + columnIdDigits);
		}
		// click in cell and update
//		driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]")).click();
		driver.findElement(
				By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"
						+ columnIdDigits + "')])[" + row + "]"))
				.click();

		waitForAjaxExtJs();
//		WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div/table"));
		// Shilpa update xpath for 11.2 on 12.07.2023
		WebElement editCell = driver.findElement(
				By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"
						+ columnIdDigits + "')])[" + row + "]"));
		Actions action = new Actions(driver);
		// action.moveToElement(editCell).sendKeys(newValue).perform();

		action.moveToElement(editCell).clickAndHold().sendKeys(Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(1000);
		action.moveToElement(editCell).clickAndHold().sendKeys(newValue).sendKeys(Keys.chord(Keys.ENTER)).build()
				.perform();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
	}

	public void setFilterValues(WebElement fieldOptionsUl, String field, String operator, String condition,
			String value) throws InterruptedException {
		waitForAjaxExtJs();
		setDropdownValue(dialog.getFilterDialogDropdownField(), fieldOptionsUl, field, printout);
		setDropdownValue(dialog.getFilterDialogDropdownOperator(), operator, printout);
		setDropdownValue(dialog.getFilterDialogDropdownCondition(), condition, printout);
		webdriverClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(value);
	}

	public void setDropdownValue(WebElement triggerDropdown, String value, boolean printout)
			throws InterruptedException {
		setDropdownValue(triggerDropdown, null, value, printout);
	}

	public void setDropdownValue(WebElement triggerDropdown, WebElement menuOptionsUl, String value, boolean printout)
			throws InterruptedException {
		waitForAjaxExtJs();
		Thread.sleep(500);
		webdriverClick(triggerDropdown);
		waitForAjaxExtJs();
		WebElement optionsUl;
		if (menuOptionsUl != null) {
			optionsUl = menuOptionsUl;
		} else {
//			Omkar 14/04/2023 : xpath changes for 11.2
//			optionsUl = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
			optionsUl = driver
					.findElement(By.xpath("//div[contains(@class,'floating')]//div[contains(@id,'listWrap')]/ul"));
		}
		if (printout) {
			System.out.println("menuOptionsUl: " + menuOptionsUl);
		}
		List<WebElement> options = optionsUl.findElements(By.tagName("li"));
		for (WebElement option : options) {
			if (option.getText().equals(value)) {
				if (printout) {
					System.out.println("Menu option set to: " + option);
				}
				Thread.sleep(200);
				option.click();
				break;
			}
		}
	}

	public void filterClickButtonApplyFilter() {
		driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
	}

	public void filterClickButtonCancelAndClose() {
		driver.findElement(
				By.xpath("//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']"))
				.click();
	}

	public String filterGetFilterMatchesTheseCriteriaText() {
		String getFilterMatchesText = driver
				.findElement(By.xpath("//label[contains(text(), 'Filter to Match These Criteria')]")).getText();
		return getFilterMatchesText;
	}

	public String filterGetNumberOfMatches() {
		String text = filterGetFilterMatchesTheseCriteriaText();
		String[] parsed = text.split(" ");
		String[] finalParse = parsed[5].split("/");
		return finalParse[0];
	}

	public void doFilter(String[][] filterStatements, WebElement fieldOptionsUl) throws InterruptedException {
		for (String[] filterParameters : filterStatements) {
			if (printout) {
				System.out.println("Set Filter To: " + filterParameters[0] + filterParameters[1] + filterParameters[2]
						+ filterParameters[3]);
			}
			setFilterValues(fieldOptionsUl, filterParameters[0], filterParameters[1], filterParameters[2],
					filterParameters[3]);
			waitForAjaxExtJs();
			waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
			doClick(dialog.getFilterDialogButtonAdd());
			waitForAjaxExtJs();
		}
		doClick(dialog.getFilterDialogButtonApplyFilter());
		waitForSpinnerToEnd();
	}

	public void doFilterCreateCIM(String[] filterParameters) throws InterruptedException {
		System.out.println(filterParameters[0]);
		doFilterSetFilterParameters(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
//		waitForAjaxExtJs();
//		Omkar 21/4/2023 : Commenting below method call as the method is not computing the xpath correctly
//		waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
		driverDelay();
		waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
		doClick(dialog.getFilterDialogButtonAdd());
		waitForAjaxExtJs();
		doClick(dialog.getFilterDialogButtonApplyFilter());
		waitForSpinnerToEnd();
	}

	public void doFilterCreateIsOneOf(String[] filterParameters) throws InterruptedException {
		System.out.println(filterParameters[0]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(), dialog.getFilterDialogDropdownField(),
				filterParameters[0]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),
				dialog.getFilterDialogDropdownOperator(), filterParameters[1]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),
				dialog.getFilterDialogDropdownCondition(), filterParameters[2]);
//		doClick(dialog.getFilterDialogFormFieldValue());
//		dialog.getFilterDialogFormFieldValue().sendKeys(value);
//		doFilterSetFilterParameters(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
//		waitForAjaxExtJs();
//		Omkar 21/4/2023 : Commenting below method call as the method is not computing the xpath correctly
//		waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
//		driverDelay();
//		doClick("//div[contains(@id,'filterwindow')]//span[text() = 'Add Value']");

	}

	public void doFilterCreateForDate(String[] filterParameters) throws InterruptedException {
		System.out.println(filterParameters[0]);
		doFilterSetFilterParametersForDate(filterParameters[0], filterParameters[1], filterParameters[2],
				filterParameters[3]);
		waitForAjaxExtJs();
		waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
		doClick(dialog.getFilterDialogButtonAdd());
		waitForAjaxExtJs();
		doClick(dialog.getFilterDialogButtonApplyFilter());
		waitForSpinnerToEnd();
	}

	public void addFilter() {
		try {
			doClick(dialog.getFilterDialogButtonAdd());
			waitForAjaxExtJs();
			driverDelay(300);
		} catch (Exception e) {

		}
	}

	public void doDropdownSelectUsingOptionTextWithelement(WebElement dropdownList, String optionText)
			throws InterruptedException {
		waitForAjaxExtJs();

		// WebElement list =
		// driver.findElement(By.xpath("(//div[contains(@id,'specialtagcombo')]//following::div[contains(@class,'floating')]//ul)["+i+"]"));
		List<WebElement> menu = dropdownList.findElements(By.tagName("li"));
		System.out.println(optionText);
		for (WebElement option : menu) {
			System.out.println("Value" + option.getText());
			if (option.getText().equals(optionText)) {
				option.click();
				break;
			}
		}

	}

	public void doDropdownSelectUsingOptionTextServices(WebElement dropdownList, WebElement element, String optionText)
			throws InterruptedException {
		waitForAjaxExtJs();
		doClick(element);
		waitForAjaxExtJs();
		driverDelay(300);

		// WebElement list =
		// driver.findElement(By.xpath("(//div[contains(@id,'specialtagcombo')]//following::div[contains(@class,'floating')]//ul)["+i+"]"));
		List<WebElement> menu = dropdownList.findElements(By.tagName("li"));
		System.out.println(optionText);
		for (WebElement option : menu) {
			System.out.println("Value" + option.getText());
			if (option.getText().equals(optionText)) {
//				Actions act=new Actions(driver);
//				act.moveToElement(option).click().perform();
//				option.click();
				doJsClick(option);
				break;
			}
		}

	}

	public void doFilterSetFilterParameters(String field, String operator, String condition, String value)
			throws InterruptedException {
		waitForAjaxExtJs();
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(), dialog.getFilterDialogDropdownField(),
				field);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),
				dialog.getFilterDialogDropdownOperator(), operator);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),
				dialog.getFilterDialogDropdownCondition(), condition);
		doClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(value);
	}

	public void doFilterSetFilterParametersForDate(String field, String operator, String condition, String value)
			throws InterruptedException {
		waitForAjaxExtJs();
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(), dialog.getFilterDialogDropdownField(),
				field);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),
				dialog.getFilterDialogDropdownOperator(), operator);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),
				dialog.getFilterDialogDropdownCondition(), condition);
		doClick(dialog.getstatusFilterDialogFieldValueDate());
		dialog.getstatusFilterDialogFieldValueDate().sendKeys(value);
	}

	public void doFilterSetFilterParameterswithElement(String field, String operator, String condition, String value)
			throws InterruptedException {
		waitForAjaxExtJs();
		doDropdownSelectUsingOptionTextServices(dialog.getfieldNAme(), dialog.getFilterDialogDropdownField(), field);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),
				dialog.getFilterDialogDropdownOperator(), operator);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),
				dialog.getFilterDialogDropdownCondition(), condition);
		doClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(value);
	}

	public void doSelectCalcStatusFilterCriteria(String criteriaOption, String optionText) throws InterruptedException {
		if (criteriaOption.equals("Field")) {
			doClick(driver.findElement(By.xpath("//input[@name='field']")));
			waitForAjaxExtJs();
			doClick(driver.findElement(By.xpath(
					"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][1]/descendant::ul/li[text()=\""
							+ optionText + "\"]")));
		}
		if (criteriaOption.equals("Operator")) {
			doClick(driver.findElement(By.xpath("//input[@name='operator']")));
			waitForAjaxExtJs();
			doClick(driver.findElement(By.xpath(
					"//div[@class='x-mask']/preceding::div[@class=\"x-boundlist x-boundlist-floating x-layer x-boundlist-default\"]/child::div/ul/li[text()='"
							+ optionText + "']")));
		}
		if (criteriaOption.equals("Condition")) {
			doSelectCalcStatusFilterConditionValue(optionText);
		}
		if (criteriaOption.equals("Value")) {
			doClick(driver.findElement(By.xpath("//input[@name='valuefield']")));
			driver.findElement(By.xpath("//input[@name='valuefield']")).sendKeys(optionText);
		}
	}

	public void doSelectCalcStatusFilterConditionValue(String conditionValue) {
		doClick(driver.findElement(By.xpath("//input[@name='condition']")));
		WebElement conditions = driver.findElement(By.xpath(
				"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul"));
		List<WebElement> existingConditions = conditions.findElements(By.tagName("li"));
		List<String> actualConditions = new ArrayList<>();
		for (WebElement condition : existingConditions) {
			// System.out.println("Actual condition listed: " + condition.getText());
			actualConditions.add(condition.getText());
		}

		if (conditionValue.equals("Equal To")) {
			doClick(driver.findElement(By.xpath(
					"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='"
							+ actualConditions.get(0) + "']")));
		}
		if (conditionValue.equals("Contains")) {
			doClick(driver.findElement(By.xpath(
					"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='"
							+ actualConditions.get(1) + "']")));
		}
		if (conditionValue.equals("Starts With")) {
			doClick(driver.findElement(By.xpath(
					"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='"
							+ actualConditions.get(2) + "']")));
		}
		if (conditionValue.equals("Ends With")) {
			doClick(driver.findElement(By.xpath(
					"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='"
							+ actualConditions.get(3) + "']")));
		}
		if (conditionValue.equals("One Of")) {
			doClick(driver.findElement(By.xpath(
					"//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='"
							+ actualConditions.get(4) + "']")));
		}
	}

	@SuppressWarnings("unused")
	private WebElement tableGetTableContainerElementWithTableHeading(String tableHeading) {
		return driver.findElement(
				By.xpath("//div[text()='" + tableHeading + "']/ancestor::div[contains(@class,'x-panel commonTBar')]"
						+ "/following-sibling::div/descendant::table/tbody"));
	}

	public static void tableClickColumnHeader(String headerText) {
		waitForPresenceOfElement(
				"//span[contains(@id,'gridcolumn') and @class='x-column-header-text' and text()='" + headerText + "']");
		driver.findElement(By.xpath(
				"//span[contains(@id,'gridcolumn') and @class='x-column-header-text' and text()='" + headerText + "']"))
				.click();
		waitForSpinnerToEnd();
	}

	public int getTableNumberOfNumberedRows(boolean printout) throws InterruptedException {
		waitForAjaxExtJs();
		WebElement table;
		List<WebElement> tablesPresent = driver.findElements(
				By.xpath("//div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody"));
		if (tablesPresent.size() > 1) {
			table = driver.findElement(By.xpath(
					"//div[contains(@id, 'adynamic')]/div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody"));

			// div[contains(@id, 'adynamic')]/div[contains(@id,
			// 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody
		} else {
			table = driver.findElement(
					By.xpath("//div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody"));
		}

		List<WebElement> rows = table
				.findElements(By.xpath("//tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'rownumberer')]"));
		if (printout) {
			System.out.println("Table rows count = " + rows.size());
		}
		return rows.size();
	}

	public List<WebElement> tableGetTableRows(WebElement tableContainerElement, String optionsTag) {
		ArrayList<WebElement> actualList = new ArrayList<>();
		if (optionsTag.equals("")) {
			optionsTag = "tr";
		}
		List<WebElement> list = javaMakeListOfWebElements(tableContainerElement, optionsTag);
		System.out.println(list.size());
		for (WebElement webelement : list) {
			if (webelement.getAttribute("class").contains("x-grid-row")) {
				actualList.add(webelement);
			} else {
				continue;
			}
		}
		return actualList;
	}

	public int tableGetTableColumnIdDigitsWithHeaderText(String columnHeaderText, boolean printout)
			throws InterruptedException {
		String columnId;
		columnId = driver
				.findElement(
						By.xpath("//*[contains(@class,'x-column-header-') and [text()='" + columnHeaderText + "']"))
				.getAttribute("id");
		int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnId));
		if (printout) {
			System.out.println("Column ID Digits: " + columnIdDigits);
		}
		return columnIdDigits;
	}

	public void tableClickCell(String cellValue, int columnIndex) {
		WebElement element = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]["
				+ columnIndex + "]/*[text()='" + cellValue + "']"));
		element.click();
	}

	public void tableClickCellFirstColumn(String cellValue) {
//		WebElement element = driver.findElement(By.xpath(
//				"//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"))
//				;
		// Shilpa Update xpath for 11.2 on 12.04.2023
		WebElement element = driver.findElement(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"));
		element.click();
	}

	public void tableDoubleClickCell(String cellValue, int columnIndex) {
		Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]["
				+ columnIndex + "]/*[text()='" + cellValue + "']"));
		act.doubleClick(element).perform();
	}

	public static void tableDoubleClickCellFirstColumn(String cellValue) throws InterruptedException {
		Actions act = new Actions(driver);
		Thread.sleep(500);
		// Omkar 14/04/2023 : xpath changes for 11.2
		// WebElement element = driver.findElement(By.xpath(
		// "//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" +
		// cellValue + "']"));
		WebElement element = driver.findElement(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"));
		act.doubleClick(element).pause(500).perform();

		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

	public String tableGetCellValueFromFirstRow(String columnName, int columnId, boolean printout) {
		String cellValue = null;
		if (columnName.toLowerCase().equals("number") || columnName.toLowerCase().equals("row")) {
			cellValue = driver
					.findElement(By.xpath("//tbody/tr[2]/td[contains(@class,'rownumberer-" + columnId + "')]/div"))
					.getText();
		} else {
			cellValue = driver
					.findElement(By.xpath("//tbody/tr[2]/td[contains(@class,'gridcolumn-" + columnId + "')]/div"))
					.getText();
		}
		if (printout) {
			System.out.println("Actual Cell Value: " + cellValue);
		}
		return cellValue;
	}

	public void assertTableCellValueFirstRow(String expectedValue, String tableHeading, boolean printout)
			throws InterruptedException {
		int headerIdDigits = tableGetTableColumnIdDigitsWithHeaderText(tableHeading, printout);
		String cellValue = tableGetCellValueFromFirstRow(tableHeading, headerIdDigits, printout);
		assertEquals(expectedValue, cellValue);
	}

	public static void keyInDates(String date, WebElement dateField) throws InterruptedException {
		for (int i = 0; i <= 9; i++) {
			dateField.click();
			dateField.sendKeys(Keys.BACK_SPACE);
		}
		dateField.sendKeys(date);
	}

	public static String getFontColor(WebElement element) throws InterruptedException {
		String color = element.getCssValue("color");
		System.out.println(color);
		return color;

	}

	public static void keyboardNavig(int times) throws InterruptedException {
		Actions action = new Actions(driver);
		for (int i = 0; i <= times; i++) {
			action.sendKeys(Keys.TAB).perform();
			driverDelay(200);
		}
		action.sendKeys(Keys.ENTER).perform();
	}

	

	public void assertThatFieldValueContainsString(WebElement element, String expectedValue, String attribute) {
		String defaultValue = element.getAttribute(attribute);
		assertThat(defaultValue, containsString(expectedValue));
	}

	public static void checkDropdownValuesAscending(List<WebElement> elements) {
		List<String> originalList = new ArrayList<>();
		for (WebElement item : elements) {
			originalList.add(item.getText().trim());
		}
		List<String> sortedList = new ArrayList<>(originalList);
		Collections.sort(sortedList);
		if (originalList.equals(sortedList)) {
			System.out.println("Original List: \n" + originalList);
			System.out.println("sorted List: \n" + sortedList);
			assertTrue(printout);
		} else {
			fail();
		}
	}

	public static void compareList(List<WebElement> elements, List<String> expectedList) {
		List<String> actualList = new ArrayList<>();
		for (WebElement item : elements) {
			actualList.add(item.getText().trim());
			System.out.println(item.getText());
		}
		Collections.sort(actualList);
		Collections.sort(expectedList);

		if (actualList.equals(expectedList)) {
			assertTrue(printout);
		} else {
			fail();
		}
	}

	public static void selectDropdownOption(WebElement dropdown, List<WebElement> options,
			List<WebElement> tableValues) {

		for (int i = 1; i < options.size(); i++) {
			dropdown.click();
			options.get(i).click();
			for (int j = 0; j <= tableValues.size(); j++) {
				if (j == 0) {
					String tableValue = tableValues.get(j).getText();
					String[] parts = tableValue.split(":");

					if (tableValues.get(j).getText().contains(parts[0])) {
						System.out.println(parts[0]);
						assertTrue(printout);
					} else {
						System.out.println("No Values in the table");
					}
				}

			}

		}

	}

	public void searchStringInList(List<WebElement> elements, String search) {
		for (int i = 0; i < elements.size(); i++) {

			if (elements.get(i).getText().contains(search)) {
				System.out.println(elements.get(i).getText());
				assertTrue(printout);
			} else {
				fail();
			}
			if (i == 2) {
				break;
			}

		}
	}

	public static void searchCalculationType(WebElement element, String searchString) {
		element.click();

	}

	public void selectAvailableItems(List<WebElement> elements, int count) {
		Actions action = new Actions(driver);
		if (count > elements.size()) {
			count = elements.size();
		}
		action.keyDown(Keys.CONTROL);
		for (int i = 0; i < count; i++) {

			action.moveToElement(elements.get(i)).click().perform();
//			action.keyUp(Keys.CONTROL).build().perform();
		}

		action.keyUp(Keys.CONTROL); // Release CTRL
		action.build().perform();
	}

	public static List<String> addElementToList(List<WebElement> elements) {
		List<String> actualList = new ArrayList<>();
		for (WebElement element : elements) {
			actualList.add(element.getText());
		}
		return actualList;

	}

	public static void createNewScenario(String scenarioName, String CalcType) throws InterruptedException {
		doClick(cimMap.getcimNewBtn());
		waitForElementToBeVisible(cimMap.getcimName());
		cimMap.getcimName().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		cimMap.getcimName().sendKeys(Keys.BACK_SPACE);
		cimMap.getcimName().sendKeys(scenarioName);
		driverDelay();
		String[] input=CalcType.split(": ");
		cimMap.getcimScenarioSearchInput().sendKeys(input[1]);
		cimMap.getsearchIcon().click();
		driverDelay();
		doClick("(//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div[contains(text(),'"+CalcType+"')]");
		doClick(cimMap.getcalcTypeSelectBtn());
		doClick(cimMap.gethostLocation());
		doClick(cimMap.getcimSharedLoc());
		cimMap.getcimLocation().sendKeys("ADS-20031");
	}

	public static String differenceTime(String startTime,String endTime) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		LocalDateTime s = LocalDateTime.parse(startTime, f), e = LocalDateTime.parse(endTime, f);
		String diff = String.format("%02d:%02d:%02d", Duration.between(s, e).getSeconds()/3600, (Duration.between(s, e).getSeconds()%3600)/60, Duration.between(s, e).getSeconds()%60);
		return diff;
		
	}
	public static void validateSort(WebElement element, WebElement elementValdiate) {
		doClick(element);
		waitForDisplayedSpinnerToEnd();
		GoHelper helper = new GoHelper();
		GoHelper.assertElementIsDisplayed(elementValdiate);
	}

	public static void deleteSchedules(WebElement schedule) {
		try {
			if (cimMap.getcimScheduledPopUp().isDisplayed()) {
				doClick(cimMap.getcimDeleteScheduleBtn());
				if (cimMap.getcimDeleteSelectSchedule().isDisplayed()) {
					doClick(schedule);
					doClick(cimMap.getcimDeleteScheduledBtn());
					waitForDisplayedSpinnerToEnd();
				}
			}

		} catch (Exception e) {
			doClick(cimMap.getcimDeleteScheduleBtn());
			waitForElementToBeVisible(cimMap.getcimDeleteScheduledPopUp());
			doClick(cimMap.getcimDeleteScheduledBtn());
			waitForDisplayedSpinnerToEnd();
		}

	}

	public static void selectDate(String day, WebElement element) throws Exception {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		if (day == "current") {

			String formattedDate = date.format(formatter);
			System.out.println("Formatted Date: " + formattedDate);
			keyInDates(formattedDate, element);
		}
		if (day == "next") {
			LocalDate tomorrow = date.plusDays(1);
			String formattedDate = tomorrow.format(formatter);
			System.out.println("Formatted Date: " + formattedDate);
			keyInDates(formattedDate, element);
		}
		if (day == "previous") {
			LocalDate today = LocalDate.now();
	        LocalDate yesterday = today.minusDays(1);
			String formattedDate = yesterday.format(formatter);
			System.out.println("Formatted Date: " + formattedDate);
			keyInDates(formattedDate, element);
		}
	}

	public static String getCurrentDate(String day) throws Exception {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = null;
		if (day == "current") {

			formattedDate = date.format(formatter);
			System.out.println("Formatted Date: " + formattedDate);

		}
		if (day == "next") {
			LocalDate tomorrow = date.plusDays(1);
			formattedDate = tomorrow.format(formatter);
			System.out.println("Formatted Date: " + formattedDate);

		}
		return formattedDate;
	}

	public static void deleteCim() throws Throwable {
		doClick(cimMap.getcimDeleteButton());
		assertTextIsDisplayed(
				"Decision Support will delete the data you selected. Click Delete to remove the data from the database, or click Cancel to return to the previous screen without deleting the data.");
		doClick(cimMap.getcimWarningDeleteButton());
		driverDelay(1000);
		doClick(cimMap.getcimClearFilterButton());
		waitForDisplayedSpinnerToEnd();
	}

	public static void deleteMultipleCim() {
		List<WebElement> cimRows = cimMap.getcimGrid();
		if (cimRows.size() < 2) {
			System.out.println("Not enough rows to delete");
		} else {
			builder.moveToElement(cimRows.get(0)).click().perform();
			builder.keyDown(Keys.SHIFT).click(cimRows.get(cimRows.size() - 1)).keyUp(Keys.SHIFT).perform();
		}
		doClick(cimMap.getcimDeleteButton());
		doClick(cimMap.getcimWarningDeleteButton());
		assertTextIsDisplayed("There is no data available to display.");
		doClick(cimMap.getcimClearFilterButton());
		waitForDisplayedSpinnerToEnd();
	}

	public static void createCIM(String scenarioName, String calctype) throws Throwable {
		createNewScenario(scenarioName, calctype);
		doClick(cimMap.getcimSaveCloseBtn());
		waitForDisplayedSpinnerToEnd();
		doClick(cimMap.getcimFilterButton());

	}

	public List<String> createMultipleCIM(String scenarioName, int numberOfRecords, String calcType,List<String> groupList) throws Throwable {

		for (int i = 0; i <= numberOfRecords; i++) { // Add 6 new Cim groups
			cimGroupName = random.nextInt(1000) + scenarioName;
			groupList.add(cimGroupName);
			createNewScenario(cimGroupName, calcType);
			driverDelay();
			doClick(cimMap.getcimSaveCloseBtn());
		}
		doClick(cimMap.getcimFilterButton());
		for (int j = 0; j <= numberOfRecords; j++) {
			System.out.println(groupList.get(j));
			String[] doFilterCreateIsOneOf = { "Name", "Is", "One Of" };// Apply filter to 6 new Cim groups

			doFilterCreateIsOneOf(doFilterCreateIsOneOf);
			doClick(dialog.getFilterDialogFormFieldValueOneOf());
			dialog.getFilterDialogFormFieldValueOneOf().sendKeys(groupList.get(j));
			doClick(costing.getRvuContainerAddValueButton());
		}
		addFilter();
		doClick(dialog.getFilterDialogButtonApplyFilter());
		waitForSpinnerToEnd();
		return groupList;

	}

	public static void dragAndDrop(WebElement source,WebElement target) {
		Actions builder = new Actions(driver);
		 source.click();
        Action dragAndDrop = builder.clickAndHold(source)
        		.moveToElement(target)
        		.release(target).pause(500)
        		.build();

        		//Performing the drag and drop action
        		dragAndDrop.perform();

	}
	public static void checkElement(List<WebElement> elementList, WebElement validateElement) {
//		 List<WebElement> list = driver.findElements(By.xpath("(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])"));

		boolean spanVal = true;
		if (elementList.size() == 1) {
			elementList.get(0).click();
			validateElement.click();
		}
		if (elementList.size() > 1) {
			for (WebElement items : elementList) {
//					System.out.println(list.size());
				String span = items.getAttribute("id");
//					while(spanVal)
				try {
					doClick("//span[@id='" + span + "']");

					if (validateElement.isDisplayed()) {
						validateElement.click();
						break;
					}

				} catch (Exception e) {

				}
			}

		}

	}

	public static String checkElements(List<WebElement> elementList) throws Throwable {
//		 List<WebElement> list = driver.findElements(By.xpath("(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])"));
		String clickableElement = null;
//		 boolean spanVal=true;
		if (elementList.size() == 1) {
			String tagName = elementList.get(0).getTagName();
			String id = elementList.get(0).getAttribute("id");
			clickableElement = "//"+tagName+"[@id='" + id + "']";
			System.out.println(clickableElement);
			elementList.get(0).click();
			
		}
		if (elementList.size() > 1) {
			for (WebElement element : elementList) {
//					System.out.println(list.size());
				String tagName = element.getTagName();
				String id = element.getAttribute("id");
//					while(spanVal)
				try {
					clickableElement = "//"+tagName+"[@id='" + id + "']";
					doClick("//"+tagName+"[@id='" + id + "']");
				} catch (Exception e) {
//					clickableElement = "//"+tagName+"[@id='" + id + "']";
//					doClick("//input[@id='" + id + "']");
				}

			}

		}
		return clickableElement;
	}

	public static void waitForFirstRowCalculationBarToReach100Percent(WebElement element) throws Exception {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		while (calculate) {
			try {
				// Omkar 14/04/2023 : xpath changes for 11.2
				// driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				doClick(element);
				waitForSpinnerToEnd();
				percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("100%"));
				break;
			} catch (Throwable e) {
				System.out.println("percent less than 100");
				Thread.sleep(1000);
				if (counter == 120) {
					fail("Calculation did not finish in allotted time");
					break;
				}
				counter++;
			}
			System.out.println(counter);
		}
		Thread.sleep(500);
	}
public static boolean dateTimeCheck(String dateTimeStr) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	try {
        LocalDateTime.parse(dateTimeStr, formatter);
        return true; // valid format and valid values
    } catch (DateTimeParseException e) {
        return false;
    }
	
}
	public void setRecurrence(String recurrence, String scenario) throws Throwable {
		String date;
		String time;
		String startTime;
		String nextStartTime;
		String currentTime = getSystemTimeFormatted();
		checkElements(driver.findElements(
				By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Custom Date & Time']")));
		date = checkElements(driver.findElements(By.xpath("//input[@name='customDate']")));
		driver.findElement(By.xpath(date));
		selectDate("next", driver.findElement(By.xpath(date)));
		checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
		time = checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
		driver.findElement(By.xpath(time)).click();
		driver.findElement(By.xpath(time)).sendKeys(currentTime);
		// Select recurrence
		checkElements(cimMap.getrepeatCalcDropdown());
		List<WebElement> elements = driver.findElements(By.xpath("//li[text()='" + recurrence + "']"));
		if (elements.size() > 1) {
			WebElement lastElement = elements.get(elements.size() - 1);
			lastElement.click();
		}
		if (elements.size() == 1) {
			elements.get(0).click();
		}
//		doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[contains(@class,'x-boundlist-floating x-layer')])[3]//ul")), recurrence);
		checkElements(driver.findElements(
				By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Save & Close']")));
		driverDelay(300);
		if(recurrence.equals("Does not repeat")) {
			assertElementIsNotDisplayed("//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div//span[@class='icon']");

			String cimNextStartTime = driver.findElement(By.xpath("//div[contains(@id,'cimmasterlist')]//div[text()='"+scenario+"']//following::td[4]/div")).getText().split(" ")[0];
			String nextStrtTime = getSystemDate();
			assertTextEquals(cimNextStartTime,nextStrtTime);
			doClick("//div[text()='" + scenario + "']");
			doClick(cimMap.getcimCalculateBtn());
			waitForElementToBeVisible(cimMap.getschedulePopUp());
			if (cimMap.getrepeatsText().getText().contains("Does not repeat")) {
				assertTrue(printout);
			} else {
				fail();
			}
		}
		
		else {
		nextStartTime = driver
				.findElement(By.xpath(
						"//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div"))
				.getText();
		assertElementIsDisplayedWithXpath("//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div//span[@class='icon']");
		startTime = extractTime(nextStartTime);
		doClick("//div[text()='" + scenario + "']");
		doClick(cimMap.getcimCalculateBtn());
		waitForElementToBeVisible(cimMap.getschedulePopUp());
		System.out.println(cimMap.getrepeatsText().getText());
		
		if (recurrence == "Daily") {
			if (cimMap.getrepeatsText().getText().contains(recurrence)
					&& cimMap.getrepeatsText().getText().contains(startTime)) {
				assertTrue(printout);
			} else {
				fail();
			}
		}
		if (recurrence == "Quarterly") {
			if (cimMap.getrepeatsText().getText().contains("Every 3 months (quarterly)")
					&& cimMap.getrepeatsText().getText().contains(startTime)) {
				assertTrue(printout);
			} else {
				fail();
			}
		}
		if (recurrence == "Monthly") {
			startTime = extractTime(nextStartTime);
			System.out.println(cimMap.getrepeatsText().getText());
			if (cimMap.getrepeatsText().getText().contains("Every month")
					&& cimMap.getrepeatsText().getText().contains(startTime)) {
				assertTrue(printout);
			} else {
				fail();
			}
		}
		if (recurrence == "Weekly") {
			String day = currentDay();
			if (cimMap.getrepeatsText().getText().contains("Every " + day + "")
					&& cimMap.getrepeatsText().getText().contains(startTime)) {
				assertTrue(printout);
			} else {
				fail();
			}
		}
		if (recurrence == "Annually") {
			if (cimMap.getrepeatsText().getText().contains(recurrence)
					&& cimMap.getrepeatsText().getText().contains(startTime)) {
				assertTrue(printout);
			} else {
				fail();
			}
		}
		}
	}
public void setNoRecurrence(String recurrence,String scenario,String interval,String repeat) throws Throwable {
	String date;
	String time;
	String startTime;
	String nextStartTime;
	String currentTime = getSystemTimeFormatted();
	checkElements(driver.findElements(
			By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Custom Date & Time']")));
	date = checkElements(driver.findElements(By.xpath("//input[@name='customDate']")));
	driver.findElement(By.xpath(date));
	selectDate("next", driver.findElement(By.xpath(date)));
	checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
	time = checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
	driver.findElement(By.xpath(time)).click();
	driver.findElement(By.xpath(time)).sendKeys(currentTime);
	// Select recurrence
	checkElements(cimMap.getrepeatCalcDropdown());
	List<WebElement> elementsRecurrence = driver.findElements(By.xpath("//li[text()='" + recurrence + "']"));
	if (elementsRecurrence.size() > 1) {
		WebElement lastElement = elementsRecurrence.get(elementsRecurrence.size() - 1);
		lastElement.click();
	}
	if (elementsRecurrence.size() == 1) {
		elementsRecurrence.get(0).click();
	}
	checkElements(cimMap.getRepeatEveryInput());
	List<WebElement> elementsRepeat = cimMap.getRepeatEveryInput();
	if (elementsRepeat.size() > 1) {
		WebElement lastElement = elementsRepeat.get(elementsRepeat.size() - 1);
		lastElement.click();
		lastElement.clear();
		lastElement.sendKeys(interval);
	}
	if (elementsRepeat.size() == 1) {
		elementsRepeat.get(0).click();
		elementsRepeat.get(0).clear();
		elementsRepeat.get(0).sendKeys(interval);
	}
	checkElements(cimMap.getrepeatInterval());
	List<WebElement> elements = driver.findElements(By.xpath("//li[text()='" + repeat + "']"));
	if (elements.size() > 1) {
		WebElement lastElement = elements.get(elements.size() - 1);
		lastElement.click();
	}
	if (elements.size() == 1) {
		elements.get(0).click();
	}
	date = checkElements(cimMap.getrepeatEnds());
	driver.findElement(By.xpath(date));
	selectDate("next", driver.findElement(By.xpath(date)));
	checkElements(driver.findElements(
			By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Save & Close']")));
	driverDelay(200);
	nextStartTime = driver
			.findElement(By.xpath(
					"//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div"))
			.getText();
	assertElementIsNotDisplayed("//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div//span[@class='icon']");
	startTime = extractTime(nextStartTime);
	doClick("//div[text()='" + scenario + "']");
	doClick(cimMap.getcimCalculateBtn());
	waitForElementToBeVisible(cimMap.getschedulePopUp());
	System.out.println(cimMap.getrepeatsText().getText());
	if(repeat.equals("Days")) {
		
		if (cimMap.getrepeatsText().getText().contains("Every "+interval+" days")&& cimMap.getrepeatsText().getText().contains(startTime)) {
			assertTrue(printout);
		} else {
			fail();
		}
	}
}
public  void setRecurrenceForCustom(String recurrence,String scenario,String interval,String repeat) throws Throwable {
	String date;
	String time;
	String startTime;
	String nextStartTime;
	String currentTime = getSystemTimeFormatted();
	checkElements(driver.findElements(
			By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Custom Date & Time']")));
	date = checkElements(driver.findElements(By.xpath("//input[@name='customDate']")));
	driver.findElement(By.xpath(date));
	selectDate("next", driver.findElement(By.xpath(date)));
	checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
	time = checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
	driver.findElement(By.xpath(time)).click();
	driver.findElement(By.xpath(time)).sendKeys(currentTime);
	// Select recurrence
	checkElements(cimMap.getrepeatCalcDropdown());
	List<WebElement> elementsRecurrence = driver.findElements(By.xpath("//li[text()='" + recurrence + "']"));
	if (elementsRecurrence.size() > 1) {
		WebElement lastElement = elementsRecurrence.get(elementsRecurrence.size() - 1);
		lastElement.click();
	}
	if (elementsRecurrence.size() == 1) {
		elementsRecurrence.get(0).click();
	}
	checkElements(cimMap.getRepeatEveryInput());
	List<WebElement> elementsRepeat = cimMap.getRepeatEveryInput();
	if (elementsRepeat.size() > 1) {
		WebElement lastElement = elementsRepeat.get(elementsRepeat.size() - 1);
		lastElement.click();
		lastElement.clear();
		lastElement.sendKeys(interval);
	}
	if (elementsRepeat.size() == 1) {
		elementsRepeat.get(0).click();
		elementsRepeat.get(0).clear();
		elementsRepeat.get(0).sendKeys(interval);
	}
	checkElements(cimMap.getrepeatInterval());
	List<WebElement> elements = driver.findElements(By.xpath("//li[text()='" + repeat + "']"));
	if (elements.size() > 1) {
		WebElement lastElement = elements.get(elements.size() - 1);
		lastElement.click();
	}
	if (elements.size() == 1) {
		elements.get(0).click();
	}
	
	checkElements(driver.findElements(
			By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Save & Close']")));
	driverDelay(200);
	nextStartTime = driver
			.findElement(By.xpath(
					"//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div"))
			.getText();
	assertElementIsDisplayedWithXpath("//div[contains(@id,'cimmasterlist')]//div[text()='" + scenario + "']//following::td[4]/div//span[@class='icon']");
	startTime = extractTime(nextStartTime);
	doClick("//div[text()='" + scenario + "']");
	doClick(cimMap.getcimCalculateBtn());
	waitForElementToBeVisible(cimMap.getschedulePopUp());
	System.out.println(cimMap.getrepeatsText().getText());
	if(repeat.equals("Days")) {
		
		if (cimMap.getrepeatsText().getText().contains("Every "+interval+" days")&& cimMap.getrepeatsText().getText().contains(startTime)) {
			assertTrue(printout);
		} else {
			fail();
		}
	}
if(repeat.equals("Weeks")) {
		
		if (cimMap.getrepeatsText().getText().contains("Every "+interval+" weeks")&& cimMap.getrepeatsText().getText().contains(startTime)) {
			assertTrue(printout);
		} else {
			fail();
		}
	}
if(repeat.equals("Months")) {
	
	if (cimMap.getrepeatsText().getText().contains("Every "+interval+" months")&& cimMap.getrepeatsText().getText().contains(startTime)) {
		assertTrue(printout);
	} else {
		fail();
	}
}
	
}
	public static String currentDay() {
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		;
		String dayOfWeek = tomorrow.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		System.out.println("Tommorrow is: " + dayOfWeek);
		return dayOfWeek;

	}

	public void validateCalcStatus(String status, String scenario) throws Throwable {
		String calcstatus = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
		String date;
		String time;
		if (status == "PENDING") {
			doClick(cimMap.getcimCalculateBtn());
			driverDelay();
			checkElements(driver.findElements(By.xpath(
					"//div[text()='Calculate " + scenario + "']//following::span[text()='Custom Date & Time']")));
//			checkElements(driver.findElements(By.xpath("//input[@name='customDate']")));
			date = checkElements(driver.findElements(By.xpath("//input[@name='customDate']")));
			driver.findElement(By.xpath(date));
			selectDate("next", driver.findElement(By.xpath(date)));
			checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
			time = checkElements(driver.findElements(By.xpath("//input[@name='customTime']")));
			driver.findElement(By.xpath(time)).click();
			driver.findElement(By.xpath(time)).sendKeys("12:30 am");
			checkElements(driver.findElements(
					By.xpath("//div[text()='Calculate " + scenario + "']//following::span[text()='Save & Close']")));
			assertElementIsDisplayed("//div[text()='" + scenario
					+ "']//following::a[contains(@id,'cimStatusLink')][text()='" + status + "']");
			doClick("//div[text()='" + scenario + "']//following::a[contains(@id,'cimStatusLink')][text()='" + status
					+ "']");
			assertElementIsDisplayed("(//div[text()='" + scenario
					+ "']//following::div[contains(@class,'x-grid-cell-inner')][text()='" + calcstatus + "'])[1]");
			doClosePageOnLowerBar("Calculation Status");
			doClick(cimMap.getcimRefreshBtn());
		}
		if (status == "RUNNING") {
			doClick(cimMap.getcimCalculateBtn());
			checkElements(cimMap.getcimCalculateNowBtn());
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			assertElementIsDisplayed("(//div[text()='" + scenario
					+ "']//following::div[contains(@class,'x-grid-cell-inner')][text()='" + calcstatus + "'])[1]");
			doClosePageOnLowerBar("Calculation Status");
			assertElementIsDisplayed("//div[text()='" + scenario
					+ "']//following::a[contains(@id,'cimStatusLink')][text()='" + status + "']");

		}
		if (status == "COMPLETED") {
			doClick(cimMap.getcimCalculateBtn());
			checkElements(cimMap.getcimCalculateNowBtn());
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			waitForFirstRowCalculationBarToReach100Percent(cimMap.getcalcRefreshBtn());
			assertElementIsDisplayed("(//div[text()='" + scenario
					+ "']//following::div[contains(@class,'x-grid-cell-inner')][text()='" + calcstatus + "'])[1]");
			doClosePageOnLowerBar("Calculation Status");
			doClick(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed("//div[text()='" + scenario
					+ "']//following::a[contains(@id,'cimStatusLink')][text()='" + status + "']");

		}
		if (status == "CANCELLED") {
//			doClick(cimMap.getcimCalculateBtn());
//			checkElements(cimMap.getcimCalculateNowBtn());
			//Calculate from CIM group in Edit mode
			doClick(cimMap.getcimEditBtn());
			checkElements(cimMap.getcimCalculateBtnEditMode());
			checkElements(cimMap.getcimCalculateNowBtn());
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			driverDelay(300);
			doClick("(//div[text()='" + scenario + "']//following::span[text()='Cancel'])[2]");
			driverDelay(2000);
			doClick(cimMap.getcancelWarning());
			doClosePageOnLowerBar("Calculation Status");
			doClick(cimMap.getcimSaveCloseBtn());
			doClick(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed("//div[text()='" + scenario
					+ "']//following::a[contains(@id,'cimStatusLink')][text()='" + status + "']");
			doClick("//div[text()='" + scenario + "']//following::a[contains(@id,'cimStatusLink')][text()='" + status
					+ "']");
			assertElementIsDisplayed("(//div[text()='" + scenario
					+ "']//following::div[contains(@class,'x-grid-cell-inner')][text()='" + calcstatus + "'])[1]");

		}
		if (status == "FAILED") {
			doClick(cimMap.getcimEditBtn());
			doClick(cimMap.getcalcTypeRemoveAllBtn());
			driverDelay(1000);
			cimMap.getcimScenarioSearchInput().sendKeys("*Practitioner Costing Enc");
			cimMap.getsearchIcon().click();
			driverDelay(300);
			doClick("((//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div)[1]");
			doClick(cimMap.getcalcTypeSelectBtn());
			doClick(cimMap.getcimSaveCloseBtn());
			doClick(cimMap.getcimCalculateBtn());
			checkElements(cimMap.getcimCalculateNowBtn());
			doClick(cimMap.getcimCalculateNowConfirmBtn());
			driverDelay(300);
			boolean calculate = true;

			while (calculate) {
				try {
					doClick(cimMap.getcalcRefreshBtn());
					if ("Failed".equalsIgnoreCase(getElementText(
							driver.findElement(By.xpath("(//div[text()='Calculation Status']//following::div[text()='"
									+ scenario + "']//following::div)[10]")),
							printout))) {
						assertTrue(printout);
						break;
					}
				} catch (Exception e) {
					break;
				}
			}
			doClosePageOnLowerBar("Calculation Status");
			doClick(cimMap.getcimRefreshBtn());
			assertElementIsDisplayed("//div[text()='" + scenario
					+ "']//following::a[contains(@id,'cimStatusLink')][text()='" + status + "']");

		}
	}

	public static String getSystemTimeFormatted() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 10);
		Date updatedTime = calendar.getTime();
		String formattedTime = sdf.format(updatedTime).toLowerCase();
		System.out.println(formattedTime);
		return formattedTime;

	}

	public static String getSystemTimeToday(String input) {
		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		LocalDateTime dateTime = LocalDateTime.parse(input, inputFormat);
		String formatted = dateTime.format(outputFormat);
		return formatted;
	}

	public static String getSystemTimeTomorrow(String input) {
		DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		LocalDateTime dateTime = LocalDateTime.parse(input, inputFormat);
		LocalDateTime tomorrowDateTime = dateTime;
		String formatted = tomorrowDateTime.format(outputFormat);
		return formatted;
	}

	public static String extractTime(String input) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");

		Date date = inputFormat.parse(input);
		return outputFormat.format(date);
	}

	public static String getParsedDateTimeForPresetToday(String input) throws Throwable {
		String timePart = input.split(",")[1].trim();
		SimpleDateFormat inputTimeFormat = new SimpleDateFormat("h:mm a");
		Date time = inputTimeFormat.parse(timePart);

		// Get today’s date
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		// Format time with leading zero hour
		SimpleDateFormat outputTimeFormat = new SimpleDateFormat("hh:mm a");

		String formattedDateTime = dateFormat.format(today) + " " + outputTimeFormat.format(time);
		return formattedDateTime;

	}

	public static String getParsedDateTimeForPresetTommorow(String input) throws Throwable {
		String timePart = input.split(",")[1].trim();

		// 2. Parse time
		SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
		Date time = timeFormat.parse(timePart);

		// 3. Get tomorrow's date
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1); // move to tomorrow

		// 4. Set time from parsed value
		Calendar timeCal = Calendar.getInstance();
		timeCal.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));

		// 5. Format as MM/dd/yyyy hh:mm a
		SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		String result = outputFormat.format(calendar.getTime());
		return result;

	}

	public static String getSystemDate() {
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = tomorrow.format(formatter);
		return formattedDate;

	}

	// Check preset options for CIM
	public static void getPresetOption() {
		LocalTime now = LocalTime.now();
		LocalTime start = LocalTime.MIDNIGHT;
		LocalTime range1 = LocalTime.of(7, 59);
		LocalTime range2 = LocalTime.of(15, 59);
		LocalTime range3 = LocalTime.of(23, 59);
		LocalDate today = LocalDate.now();
		String dayToday = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		;
		String nextDay = today.plusDays(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		;
		if (!now.isBefore(start) && !now.isAfter(range1)) {
			System.out.println("Today: " + dayToday + ", 8:00 AM");
			System.out.println("Today: " + dayToday + ", 4:00 PM");
			assertTextIsDisplayed("Today: " + dayToday + ", 8:00 AM");
			assertTextIsDisplayed("Today: " + dayToday + ", 4:00 PM");
		} else if (!now.isBefore(start) && !now.isAfter(range2)) {
			System.out.println("Today: " + dayToday + ", 4:00 PM");
			System.out.println("Tomorrow Morning: " + nextDay + ", 8:00 AM");
			assertTextIsDisplayed("Today: " + dayToday + ", 4:00 PM");
			assertTextIsDisplayed("Tomorrow Morning: " + nextDay + ", 8:00 AM");
		} else if (!now.isBefore(start) && !now.isAfter(range3)) {
			System.out.println("Tommorrow: " + nextDay + ", 8:00 AM");
			System.out.println("Tommorrow: " + nextDay + ", 4:00 PM");
			assertTextIsDisplayed("Tommorrow: " + nextDay + ", 8:00 AM");
			assertTextIsDisplayed("Tommorrow: " + nextDay + ", 4:00 PM");
		}
	}

	public static void assertTextEquals(String expected, String actual) {
		if (expected.equals(actual)) {
			assertTrue(printout);
		} else {
			fail();
		}
	}
}
