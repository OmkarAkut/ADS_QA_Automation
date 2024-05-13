package Regression;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-5798 **/
public class AssociateOverheadModeltoCostModelCustomTaskList extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String folder;
//	static String name="Folder Name"+currentDateTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AssociateOverheadModeltoCostModelCustomTaskList", "webdriver.scripts.systemmaintenance", "AssociateOverheadModeltoCostModelCustomTaskList");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
			systemMap=BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void clearNewFolderName(String name) throws Exception {
		Actions act=new Actions(driver);
		doClick(systemMap.getSystemMaintenanceAddFolderButton());
		/*
		doClick(systemMap.getSystemMaintenanceAddFoldercol());
		String newFolder=systemMap.getSystemMaintenanceAddFolder().getText();
		for(int i=0;i<=newFolder.length();i++) {
			act.moveToElement(systemMap.getSystemMaintenanceAddFolder()).sendKeys(Keys.DELETE).perform();
			driverDelay(200);
		}
		*/
		//Shilpa updated code for 11.2 on 12.11.2023
		 List<WebElement> elements = driver.findElements(By.xpath("(//div[@id='ctlCostingTree-body']//div[@class='x-grid-item-container']//table//tr//td//div/span[text()='Folder Name'])"));
		 for(int i=1;i<=elements.size();i++) {
			 if(i==elements.size()) {
				 String folderName=currentDateTime+name;
			 act.moveToElement(driver.findElement(By.xpath("(//div[@id='ctlCostingTree-body']//div[@class='x-grid-item-container']//table//tr//td//div/span[text()='Folder Name'])["+i+"]"))).sendKeys(Keys.DELETE).sendKeys(Keys.chord(folderName)).pause(1000).sendKeys(Keys.ENTER).perform();
				driverDelay(200);
//				doClick(SystemMaintenanceMap.getTaskListSaveButton());
//				doClick(ContractingMap.getSaveButtonMesaageBox());
//				waitForAjaxExtJs();
//				Thread.sleep(4000);
				doClick("((//div[@id='ctlCostingTree-body']//div[@class='x-grid-item-container']//table//tr//td//div/span[text()='"+folderName+"'])//parent::div//div[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'])[1]");
				Thread.sleep(500);
	
										act.dragAndDrop(driver.findElement(By.xpath("(//div[@class='x-grid-item-container'])[2]//div[text()='Allocation Exceptions']")), 
						driver.findElement(By.xpath("//span[text()='"+folderName+"']//parent::div//parent::td//parent::tr"))).pause(1000).build().perform();
				
				/*
				String deleteFolder=currentDateTime+name;
				System.out.println(deleteFolder);
				act.moveToElement(driver.findElement(By.xpath("(//div[@id='ctlCostingTree-body']//div[@class='x-grid-item-container']//table//tr//td//div/span[text()='"+folderName+"'])"))).click().perform();
				doClick(SystemMaintenanceMap.getTaskListRemoveButton());
				doClick(contractMap.getContractModelDeleteButtonInPopUp());
				waitForAjaxExtJs();
				Thread.sleep(4000);
				*/
			 }
		 }

	}
	//ADS-5798
	@Test
	public void test01AddScreens_5798() throws Throwable {
		try {
			clearNewFolderName("Allocation Exceptions");
//			clearNewFolderName("Allocation Statistic Assignments");
//			clearNewFolderName("Overhead Account Variability Masters");
//			clearNewFolderName("General Information Overhead");
//			doClick(contractMap.getContractModelSaveCopy());
//			doClick(ContractingMap.getSaveBenefitPlan());
			waitForAjaxExtJs();
			
			ExtentReport.logPass("PASS", "test01AddScreens");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddScreens", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
