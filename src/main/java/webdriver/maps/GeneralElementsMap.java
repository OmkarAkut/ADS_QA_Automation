package webdriver.maps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

/*
This map file includes all general elements found in Evolve.
Section 1: Login Page
Section 2: Global Header
Section 3: User Menu
Section 4: Main Tabs
Section 5: Sub Tabs
Section 6: Landing Page (Quick Link Bubbles)
 */

public class GeneralElementsMap extends MapConfig {

    /*************************LogIn Page********************************/
    @FindBy(xpath = "//*[contains(@class,'loginText')]")
    private WebElement loginPageHeaderLogIn;
    public WebElement getLoginPageHeaderLogIn() {return loginPageHeaderLogIn;}
    @FindBy(xpath = "//*[contains(@class,'todayDate')]")
    private WebElement loginPageDate;
    public WebElement getLoginPageDate() {return loginPageDate;}
    @FindBy(xpath = "//*[contains(@class,'signIn')]")
    private WebElement loginPageLogInHeaderSignIn;
    public WebElement getLoginPageLogInHeaderSignIn() {return loginPageLogInHeaderSignIn;}
    @FindBy(xpath = "//*[contains(@class,'usernameField')]")
    private WebElement loginPageFieldUsername;
    public WebElement getLoginPageFieldUsername() {return loginPageFieldUsername;}
    @FindBy(id = "password-inputRow")
    private WebElement loginPageFieldPassword;
    public WebElement getLoginPageFieldPassword() {return loginPageFieldPassword;}
    @FindBy(xpath = "//*[contains(@class,'loginBtn')]")
    private WebElement loginPageButtonLogIn;
    public WebElement getLoginPageButtonLogIn() {return loginPageButtonLogIn;}
    @FindBy(id = "releaseVersionNo-body")
    private WebElement loginPageReleaseVersion;
    public WebElement getLoginPageReleaseVersion() {return loginPageReleaseVersion;}
    @FindBy(xpath = "//img[contains(@class,'Logo')]")
    private WebElement loginPageLogo;
    public WebElement getLoginPageLogo() {return loginPageLogo;}
    /*************************End Log In Page********************************/

    /*************************Global Header********************************/
    @FindBy(xpath = "//*[contains(@src,'Harris_Affinity_Logo.png')]")
    private WebElement globalHeaderLogo;
    public WebElement getGlobalHeaderLogo() {return globalHeaderLogo;}
   // @FindBy(xpath = "//*[contains(@class,'help')]")
    @FindBy(xpath = "//*[contains(@class,'help')]/a")//Shilpa 09.09.2022 updated Xpath
    private WebElement globalHeaderButtonHelp;
    public WebElement getGlobalHeaderButtonHelp() {return globalHeaderButtonHelp;}
    @FindBy(xpath = "//*[contains(@class,'contactUs')]")
    private WebElement globalHeaderButtonContactUs;
    public WebElement getGlobalHeaderButtonContactUs() {return globalHeaderButtonContactUs;}
    @FindBy(xpath = "//*[contains(@class,'logout')]")
    private WebElement globalHeaderButtonLogout;
    public WebElement getGlobalHeaderButtonLogout() {return globalHeaderButtonLogout;}
    /*************************End Global Header********************************/

    /*************************User Menu********************************/
    @FindBy(id = "dropdown")
    private WebElement userDropdown;
    public WebElement getUserDropdown() {return userDropdown;}

    @FindBy(id = "changePassword")
    private WebElement userDropdownChangePassword;
    public WebElement getUserDropdownChangePassword() {return userDropdownChangePassword;}

    @FindBy(xpath = "//*[contains(@class,'headerMenuBar')]/descendant::*[contains(text(),'YES')]")
    private WebElement userDropdownUseOurTermsYes;
    public WebElement getUserDropdownUseOurTermsYes() {return userDropdownUseOurTermsYes;}

