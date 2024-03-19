package webdriver.maps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import webdriver.maps.mapbuilder.MapConfig;

public class SystemMaintenanceMap extends MapConfig {

	// ===== System Maintenance Tab >  Users Page ===== //

//	@FindBy(xpath = "//div[contains(@id,'systemmaintenance_users')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-inner']/span[@class='x-column-header-text']")
	//Shilpa update xpath for 11.2 on 1.2.2024
	@FindBy(xpath = "//div[contains(@id,'userLayoutPage')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-text']/span[@class='x-column-header-text-inner']")
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
	
//	Omkar 6/4/23 : Change in xpath for 11.2
//	@FindBy(xpath = "//span[text()='Clear Filter']//parent::button")
	@FindBy(xpath = "//span[text()='Clear Filter']/parent::span")
	private WebElement getCustomTaskListClearFilterButton;
	public WebElement getCustomTaskListClearFilterButton() {return getCustomTaskListClearFilterButton;}

//	@FindBy(xpath = "//span[text()='Filter']//parent::button")
	//SHILPA UPDATE XPATH FOR 11.2 ON 12.13.2023
	@FindBy(xpath = "//span[text()='Filter']")
	private WebElement getCustomTaskListFilterButton;
	public WebElement getCustomTaskListFilterButton() {return getCustomTaskListFilterButton;}

	@FindBy(xpath = "//div[contains(@class,'customGrid')]//*[contains(@class,'docked-bottom')]/descendant::span[text()='Cancel & Close']")
	private WebElement getSystemMaintenanceCancelCloseButton;
	public WebElement getSystemMaintenanceCancelCloseButton() {
		return getSystemMaintenanceCancelCloseButton;
	}


	//	Omkar 6/4/23 : Change in xpath for 11.2
	//	 @FindBy(xpath = "//span[text()='Add Folder']//parent::button")
	@FindBy(xpath = "//span[text()='Add Folder']")
	private WebElement getSystemMaintenanceAddFolderButton;
	public WebElement getSystemMaintenanceAddFolderButton() {
		return getSystemMaintenanceAddFolderButton;
	}
	@FindBy(xpath = "//div[contains(@class,'x-grid-view')]//td[3]/div")
	private List<WebElement> getSystemMaintenanceUserList;
	public List<WebElement> getSystemMaintenanceUserList() {
		return getSystemMaintenanceUserList;
	}
	// ===== End  Users Page ===== //

