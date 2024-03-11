package Regression;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CalculateExistingPublishedContractAds1447.class,//ADS-6433,ADS-6085 Done
	CopyPasteButtons.class,//ADS-6434,ADS-6084 Done
	MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286.class,//ADS-6083[incomplete steps need to be added]
	ValidateBenifitPlansInfoForMultipleUnpublishedContracts.class,//ADS-6080 Done
	EditRiskLimiterScreen.class,//ADS-6079 has issue ADS-12487 so tc fails
	ValidateBenefitPlanInfoUpdate.class,//ADS-6076 ValidateBenefitPlanInfoUpdate
	ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate.class,//ADS-6775,ADS-6782 tc fails scroll issue
	GeneralCalculationsRunContractualAllowancesScenarioAds2163.class,//ADS-6104 Done
	PriceListCalculationScenario.class,//ADS-6103 scroll issue
	GeneralCalculationsEncounterServiceClassificationSchemeAds2341.class,//ADS-6102 has issue with UI ADS-12509
	GeneralCalculationsChargeItemServiceClassificationSchemeAds2342.class,//ADS-6101 has issue with UI ADS-12509
	GeneralCalculationsRunMedicalServiceAssignmentAds2343.class,//ADS-6100 Done
	GeneralCalculationsRunPriceListEncounterAssignmentAds2344.class,//ADS-6099 Done
	RvuMaintenanceAds1492.class,//ADS-5993,ADS-6646 Done
	ValidateHelpLinkHideTabRvuMaintenance.class,//ADS-5992 Done
	CostingCalculateOverheadScenario.class,//ADS-5991 Done
	CostingRunActivityVolumeCalculationScenarioAds2338.class,//ADS-5990[incomplete]
	CostingRunStatisticDataCalculationScenarioAds2339.class,//ADS-5989 Done
	RunGLAdjustmentAndReclassifications.class,//ADS-5988 Done
	UcqcCreateCopyOfCmsToStoreResultsOfUcqcCalculationCmsScenarioAds1378.class,//ADS-5987 Done
	RVUContainerDeleteFilteredData.class,//ADS-5983 Done
	EncCostCalcScenarioSelectedCostModelScenariosdisplayed.class,//ADS-5982 Done
	UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175.class,//ADS-5927,ADS-6644 Done
	UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136.class,//ADS-5926 Done
	UpdateUCQCParmsRvuCalcScenario.class,//ADS-5925 Done
	QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229.class,//ADS-5924,ADS-6665 Done
	CreateUCQCLogMakeUCQCProcessAvailableCalculationPage.class,//ADS-5923 Done
	ConfirmDepartmentFilterSortOrder.class,//ADS-5922 Done
	HorizontalScrollColumnsToDisplay.class,//ADS-5921 Done
	OverwriteRvuNoCMSResults.class,//ADS-5920 Done
	OverWriteRVUValues5919.class,//ADS-5919 Done
	VerifyUCQCButtonsBackgroundColor.class,//ADS-5918 Done
	VerifyUCQCdropdownValuesForOptionNone.class,//ADS-5915 Done
	CreateNewCostModel.class,//ADS-6632, ADS-6633 ,ADS-6641,ADS-6253,,ADS-6254[missing steps from  step7] Done
	CreateNewEditDeleteTimePeriod.class,//ADS-6673, ADS-6675 ,ADS-6672 Done
	CreateDeleteCostMethodMaster.class,//ADS-6671, ADS-6670 Done
	ValidateEditCostMethodMasters.class,//ADS-6643,ADS-6669[steps need to be added] Done
	ValidateOpenTaskList.class,//ADS-6642,ADS-6668 Done
	OverwriteRvuMaintenancePopupAds1181.class,//ADS-6667 Done
	UcqcCopyToQuickRvusPopupIsLaunchedAds1148.class,//ADS-6666 Done
	EditCostModelCalculationScenarios.class,//ADS-6645,ADS-6662,ADS-6663,ADS-6664 Done
	RVUExportImportFunction.class,//ADS-6659,ADS-6660, Done
	CreateDeleteCostMethodDeptMasterDischargeStatus.class,//ADS-6655,ADS-6656,ADS-6657,ADS-6658, Done
	TableColumnSorting.class,//ADS-6654,ADS-6509 Done
	PaginationInCostingModels.class,//ADS-6652 Done
	ClearFilterbuttonModels.class,//ADS-6647,ADS-6648,ADS-6650,ADS-6438,ADS-6432,ADS-6431 check
