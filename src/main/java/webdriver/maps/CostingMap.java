package webdriver.maps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

/*
This map file includes all elements found on the Costing SubTabs.
Section 1: RVU Maintenance
Section 2: RVU Maintenance Filter
Section 3: Cost Model Scenario Calculation
Section 4: Cost Model Scenario Calculation Filter
Section 5: Unit Cost Quick Calculation
Section 6: Unit Cost Quick Calculation Department
Section 7: Unit Cost Quick Calculation Department Filter
Section 8: Table Navigation

 */

public class CostingMap extends MapConfig {

	/****** Costing > RVU Maintenance ******/

	@FindBy(xpath = "//*[contains(@onclick,'csrvumls.htm') and @class='listhelpLnk']")
	private WebElement rvuMaintenancePageHelpLink;

	public WebElement getRvuMaintenancePageHelpLink() {
		return rvuMaintenancePageHelpLink;
	}

//	@FindBy(xpath = "//button/span[text()='RVU Container List']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//span[text()='RVU Container List']")
	private WebElement rvuMaintenanceButtonRvuContainerList;

	public WebElement getRvuMaintenanceButtonRvuContainerList() {
		return rvuMaintenanceButtonRvuContainerList;
	}

//	@FindBy(xpath = "//button/span[text()='Save RVUs']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//span[text()='Save RVUs']/../../..")
	private WebElement rvuMaintenanceButtonSaveRvus;

	public WebElement getRvuMaintenanceButtonSaveRvus() {
		return rvuMaintenanceButtonSaveRvus;
	}

//	@FindBy(xpath = "//button/span[text()='Undo']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//span[text()='Undo']/../../..")
	private WebElement rvuMaintenanceButtonUndo;

	public WebElement getRvuMaintenanceButtonUndo() {
		return rvuMaintenanceButtonUndo;
	}

//	@FindBy(xpath = "//button/span[text()='Apply Selections']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//span[text()='Apply Selections']/../../..")
	private WebElement rvuMaintenanceButtonApplySelections;

	public WebElement getRvuMaintenanceButtonApplySelections() {
		return rvuMaintenanceButtonApplySelections;
	}

//	@FindBy(xpath = "//button/span[text()='Clear RVUs & Save']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//span[text()='Clear RVUs & Save']")
	private WebElement rvuMaintenanceButtonClearRVUsAndSave;

	public WebElement getRvuMaintenanceButtonClearRvusAndSave() {
		return rvuMaintenanceButtonClearRVUsAndSave;
		
	}


//	Omkar 6/12/2023 : Changes in xpath for 11.2
//	@FindBy(xpath = "//button/span[text()='Maintain RVUs']")
//	@FindBy(xpath = "//span[text()='Maintain RVUs']")

//	@FindBy(xpath = "//button/span[text()='Maintain RVUs']")
	//Shilpa update xpath for 11.2 on 12.06.2023
	@FindBy(xpath = "//span[text()='Maintain RVUs']/../../..")

	private WebElement rvuMaintenanceButtonMaintainRVUs;

	public WebElement getRvuMaintenanceButtonMaintainRVUs() {
		return rvuMaintenanceButtonMaintainRVUs;
	}

//	@FindBy(xpath = "//button/span[text()='Copy RVUs']")
	//Shilpa update xpath for 11.2
	@FindBy(xpath = "//span[text()='Copy RVUs']")
	private WebElement rvuMaintenanceButtonCopyRVUs;

	public WebElement getRvuMaintenanceButtonCopyRvus() {
		return rvuMaintenanceButtonCopyRVUs;
	}

//	@FindBy(xpath = "//button/span[text()='Export to Spreadsheet']")
	//Shilpa update xpath for 11.2
	@FindBy(xpath = "//span[text()='Export to Spreadsheet']")
	private WebElement rvuMaintenanceButtonExportToSpreadsheet;

	public WebElement getRvuMaintenanceButtonExportToSpreadsheet() {
		return rvuMaintenanceButtonExportToSpreadsheet;
	}

	@FindBy(xpath = "//*[contains(@id,'costing_rvusmaintenance_tabId-body')]/descendant::span[text()='Filter']")
	private WebElement rvuMaintenanceButtonFilter;

	public WebElement getRvuMaintenanceButtonFilter() {
		return rvuMaintenanceButtonFilter;
	}

	@FindBy(xpath = "//*[contains(text(),'Clear Filter')]")
	private WebElement rvuMaintenanceButtonClearFilter;

	public WebElement getRvuMaintenanceButtonClearFilter() {
		return rvuMaintenanceButtonClearFilter;
	}

	@FindBy(xpath = "//*[text()='Export to Spreadsheet']/ancestor::div[contains(@class, 'x-btn')]/following-sibling::div/descendant::button/span[text()='Import']")
	private WebElement getRvuMaintenanceMaintainRvuPageButtonImport;

	public WebElement getRvuMaintenanceMaintainRvuPageButtonImport() {
		return getRvuMaintenanceMaintainRvuPageButtonImport;
	}

//	Omkar 6/12/2023 : Changes in xpath for 11.2
//	@FindBy(xpath = "//div[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']//button/span[text()='Import']")
	@FindBy(xpath = "//span[contains(@id,'abutton')][text()='Import']")
	private WebElement rvuMaintenanceButtonImport;

	public WebElement getRvuMaintenanceButtonImport() {
		return rvuMaintenanceButtonImport;
	}

//	Omkar 6/12/2023 : Changes in xpath for 11.2
//	@FindBy(xpath = "//*[text()='Export to Spreadsheet']/ancestor::div[contains(@class, 'x-btn')]/following-sibling::div/descendant::button/span[text()='Export']")
	@FindBy(xpath = "//*[contains(@id,'abutton')][text()='Export']")
	private WebElement getRvuMaintenanceMaintainRvuPageButtonExport;

	public WebElement getRvuMaintenanceMaintainRvuPageButtonExport() {
		return getRvuMaintenanceMaintainRvuPageButtonExport;
	}

//	Omkar 6/12/2023 : Changes in xpath for 11.2
//	@FindBy(xpath = "//div[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']//button/span[text()='Export']")
	@FindBy(xpath = "//span[contains(@id,'abutton')][text()='Export']")
	private WebElement rvuMaintenanceButtonExport;

	public WebElement getRvuMaintenanceButtonExport() {
		return rvuMaintenanceButtonExport;
	}

//	@FindBy(xpath = "//label[text()='Entity']/../following-sibling::td/descendant::div[contains(@class,'trigger')]")
	@FindBy(xpath = "//span[text()='Entity']/../../following-sibling::div[contains(@id,'dynamiccombo')]")
	private WebElement getRvuMaintenanceDropdownEntity;

	public WebElement getRvuMaintenanceDropdownEntity() {
		return getRvuMaintenanceDropdownEntity;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='150 Marina Medical Center']/..")
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='150 Marina Medical Center']/..")
	private WebElement getRvuMaintenanceDropdownEntityOptions;

	public WebElement getRvuMaintenanceDropdownEntityOptions() {
		return getRvuMaintenanceDropdownEntityOptions;
	}

//	@FindBy(xpath = "//label[text()='Effective Month Start ']/../descendant::div[contains(@class,'trigger')]")
	@FindBy(xpath = "(//label[text()='Effective Month Start ']/../../..//div//following::div[contains(@class,'customComboTriggerCls ')])[1]")
	private WebElement getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown;

	public WebElement getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown() {
		return getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Jan']/..")
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='Jan']/..")
	private WebElement getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions;

	public WebElement getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions() {
		return getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions;
	}

//	@FindBy(xpath = "//label[text()='Effective Month Start ']/../descendant::div[contains(@class,'trigger')][2]")
	@FindBy(xpath = "(//label[text()='Effective Month Start ']/../../..//div//following::div[contains(@class,'customComboTriggerCls ')])[2]")
	private WebElement getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown;

	public WebElement getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown() {
		return getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='2011']/..")
	//	@FindBy(xpath = "//*[@name=\"selectedYear\"]")
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='2011']/..")
	private WebElement getRvuMaintenanceDropdownEffectiveMonthStartYearOptions;

	public WebElement getRvuMaintenanceDropdownEffectiveMonthStartYearOptions() {
		return getRvuMaintenanceDropdownEffectiveMonthStartYearOptions;
	}

//	@FindBy(xpath = "//*[@name='costModelScenarioId']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//*[@name='costModelScenarioId']/parent::div/parent::div/../..")
	private WebElement getRvuMaintenanceDropdownCostModelScenario;

	public WebElement getRvuMaintenanceDropdownCostModelScenario() {
		return getRvuMaintenanceDropdownCostModelScenario;
	}
	@FindBy(xpath = "//input[@name='costModelScenarioId']")
	private WebElement getRvuMaintenanceDropdownCostModelScenarioNone;

	public WebElement getRvuMaintenanceDropdownCostModelScenarioNone() {
		return getRvuMaintenanceDropdownCostModelScenarioNone;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='ADS-302 LG By Month']/..")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='ADS-302 LG By Month']/..")
	private WebElement getRvuMaintenanceDropdownCostModelScenarioOptions;

	public WebElement getRvuMaintenanceDropdownCostModelScenarioOptions() {
		return getRvuMaintenanceDropdownCostModelScenarioOptions;
	}

//	@FindBy(xpath = "//*[@name='activityVolumeScenarioId']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//*[@name='activityVolumeScenarioId']/parent::div/parent::div/../..")
	private WebElement getRvuMaintenanceDropdownActivityVolumeScenario;

	public WebElement getRvuMaintenanceDropdownActivityVolumeScenario() {
		return getRvuMaintenanceDropdownActivityVolumeScenario;
	}
	@FindBy(xpath = "//input[@name='activityVolumeScenarioId']")
	private WebElement getRvuMaintenanceDropdownActivityVolumeScenarioNone;

	public WebElement getRvuMaintenanceDropdownActivityVolumeScenarioNone() {
		return getRvuMaintenanceDropdownActivityVolumeScenarioNone;
	}
	

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='00FZTEST 00 fz test 1/15/2019']/..")
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='00FZTEST 00 fz test 1/15/2019']/..")

	//Shilpa update xpath for 11.2 on 12.07.2023
	private WebElement getRvuMaintenanceDropdownActivityVolumeScenarioOptions;

	public WebElement getRvuMaintenanceDropdownActivityVolumeScenarioOptions() {
		return getRvuMaintenanceDropdownActivityVolumeScenarioOptions;
	}

//	@FindBy(xpath = "//*[@name='priceListId']")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//*[@name='priceListId']/parent::div/parent::div/../..")

	private WebElement getRvuMaintenanceDropdownPriceList;

	public WebElement getRvuMaintenanceDropdownPriceList() {
		return getRvuMaintenanceDropdownPriceList;
	}
	@FindBy(xpath = "//input[@name='priceListId']")

	private WebElement getRvuMaintenanceDropdownPriceListNone;

	public WebElement getRvuMaintenanceDropdownPriceListNone() {
		return getRvuMaintenanceDropdownPriceListNone;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='0TB  Test']/..")
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='0TB  Test']/..")

	private WebElement getRvuMaintenanceDropdownPriceListOptions;

	public WebElement getRvuMaintenanceDropdownPriceListOptions() {
		return getRvuMaintenanceDropdownPriceListOptions;
	}

