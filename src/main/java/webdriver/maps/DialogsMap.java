package webdriver.maps;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.helperstatic.WaitStatic;
import webdriver.maps.mapbuilder.MapConfig;

public class DialogsMap extends MapConfig {

    WaitStatic waitHelper = new WaitStatic();

    // ===== Filter Dialog ===== //

    @FindBy(xpath = "//*[contains(@id, 'filterwindow') and contains(@id, 'header_hd-textEl')]")
    private WebElement filterDialogHeader;
    public WebElement getFilterDialogHeader() {return filterDialogHeader;}

    @FindBy(xpath = "//*[contains(@onclick, 'filterfd.htm') and @class='listhelpLnk']")
    private WebElement filterDialogHelpLink;
    public WebElement getFilterDialogHelpLink() {return filterDialogHelpLink;}

    @FindBy(name = "field")
    private WebElement statusFilterDialogDropdownField;
    public WebElement getFilterDialogDropdownField() {return statusFilterDialogDropdownField;}

    @FindBy(name = "operator")
    private WebElement statusFilterDialogDropdownOperator;
    public WebElement getFilterDialogDropdownOperator() {return statusFilterDialogDropdownOperator;}

    @FindBy(name = "condition")
    private WebElement statusFilterDialogDropdownCondition;
    public WebElement getFilterDialogDropdownCondition() {return statusFilterDialogDropdownCondition;}

    @FindBy(name = "valuefield")
    private WebElement statusFilterDialogFieldValue;
    public WebElement getFilterDialogFormFieldValue() {return statusFilterDialogFieldValue;}

    @FindBy(name = "valueOfOnefield")
    private WebElement statusFilterDialogFieldValueOfOne;
    public WebElement getFilterDialogFormFieldValueOfOne() {return statusFilterDialogFieldValueOfOne;}

    @FindBy(xpath = "//*[text()='Apply Filter']")
    private WebElement statusFilterDialogButtonApplyFilter;
    public WebElement getFilterDialogButtonApplyFilter() {return statusFilterDialogButtonApplyFilter;}

    @FindBy(xpath = "//div[@class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']//*[text()='Cancel & Close']")
    private WebElement statusFilterDialogButtonCancelAndClose;
    public WebElement getFilterDialogButtonCancelAndClose() {return statusFilterDialogButtonCancelAndClose;}

    private WebElement filterDialogButtonAdd;
    public WebElement getFilterDialogButtonAdd() throws InterruptedException {
        filterDialogButtonAdd = driver.findElement(By.id("button-"+getFilterDialogAddButtonIdNumber()+""));
        try {
            assertEquals("Add", filterDialogButtonAdd.getText());
        } catch (Throwable e) {
            System.out.println("ERROR: Filter Dialog - Add button NOT FOUND");
        }
        return filterDialogButtonAdd;
    }
    private int getFilterDialogAddButtonIdNumber() throws InterruptedException {
        WebElement dialogHeaderElement = driver.findElement(By.xpath("//span[contains(@id,'filterwindow')]"));
        String dialogHeaderElementId = dialogHeaderElement.getAttribute("id");
        String dialogHeaderIdNumber = getNumbersFromStringWithRegex(dialogHeaderElementId);
        return  Integer.parseInt(dialogHeaderIdNumber)+48;
    }

//    public void getFilterDialogResultsRatio() throws InterruptedException {
//        waitHelper.waitForAjaxExtJs();
//        String filterResults = driver.findElement(By.xpath("//label[contains(text(),'Filter to Match These Criteria')]")).getText();
//        String filterNumbers = getNumbersFromStringWithRegex(filterResults);
//        System.out.println(filterNumbers);
//    }

    private String getNumbersFromStringWithRegex(String string) throws InterruptedException {
        Pattern p = Pattern.compile("\\d+"); //regex pattern
        Matcher m = p.matcher(string);    //string to search
        String numbers = null;
        while(m.find()) {
            numbers = m.group();
        }
        return numbers;
    }

    // ===== End Filter Dialog ===== //
}
