package webdriver.scripts.calculations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

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

import ExtentReport.ExcelUtility;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatisticDataCalculations extends AzHelper {
	static DataMaintenanceMap dmMap;
	static ContractingMap contractMap;
	private static ReportingMap reportMap;
	static CostingMap costing;
	static String costModel = "0-MarinaCostModel";
	static String reportName = "Cost Accounting Income Statement";
	static String directory = "Reports";
	static String subDirectory = "Costing";
	static ModelLibraryMap modelMap;
	static int refreshTime = 10;
	static int retry=0;
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String[] statsDatafilter = { "Name", "Is", "Equal To", "150FY05Budget" };
	static String[] statsDataCalcFilter= {"Scenario Name", "Is", "Equal To", "150FY05Budget"};
	static String[] glDataScenarioFilter = { "Name", "Is", "Equal To", "MH FY05Budget" };
	static String[] glDataFilter = { "Department Code", "Is", "Equal To", "2015" };
	 Actions action=new Actions(driver);
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("StatisticDataCalculations", "webdriver.scripts.contracting", "StatisticDataCalculations");
    try {
    	dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    	reportMap = BuildMap.getInstance(driver, ReportingMap.class);
    	contractMap = BuildMap.getInstance(driver, ContractingMap.class);
    	modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    	costing=BuildMap.getInstance(driver, CostingMap.class);
		Login.loginUser("AutomationTesterAdmin");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "setupScript", driver, e);
	fail(e.getMessage());
	}
  }