//	@FindBy(xpath = "//label[text()='Based On']/ancestor::table/following-sibling::table/descendant::div[contains(@class,'trigger')]")
	//shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//*[@name='closestPriorMonth']/parent::div/parent::div")
	private WebElement getRvuMaintenanceDropdownBasedOn;

	public WebElement getRvuMaintenanceDropdownBasedOn() {
		return getRvuMaintenanceDropdownBasedOn;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Stored Month or Closest Prior']/..")
	//shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='Stored Month or Closest Prior']/..")
	private WebElement getRvuMaintenanceDropdownBasedOnOptions;

	public WebElement getRvuMaintenanceDropdownBasedOnOptions() {
		return getRvuMaintenanceDropdownBasedOnOptions;
	}

//	@FindBy(xpath = "//*[@name='volumesConfig']/parent::td/following-sibling::td/div")
	//shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//*[@name='volumesConfig']/parent::div/parent::div")
	private WebElement getRvuMaintenanceDropdownBasedOnVolumes;

	public WebElement getRvuMaintenanceDropdownBasedOnVolumes() {
		return getRvuMaintenanceDropdownBasedOnVolumes;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='With and Without Volumes']/..")
	//shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='With and Without Volumes']/..")
	private WebElement getRvuMaintenanceDropdownBasedOnVolumesOptions;

	public WebElement getRvuMaintenanceDropdownBasedOnVolumesOptions() {
		return getRvuMaintenanceDropdownBasedOnVolumesOptions;
	}

//	@FindBy(xpath = "//*[@name='pricesConfig']/parent::td/following-sibling::td/div")
	//shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//*[@name='pricesConfig']/parent::div/parent::div")
	private WebElement getRvuMaintenanceDropdownBasedOnPrices;

	public WebElement getRvuMaintenanceDropdownBasedOnPrices() {
		return getRvuMaintenanceDropdownBasedOnPrices;
	}

//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='With and Without Prices']/..")
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='With and Without Prices']/..")
	private WebElement getRvuMaintenanceDropdownBasedOnPricesOptions;

	public WebElement getRvuMaintenanceDropdownBasedOnPricesOptions() {
		return getRvuMaintenanceDropdownBasedOnPricesOptions;
	}

	/****** End RVU Maintenance ******/

	/****** Costing > RVU Maintenance > Filter ******/

	@FindBy(name = "field")
	private WebElement rvuMaintenanceFilterField;

	public WebElement getRvuMaintenanceFilterField() {
		return rvuMaintenanceFilterField;
	}

	@FindBy(name = "operator")
	private WebElement rvuMaintenanceFilterFieldOperator;

	public WebElement getRvuMaintenanceFilterFieldOperator() {
		return rvuMaintenanceFilterFieldOperator;
	}

	@FindBy(name = "condition")
	private WebElement rvuMaintenanceFilterFieldCondition;

	public WebElement getRvuMaintenanceFilterFieldCondition() {
		return rvuMaintenanceFilterFieldCondition;
	}

	@FindBy(name = "valuefield")
	private WebElement rvuMaintenanceFilterFieldValue;

	public WebElement getRvuMaintenanceFilterFieldValue() {
		return rvuMaintenanceFilterFieldValue;
	}

	@FindBy(xpath = "//*[contains(@class,'box-item')]/descendant::span[text()='Add']")
	private WebElement rvuMaintenanceFilterButtonAdd;

	public WebElement getRvuMaintenanceFilterButtonAdd() {
		return rvuMaintenanceFilterButtonAdd;
	}

	@FindBy(xpath = "//*[contains(text(),'Apply Filter')]")
	private WebElement rvuMaintenanceFilterButtonApplyFilter;

	public WebElement getRvuMaintenanceFilterButtonApplyFilter() {
		return rvuMaintenanceFilterButtonApplyFilter;
	}

	//	Omkar 26/4/23 : Change in xpath for 11.2
	//	@FindBy(xpath = "//*[contains(@class,'docked-bottom')]/descendant::span[text()='Cancel & Close']")
	//	@FindBy(xpath ="//a[contains(@class,'x-btn cancelCloseBtn')]//span[text()='Cancel & Close']")
	@FindBy(xpath ="//span[text()='Cancel & Close']") // xpath not working in create new contract model cancel and close.To be checked once else create new one.
	private WebElement rvuMaintenanceFilterButtonCancelAndClose;

	public WebElement getRvuMaintenanceFilterButtonCancelAndClose() {
		return rvuMaintenanceFilterButtonCancelAndClose;
	}

//	@FindBy(xpath = "//div[contains(@id,'listEl')]/ul/li[text()='Entity Code']/..")
	//Shilpa update xpath for 11.2 on 12.07.2023
	@FindBy(xpath = "//div[contains(@id,'listWrap')]/ul/li[text()='Entity Code']/..")
	private WebElement getRvuMaintenanceDialogFilterRvuContainerListFieldOptions;

	public WebElement getRvuMaintenanceDialogFilterRvuContainerListFieldOptions() {
		return getRvuMaintenanceDialogFilterRvuContainerListFieldOptions;
	}

	/****** End Costing > RVU Maintenance > Filter ******/

	/****** Costing > Cost Model Scenario Calculation *****/

	@FindBy(xpath = "//*[contains(@onclick,'cscmsls.htm') and @class='listhelpLnk']")
	private WebElement costModelScenarioCalculationPageHelpLink;

	public WebElement getCostModelScenarioCalculationPageHelpLink() {
		return costModelScenarioCalculationPageHelpLink;
	}

//	@FindBy(xpath = "//div[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']//*[contains(text(),'Edit')]")
	//Shilpa update xpath for 11.2 on 1.2.2024
	@FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[contains(text(),'Edit')]")
	private WebElement costModelScenarioCalculationButtonEdit;

	public WebElement getCostModelScenarioCalculationButtonEdit() {
		return costModelScenarioCalculationButtonEdit;
	}

//	@FindBy(xpath = "//*[contains(@id,'costing_costingmodelscenario_tabId-body')]/descendant::span[text()='Filter']")
	//Shilpa update xpath for 11.2 on 1.2.2024
	@FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[./text()='Filter']")
	private WebElement costModelScenarioCalculationButtonFilter;

	public WebElement getCostModelScenarioCalculationButtonFilter() {
		return costModelScenarioCalculationButtonFilter;
	}

	@FindBy(xpath = "//*[contains(@id,'costing_costingmodelscenario_tabId-body')]/descendant::span[text()='Clear Filter']")
	private WebElement costModelScenarioCalculationButtonClearFilter;

	public WebElement getCostModelScenarioCalculationButtonClearFilter() {
		return costModelScenarioCalculationButtonClearFilter;
	}

//	@FindBy(xpath = "//em[contains(@id,'abutton')]/button/*[contains(text(),'Calculate')]")
//	@FindBy(xpath = "(//h1//following::span[text()='Calculate']/../..)[1]")
	@FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[./text()='Calculate']")
	private WebElement costModelScenarioCalculationButtonCalculate;

	public WebElement getCostModelScenarioCalculationButtonCalculate() {
		return costModelScenarioCalculationButtonCalculate;
	}

//	@FindBy(xpath = "//div[@class='x-btn btnCls x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']//*[contains(text(),'Results')]")
	//Shilpa update xpath for 11.2 on 1.2.2024
	@FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[./text()='Results']")

	private WebElement costModelScenarioCalculationButtonResults;

	public WebElement getCostModelScenarioCalculationButtonResults() {
		return costModelScenarioCalculationButtonResults;
	}

	/****** End Cost Model Scenario Calculation ******/

	/****** Costing > Cost Model Scenario Calculation > Filter ******/

	@FindBy(name = "field")
	private WebElement costModelScenarioCalculationFilterField;

	public WebElement getCostModelScenarioCalculationFilterField() {
		return costModelScenarioCalculationFilterField;
	}

	@FindBy(name = "operator")
	private WebElement costModelScenarioCalculationFilterFieldOperator;

	public WebElement getCostModelScenarioCalculationFilterFieldOperator() {
		return costModelScenarioCalculationFilterFieldOperator;
	}

	@FindBy(name = "condition")
	private WebElement costModelScenarioCalculationFilterFieldCondition;

	public WebElement getCostModelScenarioCalculationFilterFieldCondition() {
		return costModelScenarioCalculationFilterFieldCondition;
	}

	@FindBy(name = "valuefield")
	private WebElement costModelScenarioCalculationFilterFieldValue;

	public WebElement getCostModelScenarioCalculationFilterFieldValue() {
		return costModelScenarioCalculationFilterFieldValue;
	}

	@FindBy(xpath = "//*[contains(@class,'box-item')]/descendant::span[text()='Add']")
	private WebElement costModelScenarioCalculationFilterButtonAdd;

	public WebElement getCostModelScenarioCalculationFilterButtonAdd() {
		return costModelScenarioCalculationFilterButtonAdd;
	}

	@FindBy(xpath = "//*[contains(text(),'Apply Filter')]")
	private WebElement costModelScenarioCalculationFilterButtonApplyFilter;

	public WebElement getCostModelScenarioCalculationFilterButtonApplyFilter() {
		return costModelScenarioCalculationFilterButtonApplyFilter;
	}

	@FindBy(xpath = "//*[contains(@class,'winCls')]/descendant::span[text()='Cancel & Close']")
	private WebElement costModelScenarioCalculationFilterButtonCancelAndClose;

	public WebElement getCostModelScenarioCalculationFilterButtonCancelAndClose() {
		return costModelScenarioCalculationFilterButtonCancelAndClose;
	}

	/****** End Cost Model Scenario Calculation > Filter ******/

	/****** Costing > Unit Cost Quick Calculation *****/

	@FindBy(xpath = "//*[contains(@onclick,'csucqcfd.htm') and @class='listhelpLnk']")
	private WebElement unitCostQuickCalculationPageHelpLink;

	public WebElement getUnitCostQuickCalculationPageHelpLink() {
		return unitCostQuickCalculationPageHelpLink;
	}

	@FindBy(name = "costModelId")
	private WebElement unitCostQuickCalculationDropdownCostModel;

	public WebElement getUnitCostQuickCalculationDropdownCostModel() {
		return unitCostQuickCalculationDropdownCostModel;
	}

	@FindBy(name = "costModelScenarioId")
	private WebElement unitCostQuickCalculationDropdownCostModelScenario;

	public WebElement getUnitCostQuickCalculationDropdownCostModelScenario() {
		return unitCostQuickCalculationDropdownCostModelScenario;
	}

	@FindBy(name = "entityCode")
	private WebElement unitCostQuickCalculationDropdownEntity;

	public WebElement getUnitCostQuickCalculationDropdownEntity() {
		return unitCostQuickCalculationDropdownEntity;
	}

	@FindBy(xpath = "//*[contains(@id,'singleselectorform')]/descendant::*[contains(text(),'Select')]")
	private WebElement unitCostQuickCalculationButtonSelect;

	public WebElement getUnitCostQuickCalculationButtonSelect() {
		return unitCostQuickCalculationButtonSelect;
	}

	@FindBy(xpath = "//*[contains(@name, 'resultStored')]")
	private WebElement unitCostQuickCalculationFieldResultsStoredFor;

	public WebElement getUnitCostQuickCalculationFieldResultsStoredFor() {
		return unitCostQuickCalculationFieldResultsStoredFor;
	}

