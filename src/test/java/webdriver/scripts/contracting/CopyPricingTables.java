package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
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
	static String excelFilePath = System.getProperty("user.dir") + "//TestFiles//ExcelFiles";
	static String levelOfCareTable = "ASESC-2397 Message";
	static String ascScheme = "ASESC2255 C";
	static String perDiemServiceModel = "000 OP Per Diem RC 0921 (PD)";
	static String perChargeServiceModel = "ASESC-1633 Charge Default";
	static String[] filterPerDiemService = { "Name", "Is", "Equal To", perDiemServiceModel };
	static String[] filterChargeLevelService = { "Name", "Is", "Equal To", perChargeServiceModel };
	static String serviceModelNew;

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
			Arrays.stream(new File(excelFilePath).listFiles()).forEach(File::delete);
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
			existServiceName = ContractingMap.getselectServiceForContractModel().getText();
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
			List<WebElement> pastePricingTableList, int colIndex) throws IOException, Throwable {
		excelPath = excelFilePath + "//" + excelPath + ".xlsx";
		String copiedText = ExcelUtility.copyColumToExcel(tableRowList, excelPath);
		ExcelUtility.pasteClipboardTextToExcel(copiedText, excelPath);
		ExcelUtility.updateColumnWithRandomValuesAndCopy(excelPath, colIndex, 2.1, 12.5);// the values 2.1 12.5 is min
																							// and max range to
		// enter rates in the table
		List<String> columnValues = ExcelUtility.readSecondColumnValues(excelPath, colIndex);
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
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
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
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
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
			selectPricingMethod(ContractingMap.getpricingMethodLevelOfCare());
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
			doClick(ContractingMap.getlevelOfCareBtn());
			doDropdownSelectUsingOptionTextOnly(ContractingMap.getlevelOfCareDropDwn(), levelOfCareTable);
			List<String> columnValues = copyPasteFromExcelToRates("LevelOfCare",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test04_CopyPasteTable_LevelOfCare_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04_CopyPasteTable_LevelOfCare_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test05_MedicareCommercialASC_ADS_12496() throws Throwable {
		try {
			selectPricingMethod(ContractingMap.getpricingMethodMediCommAsc());
			navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
			doClick(ContractingMap.getascSchemeBtn());
			doDropdownSelectUsingOptionTextOnly(ContractingMap.getascSchemeDrpdwn(), ascScheme);
			navigateCloseGeneralSectionOpenNewSection("ASC Payment");
			List<String> columnValues = copyPasteFromExcelToRates("AscScheme", ContractingMap.getascSchemeList(),
					ContractingMap.getascRateTableList(), 2);
			openEditDialog();
			navigateCloseGeneralSectionOpenNewSection("ASC Payment");
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test05_MedicareCommercialASC_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05_MedicareCommercialASC_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test06_PercentOfCharge_ADS_12496() throws Throwable {
		try {
			selectPricingMethod(ContractingMap.getpricingMethodPercentOfCharge());
			List<String> columnValues = copyPasteFromExcelToRates("PercentOfCharge",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test06_PercentOfCharge_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06_PercentOfCharge_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test07_PerDiem_ADS_12496() throws Throwable {
		try {
			selectPricingMethod(ContractingMap.getpricingMethodPerDiem());
			List<String> columnValues = copyPasteFromExcelToRates("PerDiem",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test07_PerDiem_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07_PerDiem_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test08_DeptPerDiem_ADS_12496() throws Throwable {
		try {
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filterPerDiemService);
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + perDiemServiceModel + "']");
			selectPricingMethod(ContractingMap.getpricingMethodDeptPerDiem());
			List<String> columnValues = copyPasteFromExcelToRates("DeptPerDiem",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test08_DeptPerDiem_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08_DeptPerDiem_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test09_DeptPerDiemDayStay_ADS_12496() throws Throwable {
		try {
			selectPricingMethod(ContractingMap.getpricingMethodDeptPerDiemDayStay());
			List<String> columnValues = copyPasteFromExcelToRates("DeptPerDiemDayStay",
					ContractingMap.getascSchemeList(), ContractingMap.getdeptPerDiemRateTableList(), 1);
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test09_DeptPerDiemDayStay_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09_DeptPerDiemDayStay_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test10_RatePerProcedure_ADS_12496() throws Throwable {
		try {
			doClick(ContractingMap.getContractFeeForServicePaymentClearFilter());
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filterChargeLevelService);
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + perChargeServiceModel + "']");
			selectPricingMethod(ContractingMap.getpricingMethodRatePerProc());
			List<String> columnValues = copyPasteFromExcelToRates("RatePerProc",
					ContractingMap.getpricingTableCopyRowList(), ContractingMap.getpricingTableList(), 1);
			openEditDialog();
			assertPastedPricingValue(ContractingMap.getpricingTableList(), columnValues);
			closeEditDialog();
			columnValues.clear();
			ExtentReport.logPass("PASS", "test10_RatePerProcedure_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10_RatePerProcedure_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test11_DeleteContractModel_ADS_12496() throws Throwable {
		try {
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			doClick(ContractingMap.getCloseContractBtn());
			model.test02DeleteContractModel_ADS6435_ADS6412();
			ExtentReport.logPass("PASS", "test11_DeleteContractModel_ADS_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11_DeleteContractModel_ADS_12496", driver, e);
			fail(e.getMessage());

		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
