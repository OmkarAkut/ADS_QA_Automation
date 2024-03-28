package webdriver.maps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

/*
This map file includes all elements found on the Model Library.
Section 1: Model Library
Section 2: Contracting Model Library
Section 3: Model Library Filter
 */

public class ModelLibraryMap extends MapConfig {

    /******Model Library Page******/

    @FindBy(xpath = "//*[contains(@onclick,'mlfd.htm') and @class='listhelpLnk']")
    private WebElement modelLibraryPageHelpLink;
    public WebElement getModelLibraryPageHelpLink() {return modelLibraryPageHelpLink;}

    
    @FindBy(xpath = "//input[@name='searchText']") 
    private WebElement modelLibraryFieldSearch;
    public WebElement getModelLibraryFieldSearch() {return modelLibraryFieldSearch;}

    @FindBy(xpath = "//*[contains(@class,'statusSearch')]")
    private WebElement modelLibraryButtonSearch;
    public WebElement getModelLibraryButtonSearch() {return modelLibraryButtonSearch;}

    @FindBy(xpath = "//*[contains(@id,'costing_modelcosts_tabId')]/descendant::span[text()='New'][contains(@id,'abutton')]")
    private WebElement modelLibraryCostModelButtonNew;
    public WebElement getModelLibraryCostModelButtonNew() {return modelLibraryCostModelButtonNew;}

    @FindBy(xpath = "//*[contains(@id,'costing_modelcosts_tabId')]/descendant::span[text()='Open Task List']")
    private WebElement modelLibraryButtonOpenTaskList;
    public WebElement getModelLibraryButtonOpenTaskList() {return modelLibraryButtonOpenTaskList;}

    @FindBy(xpath = "//*[contains(@id,'costing_modelcosts_tabId')]/descendant::span[text()='Filter']")
    private WebElement modelLibraryButtonFilter;
    public WebElement getModelLibraryButtonFilter() {return modelLibraryButtonFilter;}

    @FindBy(xpath = "//*[contains(@id,'costing_modelcosts_tabId')]/descendant::span[text()='Clear Filter']")
    private WebElement modelLibraryButtonClearFilter;
    public WebElement getModelLibraryButtonClearFilter() {return modelLibraryButtonClearFilter;}

    @FindBy(xpath = "//*[contains(@id,'costing_modelcosts_tabId')]/descendant::span[text()='Delete'][contains(@id,'abutton')]")
    private WebElement modelLibraryCostModelButtonDelete;
    public WebElement getModelLibraryCostModelButtonDelete() {return modelLibraryCostModelButtonDelete;}

    @FindBy(xpath = "//*[contains(@class,'leftBarCls')]/descendant::span[text()='New'][contains(@id,'button')]")
    private WebElement modelLibraryButtonNewFolder;
    public WebElement getModelLibraryButtonNewFolder() {return modelLibraryButtonNewFolder;}

    @FindBy(xpath = "//*[contains(@id,'costing_modelcosts_tabId')]/descendant::span[text()='Rename']")
    private WebElement modelLibraryButtonRenameFolder;
    public WebElement getModelLibraryButtonRenameFolder() {return modelLibraryButtonRenameFolder;}

    @FindBy(xpath = "//*[contains(@class,'leftBarCls')]/descendant::span[text()='Delete'][contains(@id,'button')]")
    private WebElement modelLibraryButtonDeleteFolder;
    public WebElement getModelLibraryButtonDeleteFolder() {return modelLibraryButtonDeleteFolder;}

//    Omkar 28/11/2023 : Changes in xpath for 11.2
//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Costing')]")
    @FindBy(xpath ="//*[contains(@class,'grid-cell-treecolumn')]//span[contains(text(),'Costing')]")
    private WebElement modelLibraryTreeColumnCosting;
    public WebElement getModelLibraryTreeColumnCosting() {return modelLibraryTreeColumnCosting;}

//    Omkar 6/12/2023 : Changes in xpath for 11.2
//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Costing')]/img")
    @FindBy(xpath = "//div[@id='modelFoldertree-body']//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Costing']/../div[contains(@class,'x-tree-elbow')]")
    private WebElement modelLibraryTreeColumnArrowCosting;
    public WebElement getModelLibraryTreeColumnArrowCosting() {return modelLibraryTreeColumnArrowCosting;}

//    Omkar 6/12/2023 : Changes in xpath for 11.2
//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Contracting')]")
    @FindBy(xpath ="//*[contains(@class,'grid-cell-treecolumn')]//span[contains(text(),'Contracting')]")
    private WebElement modelLibraryTreeColumnContracting;
    public WebElement getModelLibraryTreeColumnContracting() {return modelLibraryTreeColumnContracting;}

//    Omkar 6/12/2023 : Changes in xpath for 11.2
//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Contracting')]/img")
    @FindBy(xpath = "//div[@id='modelFoldertree-body']//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Contracting']/../div[contains(@class,'x-tree-elbow')]")
    private WebElement modelLibraryTreeColumnArrowContracting;
    public WebElement getModelLibraryTreeColumnArrowContracting() {return modelLibraryTreeColumnArrowContracting;}

//    Omkar 6/12/2023 : Changes in xpath for 11.2
//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Episodes')]")
    @FindBy(xpath ="//*[contains(@class,'grid-cell-treecolumn')]//span[contains(text(),'Episodes')]")
    private WebElement modelLibraryTreeColumnEpisodes;
    public WebElement getModelLibraryTreeColumnEpisodes() {return modelLibraryTreeColumnEpisodes;}

//    Omkar 6/12/2023 : Changes in xpath for 11.2
//    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Episodes')]/img")
    @FindBy(xpath = "//div[@id='modelFoldertree-body']//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Episodes']/../div[contains(@class,'x-tree-elbow')]")
    private WebElement modelLibraryTreeColumnArrowEpisodes;
    public WebElement getModelLibraryTreeColumnArrowEpisodes() {return modelLibraryTreeColumnArrowEpisodes;}

