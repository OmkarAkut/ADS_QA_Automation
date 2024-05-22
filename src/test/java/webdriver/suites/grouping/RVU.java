package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.costing.OverwriteRvuNoCMSResults;
import webdriver.scripts.costing.RVUCalculationScenario;
import webdriver.scripts.costing.RVUContainerDeleteFilteredData;
import webdriver.scripts.costing.RVUExportImportFunction;
import webdriver.scripts.costing.RvuMaintenanceAds1492;
import webdriver.scripts.costing.ValidateHelpLinkHideTabRvuMaintenance;
import webdriver.scripts.costing.ValidatePaginationOnTheCostingRVUMaintenancepage;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	OverwriteRvuNoCMSResults.class,
	RVUContainerDeleteFilteredData.class,
	RVUCalculationScenario.class,
	RVUExportImportFunction.class,
	RvuMaintenanceAds1492.class,
	ValidateHelpLinkHideTabRvuMaintenance.class,
	ValidatePaginationOnTheCostingRVUMaintenancepage.class,


})
public class RVU {

}
