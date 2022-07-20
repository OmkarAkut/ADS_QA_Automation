package webdriver.helperstatic;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class SetStatic extends GoStatic {

    private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
    public final static String setStandardDateFormat = "yyyy-MM-dd:HH:mm:ss";

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
        driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]")).click();
        waitForAjaxExtJs();
        WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')]["+row+"]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-"+columnIdDigits+"')]/div/table"));
        Actions action = new Actions(driver);
        action.moveToElement(editCell).sendKeys(newValue).perform();
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
        webdriverClick(triggerDropdown);
        waitForAjaxExtJs();
        WebElement optionsUl;
        if (menuOptionsUl != null) {
            optionsUl = menuOptionsUl;
        } else {
            optionsUl = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
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
                option.click();
                break;
            }
        }
    }

}
