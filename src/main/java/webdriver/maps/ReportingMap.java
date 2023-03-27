package webdriver.maps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import webdriver.maps.mapbuilder.MapConfig;

public class ReportingMap extends MapConfig {

    public String reportingTabXpathDateMaintenancePage = "//*[contains(@id,'reporting_reportdatemaintenance_tabId')]";
    public String reportingTabXpathMenuMaintenancePage = "//*[contains(@id, 'reporting_reportmenumaintenance_tabId')]";

    // ===== Reporting Tab > Report Library Page ===== //

    @FindBy(xpath = "//td[@align='left']/*[text()='Standard and Flexible Report Library']")
    private WebElement reportLibraryPageName;
    public WebElement getReportLibraryPageName() {return reportLibraryPageName;}

    @FindBy(xpath = "//div[@id='header']/descendant::a[text()='Help']")
    private WebElement reportLibraryPageHelpLink;
    public WebElement getReportLibraryPageHelpLink() {return reportLibraryPageHelpLink;}

    @FindBy(xpath = "//following-sibling::input[@type='text']")
    private WebElement reportLibraryPageFormFieldSearch;
    public WebElement getReportLibraryPageFormFieldSearch() {return reportLibraryPageFormFieldSearch;}

    @FindBy(xpath = "//button[text()='Rename']")
    private WebElement reportLibraryPageButtonRename;
    public WebElement getReportLibraryPageButtonRename() {return reportLibraryPageButtonRename;}

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement reportLibraryPageButtonDelete;
    public WebElement getReportLibraryPageButtonDelete() {return reportLibraryPageButtonDelete;}

    @FindBy(xpath = "//a[text()='Report Status']")
    private WebElement reportLibraryPageSectionHeaderReportStatus;
    public WebElement getReportLibraryPageSectionHeaderReportStatus() {return reportLibraryPageSectionHeaderReportStatus;}

    @FindBy(xpath = "//div[contains(@class,'gwtQuery-draggable')]/a[@title]")
    private WebElement reportLibraryPageSectionReportStatusFirstReportListed;
    public WebElement getReportLibraryPageSectionReportStatusFirstReportListed() {return reportLibraryPageSectionReportStatusFirstReportListed;}

    @FindBy(xpath = "//th[@colspan='1']/div/div[text()='Name']")
    private WebElement reportLibraryPageSectionReportStatusTableHeaderName;
    public WebElement getReportLibraryPageSectionReportStatusTableHeaderName() {return reportLibraryPageSectionReportStatusTableHeaderName;}

    @FindBy(xpath = "//td[@align='left']/*[text()='Expand All']")
    private WebElement reportLibraryPageLinkExpandAll;
    public WebElement getReportLibraryPageLinkExpandAll() {return reportLibraryPageLinkExpandAll;}

    @FindBy(xpath = "//td[@align='left']/*[text()='Collapse All']")
    private WebElement reportLibraryPageLinkCollapseAll;
    public WebElement getReportLibraryPageLinkCollapseAll() {return reportLibraryPageLinkCollapseAll;}

    @FindBy(xpath = "//*[contains(text(),'Templates')]/ancestor::td/preceding-sibling::td/img")
    private WebElement reportLibraryPageExpanderTemplates;
    public WebElement getReportLibraryPageExpandTemplates() {return reportLibraryPageExpanderTemplates;}

    @FindBy(xpath = "//*[contains(text(),'Reports')]/ancestor::td/preceding-sibling::td/img")
    private WebElement reportLibraryPageExpanderReports;
    public WebElement getReportLibraryPageExpandReports() {return reportLibraryPageExpanderReports;}

    @FindBy(xpath = "//*[contains(text(),'Folders')]/ancestor::td/preceding-sibling::td/img")
    private WebElement reportLibraryPageExpanderFolders;
    public WebElement getReportLibraryPageExpandFolders() {return reportLibraryPageExpanderFolders;}
    
