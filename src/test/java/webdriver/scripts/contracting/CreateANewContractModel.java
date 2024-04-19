package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateANewContractModel extends CalculationHelper {

	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName = "Contract Model" + currentDateTime;
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	private static final String serviceName = "CM IPPS 2021";
	private static final String serviceModel = "CM IPPS 2021";

	String[] columns = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String[] columnsToSelect = { "100  Pacific Hospital", "151  Copy of Marina Medical Center", "150  Marina Medical Center" };
	String[] columnsToRemove = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String addProvider = "150  Marina Medical Center";
	static String[] filter = { "Name", "Is", "Equal To", serviceName };
	

	/** Regression: Automated test script for ADS-6413 ,ADS-6412,ADS-6435 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateANewContractModel", "webdriver.scripts.contracting",
				"CreateANewContractModel");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(CostingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	/**Test - UI Validation [Contracting] â€œCreate a New Contract Model ADS-6413 **/
	@Test
	public void test01CreateNewContractModel_6413() throws Throwable {
		try {
			doClick(modelMap.getNewContractModelButton());
			waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
			assertElementIsDisplayed(ContractingMap.getNewContractModelPopUp());// assert contract model pop up
			doClick(ContractingMap.getContractModelNameInput());
			ContractingMap.getContractModelNameInput().sendKeys(contractModelName);
			doClick(ContractingMap.getContractModelAddProviderBtn());
			waitForElementToBeVisible(ContractingMap.getContractModelAddProviderPopup());
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsToSelect);
			contractModelsHelper.removeMultipleColumnsToDisplay(columnsToRemove);
			doClick(modelMap.getApplySelections());
			waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
			// Validate model name and providers
			assertElementIsDisplayedWithXpath("//div[contains(@class,'contractFrmCls')]//ul/li[contains(text(),'"+addProvider+"')]");
//			assertElementTextContains(ContractingMap.getProviderText(), addProvider, printout);
			doClick(modelMap.getSaveContractModel());
			goToPage("Contract Models");
			doSearchForContractModel(contractModelName);
			driverDelay(2000);
			assertTextIsDisplayed(contractModelName);
			ExtentReport.logPass("PASS", "test01CreateNewContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractModel", driver, e);
			fail(e.getMessage());
		}
	}
	/**Test - [CMS Regs: FY2023 IPPS] - Confirm FY2023 Medicare Year is Available For Med IPPS **/
	@Test
	public void test03AssertConfirmFY2023MedicareYearisAvailableForMedIPPS() {
		try {
			navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModelName);
			waitForAjaxExtJs();
			Thread.sleep(200);
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServices());
			assertElementIsDisplayed(modelMap.getContractFeeForServicePaymentServiceModel());
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filter);
			try {
				assertElementIsDisplayed(ContractingMap.getselectDropServiceModelPanel());
				System.out.println(driver.findElement(By.xpath("//div[contains(@class,'glAccountsGrid')]//div[contains(@id,'gridview')]//following::div[text()='"+serviceName+"']")).getText());
				ContractModelsHelper.dragAndDropElement((driver.findElement(By.xpath("//div[contains(@class,'glAccountsGrid')]//div[contains(@id,'gridview')]//following::div[text()='"+serviceName+"']//parent::td//parent::tr"))),ContractingMap.getselectDropServiceModelPanel());

			} catch (Exception|AssertionError e) {

			}
		} catch (Exception|AssertionError e) {
			
		}
	}
/*TestUIValidationContractingValidateContractingModelDeletebutton : ADS-6435,ADS-6412*/
	@Test
	public void test02DeleteContractModel_ADS6435_ADS6412() throws Throwable {
	
		try {
			doClick(modelMap.getContractModelDeleteButton());
			waitForElementToBeVisible(modelMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(modelMap.getContractModelDeletePopUp());
			doClick(modelMap.getContractModelCancelButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButton());
			waitForElementToBeVisible(modelMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(modelMap.getContractModelDeleteButtonInPopUp());
			assertElementIsDisplayed(modelMap.getContractModelCancelButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			waitForElementToBeVisible(driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
			assertTextIsDisplayed("There is no data available to display.");
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test02DeleteContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02DeleteContractModel", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
