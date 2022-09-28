package webdriver.maps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;

public class StatusMap extends MapConfig {

    // ===== Status Tab > Calculation Status Page ===== //

   // @FindBy(xpath = "//*[contains(@id,'progressbar')]/div")
    @FindBy(xpath = "//div[@class='x-progress-text x-progress-text-back']")// venkat update xpath
    private WebElement calculationStatusPageFirstRowStatusBar;
    public WebElement getCalculationStatusPageMyStatusFirstRowStatusBarPercentage() {return calculationStatusPageFirstRowStatusBar;}

    @FindBy(xpath = "//*[contains(@onclick, 'statcalfd.htm') and @class='listhelpLnk']")
    private WebElement calculationStatusPageHelpLink;
    public WebElement getCalculationStatusPageHelpLink() {return calculationStatusPageHelpLink;}

    @FindBy(xpath = "//div[contains(@id,'calculationstatus') and contains(@class,'x-box-layout-ct')]/descendant::table/tbody")
    private WebElement calculationStatusPageTableMyStatus;
    public WebElement getCalculationStatusPageTableMyStatus() {return calculationStatusPageTableMyStatus;}

    @FindBy(xpath = "//div[contains(@id,'calculationstatus')]/descendant::div[contains(@id,'rownumberer') and @class='x-column-header-inner']/span[@class='x-column-header-text']")
    private WebElement calculationStatusPageTableCornerCell;
    public WebElement getCalculationStatusPageTableCornerCell() {return calculationStatusPageTableCornerCell;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::table/descendant::input[@name='searchText']")
    private WebElement calculationStatusFormFieldSearch;
    public WebElement getCalculationStatusPageFormFieldSearch() {return calculationStatusFormFieldSearch;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::div[contains(@class, 'statusSearch')]/descendant::span[contains(@id,'button')]")
    private WebElement calculationStatusButtonSearchGlass;
    public WebElement getCalculationStatusPageButtonSearchGlass() {return calculationStatusButtonSearchGlass;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'My Status')]")
    private WebElement calculationStatusPageButtonMyStatus;
    public WebElement getCalculationStatusPageButtonMyStatus() {return calculationStatusPageButtonMyStatus;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'All Status')]")
    private WebElement calculationStatusPageButtonAllStatus;
    public WebElement getCalculationStatusPageButtonAllStatus() {return calculationStatusPageButtonAllStatus;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Refresh')]")
    private WebElement calculationStatusPageButtonRefresh;
    public WebElement getCalculationStatusPageButtonRefresh() {return calculationStatusPageButtonRefresh;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Filter')]")
    private WebElement calculationStatusPageButtonFilter;
    public WebElement getCalculationStatusPageButtonFilter() {return calculationStatusPageButtonFilter;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Clear Filter')]")
    private WebElement calculationStatusPageButtonClearFilter;
    public WebElement getCalculationStatusPageButtonClearFilter() {return calculationStatusPageButtonClearFilter;}

    @FindBy(xpath = "//*[contains(@id,'calculationstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Delete Filtered')]")
    private WebElement calculationStatusPageButtonDeleteFiltered;
    public WebElement getCalculationStatusPageButtonDeleteFiltered() {return calculationStatusPageButtonDeleteFiltered;}

    // ===== End Calculation Status Page ===== //

    // ===== Status Tab >  Status Page ===== //



    // ===== Status Tab > Import/Export Status Page ===== //

    @FindBy(xpath = "//*[contains(@onclick, 'statiefd.htm') and @class='listhelpLnk']")
    private WebElement importexportStatusPageHelpLink;
    public WebElement getImportExportStatusPageHelpLink() {return importexportStatusPageHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::table/descendant::input[@name='searchText']")
    private WebElement importexportStatusFormFieldSearch;
    public WebElement getImportExportStatusPageFormFieldSearch() {return importexportStatusFormFieldSearch;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::div[contains(@class, 'statusSearch')]/descendant::span[contains(@id,'button')]")
    private WebElement importexportStatusButtonSearchGlass;
    public WebElement getImportExportStatusPageButtonSearchGlass() {return importexportStatusButtonSearchGlass;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'My Status')]")
    private WebElement importexportStatusPageButtonMyStatus;
    public WebElement getImportExportStatusPageButtonMyStatus() {return importexportStatusPageButtonMyStatus;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'All Status')]")
    private WebElement importexportStatusPageButtonAllStatus;
    public WebElement getImportExportStatusPageButtonAllStatus() {return importexportStatusPageButtonAllStatus;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Refresh')]")
    private WebElement importexportStatusPageButtonRefresh;
    public WebElement getImportExportStatusPageButtonRefresh() {return importexportStatusPageButtonRefresh;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Filter')]")
    private WebElement importexportStatusPageButtonFilter;
    public WebElement getImportExportStatusPageButtonFilter() {return importexportStatusPageButtonFilter;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Clear Filter')]")
    private WebElement importexportStatusPageButtonClearFilter;
    public WebElement getImportExportStatusPageButtonClearFilter() {return importexportStatusPageButtonClearFilter;}

    @FindBy(xpath = "//*[contains(@id,'importexportstatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Delete Filtered')]")
    private WebElement importexportStatusPageButtonDeleteFiltered;
    public WebElement getImportExportStatusPageButtonDeleteFiltered() {return importexportStatusPageButtonDeleteFiltered;}

    // ===== End Import/Export Status Page ===== //

    // ===== Status Tab > Utility Status Page ===== //

    @FindBy(xpath = "//*[contains(@onclick, 'statumfd.htm') and @class='listhelpLnk']")
    private WebElement utilityStatusPageHelpLink;
    public WebElement getUtilityStatusPageHelpLink() {return utilityStatusPageHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::table/descendant::input[@name='searchText']")
    private WebElement utilityStatusFormFieldSearch;
    public WebElement getUtilityStatusPageFormFieldSearch() {return utilityStatusFormFieldSearch;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::div[contains(@class, 'statusSearch')]/descendant::span[contains(@id,'button')]")
    private WebElement utilityStatusButtonSearchGlass;
    public WebElement getUtilityStatusPageButtonSearchGlass() {return utilityStatusButtonSearchGlass;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::*[contains(text(),'My Status')]")
    private WebElement utilityStatusPageButtonMyStatus;
    public WebElement getUtilityStatusPageButtonMyStatus() {return utilityStatusPageButtonMyStatus;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::*[contains(text(),'All Status')]")
    private WebElement utilityStatusPageButtonAllStatus;
    public WebElement getUtilityStatusPageButtonAllStatus() {return utilityStatusPageButtonAllStatus;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Refresh')]")
    private WebElement utilityStatusPageButtonRefresh;
    public WebElement getUtilityStatusPageButtonRefresh() {return utilityStatusPageButtonRefresh;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Filter')]")
    private WebElement utilityStatusPageButtonFilter;
    public WebElement getUtilityStatusPageButtonFilter() {return utilityStatusPageButtonFilter;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Clear Filter')]")
    private WebElement utilityStatusPageButtonClearFilter;
    public WebElement getUtilityStatusPageButtonClearFilter() {return utilityStatusPageButtonClearFilter;}

    @FindBy(xpath = "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::*[contains(text(),'Delete Filtered')]")
    private WebElement utilityStatusPageButtonDeleteFiltered;
    public WebElement getUtilityStatusPageButtonDeleteFiltered() {return utilityStatusPageButtonDeleteFiltered;}

    // ===== End Utility Status Page ===== //
    @FindBy(xpath = "//span[text()=\"Add\"]")
    private WebElement statusFilterDialogButtonAdd;
    public WebElement getStatusFilterDialogButtonAdd() {return statusFilterDialogButtonAdd;}

    @FindBy(xpath = "//span[@class=\"x-btn-inner\"][text()=\"Add Value\"]")
    private WebElement statusFilterDialogButtonAddValue;
    public WebElement getStatusFilterDialogButtonAddValue() {return statusFilterDialogButtonAddValue;}
    // ===== End  Status Page > Filter  Status Dialog ===== //
}

//    // ===== Status Tab >  Status Page ===== //
//    @FindBy(name = "searchText")
//    private WebElement formFieldSearch;
//    public WebElement getFormFieldSearch() {return formFieldSearch;}
//
//    @FindBy(xpath = "//*[contains(@class,'statusSearch')]")
//    private WebElement buttonSearchGlass;
//    public WebElement getButtonSearchGlass() {return buttonSearchGlass;}
//
//    @FindBy(xpath = "//*[contains(text(),'My Status')]")
//    private WebElement statusPageButtonMyStatus;
//    public WebElement getStatusPageButtonMyStatus() {return statusPageButtonMyStatus;}
//
//    @FindBy(xpath = "//*[contains(text(),'All Status')]")
//    private WebElement statusPageButtonAllStatus;
//    public WebElement getStatusPageButtonAllStatus() {return statusPageButtonAllStatus;}
//
//    @FindBy(xpath = "//*[contains(text(),'Filter')]")
//    private WebElement statusPageButtonFilter;
//    public WebElement getStatusPageButtonFilter() {return statusPageButtonFilter;}
//
//    @FindBy(xpath = "//span[contains(text(),'Refresh')]")
//    private WebElement statusPageButtonRefresh;
//    public WebElement getStatusPageButtonRefresh() {return statusPageButtonRefresh;}
//
//    @FindBy(name = "searchText")
//    private WebElement statusPageFieldSearch;
//    public WebElement getStatusPageFieldSearch() {return statusPageFieldSearch;}
//
//    @FindBy(xpath = "//*[contains(@class,'statusSearch')]")
//    private WebElement statusPageButtonSearch;
//    public WebElement getStatusPageButtonSearchGlass() {return statusPageButtonSearch;}
//
//    @FindBy(xpath = "//*[contains(text(),'Clear Filter')]")
//    private WebElement statusPageButtonClearFilter;
//    public WebElement getStatusPageButtonClearFilter() {return statusPageButtonClearFilter;}
//
//    @FindBy(xpath = "//*[contains(text(),'Delete Filtered')]")
//    private WebElement statusPageButtonDeleteFiltered;
//    public WebElement getStatusPageButtonDeleteFiltered() {return statusPageButtonDeleteFiltered;}
//
//// ===== End  Status Page ===== //
//
//    // ===== Status Tab >  Status Page > Filter Dialog ===== //
//
//    @FindBy(name = "field")
//    private WebElement statusFilterDialogDropdownField;
//    public WebElement getStatusFilterDialogDropdownField() {return statusFilterDialogDropdownField;}
//
//    @FindBy(name = "operator")
//    private WebElement statusFilterDialogDropdownOperator;
//    public WebElement getStatusFilterDialogDropdownOperator() {return statusFilterDialogDropdownOperator;}
//
//    @FindBy(name = "condition")
//    private WebElement statusFilterDialogDropdownCondition;
//    public WebElement getStatusFilterDialogDropdownCondition() {return statusFilterDialogDropdownCondition;}
//
//    @FindBy(name = "valuefield")
//    private WebElement statusFilterDialogFieldValue;
//    public WebElement getStatusFilterDialogFieldValue() {return statusFilterDialogFieldValue;}
//
//    @FindBy(xpath = "//span[contains(text(),'Add')]")
//    private WebElement statusFilterDialogButtonAdd;
//    public WebElement getStatusFilterDialogButtonAdd() {return statusFilterDialogButtonAdd;}

//    @FindBy(xpath = "//*[contains(text(),'Apply Filter')]")
//    private WebElement statusFilterDialogButtonApplyFilter;
//    public WebElement getStatusFilterDialogButtonApplyFilter() {return statusFilterDialogButtonApplyFilter;}
//
//    @FindBy(xpath = "//*[contains(text(),'Cancel & Close')]")
//    private WebElement statusFilterDialogButtonCancelAndClose;
//    public WebElement getStatusFilterDialogButtonCancelAndClose() {return statusFilterDialogButtonCancelAndClose;}
//
//// ===== End  Status Page > Filter  Status Dialog ===== //