package webdriver.maps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import webdriver.maps.mapbuilder.MapConfig;

public class AeMap extends MapConfig {
	
	 @FindBy(id = "executionDetailsButton")
	    private WebElement execDetailsBtn;
	    public WebElement getexecDetailsBtn() {return execDetailsBtn;}
	    
	    @FindBy(id = "executionDetailsModalLabel")
	    private WebElement execDetailsPopUp;
	    public WebElement getexecDetailsPopUp() {return execDetailsPopUp;}
	    
	    @FindBy(id = "scheduledButton")
	    private WebElement ScheduledBtn;
	    public WebElement getScheduledBtn() {return ScheduledBtn;}
	    
	    @FindBy(xpath = "//h1[@id='scheduledModalLabel'][text()='Scheduled']")
	    private WebElement scheduleDetailsPopUp;
	    public WebElement getscheduleDetailsPopUp() {return scheduleDetailsPopUp;}
	    
	    @FindBy(xpath = "//h1[@id='executionDetailsModalLabel']//following::table[@class='table table-bordered table-hover processContainerTable']//tr[1]//th")
	    private List<WebElement> execDetailsHeader;
	    public List<WebElement> getexecDetailsHeader() {return execDetailsHeader;}
	    
	    @FindBy(xpath = "(//h1[@id='executionDetailsModalLabel']//following::button[contains(text(),'Close')])[1]")
	    private WebElement closeBtn;
	    public WebElement getcloseBtn() {return closeBtn;}
	    
	    @FindBy(xpath = "//div[@id='executionDetailsModal']//button[@class='btn-close']")
	    private WebElement closeIcon;
	    public WebElement getcloseIcon() {return closeIcon;}
	    
	    @FindBy(xpath = "//button[@id='initScheduleButton'][text()='Execute Job']")
	    private WebElement executeJobBtn;
	    public WebElement getexecuteJobBtn() {return executeJobBtn;}
	    
	    @FindBy(xpath = "//div[text()='Please select at least one task for execution']")
	    private WebElement executeNoTaskSelectMessage;
	    public WebElement getexecuteNoTaskSelectMessage() {return executeNoTaskSelectMessage;}
	    
	    @FindBy(xpath = "//div[text()='Please select at least one task for execution']//following::button[contains(text(),'Close')]")
	    private WebElement executeNoTaskCloseBtn;
	    public WebElement getexecuteNoTaskCloseBtn() {return executeNoTaskCloseBtn;}
	    
	    @FindBy(xpath = "(//input[@name='cbServiceName'])[2]")
	    private WebElement taskSelect;
	    public WebElement gettaskSelect() {return taskSelect;}
	    
	    @FindBy(xpath = "//h1[@id='scheduleModalLabel']")
	    private WebElement executeJobPopUp;
	    public WebElement getexecuteJobPopUp() {return executeJobPopUp;}
	    
	    @FindBy(xpath = "//input[@id='sjBatchName']")
	    private WebElement executeJobName;
	    public WebElement getexecuteJobName() {return executeJobName;}
	    

	    @FindBy(xpath = "//button[@id='sjBtnExecuteNow']")
	    private WebElement executeNowBtn;
	    public WebElement getexecuteNowBtn() {return executeNowBtn;}
	    
	    @FindBy(xpath = "//button[@id='sjBtnTomorrowMorning']")
	    private WebElement executeTomMorning;
	    public WebElement getexecuteTomMorning() {return executeTomMorning;}
	    
	    @FindBy(xpath = "//button[@id='sjBtnTomorrowAfternoon']")
	    private WebElement executeTomAfternoon;
	    public WebElement getexecuteTomAfternoon() {return executeTomAfternoon;}
	    
	    @FindBy(xpath = "//button[@id='sjBtnCustomDateTime']")
	    private WebElement executeCustomDateTime;
	    public WebElement getexecuteCustomDateTime() {return executeCustomDateTime;}
	    