    @FindBy(xpath = "//*[@data-qtip = 'First Page']")
    private WebElement modelLibraryTableButtonFirst;
    public WebElement getModelLibraryTableButtonFirst() {return modelLibraryTableButtonFirst;}

    @FindBy(xpath = "//*[@data-qtip = 'Previous Page']")
    private WebElement modelLibraryTableButtonPrevious;
    public WebElement getModelLibraryTableButtonPrevious() {return modelLibraryTableButtonPrevious;}

    @FindBy(xpath = "//*[contains(@name, 'inputItem')]")
    private WebElement modelLibraryFieldInputNumber;
    public WebElement getModelLibraryFieldInputNumber() {return modelLibraryFieldInputNumber;}

    @FindBy(xpath = "//*[contains(@class, 'go-button')]")
    private WebElement modelLibraryTableButtonGo;
    public WebElement getModelLibraryTableButtonGo() {return modelLibraryTableButtonGo;}

    @FindBy(xpath = "//*[@data-qtip = 'Next Page']")
    private WebElement modelLibraryTableButtonNext;
    public WebElement getModelLibraryTableButtonNext() {return modelLibraryTableButtonNext;}

    @FindBy(xpath = "//*[@data-qtip = 'Last Page']")
    private WebElement modelLibraryTableButtonLast;
    public WebElement getModelLibraryTableButtonLast() {return modelLibraryTableButtonLast;}

    @FindBy(xpath = "//div[text()='/ 3']")
    private WebElement modelLibraryTableText3PageShown;
    public WebElement getmodelLibraryTableText3PageShown() {return modelLibraryTableText3PageShown;}
   
    @FindBy(xpath = "//div[text()='/ 4']")
    private WebElement modelLibraryTableText4PageShown;
    public WebElement modelLibraryTableText4PageShown() {return modelLibraryTableText4PageShown;}
    /******End******/

    /******Contracting Library Page******/

//    @FindBy(xpath = "//div[@class='x-toolbar commonTBar x-docked x-toolbar-default x-docked-top x-toolbar-docked-top x-toolbar-default-docked-top x-box-layout-ct x-docked-noborder-right x-docked-noborder-left']//button/span[text()='Calculate']")
   //Shilpa :update Xpath for 11.2  
    @FindBy(xpath = "//div[contains(@class,'x-panel-default-outer-border-rl')]//span/span[text()='Calculate']")

    private WebElement modelLibraryContractingButtonCalculate;
    public WebElement getModelLibraryContractingButtonCalculate() {return modelLibraryContractingButtonCalculate;}

    @FindBy(xpath = "//*[contains(@id,'abutton')]/*[contains(text(),'Copy')]")
    private WebElement modelLibraryContractingButtonCopy;
    public WebElement getModelLibraryContractingButtonCopy() {return modelLibraryContractingButtonCopy;}

    @FindBy(xpath = "//*[contains(text(),'Paste')]")
    private WebElement modelLibraryContractingButtonPaste;
    public WebElement getModelLibraryContractingButtonPaste() {return modelLibraryContractingButtonPaste;}

    @FindBy(xpath = "//*[contains(@id,'abutton')]/*[contains(text(),'Import')]")
    private WebElement modelLibraryContractingButtonImport;
    public WebElement getModelLibraryContractingButtonImport() {return modelLibraryContractingButtonImport;}

