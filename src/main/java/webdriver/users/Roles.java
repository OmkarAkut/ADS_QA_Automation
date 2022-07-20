package webdriver.users;

//NOTE: This is a set of standard roles in ADS that is used exclusively for security roles testing.  For general use users, use Users instead.
public enum Roles {
    Application_Administrator("automationappadmin1", "password"),
    System_Administrator("automationsysadmin1", "password"),
    Security_Administrator("automationsecuradmin1", "password"),

    Data_Administrator("automationdataadmin1", "password"),
    Cost_Analyst("automationcostanalyst1", "password"),
    Costing_Department_Manager("automationcostdeptmanag1", "password"),
    Contract_Analyst("automationcontranalyst1", "password"),

    Contract_Administrator("automationcontradmin1", "password"),
    Contract_Reviewer("automationcontrreviewer1", "password"),
    Episode_Analyst("automationepisanalyst1", "password"),
    Report_Administrator("automationrepadmin1", "password"),

    AdHoc_Report_Designer("automationadhocrepdes1", "password"),
    Report_User("automationrepuser1", "password"),
    Web_Intelligence_Designer("automationwebInteldesigner1", "password"),
    Web_Intelligence_User("automationwebInteluser1", "password"),

    Budgeting_User("automationbudgetuser1", "password"),
    Analytics_Administrator("automationanalyticsadmin1", "password"),
    Analytics_Analyst("automationanalyticsanalyst1", "password"),
    Analytics_Designer("automationanalyticsdesign1", "password"),

    Analytics_Executive("automationanalyticsexec1", "password");
    //dbadminUser1("automationdbadmin1", "password")
    //Automation_Tester("testautomation", "password")


    // ===== Note: Do not alter below this line ===== //
    private final String username;
    private final String password;
    Roles(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