    @FindBy(xpath = "//*[contains(@class,'headerMenuBar')]/descendant::*[contains(text(),'NO')]")
    private WebElement userDropdownUseOurTermsNo;
    public WebElement getUserDropdownUseOurTermsNo() {return userDropdownUseOurTermsNo;}

    @FindBy(xpath = "//*[contains(@class,'headerMenuBar')]/descendant::*[contains(text(), 'Log Out')]")
    private WebElement userDropdownLogOut;
    public WebElement getUserDropdownLogOut() {return userDropdownLogOut;}

    @FindBy(xpath = "//*[contains(@class,'headerMenuBar')]/descendant::*[contains(text(), 'Terms of Use')]")
    private WebElement userDropdownTermsOfUse;
    public WebElement getUserDropdownTermsOfUse() {return userDropdownTermsOfUse;}

    @FindBy(xpath = "//*[contains(@onclick,'/alliance-help/Alliance.htm#../Content/contact.htm')]")
    private WebElement getContactUsPageHelpLink;
    public WebElement getContactUsPageHelpLink() {return getContactUsPageHelpLink;}

    @FindBy(linkText = "https://support.harrishealthcare.com/Affinity/")
    private WebElement getContactUsPageSupportPortalLink;
    public WebElement getContactUsPageSupportPortalLink() {return getContactUsPageSupportPortalLink;}

    @FindBy(linkText = "www.HarrisAffinity.com")
    private WebElement getContactUsPageHarrisAffinityLink;
    public WebElement getContactUsPageHarrisAffinityLink() {return getContactUsPageHarrisAffinityLink;}

    /*************************End User Menu********************************/

    /*************************Main Tabs********************************/
    //Analytics Tab
    @FindBy(id = "analytics")
    private WebElement analyticsTab;
    public WebElement getAnalyticsTab() {return analyticsTab;}

    //Reporting Tab
    @FindBy(id = "reporting")
    private WebElement reportingTab;
    public WebElement getReportingTab() {return reportingTab;}

    //Costing Tab
    @FindBy(id = "costing")
    private WebElement costingTab;
    public WebElement getCostingTab() {return costingTab;}

    //Contracting Tab
    @FindBy(id = "contracting")
    private WebElement contractingTab;
    public WebElement getContractingTab() {return contractingTab;}

    //Episodes Tab
    @FindBy(id = "episode")
    private WebElement episodesTab;
    public WebElement getEpisodesTab() {return episodesTab;}

    //Budgeting Tab
    @FindBy(id = "budgeting")
    private WebElement budgetingTab;
    public WebElement getBudgetingTab() {return budgetingTab;}

    //Data Maintenance Tab
    @FindBy(id = "datamaintenance")
    private WebElement dataMaintenanceTab;
    public WebElement getDataMaintenanceTab() {return dataMaintenanceTab;}

    //System Maintenance Tab
    @FindBy(id = "systemmaintenance")
    private WebElement systemMaintenanceTab;
    public WebElement getSystemMaintenanceTab() {return systemMaintenanceTab;}

    //Status
    @FindBy(id = "status")
    private WebElement statusTab;
    public WebElement getStatusTab() {return statusTab;}
    /*************************End Main Tabs********************************/

    /***********************Sub Tabs**********************************/
    //Analytics > Executive Dashboards
    @FindBy(id = "executivedashboards")
    private WebElement executiveDashboardSubTab;
    public WebElement getExecutiveDashboardSubTab(){return executiveDashboardSubTab;}

    //Analytics > Analytic Dashboards
    @FindBy(id = "analystdashboards")
    private WebElement analyticDashboardSubTab;
    public WebElement getAnalyticDashboardSubTab(){return analyticDashboardSubTab;}

    //Analytics > Analytic Refresh Scenarios
    @FindBy(id = "analyticsextracts")
    private WebElement analyticRefreshScenariosSubTab;
    public WebElement getAnalyticRefreshScenariosSubTab(){return analyticRefreshScenariosSubTab;}

    //Analytics > Analytics Administration
    @FindBy(id = "analyticsadministration")
    private WebElement AnalyticsAdministrationSubTab;
    public WebElement getAnalyticsAdministrationSubTab(){return AnalyticsAdministrationSubTab;}

