package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.DoHelper;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

//Omkar 7/8/2023 : Unable to scroll to created folder. See comment below in the code
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreatingaNewContractingFolder extends GoHelper {

	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractFolderName = "Folder" + currentDateTime;
	static String renameFolderName=contractFolderName+"Test";
	Actions act=new Actions(driver);

	/** Regression: Automated test script for ADS-6410,ADS-6411 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreatingaNewContractingFolder",
				"webdriver.scripts.contracting", "CreatingaNewContractingFolder");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
/**Test - UI Validation [Contracting] �Creating a New Contracting Folder�.**/
	//ADS-6410
	@Test
	public void test01CreateNewContractFolder_ADS_6410() throws Throwable {
		try {
			/*
			doClick(modelMap.getNewContractFolderBtn());
			waitForElementToBeVisible(modelMap.getNewFolderPopUp());
			doClick(modelMap.getNewFolderNameInput());
			modelMap.getNewFolderNameInput().sendKeys(contractFolderName);
			driverDelay(500);
			doClick(ContractingMap.getNewFolderNameSave());
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
			doClick(modelMap.getContractingTreeExpand());
			driverDelay(500);
			DoHelper.scrollToView(driver.findElement(By.xpath("//span[text()='" + contractFolderName + "']")));  //unable to scroll to this folder in 11.2 need a fix ADS-11058
			assertTextIsDisplayed(contractFolderName);
			*/
			//Shilpa : Updated script for 11.2 on 11.5.2024
			doClick(modelMap.getNewContractFolderBtn());
			waitForElementToBeVisible(modelMap.getNewFolderPopUp());
			doClick(modelMap.getNewFolderNameInput());
			modelMap.getNewFolderNameInput().sendKeys(contractFolderName);
			driverDelay(500);
			doClick(ContractingMap.getNewFolderNameSave());
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
			doClick(modelMap.getContractingTreeExpand());
			driverDelay(500);
			ScrollToElementInTree(contractFolderName);
			ExtentReport.logPass("PASS", "test01CreateNewContractFolder_ADS_6410");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractFolder_ADS_6410", driver, e);
			fail(e.getMessage());
		}
	}
	
	//ADS-6411
	@Test
	public void test02EditNewContractFolder_ADS_6411() throws Throwable {
		try {
			/*
			doClick("//span[text()='" + contractFolderName + "']");
			doClick(ContractingMap.getNewFolderRename());
			act.moveToElement(driver.findElement(By.xpath("//div[text()='" + contractFolderName + "']"))).sendKeys(renameFolderName).build().perform();
			
			*/
			//Shilpa : Updated script for 11.2 on 11.5.2024
			ScrollToElementInTree(contractFolderName);
			doClick("//span[text()='" + contractFolderName + "']");
			doClick(ContractingMap.getNewFolderRename());
//			act.moveToElement(driver.findElement(By.xpath("//div[text()='" + contractFolderName + "']"))).sendKeys(renameFolderName).build().perform();
			//Shilpa: Update for 11.2 on 11.5.2024
//			ScrollToElementInTree(contractFolderName);
//			scrollToView("(//span[contains(@class,'x-tree-node-text')])[30]");
//			scrollToView("(//span[contains(@class,'x-tree-node-text')])[40]");
			act.moveToElement(driver.findElement(By.xpath("//span[text()='" + contractFolderName + "']"))).sendKeys(Keys.CLEAR).sendKeys(renameFolderName).sendKeys(Keys.ENTER).build().perform();
			ExtentReport.logPass("PASS", "test02EditNewContractFolder_ADS_6411");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditNewContractFolder_ADS_6411", driver, e);
			fail(e.getMessage());
		}
	}
	//Shilpa Add method for 11.2 as direct scroll to folder wont work, so scroll one by one folder : 11.5.2024
	public static void ScrollToElementInTree(String folderName) throws Throwable {
		List<WebElement> elements=driver.findElements(By.xpath("(//span[contains(@class,'x-tree-node-text')])"));
		for(int i=1;i<elements.size();i++) {
			scrollToView("(//span[contains(@class,'x-tree-node-text')])["+i+"]");
			try {
				if(driver.findElement(By.xpath("(//span[contains(@class,'x-tree-node-text')])["+i+"]\"")).getText().contains(folderName)) {
					assertTextIsDisplayed(contractFolderName);
					break;

				}
			} catch (Exception e) {
				continue;
			}
		}
	}
	@Test
	public void test03DeleteNewContractFolder() throws Throwable {
		try {
			/*
			act.moveToElement(driver.findElement(By.xpath("//span[text()='" + renameFolderName + "']"))).click().build().perform();
			doClick(modelMap.getDeleteContractFolderBtn());
			waitForElementToBeVisible(modelMap.getContractModelDeleteButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			doClosePageOnLowerBar("Model Library");
			*/
			//Shilpa: Update for 11.2 on 11.5.2024
			doClick(modelMap.getContractingTreeExpand());
			driverDelay(500);
			ScrollToElementInTree(renameFolderName);
			act.moveToElement(driver.findElement(By.xpath("//span[text()='" + renameFolderName + "']"))).click().build().perform();
			doClick(modelMap.getDeleteContractFolderBtn());
			waitForElementToBeVisible(modelMap.getContractModelDeleteButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test03DeleteNewContractFolder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteNewContractFolder", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
