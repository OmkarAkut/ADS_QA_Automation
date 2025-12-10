package webdriver.maps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

/*
This map file includes all elements found on the Data Maintenance Subtabs.
Section 1: Maintain Data
Section 2: Load Data
Section 3: Utilities
 */

public class DataMaintenanceMap extends MapConfig {

    /******Maintain Data******/
    @FindBy(xpath = "//*[contains(text(),'Tree')]")
    private WebElement dataMaintenanceTree;
    public WebElement getDataMaintenanceTree() {return dataMaintenanceTree;}

//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Contracting')]/img[1]")
    //Update xpath for 11.2 on 1.2.2024
    @FindBy(xpath = "(//*[contains(@class,'grid-cell-inner-treecolumn')]/*[./text()='Contracting']//preceding::div[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'])[1]")
    private WebElement dataMaintenanceTreeExpanderContracting;
    public WebElement getDataMaintenanceTreeExpanderContracting() {return dataMaintenanceTreeExpanderContracting;}

//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Costing')]/img[1]")
    @FindBy(xpath = "(//*[contains(@class,'grid-cell-inner-treecolumn')]/*[./text()='Costing']//preceding::div[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'])[1]")
    private WebElement dataMaintenanceTreeExpanderCosting;
    public WebElement getDataMaintenanceTreeExpanderCosting() {return dataMaintenanceTreeExpanderCosting;}

//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Episode')]/img[1]")
    @FindBy(xpath = "(//*[contains(@class,'grid-cell-inner-treecolumn')]/*[./text()='Episode']//preceding::div[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'])[1]")
    private WebElement dataMaintenanceTreeExpanderEpisode;
    public WebElement getDataMaintenanceTreeExpanderEpisode() {return dataMaintenanceTreeExpanderEpisode;}

//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'General')]/img[1]")
    @FindBy(xpath = "(//*[contains(@class,'grid-cell-inner-treecolumn')]/*[./text()='General']//preceding::div[@class=' x-tree-elbow-img x-tree-elbow-plus x-tree-expander'])[1]")
    private WebElement dataMaintenanceTreeExpanderGeneral;
    public WebElement getDataMaintenanceTreeExpanderGeneral() {return dataMaintenanceTreeExpanderGeneral;}

    @FindBy(xpath = "//*[contains(text(),'A - Z')]")
    private WebElement dataMaintenanceAZ;
    public WebElement getDataMaintenanceAZ() {return dataMaintenanceAZ;}

    @FindBy(xpath = "//div[contains(@class,'x-box-item x-btn-default')]/em/button/span[text()='Calculate']")
    private WebElement maintainDataPageButtonCalculate;
    public WebElement getMaintainDataPageButtonCalculate() {return maintainDataPageButtonCalculate;}

    /******End Maintain Data******/

    /******Load Data******/
    @FindBy(xpath = "//*[contains(text(),'New')]")
    private WebElement dataMaintenanceLoadDataButtonNew;
    public WebElement getDataMaintenanceLoadDataButtonNew() {return dataMaintenanceLoadDataButtonNew;}

    @FindBy(xpath = "//*[contains(text(),'Continue')]")
    private WebElement dataMaintenanceLoadDataButtonContinue;
    public WebElement getDataMaintenanceLoadDataButtonContinue() {return dataMaintenanceLoadDataButtonContinue;}

    @FindBy(xpath = "//*[contains(text(),'Close')]")
    private WebElement dataMaintenanceLoadDataButtonClose;
    public WebElement getDataMaintenanceLoadDataButtonClose() {return dataMaintenanceLoadDataButtonClose;}
    /******End Load Data******/

    /******Utilities******/
    private String utilityElement = "//div[contains(@id,'dataview')]/div[text()='";
    public String getUtilityElementXpath() {return utilityElement;}
     /*


    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Encounters With No EFRs Report']")
    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Allocate HCPCS and Bundled Charges']")
    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Reset Encounter APC Charges']")
    @FindBy(xpath = "//div[contains(@id,'dataview')]/div[text()='Match Encounter ID']")*/

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With No EFRs Report']")
    private WebElement getUtilitiesPageEncountersWithNoEfrsReport;
    public WebElement getUtilitiesPageEncountersWithNoEfrsReport() {return getUtilitiesPageEncountersWithNoEfrsReport;}

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With Negative Charge Balance Report']")
    private WebElement getUtilitiesPageEncountersWithNegativeChargeBalanceReport;
    public WebElement getUtilitiesPageEncountersWithNegativeChargeBalanceReport() {return getUtilitiesPageEncountersWithNegativeChargeBalanceReport;}

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With Zero Charge Balance Report']")
    private WebElement getUtilitiesPageEncountersWithZeroChargeBalanceReport;
    public WebElement getUtilitiesPageEncountersWithZeroChargeBalanceReport() {return getUtilitiesPageEncountersWithZeroChargeBalanceReport;}
    
    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Delete Encounters With Zero Charge Balances']")
    private WebElement getUtilitiesPageDeleteEncountersWithZeroChargeBalanceReport;
    public WebElement getgetUtilitiesPageDeleteEncountersWithZeroChargeBalanceReport() {return getUtilitiesPageDeleteEncountersWithZeroChargeBalanceReport;}

    @FindBy(xpath = "//*[contains(@class,'itemWrap') and text()='Encounters With No Charges Report']")
    private WebElement getUtilitiesPageEncountersWithNoChargesReport;
    public WebElement getUtilitiesPageEncountersWithNoChargesReport() {return getUtilitiesPageEncountersWithNoChargesReport;}

    @FindBy(xpath = "//span[contains(@id,'dynamicwindow')][text()='New Terminal Server Session']")
    private WebElement dataMaintenanceNewTerminalPopUp;
    public WebElement dataMaintenanceNewTerminalPopUp() {return dataMaintenanceNewTerminalPopUp;}
    
    @FindBy(xpath = "//span[text()='Open']//parent::button")
    private WebElement dataMaintenanceOpenButton;
    public WebElement dataMaintenanceOpenButton() {return dataMaintenanceOpenButton;}
    //*[@class='itemWrap' and text()='Encounters With No Charges Report']
    /******End Utilities******/
    
//    Omkar 17/8/2023 : xpath changes for 11.2
    //Shilpa
//    @FindBy(xpath = "//label[text()='Encounter Population for Assign/Remove']//following::div[contains(@id,'displayfield')]")
    @FindBy(xpath = "//span[text()='Encounter Population for Assign/Remove']")
    private static WebElement getPopulationValue;
    public static WebElement getPopulationValue() {return getPopulationValue;}
    
    @FindBy(xpath = "//label[text()='Service Model']//following::table[4]//tr[contains(@class,'x-grid-row treeWhite')]/td/div")
    private  List<WebElement> getServiceModelList;
    public List<WebElement> getServiceModelList() {return getServiceModelList;}
    
//    Omkar 16/8/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[text()='Filter']//parent::button")
//    @FindBy(xpath = "//span[text()='Filter']//parent::span")
    //Shilpa update xpath for 11.2 on 11.23.2023
  @FindBy(xpath = "(//span[text()='Filter']//parent::span)[1]")

