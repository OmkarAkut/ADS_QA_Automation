package webdriver.helpers;

import java.util.List;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class AzHelper extends CalculationHelper{
	private static DialogsMap dialog;
	private static Actions builder;
	private static CostingMap costing;
	@BeforeClass
	public static void setupHelper() {
		dialog = BuildMap.getInstance(driver, DialogsMap.class);
		costing = BuildMap.getInstance(driver, CostingMap.class);
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		builder = new Actions(driver);
	}
	public void addNewScenario(String code,String name,String dropdown,WebElement codeEle,WebElement nameEle,String dropdownValue,
			List<WebElement> dropdownBtn,WebElement dropdownTriggerList,String action) throws Throwable {
		if(code!=null) {
			keyInInputText(code, codeEle);
			
			
		}
		if(name!=null) {
			keyInInputText(name, nameEle);
		}
		if(dropdown!=null) {
//			doClick(dropdownBtn);
			CimHelper.checkElements(dropdownBtn);
			doDropdownSelectUsingOptionTextOnly(dropdownTriggerList, dropdownValue);
		}
		switch (action) {
		case "Save & Create New":
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			break;
		case "Save":
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			break;
		case "Save & Close":
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));

			break;
		default:
			break;
		}
	}
	public void deleteScenario(WebElement button,WebElement waitForElement) {
		doClick(button);
		waitForElementToBeVisible(waitForElement);
		doClick(waitForElement);
		assertTextIsDisplayed("There is no data available to display.");
	}
	public void doClickButtons(String scenarioName,String buttonName) throws Throwable {
		doClick("//div[text()='"+scenarioName+"']//following::div[contains(@class,'x-toolbar')]//span[text()='"+buttonName+"']");
	}
	public void waitForFormDialog(String windowName) {
		waitForElementToBeVisible(driver.findElement(By.xpath("//div[contains(@id,'dynamicwindow')][text()='"+windowName+"']")));
	}
	public void doClickSelectButton(String title) throws Throwable {
		doClick("(//div[contains(@class,'x-form-fieldcontainer')]//span[text()='"+title+"']//following::div//span[text()='Select'])[1]");
	}
	public void keyInInputWithActionClass(WebElement element,String input) {
		Actions action=new Actions(driver);
		action.moveToElement(element).click().sendKeys(Keys.CLEAR).sendKeys(input).sendKeys(Keys.ENTER).perform();
	}
	public void selectFormItem(String name) throws Throwable {
		doClick("//div[text()='"+name+"']");
		doClick(DataMaintenanceMap.getapplyBtnInPopUp());
	}
	public void addDetailsInnerPages(String code,String name,String action) throws Throwable {
		if(code!=null) {
			String codeelement = CimHelper.checkElements(driver.findElements(By.name("code")));
			driver.findElement(By.xpath(codeelement)).sendKeys(code);
		}
		if(name!=null) {
			String nameElement= CimHelper.checkElements(driver.findElements(By.name("name")));
			driver.findElement(By.xpath(nameElement)).clear();
			driver.findElement(By.xpath(nameElement)).sendKeys(name);
		}
	
		
		
		
		
		switch (action) {
		case "Save & Create New":
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			break;
		case "Save":
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			break;
		case "Save & Close":
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			break;
		default:
			break;
		}
	}
}
