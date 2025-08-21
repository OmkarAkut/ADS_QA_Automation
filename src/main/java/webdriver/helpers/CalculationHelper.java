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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
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
	/*
	Omkar 18/8/2023 : xpath changes for 11.2
	public String calculationStatusDeleteFirstRowDeleteButtonXpath =
			"//table/tbody/tr[2]/td[21]/div/div/em/button/span[2]";
	*/
	public String calculationStatusDeleteFirstRowDeleteButtonXpath =
			"(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[contains(@class,'x-btn-icon-el x-btn-icon-el-default-small delBtn')])[1]";

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

//		String status = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[10]/div").getText();
		//Shilpa update xpath for 11.2 11.09.2023
		String status = getWebElement("((//div[contains(@id,'calculationstatus')])[11]//table//td[7]/div)[1]").getText();
		return status;
	}

	public String getCalculationStatusMyStatusFirstRowLogStatusCellText() {
//		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[11]/div").getText();
		//Shilpa update xpath for 11.2 11.09.2023
		String text = getWebElement("((//div[contains(@id,'calculationstatus')])[11]//table//td[8]/div)[1]").getText();
		return text;
	}

	public String getCalculationStatusMyStatusFirstRowRecordsProcessedCellText() {
//		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[15]/div").getText();
		//Shilpa update xpath for 11.2 11.09.2023
		String text = getWebElement("((//div[contains(@id,'calculationstatus')])[11]//table//td[12]/div)[1]").getText();
		return text;
	}

	public String getCalculationStatusMyStatusFirstRowRecordsPendingCellText() {
//		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[16]/div").getText();
		//Shilpa update xpath for 11.2 11.09.2023
		String text = getWebElement("((//div[contains(@id,'calculationstatus')])[11]//table//td[13]/div)[1]").getText();
		return text;
	}
	public String getCalculationStatusMyStatusFirstRowTotalRecordsCellText() {
//		String text = getWebElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div").getText();
		//Shilpa update xpath for 11.2 11.09.2023
		String text = getWebElement("((//div[contains(@id,'calculationstatus')])[11]//table//td[14]/div)[1]").getText();
		return text;
	}

	public void assertFilterResults(String expectedTotal) throws InterruptedException {
		//shilpa updated xpath for 11.2
		doClick(getWebElement("//div[contains(@id,'statisticdatagrid') or contains(@id,'activityvolumedatagrid')]//span[text()='Filter']"));
		waitForAjaxExtJs();
		assertThat(filterGetFilterMatchesTheseCriteriaText(), containsString(expectedTotal));
//		doClick(getWebElement("//div[3]/em/button/span[text()='Cancel & Close']"));
		//shilpa updated xpath for 11.2
		doClick(getWebElement("//div[contains(@id,'activityvolumedatagrid')or contains(@id,'statisticdatagrid')]//following::span[text()='Cancel & Close']"));

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
//		driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
		doClick("((//table[contains(@id,'gridview')]//div[not(contains(@class,'disabled'))]//span[text()='View']))[1]");
		waitForSpinnerToEnd();
		Thread.sleep(2000);
		assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='" + detailText + "']")), printout);
		//Completed at: 11/18/2019 01:52 PM
//		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		driver.findElement(By.xpath("//div[text()='View Log']//following::span[text()='Cancel']")).click();
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
//		Scroll down till the bottom of the page
		  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		  js.executeScript("window.scrollBy(0,1050)","");
		//    driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='# TB Test 0228.2018']")).click();
		  driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + batchName + "']")).sendKeys(Keys.PAGE_DOWN);
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT)
        .sendKeys("a")
        .perform(); //Page Down
//		System.out.println("Scroll down perfomed");
//		Thread.sleep(5000);
		//    Actions action = new Actions(driver);
		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();", driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + batchName + "']")));
