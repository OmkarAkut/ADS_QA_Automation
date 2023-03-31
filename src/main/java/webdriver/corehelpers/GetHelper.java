package webdriver.corehelpers;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.utilities.JavaDataBaseConnectivity;

public class GetHelper extends DoHelper {

    private static JavaDataBaseConnectivity jdbc = new JavaDataBaseConnectivity(setupDb(testEnvironment));
    
    public void getStringDataFromDatabaseAndAssertExpectedValues(
            String sqlQuery, int datasetColumn, List<String> expectedValues)
            throws ClassNotFoundException {
        jdbc.setSqlQuery(sqlQuery);
        System.out.println("Sql Query: " +  jdbc.getSqlQuery());
        ArrayList<String> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsString(jdbc.getSqlQuery(), datasetColumn);
        int index = 0;
        for (String dbValue : dataFromDb) {
            System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
            assertTrue(dbValue.equals(expectedValues.get(index)));
            index++;
        }
    }

    public WebElement getWebElementNumberOfPagesDisplayedTotal(int expectedNumberOfPagesDisplayedTotal){
        WebElement element = driver.findElement(By.xpath("//div[contains(@class,'x-toolbar-text')][text()='/ "+expectedNumberOfPagesDisplayedTotal+"']"));
        return element;
    }

    public WebElement getWebElementNoDataToDisplayMessage(){
        WebElement element = driver.findElement(By.xpath("//div[@class='x-grid-empty'][text()='There is no data available to display']"));
        return element;
    }

    public String getDataGridCellValue (String chargeCode, String headerName) throws InterruptedException {
        String columnID;
        String cellValue = null;
        try {
            System.out.println(chargeCode);
            System.out.println(headerName);
            String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
            System.out.println("Row Number: " + row);
            columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][contains(text(),'" + headerName + "')]")).getAttribute("id");
            int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
            cellValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]")).getText();
            System.out.println("Value: " + cellValue);
        } catch (Throwable e) {
            e.getMessage();
        }
        return cellValue;
    }

    public static List<WebElement> getListOfAllOfSameElementWhenMoreThanOneOnPage(String elementXPath) {
        List<WebElement> listOfElement = driver.findElements(By.xpath(elementXPath));
        return listOfElement;
    }

    public static WebElement getListOfSameElementAndSelectOne(String elementXPath, int listIndex) {
        List<WebElement> listOfElement = driver.findElements(By.xpath(elementXPath));
        return listOfElement.get(listIndex);
    }

    public LocalDate getSystemLocalDate(boolean printout) {
        LocalDate date = LocalDate.now();
        if (printout) {
            System.out.println(date);
        }
        return date;
    }

    public String getServerMonth() {
        LocalDate date = LocalDate.now();
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return month;
    }

    public int getServerYear() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        return year;
    }

    public ArrayList<String> getDropdownMenuItemsTextAsArrayList(WebElement listElement, boolean printout) throws Exception {
        waitForAjaxExtJs();
        List<WebElement> menuList = listElement.findElements(By.tagName("li"));
        ArrayList<String> menuItemsAsStrings = new ArrayList<>();
        for(WebElement item : menuList) {
            menuItemsAsStrings.add(item.getText());
            if(printout) {
                System.out.println("Menu item: " + item.getText());
            }
        }
        if(printout) {
            System.out.println("Dropdown Menu size: " + menuItemsAsStrings.size());
        }
        return menuItemsAsStrings;
    }

    public int getDropdownMenuSize(WebElement element, boolean printout) throws InterruptedException {
        waitForAjaxExtJs();
        webdriverClick(element);
        waitForAjaxExtJs();
        WebElement list = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
        List<WebElement> menu = list.findElements(By.tagName("li"));
        if(printout) {
            System.out.println("Menu size: " + menu.size());
        }
        return menu.size();
    }

    public WebElement getButton(String buttonText) throws InterruptedException {
        waitForAjaxExtJs();
        WebElement button = driver.findElement(By.xpath("//button/span[text()='"+buttonText+"']"));
        return button;
    }

    public String getElementText(WebElement element, boolean printout){
        String elementText = element.getText();

        if(printout){
            System.out.println("Element Text: " + elementText);
        }
        return elementText;
    }

    public WebElement getWebElement(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        return element;
    }

    public WebElement getWebElementWithElementText(String elementXpath, String elementText, boolean printout) throws InterruptedException {
        String xPath = elementXpath + elementText + "']";
        if(printout){
            System.out.println("xpath:" + xPath);
        }
        waitForAjaxExtJs();
        WebElement element = driver.findElement(By.xpath(xPath));
        return element;
    }

    public WebElement getWebElementButtonWithElementText(String pageXpath, String buttonText, boolean printout) throws InterruptedException {
//        Omkar 31/3/2023 : Change in xpath for ADS 11.2
//    	String xPath = pageXpath + "/descendant::button/span[text()='"+buttonText+"']";
        String xPath = pageXpath + "/descendant::span[text()='"+buttonText+"']";
        if(printout){
            System.out.println("Element xpath:" + xPath);
        }
        waitForAjaxExtJs();
        WebElement element = driver.findElement(By.xpath(""+xPath+""));
        return element;
    }

    public WebElement getWebElementButtonWithElementText(String buttonText) throws InterruptedException {
        String xpath = "//div[not(contains(@class,'disabled'))]/em/button/span[text()='" + buttonText + "']";
        waitForAjaxExtJs();
        WebElement element = driver.findElement(By.xpath("" + xpath + ""));
        return element;
    }

}