//	@FindBy(xpath = "//input[contains(@id,'checkboxfield')]")
	//Shilpa update xpath for 11.2 on 11.22.2023
	@FindBy(xpath = "//input[contains(@id,'checkbox')]")
	private WebElement unitCostQuickCalculationCheckBoxColumnsToDisplayAll;

	public WebElement getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll() {
		return unitCostQuickCalculationCheckBoxColumnsToDisplayAll;
	}

	@FindBy(xpath = "//div[contains(@class,'x-box')]/descendant::*[contains(text(),'Select')][2]")
	private WebElement unitCostQuickCalculationButtonColumnsToDisplaySelect;

	public WebElement getUnitCostQuickCalculationButtonColumnsToDisplaySelect() {
		return unitCostQuickCalculationButtonColumnsToDisplaySelect;
	}

	@FindBy(xpath = "//*[contains(text(),'Apply Selections')]")
	private WebElement unitCostQuickCalculationButtonApplySelections;

	public WebElement getUnitCostQuickCalculationButtonApplySelections() {
		return unitCostQuickCalculationButtonApplySelections;
	}

	@FindBy(xpath = "//*[contains(text(),'Copy to Quick RVUs')]")
	private WebElement unitCostQuickCalculationButtonCopyToQuickRVUs;

	public WebElement getUnitCostQuickCalculationButtonCopyToQuickRVUs() {
		return unitCostQuickCalculationButtonCopyToQuickRVUs;
	}

	@FindBy(xpath = "//*[contains(text(),'Clear Quick RVUs & Save')]")
	private WebElement unitCostQuickCalculationButtonClearQuickRVUsAndSave;

	public WebElement getUnitCostQuickCalculationButtonClearQuickRVUsAndSave() {
		return unitCostQuickCalculationButtonClearQuickRVUsAndSave;
	}

	@FindBy(xpath = "//span[contains(text(),'Clear and Save')]")
	private WebElement unitCostQuickCalculationButtonClearAndSave;

	public WebElement getUnitCostQuickCalculationButtonClearAndSave() {
		return unitCostQuickCalculationButtonClearAndSave;
	}

	@FindBy(xpath = "//*[contains(@class,'windowbtn')]//span[contains(text(),'Cancel')]")
	private WebElement unitCostQuickCalculationClearAndSaveButtonCancel;

	public WebElement getUnitCostQuickCalculationClearAndSaveButtonCancel() {
		return unitCostQuickCalculationClearAndSaveButtonCancel;
	}

	@FindBy(xpath = "//span[contains(text(),'Save & Continue')]")
	private WebElement unitCostQuickCalculationButtonSaveAndContinue;

	public WebElement getUnitCostQuickCalculationButtonSaveAndContinue() {
		return unitCostQuickCalculationButtonSaveAndContinue;
	}

	@FindBy(xpath = "//*[contains(@class,'x-box-item')]//span[contains(text(),'Cancel')]")
	private WebElement unitCostQuickCalculationSaveAndContinueButtonCancel;

	public WebElement getUnitCostQuickCalculationSaveAndContinueButtonCancel() {
		return unitCostQuickCalculationSaveAndContinueButtonCancel;
	}

	@FindBy(xpath = "//*[contains(text(),'Undo')]")
	private WebElement unitCostQuickCalculationButtonUndo;

	public WebElement getUnitCostQuickCalculationButtonUndo() {
		return unitCostQuickCalculationButtonUndo;
	}

	@FindBy(xpath = "//*[contains(text(),'Save Quick RVUs')]")
	private WebElement unitCostQuickCalculationButtonSaveQuickRVUs;

	public WebElement getUnitCostQuickCalculationButtonSaveQuickRVUs() {
		return unitCostQuickCalculationButtonSaveQuickRVUs;
	}


//	Omkar 28/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[@class='x-panel gridCls gridClsCondensed lockCls ucqc-grid x-grid-with-row-lines x-grid-locked x-border-item x-box-item x-panel-default x-grid']//*[contains(text(),'Calculate')]")
	@FindBy(xpath = "//span[contains(@class,'x-btn-button-center ')]//*[contains(text(),'Calculate')]") 

	private WebElement unitCostQuickCalculationButtonCalculate;

	public WebElement getUnitCostQuickCalculationButtonCalculate() {
		return unitCostQuickCalculationButtonCalculate;
	}

	@FindBy(xpath = "//*[contains(text(),'Overwrite RVU Maintenance')]")
	private WebElement unitCostQuickCalculationButtonOverwriteRVUMaintenance;

	public WebElement getUnitCostQuickCalculationButtonOverwriteRVUMaintenance() {
		return unitCostQuickCalculationButtonOverwriteRVUMaintenance;
	}

	@FindBy(xpath = "//*[contains(text(),'Hide')]")
	private WebElement unitCostQuickCalculationButtonHide;

	public WebElement getUnitCostQuickCalculationButtonHide() {
		return unitCostQuickCalculationButtonHide;
	}

	@FindBy(xpath = "//*[contains(text(),'Show')]")
	private WebElement unitCostQuickCalculationButtonShow;

	public WebElement getUnitCostQuickCalculationButtonShow() {
		return unitCostQuickCalculationButtonShow;
	}

	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-floating x-layer')])[1]//ul")
	private WebElement unitCostQuickCalculationDropdownCostModelMenuList;

	public WebElement getUnitCostQuickCalculationDropdownCostModelMenuList() {
		return unitCostQuickCalculationDropdownCostModelMenuList;
	}

	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-floating x-layer')])[2]//ul")
	private WebElement unitCostQuickCalculationDropdownCostModelScenarioMenuList;

	public WebElement getUnitCostQuickCalculationDropdownCostModelScenarioMenuList() {
		return unitCostQuickCalculationDropdownCostModelScenarioMenuList;
	}

	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-floating x-layer')])[3]//ul")
	private WebElement unitCostQuickCalculationDropdownEntityMenuList;

	public WebElement getUnitCostQuickCalculationDropdownEntityMenuList() {
		return unitCostQuickCalculationDropdownEntityMenuList;
	}

	// @FindBy(xpath =
	// "//div[@class='x-mask'][4]/preceding::div[@class='x-boundlist-list-ct']/following::ul[4]")
	@FindBy(xpath = "(//div[contains(@class,'x-boundlist-floating x-layer')])[4]//ul") // Shilpa update xpath for 11.2 on 11.20.2023
	// update
	// xpath
	// 08-09-2022
	private WebElement unitCostQuickCalculationDropdownResultsStoredForMenuList;

	public WebElement getUnitCostQuickCalculationDropdownResultsStoredForMenuList() {
		return unitCostQuickCalculationDropdownResultsStoredForMenuList;
	}

	@FindBy(xpath = "//*[contains(@class,'x-column-header-text')][text()=\"Quick Salaries and Wages RVU\"]")
	private WebElement unitCostQuickCalculationHeaderQuickSalariesAndWagesRVU;

	public WebElement getUnitCostQuickCalculationHeaderQuickSalariesAndWagesRVU() {
		return unitCostQuickCalculationHeaderQuickSalariesAndWagesRVU;
	}

	/****** End Unit Cost Quick Calculation *****/

	/****** Unit Cost Quick Calculation > Columns to Display Modal *****/
	@FindBy(xpath = "//*[contains(@id,'buttonsContainer')]/descendant::span[text()='Select']")
	private WebElement unitCostQuickCalculationButtonColumnsToDisplayModalSelect;

	public WebElement getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect() {
		return unitCostQuickCalculationButtonColumnsToDisplayModalSelect;
	}

	@FindBy(xpath = "//*[contains(@id,'button')][text()='Remove']")
	private WebElement unitCostQuickCalculationButtonColumnsToDisplayModalRemove;

	public WebElement getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove() {
		return unitCostQuickCalculationButtonColumnsToDisplayModalRemove;
	}

	@FindBy(xpath = "//*[contains(@class,'x-btn windowbtn')]/descendant::*[text()='Apply']")
	private WebElement unitCostQuickCalculationColumnsToDisplayModalApply;

	public WebElement getUnitCostQuickCalculationColumnsToDisplayModalApply() {
		return unitCostQuickCalculationColumnsToDisplayModalApply;
	}

	@FindBy(xpath = "//*[contains(@class,'x-btn windowbtn')]/descendant::*[text()='Cancel']")
	private WebElement unitCostQuickCalculationColumnsToDisplayModalCancel;

	public WebElement getUnitCostQuickCalculationColumnsToDisplayModalCancel() {
		return unitCostQuickCalculationColumnsToDisplayModalCancel;
	}

	@FindBy(xpath = "//div[contains(@class,'x-tool-pressed')]")
	private WebElement unitCostQuickCalculationColumnsToDisplayModalClose;

	public WebElement getUnitCostQuickCalculationColumnsToDisplayModalClose() {
		return unitCostQuickCalculationColumnsToDisplayModalClose;
	}

	/****** End Unit Cost Quick Calculation > Columns to Display Modal *****/

	/****** Costing > Department > Department Modal *****/
	@FindBy(name = "carrierfield")
	private WebElement unitCostQuickCalculationDepartmentField;

	public WebElement getUnitCostQuickCalculationDepartmentField() {
		return unitCostQuickCalculationDepartmentField;
	}

	@FindBy(xpath = "//*[contains(@class,'filterIconBtn')]/descendant::span[text()='Filter']")
	private static WebElement unitCostQuickCalculationDepartmentButtonFilter;

	public static WebElement getUnitCostQuickCalculationDepartmentButtonFilter() {
		return unitCostQuickCalculationDepartmentButtonFilter;
	}

	@FindBy(xpath = "//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")
	private WebElement unitCostQuickCalculationDepartmentButtonApply;

	public WebElement getUnitCostQuickCalculationDepartmentButtonApply() {
		return unitCostQuickCalculationDepartmentButtonApply;
	}

	@FindBy(xpath = "//span/ancestor::div/following-sibling::div/descendant::span[text()='Cancel & Close'][2]")
	private WebElement unitCostQuickCalculationDepartmentButtonCancelAndClose;

	public WebElement getUnitCostQuickCalculationDepartmentButtonCancelAndClose() {
		return unitCostQuickCalculationDepartmentButtonCancelAndClose;
	}

	//	Omkar 12/04/2023 : Not needed as we have a generalised method
	//	@FindBy(xpath = "//*[contains(@class,'tool-close')]")
	//	private WebElement unitCostQuickCalculationDepartmentButtonClose;
	//
	//	public WebElement getUnitCostQuickCalculationDepartmentButtonClose() {
	//		return unitCostQuickCalculationDepartmentButtonClose;
	//	}

	/****** End Department Modal *****/

	/****** Department Filter *****/
	@FindBy(name = "field")
	private WebElement unitCostQuickCalculationDepartmentFilterField;

	public WebElement getUnitCostQuickCalculationDepartmentFilterField() {
		return unitCostQuickCalculationDepartmentFilterField;
	}

	@FindBy(xpath = "//div[@class=\"x-mask\"][4]/preceding::div[@class=\"x-boundlist-list-ct\"][1]/ul")
	private WebElement unitCostQuickCalculationDepartmentFilterFieldMenuList;

	public WebElement getUnitCostQuickCalculationDepartmentFilterFieldMenuList() {
		return unitCostQuickCalculationDepartmentFilterFieldMenuList;
	}

	@FindBy(name = "operator")
	private WebElement unitCostQuickCalculationDepartmentFilterOperator;

	public WebElement getUnitCostQuickCalculationDepartmentFilterOperator() {
		return unitCostQuickCalculationDepartmentFilterOperator;
	}

	@FindBy(xpath = "//div[@class=\"x-mask\"][4]/preceding::div[@class=\"x-boundlist-list-ct\"][2]/ul")
	private WebElement unitCostQuickCalculationDepartmentFilterOperatorMenuList;

	public WebElement getUnitCostQuickCalculationDepartmentFilterOperatorMenuList() {
		return unitCostQuickCalculationDepartmentFilterOperatorMenuList;
	}

	@FindBy(name = "condition")
	private WebElement unitCostQuickCalculationDepartmentFilterCondition;

	public WebElement getUnitCostQuickCalculationDepartmentFilterCondition() {
		return unitCostQuickCalculationDepartmentFilterCondition;
	}

	@FindBy(xpath = "//div[@class=\"x-mask\"][5]/preceding::div[contains(@class,'x-boundlist-floating')][2]/div/ul")
	private WebElement unitCostQuickCalculationDepartmentFilterConditionMenuList;

	public WebElement getUnitCostQuickCalculationDepartmentFilterConditionMenuList() {
		return unitCostQuickCalculationDepartmentFilterConditionMenuList;
	}

	@FindBy(xpath = "//input[@name='valuefield']")
	private WebElement unitCostQuickCalculationDepartmentFilterValue;

	public WebElement getUnitCostQuickCalculationDepartmentFilterValue() {
		return unitCostQuickCalculationDepartmentFilterValue;
	}

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-footer x-docked-bottom x-toolbar-docked-bottom x-toolbar-footer-docked-bottom x-box-layout-ct']//following::span[text()='Add']")
	@FindBy(xpath="//div[contains(@id,'filter')]//following::span[text()='Add']")
	private WebElement unitCostQuickCalculationDepartmentFilterButtonAdd;

	public WebElement getUnitCostQuickCalculationDepartmentFilterButtonAdd() {
		return unitCostQuickCalculationDepartmentFilterButtonAdd;
	}

	@FindBy(xpath = "//*[contains(@class,'removeAllBtn')]/descendant::span[text()='Remove All']")
	private WebElement unitCostQuickCalculationDepartmentFilterButtonRemoveAll;

	public WebElement getUnitCostQuickCalculationDepartmentFilterButtonRemoveAll() {
		return unitCostQuickCalculationDepartmentFilterButtonRemoveAll;
	}

	@FindBy(xpath = "//*[contains(@class,'applyfilterCls')]/descendant::span[text()='Apply Filter']")
	private WebElement unitCostQuickCalculationDepartmentFilterButtonApplyFilter;

	public WebElement getUnitCostQuickCalculationDepartmentFilterButtonApplyFilter() {
		return unitCostQuickCalculationDepartmentFilterButtonApplyFilter;
	}

	/****** End Department Filter *****/

	/****** Overwrite RVU Maintenance Modal *****/
	@FindBy(xpath = "//*[input/@name='selectedMonth']/descendant::input")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Jan']/..")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonthOptions;

	public WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonthOptions() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonthOptions;
	}

	@FindBy(xpath = "//*[input/@name='selectedYear']/descendant::input")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='2021']/..")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYearOptions;

	public WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYearOptions() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYearOptions;
	}

