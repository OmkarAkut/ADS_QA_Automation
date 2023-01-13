package webdriver.scripts.episodes;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;


public class EpisodesCreateAssignRemoval extends CalculationHelper{
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String episodeModelName = "Episode Model " + currentDateTime;
	static String enterPreAdmissionPhase="3";
	static String enterPostDischargePhase="60";
	private static String[] trigerrFilter= {"Name","Is","Equal To","CMS CJR Trigger"};
	private static String populationName="# Episodes of Care Population CJR 2016";
	 static  ContractingMap contractingMap;
	 static ModelLibraryMap modelMap;
	 private String serviceName="CMS CJR Trigger";
		ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

/** Automates test ticket ADS-1492. */
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("EpisodesCreateAssignRemoval", "webdriver.scripts.episodes", "EpisodesCreateAssignRemoval");
		try {
			 contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
			 modelMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
			Login.loginUser("EpisodeAnalyst1");
			goToPage("Episode Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	
	@Test
	public void test01EpisodeCreation() throws Throwable {
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
			doClickTreeItemWithCheckbox("General Information - Episode");
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
			doClick(modelMap.getshareLogCheckbox());
			
		} catch (Exception|AssertionError e) {
			
		}
		
	}
	@Test
	public void test02VerifyAssinEpisodeToEncounter() throws Throwable {
		try {
			doClick(modelMap.getModelEpisode());

			doClickTreeItemWithCheckbox("Assign Episode to Encounters");
			doDropdownSelectUsingOptionText(modelMap.getEpisodePopulationDropdown(), populationName);
			doClick(modelMap.getshareLogCheckbox());
			doClick(modelMap.getSaveButton());
			doClick(modelMap.getAssignButton());
			 waitForSpinnerToEnd();
		      waitForFirstRowCalculationBarToReach100Percent();
		      calculationStatusPageOpenViewDialog();
		      confirmCalculationStatusDetailsContains("Total number of Encounters tagged as triggers : 10");
		      confirmCalculationStatusDetailsContains("Total number of Encounters tagged as pre-admission inclusions: 0");
		      confirmCalculationStatusDetailsContains("Total number of Encounters tagged as acute inclusions: 0");
		      confirmCalculationStatusDetailsContains("Total number of Encounters tagged as post-discharge inclusions: 0");
		      confirmCalculationStatusDetailsContains("Remove Total number of tagged Encounters: 0");
		      closeViewDialog();
		      deleteMyCalculationStatusFirstRow();
		      doClosePageOnLowerBar("Calculation Status");
		      doClickTreeItemWithCheckbox("General Information - Episode");
		  	navigateOpenNewSection(modelMap.getOpenNewSection());
		      doClick("//div[text()='"+serviceName+"']");
		      doClick(modelMap.getTriggerRemoveButton());
		      waitForElementToBeVisible(modelMap.getWarningPopUp());
		      doClick(modelMap.getRemoveButton());
		      
		      ExtentReport.logPass("PASS", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected");
		     	} catch (Exception|AssertionError e) {
		     		ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
		     		fail(e.getMessage());
		     	}			
		}
	}

