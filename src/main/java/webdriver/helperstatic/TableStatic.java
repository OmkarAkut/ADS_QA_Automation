package webdriver.helperstatic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TableStatic extends FilterStatic {

    private WebElement tableGetTableContainerElementWithTableHeading(String tableHeading) {
        return driver.findElement(By.xpath(
                "//div[text()='"+tableHeading+"']/ancestor::div[contains(@class,'x-panel commonTBar')]"
                        + "/following-sibling::div/descendant::table/tbody"));
    }

    public static void tableClickColumnHeader(String headerText) {
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
        WebElement element = driver.findElement(By.xpath(
            "//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"))
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

    public void tableDoubleClickCellFirstColumn(String cellValue) throws InterruptedException {
        Actions act = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(
           "//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + cellValue + "']"))
        ;
        act.doubleClick(element).perform();
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

    //UNDER CONSTRUCTION
    /*
    private void getTableHeadings(boolean printout) throws InterruptedException {
        boolean hasNext = true;
        //ArrayList<String> tableHeadings = new ArrayList<>();
        //int seedId;  // = getInitialTableId(printout);
        String seedId = driver.findElement(By.xpath("//*[contains(@class,'x-panel-header-text-default')]")).getAttribute("id");
        System.out.println("seedID: " + seedId);
        int seed = Integer.parseInt(getNumbersFromStringWithRegex(seedId));
        System.out.println("Seed: " + seed);
        int seedAdj = seed + 5;
        System.out.println("Adj Seed: " + seedAdj);
        while(hasNext) {
            try {
                WebElement heading = driver.findElement(By.xpath("//span[@id='gridcolumn-"+seedAdj+"-textEl' and @class='x-column-header-text']"));
                System.out.println("Column Heading: " + heading.getText());
                //tableHeadings.add(heading.getText());
                seedAdj++;
                hasNext = false;
            } catch(Throwable e){
                System.out.println("Element not found");
                hasNext = false;
            }
        }
    }
    */
}

//ARCHIVE
/*
    private int getInitialTableId(boolean printout) {
    String initialTableId;
    initialTableId = driver.findElement(By.xpath("//*[contains(@class,'x-column-header-first')]")).getAttribute("id");
    initialTableId = initialTableId.substring(12);
    if (printout) {
        System.out.println(Integer.parseInt(initialTableId));
    }
    return Integer.parseInt(initialTableId);
}
*/
