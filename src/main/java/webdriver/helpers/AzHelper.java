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
			driverDelay(300);
		} catch (NoSuchElementException e) {
			try {
				doClick("(//div[text()='"+scenarioName+"']//following::div[contains(@class,'x-panel')]//span[text()='"+buttonName+"'])[1]");
				driverDelay(300);
			} catch (NoSuchElementException e1) {
				try {
					doClick("(//div[text()='"+scenarioName+"']//following::div[contains(@class,'x-btn')]//span[text()='"+buttonName+"'])[1]");
					driverDelay(300);
				} catch (NoSuchElementException e2) {
					try {
						doClick("(//div[text()='"+scenarioName+"']//following::a[contains(@class,'x-btn')]//span[text()='"+buttonName+"'])[1]");
						driverDelay(300);
					} catch (NoSuchElementException e3) {
						doClick("(//label[text()='"+scenarioName+"']//following::a[contains(@class,'x-btn')]//span[text()='"+buttonName+"'])[1]");
						driverDelay(300);
					}
				}
			}

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
			for(int i=2;i<driver.findElements(By.xpath("//div[contains(@id,'panel')][text()='"+azName+"']//following::table//tbody//tr//td[3]/div")).size();i++) {
					if(driver.findElements(By.xpath("//div[contains(@id,'panel')][text()='"+azName+"']//following::table//tbody//tr//td[3]/div")).get(i).getText().equals(ages[i])) {
						
						
						assertTrue(printout);
						
					}

			
			}
		
	}
	public void clickButton(String name) throws Throwable {
		doClick("//span[text()='"+name+"']");
	}
	public void clickCheckboxByName(String name) throws Throwable {
		doClick("//input[@name='"+name+"'][@type='text']");
	}
	public void keyInInputByName(String inputName,String inputText,String dialogName) throws Throwable {
		try {
			driver.findElement(By.xpath("//div[text()='"+dialogName+"']//following::input[@name='"+inputName+"']")).clear();;
			driver.findElement(By.xpath("//div[text()='"+dialogName+"']//following::input[@name='"+inputName+"']")).sendKeys(inputText);
			driver.findElement(By.xpath("//div[text()='"+dialogName+"']//following::input[@name='"+inputName+"']")).sendKeys(Keys.ENTER);
			driverDelay(500);
		} catch (NoSuchElementException e) {
			driver.findElement(By.xpath("//textarea[@name='"+inputName+"']")).clear();;
			driver.findElement(By.xpath("//textarea[@name='"+inputName+"']")).sendKeys(inputText);
			driverDelay(500);
		}
	}
	public void waitForFormDialog(String windowName) {
		waitForElementToBeVisible(driver.findElement(By.xpath("//div[contains(@id,'dynamicwindow')][text()='"+windowName+"']")));
	}
	public void closeWindowDialog(String windowName) {
		doClick(driver.findElement(By.xpath("//div[contains(@id,'dynamicwindow')][text()='"+windowName+"']//following::span[text()='Cancel & Close']")));
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
			scrollToView("//div[text()='"+name+"']");
			doClick("//div[text()='"+name+"']");
			doClick("//div[contains(@id,'dynamicwindow')]//following::span[text()='Select']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
		}
		else {
			scrollToView("//div[text()='"+name+"']");
			doClick("//div[text()='"+name+"']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
		}
	}
	public void applyFilterInDialog() {
		
	}
	public void assertData(String azName, String text) {
		assertElementIsDisplayedWithXpath("//*[text()='"+azName+"']//following::*[text()='"+text+"']");
	}
	public void addDetailsInnerPages(String code,String name,String action,String webeleCode,String webeleName) throws Throwable {
		if (code != null) {
			String codeelement = CimHelper.checkElements(driver.findElements(By.name(webeleCode)));
			driver.findElement(By.xpath(codeelement)).sendKeys(code);
		}
		if (name != null) {
			String nameElement = CimHelper.checkElements(driver.findElements(By.name(webeleName)));
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
		Actions action = new Actions(driver);
		doClick("//h1[text()='" + panelName + "']//following::span[text()='New']");
		List<WebElement> inputText = driver.findElements(By
				.xpath("//h1[text()='" + panelName + "']//following::table//td/div/div[@class='x-grid-checkheader']"));
		System.out.println(inputText.size());

		for (int i = 0; i < inputText.size(); i++) {
			inputText.get(i).click();

		}

		int index = 0;
		if (index < entries.length) {
//	int target = i + 1;
			List<WebElement> checkbox = driver.findElements(By
					.xpath("//h1[text()='" + panelName + "']//following::table//td/div[@class='x-grid-cell-inner ']"));
			System.out.println(checkbox.size());
			for (int j = 1; j <= checkbox.size(); j++) {
				action.moveToElement(driver.findElement(By.xpath("(//h1[text()='" + panelName
						+ "']//following::table//td/div[@class='x-grid-cell-inner '])[" + j + "]"))).doubleClick()
						.sendKeys(entries[index]).sendKeys(Keys.ENTER).pause(200).perform();
				index++;
			}

		}

	}
	public void navigateToTab(String tabName) throws Throwable {
		driverDelay(3000);
		doClick("//span[text()='"+tabName+"']");
	}
	public void expandPanel(String panel) throws Throwable {
		CimHelper.checkElements(driver.findElements(By.xpath("//*[contains(@id, 'header-title')][text()='"+panel+"']/parent::div/following-sibling::div")));
//		driver.findElement(By.xpath("//*[contains(@id, 'header-title')][text()='"+panel+"']/parent::div/following-sibling::div")).click();
		waitForAjaxExtJs();
		Thread.sleep(2000); 
	}
	public void collapsePanel(String panel) throws Throwable {
		driver.findElement(By.xpath("//*[contains(@id, 'header-title')][text()='"+panel+"']/parent::div/following-sibling::div")).click();
		waitForAjaxExtJs();
		Thread.sleep(2000); 
	}
}
	