    //Analytics > Customize Analytics
    @FindBy(id = "customizeanalytics")
    private WebElement CustomizeAnalyticsSubTab;
    public WebElement getCustomizeAnalyticsSubTab() {return CustomizeAnalyticsSubTab;}

    //Analytics > Customize Analytics Sessions
    @FindBy(id = "customizeanalyticssessions")
    private WebElement customizeAnalyticsSessionsSubTab;
    public WebElement getCustomizeAnalyticsSessionsSubTab(){return customizeAnalyticsSessionsSubTab;}

    //Analytics > Analytics Server Desktop
    @FindBy(id = "analyticsserverdesktop")
    private WebElement analyticsServerDesktopSubTab;
    public WebElement getAnalyticsServerDesktopSubTab() {return analyticsServerDesktopSubTab;}

    //Analytics > Analytics Server Desktop Sessions
    @FindBy(id = "analyticsserverdesktopsessions")
    private WebElement analyticsServerDesktopSessionsSubTab;
    public WebElement getAnalyticsServerDesktopSessionsSubTab(){return analyticsServerDesktopSessionsSubTab;}

    //Reporting > Report Library
    @FindBy(id = "reportlibrary")
    private WebElement reportingLibrarySubTab;
    public WebElement getReportingLibrarySubTab(){return reportingLibrarySubTab;}

    //Reporting > Web Intelligence
    @FindBy(id = "webiuniverse")
    private WebElement webIntelligenceSubTab;
    public WebElement getWebIntelligenceSubTab(){return webIntelligenceSubTab;}

    //Reporting > Ad Hoc Report Design
    @FindBy(id = "designadhocreports")
    private WebElement adHocReportDesignSubTab;
    public WebElement getAdHocReportDesignSubTab(){return adHocReportDesignSubTab;}

    //Reporting > Report Menu Maintenance
    @FindBy(id = "reportmenumaintenance")
    private WebElement reportMenuMaintenanceSubTab;
    public WebElement getReportMenuMaintenanceSubTab(){return reportMenuMaintenanceSubTab; }

    //Reporting > Report Date Maintenance
    @FindBy(id = "reportdatemaintenance")
    private WebElement reportDateMaintenanceSubTab;
    public WebElement getReportDateMaintenanceSubTab(){return reportDateMaintenanceSubTab;}

    //Reporting > Report Publication
    @FindBy(id = "publishstandardflex")
    private WebElement reportPublicationSubTab;
    public WebElement getReportPublicationSubTab(){return reportPublicationSubTab;}

    //Reporting > Ad Hoc Business View Maintenance
    @FindBy(id = "maintainadhocbusinessreviews")
    private WebElement adHocBusinessViewMaintenanceSubTab;
    public WebElement getAdHocBusinessViewMaintenanceSubTab(){return adHocBusinessViewMaintenanceSubTab;}

    //Reporting > Web Intelligence Universe Maintenance
    @FindBy(id = "maintainwebiuniverse")
    private WebElement webIntelligenceUniverseMaintenanceSubTab;
    public WebElement getWebIntelligenceUniverseMaintenanceSubTab(){return webIntelligenceUniverseMaintenanceSubTab;}

    //Reporting > ICD9/ICD10 GEMs Inquiry
    //@FindBy(id = "icd9")  //icd9/icd10gemsinquiry
    //@FindBy(id = "icd9/icd10gemsinquiry")  //updated id 9-23-20    ]
    @FindBy(xpath = "//*[contains(text(),'ICD9/ICD10 GEMs Inquiry')]")  //updated by Omkar on 20/6/2022
    private WebElement icd9icd10GemsInquirySubTab;
    public WebElement getIcd9icd10GemsInquirySubTab(){return icd9icd10GemsInquirySubTab;}

