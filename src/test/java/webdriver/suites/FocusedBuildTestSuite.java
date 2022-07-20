package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020saveedits.Fy2020IppsSavePricingGeneralSectionAds1626;
import webdriver.scripts.costing.RvuMaintenanceAds1492;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175;
import webdriver.scripts.datamaintenance.utilities.UtilitiesReportCheck;
import webdriver.scripts.reporting.ReportingWebiCheck;
import webdriver.scripts.reporting.ReportingAdHocReportDesignIsManualTest;
import webdriver.scripts.reporting.ReportsLibraryVerifyTemplateDirectoryContents;
import webdriver.scripts.status.calculationstatus.CalculationStatusPageSmokeTest;

// The specific build test suite contains tests of the primary purpose/functionality for each main page
// of the application.
// Last update 7-26-2021.
@RunWith(Suite.class)
@Suite.SuiteClasses({


        //reporting-report library
          ReportsLibraryVerifyTemplateDirectoryContents.class,
        //reporting-web intelligence
          ReportingWebiCheck.class,
        //reporting-ad hoc report design
          ReportingAdHocReportDesignIsManualTest.class,
        //reporting-report menu maintenance
        //reporting-report date maintenance
        //reporting-report publication
        //reporting-ad hoc business view maintenance
        //reporting-web intelligence universe maintenance
        //reporting-GEMs inquiry
        //reporting-GEMS analysis
        //reporting-costing reports

        //costing-costing models
        //costing-costing data maintenance
        //costing-RVU Maintenance//
//          RvuMaintenanceAds1492.class,
        //costing-cost model scenario calculation
        //costing-unit cost quick calculation (ucqc)//
          UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175.class,

        //contracting-contract models
          Fy2020IppsSavePricingGeneralSectionAds1626.class,
        //contracting-contracting data maintenance
        //contracting-contractual allowance export
        //contracting-APC allocation

        //episodes-episode models
        //episodes-episode data maintenance

        //data maintenance-maintain data
        //data maintenance-load data
        //data maintenance-utilities
          UtilitiesReportCheck.class,

        //system maintenance-users
        //system maintenance-roles
        //system maintenance-security settings
        //system maintenance-general settings
        //system maintenance-customize maintain data
        //system maintenance-customize task lists
        //system maintenance-terminal server sessions
        //system maintenance-terminal server desktop

        //status-calculation status
        CalculationStatusPageSmokeTest.class,
        //status-import export status
        //status-utility status

        //calculations
//        Ipps2019TransfersAds1337.class,
//        Ipps2020TransfersAds1638.class,
//        Opps2019JPackagingC.class,

        //GeneralCalculationsRunMedicalServiceAssignment.class,
        //GeneralSectionVerifyUserUpdatedValuesAfterSwitchingMedicareYear1514.class,

})

public class FocusedBuildTestSuite {
}
