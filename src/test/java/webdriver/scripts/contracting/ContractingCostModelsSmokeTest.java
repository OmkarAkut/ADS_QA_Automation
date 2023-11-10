package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.AssertHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContractingCostModelsSmokeTest extends ContractModelsHelper {
	private static EditContractingModelMap editModelMap;
	private static final String contractModel = "AFT IPPS 2020 - Criteria Text";
	private static final String serviceModel = "OPPS 2019";


	// static ExtentTest test1 ;
	// static ExtentReports report=new ExtentReports();
	/**
	 * Automates test ticket ADS-1207-General Section. Dev Story ADS-1405. Verifies
	 * text in Criteria box displays proper data for Medicare years 2020 (new ui)
	 * and 2019 and before (previous ui) after changing the year.
	 * @throws Throwable 
	 **/
	@BeforeClass
	public static void setupScript() throws Throwable {
		//Shilpa 10.08.2022 added report 
		ExtentReport.reportCreate("ContractingCostModelsSmokeTest", "webdriver.scripts.contracting","ContractingCostModelsSmokeTest");
		try {
			editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
			Login.loginUser("ContractAnalyst1");
			navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
			Thread.sleep(2000);
			navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		fail(e.getMessage());
		}
	}

	@Test
	public void test01Verify() throws InterruptedException,Throwable {
		try {
			waitForAjaxExtJs();
			// Shilpa : 29.07.2022 updated list and xpath's , exceptions handled
			String optionUI;
			//shilpa: 11/10/2023 added this line to select service model first then price method is enabled
			doClick("//div[text()='Pricing Method']//following::div[contains(@class,'x-grid-cell-inner')]//following::span[text()='"+serviceModel+"']");
			waitForPresenceOfElement("//*[@name = 'pricemethodoption']");
			waitForAjaxExtJs();
			webdriverClick(driver.findElement(By.name("pricemethodoption")));
			waitForAjaxExtJs();
			//shilpa updated xpath for 11.2
//			List<WebElement> costMethods = driver.findElements(By.xpath("//div[@class='x-boundlist-list-ct']/ul/li"));
			List<WebElement> costMethods = driver.findElements(By.xpath("//div[contains(@id,'dynamiccombo')]/ul/li"));
			for (int i = 1; i <= costMethods.size(); i++) {
				Thread.sleep(300);
				waitForElementToBeVisible(
						driver.findElement(By.xpath("//div[contains(@id,'dynamiccombo')]/ul/li[" + i + "]")));
				optionUI = driver.findElement(By.xpath("//div[contains(@id,'dynamiccombo')]/ul/li[" + i + "]")).getText();
				if (optionUI.equals("<None>")) {
					continue;
				}
				webdriverClick(driver.findElement(By.xpath("//div[contains(@id,'dynamiccombo')]/ul/li[" + i + "]")));
				Thread.sleep(300);
				//shilpa updated xpath for 11.2
//				webdriverClick(driver.findElement(By.xpath("//input[@name='pricemethodoption']//following::span[1]")));
				webdriverClick(driver.findElement(By.xpath("(//input[@name='pricemethodoption']//following::a//span[text()='Edit'])[1]")));

				Thread.sleep(500);
				System.out.println(optionUI);
				//shilpa updated xpath for 11.2
//				waitForElementToBeVisible(driver
//						.findElement(By.xpath("//span[contains(@id, 'header') and contains(text(), '" + optionUI + "')]")));
				waitForElementToBeVisible(driver
						.findElement(By.xpath("//div[contains(@id, 'header') and contains(text(), '" + optionUI + "')]")));
				AssertHelper.assertElementIsDisplayed(driver
						.findElement(By.xpath("//div[contains(@id, 'header') and contains(text(), '" + optionUI + "')]")));
				try {
					webdriverClick(driver.findElement(By.xpath(
							"//*[contains(@id, 'feeschedule') and contains(@id, 'header-inner')]/descendant::*[@class = 'x-tool-close']")));
					try {
						if (driver.findElement(By.xpath(
								"((//div[contains(@id,'messagebox')]//following::div[contains(@class,'x-box-item')]//following::span[contains(@id,'button')][contains(text(),'Cancel & Close')]//parent::button))[1]"))
								.isDisplayed()) {
							webdriverClick(driver.findElement(By.xpath(
									"((//div[contains(@id,'messagebox')]//following::div[contains(@class,'x-box-item')]//following::span[contains(@id,'button')][contains(text(),'Cancel & Close')]//parent::button))[1]")));
							Thread.sleep(200);
						}
					} catch (Exception e) {
						
					}
				} catch (Exception e) {
//					webdriverClick(driver.findElement(
//							By.xpath("(//div[contains(@class,'helplnk')])[2]//following::img[@class='x-tool-close']")));
					//shilpa xpath update 11.10.2023
					webdriverClick(driver.findElement(
							By.xpath("(//label[contains(@class,'helplnk')])//following::div[contains(@class,'x-tool-close')]")));
					Thread.sleep(200);
					try {
						webdriverClick(driver.findElement(By.xpath(
								"((//div[contains(@id,'messagebox')]//following::div[contains(@class,'x-box-item')]//following::span[contains(@id,'button')][contains(text(),'Cancel & Close')]//parent::button))[1]")));
						Thread.sleep(300);
					} catch (Exception e1) {
						System.out.println("Message not shown!!");
					}
				}
				waitUntilElementIsClickable(driver.findElement(By.name("pricemethodoption")));
				webdriverClick(driver.findElement(By.name("pricemethodoption")));
				Thread.sleep(400);
				waitForAjaxExtJs();
				ExtentReport.logPass("PASS", "test01Verify for : "+optionUI+"");
			}
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Verify", driver, e);
			fail(e.getMessage());
		}
	}

	public void setDropdownMenuValue(WebElement triggerDropdown, WebElement menuOptionsUl, String value,
			boolean printout) throws InterruptedException {
		waitForAjaxExtJs();
		webdriverClick(triggerDropdown);
		waitForAjaxExtJs();
		WebElement optionsUl;
		if (menuOptionsUl != null) {
			optionsUl = menuOptionsUl;
		} else {
			optionsUl = driver
					.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
		}
		if (printout) {
			System.out.println("menuOptionsUl: " + menuOptionsUl);
		}
		List<WebElement> options = optionsUl.findElements(By.tagName("li"));
		for (WebElement option : options) {
			if (option.getText().equals(value)) {
				if (printout) {
					System.out.println("Menu option set to: " + option);
				}
				option.click();
				break;
			}
		}
	}

	@AfterClass
	public static void endtest() {
		ExtentReport.report.flush();
	}
}
