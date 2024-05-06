package webdriver.scripts.episodes;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EpisodesCreateAssignRemoval extends CalculationHelper {
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String episodeModelName = "Episode Model " + currentDateTime;
	static String enterPreAdmissionPhase = "3";
	static String enterPostDischargePhase = "60";
	private static String[] trigerrFilter = { "Name", "Is", "Equal To", "CMS CJR Trigger" };
//	private static String populationName = "# Episodes of Care Population CJR 2016";
	private static String populationName = "# EpisodesE01A - One EFR";
	static ContractingMap contractingMap;
	static ModelLibraryMap modelMap;
	private String serviceName = "CMS CJR Trigger";
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/** Automates test ticket ADS-6296. */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EpisodesCreateAssignRemoval", "webdriver.scripts.episodes",
				"EpisodesCreateAssignRemoval");
		try {
			contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			Login.loginUser("EpisodeAnalyst1");
			goToPage("Episode Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6296 all steps
	@Test
	public void test01EpisodeCreation_6296() throws Throwable {
		try {
			doClick(contractingMap.getNewContractModelButton());
			waitForElementToBeVisible(modelMap.getNewEpisodeModelPopUp());
			contractingMap.getNewFolderNameInput().sendKeys(episodeModelName);
			driverDelay(1000);
			doClick(contractingMap.getNewFolderNameSave());
			driverDelay(500);
			goToPage("Episode Models");
			doSearchForModel(episodeModelName);
			tableDoubleClickCellFirstColumn(episodeModelName);
			waitForDisplayedSpinnerToEnd();
			doClick(modelMap.getModelEpisode());
			driverDelay(500);
			doClickTreeItem("General Information - Episode");
//			doClickTreeItem("General Information - Episode");
			ContractModelsHelper.keyInValues(modelMap.getPreAdmissionPhase(), enterPreAdmissionPhase);
			ContractModelsHelper.keyInValues(modelMap.getpreDischargePhase(), enterPostDischargePhase);
			navigateOpenNewSection(modelMap.getOpenNewSection());
			doClick(modelMap.getTriggerAddButton());
			waitForElementToBeVisible(modelMap.getTriggerSelectorPopUp());
			doClick(ContractingMap.getaddOnServicesPopUpFilterButton());
			waitForAjaxExtJs();
			Thread.sleep(200);
			contractModelsHelper.doFilterCreateForServices(trigerrFilter);
			ContractModelsHelper.highlightColumnsToDisplayColumnServices();
			doClick(contractingMap.getContractFeeForServicePaymentApply());
			doClick(contractingMap.getContractModelSaveCopy());
//			doClick(modelMap.getshareLogCheckbox());

			ExtentReport.logPass("PASS", "test01EpisodeCreation");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01EpisodeCreation",
					driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test02VerifyAssinEpisodeToEncounter_6296() throws Throwable {
		try {
			doClick(modelMap.getModelEpisode());

			doClickTreeItem("Assign Episode to Encounters");
			doDropdownSelectUsingOptionText(modelMap.getEpisodePopulationDropdown(), populationName);
			doClick(modelMap.getshareLogCheckbox());
			doClick(modelMap.getSaveButton());
			doClick(modelMap.getAssignButton());
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay(7000);
//			clickLastPageIconOnCalculationStatusViewLog();
//			System.out.println("Waiting");
//			Thread.sleep(1000);
//			Omkar 17/04/2023 : The below elements would be found only on last page of view window
			//Shilpa added below method to search records processed in all pages
//			checkForRecordsProcessed("Total number");
			confirmCalculationStatusDetailsContains("Total number of Encounters tagged as triggers : 0");
			confirmCalculationStatusDetailsContains("Total number of Encounters tagged as pre-admission inclusions: 0");
			confirmCalculationStatusDetailsContains("Total number of Encounters tagged as acute inclusions: 0");
			confirmCalculationStatusDetailsContains(
					"Total number of Encounters tagged as post-discharge inclusions: 0");
			confirmCalculationStatusDetailsContains("Remove Total number of tagged Encounters: 0");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClickTreeItemWithCheckbox("General Information - Episode");
			navigateOpenNewSection(modelMap.getOpenNewSection());
			doClick("//div[text()='" + serviceName + "']");
			doClick(modelMap.getTriggerRemoveButton());
//			waitForElementToBeVisible(modelMap.getWarningPopUp());
//			doClick(modelMap.getRemoveButton());
			driverDelay();
			doClickTreeItem("Assign Episode to Encounters");
			waitForElementToBeVisible(ContractingMap.getWarningCancelCloseBtn());
			doClick(ContractingMap.getWarningCancelCloseBtn());
			//Shilpa: below steps cannot be executed becoz of issue ADS-11770
			doClick(contractingMap.getContractModelButtonColumnsToDisplayModalRemove());
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent();
// Omkar 17/4/2023 : Unab;le to scroll to bottom to find element. Code will fail after this
			calculationStatusPageOpenViewDialog();
			driverDelay();
//			confirmCalculationStatusDetailsContains("Total EFRs in Population to process: 1");
			confirmCalculationStatusDetailsContains("Remove Total number of tagged Encounters: 0");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			//Shilpa updated xpath for 11.2 on 12.20.2023
			doClick("//span[text()='"+episodeModelName+"']//following::span[@class='x-tab-close-btn']");
//			doClosePageOnLowerBar(episodeModelName);
			
			doClick(contractingMap.getContractModelDeleteButton());
			waitForElementToBeVisible(contractingMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(contractingMap.getContractModelDeletePopUp());
			doClick(contractingMap.getContractModelDeleteButton());
			waitForElementToBeVisible(contractingMap.getContractModelDeletePopUp());
			assertElementIsDisplayed(contractingMap.getContractModelDeleteButtonInPopUp());
			assertElementIsDisplayed(contractingMap.getContractModelCancelButtonInPopUp());
			doClick(contractingMap.getContractModelDeleteButtonInPopUp());
			waitForElementToBeVisible(
					driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
			doClosePageOnLowerBar("Episode Models");

			ExtentReport.logPass("PASS", "test02VerifyAssinEpisodeToEncounter");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyAssinEpisodeToEncounter",
					driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