//	SmokeTest.class, //ADS-6642[add step 4-9],ADS-6582,ADS-6503,ADS-6502[add step 4-11],ADS-6501[ add step 4-7],ADS-6500[add step4-15],ADS-6498[add step4 -12],ADS-6497[add step4 -12],ADS-6496[add step4 -12],ADS-6495[add step 4-8],
	CreateEditDeleteNewStructurePopulations.class,//ADS-6396 Done
	ValidateCalculationStatusPage.class,//ADS-5771[add step15 -25] Done
	BuildVerificationTestScript.class,//ADS-6584[add step4],ADS-6586,ADS-6592,ADS-6593,ADS-6594,ADS-6595,ADS-6596,ADS-6597,ADS-6598,ADS-6599
	 //ADS-6601,ADS-6600,ADS-6583 Done
	ChangePasswordDialogTest.class,//ADS-6585 Done
	EpisodesCreateAssignRemoval.class,//ADS-6296 Done
	ModelContractLumpSumPaymentAllocation.class,//ADS-6468 done
	ModelContractPatientFinancialResponsibility.class,//ADS-6467
	UIValidationPrepareTables.class,//ADS-6463,ADS-6462[add step],ADS-6461 check -Completed
	UIValidationContracting.class,//ADS-6465,ADS-6466 Completed
	ConfirmAddNewDeleteAPCFeeScheduleMasters.class,//ADS-6458,ADS-6457[to be auto],ADS-6455,ADS-6442 Done
	
	VerifyFilterAscSchemes.class,//ADS-6447Done
	NewContractType.class,//ADS-6446 Done
	ContractingDataMaintenanceNewItem.class,//ADS-6445 done
	ValidateContractingTreeTab.class,//ADS-6444 done
	ContractingDataMaintenanceNewItem.class,//ADS-6443 done
	ValidateContractingModeExportImportButton.class,//ADS-6437,ADS-6436 done
	CreateANewContractModel.class,//ADS-6435,ADS-6413,ADS-6412
	ValidateContracDataMaintenanceBubble.class,//ADS-6430,ADS-6428
	CreatingaNewContractingFolder.class,//ADS-6411[to be auto],ADS-6410, Done
	WebIntelligenceReportRunningExistingWebIReport.class,//ADS-6638 Done
	SearchTheReportLibrary.class,//ADS-5663 Done
	ReportLibrarySortingTest.class,//ADS-5662 Done
	ReportsLibraryCreateRenameAndDeleteNewFolderAds1348.class,//ADS-5661 Done
	FlexibleReportsProfitAndLossStatementTesting.class,//ADS-5660 done
	TestAbilitytoCreateExistingStandardCostingReport.class,//ADS-5659,done
	TestADS1617ApplySelectionsforStandardReport.class,//ADS-5657 done
	ValidateCustomizeTaskListspageSystemMaintenance.class,//ADS-6516 done
	TestGenericUIValidateCostingModelSearchTextbox.class,//ADS-6515, done
	ValidateSecuritySettingsGeneralSettingspagesintheSystemMaintenance.class,//ADS-6512 done
	ValidateImportExportbuttonsontheSystemMaintenance.class,//ADS-6511 done
	ValidateFilterbuttonontheSystemMaintenanceUserspage.class,//ADS-6510 done
	ValidatePaginationOnTheCostingRVUMaintenancepage.class,//ADS-6508 done
	UIValidationStatusUIValidateUtilityStatusPage.class,//ADS-6622,ADS-6621,ADS-6620,ADS-6619,ADS-6618,ADS-6617
	UIValidateImportStatuPage.class,//ADS-6616,,ADS-6615,ADS-6614,ADS-6613,ADS-6612,ADS-6611 done
	//Start from here
	ValidateCalculationStatusAllStatus.class,//ADS-6610,ADS-6609,ADS-6607,ADS-6606,ADS-6605,ADS-6604,ADS-6603,ADS-6602
	EntityLevelSecurityContracting.class,//ADS-5794
	EntityLevelSecurityCosting.class,//ADS-5793
	UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting.class,//ADS-6589 scroll issue
	CreateaCustomTaskListwithOverheadTaskListSpecificScreens.class,//ADS-5801 scroll bar issue
	ValidateOverheadTaskListscreensAreAvailableforCost.class,//ADS-5799 scrollbar issue so tc fail
	AssociateOverheadModeltoCostModelCustomTaskList.class,//ADS-5798 scroll issue need to implement steps aswell
	EditExistingRoleSetup.class,//ADS-5783 Completed - tc fail due to ADS-12564 
	EditAnExistingUserSetup.class,//ADS-5781 Completed - tc fail due to ADS-12547
})

public class Regression_suite {

}










