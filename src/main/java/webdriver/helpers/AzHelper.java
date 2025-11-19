package webdriver.helpers;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
		try {
			doClick("//div[text()='"+scenarioName+"']//following::div[contains(@class,'x-toolbar')]//span[text()='"+buttonName+"']");
		} catch (NoSuchElementException e) {
			doClick("(//div[text()='"+scenarioName+"']//following::div[contains(@class,'x-panel')]//span[text()='"+buttonName+"'])[1]");

		}
		
	}
	public void addNewScenario(String[] ages,String name,String scenarioName,String[] columns) throws Throwable {
		doClick(DataMaintenanceMap.getazNewBtn());
		keyInInputText(name, DataMaintenanceMap.getaddName());
		doClickButtons(scenarioName, "Select");
		driverDelay();
//		waitForPresenceOfElement("//div[contains(@class,'x-window-header-title')]");
		ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
		CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
//		doClick(DataMaintenanceMap.getapplyBtnInPopUp());
		assertElements(ages,scenarioName);
	}
	public void assertElements(String[] ages,String azName) {
			for(int i=0;i>=driver.findElements(By.xpath("//div[text()='"+azName+"']//following::table//tbody//tr//td[3]/div")).size();i++) {
			if(DataMaintenanceMap.getageGroupElements().get(i+1).getText().equals(ages[i])) {
				assertTrue(printout);
			}
			}
		
	}
	public void clickButton(String name) throws Throwable {
		doClick("//span[text()='"+name+"']");
	}
	public void keyInInputByName(String inputName,String inputText,String dialogName) throws Throwable {
		driver.findElement(By.xpath("//div[text()='"+dialogName+"']//following::input[@name='"+inputName+"']")).clear();;
		driver.findElement(By.xpath("//div[text()='"+dialogName+"']//following::input[@name='"+inputName+"']")).sendKeys(inputText);
		driverDelay(500);
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
	public void selectFormItem(String name,String selectServices) throws Throwable {
		
		if(selectServices.equals("services")) {
			doClick("//div[text()='"+name+"']");
			doClick("//div[contains(@id,'dynamicwindow')]//following::span[text()='Select']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
		}
		else {
			doClick("//div[text()='"+name+"']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
		}
	}
	public void assertData(String azName, String text) {
		assertElementIsDisplayedWithXpath("//*[text()='"+azName+"']//following::*[text()='"+text+"']");
	}
	public void addDetailsInnerPages(String code,String name,String action,String webeleCode,String webeleName) throws Throwable {
		if(code!=null) {
			String codeelement = CimHelper.checkElements(driver.findElements(By.name(webeleCode)));
			driver.findElement(By.xpath(codeelement)).sendKeys(code);
		}
		if(name!=null) {
			String nameElement= CimHelper.checkElements(driver.findElements(By.name(webeleName)));
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
	public void addEntries(String panelName,String button,String[] entries) throws Throwable {
		Actions action=new Actions(driver);
		doClick("//h1[text()='"+panelName+"']//following::span[text()='New']");
		int j=0;
		List<WebElement> div=driver.findElements(By.xpath("//h1[text()='"+panelName+"']//following::table//td/div"));
		for(int i=1;i<=div.size();i++) {
			if(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td/div")).getAttribute("class").contains("x-grid-checkcolumn")) {
				doClick("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div");
				continue;
			}
			
			List<WebElement> nocheck=driver.findElements(By.xpath("//h1[text()='"+panelName+"']//following::table//td/div[contains(@class,'x-grid-cell-inner')]"));
			if(!nocheck.isEmpty()&&j<entries.length) {
				action.moveToElement(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(j+2)+"]/div"))).sendKeys(Keys.ENTER).perform();
				action.moveToElement(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(j+2)+"]/div"))).doubleClick().sendKeys(entries[i]).sendKeys(Keys.ENTER).pause(200).perform();
				j++;
			}
		}
//		for(int i=1;i<=div.size();i++) {
//			if(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div")).getAttribute("class").contains("x-grid-checkcolumn")) {
//				System.out.println(i);
//				doClick("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div");
//			}
//			if(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div")).getAttribute("class").contains("x-grid-cell-inner")) {
//				action.moveToElement(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div"))).sendKeys(Keys.ENTER).perform();
//				action.moveToElement(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div"))).doubleClick().sendKeys(entries[i-1]).sendKeys(Keys.ENTER).pause(200).perform();
//			}
////			else {
////			System.out.println(i);
////			action.moveToElement(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div"))).sendKeys(Keys.ENTER).perform();
////			action.moveToElement(driver.findElement(By.xpath("//h1[text()='"+panelName+"']//following::table//td["+(i+1)+"]/div"))).doubleClick().sendKeys(entries[i-1]).sendKeys(Keys.ENTER).pause(200).perform();
////			}
//			
//		}
		
	}
}
