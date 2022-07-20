package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    CostChangeColumnCheckNumberPlacesAndThousandsCommasAndDecimalPlacesAds1230.class,
    CostChangeColumnsPopulateAfterCalculateAds1230.class,
     //QuickCcCostColumnsForEachCostComponentPopulateAfterCalculate.class,
    //TotalQuickCostColumnPopulatesAfterCalculateStatic.class,
    UcqcCalculateButtonDisabledIfAttemptToCalculateWithUnsavedQuickRvusAds1233.class,
    //UcqcCalculateUcqcWithPublishedCms.class,
    //UcqcCalculateUcqcWithSixDigitQuickRvus.class,
    //UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms.class,
    //UcqcCreateCopyOfCmsToStoreResultsOfUcqcCalculationCmsScenario.class,
    ValidateTotalChangeValueCalculationStaticAds1278.class,

})

public class UcqcCalculationSmokeTestSuite {
}
