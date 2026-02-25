package webdriver.scripts.costing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.contracting.ValidateContractingModeExportImportButton;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus.OverWriteRVUValues5919;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	RVUExportImportFunction.class,
	OverwriteRvuNoCMSResults.class,
	OverWriteRVUValues5919.class,
	ValidateContractingModeExportImportButton.class,
	ActualCostingMethodPatientChgsDateRange.class,
	ActualCostingMethodPatientChgsDoesNotMatch.class,
	ActualCostingMethodPatientChgsTimestamp.class,
	ConfirmDepartmentFilterSortOrder.class,
	CreateDeleteCostMethodMaster.class,
	CreateNewCostModel.class,
	CreateNewEditDeleteTimePeriod.class,
	EditCostModelCalculationScenarios.class,
	EncCostCalcScenarioSelectedCostModelScenariosdisplayed.class,
	EntityLevelSecurityCosting.class,
	PaginationInCostingModels.class,
	RunGLAdjustmentAndReclassifications.class,
	RVUContainerDeleteFilteredData.class,
	RVUCalculationScenario.class,
	RvuMaintenanceAds1492.class,
	TableColumnSorting.class,
	TestGenericUIValidateCostingModelSearchTextbox.class,
	UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting.class,
	ValidateCalculationStatusAllStatus.class,
	ValidateCalculationStatusPage.class,
	ValidateEditCostMethodMasters.class,
	ValidateHelpLinkHideTabCosting.class,
	ValidateHelpLinkHideTabDataMaintenance.class,
	ValidateHelpLinkHideTabRvuMaintenance.class,
	ValidateOpenTaskList.class,
	ValidatePaginationOnTheCostingRVUMaintenancepage.class,
	VerifyStartEndMonthForOHScenario.class,
	ActivityStatisticMasterEditStatsSave.class,
	ExpandHelpSectionHeadersCosting.class,
	
	
})

public class CostingTestSuite {
}
