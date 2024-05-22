package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.costing.ActualCostingMethodPatientChgsDateRange;
import webdriver.scripts.costing.ActualCostingMethodPatientChgsDoesNotMatch;
import webdriver.scripts.costing.ActualCostingMethodPatientChgsTimestamp;
import webdriver.scripts.costing.ConfirmDepartmentFilterSortOrder;
import webdriver.scripts.costing.CreateDeleteCostMethodMaster;
import webdriver.scripts.costing.CreateNewCostModel;
import webdriver.scripts.costing.CreateNewEditDeleteTimePeriod;
import webdriver.scripts.costing.EntityLevelSecurityCosting;
import webdriver.scripts.costing.PaginationInCostingModels;
import webdriver.scripts.costing.RunGLAdjustmentAndReclassifications;
import webdriver.scripts.costing.TableColumnSorting;
import webdriver.scripts.costing.TestGenericUIValidateCostingModelSearchTextbox;
import webdriver.scripts.costing.UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting;
import webdriver.scripts.costing.ValidateEditCostMethodMasters;
import webdriver.scripts.costing.ValidateHelpLinkHideTabCosting;
import webdriver.scripts.costing.ValidateOpenTaskList;
import webdriver.scripts.costing.costingcalculations.CostingCalculateOverheadScenario;
import webdriver.scripts.costing.costingcalculations.CostingRunActivityVolumeCalculationScenarioAds2338;
import webdriver.scripts.costing.costingcalculations.CostingRunStatisticDataCalculationScenarioAds2339;
import webdriver.scripts.costing.costingmodels.CostingModelsSmokeTest;
import webdriver.scripts.costing.costingmodels.EncounterCost;

@RunWith(Suite.class)
@Suite.SuiteClasses({	
	ActualCostingMethodPatientChgsDateRange.class, 
	ActualCostingMethodPatientChgsDoesNotMatch.class,
	ActualCostingMethodPatientChgsTimestamp.class,
	ConfirmDepartmentFilterSortOrder.class,
	CreateDeleteCostMethodMaster.class,
	CreateNewCostModel.class,
	CreateNewEditDeleteTimePeriod.class,
	EntityLevelSecurityCosting.class,
	PaginationInCostingModels.class,
	RunGLAdjustmentAndReclassifications.class,
	TableColumnSorting.class,
	TestGenericUIValidateCostingModelSearchTextbox.class,
	UsingCustomvsDefaulttasklistsInCustomizeTaskListCosting.class,
	ValidateEditCostMethodMasters.class,
	ValidateHelpLinkHideTabCosting.class,
	ValidateOpenTaskList.class,
	CostingCalculateOverheadScenario.class,
	CostingRunActivityVolumeCalculationScenarioAds2338.class,
	CostingRunStatisticDataCalculationScenarioAds2339.class,
	CostingModelsSmokeTest.class,
	EncounterCost.class,
	TestGenericUIValidateCostingModelSearchTextbox.class,


	
})
public class CostingModels {

}
