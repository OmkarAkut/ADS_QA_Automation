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

    @FindBy(xpath = "//label[contains(@class,'LablAlgn-left')]//following::input[1]") 
//    @FindBy(xpath = "//input[@name='searchText']") //Shilpa update xpath 31.10.2022
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

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Costing')]")
    private WebElement modelLibraryTreeColumnCosting;
    public WebElement getModelLibraryTreeColumnCosting() {return modelLibraryTreeColumnCosting;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Costing')]/img")
    private WebElement modelLibraryTreeColumnArrowCosting;
    public WebElement getModelLibraryTreeColumnArrowCosting() {return modelLibraryTreeColumnArrowCosting;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Contracting')]")
    private WebElement modelLibraryTreeColumnContracting;
    public WebElement getModelLibraryTreeColumnContracting() {return modelLibraryTreeColumnContracting;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Contracting')]/img")
    private WebElement modelLibraryTreeColumnArrowContracting;
    public WebElement getModelLibraryTreeColumnArrowContracting() {return modelLibraryTreeColumnArrowContracting;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Episodes')]")
    private WebElement modelLibraryTreeColumnEpisodes;
    public WebElement getModelLibraryTreeColumnEpisodes() {return modelLibraryTreeColumnEpisodes;}

    @FindBy(xpath = "//*[contains(@class,'grid-cell-treecolumn')]/*[contains(text(),'Episodes')]/img")
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

    /******End******/

    /******Contracting Library Page******/

    @FindBy(xpath = "//button/span[text()='Calculate']")
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
    @FindBy(name = "operCostChargeRatio")
    private WebElement contractModelsCostOutlierPaymentOperatingRatioOfCostCharge;
    public WebElement getContractModelsCostOutlierPaymentOperatingRatioOfCostCharge() {return contractModelsCostOutlierPaymentOperatingRatioOfCostCharge;}

    @FindBy(name = "capitalCostChargeRatio")
    private WebElement contractModelsCostOutlierPaymentCapitalRatioOfCostCharge;
    public WebElement getContractModelsCostOutlierPaymentCapitalRatioOfCostCharge() {return contractModelsCostOutlierPaymentCapitalRatioOfCostCharge;}

    @FindBy(name = "paymentPercentage")
    private WebElement contractModelsCostOutlierPaymentNonBurnMarginalCostFactor;
    public WebElement getContractModelsCostOutlierPaymentNonBurnMarginalCostFactor() {return contractModelsCostOutlierPaymentNonBurnMarginalCostFactor;}

    @FindBy(name = "fixedLossThreshold")
    private WebElement contractModelsCostOutlierPaymentFixedLossThreshold;
    public WebElement getContractModelsCostOutlierPaymentFixedLossThreshold() {return contractModelsCostOutlierPaymentFixedLossThreshold;}

    @FindBy(name = "laborPortion")
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

}
