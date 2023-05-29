package webdriver.helpers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.utilities.JavaDataBaseConnectivity;

public class CalculationHelper extends GoHelper {

	private static JavaDataBaseConnectivity jdbc;
	static DataMaintenanceMap dmMap;
	private static ModelLibraryMap modelMap;
	@SuppressWarnings("unused")
	private static EditContractingModelMap editModelMap;
	public String calculationStatusDeleteFirstRowDeleteButtonXpath =
			"//table/tbody/tr[2]/td[21]/div/div/em/button/span[2]";

	public String oldCalculationStatusFirstRowDeleteButtonXpath =
			"//div[contains(@id,'calculationstatus') and contains(@class,'x-box-layout-ct')]/descendant::table/tbody/tr[2]/td[16]/descendant::button";

	/** Helper Class for OPPS 2019 regression scripts - individual test scripts should extend this one to use it. */
	@BeforeClass
	public static void setupHelper() {
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		jdbc = new JavaDataBaseConnectivity(setupDb(testEnvironment));
	}

	public static String getVersion() {
		String version = null;
		if (testEnvironment.toLowerCase().contains("evolve") || testEnvironment.toLowerCase().contains("edge")|| testEnvironment.toLowerCase().contains("qa3")||testEnvironment.toLowerCase().contains("ads11")||testEnvironment.toLowerCase().contains("qastage")) {
			version = "v104";
		} else {
			version = "v103";
		}

		return version;
	}

	public String getCalculationStatusMyStatusFirstRow() {
		String status = getWebElement("//table/tbody/tr[2]/td[10]/div").getText();
		return status;
	}

	public void clickLastPageIconOnCalculationStatusViewLog() {
		//	Omkar 17/04/2023 : Xpath changes to click the last page button  
		//    String xpath = "//div[4]/div/div/div[2]/div/div/div[7]/em/button[@aria-label='Last Page']";
		String xpath = "(//span[contains(@class,'tbar-last-button')])[3]";
		try {
			waitForPresenceOfElement(xpath);
			driver.findElement(By.xpath(xpath)).click();
		} catch (Throwable e) {
		}
	}

