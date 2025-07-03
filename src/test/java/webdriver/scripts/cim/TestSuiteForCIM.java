package webdriver.scripts.cim;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	AddAbilitytoCalculateaCostCalculationGroup.class,
	AddAbilitytoCreateaCostCalculationGroup.class,
	AddAbilitytoDeleteaCostCalculationGroup.class,
	AddAbilitytoEditanExistingCostCalculationGroup.class,
//	AddAbilitytoFilterCostCalculationGroupsDisplayed.class,
	AddCostIntegrationManagerCIMtoCostingModulelist.class,
	AddCostIntegrationManagerCIMtoHomePageCostingsection.class,
	AddSaveCreateNewButtontoNewCalculationGroupScreen.class,
	CalculateNowFunctionality.class,
	CALCULATEPopUp.class,
	CIMaddedtoCustomCostOverheadTaskLists.class,
	CreateNewCostIntegrationManagerScreen.class,
//	//EMAILNotificationsofCostIntegrationManagerCalculations.class,
	HYPERLINKSnCalcStatusColumnToCalculationStatusScreen.class,
	SCHEDULECalculationfunctionality.class,
//	USERSECURITYStandardCustomRoles.class

})

public class TestSuiteForCIM {
}