    private WebElement encounterButtonFilter;
    public WebElement getencounterButtonFilter() {return encounterButtonFilter;}
    
//    Omkar 16/8/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[text()='Edit']//parent::button")
    @FindBy(xpath = "//span[text()='Edit']//parent::span")
    private WebElement encounterButtonEdit;
    public WebElement getencounterButtonEdit() {return encounterButtonEdit;}
    
//    Omkar 18/8/2023 : xpath changes for 11.2
//    @FindBy(xpath = "(//button/span[text()='Services'])[1]")
    @FindBy(xpath = "(//span[text()='Services'])[1]")
	private static WebElement getServicesTabEncounter;
	public static WebElement getServicesTabEncounter() {return getServicesTabEncounter;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Assign']")
	private static WebElement getAssignButtonEncounter;
	public static WebElement getAssignButtonEncounter() {return getAssignButtonEncounter;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='New']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='New']//parent::span)[1]")
	private static WebElement getLoadDataNewButton;
	public static WebElement getLoadDataNewButton() {return getLoadDataNewButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Filter']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Filter']//parent::span)[1]")
	private static WebElement getLoadDataFilterButton;
	public static WebElement getLoadDataFilterButton() {return getLoadDataFilterButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Delete']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Delete']//parent::span)[1]")
	private static WebElement getLoadDataDeleteButton;
	public static WebElement getLoadDataDeleteButton() {return getLoadDataDeleteButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Edit']//parent::button)[1]")
	@FindBy(xpath = "(//div[contains(@id,'commontbar')]//span[text()='Edit']//parent::span)[1]")
	private static WebElement getLoadDataEditButton;
	public static WebElement getLoadDataEditButton() {return getLoadDataEditButton;}
	
	@FindBy(name = "chargeMasterCode")
	private static WebElement getChargeMaster;
	public static WebElement getChargeMaster() {return getChargeMaster;}
	
	@FindBy(name = "priceListId")
	private static WebElement getPriceListMaster;
	public static WebElement getPriceListMaster() {return getPriceListMaster;}
	
	@FindBy(name = "logLocation")
	private static WebElement getLogLoc;
	public static WebElement getLogLoc() {return getLogLoc;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save & Create New']")
	private static WebElement getSaveandCreateNewButton;
	public static WebElement getSaveandCreateNewButton() {return getSaveandCreateNewButton;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close']")
	private static WebElement getCancelCloseButton;
	public static WebElement getCancelCloseButton() {return getCancelCloseButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']//parent::button")
//	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Calculate']//parent::span")
	//Shilpa update xpath for 11.2 on 11.27.2023
	@FindBy(xpath = "(//span[text()='Calculate']/../../..)[3]")
	private static WebElement getCalculateButton;
	public static WebElement getCalculateButton() {return getCalculateButton;}
	
	//Shilpa added xpath for 11.2 on 11.27.2023
		@FindBy(xpath = "//span[text()='Save & Continue']/../../..")
		private static WebElement getSaveContinueButton;
		public static WebElement getSaveContinueButton() {return getSaveContinueButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Filter']//parent::span")
	private static WebElement getPriceItemFilterButton;
	public static WebElement getPriceItemFilterButton() {return getPriceItemFilterButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Clear Filter']//parent::button")
	@FindBy(xpath = "//h1[text()='Price Items']//following::span[text()='Clear Filter']//parent::span")
	private static WebElement getPriceItemClearFilterButton;
	public static WebElement getPriceItemClearFilterButton() {return getPriceItemClearFilterButton;}
	
	@FindBy(xpath = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr[2]/td[11]/div")
	private static WebElement getPriceItemValue;
	public static WebElement getPriceItemValue() {return getPriceItemValue;}
	
	@FindBy(xpath = "//span[text()='Department Code']//following::div[contains(@id,'dynamicGrid')]//table//tr/td[5]/div")
	private static List<WebElement> getPriceItemDeptCode;
	public static List<WebElement> getPriceItemDeptCode() {return getPriceItemDeptCode;}

	
	@FindBy(xpath = "//div[contains(@id,'dynamicGrid')]//table//tr/td[5]/div")
	private static List<WebElement> getEncounterItemList;
	public static List<WebElement> getEncounterItemList() {return getEncounterItemList;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicGrid')]//table//tr/td[5]/div(//div[contains(@id,'boundlist')]/ul/li[text()='Admission Source Code']/..)")
	private static WebElement getPopulationFieldDropdownOptions;
	public static WebElement getPopulationFieldDropdownOptions() {return getPopulationFieldDropdownOptions;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Add']//parent::button")
	@FindBy(xpath = "//span[text()='Add']//parent::span")
	private static WebElement getPopulationAddButton;
	public static WebElement getPopulationAddButton() {return getPopulationAddButton;}
	
//	Omkar 16/8/2023 : xpath changes for 11.2
//	@FindBy(xpath = "//span[text()='Show Size']//parent::button")
	@FindBy(xpath = "//span[text()='Show Size']//parent::span")
	private static WebElement getPopulationShowSizeButton;
	public static WebElement getPopulationShowSizeButton() {return getPopulationShowSizeButton;}
	
	//********09.16.2025 ************************//
	@FindBy(xpath = "//h1[text()='Bill Types']//following::span[text()='Filter']")
	private static WebElement billTypeFilterBtn;
	public static WebElement getbillTypeFilterBtn() {return billTypeFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Bill Types']//following::span[text()='Edit']")
	private static WebElement billTypeEditBtn;
	public static WebElement getbillTypeEditBtn() {return billTypeEditBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicform')][text()='Bill Type']")
	private static WebElement billTypeWindow;
	public static WebElement getbillTypeWindow() {return billTypeWindow;}
	
	@FindBy(xpath = "//div[@class='x-grid-checkheader x-grid-checkheader-checked']")
	private static List<WebElement> billTypeCheckbox;
	public static List<WebElement> getbillTypeCheckbox() {return billTypeCheckbox;}
	
	@FindBy(xpath = "//input[@name='isRebill']/../../../..")
	private static WebElement isRebill;
	public static WebElement getisRebill() {return isRebill;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicform')][text()='Bill Type']//following::span[text()='Cancel & Close']")
	private static WebElement isRebillCancelCloseBtn;
	public static WebElement getisRebillCancelCloseBtn() {return isRebillCancelCloseBtn;}
	
	@FindBy(xpath = "(//h1[text()='ASC Schemes']//following::span[text()='New'])[1]")
	private static WebElement ascSchemeNewBtn;
	public static WebElement getascSchemeNewBtn() {return ascSchemeNewBtn;}
	
	@FindBy(xpath = "(//h1[text()='ASC Schemes']//following::span[text()='Edit'])[1]")
	private static WebElement ascSchemeEditBtn;
	public static WebElement getascSchemeEditBtn() {return ascSchemeEditBtn;}
	
	@FindBy(xpath = "(//h1[text()='ASC Schemes']//following::span[text()='Filter'])[1]")
	private static WebElement ascSchemeFilterBtn;
	public static WebElement getascSchemeFilterBtn() {return ascSchemeFilterBtn;}
	
	@FindBy(xpath = "(//h1[text()='ASC Schemes']//following::span[text()='Delete'])[1]")
	private static WebElement ascSchemeDeleteBtn;
	public static WebElement getascSchemeDeleteBtn() {return ascSchemeDeleteBtn;}
	
	@FindBy(name = "name")
	private static WebElement addName;
	public static WebElement getaddName() {return addName;}
	
	@FindBy(name = "hcpcscdmastcd")
	private static WebElement hcpcsCodeMaster;
	public static WebElement gethcpcsCodeMaster() {return hcpcsCodeMaster;}
	
	@FindBy(xpath = "//span[text()='HCPCS Code Master']//following::ul")
	private static WebElement hcpcsCodeMasterDrpdwn;
	public static WebElement gethcpcsCodeMasterDrpdwn() {return hcpcsCodeMasterDrpdwn;}
	
	@FindBy(xpath = "//span[text()='HCPCS Code Master']//following::span[text()='Save & Close']")
	private static WebElement hcpcsCodeMasterSaveClose;
	public static WebElement gethcpcsCodeMasterSaveClose() {return hcpcsCodeMasterSaveClose;}
	
	@FindBy(xpath = "//span[text()='HCPCS Code Master']//following::span[text()='Cancel & Close']")
	private static WebElement ascSchemeCancelClose;
	public static WebElement getascSchemeCancelClose() {return ascSchemeCancelClose;}
	
	@FindBy(xpath = "//div[contains(@id,'warningwindow')]//following::span[text()='Delete']")
	private static WebElement warningDeleteBtn;
	public static WebElement getwarningDeleteBtn() {return warningDeleteBtn;}
	
	@FindBy(xpath = "//h1[text()='Consumers']//following::span[text()='Filter']")
	private static WebElement consumerFilterBtn;
	public static WebElement getConsumerFilterBtn() {return consumerFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Consumers']//following::span[text()='Edit']")
	private static WebElement consumerEditBtn;
	public static WebElement getconsumerEditBtn() {return consumerEditBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'consumerstabpanel')]//span[text()='Consumer History']")
	private static WebElement consumerPanel;
	public static WebElement getconsumerPanel() {return consumerPanel;}
	
	@FindBy(xpath = "(//h1[text()='Consumer History Effective Periods']//following::span[text()='Edit'])[1]")
	private static WebElement consumerHistoryEditBtn;
	public static WebElement getconsumerHistoryEditBtn() {return consumerHistoryEditBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'consumershistoryform')]//following::div[text()='Consumer History Effective Period']")
	private static WebElement consumerHistoryForm;
	public static WebElement getconsumerHistoryForm() {return consumerHistoryForm;}
	
	@FindBy(xpath = "//div[contains(@id,'consumershistoryform')]//following::span[text()='Continue']")
	private static WebElement consumerHistoryContinueBtn;
	public static WebElement getconsumerHistoryContinueBtn() {return consumerHistoryContinueBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'consumerscontainer')]//span[text()='Save & Close']")
	private static WebElement consumerPanelSaveClose;
	public static WebElement getconsumerPanelSaveClose() {return consumerPanelSaveClose;}
	
	@FindBy(xpath = "//h1[text()='Psych Combined Comorbidity Assignments']//following::span[text()='Filter']")
	private static WebElement psychFilterButton;
	public static WebElement getpsychFilterButton() {return psychFilterButton;}
	
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//following::h1[text()='Psych Combined Comorbidity Assignments']")
	private static WebElement psychFilterWindow;
	public static WebElement getpsychFilterWindow() {return psychFilterWindow;}
	
	@FindBy(xpath = "//label[text()='ICD9']//preceding::input[1]")
	private static WebElement icd9DiagnosisBtn;
	public static WebElement geticd9DiagnosisBtn() {return icd9DiagnosisBtn;}
	
	@FindBy(xpath = "//label[text()='ICD10']//preceding::input[1]")
	private static WebElement icd10DiagnosisBtn;
	public static WebElement geticd10DiagnosisBtn() {return icd10DiagnosisBtn;}
	
	@FindBy(xpath = "//span[text()='ICD9 Code First Secondary Diagnosis Codes']//following::div[contains(@class,'selectBtnCustomMulti ')]//span[text()='Select']/../../..")
	private static WebElement icd9SelectBtn;
	public static WebElement geticd9SelectBtn() {return icd9SelectBtn;}
	
	@FindBy(xpath = "//span[text()='ICD9 Code First Secondary Diagnosis Codes']//following::div[contains(@class,'x-autocontainer-outerCt')]//span[text()='View Selected Criteria']/../../..")
	private static WebElement icd9ViewCriteriaBtn;
	public static WebElement geticd9ViewCriteriaBtn() {return icd9ViewCriteriaBtn;}
	
	@FindBy(xpath = "//span[text()='ICD10 Code First Secondary Diagnosis Codes']//following::div[contains(@class,'selectBtnCustomMulti ')]//span[text()='Select']/../../..")
	private static WebElement icd10SelectBtn;
	public static WebElement geticd10SelectBtn() {return icd10SelectBtn;}
	
	@FindBy(xpath = "//span[text()='ICD10 Code First Secondary Diagnosis Codes']//following::div[contains(@class,'x-autocontainer-outerCt')]//span[text()='View Selected Criteria']/../../..")
	private static WebElement icd10ViewCriteriaBtn;
	public static WebElement geticd10ViewCriteriaBtn() {return icd10ViewCriteriaBtn;}
	
	@FindBy(xpath = "//div[text()='Psych Combined Comorbidity Assignments']//following::span[text()='Cancel & Close']")
	private static WebElement psychCancelCloseBtn;
	public static WebElement getpsychCancelCloseBtn() {return psychCancelCloseBtn;}
	
	@FindBy(xpath = "//div[text()='Psych Combined Comorbidity Assignments']//following::span[text()='Save']")
	private static WebElement psychSaveBtn;
	public static WebElement getpsychSaveBtn() {return psychSaveBtn;}
	
	@FindBy(xpath = "//div[text()='Psych Combined Comorbidity Assignments']//following::span[text()='Save & Close']")
	private static WebElement psychSaveCloseBtn;
	public static WebElement getpsychSaveCloseBtn() {return psychSaveCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'messagebox')]//following::span[text()='Cancel & Close']")
	private static WebElement psychWarningCancelCloseBtn;
	public static WebElement getpsychWarningCancelCloseBtn() {return psychWarningCancelCloseBtn;}
	
	@FindBy(xpath = "//h1[text()='Contract Batches']//following::span[text()='Edit']")
	private static WebElement contractBatchEditBtn;
	public static WebElement getcontractBatchEditBtn() {return contractBatchEditBtn;}
	
	@FindBy(xpath = "//h1[text()='Contract Batches']//following::span[text()='Filter']")
	private static WebElement contractBatchFilterBtn;
	public static WebElement getcontractBatchFilterBtn() {return contractBatchFilterBtn;}
	
	@FindBy(xpath = "(//span[text()='Contracts']//following::span[text()='Select'])[1]")
	private static WebElement contractBatchSelectBtn;
	public static WebElement getcontractBatchSelectBtn() {return contractBatchSelectBtn;}
	
	@FindBy(xpath = "(//div[text()='Add Contracts']//following::div[@class='x-grid-item-container']//following::div)[1]")
	private static WebElement contractBatchEle;
	public static WebElement getcontractBatchEle() {return contractBatchEle;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add Contracts']//following::span[text()='Select']")
	private static WebElement addContractSelectBtn;
	public static WebElement getaddContractSelectBtn() {return addContractSelectBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add Contracts']//following::span[text()='Remove']")
	private static WebElement addContractRemoveBtn;
	public static WebElement getaddContractRemoveBtn() {return addContractRemoveBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add Contracts']//following::span[text()='Apply']")
	private static WebElement addContractApplyBtn;
	public static WebElement getaddContractApplyBtn() {return addContractApplyBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'contractbatchpanel')][text()='Contract Batch']//following::span[text()='Save']")
	private static WebElement addContractSaveBtn;
	public static WebElement getaddContractSaveBtn() {return addContractSaveBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'contractbatchpanel')][text()='Contract Batch']//following::span[text()='Save & Close']")
	private static WebElement addContractSaveCloseBtn;
	public static WebElement getaddContractSaveCloseBtn() {return addContractSaveCloseBtn;}

	@FindBy(xpath = "//div[contains(@id,'contractbatchpanel')][text()='Contract Batch']//following::span[text()='Cancel & Close']")
	private static WebElement addContractCancelCloseBtn;
	public static WebElement getaddContractCancelCloseBtn() {return addContractCancelCloseBtn;}
	
	@FindBy(xpath = "//h1[text()='Contract Batches']//following::span[text()='Clear Filter']")
	private static WebElement addContractClearFilterBtn;
	public static WebElement getaddContractClearFilterBtn() {return addContractClearFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Contract Batches']//following::span[text()='Delete']")
	private static WebElement addContractDeleteBtn;
	public static WebElement getaddContractDeleteBtn() {return addContractDeleteBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'contractbatchpanel')][text()='Contract Batch']//following::span[text()='Save As']")
	private static WebElement addContractSaveAsBtn;
	public static WebElement getaddContractSaveAsBtn() {return addContractSaveAsBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Save As']")
	private static WebElement dynamicSaveAsWindow;
	public static WebElement getdynamicSaveAsWindow() {return dynamicSaveAsWindow;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//input")
	private static WebElement dynamicWindowNameInput;
	public static WebElement getdynamicWindowNameInput() {return dynamicWindowNameInput;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//span[text()='Save & Close']")
	private static WebElement dynamicWindowSaveClose;
	public static WebElement getdynamicWindowSaveClose() {return dynamicWindowSaveClose;}
	
	@FindBy(xpath = "//h1[text()='Department Hierarchies']//following::span[text()='Clear Filter']")
	private static WebElement depthierarchyClearFilterBtn;
	public static WebElement getdepthierarchyClearFilterBtn() {return depthierarchyClearFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Department Hierarchies']//following::span[text()='Filter']")
	private static WebElement depthierarchyFilterBtn;
	public static WebElement getdepthierarchyFilterBtn() {return depthierarchyFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Department Hierarchies']//following::span[text()='Edit']")
	private static WebElement depthierarchyEditBtn;
	public static WebElement getdepthierarchyEditBtn() {return depthierarchyEditBtn;}
	
	@FindBy(xpath = "//h1[text()='Department Hierarchies']//following::span[text()='New']")
	private static WebElement depthierarchyNewBtn;
	public static WebElement getdepthierarchyNewBtn() {return depthierarchyNewBtn;}
	
	@FindBy(xpath = "//h1[text()='Department Hierarchies']//following::span[text()='Delete']")
	private static WebElement depthierarchyDeleteBtn;
	public static WebElement getdepthierarchyDeleteBtn() {return depthierarchyDeleteBtn;}
	
	@FindBy(name = "masterCode")
	private static WebElement deptMasterDrpdwn;
	public static WebElement getdeptMasterDrpdwn() {return deptMasterDrpdwn;}
	
	@FindBy(name = "deptCode")
	private static WebElement deptMasterCode;
	public static WebElement getdeptMasterCode() {return deptMasterCode;}
	
	@FindBy(name = "description")
	private static WebElement deptMasterDesc;
	public static WebElement getdeptMasterDesc() {return deptMasterDesc;}
	
	@FindBy(xpath = "//span[text()='Save & Create New']")
	private static WebElement deptMasterSaveCreateNew;
	public static WebElement getdeptMasterSaveCreateNew() {return deptMasterSaveCreateNew;}
	
	@FindBy(xpath = "//div[text()='New Department Group']//following::span[text()='Cancel & Close']")
	private static WebElement deptMasterCancelClose;
	public static WebElement getdeptMasterCancelClose() {return deptMasterCancelClose;}
	
	@FindBy(xpath = "//span[text()='Department Master']//following::ul")
	private static WebElement deptMasterDrpdwnOptions;
	public static WebElement getdeptMasterDrpdwnOptions() {return deptMasterDrpdwnOptions;}
	
	@FindBy(xpath = "//div[text()='Department Hierarchy']//following::span[text()='Filter']")
	private static WebElement deptMasterFilterOption;
	public static WebElement getdeptMasterFilterOption() {return deptMasterFilterOption;}
	
	@FindBy(xpath = "//span[text()='New Group']")
	private static WebElement deptMasterNewGroup;
	public static WebElement getdeptMasterNewGroup() {return deptMasterNewGroup;}
	
	@FindBy(xpath = "//span[text()='Delete Group']")
	private static WebElement deptMasterDeleteGroup;
	public static WebElement getdeptMasterDeleteGroup() {return deptMasterDeleteGroup;}
	
	@FindBy(xpath = "//div[text()='Department Hierarchy']//following::span[text()='Save']")
	private static WebElement depthierarchySaveBtn;
	public static WebElement getdepthierarchySaveBtn() {return depthierarchySaveBtn;}
	
	@FindBy(xpath = "//div[text()='Department Hierarchy']//following::span[text()='Save & Close']")
	private static WebElement depthierarchySaveCloseBtn;
	public static WebElement getdepthierarchySaveCloseBtn() {return depthierarchySaveCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'accounthierarchyform')][text()='Department Hierarchy']")
	private static WebElement depthierarchyForm;
	public static WebElement getdepthierarchyForm() {return depthierarchyForm;}
	
	@FindBy(xpath = "(//label[contains(text(),'Hierarchy ')]//following::span[@class='x-tree-node-text '])[2]")
	private static WebElement deptNode;
	public static WebElement getdeptNode() {return deptNode;}
	
	@FindBy(xpath = "//a[contains(@class,'btnContainerMarginCls ')]//following::span[text()='Find']")
	private static WebElement deptHierarchyFindBtn;
	public static WebElement getdeptHierarchyFindBtn() {return deptHierarchyFindBtn;}
	
	@FindBy(xpath = "//a[contains(@class,'btnContainerMarginCls ')]//following::span[text()='Move To']")
	private static WebElement deptHierarchyMoveToBtn;
	public static WebElement getdeptHierarchyMoveToBtn() {return deptHierarchyMoveToBtn;}
	
	@FindBy(xpath = "//div[text()='Find  Department Code or Department Group']")
	private static WebElement deptHierarchyFindWindow;
	public static WebElement getdeptHierarchyFindWindow() {return deptHierarchyFindWindow;}
	
	@FindBy(xpath = "//div[text()='Find  Department Code or Department Group']//following::div[text()='120 EMERGENCY DEPARTMENT']")
	private static WebElement deptHierarchyFindDept;
	public static WebElement getdeptHierarchyFindDept() {return deptHierarchyFindDept;}
	
	@FindBy(xpath = "//div[text()='Find  Department Code or Department Group']//following::span[text()='Apply']")
	private static WebElement deptHierarchyFindWinddApplyBtn;
	public static WebElement getdeptHierarchyFindWinddApplyBtn() {return deptHierarchyFindWinddApplyBtn;}
	
	@FindBy(xpath = "//div[text()='Move To']")
	private static WebElement deptHierarchyMovetoWindow;
	public static WebElement getdeptHierarchyMovetoWindow() {return deptHierarchyMovetoWindow;}
	
	@FindBy(name = "groupNames")
	private static WebElement deptHierarchyGroupNameDrpDwn;
	public static WebElement getdeptHierarchyGroupNameDrpDwn() {return deptHierarchyGroupNameDrpDwn;}
	
	@FindBy(xpath = "//div[text()='Move To']//following::span[text()='Move']")
	private static WebElement deptMoveBtn;
	public static WebElement getdeptMoveBtn() {return deptMoveBtn;}
	
	@FindBy(xpath = "//span[text()='Select ICD10 Dx']")
	private static WebElement selectICD10Btn;
	public static WebElement getselectICD10Btn() {return selectICD10Btn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Diagnosis Group Members']")
	private static WebElement selectICD10Window;
	public static WebElement getselectICD10Window() {return selectICD10Window;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Diagnosis Group Members']//following::span[text()='Select']")
	private static WebElement selectICD10WindowSelectBtn;
	public static WebElement getselectICD10WindowSelectBtn() {return selectICD10WindowSelectBtn;}
	
	@FindBy(xpath = "(//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Diagnosis Group Members']//following::span[text()='All'])[2]")
	private static WebElement selectICD10WindowSelectAllBtn;
	public static WebElement getselectICD10WindowSelectAllBtn() {return selectICD10WindowSelectAllBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Diagnosis Group Members']//following::span[text()='Apply']")
	private static WebElement selectICD10WindowApplyBtn;
	public static WebElement getselectICD10WindowApplyBtn() {return selectICD10WindowApplyBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Diagnosis Group Members']//following::span[text()='Remove']")
	private static WebElement selectICD10WindowRemoveBtn;
	public static WebElement getselectICD10WindowRemoveBtn() {return selectICD10WindowRemoveBtn;}
	
	@FindBy(xpath = "((//div[contains(@class,'x-grid-view nowrapRow')])[1]//div[@class='x-grid-item-container']//div)[1]")
	private static WebElement selectICDgroupFirstItem;
	public static WebElement getselectICDgroupFirstItem() {return selectICDgroupFirstItem;}
	
	@FindBy(xpath = "//div[text()='ICD Diagnosis Group']//following::span[text()='Save']")
	private static WebElement selectICDgroupSaveBtn;
	public static WebElement getselectICDgroupSaveBtn() {return selectICDgroupSaveBtn;}
	
	@FindBy(xpath = "//div[text()='ICD Diagnosis Group']//following::span[text()='Save & Close']")
	private static WebElement selectICDgroupSaveCloseBtn;
	public static WebElement getselectICDgroupSaveCloseBtn() {return selectICDgroupSaveCloseBtn;}
	
	@FindBy(xpath = "//h1[text()='ICD Diagnosis Groups']//following::span[text()='Filter']")
	private static WebElement selectICDgroupFilterBtn;
	public static WebElement getselectICDgroupFilterBtn() {return selectICDgroupFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='ICD Diagnosis Groups']//following::span[text()='Edit']")
	private static WebElement selectICDgroupEditBtn;
	public static WebElement getselectICDgroupEditBtn() {return selectICDgroupEditBtn;}
	
	@FindBy(xpath = "(//div[text()='ICD Diagnosis Group']//following::div[@class='x-grid-item-container']//table//td/div)[3]")
	private static WebElement diagnosisCode;
	public static WebElement getdiagnosisCode() {return diagnosisCode;}
	
	@FindBy(xpath = "//h1[text()='Age In Days Groups']//following::span[text()='New']")
	private static WebElement ageGroupNewBtn;
	public static WebElement getageGroupNewBtn() {return ageGroupNewBtn;}
	
	@FindBy(xpath = "//h1[text()='Age In Days Groups']//following::span[text()='Filter']")
	private static WebElement ageGroupFilterBtn;
	public static WebElement getageGroupFilterBtn() {return ageGroupFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Age In Days Groups']//following::span[text()='Clear Filter']")
	private static WebElement ageGroupClearFilterBtn;
	public static WebElement getageGroupClearFilterBtn() {return ageGroupClearFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Age In Days Groups']//following::span[text()='Delete']")
	private static WebElement ageGroupDeleteBtn;
	public static WebElement getageGroupDeleteBtn() {return ageGroupDeleteBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'groupsdetailpanel')]//div[text()='Age In Days Groups']//following::span[text()='Select']")
	private static WebElement ageGroupSelectBtn;
	public static WebElement getageGroupSelectBtn() {return ageGroupSelectBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add Age In Days Group Members']")
	private static WebElement ageGroupSelectWindow;
	public static WebElement getageGroupSelectWindow() {return ageGroupSelectWindow;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add Age In Years Group Members']")
	private static WebElement ageYearsGroupSelectWindow;
	public static WebElement getageYearsGroupSelectWindow() {return ageYearsGroupSelectWindow;}
	
	@FindBy(xpath = "//a[contains(@class,'x-box-item x-toolbar-item')]//following::span[text()='Save']")
	private static WebElement saveButton;
	public static WebElement getsaveButton() {return saveButton;}
	
	@FindBy(xpath = "//a[contains(@class,'x-box-item x-toolbar-item')]//following::span[text()='Save & Close']")
	private static WebElement saveCloseButton;
	public static WebElement getsaveCloseButton() {return saveCloseButton;}
	
	@FindBy(xpath = "//div[contains(@class,'x-closable')]//div[contains(@id,'dynamicwindow')]//span[text()='Apply']")
	private static WebElement applyBtnInPopUp;
	public static WebElement getapplyBtnInPopUp() {return applyBtnInPopUp;}
	
	@FindBy(xpath = "(//div[text()='Encounter Service Classification Scheme']//following::span[text()='Save'])[1]")
	private static WebElement encounterSaveBtn;
	public static WebElement getencounterSaveBtn() {return encounterSaveBtn;}
	
	@FindBy(xpath = "(//div[text()='Encounter Service Classification Scheme']//following::span[text()='Save & Close'])[1]")
	private static WebElement encounterSaveCloseBtn;
	public static WebElement getencounterSaveCloseBtn() {return encounterSaveCloseBtn;}
	
	@FindBy(xpath = "(//span[text()='Clear Filter']//parent::span)[1]")
    private static WebElement encounterButtonClearFilter;
    public static WebElement getencounterButtonClearFilter() {return encounterButtonClearFilter;}
    
    @FindBy(xpath = "(//span[text()='New']//parent::span)[1]")
    private static WebElement encounterButtonNewBtn;
    public static WebElement getencounterButtonNewBtn() {return encounterButtonNewBtn;}
    
    @FindBy(xpath = "//span[text()='Encounter Population for Assign/Remove']//following::span[text()='Select']")
    private static WebElement encounterPopSelectBtn;
    public static WebElement getencounterPopSelectBtn() {return encounterPopSelectBtn;}
    
    @FindBy(xpath = "(//div[contains(@id,'dynamicwindow')][text()='Add Population']//following::table//div)[2]")
    private static WebElement encounterAddPopl;
    public static WebElement getencounterAddPopl() {return encounterAddPopl;}
    
    @FindBy(xpath = "(//span[text()='Delete']//parent::span)[1]")
    private static WebElement encounterButtonDeleteBtn;
    public static WebElement getencounterButtonDeleteBtn() {return encounterButtonDeleteBtn;}
    
    @FindBy(xpath = "//div[contains(@id,'messagebox')]//following::span[text()='Delete']")
    private static WebElement messageboxDeleteBtn;
    public static WebElement getmessageboxDeleteBtn() {return messageboxDeleteBtn;}
    
    @FindBy(xpath = "//div[contains(@id,'messagebox')]//following::span[text()='Ok']")
    private static WebElement okDeleteBtn;
    public static WebElement getokDeleteBtn() {return okDeleteBtn;}
    
    @FindBy(xpath = "//span[text()='Totals']/../../..")
    private static WebElement totalsTabs;
    public static WebElement gettotalsTabs() {return totalsTabs;}
    
    @FindBy(xpath = "(//span[text()='Total Actual Charges']//following::div/div)[1]")
    private static WebElement totalActualCharges;
    public static WebElement gettotalActualCharges() {return totalActualCharges;}
    
    @FindBy(xpath = "(//span[text()='Total Actual Cash Payments']//following::div/div)[1]")
    private static WebElement totalActualCashPay;
    public static WebElement gettotalActualCashPay() {return totalActualCashPay;}
    
    @FindBy(xpath = "(//span[text()='Total Actual Adjustments']//following::div/div)[1]")
    private static WebElement totalActualAdjust;
    public static WebElement gettotalActualAdjust() {return totalActualAdjust;}
    
    @FindBy(xpath = "(//span[text()='Balance Amount']//following::div/div)[1]")
    private static WebElement balanceAmount;
    public static WebElement getbalanceAmount() {return balanceAmount;}
    
    @FindBy(xpath = "(//span[text()='Balance Status']//following::div/div)[1]")
    private static WebElement balanceStatus;
    public static WebElement getbalanceStatus() {return balanceStatus;}
    
    @FindBy(xpath = "(//span[text()='Total Non-Covered Charges']//following::div/div)[1]")
    private static WebElement totalnonCoveredCharges;
    public static WebElement gettotalnonCoveredCharges() {return totalnonCoveredCharges;}
    
    @FindBy(xpath = "(//span[text()='Expected Payment']//following::div/div)[1]")
    private static WebElement expPayment;
    public static WebElement getexpPayment() {return expPayment;}
    
    @FindBy(xpath = "(//span[text()='Direct Fixed Cost']//following::div/div)[1]")
    private static WebElement directFixedCost;
    public static WebElement getdirectFixedCost() {return directFixedCost;}
    
    @FindBy(xpath = "(//span[text()='Direct Variable Cost']//following::div/div)[1]")
    private static WebElement directVarialeCost;
    public static WebElement getdirectVarialeCost() {return directVarialeCost;}
    
    @FindBy(xpath = "(//span[text()='Indirect Fixed Cost']//following::div/div)[1]")
    private static WebElement indirectFixedCost;
    public static WebElement getindirectFixedCost() {return indirectFixedCost;}
    
    @FindBy(xpath = "(//span[text()='Indirect Variable Cost']//following::div/div)[1]")
    private static WebElement indirectVariableCost;
    public static WebElement getindirectVariableCost() {return indirectVariableCost;}
    
    @FindBy(xpath = "(//span[text()='Total Costs']//following::div/div)[1]")
    private static WebElement totalCosts;
    public static WebElement gettotalCosts() {return totalCosts;}
    
    @FindBy(xpath = "//span[text()='Procedures']/../../..")
    private static WebElement proceduresTab;
    public static WebElement getproceduresTab() {return proceduresTab;}
    
    @FindBy(xpath = "(((//div[contains(@id,'dynamicGrid')]//div[@class='x-grid-item-container']))[4]//table[1]//td/div)[9]")
    private static WebElement udf;
    public static WebElement getudf() {return udf;}
    
    @FindBy(xpath = "(((//div[contains(@id,'dynamicGrid')]//div[@class='x-grid-item-container']))[4]//table[1]//td/div)[5]")
    private static WebElement icdProcedure;
    public static WebElement geticdProcedure() {return icdProcedure;}
    
    @FindBy(xpath = "(((//div[contains(@id,'dynamicGrid')]//div[@class='x-grid-item-container']))[4]//table[1]//td/div)[7]")
    private static WebElement hcpcsProcedure;
    public static WebElement gethcpcsProcedure() {return hcpcsProcedure;}
    
    @FindBy(xpath = "//div[contains(@id,'encounterProcedures')]//following::span[text()='Edit']")
    private static WebElement procEditBtn;
    public static WebElement getprocEditBtn() {return procEditBtn;}
    
    @FindBy(xpath = "//div[text()='Procedure']")
    private static WebElement procEditWindow;
    public static WebElement getprocEditWindow() {return procEditWindow;}
    
    @FindBy(xpath = "(//div[contains(@id,'proceduresformpanel')]//following::span[text()='ICD']//following::div[@data-ref='inputEl'])[1]")
    private static WebElement icdProcedureInput;
    public static WebElement geticdProcedureInput() {return icdProcedureInput;}
    
    @FindBy(xpath = "(//div[contains(@id,'proceduresformpanel')]//following::span[text()='ICD']//following::div[@data-ref='inputEl'])[2]")
    private static WebElement hcpcsProcedureInput;
    public static WebElement gethcpcsProcedureInput() {return hcpcsProcedureInput;}
    
    @FindBy(xpath = "(//div[contains(@id,'proceduresformpanel')]//following::span[text()='ICD']//following::div[@data-ref='inputEl'])[3]")
    private static WebElement udfInput;
    public static WebElement getudfInput() {return udfInput;}
    
    @FindBy(xpath = "//a[contains(@class,'cancelCloseBtn ')]")
    private static WebElement cancelCloseBtn;
    public static WebElement getcancelCloseBtn() {return cancelCloseBtn;}
    
    @FindBy(xpath = "//span[text()='Diagnoses']/../../..")
    private static WebElement diagnosisTab;
    public static WebElement getdiagnosisTab() {return diagnosisTab;}
    
    @FindBy(xpath = "(//span[text()='ICD10 Principal Diagnosis']//following::span[text()='Select'])[1]")
    private static WebElement icd10ProcDiagnosisSelectBtn;
    public static WebElement geticd10ProcDiagnosisSelectBtn() {return icd10ProcDiagnosisSelectBtn;}
    
    @FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Principal Diagnosis']")
    private static WebElement icd10ProcDiagnosisWindow;
    public static WebElement geticd10ProcDiagnosisWindow() {return icd10ProcDiagnosisWindow;}
    
    @FindBy(xpath = "(//span[text()='ICD10 Admitting Diagnosis']//following::span[text()='Select'])[1]")
    private static WebElement icd10ProcAdmitDiagnosisSelectBtn;
    public static WebElement geticd10ProcAdmitDiagnosisSelectBtn() {return icd10ProcAdmitDiagnosisSelectBtn;}
    
    @FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add ICD10 Admitting Diagnosis']")
    private static WebElement icd10ProcAdmitDiagnosisWindow;
    public static WebElement geticd10ProcAdmitDiagnosisWindow() {return icd10ProcAdmitDiagnosisWindow;}
    
    @FindBy(xpath = "//span[text()='APCs & APGs']/../../..")
    private static WebElement apcapgTab;
    public static WebElement getapcapgTab() {return apcapgTab;}
    
    @FindBy(xpath = "((//span[text()='EAPG Scheme']//following::div[contains(@id,'dynamicGrid')]//following::table)[1]//div)[2]")
    private static WebElement eapgScheme;
    public static WebElement geteapgScheme() {return eapgScheme;}
    
    @FindBy(xpath = "((//span[text()='EAPG Scheme']//following::div[contains(@id,'dynamicGrid')]//following::table)[1]//div)[3]")
    private static WebElement eapg;
    public static WebElement geteapg() {return eapg;}
    
    @FindBy(xpath = "//div[text()='Enhanced Ambulatory Patient Groups (EAPGs)']//following::span[text()='Edit']")
    private static WebElement eapgEditBtn;
    public static WebElement geteapgEditBtn() {return eapgEditBtn;}
    
    @FindBy(xpath = "//div[text()='EAPG']")
    private static WebElement eapgWindow;
    public static WebElement geteapgWindow() {return eapgWindow;}
    
    @FindBy(name = "eapgClassSchemeCode")
    private static WebElement eapgSchemeField;
    public static WebElement geteapgSchemeField() {return eapgSchemeField;}
    
    @FindBy(xpath = "(//span[contains(@id,'singleselectorform')][text()='EAPG']//following::div[contains(@id,'displayfield')])[1]//div/div")
    private static WebElement eapgSchemeText;
    public static WebElement geteapgSchemeText() {return eapgSchemeText;}
    
    @FindBy(xpath = "//span[text()='Charges']/../../..")
    private static WebElement chargesTab;
    public static WebElement getchargesTab() {return chargesTab;}
    
    @FindBy(xpath = "(//div[contains(@id,'encounterActualCharges')]//following::span[text()='Edit'])[1]")
    private static WebElement chargesTabEditBtn;
    public static WebElement getchargesTabEditBtn() {return chargesTabEditBtn;}
    
    @FindBy(xpath = "//div[text()='Actual Charge']//following::div[text()='Practitioners']//following::div[1]")
    private static WebElement practitionersAccordian;
    public static WebElement getpractitionersAccordian() {return practitionersAccordian;}
    
    @FindBy(xpath = "//div[text()='Actual Charge']//following::div[text()='Mappings']//following::div[1]")
    private static WebElement mappingsAccordian;
    public static WebElement getmappingsAccordian() {return mappingsAccordian;}
    
    @FindBy(xpath = "//div[text()='Actual Charge']//following::div[text()='Diagnosis']//following::div[1]")
    private static WebElement diagnosisAccordian;
    public static WebElement getdiagnosisAccordian() {return diagnosisAccordian;}
    
    @FindBy(xpath = "//div[text()='Actual Charge']//following::div[text()='RBRVS RVUs']//following::div[1]")
    private static WebElement rbrvsAccordian;
    public static WebElement getrbrvsAccordian() {return rbrvsAccordian;}
    
    @FindBy(xpath = "//div[text()='Actual Charge']//following::div[text()='Charge UDFs & UDRs']//following::div[1]")
    private static WebElement chargeUDfAccordian;
    public static WebElement getchargeUDfAccordianAccordian() {return chargeUDfAccordian;}
    
    @FindBy(xpath = "//div[text()='Actual Charge']//following::div[text()='General']//following::div[1]")
    private static WebElement generalAccordian;
    public static WebElement getgeneralAccordianAccordian() {return generalAccordian;}
    
    @FindBy(xpath = "(//div[contains(@id,'encounterActualCharges')]//following::span[text()='New'])[1]")
    private static WebElement chargesTabNewBtn;
    public static WebElement getchargesTabNewBtn() {return chargesTabNewBtn;}
    
    @FindBy(xpath = "(//div[text()='General']//following::span[text()='Department']//following::span[text()='Select'])[1]")
    private static WebElement newChargeDeptSelectBtn;
    public static WebElement getnewChargeDeptSelectBtn() {return newChargeDeptSelectBtn;}
    
    @FindBy(xpath = "(//div[text()='General']//following::span[text()='Charge Code']//following::span[text()='Select'])[1]")
    private static WebElement newChargeCodeSelectBtn;
    public static WebElement getnewChargeCodeSelectBtn() {return newChargeCodeSelectBtn;}
    
    @FindBy(xpath = "//span[text()='Financial Records']/../../..")
    private static WebElement financialRecordsTab;
    public static WebElement getfinancialRecordsTab() {return financialRecordsTab;}
    
    @FindBy(xpath = "(//div[contains(@class,'extNativeToolbar ')])[2]//span[text()='Edit']")
    private static WebElement financialRecordsEditBtn;
    public static WebElement getfinancialRecordsEditBtn() {return financialRecordsEditBtn;}
    
    @FindBy(xpath = "//input[@name='efrCategoryCode']")
    private static WebElement financialRecordsEFR;
    public static WebElement getfinancialRecordsEFR() {return financialRecordsEFR;}
    
    @FindBy(xpath = "//span[text()='Current Allowance Benefit Plan']//following::span[text()='Select']")
    private static WebElement contractualSelectBtn;
    public static WebElement getcontractualSelectBtn() {return contractualSelectBtn;}
    
    @FindBy(xpath = "(//input[@name='carrierfield']//following::span[text()='Cancel & Close'])[2]")
    private static WebElement dialogFormCancelCloseBtn;
    public static WebElement getdialogFormCancelCloseBtn() {return dialogFormCancelCloseBtn;}
    
    @FindBy(name = "billTypeCode")
    private static WebElement billTypeComboBox;
    public static WebElement getbillTypeComboBox() {return billTypeComboBox;}
    
    @FindBy(xpath = "//span[text()='Payments']/../../..")
    private static WebElement patmentsTab;
    public static WebElement getpatmentsTab() {return patmentsTab;}
    
    @FindBy(xpath = "//div[contains(@id,'encounterPayments')]//following::span[text()='New']")
    private static WebElement paymentsNewBtn;
    public static WebElement getpaymentsNewBtn() {return paymentsNewBtn;}
    
    @FindBy(xpath = "//div[contains(@id,'encounterPayments')]//following::span[text()='Delete']")
    private static WebElement paymentsDeleteBtn;
    public static WebElement getpaymentsDeleteBtn() {return paymentsDeleteBtn;}
    
    @FindBy(name = "payorEntityCode")
    private static WebElement payorEntityCode;
    public static WebElement getpayorEntityCode() {return payorEntityCode;}
    
    @FindBy(name = "paymentAmount")
    private static WebElement paymentAmount;
    public static WebElement getpaymentAmount() {return paymentAmount;}
    
    @FindBy(name = "payeeEntityCode")
    private static WebElement payeeEntityCode;
    public static WebElement getpayeeEntityCode() {return payeeEntityCode;}
    
    @FindBy(name = "transTypeCode")
    private static WebElement transTypeCode;
    public static WebElement gettransTypeCode() {return transTypeCode;}
    
    @FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//following::span[text()='Continue']")
    private static WebElement dynamicWinContinueBtn;
    public static WebElement getdynamicWinContinueBtn() {return dynamicWinContinueBtn;}
    
    @FindBy(xpath = "//div[contains(@id, 'apcsapgspanel')]//div[text()='Enhanced Ambulatory Patient Groups (EAPGs)']")
    private static WebElement eapgSection;
    public static WebElement geteapgSection() {return eapgSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'chargesactualpanel')]//following::div[text()='General']")
    private static WebElement generalSection;
    public static WebElement getgeneralSection() {return generalSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'chargesactualpanel')]//following::div[text()='Practitioners']")
    private static WebElement practSection;
    public static WebElement getpractSection() {return practSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'chargesactualpanel')]//following::div[text()='Mappings']")
    private static WebElement mappingsSection;
    public static WebElement getmappingsSection() {return mappingsSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'chargesactualpanel')]//following::div[text()='Diagnoses']")
    private static WebElement diagnosesSection;
    public static WebElement getdiagnosesSection() {return diagnosesSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'chargesactualpanel')]//following::div[text()='RBRVS RVUs']")
    private static WebElement rbrvsSection;
    public static WebElement getrbrvsSection() {return rbrvsSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'financialrecordsformpanel')]//following::div[text()='Contractual Allowance']")
    private static WebElement contractAllowanceSection;
    public static WebElement getcontractAllowanceSection() {return contractAllowanceSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'financialrecordsformpanel')]//following::div[text()='Billing']")
    private static WebElement billingSection;
    public static WebElement getbillingSectionSection() {return billingSection;}
    
    @FindBy(xpath = "//*[contains(@id, 'chargesactualpanel')]//following::div[text()='Charge UDFs & UDRs']")
    private static WebElement chargeudfSection;
    public static WebElement getchargeudfSection() {return chargeudfSection;}
    
    @FindBy(xpath = "(//div[text()='Encounter']//following::div[contains(@class,'extNativeToolbar ')]//following::span[text()='Cancel & Close'])[1]")
    private static WebElement encounterCancelCloseBtn;
    public static WebElement getencounterCancelCloseBtn() {return encounterCancelCloseBtn;}
    
    @FindBy(xpath = "//div[text()='New Actual Charge']//following::div[contains(@id,'toolbar')]//span[text()='Continue']/../../..")
    private static WebElement actualChargeContinue;
    public static WebElement getactualChargeContinue() {return actualChargeContinue;}
    
    @FindBy(xpath = "//div[contains(@id,'paymentformpanel')]//following::ul[contains(@id,'dynamiccombo')]")
    private static WebElement payorList;
    public static WebElement getpayorList() {return payorList;}
    
    @FindBy(xpath = "(//div[contains(@id,'paymentformpanel')]//following::ul[contains(@id,'dynamiccombo')])[2]")
    private static WebElement payeeList;
    public static WebElement getpayeeList() {return payeeList;}
    
    @FindBy(xpath = "(//div[contains(@id,'paymentformpanel')]//following::ul[contains(@id,'dynamiccombo')])[3]")
    private static WebElement transTypeList;
    public static WebElement gettransTypeList() {return transTypeList;}
    
    @FindBy(xpath = "//span[text()='Edit']")
    private static WebElement azPageEditBtn;
    public static WebElement getazPageEditBtn() {return azPageEditBtn;}
    
    @FindBy(xpath = "//input[@name='code']")
    private static WebElement inputCode;
    public static WebElement getinputCode() {return inputCode;}
    
    @FindBy(xpath = "(//div[contains(@id,'industryclasschemepanel')]//following::div[text()='Groups'])[2]")
    private static WebElement groupsPanel;
    public static WebElement getgroupsPanel() {return groupsPanel;}
    
    @FindBy(xpath = "((//div[@class='x-grid-item-container'])[3]//table)[1]")
    private static WebElement groupsRow1;
    public static WebElement getgroupsRow1() {return groupsRow1;}
    
    @FindBy(xpath = "(//div[text()='Groups']//following::span[text()='Edit'])[2]")
	private static WebElement getGroupsEditBtn;
	public static WebElement getGroupsEditBtn() {return getGroupsEditBtn;}
	
	@FindBy(xpath = "//input[@name='minCopayStatus']/../../../..")
	private static WebElement miniCopyStatusCheck;
	public static WebElement getminiCopyStatusCheck() {return miniCopyStatusCheck;}
	
	@FindBy(xpath = "//div[text()='Group']//following::span[text()='Cancel & Close']")
	private static WebElement groupCancelClose;
	public static WebElement getgroupCancelClose() {return groupCancelClose;}
	
	@FindBy(xpath = "((//div[@class='x-grid-item-container'])[3]//table)[1]//div/div")
	private static WebElement groupGridMiniCopayCheck;
	public static WebElement getgroupGridMiniCopayCheck() {return groupGridMiniCopayCheck;}
    
	@FindBy(xpath = "//h1[text()='Cost Components']//following::span[text()='New']")
	private static WebElement costComponentNewBtn;
	public static WebElement getcostComponentNewBtn() {return costComponentNewBtn;}
	
	@FindBy(xpath = "//h1[text()='Cost Components']//following::span[text()='Delete']")
	private static WebElement costComponentDeleteBtn;
	public static WebElement getcostComponentDeleteBtn() {return costComponentDeleteBtn;}
	
	@FindBy(xpath = "//div[text()='Warning']//following::span[text()='Continue']")
	private static WebElement costComponentContinueBtn;
	public static WebElement getcostComponentContinueBtn() {return costComponentContinueBtn;}
	
	@FindBy(name = "typeExpensesource")
	private static WebElement componentTypeBtn;
	public static WebElement getcomponentTypeBtn() {return componentTypeBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'costcomponentpanel')]//following::ul")
	private static WebElement componentTypeOptions;
	public static WebElement getcomponentTypeOptions() {return componentTypeOptions;}
	
	@FindBy(name = "rvuOrderIndex")
	private static WebElement orderRVUIndex;
	public static WebElement getorderRVUIndex() {return orderRVUIndex;}
	
	@FindBy(xpath = "//div[contains(@id,'costcomponentpanel')]//following::span[text()='Save']")
	private static WebElement componentSave;
	public static WebElement getcomponentSave() {return componentSave;}
	
	@FindBy(xpath = "//div[contains(@id,'costcomponentpanel')]//following::span[text()='Save & Close']")
	private static WebElement componentSaveClose;
	public static WebElement getcomponentSaveClose() {return componentSaveClose;
	}
	@FindBy(xpath = "//div[text()='Cost Component Master']//following::span[text()='Save & Close']")
	private static WebElement componentMasterSaveClose;
	public static WebElement getcomponentMasterSaveClose() {return componentMasterSaveClose;}
	
	@FindBy(xpath = "//h1[text()='Cost Component Masters']//following::span[text()='Filter']")
	private static WebElement CostComponentFilterButton;
	public static WebElement getCostComponentFilterButton() {return CostComponentFilterButton;}
	
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//following::div[text()='Filter Cost Component Masters']")
	private static WebElement costComponentFilterWindow;
	public static WebElement getcostComponentFilterWindow() {return costComponentFilterWindow;}
	
	@FindBy(xpath = "//div[text()='Cost Components']//following::input[@name='name']")
	private static WebElement costCompInputName;
	public static WebElement getcostCompInputName() {return costCompInputName;}
	
	@FindBy(xpath = "//h1[text()='Cost Components']//following::span[text()='Edit']")
	private static WebElement costCompEditBtn;
	public static WebElement getcostCompEditBtn() {return costCompEditBtn;}
	
	@FindBy(xpath = "//h1[text()='Cost Component Assignments']//following::span[text()='New']")
	private static WebElement costCompAssignNewBtn;
	public static WebElement getcostCompAssignNewBtn() {return costCompAssignNewBtn;}
	
	@FindBy(xpath = "//h1[text()='Cost Component Assignments']//following::span[text()='Delete']")
	private static WebElement costCompAssignDeleteBtn;
	public static WebElement getcostCompAssignDeleteBtn() {return costCompAssignDeleteBtn;}
	
	@FindBy(name = "entityCode")
	private static WebElement costCompAssignEntityBtn;
	public static WebElement getcostCompAssignEntityBtn() {return costCompAssignEntityBtn;}
	
	@FindBy(xpath = "//h1[text()='Cost Component Assignments']//following::ul")
	private static WebElement costCompAssignEntityOptions;
	public static WebElement getcostCompAssignEntityOptions() {return costCompAssignEntityOptions;}
	
	@FindBy(xpath = "(//span[text()='Department / Group']//following::span[text()='Select'])[1]")
	private static WebElement costCompAssignDeptGroupSelectBtn;
	public static WebElement getcostCompAssignDeptGroupSelectBtn() {return costCompAssignDeptGroupSelectBtn;}
	
	@FindBy(xpath = "(//span[text()='GL Account / Group']//following::span[text()='Select'])[2]")
	private static WebElement costCompAssignGLAccountGrpSelectBtn;
	public static WebElement getcostCompAssignGLAccountGrpSelectBtn() {return costCompAssignGLAccountGrpSelectBtn;}
	
	@FindBy(xpath = "//div[text()='*FIRSTDEPTGROUP FIRSTDEPTGROUP']")
	private static WebElement costCompAssignDeptGroup;
	public static WebElement getcostCompAssignDeptGroup() {return costCompAssignDeptGroup;}
	
	@FindBy(xpath = "//div[text()='JKSUPPLIES MS JKSUPPLIES']")
	private static WebElement costCompAssignGLGroup;
	public static WebElement getcostCompAssignGLGroup() {return costCompAssignGLGroup;}
	
	@FindBy(xpath = "//div[text()='Cost Component Assignment']//following::span[text()='Save']")
	private static WebElement costCompAssignSaveBtn;
	public static WebElement getcostCompAssignSaveBtn() {return costCompAssignSaveBtn;}
	
	@FindBy(xpath = "//div[text()='Cost Component Assignment']//following::span[text()='Save & Close']")
	private static WebElement costCompAssignSaveCloseBtn;
	public static WebElement getcostCompAssignSaveCloseBtn() {return costCompAssignSaveCloseBtn;}
	
	@FindBy(xpath = "//div[text()='Cost Component']//following::div[text()='JKENTITY3 JKENTITY3']")
	private static WebElement costCompAssignAssert;
	public static WebElement getcostCompAssignAssert() {return costCompAssignAssert;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Masters']//following::span[text()='New'])[1]")
	private static WebElement glAcctMasterNewBtn;
	public static WebElement getGlAcctMasterNewBtn() {return glAcctMasterNewBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Masters']//following::span[text()='Filter'])[1]")
	private static WebElement glAcctMasterFilterBtn;
	public static WebElement getGlAcctMasterFilterBtn() {return glAcctMasterFilterBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Masters']//following::span[text()='Edit'])[1]")
	private static WebElement glAcctMasterEditBtn;
	public static WebElement getGlAcctMasterEditBtn() {return glAcctMasterEditBtn;}
	
	@FindBy(xpath = "(//div[text()='GL Account Master']//following::span[text()='Save'])")
	private static WebElement glAcctMasterSaveBtn;
	public static WebElement getGlAcctMasterSaveBtn() {return glAcctMasterSaveBtn;}
	
	@FindBy(xpath = "(//div[text()='GL Account Master']//following::span[text()='Cancel & Close'])")
	private static WebElement glAcctMasterCancelCloseBtn;
	public static WebElement getGlAcctMasterCancelCloseBtn() {return glAcctMasterCancelCloseBtn;}
	
	@FindBy(xpath = "(//div[text()='GL Account Master']//following::span[text()='Save & Close'])")
	private static WebElement glAcctMasterSaveCloseBtn;
	public static WebElement getGlAcctMasterSaveCloseBtn() {return glAcctMasterSaveCloseBtn;}
	
	@FindBy(xpath = "(//div[text()='GL Account Masters']//following::span[text()='Delete'])[1]")
	private static WebElement glAcctMasterDeleteBtn;
	public static WebElement getGlAcctMasterDeleteBtn() {return glAcctMasterDeleteBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Codes']//following::span[text()='New'])")
	private static WebElement glAcctCodesNewBtn;
	public static WebElement getGlAcctCodesNewBtn() {return glAcctCodesNewBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Codes']//following::span[text()='Filter'])")
	private static WebElement glAcctCodesFilterBtn;
	public static WebElement getGlAcctCodesFilterBtn() {return glAcctCodesFilterBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Codes']//following::span[text()='Clear Filter'])")
	private static WebElement glAcctCodesClearFilterBtn;
	public static WebElement getGlAcctCodesClearFilterBtn() {return glAcctCodesClearFilterBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Codes']//following::span[text()='Edit'])")
	private static WebElement glAcctCodesEditBtn;
	public static WebElement getGlAcctCodesEditBtn() {return glAcctCodesEditBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'errorwindow')][text()='Reminder']")
	private static WebElement glAcctUpdateWarning;
	public static WebElement getGlAcctUpdateWarning() {return glAcctUpdateWarning;}
	
	@FindBy(xpath = "//div[contains(@id,'errorwindow')][text()='Reminder']//following::span[text()='OK']")
	private static WebElement glAcctUpdateWarningOkBtn;
	public static WebElement getGlAcctUpdateWarningOkBtn() {return glAcctUpdateWarningOkBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Codes']//following::span[text()='Delete'])")
	private static WebElement glAcctCodesDeleteBtn;
	public static WebElement getGlAcctCodesDeleteBtn() {return glAcctCodesDeleteBtn;}
	
	@FindBy(xpath = "(//h1[text()='GL Account Codes']//following::div[contains(@id,'dynamicGrid')])[3]//table")
	private static List<WebElement> glAcctCodesGridList;
	public static List<WebElement> getGlAcctCodesGridList() {return glAcctCodesGridList;}
	
	@FindBy(xpath = "(//div[text()='GL Account Code']//following::input[@name='code'])")
	private static WebElement glAcctCode;
	public static WebElement getGlAcctCode() {return glAcctCode;}
	
	@FindBy(xpath = "(//div[text()='GL Account Code']//following::input[@name='name'])")
	private static WebElement glAcctName;
	public static WebElement getGlAcctName() {return glAcctName;}
	
	@FindBy(name = "accountType")
	private static WebElement glAccType;
	public static WebElement getGlAccType() {return glAccType;}
	
	@FindBy(xpath = "//div[contains(@class,'x-boundlist-floating')]//div[contains(@id,'listWrap')]/ul")
	private static WebElement glAccTypeOptions;
	public static WebElement getGlAccTypeOptions() {return glAccTypeOptions;}
	
	@FindBy(xpath = "//div[text()='GL Account Code']//following::span[text()='Save & Create New']")
	private static WebElement glAccTypeSaveCreateNewBtn;
	public static WebElement getGlAccTypeSaveCreateNewBtn() {return glAccTypeSaveCreateNewBtn;}
	
	@FindBy(xpath = "//div[text()='GL Account Code']//following::span[text()='Cancel & Close']")
	private static WebElement glAccTypeCancelCloseBtn;
	public static WebElement getGlAccTypeCancelCloseBtn() {return glAccTypeCancelCloseBtn;}
	
	@FindBy(xpath = "//div[text()='GL Account Code']//following::span[text()='Save & Close']")
	private static WebElement glAccTypeSaveCloseBtn;
	public static WebElement getGlAccTypeSaveCloseBtn() {return glAccTypeSaveCloseBtn;}
	
	@FindBy(xpath = "//input[@name='field']")
	private static WebElement glAccCodefieldDrp;
	public static WebElement getGlAccCodefieldDrp() {return glAccCodefieldDrp;}
	
	@FindBy(xpath = "//div[contains(@id,'specialtagcombo')]//ul")
	private static WebElement glAccCodefieldDrpList;
	public static WebElement getGlAccCodefieldDrpList() {return glAccCodefieldDrpList;}
	
	@FindBy(xpath = "//input[@name='operator']")
	private static WebElement glAccOpfieldDrp;
	public static WebElement getGlAccOpfieldDrp() {return glAccOpfieldDrp;}
	
	@FindBy(xpath = "(//div[contains(@id,'specialtagcombo')]//ul)[2]")
	private static WebElement glAccCodeOpDrpList;
	public static WebElement getGlAccCodeOpDrpList() {return glAccCodeOpDrpList;}
	
	@FindBy(xpath = "//input[@name='condition']")
	private static WebElement glAccConditionDrp;
	public static WebElement getGlAccConditionDrp() {return glAccConditionDrp;}
	
	@FindBy(xpath = "(//div[contains(@id,'specialtagcombo')]//ul)[3]")
	private static WebElement glAccCodeCondDrpList;
	public static WebElement getGlAccCodeCondDrpList() {return glAccCodeCondDrpList;}
	
	@FindBy(xpath = "//input[@name='valuelist']")
	private static WebElement glAccValueList;
	public static WebElement getGlAccValueList() {return glAccValueList;}
	
	@FindBy(xpath = "(//div[contains(@id,'dynamiccombo')]//ul)")
	private static WebElement glAccValueListOptions;
	public static WebElement getGlAccValueListOptions() {return glAccValueListOptions;}
	
	@FindBy(xpath = "//div[text()='Filter GL Account Codes']//following::span[text()='Add']")
	private static WebElement glAccCodeAddBtn;
	public static WebElement getGlAccCodeAddBtn() {return glAccCodeAddBtn;}
	
	@FindBy(xpath = "//div[text()='Filter GL Account Codes']//following::span[text()='Apply Filter']")
	private static WebElement glAccCodeApplyFilterBtn;
	public static WebElement getGlAccCodeApplyFilterBtn() {return glAccCodeApplyFilterBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'commonTBar ')]//following::span[text()='New'])[1]")
	private static WebElement azNewBtn;
	public static WebElement getazNewBtn() {return azNewBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'commonTBar ')]//following::span[text()='Filter'])[1]")
	private static WebElement azFilterBtn;
	public static WebElement getazFilterBtn() {return azFilterBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'commonTBar ')]//following::span[text()='View'])[1]")
	private static WebElement azViewBtn;
	public static WebElement getazViewBtn() {return azViewBtn;}
	
	@FindBy(xpath = "//span[text()='Remove All']/ancestor::a")
	private static WebElement calcTypeRemoveAllBtn;
	public static WebElement getcalcTypeRemoveAllBtn() {return calcTypeRemoveAllBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'commonTBar ')]//following::span[text()='Clear Filter'])[1]")
	private static WebElement azClearFilterBtn;
	public static WebElement getazClearFilterBtn() {return azClearFilterBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'commonTBar ')]//following::span[text()='Edit'])[1]")
	private static WebElement getazEditBtn;
	public static WebElement getazEditBtn() {return getazEditBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'commonTBar ')]//following::span[text()='Delete'])[1]")
	private static WebElement azDeleteBtn;
	public static WebElement getazDeleteBtn() {return azDeleteBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'toolbar')]//following::span[text()='Save']")
	private static WebElement azSaveBtn;
	public static WebElement getazSaveBtn() {return azSaveBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'toolbar')]//following::span[text()='Save & Create New']")
	private static WebElement azSaveCreateNewBtn;
	public static WebElement getazSaveCreateNewBtn() {return azSaveCreateNewBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'toolbar')]//following::span[text()='Save As']")
	private static WebElement azSaveAsBtn;
	public static WebElement getazSaveAsBtn() {return azSaveAsBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'toolbar')]//following::span[text()='Save & Close']")
	private static WebElement azSaveCloseBtn;
	public static WebElement getazSaveCloseBtn() {return azSaveCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'toolbar')]//following::span[text()='Cancel & Close']")
	private static WebElement azCancelCloseBtn;
	public static WebElement getazCancelCloseBtn() {return azCancelCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'fixDisableOpacityGL ')]//following::span[text()='Import']")
	private static WebElement adHocImportBtn;
	public static WebElement getadHocImportBtn() {return adHocImportBtn;}
	
	@FindBy(xpath = "(//div[text()='GL Data']//following::span[text()='Filter'])[1]")
	private static WebElement glDataFilterBtn;
	public static WebElement getglDataFilterBtn() {return glDataFilterBtn;}
	
	@FindBy(xpath = "(//div[text()='GL Data']//following::span[text()='Export'])[1]")
	private static WebElement glDataExportBtn;
	public static WebElement getglDataExportBtn() {return glDataExportBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'exportwindow')]//following::span[text()='Export']")
	private static WebElement glDataWindowExportBtn;
	public static WebElement getglDataWindowExportBtn() {return glDataWindowExportBtn;}
	
	@FindBy(xpath = "(//table[contains(@id,'gridview')])[1]")
	private static WebElement azGirdFirstElement;
	public static WebElement getazGirdFirstElement() {return azGirdFirstElement;}
	
	@FindBy(xpath = "(//div[text()='Templates']//following::span[text()='New'])[1]")
	private static WebElement azTemplatesNewBtn;
	public static WebElement getazTemplatesNewBtn() {return azTemplatesNewBtn;}
	
	@FindBy(xpath = "(//div[text()='Templates']//following::span[text()='Delete'])[1]")
	private static WebElement azTemplatesDeleteBtn;
	public static WebElement getazTemplatesDeleteBtn() {return azTemplatesDeleteBtn;}
	
	@FindBy(xpath = "//input[@name='drgClassCode']")
	private static WebElement azMasterClassificationDrpDwn;
	public static WebElement getazMasterClassificationDrpDwn() {return azMasterClassificationDrpDwn;}
	
	@FindBy(xpath = "//input[@name='templateId']")
	private static WebElement templateDropdown;
	public static WebElement gettemplateDropdown() {return templateDropdown;}
	
	@FindBy(xpath = "//div[text()='AP DRG Fee Schedule Template']//following::ul")
	private static WebElement masterClassificationScheme;
	public static WebElement getmasterClassificationScheme() {return masterClassificationScheme;}
	
	@FindBy(xpath = "//div[text()='APC Fee Schedule Template']//following::ul")
	private static WebElement masterAPCClassificationScheme;
	public static WebElement getmasterAPCClassificationScheme() {return masterAPCClassificationScheme;}
	
	@FindBy(xpath = "//div[text()='APG Fee Schedule Template']//following::ul")
	private static WebElement masterAPGClassificationScheme;
	public static WebElement getmasterAPGClassificationScheme() {return masterAPGClassificationScheme;}
	
	@FindBy(xpath = "//div[text()='APR DRG Fee Schedule Template']//following::ul")
	private static WebElement masterAPRDRGClassificationScheme;
	public static WebElement getmasterAPRDRGClassificationScheme() {return masterAPRDRGClassificationScheme;}
	
	@FindBy(xpath = "//div[text()='Chargeable Activity Fee Schedule Template']//following::ul")
	private static WebElement masterChargeableActivityClassificationScheme;
	public static WebElement getmasterChargeableActivityClassificationScheme() {return masterChargeableActivityClassificationScheme;}
	
	@FindBy(xpath = "//div[text()='EAPG Fee Schedule Template']//following::ul")
	private static WebElement eapgFeeScheduleMasterScheme;
	public static WebElement geteapgFeeScheduleMasterScheme() {return eapgFeeScheduleMasterScheme;}
	
	@FindBy(xpath = "//div[text()='ICD Procedure Fee Schedule Template']//following::ul")
	private static WebElement icdFeeScheduleMasterScheme;
	public static WebElement geticdFeeScheduleMasterScheme() {return icdFeeScheduleMasterScheme;}
	
	@FindBy(xpath = "//div[text()='MSDRG Fee Schedule Template']//following::ul")
	private static WebElement msdrgFeeScheduleMasterScheme;
	public static WebElement getmsdrgFeeScheduleMasterScheme() {return msdrgFeeScheduleMasterScheme;}
	
	@FindBy(xpath = "//div[text()='Revenue Code Fee Schedule Template']//following::ul")
	private static WebElement revenueFeeScheduleMasterScheme;
	public static WebElement getrevenueFeeScheduleMasterScheme() {return revenueFeeScheduleMasterScheme;}
	
	@FindBy(xpath = "//div[text()='HCFA DRG Fee Schedule Template']//following::ul")
	private static WebElement hcfaFeeScheduleMasterScheme;
	public static WebElement gethcfaFeeScheduleMasterScheme() {return hcfaFeeScheduleMasterScheme;}
	
	@FindBy(xpath = "//div[text()='HCPCS Fee Schedule Template']//following::ul")
	private static WebElement hcpcsFeeScheduleMasterScheme;
	public static WebElement gethcpcsFeeScheduleMasterScheme() {return hcpcsFeeScheduleMasterScheme;}
	
	@FindBy(xpath = "//ul[contains(@id,'dynamiccombo')]")
	private static WebElement azMasterDropdownOptions;
	public static WebElement getazMasterDropdownOptions() {return azMasterDropdownOptions;}
	
	@FindBy(xpath = "//div[text()='New Template']//following::span[text()='Save & Close']")
	private static WebElement azMasterDropdownSaveClose;
	public static WebElement getazMasterDropdownSaveClose() {return azMasterDropdownSaveClose;}
	
	@FindBy(xpath = "//div[text()='New Template']//following::input[@name='name']")
	private static WebElement azMasterName;
	public static WebElement getazMasterName() {return azMasterName;}
	
	@FindBy(xpath = "//div[text()='Templates']")
	private static WebElement azTemplatesPanel;
	public static WebElement getazTemplatesPanel() {return azTemplatesPanel;}
	
	@FindBy(xpath = "//span[text()='Select Zip Codes']/../../..")
	private static WebElement selectZipCodeBtn;
	public static WebElement getselectZipCodeBtn() {return selectZipCodeBtn;}
	
	@FindBy(xpath = "//span[text()='Apply']/../../..")
	private static WebElement applyZipCodeBtn;
	public static WebElement getapplyZipCodeBtn() {return applyZipCodeBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'window')][text()='Select Zip Codes']")
	private static WebElement selectZipCodeWindow;
	public static WebElement getselectZipCodeWindow() {return selectZipCodeWindow;}
	
	@FindBy(xpath = "//div[text()='Select Zip Codes']//following::span[text()='Apply']")
	private static WebElement selectZipCodeApplyBtn;
	public static WebElement getselectZipCodeApplyBtn() {
		return selectZipCodeApplyBtn;
	}
	@FindBy(xpath = "//div[text()='Select Zip Codes']//following::span[text()='Cancel']")
	private static WebElement selectZipCodeCancelBtn;
	public static WebElement getselectZipCodeCancelBtn() {
		return selectZipCodeCancelBtn;
	}
	@FindBy(xpath = "//*[contains(@id,'buttonsContainer')]/descendant::span[text()='Select']")
	private static WebElement selectZipCodeSelectBtn;
	public static WebElement getSelectZipCodeSelectBtn() {
		return selectZipCodeSelectBtn;
	}
	@FindBy(xpath = "//span[text()='Zip Code']//following::table//td[2]/div")
	private static List<WebElement> lowZipCodeGrid;
	public static List<WebElement> getlowZipCodeGrid() {
		return lowZipCodeGrid;
	}
	@FindBy(xpath = "//span[text()='High Zip Code']//following::table//td[4]/div")
	private static List<WebElement> highZipCodeGrid;
	public static List<WebElement> gethighZipCodeGrid() {
		return highZipCodeGrid;
	}
	
	@FindBy(xpath = "(//label[text()='Show Ranges']//following::input[@name='type'])")
	private static WebElement selectShowCodesBtn;
	public static WebElement getselectShowCodesBtn() {
		return selectShowCodesBtn;
	}
	@FindBy(xpath = "//label[text()='Show Ranges']//preceding::input[@name='type']")
	private static WebElement selectShowRangesBtn;
	public static WebElement getselectShowRangesBtn() {
		return selectShowRangesBtn;
	}
	@FindBy(xpath = "//div[text()='Zip Code Group']//following::span[text()='Save']")
	private static WebElement selectZipCodeSaveBtn;
	public static WebElement getselectZipCodeSaveBtn() {
		return selectZipCodeSaveBtn;
	}
	@FindBy(xpath = "//div[text()='Zip Code Group']//following::span[text()='Save & Close']")
	private static WebElement selectZipCodeSaveCloseBtn;
	public static WebElement getselectZipCodeSaveCloseBtn() {
		return selectZipCodeSaveCloseBtn;
	}
	@FindBy(xpath = "//div[text()='Zip Code Group']//following::span[text()='Cancel & Close']")
	private static WebElement selectZipCodeCancelCloseBtn;
	public static WebElement getselectZipCodeCancelCloseBtn() {
		return selectZipCodeCancelCloseBtn;
	}
	@FindBy(xpath = "//div[text()='Zip Code Group']//following::span[text()='Save & Create New']")
	private static WebElement selectZipCodeSaveNewBtn;
	public static WebElement getselectZipCodeSaveNewBtn() {
		return selectZipCodeSaveNewBtn;
	}
	@FindBy(xpath = "//div[contains(text(),'Item(s) Selected')]//following::ul/li")
	private static List<WebElement> selectedZipCodes;
	public static List<WebElement> getselectedZipCodes() {
		return selectedZipCodes;
	}
	@FindBy(xpath = "//table[contains(@id,'gridview')]//td[2]/div")
	private static WebElement zipCodeGroups;
	public static WebElement getzipCodeGroups() {
		return zipCodeGroups;
	}
//	@FindBy(name = "departmentHierarchy")
//	private static WebElement activityStatsMasterDropdown;
//	public static WebElement getactivityStatsMasterDropdown() {
//		return activityStatsMasterDropdown;
//	}
	@FindBy(name = "departmentHierarchy")
	private static List<WebElement> activityStatsMasterDropdown;
	public static List<WebElement> getactivityStatsMasterDropdown() {
		return activityStatsMasterDropdown;
	}
	@FindBy(name = "timePeriodName")
	private static List<WebElement> timePeriodName;
	public static List<WebElement> gettimePeriodName() {
		return timePeriodName;
	}
	@FindBy(xpath = "//div[text()='Activity Statistic Master']//following::ul")
	private static WebElement activityStatsMasterDropdownList;
	public static WebElement getactivityStatsMasterDropdownList() {
		return activityStatsMasterDropdownList;
	}
	@FindBy(xpath = "//div[text()='Activity Volume Data Scenario']//following::ul")
	private static WebElement activityVolDataScenarioDropdownList;
	public static WebElement getactivityVolDataScenarioDropdownList() {
		return activityVolDataScenarioDropdownList;
	}
	@FindBy(xpath = "//h1//following::a//span[text()='New']")
	private static List<WebElement> azInnerPageNewBtn;
	public static List<WebElement> getazInnerPageNewBtn() {return azInnerPageNewBtn;}
	
	@FindBy(xpath = "//h1//following::a//span[text()='Edit']")
	private static List<WebElement> azInnerPageEditBtn;
	public static List<WebElement> getazInnerPageEditBtn() {return azInnerPageEditBtn;}
	
	@FindBy(xpath = "//h1//following::a//span[text()='Delete']")
	private static List<WebElement> azInnerPageDeleteBtn;
	public static List<WebElement> getazInnerPageDeleteBtn() {return azInnerPageDeleteBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save & Create New']")
	private static List<WebElement> azInnerPageSaveCreateNewBtn;
	public static List<WebElement> getazInnerPageSaveCreateNewBtn() {return azInnerPageSaveCreateNewBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save']")
	private static List<WebElement> azInnerPageSaveBtn;
	public static List<WebElement> getazInnerPageSaveBtn() {return azInnerPageSaveBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Save & Close']")
	private static List<WebElement> azInnerPageSaveCloseBtn;
	public static List<WebElement> getazInnerPageSaveCloseBtn() {return azInnerPageSaveCloseBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close']")
	private static List<WebElement> azInnerPageCancelCloseBtn;
	public static List<WebElement> getazInnerPageCancelCloseBtn() {return azInnerPageCancelCloseBtn;}
	
	@FindBy(xpath = "//div[text()='New Mapping']//following::ul")
	private static WebElement entityDropdownList;
	public static WebElement getentityDropdownList() {
		return entityDropdownList;
	}
	@FindBy(xpath = "(//span[text()='Department / Group']//following::span[text()='Select'])[1]")
	private static WebElement deptGroupSelectBtn;
	public static WebElement getdeptGroupSelectBtn() {
		return deptGroupSelectBtn;
	}
	@FindBy(xpath = "(//span[text()='Department / Group']//following::span[text()='Select'])[2]")
	private static WebElement ModifierSelectBtn;
	public static WebElement getModifierSelectBtn() {
		return ModifierSelectBtn;
	}
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//following::span[text()='Cancel & Close']")
	private static WebElement dynamicWindowCancelCloseButton ;
	public static WebElement getdynamicWindowCancelCloseButton() {
		return dynamicWindowCancelCloseButton;
	}
	@FindBy(xpath = "//div[contains(@id,'messagebox')]//following::span[text()='Cancel & Close']")
	private static List<WebElement> dynamicWindowCancelCloseBtnlist ;
	public static List<WebElement> getdynamicWindowCancelCloseBtnlist() {
		return dynamicWindowCancelCloseBtnlist;
	}
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')]//following::span[text()='New']")
	private static WebElement dynamicWindowNewButton ;
	public static WebElement getdynamicWindowNewButton() {
		return dynamicWindowNewButton;
	}
	@FindBy(xpath = "//div[text()='Mappings']//following::span[text()='New']")
	private static WebElement mappingNewButton ;
	public static WebElement getmappingNewButton() {
		return mappingNewButton;
	}
	@FindBy(xpath = "//div[text()='Mappings']//following::span[text()='Delete']")
	private static WebElement mappingDeleteButton ;
	public static WebElement getmappingDeleteButton() {
		return mappingDeleteButton;
	}
	@FindBy(xpath = "//div[text()='Job Codes']//following::span[text()='Delete']")
	private static WebElement jobCodeDeleteButton ;
	public static WebElement getjobCodeDeleteButton() {
		return jobCodeDeleteButton;
	}
	@FindBy(xpath = "//div[text()='Mappings']//following::span[text()='Cancel & Close']")
	private static WebElement mappingCancelCloseButton ;
	public static WebElement getmappingCancelCloseButton() {
		return mappingCancelCloseButton;
	}
	@FindBy(xpath = "//div[text()='Activity Statistic']//following::input[@name='name']")
	private static WebElement activityStatsInputName ;
	public static WebElement getactivityStatsInputName() {
		return activityStatsInputName;
	}
	@FindBy(xpath = "(//div[text()='Activity Volume Data']//following::div[@class='x-grid-item-container']//table)[2]//td[2]/div")
	private static WebElement activityVolData ;
	public static WebElement getactivityVolData() {
		return activityVolData;
	}
	@FindBy(xpath = "//span[text()='Age In Years']//following::div[@class='x-grid-cell-inner ']")
	private static List<WebElement> ageGroupElements ;
	public static List<WebElement> getageGroupElements() {
		return ageGroupElements;
	}
	@FindBy(name = "shortName")
	private static WebElement shortName ;
	public static WebElement getshortName() {
		return shortName;
	}
	@FindBy(name = "supplyCost")
	private static WebElement supplyCost ;
	public static WebElement getsupplyCost() {
		return supplyCost;
	}
	@FindBy(name = "transactionCategoryCode")
	private static List<WebElement> categoryDropdown;
	public static List<WebElement> getcategoryDropdown() {
		return categoryDropdown;
	}
	@FindBy(xpath = "//div[text()='Transaction Type']//following::ul")
	private static WebElement transactionTypeDrpdwnList;
	public static WebElement gettransactionTypeDrpdwnList() {
		return transactionTypeDrpdwnList;
	}
	@FindBy(xpath = "//h1[text()='Transaction Classification Code List']//following::span[text()='Delete']")
	private static WebElement transactionCodeListDeleteBtn;
	public static WebElement gettransactionCodeListDeleteBtn() {
		return transactionCodeListDeleteBtn;
	}
	@FindBy(name = "rbrvsrvuMod")
	private static WebElement checkboxrbrvs;
	public static WebElement getcheckboxrbrvs() {
		return checkboxrbrvs;
	}
	@FindBy(xpath = "//h1[text()='Fee Schedule Entries']//following::span[text()='Save']")
	private static WebElement feeScheduleEntriesSaveBtn;
	public static WebElement getfeeScheduleEntriesSaveBtn() {
		return feeScheduleEntriesSaveBtn;
	}
	@FindBy(xpath = "//h1[text()='Fee Schedule Entries']//following::span[text()='Save & Close']")
	private static WebElement feeScheduleEntriesSaveCloseBtn;
	public static WebElement getfeeScheduleEntriesSaveCloseBtn() {
		return feeScheduleEntriesSaveCloseBtn;
	}
	@FindBy(xpath = "//h1[text()='Fee Schedule Entries']//following::span[text()='Delete']")
	private static WebElement feeScheduleEntriesDeleteBtn;
	public static WebElement getfeeScheduleEntriesDeleteBtn() {
		return feeScheduleEntriesDeleteBtn;
	}
	@FindBy(xpath = "//input[@name='startDate']")
	private static WebElement effectiveperiodStartDate;
	public static WebElement geteffectiveperiodStartDate() {
		return effectiveperiodStartDate;
	}
	@FindBy(xpath = "//input[@name='endDate']")
	private static WebElement effectiveperiodEndDate;
	public static WebElement geteffectiveperiodEndDate() {
		return effectiveperiodEndDate;
	}
	@FindBy(xpath = "//label[text()='Charges that meet a certain service criteria']/preceding::input[1]")
	private static WebElement chargesRadioBtn;
	public static WebElement getchargesRadioBtn() {
		return chargesRadioBtn;
	}
	@FindBy(xpath = "//label[text()='Apply to only charges that meet certain criteria']/preceding::input[1]")
	private static WebElement applyChargesRadioBtn;
	public static WebElement getapplyChargesRadioBtn() {
		return applyChargesRadioBtn;
	}
	@FindBy(name = "productLineId")
	private static WebElement productLine;
	public static WebElement getProductLine() {
		return productLine;
	}
	@FindBy(name = "lineOfBusinessId")
	private static WebElement lineOfBusiness;
	public static WebElement getlineOfBusiness() {
		return lineOfBusiness;
	}
	@FindBy(xpath = "(//div[text()='Benefit Plan Code']//following::ul)[1]")
	private static WebElement productLineList;
	public static WebElement getproductLineList() {
		return productLineList;
	}
	@FindBy(xpath = "(//div[text()='Benefit Plan Code']//following::ul)[2]")
	private static WebElement lineOfBusinessList;
	public static WebElement getlineOfBusinessList() {
		return lineOfBusinessList;
	}
	@FindBy(xpath = "(//div[text()='New Benefit Plan']//following::span[text()='New'])[1]")
	private static WebElement productLineNewBtn;
	public static WebElement getproductLineNewBtn() {
		return productLineNewBtn;
	}
	@FindBy(xpath = "(//div[text()='New Benefit Plan']//following::span[text()='New'])[2]")
	private static WebElement BusinessLineNewBtn;
	public static WebElement getBusinessLineNewBtn() {
		return BusinessLineNewBtn;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='New']")
	private static WebElement effectivePeriodNewBtn;
	public static WebElement geteffectivePeriodNewBtn() {
		return effectivePeriodNewBtn;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='Save & Close']")
	private static WebElement effectivePeriodSaveCloseBtn;
	public static WebElement geteffectivePeriodSaveCloseBtn() {
		return effectivePeriodSaveCloseBtn;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='Cancel & Close']")
	private static WebElement effectivePeriodCancelCloseBtn;
	public static WebElement geteffectivePeriodCancelCloseBtn() {
		return effectivePeriodCancelCloseBtn;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='Edit']")
	private static WebElement effectivePeriodEditBtn;
	public static WebElement geteffectivePeriodEditBtn() {
		return effectivePeriodEditBtn;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='Delete']")
	private static WebElement effectivePeriodDeleteBtn;
	public static WebElement geteffectivePeriodDeleteBtn() {
		return effectivePeriodDeleteBtn;
	}
	@FindBy(xpath = "(//div[text()='New Contact']//following::ul)[1]")
	private static WebElement contactTypeDrpdown;
	public static WebElement getcontactTypeDrpdown() {
		return contactTypeDrpdown;
	}
	@FindBy(xpath = "(//div[text()='New Contact']//following::ul)[2]")
	private static WebElement genderDrpdown;
	public static WebElement getgenderDrpdown() {
		return genderDrpdown;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='Save & Create New']")
	private static WebElement effectivePeriodSaveCreateNewBtn;
	public static WebElement geteffectivePeriodSaveCreateNewBtn() {
		return effectivePeriodSaveCreateNewBtn;
	}
	@FindBy(xpath = "//*[text()='Effective Periods']//following::span[text()='Save']")
	private static WebElement effectivePeriodSaveBtn;
	public static WebElement geteffectivePeriodSaveBtn() {
		return effectivePeriodSaveBtn;
	}
	@FindBy(xpath = "(//div[text()='Chargeable Activity-HCPCS Code Mapping Scheme']//following::ul)[1]")
	private static WebElement chargeMasterDropdown;
	public static WebElement getchargeMasterDropdown() {
		return chargeMasterDropdown;
	}
	@FindBy(xpath = "(//div[text()='Chargeable Activity-HCPCS Code Mapping Scheme']//following::ul)[2]")
	private static WebElement codechargeMasterHcpcsDropdown;
	public static WebElement getcodechargeMasterHcpcsDropdown() {
		return codechargeMasterHcpcsDropdown;
	}
	@FindBy(xpath = "(//div[text()='Chargeable Activity-Revenue Code Mapping Scheme']//following::ul)[1]")
	private static WebElement revenueCodeMasterDropdown;
	public static WebElement getrevenueCodeMasterDropdown() {
		return revenueCodeMasterDropdown;
	}
	@FindBy(xpath = "(//div[text()='Chargeable Activity-Revenue Code Mapping Scheme']//following::ul)[2]")
	private static WebElement revenuCodeDropdown;
	public static WebElement getrevenuCodeDropdown() {
		return revenuCodeDropdown;
	}
	@FindBy(xpath = "//span[text()='Save & Create New']")
	private static List<WebElement> saveCreateNew;
	public static List<WebElement> getsaveCreateNew() {
		return saveCreateNew;
	}
	@FindBy(xpath = "//span[text()='Save']")
	private static List<WebElement> saveBtn;
	public static List<WebElement> getsaveBtn() {
		return saveBtn;
	}
	@FindBy(xpath = "//span[text()='Save & Close']")
	private static List<WebElement> saveCloseBtn;
	public static List<WebElement> getsaveCloseBtn() {
		return saveCloseBtn;
	}
	@FindBy(xpath = "(//div[text()='New Mapping']//following::div[contains(@class,'x-panel')]//span[text()='Select'])[1]")
	private static WebElement chargeModifierSelectBtn;
	public static WebElement getchargeModifierSelectBtn() {
		return chargeModifierSelectBtn;
	}
	@FindBy(xpath = "(//div[text()='New Mapping']//following::div[contains(@class,'x-panel')]//span[text()='Select'])[2]")
	private static WebElement hcpcsModifierSelectBtn;
	public static WebElement gethcpcsModifierSelectBtn() {
		return hcpcsModifierSelectBtn;
	}
	@FindBy(xpath = "//div[text()='Revenue Code']//following::span[text()='Select']")
	private static WebElement revenueCodeSelectBtn;
	public static WebElement getrevenueCodeSelectBtn() {
		return revenueCodeSelectBtn;
	}
	@FindBy(name = "hcpcsModifierCode")
	private static WebElement hcpcsModCode;
	public static WebElement gethcpcsModCode() {
		return hcpcsModCode;
	}
	@FindBy(xpath = "//span[text()='Modifier']//following::ul")
	private static WebElement modifierDropdown;
	public static WebElement getmodifierDropdown() {
		return modifierDropdown;
	}
	@FindBy(xpath = "(//div[text()='Commission Broker Agencies']//following::input[@name='name'])")
	private static WebElement commissionBrokerName;
	public static WebElement getcommissionBrokerName() {
		return commissionBrokerName;
	}
	@FindBy(name = "ethnicityCode")
	private static WebElement ethnicityCode;
	public static WebElement getethnicityCode() {
		return ethnicityCode;
	}
	@FindBy(name = "sexCode")
	private static WebElement genderCode;
	public static WebElement getgenderCode() {
		return genderCode;
	}
	@FindBy(name = "homePhoneNumber")
	private static WebElement homePhoneNumber;
	public static WebElement gethomePhoneNumber() {
		return homePhoneNumber;
	}
	@FindBy(xpath = "//div[text()='Consumer']//following::ul")
	private static WebElement ethnicityDropdown;
	public static WebElement getethnicityDropdown() {
		return ethnicityDropdown;
	}
	@FindBy(xpath = "//div[text()='New Consumer History Effective Period']//following::ul")
	private static WebElement genderDropdown;
	public static WebElement getgenderDropdown() {
		return genderDropdown;
	}
	@FindBy(name = "birthDatechk")
	private static WebElement birthDateCheckbox;
	public static WebElement getbirthDateCheckbox() {
		return birthDateCheckbox;
	}
	@FindBy(xpath = "(//h1[text()='Consumer History Effective Periods']//following::span[text()='New'])[1]")
	private static WebElement cosumnerHistoryNewBtn;
	public static WebElement getcosumnerHistoryNewBtn() {
		return cosumnerHistoryNewBtn;
	}
	@FindBy(xpath = "(//h1[text()='Consumer History Effective Periods']//following::span[text()='Delete'])[1]")
	private static WebElement cosumnerHistoryDeleteBtn;
	public static WebElement getcosumnerHistoryDeleteBtn() {
		return cosumnerHistoryDeleteBtn;
	}
	@FindBy(xpath = "(//h1[text()='Member History Effective Periods']//following::span[text()='New'])[1]")
	private static WebElement memberHistoryNewBtn;
	public static WebElement getmemberHistoryNewBtn() {
		return memberHistoryNewBtn;
	}
	@FindBy(xpath = "(//h1[text()='Member History Effective Periods']//following::span[text()='Delete'])[1]")
	private static WebElement memberHistoryDeleteBtn;
	public static WebElement getmemberHistoryDeleteBtn() {
		return memberHistoryDeleteBtn;
	}
	@FindBy(xpath = "(//h1[text()='Consumer History Effective Periods']//following::span[text()='Edit'])[1]")
	private static WebElement cosumnerHistoryEditBtn;
	public static WebElement getcosumnerHistoryEditBtn() {
		return cosumnerHistoryEditBtn;
	}
	@FindBy(xpath = "(//span[text()='Employer Subgroup']//following::span[text()='Select'])[1]")
	private static WebElement empSubgroupSelectBtn;
	public static WebElement getempSubgroupSelectBtn() {
		return empSubgroupSelectBtn;
	}
	@FindBy(xpath = "(//span[text()='Member Designation']//following::span[text()='Select'])[1]")
	private static WebElement memberDesignation;
	public static WebElement getmemberDesignation() {
		return memberDesignation;
	}
	@FindBy(xpath = "(//h1[text()='Medical Records']//following::span[text()='New'])[1]")
	private static WebElement medicalRecordsNewBtn;
	public static WebElement getmedicalRecordsNewBtn1() {
		return medicalRecordsNewBtn;
	}
	@FindBy(xpath = "(//h1[text()='Medical Records']//following::span[text()='Delete'])[1]")
	private static WebElement medicalRecordsDeleteBtn;
	public static WebElement getmedicalRecordsDeleteBtn() {
		return medicalRecordsDeleteBtn;
	}
	@FindBy(xpath = "(//span[text()='Care Delivery Facility']//following::span[text()='Select'])[1]")
	private static WebElement careDeliverySelectBtn;
	public static WebElement getcareDeliverySelectBtn() {
		return careDeliverySelectBtn;
	}
	@FindBy(xpath = "(//span[text()='Destination Reimbursement Scenario']//following::ul)[2]")
	private static WebElement destReimburseScenario;
	public static WebElement getdestReimburseScenario() {
		return destReimburseScenario;
	}
	@FindBy(xpath = "//div[text()='Enter Lump Sum Amounts']//following::table//td[2]/div")
	private static List<WebElement> lumpSumAmountTable;
	public static List<WebElement> getlumpSumAmountTable() {
		return lumpSumAmountTable;
	}
	@FindBy(xpath = "(//label[text()='Benefit Plan Codes']//following::span[text()='Select'])[1]")
	private static WebElement benifitPlanCodeSelect;
	public static WebElement getbenifitPlanCodeSelect() {
		return benifitPlanCodeSelect;
	}
	@FindBy(xpath = "(//label[text()='Bill Types']//following::span[text()='Select'])[1]")
	private static WebElement billtypesSelect;
	public static WebElement getbilltypesSelect() {
		return billtypesSelect;
	}
	@FindBy(xpath = "(//label[text()='Care Delivery Facilities']//following::span[text()='Select'])[1]")
	private static WebElement careDeliveryFacilitySelect;
	public static WebElement getcareDeliveryFacilitySelect() {
		return careDeliveryFacilitySelect;
	}
	@FindBy(xpath = "(//label[text()='Contract Types']//following::span[text()='Select'])[1]")
	private static WebElement contractTypesSelect;
	public static WebElement getcontractTypesSelect() {
		return contractTypesSelect;
	}
	@FindBy(xpath = "(//label[text()='Contracts']//following::span[text()='Select'])[1]")
	private static WebElement contractSelect;
	public static WebElement getcontractSelect() {
		return contractSelect;
	}
	@FindBy(xpath = "(//label[text()='EFR Categories']//following::span[text()='Select'])[1]")
	private static WebElement efrCategorySelect;
	public static WebElement getefrCategorySelect() {
		return efrCategorySelect;
	}
	@FindBy(xpath = "(//label[text()='Encounter Types']//following::span[text()='Select'])[1]")
	private static WebElement encounterTypeSelect;
	public static WebElement getencounterTypeSelect() {
		return encounterTypeSelect;
	}
	@FindBy(xpath = "(//label[text()='Benefit Plan Codes']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement benifitPlanCodeView;
	public static WebElement getbenifitPlanCodeView() {
		return benifitPlanCodeView;
	}
	@FindBy(xpath = "(//label[text()='Bill Types']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement billtypesView;
	public static WebElement getbilltypesView() {
		return billtypesView;
	}
	@FindBy(xpath = "(//label[text()='Care Delivery Facilities']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement careDeliveryFacilityView;
	public static WebElement getcareDeliveryFacilityView() {
		return careDeliveryFacilityView;
	}
	@FindBy(xpath = "(//label[text()='Contract Types']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement contractTypesView;
	public static WebElement getcontractTypesView() {
		return contractTypesView;
	}
	@FindBy(xpath = "(//label[text()='Contracts']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement contractView;
	public static WebElement getcontractView() {
		return contractView;
	}
	@FindBy(xpath = "(//label[text()='EFR Categories']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement efrCategoryView;
	public static WebElement getefrCategoryView() {
		return efrCategoryView;
	}
	@FindBy(xpath = "(//label[text()='Encounter Types']//following::span[text()='View Selected Criteria'])[1]")
	private static WebElement encounterTypeView;
	public static WebElement getencounterTypeView() {
		return encounterTypeView;
	}
	@FindBy(xpath = "(//div[text()='Hierarchies']//following::ul)[2]")
	private static WebElement deptDropdown;
	public static WebElement getdeptDropdown() {
		return deptDropdown;
	}
	@FindBy(xpath = "(//div[text()='Hierarchies']//following::ul)[3]")
	private static WebElement glAccountDropdown;
	public static WebElement getglAccountDropdown() {
		return glAccountDropdown;
	}
	@FindBy(xpath = "//div[text()='Cost Component']//following::ul")
	private static WebElement expenseType;
	public static WebElement getexpenseType() {
		return expenseType;
	}
	@FindBy(xpath = "//div[text()='Cost Component Assignment']//following::ul")
	private static WebElement costComponentAssignmentEntity;
	public static WebElement getcostComponentAssignmentEntity() {
		return costComponentAssignmentEntity;
	}
	@FindBy(xpath = "(//span[text()='Department / Group']//following::span[text()='Select'])[1]")
	private static WebElement deptGroup;
	public static WebElement getdeptGroup() {
		return deptGroup;
	}
	@FindBy(xpath = "(//span[text()='GL Account / Group']//following::span[text()='Select'])[2]")
	private static WebElement glAcctGroup;
	public static WebElement getglAcctGroup() {
		return glAcctGroup;
	}
	@FindBy(xpath = "//div[text()='Cost Component Variability Master']//following::ul")
	private static WebElement costComponentMasterDrpdwn;
	public static WebElement getcostComponentMasterDrpdwn() {
		return costComponentMasterDrpdwn;
	}
	@FindBy(xpath = "//div[text()='Cost Method Master']//following::ul")
	private static WebElement costMethodMasterDrpdwn;
	public static WebElement getcostMethodMasterDrpdwn() {
		return costMethodMasterDrpdwn;
	}
	@FindBy(xpath = "//div[text()='New Cost Component Variability Assignment']//following::ul")
	private static WebElement costAssignEntityDrp;
	public static WebElement getcostAssignEntityDrp() {
		return costAssignEntityDrp;
	}
	@FindBy(xpath = "//div[text()='New Cost Method Assignment']//following::ul")
	private static WebElement costMethodAssignEntityDrp;
	public static WebElement getcostMethodAssignEntityDrp() {
		return costMethodAssignEntityDrp;
	}
	@FindBy(xpath = "(//span[text()='Department / Group']//following::ul)[2]")
	private static WebElement costComponentDrpdwn;
	public static WebElement getcostComponentDrpdwn() {
		return costComponentDrpdwn;
	}
	@FindBy(xpath = "(//div[text()='Cost Method Assignment']//following::ul/li[text()='RCC']/..)")
	private static WebElement costMethodDrpdwn;
	public static WebElement getcostMethodDrpdwn() {
		return costMethodDrpdwn;
	}
	@FindBy(xpath = "(//div[text()='Cost Method Assignment']//following::ul/li[text()='Volume']/..)[2]")
	private static WebElement costMethodDefaultDrpdwn;
	public static WebElement getcostMethodDefaultDrpdwn() {
		return costMethodDefaultDrpdwn;
	}
	@FindBy(xpath = "(//div[text()='Cost Method Assignment']//following::ul/li[text()='Exams General']/..)")
	private static WebElement costMethodProducttypeDrpdwn;
	public static WebElement getcostMethodProducttypeDrpdwn() {
		return costMethodProducttypeDrpdwn;
	}
	@FindBy(xpath = "(//div[text()='Cost Method Assignment']//following::ul/li[text()='Act Cost End to End Chg Grp']/..)")
	private static WebElement chargeableActivtyGroup;
	public static WebElement getchargeableActivtyGroup() {
		return chargeableActivtyGroup;
	}
	@FindBy(xpath = "(//span[text()='RCAC Cost Factor List']//following::table//td/div)")
	private static List<WebElement> rcacCostFactorList;
	public static List<WebElement> getrcacCostFactorList() {
		return rcacCostFactorList;
	}
	@FindBy(xpath = "(//div[text()='RCAC Cost Assignment']//following::ul/li[text()='Jan']/..)")
	private static WebElement rcacStartMonth;
	public static WebElement getrcacStartMonth() {
		return rcacStartMonth;
	}
	@FindBy(xpath = "(//div[text()='RCAC Cost Assignment']//following::ul)[2]")
	private static WebElement rcacStartYear;
	public static WebElement getrcacStartYear() {
		return rcacStartYear;
	}
	@FindBy(xpath = "(//div[text()='Results']//following::ul/li[text()='150 Marina Medical Center']/..)")
	private static WebElement costScenarioEntitycode;
	public static WebElement getcostScenarioEntitycode() {
		return costScenarioEntitycode;
	}
	@FindBy(xpath = "(//div[text()='Results']//following::ul/li[text()='2130  PED ICU']/..)")
	private static WebElement costScenarioDept;
	public static WebElement getcostScenarioDept() {
		return costScenarioDept;
	}
	@FindBy(xpath = "(//div[text()='Results']//following::ul/li[text()='Jan 2005 to Jan 2005']/..)")
	private static WebElement costScenarioResults;
	public static WebElement getcostScenarioResults() {
		return costScenarioResults;
	}
	@FindBy(xpath = "(//div[text()='Results']//following::ul/li[text()='Salaries and Wages']/..)")
	private static WebElement costScenarioComponent;
	public static WebElement getcostScenarioComponent() {
		return costScenarioComponent;
	}
	@FindBy(xpath = "(//span[text()='Hide'])[1]")
	private static WebElement hideButton;
	public static WebElement gethideButton() {
		return hideButton;
	}
	@FindBy(xpath = "(//span[text()='Show'])[1]")
	private static WebElement showButton;
	public static WebElement getshowButton() {
		return showButton;
	}
	@FindBy(xpath = "(//span[text()='Show'])[2]")
	private static WebElement showButton2;
	public static WebElement getshowButton2() {
		return showButton2;
	}
	@FindBy(xpath = "//span[text()='Department Master']//following::ul/li[text()='150 old master 150']/..")
	private static WebElement deptMasterDrp;
	public static WebElement getdeptMasterDrp() {
		return deptMasterDrp;
	}
	@FindBy(xpath = "//span[text()='GL Account Master']//following::ul/li[text()='MARINA Marina Master']/..")
	private static WebElement glAcctMasterDrp;
	public static WebElement getglAcctMasterDrp() {
		return glAcctMasterDrp;
	}
	@FindBy(xpath = "//div[text()='Department Code']//following::ul/li[text()='Patient Care Department']/..")
	private static WebElement deptCodeDrp;
	public static WebElement getdeptCodeDrp() {
		return deptCodeDrp;
	}
	@FindBy(xpath = "//div[text()='Encounter Type']//following::ul/li[text()='1']/..")
	private static WebElement losType;
	public static WebElement getlosType() {
		return losType;
	}
}