    @FindBy(xpath = "//div[text()='Entity Range']//following::a[2]/parent::div")
    private WebElement reportLibraryPageEntityRange;
    public WebElement reportLibraryPageEntityRange() {return reportLibraryPageEntityRange;}
    
    @FindBy(xpath = "//input[@class='GJT013UBGIC']")
    private WebElement reportLibraryPageEntitySearch;
    public WebElement reportLibraryPageEntitySearch() {return reportLibraryPageEntitySearch;}
    
    @FindBy(xpath = "//table[@class='GJT013UBAIC']//select[@class='gwt-ListBox']")
    private WebElement reportLibraryPageEntitySelectDropdown;
    public WebElement reportLibraryPageEntitySelectDropdown() {return reportLibraryPageEntitySelectDropdown;}
    
    @FindBy(xpath = "//button[text()='OK']")
    private WebElement reportLibraryPageEntityOkButton;
    public WebElement reportLibraryPageEntityOkButton() {return reportLibraryPageEntityOkButton;}
    
    @FindBy(xpath = "//div[contains(@class,'dialogMiddleCenterInner')]//following::button[text()='Cancel']")
    private WebElement reportLibraryPageEntityCancelButton;
    public WebElement reportLibraryPageEntityCancelButton() {return reportLibraryPageEntityCancelButton;}
    
    @FindBy(xpath = "//button[text()='Run']")
    private WebElement reportLibraryPageEntityRunButton;
    public WebElement reportLibraryPageEntityRunButton() {return reportLibraryPageEntityRunButton;}
    
    @FindBy(xpath = "//button[text()='Refresh']")
    private WebElement reportLibraryPageEntityRefreshButton;
    public WebElement reportLibraryPageEntityRefreshButton() {return reportLibraryPageEntityRefreshButton;}
    
    @FindBy(xpath = "//button[text()='Save As']")
    private WebElement reportLibraryPageEntitySaveAsButton;
    public WebElement reportLibraryPageEntitySaveAsButton() {return reportLibraryPageEntitySaveAsButton;}
    
    @FindBy(xpath = "//div[text()='Department Hierarchy']//following::a[1]")
    private WebElement reportLibraryPageDeptHierarchy;
    public WebElement reportLibraryPageDeptHierarchy() {return reportLibraryPageDeptHierarchy;}
    
    @FindBy(xpath = "//div[text()='Department or Department Group']//following::a[1]//parent::div")
    private WebElement reportLibraryPageDeptGrp;
    public WebElement reportLibraryPageDeptGrp() {return reportLibraryPageDeptGrp;}
    
    @FindBy(xpath = "//div[@class='Caption'][text()='Select: Contract Names']")
    private WebElement reportContractNamePopUp;
    public WebElement reportContractNamePopUp() {return reportContractNamePopUp;}
    
    @FindBy(xpath = "//div[@class='Caption'][text()='Select: Care Delivery Facilities']")
    private WebElement reportCareDeliveryFacilitiesPopUp;
    public WebElement reportCareDeliveryFacilitiesPopUp() {return reportCareDeliveryFacilitiesPopUp;}
    
    @FindBy(xpath = "//div[text()='Contract Names']//following::a[1]/u")
    private WebElement reportContractNameLink;
    public WebElement reportContractNameLink() {return reportContractNameLink;}
    
    @FindBy(xpath = "//div[text()='Name']")
    private WebElement reportColumnName;
    public WebElement reportColumnName() {return reportColumnName;}
    
    @FindBy(xpath = "//div[@class='GJT013UBH']//following::table//a")
    private List<WebElement> reportTableElementList;
    public List<WebElement> reportTableElementList() {return reportTableElementList;}
    
    @FindBy(xpath = "//div[@id='ListingURE_detailView_listColumn_8_0_1']")
    private WebElement reportConversionTool;
    public WebElement reportConversionTool() {return reportConversionTool;}
    
    @FindBy(xpath = "//div[@id='ListingURE_detailView_listColumn_0_0_1']")
    private WebElement reportConversionToolDoc;
    public WebElement reportConversionToolDoc() {return reportConversionToolDoc;}
    
