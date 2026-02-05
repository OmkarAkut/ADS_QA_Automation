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
	static String reportDownloadName="Cost_Accounting_Income_Statement.xlsx";
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

  @Test
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
		doClick(costing.getstatisticDataCalcScenarionMainCalculateBtn());
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
		driverDelay(2500);
	} catch (Exception | AssertionError e) {
		ExtentReport.logFail("FAIL", "test02OpenCostingReport", driver, e);
		fail(e.getMessage());
	}
    	ExtentReport.logPass("PASS", "test01RunStatisticDataCalculation");
	
	}

  @Test
  public void test03SelectEntity() throws Throwable, InterruptedException {
    try {
    	driverDelay(2000);
    	for(int i=1;i<=2;i++) {
			doClick("//div[text()='Entity Range']//following::a["+i+"]/parent::div");
			driverDelay(500);
			action.moveToElement(reportMap.getreportDialogSerachbox()).sendKeys("150").sendKeys(Keys.ENTER).pause(500).perform();
			action.moveToElement(reportMap.getreportEntity()).click().perform();
			driverDelay();
			ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
			driverDelay();
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
    	doClick(reportMap.getreportDeptHierarchy());
		action.moveToElement(reportMap.getreportDialogSerachbox()).click().sendKeys("Marina").sendKeys(Keys.ENTER).pause(500).perform();
		action.moveToElement(reportMap.getreportHierarchy()).click().perform();
		driverDelay();
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
    	doClick(reportMap.getreportDeptGroup());
		action.moveToElement(reportMap.getreportDialogSerachbox()).sendKeys("2015").sendKeys(Keys.ENTER).pause(500).perform();
		action.moveToElement(reportMap.getreportDeptGroupName()).click().perform();
		driverDelay();
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
    	doClick(reportMap.getreportAcctHierarchy());
		action.moveToElement(reportMap.getreportDialogSerachbox()).sendKeys("Marina").sendKeys(Keys.ENTER).pause(500).perform();
		action.moveToElement(reportMap.getreportAccountHierarchy()).click().perform();
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
    	doClick(reportMap.getreportAcctGroup());
		action.moveToElement(reportMap.getreportDialogSerachbox()).click().sendKeys("TOT").sendKeys(Keys.ENTER).pause(500).perform();
		action.moveToElement(reportMap.getreportAcctGroupName()).click().perform();
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
    	doClick(reportMap.getreportglDataScenario());
		action.moveToElement(reportMap.getreportDialogSerachbox()).click().sendKeys("MH").sendKeys(Keys.ENTER).pause(500).perform();
		action.moveToElement(reportMap.getreportglDataScenarioName()).click().perform();
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
    	keyInInputText("04/01/2004", reportMap.getreportglDataScenarioFrom());
		keyInInputText("03/01/2005", reportMap.getreportglDataScenarioTo());
//		ContractModelsHelper.scrollToView(reportMap.reportLibraryPageEntityOkButton());
		doClick(reportMap.reportLibraryPageEntityRunButton());
		driverDelay(2000);
		doClick(reportMap.getreportwarningPopUp());
		driverDelay(2000);
		String reportTime=reportMap.getreportTime().getText().replaceFirst("^\\s+", "");;
		
		for(int i=1;i<=refreshTime;i++) {
			
			try {
				//Shilpa:Update for 11.4 on 01.28.2026
				doClick(reportMap.reportLibraryPageEntityRefreshButton());
				driverDelay(2500);
				String time=java.time.LocalDateTime.parse(""+reportTime+"", java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")).format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a"));
				if(driver.findElement(By.xpath("//div[text()='"+time+"']//following::div[4]/a")).getText().contains("COMPLETED")){
					doClick("(//span[text()='Cost Accounting Income Statement']//following::div[text()='"+time+"']//following::div[4]/a)[1]");
					break;

				}
				
				if(driver.findElement(By.xpath("//div[text()='"+time+"']//following::div[4]/a")).getText().contains("FAILED")){
					break;
					

				}
				if(driver.findElement(By.xpath("//div[text()='"+time+"']//following::div[4]/a")).getText().contains("PENDING")){
					doClick(reportMap.reportLibraryPageEntityRefreshButton());
					driverDelay(2500);
					if(i==refreshTime) {
						System.out.println("X Status Failed after retries.");
						break;
					}
					

				}
				if(driver.findElement(By.xpath("//div[text()='"+time+"']//following::div[4]/a")).getText().contains("RUNNING")){
					doClick(reportMap.reportLibraryPageEntityRefreshButton());
					driverDelay(2500);
					if(i==refreshTime) {
						System.out.println("X Status Failed after retries.");
						break;
					}
					

				}
				
			} catch (Exception e1) {
				

			}
			i++;
		}
    	ExtentReport.logPass("PASS", "test10SelectEntityDateRangeRunReport");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test10SelectEntityDateRangeRunReport", driver, e);
	fail(e.getMessage());
	}
}
  @Test
  public void test10DownloadReport() throws Throwable, InterruptedException {
    try {
    	driverDelay();
    	driver.switchTo().frame(reportMap.getreportFrame());
		driver.findElement(By.id("IconImg_CostAccountingIncomeStatement_toptoolbar_export")).click();
		driver.findElement(By.id("IconImg_Txt_iconMenu_icon_CostAccountingIncomeStatement_exportUI_dialog_combo")).click();
		driver.findElement(By.xpath("//span[text()='Microsoft Excel Workbook Data-only']")).click();
		doClick("//a[text()='Export']");
		driver.switchTo().defaultContent();
		doClosePageOnLowerBar("Report Library");
		doClosePageOnLowerBar("Costing Models");
    	ExtentReport.logPass("PASS", "test10DownloadReport");
	} catch (Exception|AssertionError e) {
ExtentReport.logFail("FAIL", "test10DownloadReport", driver, e);
	fail(e.getMessage());
	}
}
    @Test
  public void test11OpenGLDataScenario() throws Throwable, InterruptedException {
    try {
    	goToPage("Maintain Data");
		selectMaintainDataAtoZ("GL Data Scenarios");
		doClick(DataMaintenanceMap.getazFilterBtn());
		doFilterCreate(glDataScenarioFilter);
		doClick(DataMaintenanceMap.getazEditBtn());
		doClickButtons("GL Data", "Filter");
		doFilterCreate(glDataFilter);
		ExcelUtility.copyFromDownload("Cost_Accounting_Income_Statement.xlsx");

		for(int i=1;i<=DataMaintenanceMap.getglDataScenarioElements().size();i++) {
			String getGlAccount=driver.findElement(By.xpath("(//div[contains(@id,'glspreadsheetgrid')]//div[@class='x-grid-item-container'])[1]/table["+i+"]//td[4]")).getText();
			String totalValue=driver.findElement(By.xpath("(//div[contains(@id,'glspreadsheetgrid')]//div[@class='x-grid-item-container'])[1]/table[1]//following::div[contains(@class,'x-grid-scrollbar-clipper ')]//table["+i+"]//td[1]/div")).getText();
			String parseValue=totalValue.replaceAll("\\.00$", "").replaceAll(",", "");
			System.out.println(parseValue);
			System.out.println(totalValue);
			String excelPath = System.getProperty("user.dir")
	                + "\\TestFiles\\'"+reportDownloadName+"'"; 
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
