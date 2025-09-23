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
	
	@FindBy(xpath = "//h1[text()='Age In Days Groups']//following::span[text()='Delete']")
	private static WebElement ageGroupDeleteBtn;
	public static WebElement getageGroupDeleteBtn() {return ageGroupDeleteBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'groupsdetailpanel')]//div[text()='Age In Days Groups']//following::span[text()='Select']")
	private static WebElement ageGroupSelectBtn;
	public static WebElement getageGroupSelectBtn() {return ageGroupSelectBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'dynamicwindow')][text()='Add Age In Days Group Members']")
	private static WebElement ageGroupSelectWindow;
	public static WebElement getageGroupSelectWindow() {return ageGroupSelectWindow;}
	
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
}
