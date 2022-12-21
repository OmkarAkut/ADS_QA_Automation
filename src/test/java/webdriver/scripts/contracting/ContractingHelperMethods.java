package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.SelectColumnsPopupForColumnsToDisplayAds1083;

public class ContractingHelperMethods extends SelectColumnsPopupForColumnsToDisplayAds1083 {
	 static ContractingMap selectColumn =BuildMap.getInstance(driver, ContractingMap.class);
	 private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);

	public void highlightColumnsToDisplayColumn(String column) throws InterruptedException,Throwable {
	    String columnPath = "//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='"+column+"']";
	   //Shilpa 02.09.2022 added dimension , scroll to element 
	    addDimension(1000,1000);
	    WebElement element = driver.findElement(By.xpath(columnPath));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    Thread.sleep(1000); 
		doClick(columnPath);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	    waitForAjaxExtJs();
	  }


	  public void selectMultipleColumnsToDisplay(String[] columnsToSelect) throws InterruptedException,Throwable{
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

	  public List<String> getSelectedColumnList() {
	    String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
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

	  public void assertColumnsToDisplayColumnIsSelected(String column) throws Exception {
	    String columnIsSelected = null;
	    try {
	      waitForAjaxExtJs();
	      columnIsSelected = driver.findElement(By.xpath("//*[contains(@class,'x-grid-table')]/descendant::*[text()='"+column+"']/../..")).getAttribute("class");
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
	  public void doFilterCreate(String[] filterParameters) throws InterruptedException {
		    doFilterSetFilterParameters(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
		    waitForAjaxExtJs();
		    waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
		    doClick(dialog.getFilterDialogButtonAdd());
		    waitForAjaxExtJs();
		   
		  }
}
