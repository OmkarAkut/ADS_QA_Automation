package webdriver.scripts.costing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.systemmaintenance.UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting;

@RunWith(Suite.class)
@Suite.SuiteClasses({
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
	OverwriteRvuNoCMSResults.class,
	PaginationInCostingModels.class,
	RunGLAdjustmentAndReclassifications.class,
	RVUContainerDeleteFilteredData.class,
	RVUExportImportFunction.class,
	RvuMaintenanceAds1492.class,
	TableColumnSorting.class,
	TestGenericUIValidateCostingModelSearchTextbox.class,
	UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting.class,
	ValidateCalculationStatusAllStatus.class,
	ValidateCalculationStatusPage.class,
	ValidateCostModelOpenTaskListLeftPaneItems.class,
	ValidateEditCostMethodMasters.class,
	ValidateHelpLinkHideTabCosting.class,
	ValidateHelpLinkHideTabDataMaintenance.class,
	ValidateHelpLinkHideTabRvuMaintenance.class,
	ValidateOpenTaskList.class,
	ValidatePaginationOnTheCostingRVUMaintenancepage.class,

})

public class CostingTestSuite {
}
