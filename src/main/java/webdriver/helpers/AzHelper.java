package webdriver.helpers;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
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
			WebElement dropdownBtn,WebElement dropdownTriggerList,String action) throws Throwable {
		if(code!=null) {
			keyInInputText(code, codeEle);
			
			
		}
		if(name!=null) {
			keyInInputText(name, nameEle);
		}
		if(dropdown!=null) {
			doClick(dropdownBtn);
			doDropdownSelectUsingOptionTextOnly(dropdownTriggerList, dropdownValue);
		}
		switch (action) {
		case "Save & Create New":
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			break;
		case "Save":
			doClick(DataMaintenanceMap.getazSaveBtn());
			break;
		case "Save & Close":
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			break;
		default:
			break;
		}
	}
	public void deleteScenario() {
		doClick(DataMaintenanceMap.getazDeleteBtn());
		waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
		doClick(DataMaintenanceMap.getwarningDeleteBtn());
		assertTextIsDisplayed("There is no data available to display.");
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
