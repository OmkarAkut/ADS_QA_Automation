package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.reporting.FlexibleReportsProfitAndLossStatementTesting;
import webdriver.scripts.reporting.ReportLibrarySortingTest;
import webdriver.scripts.reporting.ReportingWebiCheck;
import webdriver.scripts.reporting.ReportsLibraryCreateRenameAndDeleteNewFolderAds1348;
import webdriver.scripts.reporting.ReportsLibraryRunCostingReportFromTemplate;
import webdriver.scripts.reporting.ReportsLibraryTestScript;
import webdriver.scripts.reporting.RunReportLoop;
import webdriver.scripts.reporting.SearchTheReportLibrary;
import webdriver.scripts.reporting.TestADS1617ApplySelectionsforStandardReport;
import webdriver.scripts.reporting.TestAbilityToCreateNewStandardCostingReport;
import webdriver.scripts.reporting.TestAbilitytoCreateExistingStandardCostingReport;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FlexibleReportsProfitAndLossStatementTesting.class,
	ReportingWebiCheck.class,
	ReportLibrarySortingTest.class,
	ReportsLibraryCreateRenameAndDeleteNewFolderAds1348.class,
	ReportsLibraryRunCostingReportFromTemplate.class,
	ReportsLibraryTestScript.class,
	RunReportLoop.class,
	SearchTheReportLibrary.class,
	TestAbilitytoCreateExistingStandardCostingReport.class,
	TestAbilityToCreateNewStandardCostingReport.class,
	TestADS1617ApplySelectionsforStandardReport.class,

})
public class ReportLibrary {

}