    @FindBy(xpath = "//*[contains(@id,'abutton')]/*[contains(text(),'Export')]")
    private WebElement modelLibraryContractingButtonExport;
    public WebElement getModelLibraryContractingButtonExport() {return modelLibraryContractingButtonExport;}

    // ===== Contracting Tab > Contract Models Page > Edit Price dialog ===== //
    @FindBy(xpath = "(//div[text()='Cost Outlier Payment']//following::input[@name='operCostChargeRatio'])[1]")
    private WebElement contractModelsCostOutlierPaymentOperatingRatioOfCostCharge;
    public WebElement getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge() {return contractModelsCostOutlierPaymentOperatingRatioOfCostCharge;}

    @FindBy(xpath = "(//div[text()='Cost Outlier Payment']//following::input[@name='capitalCostChargeRatio'])[1]")
    private WebElement contractModelsCostOutlierPaymentCapitalRatioOfCostCharge;
    public WebElement getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge() {return contractModelsCostOutlierPaymentCapitalRatioOfCostCharge;}

    @FindBy(xpath = "(//div[text()='Cost Outlier Payment']//following::input[@name='paymentPercentage'])[1]")
    private WebElement contractModelsCostOutlierPaymentNonBurnMarginalCostFactor;
    public WebElement getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor() {return contractModelsCostOutlierPaymentNonBurnMarginalCostFactor;}

    @FindBy(xpath = "(//div[text()='Cost Outlier Payment']//following::input[@name='fixedLossThreshold'])[1]")
    private WebElement contractModelsCostOutlierPaymentFixedLossThreshold;
    public WebElement getContractModelsCostOutlierPaymentFixedLossThreshold() {return contractModelsCostOutlierPaymentFixedLossThreshold;}

    @FindBy(xpath = "(//div[text()='Cost Outlier Payment']//following::input[@name='laborPortion'])[1]")
    private WebElement contractModelsCostOutlierPaymentThresholdLaborPortion;
    public WebElement getContractModelsCostOutlierPaymentThresholdLaborPortion() {return contractModelsCostOutlierPaymentThresholdLaborPortion;}


    /******Contracting Library End******/

    /******Model Library Filter ******/

    @FindBy(name = "field")
    private WebElement modelLibraryFilterField;
    public WebElement getModelLibraryFilterField() {return modelLibraryFilterField;}

    @FindBy(name = "operator")
    private WebElement modelLibraryFilterFieldOperator;
    public WebElement getModelLibraryFilterFieldOperator() {return modelLibraryFilterFieldOperator;}

    @FindBy(name = "condition")
    private WebElement modelLibraryFilterFieldCondition;
    public WebElement getModelLibraryFilterFieldCondition() {return modelLibraryFilterFieldCondition;}

    @FindBy(name = "valuefield")
    private WebElement modelLibraryFilterFieldValue;
    public WebElement getModelLibraryFilterFieldValue() {return modelLibraryFilterFieldValue;}

    @FindBy(xpath = "//*[contains(@class,'box-item')]/descendant::span[text()='Add']")
    private WebElement modelLibraryFilterButtonAdd;
    public WebElement getModelLibraryFilterButtonAdd() {return modelLibraryFilterButtonAdd;}

    @FindBy(xpath = "//*[contains(text(),'Apply Filter')]")
    private WebElement modelLibraryFilterButtonApplyFilter;
    public WebElement getModelLibraryFilterButtonApplyFilter() {return modelLibraryFilterButtonApplyFilter;}

    @FindBy(xpath = "//*[contains(text(),'Cancel & Close')]")
    private WebElement modelLibraryFilterButtonCancelAndClose;
    public WebElement getModelLibraryFilterButtonCancelAndClose() {return modelLibraryFilterButtonCancelAndClose;}

    /******Model Library Filter End******/
    /*****Episodes***/
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//div[contains(@class,'x-window-closable ')]//div[contains(@id,'window')]//span[text()='New Episode Model']")
	@FindBy(xpath = "//div[contains(@class,'x-window-closable ')]//div[contains(@id,'window')]//div[text()='New Episode Model']")
    public  WebElement getNewpisodeModelPopUp;
	public  WebElement getNewEpisodeModelPopUp() { return getNewpisodeModelPopUp;}
	
	@FindBy(name = "preAdmitDays")
    private  WebElement preAdmissionPhase;
    public  WebElement getPreAdmissionPhase() {return preAdmissionPhase;}
    
