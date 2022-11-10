package webdriver.globalscripts.pagetests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.data.AdsStandardData;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.AnalyticsMap;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.ReportingMap;
import webdriver.maps.StatusMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BuildVerificationTestScript extends UcqcHelper {

  private static GeneralElementsMap generalElement;
  private static AnalyticsMap analyticsMap;
  private static ContractingMap contractingMap;
  private static CostingMap costingMap;
  private static DataMaintenanceMap dataMaintenance;
  private static DialogsMap dialogsMap;
  private static ModelLibraryMap modelLibrary;
  private static ReportingMap reportingMap;
  private static StatusMap statusMap;
  private static SystemMaintenanceMap sysmaint;
  WebDriverWait wait = new WebDriverWait(driver, 30);
  String expectedReleaseVersion = version;  //only checks version, not date

  /** The local pages map test is a test of the elements on all of the individual functional area (local) page maps -
   * that the elements on the map display on the page.
 * @throws Exception 
   */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("BuildVerificationTestScript", "webdriver.globalscripts.pagetests", "BuildVerificationTestScript");
    try {
		generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
		analyticsMap = BuildMap.getInstance(driver, AnalyticsMap.class);
		contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		statusMap = BuildMap.getInstance(driver, StatusMap.class);
		dialogsMap = BuildMap.getInstance(driver, DialogsMap.class);
		dataMaintenance = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		modelLibrary = BuildMap.getInstance(driver, ModelLibraryMap.class);
		reportingMap = BuildMap.getInstance(driver, ReportingMap.class);
		sysmaint = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
		System.out.println("Test Class: " + BuildVerificationTestScript.class.getSimpleName());
		ExtentReport.logPass("PASS", "setupScript");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }


  
  //===== Global Tests ======//
  @Test
  public void test0001LandingPageSystemMaintenance() throws Throwable {
    try {
		System.out.println("Logging In");
		
		/*modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
		Login.loginUser("AutomationTester1");
		*/
		Login.loginUser("AutomationTesterAdmin");
		// End of modification
		waitForSpinnerToEnd();
		waitForJsWindowOnload();
		System.out.println("Testing Global Pages");
		WebElement[] landingPageSystemMaintenanceElements = {
		        generalElement.getLandingPageBubbleSystemMaintenance(),
		        generalElement.getLandingPageBubbleSystemMaintenanceHeader(),
		        generalElement.getLandingPageBubbleSystemMaintenanceImage(),
		        generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkUsers(),
		        generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkRoles(),
		        generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings(),
		        generalElement.getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings()
		};
		assertElementsAreDisplayed(landingPageSystemMaintenanceElements, printout);
		ExtentReport.logPass("PASS", "test0001LandingPageSystemMaintenance");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test0001LandingPageSystemMaintenance", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test0340aCostingTabUnitCostQuickCalculationPage() throws Throwable {
    try {
      goToPage("Unit Cost Quick Calculation");
      waitForSpinnerToEnd();
      //doMaximizeWindow();
      waitForAjaxExtJs();
      WebElement[] costModelScenarioCalculationElements = {
              costingMap.getUnitCostQuickCalculationDropdownCostModel(),
              costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
              costingMap.getUnitCostQuickCalculationDropdownEntity(),
              costingMap.getUnitCostQuickCalculationButtonSelect(),
              costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
              costingMap.getUnitCostQuickCalculationButtonApplySelections(),
              costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),
              costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),
              costingMap.getUnitCostQuickCalculationButtonUndo(),
              costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs(),
              costingMap.getUnitCostQuickCalculationButtonCalculate(),
              costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),
              costingMap.getUnitCostQuickCalculationButtonHide()
      };
      assertElementsAreDisplayed(costModelScenarioCalculationElements, printout);
      ExtentReport.logPass("PASS", "test0340aCostingTabUnitCostQuickCalculationPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0340aCostingTabUnitCostQuickCalculationPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  String costModel = "QA Cost Model";
  String costModelScenario = "QA MHFY05 After Vol Change";
  String entity = "150 Marina Medical Center";

  @Test
  public void test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog() throws Throwable {
    try {
		try {
		  waitForAjaxExtJs();
		  triggerDepartmentDialog(costModel, costModelScenario, entity);
		  String style = costingMap.getUnitCostQuickCalculationDepartmentButtonCancelAndClose().getAttribute("style");
		  WebElement[] departmentModalElements = {
		          costingMap.getUnitCostQuickCalculationDepartmentField(),
		          costingMap.getUnitCostQuickCalculationDepartmentButtonFilter(),
		          costingMap.getUnitCostQuickCalculationDepartmentButtonApply(),
		          costingMap.getUnitCostQuickCalculationDepartmentButtonClose(),
		          costingMap.getUnitCostQuickCalculationDepartmentButtonCancelAndClose()
		  };
		  assertElementsAreDisplayed(departmentModalElements, printout);
		  doClick(costingMap.getUnitCostQuickCalculationDepartmentButtonFilter());
		  waitForAjaxExtJs();
		  WebElement[] departmentFilterElements = {
		          costingMap.getUnitCostQuickCalculationDepartmentFilterField(),
		          costingMap.getUnitCostQuickCalculationDepartmentFilterOperator(),
		          costingMap.getUnitCostQuickCalculationDepartmentFilterCondition(),
		          costingMap.getUnitCostQuickCalculationDepartmentFilterValue(),
		          costingMap.getUnitCostQuickCalculationDepartmentFilterButtonAdd(),
		          costingMap.getUnitCostQuickCalculationDepartmentFilterButtonRemoveAll(),
		          costingMap.getUnitCostQuickCalculationDepartmentFilterButtonApplyFilter()
		  };
		  assertElementsAreDisplayed(departmentFilterElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClick(costingMap.getUnitCostQuickCalculationDepartmentButtonClose());
		}
		ExtentReport.logPass("PASS", "test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0340bCostingTabUnitCostQuickCalculationPageDepartmentDialog", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0340cCostingTabUnitCostQuickCalculationPageShowHideFields()
          throws InterruptedException,Throwable {
    try {
		try {
		  waitForAjaxExtJs();
		  costingMap.getUnitCostQuickCalculationButtonHide().click();
		  waitForAjaxExtJs();
		  assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonShow(), printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Unit Cost Quick...");
		}
		ExtentReport.logPass("PASS", "test0340cCostingTabUnitCostQuickCalculationPageShowHideFields");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0340cCostingTabUnitCostQuickCalculationPageShowHideFields", driver, e);
 		fail(e.getMessage());
 	}
  }
  //------------------------------------------------------------------------

  //===== Contracting Tab =====/
  @Test
  public void test0400ContractingTabContractModelsPage() throws InterruptedException,Throwable {
    try {
		try {
		  goToPage("Contract Models");
		  waitForAjaxExtJs();
		  waitForSpinnerToEnd();
		  WebElement[] modelLibraryContractingElements = {
		          modelLibrary.getModelLibraryContractingButtonCalculate(),
		          modelLibrary.getModelLibraryContractingButtonCopy(),
		          modelLibrary.getModelLibraryContractingButtonPaste(),
		          modelLibrary.getModelLibraryContractingButtonImport(),
		          modelLibrary.getModelLibraryContractingButtonExport()
		  };
		  assertElementsAreDisplayed(modelLibraryContractingElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Model Library");
		}
		ExtentReport.logPass("PASS", "test0400ContractingTabContractModelsPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0400ContractingTabContractModelsPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0410ContractingTabApcAllocationPageMap() throws InterruptedException,Throwable {
    try {
		try {
		  goToPage("APC Allocation");
		  WebElement[] contractingTabApcAllocationPageElements = {
		    contractingMap.getApcAllocationPageHelpLink(),
		    contractingMap.getApcAllocationPageDataViewEncountersWithNoChargesReport(),
		    contractingMap.getApcAllocationPageDataViewEncountersWithZeroChargeBalanceReport(),
		    contractingMap.getApcAllocationPageDataViewEncountersWithNegativeChargeBalanceReport(),
		    contractingMap.getApcAllocationPageDataViewEncountersWithNoEfrsReport(),
		    contractingMap.getApcAllocationPageDataViewAllocateHcpcsAndBundledCharges(),
		    contractingMap.getApcAllocationPageDataViewResetEncounterApcCharges(),
		    contractingMap.getApcAllocationPageDataViewMatchEncounterId(),
		  };
		  assertElementsAreDisplayed(contractingTabApcAllocationPageElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("APC Allocation");
		}
		ExtentReport.logPass("PASS", "test0410ContractingTabApcAllocationPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0410ContractingTabApcAllocationPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0420ContractingTabContractualAllowanceExportPageMap() throws Throwable {
    try {
		try {
		  goToPage("Contractual Allowance Export");
		  WebElement[] contractingTabContractualAllowanceExportPageElements = {
		    contractingMap.getContractualAllowanceExportPageButtonNew(),
		    contractingMap.getContractualAllowanceExportPageButtonEdit(),
		    contractingMap.getContractualAllowanceExportPageButtonFilter(),
		    contractingMap.getContractualAllowanceExportPageButtonClearFilter(),
		    //uncomment this        contractingMap.getContractualAllowanceExportPageButtonDelete(),
		    contractingMap.getContractualAllowanceExportPageHelpLink(),
		    contractingMap.getContractualAllowanceExportPageTableCornerCell(),
		  };
		  assertElementsAreDisplayed(contractingTabContractualAllowanceExportPageElements, printout);
		  doClosePageOnLowerBar("Contractual...");
		} catch (Throwable e) {
		  fail(e.getMessage());
		}
		ExtentReport.logPass("PASS", "test0420ContractingTabContractualAllowanceExportPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0420ContractingTabContractualAllowanceExportPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  //------------------------------------------------------------------------

  @Test
  public void test0700DataMaintenanceTabMaintainDataPage() throws Throwable {
    try {
      goToPage("Maintain Data");
      waitForAjaxExtJs();
      waitForElementToBeVisible(dataMaintenance.getDataMaintenanceTreeExpanderContracting());
      WebElement[] maintainDataElements = {
              dataMaintenance.getDataMaintenanceTree(),
              dataMaintenance.getDataMaintenanceTreeExpanderContracting(),
              dataMaintenance.getDataMaintenanceTreeExpanderCosting(),
              dataMaintenance.getDataMaintenanceTreeExpanderEpisode(),
              dataMaintenance.getDataMaintenanceTreeExpanderGeneral(),
              dataMaintenance.getDataMaintenanceAZ()
      };
      assertElementsAreDisplayed(maintainDataElements, printout);
      ExtentReport.logPass("PASS", "test0700DataMaintenanceTabMaintainDataPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0700DataMaintenanceTabMaintainDataPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories()
          throws InterruptedException,Throwable {
    try {
		try {
		  String[] mainFolders = {"Contracting", "Costing", "Episode", "General"};
		  for (String mainFolder : mainFolders) {
		    assertThatElementIsDisplayed(
		            driver.findElement(By.xpath("//div[text()='" + mainFolder + "']"
		                    + "/img[contains(@class,'x-tree-icon x-tree-icon-parent ')]")));
		  }
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Maintain Data");
		}
		 ExtentReport.logPass("PASS", "test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0710DataMaintenanceTabMaintainDataPageAssertTreeListContainsTheFourMainDirectories", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0720DataMaintenanceTabUtilitiesPage() throws InterruptedException,Throwable{
    try {
		try {
		  goToPage("Utilities");
		  waitForAjaxExtJs();
		  WebElement[] getUtilitiesElements = {
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With No Charges Report", printout),
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With Zero Charge Balance Report", printout),
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With Negative Charge Balance Report", printout),
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Encounters With No EFRs Report", printout),
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Allocate HCPCS and Bundled Charges", printout),
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Reset Encounter APC Charges", printout),
		          getWebElementWithElementText(dataMaintenance.getUtilityElementXpath(), "Match Encounter ID", printout)
		  };
		  assertElementsAreDisplayed(getUtilitiesElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Utilities");
		}
		 ExtentReport.logPass("PASS", "test0720DataMaintenanceTabUtilitiesPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0720DataMaintenanceTabUtilitiesPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  //------------------------------------------------------------------------
  @Test
  public void test0800SystemMaintenanceTabUsersPageMap() throws Throwable {
    try {
      goToPage("Users");
      waitForAjaxExtJs();
      WebElement[] userPageElements = {
              sysmaint.getUsersPageButtonNew(),
              sysmaint.getUsersPageButtonEdit(),
              sysmaint.getUsersPageButtonFilter(),
              sysmaint.getUsersPageButtonSynchToBoeServer(),
              sysmaint.getUsersPageButtonImport(),
              sysmaint.getUsersPageButtonExport(),
              sysmaint.getUsersPageButtonClearFilter(),
              sysmaint.getUsersPageHelpLink(),
              sysmaint.getUsersPageTableCornerCell(),
      };
      assertElementsAreDisplayed(userPageElements, printout);
      doClosePageOnLowerBar("Users");
      ExtentReport.logPass("PASS", "test0800SystemMaintenanceTabUsersPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0800SystemMaintenanceTabUsersPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0810SystemMaintenanceTabRolesPageMap() throws Throwable {
    try {
      goToPage("Roles");
      waitForAjaxExtJs();
      WebElement[] rolesPageElements = {
              sysmaint.getRolesPageButtonNew(),
              sysmaint.getRolesPageButtonEdit(),
              sysmaint.getRolesPageHelpLink(),
              sysmaint.getRolesPageTableCornerCell(),
      };
      assertElementsAreDisplayed(rolesPageElements, printout);
      doClosePageOnLowerBar("Roles");
      ExtentReport.logPass("PASS", "test0810SystemMaintenanceTabRolesPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0810SystemMaintenanceTabRolesPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0820SystemMaintenanceTabSecuritySettingsPageMap() throws Throwable {
    try {
      goToPage("Security Settings");
      waitForAjaxExtJs();
      WebElement[] securitySettingsPageElements = {
              sysmaint.getSecuritySettingsPageHelpLink(),
              sysmaint.getSecuritySettingsPageFormFieldAuthenticationType(),
              sysmaint.getSecuritySettingsPageFormFieldInactivityTimeOutPeriod(),
              sysmaint.getSecuritySettingsPageFormFieldFailedLoginAttempts(),
              sysmaint.getSecuritySettingsPageFormFieldAutomaticAccountReactivationPeriod(),
              sysmaint.getSecuritySettingsPageFormFieldPasswordMinimumLength(),
              sysmaint.getSecuritySettingsPageFormFieldPasswordExpirationPeriod(),
              sysmaint.getSecuritySettingsPageFormFieldPasswordGraceLoginsAfterExpirationPeriod(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeAtLeastOneLetter(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeBothUpperAndLowerCaseLetters(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeAtLeastOneNumber(),
              sysmaint.getSecuritySettingsPageCheckboxMustIncludeAtLeastOneSpecialCharacter(),
              sysmaint.getSecuritySettingsPageFormFieldBusinessObjectsEnterpriseSynchInterval(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersAll(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersNone(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersAll(),
              sysmaint.getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersNone(),
              sysmaint.getSecuritySettingsPageCheckboxAuditLoggingEnabled(),
              sysmaint.getSecuritySettingsPageFormFieldAuditLogRetentionPeriod(),
              sysmaint.getSecuritySettingsPageButtonSave(),
      };
      assertElementsAreDisplayed(securitySettingsPageElements, printout);
      doClosePageOnLowerBar("Security Settings");
      ExtentReport.logPass("PASS", "test0820SystemMaintenanceTabSecuritySettingsPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0820SystemMaintenanceTabSecuritySettingsPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0830SystemMaintenanceTabGeneralSettingsPageMap() throws Throwable {
    try {
      goToPage("General Settings");
      waitForAjaxExtJs();
      WebElement[] generalSettingsPageElements = {
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosPublishedStart(),
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosPublishedEnd(),
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedStart(),
              sysmaint.getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedEnd(),
              sysmaint.getGeneralSettingsPageCheckboxRvuMaintenanceShowPrice(),
              sysmaint.getGeneralSettingsPageCheckboxRvuMaintenanceShowRevenue(),

              sysmaint.getGeneralSettingsPageFormFieldUiPageSize(),
              sysmaint.getGeneralSettingsPageFormFieldCalculationStatusUiPageSize(),
              sysmaint.getGeneralSettingsPageFormFieldImportExportAndUtilityStatusUiPageSize(),
              sysmaint.getGeneralSettingsPageFormFieldMaximumDockItems(),

              sysmaint.getGeneralSettingsPageFormFieldContractingContractBatch(),
              sysmaint.getGeneralSettingsPageFormFieldContractingContractAllowances(),
              sysmaint.getGeneralSettingsPageFormFieldContractingPsychCombinedComorbidityAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldContractingPublishedContractCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldContractingReimbursementScenarioAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldContractingUnpublishedContractCalculation(),

              sysmaint.getGeneralSettingsPageFormFieldCostingActivityVolumeDataCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingEncounterCostCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingClearEncounterCosts(),
              sysmaint.getGeneralSettingsPageFormFieldCostingCostModelScenarioCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingGlAdjustmentsAndReclassificationCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingGroupAllocationCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingOverheadModelScenarioCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingRvuCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingStatisticDataCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldCostingUnitCostQuickCalculation(),

              sysmaint.getGeneralSettingsPageFormFieldGeneralClearMedicalServiceAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralChargeItemServiceClassificationScheme(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralEncounterServiceClassificationScheme(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralMedicalServiceAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralPriceListCalculation(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralPriceListEncountersAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralPriceListEncountersAssignment(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralRemoveChargeItemServiceClassification(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralRemoveEncounterServiceClassification(),
              sysmaint.getGeneralSettingsPageFormFieldGeneralAllImportsAndExports(),
              sysmaint.getGeneralSettingsPageButtonSave(),
      };
      assertElementsAreDisplayed(generalSettingsPageElements, printout);
      doClosePageOnLowerBar("General Settings");
      ExtentReport.logPass("PASS", "test0830SystemMaintenanceTabGeneralSettingsPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0830SystemMaintenanceTabGeneralSettingsPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0840SystemMaintenanceTabCustomizeMaintainDataPageMap() throws InterruptedException ,Throwable{
    try {
		try {
		  goToPage("Customize Maintain Data");
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
		  waitForElementDoWhileLoop(sysmaint.getCustomizeMaintainDataRadioButtonShowSelected(),
		          printout);
		  WebElement[] customizeMaintainDataPageElements = {
		          sysmaint.getCustomizeMaintainDataRadioButtonShowSelected(),
		          sysmaint.getCustomizeMaintainDataRadioButtonShowAll(),
		          sysmaint.getCustomizeMaintainDataPageButtonSave(),
		          sysmaint.getCustomizeMaintainDataPageButtonSaveAndClose(),
		          sysmaint.getCustomizeMaintainDataPageButtonCancelAndClose(),
		          sysmaint.getCustomizeMaintainDataPageLinkHelp(),
		          sysmaint.getCustomizeMaintainDataCollapsibleSectionMaintainData(),
		          sysmaint.getCustomizeMaintainDataCollapsibleSectionEncounterTabs(),
		          sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataButtonFilter(),
		          sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataButtonClearFilter(),
		          sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll(),
		          sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll(),
		          sysmaint.getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters(),
		          sysmaint.getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxShowTabSelectAll(),
		          sysmaint.getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxTotals(),
		  };
		  assertElementsAreDisplayed(customizeMaintainDataPageElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Customize Maintain Data");
		}
		ExtentReport.logPass("PASS", "test0840SystemMaintenanceTabCustomizeMaintainDataPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0840SystemMaintenanceTabCustomizeMaintainDataPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0850SystemMaintenanceTabCustomizeTaskListsPageMap() throws InterruptedException ,Throwable{
    try {
		try {
		  goToPage("Customize Task Lists");
		  waitForAjaxExtJs();
		  WebElement[] customizeTaskListsPageElements = {
		          sysmaint.getCustomizeTaskListsPageLinkHelp(),
		          sysmaint.getCustomizeTaskListsPageSubTabCost(),
		          sysmaint.getCustomizeTaskListsPageSubTabOverhead(),
		          sysmaint.getCustomizeTaskListsPageSubTabUnpublishedContract(),
		          sysmaint.getCustomizeTaskListsPageSubTabPublishedContract(),
		          sysmaint.getCustomizeTaskListsPageSubTabEpisode(),
		  };
		  assertElementsAreDisplayed(customizeTaskListsPageElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Customize Task Lists");
		}
		ExtentReport.logPass("PASS", "test0850SystemMaintenanceTabCustomizeTaskListsPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0850SystemMaintenanceTabCustomizeTaskListsPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0860SystemMaintenanceTabTerminalServerSessionsPageMap() throws InterruptedException,Throwable {
    try {
		try {
		  goToPage("Terminal Server Sessions");
		  waitForAjaxExtJs();
		  WebElement[] terminalServerSessionsPageElements = {
		          sysmaint.getTerminalServerSessionsPageLinkHelp(),
		          sysmaint.getTerminalServerSessionsPageButtonContinue(),
		          sysmaint.getTerminalServerSessionsPageButtonClose(),
		  };
		  assertElementsAreDisplayed(terminalServerSessionsPageElements, printout);
		} catch (Throwable e) {
		  fail(e.getMessage());
		} finally {
		  doClosePageOnLowerBar("Terminal Server...");
		}
		ExtentReport.logPass("PASS", "test0860SystemMaintenanceTabTerminalServerSessionsPageMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0860SystemMaintenanceTabTerminalServerSessionsPageMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  //-------------------------------------
  @Test
  public void test0900StatusTabCalculationStatusPage() throws Throwable {
    try {
      goToPage("Calculation Status");
      doClick(statusMap.getCalculationStatusPageButtonAllStatus());
      waitUntilElementIsClickable(statusMap.getCalculationStatusPageButtonFilter());
      WebElement[] statusPageElements = {
        statusMap.getCalculationStatusPageButtonMyStatus(),
        statusMap.getCalculationStatusPageButtonAllStatus(),
        statusMap.getCalculationStatusPageButtonRefresh(),
        statusMap.getCalculationStatusPageFormFieldSearch(),
        statusMap.getCalculationStatusPageButtonSearchGlass(),
        statusMap.getCalculationStatusPageButtonFilter(),
        statusMap.getCalculationStatusPageButtonClearFilter(),
        statusMap.getCalculationStatusPageTableCornerCell(),
        //statusMap.getCalculationStatusPageButtonDeleteFiltered(),
      };
      assertElementsAreDisplayed(statusPageElements, printout);
      doClosePageOnLowerBar("Calculation Status");
      ExtentReport.logPass("PASS", "test0900StatusTabCalculationStatusPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0900StatusTabCalculationStatusPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0910StatusTabImportExportStatusPage() throws Throwable {
    try {
      goToPage("Import/Export Status");
      doClick(statusMap.getImportExportStatusPageButtonAllStatus());
      waitUntilElementIsClickable(statusMap.getImportExportStatusPageButtonFilter());
      WebElement[] statusPageElements = {
        statusMap.getImportExportStatusPageButtonMyStatus(),
        statusMap.getImportExportStatusPageButtonAllStatus(),
        statusMap.getImportExportStatusPageButtonRefresh(),
        statusMap.getImportExportStatusPageFormFieldSearch(),
        statusMap.getImportExportStatusPageButtonSearchGlass(),
        statusMap.getImportExportStatusPageButtonFilter(),
        statusMap.getImportExportStatusPageButtonClearFilter(),
        //statusMap.getImportExportStatusPageTableCornerCell(), //need to add to map
        //statusMap.getImportExportStatusPageButtonDeleteFiltered(),
      };
      assertElementsAreDisplayed(statusPageElements, printout);
      doClosePageOnLowerBar("Import/Export Status");
      ExtentReport.logPass("PASS", "test0910StatusTabImportExportStatusPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0910StatusTabImportExportStatusPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test0920StatusTabUtilityStatusPage() throws Throwable {
    try {
      goToPage("Utility Status");
      doClick(statusMap.getUtilityStatusPageButtonAllStatus());
      waitUntilElementIsClickable(statusMap.getUtilityStatusPageButtonFilter());
      WebElement[] statusPageElements = {
        statusMap.getUtilityStatusPageButtonMyStatus(),
        statusMap.getUtilityStatusPageButtonAllStatus(),
        statusMap.getUtilityStatusPageButtonRefresh(),
        statusMap.getUtilityStatusPageFormFieldSearch(),
        statusMap.getUtilityStatusPageButtonSearchGlass(),
        statusMap.getUtilityStatusPageButtonFilter(),
        statusMap.getUtilityStatusPageButtonClearFilter(),
        //statusMap.getUtilityStatusPageButtonDeleteFiltered(),
      };
      assertElementsAreDisplayed(statusPageElements, printout);
      doClosePageOnLowerBar("Utility Status");
      ExtentReport.logPass("PASS", "test0920StatusTabUtilityStatusPage");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test0920StatusTabUtilityStatusPage", driver, e);
 		fail(e.getMessage());
 	}
  }

  //------------------------------------------------------------------------

  //===== Filter Dialog Map =====//
  @Test
  public void test1000FilterDialogMap() throws Throwable {
    try {
		try {
		  goToPage("Users");
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
		  doClick(sysmaint.getUsersPageButtonFilter());
		  waitForAjaxExtJs();
		  waitUntilElementIsClickable(dialogsMap.getFilterDialogButtonCancelAndClose());
		  String dialogHeader = dialogsMap.getFilterDialogHeader().getText();
		  assertEquals("Filter Users", dialogHeader);
		  WebElement[] filterDialogElements = {
		          dialogsMap.getFilterDialogHelpLink(),
		          dialogsMap.getFilterDialogDropdownField(),
		          dialogsMap.getFilterDialogDropdownOperator(),
		          dialogsMap.getFilterDialogDropdownCondition(),
		          dialogsMap.getFilterDialogFormFieldValue(),
		          dialogsMap.getFilterDialogButtonAdd(),
		          dialogsMap.getFilterDialogButtonApplyFilter(),
		  };
		  assertElementsAreDisplayed(filterDialogElements, printout);
		} catch (Throwable e) {
		  throw e;
		} finally {
		  try {
		    doClick(dialogsMap.getFilterDialogButtonCancelAndClose());
		    waitForAjaxExtJs();
		  } catch (Throwable ee) {}
		  doClosePageOnLowerBar("Users");
		}
		ExtentReport.logPass("PASS", "test1000FilterDialogMap");
 	} catch (Exception|AssertionError e) {

 		ExtentReport.logFail("FAIL", "test1000FilterDialogMap", driver, e);
 		fail(e.getMessage());
 	}
  }

  private void triggerDepartmentDialog(String costModel, String costModelScenario, String entity) throws InterruptedException {
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),costModel);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),costModelScenario);
    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),entity);
    doClick(costingMap.getUnitCostQuickCalculationButtonSelect());
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(500);
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