//	 Omkar 27/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'windowbtn')]/descendant::*[text()='Select All']")
	@FindBy(xpath = "//span[contains(@class,'x-btn-inner-default-small')][text()='Select All']")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonSelectAll;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonSelectAll() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonSelectAll;
	}

//	Omkar 27/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'windowbtn')]/descendant::*[text()='Remove All']")
	@FindBy(xpath = "//span[contains(@class,'x-btn-inner-default-small')][text()='Remove All']")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonRemoveAll;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonRemoveAll() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonRemoveAll;
	}

//	Omkar 27/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'windowbtn')]/descendant::*[text()='Overwrite RVUs']")
	@FindBy(xpath = "//a[contains(@class,'windowbtn')]/descendant::*[text()='Overwrite RVUs']")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs;
	}

//	Omkar 27/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'windowbtn')]/descendant::*[text()='Cancel & Close']")
	@FindBy(xpath = "//a[contains(@class,'windowbtn')]/descendant::*[text()='Cancel & Close']")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose;
	}

//	Omkar 28/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'windowbtn')]/descendant::*[text()='Overwrite']")
	@FindBy(xpath = "//a[contains(@class,'windowbtn')]/descendant::*[text()='Overwrite']")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite;
	}

//	Omkar 28/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'windowbtn')]/descendant::*[text()='Cancel']")
	@FindBy(xpath = "//a[contains(@class,'windowbtn')]/descendant::*[text()='Cancel']")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel;
	}

	@FindBy(xpath = "//*[contains(@id,'warningwindow')]/descendant::*[contains(@class,'x-tool-close')]")
	private WebElement unitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonClose;

	public WebElement getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonClose() {
		return unitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonClose;
	}

	@FindBy(xpath = "//div[@id='boundlist-1376-listEl']/ul")
	private WebElement unitCostQuickCalculationDropdownDestinationEffectiveMonthStartMonthMenuList;

	public WebElement getUnitCostQuickCalculationDropdownDestinationEffectiveMonthStartMonthMenuList() {
		return unitCostQuickCalculationDropdownDestinationEffectiveMonthStartMonthMenuList;
	}

	@FindBy(xpath = "//div[@id='boundlist-1378-listEl']/ul")
	private WebElement unitCostQuickCalculationDropdownDestinationEffectiveMonthStartYearMenuList;

	public WebElement getUnitCostQuickCalculationDropdownDestinationEffectiveMonthStartYearMenuList() {
		return unitCostQuickCalculationDropdownDestinationEffectiveMonthStartYearMenuList;
	}

	/****** End Overwrite RVU Maintenance Modal *****/

	/****** Table Navigation *****/

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-docked-noborder-right x-docked-noborder-bottom x-docked-noborder-left']//div[@class='x-toolbar pagingCls x-box-item x-toolbar-item x-toolbar-default x-box-layout-ct']//div[contains(@id,'custompagingtoolbar')]//*[@data-qtip = 'First Page']")
	//shilpa update xpath for 11.2 on 1.2.2024
	@FindBy(xpath = "//div[contains(@id,'rvumasterlist')]//*[@data-qtip='First Page']")

	private WebElement costingMapTableButtonFirst;

	public WebElement getCostingMapTableButtonFirst() {
		return costingMapTableButtonFirst;
	}

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-docked-noborder-right x-docked-noborder-bottom x-docked-noborder-left']//div[@class='x-toolbar pagingCls x-box-item x-toolbar-item x-toolbar-default x-box-layout-ct']//div[contains(@id,'custompagingtoolbar')]//*[@data-qtip = 'Previous Page']")
	//shilpa update xpath for 11.2 on 1.2.2024
		@FindBy(xpath = "//div[contains(@id,'rvumasterlist')]//*[@data-qtip='Previous Page']")

	private WebElement costingMapTableButtonPrevious;

	public WebElement getCostingMapTableButtonPrevious() {
		return costingMapTableButtonPrevious;
	}

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-docked-noborder-right x-docked-noborder-bottom x-docked-noborder-left']//div[@class='x-toolbar pagingCls x-box-item x-toolbar-item x-toolbar-default x-box-layout-ct']//div[contains(@id,'custompagingtoolbar')]//*[contains(@name, 'inputItem')]")
	//shilpa update xpath for 11.2 on 1.2.2024
	@FindBy(xpath = "//div[contains(@id,'rvumasterlist')]//*[@name='inputItem']")
	private WebElement costingMapTableFieldInputNumber;
	public WebElement getCostingMapTableFieldInputNumber() {
		return costingMapTableFieldInputNumber;
	}

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-docked-noborder-right x-docked-noborder-bottom x-docked-noborder-left']//div[@class='x-toolbar pagingCls x-box-item x-toolbar-item x-toolbar-default x-box-layout-ct']//div[contains(@id,'custompagingtoolbar')]//*[contains(@class, 'go-button')]")
	//shilpa update xpath for 11.2 on 1.2.2024
		@FindBy(xpath = "//div[contains(@id,'rvumasterlist')]//*[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-go-button ']")
	private WebElement costingMapTableButtonGo;

	public WebElement getCostingMapTableButtonGo() {
		return costingMapTableButtonGo;
	}

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-docked-noborder-right x-docked-noborder-bottom x-docked-noborder-left']//div[@class='x-toolbar pagingCls x-box-item x-toolbar-item x-toolbar-default x-box-layout-ct']//div[contains(@id,'custompagingtoolbar')]//*[@data-qtip = 'Next Page']")
	//shilpa update xpath for 11.2 on 1.2.2024
    @FindBy(xpath = "//div[contains(@id,'rvumasterlist')]//*[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-next-button ']")
	private WebElement costingMapTableButtonNext;

	public WebElement getCostingMapTableButtonNext() {
		return costingMapTableButtonNext;
	}

//	@FindBy(xpath = "//div[@class='x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-docked-noborder-right x-docked-noborder-bottom x-docked-noborder-left']//div[@class='x-toolbar pagingCls x-box-item x-toolbar-item x-toolbar-default x-box-layout-ct']//div[contains(@id,'custompagingtoolbar')]//*[@data-qtip = 'Last Page']")
	//shilpa update xpath for 11.2 on 1.2.2024
    @FindBy(xpath = "//div[contains(@id,'rvumasterlist')]//*[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-last-button ']")
	private WebElement costingMapTableButtonLast;

	public WebElement getCostingMapTableButtonLast() {
		return costingMapTableButtonLast;
	}
	
    @FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[@data-qtip='First Page']")
	private WebElement getCostModelCalcTableButtonFirst;

	public WebElement getCostModelCalcTableButtonFirst() {
		return getCostModelCalcTableButtonFirst;
	}
	 
	@FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[@data-qtip='Previous Page']")
	 private WebElement getCostModelCalcTableButtonPrevious;
     public WebElement getCostModelCalcTableButtonPrevious() {
	 return getCostModelCalcTableButtonPrevious;
		}
     @FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[@name='inputItem']")
	 private WebElement getCostModelCalcTableButtonInputNumber;
     public WebElement getCostModelCalcTableButtonInputNumber() {
	 return getCostModelCalcTableButtonInputNumber;
		}	
    
     @FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-go-button ']")
	 private WebElement getCostModelCalcTableButtonGo;
     public WebElement getCostModelCalcTableButtonGo() {
	 return getCostModelCalcTableButtonGo;
		}	
     @FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-next-button ']")
	 private WebElement getCostModelCalcTableButtonNext;
     public WebElement getCostModelCalcTableButtonNext() {
	 return getCostModelCalcTableButtonNext;
		}	
     @FindBy(xpath = "//div[contains(@id,'costmodelscenariolist')]//*[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-last-button ']")
	 private WebElement getCostModelCalcTableButtonLast;
     public WebElement getCostModelCalcTableButtonLast() {
	 return getCostModelCalcTableButtonLast;
		}	

	/****** End Table Navigation *****/
	//Shilpa
	//Shilpa
	@FindBy(name = "field")
	private static WebElement calculationFilterPopUpFilterDrop;
	public static WebElement getcalculationFilterPopUpFilterDrop() {
		return calculationFilterPopUpFilterDrop;
	}

//	@FindBy(xpath = "//div[contains(@class,'x-btn windowbtn')]//button")
	@FindBy(xpath = "(//div[contains(@id,'container')]//span[text()='Select']/../../..)[1]")
	private WebElement selectCostModelScenariosInEvaluationOrder;
	public WebElement getSelctCostModelScenariosInEvaluationOrder() {
		return selectCostModelScenariosInEvaluationOrder;
	}

	@FindBy(xpath = "//div[contains(@id,'custommultiselector')]//span[contains(@id,'button')][text()='Select']")
	private WebElement costModelButtonColumnsToDisplayModalSelect;
	public WebElement getcostModelButtonColumnsToDisplayModalSelect() {
		return costModelButtonColumnsToDisplayModalSelect;
	}

	@FindBy(xpath = "//input[@name='utCstStartDate']")
	private WebElement getCostModelScenariosinEvaluationOrderFrom;
	public WebElement getCostModelScenariosinEvaluationOrderFrom() {
		return getCostModelScenariosinEvaluationOrderFrom;
	}

	@FindBy(xpath = "//input[@name='utCstEndDate']")
	private WebElement getCostModelScenariosinEvaluationOrderTo;
	public WebElement getCostModelScenariosinEvaluationOrderTo() {
		return getCostModelScenariosinEvaluationOrderTo;
	}

//	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[contains(text(),'Apr')]/..)[1]")
	@FindBy(xpath = "(//div[contains(@class,'boundlist')]/ul/li[contains(text(),'Apr')]/..)[1]")
	private WebElement getCostModelScenariosinEvaluationOrderFromList;
	public WebElement getCostModelScenariosinEvaluationOrderFromList() {
		return getCostModelScenariosinEvaluationOrderFromList;
	}

