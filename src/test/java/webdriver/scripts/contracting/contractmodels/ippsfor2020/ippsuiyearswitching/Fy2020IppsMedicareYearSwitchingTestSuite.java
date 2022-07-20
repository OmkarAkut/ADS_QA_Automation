package webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        Fy2020IppsMedicareYearSwitchingGeneralSectionAds2221.class,
        Fy2020IppsMedicareYearSwitchingOperatingPaymentSectionAds2222.class,
        //Fy2020IppsMedicareYearSwitchingCapitalPaymentSectionAds2223.class,  //needs update due to wcag work
        Fy2020IppsMedicareYearSwitchingCostOutlierPaymentSectionAds1631.class,
        Fy2020IppsMedicareYearSwitchingAddOnTechnologyPaymentSectionAds1432.class,

})

public class Fy2020IppsMedicareYearSwitchingTestSuite {
}