	public static void filterAndSelectContractModelFromContractModelLibrary(String contractModel) throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
		modelMap.getModelLibraryButtonSearch().click();
		waitForAjaxExtJs();
		Thread.sleep(1000);
		//    Omkar 13/4/2023 : change in xpath for 11.2
		//    driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
		driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
	}

	public void assertCalculationStatusMyStatusFirstRowIsCompleted() {
		String status = getWebElement("//div[2]/div[2]/div/table/tbody/tr[2]/td[5]/div").getText();
		assertThat(status, containsString("Completed"));
	}

	public void assertCalculationStatusMyStatusFirstRowIsNotFailed() {
		String status = getWebElement("//div[2]/div[2]/div/table/tbody/tr[2]/td[5]/div").getText();
		if (status.contains("Failed")) {
			fail("Calculation Status = Failed");
		}
	}

	public String getCalculationStatusMyStatusFirstRowStatusCellText() {

		String status = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[10]/div").getText();
		return status;
	}

	public String getCalculationStatusMyStatusFirstRowLogStatusCellText() {
		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[11]/div").getText();
		return text;
	}

	public String getCalculationStatusMyStatusFirstRowRecordsProcessedCellText() {
		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[15]/div").getText();
		return text;
	}

	public String getCalculationStatusMyStatusFirstRowRecordsPendingCellText() {
		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[16]/div").getText();
		return text;
	}
	public String getCalculationStatusMyStatusFirstRowTotalRecordsCellText() {
		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div").getText();
		return text;
	}

	public void assertFilterResults(String expectedTotal) throws InterruptedException {
		doClick(getWebElement("//div[3]/em/button/span[text()='Filter']"));
		waitForAjaxExtJs();
		assertThat(filterGetFilterMatchesTheseCriteriaText(), containsString(expectedTotal));
		doClick(getWebElement("//div[3]/em/button/span[text()='Cancel & Close']"));
	}

	public void calculationsAssertDbRowCount(
			String sqlQuery, String evaluation, int expectedRowCount)
					throws ClassNotFoundException {
		jdbc.setSqlQuery(sqlQuery);
		int count = jdbc.jdbcGetRowCountFromDb(jdbc.getSqlQuery());
		System.out.println("Db Row Count: " + count);
		if (evaluation.contains("equal")) {
			assertEquals(expectedRowCount, count);
		} else if (evaluation.contains("less")) {
			assertTrue(count < expectedRowCount);
		} else if (evaluation.contains("greater")) {
			assertTrue(count > expectedRowCount);
		} else {
			fail("Operator not recognized - use less than, greater than, or equal to");
		}
	}

	public void waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(String detailText) throws InterruptedException {
		waitForCalculationToEnd();
		driverDelay(4000);
		driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
		waitForSpinnerToEnd();
		Thread.sleep(2000);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='" + detailText + "']")), printout);
		//Completed at: 11/18/2019 01:52 PM
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}

	public static void selectMaintainDataAtoZ(String dataCategory) throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		dmMap.getDataMaintenanceAZ().click();
		waitForPresenceOfElement("//div[contains(@class, 'left_atoz')]/div[text()='" + dataCategory + "']");
		driver.findElement(By.xpath("//div[contains(@class, 'left_atoz')]/div[text()='" + dataCategory + "']")).click();
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

	public static void openMaintainDataBatch(String batchName) throws InterruptedException {
		Thread.sleep(1000);
		waitForSpinnerToEnd();

		//    driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='# TB Test 0228.2018']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Scroll down till the bottom of the page
		//  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		//  js.executeScript("window.scrollBy(0,250)","");
		//    driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='# TB Test 0228.2018']")).click();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform(); //Page Down
		System.out.println("Scroll down perfomed");
		Thread.sleep(5000);
		//    Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + batchName + "']"))).perform();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
	}

	public void getStringDataFromDatabaseAndAssertExpectedValues(String sqlQuery, int datasetColumn, List<String> expectedValues) throws ClassNotFoundException {
		jdbc.setSqlQuery(sqlQuery);
		System.out.println("Sql Query: " +  jdbc.getSqlQuery());
		ArrayList<String> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsString(jdbc.getSqlQuery(), datasetColumn);
		int index = 0;
		for (String dbValue : dataFromDb) {
			System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
			assertTrue(dbValue == expectedValues.get(index));
			index++;
		}
	}

	public void getDataFromDatabaseAndAssertExpectedValues(String sqlQuery, int datasetColumn, List<Double> expectedValues) throws ClassNotFoundException {
		jdbc.setSqlQuery(sqlQuery);
		System.out.println("Sql Query: " +  jdbc.getSqlQuery());
		ArrayList<Double> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsDouble(jdbc.getSqlQuery(), datasetColumn);
		int index = 0;
		for (double dbValue : dataFromDb) {
			System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
			assertTrue(dbValue == expectedValues.get(index));
			index++;
		}
	}

	public void getStringDataFromDatabaseAndAssertValuesAreNull(String sqlQuery, int datasetColumn) throws ClassNotFoundException {
		jdbc.setSqlQuery(sqlQuery);
		System.out.println("Sql Query: " +  jdbc.getSqlQuery());
		ArrayList<String> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsStrings(jdbc.getSqlQuery(), datasetColumn);
		for (String dbValue : dataFromDb) {
			System.out.println("Assert: " + dbValue + " is null");
			assertNull(dbValue);
		}
	}

	public void getDataFromDatabaseAndAssertExpectedValues(String sqlQuery, List<Double> expectedValues) throws ClassNotFoundException {
		jdbc.setSqlQuery(sqlQuery);
		System.out.println("Sql Query: " +  jdbc.getSqlQuery());
		ArrayList<Double> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsDouble(jdbc.getSqlQuery(), 3);
		int index = 0;
		for (double dbValue : dataFromDb) {
			System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
			assertTrue(dbValue == expectedValues.get(index));
			index++;
		}
	}

	@Deprecated
	public void clickCalculateButtonAndVerifySummaryDetailsOnDialogAndCloseDialog(byte numberOfEfrs, byte errors, byte efrsCalculatedToZero ) throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		modelMap.getModelLibraryContractingButtonCalculate().click();
		waitForCalculationToEnd();
		driver.findElement(By.xpath("//button/span[text()='View']")).click();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Total EFRs to be processed: " + numberOfEfrs + "']")), printout);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Errors: " + errors + "']")), printout);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Encounter Financial Records Calculated to Zero: " + efrsCalculatedToZero + "']")), printout);
		//Completed at: 11/18/2019 01:52 PM
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}

	@Deprecated
	public void clickCalculateButtonAndVerifyEfrCountAndZeroErrorsOnSummaryDialogAndCloseDialog(byte numberOfEfrs) throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		modelMap.getModelLibraryContractingButtonCalculate().click();
		waitForCalculationToEnd();
		driver.findElement(By.xpath("//button/span[text()='View']")).click();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Total EFRs to be processed: " + numberOfEfrs + "']")), printout);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Errors: 0']")), printout);
		//Completed at: 11/18/2019 01:52 PM
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}

	public void verifySummaryDetailsOnViewDialogAndCloseDialog(
			String expectedViewLog,
			String[] expectedCalculationDetails) {
		driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
		//driver.findElement(By.xpath("//tbody/tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'x-grid-cell')]/descendant::button/span[text()='View']")).click();
		waitForDisplayedSpinnerToEnd();
		confirmCalculationStatusViewLogContains(expectedViewLog);
		//confirmCalculationStatusDetailsContains(expectedCalculationDetails);
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}

	public void calculationStatusPageOpenViewDialog() {
		//	  Omkar 14/04/2023 : xpath changes for 11.2
		//    waitForPresenceOfElement("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']");
		waitForPresenceOfElement("(//div[not(contains(@class,'disabled'))]//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-1490 x-unselectable')]/div/span)[1]");
		//    Omkar 14/04/2023 : xpath changes for 11.2
		//    driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
		driver.findElement(By.xpath("(//div[not(contains(@class,'disabled'))]//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-1490 x-unselectable')]/div/span)[1]")).click();
		waitForDisplayedSpinnerToEnd();
	}

	/* calculation status page */
	public void assertViewLogTitle(String expectedViewLogTitle) {
		waitForDisplayedSpinnerToEnd();
		confirmCalculationStatusViewLogContains(expectedViewLogTitle);
	}

	/* calculation status page */
	public void closeViewDialog() {
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}

	public void waitForCalculationToEndAndVerifySummaryDetailsOnViewDialogAndCloseDialog(
			String expectedViewLog,
			String[] expectedCalculationDetails)
					throws InterruptedException {
		waitForCalculationToEnd();
		driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
		//driver.findElement(By.xpath("//tbody/tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'x-grid-cell')]/descendant::button/span[text()='View']")).click();
		waitForDisplayedSpinnerToEnd();
		confirmCalculationStatusViewLogContains(expectedViewLog);
		confirmCalculationStatusDetailsContains(expectedCalculationDetails);
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}
	//Shilpa 14.09.2022 removed @Depreciated
	public void waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog(String expectedViewLog) throws InterruptedException {
		waitForCalculationToEnd();
		driverDelay(4000);
		driver.findElement(By.xpath("//tbody/tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'x-grid-cell')]/descendant::button/span[text()='View']")).click();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
		confirmCalculationStatusViewLogContains(expectedViewLog);
		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		waitForSpinnerToEnd();
	}

	public void confirmCalculationStatusDetailsContains(String[] expectedDetails) {
		for (String detail : expectedDetails) {
			assertThatElementIsDisplayed(driver.findElement(By.xpath("//*[text()='" + detail + "']")));
		}
	}

	public void confirmCalculationStatusDetailsContains(String expectedDetail) throws Exception {
		//Shilpa 07.09.2022 added wait for element

		//waitUntilElementIsClickable(driver.findElement(By.xpath("//*[text()='" + expectedDetail + "']")));
		//assertThatElementIsDisplayed(driver.findElement(By.xpath("//*[text()='" + expectedDetail + "']")));
		ContractModelsHelper.scrollToView("//*[contains(text(),'" + expectedDetail + "')]");
		assertThatElementIsDisplayed(driver.findElement(By.xpath("//*[contains(text(),'" + expectedDetail + "')]")));
	}

	public void confirmCalculationStatusViewLogContains(String expectedViewLog) {
		String viewLog = driver.findElement(By.xpath("//label[text()='View Log:']/../following-sibling::td/div")).getText();
		System.out.println("View Log: " + viewLog);
		assertThat(viewLog, containsString(expectedViewLog));
	}

	public void deleteMyCalculationStatusFirstRow() throws InterruptedException {
		String xpath = "//table/tbody/tr[2]/td[21]/div/div/em/button/span[2]";
		deleteFirstRow(xpath);
	}

	public void deleteFirstRow(String xpath) throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		waitForPresenceOfElement(xpath);
		//Thread.sleep(1000);
		WebElement firstRowDeleteIcon = driver.findElement(By.xpath(xpath));
		firstRowDeleteIcon.click();
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//div[contains(@class, 'windowbtn')]/descendant::button/span[text()='Delete']")).click();
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

	public void deleteFirstRow() throws InterruptedException {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		Thread.sleep(1000);
		WebElement firstRowDeleteIcon = driver.findElement(By.xpath(calculationStatusDeleteFirstRowDeleteButtonXpath));
		firstRowDeleteIcon.click();
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//div[contains(@class, 'windowbtn')]/descendant::button/span[text()='Delete']")).click();
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

	public void deleteCalculationStatusMyStatusPageFirstRow() throws InterruptedException {
		String xpath = "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[21]/descendant::button";  //"//div[2]/div/div[4]/div/table/tbody/tr[2]/td[21]/descendant::button"
		deleteFirstRow(xpath);
	}

	public static void waitForCalculationToEnd() throws InterruptedException {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		//    CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
		//    doClick(driver.findElement(By.xpath("//div[contains(@id,'statustoolbar')]//span[text()='Filter']/parent::button")));
		//    doDropdownSelectUsingOptionText(CostingMap.getcalculationFilterPopUpFilterDrop(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Calculation Start Time");
		//    
		////    doClick(driver.findElement(By.xpath("//div[contains(@id,'statustoolbar')]//span[text()='Filter']/parent::button")));
		////    doClick(driver.findElement(By.xpath("(//table[contains(@id,'specialtagcombo')]//input[contains(@id,'specialtagcombo')])[1]")));
		////    doClick(driver.findElement(By.xpath("//li[text()='Calculation Start Time']")));
		//    doClick(driver.findElement(By.xpath("//input[@name='valuedate']")));
		//    driver.findElement(By.xpath("//input[@name='valuedate']")).sendKeys("11/24/2022");
		//    Thread.sleep(400);
		//d    doClick(driver.findElement(By.xpath("//span[text()='Apply Filter']/parent::button")));
		try {
			filterByCalculationStartTimeInCalculationStatusPage();
		} catch (Throwable e1) {

		}
		while (calculate) {
			try {
				driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("100%"));
				break;
			} catch (Throwable e) {
				System.out.println("percent less than 100");
				Thread.sleep(1000);
				if (counter == 30) {
					fail("Calculation did not finish in allotted time");
				}
				counter++;
			}
		}
		Thread.sleep(1000);
	}

	public static void waitForCalculationToEnd(int waitTimeInSeconds) throws Exception {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		try {
			filterByCalculationStartTimeInCalculationStatusPage();
		} catch (Throwable e1) {

		}
		while (calculate) {
			try {
				driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("100%"));
				break;
			} catch (Throwable e) {
				System.out.println("percent less than 100");
				Thread.sleep(1000);
				if (counter == waitTimeInSeconds) {
					fail("Calculation did not finish in allotted time");
				}
				counter++;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
	}

	public static void waitForFirstRowCalculationBarToReach100Percent() throws Exception {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		try {
			filterByCalculationStartTimeInCalculationStatusPage();
		} catch (Throwable e1) {

		}
		while (calculate) {
			try {
				//    	  Omkar 14/04/2023 : xpath changes for 11.2
				//        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				driver.findElement(By.xpath("//span[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("100%"));
				break;
			} catch (Throwable e) {
				System.out.println("percent less than 100");
				Thread.sleep(1000);
				if (counter == 120) {
					fail("Calculation did not finish in allotted time");
				}
				counter++;
			}
		}
		Thread.sleep(5000);
	}

	//number of checks is 10 - total run time can be controlled by setting refresh interval - longer interval, longer run time
	public static void waitForFirstRowCalculationBarToReach100Percent(int refreshInterval) throws Exception {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		try {
			filterByCalculationStartTimeInCalculationStatusPage();
		} catch (Throwable e1) {

		}
		while (calculate) {
			try {
				driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("100%"));
				break;
			} catch (Throwable e) {
				System.out.println("percent less than 100");
				Thread.sleep(refreshInterval);
				if (counter == 30) {
					fail("Calculation did not finish in allotted time");
				}
				counter++;
			}
		}
		Thread.sleep(1000);
	}

	public static void goToContractModelsAndSelectFolder(String folderName) throws InterruptedException {
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='Contracting']/img")).click();
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//*[text()='" + folderName + "']")).click();  ///img[2]
	}

	public static void goToContractModelsAndSelectFolderAndSubFolder(String folderName, String subFolderName) throws InterruptedException {
		goToContractModelsAndSelectFolder(folderName);
		Thread.sleep(1000);
		waitForSpinnerToEnd();
		driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='" + subFolderName + "']")).click();
	}

	public static void filterByCalculationStartTimeInCalculationStatusPage() throws Throwable {
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = f. format(new Date());
		CostingMap costingMap = BuildMap.getInstance(driver, CostingMap.class);
		doClick(driver.findElement(By.xpath("//div[contains(@id,'statustoolbar')]//span[text()='Filter']/parent::button")));
		doDropdownSelectUsingOptionText(CostingMap.getcalculationFilterPopUpFilterDrop(),costingMap.getCalculationFilterDropdownMenuList(),"Calculation Start Time");

		doClick(driver.findElement(By.xpath("//input[@name='valuedate']")));
		driver.findElement(By.xpath("//input[@name='valuedate']")).sendKeys(strDate);
		doClick(driver.findElement(By.xpath("//div[contains(@id,'filter')]//span[text()='Add']/parent::button")));
		doClick(driver.findElement(By.xpath("//span[text()='Apply Filter']/parent::button")));
	}
	public static void goToMaintainDataPageAndSelectContractBatch(String folderName) throws InterruptedException {
		goToPage("Maintain Data");
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		dmMap.getDataMaintenanceAZ().click();
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//div[contains(@class, 'left_atoz')]/div[text()='Contract Batches']")).click();
		Thread.sleep(1000);
		waitForSpinnerToEnd();
		try {
			driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + folderName + "']")).click();
		} catch (Throwable e) {
			driver.findElement(By.xpath("//div[contains(@class,'column-header')]/span[contains(@id,'gridcolumn') and text()='Name']")).click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + folderName + "']")).click();
		}
		waitForSpinnerToEnd();
		Thread.sleep(1000);
	}

	public static void goToContractModelsAndSelectModel(String folderName, String modelName) throws InterruptedException {
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='Contracting']/img")).click();
		waitForAjaxExtJs();
		driver.findElement(By.xpath("//*[text()='Automation']/img[2]")).click();
		Thread.sleep(1000);
		waitForSpinnerToEnd();
		driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='" + folderName + "']")).click();
		waitForSpinnerToEnd();
		Thread.sleep(1000);
		doSearchForAndSelectContractModelFromContractModelLibrary(modelName);
	}

	public static void goToContractModelsPageAndSearchAndSelectModel(String modelName) throws InterruptedException {
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		Thread.sleep(1000);
		doSearchForAndSelectContractModelFromContractModelLibrary(modelName);
	}

}