    //Reporting > ICD9/ICD10 GEMs Analysis
    @FindBy(xpath = "//*[contains(text(),'ICD9/ICD10 GEMs Analysis')]") //used linkText here as this element has the same ID and xpath as ICD9/ICD10 GEMs Inquiry
    private WebElement icd9icd10GemsAnalysisSubTab;
    public WebElement getIcd9icd10GemsAnalysisSubTab(){return icd9icd10GemsAnalysisSubTab;}

    //Reporting > Costing Reports
    @FindBy(id = "Costing Reports")
    private WebElement costingReportsSubTab;
    public WebElement getCostingReportsSubTab(){return costingReportsSubTab;}

    //Costing > Costing Models
    @FindBy(id = "modelcosts")
    private WebElement costingModelsSubTab;
    public WebElement getCostingModelsSubTab(){return costingModelsSubTab; }

    //Costing > Costing Data Maintenance
    @FindBy(id = "costingmaintaindata")
    private WebElement costingDataMaintenanceSubTab;
    public WebElement getCostingDataMaintenanceSubTab(){return costingDataMaintenanceSubTab;}

    //Costing > RVU Maintenance
    @FindBy(id = "rvusmaintenance")
    private WebElement rvuMaintenanceSubTab;
    public WebElement getRVUMaintenanceSubTab(){return rvuMaintenanceSubTab;}

    //Costing > Cost Model Scenario Calculation
    @FindBy(id = "costingmodelscenario")
    private WebElement costModelScenarioCalculationSubTab;
    public WebElement getCostModelScenarioCalculationSubTab(){return costModelScenarioCalculationSubTab;}

    //Costing > Unit Cost Quick Calculation
    @FindBy(id = "ucqc")
    private WebElement unitCostQuickCalculationSubTab;
    public WebElement getUnitCostQuickCalculationSubTab(){return unitCostQuickCalculationSubTab;}

    //Contracting > Contract Models
    @FindBy(id = "modelcontracts")
    private WebElement contractModelsSubTab;
    public WebElement getContractModelsSubTab(){return contractModelsSubTab;}

    //Contracting > Contracting Data Maintenance
    @FindBy(id = "contractingmaintaindata")
    private WebElement contractingDataMaintenanceSubTab;
    public WebElement getContractingDataMaintenanceSubTab(){return contractingDataMaintenanceSubTab;}

    //Contracting > Contractual Allowance Export
    @FindBy(id = "exportcontractualallowances")
    private WebElement contractualAllowanceExportSubTab;
    public WebElement getContractualAllowanceExportSubTab(){return contractualAllowanceExportSubTab;}

    //Contracting > APC Allocation
    @FindBy(id = "apcallocation")
    private WebElement apcAllocationSubTab;
    public WebElement getApcAllocationSubTab(){return apcAllocationSubTab;}

    //Episodes > Episode Models
    @FindBy(id = "modelepisode")
    private WebElement episodeModelsSubTab;
    public WebElement getEpisodeModelsSubTab(){return episodeModelsSubTab;}

    //Episodes > Episode Data Maintenance
    @FindBy(id = "episodemaintaindata")
    private WebElement episodeDataMaintenanceSubTab;
    public WebElement getEpisodeDataMaintenanceSubTab(){return episodeDataMaintenanceSubTab;}

    //Budgeting > Budgeting
    @FindBy(id = "budgeting_sub")
    private WebElement budgetingSubTab;
    public WebElement getBudgetingSubTab(){return budgetingSubTab;}

    //Data Maintenance > Maintain Data
    @FindBy(id = "maintaindata")
    private WebElement MaintainDataSubTab;
    public WebElement getMaintainDataSubTab(){return MaintainDataSubTab;}

    //Data Maintenance > Load Data
    @FindBy(id = "loaddata")
    private WebElement loadDataSubTab;
    public WebElement getLoadDataSubTab(){return loadDataSubTab;}

    //Data Maintenance > Utilities
    @FindBy(id = "datamaintenanceutilities")
    private WebElement utilitiesSubTab;
    public WebElement getUtilitiesSubTab(){return utilitiesSubTab;}