    @FindBy(name = "postDischargeDays")
    private  WebElement preDischargePhase;
    public  WebElement getpreDischargePhase() {return preDischargePhase;}
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "(//span[text()='Triggers']//following::span[text()='Add'])[1]")
    @FindBy(xpath = "(//div[text()='Triggers']//following::span[text()='Add'])[1]")
    private  WebElement triggerAddButton;
    public  WebElement getTriggerAddButton() {return triggerAddButton;}
    
//    @FindBy(xpath = "(//span[text()='Triggers']//following::span[text()='Remove'])[1]")
    //Shilpa update xpath for 11.2 on 11.28.2023
    @FindBy(xpath = "(//div[text()='Triggers']//following::span[text()='Remove'])[1]")
    private  WebElement triggerRemoveButton;
    public  WebElement getTriggerRemoveButton() {return triggerRemoveButton;}
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[text()='Triggers Selector']")
    @FindBy(xpath = "//div[text()='Triggers Selector']")
    private  WebElement triggerSelectorPopUp;
    public  WebElement getTriggerSelectorPopUp() {return triggerSelectorPopUp;}
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(name = "populationId")//input[@name='populationId']
    @FindBy(xpath = "//input[@name='populationId']")
    private static WebElement episodePopulationDropdown;
    public static WebElement getEpisodePopulationDropdown() {return episodePopulationDropdown;}

    @FindBy(xpath = "//label[text()='Share Log in Selected Shared Location']//preceding::input[1]")
    private  WebElement shareLogCheckbox;
    public  WebElement getshareLogCheckbox() {return shareLogCheckbox;}
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//div[text()='Model Episode']/img[3]")
    @FindBy(xpath = "//span[text()='Model Episode']//preceding-sibling::div[contains(@class,'x-tree-elbow-img x-tree-elbow-end')]")
    private  WebElement modelEpisode;
    public  WebElement getModelEpisode() {return modelEpisode;} 
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//*[contains(@id, 'panel')][text()='Triggers']/parent::div/following-sibling::div/img")
    @FindBy(xpath = "//*[contains(@id, 'panel')][text()='Triggers']/parent::div/following-sibling::div")
    private  WebElement openNewSection;
    public  WebElement getOpenNewSection() {return openNewSection;} 
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Save']//parent::button")
    @FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Save']//parent::span")
    private  WebElement SaveButton;
    public  WebElement getSaveButton() {return SaveButton;} 
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Assign']//parent::button")
    @FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Assign']//parent::span")
    private  WebElement AssignButton;
    public  WebElement getAssignButton() {return AssignButton;}
    
    @FindBy(xpath = "(//div[text()='View Log']//following::div[contains(@id,'custompagingtoolbar')]//div[contains(@id,'tbtext')])[2]")
    private  WebElement pagination;
    public  WebElement getpagination() {return pagination;}
    
//    @FindBy(xpath = "//span[contains(@id,'messagebox')]")
    //Shilpa update xpath for 11.2 on 11.28.2023
    @FindBy(xpath = "(//a[contains(@class,'x-toolbar-item')]//following::span[text()='Remove'])[5]")
    private  WebElement warningPopUp;
    public  WebElement getWarningPopUp() {return warningPopUp;}
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[contains(@id,'messagebox')]//following::span[text()='Remove']//parent::button")
    @FindBy(xpath = "//span[contains(@id,'messagebox')]//following::span[text()='Remove']//parent::span")
    private  WebElement RemoveButton;
    public  WebElement getRemoveButton() {return RemoveButton;}
    
//    Omkar 14/04/2023 : xpath changes for 11.2
//    @FindBy(xpath = "//span[contains(@id,'messagebox')]//following::span[text()='Return']//parent::button")
    @FindBy(xpath = "//span[contains(@id,'messagebox')]//following::span[text()='Return']//parent::span")
    private  static WebElement ReturnButton;
    public static WebElement getReturnButton() {return ReturnButton;}
    
    //View Log
    
    @FindBy(xpath = "//div[contains(@id,'window')]//*[contains(@name, 'inputItem')]")
    private  static WebElement getInputNumberPage;
    public static WebElement getInputNumberPage() {return getInputNumberPage;}
    
    @FindBy(xpath = "//div[contains(@id,'window')]//*[contains(@class, 'go-button')]")
    private  static WebElement getGoToPage;
    public static WebElement getGoToPage() {return getGoToPage;}
    
    @FindBy(xpath = "//div[text()='View Log']//following::span[contains(@class,'pagging-tbar-next-button')]/../../..")
    private  static WebElement getGoToNextPage;
    public static WebElement getGoToNextPage() {return getGoToNextPage;}
}
