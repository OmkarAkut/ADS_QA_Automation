package webdriver.scripts.cim;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	AddAbilitytoCalculateaCostCalculationGroup.class,
	AddAbilitytoCreateaCostCalculationGroup.class,
	AddAbilitytoDeleteaCostCalculationGroup.class,
	AddAbilitytoEditanExistingCostCalculationGroup.class,
	AddAbilitytoFilterCostCalculationGroupsDisplayed.class,
	AddCostIntegrationManagerCIMtoCostingModulelist.class,
	AddCostIntegrationManagerCIMtoHomePageCostingsection.class,
	AddNextCalcDateToCIMLandingPage.class,
	AddSaveCreateNewButtontoNewCalculationGroupScreen.class,
	CalculateNowFunctionality.class,
	CALCULATEPopUp.class,
	CIMaddedtoCustomCostOverheadTaskLists.class,
	CreateNewCostIntegrationManagerScreen.class,
//	//EMAILNotificationsofCostIntegrationManagerCalculations.class,
	EnableDragAndDropFunctionalityForCalculationsInCIMGroup.class,
	HYPERLINKSnCalcStatusColumnToCalculationStatusScreen.class,
	ModificationsToCalculationStatusforCIM.class,
	RECURRINGCalculationfunctionality.class,
	SCHEDULECalculationfunctionality.class,
//	USERSECURITYStandardCustomRoles.class
	UpdateCalcTimesonCIMMainLandingPage.class,
	UpdatesCorrectionsToCIMLanding.class,

})

public class TestSuiteForCIM {
}