//	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[contains(text(),'Apr')]/..)[2]")
	@FindBy(xpath = "(//div[contains(@class,'boundlist')]/ul/li[contains(text(),'Apr')]/..)[2]")
	private WebElement getCostModelScenariosinEvaluationOrderToList;
	public WebElement getCostModelScenariosinEvaluationOrderToList() {
		return getCostModelScenariosinEvaluationOrderToList;
	}

	@FindBy(xpath = "//label[text()='Assigned Cost Destination']//following::input[contains(@id,'dynamiccombo')][1]")
	private WebElement getCostModelScenariosinEvaluationOrderAssignedCost;
	public WebElement getCostModelScenariosinEvaluationOrderAssignedCost() {
		return getCostModelScenariosinEvaluationOrderAssignedCost;
	}

//	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[contains(text(),'1 : Primary Assigned Cost Destination')]/..)[1]")
	@FindBy(xpath = "(//div[contains(@class,'boundlist')]/ul/li[contains(text(),'1 : Primary Assigned Cost Destination')]/..)[1]")
	private WebElement getCostModelScenariosinEvaluationOrderAssignedCostList;
	public WebElement getCostModelScenariosinEvaluationOrderAssignedCostList() {
		return getCostModelScenariosinEvaluationOrderAssignedCostList;
	}

//	@FindBy(xpath = "//label[text()='Encounter Types']//following::button[contains(@id,'abutton')][1]")
	//shilpa updated xpath for 11.2 on 12.27.2023
	@FindBy(xpath = "//span[text()='Encounter Types']//following::span[contains(@id,'abutton')][1]")
	private WebElement getCostModelScenariosinEvaluationOrderEncounterSelect;
	public WebElement getCostModelScenariosinEvaluationOrderEncounterSelect() {
		return getCostModelScenariosinEvaluationOrderEncounterSelect;
	}

//	@FindBy(xpath = "//h1[text()='Select']//following::input[contains(@id,'checkboxfield')]")
	//shilpa updated xpath for 11.2 on 12.27.2023
	@FindBy(xpath = "//h1[text()='Select']//following::input[contains(@name,'selectAllChk')]")
	private WebElement getCostModelScenariosinEvaluationOrderEncounterSelectAll;
	public WebElement getCostModelScenariosinEvaluationOrderEncounterSelectAll() {
		return getCostModelScenariosinEvaluationOrderEncounterSelectAll;
	}

//	@FindBy(xpath = "//label[text()='Entities']//following::button[contains(@id,'abutton')][1]")
	//shilpa updated xpath for 11.2 on 12.27.2023
	@FindBy(xpath = "//span[text()='Entities']//following::span[contains(@id,'abutton')][1]")
	private WebElement getCostModelScenariosinEvaluationOrderEntitiesSelect;
	public WebElement getCostModelScenariosinEvaluationOrderEntitiesSelect() {
		return getCostModelScenariosinEvaluationOrderEntitiesSelect;
	}

//	@FindBy(xpath = "//label[text()='Admission']//preceding::input[contains(@id,'checkboxfield')][1]")
	@FindBy(xpath = "//input[@name='useAdmissionTimePeriod']")
	private WebElement getCostModelScenariosinEvaluationOrderAdmissionCheck;
	public WebElement getCostModelScenariosinEvaluationOrderAdmissionCheck() {
		return getCostModelScenariosinEvaluationOrderAdmissionCheck;
	}

//	@FindBy(xpath = "//label[text()='Discharge']//preceding::input[contains(@id,'checkboxfield')][1]")
	@FindBy(xpath = "//input[@name='useDischargeTimePeriod']")
	private WebElement getCostModelScenariosinEvaluationOrderDischargeCheck;
	public WebElement getCostModelScenariosinEvaluationOrderDischargeCheck() {
		return getCostModelScenariosinEvaluationOrderDischargeCheck;
	}

//	@FindBy(xpath = "//label[text()='Posting']//preceding::input[contains(@id,'checkboxfield')][1]")
	@FindBy(xpath = "//input[@name='usePostingTimePeriod']")
	private WebElement getCostModelScenariosinEvaluationOrderPostingCheck;
	public WebElement getCostModelScenariosinEvaluationOrderPostingCheck() {
		return getCostModelScenariosinEvaluationOrderPostingCheck;
	}
	//Shilpa updated xpath for 11.2
	@FindBy(xpath = "//div[contains(@id,'messagebox')]//span[text()='Save & Continue']/parent::span")
	private WebElement getCostModelScenariosinEvaluationOrderSave;
	public WebElement getCostModelScenariosinEvaluationOrderSave() {
		return getCostModelScenariosinEvaluationOrderSave;
	}

	@FindBy(xpath = "(//div[contains(@id,'statustoolbar')]//input[@name='searchText']//following::span[contains(@id,'button')])[1]")
	private static WebElement getCalculationModelSearch;
	public static WebElement getCalculationModelSearch() {
		return getCalculationModelSearch;
	}

//	Omkar 6/12/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[@id='modelFoldertree-body']//div[text()='Costing']/img[1]")
	@FindBy(xpath ="//div[@id='modelFoldertree-body']//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Costing']/../div[contains(@class,'x-tree-elbow')]")
	private static WebElement getCostingTreeItem;
	public static WebElement getCostingTreeItem() {
		return getCostingTreeItem;
	}

	@FindBy(xpath = "//td/div[text()='Calculate Encounter Cost']")
	private static WebElement getCalculateEncounterCostItem;
	public static WebElement getCalculateEncounterCostItem() {
		return getCalculateEncounterCostItem;
	}

//	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//following::span[contains(@id,'abutton')and (text()='New')]//parent::button")
	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//span[text()='New']")
	private static WebElement getEncounterNewBtn;
	public static WebElement getEncounterNewBtn() {
		return getEncounterNewBtn;
	}

//	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//following::input[contains(@id,'atextname')]")
	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationform')]//h1[text()='Encounter Cost Calculation Scenario']")
	private static WebElement getEncounterPageText;
	public static WebElement getEncounterPageText() {
		return getEncounterPageText;
	}

	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//following::input[contains(@id,'atextname')]")
	private static WebElement getEncounterName;
	public static WebElement getEncounterName() {
		return getEncounterName;
	}

	@FindBy(xpath = "//li[text()='<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY1/']")
	private static WebElement getEncounterFileLocDropdown;
	public static WebElement getEncounterFileLocDropdown() {
		return getEncounterFileLocDropdown;
	}

	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationform')]//following::span[text()='Calculate']")
	private static WebElement getEncounterCalculateBtn;
	public static WebElement getEncounterCalculateBtn() {
		return getEncounterCalculateBtn;
	}

//	@FindBy(xpath = "//span[text()='Save']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationform')]//following::span[text()='Save']")
	private static WebElement getEncounterSave;
	public static WebElement getEncounterSave() {return getEncounterSave;
	}

	@FindBy(xpath = "(//div[contains(@id,'filterwindow')]//following-sibling::div[contains(@class,'x-boundlist-floating x-layer')])[1]//ul")
	private WebElement getCalculationFilterDropdownMenuList;
	public WebElement getCalculationFilterDropdownMenuList() {
		return getCalculationFilterDropdownMenuList;
	}
	//	Omkar 14/6/2023 : xpath changes for 11.2
	
	//	public static String getContractingName="(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Contracting']/img)[1]";
//	public static String getContractingName="//span[contains(@class,'x-tree-node-text ') and text()='Contracting']";
//	public static String getContractingAutomationName="(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Automation']/img)[2]"; 
//	public static String getContractingAutomationRegressName="(//td[contains(@class,'x-grid-cell-treecolumn')]/*[text()='Regression v10.0'])";
	//sHILPA 10/11/2023: XPATH CHANGES FOR 11.2
	public static String getContractingName="//span[contains(@class,'x-tree-node-text ') and text()='Contracting']//preceding::div[2]";
	public static String getContractingAutomationName="(//td[contains(@class,'x-grid-cell-treecolumn')]//div/*[text()='Automation']//preceding::div[2])"; //updated xpath 10.11.2023
	public static String getContractingAutomationRegressName="(//td[contains(@class,'x-grid-cell-treecolumn')]//div/*[text()='Regression v10.0'])";
	@FindBy(xpath = "(//div[contains(@class,'horzOverflow')]//table[@class='x-grid-table x-grid-table-resizer'])//tr/td[3]/div")
	private static List<WebElement> getCostingModelElementList;
	public static List<WebElement> getCostingModelElementList() {return getCostingModelElementList;}
	//Shilpa 23.01.2023
	@FindBy(xpath = "//a[text()='Model Library']")
	private static WebElement getHelpModelLibraryLink;
	public static WebElement getHelpModelLibraryLink() {return getHelpModelLibraryLink;}

	@FindBy(xpath = "//span[text()='Index']")
	private static WebElement getHelpIndexLink;
	public static WebElement getHelpIndexLink() {return getHelpIndexLink;}

	@FindBy(xpath = "//input[@id='search-index']")
	private static WebElement getHelpSearchIndex;
	public static WebElement getHelpSearchIndex() {return getHelpSearchIndex;}

	@FindBy(xpath = "//li[@id='GlossaryTab']/div")
	private static WebElement getHelpGlossaryTab;
	public static WebElement getHelpGlossaryTab() {return getHelpGlossaryTab;}

	@FindBy(xpath = "//input[@id='search-glossary']")
	private static WebElement getHelpGlossarySearch;
	public static WebElement getHelpGlossarySearch() {return getHelpGlossarySearch;}

	//Shilpa
	@FindBy(xpath = "//div[contains(@class,'x-window-closable ')]//div[contains(@id,'window')]//span[text()='New Cost Model']")
	public static WebElement getNewCostModelPopUp;
	public static WebElement getNewCostModelPopUp() { return getNewCostModelPopUp;}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'glFormCls')]//following::table//following::span[text()='Save & Close']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'glFormCls')]//following::table//following::span[text()='Save & Close']")
	private static WebElement getSaveCostModel;
	public WebElement getSaveCostModel() {return getSaveCostModel;}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'glFormCls')]//following::table//following::span[text()='Cancel & Close']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'glFormCls')]//following::table//following::span[text()='Cancel & Close']")
	private static WebElement getCancelCostModel;
	public WebElement getCancelCostModel() {return getCancelCostModel;}

	@FindBy(xpath = "//td[contains(@id,'combobox')]/input")
	private static WebElement getModelTypedropdown;
	public WebElement getModelTypedropdown() {return getModelTypedropdown;}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'masterlist')]//span[text()='Edit']//parent::button")
	@FindBy(xpath = "//div[contains(@id,'masterlist')]//span[text()='Edit']")
	private static WebElement getEditButton;
	public WebElement getEditButton() {return getEditButton;}

	@FindBy(xpath = "//span[text()='Name']//following::span[@class='fas fa-angle-down']")
	private WebElement landingPageNameSortAsc;
	public WebElement getlandingPageNameSortAsc() {return landingPageNameSortAsc;}

	@FindBy(xpath = "//span[text()='Date Created']//following::span[@class='fas fa-angle-down']")
	private WebElement landingPageDateSortAsc;
	public WebElement getlandingPageDateSortAsc() {return landingPageDateSortAsc;}

	@FindBy(xpath = "//span[text()='Name']//following::span[@class='fas fa-angle-up']")
	private WebElement landingPageNameSortDesc;
	public WebElement getlandingPageNameSortDesc() {return landingPageNameSortDesc;}

	@FindBy(xpath = "//span[text()='Date Created']//following::span[@class='fas fa-angle-up']")
	private WebElement landingPageDateSortDesc;
	public WebElement getlandingPageDateSortDesc() {return landingPageDateSortDesc;}

	@FindBy(xpath = "(//div[contains(@id,'adynamicgrid')])[7]//following::tr[2]//td[3]/div")
	private static List<WebElement> landingPageGridList;
	public static List<WebElement> getlandingPageGridList() {return landingPageGridList;}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Cost Model Calculation Scenarios']//following::span[text()='New']//parent::button")
	@FindBy(xpath = "//h1[text()='Cost Model Calculation Scenarios']//following::span[text()='New']")
	private static WebElement getCostCalcNewButton;
	public static WebElement getCostCalcNewButton() {return getCostCalcNewButton;}

	@FindBy(name = "costModelScenarioName")
	private static WebElement getCostScenarioName;
	public static WebElement getCostScenarioName() {return getCostScenarioName;}

	@FindBy(name = "gLDataDescription")
	private static WebElement getGldataDesc;
	public static WebElement getGldataDesc() {return getGldataDesc;}

	@FindBy(name = "actStatCalcCode")
	private static WebElement getVolScenario;
	public static WebElement getVolScenario() {return getVolScenario;}

	@FindBy(name = "variabilityMasterId")
	private static WebElement getVariablilityMaster;
	public static WebElement getVariablilityMaster() {return getVariablilityMaster;}

	@FindBy(name = "overHeadScenarioId")
	private static WebElement getOverheadDrpDwn;
	public static WebElement getOverheadDrpDwn() {return getOverheadDrpDwn;}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//span[text()='Entities and Departments']//following::span[text()='Select']//parent::button)[1]")
