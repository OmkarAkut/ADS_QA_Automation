package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelpLinksContractingEditIppsUi2020Ads2525 extends PageTestHelper {

	private static ContractingMap contractingMap;
	private static GeneralElementsMap generalElement;
	private static ModelLibraryMap modelLibrary;
	private static final String contractModel = "AFT IPPS 2020 Defaults - DoNotModify";
	private static final String serviceModel = "OPPS 2019";

	/**
	 * This scripts verifies that the help link on each page of the application
	 * links to the user help guide and the appropriate help page in the guide
	 * displays correctly.
	 * 
	 * @throws Throwable
	 */
	@BeforeClass
	public static void setupScript() throws Throwable {

		ExtentReport.reportCreate("HelpLinksContractingEditIppsUi2020Ads2525", "webdriver.globalscripts.help",
				"HelpLinksContractingEditIppsUi2020Ads2525");

		try {
			contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			System.out.println("TEST CLASS: " + HelpLinksContractingEditIppsUi2020Ads2525.class.getSimpleName());
			loginUser(Users.ApplicationAdministrator1);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testContractingTabContractModelsPage01HelpLinkTest() throws Throwable {
		try {
			goToPage("Contract Models");
			waitForSpinnerToEnd();
			testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'mlfd.htm')]")), "Model Library",
					printout);
//			doClosePageOnLowerBar("Model Library");
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "testContractingTabContractModelsPage01HelpLinkTest");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testContractingTabContractModelsPage01HelpLinkTest", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testContractingTabContractModelsPage02FeeForServicePaymentTermsPageHelpLink() throws Throwable {
		try {
			goToPage("Contract Models");
			waitForSpinnerToEnd();
			navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
			testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'cnffsfd.htm')]")),
					"Fee for Service Payment Terms", printout);
			ExtentReport.logPass("PASS", "testContractingTabContractModelsPage02FeeForServicePaymentTermsPageHelpLink");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testContractingTabContractModelsPage02FeeForServicePaymentTermsPageHelpLink",
					driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testContractingTabContractModelsPage03EditPriceDialogHelpLink() throws Throwable {
		try {
			navigateToFeeForServicePaymentTermsPagePricingMethodSectionEditPriceDialog(serviceModel);
			waitForAjaxExtJs();
			Thread.sleep(1000);
			
			/*Omkar 29/03/2023 : Xpath change for 11.2
			testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'cnpmippsfd.htm')]")),
					"Medicare Inpatient PPS Pricing Method", printout); */
//			doClick("//div[text()='Pricing Method']//following::span[text()='"+serviceModel+"']");
//			Thread.sleep(500);
//			doClick("//div[text()='Pricing Method']//following::span[text()='Edit'][1]");
//			waitForElementPresence("//*[contains(@onclick, 'cnffsfd.htm')]");
			testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'cnpmippsfd.htm')]")),
					 "Medicare Inpatient PPS Pricing Method", printout);
			ExtentReport.logPass("PASS", "testContractingTabContractModelsPage03EditPriceDialogHelpLink");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testContractingTabContractModelsPage03EditPriceDialogHelpLink", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testContractingTabContractModelsPage04DischargeStatusCodeForTransfersDialogHelpLink() throws Throwable {
		try {
			Thread.sleep(1000);
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			Thread.sleep(1000);
			testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'selectorfd.htm')]")), "Selector",
					printout);
			driver.findElement(
					By.xpath("//div[contains(@id,'dynamicwindow')]/descendant::*[contains(@class,'x-tool-close')]"))
					.click();
//			doClosePageOnLowerBar("AFT IPPS 2020...");
			doClick("//span[contains(text(),'AFT IPPS 2020...')]//following::span[@class='x-tab-close-btn']");

			ExtentReport.logPass("PASS",
					"testContractingTabContractModelsPage04DischargeStatusCodeForTransfersDialogHelpLink");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL",
					"testContractingTabContractModelsPage04DischargeStatusCodeForTransfersDialogHelpLink", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
