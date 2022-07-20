package webdriver.maps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

public class SystemMaintenanceMap extends MapConfig {

    // ===== System Maintenance Tab >  Users Page ===== //

    @FindBy(xpath = "//div[contains(@id,'systemmaintenance_users')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-inner']/span[@class='x-column-header-text']")
    private WebElement usersPageTableCornerCell;
    public WebElement getUsersPageTableCornerCell() {return usersPageTableCornerCell;}

    @FindBy(xpath = "//*[contains(@onclick, 'susrls.htm') and @class='listhelpLnk']")
    private WebElement usersPageHelpLink;
    public WebElement getUsersPageHelpLink() {return usersPageHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='New']")
    private WebElement usersPageButtonNew;
    public WebElement getUsersPageButtonNew() {return usersPageButtonNew;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Edit']")
    private WebElement usersPageButtonEdit;
    public WebElement getUsersPageButtonEdit() {return usersPageButtonEdit;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Filter']")
    private WebElement usersPageButtonFilter;
    public WebElement getUsersPageButtonFilter() {return usersPageButtonFilter;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Clear Filter']")
    private WebElement usersPageButtonClearFilter;
    public WebElement getUsersPageButtonClearFilter() {return usersPageButtonClearFilter;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Sync to BOE Server']")
    private WebElement usersPageButtonSyncToBoeServer;
    public WebElement getUsersPageButtonSynchToBoeServer() {return usersPageButtonSyncToBoeServer;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Import']")
    private WebElement usersPageButtonImport;
    public WebElement getUsersPageButtonImport() {return usersPageButtonImport;}

    @FindBy(xpath = "//*[contains(@id,'userLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Export']")
    private WebElement usersPageButtonExport;
    public WebElement getUsersPageButtonExport() {return usersPageButtonExport;}


    // ===== End  Users Page ===== //

    // ===== System Maintenance Tab >  Roles Page ===== //

    @FindBy(xpath = "//div[contains(@id,'systemmaintenance_roles')]/../descendant::div[contains(@class,'column-header-align-center')]/div/span[@class='x-column-header-text']")
    private WebElement rolesPageTableCornerCell;
    public WebElement getRolesPageTableCornerCell() {return rolesPageTableCornerCell;}

    @FindBy(xpath = "//*[contains(@onclick, 'susrrolls.htm') and @class='listhelpLnk']")
    private WebElement rolesHelpLink;
    public WebElement getRolesPageHelpLink() {return rolesHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'rolesLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='New']")
    private WebElement rolesPageButtonNew;
    public WebElement getRolesPageButtonNew() {return rolesPageButtonNew;}

    @FindBy(xpath = "//*[contains(@id,'rolesLayoutPage') and contains(@id,'targetEl')]/descendant::span[text()='Edit']")
    private WebElement rolesPageButtonEdit;
    public WebElement getRolesPageButtonEdit() {return rolesPageButtonEdit;}

    // ===== End  Roles Page ===== //

    // ===== System Maintenance Tab >  Security Settings Page ===== //

    @FindBy(xpath = "//*[contains(@id,'security') and contains(@id,'targetEl')]/descendant::span[text()='Save']")
    private WebElement securitySettingsPageButtonSave;
    public WebElement getSecuritySettingsPageButtonSave() {return securitySettingsPageButtonSave;}

    @FindBy(xpath = "//*[contains(@onclick, 'ssetfd.htm') and @class='listhelpLnk']")
    private WebElement securitySettingsHelpLink;
    public WebElement getSecuritySettingsPageHelpLink() {return securitySettingsHelpLink;}

    @FindBy(xpath = "//*[@name='authenticationType']")
    private WebElement authenticationType;
    public WebElement getSecuritySettingsPageFormFieldAuthenticationType() {return authenticationType;}

    @FindBy(xpath = "//*[@name='inactiveTimePeriod']")
    private WebElement inactiveTimePeriod;
    public WebElement getSecuritySettingsPageFormFieldInactivityTimeOutPeriod() {return inactiveTimePeriod;}

    @FindBy(xpath = "//*[@name='failedLoginAttmpts']")
    private WebElement failedLoginAttmpts;
    public WebElement getSecuritySettingsPageFormFieldFailedLoginAttempts() {return failedLoginAttmpts;}

    @FindBy(xpath = "//*[@name='autoUnlockScheduledDelay']")
    private WebElement autoUnlockScheduledDelay;
    public WebElement getSecuritySettingsPageFormFieldAutomaticAccountReactivationPeriod() {return autoUnlockScheduledDelay;}

    @FindBy(xpath = "//*[@name='passwordMinLength']")
    private WebElement passwordMinLength;
    public WebElement getSecuritySettingsPageFormFieldPasswordMinimumLength() {return passwordMinLength;}

    @FindBy(xpath = "//*[@name='pwdExpirationDays']")
    private WebElement pwdExpirationDays;
    public WebElement getSecuritySettingsPageFormFieldPasswordExpirationPeriod() {return pwdExpirationDays;}

    @FindBy(xpath = "//*[@name='allowedLoginAftExp']")
    private WebElement allowedLoginAftExp;
    public WebElement getSecuritySettingsPageFormFieldPasswordGraceLoginsAfterExpirationPeriod() {return allowedLoginAftExp;}

    @FindBy(xpath = "//*[contains(@id,'isAtleastOneLetter')]")
    private WebElement isAtleastOneLetter;
    public WebElement getSecuritySettingsPageCheckboxMustIncludeAtLeastOneLetter() {return isAtleastOneLetter;}

    @FindBy(xpath = "//*[contains(@id,'isBothUpperAndLoweCase')]")
    private WebElement isBothUpperAndLoweCase;
    public WebElement getSecuritySettingsPageCheckboxMustIncludeBothUpperAndLowerCaseLetters() {return isBothUpperAndLoweCase;}

    @FindBy(xpath = "//*[contains(@id,'isAtleastOneNumber')]")
    private WebElement isAtleastOneNumber;
    public WebElement getSecuritySettingsPageCheckboxMustIncludeAtLeastOneNumber() {return isAtleastOneNumber;}

    @FindBy(xpath = "//*[contains(@id,'isBothUpperAndLoweCase')]")
    private WebElement isAtleastOneSplChar;
    public WebElement getSecuritySettingsPageCheckboxMustIncludeAtLeastOneSpecialCharacter() {return isAtleastOneSplChar;}

    @FindBy(xpath = "//*[@name='objectsSyncLevel']")
    private WebElement objectsSyncLevel;
    public WebElement getSecuritySettingsPageFormFieldBusinessObjectsEnterpriseSynchInterval() {return objectsSyncLevel;}

    @FindBy(xpath = "//*[contains(text(),'Default Entities For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='All']/preceding-sibling::input[contains(@id,'radiofield')]")
    private WebElement defaultEntitiesForNewUsersRadioAll;
    public WebElement getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersAll() {return defaultEntitiesForNewUsersRadioAll;}

    @FindBy(xpath = "//*[contains(text(),'Default Entities For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='None']/preceding-sibling::input[contains(@id,'radiofield')]")
    private WebElement defaultEntitiesForNewUsersRadioNone;
    public WebElement getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersNone() {return defaultEntitiesForNewUsersRadioNone;}

    @FindBy(xpath = "//*[contains(text(),'Default Departments For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='All']/preceding-sibling::input[contains(@id,'radiofield')]")
    private WebElement defaultDepartmentsForNewUsersRadioAll;
    public WebElement getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersAll() {return defaultDepartmentsForNewUsersRadioAll;}

    @FindBy(xpath = "//*[contains(text(),'Default Departments For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='None']/preceding-sibling::input[contains(@id,'radiofield')]")
    private WebElement defaultDepartmentsForNewUsersRadioNone;
    public WebElement getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersNone() {return defaultDepartmentsForNewUsersRadioNone;}

    @FindBy(xpath = "//*[contains(@id,'isAuditLogEnabled')]")
    private WebElement isAuditLogEnabled;
    public WebElement getSecuritySettingsPageCheckboxAuditLoggingEnabled() {return isAuditLogEnabled;}

    @FindBy(xpath = "//*[@name='auditRetentionPeriod']")
    private WebElement auditRetentionPeriod;
    public WebElement getSecuritySettingsPageFormFieldAuditLogRetentionPeriod() {return auditRetentionPeriod;}

    // ===== End Security Settings Page ===== //

    // ===== System Maintenance Tab > General Settings Page ===== //

    @FindBy(xpath = "//*[contains(@onclick,'sgensetfd.htm') and @class='listhelpLnk']")
    private WebElement generalSettingsPageHelpLink;
    public WebElement getGeneralSettingsPageHelpLink() {return generalSettingsPageHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'generalsettings')]/descendant::span[text()='Save']")
    private WebElement generalSettingsPageButtonSave;
    public WebElement getGeneralSettingsPageButtonSave() {return generalSettingsPageButtonSave;}

    @FindBy(name = "publishedStart")
    private WebElement destReimbursementsScenariosPublishedStart;
    public WebElement getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosPublishedStart() {return destReimbursementsScenariosPublishedStart;}

    @FindBy(name = "publishedEnd")
    private WebElement destReimbursementsScenariosPublishedEnd;
    public WebElement getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosPublishedEnd() {return destReimbursementsScenariosPublishedEnd;}

    @FindBy(name = "unPublishedStart")
    private WebElement destReimbursementsScenariosUnPublishedStart;
    public WebElement getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedStart() {return destReimbursementsScenariosUnPublishedStart;}

    @FindBy(name = "unPublishedEnd")
    private WebElement destReimbursementsScenariosUnPublishedEnd;
    public WebElement getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedEnd() {return destReimbursementsScenariosUnPublishedEnd;}

    @FindBy(xpath = "//*[contains(@id,'checkbox') and text()='Price']/../following-sibling::input")
    private WebElement rvuMaintShowPriceCheckbox;
    public WebElement getGeneralSettingsPageCheckboxRvuMaintenanceShowPrice() {return rvuMaintShowPriceCheckbox;}

    @FindBy(xpath = "//*[contains(@id,'checkbox') and text()='Revenue']/../following-sibling::input")
    private WebElement rvuMaintShowRevenueCheckbox;
    public WebElement getGeneralSettingsPageCheckboxRvuMaintenanceShowRevenue() {return rvuMaintShowRevenueCheckbox;}

    @FindBy(name = "uiPageSize")
    private WebElement uiPageSize;
    public WebElement getGeneralSettingsPageFormFieldUiPageSize() {return uiPageSize;}

    @FindBy(name = "calStatusUIPageSize")
    private WebElement calStatusUIPageSize;
    public WebElement getGeneralSettingsPageFormFieldCalculationStatusUiPageSize() {return calStatusUIPageSize;}

    @FindBy(name = "impExpStatusUIPageSize")
    private WebElement impExpStatusUIPageSize;
    public WebElement getGeneralSettingsPageFormFieldImportExportAndUtilityStatusUiPageSize() {return impExpStatusUIPageSize;}

    @FindBy(name = "maxDockItems")
    private WebElement maxDockItems;
    public WebElement getGeneralSettingsPageFormFieldMaximumDockItems() {return maxDockItems;}

    @FindBy(name = "CONTRACT_CALCULATION")
    private WebElement CONTRACT_CALCULATION;
    public WebElement getGeneralSettingsPageFormFieldContractingContractBatch() {return CONTRACT_CALCULATION;}

    @FindBy(name = "CONTRACTUAL_ALLOWANCES")
    private WebElement CONTRACTUAL_ALLOWANCES;
    public WebElement getGeneralSettingsPageFormFieldContractingContractAllowances() {return CONTRACTUAL_ALLOWANCES;}

    @FindBy(name = "PSYCH_COMORBIDITY")
    private WebElement PSYCH_COMORBIDITY;
    public WebElement getGeneralSettingsPageFormFieldContractingPsychCombinedComorbidityAssignment() {return PSYCH_COMORBIDITY;}

    @FindBy(name = "PUBLISHED_CONTRACT_CALCULATION")
    private WebElement PUBLISHED_CONTRACT_CALCULATION;
    public WebElement getGeneralSettingsPageFormFieldContractingPublishedContractCalculation() {return PUBLISHED_CONTRACT_CALCULATION;}

    @FindBy(name = "REIMBURSEMENT_SCENARIO")
    private WebElement REIMBURSEMENT_SCENARIO;
    public WebElement getGeneralSettingsPageFormFieldContractingReimbursementScenarioAssignment() {return REIMBURSEMENT_SCENARIO;}

    @FindBy(name = "UNPUBLISHED_CONTRACT_CALCULATION")
    private WebElement UNPUBLISHED_CONTRACT_CALCULATION;
    public WebElement getGeneralSettingsPageFormFieldContractingUnpublishedContractCalculation() {return UNPUBLISHED_CONTRACT_CALCULATION;}


    @FindBy(name = "ACTIVITY_VOLUME")
    private WebElement ACTIVITY_VOLUME;
    public WebElement getGeneralSettingsPageFormFieldCostingActivityVolumeDataCalculation() {return ACTIVITY_VOLUME;}

    @FindBy(name = "ENCOUNTER_COST_CALC")
    private WebElement ENCOUNTER_COST_CALC;
    public WebElement getGeneralSettingsPageFormFieldCostingEncounterCostCalculation() {return ENCOUNTER_COST_CALC;}

    @FindBy(name = "ENCOUNTER_COST_CALC_CLEAR")
    private WebElement ENCOUNTER_COST_CALC_CLEAR;
    public WebElement getGeneralSettingsPageFormFieldCostingClearEncounterCosts() {return ENCOUNTER_COST_CALC_CLEAR;}

    @FindBy(name = "COST_MODEL_SCENARIO_CALC")
    private WebElement COST_MODEL_SCENARIO_CALC;
    public WebElement getGeneralSettingsPageFormFieldCostingCostModelScenarioCalculation() {return COST_MODEL_SCENARIO_CALC;}

    @FindBy(name = "GL_ADJUSTMENT_AND_RECLASS")
    private WebElement GL_ADJUSTMENT_AND_RECLASS;
    public WebElement getGeneralSettingsPageFormFieldCostingGlAdjustmentsAndReclassificationCalculation() {return GL_ADJUSTMENT_AND_RECLASS;}

    @FindBy(name = "GROUP_ALLOCATION")
    private WebElement GROUP_ALLOCATION;
    public WebElement getGeneralSettingsPageFormFieldCostingGroupAllocationCalculation() {return GROUP_ALLOCATION;}

    @FindBy(name = "OVERHEAD_ALLOCATION")
    private WebElement OVERHEAD_ALLOCATION;
    public WebElement getGeneralSettingsPageFormFieldCostingOverheadModelScenarioCalculation() {return OVERHEAD_ALLOCATION;}

    @FindBy(name = "RVU_CALC")
    private WebElement RVU_CALC;
    public WebElement getGeneralSettingsPageFormFieldCostingRvuCalculation() {return RVU_CALC;}

    @FindBy(name = "COSTING_STATISTIC")
    private WebElement COSTING_STATISTIC;
    public WebElement getGeneralSettingsPageFormFieldCostingStatisticDataCalculation() {return COSTING_STATISTIC;}

    @FindBy(name = "COST_MODEL_SCENARIO_CALC_UCQC")
    private WebElement COST_MODEL_SCENARIO_CALC_UCQC;
    public WebElement getGeneralSettingsPageFormFieldCostingUnitCostQuickCalculation() {return COST_MODEL_SCENARIO_CALC_UCQC;}


    @FindBy(name = "MEDICAL_SVC_ANALYSIS_CLEAR")
    private WebElement MEDICAL_SVC_ANALYSIS_CLEAR;
    public WebElement getGeneralSettingsPageFormFieldGeneralClearMedicalServiceAssignment() {return MEDICAL_SVC_ANALYSIS_CLEAR;}

    @FindBy(name = "CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN")
    private WebElement CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN;
    public WebElement getGeneralSettingsPageFormFieldGeneralChargeItemServiceClassificationScheme() {return CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN;}

    @FindBy(name = "ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN")
    private WebElement ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN;
    public WebElement getGeneralSettingsPageFormFieldGeneralEncounterServiceClassificationScheme() {return ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN;}

    @FindBy(name = "MEDICAL_SVC_ANALYSIS")
    private WebElement MEDICAL_SVC_ANALYSIS;
    public WebElement getGeneralSettingsPageFormFieldGeneralMedicalServiceAssignment() {return MEDICAL_SVC_ANALYSIS;}

    @FindBy(name = "PRICE_LIST_CALC")
    private WebElement PRICE_LIST_CALC;
    public WebElement getGeneralSettingsPageFormFieldGeneralPriceListCalculation() {return PRICE_LIST_CALC;}

    @FindBy(name = "PRICE_LIST_ASSIGN")
    private WebElement PRICE_LIST_ASSIGN;
    public WebElement getGeneralSettingsPageFormFieldGeneralPriceListEncountersAssignment() {return PRICE_LIST_ASSIGN;}

    @FindBy(name = "CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL")
    private WebElement CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;
    public WebElement getGeneralSettingsPageFormFieldGeneralRemoveChargeItemServiceClassification() {return CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;}

    @FindBy(name = "ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL")
    private WebElement ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;
    public WebElement getGeneralSettingsPageFormFieldGeneralRemoveEncounterServiceClassification() {return ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;}

    @FindBy(name = "PRICE_LIST_REMOVE")
    private WebElement PRICE_LIST_REMOVE;
    public WebElement getGeneralSettingsPageFormFieldGeneralRemovePriceListToEncountersAssignment() {return PRICE_LIST_REMOVE;}

    @FindBy(name = "IMPORT_EXPORT")
    private WebElement IMPORT_EXPORT;
    public WebElement getGeneralSettingsPageFormFieldGeneralAllImportsAndExports() {return IMPORT_EXPORT;}

    // ===== System Maintenance Tab > Customize Maintain Data Page ===== //

    @FindBy(xpath = "//label[contains(@id,'radiofield') and text()='Show Selected']/../descendant::input")
    private WebElement customizeMaintainDataRadioShowSelected;
    public WebElement getCustomizeMaintainDataRadioButtonShowSelected() {return customizeMaintainDataRadioShowSelected;}

    @FindBy(xpath = "//label[contains(@id,'radiofield') and text()='Show All']/../descendant::input")
    private WebElement customizeMaintainDataRadioShowAll;
    public WebElement getCustomizeMaintainDataRadioButtonShowAll() {return customizeMaintainDataRadioShowAll;}

    @FindBy(xpath = "//*[contains(@id,'customizemaintaindata')]/descendant::span[text()='Save']")
    private WebElement customizeMaintainDataPageButtonSave;
    public WebElement getCustomizeMaintainDataPageButtonSave() {return customizeMaintainDataPageButtonSave;}

    @FindBy(xpath = "//*[contains(@id,'customizemaintaindata')]/descendant::span[text()='Save & Close']")
    private WebElement customizeMaintainDataPageButtonSaveAndClose;
    public WebElement getCustomizeMaintainDataPageButtonSaveAndClose() {return customizeMaintainDataPageButtonSaveAndClose;}

    @FindBy(xpath = "//*[contains(@id,'customizemaintaindata')]/descendant::span[text()='Cancel & Close']")
    private WebElement customizeMaintainDataPageButtonCancelAndClose;
    public WebElement getCustomizeMaintainDataPageButtonCancelAndClose() {return customizeMaintainDataPageButtonCancelAndClose;}

    @FindBy(xpath = "//*[contains(@onclick, 'scustmdfd.htm') and @class='listhelpLnk']")
    private WebElement customizeMaintainDataPageLinkHelp;
    public WebElement getCustomizeMaintainDataPageLinkHelp() {return customizeMaintainDataPageLinkHelp;}

    @FindBy(xpath = "//*[contains(@id,'customizeMaintainData')]/descendant::span[contains(@id,'header') and text()='Maintain Data']")
    private WebElement customizeMaintainDataCollapsibleSectionMaintainData;
    public WebElement getCustomizeMaintainDataCollapsibleSectionMaintainData() {return customizeMaintainDataCollapsibleSectionMaintainData;}

    @FindBy(xpath = "//*[contains(@id,'customizeMaintainData')]/descendant::span[contains(@id,'header') and text()='Encounter Tabs']")
    private WebElement customizeMaintainDataCollapsibleSectionEncounterTabs;
    public WebElement getCustomizeMaintainDataCollapsibleSectionEncounterTabs() {return customizeMaintainDataCollapsibleSectionEncounterTabs;}

    @FindBy(name = "viewOption")
    private WebElement customizeMaintainDataCollapsibleMaintainDataDropdownMaintainDataViewOption;
    public WebElement getCustomizeMaintainDataPageCollapsibleMaintainDataDropdownMaintainDataViewOption() {return customizeMaintainDataCollapsibleMaintainDataDropdownMaintainDataViewOption;}

    @FindBy(xpath = "//div[text()='Maintain Data Screen Selections']/../descendant::span[text()='Filter']")
    private WebElement customizeMaintainDataCollapsibleMaintainDataButtonFilter;
    public WebElement getCustomizeMaintainDataCollapsibleMaintainDataButtonFilter() {return customizeMaintainDataCollapsibleMaintainDataButtonFilter;}

    @FindBy(xpath = "//div[text()='Maintain Data Screen Selections']/../descendant::span[text()='Clear Filter']")
    private WebElement customizeMaintainDataCollapsibleMaintainDataButtonClearFilter;
    public WebElement getCustomizeMaintainDataCollapsibleMaintainDataButtonClearFilter() {return customizeMaintainDataCollapsibleMaintainDataButtonClearFilter;}

    @FindBy(xpath = "//div[text()='Maintain Data Screen Selections']/../descendant::div[contains(text(),'Show Screen')]/following-sibling::div[contains(@class,'maintaindatagridheader')]")
    private WebElement customizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll;
    public WebElement getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll() {return customizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll;}

    @FindBy(xpath = "//div[text()='Maintain Data Screen Selections']/../descendant::div[contains(text(),'Read-Only')]/following-sibling::div[contains(@class,'maintaindatagridheader')]")
    private WebElement customizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll;
    public WebElement getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll() {return customizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll;}

    @FindBy(xpath = "//div[contains(text(),'Activity Statistic Masters')]/..//div[contains(@class,'checkheader')]")
    private WebElement customizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters;
    public WebElement getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters() {return customizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters;}

    @FindBy(xpath = "//*[text()='Encounter Tabs']/ancestor::div/descendant::div[contains(text(),'Show Tab')]/following-sibling::div[contains(@class,'maintaindatagridheader')]")
    private WebElement customizeMaintainDataCollapsibleEncounterTabsCheckBoxShowTabSelectAll;
    public WebElement getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxShowTabSelectAll() {return customizeMaintainDataCollapsibleEncounterTabsCheckBoxShowTabSelectAll;}

    @FindBy(xpath = "//div[contains(text(),'Totals')]/..//div[contains(@class,'checkheader')]")
    private WebElement customizeMaintainDataCollapsibleEncounterTabsCheckBoxTotals;
    public WebElement getCustomizeMaintainDataCollapsibleEncounterTabsCheckBoxTotals() {return customizeMaintainDataCollapsibleEncounterTabsCheckBoxTotals;}

    // ===== End Customize Maintain Data Page ===== //

    // ===== System Maintenance Tab > Customize Task Lists Page ===== //

    @FindBy(xpath = "//*[contains(@onclick,'scusttlfd.htm') and @class='listhelpLnk']")
    private WebElement customizeTaskListsPageLinkHelp;
    public WebElement getCustomizeTaskListsPageLinkHelp() {return customizeTaskListsPageLinkHelp;}

    @FindBy(xpath = "//*[contains(@id,'customizetasklists')]/descendant::span[text()='Cost']")
    private WebElement customizeTaskListsPageSubTabCost;
    public WebElement getCustomizeTaskListsPageSubTabCost() {return customizeTaskListsPageSubTabCost;}

    @FindBy(xpath = "//*[contains(@id,'customizetasklists')]/descendant::span[text()='Overhead']")
    private WebElement customizeTaskListsPageSubTabOverhead;
    public WebElement getCustomizeTaskListsPageSubTabOverhead() {return customizeTaskListsPageSubTabOverhead;}

    @FindBy(xpath = "//*[contains(@id,'customizetasklists')]/descendant::span[text()='Unpublished Contract']")
    private WebElement customizeTaskListsPageSubTabUnpublishedContract;
    public WebElement getCustomizeTaskListsPageSubTabUnpublishedContract() {return customizeTaskListsPageSubTabUnpublishedContract;}

    @FindBy(xpath = "//*[contains(@id,'customizetasklists')]/descendant::span[text()='Published Contract']")
    private WebElement customizeTaskListsPageSubTabPublishedContract;
    public WebElement getCustomizeTaskListsPageSubTabPublishedContract() {return customizeTaskListsPageSubTabPublishedContract;}

    @FindBy(xpath = "//*[contains(@id,'customizetasklists')]/descendant::span[text()='Episode']")
    private WebElement customizeTaskListsPageSubTabEpisode;
    public WebElement getCustomizeTaskListsPageSubTabEpisode() {return customizeTaskListsPageSubTabEpisode;}

    // ===== End Customize Task Lists Page ===== //

    // ===== System Maintenance Tab > Terminal Server Sessions Page ===== //

    @FindBy(xpath = "//*[contains(@onclick,'stsccnfd.htm') and @class='listhelpLnk']")
    private WebElement terminalServerSessionsPageLinkHelp;
    public WebElement getTerminalServerSessionsPageLinkHelp() {return terminalServerSessionsPageLinkHelp;}

    @FindBy(xpath = "//*[contains(@id,'terminalserversessions')]/descendant::span[text()='Continue']")
    private WebElement terminalServerSessionsPageButtonContinue;
    public WebElement getTerminalServerSessionsPageButtonContinue() {return terminalServerSessionsPageButtonContinue;}

    @FindBy(xpath = "//*[contains(@id,'terminalserversessions')]/descendant::span[text()='Close']")
    private WebElement terminalServerSessionsPageButtonClose;
    public WebElement getTerminalServerSessionsPageButtonClose() {return terminalServerSessionsPageButtonClose;}


    // ===== End Terminal Server Sessions Page ===== //

}