    //System Maintenance > Users
    @FindBy(id = "users")
    private WebElement usersSubTab;
    public WebElement getUsersSubTab(){return usersSubTab;}

    //System Maintenance > Roles
    @FindBy(id = "roles")
    private WebElement rolesSubTab;
    public WebElement getRolesSubTab(){return rolesSubTab;}

    //System Maintenance > Security Settings
    @FindBy(id = "security")
    private WebElement securitySettingsSubTab;
    public WebElement getSecuritySettingsSubTab(){return securitySettingsSubTab;}

    //System Maintenance > General Settings
    @FindBy(id = "generalsettings")
    private WebElement generalSettingsSubTab;
    public WebElement getGeneralSettingsSubTab(){return generalSettingsSubTab;}

    //System Maintenance > Customize Maintain Data
    @FindBy(id = "customizemaintaindata")
    private WebElement customizeMaintainDataSubTab;
    public WebElement getCustomizeMaintainDataSubTab(){return customizeMaintainDataSubTab;}

    //System Maintenance > Customize Task List
    @FindBy(id = "customizetasklist")
    private WebElement customizeTaskListSubTab;
    public WebElement getCustomizeTaskListSubTab(){return customizeTaskListSubTab;}

    //System Maintenance > Terminal Server Sessions
    @FindBy(id = "terminalserversessions")
    private WebElement terminalServerSessionsSubTab;
    public WebElement getTerminalServerSessionsSubTab(){return terminalServerSessionsSubTab;}

    //System Maintenance > Terminal Server Desktop
    @FindBy(id = "terminalserverdesktop")
    private WebElement terminalServerDesktopSubTab;
    public WebElement getTerminalServerDesktopSubTab(){return terminalServerDesktopSubTab;}

    //Status > Calculation Status
    @FindBy(id = "statuscalculationstatus")
    private WebElement calculationStatusSubTab;
    public WebElement getCalculationStatusSubTab(){return calculationStatusSubTab;}

    //Status > Import/Export Status
    @FindBy(id = "statusimportexportstatus")
    private WebElement importExportStatusSubTab;
    public WebElement getImportExportStatusSubTab(){return importExportStatusSubTab;}

    //Status > Utility Status
    @FindBy(id = "statusutilitystatus")
    private WebElement utilityStatusSubTab;
    public WebElement getUtilityStatusSubTab(){return utilityStatusSubTab;}
    /***********************End Sub Tabs**********************************/

