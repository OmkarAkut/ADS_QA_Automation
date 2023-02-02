package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceListCalculationScenario extends CalculationHelper {
		static DataMaintenanceMap dmMap;
		static ContractingMap contractMap;
		final static String batch="v10.2 REGRESSION PL Calc - Avg Enc Chgs1";
		final static String aTozPage = "Price Lists";
		final static String aTozPage1="Price List Calculation Scenarios";
		static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		static String priceList = "PRICE" + currentDateTime;
		static String priceListName=currentDateTime.replaceAll("\\W", "")+" "+priceList;
		static String chargeMaster="150 Marina Charge Master 2";
		static String[] filterPriceScenario= {"Name","Is","Equal To","v10.2 REGRESSION PL Calc - Avg Enc Chgs1"};
		static String[] filterPriceList= {"Name","Is","Equal To",priceList};
		static HashMap<String,String> filters = new HashMap<>();

		private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	

		/** Regression: Test script for ADS-6103 */
	  @BeforeClass
	  public static void setupScript() throws Exception,Throwable {
		  ExtentReport.reportCreate("GeneralCalculationsChargeItemServiceClassificationSchemeAds2342", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsChargeItemServiceClassificationSchemeAds2342");
	    try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
			fail(e.getMessage());
		} 
	  }
	  
	  @Test
	  public void test01AddNewPriceList() throws Throwable {
		  try {
			doClick(DataMaintenanceMap.getLoadDataNewButton());
			  ContractingMap.getMedicareCode().sendKeys(currentDateTime.replaceAll("\\W", ""));
			 ContractingMap.getInputName().sendKeys(priceList);
			 doDropdownSelectUsingOptionText(DataMaintenanceMap.getChargeMaster(), chargeMaster);
			 doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			  ExtentReport.logPass("PASS", "test01AddNewPriceList");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01AddNewPriceList", driver, e);
				fail(e.getMessage());
			}
	  }
	  
	  @Test
	  public void test02ChangeResultsDestinationPriceLists() throws Throwable {
		  try {
			selectMaintainDataAtoZ(aTozPage1);
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			driverDelay(100);
			doFilterCreate(filterPriceScenario);
			tableDoubleClickCellFirstColumn(batch);
			System.out.println(currentDateTime.replaceAll("\\W", "")+" "+priceList);
			doDropdownSelectUsingOptionText(DataMaintenanceMap.getPriceListMaster(), currentDateTime.replaceAll("\\W", "")+" "+priceList);
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
	  
	  @Test
	  public void test03ValidateCalculationStatus() throws Throwable {
		  try {
			  doClick(DataMaintenanceMap.getCalculateButton());
			  waitForPageTitle("Calculation Status");
			 
			  waitForFirstRowCalculationBarToReach100Percent();
			  calculationStatusPageOpenViewDialog();
			  driverDelay(600);
			  clickLastPageIconOnCalculationStatusViewLog();
				waitForSpinnerToEnd();
		      confirmCalculationStatusDetailsContains("Total number of price items inserted/updated: 6443");
		      confirmCalculationStatusDetailsContains("Total number of charge items processed: 4185640");

		      closeViewDialog();
//		      deleteMyCalculationStatusFirstRow();
		     
		      doClosePageOnLowerBar("Calculation Status");
		      doClick(ContractingMap.getContractFeeForServicePaymentSave());
			  ExtentReport.logPass("PASS", "test03ValidateCalculationStatus");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03ValidateCalculationStatus", driver, e);
				fail(e.getMessage());
			}
	  }
	  
	  @Test
	  public void test04OpenPriceList() throws Throwable {
		  try {
			selectMaintainDataAtoZ(aTozPage);
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			doFilterCreate(filterPriceList);
			tableDoubleClickCellFirstColumn(priceList);
			 Multimap<String, String> multimap = ArrayListMultimap.create();
			 Multimap<String, String> multimapValue = ArrayListMultimap.create();
			 multimap.put("Department Code","1111");
			 multimap.put("Charge Code","5465158");
			 multimap.put("Department Code","2016");
			 multimap.put("Charge Code","8460206");
			 multimap.put("Department Code","2111");
			 multimap.put("Charge Code","90118");
			 multimap.put("Department Code","2220");
			 multimap.put("Charge Code","117");
			 multimap.put("Department Code","2269");
			 multimap.put("Charge Code","8141517");
			 multimap.put("Department Code","2330");
			 multimap.put("Charge Code","7715139");
			 multimap.put("Department Code","3030");
			 multimap.put("Charge Code","2783033");
			 multimap.put("Department Code","4021");
			 multimap.put("Charge Code","8407066");
			
			 multimapValue.put("2016","482.35");
			 multimapValue.put("2111","118.26");
			 multimapValue.put("2220","684.72");
			 multimapValue.put("2269","106.59");
			 multimapValue.put("2330","248");
			 multimapValue.put("3030","145");
			 multimapValue.put("4021","25.84");
			 multimapValue.put("1111", "26.25");
			 
			 for (Map.Entry<String,String> entry : multimap.entries()) {
				 doClick(DataMaintenanceMap.getPriceItemFilterButton());

		            System.out.println("Key = " + entry.getKey() +
		                             ", Value = " + entry.getValue());
		            		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(),dialog.getFilterDialogDropdownField(), entry.getKey());
		            doClick(dialog.getFilterDialogFormFieldValue());
		            dialog.getFilterDialogFormFieldValue().sendKeys(entry.getValue());
		            waitForAjaxExtJs();
				    waitUntilElementIsClickable(dialog.getFilterDialogButtonAdd());
				    doClick(dialog.getFilterDialogButtonAdd());
				    waitForAjaxExtJs();
				    doClick(dialog.getFilterDialogButtonApplyFilter());
				    waitForSpinnerToEnd();
				    String code=DataMaintenanceMap.getPriceItemDeptCode().getText();
				    for (Map.Entry<String,String> entryValue : multimapValue.entries()) {
			            System.out.println("Key = " + entryValue.getKey() +
			                             ", Value = " + entryValue.getValue());
						if (code.equals("1111")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"
											+ code + "']//following::td[6]/div")),
									"26.25", printout);
							break;
						}
						if (code.equals("4021")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"
											+ code + "']//following::td[6]/div")),
									"25.84", printout);
							break;
						}
						if (code.equals("2016")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"
											+ code + "']//following::td[6]/div")),
									"482.35", printout);
							break;
						}
						if (code.equals("3030")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"
											+ code + "']//following::td[6]/div")),
									"145", printout);
							break;
						}
						if (entryValue.getKey().equals("2330")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"
											+ entryValue.getKey() + "']//following::td[6]/div")),
									"25.84", printout);
							break;
						}
						if (code.equals("2269")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"+code+"']//following::td[6]/div")),
									"106.59", printout);
							break;
						}
						if (code.equals("2220")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"+code+"']//following::td[6]/div")),
									"684.72", printout);
							break;
						}
						if (code.equals("2111")) {
							assertElementText(driver.findElement(By.xpath(
									"//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[5]/div[text()='"+code+"']//following::td[6]/div")),
									"118.26", printout);
							break;
						}
				 }  
					   doClick(DataMaintenanceMap.getPriceItemClearFilterButton());

		    }
			
			 

				 
				 
			 ExtentReport.logPass("PASS", "test03ValidateCalculationStatus");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03ValidateCalculationStatus", driver, e);
				fail(e.getMessage());
			}
	  }
}