	// ===== System Maintenance Tab >  Roles Page ===== //

//	@FindBy(xpath = "//div[contains(@id,'systemmaintenance_roles')]/../descendant::div[contains(@class,'column-header-align-center')]/div/span[@class='x-column-header-text']")
	@FindBy(xpath = "//div[contains(@id,'rolesGridLayout')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-text']/span[@class='x-column-header-text-inner']")
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

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'security') and contains(@id,'targetEl')]/descendant::span[text()='Save']")
	@FindBy(xpath = "//*[contains(@id,'security') and contains(@id,'targetEl')]/descendant::span[text()='Save']/parent::span")
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

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'isAtleastOneLetter')]")
	@FindBy(xpath = "//*[contains(@id,'isAtleastOneLetter') and @type ='checkbox']/parent::span")
	private WebElement isAtleastOneLetter;
	public WebElement getSecuritySettingsPageCheckboxMustIncludeAtLeastOneLetter() {return isAtleastOneLetter;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'isBothUpperAndLoweCase')]")
	@FindBy(xpath = "//*[contains(@id,'isBothUpperAndLoweCase') and @type='checkbox']/parent::span")
	private WebElement isBothUpperAndLoweCase;
	public WebElement getSecuritySettingsPageCheckboxMustIncludeBothUpperAndLowerCaseLetters() {return isBothUpperAndLoweCase;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'isAtleastOneNumber')]")
	@FindBy(xpath = "//*[contains(@id,'isAtleastOneNumber')  and @type='checkbox']/parent::span")
	private WebElement isAtleastOneNumber;
	public WebElement getSecuritySettingsPageCheckboxMustIncludeAtLeastOneNumber() {return isAtleastOneNumber;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'isBothUpperAndLoweCase')]")
	@FindBy(xpath = "//*[contains(@id,'isBothUpperAndLoweCase') and @type='checkbox']/parent::span")
	private WebElement isAtleastOneSplChar;
	public WebElement getSecuritySettingsPageCheckboxMustIncludeAtLeastOneSpecialCharacter() {return isAtleastOneSplChar;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[@name='objectsSyncLevel']")
	@FindBy(xpath = "//*[@name='objectsSyncLevel']/parent::div")
	private WebElement objectsSyncLevel;
	public WebElement getSecuritySettingsPageFormFieldBusinessObjectsEnterpriseSynchInterval() {return objectsSyncLevel;}

//	@FindBy(xpath = "//*[contains(text(),'Default Entities For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='All']/preceding-sibling::input[contains(@id,'radiofield')]")
	//Shilpa updated xpath for 11.2 on 1.2.2024
//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "(//*[contains(text(),'Default Entities For New Users')]//following::input[@name='entityRadio'])[1]")
	@FindBy(xpath = "(//input[@name='entityRadio'])[1]/parent::span")
	private WebElement defaultEntitiesForNewUsersRadioAll;
	public WebElement getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersAll() {return defaultEntitiesForNewUsersRadioAll;}

	//Shilpa updated xpath for 11.2 on 1.2.2024
//	@FindBy(xpath = "//*[contains(text(),'Default Entities For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='None']/preceding-sibling::input[contains(@id,'radiofield')]"
//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "(//*[contains(text(),'Default Entities For New Users')]//following::input[@name='entityRadio'])[2]")
	@FindBy(xpath = "(//input[@name='entityRadio'])[2]/parent::span")
	private WebElement defaultEntitiesForNewUsersRadioNone;
	public WebElement getSecuritySettingsPageRadioButtonDefaultEntitiesForNewUsersNone() {return defaultEntitiesForNewUsersRadioNone;}

	//Shilpa updated xpath for 11.2 on 1.2.2024
//	@FindBy(xpath = "//*[contains(text(),'Default Departments For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='All']/preceding-sibling::input[contains(@id,'radiofield')]")
//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "(//*[contains(text(),'Default Departments For New Users')]//following::input[@name='departmentRadio'])[1]")
	@FindBy(xpath = "(//input[@name='departmentRadio'])[1]/parent::span")
	private WebElement defaultDepartmentsForNewUsersRadioAll;
	public WebElement getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersAll() {return defaultDepartmentsForNewUsersRadioAll;}

	//Shilpa updated xpath for 11.2 on 1.2.2024
//	@FindBy(xpath = "//*[contains(text(),'Default Departments For New Users')]/../following-sibling::td/descendant::*[contains(@id,'radiofield') and text()='None']/preceding-sibling::input[contains(@id,'radiofield')]")
//	Omkar 20/2/2024 : xpath changes for 11.2
	//	@FindBy(xpath = "(//*[contains(text(),'Default Departments For New Users')]//following::input[@name='departmentRadio'])[2]")
	@FindBy(xpath = "(//input[@name='departmentRadio'])[2]/parent::span")
	private WebElement defaultDepartmentsForNewUsersRadioNone;
	public WebElement getSecuritySettingsPageRadioButtonDefaultDepartmentsForNewUsersNone() {return defaultDepartmentsForNewUsersRadioNone;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'isAuditLogEnabled')]")
	@FindBy(xpath = "//input[@name='isAuditLogEnabled']/parent::span")
	private WebElement isAuditLogEnabled;
	public WebElement getSecuritySettingsPageCheckboxAuditLoggingEnabled() {return isAuditLogEnabled;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[@name='auditRetentionPeriod']")
	@FindBy(xpath = "//input[@name='auditRetentionPeriod']/parent::div")
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

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(name = "unPublishedEnd")
	@FindBy(xpath ="//*[contains(@name , 'unPublishedEnd')]/parent::div")
	private WebElement destReimbursementsScenariosUnPublishedEnd;
	public WebElement getGeneralSettingsPageComboBoxDestinationReimbursementsScenariosUnpublishedEnd() {return destReimbursementsScenariosUnPublishedEnd;}

//	 Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'checkbox') and text()='Price']/../following-sibling::input")
	@FindBy(xpath ="//*[contains(@aria-label , 'Price')]/parent::span")
	private WebElement rvuMaintShowPriceCheckbox;
	public WebElement getGeneralSettingsPageCheckboxRvuMaintenanceShowPrice() {return rvuMaintShowPriceCheckbox;}

//	Omkar 20/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'checkbox') and text()='Revenue']/../following-sibling::input")
	@FindBy(xpath ="//*[contains(@aria-label , 'Revenue')]/parent::span")
	private WebElement rvuMaintShowRevenueCheckbox;
	public WebElement getGeneralSettingsPageCheckboxRvuMaintenanceShowRevenue() {return rvuMaintShowRevenueCheckbox;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "uiPageSize")
	@FindBy(xpath = "//*[@name= 'uiPageSize']/parent::div")
	private WebElement uiPageSize;
	public WebElement getGeneralSettingsPageFormFieldUiPageSize() {return uiPageSize;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "calStatusUIPageSize")
	@FindBy(xpath = "//*[@name= 'calStatusUIPageSize']/parent::div")
	private WebElement calStatusUIPageSize;
	public WebElement getGeneralSettingsPageFormFieldCalculationStatusUiPageSize() {return calStatusUIPageSize;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "impExpStatusUIPageSize")
	@FindBy(xpath = "//*[@name= 'impExpStatusUIPageSize']/parent::div")
	private WebElement impExpStatusUIPageSize;
	public WebElement getGeneralSettingsPageFormFieldImportExportAndUtilityStatusUiPageSize() {return impExpStatusUIPageSize;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "maxDockItems")
	@FindBy(xpath = "//*[@name= 'maxDockItems']/parent::div")
	private WebElement maxDockItems;
	public WebElement getGeneralSettingsPageFormFieldMaximumDockItems() {return maxDockItems;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "CONTRACT_CALCULATION")
	@FindBy(xpath = "//*[@name= 'CONTRACT_CALCULATION']/parent::div")
	private WebElement CONTRACT_CALCULATION;
	public WebElement getGeneralSettingsPageFormFieldContractingContractBatch() {return CONTRACT_CALCULATION;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "CONTRACTUAL_ALLOWANCES")
	@FindBy(xpath = "//*[@name= 'CONTRACTUAL_ALLOWANCES']/parent::div")
	private WebElement CONTRACTUAL_ALLOWANCES;
	public WebElement getGeneralSettingsPageFormFieldContractingContractAllowances() {return CONTRACTUAL_ALLOWANCES;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "PSYCH_COMORBIDITY")
	@FindBy(xpath = "//*[@name= 'PSYCH_COMORBIDITY']/parent::div")
	private WebElement PSYCH_COMORBIDITY;
	public WebElement getGeneralSettingsPageFormFieldContractingPsychCombinedComorbidityAssignment() {return PSYCH_COMORBIDITY;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "PUBLISHED_CONTRACT_CALCULATION")
	@FindBy(xpath = "//*[@name= 'PUBLISHED_CONTRACT_CALCULATION']/parent::div")
	private WebElement PUBLISHED_CONTRACT_CALCULATION;
	public WebElement getGeneralSettingsPageFormFieldContractingPublishedContractCalculation() {return PUBLISHED_CONTRACT_CALCULATION;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "REIMBURSEMENT_SCENARIO")
	@FindBy(xpath = "//*[@name= 'REIMBURSEMENT_SCENARIO']/parent::div")
	private WebElement REIMBURSEMENT_SCENARIO;
	public WebElement getGeneralSettingsPageFormFieldContractingReimbursementScenarioAssignment() {return REIMBURSEMENT_SCENARIO;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "UNPUBLISHED_CONTRACT_CALCULATION")
	@FindBy(xpath = "//*[@name= 'UNPUBLISHED_CONTRACT_CALCULATION']/parent::div")
	private WebElement UNPUBLISHED_CONTRACT_CALCULATION;
	public WebElement getGeneralSettingsPageFormFieldContractingUnpublishedContractCalculation() {return UNPUBLISHED_CONTRACT_CALCULATION;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "ACTIVITY_VOLUME")
	@FindBy(xpath = "//*[@name= 'ACTIVITY_VOLUME']/parent::div")
	private WebElement ACTIVITY_VOLUME;
	public WebElement getGeneralSettingsPageFormFieldCostingActivityVolumeDataCalculation() {return ACTIVITY_VOLUME;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "ENCOUNTER_COST_CALC")
	@FindBy(xpath = "//*[@name= 'ENCOUNTER_COST_CALC']/parent::div")
	private WebElement ENCOUNTER_COST_CALC;
	public WebElement getGeneralSettingsPageFormFieldCostingEncounterCostCalculation() {return ENCOUNTER_COST_CALC;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "ENCOUNTER_COST_CALC_CLEAR")
	@FindBy(xpath = "//*[@name= 'ENCOUNTER_COST_CALC_CLEAR']/parent::div")
	private WebElement ENCOUNTER_COST_CALC_CLEAR;
	public WebElement getGeneralSettingsPageFormFieldCostingClearEncounterCosts() {return ENCOUNTER_COST_CALC_CLEAR;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "COST_MODEL_SCENARIO_CALC")
	@FindBy(xpath = "//*[@name= 'COST_MODEL_SCENARIO_CALC']/parent::div")
	private WebElement COST_MODEL_SCENARIO_CALC;
	public WebElement getGeneralSettingsPageFormFieldCostingCostModelScenarioCalculation() {return COST_MODEL_SCENARIO_CALC;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "GL_ADJUSTMENT_AND_RECLASS")
	@FindBy(xpath = "//*[@name= 'GL_ADJUSTMENT_AND_RECLASS']/parent::div")
	private WebElement GL_ADJUSTMENT_AND_RECLASS;
	public WebElement getGeneralSettingsPageFormFieldCostingGlAdjustmentsAndReclassificationCalculation() {return GL_ADJUSTMENT_AND_RECLASS;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "GROUP_ALLOCATION")
	@FindBy(xpath = "//*[@name= 'GROUP_ALLOCATION']/parent::div")
	private WebElement GROUP_ALLOCATION;
	public WebElement getGeneralSettingsPageFormFieldCostingGroupAllocationCalculation() {return GROUP_ALLOCATION;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "OVERHEAD_ALLOCATION")
	@FindBy(xpath = "//*[@name= 'OVERHEAD_ALLOCATION']/parent::div")
	private WebElement OVERHEAD_ALLOCATION;
	public WebElement getGeneralSettingsPageFormFieldCostingOverheadModelScenarioCalculation() {return OVERHEAD_ALLOCATION;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "RVU_CALC")
	@FindBy(xpath = "//*[@name= 'RVU_CALC']/parent::div")
	private WebElement RVU_CALC;
	public WebElement getGeneralSettingsPageFormFieldCostingRvuCalculation() {return RVU_CALC;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "COSTING_STATISTIC")
	@FindBy(xpath = "//*[@name= 'COSTING_STATISTIC']/parent::div")
	private WebElement COSTING_STATISTIC;
	public WebElement getGeneralSettingsPageFormFieldCostingStatisticDataCalculation() {return COSTING_STATISTIC;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "COST_MODEL_SCENARIO_CALC_UCQC")
	@FindBy(xpath = "//*[@name= 'COST_MODEL_SCENARIO_CALC_UCQC']/parent::div")
	private WebElement COST_MODEL_SCENARIO_CALC_UCQC;
	public WebElement getGeneralSettingsPageFormFieldCostingUnitCostQuickCalculation() {return COST_MODEL_SCENARIO_CALC_UCQC;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "MEDICAL_SVC_ANALYSIS_CLEAR")
	@FindBy(xpath = "//*[@name= 'MEDICAL_SVC_ANALYSIS_CLEAR']/parent::div")
	private WebElement MEDICAL_SVC_ANALYSIS_CLEAR;
	public WebElement getGeneralSettingsPageFormFieldGeneralClearMedicalServiceAssignment() {return MEDICAL_SVC_ANALYSIS_CLEAR;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN")
	@FindBy(xpath = "//*[@name= 'CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN']/parent::div")
	private WebElement CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN;
	public WebElement getGeneralSettingsPageFormFieldGeneralChargeItemServiceClassificationScheme() {return CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN")
	@FindBy(xpath = "//*[@name= 'ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN']/parent::div")
	private WebElement ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN;
	public WebElement getGeneralSettingsPageFormFieldGeneralEncounterServiceClassificationScheme() {return ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "MEDICAL_SVC_ANALYSIS")
	@FindBy(xpath = "//*[@name= 'MEDICAL_SVC_ANALYSIS']/parent::div")
	private WebElement MEDICAL_SVC_ANALYSIS;
	public WebElement getGeneralSettingsPageFormFieldGeneralMedicalServiceAssignment() {return MEDICAL_SVC_ANALYSIS;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "PRICE_LIST_CALC")
	@FindBy(xpath = "//*[@name= 'PRICE_LIST_CALC']/parent::div")
	private WebElement PRICE_LIST_CALC;
	public WebElement getGeneralSettingsPageFormFieldGeneralPriceListCalculation() {return PRICE_LIST_CALC;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "PRICE_LIST_ASSIGN")
	@FindBy(xpath = "//*[@name= 'PRICE_LIST_ASSIGN']/parent::div")
	private WebElement PRICE_LIST_ASSIGN;
	public WebElement getGeneralSettingsPageFormFieldGeneralPriceListEncountersAssignment() {return PRICE_LIST_ASSIGN;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL")
	@FindBy(xpath = "//*[@name= 'CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL']/parent::div")
	private WebElement CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;
	public WebElement getGeneralSettingsPageFormFieldGeneralRemoveChargeItemServiceClassification() {return CHARGEITEM_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL")
	@FindBy(xpath = "//*[@name= 'ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL']/parent::div")
	private WebElement ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;
	public WebElement getGeneralSettingsPageFormFieldGeneralRemoveEncounterServiceClassification() {return ENCOUNTER_SERVICE_CLASSIFICATION_ASSIGN_REMOVAL;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "PRICE_LIST_REMOVE")
	@FindBy(xpath = "//*[@name= 'PRICE_LIST_REMOVE']/parent::div")
	private WebElement PRICE_LIST_REMOVE;
	public WebElement getGeneralSettingsPageFormFieldGeneralRemovePriceListToEncountersAssignment() {return PRICE_LIST_REMOVE;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(name = "IMPORT_EXPORT")
	@FindBy(xpath = "//*[@name= 'IMPORT_EXPORT']/parent::div")
	private WebElement IMPORT_EXPORT;
	public WebElement getGeneralSettingsPageFormFieldGeneralAllImportsAndExports() {return IMPORT_EXPORT;}

	// ===== System Maintenance Tab > Customize Maintain Data Page ===== //

//	Omkar 12/4/2023 : xpath change for 11.2
//	@FindBy(xpath = "//label[contains(@id,'radiofield') and text()='Show Selected']/../descendant::input")
	@FindBy(xpath = "//label[contains(@id,'radio') and text()='Show All']/../descendant::input/parent::span")
	private WebElement customizeMaintainDataRadioShowSelected;
	public WebElement getCustomizeMaintainDataRadioButtonShowSelected() {return customizeMaintainDataRadioShowSelected;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//label[contains(@id,'radiofield') and text()='Show All']/../descendant::input")
	@FindBy(xpath = "//label[contains(@id,'radio') and text()='Show All']/../descendant::input/parent::span")
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

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'customizeMaintainData')]/descendant::span[contains(@id,'header') and text()='Maintain Data']")
	@FindBy(xpath = "//*[contains(@id,'customizeMaintainData')]/descendant::div[contains(@id,'header') and text()='Maintain Data']/parent::div/following-sibling::div")
	private WebElement customizeMaintainDataCollapsibleSectionMaintainData;
	public WebElement getCustomizeMaintainDataCollapsibleSectionMaintainData() {return customizeMaintainDataCollapsibleSectionMaintainData;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//*[contains(@id,'customizeMaintainData')]/descendant::span[contains(@id,'header') and text()='Encounter Tabs']")
	@FindBy(xpath = "//*[contains(@id,'customizeMaintainData')]/descendant::div[contains(@id,'header') and text()='Encounter Tabs']/parent::div/following-sibling::div")
	private WebElement customizeMaintainDataCollapsibleSectionEncounterTabs;
	public WebElement getCustomizeMaintainDataCollapsibleSectionEncounterTabs() {return customizeMaintainDataCollapsibleSectionEncounterTabs;}

	@FindBy(name = "viewOption")
	private WebElement customizeMaintainDataCollapsibleMaintainDataDropdownMaintainDataViewOption;
	public WebElement getCustomizeMaintainDataPageCollapsibleMaintainDataDropdownMaintainDataViewOption() {return customizeMaintainDataCollapsibleMaintainDataDropdownMaintainDataViewOption;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//div[text()='Maintain Data Screen Selections']/../descendant::span[text()='Filter']")
	@FindBy(xpath = "//span[text()='Filter']/parent::span")
	private WebElement customizeMaintainDataCollapsibleMaintainDataButtonFilter;
	public WebElement getCustomizeMaintainDataCollapsibleMaintainDataButtonFilter() {return customizeMaintainDataCollapsibleMaintainDataButtonFilter;}

//	Omkar 21/2/2024 : xpath changes for 11.2
//	@FindBy(xpath = "//div[text()='Maintain Data Screen Selections']/../descendant::span[text()='Clear Filter']")
	@FindBy(xpath = "//span[text()='Clear Filter']/parent::span")
	private WebElement customizeMaintainDataCollapsibleMaintainDataButtonClearFilter;
	public WebElement getCustomizeMaintainDataCollapsibleMaintainDataButtonClearFilter() {return customizeMaintainDataCollapsibleMaintainDataButtonClearFilter;}

	@FindBy(xpath = "(//div[text()='Maintain Data Screen Selections']/../../following::div[contains(text(),'Show Screen?')]//following::div[contains(@class,'maintaindatagridheader')])[1]")
	private WebElement customizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll;
	public WebElement getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll() {return customizeMaintainDataCollapsibleMaintainDataCheckBoxShowScreenSelectAll;}

	@FindBy(xpath = "(//div[text()='Maintain Data Screen Selections']/../../following::div[contains(text(),'Show Screen?')]//following::div[contains(@class,'maintaindatagridheader')])[2]")
	private WebElement customizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll;
	public WebElement getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll() {return customizeMaintainDataCollapsibleMaintainDataCheckBoxReadOnlySelectAll;}

	@FindBy(xpath = "//td[contains(@class,'x-grid-cell-first x-unselectable')]//div[contains(text(),'Activity Statistic Masters')]/..//div[contains(@class,'checkheader')]")
	private WebElement customizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters;
	public WebElement getCustomizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters() {return customizeMaintainDataCollapsibleMaintainDataCheckBoxActivityStatisticMasters;}

	@FindBy(xpath = "(//div[text()='Maintain Data Screen Selections']/../../following::div[contains(text(),'Show Screen?')]//following::div[contains(@class,'maintaindatagridheader')])[3]")
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
	
	
	@FindBy(xpath = "(//h1[text()='Department Hierarchies']//following::span[text()='Filter']//parent::button)[1]")
	private WebElement getDeptHierarchyFilterButton;
	public WebElement getDeptHierarchyFilterButton() {
		return getDeptHierarchyFilterButton;
	}
	@FindBy(xpath = "//label[text()='Use Default']//preceding::input[1]")
	private WebElement getSystemMaintenanceRadioButton;

	public WebElement getSystemMaintenanceRadioButton() {
		return getSystemMaintenanceRadioButton;
	}
	@FindBy(xpath = "//div[@id='costingScreens-body']//table//tbody//tr/td/div")
	private List<WebElement> getSystemMaintenanceCostScreenList;

	public List<WebElement> getSystemMaintenanceCostScreenList() {
		return getSystemMaintenanceCostScreenList;
	}
	@FindBy(xpath = "//div[@id='ctlCostingTree']//table//tbody//tr/td/div")
	private List<WebElement> getSystemMaintenanceCostModelTaskList;

	public List<WebElement> getSystemMaintenanceCostModelTaskList() {
		return getSystemMaintenanceCostModelTaskList;
	}
	@FindBy(xpath = "(//div[@id='ctlCostingTree-body']//div[@class='x-grid-item-container']//table//tr//td//div/span[text()='Folder Name'])")
	private WebElement getSystemMaintenanceAddFolder;

	public WebElement getSystemMaintenanceAddFolder() {
		return getSystemMaintenanceAddFolder;
	}
	@FindBy(xpath = "(//div[@id='ctlCostingTree-body']//tr[contains(@class,'x-grid-row-focused')]/td/div)[2]")
	private WebElement getSystemMaintenanceAddFoldercol;

	public WebElement getSystemMaintenanceAddFoldercol() {
		return getSystemMaintenanceAddFoldercol;
	}
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

	@FindBy(name = "roleDesc")
	private WebElement rolesPageEditDesc;
	public WebElement getrolesPageEditDesc() {return rolesPageEditDesc;}

	@FindBy(name = "roleName")
	private WebElement rolesPageEditName;
	public WebElement getrolesPageEditName() {return rolesPageEditName;}

	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-list-ct')])[3]//ul")
	private WebElement getAssignedUsers;

	public WebElement getAssignedUsers() {
		return getAssignedUsers;
	}
//	Omkar 8/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'itemselectorfield')]//following::span[text()='Select']//parent::button)")
	@FindBy(xpath = "//a[contains(@class,'x-btn windowbtn x-unselectable x-box-item x-btn-default-small')]//span[text() = 'Select']/..")
	private WebElement getAssignedUsersSelectButton;

	public WebElement getAssignedUsersSelectButton() {
		return getAssignedUsersSelectButton;
	}
//	Omkar 9/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'itemselectorfield')]//following::span[text()='Remove']//parent::button)")
	@FindBy(xpath = "(//div[contains(@id,'buttonsContainer-innerCt')]//following::span[text()='Remove']/..)")
	private WebElement getAssignedUsersRemoveButton;

	public WebElement getAssignedUsersRemoveButton() {
		return getAssignedUsersRemoveButton;
	}
	
	@FindBy(xpath = "//span[text()='Remove']")
	private static WebElement getTaskListRemoveButton;

	public static WebElement getTaskListRemoveButton() {
		return getTaskListRemoveButton;
	}
	
	@FindBy(xpath = "//div[contains(@class,'panel')]//span[text()='Save']")
	private static WebElement getTaskListSaveButton;

	public static WebElement getTaskListSaveButton() {
		return getTaskListSaveButton;
	}
	
//	Omkar 29/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@class,'rolesMenuItems')]//table[contains(@class,'x-grid-table')]//following::span[text()='Select']//parent::button)[1]")
	@FindBy(xpath = "//div[contains(@class,'rolesMenuItems')]//following::span[text()='Select']/..")
	private WebElement getMenuItemsSelectButton;
	public WebElement getMenuItemsSelectButton() {
		return getMenuItemsSelectButton;
	}
	@FindBy(xpath = "//input[@name='userId']")
	private WebElement getUserID;
	public WebElement getUserID() {
		return getUserID;
	}
	@FindBy(xpath = "//input[@name='dbUserName']")
	private WebElement getDbUsername;
	public WebElement getDbUsername() {
		return getDbUsername;
	}
	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement getUserFirstName;
	public WebElement getUserFirstName() {
		return getUserFirstName;
	}
	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement getUserLastName;
	public WebElement getUserLastName() {
		return getUserLastName;
	}
	@FindBy(xpath = "//input[@name='displayName']")
	private WebElement getUserDisplayName;
	public WebElement getUserDisplayName() {
		return getUserDisplayName;
	}
	@FindBy(xpath = "//input[@name='initials']")
	private WebElement getUserInitialsName;
	public WebElement getUserInitialsName() {
		return getUserInitialsName;
	}
	@FindBy(xpath = "//input[@name='email']")
	private WebElement getUserEmailName;
	public WebElement getUserEmailName() {
		return getUserEmailName;
	}
	@FindBy(xpath = "//input[@name='jobFunction']")
	private WebElement getUserJobFunction;
	public WebElement getUserJobFunction() {
		return getUserJobFunction;
	}
	@FindBy(xpath = "//input[@name='newPassword']")
	private WebElement getUserNewPassword;
	public WebElement getUserNewPassword() {
		return getUserNewPassword;
	}
	@FindBy(xpath = "//input[@name='confirmNewPassword']")
	private WebElement getUserConfirmNewPassword;
	public WebElement getUserConfirmNewPassword() {
		return getUserConfirmNewPassword;
	}
	
//	Omkar 25/4/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//label[text()='Roles']//following::div[contains(@class,'roleSelect')])[1]")
	@FindBy(xpath = "(//label[text()='Roles']//following::a[contains(@class,'roleSelect')])[1]")
	private WebElement getUserRoleSelect;
	public WebElement getUserRoleSelect() {
		return getUserRoleSelect;
	}
//	Omkar 18/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//label[text()='Entities']//following::div[contains(@class,'roleSelect')])[1]")
	@FindBy(xpath = "//div[contains(@class,'x-panel x-box-item x-panel-default')]//span[text()='Select']")
	private WebElement getUserEntitySelect;
	public WebElement getUserEntitySelect() {
		return getUserEntitySelect;
	}
//	Omkar 8/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@class,'x-window-header-draggable')]//following::div[contains(@class,'x-boundlist-list-ct')])[4]//ul")
	@FindBy(xpath ="(//div[contains(@class,'x-panel x-panel-default')]//following::div[contains(@class,'x-boundlist-list-ct')])[4]//ul")
	private WebElement getUserRoleSelectOptions;
	public WebElement getUserRoleSelectOptions() {
		return getUserRoleSelectOptions;
	}
	@FindBy(xpath = "(//span[text()='All'])[2]")
	private WebElement getUserRoleSelectAllLeftButton;
	public WebElement getUserRoleSelectAllLeftButton() {
		return getUserRoleSelectAllLeftButton;
	}
	@FindBy(xpath = "(//span[text()='All'])[1]")
	private WebElement getUserRoleSelectAllRightButton;
	public WebElement getUserRoleSelectAllRightButton() {
		return getUserRoleSelectAllRightButton;
	}
	@FindBy(xpath = "(//label[contains(@class,'areaHeaderTitle')]//following::div[contains(@class,'x-grid-header-ct x-docked')]//following::table[1]//td[2]//div)[1]")
	private WebElement getUserMasterSelectDropdownButton;
	public WebElement getUserMasterSelectDropdownButton() {
		return getUserMasterSelectDropdownButton;
	}
	@FindBy(xpath = "(//label[contains(@class,'areaHeaderTitle')]//following::div[contains(@class,'x-grid-header-ct x-docked')]//following::table[1]//td[3]//div)[1]")
	private WebElement getUserMasterSelectDepartments;
	public WebElement getUserMasterSelectDepartments() {
		return getUserMasterSelectDepartments;
	}
	@FindBy(xpath = "//label[text()='Select All']//preceding::input[1]")
	private WebElement getUserMasterSelectAllCheckbox;
	public WebElement getUserMasterSelectAllCheckbox() {
		return getUserMasterSelectAllCheckbox;
	}
	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-list-ct')]//ul)[4]")
	private WebElement getUserMasterSelectDropdownButtonOptions;
	public WebElement getUserMasterSelectDropdownButtonOptions() {
		return getUserMasterSelectDropdownButtonOptions;
	}
	@FindBy(xpath = "(//label[contains(@class,'areaHeaderTitle')]//following::div[contains(@class,'x-grid-header-ct x-docked')]//following::table[1]//td[3]//div)[1]")
	private WebElement getUserMasterSelectDepartment;
	public WebElement getUserMasterSelectDepartment() {
		return getUserMasterSelectDepartment;
	}
	@FindBy(xpath = "(//label[contains(@class,'areaHeaderTitle')]//following::div[contains(@class,'x-grid-header-ct x-docked')]//following::table[1]//td[2]//div)[1]")
	private WebElement getUserMasterSelectButton;
	public WebElement getUserMasterSelectButton() {
		return getUserMasterSelectButton;
	}
	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-list-ct')]//ul)[3]")
	private WebElement getUserMasterMenuItems;
	public WebElement getUserMasterMenuItems() {
		return getUserMasterMenuItems;
	}
//	Omkar 9/5/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@class,'x-window-header-draggable')]//following::span[text()='Select']//parent::button)[3]")
	@FindBy(xpath = "//div[contains(@class,'x-container x-box-item x-container-default x-box-layout-ct')]//span[text()='Select']/..")
	private WebElement getUserSelectButtonInPopUp;
	public WebElement getUserSelectButtonInPopUp() {
		return getUserSelectButtonInPopUp;
	}
	// ===== End Terminal Server Sessions Page ===== //

}