    /***********************Landing Page**********************************/
    //Analytics
    @FindBy(xpath = "//*[contains(@class,'analytics')]")
    private WebElement landingPageBubbleAnalytics;
    public WebElement getLandingPageBubbleAnalytics() {
        return landingPageBubbleAnalytics;
    }
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Analytics')]")
    private WebElement landingPageBubbleAnalyticsHeader;
    public WebElement getLandingPageBubbleAnalyticsHeader() {return landingPageBubbleAnalyticsHeader;}
    @FindBy(xpath = "//*[contains(@class,'bubbleContent')][contains(@id,'analytics')]")
    private WebElement landingPageBubbleAnalyticsContent;
    public WebElement getLandingPageBubbleAnalyticsContent() {return landingPageBubbleAnalyticsContent;}
    @FindBy(xpath = "//*[contains(text(),'Analyze patient data from your Decision Support database using predefined or self defined...')]")
    private WebElement landingPageBubbleAnalyticsContentText;
    public WebElement getLandingPageBubbleAnalyticsContentText() {return landingPageBubbleAnalyticsContentText;}
    @FindBy(xpath = "//*[contains(@src,'M-analytics.png')]")
    private WebElement landingPageBubbleAnalyticsImage;
    public WebElement getLandingPageBubbleAnalyticsImage() {return landingPageBubbleAnalyticsImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Executive Dashboards')]")
    private WebElement landingPageBubbleAnalyticsQuickLinkExecutiveDashboard;
    public WebElement getLandingPageBubbleAnalyticsQuickLinkExecutiveDashboard() {return landingPageBubbleAnalyticsQuickLinkExecutiveDashboard;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Analytic Dashboards')]")
    private WebElement landingPageBubbleAnayticsQuickLinkAnalyticDashobaords;
    public WebElement getLandingPageBubbleAnayticsQuickLinkAnalyticDashobaords() {return landingPageBubbleAnayticsQuickLinkAnalyticDashobaords;}

    //Reporting
    @FindBy(xpath = "//*[contains(@class,'reporting')]")
    private WebElement landingPageBubbleReporting;
    public WebElement getLandingPageBubbleReporting() {return landingPageBubbleReporting;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Reporting')]")
    private WebElement landingPageBubbleReportingHeader;
    public WebElement getLandingPageBubbleReportingHeader() {return landingPageBubbleReportingHeader;}
    @FindBy(xpath = "//*[contains(@class,'bubbleContent')][contains(@id,'reporting')]")
    private WebElement landingPageBubbleReportingContent;
    public WebElement getLandingPageBubbleReportingContent() {return landingPageBubbleReportingContent;}
    @FindBy(xpath = "//*[contains(text(),'Run standard reports, flex reports, and ad hoc reports using the information that you have stored...')]")
    private WebElement landingPageBubbleReportingContentText;
    public WebElement getLandingPageBubbleReportingContentText() {return landingPageBubbleReportingContentText;}
    @FindBy(xpath = "//*[contains(@src,'M-reporting.png')]")
    private WebElement landingPageBubbleReportingImage;
    public WebElement getLandingPageBubbleReportingImage() {return landingPageBubbleReportingImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Report Library')]")
    private WebElement landingPageBubbleReportingQuickLinkReportLibrary;
    public WebElement getLandingPageBubbleReportingQuickLinkReportLibrary() {return landingPageBubbleReportingQuickLinkReportLibrary;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Web Intelligence')]")
    private WebElement landingPageBubbleReportingQuickLinkWebIntelligence;
    public WebElement getLandingPageBubbleReportingQuickLinkWebIntelligence() {return landingPageBubbleReportingQuickLinkWebIntelligence;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Ad Hoc Report Design')]")
    private WebElement landingPageBubbleReportingQuickLinkAdHocReportDesign;
    public WebElement getLandingPageBubbleReportingQuickLinkAdHocReportDesign() {return landingPageBubbleReportingQuickLinkAdHocReportDesign;}

    //Costing
    @FindBy(xpath = "//*[contains(@class,'costing')]")
    private WebElement landingPageBubbleCosting;
    public WebElement getLandingPageBubbleCosting() {return landingPageBubbleCosting;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Costing')]")
    private WebElement landingPageBubbleCostingHeader;
    public WebElement getLandingPageBubbleCostingHeader() {return landingPageBubbleCostingHeader;}
    @FindBy(xpath = "//*[contains(@class,'bubbleContent')][contains(@id,'costing')]")
    private WebElement landingPageBubbleCostingContent;
    public WebElement getLandingPageBubbleCostingContent() {return landingPageBubbleCostingContent;}
    @FindBy(xpath = "//*[contains(text(),'Define contract terms between buyers and sellers of services.')]")
    private WebElement landingPageBubbleCostingContentText;
    public WebElement getLandingPageBubbleCostingContentText() {return landingPageBubbleCostingContentText;}
    @FindBy(xpath = "//*[contains(@src,'M-costing.png')]")
    private WebElement landingPageBubbleCostingImage;
    public WebElement getLandingPageBubbleCostingImage() {return landingPageBubbleCostingImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Costing Models')]")
    private WebElement landingPageBubbleCostingQuickLinkCostingModels;
    public WebElement getLandingPageBubbleCostingQuickLinkCostingModels() { return landingPageBubbleCostingQuickLinkCostingModels;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Costing Data Maintenance')]")
    private WebElement landingPageBubbleCostingQuickLinkCostingDataMaintenance;
    public WebElement getLandingPageBubbleCostingQuickLinkCostingDataMaintenance() {return landingPageBubbleCostingQuickLinkCostingDataMaintenance;}
    @FindBy(xpath = "//h1[text()='Costing']/../descendant::div[@class='bubbleQuickLinks']/descendant::a[text()='Unit Cost Quick Calculation']")
    private WebElement landingPageBubbleCostingUnitCostQuickCalculation;
    public WebElement getLandingPageBubbleCostingQuickLinkUnitCostQuickCalculation() {return landingPageBubbleCostingUnitCostQuickCalculation;}

    //Contracting
    @FindBy(xpath = "//*[contains(@class,'contracting')]")
    private WebElement landingPageBubbleContracting;
    public WebElement getLandingPageBubbleContracting() {return landingPageBubbleContracting;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Contracting')]")
    private WebElement landingPageBubbleContractingHeader;
    public WebElement getLandingPageBubbleContractingHeader() {return landingPageBubbleContractingHeader;}
    @FindBy(xpath = "//*[contains(@class,'bubbleContent')][contains(@id,'contracting')]")
    private WebElement landingPageBubbleContractingContent;
    public WebElement getLandingPageBubbleContractingContent() {return landingPageBubbleContractingContent;}
    @FindBy(xpath = "//*[contains(text(),'Define contract terms between buyers and sellers of services.')]")
    private WebElement landingPageBubbleContractingContentText;
    public WebElement getLandingPageBubbleContractingContentText() {return landingPageBubbleContractingContentText;}
    @FindBy(xpath = "//*[contains(@src,'M-contracting.png')]")
    private WebElement landingPageBubbleContractingImage;
    public WebElement getLandingPageBubbleContractingImage() {return landingPageBubbleContractingImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Contract Models')]")
    private WebElement landingPageBubbleContractingQuickLinkContractModels;
    public WebElement getLandingPageBubbleContractingQuickLinkContractModels() {return landingPageBubbleContractingQuickLinkContractModels;}
    @FindBy(xpath = "//*[contains(@class,'bubbleSubContainer')]/descendant::a[contains(text(),'Contracting Data Maintenance')]")
    private WebElement landingPageBubbleContractingQuickLinkContractingDataMaintenance;
    public WebElement getLandingPageBubbleContractingQuickLinkContractingDataMaintenance() {return landingPageBubbleContractingQuickLinkContractingDataMaintenance;}

    //Episodes
    @FindBy(xpath = "//*[contains(@class,'episode')]")
    private WebElement landingPageBubbleEpisode;
    public WebElement getLandingPageBubbleEpisode() {return landingPageBubbleEpisode;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Episode')]")
    private WebElement landingPageBubbleEpisodeHeader;
    public WebElement getLandingPageBubbleEpisodeHeader() {return landingPageBubbleEpisodeHeader;}
    @FindBy(xpath = "//*[contains(@src,'S-episode.png')]")
    private WebElement landingPageBubbleEpisodeImage;
    public WebElement getLandingPageBubbleEpisodeImage() {return landingPageBubbleEpisodeImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Episode Models')]")
    private WebElement landingPageBubbleEpisodeQuickLinkEpisodeModels;
    public WebElement getLandingPageBubbleEpisodeQuickLinkEpisodeModels() {return landingPageBubbleEpisodeQuickLinkEpisodeModels;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Episode Data Maintenance')]")
    private WebElement landingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance;
    public WebElement getLandingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance() {return landingPageBubbleEpisodeQuickLinkEpisodeDataMaintenance;}

    //Budgeting
    @FindBy(xpath = "//*[contains(@class,'budgeting')]")
    private WebElement landingPageBubbleBudgeting;
    public WebElement getLandingPageBubbleBudgeting() {return landingPageBubbleBudgeting;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Budgeting')]")
    private WebElement landingPageBubbleBudgetingHeader;
    public WebElement getLandingPageBubbleBudgetingHeader() {return landingPageBubbleBudgetingHeader;}
    @FindBy(xpath = "//*[contains(@src,'S-budgeting.png')]")
    private WebElement landingPageBubbleBudgetingImage;
    public WebElement getLandingPageBubbleBudgetingImage() {return landingPageBubbleBudgetingImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Budgeting')]")
    private WebElement landingPageBubbleBudgetingQuickLinkBudgeting;
    public WebElement getLandingPageBubbleBudgetingQuickLinkBudgeting() {return landingPageBubbleBudgetingQuickLinkBudgeting;}

    //Data Maintenance
    @FindBy(xpath = "//*[contains(@class,'datamaintenance')]")
    private WebElement landingPageBubbleDataMaintenance;
    public WebElement getLandingPageBubbleDataMaintenance() {return landingPageBubbleDataMaintenance;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'Data Maintenance')]")
    private WebElement landingPageBubbleDataMaintenanceHeader;
    public WebElement getLandingPageBubbleDataMaintenanceHeader() {return landingPageBubbleDataMaintenanceHeader;}
    @FindBy(xpath = "//*[contains(@src,'S-datamaintenance.png')]")
    private WebElement landingPageBubbleDataMaintenanceImage;
    public WebElement getLandingPageBubbleDataMaintenanceImage() {return landingPageBubbleDataMaintenanceImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Maintain Data')]")
    private WebElement landingPageBubbleDataMaintenanceQuickLinkMaintainData;
    public WebElement getLandingPageBubbleDataMaintenanceQuickLinkMaintainData() {return landingPageBubbleDataMaintenanceQuickLinkMaintainData;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Load Data')]")
    private WebElement landingPageBubbleDataMaintenanceQuickLinkLoadData;
    public WebElement getLandingPageBubbleDataMaintenanceQuickLinkLoadData() {return landingPageBubbleDataMaintenanceQuickLinkLoadData;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Utilities')]")
    private WebElement landingPageBubbleDataMaintenanceQuickLinkUtilities;
    public WebElement getLandingPageBubbleDataMaintenanceQuickLinkUtilities() {return landingPageBubbleDataMaintenanceQuickLinkUtilities;}

    //System Maintenance
    @FindBy(xpath = "//*[contains(@class,'systemmaintenance')]")
    private WebElement landingPageBubbleSystemMaintenance;
    public WebElement getLandingPageBubbleSystemMaintenance() {return landingPageBubbleSystemMaintenance;}
    @FindBy(xpath = "//div[contains(@class, 'bubble')]/*[contains(text(),'System Maintenance')]")
    private WebElement landingPageBubbleSystemMaintenanceHeader;
    public WebElement getLandingPageBubbleSystemMaintenanceHeader() {return landingPageBubbleSystemMaintenanceHeader;}
    @FindBy(xpath = "//*[contains(@src,'S-systemmaintenance.png')]")
    private WebElement landingPageBubbleSystemMaintenanceImage;
    public WebElement getLandingPageBubbleSystemMaintenanceImage() {return landingPageBubbleSystemMaintenanceImage;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Users')]")
    private WebElement landingPageBubbleSystemMaintenanceQuickLinkUsers;
    public WebElement getLandingPageBubbleSystemMaintenanceQuickLinkUsers() {return landingPageBubbleSystemMaintenanceQuickLinkUsers;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Roles')]")
    private WebElement landingPageBubbleSystemMaintenanceQuickLinkRoles;
    public WebElement getLandingPageBubbleSystemMaintenanceQuickLinkRoles() {return landingPageBubbleSystemMaintenanceQuickLinkRoles;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Security Settings')]")
    private WebElement landingPageBubbleSystemMaintenanceQuickLinkSecuritySettings;
    public WebElement getLandingPageBubbleSystemMaintenanceQuickLinkSecuritySettings() {return landingPageBubbleSystemMaintenanceQuickLinkSecuritySettings;}
    @FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'General Settings')]")
    private WebElement landingPageBubbleSystemMaintenanceQuickLinkGeneralSettings;
    public WebElement getLandingPageBubbleSystemMaintenanceQuickLinkGeneralSettings() {return landingPageBubbleSystemMaintenanceQuickLinkGeneralSettings;}
    /***********************End Landing Page**********************************/
}
