package webdriver.users;

//NOTE: This is a set of standard roles in ADS that is used exclusively for security roles testing.  For general use users, use Users instead.
public enum Roles {
    Application_Administrator("autoappadmin1", "P@$$w0rd123"),
    System_Administrator("autosysadmin1", "P@$$w0rd123"),
//    System_Administrator("sys", "P@ssword12345"),

    Security_Administrator("autosecuradmin1", "P@$$w0rd123"),

    
    /*
    Omkar 22/2/2024 : Someone seems to have changed the role while testing so adding back the 
                      role and changing the password as system does not allow with old password
   Data_Administrator("automationdataadmin1", "password"), */
    Data_Administrator("autodataadmin1", "P@$$w0rd123"),
    Cost_Analyst("autocostanalyst1", "P@$$w0rd123"),
    Costing_Department_Manager("autocostdeptmanag1", "P@$$w0rd123"),
    Contract_Analyst("autocontraanalyst1", "P@$$w0rd123"),

    Contract_Administrator("autocontradmin1", "P@$$w0rd123"),
    Contract_Reviewer("autocontrreviewer1", "P@$$w0rd123"),
    Episode_Analyst("autoepisanalyst1", "P@$$w0rd123"),
    Report_Administrator("autorepadmin1", "P@$$w0rd123"),

    AdHoc_Report_Designer("autoadhocrepdes1", "P@$$w0rd123"),
    Report_User("autorepuser1", "P@$$w0rd123"),
    Web_Intelligence_Designer("autowebIdesigner1", "P@$$w0rd123"),
    Web_Intelligence_User("autowebIuser1", "P@$$w0rd123"),

    Budgeting_User("autobudgetuser1", "P@$$w0rd123"),
    Analytics_Administrator("autoanalyadmin1", "P@$$w0rd123"),
    Analytics_Analyst("autoanalyanalyst1", "P@$$w0rd123"),
    Analytics_Designer("automationanalyticsdesign1", "P@$$w0rd123"),

    Analytics_Executive("autoanalyticsexec1", "P@$$w0rd123");
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
