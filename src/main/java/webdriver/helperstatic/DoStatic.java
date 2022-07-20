package webdriver.helperstatic;

import java.util.List;
import java.util.Set;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class DoStatic extends GetStatic {

    public static void doSearchForContractModel(String contractModel) throws InterruptedException {
        ModelLibraryMap modelMapStatic = BuildMap.getInstance(driver, ModelLibraryMap.class);
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
        modelMapStatic.getModelLibraryButtonSearch().click();
        waitForSpinnerToEnd();
    }

    public static void doSearchForAndSelectContractModelFromContractModelLibrary(String contractModel) throws InterruptedException {
        ModelLibraryMap modelMapStatic = BuildMap.getInstance(driver, ModelLibraryMap.class);
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
        modelMapStatic.getModelLibraryButtonSearch().click();
        waitForAjaxExtJs();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
    }

    /**
     * Returns a String of the starting page window handle, so can be used at the end of a String variable:
     * String firstHandle = webSwitchToNewWindow(element, printout);
     * AND THEN an ending switchTo is required to switch focus back or framework will not logout properly:
     * driver.switchTo().window(firstHandle);
     * --This statement goes at the end of the script or if focus needs to be moved back to initial page to
     * interact with elements.  The element that triggers the new window is the "elementToClick."
     *
     * @param elementToClick
     * @param printout
     * @return String firstHandle
     * @throws InterruptedException
     */
    public String webSwitchToNewWindow(WebElement elementToClick, boolean printout) throws InterruptedException {
        String firstHandle = driver.getWindowHandle();
        doClick(elementToClick);
        if (browser.toLowerCase().equals("ie") || browser.toLowerCase().equals("internetexplorer")) {
            clickThroughIeCertificateScreens();
        }
        waitForAjaxExtJs();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!firstHandle.equals(handle)) {
                driver.switchTo().window(handle);
                //waitForJsWindowOnload();
//        if (printout) {
//          System.out.println("First Handle: " + firstHandle);
//          System.out.println("Switched to Handle: " + handle);
//        }
                break;
            }
        }
        return firstHandle;
    }

    //DialogsMap dialog = new DialogsMap();
    //private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
    private ModelLibraryMap modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    private DataMaintenanceMap dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);

    public void doMaintainDataPageSelectAtoZOption(String optionName) throws InterruptedException {
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        dmMap.getDataMaintenanceAZ().click();
        waitForAjaxExtJs();
        driver.findElement(By.xpath("//div[contains(@class, 'left_atoz')]/div[text()='" + optionName + "']")).click();
        waitForSpinnerToEnd();
    }


    public void doClickTreeItem(String name) throws InterruptedException {
        waitUntilElementIsClickable(driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + name + "']")));
        driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + name + "']")).click();
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        waitForSpinnerToEnd();
    }

    public void doClickTreeItemWithCheckbox(String name) throws InterruptedException {
        waitUntilElementIsClickable(driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + name + "']")));
    driver
        .findElement(
            By.xpath(
                "//td[contains(@class,'x-grid-cell-treecolumn')]" +
                        "/div[text()='" + name + "']/input[@class='x-tree-checkbox']/.."))
        .click();
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        waitForSpinnerToEnd();
    }

    public void doClickTreeItemUsingXpathLocator(String xpath) throws InterruptedException {
        waitUntilElementIsClickable(driver.findElement(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        waitForAjaxExtJs();
        waitForSpinnerToEnd();
    }

    public void doSearchForModel(String contractModel) throws InterruptedException {
        waitForSpinnerToEnd();
        waitUntilElementIsClickable(modelMap.getModelLibraryFieldSearch());
        modelMap.getModelLibraryFieldSearch().sendKeys(contractModel);
        Thread.sleep(1000);
        modelMap.getModelLibraryButtonSearch().click();
        waitForSpinnerToEnd();
        Thread.sleep(1000);
    }

//    public void doFilter(String[][] filterStatements, WebElement fieldOptionsUl) throws InterruptedException {
//        for (String[] filterParameters : filterStatements) {
//            if (printout) {
//                System.out.println("Set Filter To: " + filterParameters[0] + filterParameters[1] + filterParameters[2] + filterParameters[3]);
//            }
//            setFilterValues(fieldOptionsUl, filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
//            waitForAjaxExtJs();
//            waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
//            doClick(dialog.getFilterDialogButtonAdd());
//            waitForAjaxExtJs();
//        }
//        doClick(dialog.getFilterDialogButtonApplyFilter());
//        waitForSpinnerToEnd();
//    }


//    private void setFilterFields(WebElement fieldOptionsUl, String field, String operator, String condition, String value) throws InterruptedException {
//        waitForAjaxExtJs();
//        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownField(), fieldOptionsUl, field);
//        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownOperator(), operator);
//        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownCondition(), condition);
//        doClick(dialog.getFilterDialogFormFieldValue());
//        dialog.getFilterDialogFormFieldValue().sendKeys(value);
//    }

//    public void doFilterCreate(String[] filterParameters) throws InterruptedException {
//        doFilterSetFilterParameters(filterParameters[0], filterParameters[1], filterParameters[2], filterParameters[3]);
//        waitForAjaxExtJs();
//        waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
//        doClick(dialog.getFilterDialogButtonAdd());
//        waitForAjaxExtJs();
//        doClick(dialog.getFilterDialogButtonApplyFilter());
//        waitForSpinnerToEnd();
//    }
//
//    public void doFilterSetFilterParameters(String field, String operator, String condition, String value) throws InterruptedException {
//        waitForAjaxExtJs();
//        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownField(), field);
//        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownOperator(), operator);
//        doDropdownSelectUsingOptionText(dialog.getFilterDialogDropdownCondition(), condition);
//        doClick(dialog.getFilterDialogFormFieldValue());
//        dialog.getFilterDialogFormFieldValue().sendKeys(value);
//    }

    public static void doClosePageOnLowerBar(String pageName) throws InterruptedException {
        waitForAjaxExtJs();
        driver.findElement(By.xpath("//*[contains(@id,'tab') and contains(text(),'"+pageName+"')]/../../following-sibling::a")).click();
        waitForAjaxExtJs();
    }

    public static void doClickButton(String buttonText) throws InterruptedException {
        waitForAjaxExtJs();
        doClick(driver.findElement(By.xpath("//button/span[text()='"+buttonText+"']")));
    }

    public static void doClick(WebElement element) {
        waitUntilElementIsClickable(element);
        element.click();
    }

    public static void doClick(String elementXpath) throws InterruptedException {
        waitUntilElementIsClickable(driver.findElement(By.xpath("" + elementXpath + "")));
        driver.findElement(By.xpath("" + elementXpath + "")).click();
        waitForAjaxExtJs();
    }

    public static void doDropdownSelectUsingOptionText(WebElement elementTriggerList, WebElement elementList, String optionText) throws InterruptedException {
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        doClick(elementTriggerList);
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        List<WebElement> menu = elementList.findElements(By.tagName("li"));
        for(WebElement option : menu) {
            if(option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
      
    }

    public void doDropdownSelectUsingOptionText(WebElement element, String optionText) throws InterruptedException {
        waitForAjaxExtJs();
        doClick(element);
        waitForAjaxExtJs();
        WebElement list = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
        List<WebElement> menu = list.findElements(By.tagName("li"));
        for(WebElement option : menu) {
            if(option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }

  
    public void doDropdownSelectUsingOptionText(WebElement element, WebElement menuOptionsUl, String optionText, boolean printout) throws InterruptedException {
        waitForAjaxExtJs();
        doClick(element);
        waitForAjaxExtJs();
        WebElement list;
        if (menuOptionsUl != null) {
            list = menuOptionsUl;
        } else {
            list = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
        }
        if (printout) {
            System.out.println("menuOptionsUl: " + menuOptionsUl);
        }
        List<WebElement> menu = list.findElements(By.tagName("li"));
        for(WebElement option : menu) {
            if(option.getText().equals(optionText)) {
                option.click();
                break;
            }
        }
    }

   
    public static void doMaximizeWindow() {
        //driver.manage().window().maximize();
        webdriverMaximizeWindow();
    }

//    public void doSelectCalcStatusFilterCriteria(String criteriaOption, String optionText) throws InterruptedException {
//        if(criteriaOption.equals("Field")){
//            doClick(driver.findElement(By.xpath("//input[@name='field']")));
//            waitForAjaxExtJs();
//            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][1]/descendant::ul/li[text()=\"" + optionText + "\"]")));
//        }
//        if(criteriaOption.equals("Operator")){
//            doClick(driver.findElement(By.xpath("//input[@name='operator']")));
//            waitForAjaxExtJs();
//            doClick(driver.findElement(By.xpath("//div[@class='x-mask']/preceding::div[@class=\"x-boundlist x-boundlist-floating x-layer x-boundlist-default\"]/child::div/ul/li[text()='" + optionText + "']")));
//        }
//        if(criteriaOption.equals("Condition")){
//            doSelectCalcStatusFilterConditionValue(optionText);
//        }
//        if(criteriaOption.equals("Value")){
//            doClick(driver.findElement(By.xpath("//input[@name='valuefield']")));
//            driver.findElement(By.xpath("//input[@name='valuefield']")).sendKeys(optionText);
//        }
//    }

//    public void doSelectCalcStatusFilterConditionValue(String conditionValue) {
//        doClick(driver.findElement(By.xpath("//input[@name='condition']")));
//        WebElement conditions = driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul"));
//        List<WebElement> existingConditions = conditions.findElements(By.tagName("li"));
//        List<String> actualConditions = new ArrayList<>();
//        for(WebElement condition: existingConditions){
////            System.out.println("Actual condition listed: " + condition.getText());
//            actualConditions.add(condition.getText());
//        }
//
//        if(conditionValue.equals("Equal To")){
//            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(0) + "']")));
//        }
//        if(conditionValue.equals("Contains")){
//            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(1) + "']")));
//        }
//        if(conditionValue.equals("Starts With")){
//            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(2) + "']")));
//        }
//        if(conditionValue.equals("Ends With")){
//            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(3) + "']")));
//        }
//        if(conditionValue.equals("One Of")){
//            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-window winCls')]/following::div[contains(@class,'x-boundlist-floating')][2]/descendant::ul/li[text()='" + actualConditions.get(4) + "']")));
//        }
//    }

    public void doSelectOneOfValueField(String valueText){
        doClick(driver.findElement(By.xpath("//input[@name='valueoneof']")));
        driver.findElement(By.xpath("//input[@name='valueoneof']")).sendKeys(valueText);
    }
}



