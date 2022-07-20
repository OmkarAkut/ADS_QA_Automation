package webdriver.helperstatic;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

/** Methods to interact with elements on the Filter pages in ADS.
 *  Contains all filter related methods, including wait, set, get, etc.
 */
public class FilterStatic extends DoStatic {

    private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);

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
        doFilterSetFilterParameters(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
        waitForAjaxExtJs();
        waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
        doClick(dialog.getFilterDialogButtonAdd());
        waitForAjaxExtJs();
        doClick(dialog.getFilterDialogButtonApplyFilter());
        waitForSpinnerToEnd();
    }

    public void doFilterSetFilterParameters(String field, String operator, String condition, String value) throws InterruptedException {
        waitForAjaxExtJs();
        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownField(), field);
        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownOperator(), operator);
        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownCondition(), condition);
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

}
