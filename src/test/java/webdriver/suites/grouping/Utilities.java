package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.datamaintenance.utilities.UtilitiesEncountersWithNegativeChargeBalanceReportAds2427;
import webdriver.scripts.datamaintenance.utilities.UtilitiesEncountersWithNoChargesReportAds1480;
import webdriver.scripts.datamaintenance.utilities.UtilitiesEncountersWithNoEfrsReportAds2429;
import webdriver.scripts.datamaintenance.utilities.UtilitiesEncountersWithZeroChargeBalanceReportAds2428;
import webdriver.scripts.datamaintenance.utilities.UtilitiesReportCheck;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UtilitiesEncountersWithNegativeChargeBalanceReportAds2427.class,
	UtilitiesEncountersWithNoChargesReportAds1480.class,
	UtilitiesEncountersWithNoEfrsReportAds2429.class,
	UtilitiesEncountersWithZeroChargeBalanceReportAds2428.class,
	UtilitiesReportCheck.class,
})
public class Utilities {

}
