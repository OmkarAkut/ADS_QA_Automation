package webdriver.scripts.costing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import webdriver.corehelpers.GoHelper;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class saveSystemSettings extends GoHelper{
	Actions act=new Actions(driver);
	
	public void saveScreenshot(String name) {
		 File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(screenshotFile, new File(name+"."+"png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public void saveCustomSettings(String setName,String modelName) {
		try {
			goToPage("Customize Task Lists");
//			act.moveToElement(driver.findElement(By.id("systemmaintenance"))).click().perform();
//			act.moveToElement(driver.findElement(By.id("customizetasklist"))).click().perform();
			driverDelay();
			saveScreenshot("Before");
			act.moveToElement(driver.findElement(By.xpath("//label[text()='Use Default']//preceding-sibling::span"))).click().pause(1000).perform();
			act.moveToElement(driver.findElement(By.xpath("//label[text()='"+setName+"']//preceding-sibling::span"))).click().pause(1000).perform();
			saveScreenshot("selectCustom");
			driverDelay(1000);
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			driverDelay(1000);
			saveScreenshot("ClickSave");
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			
			waitForDisplayedSavingSpinnerToEnd();
			
//			waitForAjaxExtJs();
			driverDelay(5000);
			
			goToPage("Customize Task Lists");
			
			 driverDelay();
			 saveScreenshot("CrossVerifySettingsSaved");
			 doClosePageOnLowerBar("Customize Task Lists");
			goToPage(modelName);
		} catch (Exception e) {
			
		}
	}
	public  void revertCustomSettings() {
		try {
			goToPage("Customize Task Lists"); 
			driverDelay();
			act.moveToElement(driver.findElement(By.xpath("//label[text()='Use Default']//preceding-sibling::span"))).click().pause(1000).perform();
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			waitForDisplayedSpinnerToEnd();
			driverDelay(5000);
			goToPage("Customize Task Lists");
		
			 driverDelay();
			 saveScreenshot("revert");
			
			doClosePageOnLowerBar("Customize Task Lists");
		} catch (Exception e) {
		
		}
	}
}
