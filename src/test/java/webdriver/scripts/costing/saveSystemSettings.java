package webdriver.scripts.costing;

import webdriver.corehelpers.GoHelper;
import webdriver.maps.SystemMaintenanceMap;

public class saveSystemSettings extends GoHelper{
	public void saveCustomSettings(String setName,String modelName) {
		try {
			goToPage("Customize Task Lists");
			driverDelay();
			doClick("//label[text()='"+setName+"']//preceding-sibling::span");
			driverDelay(1000);
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			driverDelay(1000);
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			waitForDisplayedSavingSpinnerToEnd();
//			waitForAjaxExtJs();
			driverDelay(5000);
			goToPage(modelName);
			driverDelay();
		} catch (Exception e) {
			
		}
	}
	public  void revertCustomSettings() {
		try {
			goToPage("Customize Task Lists");
			driverDelay();
			doClick("//label[text()='Use Default']//preceding-sibling::span");
			doClick(SystemMaintenanceMap.getTaskListSaveButton());
			doClick("//div[contains(@id,'messagebox')]//span[text()='Save']");
			waitForDisplayedSpinnerToEnd();
			driverDelay(5000);
			doClosePageOnLowerBar("Customize Task Lists");
		} catch (Exception e) {
		
		}
	}
}