    @FindBy(xpath = "//*[@id='Btn_rValuesOKButton']")
    private WebElement reportToolOkBtn;
    public WebElement reportToolOkBtn() {return reportToolOkBtn;}
    
    @FindBy(xpath = "//div[@id='pageContainer']//following::img")
    private WebElement reportToolPieChart;
    public WebElement reportToolPieChart() {return reportToolPieChart;}

    // ===== End Report Library Page ===== //

    // ===== Reporting Tab > Report Menu Maintenance Page ===== //

    @FindBy(xpath = "//*[contains(@class,'areaTitle')]/*[text()='Report Menu Items']")
    private WebElement reportingTabReportMenuMaintenancePageName;
    public WebElement getReportingTabReportMenuMaintenancePageName() {return reportingTabReportMenuMaintenancePageName;}

    @FindBy(xpath = "//*[contains(@onclick,'rmils.htm') and @class='listhelpLnk']")
    private WebElement reportingTabReportMenuMaintenancePageHelpLink;
    public WebElement getReportingTabReportMenuMaintenancePageHelpLink() {return reportingTabReportMenuMaintenancePageHelpLink;}

    @FindBy(xpath = "//div[contains(@id,'reporting_reportmenumaintenance')]/descendant::div[contains(@id,'rownumberer')]/div")
    private WebElement reportingTabReportMenuMaintenancePageTableCornerCell;
    public WebElement getReportingTabReportMenuMaintenancePageTableCornerCell() {return reportingTabReportMenuMaintenancePageTableCornerCell;}

    // ===== Reporting Tab > Report Date Maintenance Page ===== //

    @FindBy(xpath = "//*[contains(@id,'header') and text()='Dates']")
    private WebElement getReportingTabReportDateMaintenancePageSectionHeaderDates;
    public WebElement getReportingTabReportDateMaintenancePageSectionHeaderDates() {return getReportingTabReportDateMaintenancePageSectionHeaderDates;}

    @FindBy(xpath = "//*[contains(@onclick,'srdatefd.htm') and @class='listhelpLnk']")
    private WebElement reportingTabReportDateMaintenancePageHelpLink;
    public WebElement getReportingTabReportDateMaintenancePageHelpLink() {return reportingTabReportDateMaintenancePageHelpLink;}

    @FindBy(xpath = "//*[contains(@id,'reporting_reportdatemaintenance_tabId')]/descendant::span[text()='Save']")
    private WebElement reportingTabReportDateMaintenancePageButtonSave;
    public WebElement getReportingTabReportDateMaintenancePageButtonSave() {return reportingTabReportDateMaintenancePageButtonSave;}

    @FindBy(name = "currentDate")
    private WebElement reportingTabReportDateMaintenancePageFieldCurrentDate;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldCurrentDate() {return reportingTabReportDateMaintenancePageFieldCurrentDate;}

    @FindBy(name = "currentMonthYear")
    private WebElement reportingTabReportDateMaintenancePageComboBoxCurrentMonthYear;
    public WebElement getReportingTabReportDateMaintenancePageComboBoxCurrentMonthYear() {return reportingTabReportDateMaintenancePageComboBoxCurrentMonthYear;}

    @FindBy(name = "fiscalYearEndMonth")
    private WebElement reportingTabReportDateMaintenancePageComboBoxFiscalYearEndMonth;
    public WebElement getReportingTabReportDateMaintenancePageComboBoxFiscalYearEndMonth() {return reportingTabReportDateMaintenancePageComboBoxFiscalYearEndMonth;}

    //General Sub-tab

    @FindBy(name = "consumerHistoryReferenceDate")
    private WebElement reportingTabReportDateMaintenancePageDateFieldConsumerHistoryReferenceDate;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldConsumerHistoryReferenceDate() {return reportingTabReportDateMaintenancePageDateFieldConsumerHistoryReferenceDate;}