//  @Test
  public void test01RunStatisticDataCalculation() throws Throwable, InterruptedException {
    try {
    	goToPage("Costing Models");
    	doClick(costing.getCostModelFilterButton());
		doFilterCreate(filter);
		tableDoubleClickCellFirstColumn(costModel);
		doClickTreeData("Prepare Data");
		doClickTreeData("Statistic Data Scenarios");
		doClickTreeItem("Statistic Data Calculation Scenarios");
		doClick(costing.statisticDataCalcScenarionFilterBtn());
		doFilterCreate(statsDatafilter);
		doClick(costing.statisticDataCalcScenarionCalculateBtn());
		doClick(ContractingMap.getCalculationStatusButtonFilter());
		doFilterCreate(statsDataCalcFilter);
		waitForFirstRowCalculationBarToReach100Percent();
		doClosePageOnLowerBar("Calculation Status");
		ExtentReport.logPass("PASS", "test01RunStatisticDataCalculation");
    	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "test01RunStatisticDataCalculation", driver, e);
		fail(e.getMessage());
    	}
  }
  @Test
  public void test02RunCostAccountingReport() throws Throwable, InterruptedException {
    try {
    	goToPage("Report Library");
		Actions action=new Actions(driver);

		waitForDisplayedSpinnerToEnd();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
		doClick(reportMap.getReportLibraryPageFormFieldSearch());
		doClick("//div[contains(text(),'" + directory
				+ "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory
				+ "')]");
		ContractModelsHelper.keyInValues(reportMap.getReportLibraryPageFormFieldSearch(), reportName);
		driver.findElement(By.linkText(reportName)).click();
//		for(int i=1;i<=2;i++) {
//			doClick("//div[text()='Entity Range']//following::a["+i+"]/parent::div");
//			driverDelay();
//			action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).sendKeys("150").sendKeys(Keys.ENTER).perform();
//			action.moveToElement(driver.findElement(By.xpath("(//div[text()='Marina Medical Center']/..//preceding-sibling::td)[1]"))).click().perform();
//			driverDelay(2000);
//			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
//			driverDelay(1000);
//			doJsClick(reportMap.reportLibraryPageEntityOkButton());
//		}
//		doClick("(//div[text()='Department Hierarchy']//following::td/div/a)[1]");
//		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).click().sendKeys("Marina").sendKeys(Keys.ENTER).perform();
//		action.moveToElement(driver.findElement(By.xpath("//div[text()='Marina Department Hierarchy']"))).click().perform();
//		driverDelay(2000);
////		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
////		driverDelay(1000);
//		doJsClick(reportMap.reportLibraryPageEntityOkButton());
//		
//		doClick("(//div[text()='Department or Department Group']//following::td/div/a)[1]");
//		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).sendKeys("2015").sendKeys(Keys.ENTER).perform();
//		action.moveToElement(driver.findElement(By.xpath("(//div[text()='2015  PEDIATRIC SUPPORT']/..//preceding-sibling::td)[1]"))).click().perform();
//		driverDelay(2000);
////		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
////		driverDelay(1000);
//		doJsClick(reportMap.reportLibraryPageEntityOkButton());
//		
//		doClick("(//div[text()='Account Hierarchy']//following::td/div/a)[1]");
//		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).sendKeys("Marina").sendKeys(Keys.ENTER).perform();
//		action.moveToElement(driver.findElement(By.xpath("//div[text()='Marina Account Hierarchy']"))).click().perform();
//		driverDelay(2000);
//		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
//		driverDelay(1000);
//		doJsClick(reportMap.reportLibraryPageEntityOkButton());
//		
//		doClick("(//div[text()='Account or Account Group']//following::td/div/a)[1]");
//		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).click().sendKeys("TOT").sendKeys(Keys.ENTER).perform();
//		action.moveToElement(driver.findElement(By.xpath("(//div[text()='TOTEXP GP Total Expense']/..//preceding-sibling::td)[1]"))).click().perform();
//		driverDelay(2000);
////		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
////		driverDelay(1000);
//		doJsClick(reportMap.reportLibraryPageEntityOkButton());
//		
//		doClick("(//div[text()='GL Data Scenario']//following::td/div/a)[1]");
//		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).click().sendKeys("MH").sendKeys(Keys.ENTER).perform();
//		action.moveToElement(driver.findElement(By.xpath("//div[text()='MH FY05Budget']"))).click().perform();
//		driverDelay(2000);
//		doJsClick(reportMap.reportLibraryPageEntityOkButton());
//		keyInInputText("04/01/2004", driver.findElement(By.xpath("(//div[text()='GL Data Set Date Range']//following::input[@class='gwt-DateBox'])[1]")));
//		keyInInputText("03/01/2005", driver.findElement(By.xpath("(//div[text()='GL Data Set Date Range']//following::input[@class='gwt-DateBox'])[2]")));
////		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
//		doClick(reportMap.reportLibraryPageEntityRunButton());
//		driverDelay(2000);
//		doClick("//div[text()='Run Report Without Saving']//following::button[text()='OK']");
//		driverDelay(2000);
//		String reportTime=driver.findElement(By.xpath("//div[@class='GJT013UBNJB']")).getText().replaceFirst("^\\s+", "");;
//		while (retry <= refreshTime) {
//			doClick(reportMap.reportLibraryPageEntityRefreshButton());
//			driverDelay(2500);
//
//			try {
//				//Shilpa:Update for 11.4 on 01.28.2026
//				String time=java.time.LocalDateTime.parse(""+reportTime+"", java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")).format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a"));
//				if(driver.findElement(By.xpath("//div[text()='"+time+"']//following::div[4]")).getText().contains("COMPLETED")){
//					assertElementTextWithXpath("//div[text()='"+time+"']//following::div[4]", "COMPLETED",
//							printout);
//					ExtentReport.logPass("PASS", "test02OpenCostingReport");
//					break;
//
//				}
//				
//			} catch (Exception e1) {
//				doClick(reportMap.reportLibraryPageEntityRefreshButton());
//				retry++;
//
//			}
//			if (retry == refreshTime) {
//				System.out.println("❌ Status did not become 'Completed' after retries.");
//				fail();
//			}
//
//		}

	} catch (Exception | AssertionError e) {
		ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e);
		fail(e.getMessage());
	}
    	ExtentReport.logPass("PASS", "test01RunStatisticDataCalculation");
	
	}

  @Test
  public void test03SelectEntity() throws Throwable, InterruptedException {
    try {
    	for(int i=1;i<=2;i++) {
			doClick("//div[text()='Entity Range']//following::a["+i+"]/parent::div");
			driverDelay();
			action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).sendKeys("150").sendKeys(Keys.ENTER).perform();
			action.moveToElement(driver.findElement(By.xpath("(//div[text()='Marina Medical Center']/..//preceding-sibling::td)[1]"))).click().perform();
			driverDelay(2000);
			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
			driverDelay(1000);
			doJsClick(reportMap.reportLibraryPageEntityOkButton());
		}
    	ExtentReport.logPass("PASS", "test04SelectEntity");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test04SelectEntity", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test04SelectDepartmentHierarchy() throws Throwable, InterruptedException {
    try {
    	doClick("(//div[text()='Department Hierarchy']//following::td/div/a)[1]");
		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).click().sendKeys("Marina").sendKeys(Keys.ENTER).perform();
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Marina Department Hierarchy']"))).click().perform();
		driverDelay(2000);
//		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
//		driverDelay(1000);
		doJsClick(reportMap.reportLibraryPageEntityOkButton());
    	ExtentReport.logPass("PASS", "test05SelectDepartmentHierarchy");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test05SelectDepartmentHierarchy", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test05SelectDepartmentGroup() throws Throwable, InterruptedException {
    try {
    	doClick("(//div[text()='Department or Department Group']//following::td/div/a)[1]");
		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).sendKeys("2015").sendKeys(Keys.ENTER).perform();
		action.moveToElement(driver.findElement(By.xpath("(//div[text()='2015  PEDIATRIC SUPPORT']/..//preceding-sibling::td)[1]"))).click().perform();
		driverDelay(2000);
//		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
//		driverDelay(1000);
		doJsClick(reportMap.reportLibraryPageEntityOkButton());
    	ExtentReport.logPass("PASS", "test06SelectDepartmentGroup");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test06SelectDepartmentGroup", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test06SelectAccountHierarchy() throws Throwable, InterruptedException {
    try {
    	doClick("(//div[text()='Account Hierarchy']//following::td/div/a)[1]");
		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).sendKeys("Marina").sendKeys(Keys.ENTER).perform();
		action.moveToElement(driver.findElement(By.xpath("//div[text()='Marina Account Hierarchy']"))).click().perform();
		driverDelay(2000);
		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
		driverDelay(1000);
		doJsClick(reportMap.reportLibraryPageEntityOkButton());
    	ExtentReport.logPass("PASS", "test07SelectAccountHierarchy");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test07SelectAccountHierarchy", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test07SelectAccountGroup() throws Throwable, InterruptedException {
    try {
    	doClick("(//div[text()='Account or Account Group']//following::td/div/a)[1]");
		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).click().sendKeys("TOT").sendKeys(Keys.ENTER).perform();
		action.moveToElement(driver.findElement(By.xpath("(//div[text()='TOTEXP GP Total Expense']/..//preceding-sibling::td)[1]"))).click().perform();
		driverDelay(2000);
//		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
//		driverDelay(1000);
		doJsClick(reportMap.reportLibraryPageEntityOkButton());
    	ExtentReport.logPass("PASS", "test07SelectAccountHierarchy");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test07SelectAccountHierarchy", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test08SelectGLDataScenario() throws Throwable, InterruptedException {
    try {
    	doClick("(//div[text()='GL Data Scenario']//following::td/div/a)[1]");
		action.moveToElement(driver.findElement(By.xpath("//input[@class='GJT013UBGIC']"))).click().sendKeys("MH").sendKeys(Keys.ENTER).perform();
		action.moveToElement(driver.findElement(By.xpath("//div[text()='MH FY05Budget']"))).click().perform();
		driverDelay(2000);
		doJsClick(reportMap.reportLibraryPageEntityOkButton());
    	ExtentReport.logPass("PASS", "test09SelectGLDataScenario");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test09SelectGLDataScenario", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test09SelectEntityDateRangeRunReport() throws Throwable, InterruptedException {
    try {
    	keyInInputText("04/01/2004", driver.findElement(By.xpath("(//div[text()='GL Data Set Date Range']//following::input[@class='gwt-DateBox'])[1]")));
		keyInInputText("03/01/2005", driver.findElement(By.xpath("(//div[text()='GL Data Set Date Range']//following::input[@class='gwt-DateBox'])[2]")));
//		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
		doClick(reportMap.reportLibraryPageEntityRunButton());
		driverDelay(2000);
		doClick("//div[text()='Run Report Without Saving']//following::button[text()='OK']");
		driverDelay(2000);
		String reportTime=driver.findElement(By.xpath("//div[@class='GJT013UBNJB']")).getText().replaceFirst("^\\s+", "");;
		while (retry <= refreshTime) {
			doClick(reportMap.reportLibraryPageEntityRefreshButton());
			driverDelay(2500);

			try {
				//Shilpa:Update for 11.4 on 01.28.2026
				String time=java.time.LocalDateTime.parse(""+reportTime+"", java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")).format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a"));
				if(driver.findElement(By.xpath("//div[text()='"+time+"']//following::div[4]")).getText().contains("COMPLETED")){
					doClick("(//span[text()='Cost Accounting Statement']//following::div[text()='"+time+"']//following::div[4]/a)[1]");
					assertElementTextWithXpath("//div[text()='"+time+"']//following::div[4]", "COMPLETED",
							printout);
					action.doubleClick(driver.findElement(By.xpath(""))).perform();
					ExtentReport.logPass("PASS", "test02OpenCostingReport");
					break;

				}
				
			} catch (Exception e1) {
				doClick(reportMap.reportLibraryPageEntityRefreshButton());
				retry++;

			}
			if (retry == refreshTime) {
				System.out.println("❌ Status did not become 'Completed' after retries.");
				fail();
			}

		}
    	ExtentReport.logPass("PASS", "test10SelectEntityDateRangeRunReport");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test10SelectEntityDateRangeRunReport", driver, e);
	fail(e.getMessage());
	}
}
  //  @Test
  public void test02OpenGLDataScenario() throws Throwable, InterruptedException {
    try {
    	goToPage("Maintain Data");
		selectMaintainDataAtoZ("GL Data Scenarios");
		doClick(DataMaintenanceMap.getazFilterBtn());
		doFilterCreate(glDataScenarioFilter);
		doClick(DataMaintenanceMap.getazEditBtn());
		doClickButtons("GL Data", "Filter");
		doFilterCreate(glDataFilter);
		for(int i=1;i<=driver.findElements(By.xpath("(//div[contains(@id,'glspreadsheetgrid')]//div[@class='x-grid-item-container'])[1]/table")).size();i++) {
			ExcelUtility.copyFromDownload("Cost_Accounting_Income_Statement.xlsx");
			String getGlAccount=driver.findElement(By.xpath("(//div[contains(@id,'glspreadsheetgrid')]//div[@class='x-grid-item-container'])[1]/table["+i+"]//td[4]")).getText();
			String totalValue=driver.findElement(By.xpath("(//div[contains(@id,'glspreadsheetgrid')]//div[@class='x-grid-item-container'])[1]/table[1]//following::div[contains(@class,'x-grid-scrollbar-clipper ')]//table["+i+"]//td[1]/div")).getText();
			String parseValue=totalValue.replaceAll("\\.00$", "").replaceAll(",", "");
			System.out.println(parseValue);
			System.out.println(totalValue);
			String excelPath = System.getProperty("user.dir")
	                + "\\TestFiles\\Cost_Accounting_Income_Statement.xlsx"; 
			String compareValue=ExcelUtility.searchFileAndFetchTheColValue(excelPath, getGlAccount);
			String compareTotalValue=compareValue.replaceAll(",", "");
			if(compareTotalValue.replaceAll(",", "").equals(parseValue)) {
				System.out.println(compareValue);
				System.out.println(parseValue);
				assertTrue(printout);
			}
			
		}
    	ExtentReport.logPass("PASS", "test01RunStatisticDataCalculation");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test01RunStatisticDataCalculation", driver, e);
	fail(e.getMessage());
	}
}
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
