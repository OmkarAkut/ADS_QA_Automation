package webdriver.maps;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webdriver.maps.mapbuilder.MapConfig;


public class CimMap extends MapConfig {

	/****** CIM >  ******/

	@FindBy(xpath = "//*[contains(@class,'bubbleQuickLinks')]/descendant::a[contains(text(),'Cost Integration Manager (CIM)')]")
	private WebElement cimQuickLink;

	public WebElement getcimQuickLink() {
		return cimQuickLink;
	}
	@FindBy(xpath = "//div[contains(@id,'costing_costintegrationmanager_tabId-bodyWrap')]")
	private WebElement cimHeader;

	public WebElement getcimHeader() {
		return cimHeader;
	}
	@FindBy(xpath = "//span[contains(@id,'tab') and contains(text(),' Close Cost Integration Manager (CIM)')]")
	private WebElement cimDockItem;

	public WebElement getcimDockItem() {
		return cimDockItem;
	}
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='New']")
	private WebElement cimNewBtn;

	public WebElement getcimNewBtn() {
		return cimNewBtn;
	}
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Edit']")
	private WebElement cimEditBtn;
	public WebElement getcimEditBtn() {return cimEditBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Calculation Options']")
	private WebElement cimCalculateBtn;
	public WebElement getcimCalculateBtn() {return cimCalculateBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'costintegrationmanagerform')]//span[text()='Calculation Options']")
	private List<WebElement> cimCalculateBtnEditMode;
	public List<WebElement> getcimCalculateBtnEditMode() {return cimCalculateBtnEditMode;}
	
	@FindBy(xpath = "//span[text()='Calculate Now']/ancestor::a")
	private WebElement cimCalculateNowButton;
	public WebElement getcimCalculateNowButton() {return cimCalculateNowButton;}
	
	@FindBy(xpath = "(//a[contains(@class,'CIMButtonOptions ')]/span)[1]")
	private WebElement cimCalculatePreset1Btn;
	public WebElement getcimCalculatePreset1Btn() {return cimCalculatePreset1Btn;}
	
	@FindBy(xpath = "(//a[contains(@class,'CIMButtonOptions ')]/span)[2]")
	private WebElement cimCalculatePreset2Btn;
	public WebElement getcimCalculatePreset2Btn() {return cimCalculatePreset2Btn;}
	
	@FindBy(xpath = "(//a[contains(@class,'CIMButtonOptions ')]/span)[3]")
	private WebElement cimCalculatePreset3Btn;
	public WebElement getcimCalculatePreset3Btn() {return cimCalculatePreset3Btn;}
	
	@FindBy(xpath = "//span[text()='Calculation Options']/ancestor::a")
	private WebElement cimCalculateOptionBtn;
	public WebElement getcimCalculateOptionBtn() {return cimCalculateOptionBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Refresh']")
	private WebElement cimRefreshBtn;
	public WebElement getcimRefreshBtn() {return cimRefreshBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='View']")
	private WebElement cimViewBtn;
	public WebElement getcimViewBtn() {return cimViewBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//a[text()='Calculation Status Screen']")
	private WebElement cimCalcStatusSreenBtn;
	public WebElement getcimCalcStatusSreenBtn() {return cimCalcStatusSreenBtn;}
	
	@FindBy(name = "name")
	private static WebElement cimName;
	public WebElement getcimName() {return cimName;}
	
	@FindBy(name = "description")
	private static WebElement cimDesc;
	public WebElement getcimDesc() {return cimDesc;}
	
	@FindBy(name = "description")
	private static WebElement cimDescription;
	public WebElement getcimDescription() {return cimDescription;}
	
	@FindBy(name = "calculationType")
	private static WebElement cimCalcTypeDrpdwn;
	public WebElement getcimCalcTypeDrpdwn() {return cimCalcTypeDrpdwn;}
	
	@FindBy(name = "recurrenceInterval")
	private static WebElement cimRecurringDrpdwn;
	public WebElement getcimRecurringDrpdwn() {return cimRecurringDrpdwn;}
	
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='<All>']/../li")
	private static List<WebElement> cimCalcTypeDrpdwnValues;
	public List<WebElement> getcimCalcTypeDrpdwnValues() {return cimCalcTypeDrpdwnValues;}
	
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='Does not repeat']/../li")
	private static List<WebElement> cimRecurringValues;
	public List<WebElement> getcimRecurringValues() {return cimRecurringValues;}
	
	@FindBy(xpath = "//div[contains(@class,'x-toolbar-footer')]//span[text()='Calculation Options']/ancestor::a")
	private static WebElement cimCalculationOption;
	public WebElement getcimCalculationOption() {return cimCalculationOption;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//span[text()='Save & Create New']/ancestor::a)[1]")
	private static WebElement cimSaveCreateNewBtn;
	public WebElement getcimSaveCreateNewBtn() {return cimSaveCreateNewBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//span[text()='Save']/ancestor::a)[1]")
	private static WebElement cimSaveBtn;
	public WebElement getcimSave() {return cimSaveBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//span[text()='Cancel & Close']/ancestor::a)[1]")
	private static WebElement cimCancelCloseBtn;
	public WebElement getcimCancelCloseBtn() {return cimCancelCloseBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//span[text()='Save & Close']/ancestor::a)[1]")
	private static WebElement cimSaveCloseBtn;
	public WebElement getcimSaveCloseBtn() {return cimSaveCloseBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'hierarchyGrid ')])[1]//div/table//div")
	private static List<WebElement> cimAvailablePanel;
	public List<WebElement> getcimAvailablePanel() {return cimAvailablePanel;}
	

	@FindBy(name = "scenarioSearchStr")
	private static WebElement cimScenarioSearchInput;
	public WebElement getcimScenarioSearchInput() {return cimScenarioSearchInput;}
	
	@FindBy(xpath = "//a[contains(@class,'statusSearch ')]")
	private static WebElement searchIcon;
	public WebElement getsearchIcon() {return searchIcon;}
	
	@FindBy(xpath = "//span[text()='Select']/ancestor::a")
	private static WebElement calcTypeSelectBtn;
	public WebElement getcalcTypeSelectBtn() {return calcTypeSelectBtn;}
	
	@FindBy(xpath = "(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div")
	private static List<WebElement> cimSelectedPanel;
	public List<WebElement> getcimSelectedPanel() {return cimSelectedPanel;}
	
	@FindBy(xpath = "//a[contains(@class,'upArrowBtn ')]")
	private static WebElement cimUpArrowBtn;
	public WebElement getcimUpArrowBtn() {return cimUpArrowBtn;}
	
	@FindBy(xpath = "//a[contains(@class,'downArrowBtn ')]")
	private static WebElement cimDownArrowBtn;
	public WebElement getcimDownArrowBtn() {return cimDownArrowBtn;}
	
	@FindBy(xpath = "//span[text()='Select All']/ancestor::a")
	private static WebElement calcTypeSelectAllBtn;
	public WebElement getcalcTypeSelectAllBtn() {return calcTypeSelectAllBtn;}
	
	@FindBy(xpath = "//span[text()='Remove All']/ancestor::a")
	private static WebElement calcTypeRemoveAllBtn;
	public WebElement getcalcTypeRemoveAllBtn() {return calcTypeRemoveAllBtn;}
	
	@FindBy(xpath = "//span[text()='Remove']/ancestor::a")
	private static WebElement calcTypeRemoveBtn;
	public WebElement getcalcTypeRemoveBtn() {return calcTypeRemoveBtn;}
	
	@FindBy(id = "availableCountLabel")
	private static WebElement availableCountLabel;
	public WebElement getavailableCountLabel() {return availableCountLabel;}
	
	@FindBy(id = "selectedCountLabel")
	private static WebElement selectedCountLabel;
	public WebElement getselectedCountLabel() {return selectedCountLabel;}
	
	@FindBy(xpath = "//div[text()='Warning']//following::span[text()='Cancel & Close']/ancestor::a")
	private static WebElement cancelCloseWarningBtn;
	public WebElement getcancelCloseWarningBtn() {return cancelCloseWarningBtn;}
	
	@FindBy(xpath = "(//div[contains(text(),'Calculation Options')]//following::a[contains(@class,'x-btn windowbtn')]//span[text()='Cancel & Close'])[2]")
	private static WebElement calcCancelCloseBtn;
	public WebElement getcalcCancelCloseBtn() {return calcCancelCloseBtn;}
	
	@FindBy(xpath = "((//div[contains(@class,'x-grid-item-container')])//td/div)[2]")
	private static WebElement cimFormFirstEle;
	public WebElement getcimFormFirstEle() {return cimFormFirstEle;}
	
	@FindBy(name = "hostLocation")
	private static WebElement cimhostLocation;
	public WebElement gethostLocation() {return cimhostLocation;}
	
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='<SFTP_SERVER>/PATH/TO/CALC_LOGS_SHARED_DIRECTORY1/']")
	private static WebElement cimSharedLoc;
	public WebElement getcimSharedLoc() {return cimSharedLoc;}
	
	@FindBy(name = "shareLog")
	private WebElement cimShareLogCheckbox;
	public WebElement getcimShareLogCheckbox() {return cimShareLogCheckbox;}
	
	@FindBy(name = "logLocation")
	private static WebElement cimLocation;
	public WebElement getcimLocation() {return cimLocation;}
	
	@FindBy(xpath = "//span[text()='Filter']/ancestor::div[1]")
	private static WebElement cimFilterButton;
	public WebElement getcimFilterButton() {return cimFilterButton;}
	
	@FindBy(xpath = "//span[text()='Clear Filter']/ancestor::div[1]")
	private static WebElement cimClearFilterButton;
	public WebElement getcimClearFilterButton() {return cimClearFilterButton;}
	
	@FindBy(xpath = "//span[text()='Delete']/ancestor::div[1]")
	private static WebElement cimDeleteButton;
	public WebElement getcimDeleteButton() {return cimDeleteButton;}
	
	@FindBy(xpath = "//h1[contains(text(),'Warning')]//following::span[text()='Delete']/ancestor::a")
	private static WebElement cimWarningDeleteButton;
	public WebElement getcimWarningDeleteButton() {return cimWarningDeleteButton;}
	
	@FindBy(xpath = "//div[@class='x-grid-item-container']//tr//td[2]/div")
	private static List<WebElement> cimGrid;
	public List<WebElement> getcimGrid() {return cimGrid;}
	
	@FindBy(xpath = "//div[contains(@class,'horzOverflow ')]//div[contains(@id,'header-title')][text()='Cost Integration Manager (CIM)']")
	private static WebElement cimHeaderTitle;
	public WebElement getcimHeaderTitle() {return cimHeaderTitle;}
	
	@FindBy(xpath = "//div[contains(@id,'breadcrumbs')]//span[text()='Cost Integration Manager (CIM)']")
	private static WebElement cimBreadCrumbTitle;
	public WebElement getcimBreadCrumbTitle() {return cimBreadCrumbTitle;}
	

	@FindBy(xpath = "//div[contains(@class,'horzOverflow ')]//a[text()='Help']")
	private static WebElement cimHelpLink;
	public WebElement getcimHelpLink() {return cimHelpLink;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Name']")
	private WebElement cimNameHeader;
	public WebElement getcimNameHeader() {return cimNameHeader;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Description']")
	private WebElement cimDescHeader;
	public WebElement getcimDescHeader() {return cimDescHeader;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Last Start Time']")
	private WebElement cimLastStartTime;
	public WebElement getcimLastStartTime() {return cimLastStartTime;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Last End Time']")
	private WebElement cimLastEndTime;
	public WebElement getcimLastEndTime() {return cimLastEndTime;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Next Start Time']")
	private WebElement cimNextStartTime;
	public WebElement getcimNextStartTime() {return cimNextStartTime;}
	
	@FindBy(xpath = "//div[contains(@id,'cimmasterlist')]//span[text()='Calc Status']")
	private WebElement cimCalcStatusHeader;
	public WebElement getcimCalcStatusHeader() {return cimCalcStatusHeader;}
	
	@FindBy(xpath = "//span[text()='Name']/ancestor::div[contains(@class,'x-column-header-sort-ASC')]")
	private WebElement cimSortNameAsec;
	public WebElement getcimSortNameAsec() {return cimSortNameAsec;}
	
	@FindBy(xpath = "//span[text()='Name']/ancestor::div[contains(@class,'x-column-header-sort-DESC')]")
	private WebElement cimSortNameDesc;
	public WebElement getcimSortNameDesc() {return cimSortNameDesc;}
	
	@FindBy(xpath = "//span[text()='Description']/ancestor::div[contains(@class,'x-column-header-sort-ASC')]")
	private WebElement cimSortDescAsec;
	public WebElement getcimSortDescAsec() {return cimSortDescAsec;}
	
	@FindBy(xpath = "//span[text()='Description']/ancestor::div[contains(@class,'x-column-header-sort-DESC')]")
	private WebElement cimSortDescDesc;
	public WebElement getcimSortDescDesc() {return cimSortDescDesc;}
	
	@FindBy(xpath = "//span[text()='Last Start Time']/ancestor::div[contains(@class,'x-column-header-sort-ASC')]")
	private WebElement cimSortLastStartTimeAsec;
	public WebElement getcimSortLastStartTimeAsec() {return cimSortLastStartTimeAsec;}
	
	@FindBy(xpath = "//span[text()='Last Start Time']/ancestor::div[contains(@class,'x-column-header-sort-DESC')]")
	private WebElement cimSortLastStartTimeDesc;
	public WebElement getcimSortLastStartTimeDesc() {return cimSortLastStartTimeDesc;}
	
	@FindBy(xpath = "//span[text()='Last End Time']/ancestor::div[contains(@class,'x-column-header-sort-ASC')]")
	private WebElement cimSortLastEndTimeAsec;
	public WebElement getcimSortLastEndTimeAsec() {return cimSortLastEndTimeAsec;}
	
	@FindBy(xpath = "//span[text()='Last End Time']/ancestor::div[contains(@class,'x-column-header-sort-DESC')]")
	private WebElement cimSortLastEndTimeDesc;
	public WebElement getcimSortLastEndTimeDesc() {return cimSortLastEndTimeDesc;}
	
	@FindBy(xpath = "//span[text()='Next Start Time']/ancestor::div[contains(@class,'x-column-header-sort-ASC')]")
	private WebElement cimSortNextStartTimeAsc;
	public WebElement getcimSortNextStartTimeAsc() {return cimSortNextStartTimeAsc;}
	
	@FindBy(xpath = "//span[text()='Next Start Time']/ancestor::div[contains(@class,'x-column-header-sort-DESC')]")
	private WebElement cimSortNextStartTimeDesc;
	public WebElement getcimSortNextStartTimeDesc() {return cimSortNextStartTimeDesc;}
	
	@FindBy(xpath = "//span[text()='Calc Status']/ancestor::div[contains(@class,'x-column-header-sort-ASC')]")
	private WebElement cimSortCalcStatusAsec;
	public WebElement getcimSortCalcStatusAsec() {return cimSortCalcStatusAsec;}
	
	@FindBy(xpath = "//span[text()='Calc Status']/ancestor::div[contains(@class,'x-column-header-sort-DESC')]")
	private WebElement cimSortCalcStatusCalDesc;
	public WebElement getcimSortCalcStatusDesc() {return cimSortCalcStatusCalDesc;}
	
	@FindBy(name = "inputItem")
	private WebElement cimPaginInput;
	public WebElement getcimPaginInput() {return cimPaginInput;}
	
	@FindBy(xpath = "(//span[contains(@class,'pagging-tbar-go-button')]/ancestor::a)[1]")
	private WebElement cimPaginGoBtn;
	public WebElement getcimPaginGoBtn() {return cimPaginGoBtn;}
	
	@FindBy(xpath = "(//span[contains(@class,'pagging-tbar-next-button')]/ancestor::a)[1]")
	private WebElement cimPaginNextBtn;
	public WebElement getcimPaginNextBtn() {return cimPaginNextBtn;}
	
	@FindBy(xpath = "(//span[contains(@class,'pagging-tbar-last-button')]/ancestor::a)[1]")
	private WebElement cimPaginLastBtn;
	public WebElement getcimPaginLastBtn() {return cimPaginLastBtn;}
	
	@FindBy(xpath = "(//span[contains(@class,'previous-button')]/ancestor::a)[1]")
	private WebElement cimPaginPreviousBtn;
	public WebElement getcimPaginPreviousBtn() {return cimPaginPreviousBtn;}
	
	@FindBy(xpath = "(//span[contains(@class,'pagging-tbar-first-button')]/ancestor::a)[1]")
	private WebElement cimPaginPreviousFirstBtn;
	public WebElement getcimPaginPreviousFirstBtn() {return cimPaginPreviousFirstBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-title-item')][text()='Scheduled']")
	private WebElement cimScheduledPopUp;
	public WebElement getcimScheduledPopUp() {return cimScheduledPopUp;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-closable ')]//following::span[text()='Calculate Now'])")
	private List<WebElement> cimCalculateNowBtn;
	public List<WebElement> getcimCalculateNowBtn() {return cimCalculateNowBtn;}
	
	@FindBy(xpath = "//div[text()='Confirm Calculation']//following::span[text()='Yes']/ancestor::a")
	private WebElement cimCalculateNowConfirmBtn;
	public WebElement getcimCalculateNowConfirmBtn() {return cimCalculateNowConfirmBtn;}
	
	@FindBy(xpath = "//div[text()='Confirm Calculation']//following::span[text()='Cancel']/ancestor::a")
	private WebElement cimCalculateCancelConfirmBtn;
	public WebElement getcimCalculateCancelConfirmBtn() {return cimCalculateCancelConfirmBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-title-item')][text()='Delete Scheduled']")
	private WebElement cimDeleteScheduledPopUp;
	public WebElement getcimDeleteScheduledPopUp() {return cimDeleteScheduledPopUp;}
	
	@FindBy(xpath = "//div[contains(@class,'x-title-item')][text()='Scheduled']//following::span[text()='Delete']/ancestor::a")
	private WebElement cimDeleteScheduleBtn;
	public WebElement getcimDeleteScheduleBtn() {return cimDeleteScheduleBtn;}
	
	@FindBy(xpath = "//div[contains(@class,'x-title-item')][text()='Delete Scheduled']//following::span[text()='Delete']/ancestor::a")
	private WebElement cimDeleteScheduledBtn;
	public WebElement getcimDeleteScheduledBtn() {return cimDeleteScheduledBtn;}
	
	@FindBy(xpath = "//span[text()='Select which to delete:']")
	private WebElement cimDeleteSelectSchedule;
	public WebElement getcimDeleteSelectSchedule() {return cimDeleteSelectSchedule;}
	
	@FindBy(xpath = "//label[text()='Next scheduled only']")
	private WebElement cimDeleteNextSelectSchedule;
	public WebElement getcimDeleteNextSelectSchedule() {return cimDeleteNextSelectSchedule;}
	
	@FindBy(xpath = "//label[text()='Next scheduled AND all recurring']")
	private WebElement cimDeleteAllSchedule;
	public WebElement getcimDeleteAllSchedule() {return cimDeleteAllSchedule;}
	
	@FindBy(xpath = "(//span[text()='Custom Date & Time']/ancestor::a[contains(@class,'CIMButtonOptions')])[4]")
	private WebElement cimCustoDateTimeBtn;
	public WebElement getcimCustoDateTimeBtn() {return cimCustoDateTimeBtn;}
	
		
	@FindBy(xpath = "//input[@name='customDate']")
	private WebElement cimCustomDate;
	public WebElement getcimCustomDate() {return cimCustomDate;}
	
	@FindBy(name = "customTime")
	private WebElement customTime;
	public WebElement getcustomTime() {return customTime;}
	
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='12:00 am']/../li")
	private WebElement customTimeDrp;
	public WebElement getcustomTimeDrp() {return customTimeDrp;}
	
	@FindBy(xpath = "(//span[contains(text(),'Repeat Calculation')]//following::span[text()='Save & Close']/ancestor::a)[4]")
	private WebElement scheduleSaveCloseBtn;
	public WebElement getscheduleSaveCloseBtn() {return scheduleSaveCloseBtn;}
	
	@FindBy(xpath = "(//td[contains(@class,'x-datepicker-today')]//following::td/div)[1]")
	private WebElement customDayNext;
	public WebElement getcustomDayNext() {return customDayNext;}
	
	@FindBy(xpath = "//div[contains(@class,'x-timepicker')]//div[contains(@id,'listWrap')]/ul")
	private WebElement customTimeList;
	public WebElement getcustomTimeList() {return customTimeList;}
	
	@FindBy(xpath = "(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel'])[5]")
	private WebElement cancelWarning;
	public WebElement getcancelWarning() {return cancelWarning;}
	
	@FindBy(xpath = "(//div[text()='Calculation Status']//following::span[text()='Refresh'])")
	private WebElement calcRefreshBtn;
	public WebElement getcalcRefreshBtn() {return calcRefreshBtn;}
	
	@FindBy(xpath = "//input[@name='field']/..")
	private WebElement calcFieldBtn;
	public WebElement getcalcFieldBtn() {return calcFieldBtn;}
	
	@FindBy(xpath = "//input[@name='operator']/..")
	private WebElement calcOperatorBtn;
	public WebElement getcalcOperatorBtn() {return calcOperatorBtn;}
	
	@FindBy(xpath = "//input[@name='condition']/..")
	private WebElement calcConditionBtn;
	public WebElement getcalcConditionBtn() {return calcConditionBtn;}
	
	@FindBy(xpath = "//input[@name='valuedate1']/..")
	private WebElement calcFilterDate;
	public WebElement getcalcFilterDate() {return calcFilterDate;}
	
	@FindBy(xpath = "//input[@name='valuedate']")
	private WebElement calcFilterValDate;
	public WebElement getcalcFilterValDate() {return calcFilterValDate;}
	
	@FindBy(xpath = "//input[@name='valuedate2']/..")
	private WebElement calcAndDate;
	public WebElement getcalcAndDate() {return calcAndDate;}
	
	@FindBy(xpath = "//div[contains(@class,'boundlist')]/ul/li[text()='Name']/../li")
	private List<WebElement> calcFilterField;
	public List<WebElement> getcalcFilterField() {return calcFilterField;}
	
	@FindBy(xpath = "(//div[contains(@id,'specialtagcombo')]//following::div[contains(@class,'floating')]//ul/li[text()='Is']/../li)")
	private List<WebElement> calcFilterOperator;
	public List<WebElement> getcalcFilterOperator() {return calcFilterOperator;}
	
	@FindBy(xpath = "(//div[contains(@id,'specialtagcombo')]//following::div[contains(@class,'floating')]//ul/li[text()='Equal To']/../li)")
	private List<WebElement> calcFilterCondition;
	public List<WebElement> getcalcFilterCondition() {return calcFilterCondition;}
	
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//span[text() = 'Edit']")
	private WebElement statusFilterDialogFieldValueEdit;
	public WebElement getFilterDialogFieldValueEdit() {return statusFilterDialogFieldValueEdit;}
	    
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//span[text() = 'Update']")
	private WebElement statusFilterDialogFieldValueUpdate;
	public WebElement getstatusFilterDialogFieldValueUpdate() {return statusFilterDialogFieldValueUpdate;}
	    
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//span[text() = 'Remove All']")
	private WebElement statusFilterRemoveAllBtn;
	public WebElement getstatusstatusFilterRemoveAllBtn() {return statusFilterRemoveAllBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//span[text() = 'Remove']")
	private WebElement statusFilterRemoveBtn;
	public WebElement getstatusstatusFilterRemoveBtn() {return statusFilterRemoveAllBtn;}
	
	@FindBy(xpath = "//div[contains(@id,'filterwindow')]//span[text() = 'Cancel & Close']")
	private WebElement statusFilterCancelCloseBtn;
	public WebElement getstatusFilterCancelCloseBtn() {return statusFilterCancelCloseBtn;}
	
	@FindBy(xpath = "//div[text()='Calculation Status']//following::span[text()='Filter']/ancestor::a")
	private WebElement statusFilterBtn;
	public WebElement getstatusFilterBtn() {return statusFilterBtn;}
	
	@FindBy(xpath = "//div[text()='Calculation Status']//following::span[text()='Clear Filter']/ancestor::a")
	private WebElement statusClearFilterBtn;
	public WebElement getstatusClearFilterBtn() {return statusClearFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Users']//following::span[text()='Filter']")
	private WebElement usersFilterBtn;
	public WebElement getusersFilterBtn() {return usersFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Users']//following::span[text()='Clear Filter']")
	private WebElement usersClearFilterBtn;
	public WebElement getusersClearFilterBtn() {return usersClearFilterBtn;}
	
	@FindBy(xpath = "//h1[text()='Users']//following::span[text()='Edit']")
	private WebElement usersEditBtn;
	public WebElement getusersEditBtn() {return usersEditBtn;}
	
	@FindBy(xpath = "(//label[text()='Roles']//following::span[text()='Select'])[1]")
	private WebElement usersRoleSelectBtn;
	public WebElement getusersRoleSelectBtn() {return usersRoleSelectBtn;}
	
	@FindBy(xpath = "//div[text()='Roles']//following::span[text()='Save & Close']")
	private WebElement usersSaveCloseBtn;
	public WebElement getusersSaveCloseBtn() {return usersSaveCloseBtn;}
	
	@FindBy(xpath = "//div[text()='Roles']//following::span[text()='Edit']")
	private WebElement usersRoleEditBtn;
	public WebElement getusersRoleEditBtn() {return usersRoleEditBtn;}
	
	@FindBy(xpath = "//div[text()='Log Out']")
	private WebElement LogoutBtn;
	public WebElement getLogoutBtn() {return LogoutBtn;}
	
	@FindBy(xpath = "//input[@name='recurrenceInterval']")
	private List<WebElement> repeatCalcDropdown;
	public List<WebElement> getrepeatCalcDropdown() {return repeatCalcDropdown;}
	
	@FindBy(xpath = "//div[text()='Scheduled']")
	private WebElement schedulePopUp;
	public WebElement getschedulePopUp() {return schedulePopUp;}
	
	@FindBy(xpath = "(//div[text()='Scheduled']/following::span[text()='Repeats:']//following::div/div)[1]")
	private WebElement repeatsText;
	public WebElement getrepeatsText() {return repeatsText;}
	
	@FindBy(xpath = "(//div[text()='Scheduled']/following::span[text()='Cancel & Close'])")
	private WebElement scheduledPopUpCancelCloseBtn;
	public WebElement scheduledPopUpCancelCloseBtn() {return scheduledPopUpCancelCloseBtn;}
	
	@FindBy(xpath = "//input[@name='repeatFrequency']")
	private List<WebElement> repeatEveryInput;
	public List<WebElement> getRepeatEveryInput() {return repeatEveryInput;}
	
	@FindBy(xpath = "//input[@name='customRepeatInterval']")
	private List<WebElement> repeatInterval;
	public List<WebElement> getrepeatInterval() {return repeatInterval;}
	
	@FindBy(xpath = "//input[@name='endDate']")
	private List<WebElement> repeatEnds;
	public List<WebElement> getrepeatEnds() {return repeatEnds;}
	
	@FindBy(xpath = "//div[text()='Scheduled']//following::span[text()='Edit']")
	private WebElement scheduledEditBtn;
	public WebElement getScheduledEditBtn() {return scheduledEditBtn;}
	
	@FindBy(xpath = "//a[contains(@class,'windowbtn ')]//span[text()='Delete']")
	private WebElement calcDeletewarningBtn;
	public WebElement getcalcDeletewarningBtn() {return calcDeletewarningBtn;}
	
	@FindBy(xpath = "//a[@class='listhelpLnk'][text()='Calculation Status Screen >>']")
	private WebElement calcStatusPgeLink;
	public WebElement getcalcStatusPgeLink() {return calcStatusPgeLink;}
	
	@FindBy(name = "searchText")
	private WebElement calcStatusSearch;
	public WebElement getcalcStatusSearch() {return calcStatusSearch;}
	
	@FindBy(xpath = "//span[contains(@class,'consEnconSearch ')]")
	private WebElement calcStatusSearchIcon;
	public WebElement getcalcStatusSearchIcon() {return calcStatusSearchIcon;}
	
	@FindBy(xpath = "(//div[contains(@class,'hierarchyGrid ')])[2]")
	private WebElement selectedItemGrid;
	public WebElement getselectedItemGrid() {return selectedItemGrid;}
	
		
	@FindBy(xpath = "//div[contains(@id,'calculationstatus')]//following::div[text()='Calculation Status']")
	private WebElement calcStatusPage;
	public WebElement getcalcStatusPage() {return calcStatusPage;}
	
	
	@FindBy(xpath = "(//div[contains(@class,'hierarchyGrid ')])[2]//div/table//div")
	private List<WebElement> selectedCalcList;
	public List<WebElement> getselectedCalcList() {return selectedCalcList;}
	
	@FindBy(xpath = "//a[text()='View']")
	private List<WebElement> calcPageView;
	public List<WebElement> getcalcPageView() {return calcPageView;}
	
	@FindBy(xpath = "//span[text()='Download']")
	private List<WebElement> calcPageDownload;
	public List<WebElement> getcalcPageDownload() {return calcPageDownload;}
	
	@FindBy(xpath = "//a[contains(@class,'x-btn') and (@aria-disabled='false')]//span[contains(@class,'delBtn')]")
	private List<WebElement> calcPageDelete;
	public List<WebElement> getcalcPageDelete() {return calcPageDelete;}
}