    @FindBy(name = "encounterAdmitDateRangeStart")
    private WebElement reportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeStart() {return reportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeStart;}

    @FindBy(name = "encounterAdmitDateRangeEnd")
    private WebElement reportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeEnd() {return reportingTabReportDateMaintenancePageDateFieldEncounterAdmitDateRangeEnd;}

    @FindBy(name = "encounterDischargeDateRangeStart")
    private WebElement reportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeStart() {return reportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeStart;}

    @FindBy(name = "encounterDischargeDateRangeEnd")
    private WebElement reportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeEnd() {return reportingTabReportDateMaintenancePageDateFieldEncounterDischargeDateRangeEnd;}

    //Costing Sub-tab

    @FindBy(name = "adjustmentAndReclassificationDateRangeStart")
    private WebElement adjustmentAndReclassificationDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldAdjustmentAndReclassificationDateRangeStart() {return adjustmentAndReclassificationDateRangeStart;}

    @FindBy(name = "adjustmentAndReclassificationDateRangeEnd")
    private WebElement adjustmentAndReclassificationDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldAdjustmentAndReclassificationDateRangeEnd() {return adjustmentAndReclassificationDateRangeEnd;}

    @FindBy(name = "chargeableActivityDataItemDateRangeStart")
    private WebElement chargeableActivityDataItemDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldChargeableActivityDataItemDateRangeStart() {return chargeableActivityDataItemDateRangeStart;}

    @FindBy(name = "chargeableActivityDataItemDateRangeEnd")
    private WebElement chargeableActivityDataItemDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldChargeableActivityDataItemDateRangeEnd() {return chargeableActivityDataItemDateRangeEnd;}

    @FindBy(name = "costItemDataSetDateRangeStart")
    private WebElement costItemDataSetDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldCostItemDataSetDateRangeStart() {return costItemDataSetDateRangeStart;}

    @FindBy(name = "costItemDataSetDateRangeEnd")
    private WebElement costItemDataSetDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldCostItemDataSetDateRangeEnd() {return costItemDataSetDateRangeEnd;}

    @FindBy(name = "costItemDataSetDateRangeStart")
    private WebElement costItemDataSetMonthYearRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageComboBoxCostItemDataSetMonthYearRangeStart() {return costItemDataSetMonthYearRangeStart;}

    @FindBy(name = "costItemDataSetMonthYearRangeEnd")
    private WebElement costItemDataSetMonthYearRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageComboBoxCostItemDataSetMonthYearRangeEnd() {return costItemDataSetMonthYearRangeEnd;}

    @FindBy(name = "costingActivityStatisticDateRangeStart")
    private WebElement costingActivityStatisticDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldCostingActivityStatisticDateRangeStart() {return costingActivityStatisticDateRangeStart;}

    @FindBy(name = "costingActivityStatisticDateRangeEnd")
    private WebElement costingActivityStatisticDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldCostingActivityStatisticDateRangeEnd() {return costingActivityStatisticDateRangeEnd;}

    @FindBy(name = "glDataItemDateRangeStart")
    private WebElement glDataItemDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldGlDataItemDateRangeStart() {return glDataItemDateRangeStart;}

    @FindBy(name = "glDataItemDateRangeEnd")
    private WebElement glDataItemDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldGlDataItemDateRangeEnd() {return glDataItemDateRangeEnd;}

    @FindBy(name = "overheadModelScenarioDateRangeStart")
    private WebElement overheadModelScenarioDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldOverheadModelScenarioDateRangeStart() {return overheadModelScenarioDateRangeStart;}

    @FindBy(name = "overheadModelScenarioDateRangeEnd")
    private WebElement overheadModelScenarioDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldOverheadModelScenarioDateRangeEnd() {return overheadModelScenarioDateRangeEnd;}

    @FindBy(name = "overheadTransactionDateRangeStart")
    private WebElement overheadTransactionDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldOverheadTransactionDateRangeStart() {return overheadTransactionDateRangeStart;}

