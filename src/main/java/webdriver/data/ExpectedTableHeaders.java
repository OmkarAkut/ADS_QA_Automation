package webdriver.data;

public class ExpectedTableHeaders {

    //Reporting Tab
    public String[] expectedReportingTabAdHocReportDesignPageTableHeaders = {"Name", "Established"};
    public String[] expectedReportingTabReportMenuMaintenancePageTableHeaders = {"Name"};


    //Contracting Tab
    public String[] expectedContractingTabContractualAllowanceExportPageTableHeaders = {"Name", "Description"};


    //System Maintenance Tab
    public String[] expectedSystemMaintenanceTabUsersPageTableHeaders = {"ID", "First Name", "Last Name", "Display Name", "Disabled", "Locked",
            "Business Objects License", "Job Function"};
    public String[] expectedSystemMaintenanceTabRolesPageTableHeaders = {"Name", "Type", "Disabled"};
    public String[] expectedSystemMaintenanceTabTerminalServerSessionsPageTableHeaders = {"Name", "Established", "Terminal Server Name", "Decision Support User ID"};

    //Status Tab
    public String[] expectedStatusTabCalculationStatusPageTableHeaders = {"Scenario Name", "Category", "Type", "Progress", "Est Calc End Time",
    "Calc Status", "Log Status", "Shared Location", "View", "Download", "Records Processed", "Records Pending", "Calc End Time", "Duration",
            "Delete", "Cancel"};


}