//	Omkar 2/9/2024 : xpath changes for 11.2
	@FindBy(xpath = "(//div[text()='Entities and Departments']//following::span[text()='Select']//parent::span)[1]")
	private static WebElement getEntitiesSelect;
	public static WebElement getEntitiesSelect() {return getEntitiesSelect;}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//span[text()='Departments / Groups']//following::span[text()='Select']//parent::button)[1]")
//	Omkar 2/9/2024 : xpath changes for 11.2
	@FindBy(xpath = "(//div[text()='Departments / Groups']//following::span[text()='Select']//parent::span)[1]")
	private static WebElement getDeptGroupsSelect;
	public static WebElement getDeptGroupsSelect() {return getDeptGroupsSelect;}
//Shilpa 2.9.2024 xpath update
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='&23q2']/..")
	private static WebElement getCostModelGLDataScenarioOptions;

	public static WebElement getCostModelGLDataScenarioOptions() {
		return getCostModelGLDataScenarioOptions;
	}
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='0TBACTVOLCALC']/..")
	private static WebElement getCostModelVolScenarioOptions;

	public static WebElement getCostModelVolScenarioOptions() {
		return getCostModelVolScenarioOptions;
	}
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='1833CCVM']/..")
	private static WebElement getCostModelVarMasterScenarioOptions;

	public static WebElement getCostModelVarMasterScenarioOptions() {
		return getCostModelVarMasterScenarioOptions;
	}
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='DEC12OHCALC2']/..")
	private static WebElement getCostModelOHMasterScenarioOptions;

	public static WebElement getCostModelOHMasterScenarioOptions() {
		return getCostModelOHMasterScenarioOptions;
	}
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='0TB4  Test']/..")
	private static WebElement getCostModelPriceListScenarioOptions;
	public static WebElement getCostModelPriceListScenarioOptions() {
		return getCostModelPriceListScenarioOptions;
	}
	@FindBy(name = "priceList")
	private static WebElement getCostModelPriceListDrpdown;
	public static WebElement getCostModelPriceListDrpdown() {
		return getCostModelPriceListDrpdown;
	}
	@FindBy(name = "savedStartMonth")
	private static WebElement getCostModelStartMonthDrpdown;
	public static WebElement getCostModelStartMonthDrpdown() {
		return getCostModelStartMonthDrpdown;
	}
	//Shilpa update xpath for 11.2 on 2.9.2024
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='Apr 2004']/..")
	private static WebElement getCostModelStartMonthScenarioOptions;
	public static WebElement getCostModelStartMonthScenarioOptions() {
		return getCostModelStartMonthScenarioOptions;
	}
	@FindBy(name = "savedEndMonth")
	private static WebElement getCostModelEndMonthDrpdown;
	public static WebElement getCostModelEndMonthDrpdown() {
		return getCostModelEndMonthDrpdown;
	}
	//Shilpa update xpath for 11.2 on 2.9.2024

	@FindBy(xpath = "(//div[contains(@class,'boundlist')]/ul/li[text()='May 2004']/..)[2]")
	private static WebElement getCostModelEndMonthScenarioOptions;
	public static WebElement getCostModelEndMonthScenarioOptions() {
		return getCostModelEndMonthScenarioOptions;
	}
	@FindBy(xpath = "//input[contains(@id,'checkbox')]")
	private static WebElement getCostModelSharedLogCheckbox;
	public static WebElement getCostModelSharedLogCheckbox() {
		return getCostModelSharedLogCheckbox;
	}
	@FindBy(xpath = "//label[text()='Share Log in Selected Shared Location']//preceding::input[1]")
	private static WebElement getEncCostModelScenarioSharedLogCheckbox;
	public static WebElement getEncCostModelScenarioSharedLogCheckbox() {
		return getEncCostModelScenarioSharedLogCheckbox;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='New']//parent::button")
	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='New']//parent::span")
	private static WebElement getCostModelTimePeriodNewButton;
	public static WebElement getCostModelTimePeriodNewButton() {
		return getCostModelTimePeriodNewButton;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='Edit']//parent::button")
	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='Edit']//parent::span")
	private static WebElement getCostModelTimePeriodEditButton;
	public static WebElement getCostModelTimePeriodEditButton() {
		return getCostModelTimePeriodEditButton;
	}

