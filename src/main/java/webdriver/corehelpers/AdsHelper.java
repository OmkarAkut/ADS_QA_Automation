package webdriver.corehelpers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class AdsHelper extends GetHelper {
	private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	public final static String setStandardDateFormat = "yyyy-MM-dd:HH:mm:ss";

	public static String getPageHeaderPath(String headerText) {
//		String xpath = "//*[contains(@class, 'areaTitle') and text() = '"+headerText+"']";
		String xpath = "//*[contains(@id, 'container') and text() = '"+headerText+"']";
		return xpath;
	}


	public void setDataGridCellValue(String chargeCode, String headerName, String newValue, boolean printout) throws InterruptedException {
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
		columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][contains(text(),'" + headerName + "')]")).getAttribute("id");
		int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
		if (printout) {
			System.out.println("For cell update, columnIdDigits: " + columnIdDigits);
		}
		//click in cell and update
//		driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]")).click();
		driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')])["+row+"]")).click();

		waitForAjaxExtJs();
//		WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div/table"));
		//Shilpa update xpath for 11.2 on 12.07.2023
		WebElement editCell = driver.findElement(By.xpath("(//tr[contains(@class,'x-grid-row')]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')])["+row+"]"));
		Actions action = new Actions(driver);
		//   action.moveToElement(editCell).sendKeys(newValue).perform();

		action.moveToElement(editCell).clickAndHold().sendKeys(Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(1000);
		action.moveToElement(editCell).clickAndHold().sendKeys(newValue).sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
	}

	public void setFilterValues(WebElement fieldOptionsUl, String field, String operator, String condition, String value) throws InterruptedException {
		waitForAjaxExtJs();
		setDropdownValue(dialog.getFilterDialogDropdownField(), fieldOptionsUl, field, printout);
		setDropdownValue(dialog.getFilterDialogDropdownOperator(), operator, printout);
		setDropdownValue(dialog.getFilterDialogDropdownCondition(), condition, printout);
		webdriverClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(value);
	}

	public void setDropdownValue(WebElement triggerDropdown, String value, boolean printout) throws InterruptedException {
		setDropdownValue(triggerDropdown, null, value, printout);
	}

	public void setDropdownValue(WebElement triggerDropdown, WebElement menuOptionsUl, String value, boolean printout) throws InterruptedException {
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
			optionsUl = driver.findElement(By.xpath("//div[contains(@class,'floating')]//div[contains(@id,'listWrap')]/ul"));
		}
		if (printout) {
			System.out.println("menuOptionsUl: " + menuOptionsUl);
		}
		List<WebElement> options = optionsUl.findElements(By.tagName("li"));
		for(WebElement option : options) {
			if(option.getText().equals(value)) {
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
		driver.findElement(By.xpath("//div[not(contains(@class, 'cancelCloseBtn'))]/em/button/span[text()='Cancel & Close']")).click();
	}

	public String filterGetFilterMatchesTheseCriteriaText() {
		String getFilterMatchesText = driver.findElement(
				By.xpath("//label[contains(text(), 'Filter to Match These Criteria')]"))
				.getText()
				;
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
				System.out.println("Set Filter To: " + filterParameters[0] + filterParameters[1] + filterParameters[2] + filterParameters[3]);
			}
			setFilterValues(fieldOptionsUl, filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
			waitForAjaxExtJs();
			waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
			doClick(dialog.getFilterDialogButtonAdd());
			waitForAjaxExtJs();
		}
		doClick(dialog.getFilterDialogButtonApplyFilter());
		waitForSpinnerToEnd();
	}


	public void doFilterCreate(String[] filterParameters) throws InterruptedException {
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
	public void doFilterCreateForDate(String[] filterParameters) throws InterruptedException {
		System.out.println(filterParameters[0]);
		doFilterSetFilterParametersForDate(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
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
	public void doDropdownSelectUsingOptionTextWithelement(WebElement dropdownList,String optionText) throws InterruptedException {
		waitForAjaxExtJs();


		//      WebElement list = driver.findElement(By.xpath("(//div[contains(@id,'specialtagcombo')]//following::div[contains(@class,'floating')]//ul)["+i+"]"));
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
	public void doDropdownSelectUsingOptionTextServices(WebElement dropdownList,WebElement element, String optionText) throws InterruptedException {
		waitForAjaxExtJs();
		doClick(element);
		waitForAjaxExtJs();
		driverDelay(300);

		//      WebElement list = driver.findElement(By.xpath("(//div[contains(@id,'specialtagcombo')]//following::div[contains(@class,'floating')]//ul)["+i+"]"));
		List<WebElement> menu = dropdownList.findElements(By.tagName("li"));
		System.out.println(optionText);
		for(WebElement option : menu) {
			System.out.println("Value"+option.getText());
			if(option.getText().equals(optionText)) {
//				Actions act=new Actions(driver);
//				act.moveToElement(option).click().perform();
//				option.click();
				doJsClick(option);
				break;
			}
		}

	}
	public void doFilterSetFilterParameters(String field, String operator, String condition, String value) throws InterruptedException {
		waitForAjaxExtJs();
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), field);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), operator);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), condition);
		doClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(value);
	}
	public void doFilterSetFilterParametersForDate(String field, String operator, String condition, String value) throws InterruptedException {
		waitForAjaxExtJs();
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), field);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), operator);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), condition);
		doClick(dialog.getstatusFilterDialogFieldValueDate());
		dialog.getstatusFilterDialogFieldValueDate().sendKeys(value);
	}
	public void doFilterSetFilterParameterswithElement(String field, String operator, String condition, String value) throws InterruptedException {
		waitForAjaxExtJs();
		doDropdownSelectUsingOptionTextServices(dialog.getfieldNAme(), dialog.getFilterDialogDropdownField(),field);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),dialog.getFilterDialogDropdownOperator(), operator);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),dialog.getFilterDialogDropdownCondition(), condition);
		doClick(dialog.getFilterDialogFormFieldValue());
		dialog.getFilterDialogFormFieldValue().sendKeys(value);
	}

	public void doSelectCalcStatusFilterCriteria(String criteriaOption, String optionText) throws InterruptedException {
		if(criteriaOption.equals("Field")){
			doClick(driver.findElement(By.xpath("//input[@name='field']")));
			waitForAjaxExtJs();
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][1]/descendant::ul/li[text()=\"" + optionText + "\"]")));
		}
		if(criteriaOption.equals("Operator")){
			doClick(driver.findElement(By.xpath("//input[@name='operator']")));
			waitForAjaxExtJs();
			doClick(driver.findElement(By.xpath("//div[@class='x-mask']/preceding::div[@class=\"x-boundlist x-boundlist-floating x-layer x-boundlist-default\"]/child::div/ul/li[text()='" + optionText + "']")));
		}
		if(criteriaOption.equals("Condition")){
			doSelectCalcStatusFilterConditionValue(optionText);
		}
		if(criteriaOption.equals("Value")){
			doClick(driver.findElement(By.xpath("//input[@name='valuefield']")));
			driver.findElement(By.xpath("//input[@name='valuefield']")).sendKeys(optionText);
		}
	}

	public void doSelectCalcStatusFilterConditionValue(String conditionValue) {
		doClick(driver.findElement(By.xpath("//input[@name='condition']")));
		WebElement conditions = driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul"));
		List<WebElement> existingConditions = conditions.findElements(By.tagName("li"));
		List<String> actualConditions = new ArrayList<>();
		for(WebElement condition: existingConditions){
			//            System.out.println("Actual condition listed: " + condition.getText());
			actualConditions.add(condition.getText());
		}

		if(conditionValue.equals("Equal To")){
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(0) + "']")));
		}
		if(conditionValue.equals("Contains")){
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(1) + "']")));
		}
		if(conditionValue.equals("Starts With")){
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(2) + "']")));
		}
		if(conditionValue.equals("Ends With")){
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(3) + "']")));
		}
		if(conditionValue.equals("One Of")){
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(4) + "']")));
		}
	}

	@SuppressWarnings("unused")
	private WebElement tableGetTableContainerElementWithTableHeading(String tableHeading) {
		return driver.findElement(By.xpath(
				"//div[text()='"+tableHeading+"']/ancestor::div[contains(@class,'x-panel commonTBar')]"
						+ "/following-sibling::div/descendant::table/tbody"));
	}

	public static void tableClickColumnHeader(String headerText) {
		waitForPresenceOfElement("//span[contains(@id,'gridcolumn') and @class='x-column-header-text' and text()='" + headerText + "']");
		driver.findElement(By.xpath(
				"//span[contains(@id,'gridcolumn') and @class='x-column-header-text' and text()='" + headerText + "']"))
		.click()
		;
		waitForSpinnerToEnd();
	}

	public int getTableNumberOfNumberedRows(boolean printout) throws InterruptedException {
		waitForAjaxExtJs();
		WebElement table;
		List<WebElement> tablesPresent = driver.findElements(By.xpath(
				"//div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody"
				));
		if (tablesPresent.size() > 1) {
			table = driver.findElement(By.xpath(
					"//div[contains(@id, 'adynamic')]/div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody"
					));

			//div[contains(@id, 'adynamic')]/div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody
		} else {
			table = driver.findElement(By.xpath(
					"//div[contains(@id, 'gridview')]/table[contains(@class, 'x-grid-table')]/tbody"
					));
		}

		List<WebElement> rows = table.findElements(By.xpath(
				"//tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'rownumberer')]"
				));
		if(printout) {
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

	public int tableGetTableColumnIdDigitsWithHeaderText(String columnHeaderText, boolean printout) throws InterruptedException {
		String columnId;
		columnId = driver.findElement(By.xpath("//*[contains(@class,'x-column-header-') and [text()='"+columnHeaderText+"']")).getAttribute("id");
		int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnId));
		if (printout) {
			System.out.println("Column ID Digits: " + columnIdDigits);
		}
		return columnIdDigits;
	}

	public void tableClickCell(String cellValue, int columnIndex) {
		WebElement element = driver.findElement(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')][" + columnIndex + "]/*[text()='" + cellValue + "']"))
				;
		element.click();
	}

	public void tableClickCellFirstColumn(String cellValue) {
//		WebElement element = driver.findElement(By.xpath(
//				"//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"))
//				;
		//Shilpa Update xpath for 11.2 on 12.04.2023
		WebElement element = driver.findElement(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"))
				;
		element.click();
	}

	public void tableDoubleClickCell(String cellValue, int columnIndex) {
		Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')][" + columnIndex + "]/*[text()='" + cellValue + "']"))
				;
		act.doubleClick(element).perform();
	}

	public static void tableDoubleClickCellFirstColumn(String cellValue) throws InterruptedException {
		Actions act = new Actions(driver);
		Thread.sleep(500);
		//    Omkar 14/04/2023 : xpath changes for 11.2
		//    WebElement element = driver.findElement(By.xpath(
		//            "//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"));
		WebElement element = driver.findElement(By.xpath(
				"//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"));
		act.doubleClick(element).pause(500).perform();

		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

	public String tableGetCellValueFromFirstRow(String columnName, int columnId, boolean printout) {
		String cellValue = null;
		if (columnName.toLowerCase().equals("number") || columnName.toLowerCase().equals("row")) {
			cellValue = driver.findElement(By.xpath("//tbody/tr[2]/td[contains(@class,'rownumberer-" + columnId + "')]/div")).getText();
		} else {
			cellValue = driver.findElement(By.xpath("//tbody/tr[2]/td[contains(@class,'gridcolumn-" + columnId + "')]/div")).getText();
		}
		if (printout) {
			System.out.println("Actual Cell Value: " + cellValue);
		}
		return cellValue;
	}

	public void assertTableCellValueFirstRow(String expectedValue, String tableHeading, boolean printout) throws InterruptedException {
		int headerIdDigits = tableGetTableColumnIdDigitsWithHeaderText(tableHeading, printout);
		String cellValue = tableGetCellValueFromFirstRow(tableHeading, headerIdDigits, printout);
		assertEquals(expectedValue, cellValue);
	}
	public static void keyInDates(String date,WebElement dateField) throws InterruptedException {
		for(int i=0;i<=9;i++) {
			dateField.click();
			dateField.sendKeys(Keys.BACK_SPACE);
		}
		dateField.sendKeys(date);
	}
}