    @FindBy(name = "overheadTransactionDateRangeEnd")
    private WebElement overheadTransactionDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldOverheadTransactionDateRangeEnd() {return overheadTransactionDateRangeEnd;}

    @FindBy(name = "rvuEffectiveDate")
    private WebElement rvuEffectiveDate;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldRvuEffectiveDate() {return rvuEffectiveDate;}

    //Contracting and AR Sub-tab

    @FindBy(name = "billDropDateRangeStart")
    private WebElement billDropDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldBillDropDateRangeStart() {return billDropDateRangeStart;}

    @FindBy(name = "billDropDateRangeEnd")
    private WebElement billDropDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldBillDropDateRangeEnd() {return billDropDateRangeEnd;}

    @FindBy(name = "contractStartDateRangeStart")
    private WebElement contractStartDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldContractStartDateRangeStart() {return contractStartDateRangeStart;}

    @FindBy(name = "contractStartDateRangeEnd")
    private WebElement contractStartDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldContractStartDateRangeEnd() {return contractStartDateRangeEnd;}

    @FindBy(name = "contractExpirationDateRangeStart")
    private WebElement contractExpirationDateRangeStart;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldContractExpirationDateRangeStart() {return contractExpirationDateRangeStart;}

    @FindBy(name = "contractExpirationDateRangeEnd")
    private WebElement contractExpirationDateRangeEnd;
    public WebElement getReportingTabReportDateMaintenancePageDateFieldContractExpirationDateRangeEnd() {return contractExpirationDateRangeEnd;}

    // ===== Reporting Tab > Gems Inquiry Page ===== //

    @FindBy(xpath = "//td/a[text()='Help']")
    private WebElement reportingTabGemsInquiryPageHelpLink;
    public WebElement getReportingTabGemsInquiryPageHelpLink() {return reportingTabGemsInquiryPageHelpLink;}

    @FindBy(xpath = "//div[text()='GEMs Inquiry']")
    private WebElement reportingTabGemsInquiryPageName;
    public WebElement getReportingTabGemsInquiryPageName() {return reportingTabGemsInquiryPageName;}

    // ===== Reporting Tab > Gems Analysis Page ===== //

    @FindBy(xpath = "//td/a[text()='Help']")
    private WebElement reportingTabGemsAnalysisPageHelpLink;
    public WebElement getReportingTabGemsAnalysisPageHelpLink() {return reportingTabGemsAnalysisPageHelpLink;}

    @FindBy(xpath = "//div[text()='GEMs Analysis']")
    private WebElement reportingTabGemsAnalysisPageName;
    public WebElement getReportingTabGemsAnalysisPageName() {return reportingTabGemsAnalysisPageName;}





    // ===== Reporting Tab > Ad Hoc Report Design Page ===== //

    @FindBy(xpath = "//*[contains(@onclick, 'stsccnfd.htm') and @class='listhelpLnk']")
    private WebElement adHocReportDesignLinkHelp;
    public WebElement getAdHocReportDesignLinkHelp() {return adHocReportDesignLinkHelp;}

    @FindBy(xpath = "//*[contains(@id, 'reporting_designadhocreports')]/descendant::span[text()='New']")
    private WebElement adHocReportDesignPageButtonNew;
    public WebElement getAdHocReportDesignPageButtonNew() {return adHocReportDesignPageButtonNew;}

    @FindBy(xpath = "//*[contains(@id, 'reporting_designadhocreports')]/descendant::span[text()='Continue']")
    private WebElement adHocReportDesignPageButtonContinue;
    public WebElement getAdHocReportDesignPageButtonContinue() {return adHocReportDesignPageButtonContinue;}

    @FindBy(xpath = "//*[contains(@id, 'reporting_designadhocreports')]/descendant::span[text()='Close']")
    private WebElement adHocReportDesignPageButtonClose;
    public WebElement getAdHocReportDesignPageButtonClose() {return adHocReportDesignPageButtonClose;}

    // ===== End Ad Hoc Report Design Page ===== //
}
