package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ExtentReport.ExcelUtility;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CopyPricingTables extends GoHelper {
	private static ContractingMap modelMap;
	static String modelName;
	static String contractModelName;
	static String currentDateTime = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	CreateANewContractModel model = new CreateANewContractModel();
	static String existServiceName;
	static Actions actions = new Actions(driver);
	static String excelFilePath = System.getProperty("user.dir") + "//TestFiles";

	/** Support Cases: Automated test script for ADS-5094 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CopyPricingTables", "webdriver.scripts.contracting", "CopyPricingTables");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			System.out.println("Test Class: " + CopyPasteButtons.class.getSimpleName());
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

	@Test
	public void test01AddService_12496() throws Throwable {
		try {
			model.test01CreateNewContractModel_6413();
			contractModelName = CreateANewContractModel.contractModelName;
			ValidateDragDropAddNewServiceUnderPricing.searchContractModelOpenTaskList(contractModelName);
			existServiceName = driver.findElement(By.xpath(
					"(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[1]"))
					.getText();
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			WebElement sourceAfter = driver.findElement(By.xpath("(//span[text()='" + existServiceName + "'])[1]"));
			WebElement targetAfter = driver.findElement(By.xpath("(//div[@class='x-grid-item-container'])[4]"));
			CimHelper.dragAndDrop(sourceAfter, targetAfter);

			ExtentReport.logPass("PASS", "test01AddService_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddService_12496", driver, e);
			fail(e.getMessage());

		}
	}

	public void assertPastedPricingValue(List<WebElement> elements, List<String> rates) {
		int count = Math.min(elements.size(), rates.size());
		for (int i = 0; i < count; i++) {
			WebElement input = ContractingMap.getpricingTableList().get(i);
			System.out.println(input);
			String value = rates.get(i);
			System.out.println(value);
			assertEqualsString(input.getText(), value);
		}
	}

	public void selectPricingMethod(WebElement element) throws Throwable {
		webdriverClick(driver.findElement(By.name("pricemethodoption")));
		waitForAjaxExtJs();
		webdriverClick(element);
		Thread.sleep(200);
		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
		assertElementIsDisplayed(modelMap.getContractEditPricePopUp());
	}

	public static List<String> copyPasteFromExcelToRates(String excelPath, List<WebElement> tableRowList,
			List<WebElement> pastePricingTableList) throws IOException, Throwable {
		excelPath = excelFilePath + "//" + excelPath + ".xlsx";
		String copiedText = ExcelUtility.copyColumToExcel(tableRowList, excelPath);
		ExcelUtility.pasteClipboardTextToExcel(copiedText, excelPath);
		ExcelUtility.updateSecondColumnAndCopy(excelPath, 2.1, 12.5);// the values 2.1 12.5 is min and max range to
																		// enter rates in the table
		List<String> columnValues = ExcelUtility.readSecondColumnValues(excelPath);
		ExcelUtility.sendValuesToInputs(pastePricingTableList, columnValues);
		return columnValues;
	}

	public static void openEditDialog() {
		doClick(modelMap.getContractModelRiskLimiterContinueCloseBtn());
		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
	}

	public static void closeEditDialog() {
		doClick(ContractingMap.geteditPriceCancelCloseBtn());
		doClick(ContractingMap.getWarningCancelCloseBtn());
	}

	@Test
	public void test02_CopyPasteTable_CaseRate_ADS_12496() throws Throwable {
		try {
			navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(existServiceName);
			selectPricingMethod(ContractingMap.getpricingMethodCaseRate());
			List<String> columnValues = copyPasteFromExcelToRates("CaseRate",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList());
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test02_CopyPasteTable_CaseRate_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02_CopyPasteTable_CaseRate_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test03_CopyPasteTable_CaseRateLengthStay_ADS_12496() throws Throwable {
		try {
			selectPricingMethod(ContractingMap.getpricingMethodCaseRateLengthStay());
			List<String> columnValues = copyPasteFromExcelToRates("CaseRateLengthStay",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList());
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test03_CopyPasteTable_CaseRateLengthStay_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03_CopyPasteTable_CaseRateLengthStay_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test04_CopyPasteTable_LevelOfCare_ADS_12496() throws Throwable {
		try {
			
			ExtentReport.logPass("PASS", "test03_CopyPasteTable_CaseRateLengthStay_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03_CopyPasteTable_CaseRateLengthStay_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}
	
}