//		action.doubleClick(driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + batchName + "']"))).perform();
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

	public void calculationStatusPageOpenViewDialog() throws Exception {
		//	  Omkar 14/04/2023 : xpath changes for 11.2
		//    waitForPresenceOfElement("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']");
		//    driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
//		Omkar 17/8/2023 : xpath changes for 11.2
//		waitForPresenceOfElement("(//div[not(contains(@class,'disabled'))]//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-1490 x-unselectable')]/div/span)[1]");
//		driver.findElement(By.xpath("(//div[not(contains(@class,'disabled'))]//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn-1490 x-unselectable')]/div/span)[1]")).click();
		Thread.sleep(1000);
//		waitForPresenceOfElement("(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[text()='View'])[2]");
//		driver.findElement(By.xpath("(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[text()='View'])[2]")).click();
		//Shilpa: Xpath update : 11.2 : 18.03.2025
		waitForPresenceOfElement("((//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//a[text()='View']))[1]");
		driver.findElement(By.xpath("((//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//a[text()='View']))[1]")).click();
		waitForDisplayedSpinnerToEnd();
		driverDelay(6000);
	}

	/* calculation status page */
	public void assertViewLogTitle(String expectedViewLogTitle) {
		waitForDisplayedSpinnerToEnd();
		confirmCalculationStatusViewLogContains(expectedViewLogTitle);
	}

	/* calculation status page */
	public void closeViewDialog() {
//		Omkar 18/8/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
		driver.findElement(By.xpath("//div[text()='View Log']//following::span[contains(@id,'button')]//span[text()='Cancel']")).click();
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
		String viewLog = driver.findElement(By.xpath("//span[text()='View Log:']/../following::div[1]/div")).getText();
		System.out.println("View Log: " + viewLog);
		assertThat(viewLog, containsString(expectedViewLog));
	}

	public void deleteMyCalculationStatusFirstRow() throws Throwable {
//		String xpath = "//table/tbody/tr[2]/td[21]/div/div/em/button/span[2]";
		//shilpa updated xpath for 11.2 on 11.23.2023
		String xpath = "(//div[contains(@id,'calculationstatus')])//following::div[@class='x-grid-item-container']//table[1]/tbody/tr/td[18]";

		deleteFirstRow(xpath);
	}

	public void deleteFirstRow(String xpath) throws Throwable {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		waitForPresenceOfElement(xpath);
		//Thread.sleep(1000);
		
		WebElement firstRowDeleteIcon = driver.findElement(By.xpath(xpath));
		firstRowDeleteIcon.click();
		waitForAjaxExtJs();
		assertElementIsDisplayed(ContractingMap.getWarningPopUpDeleteButton(), printout);
		assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id,'warningwindow')]//span[text()='Delete']")));
		assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id,'warningwindow')]//span[text()='Cancel']")));

		//		Omkar 18/8/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//div[contains(@class, 'windowbtn')]/descendant::button/span[text()='Delete']")).click();
		driver.findElement(By.xpath("//div[contains(@id,'warningwindow')]//span[text()='Delete']")).click();
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
//		Omkar 18/8/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//div[contains(@class, 'windowbtn')]/descendant::button/span[text()='Delete']")).click();
		driver.findElement(By.xpath("//div[contains(@id,'warningwindow')]//span[text()='Delete']")).click();
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

	public void deleteCalculationStatusMyStatusPageFirstRow() throws Throwable {
//		Omkar 18/8/2023 : xpath changes for 11.2
//		String xpath = "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[21]/descendant::button";  //"//div[2]/div/div[4]/div/table/tbody/tr[2]/td[21]/descendant::button"
		String xpath ="(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[contains(@class,'x-btn-icon-el x-btn-icon-el-default-small delBtn')])[1]";
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
//				  Omkar 21/6/2023 : xpath changes for 11.2
//			        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
			        driver.findElement(By.xpath("//span[text()='Refresh']")).click();
			        waitForSpinnerToEnd();
//			        Omkar 21/6/2023 : xpath changes for 11.2
//			        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
			        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')][1]")).getText();
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
//				driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				//Shilpa: update xpath for 11.2
				driver.findElement(By.xpath("//span/span[text()='Refresh']")).click();

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
					break;
				}
				counter++;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span/span[text()='Refresh']")).click();
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
					break;
				}
				counter++;
			}
			System.out.println(counter);
		}
		Thread.sleep(500);
	}
	//Shilpa updated 2.13.2024
	public static void waitForOverheadReceivedReport() throws Exception {
		boolean calculate = true;
		String percent;
		byte counter = 0;
		
		while (calculate) {
			try {
				//    	  Omkar 14/04/2023 : xpath changes for 11.2
				//        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
				driver.findElement(By.xpath("//button[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				driverDelay();
//				percent = driver.findElement(By.xpath("(//tr[@class='GJT013UBCG']//td//a)[1]")).getText();
				//Shilpa: update for 11.2 on 11.6.2024
				percent = driver.findElement(By.xpath("(//tr[@class='GJT013UBCG']//following::span[@title='Overhead Received']//following::td/div/a)[1]")).getText();
				System.out.println("Percent complete: " + percent);
				assertTrue(percent.contains("COMPLETED"));
				break;
			} catch (Throwable e) {
				System.out.println("Status is Failed");
				Thread.sleep(1000);
				if (counter == 10) {
					fail("Status is not Completed");
				}
				counter++;
			}
		}
		Thread.sleep(500);
	}

	//Check the records processed under View dialog
	public static void checkForRecordsProcessed(String text) throws Exception {
	try {
		int value=Integer.parseInt(modelMap.getpagination().getText().replaceAll("[^0-9]", ""));
		for(int i=1;i<=value;i++) {
			try {
				ContractModelsHelper.scrollToView("//*[contains(text(),'"+text+"')]");
				if(driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]")).isDisplayed()) {
					assertTrue(printout);
					break;
				}
				
			} catch (Exception  e) {
				try {
					ContractModelsHelper.scrollToView("//*[contains(text(),'"+text+"')]");
				} catch (Exception e1) {
					ModelLibraryMap.getGoToNextPage().click();
					driverDelay();
					continue;
				}
				
			}
		}
	}catch (Exception e) {
		for(int i=0;i<=25;i++) {
			ContractModelsHelper.scrollToView("//*[contains(text(),'"+text+"')]");
		}
		
	}
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
	//Shilpa: added method for 11.2 : 10.21.2024

	public  void doFilterCalculationPage(String[] filter) throws Exception {
		
		try {
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filter);
		} catch (InterruptedException e) {
			waitForAjaxExtJs();
		}
	
	}
}