//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='Delete']//parent::button")
	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='Delete']//parent::span")
	private static WebElement getCostModelTimePeriodDeleteButton;
	public static WebElement getCostModelTimePeriodDeleteButton() {
		return getCostModelTimePeriodDeleteButton;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Creat new Time Period']//following::span[text()='Filter']//parent::span")
	private static WebElement getCostModelTimePeriodFilterButton;
	public static WebElement getCostModelTimePeriodFilterButton() {
		return getCostModelTimePeriodFilterButton;
	}

	@FindBy(xpath = "//tr[@class='x-grid-row x-grid-row-selected x-grid-row-focused']/td[6]/div")
	private static WebElement getCostModelEndMonth;
	public static WebElement getCostModelEndMonth() {
		return getCostModelEndMonth;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Jan']/..")
	private static WebElement getCostModelTimePeriodMonthScenarioOptions;
	public static WebElement getCostModelTimePeriodMonthScenarioOptions() {
		return getCostModelTimePeriodMonthScenarioOptions;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='2013']/..")
	private static WebElement getCostModelTimePeriodYearScenarioOptions;
	public static WebElement getCostModelTimePeriodYearScenarioOptions() {
		return getCostModelTimePeriodYearScenarioOptions;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='Charge Masters']//following::span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "(//h1[text()='Charge Masters']//following::span[text()='New']//parent::span)[1]")
	private static WebElement getCostModelMethodMasterNew;
	public static WebElement getCostModelMethodMasterNew() {
		return getCostModelMethodMasterNew;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='Charge Masters']//following::span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//h1[text()='Charge Masters']//following::span[text()='Delete']//parent::button)[1]")
	private static WebElement getCostModelMethodMasterDelete;
	public static WebElement getCostModelMethodMasterDelete() {
		return getCostModelMethodMasterDelete;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='Charge Masters']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//h1[text()='Charge Masters']//following::span[text()='Filter']//parent::button)[1]")
	private static WebElement getCostModelMethodMasterFilter;
	public static WebElement getCostModelMethodMasterFilter() {
		return getCostModelMethodMasterFilter;
	}
	@FindBy(name = "deptMastCode")
	private static WebElement getCostModelMethodMasterDeptMasterdropdown;
	public static WebElement getCostModelMethodMasterDeptMasterdropdown() {
		return getCostModelMethodMasterDeptMasterdropdown;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='150 old master 150']/..")
	private static WebElement getCostModelMethodMasterDeptMasterOptions;
	public static WebElement getCostModelMethodMasterDeptMasterOptions() {
		return getCostModelMethodMasterDeptMasterOptions;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Cost Method Masters']//following::span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Cost Method Masters']//following::span[text()='New']//parent::span)[1]")
	private static WebElement getCostMethodMasterNewButton;
	public static WebElement getCostMethodMasterNewButton() {
		return getCostMethodMasterNewButton;
	}
	@FindBy(name = "cstCompObjectId")
	private static WebElement getCostMethodMasterCostComponentMaster;
	public static WebElement getCostMethodMasterCostComponentMaster() {
		return getCostMethodMasterCostComponentMaster;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='00 Test CCM']/..")
	@FindBy(xpath = "//div[contains(@id,'dynamiccombo')]/ul/li[text()='00 Test CCM']/..")	
	private static WebElement getCostMethodMasterCostComponentMasterScenarioOptions;
	public static WebElement getCostMethodMasterCostComponentMasterScenarioOptions() {
		return getCostMethodMasterCostComponentMasterScenarioOptions;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Cost Method Masters']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Cost Method Masters']//following::span[text()='Filter']//parent::span)[1]")
	private static WebElement getCostMethodMasterFilterButton;
	public static WebElement getCostMethodMasterFilterButton() {
		return getCostMethodMasterFilterButton;
	}
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Cost Method Masters']//following::span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Cost Method Masters']//following::span[text()='Delete']//parent::span)[1]")
	private static WebElement getCostMethodMasterDeleteButton;
	public static WebElement getCostMethodMasterDeleteButton() {
		return getCostMethodMasterDeleteButton;
	}
//	Omkar 8/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='New']//parent::span)[1]")
	private static WebElement getCostDeptMasterNewButton;
	public static WebElement getCostDeptMasterNewButton() {
		return getCostDeptMasterNewButton;
	}

	//		Omkar 6/4/2023 : xpath changes for ADS 11.2
	//		@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='Filter']//parent::span)")
	private static WebElement getCostDeptMasterFilterButton;
	public static WebElement getCostDeptMasterFilterButton() {
		return getCostDeptMasterFilterButton;
	}
//	Omkar 10/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='Delete']//parent::span)[1]")
	private static WebElement getCostDeptMasterDeleteButton;
	public static WebElement getCostDeptMasterDeleteButton() {
		return getCostDeptMasterDeleteButton;
	}
	@FindBy(xpath = "//div[contains(@id,'treepanelId-body')]//*[text()='Contracting']")
	private static WebElement getCostMaintenanceContracting;
	public static WebElement getCostMaintenanceContracting() {
		return getCostMaintenanceContracting;
	}
	@FindBy(xpath = "//div[contains(@id,'treepanelId-body')]//*[text()='Costing']")
	private static WebElement getCostMaintenanceCosting;
	public static WebElement getCostMaintenanceCosting() {
		return getCostMaintenanceCosting;
	}
	@FindBy(xpath = "//div[contains(@id,'treepanelId-body')]//*[text()='Episode']")
	private static WebElement getCostMaintenanceEpisode;
	public static WebElement getCostMaintenanceEpisode() {
		return getCostMaintenanceEpisode;
	}
	@FindBy(xpath = "//div[contains(@id,'treepanelId-body')]//*[text()='General']")
	private static WebElement getCostMaintenanceGeneral;
	public static WebElement getCostMaintenanceGeneral() {
		return getCostMaintenanceGeneral;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//h1[text()='Encounter Types']//following::span[text()='Edit']//parent::button)[1]")
	@FindBy(xpath = "(//h1[text()='Encounter Types']//following::span[text()='Edit']//parent::span)[1]")
	private static WebElement getEncounterTypeEditButton;
	public static WebElement getEncounterTypeEditButton() {
		return getEncounterTypeEditButton;
	}
	@FindBy(name = "columnLabel")
	private static WebElement getEncounterShortName;
	public static WebElement getEncounterShortName() {
		return getEncounterShortName;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'dynamicGrid')]//td[5]/div)[1]")
	@FindBy(xpath = "(//div[contains(@id,'dynamicGrid')]//td[3]/div)[1]")
	private static WebElement getEncounterNameinGrid;
	public static WebElement getEncounterNameinGrid() {
		return getEncounterNameinGrid;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'dynamicGrid')]//td[6]/div)[1]")
	@FindBy(xpath = "(//div[contains(@id,'dynamicGrid')]//td[4]/div)[1]")
	private static WebElement getEncounterShortNameinGrid;
	public static WebElement getEncounterShortNameinGrid() {
		return getEncounterShortNameinGrid;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='New']//parent::span)[1]")
	private static WebElement getCostDischargeStatusNewButton;
	public static WebElement getCostDischargeStatusNewButton() {
		return getCostDischargeStatusNewButton;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='Delete']//parent::span)[1]")
	private static WebElement getCostDischargeStatusDeleteButton;
	public static WebElement getCostDischargeStatusDeleteButton() {
		return getCostDischargeStatusDeleteButton;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='Filter']//parent::span)[1]")
	private static WebElement getCostDischargeStatusFilterButton;
	public static WebElement getCostDischargeStatusFilterButton() {
		return getCostDischargeStatusFilterButton;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//input[@name='singleSelectorName']//following::span[text()='Select']//parent::button)[1]")
	@FindBy(xpath = "(//input[@name='singleSelectorName']//following::span[text()='Select']//parent::span)[1]")
	private  WebElement getRvuSecSelectorSelectButton;
	public  WebElement getRvuSecSelectorSelectButton() {
		return getRvuSecSelectorSelectButton;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//input[@name='singleSelectorName']//following::span[text()='Select']//parent::button)[2]")
	@FindBy(xpath = "(//input[@name='singleSelectorName']//following::span[text()='Select']//parent::span)[2]")
	private  WebElement getRvuSecImportSelectButton;
	public  WebElement getRvuSecImportSelectButton() {
		return getRvuSecImportSelectButton;
	}
	@FindBy(name = "sharedHostLocation")
	private  WebElement getRvuSharedLocDropdown;
	public  WebElement getRvuSharedLocDropdown() {
		return getRvuSharedLocDropdown;
	}
	@FindBy(name = "logFileName")
	private  WebElement getRvuFileNameInput;
	public  WebElement getRvuFileNameInput() {
		return getRvuFileNameInput;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer x-docked-bottom')]//div[contains(@class,'x-toolbar-item')]//span[text()='Export']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer x-docked-bottom')]//div[contains(@class,'x-toolbar-item')]//span[text()='Export']//parent::span")
	private  WebElement getRvuExportButton;
	public   WebElement getRvuExportButton() {
		return getRvuExportButton;
	}
//	Omkar 11/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer x-docked-bottom')]//div[contains(@class,'x-toolbar-item')]//span[text()='Import']//parent::button")
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer x-docked-bottom')]//div[contains(@class,'x-toolbar-item')]//span[text()='Import']//parent::span")
	private  WebElement getRvuImportButton;
	public   WebElement getRvuImportButton() {
		return getRvuImportButton;
	}
	@FindBy(name = "enttityCode")
	private  WebElement getRvuCostCalcScenarioEntityDropdown;
	public   WebElement getRvuCostCalcScenarioEntityDropdown() {
		return getRvuCostCalcScenarioEntityDropdown;
	}
	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[text()='150 Marina Medical Center']/..)")
	private  WebElement getRvuCostCalcScenarioEntityOptions;
	public   WebElement getRvuCostCalcScenarioEntityOptions() {
		return getRvuCostCalcScenarioEntityOptions;
	}
	@FindBy(name = "deptName")
	private  WebElement getRvuCostCalcScenarioDeptDropdown;
	public   WebElement getRvuCostCalcScenarioDeptDropdown() {
		return getRvuCostCalcScenarioDeptDropdown;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='2110  ICU']/..")
	private  WebElement getRvuCostCalcScenarioDeptOptions;
	public   WebElement getRvuCostCalcScenarioDeptOptions() {
		return getRvuCostCalcScenarioDeptOptions;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Apr 2004 to Mar 2005']/..")
	private  WebElement getRvuCostCalcScenarioResultsOptions;
	public   WebElement getRvuCostCalcScenarioResultsOptions() {
		return getRvuCostCalcScenarioResultsOptions;
	}
	@FindBy(xpath = "(//table[contains(@class,'x-field totalCost')]//following::label[text()='Total Costs']//following::div[1])")
	private  WebElement getRvuCostCalcScenarioTotalCost;
	public   WebElement getRvuCostCalcScenarioTotalCost() {
		return getRvuCostCalcScenarioTotalCost;
	}
	@FindBy(xpath = "(//span[text()='Cost Details']//following::div[@class='x-btn x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']//span[text()='Apply Selections']//parent::button)")
	private  WebElement getRvuCostCalcScenarioApplySelection;
	public   WebElement getRvuCostCalcScenarioApplySelection() {
		return getRvuCostCalcScenarioApplySelection;
	}
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Close']")
	private  WebElement getRvuCostCalcScenarioCloseButton;
	public   WebElement getRvuCostCalcScenarioCloseButton() {
		return getRvuCostCalcScenarioCloseButton;
	}
	@FindBy(name = "hostLocation")
	private  WebElement getRvuDownloadSharedLocDropdown;
	public  WebElement getRvuDownloadSharedLocDropdown() {
		return getRvuDownloadSharedLocDropdown;
	}
	@FindBy(xpath = "//span[text()='Cost Model General Information']")
	private  WebElement getCostModelGeneralInfo;
	public   WebElement getCostModelGeneralInfo() {
		return getCostModelGeneralInfo;
	}
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Save As']")
	private   WebElement getSaveAsButton;
	public    WebElement getSaveAsButton() {
		return getSaveAsButton;
	}
	@FindBy(xpath = "//span[@class='x-window-header-text x-window-header-text-default'][text()='Save As']")
	private   WebElement getSaveAsPopup;
	public    WebElement getSaveAsPopup() {
		return getSaveAsPopup;
	}
	@FindBy(xpath = "//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Filter']")
	private   WebElement getRvuContainerFilterButton;
	public    WebElement getRvuContainerFilterButton() {
		return getRvuContainerFilterButton;
	}
	@FindBy(xpath = "//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Delete']")
	private   WebElement getRvuContainerDeleteButton;
	public    WebElement getRvuContainerDeleteButton() {
		return getRvuContainerDeleteButton;
	}
	@FindBy(xpath = "(//div[contains(@id,'rvucontainerlist')])[2]//following::tr[1]/td[6]/div")
	private  List<WebElement> getRvuContainerList;
	public    List<WebElement> getRvuContainerList() {
		return getRvuContainerList;
	}
	@FindBy(xpath = "//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Delete Filtered']")
	private   WebElement getRvuContainerDeleteFilteredButton;
	public    WebElement getRvuContainerDeleteFilteredButton() {
		return getRvuContainerDeleteFilteredButton;
	}
	@FindBy(xpath = "//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Clear Filter']")
	private   WebElement getRvuContainerClearFilterButton;
	public    WebElement getRvuContainerClearFilterButton() {
		return getRvuContainerClearFilterButton;
	}
	@FindBy(xpath = "(//span[contains(@id,'filterwindow')]//following::table[contains(@class,'customComboTriggerCls')]//following::input[@class='x-form-field x-form-checkbox'])[1]")
	private   WebElement getRvuContainerOpenCheckbox;
	public    WebElement getRvuContainerOpenCheckbox() {
		return getRvuContainerOpenCheckbox;
	}
	@FindBy(xpath = "//input[@name='valuedate']")
	private   WebElement getRvuContainerValueField;
	public    WebElement getRvuContainerValueField() {
		return getRvuContainerValueField;
	}
	@FindBy(xpath = "(//div[contains(@id,'rvucontainerlist')])[2]//following::tr[1]/td[9]/div")
	private  List<WebElement> getRvuContainerListEndMonth;
	public    List<WebElement> getRvuContainerListEndMonth() {
		return getRvuContainerListEndMonth;
	}
	@FindBy(xpath = "(//div[contains(@id,'rvucontainerlist')])[2]//following::tr[1]/td[8]/div")
	private  List<WebElement> getRvuContainerListStartMonth;
	public    List<WebElement> getRvuContainerListStartMonth() {
		return getRvuContainerListStartMonth;
	}
	@FindBy(xpath = "//span[text()='Add Value']//parent::button")
	private  WebElement getRvuContainerAddValueButton;
	public   WebElement getRvuContainerAddValueButton() {
		return getRvuContainerAddValueButton;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Entity Code']/..")
	private  WebElement getEntityDropdownOptionsInFilter;
	public   WebElement getEntityDropdownOptionsInFilter() {
		return getEntityDropdownOptionsInFilter;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Is']/..")
	private  WebElement getFilterOperatorDropdownOptions;
	public   WebElement getFilterOperatorDropdownOptions() {
		return getFilterOperatorDropdownOptions;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Equal To']/..")
	private  WebElement getFilterConditionDropdownOptions;
	public   WebElement getFilterConditionDropdownOptions() {
		return getFilterConditionDropdownOptions;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='Yes']/..")
	private  WebElement getFilterValueCostComponentOverheadDropdownOptions;
	public   WebElement getFilterValueCostComponentOverheadDropdownOptions() {
		return getFilterValueCostComponentOverheadDropdownOptions;
	}
	@FindBy(xpath = "//*[contains(@id,'costmodelscenariolist')]/descendant::span[text()='Filter']")
	private  WebElement getCostModelCalcFilterButton;
	public   WebElement getCostModelCalcFilterButton() {
		return getCostModelCalcFilterButton;
	}
	@FindBy(xpath = "//*[contains(@id,'costmodelscenariolist')]/descendant::span[text()='Clear Filter']")
	private  WebElement getCostModelCalcClearFilterButton;
	public   WebElement getCostModelCalcClearFilterButton() {
		return getCostModelCalcClearFilterButton;
	}
	@FindBy(xpath = "//div[@id='costing_modelcosts_tabId-body']/descendant::span[text()='Filter']")
	private  WebElement getCostModelFilterButton;
	public   WebElement getCostModelFilterButton() {
		return getCostModelFilterButton;
	}
	@FindBy(xpath = "(//label[text()='Cost Model Scenarios in Evaluation Order']//following::span[text()='Select']//parent::button)[1]")
	private  WebElement getCostModelEvaluationOrderSelect;
	public   WebElement getCostModelEvaluationOrderSelect() {
		return getCostModelEvaluationOrderSelect;
	}
	@FindBy(name ="secondaryCostModelScenarioOld")
	private  WebElement getCostModelScenario;
	public   WebElement getCostModelScenario() {
		return getCostModelScenario;
	}
	@FindBy(xpath = "//div[contains(@id,'boundlist')]/ul/li[text()='*USE CHC FY03 Total Cost Scenario']/..")
	private  WebElement getCostModelScenarioOptions;
	public   WebElement getCostModelScenarioOptions() {
		return getCostModelScenarioOptions;
	}
	@FindBy(name ="defaultUnitCostDate")
	private  WebElement getCostModelScenarioMonthToUse;
	public   WebElement getCostModelScenarioMonthToUse() {
		return getCostModelScenarioMonthToUse;
	}
	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[text()='May 2004']/..)[3]")
	private  WebElement getCostModelScenarioMonthToUseOptions;
	public   WebElement getCostModelScenarioMonthToUseOptions() {
		return getCostModelScenarioMonthToUseOptions;
	}
	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[text()='May 2004']/..)[1]")
	private  WebElement getCostModelScenarioFromOptions;
	public   WebElement getCostModelScenarioFromOptions() {
		return getCostModelScenarioFromOptions;
	}
	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[text()='May 2004']/..)[2]")
	private  WebElement getCostModelScenarioToOptions;
	public   WebElement getCostModelScenarioToOptions() {
		return getCostModelScenarioToOptions;
	}
	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//following::span[text()='Filter']")
	private  WebElement getEncCostModelFilterButton;
	public   WebElement getEncCostModelFilterButton() {
		return getEncCostModelFilterButton;
	}
	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//following::span[text()='Clear Filter']")
	private  WebElement getEncCostModelClearFilterButton;
	public   WebElement getEncCostModelClearFilterButton() {
		return getEncCostModelClearFilterButton;
	}
	@FindBy(xpath = "//div[contains(@id,'encountercostcalculationmaingrid')]//following::span[text()='Delete']")
	private  WebElement getEncCostModelDeleteButton;
	public   WebElement getEncCostModelDeleteButton() {
		return getEncCostModelDeleteButton;
	}
	@FindBy(xpath = "(//label[text()='Cost Model Scenarios in Evaluation Order']//following::span[text()='Select'])[1]")
	private  WebElement getEncCostModelEvaluationSelectButton;
	public   WebElement getEncCostModelEvaluationSelectButton() {
		return getEncCostModelEvaluationSelectButton;
	}
	@FindBy(xpath = "(//div[contains(@class,'x-docked-bottom x-toolbar')]//following::table//following::span[text()='Cancel & Close']//parent::button)[1]")
	private  WebElement getEncCostModelCancelCloseButton;
	public   WebElement getEncCostModelCancelCloseButton() {
		return getEncCostModelCancelCloseButton;
	}
	@FindBy(xpath = "(//div[@class='x-boundlist-list-ct'])[1]//li")
	private  List<WebElement> getEncCostModelEvaluationOrderList;
	public   List<WebElement> getEncCostModelEvaluationOrderList() {
		return getEncCostModelEvaluationOrderList;
	}
	@FindBy(xpath = "(//div[@class='x-boundlist-list-ct'])[2]//div")
	private  List<WebElement> getEncCostModelDepartmentOrderList;
	public   List<WebElement> getEncCostModelDepartmentOrderList() {
		return getEncCostModelDepartmentOrderList;
	}
	@FindBy(xpath = "(//div[@class='x-boundlist-list-ct'])[3]//div")
	private  List<WebElement> getEncCostModelEntitiesList;
	public   List<WebElement> getEncCostModelEntitiesList() {
		return getEncCostModelEntitiesList;
	}
	@FindBy(xpath = "//div[contains(@id,'rvumaintenanceform')]//input[@name='entityCode']")
	private  WebElement getRvuEntityDropdown;
	public   WebElement getRvuEntityDropdown() {
		return getRvuEntityDropdown;
	}
	@FindBy(xpath = "(//div[contains(@id,'boundlist')]/ul/li[text()='150 Marina Medical Center']/..)[2]")
	private  WebElement getRvuEntityDropdownOptions;
	public   WebElement getRvuEntityDropdownOptions() {
		return getRvuEntityDropdownOptions;
	}
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-docked-top')]//span[text()='Apply Selections']//parent::button)[2]")
	private WebElement getRvuApplySelections;

	public WebElement getRvuApplySelections() {
		return getRvuApplySelections;
	}
	@FindBy(xpath = "(//div[contains(@class,'gridClsCondensed')]//*[@data-qtip = 'Next Page'])[2]")
	private WebElement getRvuNextPageButton;

	public WebElement getRvuNextPageButton() {
		return getRvuNextPageButton;
	}
	@FindBy(xpath = "//span[contains(@id,'warningwindow')]//following::span[text()='Cancel']//parent::button")
	private WebElement getRvuCancelButton;

	public WebElement getRvuCancelButton() {
		return getRvuCancelButton;
	}
	@FindBy(xpath = "//span[contains(@id,'window')][text()='Copy to Quick RVUs']")
	private WebElement getCopyRvuWindow;

	public WebElement getCopyRvuWindow() {
		return getCopyRvuWindow;
	}
	@FindBy(xpath = "//span[contains(@id,'button') and text()='Copy and Save']")
	private WebElement getCopyandSaveRvuButton;

	public WebElement getCopyandSaveRvuButton() {
		return getCopyandSaveRvuButton;
	}
	@FindBy(xpath = "//span[contains(@id,'warningwindow')]")
	private WebElement getCopyandSaveRvuWarningPopUp;

	public WebElement getCopyandSaveRvuWarningPopUp() {
		return getCopyandSaveRvuWarningPopUp;
	}
	@FindBy(xpath = "//span[contains(@id,'window')][text()='Overwrite RVU Maintenance']")
	private WebElement getOverwriteRvuWarningPopUp;

	public WebElement getOverwriteRvuWarningPopUp() {
		return getOverwriteRvuWarningPopUp;
	}
	@FindBy(xpath = "//span[contains(@id,'messagebox')][text()='Warning']")
	private WebElement getClearRvuWarningPopUp;

	public WebElement getClearRvuWarningPopUp() {
		return getClearRvuWarningPopUp;
	}
	@FindBy(xpath = "//label[text()='Department']//following::div[text()][1]")
	private WebElement getUcqcDeptFieldNone;

	public WebElement getUcqcDeptFieldNone() {
		return getUcqcDeptFieldNone;
	}
	@FindBy(xpath = "(//div[contains(@id,'ucqcform')]//label[text()='Department']//following::span[text()='Select']//parent::button)[1]")
	private WebElement getUcqcDeptSelectButton;

	public WebElement getUcqcDeptSelectButton() {
		return getUcqcDeptSelectButton;
	}
	public  String SalariesAndWagesXpath="//*[contains(@class,'column-header-text')][text()='Salaries and Wages'][text()='Apr 2004']";
	public  String EmployeeBenifitsXpath="//*[contains(@class,'column-header-text')][text()='Employee Benefits'][text()='Apr 2004']";
	public  String MedicalSuppliesXpath="//*[contains(@class,'column-header-text')][text()='Medical Supplies'][text()='Apr 2004']";
	public  String NonMedicalSuppliesXpath="//*[contains(@class,'column-header-text')][text()='Non-Medical Supplies'][text()='Apr 2004']";
	public  String EquipRepairMaintXpath="//*[contains(@class,'column-header-text')][text()='Equip Repair & Maint'][text()='Apr 2004']";
	public  String DirectDepreciationXpath="//*[contains(@class,'column-header-text')][text()='Direct Depreciation'][text()='Apr 2004']";
	public  String PurchasedServicesXpath="//*[contains(@class,'column-header-text')][text()='Purchased Services'][text()='Apr 2004']";
	public  String ProfessionalFeesXpath="//*[contains(@class,'column-header-text')][text()='Professional Fees'][text()='Apr 2004']";
	public  String OtherExpensesXpath="//*[contains(@class,'column-header-text')][text()='Other Expenses'][text()='Apr 2004']";
	public  String DirectOverheadXpath="//*[contains(@class,'column-header-text')][text()='Direct Overhead'][text()='Apr 2004']";
	public  String HospitalOverheadXpath="//*[contains(@class,'column-header-text')][text()='Hospital Overhead'][text()='Apr 2004']";
	public  String CorporateOverheadXpath="//*[contains(@class,'column-header-text')][text()='Corporate Overhead'][text()='Apr 2004']";
	public  String DepreciationXpath="//*[contains(@class,'column-header-text')][text()='Depreciation'][text()='Apr 2004']";
	public  String TechXpath="//*[contains(@class,'column-header-text')][text()='Tech'][text()='Apr 2004']";

	@FindBy(xpath = "//div[contains(@class,'hidetoppx expand-icon')]//span[text()='Hide']//parent::button")
	private WebElement getRvuHideButton;
	public WebElement getRvuHideButton() {
		return getRvuHideButton;
	}
	@FindBy(xpath = "//div[contains(@class,'hidetoppx collapse-icon')]//span[text()='Show']//parent::button")
	private WebElement getRvuShowButton;
	public WebElement getRvuShowButton() {
		return getRvuShowButton;
	}
	@FindBy(xpath = "//div[contains(@id,'rvumaintenanceform')][contains(@class,'x-table-layout-ct')]")
	private WebElement getRvuFilterPanel;
	public WebElement getRvuFilterPanel() {
		return getRvuFilterPanel;
	}
	@FindBy(xpath = "(//div[contains(@class,'glAccountsGrid hierarchyGrid')])[2]//following::table//tr/td/div")
	private List<WebElement> getSelectColumnList;
	public List<WebElement> getSelectColumnList() {
		return getSelectColumnList;
	}
	@FindBy(xpath = "//span[contains(@id,'numbercolumn')][@class='x-column-header-text']")
	private List<WebElement> getUcqcHeaderList;
	public List<WebElement> getUcqcHeaderList() {
		return getUcqcHeaderList;
	}
	@FindBy(xpath = "//*[contains(@class,'x-btn windowbtn')]/descendant::*[text()='Cancel']")
	private WebElement getAddProviderCancelButton;

	public WebElement getAddProviderCancelButton() {
		return getAddProviderCancelButton;
	}
	//		Omkar 29/4/2023 : Changes in xpath for ADS 11.2
	//		@FindBy(xpath = "(//span[text()='Providers']//following::table[1]//tr/td[1]/div)")
	@FindBy(xpath = "//div[text()='Providers']//following::table[1]//tr/td[1]/div")
	private List<WebElement> getProviderEntityList;

	public List<WebElement> getProviderEntityList() {
		return getProviderEntityList;
	}
	//Shilpa updated xpath : 3.11.2024
	@FindBy(xpath = "//div[contains(@id,'codenomaster')]//div//table//tr//td[3]/div")
	private static List<WebElement> getEntitiesPageElementList;
	public static List<WebElement> getEntitiesPageElementList() {return getEntitiesPageElementList;}

	@FindBy(xpath = "//span[text()='I Agree']//parent::button")
	private WebElement getIgreeButton;
	public WebElement getIgreeButton() {
		return getIgreeButton;
	}
	@FindBy(xpath = "//div[@id='entities-body']")
	private WebElement getEntitiesPage;
	public WebElement getEntitiesPage() {
		return getEntitiesPage;
	}
	//		Omkar 26/4/2023 : Changes in xpath for ADS 11.2
	//		@FindBy(xpath = "(//div[contains(@id,'masterlist')]//h1[text()='Department Masters']//following::span[text()='Edit']//parent::button)[1]")
	@FindBy(xpath ="//div[contains(@id,'maintenancespli')]//h1[text()='Department Masters']//ancestor::div[@class='x-panel x-box-item x-panel-default']/following-sibling::div//span[text()='Edit']")
	private static WebElement getCostDeptMasterEditButton;
	public static WebElement getCostDeptMasterEditButton() {
		return getCostDeptMasterEditButton;
	}
	//		Omkar 26/4/2023 : Changes in xpath for ADS 11.2
	//		@FindBy(xpath = "(//div[contains(@id,'dischargeStatus')]//h1[text()='Discharge Statuses']//following::span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "//h1[text()='Department Codes']/ancestor::div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div//span[text()='Filter']")
	private static WebElement getDepartmentCodeFilterButton;
	public static WebElement getDepartmentCodeFilterButton() {
		return getDepartmentCodeFilterButton;
	}
}
