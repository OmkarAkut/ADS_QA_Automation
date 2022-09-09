package webdriver.scripts.regression.generalcalculations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** General Calculations Regression Test Suite - last Update 7-9-21 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        GeneralCalculationsEncounterServiceClassificationSchemeAds2341.class,
        GeneralCalculationsChargeItemServiceClassificationSchemeAds2342.class,
        GeneralCalculationsRunMedicalServiceAssignmentAds2343.class,
        GeneralCalculationsRunPriceListEncounterAssignmentAds2344.class,
        GeneralCalculationsRunPriceListCalculationScenarioAds2274.class,
        GeneralCalculationsRunContractualAllowancesScenarioAds2163.class,
})

public class GeneralCalculationsTestSuite {
}