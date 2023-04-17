package webdriver.scripts.systemmaintenance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AssociateOverheadModeltoCostModelCustomTaskList.class,
	CreateaCustomTaskListwithOverheadTaskListSpecificScreens.class,
	CreateCustomTaskListwithCostingTaskListSpecificScreens.class,
	DepartmentLevelSecurity.class,
	EditAnExistingUserSetup.class,
	EditExistingRoleSetup.class,
	UIValidateImportStatuPage.class,
	UIValidationStatusUIValidateUtilityStatusPage.class,
	ValidateCalculationStatusAllStatus.class,
	ValidateCustomizeTaskListspageSystemMaintenance.class,
	ValidateFilterbuttonontheSystemMaintenanceUserspage.class,
	ValidateImportExportbuttonsontheSystemMaintenance.class,
	ValidateOverheadTaskListscreensAreAvailableforCost.class,
	ValidateSecuritySettingsGeneralSettingspagesintheSystemMaintenance.class,
	
})

public class SystemMaintenanceTestSuite {

}
