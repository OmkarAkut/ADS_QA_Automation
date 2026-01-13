package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.DataMaintenanceHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceListCalculationScenario extends CalculationHelper {
	static DataMaintenanceMap dmMap;
	static ContractingMap contractMap;
	final static String batch1 = "v10.2 REGRESSION PL Calc - Avg Enc Chgs1";
	final static String batch2 = "v10.2 REGRESSION PL Calc - Avg Enc Chgs2";
	final static String batch3 = "v10.2 REGRESSION PL Calc - Avg Enc Chgs3";
	final static String aTozPage1 = "Price Lists";
	final static String aTozPage2 = "Price List Calculation Scenarios";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String priceList = "PRICE" + currentDateTime;
	static String priceListName = currentDateTime.replaceAll("\\W", "") + " " + priceList;
	static String chargeMaster = "150 Marina Charge Master 2";
	static String[] filterPriceScenario1 = { "Name", "Is", "Equal To", batch1 };
	static String[] filterPriceScenario2 = { "Name", "Is", "Equal To", batch2 };
	static String[] filterPriceScenario3 = { "Name", "Is", "Equal To", batch3 };
	static String startDate="01/01/2004";
	static String endDate="01/01/2024";
	static String[] filterCalcScenario1= {"Scenario Name", "Is", "Equal To", batch1};
	static String[] filterCalcScenario2= {"Scenario Name", "Is", "Equal To", batch2};
	static String[] filterCalcScenario3= {"Scenario Name", "Is", "Equal To", batch3};
	static String[] filterPriceList = { "Name", "Is", "Equal To", priceList };
	static HashMap<String, String> filters = new HashMap<>();
	DataMaintenanceHelper helper = new DataMaintenanceHelper();
	/** Regression: Test script for ADS-6103 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PriceListCalculationScenario",
				"webdriver.scripts.regression.generalcalculations",
				"PriceListCalculationScenario");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage1);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6103 all steps
	@Test
	public void test01AddNewPriceList_ADS_6103() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getLoadDataNewButton());
			ContractingMap.getMedicareCode().sendKeys(currentDateTime.replaceAll("\\W", ""));
			ContractingMap.getInputName().sendKeys(priceList);
			doDropdownSelectUsingOptionText(DataMaintenanceMap.getChargeMaster(), chargeMaster);
			driverDelay(200);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			ExtentReport.logPass("PASS", "test01AddNewPriceList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddNewPriceList", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02ValidateBatch1PriceValues_6103() throws Throwable {
		try {
			ChangeResultsDestinationPriceLists(aTozPage2, batch1, filterPriceScenario1);
//			ValidateCalculationStatus("10081", "9471569",filterCalcScenario1);
			ValidateCalculationStatus("794", "83440",filterCalcScenario1);
//			helper.FilterByChargeCode(aTozPage1, filterPriceList, priceList, DataMaintenanceMap.getPriceItemDeptCode(),
//					"26.25", "482.35", "1,121.86", "684.72", "106.59", "248", "145", "25.84");
			helper.FilterByChargeCode(aTozPage1, filterPriceList, priceList, DataMaintenanceMap.getPriceItemDeptCode(),
					"21.66", "67.10", "24.00", "128.10", "827.40", "208.95", "38.85", "68.25");
			doClick(ContractingMap.getFeeForPaymentCancelClose());
			ExtentReport.logPass("PASS", "test02ValidateBatch1PriceValues");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateBatch1PriceValues", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ValidateBatch2PriceValues_ADS_6103() throws Throwable {
		try {
			ChangeResultsDestinationPriceLists(aTozPage2, batch2, filterPriceScenario2);
//			ValidateCalculationStatus("6443", "4185640");
//			helper.FilterByChargeCode(aTozPage1, filterPriceList, priceList, DataMaintenanceMap.getPriceItemDeptCode(),
//					"26.25", "482.35", "1,121.86", "684.72", "86.57", "277.2", "136", "25.84");
//			ValidateCalculationStatus("10081", "9471569",filterCalcScenario2);
			ValidateCalculationStatus("10084", "9471752",filterCalcScenario2);
			helper.FilterByChargeCode(aTozPage1, filterPriceList, priceList, DataMaintenanceMap.getPriceItemDeptCode(),
					"21.66", "67.10", "24.00", "128.10", "29.40", "208.95", "38.85", "68.25");
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateBatch2PriceValues");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateBatch2PriceValues", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04ValidateBatch3PriceValues_ADS_6103() throws Throwable {
		try {
			ChangeResultsDestinationPriceLists(aTozPage2, batch3, filterPriceScenario3);
//			ValidateCalculationStatus("6443", "4185640");
//			helper.FilterByChargeCode(aTozPage1, filterPriceList, priceList, DataMaintenanceMap.getPriceItemDeptCode(),
//					"125.00", "448.5", "954.89", "752.11", "96.59", "238", "135", "124.61");
//			ValidateCalculationStatus("10081", "9471569",filterCalcScenario3);
			ValidateCalculationStatus("10084", "9471752",filterCalcScenario2);
			helper.FilterByChargeCode(aTozPage1, filterPriceList, priceList, DataMaintenanceMap.getPriceItemDeptCode(),
					"21.66", "67.10", "24.00", "128.10", "29.40", "208.95", "38.85", "68.25");
			ExtentReport.logPass("PASS", "test04ValidateBatch3PriceValues");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateBatch3PriceValues", driver, e);
			fail(e.getMessage());
		}
	}

	public void ChangeResultsDestinationPriceLists(String atoZpage, String batch, String[] filterPriceScenario)
			throws Throwable {
		try {
			selectMaintainDataAtoZ(atoZpage);
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			driverDelay(100);
			doFilterCreate(filterPriceScenario);
			tableDoubleClickCellFirstColumn(batch);
			
			String priceName=currentDateTime.replaceAll("\\W", "") + " " + priceList;
			System.out.println(currentDateTime.replaceAll("\\W", "") + " " + priceList);
			for(int i=0;i<=5;i++) {
				doClick(DataMaintenanceMap.getPriceListMaster());
				try {
					if(driver.findElement(By.xpath("//div[contains(@class,'floating')]//div[contains(@id,'listWrap')]/ul")).isDisplayed()) {
						break;
					}
				} catch (Exception e) {
					continue;
				}
			}
//			doDropdownSelectUsingOptionText(DataMaintenanceMap.getPriceListMaster(),
//					priceName);
			WebElement list = driver.findElement(By.xpath("//div[contains(@class,'floating')]//div[contains(@id,'listWrap')]/ul"));
			
			List<WebElement> menu = list.findElements(By.tagName("li"));
			System.out.println(menu.size());
			for(WebElement option : menu) {
				ContractModelsHelper.scrollToView(option);
				System.out.println(option.getText());
				if(option.getText().equals(priceName)) {

					option.click();
					break;
				}
			}
			
			Actions act=new Actions(driver);
			ContractModelsHelper.scrollToView("//label[text()='Discharge']//preceding::span[contains(@id,'checkbox')]/input[@name='useDischargeTimePeriod']");
			keyInDates(startDate,ContractingMap.getDischargeDateFrom());
//			keyInDates(endDate,ContractingMap.getDischargeDateTo());
			ContractModelsHelper.scrollToView(DataMaintenanceMap.getLogLoc());
			DataMaintenanceMap.getLogLoc().clear();
			DataMaintenanceMap.getLogLoc().sendKeys(priceListName);
			doClick(ContractingMap.getSaveBenefitPlan());
			ExtentReport.logPass("PASS", "test02ChangeResultsDestinationPriceLists");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ChangeResultsDestinationPriceLists", driver, e);
			fail(e.getMessage());
		}
	}

	public void ValidateCalculationStatus(String priceVal, String chargeVal,String[] calcScenario) throws Throwable {
		try {
			doClick(DataMaintenanceMap.getCalculateButton());
//			doClick(DataMaintenanceMap.getSaveContinueButton());
			waitForPageTitle("Calculation Status");
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(calcScenario);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay(600);
			clickLastPageIconOnCalculationStatusViewLog();
			waitForSpinnerToEnd();
			driverDelay(2000);
			checkForRecordsProcessed("Total number of price items inserted/updated: " + priceVal + "");
			checkForRecordsProcessed("Total number of charge items processed: " + chargeVal + "");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getFeeForPaymentSaveClose());
			ExtentReport.logPass("PASS", "test03ValidateCalculationStatus");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateCalculationStatus", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