	    @FindBy(xpath = "//label[text()='Recurrence']")
	    private WebElement executeRecurrence;
	    public WebElement getexecuteRecurrence() {return executeRecurrence;}
	    
	    @FindBy(xpath = "(//h1[@id='scheduleModalLabel']//following::button[@class='btn-close'])[1]")
	    private WebElement executePopUpCloseIcon;
	    public WebElement getexecutePopUpCloseIcon() {return executePopUpCloseIcon;}
	    
	    @FindBy(xpath = "(//h1[@id='scheduleModalLabel']//following::button[contains(text(),'Cancel')])[1]")
	    private WebElement executePopUpCancelBtn;
	    public WebElement getexecutePopUpCancelBtn() {return executePopUpCancelBtn;}
	    
	    @FindBy(xpath = "(//h1[@id='scheduleModalLabel']//following::button[contains(text(),'Save & Close')])[1]")
	    private WebElement executePopUpSaveCloseBtn;
	    public WebElement getexecutePopUpSaveCloseBtn() {return executePopUpSaveCloseBtn;}
	    
	    @FindBy(xpath = "(//h1[@id='scheduledModalLabel']//following::button[contains(text(),'Close')])[1]")
	    private WebElement schedulePopUpCloseBtn;
	    public WebElement getschedulePopUpCloseBtn() {return schedulePopUpCloseBtn;}
	    
	    @FindBy(xpath = "(//h1[@id='scheduledModalLabel']//following::button[@class='btn-close'])[1]")
	    private WebElement schedulePopUpCloseIcon;
	    public WebElement getschedulePopUpCloseIcon() {return schedulePopUpCloseIcon;}
	    
	    
	    @FindBy(xpath = "(//div[@class='modal-body'])[6]/strong")
	    private WebElement executeJobWarning;
	    public WebElement getexecuteJobWarning() {return executeJobWarning;}
	    
	    @FindBy(xpath = "//div[@class='modal-footer']//button[contains(text(),'Execute Now')]")
	    private WebElement executeJobNowBtn;
	    public WebElement getexecuteJobNowBtn() {return executeJobNowBtn;}
	    
	    @FindBy(xpath = "//*[text()='Job Created Successfully.']//following::div/button[contains(text(),'Close')]")
	    private WebElement jobCloseBtn;
	    public WebElement getjobCloseBtn() {return jobCloseBtn;}
	    
	    @FindBy(xpath = "//button[contains(text(),'Save & Close')]")
	    private WebElement executeJobSaveCloseBtn;
	    public WebElement getexecuteJobSaveCloseBtn() {return executeJobSaveCloseBtn;}
	    
	    @FindBy(id = "sjBtnCustomDateTime")
	    private WebElement customDateTimeBtn;
	    public WebElement getcustomDateTimeBtn() {return customDateTimeBtn;}
	    
	    @FindBy(id = "sjCustomDateTime")
	    private WebElement customDateTimeInput;
	    public WebElement getcustomDateTimeInput() {return customDateTimeInput;}
	    
	    @FindBy(id = "sjScheduleRecurrence")
	    private WebElement recurrenceDropdown;
	    public WebElement getrecurrenceDropdown() {return recurrenceDropdown;}
	    
	    @FindBy(xpath = "//*[text()='No jobs are currently scheduled.']")
	    private List<WebElement> noJobSchedule;
	    public List<WebElement> getnoJobSchedule() {return noJobSchedule;}
	    
	    @FindBy(xpath = "//table[contains(@class,'processContainerTable')]//tr/th")
	    private List<WebElement> jobMgrHeaders;
	    public List<WebElement> getjobMgrHeaders() {return jobMgrHeaders;}
	    
	    @FindBy(xpath = "//h1[@id='scheduledModalLabel']//following::table//tbody//tr//th//button[1]")
	    private List<WebElement> cancelScheduleBtn;
	    public List<WebElement> getcancelScheduleBtn() {return cancelScheduleBtn;}
}
